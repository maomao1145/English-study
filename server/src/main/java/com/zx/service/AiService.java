package com.zx.service;

import java.util.List;
import java.util.Map;

public interface AiService {

    /** 为单个单词生成趣味记忆法 */
    String generateMnemonic(String word, String definition);

    /** 为一组单词生成童趣小故事 */
    String generateStory(List<Map<String, String>> words);

    /** AI 对话 */
    String chat(String message, List<Map<String, String>> history);
}
