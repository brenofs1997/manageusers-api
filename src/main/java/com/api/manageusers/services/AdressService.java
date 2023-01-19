package com.api.manageusers.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.manageusers.dtos.AdressDto;
import com.api.manageusers.models.AdressModel;
import com.api.manageusers.repositories.AdressRepository;
import com.api.manageusers.repositories.UserRepository;

@Service
public class AdressService {
	final AdressRepository adressRepository;

	public AdressService(AdressRepository adressRepository) {
		this.adressRepository = adressRepository;
	}

	@Transactional
	public AdressModel save(AdressModel userModel) {
		return adressRepository.save(userModel);
	}

	public Optional<AdressModel> findById(Long id) {
		return adressRepository.findById(id);
	}

	@Transactional
	public List<AdressModel> findAllById(Long id) {
		return adressRepository.findAllById(id);
	}

	@Transactional
	public void updateAllById(Long id) {
		adressRepository.updateAllById(id);
	}

}
