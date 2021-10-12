package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.online.food.delivery.dto.OrderDTO;


public interface OrderDAOInt {

	public long add(OrderDTO dto);
	
	public void delete(OrderDTO dto);
	
	public OrderDTO findBypk(long pk);
	
	public OrderDTO findByName(String name);
	
	public void update(OrderDTO dto);
	
	public List<OrderDTO> list();
	
	public List<OrderDTO>list(int pageNo,int pageSize);
	
	public List<OrderDTO> search(OrderDTO dto);
	
	public List<OrderDTO> search(OrderDTO dto,int pageNo,int pageSize);
	
	
	
}
