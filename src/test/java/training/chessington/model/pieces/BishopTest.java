package training.chessington.model.pieces;

import java.util.List;

import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
	
	@Test
	public void whiteBishopCanMoveInADiagonalLine() {
		// Arrange
		Board board = Board.empty();
		Piece bishop = new Bishop(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(4, 4);
		board.placePiece(coords, bishop);

		// Act
		List<Move> moves = bishop.getAllowedMoves(coords, board);

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
	public void whiteBishopCantMoveOffBoard() {
		// Arrange
		Board board = Board.empty();
		Piece bishop = new Bishop(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		board.placePiece(coords, bishop);

		// Act
		List<Move> moves = bishop.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -1)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, -1)));

	}

	@Test
	public void whiteBishopCantTakeFriendlies() {
		// Arrange
		Board board = Board.empty();
		Piece bishop = new Bishop(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		Piece queen = new Queen(PlayerColour.WHITE);
		Coordinates queencoords = new Coordinates(5, 5);
		board.placePiece(coords, bishop);
		board.placePiece(queencoords, queen);

		// Act
		List<Move> moves = bishop.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(5, 5)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(6, 6)));
	}

	@Test
	public void whiteBishopCanTakeEnemies() {
		// Arrange
		Board board = Board.empty();
		Piece bishop = new Bishop(PlayerColour.WHITE);
		Coordinates coords = new Coordinates(0, 0);
		Piece queen = new Queen(PlayerColour.BLACK);
		Coordinates queencoords = new Coordinates(5, 5);
		board.placePiece(coords, bishop);
		board.placePiece(queencoords, queen);

		// Act
		List<Move> moves = bishop.getAllowedMoves(coords, board);

		// Assert
		assertThat(moves).contains(new Move(coords, coords.plus(5, 5)));
		assertThat(moves).doesNotContain(new Move(coords, coords.plus(6, 6)));
	}



}
