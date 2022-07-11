package commons

import scala.io.Source
import java.io._
import java.math.BigInteger
import java.util.Base64
import java.nio.charset.StandardCharsets

object IOHelper {

  def openFile(path: String): List[String] =
    try {
      return Source.fromResource(path).getLines.toList
    } catch {
      case e: FileNotFoundException =>
        println(s"Verifique se o arquivo está na pasta resource")
        throw e
      case e: IOException =>
        println("Erro ao abrir o arquivo")
        throw e
    }

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) }
    finally { p.close() }
  }
}
