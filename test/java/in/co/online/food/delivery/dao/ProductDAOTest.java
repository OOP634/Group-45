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

import in.co.online.food.delivery.dto.ProductDTO;

public class ProductDAOTest {
	
	@InjectMocks
	ProductDAOImpl	productDAOImpl;
	
	@Mock
	Session session;
	
	@Mock
	Criteria criteria;
	
	@Mock
	org.hibernate.query.Query<ProductDTO>	query;
	
	@Mock
	EntityManager	entityManager;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		// Success scenario
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);

		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.save(prod1)).thenReturn(1L);
		assertTrue(1L == productDAOImpl.add(prod1));
		
		// Failure scenario
		assertNull(productDAOImpl.add(null));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.get(ProductDTO.class, 1L)).thenReturn(prod1);
		ProductDTO actualProd = productDAOImpl.findBypk(1L);
		assertEquals(prod1.getId(), actualProd.getId());
		
		// Failure scenario
		ProductDTO actualProd1 = productDAOImpl.findBypk(2L);
		assertNull(actualProd1);
	}
	
//	@Test
//	public void findByNameTest() {
//		// Success scenario
//		ProductDTO cart1 = new ProductDTO();
//		cart1.setId(1L);
//		cart1.setQuantity("10");
//		when(query.getResultList()).thenReturn(cart1);
//		ProductDTO actualCart = productDAOImpl.findBypk(1L);
//		assertEquals(cart1.getId(), actualCart.getId());
//		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
//		
//		// Failure scenario
//		ProductDTO actualCart1 = productDAOImpl.findBypk(2L);
//		assertNull(actualCart1);
//	}
	
	@Test
	public void listTest() {
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from ProductDTO", ProductDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(prodList);
		assertTrue(3 == productDAOImpl.list().size());
	}
	
	@Test
	public void listWithPaginationTest() {
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from ProductDTO", ProductDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(prodList);
		assertTrue(3 == productDAOImpl.list(0, 0).size());
	}
	
	@Test
	public void searchTest() {
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from ProductDTO as u where 1=1 and u.id = 1", ProductDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(prodList);
		assertTrue(3 == productDAOImpl.search(prod1).size());
	}
	
	@Test
	public void searchWithPaginationTest() {
		ProductDTO prod1 = new ProductDTO();
		prod1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from ProductDTO as u where 1=1 and u.id = 1", ProductDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(prodList);
		assertTrue(3 == productDAOImpl.search(prod1,0 , 0).size());
	}

}
