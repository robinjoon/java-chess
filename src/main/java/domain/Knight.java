package domain;

public class Knight extends AbstractPiece {
    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult move(Position targetPosition, ChessBoard chessBoard) {
        return null;
    }
}
