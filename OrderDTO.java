package in.co.online.food.delivery.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ORDERS")
@Setter
@Getter
public class OrderDTO extends BaseDTO {
	
	@Column(name="TOTAL",length = 225)
	private String total;

	@Column(name="NAME",length = 225)
	private String name;
	
	@Column(name="EMAIL",length = 225)
	private String email;
	
	@Column(name="MOBILE_NO",length = 225)
	private String mobileNo;
	
	@Column(name="ADDRESS1",length = 225)
	private String address1;
	
	@Column(name="ADDRESS2",length = 225)
	private String address2;
	
	@Column(name="CITY",length = 225)
	private String city;
	
	@Column(name="STATE",length = 225)
	private String state;
	
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="QUANTITY",length = 225)
	private String quantity;
	
	@Column(name="ORDER_ID")
	private long orderid;
	
	@ManyToOne
	@JoinColumn(name="PRODUCTS",nullable = false)
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
