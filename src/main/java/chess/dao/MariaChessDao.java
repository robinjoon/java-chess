package chess.dao;

import chess.entity.ChessGame;
import chess.handler.exception.SqlExecuteException;
import chess.piece.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.dao.ChessConnection.getConnection;

public class MariaChessDao implements ChessDao {
    private final ConnectionProperties connectionProperties;

    public MariaChessDao(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Override
    public ChessGame save(ChessGame entity) {
        String query =
                "INSERT INTO CHESSGAME " +
                        "(active, winner, createdTime) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, entity.isActive());
            preparedStatement.setString(2, entity.getWinner().name());
            LocalDateTime createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(createdTime));

            preparedStatement.executeQuery();

            return getChessGame(entity, preparedStatement, createdTime);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private ChessGame getChessGame(ChessGame entity, PreparedStatement preparedStatement, LocalDateTime createdTime) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (!generatedKeys.next()) {
                throw new SqlExecuteException("엔티티 저장 실패");
            }
            long id = generatedKeys.getLong("id");
            return new ChessGame(id, createdTime, entity);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    @Override
    public Optional<ChessGame> findById(Long id) {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE id = ?";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            return getChessGame(preparedStatement);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private Optional<ChessGame> getChessGame(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.next()) {
                return Optional.empty();
            }
            ChessGame chessGame = collectEntity(resultSet);

            return Optional.of(chessGame);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private ChessGame collectEntity(ResultSet resultSet) throws SQLException {
        Long rowId = resultSet.getLong("id");
        boolean active = resultSet.getBoolean("active");
        Team winningTeam = Team.valueOf(resultSet.getString("winner"));
        LocalDateTime createdTime = resultSet.getTimestamp("createdTime").toLocalDateTime();

        return new ChessGame(rowId, winningTeam, active, createdTime);
    }

    @Override
    public void update(ChessGame entity) {
        String query =
                "UPDATE CHESSGAME " +
                        "SET active = ? , winner = ?" +
                        "WHERE id = ?";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, entity.isActive());
            preparedStatement.setString(2, entity.getWinner().name());
            preparedStatement.setLong(3, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    @Override
    public List<ChessGame> findAll() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return getChessGames(preparedStatement);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }


    }

    private List<ChessGame> getChessGames(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ChessGame> chessGames = new ArrayList<>();
            while (resultSet.next()) {
                chessGames.add(collectEntity(resultSet));
            }
            return chessGames;
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }


    }

    @Override
    public List<ChessGame> findAllByActive() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE active = ?";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);

            return getChessGamesActive(preparedStatement);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private List<ChessGame> getChessGamesActive(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ChessGame> chessGames = new ArrayList<>();
            while (resultSet.next()) {
                ChessGame chessGame = collectEntity(resultSet);
                chessGames.add(chessGame);
            }
            return chessGames;
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM CHESSGAME";
        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }
}