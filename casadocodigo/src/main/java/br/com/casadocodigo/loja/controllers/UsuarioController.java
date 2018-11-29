package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;

	@Autowired
	private UsuarioValidation usuarioValidation;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.addValidators(usuarioValidation);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = usuarioDao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}
	
	@RequestMapping("/roles/{id}")
	public ModelAndView roles(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("usuarios/roles");
		Usuario usuario = usuarioDao.loadUserByID(id);
		List<Role> roles = roleDao.listar();
		
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "usuariosHome", allEntries = true)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}

		usuarioDao.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	@CacheEvict(value = "usuariosHome", allEntries = true)
	//public ModelAndView atualizar(String email, Usuario usuario, RedirectAttributes redirectAttributes) {
	public ModelAndView atualizar(Usuario usuario, RedirectAttributes redirectAttributes) {
		
		//usuario.setEmail(email);
		usuarioDao.atualizar(usuario);

		redirectAttributes.addFlashAttribute("message", "Permissões alteradas com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}
	

}
