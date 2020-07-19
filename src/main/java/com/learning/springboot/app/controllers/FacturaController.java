package com.learning.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.springboot.app.models.entity.Cliente;
import com.learning.springboot.app.models.entity.Factura;
import com.learning.springboot.app.models.entity.ItemFactura;
import com.learning.springboot.app.models.entity.Producto;
import com.learning.springboot.app.models.service.IClienteService;

@Secured("ROLE_ADMIN")
@Controller
@SessionAttributes("factura")
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Factura factura = clienteService.fetchByIdWithClienteWithItemFacturaWithProducto(id);
		if(factura == null) {
			flash.addFlashAttribute("error", "La factura no existe");
			
			return "redirect:/listar";
		}
		model.addAttribute("factura",factura);
		model.addAttribute("titulo","Factura: ".concat(factura.getDescripcion()));
		return "factura/ver";
	}

	@GetMapping("/form/{id}")
	public String crear(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "Cliente no existe en base de datos");
			return "redirect:/listar";
		}

		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.put("factura", factura);
		model.put("titulo", "Crear Factura");
		return "factura/form";
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return clienteService.findByName(term);
	}

	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "factura/form";

		}
		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: La factura no puede estar vacia.");
			return "factura/form";
		}
		for (int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.findProductById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);
		}
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Factura creada exitosamente!");
		return "redirect:/ver/" + factura.getCliente().getId();
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		Factura factura = clienteService.findFacturaById(id);
		if (factura != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success","Factura Eliminada con éxito!");
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		flash.addFlashAttribute("error","La factura no existe en la base de datos!");
		return "redirect:/ver/" + factura.getCliente().getId();
	}
}
