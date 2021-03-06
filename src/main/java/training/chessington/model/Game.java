package training.chessington.model;

import training.chessington.model.pieces.*;
import training.chessington.model.pieces.Piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int SIZE = 8;
    private final Board board;

    private PlayerColour nextPlayer = PlayerColour.WHITE;

    private boolean isEnded = false;

    public Game(Board board) {
        this.board = board;
    }

    public Piece pieceAt(int row, int col) {
        return board.get(new Coordinates(row, col));
    }

    public List<Move> getAllowedMoves(Coordinates from) {
        if (isEnded) {
            return new ArrayList<>();
        }

        Piece piece = board.get(from);
        if (piece == null || piece.getColour() != nextPlayer) {
            return new ArrayList<>();
        }

        return piece.getAllowedMoves(from, board);
    }

    public void makeMove(Move move) throws InvalidMoveException {
        if (isEnded) {
            throw new InvalidMoveException("Game has ended!");
        }

        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        Piece piece = board.get(from);
        if (piece == null) {
            throw new InvalidMoveException(String.format("No piece at %s", from));
        }

        if (piece.getColour() != nextPlayer) {
            throw new InvalidMoveException(String.format("Wrong colour piece - it is %s's turn", nextPlayer));
        }

        if (!piece.getAllowedMoves(move.getFrom(), board).contains(move)) {
            throw new InvalidMoveException(String.format("Cannot move piece %s from %s to %s", piece, from, to));
        }

        board.move(from, to);
        if ((to.getRow() == 0 || to.getRow() == 7) && board.get(to).getType().equals(PieceType.PAWN)) {
        	Queen queenPiece = new Queen(board.get(to).getColour());
        	board.placePiece(to, queenPiece);
        }
        
        PlayerColour colour = PlayerColour.BLACK;
        if (piece.getColour().equals(PlayerColour.BLACK)) {
        	colour = PlayerColour.WHITE;
        }
        
        Coordinates kingCoords = new Coordinates(-1,-1);
        Coordinates coords;
        kingFind:
		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				coords = new Coordinates(row, col);
				if (!board.isEmpty(coords)) {
					if (board.get(coords).getColour().equals(colour) && board.get(coords).getType().equals(PieceType.KING)) {
						kingCoords = coords;
						break kingFind;
					}
				}
			}
		}
        Piece king = board.get(kingCoords);
        if (board.checkCheck(colour) && king.getAllowedMoves(kingCoords, board).size() == 0) {
        	this.isEnded = true;
        }
        	
        nextPlayer = nextPlayer == PlayerColour.WHITE ? PlayerColour.BLACK : PlayerColour.WHITE;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getResult() {
        return null;
    }
}
