package in.co.online.food.delivery.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.co.online.food.delivery.dao.OrderDAOImpl;
import in.co.online.food.delivery.dto.OrderDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public class OrderServiceTest {
	
	@InjectMocks
	private OrderServiceImpl	orderServiceImpl;
	
	@Mock
	private OrderDAOImpl	orderDAOImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() throws DuplicateRecordException {
		//Success scenario
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setAddress1("address1");
		when(orderDAOImpl.add(orderDTO)).thenReturn(1L);
		assertTrue(1L == orderServiceImpl.add(orderDTO));
	}
	
	@Test
	public void findByPkTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setId(1L);
		orderDto.setAddress1("address1");
		when(orderDAOImpl.findBypk(1L)).thenReturn(orderDto);
		OrderDTO actualOrder = orderServiceImpl.findBypk(1L);
		assertNotNull(actualOrder);
		assertEquals(1L, actualOrder.getId());
		assertEquals("address1", actualOrder.getAddress1());
		
		//Failure scenario
		OrderDTO actualOrder2 = orderServiceImpl.findBypk(2L);
		assertNull(actualOrder2);
	}

	@Test
	public void findByNameTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setId(1L);
		orderDto.setName("order1");
		orderDto.setAddress1("address1");
		when(orderDAOImpl.findByName("order1")).thenReturn(orderDto);
		OrderDTO actualOrder = orderServiceImpl.findByName("order1");
		assertNotNull(actualOrder);
		assertEquals(1L, actualOrder.getId());
		assertEquals("address1", actualOrder.getAddress1());
		
		//Failure scenario
		OrderDTO actualOrder2 = orderServiceImpl.findByName("order2");
		assertNull(actualOrder2);
	}
	
	@Test
	public void listTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setId(1L);
		orderDto.setAddress1("address1");
		
		OrderDTO orderDto2 = new OrderDTO();
		orderDto2.setId(2L);
		orderDto2.setName("order2");
		orderDto2.setAddress1("address1");
		
		OrderDTO orderDto3 = new OrderDTO();
		orderDto3.setId(3L);
		orderDto3.setName("order3");
		orderDto3.setAddress1("address1");
		
		List<OrderDTO> orderList = new ArrayList<>();
		orderList.add(orderDto);
		orderList.add(orderDto2);
		orderList.add(orderDto3);
		
		when(orderDAOImpl.list()).thenReturn(orderList);
		List<OrderDTO> actualOrderList = orderServiceImpl.list();
		assertNotNull(actualOrderList);
		assertEquals(3, actualOrderList.size());
		
		//Failure scenario
		assertFalse(2 == actualOrderList.size());
	}
	
	@Test
	public void listPaginationTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setId(1L);
		orderDto.setAddress1("address1");
		
		OrderDTO orderDto2 = new OrderDTO();
		orderDto2.setId(2L);
		orderDto2.setName("order2");
		orderDto2.setAddress1("address1");
		
		OrderDTO orderDto3 = new OrderDTO();
		orderDto3.setId(3L);
		orderDto3.setName("order3");
		orderDto3.setAddress1("address1");
		
		List<OrderDTO> orderList = new ArrayList<>();
		orderList.add(orderDto);
		orderList.add(orderDto2);
		orderList.add(orderDto3);
		
		when(orderDAOImpl.list(1, 3)).thenReturn(orderList);
		List<OrderDTO> actualOrderList = orderServiceImpl.list(1, 3);
		assertNotNull(actualOrderList);
		assertEquals(3, actualOrderList.size());
		
		//Failure scenario
		assertFalse(2 == orderServiceImpl.list(2, 3).size());
	}
	
	@Test
	public void searchTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto.setAddress1("address1");
		
		OrderDTO orderDto2 = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto2.setName("order2");
		orderDto2.setAddress1("address1");
		
		OrderDTO orderDto3 = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto3.setName("order3");
		orderDto3.setAddress1("address1");
		
		List<OrderDTO> orderList = new ArrayList<>();
		orderList.add(orderDto);
		orderList.add(orderDto2);
		orderList.add(orderDto3);
		
		when(orderDAOImpl.search(orderDto)).thenReturn(orderList);
		List<OrderDTO> actualOrderList = orderServiceImpl.search(orderDto);
		assertNotNull(actualOrderList);
		assertEquals(3, actualOrderList.size());
		
		//Failure scenario
		assertFalse(2 == orderServiceImpl.search(orderDto).size());
	}
	
	@Test
	public void searchPaginationTest() {
		//Success scenario
		OrderDTO orderDto = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto.setAddress1("address1");
		
		OrderDTO orderDto2 = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto2.setName("order2");
		orderDto2.setAddress1("address1");
		
		OrderDTO orderDto3 = new OrderDTO();
		orderDto.setCity("Ohio");
		orderDto3.setName("order3");
		orderDto3.setAddress1("address1");
		
		List<OrderDTO> orderList = new ArrayList<>();
		orderList.add(orderDto);
		orderList.add(orderDto2);
		orderList.add(orderDto3);
		
		when(orderDAOImpl.search(orderDto, 1, 3)).thenReturn(orderList);
		List<OrderDTO> actualOrderList = orderServiceImpl.search(orderDto, 1, 3);
		assertNotNull(actualOrderList);
		assertEquals(3, actualOrderList.size());
		
		//Failure scenario
		assertFalse(2 == orderServiceImpl.search(orderDto, 2, 3).size());
	}


}
