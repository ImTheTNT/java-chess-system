package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece
{
  public Queen (Board board, Color color)
  {
    super (board, color);
  }

  @Override
  public String toString ()
  {
    return "Q";
  }

  @Override
  public boolean[][] possibleMoves ()
  {
    boolean [][] matrix = new boolean [getBoard ().getRows ()] [getBoard ().getColumns ()];
    Position pos = new Position (0, 0);

    // up
    pos.setPosition (position.getRow () - 1, position.getColumn ());
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setRow (pos.getRow () - 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // down
    pos.setPosition (position.getRow () + 1, position.getColumn ());
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setRow (pos.getRow () + 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // left
    pos.setPosition (position.getRow (), position.getColumn () - 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setColumn (pos.getColumn () - 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // right
    pos.setPosition (position.getRow (), position.getColumn () + 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setColumn (pos.getColumn () + 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // up-left
    pos.setPosition (position.getRow () - 1, position.getColumn () - 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setPosition(pos.getRow () - 1, pos.getColumn () - 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // up-right
    pos.setPosition (position.getRow () + 1, position.getColumn () - 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setPosition(pos.getRow () + 1, pos.getColumn () - 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // down-left
    pos.setPosition (position.getRow () - 1, position.getColumn () + 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setPosition(pos.getRow () - 1, pos.getColumn () + 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    // down-right
    pos.setPosition (position.getRow () + 1, position.getColumn () + 1);
    while (getBoard ().positionExists (pos) && !getBoard ().thereIsAPiece (pos))
    {
      matrix [pos.getRow ()] [pos.getColumn ()] = true;
      pos.setPosition(pos.getRow () + 1, pos.getColumn () + 1);
    }
    if (getBoard ().positionExists (pos) && isThereOpponentPiece (pos))
      matrix [pos.getRow ()] [pos.getColumn ()] = true;

    return matrix;
  }
}
