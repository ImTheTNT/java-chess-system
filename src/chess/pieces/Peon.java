package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Peon extends ChessPiece
{
  public Peon (Board board, Color color)
  {
    super (board, color);
  }

  @Override
  public String toString ()
  {
    return "P";
  }

  @Override
  public boolean[][] possibleMoves ()
  {
    return null;
  }
}
