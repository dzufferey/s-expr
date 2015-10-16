package dzufferey.sexpr

import java.io.{BufferedWriter,StringWriter}

//TODO line width and indentation

object Printer {
  
  def apply(writer: BufferedWriter, sexpr: SExpr): Unit = sexpr match {
    case SAtom(id) => writer.write(id)
    case SList(arguments) =>
      writer.append('(')
      var first = true
      for (a <- arguments) {
        if (first) {
          first = false
        } else {
          writer.append(' ')
        }
        apply(writer, a)
      }
      writer.append(')')
  }

  def apply(sexpr: SExpr): String = {
    val swriter = new StringWriter()
    val bwriter = new BufferedWriter(swriter)
    apply(bwriter, sexpr)
    bwriter.flush
    swriter.toString
  }

}
