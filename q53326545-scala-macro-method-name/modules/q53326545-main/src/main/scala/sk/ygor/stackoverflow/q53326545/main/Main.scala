package sk.ygor.stackoverflow.q53326545.main

object Main {

  def main(args: Array[String]): Unit = {

    class Abc {
      def met(): Unit = ???
    }
    case class X() {
      def met(): Unit = ???

      def abc(): Abc = ???
    }
    val a = new Abc()
    val x = X()

    import sk.ygor.stackoverflow.q53326545.macros.ExampleMacro.methodName

    println(methodName(Main.main(Array("foo", "bar"))))
    println(methodName(a.met()))
    println(methodName(new Abc().met()))
    println(methodName(X()))
    println(methodName(X().met()))
    println(methodName(x.met()))
    println(methodName(x.abc().met()))
    println(methodName("a".getClass))
  }

}
