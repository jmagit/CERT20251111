import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/// # Heading
/// 
/// ## Sub-heading
/// Paragraphs are separated
/// by a blank line.
/// Two spaces at the end of a line
/// produces a line break.
/// Text attributes _italic_,
/// **bold**, `monospace`.
/// ## Sub-heading
/// Horizontal rule:
/// ---
/// Bullet list:
///   * apples
///   * oranges
///   * pears
/// Numbered list:
///   1. wash
///   2. rinse
///   3. repeat
/// A [link](http://example.com).
/// ![Image](https://via.placeholder.com/150)
/// > Markdown uses email-style > characters for blockquoting.
String nombre = "mundo";

/// Esto es un ejemplo con __CommonMark__ de *Markdown* en Javadoc
///
/// Lista
/// - ele 1
///	  - a
///	  - b
/// - ele 2
///
/// | A | B |
/// | ---- | ----- |
/// | 1 | 2 |
/// | 3 | 4 |
///
/// ```
/// /** Hello World! */
/// public class HelloWorld {
///     public static void main(String... args) {
///         System.out.println("Hello World!"); // the traditional example
///     }
/// }
/// ```
///
/// @since 11
void main(String[] args) {
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
////		default -> rslt = "no se";
////		default -> null;
//		default -> throw new IllegalArgumentException("index invalido");
//	}).toUpperCase();
	return (switch (index) {
	case 1, 3, 5, 7, 9 : 
		yield "impar";
	case 2, 4, 6, 8 : 
		yield "par";
	default : throw new IllegalArgumentException("index invalido");
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

	if(object instanceof ArrayList l) {
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
