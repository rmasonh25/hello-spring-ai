/*
 *
 *
 * Copyright (c) 2025 Bujisoft
 * Author: Robert Hendricks
 * File: ChatBotController.java
 * Created on: 2025-09-08 00:09
 * Last modified: 2025-09-08 00:09
 *
 */

package com.bujisoft.hello_sprin_ai;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ChatBotController {
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
    private final ChatBotHistoryManager chatBotHistoryManager;

    public ChatBotController(VertexAiGeminiChatModel vertexAiGeminiChatModel, ChatBotHistoryManager chatBotHistoryManager) {
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
        this.chatBotHistoryManager = chatBotHistoryManager;
    }


    @PostMapping("/api/chat")
    public ChatBotResponse askQuestion(@RequestBody ChatBotRequest chatBotRequest) {
        String sessionId = chatBotRequest.sessionId();
        String question = chatBotRequest.question();

        if (chatBotHistoryManager.isNewSession(sessionId))
            chatBotHistoryManager.addSystemMessage(sessionId, "You are my personal assistant");

        //Combine chat history with the new question
        var chatHistory = chatBotHistoryManager.getChatHistory(sessionId);
        var messages = new ArrayList<>(chatHistory);
        messages.add(new UserMessage(question));

        // create a prompt
        Prompt prompt = new Prompt(messages);


        // call the chat client
        ChatResponse chatResponse = vertexAiGeminiChatModel.call(prompt);

        // get the answer
        String answer = chatResponse.getResult().getOutput().getText();

        // add the chat history to our local cache
        chatBotHistoryManager.addChatHistory(sessionId, question, answer);

        // return the answer
        return new ChatBotResponse(question, answer);


    }


}
