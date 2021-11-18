package in.co.online.food.delivery.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mysql.cj.jdbc.Blob;

import in.co.online.food.delivery.dao.CategoryDAOImpl;
import in.co.online.food.delivery.dto.CategoryDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
	@Mock
	private CategoryDAOImpl	categoryDAOImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() throws DuplicateRecordException {
		// Success Scenario
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName("Cat1");
		when(categoryDAOImpl.findByName("Cat1")).thenReturn(null);
		when(categoryDAOImpl.add(categoryDTO)).thenReturn(1L);
		assertEquals(1L, categoryServiceImpl.add(categoryDTO));
		
		// Failure Scenario
		CategoryDTO categoryDTO2 = new CategoryDTO();
		categoryDTO2.setName("Cat2");
		when(categoryDAOImpl.findByName("Cat2")).thenReturn(categoryDTO2);
		Exception exception = assertThrows(DuplicateRecordException.class, () -> {
			categoryServiceImpl.add(categoryDTO2);
		});
		String expectedMessage = "Name Already Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setName("cat1");
		categoryDTO.setDescription("desc");
		
		when(categoryDAOImpl.findBypk(1L)).thenReturn(categoryDTO);
		CategoryDTO actualCat = categoryServiceImpl.findBypk(1L);
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("cat1", actualCat.getName());
		assertEquals("desc", actualCat.getDescription());
		
		// Failure Scenario
		CategoryDTO failCatDTO = categoryDAOImpl.findBypk(2L);
		assertNull(failCatDTO);
	}
	
	@Test
	public void findByNameTest() {
		// Success scenario
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setName("cat1");
		categoryDTO.setDescription("desc");
		
		when(categoryDAOImpl.findByName("cat1")).thenReturn(categoryDTO);
		CategoryDTO actualCat = categoryServiceImpl.findByName("cat1");
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("cat1", actualCat.getName());
		assertEquals("desc", actualCat.getDescription());
		
		// Failure Scenario
		when(categoryDAOImpl.findByName("Cat2")).thenReturn(null);
		CategoryDTO failCatDTO = categoryServiceImpl.findByName("Cat2");
		assertNull(failCatDTO);
	}
	
	@Test
	public void getListTest() {
		// Success scenario
		CategoryDTO cat1 = new CategoryDTO();
		CategoryDTO cat2 = new CategoryDTO();
		CategoryDTO cat3 = new CategoryDTO();
		
		List<CategoryDTO> catList = new ArrayList<CategoryDTO>();
		catList.add(cat1);
		catList.add(cat2);
		catList.add(cat3);
		when(categoryDAOImpl.list()).thenReturn(catList);
		assertNotNull(categoryServiceImpl.list());
		assertEquals(3, categoryServiceImpl.list().size());
		
		// Failure scenario
		assertFalse(2 == categoryServiceImpl.list().size());
	}
	
	@Test
	public void testListWithPagination() {
		// Success scenario
		List<CategoryDTO> catList = new ArrayList<>();
		catList.add(new CategoryDTO());
		catList.add(new CategoryDTO());
		catList.add(new CategoryDTO());
		
		when(categoryDAOImpl.list(1, 3)).thenReturn(catList);
		assertTrue(3 == categoryServiceImpl.list(1, 3).size());
		
		// Failure scenario
		when(categoryDAOImpl.list(2, 3)).thenReturn(null);
		assertTrue(null == categoryServiceImpl.list(2, 3));
	}
	
	@Test
	public void searchTest() {
		//Success scenario
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setName("Cat1");
		cat1.setDescription("cat type");
		cat1.setId(1L);
		
		CategoryDTO cat2 = new CategoryDTO();
		cat2.setName("Cat2");
		cat2.setDescription("cat type");
		cat2.setId(2L);
		
		CategoryDTO cat3 = new CategoryDTO();
		cat3.setName("Cat3");
		cat3.setDescription("cat type");
		cat3.setId(3L);
		
		List<CategoryDTO> catList = new ArrayList<>();
		catList.add(cat1);
		catList.add(cat2);
		catList.add(cat3);
		
		CategoryDTO queryCat = new CategoryDTO();
		queryCat.setDescription("cat type");
		when(categoryDAOImpl.search(queryCat)).thenReturn(catList);
		assertTrue(3 == categoryServiceImpl.search(queryCat).size());
		
		//Failure Scenario
		CategoryDTO queryCat2 = new CategoryDTO();
		queryCat2.setDescription("test");
		when(categoryDAOImpl.search(queryCat2)).thenReturn(null);
		assertTrue(null == categoryServiceImpl.search(queryCat2));
	}
	
	@Test
	public void searchWithPaginationTest() {
		//Success scenario
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setName("Cat1");
		cat1.setDescription("cat type");
		cat1.setId(1L);
		
		CategoryDTO cat2 = new CategoryDTO();
		cat2.setName("Cat2");
		cat2.setDescription("cat type");
		cat2.setId(2L);
		
		CategoryDTO cat3 = new CategoryDTO();
		cat3.setName("Cat3");
		cat3.setDescription("cat type");
		cat3.setId(3L);
		
		List<CategoryDTO> catList = new ArrayList<>();
		catList.add(cat1);
		catList.add(cat2);
		catList.add(cat3);
		
		CategoryDTO queryCat = new CategoryDTO();
		queryCat.setDescription("cat type");
		when(categoryDAOImpl.search(queryCat, 1, 3)).thenReturn(catList);
		assertTrue(3 == categoryServiceImpl.search(queryCat, 1, 3).size());
		
		//Failure Scenario
		CategoryDTO queryCat2 = new CategoryDTO();
		queryCat2.setDescription("test");
		when(categoryDAOImpl.search(queryCat2, 1, 3)).thenReturn(null);
		assertTrue(null == categoryServiceImpl.search(queryCat2, 1, 3));
	}
	
	@Test
	public void getImageByIdTest() throws SerialException, SQLException {
		//Success scenario
		Blob blob = new Blob(null, null);
		when(categoryDAOImpl.getImageById(1L)).thenReturn(blob);
		assertTrue(blob == categoryServiceImpl.getImageById(1L));
		
		//Failure scenario
		assertTrue(null == categoryServiceImpl.getImageById(-1L));
	}

}
