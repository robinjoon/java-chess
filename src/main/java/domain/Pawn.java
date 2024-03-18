package domain;

public class Pawn extends AbstractPiece {

    private final Position initialPosition;

    public Pawn(Position position, Team team) {
        super(position, team);
        this.initialPosition = position;
    }

    @Override
    public PieceMoveResult move(Position targetPosition, ChessBoard chessBoard) {
        return null;
    }
}
