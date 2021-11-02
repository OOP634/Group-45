package in.co.online.food.delivery.form;


import javax.validation.constraints.NotEmpty;

import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Email is required")
	private String email;

	@Override
	public BaseDTO getDTO() {
		UserDTO dto = new UserDTO();
		dto.setEmailId(email);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		
	}

}
