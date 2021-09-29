package in.co.online.food.delivery.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.dto.UserDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.form.ProductForm;
import in.co.online.food.delivery.service.CategoryServiceInt;
import in.co.online.food.delivery.service.ProductServiceInt;
import in.co.online.food.delivery.util.DataUtility;


@Controller
@RequestMapping("/ctl/product")
public class ProductCtl extends BaseCtl {

	@Autowired
	private ProductServiceInt service;
	
	@Autowired
	private CategoryServiceInt categoryService;

	@ModelAttribute
	public void preload(Model model) {
		model.addAttribute("catList",categoryService.search(null));
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") ProductForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			ProductDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "product";
	}

	@PostMapping
	public String submit(@RequestParam("image") MultipartFile file,@Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/product";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "product";
				}
				ProductDTO bean = (ProductDTO) form.getDTO();
				bean.setImage(file.getBytes());
				bean.setCategory(categoryService.findBypk(bean.getCategoryId()));
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Product update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Product Added Successfully!!!!");
				}
				return "product";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "product";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ProductForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/product/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/product";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ProductDTO dto = new ProductDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ProductDTO dto = (ProductDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		

		List<ProductDTO> list = service.search(dto, pageNo, pageSize);
		List<ProductDTO> totallist = service.search(dto);
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
		return "productList";
	}
	
	
	@RequestMapping(value = "/user/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchListUser(@ModelAttribute("form") ProductForm form,
			@RequestParam(required = false) String operation, Long cid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/product/user/search";
		}
		
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} 

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;
		
		ProductDTO dto = (ProductDTO) form.getDTO();
	
		if(DataUtility.getLong(String.valueOf(cid))>0) {
			dto.setCategoryId(DataUtility.getLong(String.valueOf(cid)));
		}
	
		List<ProductDTO> list = service.search(dto, pageNo, pageSize);
		List<ProductDTO> totallist = service.search(dto);
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
		return "userProductList";
	}
	
	@GetMapping("/getImage/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");

		Blob blb=service.getImageById(id);
		
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

}
