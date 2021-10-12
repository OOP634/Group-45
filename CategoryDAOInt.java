package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.online.food.delivery.dto.CategoryDTO;


public interface CategoryDAOInt {

	public long add(CategoryDTO dto);
	
	public void delete(CategoryDTO dto);
	
	public CategoryDTO findBypk(long pk);
	
	public CategoryDTO findByName(String name);
	
	public void update(CategoryDTO dto);
	
	public List<CategoryDTO> list();
	
	public List<CategoryDTO>list(int pageNo,int pageSize);
	
	public List<CategoryDTO> search(CategoryDTO dto);
	
	public List<CategoryDTO> search(CategoryDTO dto,int pageNo,int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;
	
	
}
