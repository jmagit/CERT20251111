package com.example;

public class Fichero implements AutoCloseable {
	private static boolean cerrado = true;
	
	public Fichero() throws Exception {
		if(!cerrado) throw new Exception("Ya esta abierto.");
		cerrado = false;
		IO.println("Abro el fichero");
	}
	
	public void write(String line) throws NoSuchMethodException {
		if(cerrado)
			throw new NoSuchMethodException();
		IO.println("Escribo " + line);
	}
	
	@Override
	public void close() throws Exception {
		cerrado = true;
		IO.println("Cierro el fichero");
	}
	
	@Override
	protected void finalize() throws Throwable {
		if(!cerrado) close();
		IO.println("Destruyo objeto");
		super.finalize();
	}

}
