package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.NotMovedPawn;
import chess.piece.Pieces;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

public class BoardGenerator {

    private BoardGenerator() {
    }

    public static ChessBoardAdapter create() {
        ChessBoard chessBoard = ChessBoard.empty();
        addTopAndBottom(chessBoard, Rank.EIGHT, Team.BLACK);
        addPawn(chessBoard, Rank.SEVEN, Team.BLACK);
        addBlank(chessBoard);
        addPawn(chessBoard, Rank.TWO, Team.WHITE);
        addTopAndBottom(chessBoard, Rank.ONE, Team.WHITE);
        return new ChessBoardAdapter(chessBoard);
    }

    private static void addTopAndBottom(ChessBoard chessBoard, Rank rank, Team team) {
        chessBoard.put(new Tile(Coordinate.of(File.A, rank), Pieces.findBy(Rook.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.B, rank), Pieces.findBy(Knight.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.C, rank), Pieces.findBy(Bishop.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.D, rank), Pieces.findBy(Queen.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.E, rank), Pieces.findBy(King.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.F, rank), Pieces.findBy(Bishop.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.G, rank), Pieces.findBy(Knight.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.H, rank), Pieces.findBy(Rook.class, team)));
    }

    private static void addPawn(ChessBoard chessBoard, Rank rank, Team team) {
        for (File file : File.values()) {
            chessBoard.put(new Tile(Coordinate.of(file, rank), Pieces.findBy(NotMovedPawn.class, team)));
        }
    }

    private static void addBlank(ChessBoard chessBoard) {
        for (File file : File.values()) {
            addBlanks(chessBoard, file);
        }
    }

    private static void addBlanks(ChessBoard chessBoard, File file) {
        for (int rank = Rank.THREE.getValue(); rank <= Rank.SIX.getValue(); rank++) {
            chessBoard.put(new Tile(Coordinate.of(file, Rank.findByValue(rank)), Blank.getInstance()));
        }
    }
}