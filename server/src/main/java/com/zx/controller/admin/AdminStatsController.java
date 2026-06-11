package com.zx.controller.admin;

import com.zx.result.Result;
import com.zx.service.AiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/stats")
@Slf4j
@Api(tags = "管理端错题分析接口")
public class AdminStatsController {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private AiService aiService;

    @GetMapping("/overview")
    @ApiOperation("总体概况")
    public Result<Map<String, Object>> overview(
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) Long unitId) {
        Map<String, Object> data = new HashMap<>();
        String gFilter = grade != null ? " AND u.grade = '" + grade + "'" : "";
        String uFilter = unitId != null ? " AND w.unit_id = " + unitId : "";

        // 总错题数
        int total = jdbc.queryForObject(
            "SELECT COUNT(*) FROM wrong_book wb JOIN word w ON wb.word_id=w.id JOIN unit u ON w.unit_id=u.id WHERE 1=1" + gFilter + uFilter,
            Integer.class);
        // 已纠正
        int corrected = jdbc.queryForObject(
            "SELECT COUNT(*) FROM wrong_book wb JOIN word w ON wb.word_id=w.id JOIN unit u ON w.unit_id=u.id WHERE wb.is_corrected=1" + gFilter + uFilter,
            Integer.class);
        // 本周
        int weekTotal = jdbc.queryForObject(
            "SELECT COUNT(*) FROM wrong_book wb JOIN word w ON wb.word_id=w.id JOIN unit u ON w.unit_id=u.id WHERE wb.last_wrong >= DATE_SUB(NOW(), INTERVAL 7 DAY)" + gFilter + uFilter,
            Integer.class);
        // 涉及学生数
        int studentCount = jdbc.queryForObject(
            "SELECT COUNT(DISTINCT wb.user_id) FROM wrong_book wb JOIN word w ON wb.word_id=w.id JOIN unit u ON w.unit_id=u.id WHERE 1=1" + gFilter + uFilter,
            Integer.class);

        data.put("total", total);
        data.put("corrected", corrected);
        data.put("weekTotal", weekTotal);
        data.put("studentCount", studentCount);
        data.put("rate", total > 0 ? (corrected * 100 / total) : 0);
        return Result.success(data);
    }

    @GetMapping("/word-ranking")
    @ApiOperation("高频错词排行")
    public Result<List<Map<String, Object>>> wordRanking(
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) Long unitId,
            @RequestParam(defaultValue = "10") int top) {
        String gFilter = grade != null ? " AND u.grade = '" + grade + "'" : "";
        String uFilter = unitId != null ? " AND w.unit_id = " + unitId : "";
        List<Map<String, Object>> list = jdbc.queryForList(
            "SELECT w.word, w.definition, w.phonetic, SUM(wb.wrong_count) as totalWrong, " +
            "COUNT(DISTINCT wb.user_id) as studentCount, w.id as wordId " +
            "FROM wrong_book wb JOIN word w ON wb.word_id=w.id " +
            "JOIN unit u ON w.unit_id=u.id WHERE 1=1" + gFilter + uFilter +
            " GROUP BY w.id ORDER BY totalWrong DESC LIMIT " + top);
        return Result.success(list);
    }

    @GetMapping("/category-pie")
    @ApiOperation("分类错误分布")
    public Result<List<Map<String, Object>>> categoryPie(
            @RequestParam(required = false) String grade) {
        String gFilter = grade != null ? " AND grade = '" + grade + "'" : "";
        // 按年级分组（模拟分类），实际可按 unit.name 分段
        List<Map<String, Object>> list = jdbc.queryForList(
            "SELECT u.name as category, SUM(wb.wrong_count) as cnt " +
            "FROM wrong_book wb JOIN word w ON wb.word_id=w.id " +
            "JOIN unit u ON w.unit_id=u.id WHERE 1=1" + gFilter +
            " GROUP BY u.id ORDER BY cnt DESC LIMIT 10");
        return Result.success(list);
    }

    @GetMapping("/student-ranking")
    @ApiOperation("后进生排行")
    public Result<List<Map<String, Object>>> studentRanking(
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) Long unitId) {
        String gFilter = grade != null ? " AND u.grade = '" + grade + "'" : "";
        String uFilter = unitId != null ? " AND w.unit_id = " + unitId : "";
        List<Map<String, Object>> list = jdbc.queryForList(
            "SELECT us.name as studentName, us.id as userId, " +
            "COUNT(CASE WHEN wb.is_corrected=0 THEN 1 END) as uncorrected, " +
            "COUNT(*) as total, SUM(wb.wrong_count) as sumWrong " +
            "FROM wrong_book wb JOIN user us ON wb.user_id=us.id " +
            "JOIN word w ON wb.word_id=w.id JOIN unit u ON w.unit_id=u.id " +
            "WHERE 1=1" + gFilter + uFilter +
            " GROUP BY us.id ORDER BY uncorrected DESC LIMIT 10");
        return Result.success(list);
    }

    @GetMapping("/student-trend")
    @ApiOperation("学生错题趋势")
    public Result<List<Map<String, Object>>> studentTrend(Long userId) {
        List<Map<String, Object>> list = jdbc.queryForList(
            "SELECT DATE(last_wrong) as date, COUNT(*) as cnt " +
            "FROM wrong_book WHERE user_id=? GROUP BY DATE(last_wrong) ORDER BY date", userId);
        return Result.success(list);
    }

    @PostMapping("/ai-tip")
    @ApiOperation("AI 教学建议")
    public Result<Map<String, String>> aiTeachingTip(Long wordId, Integer wrongCount, Integer studentCount) {
        try {
            com.zx.entity.Word word = jdbc.queryForObject(
                "SELECT * FROM word WHERE id=?", (rs, rn) -> {
                    com.zx.entity.Word w = new com.zx.entity.Word();
                    w.setWord(rs.getString("word"));
                    w.setDefinition(rs.getString("definition"));
                    return w;
                }, wordId);
            if (word == null) return Result.error("单词不存在");
            String prompt = String.format(
                "你是小学英语教学专家。单词\"%s\"(含义:%s)本周有%d个学生错了%d次。" +
                "请生成一段30-50字的趣味教学口诀或讲解技巧，帮助老师在课堂上活跃气氛、加深记忆。",
                word.getWord(), word.getDefinition(), studentCount, wrongCount);
            String tip = aiService.chat(prompt, List.of());
            Map<String, String> r = new HashMap<>();
            r.put("tip", tip);
            r.put("word", word.getWord());
            return Result.success(r);
        } catch (Exception e) {
            return Result.error("AI生成失败");
        }
    }
}
