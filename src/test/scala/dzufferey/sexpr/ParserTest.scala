package dzufferey.sexpr

import org.scalatest._
import java.io._

class ParserTest extends FunSuite {

  val resources = "src/test/resources/"

  test("260.smt2") {
    val reader = new BufferedReader(new FileReader(resources + "260.smt2"))
    Parser.parse(reader) match {
      case Some(lst) =>
        assert(lst.length == 22)
        assert(lst.head == SApplication("set-logic", List(SAtom("QF_NRA"))))
        assert(lst.last == SApplication("exit", Nil))
      case None =>
        sys.error("could not parse")
    }

  }

}
