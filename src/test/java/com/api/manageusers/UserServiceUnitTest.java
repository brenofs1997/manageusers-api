package com.api.manageusers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.api.manageusers.models.UserModel;
import com.api.manageusers.repositories.UserRepository;
import com.api.manageusers.services.UserService;

@WebMvcTest(value = UserService.class)
public class UserServiceUnitTest {

	@MockBean
	UserRepository userRepository;
	

	@Test
	public void userServiceSuccess() {
		UserModel user = new UserModel();

		LocalDate date = LocalDate.of(1999, 1, 8);

		user.setId((long) 1);
		user.setName("Machina");
		user.setBirthDate(date);

		Mockito.when(userRepository.save(user)).thenReturn(user);

		UserModel userResponse = userRepository.save(user);

		assertEquals(userResponse, user);
	}

	@Test
	public void userServiceFindAllSuccess() {

		List<UserModel> listOfUsers = new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 8);
		Page<UserModel> page = new PageImpl<>(listOfUsers, pageable, 1);

		Mockito.when(userRepository.findAll(pageable)).thenReturn(page);

		Page<UserModel> userResponse = userRepository.findAll(pageable);

		assertEquals(userResponse, page);
	}

	@Test
	public void userServiceFindAllById() {
		Optional<UserModel> optional = Optional.empty();

		Mockito.when(userRepository.findById((long) 1)).thenReturn(optional);

		Optional<UserModel> userResponse = userRepository.findById((long) 1);

		assertEquals(userResponse, optional);
	}

}
