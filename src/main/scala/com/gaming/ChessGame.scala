package com.gaming

object ChessGame {
  type Move = (Int, Int, Int, Int) // (startX, startY, endX, endY)

  def main(args: Array[String]): Unit = {
    val userInput = new UserInputFile("src/main/scala/input/checkmate.txt")
    // val userInput = new UserInputFile("src/main/scala/input/sample-moves.txt")
    // val userInput = new UserInputFile("src/main/scala/input/sample-moves-invalid.txt")
    // val userInput = new UserInputFile("src/main/scala/input/valid-moves.txt")
    val board = new ChessBoard()
    var currentPlayer = 1

    var move: Option[Move] = userInput.nextMove()
    while (move.isDefined) {
      move.foreach { case (startX, startY, endX, endY) =>
        println(s"Processing move: ${board.getPiece(startX, startY).name} at ($startX, $startY) to ($endX, $endY)")

        if (isValidMove(startX, startY, endX, endY, board, currentPlayer)) {
          makeMove(startX, startY, endX, endY, board)
          currentPlayer = 3 - currentPlayer // Switch player
        } else {
          println("Invalid move!")
        }
      }

      displayBoard(board)
      move = userInput.nextMove()
    }

    userInput.close()
  }

  def isValidMove(startX: Int, startY: Int, endX: Int, endY: Int, board: ChessBoard, currentPlayer: Int): Boolean = {
    val piece = board.getPiece(startX, startY)
    val targetPiece = board.getPiece(endX, endY)

    // Check if the start square contains a piece belonging to the current player
    if (piece.player != currentPlayer) return false

    // Check if the destination square is occupied by the player's own piece
    if (targetPiece != null && targetPiece.player == currentPlayer) return false

    // Validate the move based on the piece type
    piece match {
      case King(_) =>
        val dx = math.abs(endX - startX)
        val dy = math.abs(endY - startY)
        (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1)

      case Bishop(_) =>
        math.abs(endX - startX) == math.abs(endY - startY)

      case Rook(_) =>
        startX == endX || startY == endY

      case Queen(_) =>
        math.abs(endX - startX) == math.abs(endY - startY) || startX == endX || startY == endY

      case Knight(_) =>
        val dx = math.abs(endX - startX)
        val dy = math.abs(endY - startY)
        (dx == 1 && dy == 2) || (dx == 2 && dy == 1)

      case pawn: Pawn =>
        val dy = endY - startY
        if (math.abs(dy) == 2 && !pawn.moved) {
          // Check if there are any pieces in the way
          val direction = if (dy > 0) 1 else -1
          for (y <- startY + direction to endY - direction by direction) {
            if (board.getPiece(startX, y) != null) return false
          }
          true
        } else if (math.abs(dy) == 1) {
          val dx = math.abs(endX - startX)
          if (dx == 0) {
            // Moving forward without capturing
            targetPiece == null
          } else if (dx == 1) {
            // Capturing diagonally
            targetPiece != null && targetPiece.player != currentPlayer
          } else {
            false
          }
        } else {
          false
        }

      case _ => false // Invalid piece type
    }
  }

  def makeMove(startX: Int, startY: Int, endX: Int, endY: Int, board: ChessBoard): Unit = {
    val piece = board.getPiece(startX, startY)
    val targetPiece = board.getPiece(endX, endY)

    // Remove target piece if present
    if (targetPiece != null) {
      board.removePiece(endX, endY)
    }

    // Move the piece to the destination square
    board.movePiece(startX, startY, endX, endY)

    // Update pawn's moved flag
    piece match {
      case pawn: Pawn =>
        if (!pawn.moved) {
          pawn.moved = true
        }
      case _ =>
    }
  }

  def displayBoard(board: ChessBoard): Unit = {
    for (y <- 0 until 8) {
      for (x <- 0 until 8) {
        val piece = board.getPiece(x, y)
        val symbol = piece match {
          case King(1) => "K"
          case King(2) => "k"
          case Queen(1) => "Q"
          case Queen(2) => "q"
          case Rook(1) => "R"
          case Rook(2) => "r"
          case Bishop(1) => "B"
          case Bishop(2) => "b"
          case Knight(1) => "N"
          case Knight(2) => "n"
          case Pawn(1, _) => "P"
          case Pawn(2, _) => "p"
          case _ => "."
        }
        print(s"$symbol ")
      }
      println()
    }
    println()
  }
}

sealed abstract class ChessPiece(val player: Int) {
  def name: String
}

case class King(override val player: Int) extends ChessPiece(player) {
  def name: String = "King"
}

case class Queen(override val player: Int) extends ChessPiece(player) {
  def name: String = "Queen"
}

case class Rook(override val player: Int) extends ChessPiece(player) {
  def name: String = "Rook"
}

case class Bishop(override val player: Int) extends ChessPiece(player) {
  def name: String = "Bishop"
}

case class Knight(override val player: Int) extends ChessPiece(player) {
  def name: String = "Knight"
}

case class Pawn(override val player: Int, var moved: Boolean = false) extends ChessPiece(player) {
  def name: String = "Pawn"
}
