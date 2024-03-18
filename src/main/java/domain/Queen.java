package domain;

public class Queen extends AbstractPiece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult move(Position targetPosition, ChessBoard chessBoard) {
        return null;
    }
}
