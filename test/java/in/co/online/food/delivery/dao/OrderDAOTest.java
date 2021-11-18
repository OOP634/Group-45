package in.co.online.food.delivery.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jayway.jsonpath.Criteria;

import in.co.online.food.delivery.dto.OrderDTO;

public class OrderDAOTest {
	
	@InjectMocks
	OrderDAOImpl	orderDAOImpl;
	
	@Mock
	Session session;
	
	@Mock
	Criteria criteria;
	
	@Mock
	org.hibernate.query.Query<OrderDTO>	query;
	
	@Mock
	EntityManager	entityManager;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		// Success scenario
		OrderDTO cart1 = new OrderDTO();
		cart1.setId(1L);

		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.save(cart1)).thenReturn(1L);
		assertTrue(1L == orderDAOImpl.add(cart1));
		
		// Failure scenario
		assertNull(orderDAOImpl.add(null));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		OrderDTO ord1 = new OrderDTO();
		ord1.setId(1L);
		ord1.setQuantity("10");
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.get(OrderDTO.class, 1L)).thenReturn(ord1);
		OrderDTO actualord = orderDAOImpl.findBypk(1L);
		assertEquals(ord1.getId(), actualord.getId());
		assertEquals(actualord.getQuantity(), ord1.getQuantity());
		
		// Failure scenario
		OrderDTO actualord1 = orderDAOImpl.findBypk(2L);
		assertNull(actualord1);
	}
	
//	@Test
//	public void findByNameTest() {
//		// Success scenario
//		OrderDTO cart1 = new OrderDTO();
//		cart1.setId(1L);
//		cart1.setQuantity("10");
//		when(query.getResultList()).thenReturn(cart1);
//		OrderDTO actualCart = orderDAOImpl.findBypk(1L);
//		assertEquals(cart1.getId(), actualCart.getId());
//		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
//		
//		// Failure scenario
//		OrderDTO actualCart1 = orderDAOImpl.findBypk(2L);
//		assertNull(actualCart1);
//	}
	
	@Test
	public void listTest() {
		OrderDTO ord1 = new OrderDTO();
		ord1.setId(1L);
		
		OrderDTO ord2 = new OrderDTO();
		ord2.setId(2L);
		
		OrderDTO ord3 = new OrderDTO();
		ord3.setId(3L);
		
		List<OrderDTO> ordList = new ArrayList<>();
		ordList.add(ord1);
		ordList.add(ord2);
		ordList.add(ord3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from OrderDTO", OrderDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(ordList);
		assertTrue(3 == orderDAOImpl.list().size());
	}
	
	@Test
	public void listWithPaginationTest() {
		OrderDTO ord1 = new OrderDTO();
		ord1.setId(1L);
		
		OrderDTO ord2 = new OrderDTO();
		ord2.setId(2L);
		
		OrderDTO ord3 = new OrderDTO();
		ord3.setId(3L);
		
		List<OrderDTO> ordList = new ArrayList<>();
		ordList.add(ord1);
		ordList.add(ord2);
		ordList.add(ord3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from OrderDTO", OrderDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(ordList);
		assertTrue(3 == orderDAOImpl.list(0, 0).size());
	}
	
	@Test
	public void searchTest() {
		OrderDTO ord1 = new OrderDTO();
		ord1.setId(1L);
		
		OrderDTO ord2 = new OrderDTO();
		ord2.setId(2L);
		
		OrderDTO ord3 = new OrderDTO();
		ord3.setId(3L);
		
		List<OrderDTO> ordList = new ArrayList<>();
		ordList.add(ord1);
		ordList.add(ord2);
		ordList.add(ord3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from OrderDTO as u where 1=1 and u.id = 1", OrderDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(ordList);
		assertTrue(3 == orderDAOImpl.search(ord1).size());
	}
	
	@Test
	public void searchWithPaginationTest() {
		OrderDTO ord1 = new OrderDTO();
		ord1.setId(1L);
		
		OrderDTO ord2 = new OrderDTO();
		ord2.setId(2L);
		
		OrderDTO ord3 = new OrderDTO();
		ord3.setId(3L);
		
		List<OrderDTO> ordList = new ArrayList<>();
		ordList.add(ord1);
		ordList.add(ord2);
		ordList.add(ord3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from OrderDTO as u where 1=1 and u.id = 1", OrderDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(ordList);
		assertTrue(3 == orderDAOImpl.search(ord1,0 , 0).size());
	}

}
