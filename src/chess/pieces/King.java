package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece
{
  public King (Board board, Color color)
  {
    super (board, color);
  }
  
  @Override
  public String toString ()
  {
    return "K";
  }

  private boolean canMove (Position position)
  {
    ChessPiece p = (ChessPiece) getBoard ().piece (position);
    return p == null || p.getColor () != getColor ();
  }

  @Override
  public boolean[][] possibleMoves ()
  {
    boolean[][] matrix = new boolean[getBoard ().getRows ()][getBoard ().getColumns ()];

    Position p = new Position (0, 0);

    // up
    p.setPosition (position.getRow () - 1, position.getColumn ());
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // down
    p.setPosition (position.getRow () + 1, position.getColumn ());
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // left
    p.setPosition (position.getRow (), position.getColumn () - 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // right
    p.setPosition (position.getRow (), position.getColumn () + 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // up-left
    p.setPosition (position.getRow () - 1, position.getColumn () - 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // down-left
    p.setPosition (position.getRow () + 1, position.getColumn () - 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // up-right
    p.setPosition (position.getRow () - 1, position.getColumn () + 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    // down-right
    p.setPosition (position.getRow () + 1, position.getColumn () + 1);
    if (getBoard ().positionExists (p) && canMove (p))
      matrix [p.getRow ()] [p.getColumn ()] = true;

    return matrix;
  }
}
