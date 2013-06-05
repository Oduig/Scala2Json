package ScalaHelpers

/*
 * Open source =)
 * June 5th, 2014
 * @author Guido Josquin
 *
 * Encodes a Scala json-like object to a JSON string
 * Only the JS-compatible types are encoded; all others will become null.
 */
object jsonUtils extends htmlEscaper{
	type StringMap = Map[String, Any]
    
	def encode(root: Any): String = root match {
		case m: StringMap   => (for((k,v) <- m) yield ('\"' + escape(k) + '\"' + ": " + encode(v))).mkString("{", ",", "}")
		case l: List[Any]   => l.map(encode(_)).mkString("[", ",", "]")
		case b: Boolean     => b.toString
		case i: Int         => i.toString
		case d: Double      => d.toString
		case s: String      => '\"' + escape(s) + '\"'
		case _              => "null"
	}
    
}