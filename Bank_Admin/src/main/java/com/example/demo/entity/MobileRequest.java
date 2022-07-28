package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hrclient_mobile_req")
public class MobileRequest {

	
	@Id
	@Column(name = "account_number")
	
	long accountNumber;
	
	
	@Column(name = "old_mobile_number")
	long oldMobileNumber;
	
	@Column(name = "new_mobile_number")
	long newMobileNumber;
	
}
