package com.api.manageusers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.manageusers.contollers.UserController;
import com.api.manageusers.models.AdressModel;
import com.api.manageusers.models.UserModel;
import com.api.manageusers.services.UserService;

@WebMvcTest(value = UserController.class)
public class UserControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	private static final String USER_DTO_JSON = "{\"name\":\"ouro\",\n" + "\"birthDate\":\"1999-01-08\"}";
	@BeforeEach
	public void setUp() {
	}

	@Test
	public void saveUserSuccess() throws Exception {
		UserModel user = new UserModel();

		LocalDate date = LocalDate.of(1999, 1, 8);
		
		user.setName("Machina");
		user.setBirthDate(date);


		when(service.save(user)).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/users").accept(MediaType.APPLICATION_JSON)
				.content(USER_DTO_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void updateUserSuccess() throws Exception {
		UserModel user = new UserModel();

		LocalDate date = LocalDate.of(1999, 1, 8);
		List<AdressModel> adress = new ArrayList<>();
				
		user.setId((long) 1);
		user.setName("Machina");
		user.setBirthDate(date);
		user.setAdress(adress);

		when(service.findById((long) 1)).thenReturn(Optional.of(user));
		when(service.save(user)).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/users/{id}", 1).accept(MediaType.APPLICATION_JSON)
				.content(USER_DTO_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void getByID() throws Exception {
		UserModel user = new UserModel();
	
		LocalDate date = LocalDate.of(1999, 1, 8);
		
		user.setId((long) 1);
		user.setName("Machina");
		user.setBirthDate(date);
	
		when(service.findById((long) 1)).thenReturn(Optional.of(user));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/users/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void getAll() throws Exception {
		List<UserModel> listOfUsers= new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 8);
		Page<UserModel> page = new PageImpl<>(listOfUsers, pageable, 1);

		when(service.findAll(pageable)).thenReturn(page);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/users")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
