package com.learning.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.learning.springboot.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long >{
	
	// Consultas personalizadas
}
