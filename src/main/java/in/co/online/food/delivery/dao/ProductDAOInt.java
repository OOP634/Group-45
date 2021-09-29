package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.online.food.delivery.dto.ProductDTO;


public interface ProductDAOInt {

	public long add(ProductDTO dto);
	
	public void delete(ProductDTO dto);
	
	public ProductDTO findBypk(long pk);
	
	public ProductDTO findByName(String name);
	
	public void update(ProductDTO dto);
	
	public List<ProductDTO> list();
	
	public List<ProductDTO>list(int pageNo,int pageSize);
	
	public List<ProductDTO> search(ProductDTO dto);
	
	public List<ProductDTO> search(ProductDTO dto,int pageNo,int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;
	
	
}
