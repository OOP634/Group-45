package in.co.online.food.delivery.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
public class ProductDTO extends BaseDTO {

	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	@Column(name="PRICE",length = 225)
	private String price;
	@Column(name="IMAGE",columnDefinition = "LONGBLOB")
	private byte[] image;
	
	@Transient
	private long categoryId;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY",nullable = false)
	private CategoryDTO category;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
	private Set<CartDTO> carts;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
	private Set<OrderDTO> orders;
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}
	@Override
	public String getValue() {
		return name;
	}

}
