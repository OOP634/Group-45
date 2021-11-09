package in.co.online.food.delivery.form;


import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.CartDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForm extends BaseForm {

	private String[] quantity;


	@Override
	public BaseDTO getDTO() {
		CartDTO dto = new CartDTO();
		dto.setId(id);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		CartDTO dto = (CartDTO) bDto;
		id=dto.getId();
	}

}
