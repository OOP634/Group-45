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

import in.co.online.food.delivery.dto.UserDTO;

public class UserDAOTest {
	
	@InjectMocks
	UserDAOImpl	userDAOImpl;
	
	@Mock
	Session session;
	
	@Mock
	Criteria criteria;
	
	@Mock
	org.hibernate.query.Query<UserDTO>	query;
	
	@Mock
	EntityManager	entityManager;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		// Success scenario
		UserDTO usr1 = new UserDTO();
		usr1.setId(1L);

		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.save(usr1)).thenReturn(1L);
		assertTrue(1L == userDAOImpl.add(usr1));
		
		// Failure scenario
		assertNull(userDAOImpl.add(null));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		UserDTO usr2 = new UserDTO();
		usr2.setId(1L);
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.get(UserDTO.class, 1L)).thenReturn(usr2);
		UserDTO actualUser = userDAOImpl.findBypk(1L);
		assertEquals(usr2.getId(), actualUser.getId());
		
		// Failure scenario
		UserDTO actualUser1 = userDAOImpl.findBypk(2L);
		assertNull(actualUser1);
	}
	
//	@Test
//	public void findByNameTest() {
//		// Success scenario
//		UserDTO cart1 = new UserDTO();
//		cart1.setId(1L);
//		cart1.setQuantity("10");
//		when(query.getResultList()).thenReturn(cart1);
//		UserDTO actualCart = userDAOImpl.findBypk(1L);
//		assertEquals(cart1.getId(), actualCart.getId());
//		assertEquals(actualCart.getQuantity(), cart1.getQuantity());
//		
//		// Failure scenario
//		UserDTO actualCart1 = userDAOImpl.findBypk(2L);
//		assertNull(actualCart1);
//	}
	
	@Test
	public void listTest() {
		UserDTO usr1 = new UserDTO();
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from UserDTO", UserDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(usrList);
		assertTrue(3 == userDAOImpl.list().size());
	}
	
	@Test
	public void listWithPaginationTest() {
		UserDTO usr1 = new UserDTO();
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from UserDTO", UserDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(usrList);
		assertTrue(3 == userDAOImpl.list(0, 0).size());
	}
	
	@Test
	public void searchTest() {
		UserDTO usr1 = new UserDTO();
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from UserDTO as u where 1=1 and u.id = 1", UserDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(usrList);
		assertTrue(3 == userDAOImpl.search(usr1).size());
	}
	
	@Test
	public void searchWithPaginationTest() {
		UserDTO usr1 = new UserDTO();
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		when(session.createQuery("from UserDTO as u where 1=1 and u.id = 1", UserDTO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(usrList);
		assertTrue(3 == userDAOImpl.search(usr1,0 , 0).size());
	}

}
