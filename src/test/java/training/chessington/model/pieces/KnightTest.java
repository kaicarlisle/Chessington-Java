package training.chessington.model.pieces;

import java.util.List;

import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
	
    @Test
    public void whiteKnightCanMoveLShape() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
    }

    @Test
    public void blackKnightCanMoveLShape() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
    }
    
    @Test
    public void whiteKnightCannotMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0,2);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
    }

    @Test
    public void blackKnightCannotMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7,2);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
    }
    
    @Test
    public void whiteKnightCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(0,2);
        board.placePiece(coords, knight);
        board.placePiece(new Coordinates(2, 3), rook);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
    }

    @Test
    public void blackKnightCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7,2);
        board.placePiece(coords, knight);
        Piece rook = new Rook(PlayerColour.WHITE);
        board.placePiece(new Coordinates(6,0), rook);
        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
    }
    
    @Test
    public void whiteKnightCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0,2);
        board.placePiece(coords, knight);
        board.placePiece(new Coordinates(2, 3), rook);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
    }

    @Test
    public void blackKnightCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7,2);
        board.placePiece(coords, knight);
        Piece rook = new Rook(PlayerColour.BLACK);
        board.placePiece(new Coordinates(6,0), rook);
        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -2)));
    }
}
