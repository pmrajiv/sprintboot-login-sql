package com.pmrajiv.loginApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmrajiv.loginApp.dto.Users;

public interface UserRepository extends JpaRepository<Users,Long>{

	List<Users> findByEmail(String emailId);
}
