package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Rook;

public class ChessMatch
{
  private int turn;
  private Color currentPlayer;
  private Board board;
  private boolean check;
  private boolean checkMate;

  private List <Piece> piecesOnBoard = new ArrayList <> ();
  private List <Piece> capturedPieces = new ArrayList <> ();

  public ChessMatch ()
  {
    board = new Board (8, 8);
    turn = 1;
    currentPlayer = Color.WHITE;
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

  public int getTurn ()
  {
    return turn;
  }

  public Color getCurrentPlayer ()
  {
    return currentPlayer;
  }

  public boolean getCheck ()
  {
    return check;
  }

  public boolean getCheckMate ()
  {
    return checkMate;
  }

  public boolean [][] possibleMoves (ChessPosition sourcePosition)
  {
    Position position = sourcePosition.toPosition ();
    validateSourcePosition (position);

    return board.piece (position).possibleMoves ();
  }

  public ChessPiece performChessMove (ChessPosition sourcePosition, ChessPosition targetPosition)
  {
    Position source = sourcePosition.toPosition ();
    Position target = targetPosition.toPosition ();
    validateSourcePosition (source);
    validateTargetPosition (source, target);

    Piece capturedPiece = makeMove (source, target);

    if (testCheck (currentPlayer))
    {
      undoMove(source, target, capturedPiece);
      throw new ChessException ("You can't put yourself in check.");
    }

    check = (testCheck (opponent (currentPlayer))) ? true : false;
    checkMate = (testCheckMate (opponent (currentPlayer))) ? true : false;

    nextTurn ();

    return (ChessPiece) capturedPiece;
  }

  private Piece makeMove (Position sourcePosition, Position targetPosition)
  {
    ChessPiece movedPiece = (ChessPiece) board.removePiece (sourcePosition);
    movedPiece.increaseMoveCount ();
    Piece capturedPiece = board.removePiece (targetPosition);
    board.placePiece (movedPiece, targetPosition);

    if (capturedPiece != null)
    {
      piecesOnBoard.remove (capturedPiece);
      capturedPieces.add (capturedPiece);
    }
    
    return capturedPiece;
  }

  private void undoMove (Position source, Position target, Piece capturedPiece)
  {
    ChessPiece p = (ChessPiece) board.removePiece (target);
    p.decreaseMoveCount ();
    board.placePiece (p, source);

    if (capturedPiece != null)
    {
      board.placePiece(capturedPiece, target);
      capturedPieces.remove (capturedPiece);
      piecesOnBoard.add (p);
    }
  }

  private void validateSourcePosition (Position position)
  {
    if (!board.thereIsAPiece (position))
      throw new ChessException ("No piece at source position.");
      
    if (currentPlayer != ((ChessPiece) board.piece (position)).getColor ())
      throw new ChessException ("Select a piece from your color.");

    if (!board.piece (position).isThereAnyPossibleMove ())
      throw new ChessException ("Selected piece has no possible moves.");
  }

  private void validateTargetPosition (Position source, Position target)
  {
    if (!board.piece (source).possibleMove (target))
      throw new ChessException ("Selected piece cannot move to target position.");
  }

  private void nextTurn ()
  {
    turn++;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  private Color opponent (Color color)
  {
    return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  private ChessPiece king (Color color)
  {
    List <Piece> list = piecesOnBoard.stream ().filter (x -> ((ChessPiece) x).getColor () == color).collect (Collectors.toList ());

    for (Piece p : list)
    {
      if (p instanceof King)
        return (ChessPiece) p;
    }
    throw new IllegalStateException ("There is no " + color + "king on the board.");
  }

  private boolean testCheck (Color color)
  {
    Position kingPosition = king (color).getChessPosition ().toPosition ();
    List <Piece> opponentPieces = piecesOnBoard.stream ().filter (x -> ((ChessPiece) x).getColor () == opponent (color)).collect (Collectors.toList ());

    for (Piece p : opponentPieces)
    {
      boolean [][] matrix = p.possibleMoves ();
      if (matrix [kingPosition.getRow ()] [kingPosition.getColumn ()])
        return true;
    }
    return false;
  }

  private boolean testCheckMate (Color color)
  {
    if (testCheck (color))
    {
      List <Piece> list = piecesOnBoard.stream ().filter (x -> ((ChessPiece) x).getColor () == color).collect (Collectors.toList ());

      for (Piece p : list)
      {
        boolean [][] matrix = p.possibleMoves ();
        for (int i = 0; i < board.getRows (); i++)
          for (int j = 0; j < board.getColumns (); j++)
              if (matrix [i] [j])
              {
                Position source = ((ChessPiece) p).getChessPosition ().toPosition ();
                Position target = new Position (i, j);
                Piece capturedPiece = makeMove (source, target);
                boolean testCheck = testCheck (color);
                undoMove (source, target, capturedPiece);

                if (!testCheck)
                  return false;
              }
      }
      return true;
    }
    else
      return false;
  }

  private void placeNewPiece (char column, int row, ChessPiece piece)
  {
    board.placePiece (piece, new ChessPosition (column, row).toPosition ());
    piecesOnBoard.add (piece);
  }

  private void initialSetup ()
  {
    //placeNewPiece ('a', 1, new Rook (board, Color.WHITE));
    //placeNewPiece ('h', 1, new Rook (board, Color.WHITE));
    //placeNewPiece ('a', 8, new Rook (board, Color.BLACK));
    //placeNewPiece ('h', 8, new Rook (board, Color.BLACK));

    // placeNewPiece('b', 1, new Knight (board, Color.WHITE));
    placeNewPiece('g', 1, new Knight (board, Color.WHITE));
    placeNewPiece('b', 8, new Knight (board, Color.BLACK));
    // placeNewPiece('g', 8, new Knight (board, Color.BLACK));

    // placeNewPiece('c', 1, new Bishop (board, Color.WHITE));
    placeNewPiece('f', 1, new Bishop (board, Color.WHITE));
    placeNewPiece('c', 8, new Bishop (board, Color.BLACK));
    // placeNewPiece('f', 8, new Bishop (board, Color.BLACK));

    // placeNewPiece ('d', 1, new Queen (board, Color.WHITE));
    // placeNewPiece ('d', 8, new Queen (board, Color.BLACK));

    // placeNewPiece ('e', 1, new King (board, Color.WHITE));
    // placeNewPiece ('e', 8, new King (board, Color.BLACK));

    // placeNewPiece ('a', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('b', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('c', 2, new Pawn (board, Color.WHITE));
    placeNewPiece ('d', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('e', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('f', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('g', 2, new Pawn (board, Color.WHITE));
    // placeNewPiece ('h', 2, new Pawn (board, Color.WHITE));

    // placeNewPiece ('a', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('b', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('c', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('d', 7, new Pawn (board, Color.BLACK));
    placeNewPiece ('e', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('f', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('g', 7, new Pawn (board, Color.BLACK));
    // placeNewPiece ('h', 7, new Pawn (board, Color.BLACK));

    placeNewPiece ('h', 7, new Rook (board, Color.WHITE));
    placeNewPiece ('d', 1, new Rook (board, Color.WHITE));
    placeNewPiece ('e', 1, new King (board, Color.WHITE));

    placeNewPiece ('h', 8, new Rook (board, Color.BLACK));
    placeNewPiece ('a', 8, new King (board, Color.BLACK));
  }
}
