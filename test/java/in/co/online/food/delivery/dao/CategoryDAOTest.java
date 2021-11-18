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

import in.co.online.food.delivery.dto.CategoryDTO;

public class CategoryDAOTest {
	
	@InjectMocks
	CategoryDAOImpl	categoryDAOImpl;

	@Mock
	Session session;
	
	@Mock
	Criteria criteria;
	
	@Mock
	org.hibernate.query.Query<CategoryDTO>	query;
	
	@Mock
	EntityManager	entityManager;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		// Success scenario
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setId(1L);

		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.save(cat1)).thenReturn(1L);
		assertTrue(1L == categoryDAOImpl.add(cat1));
		
		// Failure scenario
		assertNull(categoryDAOImpl.add(null));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		CategoryDTO cart1 = new CategoryDTO();
		cart1.setId(1L);
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.get(CategoryDTO.class, 1L)).thenReturn(cart1);
		CategoryDTO actualCart = categoryDAOImpl.findBypk(1L);
		assertEquals(cart1.getId(), actualCart.getId());
		
		// Failure scenario
		CategoryDTO actualCart1 = categoryDAOImpl.findBypk(2L);
		assertNull(actualCart1);
	}
	
//	@Test
//	public void findByNameTest() {
//		// Success scenario
//		CategoryDTO cart1 = new CategoryDTO();
//		cart1.setId(1L);
//		cart1.setQuantity("10");
//		when(query.getResultList()).thenReturn(cart1);
//		CategoryDTO actualCart = cartDAOImpl.findBypk(1L);
//		assertEquals(cart1.getId(), actualCart.getId());
//		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
//		
//		// Failure scenario
//		CategoryDTO actualCart1 = cartDAOImpl.findBypk(2L);
//		assertNull(actualCart1);
//	}
	
	@Test
	public void listTest() {
		CategoryDTO cart1 = new CategoryDTO();
		cart1.setId(1L);
		
		CategoryDTO cart2 = new CategoryDTO();
		cart2.setId(2L);
		
		CategoryDTO cart3 = new CategoryDTO();
		cart3.setId(3L);
		
		List<CategoryDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CategoryDTO", CategoryDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == categoryDAOImpl.list().size());
	}
	
	@Test
	public void listWithPaginationTest() {
		CategoryDTO cart1 = new CategoryDTO();
		cart1.setId(1L);
		
		CategoryDTO cart2 = new CategoryDTO();
		cart2.setId(2L);
		
		CategoryDTO cart3 = new CategoryDTO();
		cart3.setId(3L);
		
		List<CategoryDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CategoryDTO", CategoryDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == categoryDAOImpl.list(0, 0).size());
	}
	
	@Test
	public void searchTest() {
		CategoryDTO cart1 = new CategoryDTO();
		cart1.setId(1L);
		
		CategoryDTO cart2 = new CategoryDTO();
		cart2.setId(2L);
		
		CategoryDTO cart3 = new CategoryDTO();
		cart3.setId(3L);
		
		List<CategoryDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CategoryDTO as u where 1=1 and u.id = 1", CategoryDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == categoryDAOImpl.search(cart1).size());
	}
	
	@Test
	public void searchWithPaginationTest() {
		CategoryDTO cart1 = new CategoryDTO();
		cart1.setId(1L);
		
		CategoryDTO cart2 = new CategoryDTO();
		cart2.setId(2L);
		
		CategoryDTO cart3 = new CategoryDTO();
		cart3.setId(3L);
		
		List<CategoryDTO> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		cartList.add(cart3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from CategoryDTO as u where 1=1 and u.id = 1", CategoryDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(cartList);
		assertTrue(3 == categoryDAOImpl.search(cart1,0 , 0).size());
	}

}
