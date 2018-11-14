package sk.ygor.stackoverflow.q53296953

import org.json4s.DefaultFormats
import org.json4s.JsonAST._
import org.json4s.native.JsonMethods

object Main {

  def main(args: Array[String]): Unit = {
    implicit val formats: DefaultFormats = DefaultFormats

    val parsedJson = JsonMethods.parse(""" { "a" : 42} """, useBigIntForLong = false)
    parsedJson.extract[Map[String, Any]].foreach {
      case (name, value) => println(s"$name = $value (${value.getClass})")
    }

    val constructedJson = JObject(
      "alwaysBigInt" -> JInt(42),
      "alwaysLong" -> JLong(55),
    )
    constructedJson.extract[Map[String, Any]].foreach {
      case (name, value) => println(s"$name = $value (${value.getClass})")
    }

  }

}
