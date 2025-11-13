package com.example;

import java.util.Objects;

public record Punto(int x, int y, int z) {
	public Punto(int x, int y, int z) {
		if(-100 <= x && x > 100)
			throw new IndexOutOfBoundsException("Fuera del rango -100 y 100");
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Punto minus(int delta) {
		return new Punto(x-delta, y-delta, z-delta);
	}
}

//public class Punto {
//	private final int x;
//	private final int y;
//	
//	public Punto(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//
//	public int x() {
//		return x;
//	}
//
//	public int y() {
//		return y;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(x, y);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Punto other = (Punto) obj;
//		return x == other.x && y == other.y;
//	}
//
//	@Override
//	public String toString() {
//		return "Punto [x=" + x + ", y=" + y + "]";
//	}
//	
//	
//}
