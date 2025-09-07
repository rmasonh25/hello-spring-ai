*** CHATAPI DEVELOPMENT
#### Add banner.txt to resources to overide spring banner 
#### To test API use in powershell (windows) 

```
(Invoke-WebRequest -Uri "http://localhost:8080/api/chat" 
-Method POST `
  -Headers @{ "Content-Type" = "application/json" } 
-Body '{"question": "Whats the best time to write code?"}').Content
```

