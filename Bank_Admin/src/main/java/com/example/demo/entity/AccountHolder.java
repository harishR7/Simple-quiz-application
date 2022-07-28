package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Entity
@Table(name="hr_client_details")

@Component
public class AccountHolder {

	@Id
	@SequenceGenerator(name = "accSeq1", sequenceName = "jtpsequence", initialValue = 1000000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSeq1")
	@Column(name = "account_number")
	long accountNumber;
	
	@Column(name = "account_holder_name")
	String accountHolderName;
	
	@Column(name = "address")
	String address;
	
	@Column(name = "mobile_number")
	long mobileNumber;
	
	@Column(name = "date_of_birth")
	@DateTimeFormat(iso=ISO.DATE)
	LocalDate dateOfBirth;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "balance")
	double balance;
	
	
	
	
}
