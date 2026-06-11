package com.zx.controller.user;

import com.zx.result.Result;
import com.zx.service.AiService;
import com.zx.service.WordService;
import com.zx.entity.Word;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/ai")
@Slf4j
@Api(tags = "AI 学习助手接口")
public class AiController {

    @Autowired
    private AiService aiService;

    @Autowired
    private WordService wordService;

    @PostMapping("/mnemonic")
    @ApiOperation("为单词生成趣味记忆法")
    public Result<Map<String, String>> generateMnemonic(Long wordId) {
        Word word = wordService.getById(wordId);
        if (word == null) return Result.error("单词不存在");
        String tip = aiService.generateMnemonic(word.getWord(), word.getDefinition());
        Map<String, String> result = new HashMap<>();
        result.put("word", word.getWord());
        result.put("tip", tip);
        return Result.success(result);
    }

    @PostMapping("/story")
    @ApiOperation("为单元生成AI绘本故事")
    public Result<Map<String, String>> generateStory(Long unitId) {
        List<Word> wordList = wordService.getByUnitId(unitId);
        if (wordList.isEmpty()) return Result.error("该单元暂无单词");

        List<Map<String, String>> words = new ArrayList<>();
        for (Word w : wordList) {
            Map<String, String> m = new HashMap<>();
            m.put("word", w.getWord());
            m.put("definition", w.getDefinition());
            words.add(m);
        }
        String story = aiService.generateStory(words);
        Map<String, String> result = new HashMap<>();
        result.put("story", story);
        return Result.success(result);
    }

    @PostMapping("/chat")
    @ApiOperation("AI 对话")
    public Result<Map<String, String>> chat(@RequestBody Map<String, Object> body) {
        String message = (String) body.get("message");
        @SuppressWarnings("unchecked")
        List<Map<String, String>> history = (List<Map<String, String>>) body.get("history");
        String reply = aiService.chat(message, history != null ? history : List.of());
        Map<String, String> result = new HashMap<>();
        result.put("reply", reply);
        return Result.success(result);
    }
}
