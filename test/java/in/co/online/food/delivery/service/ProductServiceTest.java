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

import in.co.online.food.delivery.dao.ProductDAOImpl;
import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

public class ProductServiceTest {
	
	@InjectMocks
	ProductServiceImpl	productServiceImpl;
	
	@Mock
	ProductDAOImpl	productDAOImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() throws DuplicateRecordException {
		//Success scenario
		ProductDTO product1 = new ProductDTO();
		product1.setName("product1");
		
		when(productDAOImpl.findByName("product1")).thenReturn(null);
		when(productDAOImpl.add(product1)).thenReturn(1L);
		assertTrue(1L == productServiceImpl.add(product1));
		
		//Failure scenario
		when(productDAOImpl.findByName("product1")).thenReturn(product1);
		Exception exception = assertThrows(DuplicateRecordException.class, () -> {
			productServiceImpl.add(product1);
		});
		String expectedMessage = "Name Already Exist";
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void findByPkTest() {
		// Success scenario
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setName("cat1");
		productDTO.setDescription("desc");
		
		when(productDAOImpl.findBypk(1L)).thenReturn(productDTO);
		ProductDTO actualCat = productServiceImpl.findBypk(1L);
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("cat1", actualCat.getName());
		assertEquals("desc", actualCat.getDescription());
		
		// Failure Scenario
		ProductDTO failCatDTO = productDAOImpl.findBypk(2L);
		assertNull(failCatDTO);
	}
	
	@Test
	public void findByNameTest() {
		// Success scenario
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setName("cat1");
		productDTO.setDescription("desc");
		
		when(productDAOImpl.findByName("cat1")).thenReturn(productDTO);
		ProductDTO actualCat = productServiceImpl.findByName("cat1");
		assertNotNull(actualCat);
		assertEquals(1L, actualCat.getId());
		assertEquals("cat1", actualCat.getName());
		assertEquals("desc", actualCat.getDescription());
		
		// Failure Scenario
		ProductDTO failCatDTO = productDAOImpl.findByName("cat2");
		assertNull(failCatDTO);
	}
	
	@Test
	public void getListTest() {
		// Success scenario
		ProductDTO product1 = new ProductDTO();
		ProductDTO product2 = new ProductDTO();
		ProductDTO product3 = new ProductDTO();
		
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		when(productDAOImpl.list()).thenReturn(productList);
		assertNotNull(productServiceImpl.list());
		assertEquals(3, productServiceImpl.list().size());
		
		// Failure scenario
		assertFalse(2 == productServiceImpl.list().size());
	}
	
	@Test
	public void getListPaginationTest() {
		// Success scenario
		ProductDTO product1 = new ProductDTO();
		ProductDTO product2 = new ProductDTO();
		ProductDTO product3 = new ProductDTO();
		
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		when(productDAOImpl.list(1, 3)).thenReturn(productList);
		assertNotNull(productServiceImpl.list(1, 3));
		assertEquals(3, productServiceImpl.list(1, 3).size());
		
		// Failure scenario
		assertFalse(2 == productServiceImpl.list(2, 3).size());
	}
	
	@Test
	public void searchTest() {
		//Success scenario
		ProductDTO product1 = new ProductDTO();
		product1.setName("Cat1");
		product1.setDescription("cat type");
		product1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setName("Cat2");
		prod2.setDescription("cat type");
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setName("Cat3");
		prod3.setDescription("cat type");
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(product1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		ProductDTO queryCat = new ProductDTO();
		queryCat.setDescription("cat type");
		when(productDAOImpl.search(queryCat)).thenReturn(prodList);
		assertTrue(3 == productServiceImpl.search(queryCat).size());
		
		//Failure Scenario
		ProductDTO queryCat2 = new ProductDTO();
		queryCat2.setDescription("test");
		when(productDAOImpl.search(queryCat2)).thenReturn(null);
		assertTrue(null == productServiceImpl.search(queryCat2));
	}
	
	@Test
	public void searchPaginationTest() {
		//Success scenario
		ProductDTO product1 = new ProductDTO();
		product1.setName("Cat1");
		product1.setDescription("cat type");
		product1.setId(1L);
		
		ProductDTO prod2 = new ProductDTO();
		prod2.setName("Cat2");
		prod2.setDescription("cat type");
		prod2.setId(2L);
		
		ProductDTO prod3 = new ProductDTO();
		prod3.setName("Cat3");
		prod3.setDescription("cat type");
		prod3.setId(3L);
		
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(product1);
		prodList.add(prod2);
		prodList.add(prod3);
		
		ProductDTO queryCat = new ProductDTO();
		queryCat.setDescription("cat type");
		when(productDAOImpl.search(queryCat, 1, 3)).thenReturn(prodList);
		assertTrue(3 == productServiceImpl.search(queryCat, 1, 3).size());
		
		//Failure Scenario
		ProductDTO queryCat2 = new ProductDTO();
		queryCat2.setDescription("test");
		when(productDAOImpl.search(queryCat2, 2, 3)).thenReturn(null);
		assertTrue(null == productServiceImpl.search(queryCat2, 2, 3));
	}
	
	@Test
	public void getImageByIdTest() throws SerialException, SQLException {
		//Success scenario
		Blob blob = new Blob(null, null);
		when(productDAOImpl.getImageById(1L)).thenReturn(blob);
		assertTrue(blob == productServiceImpl.getImageById(1L));
		
		//Failure scenario
		assertTrue(null == productServiceImpl.getImageById(-1L));
	}

}
