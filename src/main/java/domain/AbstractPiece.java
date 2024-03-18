package domain;

public abstract class AbstractPiece implements Piece {
    private final Team team;
    private Position position;

    public AbstractPiece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract PieceMoveResult move(Position targetPosition, ChessBoard chessBoard);
}
