package com.easyday.mvp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.easyday.mvp.domain.Usuario;
import com.easyday.mvp.domain.Evento;
import com.easyday.mvp.services.UsuarioService;
import com.easyday.mvp.services.EventoService;

@RestController
@RequestMapping(value="/eventos")
public class EventoResource {

	@Autowired
	private EventoService service;
	
	@Autowired
	private UsuarioService userService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Evento> find(@PathVariable Integer id) {
		Evento evento = service.find(id);
		return ResponseEntity.ok().body(evento);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> findAll() {
		List<Evento> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Evento>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Evento> pagina = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pagina);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Evento evento) {
		Usuario user = userService.find(evento.getUsuario().getId());
		evento.setUsuario(user);
		evento = service.insert(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(evento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Evento evento, @PathVariable Integer id) {
		evento.setId(id);
		evento = service.update(evento);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}