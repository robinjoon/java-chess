package domain;

public class Rook extends AbstractPiece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult move(Position targetPosition, ChessBoard chessBoard) {
        return null;
    }
}
