package com.gjos.scalahelpers

/*
 * Open source =)
 * June 5th, 2013
 * @author Guido Josquin
 *
 * Encodes a Scala json-like object to a JSON string
 * Only the JS-compatible types are encoded; all others will become null.
 */
object jsonUtils {
	type StringMap = Map[String, Any]
    
	def encode(esc: Escaper = slashEscaper)(root: Any): String = root match {
		case m: StringMap   => (for((k,v) <- m) yield ('\"' + esc.escape(k) + '\"' + ": " + encode(esc)(v))).mkString("{", ",", "}")
		case l: List[Any]   => l.map(encode(esc)(_)).mkString("[", ",", "]")
		case b: Boolean     => b.toString
		case i: Int         => i.toString
		case d: Double      => d.toString
		case s: String      => '\"' + esc.escape(s) + '\"'
		case _              => "null"
	}
    
}