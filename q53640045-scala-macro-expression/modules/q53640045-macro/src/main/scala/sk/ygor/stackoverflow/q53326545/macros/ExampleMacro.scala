package sk.ygor.stackoverflow.q53326545.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object ExampleMacro {

  def expr(expr: AnyRef): Expression = macro expr_impl

  def expr_impl(c: blackbox.Context)(expr: c.Expr[AnyRef]): c.Expr[Expression] = {
    import c.universe._

    def treeToExpression(functionBody: c.Tree): c.Expr[Expression] = {
      functionBody match {
        case Apply(Select(leftTree, operator), List(rightTree)) =>
          val operatorName = Constant(operator.toString)
          c.Expr[Expression](q"sk.ygor.stackoverflow.q53326545.macros.BinOp($operatorName, ${treeToExpression(leftTree)}, ${treeToExpression(rightTree)})")
        case Ident(TermName(varName)) =>
          c.Expr[Expression](q"sk.ygor.stackoverflow.q53326545.macros.Var($varName)")
        case Literal(Constant(num)) if num.isInstanceOf[java.lang.Number] =>
          c.Expr[Expression](q"sk.ygor.stackoverflow.q53326545.macros.Number(${num.asInstanceOf[java.lang.Number].doubleValue()})")
        case unsupported =>
          sys.error("Unsupported function body: " + unsupported);
      }
    }

    expr.tree match {
      case Function(_, body) => treeToExpression(body)
      case unsupported =>
        sys.error("Only functions are accepted. Got: " + unsupported);
    }

  }
}
