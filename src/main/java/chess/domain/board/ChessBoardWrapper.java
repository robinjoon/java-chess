package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;

public class ChessBoardWrapper {
    private final ChessBoard chessBoard;

    public ChessBoardWrapper(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean move(Position from, Position to) {
        return chessBoard.move(from, to);
    }

    public List<Piece> getPiecesOnBoard() {
        return chessBoard.getPiecesOnBoard();
    }

    public double calculatePoint(Team team) {
        return chessBoard.calculatePoint(team);
    }
}
