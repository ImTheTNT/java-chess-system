package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece
{
  private ChessMatch match;

  public Pawn (Board board, Color color, ChessMatch match)
  {
    super (board, color);
    this.match = match;
  }

  @Override
  public String toString ()
  {
    return "P";
  }

  @Override
  public boolean[][] possibleMoves ()
  {
    boolean [][] matrix = new boolean [getBoard ().getRows ()] [getBoard ().getColumns ()];
    Position pos = new Position (0, 0);

    if (getColor () == Color.WHITE)
    {
      pos.setPosition (position.getRow () - 1, position.getColumn ());
      if (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
      {
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

        pos.setPosition (position.getRow () - 2, position.getColumn ());
        if (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos) && getMoveCount () == 0)
          matrix [pos.getRow ()] [pos.getColumn ()] = true;
      }

      pos.setPosition (position.getRow () - 1, position.getColumn () - 1);
      if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

      pos.setPosition (position.getRow () - 1, position.getColumn () + 1);
      if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

      // en passant
      if (position.getRow () == 3)
      {
        Position left = new Position (position.getRow (), position.getColumn () - 1);
        Position right = new Position (position.getRow (), position.getColumn () + 1);

        if (getBoard ().positionExists (left) && isThereOpponentPiece (left) && getBoard ().piece (left) == match.getEnPassantVulnerable ())
          matrix [left.getRow () - 1] [left.getColumn ()] = true;
        if (getBoard ().positionExists (right) && isThereOpponentPiece (right) && getBoard ().piece (right) == match.getEnPassantVulnerable ())
          matrix [right.getRow () - 1] [right.getColumn ()] = true;
      }
    }
    else
    {
      pos.setPosition (position.getRow () + 1, position.getColumn ());
      if (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
      {
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

        pos.setPosition (position.getRow () + 2, position.getColumn ());
        if (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos) && getMoveCount () == 0)
          matrix [pos.getRow ()] [pos.getColumn ()] = true;
      }

      pos.setPosition (position.getRow () + 1, position.getColumn () - 1);
      if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

      pos.setPosition (position.getRow () + 1, position.getColumn () + 1);
      if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
        matrix [pos.getRow ()] [pos.getColumn ()] = true;

      // en passant
      if (position.getRow () == 4)
      {
        Position left = new Position (position.getRow (), position.getColumn () - 1);
        Position right = new Position (position.getRow (), position.getColumn () + 1);

        if (getBoard ().positionExists (left) && isThereOpponentPiece (left) && getBoard ().piece (left) == match.getEnPassantVulnerable ())
          matrix [left.getRow () + 1] [left.getColumn ()] = true;
        if (getBoard ().positionExists (right) && isThereOpponentPiece (right) && getBoard ().piece (right) == match.getEnPassantVulnerable ())
          matrix [right.getRow () + 1] [right.getColumn ()] = true;
      }
    }

    return matrix;
  }
}
