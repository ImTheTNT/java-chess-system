package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Peon;
import chess.pieces.Queen;
import chess.pieces.Bishop;
import chess.pieces.Horse;
import chess.pieces.Rook;

public class ChessMatch
{
  private Board board;

  public ChessMatch ()
  {
    board = new Board (8, 8);
    initialSetup ();
  }

  public ChessPiece [][] getPieces ()
  {
    ChessPiece [][] mat = new ChessPiece [board.getRows ()] [board.getColumns ()];

    for (int i = 0; i < board.getRows (); i++)
      for (int j = 0; j < board.getColumns (); j++)
        mat [i] [j] = (ChessPiece) board.piece(i, j);

    return mat;
  }

  public ChessPiece performChessMove (ChessPosition sourcePosition, ChessPosition targetPosition)
  {
    Position source = sourcePosition.toPosition ();
    Position target = targetPosition.toPosition ();
    validateSourcePosition (source);
    Piece capturedPiece = makeMove (source, target);
    return (ChessPiece) capturedPiece;
  }

  private Piece makeMove (Position sourcePosition, Position targetPosition)
  {
    Piece removedPiece = board.removePiece (sourcePosition);
    Piece capturedPiece = board.removePiece (targetPosition);
    board.placePiece (removedPiece, targetPosition);
    return capturedPiece;
  }

  private void validateSourcePosition (Position position)
  {
    if (!board.thereIsAPiece (position))
      throw new ChessException ("No piece at source position.");
  }

  private void placeNewPiece (char column, int row, ChessPiece piece)
  {
    board.placePiece (piece, new ChessPosition (column, row).toPosition ());
  }

  private void initialSetup ()
  {
    placeNewPiece ('a', 1, new Rook (board, Color.WHITE));
    placeNewPiece ('h', 1, new Rook (board, Color.WHITE));
    placeNewPiece ('a', 8, new Rook (board, Color.BLACK));
    placeNewPiece ('h', 8, new Rook (board, Color.BLACK));

    placeNewPiece('b', 1, new Horse (board, Color.WHITE));
    placeNewPiece('g', 1, new Horse (board, Color.WHITE));
    placeNewPiece('b', 8, new Horse (board, Color.BLACK));
    placeNewPiece('g', 8, new Horse (board, Color.BLACK));

    placeNewPiece('c', 1, new Bishop (board, Color.WHITE));
    placeNewPiece('f', 1, new Bishop (board, Color.WHITE));
    placeNewPiece('c', 8, new Bishop (board, Color.BLACK));
    placeNewPiece('f', 8, new Bishop (board, Color.BLACK));

    placeNewPiece ('d', 1, new Queen (board, Color.WHITE));
    placeNewPiece ('d', 8, new Queen (board, Color.BLACK));

    placeNewPiece ('e', 1, new King (board, Color.WHITE));
    placeNewPiece ('e', 8, new King (board, Color.BLACK));

    placeNewPiece ('a', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('b', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('c', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('d', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('e', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('f', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('g', 2, new Peon (board, Color.WHITE));
    placeNewPiece ('h', 2, new Peon (board, Color.WHITE));

    placeNewPiece ('a', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('b', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('c', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('d', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('e', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('f', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('g', 7, new Peon (board, Color.BLACK));
    placeNewPiece ('h', 7, new Peon (board, Color.BLACK));
  }
}
