package com.easyday.mvp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyday.mvp.domain.Usuario;
import com.easyday.mvp.repositories.UsuarioRepository;
import com.easyday.mvp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName())); 
	}
	
	@Transactional
	public Usuario insert(Usuario cliente) {
		cliente.setId(null);
		cliente = repository.save(cliente);
		return cliente;
	}

	public Usuario update(Usuario cliente) {
		Usuario newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return repository.save(newCliente);
	}
	
	public void deleteById(Integer id) {
		find(id);
//		try {
			repository.deleteById(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
//		}
	}
	
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void updateData(Usuario newCliente, Usuario cliente) {
		newCliente.setEmail(cliente.getEmail());
	}
}
