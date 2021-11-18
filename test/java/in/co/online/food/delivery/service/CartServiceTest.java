package in.co.online.food.delivery.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.co.online.food.delivery.dao.CartDAOImpl;
import in.co.online.food.delivery.dto.CartDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public class CartServiceTest {
	
	@InjectMocks
	CartServiceImpl	cartServiceImpl;
	
	@Mock
	CartDAOImpl	cartDAOImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() throws DuplicateRecordException {
		// Success scenario
		CartDTO cart = new CartDTO();
		when(cartDAOImpl.add(cart)).thenReturn(1L);
		assertEquals(1L, cartServiceImpl.add(cart));
	}
	
	@Test
	public void deleteTest() {
		CartDTO cart = new CartDTO();
		cartServiceImpl.delete(cart);
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		when(cartDAOImpl.findBypk(1)).thenReturn(cart);
		assertNotNull(cartServiceImpl.findBypk(1));
		CartDTO cart1 = cartServiceImpl.findBypk(1);
		assertEquals(cart1.getId(), 1L);
		assertEquals(cart1.getPrice(), "300");
		assertEquals(cart1.getQuantity(), "1");
	
		//Failure scenario
		assertNull(cartServiceImpl.findBypk(-1L));
	}
	
	@Test
	public void findByNameTest() {
		// Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		when(cartDAOImpl.findByName("cart1")).thenReturn(cart);
		assertNotNull(cartServiceImpl.findByName("cart1"));
		CartDTO cart1 = cartServiceImpl.findByName("cart1");
		assertEquals(cart1.getId(), 1L);
		assertEquals(cart1.getPrice(), "300");
		assertEquals(cart1.getQuantity(), "1");
		
		//Failure scenario
		assertNull(cartServiceImpl.findByName("test cart"));
	}
	
	@Test
	public void getListTest() {
		//Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		CartDTO cart1 = new CartDTO();
		cart1.setId(2L);
		cart1.setPrice("300");
		cart1.setQuantity("1");
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(3L);
		cart2.setPrice("300");
		cart2.setQuantity("1");
		
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		cartList.add(cart);
		cartList.add(cart1);
		cartList.add(cart2);
		
		when(cartDAOImpl.list()).thenReturn(cartList);
		assertNotNull(cartServiceImpl.list());
		assertEquals(cartServiceImpl.list().size(), 3);
		
		//Failure scenario
		assertFalse(2 == cartServiceImpl.list().size());
	}
	
	@Test
	public void getListPaginationTest() {
		//Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		CartDTO cart1 = new CartDTO();
		cart1.setId(2L);
		cart1.setPrice("300");
		cart1.setQuantity("1");
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(3L);
		cart2.setPrice("300");
		cart2.setQuantity("1");
		
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		cartList.add(cart);
		cartList.add(cart1);
		cartList.add(cart2);
		
		when(cartDAOImpl.list(1, 3)).thenReturn(cartList);
		assertNotNull(cartServiceImpl.list(1, 3));
		assertEquals(cartServiceImpl.list(1, 3).size(), 3);
		
		//Failure scenario
		assertFalse(2 == cartServiceImpl.list(2, 3).size());
	}
	
	@Test
	public void getSearchTest() {
		//Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		CartDTO cart1 = new CartDTO();
		cart1.setId(2L);
		cart1.setPrice("300");
		cart1.setQuantity("1");
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(3L);
		cart2.setPrice("300");
		cart2.setQuantity("1");
		
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		cartList.add(cart);
		cartList.add(cart1);
		cartList.add(cart2);
		
		when(cartDAOImpl.search(cart1)).thenReturn(cartList);
		assertNotNull(cartServiceImpl.search(cart1));
		assertEquals(cartServiceImpl.search(cart1).size(), 3);
		
		//Failure scenario
		assertFalse(2 == cartServiceImpl.search(cart1).size());
	}
	
	@Test
	public void getSearchPaginationTest() {
		//Success scenario
		CartDTO cart = new CartDTO();
		cart.setId(1L);
		cart.setPrice("300");
		cart.setQuantity("1");
		
		CartDTO cart1 = new CartDTO();
		cart1.setId(2L);
		cart1.setPrice("300");
		cart1.setQuantity("1");
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(3L);
		cart2.setPrice("300");
		cart2.setQuantity("1");
		
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		cartList.add(cart);
		cartList.add(cart1);
		cartList.add(cart2);
		
		when(cartDAOImpl.search(cart1, 1, 3)).thenReturn(cartList);
		assertNotNull(cartServiceImpl.search(cart1, 1, 3));
		assertEquals(cartServiceImpl.search(cart1, 1, 3).size(), 3);
		
		//Failure scenario
		assertFalse(2 == cartServiceImpl.search(cart1, 2, 3).size());
	}

}
