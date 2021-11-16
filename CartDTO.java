package in.co.online.food.delivery.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CART")
@Getter
@Setter
public class CartDTO extends BaseDTO {

	@Column(name = "QUANTITY",length = 225)
	private String quantity;
	@Column(name = "PRICE",length = 225)
	private String price;
	@Column(name = "TOTAL_PRICE",length = 225)
	private String totalPrice;
	
	@ManyToOne
	@JoinColumn(name="USER",nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT",nullable = false)
	private ProductDTO product;
	

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
