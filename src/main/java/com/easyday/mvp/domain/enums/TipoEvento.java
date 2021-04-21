package com.easyday.mvp.domain.enums;

public enum TipoEvento {
	
	WORKSHOP(1, "Workshop"),
	EXPOSICAO(2, "Exposição"),
	PALESTRA(3, "Palestra"),
	CURSO(4, "Curso"),
	REUNIAO(5, "Reunião"),
	DEBATE(6, "Debate"),
	TEATRO(7, "Teatro"),
	OUTROS(8, "Outros");
	
	private int cod;
	private String descricao;
	
	private TipoEvento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoEvento toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(TipoEvento x : TipoEvento.values()) {
			if(cod.equals(x.getCod())) return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}