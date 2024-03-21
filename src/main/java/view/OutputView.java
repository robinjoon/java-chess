package view;

import domain.Piece;
import domain.PieceType;
import domain.Team;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printGuide() {
        System.out.printf("> 체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public static void printChessBoard(List<Piece> pieces) {
        List<List<Character>> boardStatus = new ArrayList<>(8);
        for (int i = 0; i < 9; i++) {
            boardStatus.add(new ArrayList<>(List.of(' ', '.', '.', '.', '.', '.', '.', '.', '.')));
        }

        for (Piece piece : pieces) {
            int column = piece.getColumn();
            int row = piece.getRow();
            boardStatus.get(9 - row).set(column, mappingPiece(piece).value);
        }

        for (int i = 1; i < boardStatus.size(); i++) {
            for (Character c : boardStatus.get(i)) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static PieceAsset mappingPiece(Piece piece) {
        Team pieceTeam = piece.getTeam();
        PieceType pieceType = piece.getPieceType();
        return PieceAsset.valueOf(pieceTeam.name() + "_" + pieceType.name());
    }

    public static void printReInputGuide() {
        System.out.println("다시 입력해 주세요");
    }

    enum PieceAsset {
        BLACK_KING('K'),
        BLACK_QUEEN('Q'),
        BLACK_ROOK('R'),
        BLACK_BISHOP('B'),
        BLACK_KNIGHT('N'),
        BLACK_PAWN('P'),
        WHITE_KING('k'),
        WHITE_QUEEN('q'),
        WHITE_ROOK('r'),
        WHITE_BISHOP('b'),
        WHITE_KNIGHT('n'),
        WHITE_PAWN('p');

        private final char value;

        PieceAsset(char value) {
            this.value = value;
        }
    }
}