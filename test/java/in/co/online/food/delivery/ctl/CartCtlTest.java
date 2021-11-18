package in.co.online.food.delivery.ctl;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.dto.UserDTO;
import in.co.online.food.delivery.service.CartServiceInt;
import in.co.online.food.delivery.service.ProductServiceInt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class CartCtlTest {
	
	@InjectMocks
	CartCtl	cartCtl;
	
	@Mock
	CartServiceInt	cartServiceInt;
	
	@Mock
	ProductServiceInt	productServiceInt;
	
	@Mock
	HttpSession session;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void displayTest() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setPrice("100");
		
		when(session.getAttribute("user")).thenReturn(userDTO);
		when(productServiceInt.findBypk(1L)).thenReturn(productDTO);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.cartCtl).build();
		mockMvc.perform(get("/ctl/cart")).andExpect(redirectedUrl("/ctl/cart/search"));
	}

}
