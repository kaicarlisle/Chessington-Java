package training.chessington.model;

import java.util.ArrayList;

import training.chessington.model.pieces.*;
import training.chessington.model.pieces.Piece.PieceType;

public class Board {

    private Piece[][] board = new Piece[8][8];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < 8; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public void move(Coordinates from, Coordinates to) {
        board[to.getRow()][to.getCol()] = board[from.getRow()][from.getCol()];
        board[from.getRow()][from.getCol()] = null;
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }
    
    public boolean isEmpty(Coordinates place) {
    	return this.get(place) == null;
    }
    
    public Board copy() {
    	Coordinates coords;
    	Board newboard = new Board();
    	for (int row = 0; row<=7; row++) {
    		for (int col =0; col<=7; col++) {
    			coords = new Coordinates(row, col);
    			newboard.placePiece(coords , this.get(coords));
    		}
    	}
    	
    	return newboard;
    }
    public boolean checkCheck(PlayerColour colour) {
    	Coordinates coords;
    	Coordinates kingCoords = new Coordinates(-1,-1);
    	kingFind:
    	for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				coords = new Coordinates(row, col);
				if (!this.isEmpty(coords)) {
					if (this.get(coords).getColour().equals(colour) && this.get(coords).getType().equals(PieceType.KING)) {
						kingCoords = coords;
						break kingFind;
					}
				}
			}
		}
    	
    	
    	for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				coords = new Coordinates(row, col);
				if (!this.isEmpty(coords)) {
					if (!this.get(coords).getColour().equals(colour) && !this.get(coords).getType().equals(PieceType.KING)) {
						for (Move enemyMove : this.get(coords).getAllowedMoves(coords, this)) {
							if (enemyMove.getTo().equals(kingCoords)) {
								return true;
							}
						}
					}
				}
			}
		}
    	return false;
    }
}
