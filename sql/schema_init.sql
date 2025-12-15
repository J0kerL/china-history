-- schema_init.sql with full comments
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS reading_record;
DROP TABLE IF EXISTS person_relation;
DROP TABLE IF EXISTS relic;
DROP TABLE IF EXISTS geo_region;
DROP TABLE IF EXISTS `event`;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS `user_account`;
DROP TABLE IF EXISTS place_name;
DROP TABLE IF EXISTS dynasty;

SET FOREIGN_KEY_CHECKS=1;

-- 1) 朝代表
CREATE TABLE dynasty (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(100) NOT NULL COMMENT '朝代名称，如唐朝、汉朝',
  start_year INT NULL COMMENT '开国年份（公元纪年，可为负数表示公元前）',
  end_year INT NULL COMMENT '灭亡年份（公元纪年，可为负数表示公元前）',
  capital VARCHAR(200) NULL COMMENT '都城名称，如长安、洛阳',
  overview TEXT NULL COMMENT '朝代概述、历史简介',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_dynasty_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='朝代表：存储中国各历史朝代信息';


-- 2) 古今地名对照表
CREATE TABLE place_name (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  ancient_name VARCHAR(200) NOT NULL COMMENT '古代地名，如“长安”“汴梁”',
  modern_name VARCHAR(200) NOT NULL COMMENT '现代地名，如“西安”“开封”',
  modern_location VARCHAR(255) NULL COMMENT '现代地理位置，如省市区或经纬度',
  description TEXT NULL COMMENT '地名相关描述或历史背景',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  INDEX idx_ancient_name (ancient_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='古今地名对照表';


-- 3) 用户表
CREATE TABLE user_account (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  username VARCHAR(100) NOT NULL COMMENT '用户名',
  password VARCHAR(255) NOT NULL COMMENT '密码（BCrypt/MD5）',
  email VARCHAR(200) NULL COMMENT '用户邮箱',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号表';


-- 4) 历史人物表
CREATE TABLE person (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(200) NOT NULL COMMENT '姓名，如李隆基、秦始皇',
  style_name VARCHAR(200) NULL COMMENT '字/号，如“仲尼”“子龙”',
  dynasty_id BIGINT UNSIGNED NULL COMMENT '所属朝代ID，关联 dynasty.id',
  birth_year INT NULL COMMENT '出生年份（公元纪年，可为负数）',
  death_year INT NULL COMMENT '去世年份（公元纪年，可为负数）',
  summary TEXT NULL COMMENT '人物简介、主要成就',
  image_url VARCHAR(511) NULL COMMENT '人物头像或插图链接',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  INDEX idx_person_name (name),
  CONSTRAINT fk_person_dynasty FOREIGN KEY (dynasty_id)
    REFERENCES dynasty(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史人物表';


-- 5) 历史事件表（用于时间轴）
CREATE TABLE `event` (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  title VARCHAR(300) NOT NULL COMMENT '事件标题，如“安史之乱”',
  start_year INT NULL COMMENT '事件开始年份',
  end_year INT NULL COMMENT '事件结束年份',
  dynasty_id BIGINT UNSIGNED NULL COMMENT '所属朝代ID',
  category VARCHAR(100) NULL COMMENT '事件类型：政治/军事/文化/科技等',
  summary TEXT NULL COMMENT '事件概要描述',
  details TEXT NULL COMMENT '事件详情描述',
  location_place_id BIGINT UNSIGNED NULL COMMENT '事件地点ID，对应 place_name.id',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  INDEX idx_event_title (title),
  CONSTRAINT fk_event_dynasty FOREIGN KEY (dynasty_id)
    REFERENCES dynasty(id) ON DELETE SET NULL,
  CONSTRAINT fk_event_place FOREIGN KEY (location_place_id)
    REFERENCES place_name(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史事件表（用于时间轴）';


-- 6) 地理/疆域表（GeoJSON）
CREATE TABLE geo_region (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(200) NOT NULL COMMENT '疆域名称，如“唐代疆域（贞观）”',
  dynasty_id BIGINT UNSIGNED NULL COMMENT '所属朝代ID',
  period_start_year INT NULL COMMENT '对应历史时期起始年份',
  period_end_year INT NULL COMMENT '对应历史时期结束年份',
  geojson LONGTEXT NULL COMMENT '存储 GeoJSON 数据',
  description TEXT NULL COMMENT '描述信息',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  CONSTRAINT fk_geo_region_dynasty FOREIGN KEY (dynasty_id)
    REFERENCES dynasty(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史疆域/地理区域表（支持 GeoJSON）';


-- 7) 历史遗迹表
CREATE TABLE relic (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(300) NOT NULL COMMENT '遗迹名称，如兵马俑',
  location VARCHAR(255) NULL COMMENT '所在地，如城市或景区名',
  dynasty_id BIGINT UNSIGNED NULL COMMENT '相关朝代（可不填）',
  related_event_id BIGINT UNSIGNED NULL COMMENT '相关历史事件ID',
  description TEXT NULL COMMENT '遗迹介绍',
  coordinates VARCHAR(100) NULL COMMENT '经纬度坐标，如"34.3,109.2"',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  CONSTRAINT fk_relic_dynasty FOREIGN KEY (dynasty_id) REFERENCES dynasty(id) ON DELETE SET NULL,
  CONSTRAINT fk_relic_event FOREIGN KEY (related_event_id) REFERENCES `event`(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史遗迹表';


-- 8) 人物关系表
CREATE TABLE person_relation (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  person_id BIGINT UNSIGNED NOT NULL COMMENT '人物ID（主角）',
  related_person_id BIGINT UNSIGNED NULL COMMENT '被关联人物ID（若在库中）',
  related_person_name VARCHAR(200) NULL COMMENT '被关联人物名称（如不在库中）',
  relation_type VARCHAR(100) NOT NULL COMMENT '关系类型：父子/君臣/敌对/师承等',
  description TEXT NULL COMMENT '关系说明',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  CONSTRAINT fk_pr_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE,
  CONSTRAINT fk_pr_related_person FOREIGN KEY (related_person_id) REFERENCES person(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人物关系表';


-- 9) 用户阅读记录表
CREATE TABLE reading_record (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
  target_type VARCHAR(50) NOT NULL COMMENT '记录类型：person/event/dynasty/place/relic',
  target_id BIGINT UNSIGNED NULL COMMENT '目标记录ID',
  viewed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '查看时间',
  PRIMARY KEY (id),
  INDEX idx_rr_user (user_id),
  CONSTRAINT fk_rr_user FOREIGN KEY (user_id)
    REFERENCES user_account(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户阅读记录表';


-- ===== 以下为示例数据 =====
INSERT INTO dynasty (name, start_year, end_year, capital, overview) VALUES ('唐朝',618,907,'长安（今西安）','唐朝：中国历史上的盛世之一，开元、贞观之治为代表，政治、经济、文化高度繁荣。');

INSERT INTO place_name (ancient_name, modern_name, modern_location, description)
VALUES ('长安','西安','陕西省西安市','古都长安，今称西安，是多朝代都城，位于关中平原。');

INSERT INTO user_account (username, password, email)
VALUES ('admin','$2a$10$EXAMPLEPLACEHOLDERHASH','admin@example.com');

INSERT INTO person (name, style_name, dynasty_id, birth_year, death_year, summary, image_url)
VALUES ('李隆基','唐玄宗',1,685,762,'唐朝第六位皇帝，开元中兴开始，后期天宝年间朝政混乱，安史之乱爆发。',NULL);

INSERT INTO `event` (title, start_year, end_year, dynasty_id, category, summary, details, location_place_id)
VALUES ('安史之乱',755,763,1,'军事','由安禄山、史思明发动的大规模叛乱，重创唐朝国力，标志着唐朝由盛转衰。','安史之乱始于范阳、应州等地，叛军一度攻入长安与洛阳，几经战事方才平定，对唐朝造成长远影响。',1);

INSERT INTO geo_region (name, dynasty_id, period_start_year, period_end_year, geojson, description)
VALUES ('唐代（示例疆域）',1,618,907,'{ "type": "FeatureCollection", "features": [ { "type": "Feature", "properties": { "name": "Tang example" }, "geometry": { "type": "Polygon", "coordinates": [ [ [108.0,34.0],[110.0,34.0],[110.0,32.0],[108.0,32.0],[108.0,34.0] ] ] } } ] }','示例性 GeoJSON：仅用于格式演示。');

INSERT INTO relic (name, location, dynasty_id, related_event_id, description, coordinates)
VALUES ('秦始皇兵马俑','陕西省西安市临潼区秦始皇陵东侧',NULL,NULL,'秦始皇陵陪葬军阵雕塑群，世界著名考古发现之一。','34.3853,109.2825');

INSERT INTO person_relation (person_id, related_person_id, related_person_name, relation_type, description)
VALUES (1,NULL,'安禄山','君臣/敌对','安禄山反叛，引发安史之乱，本记录为示例。');

INSERT INTO reading_record (user_id, target_type, target_id)
VALUES (1,'dynasty',1);

-- done
