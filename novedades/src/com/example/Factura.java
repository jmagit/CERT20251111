package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factura {
	static BigDecimal iva = new BigDecimal(1.21); 
	
	public static class Lineas1 {
		private int numFactura;

		public Lineas1(int numFactura) {
			super();
			this.numFactura = numFactura;
		}

		public int getNumFactura() {
			return numFactura;
		}

		public void setNumFactura(int numFactura) {
			this.numFactura = numFactura;
		}
		
	}

	private int numFactura;
	private List<Lineas1> lineas = new ArrayList<>();
	
	public List<Lineas1> getLineas() {
		return Collections.unmodifiableList(lineas);
	}

	public class Lineas2 {
		public int getNumFactura() {
			return numFactura;
		}
	}
	public Factura(int numFactura) {
		super();
		this.numFactura = numFactura;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public Lineas1 addLinea1() {
		var linea = new Lineas1(numFactura);
		lineas.add(linea);
		return linea;
	}

	public Lineas2 addLinea2() {
		return new Lineas2();
	}
	
}
