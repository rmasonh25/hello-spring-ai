/*
 *
 *
 * Copyright (c) 2025 Bujisoft
 * Author: Robert Hendricks
 * File: ChatBotController.java
 * Created on: 2025-09-07 14:44
 * Last modified: 2025-09-07 14:44
 *
 */

package com.bujisoft.hello_sprin_ai;

import com.google.common.io.ByteProcessor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
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
        String question = chatBotRequest.question();

        //// Setup options
        ChatOptions options = VertexAiGeminiChatOptions.builder()
                .maxOutputTokens(100)
                .temperature(0.5)
                .build();
        Prompt prompt = new Prompt(question, options);
        ///  Invoke Gemini Client
        ChatResponse chatResponse = vertexAiGeminiChatModel.call(prompt);
        ///  extract answer
        String answer   = chatResponse.getResult().getOutput().getText();
        ///  return response
        return new ChatBotResponse(chatBotRequest.question(), answer);
    }
}
