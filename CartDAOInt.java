package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.online.food.delivery.dto.CartDTO;


public interface CartDAOInt {

	public long add(CartDTO dto);
	
	public void delete(CartDTO dto);
	
	public CartDTO findBypk(long pk);
	
	public CartDTO findByName(String name);
	
	public void update(CartDTO dto);
	
	public List<CartDTO> list();
	
	public List<CartDTO>list(int pageNo,int pageSize);
	
	public List<CartDTO> search(CartDTO dto);
	
	public List<CartDTO> search(CartDTO dto,int pageNo,int pageSize);
	
	
	
}
