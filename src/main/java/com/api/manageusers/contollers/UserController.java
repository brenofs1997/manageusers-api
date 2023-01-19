package com.api.manageusers.contollers;

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

import com.api.manageusers.dtos.UserDto;
import com.api.manageusers.models.UserModel;
import com.api.manageusers.services.UserService;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid UserDto userDto) {
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCard(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto userDto) {
		Optional<UserModel> userModelOptional = service.findById(id);
		
		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setId(userModelOptional.get().getId());

		return ResponseEntity.status(HttpStatus.OK).body(service.save(userModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<UserModel>> getAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByID(@PathVariable(value = "id") Long id) {
		Optional<UserModel> userModelOptional = service.findById(id);

		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
}
