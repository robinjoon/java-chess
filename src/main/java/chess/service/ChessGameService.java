package chess.service;

import static chess.service.status.GameStatus.RUNNING;

import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardStatus;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.service.status.GameStatus;
import chess.service.status.GameStatusValidator;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final ChessBoardService chessBoardService;
    private ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessGameService(ChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    public List<Integer> availableBoards() {
        return chessBoardService.findAllNotOverBoardIds();
    }

    public void start(int boardId) {
        GameStatusValidator.validateStart(gameStatus);
        gameStatus = RUNNING;
        chessBoard = chessBoardService.loadChessBoard(boardId);
        chessBoardService.saveChessBoard(chessBoard);
    }

    public void move(CommandRequest commandRequest) {
        GameStatusValidator.validateMove(gameStatus);
        chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
        ChessBoardStatus boardStatus = chessBoard.status();
        if (boardStatus.isOver()) {
            gameStatus = GameStatus.OVER;
        }
        chessBoardService.saveChessBoard(chessBoard);
    }

    public void end(CommandRequest commandRequest) {
        GameStatusValidator.validateEnd(gameStatus);
        gameStatus = GameStatus.READY;
    }

    public boolean isOver() {
        return gameStatus == GameStatus.OVER;
    }

    public GameResultResponse computeResult(CommandRequest commandRequest) {
        GameStatusValidator.validateStatus(gameStatus);
        return new GameResultResponse(
                chessBoard.calculateScoreByCamp(Camp.WHITE),
                chessBoard.calculateScoreByCamp(Camp.BLACK),
                chessBoard.status().getCurrentTurn().name()
        );
    }

    public Map<Position, Piece> readBoard() {
        return chessBoard.piecesByPosition();
    }

}
