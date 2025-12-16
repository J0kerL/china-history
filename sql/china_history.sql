/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : china_history

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 16/12/2025 11:46:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dynasty
-- ----------------------------
DROP TABLE IF EXISTS `dynasty`;
CREATE TABLE `dynasty`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '朝代名称，如唐朝、汉朝',
  `start_year` int NULL DEFAULT NULL COMMENT '开国年份（公元纪年，可为负数表示公元前）',
  `end_year` int NULL DEFAULT NULL COMMENT '灭亡年份（公元纪年，可为负数表示公元前）',
  `capital` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '都城名称，如长安、洛阳',
  `overview` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '朝代概述、历史简介',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_dynasty_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '朝代表：存储中国各历史朝代信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynasty
-- ----------------------------
INSERT INTO `dynasty` VALUES (1, '三皇时代', NULL, NULL, '无固定都城', '华夏上古传说时期，三皇（燧人、伏羲、神农等）教民钻木取火、耕种畜牧、创造八卦，奠定华夏文明基础', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (2, '五帝时代', NULL, NULL, '涿鹿、平阳等', '部落联盟鼎盛时期，黄帝、颛顼、帝喾、尧、舜相继执政，统一华夏部落，推行禅让制，大禹治水成功', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (3, '夏朝', -2070, -1600, '阳城、斟鄩', '中国史载第一个世袭制王朝，大禹建夏，启继位开创家天下，桀亡国，二里头遗址为重要文化遗存', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (4, '商朝', -1600, -1046, '亳、殷', '又称殷商，盘庚迁殷后国力兴盛，甲骨文成熟，青铜文明发达，商纣王荒淫亡国', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (5, '西周', -1046, -771, '镐京', '武王伐纣建立，周公制礼作乐，推行分封制、宗法制，厉王时期国人暴动，幽王烽火戏诸侯致亡国', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (6, '东周', -770, -256, '洛邑', '平王东迁后建立，分为春秋、战国两段，王室衰微，诸侯争霸，百家争鸣，最终秦灭东周', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (7, '秦朝', -221, -207, '咸阳', '秦始皇统一六国，建立中国第一个中央集权封建王朝，推行郡县制、统一文字货币度量衡，修筑长城，二世而亡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (8, '西汉', -202, 8, '长安', '刘邦建立，史称西汉，文景之治休养生息，汉武帝时期独尊儒术、开拓西域，王莽篡汉终结', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (9, '新朝', 8, 23, '长安', '王莽篡汉建立，推行托古改制，因政策失当引发绿林、赤眉起义，最终被推翻', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (10, '东汉', 25, 220, '洛阳', '刘秀建立，史称东汉，光武中兴恢复国力，外戚与宦官交替专权，黄巾起义后走向分裂', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (11, '曹魏', 220, 265, '洛阳', '曹丕篡汉建立，占据北方，实行九品中正制，司马懿家族逐渐掌权，最终被西晋取代', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (12, '蜀汉', 221, 263, '成都', '刘备建立，占据西南，诸葛亮鞠躬尽瘁北伐，后被曹魏所灭', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (13, '东吴', 222, 280, '建业', '孙权建立，占据江南，航海业发达，最终被西晋统一', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (14, '西晋', 265, 317, '洛阳', '司马炎建立，统一三国，八王之乱后国力衰退，永嘉之乱后南渡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (15, '东晋', 317, 420, '建康', '司马睿在江南建立，门阀政治盛行，淝水之战击败前秦，后被刘裕取代', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (16, '南北朝', 420, 589, '建康、平城、洛阳、长安等', '南朝（宋、齐、梁、陈）与北朝（北魏、东魏、西魏、北齐、北周）对峙，孝文帝改革促进民族融合，隋最终统一', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (17, '隋朝', 581, 618, '长安、洛阳', '杨坚建立，统一南北，开创三省六部制、科举制，开凿大运河，二世而亡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (18, '唐朝', 618, 907, '长安、洛阳', '李渊建立，中国封建王朝鼎盛时期，贞观之治、开元盛世国力强盛，安史之乱后由盛转衰，藩镇割据终结', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (19, '五代十国', 907, 960, '开封、洛阳、金陵等', '五代（梁、唐、晋、汉、周）更迭于北方，十国割据于南方，战乱频繁，后周世宗改革为统一奠定基础', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (20, '北宋', 960, 1127, '开封', '赵匡胤陈桥兵变建立，推行重文轻武，经济文化发达，澶渊之盟与辽对峙，靖康之耻被金灭亡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (21, '辽朝', 916, 1125, '上京临潢府', '契丹族建立，占据北方草原与燕云十六州，推行南北面官制，与宋、夏并立，后被金所灭', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (22, '西夏', 1038, 1227, '兴庆府', '党项族建立，占据西北，与宋、辽、金并立，创造西夏文，后被蒙古所灭', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (23, '南宋', 1127, 1279, '临安', '赵构南渡建立，偏安江南，岳飞抗金，经济重心完全南移，崖山海战被元灭亡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (24, '金朝', 1115, 1234, '上京会宁府、中都', '女真族建立，灭辽与北宋，占据北方，后被蒙古与南宋联合灭亡', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (25, '元朝', 1271, 1368, '大都', '忽必烈建立，蒙古族统一中国，疆域空前辽阔，推行行省制，民族融合加强，元末农民起义推翻', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (26, '明朝', 1368, 1644, '南京、北京', '朱元璋建立，推翻元朝，洪武之治、永乐盛世，郑和下西洋，后期闭关锁国，崇祯帝自缢亡国', '2025-12-15 17:56:07');
INSERT INTO `dynasty` VALUES (27, '清朝', 1644, 1912, '北京', '满族建立，清军入关统一全国，康乾盛世疆域稳定，后期鸦片战争后国力衰退，辛亥革命后宣统帝退位，封建王朝终结', '2025-12-15 17:56:07');

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '事件标题，如“安史之乱”',
  `start_year` int NULL DEFAULT NULL COMMENT '事件开始年份',
  `end_year` int NULL DEFAULT NULL COMMENT '事件结束年份',
  `dynasty_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属朝代ID',
  `category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '事件类型：政治/军事/文化/科技等',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '事件概要描述',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '事件详情描述',
  `location_place_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '事件地点ID，对应 place_name.id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_event_title`(`title` ASC) USING BTREE,
  INDEX `fk_event_dynasty`(`dynasty_id` ASC) USING BTREE,
  INDEX `fk_event_place`(`location_place_id` ASC) USING BTREE,
  CONSTRAINT `fk_event_dynasty` FOREIGN KEY (`dynasty_id`) REFERENCES `dynasty` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_event_place` FOREIGN KEY (`location_place_id`) REFERENCES `place_name` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '历史事件表（用于时间轴）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------

-- ----------------------------
-- Table structure for geo_region
-- ----------------------------
DROP TABLE IF EXISTS `geo_region`;
CREATE TABLE `geo_region`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '疆域名称，如“唐代疆域（贞观）”',
  `dynasty_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属朝代ID',
  `period_start_year` int NULL DEFAULT NULL COMMENT '对应历史时期起始年份',
  `period_end_year` int NULL DEFAULT NULL COMMENT '对应历史时期结束年份',
  `geojson` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '存储 GeoJSON 数据',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述信息',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_geo_region_dynasty`(`dynasty_id` ASC) USING BTREE,
  CONSTRAINT `fk_geo_region_dynasty` FOREIGN KEY (`dynasty_id`) REFERENCES `dynasty` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '历史疆域/地理区域表（支持 GeoJSON）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of geo_region
-- ----------------------------

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名，如李隆基、秦始皇',
  `style_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字/号，如“仲尼”“子龙”',
  `dynasty_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属朝代ID，关联 dynasty.id',
  `birth_year` int NULL DEFAULT NULL COMMENT '出生年份（公元纪年，可为负数）',
  `death_year` int NULL DEFAULT NULL COMMENT '去世年份（公元纪年，可为负数）',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '人物简介、主要成就',
  `image_url` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人物头像或插图链接',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_person_name`(`name` ASC) USING BTREE,
  INDEX `fk_person_dynasty`(`dynasty_id` ASC) USING BTREE,
  CONSTRAINT `fk_person_dynasty` FOREIGN KEY (`dynasty_id`) REFERENCES `dynasty` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '历史人物表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for person_relation
-- ----------------------------
DROP TABLE IF EXISTS `person_relation`;
CREATE TABLE `person_relation`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `person_id` bigint UNSIGNED NOT NULL COMMENT '人物ID（主角）',
  `related_person_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '被关联人物ID（若在库中）',
  `related_person_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被关联人物名称（如不在库中）',
  `relation_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关系类型：父子/君臣/敌对/师承等',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '关系说明',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_pr_person`(`person_id` ASC) USING BTREE,
  INDEX `fk_pr_related_person`(`related_person_id` ASC) USING BTREE,
  CONSTRAINT `fk_pr_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_pr_related_person` FOREIGN KEY (`related_person_id`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人物关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person_relation
-- ----------------------------

-- ----------------------------
-- Table structure for place_name
-- ----------------------------
DROP TABLE IF EXISTS `place_name`;
CREATE TABLE `place_name`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ancient_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '古代地名，如“长安”“汴梁”',
  `modern_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '现代地名，如“西安”“开封”',
  `modern_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现代地理位置，如省市区或经纬度',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '地名相关描述或历史背景',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ancient_name`(`ancient_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '古今地名对照表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of place_name
-- ----------------------------

-- ----------------------------
-- Table structure for reading_record
-- ----------------------------
DROP TABLE IF EXISTS `reading_record`;
CREATE TABLE `reading_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `target_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '记录类型：person/event/dynasty/place/relic',
  `target_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '目标记录ID',
  `viewed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '查看时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_rr_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_rr_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户阅读记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reading_record
-- ----------------------------

-- ----------------------------
-- Table structure for relic
-- ----------------------------
DROP TABLE IF EXISTS `relic`;
CREATE TABLE `relic`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '遗迹名称，如兵马俑',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在地，如城市或景区名',
  `dynasty_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '相关朝代（可不填）',
  `related_event_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '相关历史事件ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '遗迹介绍',
  `coordinates` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经纬度坐标，如\"34.3,109.2\"',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_relic_dynasty`(`dynasty_id` ASC) USING BTREE,
  INDEX `fk_relic_event`(`related_event_id` ASC) USING BTREE,
  CONSTRAINT `fk_relic_dynasty` FOREIGN KEY (`dynasty_id`) REFERENCES `dynasty` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_relic_event` FOREIGN KEY (`related_event_id`) REFERENCES `event` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '历史遗迹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relic
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（BCrypt/MD5）',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$aESCh0YVM07HgAF2JFKKVuB8O.ZvjZg.c4CJ73wSiiGx4Eu/K6Ng2', 'admin@example.com', '2025-12-09 15:53:27');
INSERT INTO `user` VALUES (2, 'test', '$2a$10$PDUnMF.CqDoGduiWb.qeOuaqQbgv9njhIn77WKht.zLlRNlouLqky', 'test@test.com', '2025-12-10 18:21:47');

SET FOREIGN_KEY_CHECKS = 1;
