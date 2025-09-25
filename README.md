# 프로젝트 CRUD 과제 명세서 (md 파일 작성시. LLM 사용)
**RBAC(역할 기반 접근 제어) + 요금제 한도**

---

## 구현 기능 안내

- **프로젝트 생성**
- **상세 조회**
- **수정**
- **삭제**

<br/>

---

##  실행 방법

# 실행 방법 

```bash
.\gradlew -x test clean build
docker-compose up
```

## 테스트를 위한 JWT 토큰 (만료 1년)

### BASIC PLAN 사용자 (basicuser)
| 역할(A~D) | 토큰 값 |
|:---:|:---|
| **A** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXNpY3VzZXIiLCJyb2xlcyI6WyJBIl0sImV4cCI6MTc5MDMwNDUwOH0.sTktjlVpkdnK7YuHnU7lPfQv9T65hmeJYkE0x-fQB4I` |
| **B** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXNpY3VzZXIiLCJyb2xlcyI6WyJCIl0sImV4cCI6MTc5MDMwNTQ0OH0.P48P3a0iVKiDLyJ_gQPOHfZM7TaM2oQl1pc0T1Ad_I4` |
| **C** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXNpY3VzZXIiLCJyb2xlcyI6WyJDIl0sImV4cCI6MTc5MDMwNTQ3NX0.2u3uFWL1IVnYpkLZIupbB5E7jASgwX1rDK1FtUltSag` |
| **D** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXNpY3VzZXIiLCJyb2xlcyI6WyJEIl0sImV4cCI6MTc5MDMwNTQ4OH0.pRTxxoMd45wLl6a38feP3E_jF5qSQeXxAxH7FKgmJaM` |

---

### PRO PLAN 사용자 (prouser)
| 역할(A~D) | 토큰 값 |
|:---:|:---|
| **A** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm91c2VyIiwicm9sZXMiOlsiQSJdLCJleHAiOjE3OTAzMDU1MzR9.yJ368y3r1NfEa0AM9qWbCSxN1nqxERxjkzipH2Xiv5Y` |
| **B** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm91c2VyIiwicm9sZXMiOlsiQiJdLCJleHAiOjE3OTAzMDU1Mjh9.rp13lpjzeoM-y_4T1kB9aZuyE8Vj--TkzAznLI70iME` |
| **C** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm91c2VyIiwicm9sZXMiOlsiQyJdLCJleHAiOjE3OTAzMDU1MjF9.T36tLi6mef32XMLzltTl9v9oqEdK4G6DItLLIh7bhns` |
| **D** | `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm91c2VyIiwicm9sZXMiOlsiRCJdLCJleHAiOjE3OTAzMDU1MTN9.ZlQ8PHAujjd4QG0-nxZvvwjh9js-7q9DGU81ylNgRZo` |

---

> 토큰 복사 후 Authorization 헤더에 `Bearer {토큰값}` 으로 사용하세요.
