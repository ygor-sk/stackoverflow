package sk.ygor.stackoverflow.q53326545.macros

abstract class Expression
case class Var(name: String) extends Expression
case class Number(num: Double) extends Expression
case class BinOp(operator: String, left: Expression, right: Expression) extends Expression
