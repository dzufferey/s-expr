package dzufferey.sexpr

import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.StreamReader
import java.io.Reader

object Parser extends RegexParsers {

  protected override val whiteSpace = """(\s|;.*)+""".r

  def nonWhite: Parser[String] = """[^()\s]+""".r

  def sExpr: Parser[SExpr] = (
    "\"" ~> "[^\"]*".r <~ "\"" ^^ {s => SAtom("\""+s+"\"")} //no escaping or anything. quote to quote only.
    | "(" ~> rep(sExpr) <~ ")" ^^ { case args => SList(args) }
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
