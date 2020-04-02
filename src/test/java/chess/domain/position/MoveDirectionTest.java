package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoveDirectionTest {

	@ParameterizedTest
	@CsvSource(value = {"N,d5,d6", "NE,d5,e6", "E,d5,e5", "SE,d5,e4", "S,d5,d4", "SW,d5,c4", "W,d5,c5", "NW,d5,c6"})
	void isSameDirection_SourcePositionAndTargetPosition_ReturnTrue(MoveDirection moveDirection,
		Position sourcePosition, Position targetPosition) {
		assertThat(moveDirection.isSameDirectionFrom(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"N,d5,e8", "NE,d5,a6", "E,c6,e5", "SE,e5,e4", "S,h5,d4", "SW,d5,c5", "W,f5,c8", "NW,d8,c6"})
	void isSameDirection_NotExistDirection_ReturnFalse(MoveDirection moveDirection, Position sourcePosition,
		Position targetPosition) {
		assertThat(moveDirection.isSameDirectionFrom(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"N,d5", "NE,e5", "E,e4", "SE,e3", "S,d3", "SW,c3", "W,c4", "NW,c5"})
	void move_SourcePosition_MovedPosition(MoveDirection moveDirection, Position expected) {
		Position position = Position.of("d4");

		assertThat(moveDirection.move(position)).isEqualTo(expected);
	}

	@Test
	void getQueenMovableDirections_ReturnQueenMovableDirections() {
		List<MoveDirection> expected = Arrays.asList(MoveDirection.values());

		assertThat(MoveDirection.getQueenMovableDirections()).isEqualTo(expected);
	}

	@Test
	void getRookMovableDirections_ReturnRookMovableDirections() {
		List<MoveDirection> expected = Arrays.asList(
			MoveDirection.N,
			MoveDirection.E,
			MoveDirection.S,
			MoveDirection.W);

		assertThat(MoveDirection.getRookMovableDirections()).isEqualTo(expected);
	}

	@Test
	void getBishopMovableDirections_ReturnBishopMovableDirections() {
		List<MoveDirection> expected = Arrays.asList(
			MoveDirection.NE,
			MoveDirection.SE,
			MoveDirection.SW,
			MoveDirection.NW);

		assertThat(MoveDirection.getBishopMovableDirections()).isEqualTo(expected);
	}

	@Test
	void getBlackPawnMovableDirections_ReturnBlackPawnMovableDirections() {
		List<MoveDirection> expected = Arrays.asList(MoveDirection.S);

		assertThat(MoveDirection.getBlackPawnMovableDirections()).isEqualTo(expected);
	}

	@Test
	void getBlackPawnCatchableDirections_ReturnBlackPawnCatchableDirections() {
		List<MoveDirection> expected = Arrays.asList(MoveDirection.SW, MoveDirection.SE);

		assertThat(MoveDirection.getBlackPawnCatchableDirections()).isEqualTo(expected);
	}

	@Test
	void getWhitePawnMovableDirections_ReturnWhitePawnMovableDirections() {
		List<MoveDirection> expected = Arrays.asList(MoveDirection.N);

		assertThat(MoveDirection.getWhitePawnMovableDirections()).isEqualTo(expected);
	}

	@Test
	void getWhitePawnCatchableDirections_ReturnWhitePawnCatchableDirections() {
		List<MoveDirection> expected = Arrays.asList(MoveDirection.NW, MoveDirection.NE);

		assertThat(MoveDirection.getWhitePawnCatchableDirections()).isEqualTo(expected);
	}

}