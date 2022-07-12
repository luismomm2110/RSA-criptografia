package Encrypt

import scala.io.StdIn.readLine
import commons.IOHelper
import scala.io.Source
import java.io._
import TextChunk._
import java.math.BigInteger
import java.util.Base64
import java.nio.charset.StandardCharsets

object Encriptar extends App {

  println("Oi, Bob. Insira o nome do arquivo com a chave: ")
  val fileKeys = readLine()
  val keyLines = IOHelper.openFile(fileKeys)
  val module = new BigInteger(keyLines.head)
  val publicKey = new BigInteger(keyLines.tail.head)
  val chunkSize = TextChunk.blockSize(module)

  println("Insira o nome do arquivo com o texto a ser criptografado: ")
  val fileText = readLine()
  val textLines = Source.fromResource(fileText).mkString

  val encodedText = Base64.getEncoder.encodeToString(
    textLines.getBytes()
  )

  println(
    "Insira o nome do arquivo para armazenar o texto criptofrado.\n O caminho de destino é relativo à pasta resource: "
  )
  val destFile = readLine()

  val encodedChunks: Iterator[BigInteger] = encodedText
    .grouped(chunkSize)
    .map(chunk => new TextChunk(chunk).bigIntValue())
    .map(originalChunk => originalChunk.modPow(publicKey, module))

  IOHelper.printToFile(new File("./src/main/resources/" + destFile)) { p =>
    encodedChunks.foreach(p.println)
  }
}
