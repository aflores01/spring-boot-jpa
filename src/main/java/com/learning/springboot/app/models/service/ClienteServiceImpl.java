package com.learning.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springboot.app.models.dao.IClienteDao;
import com.learning.springboot.app.models.dao.IFacturaDao;
import com.learning.springboot.app.models.dao.IProductoDao;
import com.learning.springboot.app.models.entity.Cliente;
import com.learning.springboot.app.models.entity.Factura;
import com.learning.springboot.app.models.entity.Producto;

// Acceso universal a las clases DAO
@Service
public class ClienteServiceImpl implements IClienteService {

	// Inyeccón de DAO para fachada del servicio
	@Autowired
	private IClienteDao clienteDao;
	//

	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByName(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductById(Long id) {
		
		return productoDao.findById(id).orElse(null);
	}

}
