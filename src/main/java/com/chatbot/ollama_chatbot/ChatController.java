package com.chatbot.ollama_chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatController {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(10))
            .writeTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(180))
            .build();

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final List<Map<String, String>> messages = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");

        // Add user message to context
        messages.add(Map.of("role", "user", "content", userMessage));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "phi3"); 
        requestBody.put("messages", messages);
        requestBody.put("stream", false);

        try {
            String json = mapper.writeValueAsString(requestBody);

            Request request = new Request.Builder()
                    .url("http://localhost:11434/api/chat")
                    .post(okhttp3.RequestBody.create(json, MediaType.get("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return ResponseEntity.status(response.code()).body("Ollama error: " + response.message());
                }

                String body = response.body().string();
                Map<String, Object> result = mapper.readValue(body, Map.class);
                Map<String, String> message = (Map<String, String>) result.get("message");

                String reply = message.get("content");

                // Add assistant's response to context
                messages.add(Map.of("role", "assistant", "content", reply));

                return ResponseEntity.ok(reply);
            }

        } catch (SocketTimeoutException e) {
            return ResponseEntity.status(504).body("Ollama took too long to respond. Try again.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("I/O error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }
}
