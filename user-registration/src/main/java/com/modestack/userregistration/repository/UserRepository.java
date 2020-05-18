package com.modestack.userregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.modestack.userregistration.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);

}
