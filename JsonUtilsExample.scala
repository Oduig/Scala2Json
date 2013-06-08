package com.gjos.scala.json

/*
 * Open source =)
 * June 5th, 2013
 * @author Guido Josquin
 */
object jsonUtilsExample {
  def main(args: Array[String]){
    val fullExample = Map(
  		"myKey1" -> List(1, "justAstring", 2.5, true, List("recursion!")),
  		"myKey2" -> Map(
  			"myKey2.1" -> List(
  				// Null becomes null
  				Map("myKey2.1.1" -> null),
  				// Can't encode to JSon, becomes null
  				666L,
  				// Long as Int
  				666L.toInt,
  				// Long as string
  				666L.toString
  			)
  		), 
  		"my\\&amp;Bad<'\"\'>Key" -> "my\\&amp;Bad<'\"\'>Value"
  	)
  	
  	val result1 = jsonUtils.encode(slashEscaper)(fullExample)
  	println(result1)
  	val result2 = jsonUtils.encode(urlEscaper)(fullExample)
  	println(result2)
  	val result3 = jsonUtils.encode(htmlEscaper)(fullExample)
  	println(result3)
  }
}