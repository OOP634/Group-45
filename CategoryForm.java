package in.co.online.food.delivery.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.online.food.delivery.dto.BaseDTO;
import in.co.online.food.delivery.dto.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Description is required")
	private String description;
	
	private MultipartFile image;
	

	@Override
	public BaseDTO getDTO() {
		CategoryDTO bean=new CategoryDTO();
		bean.setName(name);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		CategoryDTO bean=(CategoryDTO) bdto;
		name=bean.getName();
		description=bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
