-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： 10.53.54.151:16795
-- 生成日期： 2020-01-01 14:20:17
-- 服务器版本： 5.7.18-20170830-log
-- PHP 版本： 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `testdb`
--

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`id`, `name`, `teacherId`, `time`, `credit`) VALUES
(1, '计算机网络', 1, '20192', 3),
(2, '信息论与编码', 1, '20201', 2),
(3, '通信电子线路', 2, '20191', 4),
(4, '数字信号处理', 2, '20191', 4),
(5, '通信原理', 3, '20191', 4),
(6, '微波技术与天线', 3, '20192', 2),
(7, '高年级研讨课', 4, '20192', 2),
(8, '微型计算机原理', 4, '20191', 4),
(9, '社会实践Ⅰ', 5, '20181', 1),
(10, '社会实践Ⅱ', 5, '20191', 1),
(11, '入学教育', 6, '20171', 0),
(12, '毕业实习', 6, '20201', 2),
(13, '毕业论文（设计）', 7, '20202', 8),
(14, '创新创业', 7, '20181', 2),
(15, '传感器技术', 8, '20192', 2),
(16, '数字图像处理', 8, '20192', 2),
(17, '卫星通信', 9, '20201', 2),
(18, '综合布线', 9, '20201', 2),
(19, '无线电测量技术', 10, '20191', 2),
(20, '数字程控交换原理', 10, '20192', 3),
(21, '单片机及接口技术', 1, '20191', 2),
(22, 'EDA技术基础', 1, '20191', 2),
(23, '高等数学A1(上)', 2, '20171', 5),
(24, '高等数学A1(下)', 2, '20172', 6),
(25, '线性代数A1', 3, '20171', 3),
(26, '概率统计A1', 3, '20181', 3),
(27, '大学物理C', 4, '20172', 4),
(28, '模拟电子技术', 4, '2018', 4),
(30, '数字电子技术', 5, '20182', 4),
(31, 'C++程序设计', 6, '20171', 3),
(32, '新生导学课', 6, '20171', 2),
(33, '电路原理', 7, '20172', 4),
(34, '场论与复变函数', 7, '20181', 3),
(35, '信号与系统', 8, '20182', 4),
(36, '工程图学与计算机绘图', 8, '20171', 2),
(37, '电磁波与电磁场', 9, '20182', 4),
(38, '体育Ⅰ', 9, '20171', 1),
(39, '体育Ⅱ', 10, '20172', 1),
(40, '体育Ⅲ', 10, '20181', 1),
(41, '体育Ⅳ', 1, '20182', 1),
(42, '文献检索与利用', 1, '20182', 1),
(43, '军事理论课', 2, '20171', 1),
(44, '大学生职业发展与就业指导', 2, '20172', 1),
(45, '大学英语Ⅰ', 3, '20171', 3),
(46, '大学英语Ⅱ', 3, '20172', 4),
(47, '大学英语Ⅲ', 4, '20181', 4),
(48, '形势与政策Ⅰ', 4, '20171', 0),
(49, '形势与政策Ⅱ', 5, '20172', 1),
(50, '形势与政策Ⅲ', 5, '20181', 1),
(51, '形势与政策Ⅳ', 6, '20182', 1),
(52, '形势与政策Ⅴ', 6, '20191', 1),
(53, '形势与政策Ⅵ', 7, '20192', 1),
(54, '马克思主义基本原理', 7, '20182', 3),
(55, '思想道德修养与法律基础', 8, '20171', 3),
(56, '中国近现代史纲要', 8, '20172', 3),
(57, '毛泽东思想与中国特色社会主义理论体系概论', 9, '20182', 5),
(58, '大学英语Ⅳ', 9, '20182', 3),
(59, '软件工程', 10, '20191', 4),
(60, '算法分析与设计', 10, '20192', 4),
(61, '编译原理', 1, '20192', 4),
(62, '面向对象程序设计', 1, '20191', 4),
(63, '数据仓库与数据挖掘', 2, '20201', 4),
(64, '计算机图形学', 2, '20191', 4),
(65, '人工智能导论', 3, '20191', 3),
(66, '图论及应用', 3, '20192', 2),
(67, '多媒体技术原理', 4, '20192', 3),
(68, '计算机辅助设计', 4, '20192', 2),
(69, '面向.NET的WEB应用程序设计', 5, '20192', 2),
(70, '软件测试理论与方法', 5, '20192', 4),
(72, '数据库系统', 6, '20182', 3),
(73, '离散数学', 7, '20182', 3),
(74, '数据结构', 7, '20172', 3),
(75, '信息安全基础', 8, '20182', 2),
(76, '计算机组成原理', 8, '20181', 4),
(77, '操作系统', 9, '20181', 3),
(78, '电路与电子技术', 9, '20172', 4),
(79, '网络安全风险评估 	', 5, '20192', 2),
(80, '逆向工程', 5, '20191', 4),
(81, '网络攻防实践', 1, '20191', 4),
(82, '应用密码学', 1, '20191', 4),
(83, '信息安全数学基础', 1, '20191', 3);

-- --------------------------------------------------------

--
-- 表的结构 `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `studentId` int(11) DEFAULT NULL,
  `courseId` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `GPA` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `grade`
--

INSERT INTO `grade` (`id`, `studentId`, `courseId`, `status`, `time`, `grade`, `GPA`) VALUES
(239, 3, 9, '通过', '20181', 86, 3.6),
(242, 3, 14, '通过', '20181', 84, 3.4),
(245, 3, 23, '通过', '20171', 73, 2.3),
(246, 3, 24, '通过', '20172', 62, 1.2),
(247, 3, 25, '通过', '20171', 80, 3),
(248, 3, 26, '通过', '20181', 71, 2.1),
(249, 3, 27, '通过', '20172', 73, 2.3),
(250, 3, 28, '补考', '20181', 74, 1),
(251, 3, 30, '通过', '20182', 64, 1.4),
(252, 3, 31, '通过', '20171', 81, 3.1),
(253, 3, 32, '通过', '20171', 80, 2),
(254, 3, 33, '通过', '20172', 70, 2),
(255, 3, 34, '通过', '20181', 70, 2),
(256, 3, 35, '通过', '20182', 63, 1.3),
(257, 3, 36, '通过', '20171', 75, 2.5),
(258, 3, 37, '补考', '20182', 64, 1),
(259, 3, 38, '通过', '20171', 92, 4),
(260, 3, 39, '通过', '20172', 81, 3.1),
(261, 3, 40, '通过', '20181', 100, 4),
(262, 3, 41, '通过', '20182', 89, 3.9),
(263, 3, 42, '通过', '20182', 90, 4),
(264, 3, 43, '通过', '20171', 91, 4),
(265, 3, 44, '通过', '20172', 85, 3.5),
(266, 3, 45, '通过', '20171', 77, 2.7),
(267, 3, 46, '通过', '20172', 77, 2.7),
(268, 3, 47, '通过', '20181', 74, 2.4),
(269, 3, 48, '通过', '20171', 91, 4),
(270, 3, 49, '通过', '20172', 90, 4),
(271, 3, 50, '通过', '20181', 91, 4),
(272, 3, 51, '通过', '20182', 85, 3.5),
(274, 3, 54, '通过', '20182', 74, 2.4),
(275, 3, 55, '通过', '20171', 81, 3.1),
(276, 3, 56, '通过', '20172', 80, 3),
(277, 3, 57, '通过', '20182', 80, 3),
(278, 3, 58, '通过', '20182', 67, 1.7),
(280, 1, 9, '通过', '20181', 92, 4),
(281, 2, 9, '通过', '20181', 88, 3.8),
(286, 1, 14, '通过', '20181', 91, 4),
(287, 2, 14, '通过', '20181', 91, 4),
(288, 1, 23, '通过', '20171', 75, 2.5),
(289, 2, 23, '补考通过', '20171', 69, 1),
(290, 1, 24, '通过', '20172', 63, 1.3),
(291, 2, 24, '通过', '20172', 69, 1.9),
(292, 1, 25, '通过', '20171', 81, 3.1),
(293, 2, 25, '补考通过', '20171', 61, 1),
(294, 1, 26, '通过', '20181', 72, 2.2),
(295, 2, 26, '通过', '20181', 76, 2.6),
(296, 1, 31, '通过', '20171', 87, 3.7),
(297, 2, 31, '通过', '20171', 81, 3.1),
(298, 1, 32, '通过', '20171', 91, 4),
(299, 2, 32, '通过', '20171', 86, 3.6),
(300, 1, 38, '通过', '20171', 68, 1.8),
(301, 2, 38, '通过', '20171', 98, 4),
(302, 1, 39, '通过', '20172', 65, 1.5),
(303, 2, 39, '通过', '20172', 85, 3.5),
(304, 1, 40, '通过', '20181', 82, 3.2),
(305, 2, 40, '通过', '20181', 92, 4),
(306, 1, 41, '通过', '20182', 89, 3.9),
(307, 2, 41, '通过', '20182', 85, 3.5),
(308, 1, 42, '通过', '20182', 87, 3.7),
(310, 1, 43, '通过', '20171', 92, 4),
(311, 2, 43, '通过', '20171', 90, 4),
(312, 1, 44, '通过', '20172', 88, 3.8),
(313, 2, 44, '通过', '20172', 90, 4),
(314, 1, 45, '通过', '20171', 80, 3),
(315, 2, 45, '补考通过', '20171', 74, 1),
(316, 1, 46, '通过', '20172', 86, 3.6),
(317, 2, 46, '通过', '20172', 84, 3.4),
(318, 1, 47, '通过', '20181', 85, 3.5),
(319, 2, 47, '通过', '20181', 74, 2.4),
(320, 1, 48, '通过', '20171', 90, 4),
(321, 2, 48, '通过', '20171', 87, 3.7),
(322, 1, 49, '通过', '20172', 87, 3.7),
(323, 2, 49, '通过', '20172', 94, 4),
(324, 1, 50, '通过', '20181', 80, 3),
(325, 2, 50, '通过', '20181', 89, 3.9),
(326, 1, 51, '通过', '20182', 84, 3.4),
(327, 2, 51, '通过', '20182', 88, 3.9),
(330, 1, 54, '通过', '20182', 83, 3.3),
(331, 2, 54, '通过', '20182', 75, 2.5),
(332, 1, 55, '通过', '20171', 89, 3.9),
(333, 2, 55, '通过', '20171', 85, 3.5),
(334, 1, 56, '通过', '20172', 81, 3.1),
(335, 2, 56, '通过', '20172', 78, 2.8),
(336, 1, 57, '通过', '20182', 74, 2.4),
(337, 2, 57, '通过', '20182', 74, 2.4),
(338, 1, 58, '通过', '20182', 77, 2.7),
(339, 2, 58, '通过', '20182', 75, 2.5),
(348, 1, 72, '通过', '20182', 85, 3.5),
(349, 2, 72, '通过', '20182', 88, 3.8),
(350, 1, 73, '通过', '20182', 82, 3.2),
(351, 2, 73, '通过', '20182', 82, 3.2),
(352, 1, 74, '通过', '20172', 85, 3.5),
(353, 2, 74, '通过', '20172', 78, 2.8),
(354, 1, 75, '通过', '20182', 85, 3.5),
(355, 2, 75, '通过', '20182', 77, 2.7),
(356, 1, 76, '通过', '20181', 74, 2.4),
(357, 2, 76, '通过', '20181', 72, 2.2),
(358, 1, 77, '通过', '20181', 82, 3.2),
(359, 2, 77, '通过', '20181', 84, 3.4),
(360, 1, 78, '通过', '20172', 85, 3.5),
(361, 2, 78, '通过', '20172', 68, 1.8),
(362, 4, 9, '补考', '20181', 27, 0),
(365, 4, 14, '通过', '20181', 89, 3.9),
(366, 4, 23, '通过', '20171', 69, 1.9),
(367, 4, 24, '通过', '20172', 61, 1.1),
(368, 4, 25, '补考通过', '20171', 60, 1),
(369, 4, 26, '通过', '20181', 79, 2.9),
(370, 4, 31, '通过', '20171', 66, 1.6),
(371, 4, 32, '通过', '20171', 92, 4),
(372, 4, 38, '通过', '20171', 62, 1.2),
(373, 4, 39, '通过', '20172', 75, 2.5),
(374, 4, 40, '通过', '20181', 97, 4),
(375, 4, 41, '通过', '20182', 90, 4),
(378, 4, 44, '通过', '20172', 89, 3.9),
(379, 4, 45, '通过', '20171', 71, 2.1),
(380, 4, 46, '通过', '20172', 74, 2.4),
(381, 4, 47, '通过', '20181', 71, 2.1),
(382, 4, 48, '通过', '20171', 90, 4),
(383, 4, 49, '通过', '20172', 86, 3.6),
(384, 4, 50, '通过', '20181', 86, 3.6),
(385, 4, 51, '通过', '20182', 86, 3.6),
(387, 4, 54, '通过', '20182', 77, 2.7),
(388, 4, 55, '通过', '20171', 86, 3.6),
(389, 4, 56, '通过', '20172', 78, 2.8),
(390, 4, 57, '通过', '20182', 79, 2.9),
(391, 4, 58, '通过', '20182', 75, 2.5),
(392, 4, 72, '通过', '20182', 84, 3.4),
(393, 4, 73, '通过', '20182', 72, 2.2),
(394, 4, 74, '通过', '20172', 77, 2.7),
(395, 4, 75, '通过', '20182', 69, 1.9),
(396, 4, 76, '通过', '20181', 66, 1.6),
(397, 4, 77, '通过', '20181', 84, 3.4),
(398, 4, 78, '通过', '20172', 73, 2.3);

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `checkInTime` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `status`, `checkInTime`, `college`, `subject`, `classes`, `level`) VALUES
(1, '王兴斌', '合格', '2017', '计算机与网路空间安全学院', '计算机科学与技术', '2班', 3),
(2, '方照霖', '合格', '2017', '计算机与网络空间安全学院', '计算机科学与技术', '2班', 3),
(3, '冯松山', '合格', '2017', '通信与信息工程学院', '通信工程', '2班', 3),
(4, '董立叶', '合格', '2017', '计算机与网络空间安全学院', '信息安全', '2班', 3);

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `name`) VALUES
(1, 'teacher1'),
(10, 'teacher10'),
(2, 'teacher2'),
(3, 'teacher3'),
(4, 'teacher4'),
(5, 'teacher5'),
(6, 'teacher6'),
(7, 'teacher7'),
(8, 'teacher8'),
(9, 'teacher9');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `limits` varchar(255) DEFAULT NULL,
  `connectedId` int(11) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `userName`, `password`, `grade`, `limits`, `connectedId`, `fullName`, `email`) VALUES
(1, 'root', 'root', 'Root', '111', 0, 'Root Account', 'root@chaoswang.cn'),
(2, 'teacher1', 'teacher1', 'Teacher', '011', 1, 'Teacher 1', 'teacher1@hainanu.deu.cn'),
(3, 'teacher2', 'teacher2', 'Teacher', '011', 2, 'Teacher 2', 'teacher2@hainanu.edu.cn'),
(4, 'teacher3', 'teacher3', 'Teacher', '011', 3, 'Teacher 3', 'teacher3@hainanu.edu.cn'),
(5, 'teacher4', 'teacher4', 'Teacher', '011', 4, 'Teacher 4', 'teacher4@hainanu.edu.cn'),
(6, 'teacher5', 'teacher5', 'Teacher', '011', 5, 'Teacher 5', 'teacher5@hainanu.edu.cn'),
(7, 'teacher6', 'teacher6', 'Teacher', '011', 6, 'Teacher 6', 'teacher6@hainanu.edu.cn'),
(8, 'teacher7', 'teacher7', 'Teacher', '011', 7, 'Teacher 7', 'teacher7@hainanu.edu.cn'),
(9, 'teacher8', 'teacher8', 'Teacher', '011', 8, 'Teacher 8', 'teacher8@hainanu.edu.cn'),
(10, 'teacher9', 'teacher9', 'Teacher', '011', 9, 'Teacher 9', 'teacher9@hainanu.edu.cn'),
(11, 'teacher10', 'teacher10', 'Teacher', '011', 10, 'Teacher 10', 'teacher10@hainanu.edu.cn');

--
-- 转储表的索引
--

--
-- 表的索引 `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk5` (`teacherId`);

--
-- 表的索引 `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1` (`studentId`),
  ADD KEY `fk2` (`courseId`);

--
-- 表的索引 `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- 表的索引 `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userName` (`userName`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- 使用表AUTO_INCREMENT `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=403;

--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用表AUTO_INCREMENT `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- 限制导出的表
--

--
-- 限制表 `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `fk5` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`id`);

--
-- 限制表 `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;