package in.co.online.food.delivery.ctl;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
public class WelcomeCtl extends BaseCtl {


	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}
	
	@RequestMapping(value = {"/aboutUs" }, method = RequestMethod.GET)
	public String aboutUs(Model model) {
		return "aboutUs";
	}
	
	@RequestMapping(value = {"/contactUs" }, method = RequestMethod.GET)
	public String contactUs(Model model) {
		return "contactUs";
	}
	
	


}
