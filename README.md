# library-app

Spring Boot 기반의 간단한 도서관 앱입니다.  
사용자 등록과 목록 조회 기능을 제공하고, 정적 프론트 페이지를 통해 브라우저에서 바로 확인할 수 있습니다.

## 프로젝트 소개

이 프로젝트는 다음 내용을 중심으로 구성되어 있습니다.

- Spring Boot 기반 REST API
- JPA를 이용한 사용자 데이터 저장/조회
- 공통 API 응답 형식과 전역 예외 처리
- 정적 프론트 페이지와 백엔드 API 연동
- Docker Compose 기반 MySQL 실행 환경

## 기술 스택

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- MySQL
- H2
- Gradle
- Docker Compose

## 주요 기능

### 사용자 기능

- 사용자 등록
- 사용자 목록 조회
- 동일한 이름 등록 방지

### 프론트 화면

- 사용자 등록
- 책 등록 화면
- 책 대출 화면
- 책 반납 화면
- 사용자 목록 탭

현재 백엔드에서 직접 구현된 사용자 관련 API는 등록/목록 조회 기준입니다.  
정적 프론트는 `src/main/resources/static/v1` 아래에 포함되어 있습니다.

## 프로젝트 구조

```text
src/main/java/com/bepo/libraryapp
├─ domain
│  └─ user
│     ├─ controller
│     ├─ dto
│     ├─ entity
│     ├─ exception
│     ├─ repository
│     └─ service
├─ global
│  ├─ common
│  └─ exception
└─ LibraryAppApplication.java
```

## 실행 환경

### 기본 포트

- `default` 프로필: `18080`
- `dev` 프로필: `18081`

기본 활성 프로필은 `dev`입니다.

### 정적 페이지 접속 경로

```text
http://localhost:18081/v1/index.html
```

## 실행 방법

### 1. 환경 변수 파일 준비

프로젝트는 `.env` 파일을 읽도록 설정되어 있습니다.

예시:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=library_db
DB_USERNAME=library_user
DB_PASSWORD=library_password

MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=library_db
MYSQL_USER=library_user
MYSQL_PASSWORD=library_password
TZ=Asia/Seoul
```

### 2. MySQL 실행

```bash
docker compose up -d
```

### 3. 애플리케이션 실행

Windows:

```bash
gradlew.bat bootRun
```

macOS / Linux:

```bash
./gradlew bootRun
```

## API 예시

### 사용자 등록

`POST /user`

요청:

```json
{
  "name": "bepo03",
  "age": 27
}
```

응답:

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "bepo03",
    "age": 27
  },
  "error": null
}
```

### 사용자 목록 조회

`GET /user`

응답:

```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "bepo03",
      "age": 27
    }
  ],
  "error": null
}
```

### 중복 이름 예외 응답

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "DUPLICATE_NAME",
    "message": "이미 사용 중인 이름 입니다. (name: bepo03)"
  }
}
```

## 예외 처리

공통 응답 형식은 `ApiResponse`로 통일되어 있습니다.

- 성공 응답: `success = true`
- 실패 응답: `success = false`
- 예외 응답은 `error.code`, `error.message`로 전달

비즈니스 예외는 `GlobalExceptionHandler`에서 처리합니다.

## 참고 사항

- 개발 프로필에서는 `spring.jpa.hibernate.ddl-auto=update`가 설정되어 있습니다.
- 로컬 실행용 설정 파일인 `.env`는 `.gitignore`에 포함되어 있습니다.
- 정적 프론트는 빌드 결과물이 포함된 형태로 관리되고 있습니다.
