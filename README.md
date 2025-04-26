# Vue-Art (비오아트)

**전시회 검색 및 예약 사이트 개발**

# Git 전략 (Forking Workflow)

## 🔄 Fork & Pull Request Workflow

![Forking Workflow](https://miro.medium.com/v2/resize:fit:1210/format:webp/1*NbRsghbBK86UskBm5WGu7w.png)

# 폴더 구조 (Screaming Architecture)

```
src/
├── common/
├── config/
├── controller/
├── core/
├── dto/
├── entity/
├── repository/
└── service/
```

# 커밋 메시지 컨벤션 Commit Message Convention

## ✅ Type 종류

| Type       | 설명                                   |
| ---------- | -------------------------------------- |
| `feat`     | 새로운 기능 추가                       |
| `fix`      | 버그 수정                              |
| `docs`     | 문서 수정 (README, 주석 등)            |
| `assets`   | 정적 파일 추가 (폰트, 이미지, JSON 등) |
| `style`    | ui 수정 및 개선                        |
| `refactor` | 리팩토링 (기능 변화 없이 구조 개선)    |
| `test`     | 테스트 코드 추가/수정                  |
| `chore`    | 빌드, 패키지, 설정 등 변경             |
| `ci`       | CI 설정 변경 (GitHub Actions 등)       |
| `revert`   | 이전 커밋 되돌리기                     |

---

<br/>
<br/>

# 📦 기술 스택

## 🧑‍💻 Language & Framework

- **JAVA**
- **JDK**: `17`
- **SpringBoot**
- **JPA**
- **Websocket**

## ⚙️ DB

- **Postgres**: `17.2`
- **Redis**

## 🚀 Deployment

- **CI/CD**: GitHub Actions, Docker
