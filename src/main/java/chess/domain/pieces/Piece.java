package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Piece {
    private Position position;
    private final Team team;
    private final String initial;
    private final Double score;

    public Piece(final Position position, final String initial, final Team team, final Double score) {
        this.position = position;
        this.score = score;
        this.initial = checkTeam(team, initial);
        this.team = team;
    }

    public final Double score() {
        return score;
    }

    public final boolean sameCol(final int column) {
        return position.sameColumn(column);
    }

    public final boolean samePosition(final Position startPoint) {
        return this.position.equals(startPoint);
    }

    public final void move(final Board board, final Position endPoint) {
        List<Position> movablePositions = getMovablePositions(board);
        validateEndPoint(endPoint, movablePositions);
        erasePiece(board, endPoint);
        this.position = endPoint;
    }

    public final Team getTeam() {
        return team;
    }

    private String checkTeam(final Team team, final String initial) {
        if (team.equals(Team.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        return initial.toUpperCase(Locale.ROOT);
    }

    public final String getInitial() {
        return initial;
    }

    public final Position getPosition() {
        return position;
    }

    private void erasePiece(final Board board, final Position endPoint) {
        Pieces anotherTeamPieces = board.piecesByTeam(Team.getAnotherTeam(team));
        anotherTeamPieces.removePieceByPosition(endPoint);
    }

    private void validateEndPoint(final Position endPoint, final List<Position> movablePositions) {
        if (!movablePositions.contains(endPoint)) {
            throw new IllegalArgumentException("갈수 없는 위치입니다.");
        }
    }

    protected List<Position> getMovablePositionsByDirection(final Board board, final int[] rowDirection, final int[] colDirection) {
        List<Position> movablePositions = new ArrayList<>();
        for (int direction = 0; direction < colDirection.length; ++direction) {
            addMovablePositions(movablePositions, board, rowDirection[direction], colDirection[direction]);
        }
        return movablePositions;
    }

    protected abstract void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDirection, final int colDirection);

    protected abstract boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    protected abstract List<Position> getMovablePositions(final Board board);
}