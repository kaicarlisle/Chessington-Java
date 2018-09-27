package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
    	ArrayList<Move> moves = new ArrayList<>();
		Coordinates to = from;
		ArrayList<Coordinates> directions = new ArrayList<Coordinates>();
		directions.add(new Coordinates(1, 1));
		directions.add(new Coordinates(1, -1));
		directions.add(new Coordinates(-1, 1));
		directions.add(new Coordinates(-1, -1));
		directions.add(new Coordinates(1, 0));
		directions.add(new Coordinates(-1, 0));
		directions.add(new Coordinates(0, 1));
		directions.add(new Coordinates(0, -1));
		
		for (Coordinates dir : directions) {
			to = from.plus(dir.getRow(), dir.getCol());
			if (to.isOnBoard()) {
				if (board.isEmpty(to)) {
					moves.add(new Move(from, to));
				} else {
					if (!board.get(to).getColour().equals(this.colour)) {
						moves.add(new Move(from, to));
					}
				}
			}
		}
		
		moves = filterMoves(moves, board);
		
		return moves;
    }
    
    private ArrayList<Move> filterMoves(ArrayList<Move> kingsMoves, Board board) {
    	Coordinates coords;
		ArrayList<Move> enemyAvailableMoves = new ArrayList<Move>();
		ArrayList<Move> illegalMoves = new ArrayList<Move>();
		
		outerloop:
    	for (Move kingsMove : kingsMoves) {
    		//simulate move
    		Board copyBoard = board.copy();
    		copyBoard.move(kingsMove.getFrom(), kingsMove.getTo());
    		//for each available move of each enemy piece:
    		//	if their move.to = this move.to, exclude this move form the king's list of available moves
    		if (copyBoard.checkCheck(this.colour)) {
    			illegalMoves.add(kingsMove);
    		}
    		
    		
    	}
		
		for (Move illegalMove : illegalMoves) {
			if (kingsMoves.contains(illegalMove)) {
				kingsMoves.remove(illegalMove);
			}
		}
		
    	return kingsMoves;
    }
}
