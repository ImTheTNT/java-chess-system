package boardgame;

public class Board
{
  private int rows;
  private int columns;
  private Piece [][] pieces;

  public Board (int rows, int columns)
  {
    if (rows < 1 || rows < 1)
      throw new BoardException ("There must be at least 1 row and 1 column.");
    this.rows = rows;
    this.columns = columns;
    pieces = new Piece [rows] [columns];
  }

  public int getRows ()
  {
    return rows;
  }

  public int getColumns ()
  {
    return columns;
  }

  public Piece piece (int row, int column)
  {
    if (!positionExists (row, column))
      throw new BoardException ("Position outside board boundaries.");
    return pieces [row] [column];
  }

  public Piece piece (Position position)
  {
    if (!positionExists (position))
      throw new BoardException ("Position outside board boundaries.");
    return pieces [position.getRow ()] [position.getColumn ()];
  }

  public void placePiece (Piece piece, Position position)
  {
    if (thereIsAPiece (position))
      throw new BoardException ("Position" + position + "already occupied.");
    pieces [position.getRow ()] [position.getColumn ()] = piece;
    piece.position = position;
  }

  public Piece removePiece (Position position)
  {
    if (!positionExists (position))
      throw new BoardException ("Position outside board boundaries.");

    if (piece (position) == null)
      return null;
    else
    {
      Piece removedPiece = piece (position);
      removedPiece.position = null;
      pieces [position.getRow()] [position.getColumn()] = null;
      return removedPiece;
    }
  }

  private boolean positionExists (int row, int column)
  {
    return row >= 0 && row < rows && column >= 0 && column < columns;
  }

  public boolean positionExists (Position position)
  {
    return positionExists(position.getRow (), position.getColumn ());
  }

  public boolean thereIsAPiece (Position position)
  {
    if (!positionExists (position))
      throw new BoardException ("Position outside board boundaries.");
    return piece (position) != null;
  }
}
