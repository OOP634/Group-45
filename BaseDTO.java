package in.co.online.food.delivery.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDTO  implements DropdownList,Comparable<BaseDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	protected long id;

	@Column(name = "CREATED_BY")
	protected String createdBy;

	@Column(name = "MODIFIED_BY")
	protected String modifiedBy;

	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;

	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	

	@Override
	public int compareTo(BaseDTO o) {
		return getValue().compareTo(o.getValue());
	}
	
}
