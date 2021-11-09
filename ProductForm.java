package in.co.online.food.delivery.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.CategoryDTO;
import in.co.online.food.delivery.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Price is required")
	private String price;
	
	@Min(value = 1,message = "Category is required")
	private long categoryId;
	
	@NotEmpty(message = "Description is required")
	private String description;
	
	
	private MultipartFile image;
	

	@Override
	public BaseDTO getDTO() {
		ProductDTO bean=new ProductDTO();
		bean.setName(name);
		bean.setPrice(price);
		bean.setCategoryId(categoryId);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ProductDTO bean=(ProductDTO) bdto;
		name=bean.getName();
		categoryId=bean.getCategoryId();
		price=bean.getPrice();
		description=bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
