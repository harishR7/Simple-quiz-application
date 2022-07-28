package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MobileRequest;



public interface MobileRequestRepo extends JpaRepository<MobileRequest, Long> {

	
	@Query(nativeQuery = true,value = "delete from hrclient_mobile_req where account_number=:accountNumber" )
	@Transactional
	@Modifying
	int deleteInRequest(@Param("accountNumber") long accountNumber);
	


}
