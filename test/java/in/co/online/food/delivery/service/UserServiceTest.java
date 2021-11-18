package in.co.online.food.delivery.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.co.online.food.delivery.dao.UserDAOImpl;
import in.co.online.food.delivery.dto.UserDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl	userServiceImpl;
	
	@Mock
	UserDAOImpl	userDAOImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() throws DuplicateRecordException {
		// Success Scenario
		UserDTO UserDTO = new UserDTO();
		UserDTO.setEmailId("test@gmail.com");
		when(userDAOImpl.findByEmail("test@gmail.com")).thenReturn(null);
		when(userDAOImpl.add(UserDTO)).thenReturn(1L);
		assertEquals(1L, userServiceImpl.add(UserDTO));
		
		// Failure Scenario
		UserDTO UserDTO2 = new UserDTO();
		UserDTO2.setEmailId("test2@gmail.com");
		when(userDAOImpl.findByEmail("test2@gmail.com")).thenReturn(UserDTO2);
		Exception exception = assertThrows(DuplicateRecordException.class, () -> {
			userServiceImpl.add(UserDTO2);
		});
		String expectedMessage = "Email Id Already Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		UserDTO UserDTO = new UserDTO();
		UserDTO.setId(1L);
		UserDTO.setEmailId("test@gmail.com");
		
		when(userDAOImpl.findBypk(1L)).thenReturn(UserDTO);
		UserDTO actualCat = userServiceImpl.findBypk(1L);
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("test@gmail.com", actualCat.getEmailId());
		
		// Failure Scenario
		UserDTO failCatDTO = userDAOImpl.findBypk(2L);
		assertNull(failCatDTO);
	}
	
	@Test
	public void findByEmailTest() {
		// Success scenario
		UserDTO UserDTO = new UserDTO();
		UserDTO.setId(1L);
		UserDTO.setEmailId("test@gmail.com");
		
		when(userDAOImpl.findByEmail("test@gmail.com")).thenReturn(UserDTO);
		UserDTO actualCat = userServiceImpl.findByEmail("test@gmail.com");
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("test@gmail.com", actualCat.getEmailId());
		
		// Failure Scenario
		when(userDAOImpl.findByEmail("test@gmail.com")).thenReturn(null);
		UserDTO failCatDTO = userServiceImpl.findByEmail("test@gmail.com");
		assertNull(failCatDTO);
	}
	
	@Test
	public void getListTest() {
		// Success scenario
		UserDTO usr1 = new UserDTO();
		UserDTO usr2 = new UserDTO();
		UserDTO usr3 = new UserDTO();
		
		List<UserDTO> usrList = new ArrayList<UserDTO>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		when(userDAOImpl.list()).thenReturn(usrList);
		assertNotNull(userServiceImpl.list());
		assertEquals(3, userServiceImpl.list().size());
		
		// Failure scenario
		assertFalse(2 == userServiceImpl.list().size());
	}
	
	@Test
	public void testListWithPagination() {
		// Success scenario
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(new UserDTO());
		usrList.add(new UserDTO());
		usrList.add(new UserDTO());
		
		when(userDAOImpl.list(1, 3)).thenReturn(usrList);
		assertTrue(3 == userServiceImpl.list(1, 3).size());
		
		// Failure scenario
		when(userDAOImpl.list(2, 3)).thenReturn(null);
		assertTrue(null == userServiceImpl.list(2, 3));
	}
	
	@Test
	public void searchTest() {
		//Success scenario
		UserDTO usr1 = new UserDTO();
		usr1.setEmailId("test@gmail.com");
		usr1.setGender("male");
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setEmailId("test2@gmail.com");
		usr2.setGender("male");
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setEmailId("test3@gmail.com");
		usr3.setGender("male");
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		UserDTO queryCat = new UserDTO();
		queryCat.setGender("male");
		when(userDAOImpl.search(queryCat)).thenReturn(usrList);
		assertTrue(3 == userServiceImpl.search(queryCat).size());
		
		//Failure Scenario
		UserDTO queryCat2 = new UserDTO();
		queryCat2.setGender("female");
		when(userDAOImpl.search(queryCat2)).thenReturn(null);
		assertTrue(null == userServiceImpl.search(queryCat2));
	}
	
	@Test
	public void searchWithPaginationTest() {
		//Success scenario
		UserDTO usr1 = new UserDTO();
		usr1.setEmailId("test@gmail.com");
		usr1.setGender("male");
		usr1.setId(1L);
		
		UserDTO usr2 = new UserDTO();
		usr2.setEmailId("test2@gmail.com");
		usr2.setGender("male");
		usr2.setId(2L);
		
		UserDTO usr3 = new UserDTO();
		usr3.setEmailId("test3@gmail.com");
		usr3.setGender("male");
		usr3.setId(3L);
		
		List<UserDTO> usrList = new ArrayList<>();
		usrList.add(usr1);
		usrList.add(usr2);
		usrList.add(usr3);
		
		UserDTO queryCat = new UserDTO();
		queryCat.setGender("male");
		when(userDAOImpl.search(queryCat, 1, 3)).thenReturn(usrList);
		assertTrue(3 == userServiceImpl.search(queryCat, 1, 3).size());
		
		//Failure Scenario
		UserDTO queryCat2 = new UserDTO();
		queryCat2.setGender("female");
		when(userDAOImpl.search(queryCat2, 1, 3)).thenReturn(null);
		assertTrue(null == userServiceImpl.search(queryCat2, 1, 3));
	}
	
	@Test
	public void authenticationTest() {
		UserDTO user1 = new UserDTO();
		user1.setEmailId("test@gmail.com");
		when(userDAOImpl.authentication(user1)).thenReturn(user1);
		UserDTO actualUser = userServiceImpl.authentication(user1);
		assertNotNull(actualUser);
		assertTrue(user1.getEmailId().equals(actualUser.getEmailId()));
	}
	
	@Test
	public void changePasswordTest() {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmailId("test@gmail.com");
		userDTO.setPassword("Test@123");
		userDTO.setId(1L);
		
		when(userDAOImpl.findBypk(1L)).thenReturn(userDTO);
		boolean result = userServiceImpl.changePassword(1L, "Test@123", "Test@1234");
		assertTrue(true == result);
		
		boolean result1 = userServiceImpl.changePassword(2L, "Test@123", "Test@1234");
		assertTrue(false == result1);
	}

}
