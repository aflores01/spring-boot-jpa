package com.learning.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.learning.springboot.app.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
