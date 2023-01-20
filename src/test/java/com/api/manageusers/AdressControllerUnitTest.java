package com.api.manageusers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.manageusers.contollers.AdressController;
import com.api.manageusers.models.AdressModel;
import com.api.manageusers.models.UserModel;
import com.api.manageusers.repositories.AdressRepository;
import com.api.manageusers.services.AdressService;

@WebMvcTest(value = AdressController.class)
public class AdressControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	AdressRepository adressRepository;
	
	@MockBean
	private AdressService service;
	
	private static final String ADRESS_DTO_JSON = "{\"publicPlace\":\"AvenJas\",\"cep\":\"178920-000\",\"number\":\"145\",\"city\":\"Parapuao\",\"principal\":false,\"userModel\":{\"id\":1}}";
	@BeforeEach
	public void setUp() {
	}

	@Test
	public void saveAdressSuccess() throws Exception {
		AdressModel adress = new AdressModel();

		adress.setPublicPlace("Av");
		adress.setCEP("17171");
		adress.setNumber("11");
		adress.setCity("city");
		adress.setPrincipal(false);
		adress.setUserModel(new UserModel());


		when(service.save(adress)).thenReturn(adress);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/adress").accept(MediaType.APPLICATION_JSON)
				.content(ADRESS_DTO_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void updateAdressSuccess() throws Exception {
		AdressModel adress = new AdressModel();

		adress.setId((long) 1);
		adress.setPublicPlace("Av");
		adress.setCEP("17171");
		adress.setNumber("11");
		adress.setCity("city");
		adress.setPrincipal(true);
		adress.setUserModel(new UserModel());


		when(service.findById((long) 1)).thenReturn(Optional.of(adress));
		when(service.save(adress)).thenReturn(adress);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/adress/{id}", 1).accept(MediaType.APPLICATION_JSON)
				.content(ADRESS_DTO_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void getAllAdressById() throws Exception {
		List<AdressModel> listOfAdress= new ArrayList<>();
	

		when(service.findAllById((long) 1)).thenReturn(listOfAdress);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/adress/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void updatePrincipalById() throws Exception {
		AdressModel adress = new AdressModel();

		adress.setId((long) 1);
		adress.setPublicPlace("Av");
		adress.setCEP("17171");
		adress.setNumber("11");
		adress.setCity("city");
		adress.setPrincipal(true);
		adress.setUserModel(new UserModel());


		when(service.findById((long) 1)).thenReturn(Optional.of(adress));
		when(service.save(adress)).thenReturn(adress);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/adress/changeprincipal/{id}", 1).accept(MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

}
