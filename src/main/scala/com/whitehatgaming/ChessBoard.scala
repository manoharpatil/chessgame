package com.whitehatgaming

class ChessBoard {
  private val board: Array[Array[ChessPiece]] = Array.ofDim[ChessPiece](8, 8)

  // Initialize the board with pieces in their starting positions
  for (x <- 0 until 8) {
    board(x)(1) = Pawn(1)
    board(x)(6) = Pawn(2)
  }

  board(0)(0) = Rook(1)
  board(7)(0) = Rook(1)
  board(0)(7) = Rook(2)
  board(7)(7) = Rook(2)

  board(1)(0) = Knight(1)
  board(6)(0) = Knight(1)
  board(1)(7) = Knight(2)
  board(6)(7) = Knight(2)

  board(2)(0) = Bishop(1)
  board(5)(0) = Bishop(1)
  board(2)(7) = Bishop(2)
  board(5)(7) = Bishop(2)

  board(3)(0) = Queen(1)
  board(3)(7) = Queen(2)

  board(4)(0) = King(1)
  board(4)(7) = King(2)

  def getPiece(x: Int, y: Int): ChessPiece = board(x)(y)

  def movePiece(startX: Int, startY: Int, endX: Int, endY: Int): Unit = {
    val piece = board(startX)(startY)
    board(startX)(startY) = null
    board(endX)(endY) = piece
  }

  def removePiece(x: Int, y: Int): Unit = {
    board(x)(y) = null
  }
}
