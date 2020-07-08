package com.learning.springboot.app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.springboot.app.models.entity.Cliente;
import com.learning.springboot.app.models.service.IClienteService;
import com.learning.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value="/ver/{id}")
	String ver(@PathVariable(value="id") long id,Map<String,Object> model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente ==null) {
			flash.addFlashAttribute("error","El cliente no se encuentra en la base de datos");
			return "redirect:/listar";
		}
		model.put("titulo","Detalle del cliente: ".concat(cliente.getNombre()));
		model.put("cliente", cliente);
		return "ver";
	}
	

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes); 
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}

	@RequestMapping("/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "Usuario no encontrado en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser 0!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
				
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "form";
		}
		
		if(!foto.isEmpty()) {
			if(cliente.getId() != null && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
				String rootPath = "C://Temp//uploads";
				Path fullPath = Paths.get(rootPath + "//" + cliente.getFoto());
				File archivo = fullPath.toFile();
				if (archivo.exists() && archivo.canRead()) {
					if(archivo.delete()) {
					}
				}
			}
			String rootPath = "C://Temp//uploads";
			try {
				byte[] bytes = foto.getBytes();
				Path fullPath = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(fullPath, bytes);
				flash.addFlashAttribute("info","Carga completa: ".concat(foto.getOriginalFilename()));
				cliente.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String msgFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", msgFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			
			clienteService.delete(id);
			String rootPath = "C://Temp//uploads";
			Path fullPath = Paths.get(rootPath + "//" + cliente.getFoto());
			File archivo = fullPath.toFile();
			if (archivo.exists() && archivo.canRead()) {
				if(archivo.delete()) {
					flash.addFlashAttribute("info","Foto:".concat(cliente.getFoto()).concat(" Eliminada con éxito!"));
				}
			}
			flash.addFlashAttribute("success", "Cliente eliminado satisfactoriamente!");
			
		}
		return "redirect:/listar";
	}
}
