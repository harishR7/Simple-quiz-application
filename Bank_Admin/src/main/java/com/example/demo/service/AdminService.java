package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountHolder;
import com.example.demo.repo.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repo;
	
	
}
