```mermaid
flowchart TD
    %% 2.1 예약 가능 날짜 조회 API
    A2[날짜 조회 요청] --> B2{토큰 검증}
    B2 -->|실패| C2[401 Unauthorized]
    B2 -->|성공| D2{대기 순서?}
    D2 -->|No| E2[403 Forbidden]
    D2 -->|Yes| F2[가용 날짜 목록 반환]
```