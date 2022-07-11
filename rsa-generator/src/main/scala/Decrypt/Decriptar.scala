package Decrypt

import scala.io.StdIn.readLine
import commons.IOHelper
import scala.io.Source
import java.io._
import TextChunk._
import java.math.BigInteger
import java.util.Base64
import java.nio.charset.StandardCharsets
import commons.IOHelper

object Decriptar extends App {
  println("Oi, Alice. Insira o nome do arquivo com a chave privada: ")
  val fileKeys = readLine()
  val keyLines = IOHelper.openFile(fileKeys)
  val module = new BigInteger(keyLines.head)
  val privateKey = new BigInteger(keyLines.tail.head)

  println("Insira o nome do arquivo com o texto a ser descriptografado: ")
  val fileText = readLine()
  val textLines: List[String] = IOHelper.openFile(fileText)

  val finalText: String = textLines
    .map(line => new BigInteger(line))
    .map(encodedChunk => encodedChunk.modPow(privateKey, module))
    .map(originalChunk => new TextChunk(originalChunk).toString())
    .mkString

  val decodedFinalText = new String(
    Base64.getDecoder.decode(finalText)
  )

  println(
    "Insira o nome do arquivo para armazenar o texto descriptografado.\n O caminho de destino é relativo à pasta resource: "
  )
  val destFile = readLine()

  IOHelper.printToFile(new File("./src/main/resources/" + destFile)) { p =>
    p.write(decodedFinalText)
  }
}
