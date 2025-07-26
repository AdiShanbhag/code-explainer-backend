package com.ai.codeexplainer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String explainCode(String codeSnippet) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Explain this code:\n" + codeSnippet);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", new Map[]{message});
        body.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_API_URL, entity, Map.class);
        Map<?, ?> responseBody = response.getBody();
        if (responseBody != null) {
            var choices = (java.util.List<Map<String, Object>>) responseBody.get("choices");
            if (!choices.isEmpty()) {
                return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
            }
        }
        return "No explanation could be generated.";
    }
}