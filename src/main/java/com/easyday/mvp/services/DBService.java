package com.easyday.mvp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyday.mvp.domain.Cidade;
import com.easyday.mvp.domain.Endereco;
import com.easyday.mvp.domain.Evento;
import com.easyday.mvp.domain.Usuario;
import com.easyday.mvp.domain.enums.TipoEvento;
import com.easyday.mvp.repositories.CidadeRepository;
import com.easyday.mvp.repositories.EnderecoRepository;
import com.easyday.mvp.repositories.EventoRepository;
import com.easyday.mvp.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private CidadeRepository cidRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private EnderecoRepository endRepository;
	
	public void instantiateTestDatabase() throws ParseException {

		Cidade c1 = new Cidade(null, "Uberlândia");
		Cidade c2 = new Cidade(null, "São Paulo");
		Cidade c3 = new Cidade(null, "Campinas");
		Cidade c4 = new Cidade(null, "Cuiabá");
		Cidade c5 = new Cidade(null, "Maceió");
		Cidade c6 = new Cidade(null, "Palmas");
		
		Usuario user1 = new Usuario(null, "Maria Silva", "maria@gmail.com");
		Usuario user2 = new Usuario(null, "Pedro Almeida", "pedro@gmail.com");
		Usuario user3 = new Usuario(null, "Miranda Pereira", "miranda@gmail.com");
		Usuario user4 = new Usuario(null, "Portin Lando", "portin@gmail.com");
		Usuario user5 = new Usuario(null, "Lancaster Fintomério", "lancaster@gmail.com");
		Usuario user6 = new Usuario(null, "Alvangendino Lopurenho", "ston@gmail.com");

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto303", "Jardim Mole", "30128586", c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38421736", c2);
		Endereco e3 = new Endereco(null, "Rua Lunguina", "84", "Apto111", "Peloti", "20931822", c3);
		Endereco e4 = new Endereco(null, "Alameda Martins", "409", "Apto214", "Mencunda", "60129938", c4);
		Endereco e5 = new Endereco(null, "Praça Jovem", "567", "Apto577", "Longancundo", "21980084", c5);
		Endereco e6 = new Endereco(null, "Avenida Lúmen", "984", "Apto72", "Prantiari", "61298837", c6);
		Endereco e7 = new Endereco(null, "Rua Dardomélio", "583", "Apto19", "Erfando", "41823328", c3);
		Endereco e8 = new Endereco(null, "Rua Mergolina", "46", "Apto85", "Regunta", "71826152", c4);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Evento evento1 = new Evento(null, "Curso de Marketing", sdf.parse("21/01/2022 12:00"), null, user1, e1, TipoEvento.CURSO, "Curso de Marketing Digital voltado para a criatividade");
		Evento evento2 = new Evento(null, "Exposição MASP", sdf.parse("30/05/2021 12:00"), sdf.parse("25/10/2021 12:00"), user2, e2, TipoEvento.EXPOSICAO, "Exposição de Arte Moderna no MASP");
		Evento evento3 = new Evento(null, "Debate Virtual", sdf.parse("13/08/2021 12:00"), null, user3, e3, TipoEvento.DEBATE, "Debate virtual sobre o tema de Empatia e Amor");
		Evento evento4 = new Evento(null, "Palestra de Leandro Karnal", sdf.parse("09/07/2021 13:00"), null, user4, e4, TipoEvento.PALESTRA, "Palestra de Leandro Karnal sobre Pecado e Redenção");
		Evento evento5 = new Evento(null, "Workshop de criatividade", sdf.parse("05/08/2021 13:30"), sdf.parse("09/08/2021 17:30"), user5, e5, TipoEvento.WORKSHOP, "Workshop sobre criatividade e segurança psicológica");
		Evento evento6 = new Evento(null, "Reunião de empreendedores", sdf.parse("30/06/2021 14:30"), null, user6, e6, TipoEvento.REUNIAO, "Reunião de empreendedores para discutir o mercado");
		Evento evento7 = new Evento(null, "Peça: O Grande Inquisidor", sdf.parse("28/07/2021 19:00"), null, user3, e7, TipoEvento.TEATRO, "Peça O Grande Inquisidor estrelando Elias Andreato e Celso Frateschi");
		Evento evento8 = new Evento(null, "Pic-nic", sdf.parse("16/10/2021 15:00"), null, user4, e8, TipoEvento.OUTROS, "Pic-nic colaborativo e com fins de arrecadar fundos");
		
		user1.getEventos().addAll(Arrays.asList(evento1));
		user2.getEventos().addAll(Arrays.asList(evento2));
		user3.getEventos().addAll(Arrays.asList(evento3, evento7));
		user4.getEventos().addAll(Arrays.asList(evento4, evento8));
		user5.getEventos().addAll(Arrays.asList(evento5));
		user6.getEventos().addAll(Arrays.asList(evento6));

		user1.getTelefones().addAll(Arrays.asList("24158275", "981754162"));
		user2.getTelefones().addAll(Arrays.asList("55126654", "991431885"));
		user3.getTelefones().addAll(Arrays.asList("35016354", "954187213"));
		user4.getTelefones().addAll(Arrays.asList("55199615", "939120649"));
		user5.getTelefones().addAll(Arrays.asList("40041320", "932817592"));
		user6.getTelefones().addAll(Arrays.asList("51238812", "974192859"));
		
		evento1.setEndereco(e1);
		evento2.setEndereco(e2);
		evento3.setEndereco(e3);
		evento4.setEndereco(e4);
		evento5.setEndereco(e5);
		evento6.setEndereco(e6);
		evento7.setEndereco(e7);
		evento8.setEndereco(e8);
		
		cidRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		endRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
		eventoRepository.saveAll(Arrays.asList(evento1, evento2, evento3, evento4, evento5, evento6, evento7, evento8));
	}
}
