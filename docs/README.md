# 기능 목록

DTO 패키지

## BoardDto

- [x] 생성시, Map<Position, Piece>를 인자로 받는다
- [x] List<List<string>> 형태의 dto를 만든다
- [x] getter로 위의 형태를 반환한다

## ChessGame

- [x] 필드로 ChessBoard를 가진다
- [x] ChessBoard의 Map<Position, Piece>를 반환한다

## ChessBoard

- [x] Map<Position,Piece>를 필드로 가진다
- [x] 방어적 복사를 통해 필드를 반환한다

## BoardStrategy

- [x] 보드를 생성하는 인터페이스이다

## InitialBoardStrategy

- [x] BoardStrategy를 구현한다
- [x] 체스 보드의 기물들을 초기화 한다 (블랙 따로, 화이트 따로 초기화)

## Rank

- [x] ChessBoard의 1부터 8까지의 행을 나타낸다
- [x] 인덱스는 1-8까지 가진다
- [x] 인덱스의 오름차순으로 정렬한 List<Rank>를 반환한다
- [x] 입력한 값이 Rank에 존재하는 Value인지 유효성 검사

## Column

- [x] ChessBoard의 a부터 h까지의 열을 나타낸다
- [x] 인덱스는 1-8까지 가진다
- [x] 인덱스의 오름차순으로 정렬한 List<Column>를 반환한다
- [x] 입력한 값이 Column에 존재하는 Value인지 유효성 검사

## Position

- [x] Column과 Rank를 필드로 가진다

## todo: Name

- 각 기물들의 이름을 enum으로 관리한다.

## Color가

- [x] Black, White, None

## Piece (abstract)

- [x] 상수로 COLOR를 필드로 가진다. (White/Black)
- [x] 상수로 NAME을 필드로 가진다.
- [x] COLOR에 따라 이름의 출력값을 결정한다(ex.P/p)
- [x] Piece 종류에따라 움직일 수 있다(move method)

### EmptyPiece (Piece)

- [x] .을 이름으로 갖는다
- 오버라이드한 move 메서드는 아무 수행도 하지 않는다.

### Pawn (Piece)

- [x] p를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

### Rook (Piece)

- [x] r를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

### Knight (Piece)

- [x] n를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

### Bishop (Piece)

- [x] b를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

### Queen (Piece)

- [x] q를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

### King (Piece)

- [x] k를 이름으로 갖는다
- todo: move override , rank와 column를 필드로 가진다

~~## PieceGenerator

- [x] 상수로 각 기물이 몇개 생성해야하는지 가지고 있다
- [x] 첫번째 열 기물들을 생성해 반환한다 (Rook, Knight, Bishop ... )
- [x] 두번째 열 기물들을 생성해 반환한다 (Pawn * 8)~~


- start, end 입력
- [x] 시작 위치의 piece 존재 유무 확인 map.contains(Position)
    - [x] 있으면 가져와서, piece 변수 저장
- [x] gap 구하기
- [x] gap의 abs 를 piece에 넣어서 한 번에 이동 가능한지 확인하기
    - [x] 폰(movableDistance 1), 킹(movableDistance 1), 나이트(movableDistance 1.2 / 2.1)
    - [x] 정나머지는 true 반환
- [x] start -end = gap 으로 direction 정하기
- [x] 기물이 해당 direction으로 이동할 수 있는지 구하기
- [x] 현재 위치가 end가 될때까지, start에 direction의 단위 위치를 더하면서, 해당 포지션에 있는 기물이 존재하는지 확인한다
    - [x] 이동하는 경로에 기물이 존재하면, 바로 break
    - [x] 끝에 도달했는데 기물이 있는 경우, 색깔이 같은 편이면 break
    - [x] 끝에 도달했는데 기물이 있는경우, 색깔이 다른편인 경우만 ok
- [x] 모든 조건을 충족하면 이동!



수정할 것
1. [x] pawn이 한 번 이동하면, 두번째 부터는 1칸씩만 이동가능 하게 수정
2. [x] direction이 piece를 알 필요 없이 방향을 구하도록 수정
3. [x] 화이트 먼저, 블랙 순서로 turn 기능 추가하기
4. [x] 보드 출력 dto말고 그냥 보드 그대로 출력하기
5. [x] 보드 출력할 때 피스가 이름을 갖는게 아니고, 피스의 타입정보를 넘겨주면, 뷰가 가지고있는 피스타입의 정보에 따라서 출력하기
6. [x] 명령어 입력받기 기능 수정
   - [x] input에서 dto가 아닌,도메인 넘겨주기
   - [x] start stop move를 List<String> 상태로 변환
7. [x] empty기물을 움직이려고 하면, 예외 발생시키는 것 넣기(지금은 그냥 예외 발생 하는데 메시지 안뜨고 말없이 그 컬러 팀의 재입력 기다림)

# 리팩토링 목록

1. 동시성을 고려하지 않아도 되는 상황이기 때문에, forEach사용이 괜찮다. [initialBoardStrategy]
   /**
    * 질문 : 처음 foreach를 사용하여 필드(Map)에 Position과 기물들을 put하였습니다.
    * stream 사용 시, foreach는 동시성 보장이 안되기 때문에 연산에 사용하지 말라는 글을 읽고
    * for문 대신 stream을 사용하되 아래와 같이 수정하였습니다.
    * foreach를 사용하지 않으니 가독성이 떨어지는 점도 있는데, 차라리 for문을 사용하는 것이 나은지
    * 현재와 같이 stream을 사용해도 좋은지
    * 리뷰어님의 의견 궁금합니다.
      */
    - forEach를 사용하면 안되는 이유?
    - 동시성을 보장해야하는 코드란 무엇인가?
        - filter을 사용하는 등 lazy가 일어나는 경우
        - 예시 만들어서 답글달기!!!!

2. 함수형 프로그래밍 개념의 적용과 람다식, 함수형인터페이스 사용
    - 함수형 인터페이스 사용(람다식 사용)시, 함수형 프로그래밍 개념을 적용하지 않아도 된다?
    - 함수형 인터페이스가 함수형 프로그래밍을 지원한다는 말은 무엇인가?

3. 의존성 주입의 이점 알아보기!
    - 메서드에 인자로 넘겨주는 것과 의존성 주입의 차이점 알아오기!
    - 근데 왜 객체를 컨트롤러 내부에서 직접 생성하면 안되는 것임? 왜 인자로 application에서부터 넣어줘야하나요?

4. 서비스(체스 게임 도메인) - 뷰의 입력값과 도메인을 변환해주는 일은 서비스가 가진다!
    - 따라서, 방법 2로 수정한다.
      /**
    * 뷰로 부터 받은 문자열(예:a1, b2 등)을 Position객체로 바꾸는 역할을 컨트롤러에게 맡겼는데, (방법 1)
    * 뷰에서 넘어온 자료를 도메인 객체로 가공하는 일은 컨트롤러의 역할이라고 판단했기 때문입니다.
    *
    * 하지만, 한편으로는 도메인 객체에 해당하는 Position과, Column, Rank를 컨트롤러가 알게 하는 것이 맞나 하는 생각이 들었습니다. (방법 2)
    * 컨트롤러는 일종의 서비스(다른 도메인 객체를 모두 총괄)에 해당하는 ChessGame객체만 알고,
    * 나머지 객체들은 ChessGame을 통해서만 다루도록 하여,
    * ChessGame에서 문자열을 가지고, 비즈니스 로직에 필요한 Position 객체를 생성하는 것이 더 적합한 구조인가 싶습니다.
    * (즉, createPositionByCommand(String sourceCommand)를 ChessGame에 넘겨주는 방법이 맞을까요?
    *
    * 방법 1과 방법 2 중에 어떤 것이 더 mvc 패턴에 적합한 구조인지 리뷰어님의 의견 궁금합니다!
      */

5. 