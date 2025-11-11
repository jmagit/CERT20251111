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
	saluda(this.nombre);
	IO.println(getClass().getCanonicalName());
}

/**
 * @hidden
 */
private void saluda(String cad) {
	IO.println("Hola %s".formatted(cad));
}
