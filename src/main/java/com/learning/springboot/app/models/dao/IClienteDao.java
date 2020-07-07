package com.learning.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long >{
	
	// Consultas personalizadas
}
