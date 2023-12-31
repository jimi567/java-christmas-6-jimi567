# 요구사항 분석

### 기능 요구사항

1. 입력
    - 12월 중 식당 예상 방문 날짜 입력받기.
        - 방문할 날짜는 1 이상 31 이하의 숫자
        - 유효하지 않을 경우 ```[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.``` 출력

    - 주문 할 메뉴와 개수를 입력받기.
        - 포맷은 다음과 같이 -> 해산물파스타-2,레드와인-1,초코케이크-1 (메뉴-갯수를 쉼표로 구분)
        - 메뉴판에 없는 메뉴를 입력하면 ``` "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.``` 출력
        - 메뉴의 개수를 1개 이상의 숫자만 입력되도록, 아니라면 ```[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.``` 출력
        - 메뉴 형식이 예시와 다른 경우 ```[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.``` 출력
        - 중복 메뉴를 입력한 경우 ```[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.``` 출력

2. 출력
    - 주문 메뉴의 출력 순서는 자유롭게
    - 총혜택 금액에 따라 이벤트 배지의 이름을 "다르게" 출력
    - 총혜택 금액? == 할인 금액의 합계 + 증정 메뉴의 가격
    - 할인 후 예상 결제 금액 출력 (== 할인 전 총 주문 - 할인 금액)
    - 증정 메뉴 출력 (증정 이벤트 해당하지 않는 경우, ```없음``` 출력)
    - 혜택 내역 출력
        - 고객에게 적용된 이벤트만 출력
        - 적용된 이벤트가 없으면 ```없음``` 출력
        - 혜택 내역이 여러개라면 출력 순서는 자유롭게
    - 이벤트 배지 출력 , 없다면 ```없음```

3. 12월 이벤트 계획 할인
    - 크리스마스 디데이 할인!
        - 이벤트 기간은 2023.12.01 ~2023.12.25
        - 1000원으로 시작하여 크리스마스가 다가올수록 날마다 금액이 100원씩 증가
        - 총주문 금액에서 해당 금액만큼 할인된다. (1일은 1000원, 2일에 1100원, 25일엔 3400원 할인)
    - 평일 할인(일요일~목요일) : 평일에는 "디저트 메뉴"를 메뉴 1개당 2,023원 할인
    - 주말 할인(금요일,토요일) : 주말에는 "메인 메뉴"를 메뉴 1개당 2,023원 할인
    - 특별 할인 : 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
    - 증정 이벤트 : 할인전 총 주문 금액이 12만원 이상이면, 샴페인 1개 증정
    - 이벤트 기간 : 크리스마 디데이 할인을 제외한 다른 이벤트는 12월달 전체에 적용

4. 혜택 금액에 따른 12월 이벤트 배지 부여
    - 총 혜택 금액에 따라 이벤트 배지를 부여한다.
    - 5천원 이상 : 별
    - 만원 이상 : 트리
    - 2만원 이상 : 산타
5. 고객에게 안내할 이벤트 주의 사항
    - 총주문 금액 만원 이상부터 이벤트가 적용된다.
    - 음료만 주문 시 , 주문 할 수 없다
    - 메뉴는 한 번에 최대 20개 까지만 주문 가능하다.

### 프로그래밍 요구사항

1. 들여쓰기 2까지만 허용
2. 3항 연산자 X
3. 함수(메서드)의 길이가 15라인이 넘지않도록(공백도 포함)
4. else 예약어 X
5. 도메인 로직에 단위 테스트를 구현,(Junit5, AssertJ) 단, UI로직은 제외
6. 잘못된 값을 입력할 경우 ```IllegalArgumentException``` 발생 시키고 ```[ERROR```로 시작하는 에러메세지 출력
7. InputView, OutputView 를 통해 입출력 클래스를 구현한다.(UI로직과 도메인 로직을 구분해야된다.)

### 개인 목표

1. 3주차때 천단위 쉼표로 구분하는것을 빼먹었다..ㅠㅠㅠ 이번 주차에는 꼭 빼먹지말자..
2. 4주차도 3주차의 연장선이다. 클래스 객체의 역할과 책임을 고려해, 분리하자.
3. 이벤트 목표가 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것이다. 즉 ,1월 이벤트도 열릴 예정이고, 더 자세한 얘기는 1주일 뒤의 개발 회의에서 얘기할 예정이니 확장성을 고려한
   OCP한 코드를 작성하자

# 구현할 기능 목록

### Constants

1. MenuBoard Enum 구현
    - [x] 메뉴,가격,카테고리를 가진다.
    - [x] 최소 주문갯수(1), 최대 총 주문 갯수를 가진다(20)
    - [x] 카테고리 상수를 가진다.
    - [x] menu 객체를 통해 메뉴판에 존재하는 메뉴의 가격을 얻는다.
    - [x] menu 객체를 통해 메뉴판에 존재하는 메뉴의 카테고리을 얻는다.
    - [x] 메뉴판에 존재하는 메뉴리스트를 얻는다.


2. 이벤트 배지 enum 구현
    - [x] 이벤트 배지의 이름, 배지를 받을 수 있는 최소 혜택 금액을 가진다.
    - [x] 혜택 금액을 통해 이벤트 배지를 반환한다.

3. Event enum 구현
    - [x] 메뉴 개수의 최소(1), 메뉴 개수의 최대(20)
    - [x] 이벤트가 적용되는 최소 총 주문 금액(10000)
    - [x] 평일 및 주말 할인 금액 (2023)
    - [x] 특별 할인 금액(1000)
    - [x] 증정 이벤트 적용 금액(120000)
    - [x] 디데이 할인 시작 할인 금액 (1000)
    - [x] 디데이할인 가중 할인 금액(100)

4. Calender enum 구현
    - [x] 방문 날짜 및 이벤트 기간(1~31)
    - [x] 크리스마스 디데이 이벤트 기간(1~25)
    - [x] 별이 있는 날(각 주 일요일 , 25)
    - [x] 주말인 날

4. Error enum
    - [x] Error 메세지를 가진다.
    - [x] Error 는 ```IllegalArgumentException ```을 발생시키는 기능을 가진다.
5. ViewMessage enum
    - [x] 출력에 관한 메세지를 가진다.

### 도메인

1. VisitDate

- [x] VisitDate interface
    - [x] 날짜에 대한 유효성 검사하는 기능
    - [x] 이벤트 기간인지를 리턴하는 기능
    - [x] 해당 날이 주말인지를 리턴하는 기능
    - [x] 이벤트 달력에 별이 있는 날인지 리턴하는 기능
    - [x] 방문날짜가 디데이 이벤트 적용이 가능한지 리턴하는 기능
    - [x] 방문날짜가 디데이(크리스마스)와 며칠 차이 나는지 리턴하는 기능
- [x] DecemberVisitDate
    - [x] 날짜에 대한 유효성 검사
        - [x] 1~31사이의 정수인지?
    - [x] 해당 날이 12월에서 주말이벤트 날인지(금,토) 리턴하는 기능
    - [x] 해당 날이 이벤트 적용 기간(디데이 이벤트 제외)이 헤당하는지 리턴하는 기능
    - [x] 해당 날이 12월 달력에 별이 있는 날인지를 리턴하는 기능
    - [x] 해당 날이 디데이 이벤트 기간에 속하는지 리턴하는 기능
    - [x] 해당 날이 디데이 이벤트 일(크리스마스) 와 며칠만큼 차이가 나는지 리턴하는 기능

2. Menu
    - [x] Menu record 클래스

3. OrderMenu

- [x] 고객이 주문한 음식들을 가지는 일급 컬렉션
- [x] 주문 음식에 대한 유효성 검사
    - [x] 메뉴판에 존재하는 메뉴인가?
    - [x] 메뉴의 개수가 1개 이상인가?
    - [x] 총 주문 갯수가 20개 이하인가?
    - [x] 음료만 주문했는가?(음료만 주문 안된다.)
- [x] 메뉴의 총 금액을 리턴하는 기능
- [x] 디저트 메뉴의 수량을 리턴하는 기능
- [x] 메인 메뉴의 수량을 리턴하는 기능
- [x] 음료 메뉴의 수량을 리턴하는 기능
- [x] 에피타이저 메뉴의 개수를 리턴하는 기능
- [x] 이벤트 혜택을 받기 위한 최소 주문 금액을 넘는지를 리턴하는 기능
- [x] toString 오버라이드(주문 메뉴 X개 포맷으로 변경)

4. Customer

- [x] 방문 날짜, 주문한 메뉴 내역을 가진다.

6. Discount

- [x] DiscountPolicy 인터페이스
    - [x] 할인 금액을 리턴하는 기능
    - [x] 이벤트 적용이 가능한지 판단하는 기능
- [x] WeekdayDiscountPolicy
    - [x] Customer 주문 메뉴 목록을 통해 평일 할인 금액을 리턴한다.
    - [x] Customer 를 통해 평일 이벤트 적용이 가능한지 아닌지를 리턴한다.
- [x] WeekendDiscountPolicy
    - [x] Customer 의 주문 메뉴 목록을 통해 주말 할인 금액을 리턴한다.
    - [x] Customer 를 통해 주말 이벤트 적용이 가능한지 아닌지를 리턴한다.
- [x] D-DayDiscountPolicy
    - [x] Customer 방문 날짜을 통해 할인 금액을 리턴한다.
    - [x] Customer 를 통해 디데이 이벤트 적용이 가능한지 아닌지를 리턴한다.
- [x] SpecialDiscountPolicy
    - [x] Customer 방문 날짜을 통해 할인 금액을 리턴한다.
    - [x] Customer 를 통해 특별 이벤트 적용이 가능한지 아닌지를 리턴한다.
- [x] GiftDiscountPolicy
    - [x] Customer 총 주문 금액을 통해 할인 금액을 리턴한다.
    - [x] Customer 를 통해 증정 이벤트 적용이 가능한지 아닌지를 리턴한다.

6. EventPlannerService

- [x] EventPlanner 인터페이스
    - [x] Customer 통해 고객이 받을 수 있는 이벤트 배지를 리턴하는 기능
    - [x] Customer 통해 혜택 내역을 리턴하는 기능
    - [x] Customer 통해 혜택 금액을 리턴하는 기능
    - [x] Customer 통해 증정 메뉴를 리턴하는 기능
    - [x] Customer 통해 해당 고객의 할인 후 결제 금액을 리턴하는 기능

- [x] WootecoDecemberEventPlannerService
    - [x] 인터페이스의 메소드를 구현한다.

### View

1. InputView

- [x] 싱글톤 클래스이다.
- [x] 주어진 ```Console.readLine()```을 통해 입력을 받는다.

2. OutputView

- [x] 싱글톤 클래스이다.
- [x] 에러문을 출력한다.
- [x] 우테코 식당 이벤트 플래너 실행 메세지를 출력한다.
- [x] 식당 방문 날짜 입력 요구문을 출력한다.
- [x] 주문할 메뉴와 개수를 입력 요구문을 출력한다.
- [x] "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"를 출력한다.
- [x] 주문 메뉴를 출력한다.
- [x] 할인 전 총 주문 금액을 리턴한다.
- [x] 증정메뉴를 출력한다.
- [x] 혜택 내역을 출력한다.
- [x] 총 혜택 금액을 출력한다.
- [x] 할인 후 예상 결제 금액을 출력한다.
- [x] 이벤트 배지를 출력한다.

### Util

1. InputValidator

- [x] InputValidator 인터페이스
    - [x] validate 메서드를 가진다.
- [x] OrderMenuInputValidator
    - [x] 메뉴 형식이 주어진것과 같은지 검사
    - [x] 중복 메뉴가 존재하는지 검사 (ex , 시저 샐러드-1,시저 샐러드-1)

2. InputParser

- InputParser
    - [x] 사용자 입력을 Int로 파싱한다.
    - [x] 사용자 입력을 HashMap<Menu, Integer> 형태로 파싱한다.

### Controller

- DecemberEventPlannerController
    - [x] 입력받은 데이터를 도메인에 전달한다.
    - [x] 로직을 거친 결과를 뷰에 전달한다.
    - [x] 잘못된 입력에 대해 다시 입력받을 수 있도록 한다.

# 테스트 코드

### 도메인 로직에 관한 테스트 코드를 작성한다.

1. VisitDate
    - [x] 32 생성하면 예외를 발생하는지 테스트
    - [x] 0으로 생성하면 예외를 발생하는지 테스트
    - [x] 해당 날짜가 주말인지 아닌지를 판단하는 메서드 테스트
    - [x] 해당 날짜가 달력에 별이 존재하는 날인지 아닌지를 판단하는 메서드 테스트
    - [x] 해당 날짜가 이벤트 진행 기간에 해당하는지 판단하는 메서드 테스트
    - [x] 해당 날짜가 디데이이벤트 적용기간인지 판단하는 메서드 테스트
    - [x] 해당 날짜와 디데이 간의 차이를 계산하는 메서드 테스트

2. OrderMenu

- [x] 주문 음식에 대한 유효성 검사 테스트
    - [x] 메뉴판에 존재하는 메뉴가 아닐 경우 테스트
    - [x] 메뉴의 개수가 1개 이상이 아닐 경우 테스트
    - [x] 총 주문 갯수가 20개 이하가 아닐 경우 테스트
    - [x] 음료만 주문했을 경우 테스트
- [x] 메뉴의 총 금액을 리턴하는 기능 테스트
- [x] 디저트 메뉴의 갯수를 리턴하는 기능 테스트
- [x] 메인 메뉴의 개수를 리턴하는 기능 테스트
- [x] 음료 메뉴의 개수를 리턴하는 기능 테스트
- [x] 에피타이저 메뉴의 개수를 리턴하는 기능 테스트

3. Discount

- [x] DiscountPolicy 테스트(각 구현체에 대해서)
    - [x] 혜택 적용이 가능한지 안한지 판단하는 기능을 테스트
    - [x] 혜택 금액을 리턴하는 기능 테스트

4. EventPlanner

- [x] WootecoDecemberEventPlannerService 테스트
    - [x] Customer 통해 고객이 받을 수 있는 이벤트 배지를 리턴하는 기능
    - [x] Customer 통해 혜택 내역을 리턴하는 기능
    - [x] Customer 통해 혜택 금액을 리턴하는 기능
    - [x] Customer 통해 증정 메뉴를 리턴하는 기능
    - [x] Customer 통해 해당 고객의 할인 후 결제 금액을 리턴하는 기능

5. Inputvalidator

- [x] OrderMenuInputValidator 테스트
    - [x] 메뉴 형식이 주어진것과 다르다
    - [x] 중복 메뉴가 입력되면 예외를 발생한다.
