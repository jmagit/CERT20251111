import java.awt.Image;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.concurrent.ExecutionException;

import com.example.Dummy;
import com.example.Factura;
import com.example.Fichero;
import com.example.NotFoundException;
import com.example.Punto;
import com.example.Tarea;

/// # Heading
/// 
/// ## Sub-heading Paragraphs are separated by a blank line. Two spaces at the
/// end of a line produces a line break. Text attributes _italic_, **bold**,
/// `monospace`. ## Sub-heading Horizontal rule: --- Bullet list: * apples *
/// oranges * pears Numbered list: 1. wash 2. rinse 3. repeat A
/// [link](http://example.com). ![Image](https://via.placeholder.com/150) >
/// Markdown uses email-style > characters for blockquoting.
String nombre = "mundo";

/// Esto es un ejemplo con __CommonMark__ de *Markdown* en Javadoc
///
/// Lista - ele 1 - a - b - ele 2
///
/// | A | B | | ---- | ----- | | 1 | 2 | | 3 | 4 |
///
/// ``` /** Hello World! */ public class HelloWorld { public static void
/// main(String... args) { System.out.println("Hello World!"); // the traditional
/// example } } ```
///
/// @since 11
void main(String[] args) {
//	demosNuevoSwich();
//	autoCierre();
//
//	factuas();
//	demosIEEE754();
//	registros();
//	colecciones();
	laboratorio();
}

private void laboratorio() {
	try {
//		hilosDePlataforma();
//		hilosVirtuales();
//		virtualThreadDemo1();
//		virtualThreadDemo2();
//		virtualThreadDemo3();
		virtualThreadDemo4();
//		pinningDemo();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//sum = 48761; time = 10224675000 ns
//sum = 49420; time = 100352696500 ns
//sum = 49801; time = 3182049700 ns

record KeyValue(int key, String value) {}

private void pinningDemo() throws InterruptedException, ExecutionException {
	ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
	// Executors.newCachedThreadPool();

	final int TASKS = 20;
	long start = System.nanoTime();
	for (int i = 1; i <= TASKS; i++) {
		// service.submit(() -> block());
		service.submit(() -> rblock());
	}
	for (int i = 1; i <= TASKS; i++) {
		service.submit(() -> noblock());
	}
	service.close();
	long end = System.nanoTime();
	System.out.printf("%.2f%n", (end - start) * 1E-9);
}

private synchronized void block() {
	IO.println("Entering block " + Thread.currentThread());
	LockSupport.parkNanos(1_000_000_000);
	IO.println("Exiting block " + Thread.currentThread());
}

private Lock lock = new ReentrantLock();

private void rblock() {
	lock.lock();
	try {
		IO.println("Entering rblock " + Thread.currentThread());
		LockSupport.parkNanos(1_000_000_000);
		IO.println("Exiting rblock " + Thread.currentThread());
	} finally {
		lock.unlock();
	}
}

private void noblock() {
	IO.println("Entering noblock " + Thread.currentThread());
	LockSupport.parkNanos(1_000_000_000);
	IO.println("Exiting noblock " + Thread.currentThread());
}

private void virtualThreadDemo4() throws InterruptedException, ExecutionException {
    try(ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
	    List<Future<String>> futures = new ArrayList<>();
	    final int TASKS = 250;
	    for (int i = 1; i <= TASKS; i++)
	       futures.add(service.submit(() -> getWithSemaphore("https://horstmann.com/random/word")));
	    for (Future<String> f : futures)
	       IO.print(f.get() + " ");
	    IO.println();
    }
}

private final Semaphore SEMAPHORE = new Semaphore(20);
private String getWithSemaphore(String url) {
   try {
      var request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
      SEMAPHORE.acquire();
      try {
         Thread.sleep(100);
         return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
      } finally {
         SEMAPHORE.release();
      }
   } catch (Exception ex) {
      ex.printStackTrace();
      var rex = new RuntimeException();
      rex.initCause(ex);
      throw rex;
   }
}   

private void virtualThreadDemo3() throws InterruptedException, ExecutionException {
    ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
    List<Callable<String>> callables = new ArrayList<>();
    final int ADJECTIVES = 4;
    for (int i = 1; i <= ADJECTIVES; i++)
       callables.add(() -> get("https://horstmann.com/random/adjective"));
    callables.add(() -> get("https://horstmann.com/random/noun"));
    List<String> results = new ArrayList<>();
    for (Future<String> f : service.invokeAll(callables))
       results.add(f.get());
    IO.println(String.join(" ", results));
    service.close();
}
private void virtualThreadDemo2() throws InterruptedException, ExecutionException {
    ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
    Future<String> f1 = service.submit(() -> get("https://horstmann.com/random/adjective"));
    Future<String> f2 = service.submit(() -> get("https://horstmann.com/random/noun"));
    String result = f1.get() + " " + f2.get();
    IO.println(result);
    service.close();	
}

private HttpClient client = HttpClient.newHttpClient();
private String get(String url) {
   try {
      var request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
      return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
   } catch (Exception ex) {
      var rex = new RuntimeException();
      rex.initCause(ex);
      throw rex;
   }
}   

private void virtualThreadDemo1() {
    final int NTASKS = 100; 
    ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
     for (int i = 0; i < NTASKS; i++) {
        service.submit(() -> {
           long id = Thread.currentThread().threadId(); 
           LockSupport.parkNanos(1_000_000_000);
           IO.println(id);
        });
     }
     service.close();
}
private void hilosDePlataforma() throws Exception {
	List<Tarea> tasks = new ArrayList<>();
	for (int i = 0; i < 10_000; i++) { tasks.add(new Tarea(i)); }
	long time = System.nanoTime(), sum = 0;

	 try (var executor = Executors.newFixedThreadPool(100)) {
		List<Future<Integer>> futures = executor.invokeAll(tasks);
		for (Future<Integer> future : futures) {
			sum += future.get();
		}
		time = System.nanoTime() - time;
		System.out.println("sum = " + sum + "; time = " + time + " ns");
	}
}

//sum = 49855; time = 10159058700 ns
//sum = 48488; time = 1340552400 ns

private void hilosVirtuales() throws Exception {
	List<Tarea> tasks = new ArrayList<>();
	for (int i = 0; i < 10_000; i++) { tasks.add(new Tarea(i)); }
	long time = System.nanoTime(), sum = 0;

	try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
		List<Future<Integer>> futures = executor.invokeAll(tasks);
		for (Future<Integer> future : futures) {
			sum += future.get();
		}
		time = System.nanoTime() - time;
		System.out.println("sum = " + sum + "; time = " + time + " ns");
	}
}

private void colecciones() {
	var numeros = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	numeros.stream()
			.gather(Gatherers.fold(() -> "", (collector, number) -> collector + number))
			.findFirst().ifPresent(IO::println);
	numeros.stream()
			.gather(Gatherers.scan(() -> "", (collector, number) -> collector + number))
			.forEach(IO::println);
	numeros.stream()
			.gather(Gatherers.scan(() -> 0, (collector, number) -> collector + number))
			.forEach(IO::println);
	numeros.stream()
			.gather(Gatherers.windowFixed(3))
			.forEach(IO::println);
	numeros.stream().gather(Gatherers.windowSliding(5))
			.forEach(IO::println);
}

private void registros() {
	var r1 = new Punto(0, 0, 0);
	Object o = new Punto(10, 20, 30);
	IO.println(r1);
	IO.println(r1 == new Punto(0, 0, 0) ? "iguales" : "distintos" );
	IO.println(r1.equals(new Punto(0, 0, 0)) ? "iguales" : "distintos" );
	IO.println("x: %d y: %d".formatted(r1.x(), r1.y()));
	
	if(o instanceof Punto p) {
		IO.println("x: %d y: %d z: %d".formatted(p.x(), p.y(), p.z()));
	}
	o = r1.minus(-1);
	if(o instanceof Punto(int a, int b, int c)) {
		IO.println(a + b + c);
	}
	switch(o) {
	case Punto(int a, int b, int c) when a > 10 -> IO.println(a + b + c);
	default -> IO.println(o.getClass().getName());
	}
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		throw new NotFoundException(e);
	}
//	String s = null;
//	if(s.equals("")) {}
	var d = new Dummy("");
	d.setValor(null);
	Date date;
}

private void demosIEEE754() {
	IO.println(0.1 + 0.2 + 0.1);
	IO.println(round(0.1 + 0.2));
	IO.println(round(1 - 0.9));
}
private double round(double value) {
	return (new java.math.BigDecimal(value)).setScale(15, java.math.RoundingMode.HALF_UP).doubleValue();
}

private void factuas() {
	var f = new Factura(1);
	var l1 = new Factura.Lineas1(f.getNumFactura());
	l1 = f.addLinea1();
	var l2 = f.addLinea2();
	IO.println("Factura1: " + l1.getNumFactura());
	IO.println("Factura2: " + l2.getNumFactura());
	IO.println("Cambio num");
	f.setNumFactura(666);
	IO.println("Factura1: " + l1.getNumFactura());
	IO.println("Factura2: " + l2.getNumFactura());
	IO.println("Cambio linea");
	l1.setNumFactura(2);
	IO.println("Factura1: " + l1.getNumFactura());
	IO.println("Factura2: " + l2.getNumFactura());
	
}

private void autoCierre() {
	try {
		cierre();
//		System.gc();
		System.runFinalization();
//		Thread.sleep(1000);
		cierre();
		var fich = new Fichero();
	} catch (Exception e) {
		e.printStackTrace();
	}
	IO.println("Termino");
		System.gc();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

private void cierre() throws Exception {
//	var fich = new Fichero();
//	try {
//		fich.write("Algo");
//	} finally {
//		fich.close(); 
//		fich = null;
//	}
//	fich.write("Algo");
	
	try (var fich = new Fichero()) {
		fich.write("Algo");
	}

//	var fich = new Fichero();
//		fich.write("Algo");
//	fich.write("Algo");
	//// fich.close(); fich = null; fich.write("Algo mas");
}


private void demosNuevoSwich() {
	IO.println(nuevoSwich(6));
	IO.println(nuevoSwich(7));
//	IO.println(nuevoSwich(77));
	IO.println(nuevoSwichTipo(77));
	IO.println(nuevoSwichTipo("77"));
	IO.println(nuevoSwichTipo("nuevoSwichTipo"));
}

private String nuevoSwichTipo(Object o) {
	return (switch (o) {
	case null -> "nulo";
	case String s when s.length() > 10 -> "cadena larga " + s.length();
	case String s -> "cadena corta " + s.length();
	case Number n -> "numero";
	default -> throw new IllegalArgumentException("tipo invalido");
	}).toUpperCase();
}

private String nuevoSwich(int index) {
	var rslt = "";
//	switch (index) {
//	case 1:
//	case 3:
//	case 5:
//	case 7:
//	case 9:
//		rslt = "impar";
//		break;
//	case 2:
//	case 4:
//	case 6:
//	case 8:
//		rslt = "par";
//		break;
//	default:
//		rslt = "no se";
//		break;
//	}
//	var rslt = "";
//	switch (index) {
//	case 1, 3, 5, 7, 9:
//		rslt = "impar";
//		break;
//	case 2, 4, 6, 8:
//		rslt = "par";
//		break;
//	default:
//		rslt = "no se";
//		break;
//	}
//	switch (index) {
//	case 1, 3, 5, 7, 9 -> rslt = "impar";
//	case 2, 4, 6, 8 -> {
//		rslt = "par";
//		}
//	default -> rslt = "no se";
//	}
//	return rslt;
//	return (switch (index) {
//		case 1, 3, 5, 7, 9 -> "impar";
//		case 2, 4, 6, 8 -> {
//			yield "par";
//			}
	//// default -> rslt = "no se"; default -> null;
//		default -> throw new IllegalArgumentException("index invalido");
//	}).toUpperCase();
	return (switch (index) {
	case 1, 3, 5, 7, 9:
		yield "impar";
	case 2, 4, 6, 8:
		yield "par";
	default:
		throw new IllegalArgumentException("index invalido");
	}).toUpperCase();

}

void ejemplos() {
	var s = new ArrayList<>();
	s.clear();

	Object object = new ArrayList<>();
	// ...
//	if(object instanceof ArrayList) {
//		ArrayList l = (ArrayList)object;
//		l.clear();
//	}

	if (object instanceof ArrayList l) {
		l.clear();
	}

	saluda(this.nombre);
	IO.println(getClass().getCanonicalName());
	Predicate<String> p = _ -> true;
	saluda(p);
}

/**
 * @hidden
 */
private void saluda(String cad) {
	IO.println("""
			<html>
			<body>
			<h1>☺️ Hola "%s"</h1>
			</body>
			</html>
			""".formatted(cad));
}

private void saluda(Predicate<String> predicate) {

	IO.println(predicate.test(nombre) ? "true" : "false");
}
