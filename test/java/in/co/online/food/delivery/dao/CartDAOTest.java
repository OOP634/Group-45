package in.co.online.food.delivery.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jayway.jsonpath.Criteria;

import in.co.online.food.delivery.dto.CartDTO;

public class CartDAOTest {
	
	@InjectMocks
	CartDAOImpl	cartDAOImpl;

	@Mock
	Session session;
	
	@Mock
	Criteria criteria;
	
	@Mock
	org.hibernate.query.Query<CartDTO>	query;
	
	@Mock
	EntityManager	entityManager;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		// Success scenario
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);

		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.save(cart1)).thenReturn(1L);
		assertTrue(1L == cartDAOImpl.add(cart1));
		
		// Failure scenario
		assertNull(cartDAOImpl.add(null));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);
		cart1.setQuantity("10");
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.get(CartDTO.class, 1L)).thenReturn(cart1);
		CartDTO actualCart = cartDAOImpl.findBypk(1L);
		assertEquals(cart1.getId(), actualCart.getId());
		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
		
		// Failure scenario
		CartDTO actualCart1 = cartDAOImpl.findBypk(2L);
		assertNull(actualCart1);
	}
	
//	@Test
//	public void findByNameTest() {
//		// Success scenario
//		CartDTO cart1 = new CartDTO();
//		cart1.setId(1L);
//		cart1.setQuantity("10");
//		when(query.getResultList()).thenReturn(cart1);
//		CartDTO actualCart = cartDAOImpl.findBypk(1L);
//		assertEquals(cart1.getId(), actualCart.getId());
//		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
//		
//		// Failure scenario
//		CartDTO actualCart1 = cartDAOImpl.findBypk(2L);
//		assertNull(actualCart1);
//	}
	
	@Test
	public void listTest() {
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(2L);
		
		CartDTO cart3 = new CartDTO();
		cart3.setId(3L);
		
		List<CartDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CartDTO", CartDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == cartDAOImpl.list().size());
	}
	
	@Test
	public void listWithPaginationTest() {
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(2L);
		
		CartDTO cart3 = new CartDTO();
		cart3.setId(3L);
		
		List<CartDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CartDTO", CartDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == cartDAOImpl.list(0, 0).size());
	}
	
	@Test
	public void searchTest() {
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(2L);
		
		CartDTO cart3 = new CartDTO();
		cart3.setId(3L);
		
		List<CartDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CartDTO as u where 1=1 and u.id = 1", CartDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == cartDAOImpl.search(cart1).size());
	}
	
	@Test
	public void searchWithPaginationTest() {
		CartDTO cart1 = new CartDTO();
		cart1.setId(1L);
		
		CartDTO cart2 = new CartDTO();
		cart2.setId(2L);
		
		CartDTO cart3 = new CartDTO();
		cart3.setId(3L);
		
		List<CartDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CartDTO as u where 1=1 and u.id = 1", CartDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == cartDAOImpl.search(cart1,0 , 0).size());
	}

}
