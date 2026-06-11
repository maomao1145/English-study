-- ============================================================
-- 小学英语词汇系统 - 数据库初始化脚本
-- ============================================================

CREATE DATABASE IF NOT EXISTS `english_study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `english_study`;

-- ============================================================
-- 1. 管理员表
-- ============================================================
CREATE TABLE IF NOT EXISTS `admin` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
    `username`    VARCHAR(50)  NOT NULL                COMMENT '用户名',
    `password`    VARCHAR(100) NOT NULL                COMMENT '密码(MD5加密)',
    `name`        VARCHAR(50)  DEFAULT NULL            COMMENT '姓名',
    `phone`       VARCHAR(20)  DEFAULT NULL            COMMENT '手机号',
    `status`      TINYINT      NOT NULL DEFAULT 1      COMMENT '状态: 1启用 0禁用',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_admin_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 如果表已存在但缺字段，执行以下补救语句:
-- ALTER TABLE admin ADD COLUMN IF NOT EXISTS name VARCHAR(50) AFTER username;
-- ALTER TABLE admin ADD COLUMN IF NOT EXISTS phone VARCHAR(20) AFTER name;
-- ALTER TABLE admin ADD COLUMN IF NOT EXISTS status TINYINT NOT NULL DEFAULT 1 AFTER phone;

-- 插入默认管理员: admin / 123456
INSERT INTO `admin` (`username`, `password`, `name`, `status`) VALUES
('admin', MD5('123456'), '系统管理员', 1);

-- ============================================================
-- 2. 学生/用户表
-- ============================================================
CREATE TABLE IF NOT EXISTS `user` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '学生ID',
    `username`    VARCHAR(50)  NOT NULL                COMMENT '用户名',
    `name`        VARCHAR(50)  NOT NULL                COMMENT '姓名',
    `password`    VARCHAR(100) NOT NULL                COMMENT '密码(MD5加密)',
    `phone`       VARCHAR(20)  DEFAULT NULL            COMMENT '手机号',
    `sex`         VARCHAR(10)  DEFAULT NULL            COMMENT '性别: 男/女',
    `status`      TINYINT      NOT NULL DEFAULT 1      COMMENT '状态: 1正常 0禁用',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 测试学生
INSERT INTO `user` (`username`, `name`, `password`, `sex`) VALUES
('student1', '小明', MD5('123456'), '男'),
('student2', '小红', MD5('123456'), '女');

-- ============================================================
-- 3. 年级表
-- ============================================================
CREATE TABLE IF NOT EXISTS `grade` (
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '年级ID',
    `name`        VARCHAR(50) NOT NULL                COMMENT '年级名称: 三年级上/三年级下/...',
    `status`      TINYINT     NOT NULL DEFAULT 1      COMMENT '状态: 1启用 0禁用',
    `grade_id`    VARCHAR(20) DEFAULT NULL            COMMENT '年级编码: 3A/3B/4A/...',
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='年级表';

-- 初始化年级数据
INSERT INTO `grade` (`name`, `grade_id`) VALUES
('三年级上册', '3A'),
('三年级下册', '3B'),
('四年级上册', '4A'),
('四年级下册', '4B'),
('五年级上册', '5A'),
('五年级下册', '5B'),
('六年级上册', '6A'),
('六年级下册', '6B');

-- ============================================================
-- 4. 单元表
-- ============================================================
CREATE TABLE IF NOT EXISTS `unit` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '单元ID',
    `name`        VARCHAR(100) NOT NULL                COMMENT '单元名称: Unit 1 Hello!',
    `grade`       VARCHAR(50)  NOT NULL                COMMENT '所属年级',
    `word_count`  INT          DEFAULT 0               COMMENT '单词数量',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单元表';

-- ============================================================
-- 5. 单词表
-- ============================================================
CREATE TABLE IF NOT EXISTS `word` (
    `id`                   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '单词ID',
    `word`                 VARCHAR(100) NOT NULL                COMMENT '单词',
    `phonetic`             VARCHAR(100) DEFAULT NULL            COMMENT '音标',
    `definition`           VARCHAR(500) DEFAULT NULL            COMMENT '中文释义',
    `image_url`            VARCHAR(500) DEFAULT NULL            COMMENT '配图URL',
    `example_sentence`     VARCHAR(500) DEFAULT NULL            COMMENT '例句',
    `example_translation`  VARCHAR(500) DEFAULT NULL            COMMENT '例句翻译',
    `unit_id`              BIGINT       DEFAULT NULL            COMMENT '所属单元ID',
    `create_time`          DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_word_unit_id` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单词表';

-- ============================================================
-- 6. 错题本表 (学生端新增)
-- ============================================================
CREATE TABLE IF NOT EXISTS `wrong_book` (
    `id`           BIGINT   NOT NULL AUTO_INCREMENT COMMENT '错题记录ID',
    `user_id`      BIGINT   NOT NULL                   COMMENT '学生ID',
    `word_id`      BIGINT   NOT NULL                   COMMENT '单词ID',
    `wrong_count`  INT      NOT NULL DEFAULT 1         COMMENT '错误次数',
    `is_corrected` TINYINT  NOT NULL DEFAULT 0         COMMENT '是否已纠正: 1已纠正 0未纠正',
    `last_wrong`   DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '最近错误时间',
    `create_time`  DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '首次错误时间',
    PRIMARY KEY (`id`),
    KEY `idx_wb_user_id` (`user_id`),
    KEY `idx_wb_word_id` (`word_id`),
    UNIQUE KEY `uk_user_word` (`user_id`, `word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='错题本表';

-- ============================================================
-- 7. 用户学习进度表 (学生端新增)
-- ============================================================
CREATE TABLE IF NOT EXISTS `user_progress` (
    `id`              BIGINT  NOT NULL AUTO_INCREMENT COMMENT '进度ID',
    `user_id`         BIGINT  NOT NULL                  COMMENT '学生ID',
    `unit_id`         BIGINT  NOT NULL                  COMMENT '单元ID',
    `total_words`     INT     NOT NULL DEFAULT 0        COMMENT '单元总单词数',
    `learned_words`   INT     NOT NULL DEFAULT 0        COMMENT '已学单词数',
    `correct_count`   INT     NOT NULL DEFAULT 0        COMMENT '累计正确数',
    `wrong_count`     INT     NOT NULL DEFAULT 0        COMMENT '累计错误数',
    `create_time`     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_progress_user_unit` (`user_id`, `unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户学习进度表';

-- ============================================================
-- 8. 用户积分等级表 (学生端新增)
-- ============================================================
CREATE TABLE IF NOT EXISTS `user_points` (
    `id`          BIGINT  NOT NULL AUTO_INCREMENT COMMENT '积分ID',
    `user_id`     BIGINT  NOT NULL                  COMMENT '学生ID',
    `points`      INT     NOT NULL DEFAULT 0        COMMENT '当前积分',
    `level`       INT     NOT NULL DEFAULT 1        COMMENT '当前等级',
    `streak_days` INT     NOT NULL DEFAULT 0        COMMENT '连续打卡天数',
    `last_checkin` DATE    DEFAULT NULL             COMMENT '最后打卡日期',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_points_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户积分等级表';

-- ============================================================
-- 9. 用户徽章表 (学生端新增)
-- ============================================================
CREATE TABLE IF NOT EXISTS `user_badge` (
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `user_id`     BIGINT      NOT NULL                COMMENT '学生ID',
    `badge_key`   VARCHAR(50) NOT NULL                COMMENT '徽章标识: first_study/vocab_master/streak_7/full_score/persist_30/speed_answer',
    `earned_time` DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '获得时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_badge_user_key` (`user_id`, `badge_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户徽章表';

-- ============================================================
-- 完成
-- ============================================================
-- 管理员端: admin / 123456
-- 学生端:   student1 / 123456  (小明)
--           student2 / 123456  (小红)
