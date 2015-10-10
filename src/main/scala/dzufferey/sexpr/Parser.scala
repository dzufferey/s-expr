package dzufferey.sexpr

import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.StreamReader
import java.io.Reader

object Parser extends RegexParsers {

  protected override val whiteSpace = """(\s|;.*)+""".r

  def nonWhite: Parser[String] = """[^()\s]+""".r ^^ { _.toString }

  def sExpr: Parser[SExpr] = (
      "(" ~ ")" ^^^ SNil
    | "(" ~> (nonWhite ~ rep(sExpr)) <~ ")" ^^ { case op ~ args => SApplication(op, args) }
    | nonWhite                          ^^ { op => SAtom(op) }
  )

  def sExprs: Parser[List[SExpr]] = (
    sExpr ~ sExprs ^^ { case a ~ b => a :: b }
  | whiteSpace ~> sExprs
  | success(Nil)
  )

  def parse(str: String): Option[List[SExpr]] = {
    val result = parseAll(sExprs, str)
    if (result.successful) {
      val cmds = result.get
      Some(cmds)
    } else {
      None
    }
  }
  
  def parse(rdr: Reader): Option[List[SExpr]] = {
    val result = parseAll(sExprs, StreamReader(rdr))
    if (result.successful) {
      val cmds = result.get
      Some(cmds)
    } else {
      None
    }
  }

}
