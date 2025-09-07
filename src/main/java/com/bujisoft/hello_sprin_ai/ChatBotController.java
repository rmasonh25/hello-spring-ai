/*
 *
 *
 * Copyright (c) 2025 Bujisoft
 * Author: Robert Hendricks
 * File: ChatBotController.java
 * Created on: 2025-09-07 02:25
 * Last modified: 2025-09-07 02:25
 *
 */

package com.bujisoft.hello_sprin_ai;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;

@RestController
public class ChatBotController {

    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    public ChatBotController(VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }

    @PostMapping("/api/chat")
    public ChatBotResponse askQuestion(@RequestBody ChatBotRequest chatBotRequest) {
        String userText = chatBotRequest.text();                 // never null/blank
        String answer   = vertexAiGeminiChatModel.call(userText);
        return new ChatBotResponse(userText, answer);
    }
}
