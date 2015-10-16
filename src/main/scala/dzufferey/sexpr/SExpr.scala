package dzufferey.sexpr

abstract class SExpr
case class SAtom(id: String) extends SExpr
case class SList(lst: List[SExpr]) extends SExpr

object SApplication {
  def apply(operator: String, arguments: List[SExpr]) = {
    SList(SAtom(operator) :: arguments)
  }
  def unapply(s: SExpr): Option[(String, List[SExpr])] = s match {
    case SList(SAtom(operator) :: arguments) => Some(operator -> arguments)
    case _ => None
  }
}

object SNil {
  def apply = SList(Nil)
  def unapply(s: SExpr): Boolean = s == SList(Nil)
}
