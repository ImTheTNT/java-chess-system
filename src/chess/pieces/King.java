package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece
{
  private ChessMatch match;

  public King (Board board, Color color, ChessMatch match)
  {
    super (board, color);
    this.match = match;
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

  private boolean testRookCastling (Position position)
  {
    ChessPiece p = (ChessPiece) getBoard ().piece (position);
    return p != null && p instanceof Rook && p.getColor () == getColor () && p.getMoveCount () == 0;
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

    // castling
    if (getMoveCount () == 0 && !match.getCheck ())
    {
      // right side castling
      Position posRR = new Position (position.getRow (), position.getColumn () + 3);
      if (testRookCastling (posRR))
      {
        Position p1 = new Position (position.getRow (), position.getColumn () + 1);
        Position p2 = new Position (position.getRow (), position.getColumn () + 2);

        if (getBoard ().piece (p1) == null && getBoard ().piece (p2) == null)
          matrix [position.getRow ()] [position.getColumn () + 2] = true;
      }

      // left side castling
      Position posRL = new Position (position.getRow (), position.getColumn () - 4);
      if (testRookCastling (posRL))
      {
        Position p1 = new Position (position.getRow (), position.getColumn () - 1);
        Position p2 = new Position (position.getRow (), position.getColumn () - 2);
        Position p3 = new Position (position.getRow (), position.getColumn () - 3);

        if (getBoard ().piece (p1) == null && getBoard ().piece (p2) == null && getBoard ().piece (p3) == null)
          matrix [position.getRow ()] [position.getColumn () - 2] = true;
      }
    }

    return matrix;
  }
}
