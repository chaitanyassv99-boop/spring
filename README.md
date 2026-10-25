# Student Management API (Spring Boot)

A small REST API for practising deployment. It uses an in-memory list; data resets when the app restarts.

## Requirements
- Java 17+
- Maven 3.9+ (or use an IDE with Maven support)

## Run locally
```bash
mvn spring-boot:run
```
Open:
- http://localhost:8080/
- http://localhost:8080/health
- http://localhost:8080/api/students

## API examples

### Add a student
```bash
curl -X POST http://localhost:8080/api/students ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Chaitanya\",\"course\":\"Spring Boot\"}"
```

For PowerShell, you can instead use:
```powershell
$body = @{ name = "Chaitanya"; course = "Spring Boot" } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://localhost:8080/api/students -ContentType "application/json" -Body $body
```

## Deploy without installing Docker locally
1. Upload the project files to a GitHub repository.
2. On a hosting provider that supports Dockerfile-based deployments, create a new web service from that repository.
3. Select Docker as the runtime/build method if prompted.
4. Deploy. The host builds the image remotely from `Dockerfile`; Docker does not need to be installed on your computer.
5. Open the service's public URL and test `/health` and `/api/students`.

Free plan availability, private repository support, build limits, and runtime policies vary by host and may change. Check the provider's current plan before deploying.
"# spring" 
