package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
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
		
		for (Coordinates dir : directions) {
			to = from.plus(dir.getRow(), dir.getCol());
			while (to.isOnBoard()) {
				if (!board.isEmpty(to)) {
					if (!board.get(to).getColour().equals(this.colour)) {
						moves.add(new Move(from, to));
						break;
					} else {
						break;
					}
				} else {
					moves.add(new Move(from, to));
					to = to.plus(dir.getRow(), dir.getCol());
				}
			}
		}
		
		return moves;
    }
}
