package in.co.online.food.delivery.ctl;

import java.util.Iterator;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.co.online.food.delivery.dto.CartDTO;
import in.co.online.food.delivery.dto.OrderDTO;
import in.co.online.food.delivery.dto.UserDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.form.OrderForm;
import in.co.online.food.delivery.service.CartServiceInt;
import in.co.online.food.delivery.service.OrderServiceInt;
import in.co.online.food.delivery.util.DataUtility;


@Controller
@RequestMapping("/ctl/order")
public class OrderCtl extends BaseCtl {

	@Autowired
	private OrderServiceInt service;
	
	@Autowired
	private CartServiceInt cartService;

	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") OrderForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			OrderDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		form.setName(uDto.getFirstName()+" "+uDto.getLastName());
		form.setEmail(uDto.getEmailId());
		form.setMobileNo(uDto.getContactNo());
		CartDTO cDto=new CartDTO();
		cDto.setUser(uDto);
		model.addAttribute("cList", cartService.search(cDto));
		return "order";
	}

	@PostMapping
	public String submit(@ModelAttribute("form") OrderForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/order";
		}
		
		if (OP_PAYMENT.equalsIgnoreCase(form.getOperation())) {
			OrderDTO bean = (OrderDTO) form.getDTO();
			session.setAttribute("orders", bean);
			return "payment";
		}
		
		try {
			if (OP_PLACE_ORDER.equalsIgnoreCase(form.getOperation())) {
				OrderDTO bean = (OrderDTO) session.getAttribute("orders");
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				CartDTO cDto=new CartDTO();
				cDto.setUser(uDto);
				List<CartDTO> cList= cartService.search(cDto);
				System.out.println("Cart List Size==="+cList.size());
				bean.setOrderid(DataUtility.getRandom());
				Iterator<CartDTO> it=cList.iterator();
				model.addAttribute("cartList",cList);
				model.addAttribute("orderDetail",bean);
				while (it.hasNext()) {
					System.out.println("In loop");
					CartDTO cartDTO = (CartDTO) it.next();
					bean.setProduct(cartDTO.getProduct());
					bean.setUserId(uDto.getId());
					bean.setQuantity(cartDTO.getQuantity());
					bean.setTotal(String.valueOf(DataUtility.getLong(cartDTO.getQuantity())*DataUtility.getLong(cartDTO.getPrice())));
					service.add(bean);
					cartService.delete(cartDTO);
				}
				return "success";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "order";
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") OrderForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/order/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/order";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					OrderDTO dto = new OrderDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		OrderDTO dto = (OrderDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setUserId(uDto.getId());
		}
		

		List<OrderDTO> list = service.search(dto, pageNo, pageSize);
		List<OrderDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "orderList";
	}
	


}
