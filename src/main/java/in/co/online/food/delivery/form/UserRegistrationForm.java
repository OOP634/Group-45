package in.co.online.food.delivery.form;

import javax.validation.constraints.NotEmpty;
import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.UserDTO;
import in.co.online.food.delivery.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Login is required")
	private String login;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	
	@NotEmpty(message = "Contact No is required")
	private String contactNo;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	@NotEmpty(message = "DOB is required")
	private String dob;
	

	@Override
	public BaseDTO getDTO() {
		UserDTO bean=new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmailId(email);
		bean.setPassword(password);
		bean.setContactNo(contactNo);
		bean.setGender(gender);
		bean.setLogin(login);
		bean.setDob(DataUtility.getDate(dob));
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}
	@Override
	public void populate(BaseDTO baseBean) {
		UserDTO bean=(UserDTO)baseBean;
		id=bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		email=bean.getEmailId();
		password=bean.getPassword();
		contactNo=bean.getContactNo();
		gender=bean.getGender();
		login=bean.getLogin();
		dob=DataUtility.getDateString(bean.getDob());
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
}
