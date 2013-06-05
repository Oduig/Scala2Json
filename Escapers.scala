package ScalaHelpers

/*
 * Open source =)
 * June 5th, 2013
 * @author Guido Josquin
 *
 * Offers various ways to escape a string.
 */ 
trait escaper {
	// Abstract map from chars to replacement strings
	protected val charsToEscape: Map[Char, String]	
	
	// Generic escape method
	def escape(in: String): String = {
		def iter(s: List[Char], acc: List[Char]): List[Char] = s match {
			case Nil  => acc
			case c::xs => iter(xs, acc ++ charsToEscape.withDefaultValue(c)(c))
		}
		iter(in.toList, Nil).mkString("")
	}
}

object slashEscaper extends escaper {
	protected val charsToEscape = Map(
		'\'' -> "\\\'",
		'\"' -> "\\\"",
		'\\' -> "\\\\"
	)
}

object urlEscaper extends escaper {
	protected val charsToEscape = Map(
		'\'' -> "%27",
		'\"' -> "%22",
		'\\' -> "%5C"
	)
}

object htmlEscaper extends escaper {
	// Still need to escape \, since we can build strings within strings
	protected val charsToEscape = Map(
		'\'' -> "&apos;",
		'\"' -> "&quot;",
		'\\' -> "\\\\",
		'&'  -> "&amp;",
		'<'  -> "&lt;",
		'>'  -> "&gt;"
	)
}
