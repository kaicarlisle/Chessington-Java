package training.chessington.model.pieces;

import java.util.List;

import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
	
	@Test
	public void whiteQueenCanMoveInALine() {
		// Arrange
		Board board = Board.empty();
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		board.placePiece(coords, queen);

		// Act
		List<Move> moves = queen.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 2)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(3, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, -2)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, -3)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, -4)));
		assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(-2, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(-3, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(-4, 0)));

	}
	
	@Test
	public void whiteQueenCanMoveInADiagonalLine() {
		// Arrange
		Board board = Board.empty();
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		board.placePiece(coords, queen);

		// Act
		List<Move> moves = queen.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(1, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(2, 2)));
		assertThat(moves).contains(new Move(coords, coords.plus(3, 3)));
		assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(-2, -2)));
		assertThat(moves).contains(new Move(coords, coords.plus(-3, -3)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(2, -2)));
		assertThat(moves).contains(new Move(coords, coords.plus(3, -3)));
		assertThat(moves).contains(new Move(coords, coords.plus(-1, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(-2, 2)));
		assertThat(moves).contains(new Move(coords, coords.plus(-3, 3)));

	}

	@Test
	public void whiteQueenCantMoveOffBoard() {
		// Arrange
		Board board = Board.empty();
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		board.placePiece(coords, queen);

		// Act
		List<Move> moves = queen.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, -1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 2)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
		assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(3, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(4, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(5, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(6, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(7, 0)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 5)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 4)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 6)));
		assertThat(moves).contains(new Move(coords, coords.plus(0, 7)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 8)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(8, 0)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 0)));

	}

	@Test
	public void whiteQueenCantTakeFriendlies() {
		// Arrange
		Board board = Board.empty();
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		Piece rook = new Rook(PlayerColour.WHITE);
		Coordinates queencoords = new Coordinates(5, 5);
		board.placePiece(coords, queen);
		board.placePiece(queencoords, rook);

		// Act
		List<Move> moves = queen.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(5, 5)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(6, 6)));
	}

	@Test
	public void whiteQueenCanTakeEnemies() {
		// Arrange
		Board board = Board.empty();
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		Piece rook = new Rook(PlayerColour.BLACK);
		Coordinates rookcoords = new Coordinates(5, 5);
		board.placePiece(coords, queen);
		board.placePiece(rookcoords, rook);

		// Act
		List<Move> moves = queen.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(5, 5)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(6, 6)));
	}

	

	
}
