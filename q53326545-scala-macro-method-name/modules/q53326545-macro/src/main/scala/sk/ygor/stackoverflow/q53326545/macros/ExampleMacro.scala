package sk.ygor.stackoverflow.q53326545.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object ExampleMacro {

  final val useFullyQualifiedName = false

  def methodName(param: Any): String = macro debugParameters_Impl

  def debugParameters_Impl(c: blackbox.Context)(param: c.Expr[Any]): c.Expr[String] = {
    import c.universe._

    param.tree match {
      case Apply(Select(t, TermName(methodName)), _) =>
        val baseClass = t.tpe.resultType.baseClasses.head // there may be a better way than this line
        val className = if (useFullyQualifiedName) baseClass.fullName else baseClass.name
        c.Expr[String](Literal(Constant(className + "." + methodName)))
      case _ => sys.error("Not a method call: " + show(param.tree))
    }
  }
}
