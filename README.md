---
### Chess Board

 A computer program to allow two human players to play chess.

---

### Problem

> A good reference for the rules and general set up for chess can be found on Wikipedia:
http://en.wikipedia.org/wiki/Rules_of_chess

The program should simply read in moves and validate them, tracking and showing the board state. It should determine if 
a move leaves the player is in check. It does not need to handle checkmate.

A single implementation com.gaming.UserInputFile is also provided which will read the moves from a text file. 
Three sample move files are provided in the data directory. Feel free to add additional files and/or write automated tests as required.

> #### checkmate.txt
- e2e4 
- e7e%5
- f1c4
- b8c6
- d1f3
- d7d6
- f3f7

> #### sample-moves.txt
- e2e4
- e7e5
- b1c3
- d7d6
- h2h3
- c8e6
- h1h2

> #### sample-moves-invalid.txt
- e2e4
- e7e5
- b1b3
- d7d6
- h2h3
- c8e6
- h1h2
- h8h4

Please see the Code for UserInputFile to see how the coordinate system for the chess board is represented in the input data.

---

### Requirements
1. The board should start in the standard chess starting state with all the 16 pieces lined up for each player.

2. Play starts with player 1 (white) and on each valid move alternates to the other player. On an invalid move the existing player stays in control.

3. The moves must be read in using the supplied UserInputFile class until there are no more moves.

4. All moves must have a piece on the starting square and either an opponent piece or nothing on the destination square Anything else is invalid.

5. Validate the move according to the moves allowed by the piece on the starting square:
- The king can move only 1 square but in any direction.
- The bishop can move any number of squares but only diagonally.
- The rook can move any number of squares but only horizontally or vertically.
- The queen can move any number of squares horizontally, vertically or diagonally.
- The knight can move in an L shape with sides of 2 and 1 squares respectively. That is 8 different possible moves. Unlike other pieces it jumps over other pieces.
- The pawn can move one or two squares forward on its first move (when not taking an opponent piece).
- The pawn can move one square forward on subsequent moves (when not taking an opponent piece).
- The pawn can move one square forward diagonally if taking an opponent piece.

6. After each successful move render the board in simple ASCII form. It is suggested that player 1 is represented by upper-case characters and player 2 by lower-case characters. The conventional characters to use here are: Rook, kNight, Bishop, King, Queen, Pawn.

7. If the destination square contains an opponent piece then that piece is removed from the board. Unless that piece is a King where rules around check apply (see later).

8. For pieces other than the knight disallow the move if there are any other pieces in the way between the start and end square.

9. If a move ends with a player’s king under attack that is “check”.

10. A player cannot end their own move in check.

11. If a player starts their move in check this should be displayed as “in check”.
---

### Implementation Details

---

### Example Output

---

### Time Complexity

The time complexity of the program can be analyzed as follows:

---

### Space Complexity

The space complexity of the program can be analyzed as follows:

---
