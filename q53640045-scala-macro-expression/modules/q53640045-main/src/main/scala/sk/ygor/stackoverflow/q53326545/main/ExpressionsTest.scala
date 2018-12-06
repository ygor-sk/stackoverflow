package sk.ygor.stackoverflow.q53326545.main

object ExpressionsTest {

  def main(args: Array[String]): Unit = {
    import sk.ygor.stackoverflow.q53326545.macros.ExpressionImplicits._
    val e1 = expr { x: Double => (x * 2) + (3.0 * x) }

    println(e1) // BinOp(+,BinOp(*,Var(x),Number(2.0)),BinOp(*,Number(3.0),Var(x)))

    val e2 = expr { (x: Double, y: Double) => x * y * x }
    println(e2) // BinOp(*,BinOp(*,Var(x),Var(y)),Var(x))

//     val e3 = expr { x: Double => x.toInt } // Fails during compilation
//    println(e3)
  }

}
