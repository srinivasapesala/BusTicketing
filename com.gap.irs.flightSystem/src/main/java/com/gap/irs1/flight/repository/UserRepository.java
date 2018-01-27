package com.gap.irs1.flight.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gap.irs1.flight.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{

	@Query("SELECT  e FROM UserEntity e WHERE USERID=:source and PASSWORD=:password")
	UserEntity validateCredentials(@Param("source") String source,@Param("password") String password);

	
}
