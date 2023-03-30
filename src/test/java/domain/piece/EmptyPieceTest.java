package domain.piece;

import static domain.game.File.A;
import static domain.game.File.C;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.SEVEN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EmptyPiece는")
class EmptyPieceTest {

    @DisplayName("움직일 수 있는지 확인하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallIsMovable() {
        assertThatThrownBy(() ->
                new EmptyPiece().isMovable(Pawn.createOfBlack(), Position.of(A, FIVE), Position.of(C, SEVEN)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - EmptyPiece는 움직임을 확인할 수 없습니다.");
    }

    @DisplayName("움직임의 경로를 요청하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallGetPath() {
        assertThatThrownBy(() ->
                new EmptyPiece().collectPath(Position.of(A, FIVE), Position.of(C, SEVEN)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - EmptyPiece는 움직임의 경로를 계산할 수 없습니다.");
    }

    @DisplayName("상대 진영의 말인지 확인하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCheckEmptyPieceIsOpponentSide() {
        assertThatThrownBy(() -> new EmptyPiece().isOpponentSideWith(Pawn.createOfBlack()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - Neutral side는 상대편을 확인할 수 없습니다.");
    }

    @DisplayName("점수를 요청하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenRequestScore() {
        assertThatThrownBy(() -> new EmptyPiece().getScore())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - EmptyPiece는 점수를 계산할 수 없습니다.");
    }
}