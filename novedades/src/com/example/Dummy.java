package com.example;

import module java.base;

public class Dummy {
	private String valor;
	
	public Dummy() {
	}

	public Dummy(String valor) {
		setValor(valor);
	}

	public Optional<String> getValor() {
		return Optional.ofNullable(valor);
	}
	
	public void setValor(String valor) {
//		assert valor != null;
		if(valor == null) 
			throw new IllegalArgumentException("No puede ser nulo");
		this.valor = valor;
	}
	public void clearValor() {
		this.valor = null;
	}
}
