package in.co.online.food.delivery.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.online.food.delivery.dto.CategoryDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public interface CategoryServiceInt {

	public long add(CategoryDTO dto) throws DuplicateRecordException;

	public void delete(CategoryDTO dto);

	public CategoryDTO findBypk(long pk);

	public CategoryDTO findByName(String name);

	public void update(CategoryDTO dto) throws DuplicateRecordException;

	public List<CategoryDTO> list();

	public List<CategoryDTO> list(int pageNo, int pageSize);

	public List<CategoryDTO> search(CategoryDTO dto);

	public List<CategoryDTO> search(CategoryDTO dto, int pageNo, int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;

}
