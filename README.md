*** CHATAPI DEVELOPMENT
#### Add banner.txt to resources to overide spring banner 
#### To test API use in powershell (windows) 

```
(Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"question": "Whats the best time to write code?"}').Content

```
## Feature/chathistory ---
```
3.7 Testing the Enhanced ChatBot with Session History
With the introduction of session IDs in our chatbot, we can now maintain
a conversation history, overcoming the previous challenge. Let’s test this
improved functionality:
© 2024 Muthukumaran Navaneethakrishnan
44
Question 1: Informing the Bot About Your Name
POST http://localhost:8080/api/chat
Content-Type: application/json
{
"question": "My name is Mr Rich, remember it",
"sessionId": "cljXX1"
}
Response:
{
"question": "My name is Mr Rich, remember it",
"answer": "Hello Mr Rich, I will remember your name."
}
Question 2: Asking the Bot to Recall Your Name
POST http://localhost:8080/api/chat
Content-Type: application/json
{
"question": "Do you remember my name?",
"sessionId": "cljXX1"
}
Response:
{
"question": "Do you remember my name?",
"answer": "Yes, your name is Mr Rich."
}
Now, as demonstrated, the chatbot responds accurately and contextually,
remembering previous interactions within the same session.
Code The source code for the above section is available

```
