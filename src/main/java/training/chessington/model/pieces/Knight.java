package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
	public Knight(PlayerColour colour) {
		super(PieceType.KNIGHT, colour);
	}

	@Override
	public List<Move> getAllowedMoves(Coordinates from, Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Coordinates to;

		for (int row = -2; row <= 2; row++) {
			for (int col = -2; col <= 2; col++) {
				if ((row*row) + (col*col) == 5) {
					to = new Coordinates(from.getRow() + row, from.getCol() + col);
					if (to.isOnBoard()) {
						if (board.isEmpty(to)) {
							moves.add(new Move(from, to));

						} else if (!board.get(to).getColour().equals(this.colour)) {
							moves.add(new Move(from, to));
						}

					}
				}
			}
		}

		return moves;
	}
}
