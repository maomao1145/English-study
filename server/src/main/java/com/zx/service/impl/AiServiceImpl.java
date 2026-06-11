package com.zx.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zx.properties.AiProperties;
import com.zx.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
public class AiServiceImpl implements AiService {

    @Autowired
    private AiProperties aiProperties;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    public String generateMnemonic(String word, String definition) {
        String prompt = String.format(
            "你是一个帮助小学生背单词的AI助手。请为单词 \"%s\"（含义：%s）生成一句中文趣味记忆法。" +
            "可以是谐音、拆字联想或顺口溜。要求：15-30字，充满童趣，小学生易懂。只输出记忆法本身，不要其他内容。",
            word, definition
        );
        return callAi(prompt);
    }

    public String generateStory(List<Map<String, String>> words) {
        StringBuilder wordList = new StringBuilder();
        for (Map<String, String> w : words) {
            wordList.append(w.get("word")).append("(").append(w.get("definition")).append("), ");
        }
        String prompt = String.format(
            "你是一个儿童故事作家。请用以下英语单词写一个100字左右的童趣小故事（中文为主，英语单词保留原文并加**加粗**）。" +
            "单词列表：%s。要求：故事连贯有趣，适合小学生阅读，每个列出的单词都要出现在故事中至少一次。只输出故事内容。",
            wordList.toString()
        );
        return callAi(prompt);
    }

    public String chat(String message, List<Map<String, String>> history) {
        String systemPrompt = "你是一个帮助小学生学习英语的 AI 助手。请用中文回答，语气亲切活泼，适合小学生。回答简洁，控制在50字以内。";
        return callAiWithHistory(systemPrompt, message, history);
    }

    private String callAiWithHistory(String system, String userMsg, List<Map<String, String>> history) {
        try {
            String apiKey = aiProperties.getApiKey();
            if (apiKey == null || apiKey.isEmpty() || apiKey.startsWith("${")) {
                return "AI 服务未配置 API Key";
            }

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", system));
            if (history != null) {
                for (Map<String, String> h : history) {
                    messages.add(Map.of("role", h.get("role"), "content", h.get("content")));
                }
            }
            messages.add(Map.of("role", "user", "content", userMsg));

            return doCall(messages);
        } catch (Exception e) {
            log.error("AI 调用失败", e);
            return "AI 暂时不可用，请稍后";
        }
    }

    private String callAi(String prompt) {
        try {
            String apiKey = aiProperties.getApiKey();
            if (apiKey == null || apiKey.isEmpty() || apiKey.startsWith("${")) {
                return "AI 服务未配置 API Key，请在 application-dev.yml 中设置 zx.ai.api-key";
            }
            List<Map<String, String>> messages = List.of(Map.of("role", "user", "content", prompt));
            return doCall(messages);
        } catch (Exception e) {
            log.error("AI 调用失败", e);
            return "AI 生成失败，请稍后重试。提示：" + e.getMessage();
        }
    }

    private String doCall(List<Map<String, String>> messages) {
        try {
            String apiKey = aiProperties.getApiKey();

            Map<String, Object> body = new HashMap<>();
            body.put("model", aiProperties.getModel());
            body.put("messages", messages);
            body.put("max_tokens", 500);
            body.put("temperature", 0.9);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            ResponseEntity<String> resp = restTemplate.exchange(
                aiProperties.getBaseUrl() + "/v1/chat/completions",
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class
            );

            JsonNode root = objectMapper.readTree(resp.getBody());
            return root.path("choices").get(0).path("message").path("content").asText().trim();
        } catch (Exception e) {
            log.error("AI 调用失败", e);
            return "AI 生成失败，请稍后重试。提示：" + e.getMessage();
        }
    }
}
