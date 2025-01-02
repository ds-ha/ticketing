# API 테스트 스크립트

## test-apis.ps1
콘서트 예약 시스템의 모든 API를 순차적으로 테스트(PowerShell)

### 사용 방법
1. Spring Boot 애플리케이션이 실행 중인지 확인
2. PowerShell을 열고 scripts 폴더로 이동
   ```powershell
   cd scripts
   ```
3. 스크립트를 실행
   ```powershell
   .\test-apis.ps1
   ```

### 테스트하는 API 목록
1. 토큰 발급 API
2. 예약 가능 날짜 조회 API
3. 좌석 조회 API
4. 좌석 예약 API
5. 잔액 충전 API
6. 잔액 조회 API
7. 결제 API