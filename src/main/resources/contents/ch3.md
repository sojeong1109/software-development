## ch3

### 목표
1. 2장에서 한 것에 제한적인 기능 upgrade
   1. 여러 포멧 지원 및 텍스트,HTML 형식의 리포트 결과 내기 
2. 코드베이스에서 유연성 추가
3. 유지보수성을 개선하는데 도움을 주는 개방/폐쇄 원칙 OCP(open/close principle)
4. 인터페이스를 사용 타이밍 가이드
5. 높은 결합도 피하기
6. maven, gradle 빌드 도구 사용하여 빌드

### 요약 (총정리)

### 확장된 임출금 내역 분석기 요구 사항
1. 특정 입출금 내역을 검색할 수 있는 기능 (ex. 입력한 날짜 범위, 특정 범주의 입출금 내역)
2. 검색 결과의 요약 통계를 텍스트 HTML 등 다양한 형식으로 만들기

### 순서
1. ch3 > before > BeforeBankStatementProcessor > findDtoInMonthAndGreater 가 문제임
   1. 거래 내역의 여러 속성을 조합할수록 코드가 점점 복잡
   2. 반복 로직과 비지니스 로직이 결합되어 분리 어려움
   3. 코드 반복
2. 개방/폐쇄 원칙 OCP 를 적용
   1. BankDtoFilter 인터페이스를 만들기. > BankDto 를 인수로 받아 boolean 을 반환하는 test 메서드를 생성
   2. BankDtoFilter 는 BankDto의 선택 조건을 결정함.
   3. BankDtoFilter 의 구현체를 만들어줌 (=> BankDtoIsInFebruaryAndExpensive)
   4. 구현체를 호출 (AfterBankStatementAnalyzer > dtoListBeforeLambda 부분.)
3. 람다 표현식
   1. 위 문제점은 새로운 요구사항이 있을 떄마다 별도의 클래스를 만들어야 함. 이는 의미없는 코드를 반복해서 만들어야 하는 귀찮음.
   2. 그래서 람다표현식을 이용해 이름없이 인터페이스 구현 객체를 코드 블록 형태로 전달.
   3. 호출 (AfterBankStatementAnalyzer > dtoListAfterLambda 부분.)
4. 기존 AfterBankStatementProcessor 에 있는 기존 메소드들의 위치가 애매해짐.
   1. 하여 api 역할을 하는 구현에서 결합을 제거하는 인터페이스를 만들어야 함. (=> AfterInBankDtoProcessor)
   2. 그러나, 이 접근방식에는 몇가지 문제가 있음.
      1. 헬퍼연산이 명시적인 API 정의에 포함되면서 인터페이스가 복잡해짐.
      2. 갓 클래스와 비슷한 인터페이스가 만들어짐.
      3. 인터페이스는 모든 연산을 담당하고, 심지어 두가지 형식의 결합이 발생함.
      4. 월, 카테고리 같은 BankDto 속성이 calculateAverageForCategory(), calculateTotalInJanuary() 처럼 메서드 이름의 일부로 사용 됨.
      5. 인터페이스가 도메인 객체의 특정 접근자에 종속되는 문제가 발생함.
      6. 도메인 세부 내용이 바뀌면 인터페이스도 바뀌어야 하며 결과적으로 구현코드도 바뀌어야 하는 문제 발생.
         1. ex. BankDto 의 Category 가 변경되면 인터페이스의 calculateAverageForCategory() 부분도 변경 해야하고 이걸 구현한 곳도 변경이 필요.
      7. 하여, 보통 작은 인터페이스를 권장한다. 그래야 도메인 객체의 다양한 내부 연산으로 디펜던시를 최소화 할 수 있음.
      8. 그러나 너무 지나친 작은 인터페이스도 코드 유지보수에 방해가 됨. (=> 안티응집도(anit-cohesion) 문제발생 아래 참고)
   3. AfterInBankDtoProcessor 의 다양한 구현이 되지 않으므로 인터페이스의 필요성이 사라짐. 또한 전체 응용프로그램에 도움이 되는 메서드를 제공하지도 않음.
   4. 결론적으로 코드 베이스에 불필요한 추상화를 추가해 복잡하게 할 필요가 없음.
   5. 또한 findTransactions() 처럼 메서드를 쉽게 정의할 수 있는 상황에서 findTransactionsGreaterThanEqual() 처럼 구체적으로 메서드를 정의해야 하는지도 의문.
   6. 이를 '명시적 API 제공 vs 암묵적 API 제공' 문제 라고 부른다.
5. 명시적 API 제공 vs 암묵적 API 제공
   1. 명시적 API 제공 = ex. findTransactionsGreaterThanEqual()
   2. 암묵적 API 제공 = ex. findTransactions()
   3. 장단점
      1. 명시적 API 제공
         1. 장점
            1. 어떤 동작을 수행하는지 잘 설명이 되어있음.
            2. api 의 가족성을 높이고 쉽게 이해하도록 메서드 이름을 서술적으로 만듬
         2. 단점
            1. 용도가 특정 상황에 국한 되어 있음.
            2. 상황별로 새로운 메서드를 많이 생성해야할 상황이 벌어짐.
      2. 암묵적 API 제공
         1. 장점
            1. 관련하여 필요한 모든 상황을 단순한 API 로 처리 가능
         2. 단점
            1. 처음 사용하기 어렵
            2. 문서화를 잘 해놓아야함.
   4. ?? 이 뒤에 예제로 만들어 놓은게 어떤걸 어떤게 좋게 적용한건지 이해가 안감.
6. 도메인 클래스 vs 원싯값
   1. BankDtoSummarizer 인터페이스에 정의하면서 double 원싯값을 결과로 반환.
   2. 좋은 방법이 아님 => 이유 : 원싯값으로는 다양한 결과를 반환할 수 없어 유연성 떨어짐
   3. AfterApiBankDtoProcessor.java > summarizeTransactions()의 다양한 결과를 포함하도록 메서드 시그니처를 바꾸려면 모든 AfterApiBankDtoProcessor의 구현을 변경해야함.
   4. double 을 감싸는 새 도메인 클래스 Summary를 만들면 문제 해결 됨.
   5. 이 기법을 이용하면 도메인의 다양한 개념간의 결합을 줄이고 요구사항이 바뀔 때 연쇄적으로 코드가 바뀌는 일 최소화.
7. 다양한 형식으로 내보내기

### 개방/폐쇄 원칙 OCP
* 코드를 직접 바꾸지 않고 해당 메서드나 클래스의 동작 변경 가능.
* 장점
  * 기존 코드를 바꾸지 않으므로 기존 코드가 잘못될 가능성이 줄어든다.
  * 코드가 중복되지 않으므로 기존 코드의 재사용성이 높아짐.
  * 결합도가 낮아짐으로 코드 유지보수성이 좋아짐.

#### 람다 표현식
* 람다표현식을 이용해 이름없이 인터페이스 구현 객체를 코드 블록 형태로 전달 가능.
* 메서드 레퍼런스 사용.

### 인터페이스 문제 
* 자바의 인터페이스는 모든 구현이 지켜야할 규칙을 정의한다.
* 즉, 구현 클래슨느 인터페이스에서 정의한 모든 연산의 구현 코드를 제공해야 한다. 
* 따라서 인터페이스를 바꾸면 이를 구현한 코드도 바뀐 내용을 지원하도록 갱신이 필요.
* 더 많은 연산을 추가할수록 더 자주 코드가 바뀌며, 문제 발생할 범위가 넓어짐.

#### 지나친 세밀함
* 동작별로 인터페이스 파일을 하나하나 만든다면 안티응집도 문제가 발생한다.
* 즉, 기능이 여러 인터페이스로 분산되므로 필요한 기능을 찾기가 어렵다.
* 복잡도가 높아지고, 새로운 인터페이스가 계속해서 프로젝트에 추가됨.

### 명시적 API vs 암묵적 API
장단점
1. 명시적 API 제공
   1. ex) findTransactionsGreaterThanEqual()
   2. 장점
      1. 어떤 동작을 수행하는지 잘 설명이 되어있음.
      2. api 의 가족성을 높이고 쉽게 이해하도록 메서드 이름을 서술적으로 만듬
   3. 단점
      1. 용도가 특정 상황에 국한 되어 있음.
      2. 상황별로 새로운 메서드를 많이 생성해야할 상황이 벌어짐.
2. 암묵적 API 제공
   1. ex) findTransactions()
   2. 장점
      1. 관련하여 필요한 모든 상황을 단순한 API 로 처리 가능
   3. 단점
      1. 처음 사용하기 어렵
      2. 문서화를 잘 해놓아야함.

### 예외 처리
* 예상하지 못한 문제가 발생한 경우 스택 트레이스(stack trace)와 함께 이상한 오류 메세지 발생

#### 예외를 사용해야 하는 이유
* 자바는 예외를 일급 언어기능으로 추가하고 아래 장점을 지닌다.
1. 문서화 : 메서드 시그니처 자체에 예회를 지원한다.
2. 형식 안정성 : 개발자가 예외 흐름을 처리하고 있는지를 형식 시스템이 파막한다.
3. 관심사 분리 : 비지니스 로직과 예외 회복이 각각 try/catch 블록으로 구분.
* 단점은 예외 기능으로 복잡성이 증가 함.
* 자바에서 지원하는 예외 (그림 p.66)
1. 확인된 예외
   1. 회복해야 하는 대상의 예외.
   2. 자바에서는 메서드가 던질 수 있는 확인도니 예외 목록을 선언해야 한다. 
   3. 아니면 해당 예외를 try/catch로 처리 해야함.
2. 미확인 예외
   1. 프로그램을 실행하면서 언제든 발생할 수 있는 종류 
   2. 메서드 시그니처에 명시적으로 오류를 선언하지 않으면 호출자도 이를 처리할 필요하 겂음. 

#### 예외의 패턴과 안티 패턴
* 예외의 패턴
  * 예외를 지원하는 코드를 구현하면 API에 문제가 생겼을 댸 문제를 더 명확하게 진단 함.
  * ex) throw new CSVSyntaxException()
  * 위 코드를 쓴다고 하면, 확인된 예외?? vs 미확인 예외?? 어떤것을 써야 할까??
  * 이 선택에 대한 해답은 예외가 발생했을 때 프로그램이 회복되도록 강제할 것인지? 생각해 보면 됨.
  * 일시적으로 발생하는 오류 => 동작을 다시 시도하거나, 화면에 메시지를 출력해 응용 프로그램의 반응성을 유지가능
  * 대부분 미확인 예외로 지정하고, 꼭 필요한 상황에서만 확인된 예외로 지정하려 불필요 코드 줄이기.
  * 미확인 예외
    * 보통 비지니스 로직 검증(잘못된 형식이나 연산 등)시 바생한 문제는 불필요한 try/catch 구분을 줄일 수 있도록 미확인 예외로 결정.
    * 시스템 오류
* 안티 패턴
    * 과도한 세밀함
      * Validator 클래스로 검증할 것을 추천함.
        * 장점
          * 검증 로직을 재사용해 코드 중복 X
          * 시스템의 다른 부분도 같은 방법으로 검증할 수 있다.
          * 로직을 덕립적으로 유닛 테스트 할 수 있다.
          * 이 기법은 프로그램 유지보수와 이해하기 쉬운 SRP를 따름
      * 입력에서 발생할 수 있는 모든 경계 상황을 고려하고 각각의 경계 상황에 대한 예외로 변환.
      * 이 방법은 명시적인 예외 이지만 너무 많은 설정과 생산성이 떨어져 쉽게 사용이 어려움.
    * 과도하게 덤덤함
      * 모든 예외를 IllegalArgumentException 등의 미확인 예외로 지정하는 극단적인 상황.
      * 전부 동일한 예외로 지정하면 구체적인 회복 로직을 만들 수 없음.
    * 위 두가지 문제를 해결하는 노티피케이션 패턴!! (notification)
* 노티피케이션 패턴!! (notification)
  * 너무 많은 미확인 예외를 사용하는 상황에 적합.
  * 장점 ) 도메인 플래스로 오류를 수집
  * 특정 클래스를 만들어서 한번에 여러 오류를 수집할 수 있는 검증자를 만든다.
  
#### 예외 사용 가이드라인
* 예외를 무시하지 않음
  * 근본 원인을 몰라도 무시 X
  * 예외를 처리할 수 있는 방법이 명확치 않으면 미확인 예외를 던진다.
* 일반적인 예외는 잡지 않음
  * 가능한 구체적으로 예외를 잡으면 가독성이 상승함.
  * 일반적인 Exception, RuntimeException 은 잡지 않음. 
* 예외 문서화
  * 미확인 예외를 포함한 예외를 문서화 하여 API사용자에게 문제 해결의 실마리 제공 필요
* 특정 구현에 종속된 예외를 주의
  * throws Exception
* 예외 vs 제어 흐름
  * 예외로 흐름을 제어하면 안됨
  * 불필요한 try, catch문 남용 금지
  * 예외를 던저야 하는 상황이 아니면 예외 만들지 않기

#### 예외 대안 가능
* null 사용
* null 객체 패턴
* Optional<T>
* Try<T>

### NOTE
* 함수형 인터페이스 : 한 개의 추상 메서드를 포함하는 인터페이스
  * @FunctionalInterface 어노테이션을 이요하여 인터페이스의 의도를 더 명확하게 표현.
* double vs bigdacimal :
  * double의 비트수가 한정적으로 소수점 저장시 자릿수 제약 있음. 
  * bigdacimal : 소숫점 자릿수 제한 없지만, CPU와 메모리 부담이 커짐. 