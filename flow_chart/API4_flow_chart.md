```mermaid
flowchart TD
    %% 4.1 잔액 충전 API
    A5[충전 요청] --> B5{금액 유효?}
    B5 -->|No| C5[400 Bad Request]
    B5 -->|Yes| D5[잔액 업데이트]

     %% 4.2 잔액 조회 API
    A6[잔액 조회] --> B6[현재 잔액 반환]
```