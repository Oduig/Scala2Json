package com.gjos.scalahelpers

/*
 * Open source =)
 * June 5th, 2013
 * @author Guido Josquin
 *
 * Offers various ways to escape a string.
 */ 
trait Escaper {
	// Abstract map from chars to replacement strings
	protected val charsToEscape: Map[Char, String]	
	
	// Generic escape method
	def escape(in: String): String = {
		def iter(s: List[Char], acc: List[Char]): List[Char] = s match {
			case Nil  => acc
			case c::xs => iter(xs, acc ++ charsToEscape.withDefaultValue(c.toString)(c))
		}
		iter(in.toList, Nil).mkString("")
	}
}

object slashEscaper extends Escaper {
	val charsToEscape = Map(
		'\'' -> "\\\'",
		'\"' -> "\\\"",
		'\\' -> "\\\\"
	)
}

object urlEscaper extends Escaper {
	val charsToEscape = Map(
		'\'' -> "%27",
		'\"' -> "%22",
		'\\' -> "%5C"
	)
}

object htmlEscaper extends Escaper {
	// Still need to escape \, since we can build strings within strings
	val charsToEscape = Map(
		'\'' -> "&apos;",
		'\"' -> "&quot;",
		'\\' -> "\\\\",
		'&'  -> "&amp;",
		'<'  -> "&lt;",
		'>'  -> "&gt;"
	)
}
