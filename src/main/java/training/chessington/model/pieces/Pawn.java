package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
    	ArrayList<Move> moves = new ArrayList<Move>();
    	Coordinates to;
    	
    	int dir;
    	int homeRow;
    	int endBoard;
    	
    	if (this.getColour().equals(PlayerColour.WHITE)) {
    		dir = -1;
    		homeRow = 6;
    		endBoard = 0;
    	} else {
    		dir = 1;
    		homeRow = 1;
    		endBoard = 7;
    	}
    	
    	if (from.getRow() != endBoard) {
	    	to = from.plus(dir, 0);
	    	if (board.isEmpty(to)) {
	    		//one place in front is clear
	    		moves.add(new Move(from, to));
	    		
	    		to = to.plus(dir, 0);
	    		//two places in front is also clear
	    		if (from.getRow() == homeRow && board.isEmpty(to)) {
	    			moves.add(new Move(from, to));
	    		}
	    	}
	    	if (from.getCol() != 0) {
		    	to = from.plus(dir, -1);
		    	if (!board.isEmpty(to) && !board.get(to).getColour().equals(this.colour)) {
		    		moves.add(new Move(from, to));
		    	}
	    	}
	    	if (from.getCol() != 7) {
		    	to = from.plus(dir, 1);
		    	if (!board.isEmpty(to) && !board.get(to).getColour().equals(this.colour)) {
		    		moves.add(new Move(from, to));
		    	}
	    	}
    	}
    	
        return moves;
    }
}
