package domain;

public class King extends AbstractPiece {
    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult move(Position targetPosition, ChessBoard chessBoard) {
        return null;
    }
}
