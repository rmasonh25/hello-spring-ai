/*
 *
 *
 * Copyright (c) 2025 Bujisoft
 * Author: Robert Hendricks
 * File: ChatBotRequest.java
 * Created on: 2025-09-07 02:24
 * Last modified: 2025-09-07 02:24
 *
 */


// ChatBotRequest.java
package com.bujisoft.hello_sprin_ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record ChatBotRequest(
        @JsonProperty("message")  String message,
        @JsonProperty("prompt")   String prompt,
        @JsonProperty("question") String question
) {
    public String text() {
        if (message  != null && !message.isBlank())  return message.trim();
        if (prompt   != null && !prompt.isBlank())   return prompt.trim();
        if (question != null && !question.isBlank()) return question.trim();
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Provide a non-empty 'message' (or 'prompt'/'question')."
        );
    }
}
