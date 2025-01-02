# 콘서트 예매 시스템 API 명세서

## 1️⃣ 유저 토큰 발급 API

### Endpoint
```http
POST /api/queue_tokens
```

### Headers
```http
Content-Type: application/json
```

### Request Body
```json
{
    "user_id": 123
}
```

### Response (200 OK)
```json
{
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "queue_num": 15,
    "expires_at": "300"
}
```

### Error Response
- `401 Unauthorized`: 유효하지 않은 사용자
- `409 Conflict`: 이미 발급된 유효한 토큰 존재

## 2️⃣ 예약 가능 날짜 / 좌석 조회 API

### 2.1 예약 가능 날짜 조회
#### Endpoint
```http
GET /api/concerts/available_dates
```

#### Headers
```http
Authorization: Bearer {UUID}
```

#### Response (200 OK)
```json
{
    "available_dates": [
        {
            "date": "2024-03-20",
            "available_seat_count": 30
        },
        {
            "date": "2024-03-21",
            "available_seat_count": 45
        }
    ]
}
```

### 2.2 예약 가능 좌석 조회
#### Endpoint
```http
GET /api/concerts/{date}/seats
```

#### Headers
```http
Authorization: Bearer {UUID}
```

#### Response (200 OK)
```json
{
    "seats": [
        {
            "seat_num": 1,
            "grade": "VIP",
            "price": 200000,
            "status": "AVAILABLE"
        },
        {
            "seat_num": 2,
            "grade": "VIP",
            "price": 200000,
            "status": "RESERVED"
        }
    ]
}
```

#### Error Response
- `401 Unauthorized`: 유효하지 않은 토큰
- `403 Forbidden`: 대기열 순서가 아님
- `404 Not Found`: 존재하지 않는 날짜

## 3️⃣ 좌석 예약 요청 API
### Endpoint
```http
POST /api/reservations
```

### Headers
```http
Authorization: Bearer {UUID}
Content-Type: application/json
```

### Request Body
```json
{
    "concert_date": "2024-03-20",
    "seat_num": 1
}
```

### Response (200 OK)
```json
{
    "reservation_id": "123",
    "seat_info": {
        "seat_num": 1,
        "grade": "VIP",
        "price": 200000
    },
    "temp_reserved_at": "2024-03-19T15:30:00",
    "status": "TEMPORARY"
}
```

### Error Response
- `401 Unauthorized`: 유효하지 않은 토큰
- `403 Forbidden`: 대기열 순서가 아님
- `409 Conflict`: 이미 예약된 좌석
- `400 Bad Request`: 유효하지 않은 좌석 번호 / 날짜

## 4️⃣ 잔액 충전/조회 API

### 4.1 잔액 충전
#### Endpoint
```http
POST /api/users/balance
```

#### Headers
```http
Content-Type: application/json
```

#### Request Body
```json
{
    "amount": 10000
}
```

#### Response (200 OK)
```json
{
    "user_id": 123,
    "updated_balance": 500000,
    "last_charge_amount": 500000
}
```

### 4.2 잔액 조회
#### Endpoint
```http
GET /api/users/balance
```

#### Headers
```http
Authorization: Bearer {UUID}
```

#### Response (200 OK)
```json
{
    "user_id": 123,
    "balance": 500000
}
```

#### Error Response
- `401 Unauthorized`: 유효하지 않은 토큰
- `400 Bad Request`: 유효하지 않은 충전 금액

## 5️⃣ 결제 API
### Endpoint
```http
POST /api/payments
```

### Headers
```http
Authorization: Bearer {UUID}
Content-Type: application/json
```

### Request Body
```json
{
    "reservation_id": "123"
}
```

### Response (200 OK)
```json
{
    "payment_id": "456",
    "reservation_id": "123",
    "paid_amount": 200000,
    "status": "SUCCESS",
    "completed_at": "2024-03-19T15:28:00",
    "seat_info": {
        "concert_date": "2024-03-20",
        "seat_num": 1,
        "grade": "VIP"
    },
    "payment_details": {
        "before_balance": 500000,
        "after_balance": 300000,
        "payment_method": "BALANCE"
    }
}
```

### Error Response
- `401 Unauthorized`: 유효하지 않은 토큰
- `402 Payment Required`: 잔액 부족
- `404 Not Found`: 존재하지 않는 예약
- `409 Conflict`: 이미 결제된 예약
- `410 Gone`: 예약 만료

## 공통 오류 응답 형식
```json
{
    "timestamp": "2024-03-19T15:28:00",
    "status": 400,
    "error": "Bad Request",
    "message": "상세 에러 메시지",
    "path": "/api/reservations"
}
```