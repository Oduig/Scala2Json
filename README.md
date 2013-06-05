Scala2Json
==========

A library that recursively encodes a Scala structure into Json, with various encoding options.
----------

**Simple usage example**

	import Scala2Json.JsonUtils
	val minimalExample = Map(
			"myKey1" -> List(1, "justAstring", 2.5, true, List("recursion!")),
			"myKey2" -> Map("justAnotherKey" -> "justAnotherValue")
		)
		
	val result = JSonUtils.encode(slashEscaper)(fullExample)
	println(result)
	
*Output*

	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"justAnotherKey": "justAnotherValue"}}
	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"justAnotherKey": "justAnotherValue"}}
	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"justAnotherKey": "justAnotherValue"}}