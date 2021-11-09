package in.co.online.food.delivery.form;

import java.sql.Timestamp;

import in.co.online.food.delivery.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class BaseForm {

	protected long id;

	protected String createdBy;

	protected String modifiedBy;

	protected Timestamp createdDateTime;

	protected Timestamp modifiedDateTime;

	protected int pageNo = 1;

	protected int pageSize = 10;

	protected long[] ids;

	protected int listsize;

	protected int total;

	private String operation;
	private int pagenosize;

	

	public abstract BaseDTO getDTO();

	public abstract void populate(BaseDTO bDto);

}
