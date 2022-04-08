# java-chess
## 1단계 - 체스판 초기화

- [x] 체스판을 만든다
  - [x] 칸을 만든다
  - [x] 말을 만든다
  - [x] 초기 세팅한다
  
## 2단계 - 말 이동

- [x] command 입력 규칙을 지키지 않았을 때 에러 발생


- ChessGame - 말 이동 기능
- [x] Position 생성
  - [x] 범위를 초과하는 Column, Row 받을 시 에러 발생

  
- [x] Game
  - [x] source가 빈칸이면 에러 발생
  - [x] piece의 이동 전략에게 이동을 묻는다.
  - [x] piece를 이동시킨다.

- [x] Piece
  - [x] 각 피스별 이동 전략
  - [x] 빈칸, 폰인지 확인
  
- [x] 이동 전략
  - [x] target이 같은 색이면 이동 불가
  - [x] 폰일 경우 target 위치 확인 후 불가능하면 이동불가

## 3단계 - 점수 계산

- [x] Turn
  - [x] 색깔에 따라 자신의 차례인지 확인


- [x] GameSwitch
  - [x] 게임의 진행상태를 알 수 있다. (커맨드에 따라)


- [x] move에서 도달한 위치에 먹은 기물을 반환한다.
- [x] 왕인지 확인하고, 왕일 경우 종료한다.


- [x] 기물이 점수를 갖는다.
- [x] 체스게임에서 점수를 계산한다. (일반 피스, 폰 분리해서 계산)

## 4단계 : 웹 UI 적용, 5단계 : DB 적용

- [x] api
  - [x] get "/" 최초 접속
  - [x] get "/start" db에 저장된 Board 가져옴
  - [x] get "/end" db의 Board를 최초 상태로 초기화
  - [x] get "/status" 저장된 Board를 가져와 현재 점수를 계산
  - [x] post "/move" source와 target을 가져와 이동


- [x] dto
  - [x] ChessDto - Map<String, String> position, piece / gameOver (true, false) / turn
  - [x] MoveDto - source, target ("a7", "a5")
  - [x] StatusDto - whitePoint, BlackPoint, WinningTeam (15.5, 17.5, black)


- [x] db
  - [x] Board - position, piece ("a1", "black pawn")
  - [x] turn - 현재 턴 (black, white)

# 페어 체크리스트

### 설계를 하고 프로그래밍 시작 하기
- [x] 기능목록 작성
  - [x] 도메인 학습
  - [x] 책임을 분리하기
  - [x] 역할 구분 


- [x] 코드를 작성할 때 어떻게 작성할지 이야기 후 코드 작성 시작
  - [x] 이름
  - [x] 구현 방식


- [x] 코드 작성


- [x] 코드에 대해 회고하기
  - [x] 리팩터링 여부
  - [x] 불편한 점 최대한 개선하고 넘어가기

