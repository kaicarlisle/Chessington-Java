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
				if (!board.isEmpty(to)) {
					if (!board.get(to).getColour().equals(this.colour)) {
						moves.add(new Move(from, to));
					}
				} else {
					moves.add(new Move(from, to));
				}
			}
		}
		
		return moves;
    }
}
