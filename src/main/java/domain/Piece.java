package domain;

public interface Piece {
    PieceMoveResult move(Position targetPosition, ChessBoard chessBoard);
}
