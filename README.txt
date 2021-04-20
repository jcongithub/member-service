
./mvnw spring-boot:run



See all members in the database
> curl http://localhost:8080/member/list

List all visits of a patient
>curl "http://localhost:8080/member/visits?patientId=3"

Get the medical summary of a patient
>curl http://localhost:8080/patient/3

Create a new member 
>curl -i -X POST http://localhost:8080/member/create -H "Content-Type: application/json" -d '{"phone":"212-222-6666","name":"Eric", "address":"Albany", "legalId": "6666", "birthday":"1990-01-01", "gender":"M"}'





  


