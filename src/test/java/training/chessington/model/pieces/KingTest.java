package training.chessington.model.pieces;

import static training.chessington.model.pieces.PieceAssert.*;

import java.util.List;

import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
	@Test
	public void whiteKingCanMoveInAnyDirection() {
		// Arrange
		Board board = Board.empty();
		Piece king = new King(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		board.placePiece(coords, king);

		// Act
		List<Move> moves = king.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(-1, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
	}
	
	@Test
	public void whiteKingCanOnlyMoveOneSpace() {
		// Arrange
		Board board = Board.empty();
		Piece king = new King(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		board.placePiece(coords, king);

		// Act
		List<Move> moves = king.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, -2)));
	}
	
	@Test
	public void whiteKingCannotTakeFriendlyPiece() {
		// Arrange
		Board board = Board.empty();
		Piece king = new King(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		Piece pawn = new Pawn(PlayerColour.WHITE);
		board.placePiece(coords, king);
		board.placePiece(new Coordinates(4, 5), pawn);

		// Act
		List<Move> moves = king.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 1)));
	}
	
	@Test
	public void whiteKingCanTakeEnemyPieces() {
		// Arrange
		Board board = Board.empty();
		Piece king = new King(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		Piece pawn = new Pawn(PlayerColour.BLACK);
		board.placePiece(coords, king);
		coords = new Coordinates(4, 5);
		board.placePiece(coords, pawn);

		// Act
		List<Move> moves = king.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
	}
	
	@Test
	public void whiteKingCannotMoveOffBoard() {
		// Arrange
		Board board = Board.empty();
		Piece king = new King(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 4);
		board.placePiece(coords, king);

		// Act
		List<Move> moves = king.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 0)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -1)));
	}
}
