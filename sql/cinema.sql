-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'春季外卖节','春季外卖节','2019-04-23 17:55:59',5,'2019-04-20 17:55:59'),(3,'春季外卖节','春季外卖节','2019-04-23 17:55:59',6,'2019-04-20 17:55:59'),(4,'测试活动','测试活动','2019-04-26 16:00:00',8,'2019-04-20 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (2,10),(2,11),(2,16),(4,10);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chargerecord`
--

DROP TABLE IF EXISTS `chargerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chargerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `chargetime` timestamp NOT NULL,
  `amount` double NOT NULL,
  `VIPActivity` varchar(255) DEFAULT NULL,
  `given` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chargerecord`
--

LOCK TABLES `chargerecord` WRITE;
/*!40000 ALTER TABLE `chargerecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `chargerecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumerecord`
--

DROP TABLE IF EXISTS `consumerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consumerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `amount` double NOT NULL,
  `consumetime` timestamp NOT NULL,
  `way` int(11) NOT NULL,
  `seat` varchar(255) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumerecord`
--

LOCK TABLES `consumerecord` WRITE;
/*!40000 ALTER TABLE `consumerecord` DISABLE KEYS */;
INSERT INTO `consumerecord` VALUES (1,3,2,'2020-10-28 07:07:00',0,'2张 3排3座 3排4座 ',69),(2,3,4,'2020-10-28 07:11:16',0,'4张 6排11座 6排12座 5排11座 5排12座 ',69),(3,3,2,'2020-10-30 05:55:30',0,'2张 2排4座 4排4座 ',69),(4,3,2,'2020-10-30 05:59:18',0,'2张 4排7座 5排8座 ',69),(5,3,2,'2020-10-30 06:00:08',0,'2张 4排7座 5排8座 ',69),(6,8,2,'2020-10-30 06:27:28',0,'2张 2排8座 2排9座 ',69),(7,8,2,'2020-10-30 06:30:02',0,'2张 3排8座 3排9座 ',69),(8,3,1,'2020-10-30 06:36:17',0,'1张 4排5座 ',69),(9,3,1,'2020-10-30 06:36:38',0,'1张 1排8座 ',69),(10,3,1,'2020-10-30 06:36:58',0,'1张 5排1座 ',69),(11,3,2,'2020-10-30 06:51:15',0,'2张 3排2座 3排11座 ',69),(12,3,2,'2020-10-30 07:06:42',0,'2张 3排2座 3排11座 ',69),(13,3,1,'2020-10-30 07:07:23',0,'1张 1排3座 ',69),(14,3,1,'2020-10-30 07:09:50',0,'1张 4排6座 ',69),(15,3,1,'2020-10-30 07:10:09',0,'1张 1排6座 ',69),(16,3,1,'2020-10-30 07:10:54',0,'1张 1排10座 ',69),(17,3,1,'2020-10-30 07:24:29',0,'1张 2排12座 ',69);
/*!40000 ALTER TABLE `consumerecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',20,5,'2019-04-20 17:47:54','2019-04-23 17:47:59'),(5,'测试优惠券','品质联盟',30,4,'2019-04-20 21:14:46','2019-04-24 21:14:51'),(6,'春节电影节优惠券','电影节优惠券',50,10,'2019-04-20 21:15:11','2019-04-21 21:14:56'),(8,'测试优惠券','123',100,99,'2019-04-20 16:00:00','2019-04-26 16:00:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (8,15),(5,15),(8,15),(6,15),(5,15),(8,15),(6,15),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,8),(8,8),(6,8),(5,8),(8,8),(6,8),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3),(5,3),(8,3),(6,3);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `column` int(11) DEFAULT NULL,
  `row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'1号厅',10,5),(2,'2号厅',12,8);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (10,'https://p1.meituan.net/moviemachine/5cbf9a626b7ed27c96ca3c748655b3ec2550103.jpg@464w_644h_1e_1c','管虎/郭帆/路阳/田羽生','管虎',' 张译 / 吴京 / 李九霄 / 魏晨 / 邓超 / 欧豪 / 邱天 / 周思羽 / 刘显达 / 石昊正','战争','中国大陆','汉语普通话 / 英语',122,'2020-11-02 07:36:18','金刚川','1953年，抗美援朝战争进入最终阶段，志愿军在金城发动最后一场大型战役。为在指定时间到达，向 金城前线投放更多战力，志愿军战士们在物资匮乏、武装悬殊的情况下，不断抵御敌机狂轰滥炸，以血肉 之躯一次次修补战火中的木桥。一段鲜为人知的历史，在暗流涌动的金刚川上徐徐展开......',0),(11,'https://p0.meituan.net/movie/34ffd6e8f0119b362b5862521c296ecd5010552.jpg@464w_644h_1e_1c','田口智久','大和屋晓','花江夏树 / 细谷佳正 / 三森铃子 / 田村睦心 / 吉田仁美 / 池田纯矢 / 榎木淳弥 / 市道真央 / 坂本千夏 / 山口真弓 / 重松花鸟 / 山田木野子 / 竹内顺子 / 樱井孝宏 / 松本美和 / 德光由禾 / 松冈茉优 / 小野大辅','动画 / 冒险','日本','日语',94,'2020-10-30 14:55:31','数码宝贝：最后的进化','2010年，曾经的小学生们已经步入大学，太一、阿和等人步上不同的人生道路（八神太一和石田大和将会成长为22岁），而就在此时，数码宝贝的研究者メノア(松冈茉优 配音)却告诉他们：“被选召的孩子们”长大成人后，数码宝贝也将随之消失…。预告中字幕表明这是“太一他们最后的故事” ，结尾则注明：献给所有和数码宝贝一起成长的“孩子”们，还有亚古兽注视着长大成人的太一的背影，“太一，你长大了呢”。',0),(12,'https://p0.meituan.net/movie/3b15b45b97c8b134e5b9cce8492231613207907.jpg@464w_644h_1e_1c','张林子','赵默言','关晓彤 / 黄景瑜 / 官鸿 / 卢杉 / 潘一飞 / 昌隆 / 杨冬麒 / 范明 / 赵英俊 / 罗家英 / 嘉泽','喜剧 / 爱情 / 奇幻','中国大陆','汉语普通话',104,'2020-11-02 08:58:42','月半爱丽丝','胖女孩林晓曦（关晓彤 饰）性格开朗大方，她和音乐制作人韩冰（黄景瑜 饰）是多年好友，两人友达以上，恋爱未满。在一次奇幻的经历后，林晓曦出人意料地变身成为美丽的爱丽丝，并得以接近自己的偶像黄可（官鸿 饰）。虽然赢得了偶像的关注，但夹在青梅竹马与偶像之间时，林晓曦渐渐明白了什么……',0),(13,'https://p1.meituan.net/movie/c16b0a68f95d884d428f339f8eacce834410200.jpg@464w_644h_1e_1c','许宏宇','张冀 / 周运海','刘昊然 / 彭昱畅 / 尹昉 / 谭卓 / 张琪 / 李佳琦','剧情 / 喜剧','中国大陆','汉语普通话',97,'2020-10-04 14:52:31','一点就到家','魏晋北（刘昊然 饰）、彭秀兵（彭昱畅 饰）、李绍群（尹昉 饰）三个性格迥异的年轻人从大城市回到云南千年古寨，机缘巧合下共同创业，与古寨“格格不入”的他们用真诚改变了所有人，开启了一段非常疯狂、纯真的梦想之旅。',0),(14,'https://p0.meituan.net/movie/f2cb257de1849f71308c55e42a318401298312.jpg@464w_644h_1e_1c','李霄峰','余欣','章宇 / 宋佳 / 王砚辉 / 李鸿其 / 邓恩熙 / 周政杰 / 陈瑾 / 张建亚 / 叶青 / 高宇航 / 赵龙豪 / 丁冠中 / 林津锋','剧情 / 犯罪','中国大陆','汉语普通话',119,'2020-11-02 08:54:58','风平浪静','　　十五年前的一次意外，成绩优异高中生宋浩离开了家乡，也离开了原本的人生轨迹。\r\n　　十五年后，他回到家乡，一切仿佛都已风平浪静。与老同学潘晓霜的重逢，给了他人生久违的光亮。他决定揭开那个十五年来不敢触碰的伤疤，重新掌控人生，完成一场似乎不可能的自我救赎。',0),(15,'https://p0.meituan.net/movie/35a2550fdf6ca5088087a685e9ec8c791613927.jpg@464w_644h_1e_1c','王丹阳','王丹阳 / 梁璐璐','郭采洁 / 张国柱 / 高仁 / 李彦漫 / 曹恩齐 / 高姝瑶 / 黎燕珊 / 赵雪芹','剧情 / 爱情','中国大陆','汉语普通话',112,'2020-10-16 15:00:24','喜宝','影片改编自亦舒同名原著小说， 讲述了家境贫寒的姜喜宝（郭采洁饰），突逢巨变，生活面临种种困境。喜宝一筹莫展之际，却偶然间结识了单纯可爱的富家千金勖聪慧（李彦漫饰），并因此结识了她的父亲：一个单身多年的富商父亲勖存姿（张国柱饰）。\r\n　　无路可走的喜宝接受了勖存姿对于她学费和生活的资助，随着喜宝与勖存姿的相处，她发现自己慢慢地爱上了勖存姿。然而更多的质疑和阻碍正悄然发生……',0),(16,'https://p0.meituan.net/movie/f1e7cecb6270e1c1fe074d02ad62b8ad2344393.jpg@464w_644h_1e_1c','阿方索·卡隆','史蒂夫·克洛夫斯 / J·K·罗琳','丹尼尔·雷德克里夫 / 艾玛·沃森 / 鲁伯特·格林特 / 加里·奥德曼 / 朱丽·沃特斯 / 邦妮·怀特 / 大卫·休里斯 / 迈克尔·刚本 / 艾伦·瑞克曼 / 玛吉·史密斯 / 汤姆·费尔顿 / 艾玛·汤普森 / 朱莉·克里斯蒂 / 蒂莫西·斯波','奇幻 / 冒险','英国 / 美国','英语',141,'2020-11-02 08:58:42','哈利·波特与阿兹卡班的囚徒','　　哈利（丹尼尔·雷德克里夫）即将在霍格沃兹渡过第三个年头，此时在阿兹塔班却传出恶棍小天狼星（加里·奥德曼）越狱的消息。据说小天狼星正是背叛哈利父母的好友，他的教父，而这次小天狼星越狱似乎正是为了找他。哈利的心里悄悄的滋生了为父母报仇的想法，期待着小天狼星的出现。\r\n　　新来的魔法老师卢平（大卫·休里斯）有着桀骜不驯的个性，与哈利关系很好，教了哈利许多实用的黑魔法防御知识。而此时在他的魔法地图上却出现了一个神秘人物，虫尾巴。偶然的机会下，在尖叫棚屋里集齐了哈利父亲当年的所有好友，卢平、虫尾巴、小天狼星，哈利的魔杖直指向小天狼星，但他发现那罪犯看他的眼神里充满了疼爱……',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (10,3,'2020-10-30 08:14:38'),(10,12,'2019-03-25 02:40:19'),(11,1,'2019-03-22 09:38:12'),(11,2,'2019-03-23 09:38:12'),(11,3,'2019-03-22 08:38:12'),(12,1,'2019-03-23 09:48:46'),(12,3,'2019-03-25 06:36:22'),(14,1,'2019-03-23 09:38:12'),(16,12,'2019-03-23 15:27:48');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ticket_num` int(11) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_strategy`
--

DROP TABLE IF EXISTS `refund_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `refund_strategy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refund_percentage` float(9,6) NOT NULL,
  `available_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_strategy`
--

LOCK TABLES `refund_strategy` WRITE;
/*!40000 ALTER TABLE `refund_strategy` DISABLE KEYS */;
INSERT INTO `refund_strategy` VALUES (1,0.850000,36000),(2,0.900000,86400);
/*!40000 ALTER TABLE `refund_strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL,
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (20,1,12,'2019-04-13 17:00:00','2019-04-13 18:00:00',20.5),(21,1,10,'2019-04-11 12:00:00','2019-04-11 13:00:00',90),(27,1,11,'2019-04-17 18:01:00','2019-04-17 20:01:00',20.5),(28,1,11,'2019-04-19 16:00:00','2019-04-19 18:00:00',20.5),(30,1,11,'2019-04-18 18:01:00','2019-04-18 20:01:00',20.5),(31,1,11,'2019-04-12 16:00:00','2019-04-12 18:00:00',20.5),(32,1,11,'2019-04-12 20:00:00','2019-04-12 22:00:00',20.5),(37,1,11,'2019-04-15 00:00:00','2019-04-15 02:00:00',20.5),(38,1,11,'2019-04-14 17:00:00','2019-04-14 19:00:00',20.5),(40,1,10,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(41,1,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(42,1,11,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(43,1,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(44,2,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(45,2,10,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(46,2,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(47,2,11,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(48,2,10,'2019-04-11 13:00:00','2019-04-11 15:59:00',20.5),(50,1,10,'2019-04-15 16:00:00','2019-04-15 19:00:00',2),(51,1,10,'2019-04-17 05:00:00','2019-04-17 07:00:00',9),(52,1,10,'2019-04-18 05:00:00','2019-04-18 07:00:00',9),(53,1,16,'2019-04-19 07:00:00','2019-04-19 10:00:00',9),(54,1,16,'2019-04-16 19:00:00','2019-04-16 22:00:00',9),(55,1,15,'2019-04-17 23:00:00','2019-04-18 01:00:00',9),(56,2,10,'2019-04-19 13:00:00','2019-04-19 15:59:00',20.5),(57,2,10,'2019-04-20 13:00:00','2019-04-20 15:59:00',20.5),(58,2,10,'2019-04-21 13:00:00','2019-04-21 15:59:00',20.5),(61,1,13,'2019-04-20 11:00:00','2019-04-20 13:00:00',25),(62,1,11,'2019-04-20 08:00:00','2019-04-20 10:00:00',25),(63,2,15,'2019-04-20 16:01:30','2019-04-21 05:30:00',30),(64,1,16,'2019-04-22 02:00:00','2019-04-22 05:30:00',30),(65,1,10,'2019-04-23 02:00:00','2019-04-23 05:30:00',30),(66,2,13,'2019-04-21 07:31:29','2019-04-16 15:59:00',20.5),(67,2,10,'2019-04-25 13:00:00','2019-04-25 15:59:00',20.5),(68,2,10,'2019-06-26 13:00:00','2019-06-26 15:59:00',20.5),(69,2,10,'2020-10-31 07:05:00','2020-10-31 12:05:00',1);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `consume` double NOT NULL DEFAULT '1',
  `way` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (12,50,5,3,2,1,'2019-04-23 13:50:52',50,1),(3,69,6,3,1,71,'2020-10-30 05:59:07',1,0),(3,69,7,4,1,72,'2020-10-30 05:59:07',1,0),(8,69,7,1,1,73,'2020-10-30 06:26:58',1,0),(8,69,8,1,1,74,'2020-10-30 06:26:58',1,0),(8,69,7,2,1,75,'2020-10-30 06:29:52',1,0),(8,69,8,2,1,76,'2020-10-30 06:29:52',1,0),(3,69,4,3,1,77,'2020-10-30 06:36:09',1,0),(3,69,7,0,1,78,'2020-10-30 06:36:33',1,0),(3,69,0,4,1,79,'2020-10-30 06:36:52',1,0),(3,69,1,2,1,80,'2020-10-30 06:51:08',1,0),(3,69,10,2,1,81,'2020-10-30 06:51:08',1,0),(3,69,2,0,1,82,'2020-10-30 07:07:17',1,0),(3,69,5,3,1,83,'2020-10-30 07:09:46',1,0),(3,69,5,0,1,84,'2020-10-30 07:10:05',1,0),(3,69,9,0,1,85,'2020-10-30 07:10:47',1,0),(3,69,11,1,1,86,'2020-10-30 07:24:24',1,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_refunded`
--

DROP TABLE IF EXISTS `ticket_refunded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket_refunded` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `consume` double NOT NULL DEFAULT '1',
  `way` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_refunded`
--

LOCK TABLES `ticket_refunded` WRITE;
/*!40000 ALTER TABLE `ticket_refunded` DISABLE KEYS */;
INSERT INTO `ticket_refunded` VALUES (12,50,5,3,2,1,'2019-04-23 13:50:52',50,1),(3,69,2,2,1,63,'2020-10-28 07:06:51',1,0),(3,69,3,2,1,64,'2020-10-28 07:06:51',1,0),(3,69,10,5,1,65,'2020-10-28 07:07:40',1,0),(3,69,10,4,1,66,'2020-10-28 07:07:51',1,0),(3,69,11,4,1,67,'2020-10-28 07:07:51',1,0),(3,69,11,5,1,68,'2020-10-28 07:07:40',1,0),(3,69,3,3,1,69,'2020-10-30 05:55:17',1,0),(3,69,3,1,1,70,'2020-10-30 05:55:17',1,0);
/*!40000 ALTER TABLE `ticket_refunded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'testname','123456'),(3,'test','123456'),(5,'test1','123456'),(7,'test121','123456'),(8,'root','123456'),(10,'roottt','123123'),(12,'zhourui','123456'),(13,'abc123','abc123'),(15,'dd','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,30);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_activity`
--

DROP TABLE IF EXISTS `vip_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vip_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost_in_need` float(9,3) NOT NULL DEFAULT '0.000',
  `bonus_balance` float(9,3) NOT NULL DEFAULT '0.000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_activity`
--

LOCK TABLES `vip_activity` WRITE;
/*!40000 ALTER TABLE `vip_activity` DISABLE KEYS */;
INSERT INTO `vip_activity` VALUES (1,'赠送余额','充值100元送10元',100.000,10.000),(2,'赠送余额2','充值200元送20元',200.000,30.000);
/*!40000 ALTER TABLE `vip_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `consume` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (1,15,375,'2019-04-21 13:54:38',50),(2,12,660,'2019-04-17 18:47:42',50);
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-02 17:00:43
