-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS library_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 데이터베이스 사용
USE library_db;

-- 테이블 삭제
DROP TABLE IF EXISTS user_loan_history;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

-- user 테이블 생성
CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',
    name       VARCHAR(255) NOT NULL COMMENT '사용자 이름',
    age        INT          NOT NULL COMMENT '사용자 나이',
    created_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    version    BIGINT       NOT NULL DEFAULT 0 COMMENT '버전 (낙관적 락)'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '사용자 테이블';

-- books 테이블 생성
CREATE TABLE IF NOT EXISTS books
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '책 고유 ID',
    name       VARCHAR(255) NOT NULL COMMENT '책 이름',
    created_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    version    BIGINT       NOT NULL DEFAULT 0 COMMENT '버전 (낙관적 락)'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '책 테이블';

-- user_loan_history 테이블 생성
CREATE TABLE IF NOT EXISTS user_loan_history
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 대출 내역 고유 ID',
    user_id    BIGINT       NOT NULL COMMENT '사용자 ID (FK)',
    book_name  VARCHAR(255) NOT NULL COMMENT '책 이름',
    is_return  TINYINT(1)   NOT NULL COMMENT '반납 여부',
    created_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    version    BIGINT       NOT NULL DEFAULT 0 COMMENT '버전 (낙관적 락)',
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '사용자 대출 내역 테이블';

-- 인덱스 생성
CREATE INDEX idx_user_loan_history_user_id ON user_loan_history (user_id);
