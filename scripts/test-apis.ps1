$baseUrl = "http://localhost:8080/api"
$token = ""

Write-Host "`n1. Token Issue API Test" -ForegroundColor Green
$tokenResponse = Invoke-RestMethod -Uri "$baseUrl/queue_tokens" `
    -Method Post `
    -ContentType "application/json" `
    -Body '{"user_id": 123}'
$token = "Bearer " + $tokenResponse.uuid
Write-Host "Issued Token: $token"

Write-Host "`n2.1 Available Dates API Test" -ForegroundColor Green
$headers = @{
    "Authorization" = $token
}
$availableDates = Invoke-RestMethod -Uri "$baseUrl/concerts/available_dates" `
    -Method Get `
    -Headers $headers
Write-Host "Available Dates: $($availableDates.available_dates | ConvertTo-Json)"

Write-Host "`n2.2 Available Seats API Test" -ForegroundColor Green
$seats = Invoke-RestMethod -Uri "$baseUrl/concerts/2024-03-20/seats" `
    -Method Get `
    -Headers $headers
Write-Host "Available Seats: $($seats.seats | ConvertTo-Json)"

Write-Host "`n3. Reservation API Test" -ForegroundColor Green
$reservationBody = @{
    "concert_date" = "2024-03-20"
    "seat_num" = 1
} | ConvertTo-Json
$reservation = Invoke-RestMethod -Uri "$baseUrl/reservations" `
    -Method Post `
    -Headers $headers `
    -ContentType "application/json" `
    -Body $reservationBody
Write-Host "Reservation Info: $($reservation | ConvertTo-Json)"

Write-Host "`n4.1 Balance Charge API Test" -ForegroundColor Green
$chargeBody = @{
    "amount" = 500000
} | ConvertTo-Json
$chargeResult = Invoke-RestMethod -Uri "$baseUrl/users/balance" `
    -Method Post `
    -ContentType "application/json" `
    -Body $chargeBody
Write-Host "Charge Result: $($chargeResult | ConvertTo-Json)"

Write-Host "`n4.2 Balance Check API Test" -ForegroundColor Green
$balance = Invoke-RestMethod -Uri "$baseUrl/users/balance" `
    -Method Get `
    -Headers $headers
Write-Host "Current Balance: $($balance | ConvertTo-Json)"

Write-Host "`n5. Payment API Test" -ForegroundColor Green
$paymentBody = @{
    "reservation_id" = $reservation.reservation_id
} | ConvertTo-Json
$payment = Invoke-RestMethod -Uri "$baseUrl/payments" `
    -Method Post `
    -Headers $headers `
    -ContentType "application/json" `
    -Body $paymentBody
Write-Host "Payment Result: $($payment | ConvertTo-Json)"