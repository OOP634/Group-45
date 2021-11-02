package in.co.online.food.delivery.form;


import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.OrderDTO;
import in.co.online.food.delivery.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm extends BaseForm {
	
	private String name;
	private String email;
	private String mobileNo;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String orderId;

	@Override
	public BaseDTO getDTO() {
		OrderDTO dto=new OrderDTO();
		dto.setId(id);
		dto.setOrderid(DataUtility.getLong(orderId));
		dto.setName(name);
		dto.setEmail(email);
		dto.setMobileNo(mobileNo);
		dto.setAddress1(address1);
		dto.setAddress2(address2);
		dto.setCity(city);
		dto.setState(state);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		OrderDTO dto=(OrderDTO)bDto;
		id=dto.getId();
		orderId=String.valueOf(dto.getOrderid());
		name=dto.getName();
		email=dto.getEmail();
		mobileNo=dto.getMobileNo();
		address1=dto.getAddress1();
		address2=dto.getAddress2();
		city=dto.getCity();
		state=dto.getState();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
