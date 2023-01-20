package com.api.manageusers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.manageusers.models.AdressModel;
import com.api.manageusers.models.UserModel;
import com.api.manageusers.repositories.AdressRepository;
import com.api.manageusers.services.AdressService;

@WebMvcTest(value = AdressService.class)
public class AdressServiceUnitTest {

	@MockBean
	AdressRepository adressRepository;
	

	@Test
	public void adressServiceSuccess() {
		AdressModel adress = new AdressModel();

		adress.setId((long) 1);
		adress.setPublicPlace("Av");
		adress.setCEP("17171");
		adress.setNumber("11");
		adress.setCity("city");
		adress.setPrincipal(false);
		adress.setUserModel(new UserModel());


		Mockito.when(adressRepository.save(adress)).thenReturn(adress);

		AdressModel adressResponse = adressRepository.save(adress);

		assertEquals(adressResponse, adress);
	}
	
	@Test
	public void findById() {
		Optional<AdressModel> optional = Optional.empty();

		Mockito.when(adressRepository.findById((long) 1)).thenReturn(optional);

		Optional<AdressModel> adressResponse = adressRepository.findById((long) 1);

		assertEquals(adressResponse, optional);
	}

	@Test
	public void findAllById() {
		List<AdressModel> listOfAdress = new ArrayList<>();

		Mockito.when(adressRepository.findAllById((long) 1)).thenReturn(listOfAdress);

		List<AdressModel> listAdressResponse = adressRepository.findAllById((long) 1);

		assertEquals(listAdressResponse, listOfAdress);
	}


	
}
