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

	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"myKey2.1": [{"myKey2.1.1": null},null,666,"666"]},"my\\&amp;Bad<\'\"\'>Key": "my\\&amp;Bad<\'\"\'>Value"}

	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"myKey2.1": [{"myKey2.1.1": null},null,666,"666"]},"my%5C&amp;Bad<%27%22%27>Key": "my%5C&amp;Bad<%27%22%27>Value"}

	{"myKey1": [1,"justAstring",2.5,true,["recursion!"]],"myKey2": {"myKey2.1": [{"myKey2.1.1": null},null,666,"666"]},"my\\&amp;amp;Bad&lt;&apos;&quot;&apos;&gt;Key": "my\\&amp;amp;Bad&lt;&apos;&quot;&apos;&gt;Value"}