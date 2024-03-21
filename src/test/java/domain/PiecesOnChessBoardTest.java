package domain;

import static domain.Position.D2;
import static domain.Team.BLACK;
import static domain.Team.WHITE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PiecesOnChessBoardTest {

    public static Stream<Arguments> whichTeamParameters() {
        return Stream.of(
                Arguments.of(WHITE),
                Arguments.of(BLACK)
        );
    }

    @ParameterizedTest
    @MethodSource("whichTeamParameters")
    @DisplayName("체스 판 위의 특정 위치의 말의 팀을 잘 판단하는지 검증")
    void whichTeam(Team team) {
        List<Piece> pieces = List.of(new Pawn(D2, team));
        PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(pieces);
        Team actual = piecesOnChessBoard.whichTeam(D2).orElseThrow();
        Assertions.assertThat(actual)
                .isEqualTo(team);
    }

    @Test
    @DisplayName("해당 위치에 말이 없는 경우 팀을 판단할 수 없는지 검증")
    void whichTeamWhenEmpty() {
        PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
        Optional<Team> actual = piecesOnChessBoard.whichTeam(D2);
        Assertions.assertThat(actual)
                .isEmpty();
    }
}
