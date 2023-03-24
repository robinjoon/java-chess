package chess.domain.piece;

import chess.domain.piece.info.Team;
import chess.domain.position.Position;

public class Blank extends Piece {

    private static final Piece BLANK = new Blank();

    private Blank() {
        super(Team.EMPTY);
    }

    public static Piece getInstance() {
        return BLANK;
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        throw new UnsupportedOperationException("칸에 기물이 없습니다.");
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        throw new UnsupportedOperationException("칸에 기물이 없습니다.");
    }
}