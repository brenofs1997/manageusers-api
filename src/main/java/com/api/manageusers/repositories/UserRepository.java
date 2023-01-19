package com.api.manageusers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.manageusers.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> { }
