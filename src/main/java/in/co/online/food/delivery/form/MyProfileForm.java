package in.co.online.food.delivery.form;
import javax.validation.constraints.NotEmpty;

import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "MobileNo is required")
	private String mobileNo;
	
	
	public BaseDTO getDTO() {
		UserDTO entity = new UserDTO();
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		return entity;
	}

	
	public void populate(BaseDTO bDto) {
		UserDTO entity = (UserDTO) bDto;
		firstName = entity.getFirstName();
		lastName = entity.getLastName();

	}

	

}
