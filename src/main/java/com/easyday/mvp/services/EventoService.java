package com.easyday.mvp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyday.mvp.domain.Endereco;
import com.easyday.mvp.domain.Evento;
import com.easyday.mvp.repositories.EnderecoRepository;
import com.easyday.mvp.repositories.EventoRepository;
import com.easyday.mvp.services.exceptions.ObjectNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;

	@Autowired
	private EnderecoRepository endRepository;

	public Evento find(Integer id) {
		Optional<Evento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Evento.class.getName())); 
	}
	
	@Transactional
	public Evento insert(Evento evento) {
		evento.setId(null);
		Endereco endereco;
		if(evento.getEndereco().getId() != null) {
			endereco = (Endereco) endRepository.findById(evento.getEndereco().getId()).get();
		} else {
			endereco = endRepository.save(evento.getEndereco());
		}
		evento.setEndereco(endereco);
		evento = repository.save(evento);
		return evento;
	}

	public Evento update(Evento evento) {
		Evento newEvento = find(evento.getId());
		Endereco endereco;
		if(evento.getEndereco().getId() != null) {
			endereco = (Endereco) endRepository.findById(evento.getEndereco().getId()).get();
		} else {
			endereco = endRepository.save(evento.getEndereco());
		}
		evento.setEndereco(endereco);
		updateData(newEvento, evento);
		return repository.save(newEvento);
	}
	
	public void deleteById(Integer id) {
		find(id);
//		try {
			repository.deleteById(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
//		}
	}

	public List<Evento> findAll() {
		return repository.findAll();
	}
	
	public Page<Evento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void updateData(Evento newEvento, Evento evento) {
		newEvento.setDescricao(evento.getDescricao());
		newEvento.setEndereco(evento.getEndereco());
		newEvento.setDataInicio(evento.getDataInicio());
		newEvento.setDataFim(evento.getDataFim());
		newEvento.setNome(evento.getNome());
		newEvento.setTipoEvento(evento.getTipoEvento());
	}
}