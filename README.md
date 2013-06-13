Scala2Json
==========

A library that recursively encodes a Scala structure into Json, with various escaping options.
----------

**Simple usage example**

	import com.gjos.scala.json.jsonUtils
	val minimalExample = Map(
			"myKey1" -> List(1, "justAstring", 2.5, true, List("recursion!")),
			"myKey2" -> Map("justAnotherKey" -> "justAnotherValue")
		)
		
	val result = jsonUtils.encode(slashEscaper)(minimalExample)
	println(result)
	
**Output (this is a String)**

	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"justAnotherKey": "justAnotherValue"}}