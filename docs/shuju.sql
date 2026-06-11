-- ============================================================
-- 小学英语词汇系统 - 单词数据初始化脚本
-- ============================================================
-- 基于《小学英语单词分类大全》整理
-- 共24个主题分类，约500+单词
-- ============================================================

USE `english_study`;

-- ============================================================
-- 1. 学习用品 (school things) - 对应 Unit 1
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('学习用品', '三年级上册', 17);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('pen', '/pen/', '钢笔', @unit_id),
('pencil', '/''pensl/', '铅笔', @unit_id),
('pencil-case', '/''penslkeis/', '铅笔盒', @unit_id),
('ruler', '/''ru:lə/', '尺子', @unit_id),
('book', '/buk/', '书', @unit_id),
('bag', '/bæg/', '包', @unit_id),
('post card', '/pəust ka:d/', '明信片', @unit_id),
('newspaper', '/''nju:z,peipə/', '报纸', @unit_id),
('schoolbag', '/''sku:lbæg/', '书包', @unit_id),
('eraser', '/i''reisə/', '橡皮', @unit_id),
('crayon', '/''kreiən/', '蜡笔', @unit_id),
('sharpener', '/''ʃɑ:pənə/', '卷笔刀', @unit_id),
('story-book', '/''stɔ:ri buk/', '故事书', @unit_id),
('notebook', '/''nəutbuk/', '笔记本', @unit_id),
('Chinese book', '/''tʃai''ni:z buk/', '语文书', @unit_id),
('English book', '/''iŋgliʃ buk/', '英语书', @unit_id),
('maths book', '/mæθs buk/', '数学书', @unit_id),
('magazine', '/,mægə''zi:n/', '杂志', @unit_id);

-- ============================================================
-- 2. 身体部位 (body) - 对应 Unit 2
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('身体部位', '三年级上册', 12);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('foot', '/fut/', '脚', @unit_id),
('head', '/hed/', '头', @unit_id),
('face', '/feis/', '脸', @unit_id),
('hair', '/heə/', '头发', @unit_id),
('nose', '/nəuz/', '鼻子', @unit_id),
('mouth', '/mauθ/', '嘴', @unit_id),
('eye', '/ai/', '眼睛', @unit_id),
('ear', '/iə/', '耳朵', @unit_id),
('arm', '/ɑ:m/', '手臂', @unit_id),
('hand', '/hænd/', '手', @unit_id),
('finger', '/''fiŋgə/', '手指', @unit_id),
('leg', '/leg/', '腿', @unit_id),
('tail', '/teil/', '尾巴', @unit_id);

-- ============================================================
-- 3. 颜色 (colours) - 对应 Unit 3
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('颜色', '三年级上册', 11);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('red', '/red/', '红', @unit_id),
('blue', '/blu:/', '蓝', @unit_id),
('yellow', '/''jeləu/', '黄', @unit_id),
('green', '/gri:n/', '绿', @unit_id),
('white', '/wait/', '白', @unit_id),
('black', '/blæk/', '黑', @unit_id),
('pink', '/piŋk/', '粉红', @unit_id),
('purple', '/''pə:pl/', '紫', @unit_id),
('orange', '/''ɔrindʒ/', '橙', @unit_id),
('brown', '/braun/', '棕', @unit_id);

-- ============================================================
-- 4. 动物 (animals) - 对应 Unit 4
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('动物', '三年级上册', 9);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('cat', '/kæt/', '猫', @unit_id),
('dog', '/dɔg/', '狗', @unit_id),
('pig', '/pig/', '猪', @unit_id),
('duck', '/dʌk/', '鸭', @unit_id),
('rabbit', '/''ræbit/', '兔子', @unit_id),
('horse', '/hɔ:s/', '马', @unit_id),
('elephant', '/''elifənt/', '大象', @unit_id),
('bird', '/bə:d/', '鸟', @unit_id),
('mouse', '/maus/', '老鼠', @unit_id);

-- ============================================================
-- 5. 人物 (people) - 对应 Unit 5
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('人物', '三年级下册', 18);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('friend', '/frend/', '朋友', @unit_id),
('boy', '/bɔi/', '男孩', @unit_id),
('girl', '/gə:l/', '女孩', @unit_id),
('mother', '/''mʌðə/', '母亲', @unit_id),
('father', '/''fɑ:ðə/', '父亲', @unit_id),
('sister', '/''sistə/', '姐妹', @unit_id),
('brother', '/''brʌðə/', '兄弟', @unit_id),
('uncle', '/''ʌŋkl/', '叔叔', @unit_id),
('man', '/mæn/', '男人', @unit_id),
('woman', '/''wumən/', '女人', @unit_id),
('Mr', '/''mistə/', '先生', @unit_id),
('Miss', '/mis/', '小姐', @unit_id),
('lady', '/''leidi/', '女士', @unit_id),
('mom', '/mɔm/', '妈妈', @unit_id),
('dad', '/dæd/', '爸爸', @unit_id),
('parents', '/''peərənts/', '父母', @unit_id),
('aunt', '/ɑ:nt/', '阿姨', @unit_id),
('cousin', '/''kʌzn/', '堂兄妹', @unit_id),
('son', '/sʌn/', '儿子', @unit_id),
('baby', '/''beibi/', '婴儿', @unit_id),
('kid', '/kid/', '小孩', @unit_id),
('classmate', '/''klɑ:smeit/', '同学', @unit_id),
('queen', '/''kwi:n/', '女王', @unit_id),
('visitor', '/''vizitə/', '参观者', @unit_id),
('neighbour', '/''neibə/', '邻居', @unit_id);

-- ============================================================
-- 6. 食品 (food and drink) - 对应 Unit 6
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('食品', '三年级下册', 17);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('breakfast', '/''brekfəst/', '早餐', @unit_id),
('lunch', '/lʌntʃ/', '中餐', @unit_id),
('dinner', '/''dinə/', '晚餐', @unit_id),
('egg', '/eg/', '鸡蛋', @unit_id),
('rice', '/rais/', '米饭', @unit_id),
('cake', '/keik/', '蛋糕', @unit_id),
('bread', '/bred/', '面包', @unit_id),
('jam', '/dʒæm/', '果酱', @unit_id),
('biscuit', '/''biskit/', '饼干', @unit_id),
('sausage', '/''sɔsidʒ/', '香肠', @unit_id),
('sandwich', '/''sændwitʃ/', '三明治', @unit_id),
('dumplings', '/''dʌmpliŋ/', '饺子', @unit_id),
('French fries', '/frentʃ fraiz/', '薯条', @unit_id),
('meat', '/mi:t/', '肉', @unit_id),
('chicken', '/''tʃikin/', '鸡肉', @unit_id),
('mutton', '/''mʌtn/', '羊肉', @unit_id),
('beef', '/bi:f/', '牛肉', @unit_id),
('pork', '/pɔ:k/', '猪肉', @unit_id),
('fish', '/fiʃ/', '鱼', @unit_id);

-- ============================================================
-- 7. 水果 & 蔬菜 - 对应 Unit 7
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('水果蔬菜', '三年级下册', 20);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('grape', '/greip/', '葡萄', @unit_id),
('cherry', '/''tʃeri/', '樱桃', @unit_id),
('lemon', '/''lemən/', '柠檬', @unit_id),
('mango', '/''mæŋgəu/', '芒果', @unit_id),
('coconut', '/''kəukənʌt/', '椰子', @unit_id),
('peach', '/pi:tʃ/', '桃', @unit_id),
('strawberry', '/''strɔ:bəri/', '草莓', @unit_id),
('vegetable', '/''vedʒitəbl/', '蔬菜', @unit_id),
('eggplant', '/''egplɑ:nt/', '茄子', @unit_id),
('green beans', '/gri:n bi:nz/', '青豆', @unit_id),
('tomato', '/tə''meitəu/', '西红柿', @unit_id),
('potato', '/pə''teitəu/', '土豆', @unit_id),
('cucumber', '/''kju:kʌmbə/', '黄瓜', @unit_id),
('onion', '/''ʌnjən/', '洋葱', @unit_id),
('pea', '/pi:/', '豌豆', @unit_id),
('carrot', '/''kærət/', '胡萝卜', @unit_id),
('cabbage', '/''kæbidʒ/', '卷心菜', @unit_id),
('pumpkin', '/''pʌmpkin/', '南瓜', @unit_id),
('sweet potato', '/swi:t pə''teitəu/', '红薯', @unit_id);

-- ============================================================
-- 8. 衣服 (clothes) - 对应 Unit 8
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('衣服', '四年级上册', 16);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('jacket', '/''dʒækit/', '夹克衫', @unit_id),
('shirt', '/ʃə:t/', '衬衫', @unit_id),
('T-shirt', '/''ti:ʃə:t/', 'T恤衫', @unit_id),
('skirt', '/skə:t/', '短裙子', @unit_id),
('dress', '/dres/', '连衣裙', @unit_id),
('jeans', '/dʒi:nz/', '牛仔裤', @unit_id),
('pants', '/pænts/', '裤子', @unit_id),
('trousers', '/''trauzəz/', '裤子', @unit_id),
('socks', '/sɔks/', '袜子', @unit_id),
('shoes', '/ʃu:z/', '鞋子', @unit_id),
('sweater', '/''swetə/', '毛衣', @unit_id),
('coat', '/kəut/', '外套', @unit_id),
('raincoat', '/''reinkəut/', '雨衣', @unit_id),
('shorts', '/ʃɔ:ts/', '短裤', @unit_id),
('sandals', '/''sændl/', '凉鞋', @unit_id),
('boots', '/bu:ts/', '靴子', @unit_id),
('hat', '/hæt/', '有边帽', @unit_id),
('cap', '/kæp/', '无边帽', @unit_id),
('tie', '/tai/', '领带', @unit_id),
('sunglasses', '/''sʌnglɑ:siz/', '太阳镜', @unit_id),
('scarf', '/skɑ:f/', '围巾', @unit_id),
('gloves', '/glʌvz/', '手套', @unit_id);

-- ============================================================
-- 9. 交通工具 (vehicles) - 对应 Unit 9
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('交通工具', '四年级上册', 11);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('bike', '/baik/', '自行车', @unit_id),
('bus', '/bʌs/', '公共汽车', @unit_id),
('train', '/trein/', '火车', @unit_id),
('boat', '/bəut/', '小船', @unit_id),
('ship', '/ʃip/', '轮船', @unit_id),
('yacht', '/jɔt/', '快艇', @unit_id),
('car', '/kɑ:/', '小汽车', @unit_id),
('taxi', '/''tæksi/', '出租车', @unit_id),
('jeep', '/dʒi:p/', '吉普车', @unit_id),
('van', '/væn/', '小货车', @unit_id),
('plane', '/plein/', '飞机', @unit_id),
('subway', '/''sʌbwei/', '地铁', @unit_id),
('motor cycle', '/''məutə ''saikl/', '摩托车', @unit_id);

-- ============================================================
-- 10. 杂物/物品 (other things) - 对应 Unit 10
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('日常物品', '四年级上册', 18);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('window', '/''windəu/', '窗户', @unit_id),
('door', '/dɔ:/', '门', @unit_id),
('desk', '/desk/', '课桌', @unit_id),
('chair', '/tʃeə/', '椅子', @unit_id),
('bed', '/bed/', '床', @unit_id),
('computer', '/kəm''pju:tə/', '计算机', @unit_id),
('board', '/bɔ:d/', '写字板', @unit_id),
('fan', '/fæn/', '风扇', @unit_id),
('light', '/lait/', '灯', @unit_id),
('mirror', '/''mirə/', '镜子', @unit_id),
('teacher''s desk', '/''ti:tʃəz desk/', '讲台', @unit_id),
('picture', '/''piktʃə/', '图画', @unit_id),
('photo', '/''fəutəu/', '照片', @unit_id),
('wall', '/wɔ:l/', '墙壁', @unit_id),
('floor', '/flɔ:/', '地板', @unit_id),
('football', '/''futbɔ:l/', '足球', @unit_id),
('present', '/''preznt/', '礼物', @unit_id),
('gift', '/gift/', '礼物', @unit_id),
('ball', '/bɔ:l/', '球', @unit_id),
('balloon', '/bə''lu:n/', '气球', @unit_id),
('kite', '/kait/', '风筝', @unit_id),
('jigsaw puzzle', '/''dʒɪgsɔ: ''pʌzl/', '拼图游戏', @unit_id),
('box', '/bɔks/', '盒子', @unit_id),
('umbrella', '/ʌm''brelə/', '伞', @unit_id),
('zipper', '/''zipə/', '拉链', @unit_id),
('violin', '/,vaiə''lin/', '小提琴', @unit_id),
('nest', '/nest/', '鸟窝', @unit_id),
('hole', '/həul/', '洞', @unit_id),
('toothbrush', '/''tu:θbrʌʃ/', '牙刷', @unit_id),
('menu', '/''menju:/', '菜单', @unit_id),
('e-card', '/kɑ:d/', '电子卡片', @unit_id),
('e-mail', '/meil/', '电子邮件', @unit_id),
('money', '/''mʌni/', '钱', @unit_id),
('traffic light', '/''træfik lait/', '交通灯', @unit_id),
('medicine', '/''medisin/', '药', @unit_id);

-- ============================================================
-- 11. 地点 (locations) - 对应 Unit 11
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('地点', '四年级下册', 14);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('home', '/həum/', '家', @unit_id),
('room', '/ru:m/', '房间', @unit_id),
('bedroom', '/''bedrum/', '卧室', @unit_id),
('bathroom', '/''bæθrum/', '卫生间', @unit_id),
('livingroom', '/''liviŋrum/', '起居室', @unit_id),
('kitchen', '/''kitʃin/', '厨房', @unit_id),
('classroom', '/''klɑ:srum/', '教室', @unit_id),
('school', '/sku:l/', '学校', @unit_id),
('park', '/pɑ:k/', '公园', @unit_id),
('library', '/''laibrəri/', '图书馆', @unit_id),
('postoffice', '/pəust''ɔfis/', '邮政局', @unit_id),
('hospital', '/''hɔspitl/', '医院', @unit_id),
('cinema', '/''sinimə/', '电影院', @unit_id),
('bookstore', '/''bukstɔ:/', '书店', @unit_id),
('farm', '/fɑ:m/', '农场', @unit_id),
('zoo', '/zu:/', '动物园', @unit_id),
('garden', '/''gɑ:dn/', '花园', @unit_id),
('playground', '/''pleigraund/', '操场', @unit_id);

-- ============================================================
-- 12. 气象 (weather) - 对应 Unit 12
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('气象', '四年级下册', 9);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('cold', '/kəuld/', '冷的', @unit_id),
('hot', '/hɔt/', '热的', @unit_id),
('warm', '/wɔ:m/', '温暖的', @unit_id),
('cool', '/ku:l/', '凉爽的', @unit_id),
('snowy', '/snəui/', '下雪的', @unit_id),
('sunny', '/''sʌni/', '晴朗的', @unit_id),
('rainy', '/''reini/', '下雨的', @unit_id),
('windy', '/''windi/', '刮风的', @unit_id),
('cloudy', '/''klaudi/', '多云的', @unit_id),
('weather report', '/''weðə ri''pɔ:t/', '天气预报', @unit_id);

-- ============================================================
-- 13. 植物 (plants) - 对应 Unit 13
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('植物', '四年级下册', 6);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('flower', '/''flauə/', '花', @unit_id),
('grass', '/grɑ:s/', '草', @unit_id),
('tree', '/tri:/', '树', @unit_id),
('seed', '/si:d/', '种子', @unit_id),
('leaf', '/li:f/', '树叶', @unit_id),
('plant', '/plɑ:nt/', '植物', @unit_id),
('rose', '/rəuz/', '玫瑰', @unit_id);

-- ============================================================
-- 14. 景物 (nature) - 对应 Unit 14
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('自然景物', '五年级上册', 15);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('river', '/''rivə/', '河流', @unit_id),
('lake', '/leik/', '湖泊', @unit_id),
('stream', '/stri:m/', '小溪', @unit_id),
('forest', '/''fɔrist/', '森林', @unit_id),
('path', '/pɑ:θ/', '小路', @unit_id),
('road', '/rəud/', '马路', @unit_id),
('house', '/haus/', '房子', @unit_id),
('bridge', '/bridʒ/', '桥', @unit_id),
('building', '/''bildiŋ/', '建筑物', @unit_id),
('rain', '/rein/', '雨', @unit_id),
('cloud', '/klaud/', '云', @unit_id),
('sun', '/sʌn/', '太阳', @unit_id),
('mountain', '/''mauntin/', '大山', @unit_id),
('sky', '/skai/', '天空', @unit_id),
('rainbow', '/''reinbəu/', '彩虹', @unit_id),
('wind', '/wind/', '风', @unit_id),
('air', '/eə/', '空气', @unit_id);

-- ============================================================
-- 15. 星期 (week) - 对应 Unit 15
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('星期', '五年级上册', 7);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('Monday', '/''mʌndei/', '星期一', @unit_id),
('Tuesday', '/''tju:zdi/', '星期二', @unit_id),
('Wednesday', '/''wenzdi/', '星期三', @unit_id),
('Thursday', '/''θə:zdi/', '星期四', @unit_id),
('Friday', '/''fraidi/', '星期五', @unit_id),
('Saturday', '/''sætədi/', '星期六', @unit_id),
('Sunday', '/''sʌndi/', '星期天', @unit_id),
('weekend', '/''wi:k''end/', '周末', @unit_id);

-- ============================================================
-- 16. 月份 (months) - 对应 Unit 16
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('月份', '五年级上册', 12);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('January', '/''dʒænjuəri/', '一月', @unit_id),
('February', '/''februəri/', '二月', @unit_id),
('March', '/mɑ:tʃ/', '三月', @unit_id),
('April', '/''eiprəl/', '四月', @unit_id),
('May', '/mei/', '五月', @unit_id),
('June', '/dʒu:n/', '六月', @unit_id),
('July', '/dʒu:''lai/', '七月', @unit_id),
('August', '/ɔ:''gʌst/', '八月', @unit_id),
('September', '/sep''tembə/', '九月', @unit_id),
('October', '/ɔk''təubə/', '十月', @unit_id),
('November', '/nəu''vembə/', '十一月', @unit_id),
('December', '/di''sembə/', '十二月', @unit_id);

-- ============================================================
-- 17. 季节 (seasons) - 对应 Unit 17
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('季节', '五年级上册', 4);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('spring', '/spriŋ/', '春天', @unit_id),
('summer', '/''sʌmə/', '夏天', @unit_id),
('fall', '/fɔ:l/', '秋天', @unit_id),
('autumn', '/''ɔ:təm/', '秋天', @unit_id),
('winter', '/''wintə/', '冬天', @unit_id);

-- ============================================================
-- 18. 方位 (directions) - 对应 Unit 18
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('方位', '五年级下册', 6);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('south', '/sauθ/', '南', @unit_id),
('north', '/nɔ:θ/', '北', @unit_id),
('east', '/i:st/', '东', @unit_id),
('west', '/west/', '西', @unit_id),
('left', '/left/', '左', @unit_id),
('right', '/rait/', '右', @unit_id);

-- ============================================================
-- 19. 患病 (illness) - 对应 Unit 19
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('患病', '五年级下册', 6);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('have a fever', '/''fi:və/', '发高烧', @unit_id),
('hurt', '/hə:t/', '疼痛', @unit_id),
('have a cold', '/kəuld/', '伤风', @unit_id),
('have a toothache', '/''tu:θeik/', '牙疼', @unit_id),
('have a headache', '/''hedeik/', '头疼', @unit_id),
('have a sore throat', '/sɔ: θrəut/', '喉咙疼', @unit_id),
('have a stomachache', '/''stʌməkeik/', '胃痛', @unit_id);

-- ============================================================
-- 20. 数词 (numbers) - 对应 Unit 20
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('数词', '五年级下册', 31);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `definition`, `unit_id`) VALUES
('one', '一', @unit_id),
('two', '二', @unit_id),
('three', '三', @unit_id),
('four', '四', @unit_id),
('five', '五', @unit_id),
('six', '六', @unit_id),
('seven', '七', @unit_id),
('eight', '八', @unit_id),
('nine', '九', @unit_id),
('ten', '十', @unit_id),
('eleven', '十一', @unit_id),
('twelve', '十二', @unit_id),
('thirteen', '十三', @unit_id),
('fourteen', '十四', @unit_id),
('fifteen', '十五', @unit_id),
('sixteen', '十六', @unit_id),
('seventeen', '十七', @unit_id),
('eighteen', '十八', @unit_id),
('nineteen', '十九', @unit_id),
('twenty', '二十', @unit_id),
('thirty', '三十', @unit_id),
('forty', '四十', @unit_id),
('fifty', '五十', @unit_id),
('sixty', '六十', @unit_id),
('seventy', '七十', @unit_id),
('eighty', '八十', @unit_id),
('ninety', '九十', @unit_id),
('one hundred', '一百', @unit_id),
('first', '第一', @unit_id),
('second', '第二', @unit_id),
('third', '第三', @unit_id),
('fourth', '第四', @unit_id),
('fifth', '第五', @unit_id),
('sixth', '第六', @unit_id),
('seventh', '第七', @unit_id),
('eighth', '第八', @unit_id),
('ninth', '第九', @unit_id),
('tenth', '第十', @unit_id),
('eleventh', '第十一', @unit_id),
('twelfth', '第十二', @unit_id);

-- ============================================================
-- 21. 形容词 (adjectives) - 对应 Unit 21
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('形容词', '六年级上册', 30);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('big', '/big/', '大的', @unit_id),
('small', '/smɔ:l/', '小的', @unit_id),
('long', '/lɔŋ/', '长的', @unit_id),
('tall', '/tɔ:l/', '高的', @unit_id),
('short', '/ʃɔ:t/', '短的', @unit_id),
('young', '/jʌŋ/', '年轻的', @unit_id),
('old', '/əuld/', '老的', @unit_id),
('strong', '/strɔŋ/', '强壮的', @unit_id),
('thin', '/θin/', '瘦的', @unit_id),
('active', '/''æktiv/', '积极的', @unit_id),
('quiet', '/''kwaiət/', '安静的', @unit_id),
('nice', '/nais/', '好的', @unit_id),
('kind', '/kaind/', '善良的', @unit_id),
('strict', '/strikt/', '严格的', @unit_id),
('smart', '/smɑ:t/', '聪明的', @unit_id),
('funny', '/''fʌni/', '滑稽的', @unit_id),
('sweet', '/swi:t/', '甜的', @unit_id),
('salty', '/''sɔ:lti/', '咸的', @unit_id),
('sour', '/''sauə/', '酸的', @unit_id),
('fresh', '/freʃ/', '新鲜的', @unit_id),
('better', '/''betə/', '更好的', @unit_id),
('higher', '/''haiə/', '更高的', @unit_id),
('fine', '/fain/', '好的', @unit_id),
('great', '/greit/', '棒的', @unit_id),
('heavy', '/''hevi/', '重的', @unit_id),
('new', '/nju:/', '新的', @unit_id),
('fat', '/fæt/', '胖的', @unit_id),
('right', '/rait/', '对的', @unit_id),
('hungry', '/''hʌŋgri/', '饿的', @unit_id),
('cute', '/kju:t/', '可爱的', @unit_id),
('little', '/''litl/', '小的', @unit_id),
('lovely', '/''lʌvli/', '可爱的', @unit_id),
('beautiful', '/''bju:təfəl/', '漂亮的', @unit_id),
('colourful', '/''kʌləful/', '五颜六色的', @unit_id),
('pretty', '/''priti/', '漂亮的', @unit_id),
('cheap', '/tʃi:p/', '便宜的', @unit_id),
('expensive', '/iks''pensiv/', '贵的', @unit_id),
('juicy', '/''dʒu:si/', '有汁的', @unit_id),
('healthy', '/''helθi/', '健康的', @unit_id),
('helpful', '/''helpfəl/', '有帮助的', @unit_id);

-- ============================================================
-- 22. 介词 (prepositions) - 对应 Unit 22
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('介词', '六年级上册', 8);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('in', '/in/', '在...里面', @unit_id),
('on', '/ɔn/', '在...上面', @unit_id),
('under', '/''ʌndə/', '在...下面', @unit_id),
('near', '/niə/', '在...附近', @unit_id),
('behind', '/bi''haind/', '在...后面', @unit_id),
('next to', '/nekst tu:/', '在...旁边', @unit_id),
('over', '/''əuvə/', '悬在...上面', @unit_id),
('in front of', '/in frʌnt ɔv/', '在...前面', @unit_id);

-- ============================================================
-- 23. 代词 (pronouns) - 对应 Unit 23
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('代词', '六年级上册', 12);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('I', '/ai/', '我', @unit_id),
('we', '/wi:/', '我们', @unit_id),
('you', '/ju:/', '你，你们', @unit_id),
('he', '/hi:/', '他', @unit_id),
('she', '/ʃi:/', '她', @unit_id),
('it', '/it/', '它', @unit_id),
('they', '/ðei/', '他们', @unit_id),
('my', '/mai/', '我的', @unit_id),
('our', '/auə/', '我们的', @unit_id),
('your', '/juə/', '你的，你们的', @unit_id),
('his', '/hiz/', '他的', @unit_id),
('her', '/hə:/', '她的', @unit_id);

-- ============================================================
-- 24. 动词 (verbs) - 对应 Unit 24
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('动词', '六年级下册', 55);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('play', '/plei/', '玩', @unit_id),
('swim', '/swim/', '游泳', @unit_id),
('skate', '/skeit/', '溜冰', @unit_id),
('fly', '/flai/', '飞', @unit_id),
('jump', '/dʒʌmp/', '跳', @unit_id),
('walk', '/wɔ:k/', '走', @unit_id),
('run', '/rʌn/', '跑', @unit_id),
('climb', '/klaim/', '爬', @unit_id),
('fight', '/fait/', '打架', @unit_id),
('swing', '/swiŋ/', '荡秋千', @unit_id),
('eat', '/i:t/', '吃', @unit_id),
('sleep', '/sli:p/', '睡觉', @unit_id),
('like', '/laik/', '喜欢', @unit_id),
('have', '/hæv/', '有', @unit_id),
('buy', '/bai/', '买', @unit_id),
('take', '/teik/', '拍（照），带', @unit_id),
('live', '/liv/', '居住', @unit_id),
('teach', '/ti:tʃ/', '教', @unit_id),
('go', '/gəu/', '去', @unit_id),
('learn', '/lə:n/', '学习', @unit_id),
('sing', '/siŋ/', '唱歌', @unit_id),
('dance', '/dɑ:ns/', '跳舞', @unit_id),
('row', '/rəu/', '划', @unit_id),
('read books', '/ri:d buks/', '读书', @unit_id),
('do homework', '/du: ''həumwə:k/', '做作业', @unit_id),
('watch TV', '/wɔtʃ ti: vi:/', '看电视', @unit_id),
('cook the meals', '/kuk ðə mi:lz/', '烧菜', @unit_id),
('water the flowers', '/''wɔ:tə ðə ''flauəz/', '浇花', @unit_id),
('sweep the floor', '/swi:p ðə flɔ:/', '拖地', @unit_id),
('clean the bedroom', '/kli:n ðə ''bedrum/', '打扫房间', @unit_id),
('make the bed', '/meik ðə bed/', '铺床叠被', @unit_id),
('set the table', '/set ðə ''teibl/', '摆餐桌', @unit_id),
('wash the clothes', '/wɔʃ ðə kləuðz/', '洗衣服', @unit_id),
('wash the dishes', '/wɔʃ ðə diʃiz/', '洗盘子', @unit_id),
('use a computer', '/ju:z ə kəm''pju:tə/', '用电脑', @unit_id),
('do morning exercises', '/du: ''mɔ:niŋ ''eksəsaiziz/', '做早操', @unit_id),
('eat breakfast', '/i:t ''brekfəst/', '吃早餐', @unit_id),
('eat dinner', '/i:t ''dinə/', '吃晚饭', @unit_id),
('go to school', '/gəu tu: sku:l/', '去上学', @unit_id),
('have English class', '/hæv ''iŋgliʃ klɑ:s/', '上英语课', @unit_id),
('play sports', '/plei spɔ:ts/', '体育运动', @unit_id),
('get up', '/get ʌp/', '起床', @unit_id),
('climb mountains', '/klaim ''mauntins/', '爬山', @unit_id),
('go shopping', '/gəu ''ʃɔpiŋ/', '去购物', @unit_id),
('play the piano', '/plei ðə pi''ænəu/', '弹钢琴', @unit_id),
('visit grandparents', '/''vizit ''grændpeərənts/', '拜访祖父母', @unit_id),
('go hiking', '/gəu haikiŋ/', '去远足', @unit_id),
('fly kites', '/flai kaits/', '放风筝', @unit_id),
('make a snowman', '/meik ə ''snəumæn/', '堆雪人', @unit_id),
('plant trees', '/plɑ:nt tri:z/', '植树', @unit_id),
('draw pictures', '/drɔ: ''piktʃəz/', '画画', @unit_id),
('cook dinner', '/kuk ''dinə/', '烧晚饭', @unit_id),
('answer the phone', '/''ɑ:nsə ðə fəun/', '接电话', @unit_id),
('listen to music', '/''lisn tu: ''mju:zik/', '听音乐', @unit_id),
('clean the room', '/kli:n ðə ru:m/', '打扫房间', @unit_id),
('write a letter', '/rait ə ''letə/', '写信', @unit_id),
('write an e-mail', '/rait ən i:meil/', '写电子邮件', @unit_id),
('drink water', '/driŋk ''wɔ:tə/', '喝水', @unit_id);

-- ============================================================
-- 25. 疑问词 (question words) - 对应 Unit 25
-- ============================================================
INSERT INTO `unit` (`name`, `grade`, `word_count`) VALUES ('疑问词', '六年级下册', 14);
SET @unit_id = LAST_INSERT_ID();

INSERT INTO `word` (`word`, `phonetic`, `definition`, `unit_id`) VALUES
('what', '/wɔt/', '什么', @unit_id),
('what colour', '/wɔt ''kʌlə/', '什么颜色', @unit_id),
('what time', '/wɔt taim/', '几点', @unit_id),
('what day', '/wɔt dei/', '星期几', @unit_id),
('how', '/hau/', '怎样', @unit_id),
('how old', '/hau əuld/', '年龄多大', @unit_id),
('how many', '/hau ''meni/', '多少', @unit_id),
('how much', '/hau mʌtʃ/', '多少钱', @unit_id),
('how tall', '/hau tɔ:l/', '多高', @unit_id),
('how heavy', '/hau ''hevi/', '多重', @unit_id),
('how long', '/hau lɔŋ/', '多长', @unit_id),
('how big', '/hau big/', '多大', @unit_id),
('who', '/hu:/', '谁', @unit_id),
('when', '/hwen/', '什么时候', @unit_id),
('whose', '/hu:z/', '谁的', @unit_id),
('where', '/hweə/', '在哪里', @unit_id),
('why', '/hwai/', '为什么', @unit_id),
('which', '/hwitʃ/', '哪一个', @unit_id);

-- ============================================================
-- 数据统计
-- ============================================================
SELECT '数据初始化完成！' AS message;
SELECT COUNT(*) AS total_units FROM `unit`;
SELECT COUNT(*) AS total_words FROM `word`;