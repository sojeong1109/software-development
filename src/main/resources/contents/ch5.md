## ch5

### 목표
1. 비지니스 규칙 엔진 구현.
2. 비지니스 규칙 엔진의 핵심 기능을 포함하는 "최소 기능 제품(MVP)" 개발
3. TDD (테스트 주도 개발) 기법
4. 유닛테스트를 구현하는 유용한 mocking(모킹)기법
5. 지역 변수 형식 추론, switch문 등 최신 기능
6. builder pattern 빌더 패턴 과 ISP(인터페이스 분리 원칙) 으로 사용자 친화적인 API 개발방법 학습

### 비지니스 규칙 엔진 요구사항
* 팩트 : 규칙이 확인 할 수 있는 정보
* 액션 : 수행하려는 동작
* 조건 : 액션을 언제 발생시키는지 지정
* 규칙 : 실행하려는 비지니스 규칙을 지정, 보통 팩트, 액션, 조건을 한그룹으로 묶어 규칙으로 만듦.

### 순서
1. 어떤 기능을 제공할지 메서드 단위로 class 만들기고 빈 메소드에는 반환값으로 exception 기재.
2. 메서드 기준으로 테스트 코드 작성(TDD)
3. @테스트  붙은 메서드를 만들고 1번에서 만든 메소드 메칭 하여 호출 확인.
4. 3번에서 의도한 대로 메서드 에러가 나면, 테스트 코드 작성.
5. 1번 비지니스 코드 추가
6. 테스트 코드 실행을 위해서 mocking 데이터 추가

### TDD
* 테스트 코드 생성 후 이에 맞춰 비지니스 코드 생성

#### TDD를 사용하는 이유
* 테스트를 따로 구현하므로 테스트에 대응하는 요구사항을 한개씩 구현할 때마다 필요한 요구사항에 집중하여 개선 가능
* 코드를 올바르게 조직 가능 (ex. 테스트 코드를 구현하며 코드에 어떤 공개 인터페이스를 만들어야 하는지 검토가능.)
* TDD 주기에 따라 요구사항 구현을 반복하면서 종합적인 테스트 스위트를 환성할 수 있다. 
* 테스트를 통과하기 위한 코드를 구현하기 때문에 필요하지 않은 테스트를 구현하는 일을 줄일 수 있다.

#### TDD 주기
* 테스트 추가 -> 테스트 실행(실패) -> 코드 구현 -> 테스트 실행(통과) -> 다시 테스트 추가 : 4개의 단계가 계속 반복.
* 여기에 "리팩토링" 을 추가 하여 코드를 바꿨을 때 뭔가 잘못되어도 의지할 수 있는 "테스트 스위트"를 갖는다.

1. 실패하는 테스트 구현
2. 모든 테스트 실행
3. 기능이 동작하도록 코드 구현
4. 모든 테스트 실행

#### 케이스
1. 데이터가 없는 경우 
2. crud 각 실행

### mocking 모킹
* 모킹 라이브러리인 mockito 모키토 를 이용.
1. mock 생성
2. 메서드 호출 확인.
** QQ p.122 5-5 아래 : when, then 부분이 어디인지? 
** QQ 예제 5-6 run() 메서드 구현 에서 Action::perform 을 하니까 테스트가 통과된..??  Action에 excute밖에 메소드 없는데 어떻게 perform 이 수행됨..??
** QQ p.124에 적힌 문제가 왜 문제라는지 모르겟음.
** QQ p.125 Fcsts 클래스를 만듦으로써 사용자에게 제공할 기능을 조절 할 수 있다는게 ?? 무슨말..인지

### 조건 추가하기
#### 지역 변수 형식 추론
* type inference란 컴파일러가 정적 형식을 작동으로 추론해 결정한느 기능. 사용자는 더 이상 명시적으로 형식을 지정할 필요가 없다.
* Map<String, String> facts = new HashMap<>(); 이런식으로 
* 이 기능은 java7에서 추가된 다이어몬드 연산자 라는 기능.
* java10 부터는 형식 추론이 지역 변수까지 확장적용 됨.(var 키워드로 형식 추론.)
* 형식 추론은 자바 코드 구현 시간을 단축 할 수 있다.
* 하지만, 코드를 읽을때 가독성에 문제가 된다면 사용하지 ㅇ낳는것이 좋다.

### 플루언트 API 설계
* 도메인에 맞춰 단순하게 규칙(조건과 액션)을 추가하는 기능을 제공
* 빌터 패턴과 플루언트 API가 이 기능을 제공하는데 어떤 도움을 주는지 
#### 플루언트 API 란?
* 특정 문제를 더 직관적으로 해결 할 수 있도록 특정 도메인에 맞춰진 API를 가르킴.
* 메서드 체이닝을 이용하면 복잡한 연산도 지정 하 ㄹ수 있음.
  * ex.
  * 자바 스트림 API
  * 스프링 통함
  * jOOQ
#### 도메인 모델링
* 조건 : 어떤 팩트에 적용할 조건(참이나 거짓으로 평가됨)
* 액션 : 실행할 연산이나 코드 집합
* 규칙 : 조건과 액션을 합친 것. 조건이 참일때만 액션을 수행.
* 137페이지 5-28 : "(Facts facts) -> "이런식으로 적는 것도 가능한가?? (직접 코딩이 필요할듯.)
#### 빌더 패턴
* 객체와 필요 조건, 액션을 한드는 과정을 개선해보자
* 단순하게 객체를 만드는 방법을 제공 함.
* 생성자의 파라미터를 분해해서 각각의 파라미터를 받는 여러 메서드로 분리한다.
* ㅔ.139 : when() 메서드를 정적으로 만들어 사용자가 직접 호출 하려면 예정 생성자를 호출하도록????

#### 순서
1. 라이브러리 임포트 (import static org.mockito.Mockito.*;)
2. 이제 mock(), verify()  메서드 사용.
   1. mock : 필요한 목 객체를 만들고 특정 동작이 실행되는지 확인.
   2. verify : 메서드로 특정 메서드가 호출 되었는지 홛인하는 어서션 생성


### 기타
* 상용 엔진은 결정 모델과 표기법 (DMN)같은 표준을 준수함.
* 복잡한 규칙을 쉽게 유지보수 할 수 있도록 중앙 저장소를 제공
* GUI 를 이용한 편집 기능이 있으며, 시각화 도구를 제공함.
* 메서드 구현 전 인 경우  UnsupportedOperationException() 으로 예외 던지기
* 한개의 추상 메서드를 선언하는 : 함수형 인터페이스 = @FunctionalInterface