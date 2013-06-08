package com.gjos.scala.json

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
  val charsToEscape = Map(
    '\'' -> "&apos;",
    '\"' -> "&quot;",
    '&'  -> "&amp;",
    '<'  -> "&lt;",
    '>'  -> "&gt;",
    '\\' -> "&#92;"
  )
}

/*
 * Similar to Escaper, but in reverse
 */
trait UnEscaper {
  // Abstract map from strings to replacement chars
  protected val stringsToUnescape: Map[String, Char]  
  
  // Generic escape method
  def unescape(in: String): String = {
    /*
     * @param s: a list of characters to process
     * @param partialMatch: a partially matched list of characters, for example ('&', 'a', 'm') is a partial match of "&amp;"
     * @param acc: an accumulator to store the result, for tail recursion
     */
    def iter(s: List[Char], partialMatch: List[Char], acc: List[Char]): List[Char] = s match {
      case Nil  => partialMatch ++ acc
      case ch::xs => {
        val possibleMatch = partialMatch :+ ch
        // Check if the possibleMatch is a full match
        stringsToUnescape.get(possibleMatch.mkString) match{
          // Check if the possibleMatch is a partial match. If so, continue with possibleMatch, otherwise, add it to the accumulator and start over with an empty match
          case None          => if(stringsToUnescape.keySet.exists(_.startsWith(possibleMatch))) iter(xs, possibleMatch, acc) else iter(xs, Nil, acc ++ possibleMatch)
          // Add the value of the map to the accumulator and recurse
          case Some(c: Char) => iter(xs, Nil, acc :+ c)
        }
      }
    }
    iter(in.toList, Nil, Nil).mkString
  }
}

object slashUnEscaper extends UnEscaper {
  val stringsToUnescape = Map(
    "\\\'" -> '\'',
    "\\\"" -> '\"',
    "\\\\" -> '\\'
  )
}

object urlUnEscaper extends UnEscaper {
  val stringsToUnescape = Map(
    "%27" -> '\'',
    "%22" -> '\"',
    "%5C" -> '\\'
  )
}

object htmlUnEscaper extends UnEscaper {
  val stringsToUnescape = Map(
    "&apos;" -> '\'',
    "&quot;" -> '\"',
    "&amp;"  -> '&',
    "&lt;"   -> '<',
    "&gt;"   -> '>',
    "&#92;"  -> '\\'
  )  
}