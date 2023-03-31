package domain.piece;

import domain.position.Position;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class Piece {
    private final String name;
    private final Team team;

    Piece(final String name, final Team team) {
        this.name = name;
        this.team = team;
    }

    public abstract Optional<Position> move(Position source, Position destination);

    public abstract Optional<Position> eat(Position source, Position destination);

    public boolean isBlack() {
        return team.equals(Team.BLACK);
    }

    public boolean isEndGameIfDead() {
        return false;
    }

    public boolean isWhite() {
        return !isBlack();
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public abstract Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore);

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
