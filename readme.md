
# BookPub

## Getting Start

```java
./mvnw spring-boot:run
```

## 서비스 소개

클라우드 서비스를 적용한 온라인 책 쇼핑몰 서비스

## 아키텍처 구조

![Book_Pub_ Architecture](https://user-images.githubusercontent.com/87689191/220088802-720d31f9-71b9-494a-b413-132cd931335c.png)

## CD/CD

![CI:CD](https://user-images.githubusercontent.com/87689191/220251384-aef3b943-bd11-4582-8e63-c11f88d5d81c.png)


## 프로젝트 관리(공통)

### WBS

- 구글 sheet 에서 version 별 관리.
  <img width="1280" alt="image" src="https://user-images.githubusercontent.com/88470887/220016525-eecd7f3a-913d-47ad-ba2a-0fd2baaa829b.png">

### 칸반보드

- 두레이에서 제공하는 칸반보드사용.
  <img width="1314" alt="image" src="https://user-images.githubusercontent.com/88470887/220016688-0d7c198a-ac63-43eb-bc15-2010fcdb79cf.png">

### 테스트 커버리지

- 목표 : 라인 커버리지 80% 이상 (2023년 2월 20일 (월) 기준 81.5%)
  <img width="949" alt="스크린샷 2023-02-20 오후 2 25 42" src="https://user-images.githubusercontent.com/65845022/220016397-ba222183-d23a-4556-bcc9-adda2862801e.png">

---

## 주요기능

### 회원

- 담당자 : 유호철, 임태원
- 회원가입, 수정, 삭제, 조회
- 회원가입 시 유효성 검사 및 중복검사, hook을 이용한 sms 인증
  - 비밀번호 SHA-512 암호화를 통해 데이터베이스 저장
  - 회원가입시 회원가입 축하 쿠폰 부여
    - spring event를 통해 회원가입과의 트랜잭션 분리.
- front - gateway - shop 통신 간 aop를 이용하여 접근권한 확인

### 저자
- 담당자 : 박경서, 김서현, 정유진
- 저자 등록, 수정, 조회
- 한명의 저자에 대한 대표작을 추가로 작성해서 저자 구분

### 상품

- 담당자 : 박경서, 여운석
- 상품 등록, 수정, 삭제, 조회, 검색 기능, 최근 본 상품
- tui editor 사용
  - 입력한 텍트스대로 저장 및 불러오기 가능
- 상품검색 시 Elastic Search 사용
  - elastic Search 데이터 save 시 기존 DB와 Transcational 분리
  - ealstic Search 분석기를 활용해
    - 오늘 밤(dhsmf qka), 오늛 등 동의어 검색 완료
- 동적쿼리를 통해 여러조건에 대한 조회 처리 완료
- ELK 활용
  - Elasticsearch 와 Logstash ,Kibana 를 활용하여 데이터 처리
- spring cache 사용
  - 메인화면에 띄워지는 상품 10분에 1번 갱신, 쿼리 호출 수 감소
- redis 사용
  - 최근 본 상품을 redis를 통해 RDB 보다 빠르게 조회

### 카테고리

- 담당자 : 김서현
- 카테고리 생성, 수정, 조회
- 부모 카테고리, 자식 카테고리로 이뤄진 2depth 형태
- 우선순위를 부여하여 관리자가 회원에게 먼저 보여질 카테고리 설정 가능

### 주소

- 담당자 : 유호철, 임태원
- 주소 저장, 수정, 삭제
- kakao 주소 api를 사용하여 우편번호, 도로명 주소 조회
- 최대 10개까지의 주소 저장, 기준주소지 설정을 통해 주문시 편리함 부여

### 상품태그

- 담당자 : 박경서
- 태그 생성, 수정, 삭제
- hex color code를 입력받아 태그별 배경색 설정기능

### 포인트

- 담당자 : 임태원
- 포인트내역 생성, 조회, 포인트 선물, 포인트 적립
- 리뷰, 상품구매, 등급별 포인트 부여
  - 관리자가 설정 한 정책에 따라 부여
  - spring batch를 사용해 매달 등급에 맞는 포인트 부여

### 장바구니

- 담당자 : 박경서
- redis, cookie 사용
  - 로그인 전,후 같은 상품 조회

### 위시리스트

- 담당자 : 박경서
- 위시리스트 생성, 삭제, 조회
- 상품을 위시리스트에 담아 조회 가능
- 품절일 경우 재입고 알림기능 가능
  - hook을 이용하여 재입고 시 알림 전송

### 리뷰(상품평)

- 담당자 : 정유진
- 리뷰 생성, 수정, 삭제, 조회
- 별점 부여 가능 (1 ~ 5)
- 구매인만 리뷰 작성 가능
- 리뷰는 1회만 작성 가능
- 삭제 후 다시 작성한 리뷰는 포인트 적립 x (포인트적립은 1회만 가능)

### 매출

- 담당자 : 유호철
- 매출 조회
- 매출 조회시 시작날짜, 종료날짜 를 통해 연별, 월별 매출내용 조회

### 문의

- 담당자 : 정유진
- 상품문의, 1:1문의 생성, 수정, 삭제, 조회
- tui editor 사용
  - 실시간으로 Object Storage와 통신하여 view에 이미지 즉시 확인
- 상품문의
  - 상품구매 후 작성 가능
  - 공개, 비공개 설정에 따라 조회 가능여부에 차이를 둠
- 1:1 문의
  - 회원만 작성 가능

### 고객서비스

- 담당자 : 여운석
- QNA, 공지사항 생성, 수정, 삭제 조회
- spring cache 사용
  - 변경이 적은 데이터는 cache를 통해 쿼리 호출수 감소

### 쿠폰

- 담당자 : 김서현, 정유진
- 쿠폰 생성, 수정, 삭제, 조회
- spring batch 사용
  - 생일에 맞춰 생일축하 쿠폰 발급
- message Queue(RabbitMq) 사용
  - 선착순 쿠폰 지급등의 상황에서의 대규모 트래픽 방지
- 등급별 쿠폰
  - 회원의 권한에 따라 부여 가능, 불가능 여부 판단
- Redis 사용
  - 이달의 쿠폰 수량 체크

### 파일

- 담당자 : 정유진
- Object Storage, Local Storage 저장, 호출

### 주문

- 담당자 : 여운석, 임태원
- 주문 생성, 조회
- 주문자의 기본 주문정보(실수령자 이름, 전화번호, 주소 등) 수정
- 주문의 취소, 환불, 반품, 교환 등의 서비스 제공

### 결제

- 담당자 : 임태원
- 결제 생성, 수정
- toss api 사용
- spring event 사용
  - 결제 이외의 다른 상태변경과의 트랜잭션 분리

### 인프라

- 담당자 : 유호철
- 깃헙 액션 - front, shop, batch, mq, delivery 서버 CI/CD 관리
- 젠킨스 - auth, gateway 서버 CI/CD 관리
- 프론트 서버의 Nginx 웹서버 설치 및 L4 적용

### 팀원 공통

코드 품질 및 컨벤션 체크, 정적 코드 분석기에서 알리는 약점 개선(체크스타일, 소나린트, 큐브)을 위한 작업.

## 기술

<br/>

![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)<br/> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&amp;logo=java&amp;logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&amp;logo=Spring&amp;logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&amp;logo=springboot&amp;logoColor=white"> <img src="https://img.shields.io/badge/springbatch-6DB33F?style=for-the-badge&amp;logo=Spring&amp;logoColor=white"><br/>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&amp;logo=mysql&amp;logoColor=white"> <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&amp;logo=hibernate&amp;logoColor=white"> <img src="https://img.shields.io/badge/Jpa-FF0000?style=for-the-badge&amp;logo=Jpa&amp;logoColor=white"> <img src="https://img.shields.io/badge/Querydsl-0769AD?style=for-the-badge&amp;logo=Querydsl&amp;logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&amp;logo=redis&amp;logoColor=white"><br/>
<img src="https://img.shields.io/badge/rabbitMq-FF6600?style=for-the-badge&amp;logo=rabbitMq&amp;logoColor=white">
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)<img src="https://img.shields.io/badge/SonarLint-CB2029?style=for-the-badge&amp;logo=SonarLint&amp;logoColor=white"> <img src="https://img.shields.io/badge/SonarQube-4E9BCD?style=for-the-badge&amp;logo=SonarQube&amp;logoColor=white"><br/>
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white) ![Jenkins](https://img.shields.io/badge/jenkins-%232C5263.svg?style=for-the-badge&logo=jenkins&logoColor=white) ![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white) <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&amp;logo=linux&amp;logoColor=black"> ![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white) <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&amp;logo=github&amp;logoColor=white">

## 팀원

<br/>
<a href="https://github.com/NHN-BookPub/bookpub-front/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=NHN-BookPub/bookpub-front"/>
</a>
