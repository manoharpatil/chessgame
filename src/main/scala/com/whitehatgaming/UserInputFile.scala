package com.whitehatgaming

import com.whitehatgaming.ChessGame.Move

import java.io.{FileReader, IOException, LineNumberReader}

class UserInputFile(fileName: String) {
  private val reader: LineNumberReader = new LineNumberReader(new FileReader(fileName))

  def nextMove(): Option[Move] = {
    val line: String = reader.readLine()
    if (line != null) {
      parseMove(line)
    } else {
      None
    }
  }

  private def parseMove(line: String): Option[Move] = {
    if (line.length == 4) {
      val startX: Int = line.charAt(0) - 'a'
      val startY: Int = '8' - line.charAt(1)
      val endX: Int = line.charAt(2) - 'a'
      val endY: Int = '8' - line.charAt(3)
      Some((startX, startY, endX, endY))
    } else {
      None
    }
  }

  def close(): Unit = {
    try {
      reader.close()
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }
}