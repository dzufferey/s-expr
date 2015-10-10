package dzufferey.sexpr

abstract class SExpr
case class SAtom(id: String) extends SExpr
case class SApplication(operator: String, arguments: List[SExpr]) extends SExpr
case object SNil extends SExpr

