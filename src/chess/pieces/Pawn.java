package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece
{
  public Pawn (Board board, Color color)
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
    }

    return matrix;
  }
}
