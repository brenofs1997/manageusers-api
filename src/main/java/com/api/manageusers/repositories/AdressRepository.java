package com.api.manageusers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.manageusers.models.AdressModel;

public interface AdressRepository extends JpaRepository<AdressModel, Long> {

	@Query("SELECT obj FROM AdressModel obj JOIN FETCH obj.userModel WHERE obj.userModel.id = :id")
	List<AdressModel> findAllById(@Param("id") Long id);

	@Modifying
	@Query("update AdressModel set principal = false where userModel.id = :id")
	void updateAllById(@Param("id") Long id);
}
