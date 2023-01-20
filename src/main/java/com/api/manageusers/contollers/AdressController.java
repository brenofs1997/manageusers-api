package com.api.manageusers.contollers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.manageusers.dtos.AdressDto;
import com.api.manageusers.models.AdressModel;
import com.api.manageusers.services.AdressService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/adress")
public class AdressController {

	@Autowired
	private AdressService service;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid AdressDto adressDto) {
		var adressModel = new AdressModel();
	
		BeanUtils.copyProperties(adressDto, adressModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(adressModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAdress(@PathVariable(value = "id") Long id,@RequestBody @Valid AdressDto adressDto) {
		Optional<AdressModel> adressModelOptional = service.findById(id);

		if (!adressModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adress not found.");
		}

		var adressModel = new AdressModel();
		BeanUtils.copyProperties(adressDto, adressModel);
		adressModel.setId(adressModelOptional.get().getId());

		return ResponseEntity.status(HttpStatus.OK).body(service.save(adressModel));
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<AdressModel>> getAllAdressById(@PathVariable(value = "id") Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(service.findAllById(id));
	}

	@PutMapping("/changeprincipal/{id}")
	public ResponseEntity<Object> updateAllById(@PathVariable(value = "id") Long id) {
		Optional<AdressModel> adressModelOptional = service.findById(id);
		
		if (!adressModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adress not found.");
		}
		
		var adressModel = new AdressModel();
		adressModel.setId(adressModelOptional.get().getId());
		adressModel.setPublicPlace(adressModelOptional.get().getPublicPlace());
		adressModel.setCEP(adressModelOptional.get().getCEP());
		adressModel.setNumber(adressModelOptional.get().getNumber());
		adressModel.setCity(adressModelOptional.get().getCity());
		adressModel.setPrincipal(true);
		adressModel.setUserModel(adressModelOptional.get().getUserModel());
		
		service.updateAllById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(adressModel));
	}

}
