package com.example;

import java.math.BigDecimal;

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
		return new Lineas1(numFactura);
	}

	public Lineas2 addLinea2() {
		return new Lineas2();
	}
	
}
