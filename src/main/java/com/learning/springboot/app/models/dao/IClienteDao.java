package com.learning.springboot.app.models.dao;

import java.util.List;

import com.learning.springboot.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();

	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
}
