package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;
import static domain.Position.A2;
import static domain.Position.A3;
import static domain.Position.A4;
import static domain.Position.A5;
import static domain.Team.WHITE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("한칸 전진 할 수 있는지 검증")
    void moveOneSuccess() {
        Pawn pawn = new Pawn(A2, WHITE);
        ChessBoard chessBoard = new ChessBoard() {
        };
        PieceMoveResult actual = pawn.move(A3, chessBoard);
        Assertions.assertThat(actual)
                .isEqualTo(SUCCESS);
    }

    @Test
    @DisplayName("처음에 두칸 전진 할 수 있는지 검증")
    void moveTwoSuccess() {
        Pawn pawn = new Pawn(A2, WHITE);
        ChessBoard chessBoard = new ChessBoard() {
        };
        PieceMoveResult actual = pawn.move(A4, chessBoard);
        Assertions.assertThat(actual)
                .isEqualTo(SUCCESS);
    }

    @Test
    @DisplayName("처음이 아닐 땐 두칸 전진 할 수 없는지 검증")
    void moveTwoFail() {
        Pawn pawn = new Pawn(A3, WHITE);
        ChessBoard chessBoard = new ChessBoard() {
        };
        PieceMoveResult actual = pawn.move(A5, chessBoard);
        Assertions.assertThat(actual)
                .isEqualTo(FAILURE);
    }
}
