/*
Navicat MySQL Data Transfer

Source Server         : bii
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : nd

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-02-13 14:34:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` varchar(50) DEFAULT NULL,
  `qid` varchar(50) DEFAULT NULL COMMENT '问题ID',
  `uid` varchar(50) DEFAULT NULL COMMENT '回答用户ID',
  `content` text COMMENT '回答内容',
  `img` varchar(255) DEFAULT NULL COMMENT '插图，可使用逗号隔开',
  `anonymity` int(11) DEFAULT NULL COMMENT '匿名',
  `hit` int(11) DEFAULT NULL COMMENT '点赞次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `guanzhu`
-- ----------------------------
DROP TABLE IF EXISTS `guanzhu`;
CREATE TABLE `guanzhu` (
  `id` int(11) NOT NULL,
  `gid` varchar(50) DEFAULT NULL,
  `uid` varchar(50) DEFAULT NULL COMMENT '用户id',
  `uuid` varchar(50) DEFAULT NULL COMMENT '关注的用户id',
  `aid` varchar(50) DEFAULT NULL COMMENT '收藏的回答id',
  `qid` varchar(50) DEFAULT NULL COMMENT '关注的问题id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guanzhu
-- ----------------------------

-- ----------------------------
-- Table structure for `identify`
-- ----------------------------
DROP TABLE IF EXISTS `identify`;
CREATE TABLE `identify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(320) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of identify
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `uid` varchar(50) DEFAULT NULL COMMENT '问题的提出用户ID',
  `time` datetime DEFAULT NULL COMMENT '提出时间',
  `aid` varchar(50) DEFAULT NULL COMMENT '回答编号ID',
  `anonymity` int(11) DEFAULT NULL COMMENT '匿名',
  `anum` int(11) DEFAULT NULL COMMENT '回答数',
  `theme` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1', 'GitHub上Stars最多的10个Python项目', '作者：路人甲\n链接：https://zhuanlan.zhihu.com/p/24701448\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n上篇文章整理了 GitHub上Stars最多的项目，发现大部分都是JavaScript项目，这也是这两年的大趋势，当然在文章最后我也答应大家会继续更新Stars最多的10个Python项目，今天文章来了，希望这些项目可以帮助到你！上期回顾：2016年度GitHub上Stars最多的10个项目\n\n1、tensorflow/tensorflow\n\nStars：41347\n\n\nTensorFlow™ 是一个采用数据流图（data flow graphs），用于数值计算的开源软件库。节点（Nodes）在图中表示数学操作，图中的线（edges）则表示在节点间相互联系的多维数据数组，即张量（tensor）。它灵活的架构让你可以在多种平台上展开计算，例如台式计算机中的一个或多个CPU（或GPU），服务器，移动设备等等。TensorFlow 最初由Google大脑小组（隶属于Google机器智能研究机构）的研究员和工程师们开发出来，用于机器学习和深度神经网络方面的研究，但这个系统的通用性使其也可广泛用于其他计算领域。\n\n\n2、vinta/awesome-python\n\nStars：27494\n\n这个开源项目包含的是：收集了Python相关的框架、开源库、软件、学习等等资源。有了它，学好Python不害怕！\n\n\n3、jkbrzt/httpie\n\nStars：27432\n\n\nHTTPie 是一个开源的命令行的 HTTP 工具包，提供命令行交互方式来访问 HTTP 服务。\n\n\n4、pallets/flask\n\nStars：24304\n\n\n\nFlask 是一个微型的 Python 开发的 Web 框架\n\n\n5、nvbn/thefuck\n\nStars：23211\n\n\n开发者都或多或少接触过 linux 接触过命令行，当然肯定也都被命令行狠地“fuck”过。我很多时候都是微不足道的原因导致了命令行出错，例如将 python 输入成 ptyhon，例如将 ls -alh 输入成 ls a-lh而导致出错，这个时候我会想说：“fuck”。\n\n开发 thefuck 的这位同仁，恐怕也经常会有这种不和谐的情况。因此开发了这个软件 thefuck。thefuck 不仅仅能修复字符输入顺序的错误，在很多别的你想说“fuck”的情况下，thefuck 依然有效。\n\n\n6、django/django\n\nStars：22839\n\n\nDjango 是 Python 编程语言驱动的一个开源模型-视图-控制器（MVC）风格的 Web 应用程序框架。使用 Django，我们在几分钟之内就可以创建高品质、易维护、数据库驱动的应用程序。\n\nDjango 框架的核心组件有：\n\n用于创建模型的对象关系映射\n\n为最终用户设计的完美管理界面\n\n一流的 URL 设计\n\n设计者友好的模板语言\n\n缓存系统\n\n\n7、kennethreitz/requests\n\nStars：22525\n\n虽然Python的标准库中urllib2模块已经包含了平常我们使用的大多数功能，但是它的API使用起来让人实在感觉不好。它已经不适合现在的时代，不适合现代的互联网了。而Requests的诞生让我们有了更好的选择。\n\n正像它的名称所说的，HTTP for Humans,给人类使用的HTTP库！在Python的世界中，一切都应该简单。Requests使用的是urllib3，拥有了它的所有特性，Requests 支持 HTTP 连接保持和连接池，支持使用 cookie 保持会话，支持文件上传，支持自动确定响应内容的编码，支持国际化的 URL 和 POST 数据自动编码。现代、国际化、人性化。\n\n除此之外,Requests的文档非常完备，中文文档也相当不错。Requests能完全满足当前网络的需求，主要支持的功能如下：\n\nRequests 完全满足如今网络的需求。\n\n国际化域名和 URLs\n\nKeep-Alive & 连接池\n\n持续性的 Cookie 会话\n\n类浏览器式的 SSL 加密认证\n\n基本/精简式的身份认证\n\n优雅的键/值 Cookies\n\n自动解压\n\nUnicode 编码的响应主体\n\n多段文件上传\n\n连接超时\n\n支持 .netrc\n\n适用于 Python 2.6—3.3\n\n安全的线程使用\n\n\n8、rg3/youtube-dl\n\nStars：22447\n\n\nyoutube-dl 是一个很小的命令行程序，用于从YouTube等网站下载视频。它需要 Python interpreter (2.6, 2.7, or 3.3+)，支持多平。它还提供一个包含Python的Windows可执行程序 。youtube-dl可以在Unix box, Windows或Mac OS X中使用。\n\n\n9、ansible/ansible\n\nStars：20465\n\nansible是新出现的 自动化 运维工具 ， 基于Python研发 。 糅合了众多老牌运维工具的优点实现了批量操作系统配置、批量程序的部署、批量运行命令等功能。 仅需在管理工作站上安装 ansible 程序配置被管控主机的 IP 信息，被管控的主机无客户端。 ansible 应用程序存在于 epel( 第三方社区 ) 源，依赖于很多 python组件\n\n\n10、josephmisiti/awesome-machine-learning\n\nStars：18626\n\n此项目整理了机器学习相关的学习资源等。\n\n\n作为开发者，以上有你使用过的项目吗？那么你对这些项目的评价是什么呢？\n\n文章首发，欢迎关注公众号（一个程序员的日常）', '1', '2017-01-01 00:00:00', null, '0', '9', null);
INSERT INTO `question` VALUES ('2', '2', '程序员怎么找到想要的资源？（新手小白 向）', '作者：林梓\n链接：https://zhuanlan.zhihu.com/p/24708909\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n选择做程序员就要有一颗保持学习的心，因为技术更新快、需求变化快……所以得时刻保持学习才能不被淘汰。正因为如此，我认为我们程序员的自学能力还是挺不错的，嘻嘻~\n\n说到自学，就出现了“如何找到自己想要的资源”的问题了，其实关于如何找到资源，我想基本都知道，去谷歌、去百度一搜就出来了，但是我想要说的是如何更好的找到想要的资源，而不只是谷歌和百度搜索，毕竟有时候搜索出来的形形色色，还需要自己再次筛选一遍，真的挺累的。\n\n说明：这篇文章还是写给新手小白的，因为大牛肯定有自己找资源的更极客的方法。\n\n我们在学习和工作中一般会遇到这样一些情景：突然或者思考了很久想做个项目，却没有开发思路，不知道从何下手；想学习新的技术，却不知道哪里有详细的教程可以学；想找一些书籍来看看，却不知道在哪里可以找到书籍，找到免费的书籍；遇到问题没人可以交流，不知道哪里可以交流；\n\n基于以上的各种可能会遇到的情景呢，文章就从以下几个方面进行了整理和推荐，希望对于新手小白能够有所帮助：\n\n1）学新技术：想学新技术去哪里找教程？\n2）找项目：想要开发项目，去哪里找点子？找教程？\n3）找书籍：哪里可以找到免费的书籍？\n4）交流探讨：遇到问题哪里可以交流？\n5）其他：一些程序员相关\n\n\n一、学新技术\n\n如果想要学习新技术，首先肯定是看官方文档，新技术谁也没用过，官方文档基本可以满足学习需求了；\n\n当然也有英语不那么好、或者看官方文档一通说的不知所云，那么可以看一些技术大牛写的博文，很多大牛都会第一时间写出相关的博文，可以参考阅读，参考性还是大大的；\n\n这里分享2篇文章，可以找到大牛：\n\nGitHub中国区前100名到底是什么样的人？\nGitHub中国区follower最多的人有哪些？\n\n这两种方式呢，我还真无法给出具体的推荐，因为每个人学的技术不一样，官方文档不一样，大牛更是如此了。\n\n除了以上两种方式之外，还可以选择一些学习网站进行了解，很多学习网站都会快速的出新技术相关的教程，这个倒是可以推荐几个：\n\n1）实验楼\n\n实验楼对于学习新技术的优势就在于，有在线开发环境，新技术出来，一般很多人都会报以观望的态度，如果你想体验一把新技术但又不想在自己电脑上安装环境的话，那么这个时候就可以选择用实验楼了，可以随便折腾，非常方便。\n\n2）慕课网\n\n慕课网这种视频方式，其实对于体验新技术没有太大的优势，毕竟视频做出来比较慢，而且没有在线的开发环境，不过如果你不是那么着急想体验新技术的话，可以等到这种视频类网站出了相应的教程之后再来学习，看视频学习的方式确实比较轻松。\n\n3）网易云课堂\n\n网易云课堂和慕课网类似，不过里面的课程提供方比较杂而且多，特别是学习基础课程，需要自己挑选比较好的课程，对于学习新技术来说，或许会有一些课程提供者会比较快速的出入门或者介绍教程，还是可以看看。\n\n当然，还有其他各种各样的IT学习网站，需要的话可以自己去搜索。\n\n4）国外的一些学习网站\n\n国外的网站，用的就没有国内的多了，不过可以推荐几个，毕竟国外在新技术的普及以及反应方面比国内还是快一点的。\n\ncodecademy：codecademy适合编程入门，它提供的是一个文档和在线编辑器，貌似和前面介绍的实验楼很相同，但是完全不一样，实验楼提供的是一台虚拟机，可以随便玩，而codecademy则不可以！\ncoursera：coursera呢是一个涵盖各领域的公开课程集中地，和很多大学都有合作，里面的课程还是蛮好的，如果英文可以的话倒是推荐去看看，毕竟还是有那么多世界性的大学课程啊。\nCode School：codeschool的教程包括：视频教程、编码挑战以及屏幕截屏等多种学习方式，网站的一些入门课程比较好，学起来也比较有趣味，上面分阶段的挑战是很多人都还蛮喜欢的，如果你的英文可以的话，可以去看看。\nTreehouse：treehouse也是一个英文的IT学习网站，上面的课程开始是Web设计开发，现在有了APP开发、以及其他语言等，主要提供的是视频，包括教学视频啊、培训视频啊等，不过现在网站是完全收费的\n\n二、找项目\n\n肯定会有这样的情景的，想开发一个东西，却不知道怎么下手去开发，或者卡在某个地方；亦或是想找一点项目来练练手；更或者不知道所学的技术可以开发哪些东西……那么这个时候就涉及到“找项目”啦，我想做得最多的就是谷歌或者百度“XX怎么实现？”，但是这样找出来的东西太杂乱了，能拿来学习的很少，所以在找项目这一点上，我首先推荐到各学习平台找。\n\n关于编程的学习平台，基本上都会有项目教程的，在学习平台上找项目，一方面是因为教程讲解的详细，另一方面是因为这是最快的可以找到既有教程又有项目的地方，所以非常推荐。\n\n至于学习网站嘛，就如上面所说的那些，这里再叙述一遍，只介绍2个中文网站，再介绍几个可以找到项目的其他资源。\n\n1）实验楼\n\n实验楼是个非常适合找学习项目的网站，有详细的教程和在线开发环境，而且最近几个月的教程更新频次和质量都非常不错，涉及各个领域，推荐想找项目的小伙伴去网站看看。\n\n2）慕课网\n\n慕课网和实验楼的区别就是视频和非视频，里面同样也有很多的项目教程，不过有一点不太方便的地方在于，慕课网是视频方式，这对于有基础而且只想快速看看项目开发大纲或者某个开发知识点的人来说，不太方便，毕竟文档更容易查找想看的内容一点。\n\n当然，还有一种方式，就是在一些开源平台上去扒拉找项目，或者会有一些大牛总结关于项目的文章，都可以找到不错的项目点子或者教程；\n\n开源中国：对于开发者来说肯定还是很熟悉的，上面会有各种开源项目，以及项目代码，可以找到不错的项目资源。\n有了这个列表，程序员不愁没练手的小项目了：我经常看有人发帖问关于项目点子的事，大家要找简单的编程项目学习练手的话，可以收藏这个列表。这些项目并不是论文级别的，只是想抛砖引玉让大家能从中受些启发。\n\n其实关于项目的核心代码倒是很多博客里也有，如果你想要开发某个项目的话，可以换各种关键字以及方法搜索，总会找到想要的。\n\n三、找书籍\n\n其实找书籍还好，自有各种途径，我这里就不再叙述了，把我以前整理的一篇书籍文章放上来，里面介绍了不少的可以找到书籍的地方，感兴趣的可以看看。\n\n程序员的书籍资源\n\n四、交流探讨\n\n在学习和开发中肯定会遇到各种问题，有时候身边并没有可以交流的人，这个时候就可以上一些论坛交流社区了，推荐几个：\n\n1）国内的：\n\nV2EX\nSegmentFault\n知乎\nInfoQ\n\n2）国外的：\n\nStack Overflow\nQuora\n\n3）其他一些资讯网站：\n\nCSDN：最大的IT中文社区，可以找到各种资源和博客文章；\n51CTO：和CSDN差不多，也是可以找到各种资源和文章；\n博客园：和CSDN、51cto差不多，文章也是很多，参差不齐，需要自己筛选；\n码农网：主要是文章，有技术的，也有非技术的；\nLinux中国：关于Linux相关的资讯、文章、技术等；\nFreeBuf：关于信息安全方面的各种资讯、文章和技术等；\n对了，还有稀土掘金、开发者头条、极客头条上都可以找到不错的技术文章；\n\n以上介绍的只是部分可以交流探讨，或者找到解决方案的地方，还有很多很多其他的平台，如果你觉得有用着很实用的欢迎告诉我，我添加进来~\n\n五、其他\n\n前面分享了4种需求的找资源的地方，当然肯定不全面，这里再分享一些其他有用的资源，或许对你就有所帮助。\n\n动画展示各种路径搜索算法：我觉得还蛮有趣的，直观的感受路径搜索算法；\npythonchallenge挑战：类似于闯关挑战游戏，给出一个Python相关的图片，闯关者需要解答问题之后才能跳转到下一个关卡；（嗯，这里还有这个挑战的闯关秘籍，可以看看）\n一个编程刷题网站：一个编程刷题网站，比较有趣；\n技术类博客网站推荐：分享了一些技术类的博文网站；\n优秀的计算机编程类博客和文章：一些关于计算机方面的优秀博客；\nＩＴ，互联网，科技博客推荐：偏向互联网，科技资讯类网站；\n\n六、最后\n\n另附我以前整理的一些文章，希望里面的资源能够对你有所帮助：\n\n16个小众却很实用的网站（程序员 向）\n想做黑客？先看看这个信息安全资源列表吧~\n插件资源整理\n大数据资源整理\nPHP学习资源整理\n程序员的书籍资源\nC语言学习资源整理\nDocker 学习资源整理\n前端Web开发资源整理\n学 [数据结构、算法] 的资源推荐\nJava 学习资源整理\nlinux学习资源整理\n给想学习VIM的超级小白的文章\nPython学习资料总结\nIT学习网站总结\n\n后记：\n\n写了很多，把我找资料的思路给记录了下来，希望对新手小白能够有所帮助，我非常了解作为一个新手小白所遇到的各种苦恼：想学，可是没人教；想问，又害怕别人说自己是伸手党；想开发项目，却不知道从何下手……\n\n要相信，每个学编程的人都有这样一段时光，不要怀疑自己，最重要的是多看多做多敲代码，上天不会辜负一个爱学习的人的。\n\n最后呢，把我以前整理的一些资源文章也粘贴了出来，既是对自己的一个总结，也更希望对小伙伴有所帮助~\n\n还想说：每个程序员上辈子都是折翼的天使……', '2', '2017-01-01 00:00:00', null, '0', '2', null);
INSERT INTO `question` VALUES ('26', '3', '那些长得好看的人，最终都会遇到一道坎。', '作者：源靖\n链接：https://zhuanlan.zhihu.com/p/25099082\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n我看过日本一个很有趣的实验：请一位女生分别在素颜和化妆的情况下向路人借钱，然后对比结果，看看颜值在生活中到底发挥着怎样的作用。\n\n\n在素颜的时候，女生接二连三的被拒绝。有说“我身上没带钱”的，更多的人都是随便敷衍一下就走了。几次下来，她一分钱都没有借到。而在化妆之后，遇到的第一位路人就明显专注多了，听完妹子的来意就拿出钱包掏出三千日元递给女生，还关切地问够不够，最后还把自己的名片和联系方式给了她。之后借起钱来那就像开了挂一样，最终她借到了15800日元。\n\n\n接下来网友的评论大致都是一个论调：这是个看脸的社会啊！\n\n\n实际上，这个实验并不能完全得出“这是一个看脸的社会”的结论。或者说，这个实验只探索了颜值在生活中发挥的作用的一半。还有另一部分更深层次的东西没有被揭示出来。\n\n\n想必大部分人都看过《甄嬛传》，甄嬛因为是美女被选入宫，又因为“翩若惊鸿，婉若游龙”而受到皇帝的青睐，然后经历一系列故事之后成为了一人之下万人之上。因为颜值高，所以甄嬛有了进宫的机会。但仅仅是因为颜值高，她就能因此当上登顶吗？\n\n\n在后期与各个敌人勾心斗角并且取得胜利的关键元素早已不再是颜值了，而是能力，人脉，城府，性格等等因素。这也是最吸引观众的地方。\n\n\n这部剧说明白了一个很简单但又很隐晦的道理：颜值能够帮助一个人在社会中低层的时候发挥作用，随着社会地位和社交圈的提升，颜值发挥的作用将越来越小。\n\n\n其实不光是甄嬛，也不光是后宫戏。任何一部写实类的影视作品里，我们都可以看到这样的影子，就好像这是约定俗成的东西。这不是导演的精心安排，只是艺术源于生活。历史上的西施，王昭君，杨玉环之所以能够成为四大美女，千古流传，是因为她们身上都具备一些除了美貌以外，更深层，更鲜活的特质。\n\n\n换句话说，高颜值就等于一张入场券，是否成为座上宾，那得看你有几把刷子。\n\n\n\n2\n\n德国的社会学家做了一个调查，他们收集了3000多名职场人士的事业发展情况，并给他们的外貌打分，再将二者进行比较；结果发现：外貌吸引力比平均分高出1分，被雇用的几率就会增加3%。\n\n\n我曾问过一个HR，你们不担心那些个美女或者帅哥只是花瓶，没有什么真才实干吗？他的回复是：这当然是一个隐患，但是美女帅哥还会有很多正面作用，比如促使异性更加的努力工作，争取好的表现。而且他们的自信也会让他们融入团队也会更快一些。如果真的只是花瓶，公司有的是办法让他们滚蛋。\n\n\n可见，在同样的学历和经验作为前提下，通常颜值高的人获得机会的可能性会更大一些。但是要在公司混得好，仅仅靠脸是不够的。\n\n\n美貌的限制性就在于，它是个被动技能。如果你长得好看，别人会额外的对你好一点，这无须怀疑。但是你想要获得质的飞跃，去掠夺资源，脸恐怕是最靠不住的东西。\n\n\n假如跳出来看，我们通常会看到帅哥和美女过着光鲜亮丽的生活。早晨西装革履，晚上翻云覆雨；今天玛莎拉蒂，明天巴黎走起。好像长得丑的人就只能在家里写代码，撸美剧。\n\n\n这里很容易让人误解为，长得好看的人就能找到体面的工作，而长的丑的人只能做一些普通的工作。\n\n\n确实，很大程度上，颜值的高低和职业的选择也有着一定关系。然而原因绝不是这样，实际上通常长得好看的人，更愿意去选择一些“颜值回报”更大的工作。比如平面模特，演员，主持人等等。而长得不好看的人，更愿意去选择一些“技能回报”更大的工作。比如科研人员，律师，医生等等。\n\n\n也就是说，长得好看的人，“靠脸吃饭”会更加容易，也能获得更多的报酬。而长的不好看的人，靠脸是吃不上饭的。大多数丑陋的人，如果他们想成为银幕演员的话，他们将在大部分时间处于失业的状态。这也能解释为什么有些人明明能够靠脸吃饭，却偏要靠才华——因为靠才华能够帮他获得比靠脸更多的回报。\n\n\n然而回到不同的职业当中，颜值的作用依然受社会地位的提升而逐渐降低这条规则限制。\n\n\n就拿演员来说，我们就会发现，多半三线小演员都长得好看。而一线演员里面，美女帅哥的数量数量明显降低了。因为想要做演员，长相是一个基本条件（丑角除外），这时候颜值发挥着很大的作用。但是想要做好演员，演技是一个基本条件，这时候颜值基本不发挥作用。\n\n\n就像小李子，当年的Jack够帅吧。但只演小鲜肉他能拿到小金人吗？又有哪个小金人是属于小帅哥的吗？达斯汀·霍夫曼（Dustin Hoffman）的姑妈告诉他，“你成不了伟大的演员，你太丑了”。然而霍夫曼获得了两座奥斯卡最佳男演员的奖杯。赵奕欢是很漂亮，然而从2011年出道至今她就没演过一部能看的片子。再看同样是靠着外表出道的汤唯汤女神，看到《北京遇上西雅图》里面她那一口利索的英文，就知道她在英国留学的时候没少下功夫。\n\n\n演艺圈如此，任何职业都是如此，就连妓女也不例外。想要从一个妓女晋升为一个妈咪，光有脸蛋和大胸是远远不够的。男人也一样。看过《鸭王》的人就知道，从鸭到鸭王需要多么努力，弹多少“钢琴”，舔多少硬币......你以为这是一部三级片，其实这是一部充满了教育意义的正能量电影。\n\n\n对外表的过度推崇和迷恋，是当下最普遍也最隐秘的舆论暴力之一。当大家都在说看脸的时候，不但会伤害那些长相丑陋的人，同样会伤害到那些长相俊美的人。丑的人会因此累计大量的负能量，失去信心，甚至失去更多东西。好看的人也会饱受非议，需要承受某些尖锐的压力。\n\n\n\n\n3\n\n可能有人要说，这都是在工作中的体现。那么在男女交往中，总是看脸的吧。\n\n\n实际上，在男女择偶的时候，照样遵循着颜值随社会地位的提升而逐渐降低这条规则。\n\n\n我们先看男人。\n\n\n通常人们在中学会开始谈恋爱或者想要谈恋爱。这个阶段的小女生根本不懂得什么叫做社会地位，所以她们通常不会在意是否有钱这个选项，男生长的帅就是一切。你可以回想一下，在你们班上，是帅哥谈的恋爱多，还是学霸谈的恋爱多。当然，如果有帅哥学霸，那当我没说。\n\n\n大学这个阶段就很有意思了，这是一个转折点，女生在这个时期已经开始懂事。不同的大学代表了不同的阶级，离开了家庭之后也慢慢的感受到经济实力的重要性。但大学依然是一个相对简单的环境，很多女生依然还稚嫩，所以大学女生会出现三种情况：\n\n\n1：只看脸。2：既看脸，也看学习和经济条件。3：只看学习和经济条件。\n\n在普遍情况下，第2种的女生最多，其次是第1类，第3类最少。\n\n\n我自己就亲身体验过一件事情，在大学里，我算是颜值中上的小伙子，大一下学期刚开始的时候英语老师在班上公布成绩，我拿了全班第一（全专业第二），当天晚上我就意外的收到了两个女生的表白。\n\n\n在25岁到30岁之间的女人，已经毕业好几年，甚至换过好几份工作了，这时候的她们早已褪去了学生气。在择偶的时候，她们心里最关心的问题通常就是这个男人是做什么工作的。好的工作意味着高收入和高社会地位。这个阶段的女人已经不被允许只去考虑一个男人的脸了。在社会上摸爬滚打了几年之后，她们心里明白什么叫做“帅不能当饭吃”。', '1', '2017-02-08 15:01:11', null, '0', '4', null);
INSERT INTO `question` VALUES ('27', '4', '你认为炒股是心理上的博弈，还是技术上的博弈？', '===\n投机者唯一要做的就是顺势，很大程度上交易是理性的人收割没有敬畏之心、为所欲为的人所上交的智商税。指数绝大部分情况下都会走成教科书那样的，只要尊重客观事实，市场还是相当慷慨的。\n20161212一颗大阴线下来，市场先生明明白白告诉你：“我要开始跌了，你当心点，先撤吧“；后来几天市场先生没改变主意，那就不存在返场做多的理由了。看下我以前的回答吧，如果你愿意建立一种客观理性的操作体系。\n\n纯粹的交易（没有内幕消息，不控盘）是一场和内心的博弈，大部分人都是在妄想，其实未来的走势一直明明白白放在眼前，只是大部分人选择视而不见，按自己的所谓“分析”进行投机。\n\n作者：马俊\n链接：https://www.zhihu.com/question/54927090/answer/144858183\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。', '2', '2017-02-05 15:02:08', null, '0', '5', null);
INSERT INTO `question` VALUES ('28', '5', '为什么你应该开始学习编程了？', '作者：张砷镓\n链接：https://zhuanlan.zhihu.com/p/25112724\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n有一家饭店的大厨，烧得一手好菜，经过口碑相传，客人从五湖四海闻名而来。然而这对饭店的老板来说，并不单纯是一个好消息。因为客人不是奔着饭店，而是奔着大厨的手艺来的。老板必须想办法留住这位大厨，否则他一旦被别人挖走，饭店的生意就会一落千丈了。然而即便老板不惜血本保证了大厨的忠诚度，风险也依然存在：\n\n大厨休息或请假的时候，菜品的口味就无法让顾客满意；\n\n大厨只有一个，如果想在多个地方开分店，那口味也就不能保证了；\n\n大厨再厉害，同时也只能炒一个菜，而顾客越来越多，输出总是供不应求；\n\n大厨年纪大了总是要退休的，如果收徒的话，怎么继续保证徒弟的忠诚度呢？\n\n有一天，老板突然悟到，决定菜品口味的是大厨烧菜的过程，而不是大厨本人。如果大厨愿意把自己每个菜的菜谱都写出来，那不就可以请别的厨师来操作了吗？虽然别人按照菜谱烧出来可能达不到原味的100分，但90分总是能达到的，而这样的差距是一般的食客无法分辨的。这样只要菜谱在，饭店菜品的质量就能得到保证，扩张店面、开分店什么的就都不成问题了。\n\n于是老板和大厨谈了一个晚上，说服了他用菜谱技术入股。几年后，饭店生意越来越好，开了无数家分店，老板赚了很多钱，大厨也得到丰厚的分成，不用再天天靠手艺吃饭了。\n\n什么是编程？\n\n在洗衣机出现之前，人们只能用手来洗衣服，需要经过浸泡、揉搓、漂洗、拧干的一系列流程。而全自动洗衣机的发明使洗衣服的工作人人都可以轻松完成，从而可以将原本用来洗衣服的时间解放出来去做其他事情。随着洗衣机的不断迭代升级，洗衣服的整个流程最终会被人们遗忘，只有设计洗衣机的工程师们了解流程，因为他们负责对其进行优化和改进。\n\n所谓程序，就是为了实现一个需求而设计的逻辑流程。大厨的菜谱和洗衣机的工作流程都是程序，只不过前者由人来执行，后者由机器来执行。正是因为有程序作为媒介，我们才可以分离设计者、调用者和执行者的角色。虽然程序的执行效果未必能让其设计者满意，但它已经可以脱离设计者的时间和空间局限而存在，可以被其他人执行、验证和改进。\n\n所谓编程，就是指编写程序。将烧菜的过程写成菜谱，本质上就是在编程。通过编程，我们更加透彻地理解并阐述了事物的本质，让曾经专属于某个人、某个团体、某个地方甚至某个时刻的东西，得以独立的存在和发展。从这个角度来讲，说编程是在创造生命也不为过。\n\n编程可以说是一种标准化的写作。标准化保证了服务和产品的质量，也使大规模复制和扩张成为可能。KFC正是依赖其详尽到炸薯条的油温秒数的食品加工手册，才将分店开满世界各地的。陶华碧如果不能将她的豆瓣酱配方和制作过程清楚地描述出来，交由标准化生产线去制造的话，那么今天“老干妈”最多只是一个小乡村里的作坊级产品，不可能走上我们的餐桌。\n\n我们每天的生活，都在和各种技术产品和服务打交道，比如导航、搜索引擎、聊天软件……你可曾想过，这些产品和服务背后的原理是什么？相信除了程序员群体之外，绝大多数人不会去想这些问题，因为：\n\n这跟我没有关系啊！我又不去干这行……\n\n应该只有专业人士才能搞懂吧，我可不行！\n\n能用就行了，想那么多干啥，多累呀！\n\n他们这么想并没有错，因为编程的本质就是：设计一个逻辑流程来实现指定的需求，使调用者无需了解实现细节即可达到目的。\n\n由于程序和编程的广义概念太过笼统，为不导致概念混淆，下文中提到的“程序”和“编程”，特指使用计算机编程语言编写，由机器来运行的程序。\n\n解放时间和注意力\n\n假设你已经在某岗位工作了一段时间，根据经验总结出来每天下班前有下面三件事需要做：\n\nA、在公司内网系统查询某业务当天的数据（约10分钟）\n\nB、整理成日报表并存档（约15分钟）\n\nC、把几个核心数据用电子邮件发送给领导（约5分钟）\n\n你可以在认真梳理过之后，将这个流程写下来，贴在办公桌前，或者记在脑子里。这自然比那些不知道流程的人要强得多，你可以确保每个步骤都不被遗漏地执行到（然而这并不能完全保证）。虽然你已经花了时间认真思考过，但每天这样枯燥乏味的流程都需要自己执行一遍……等等，这活我不可以找个秘书来干吗？\n\n当然可以，雇佣他人是一种用金钱换时间的解决方案。但是这同时又引入了许多新的问题：\n\n你需要花钱（废话）\n\n你需要把要做的事解释给秘书听，并确保他能听懂（沟通成本）\n\n秘书下班或休假时，这些事你还得自己做（有时间限制）\n\n你要为秘书犯的错误承担责任（质量得不到保证）\n\n每次秘书犯了错误，你都需要进行教育（培训成本）\n\n秘书会直接接触业务数据和信息，难保有泄密的可能（安全隐患）\n\n换一个秘书，以上的事情都得再来一遍……\n\n这不是把事情搞复杂了吗？人的成本太高，提供的服务又不可靠。而如果你会编程的话，也许你可以：\n\n编一个小程序来完成查数据、做报表、发邮件的流程，再配置一个定时任务每天自动执行；\n\n谨慎一点的话，可以让程序先发到自己的邮箱，检查没有问题后再转发给领导；\n\n必要的话，还可以让程序在运行出现意外时给你的邮箱或手机发报警通知；\n\n如果你愿意，甚至可以给内网办公系统直接加上邮件报表这个功能……\n\n假设你原本手工做完ABC流程需要30分钟，那么你的程序每执行一次，就为你节省了30分钟的时间。假如你写程序用了三个小时，那么一周就能收回成本，以后全部都是净赚的。如果程序运行的时间足够长，那么单次运行的均摊成本将趋近于零。可以看出，编程是一种用时间换时间的解决方案。\n\n当然，你需要将要做的流程写成机器能读懂的程序；如果需求发生变化，你需要对应地修改程序；如果运行时出了BUG，你需要调试修复……但更重要的是：程序不拿工资，不可能辞职，不需要休息，不会闹情绪，不可能犯错，只要你的流程正确，依赖的资源不出问题，它就可以7*24小时一直运转下去。\n\n每当我们写程序实现了一个需求，就好比造出了一把锤子。今后你遇到类似的问题，都可以拿这个锤子来解决。当然有时会遇到锤子不称手的情况，需要持续地升级和优化。但在大多数情况下，这个锤子都是可以直接拿来用的。也就是说：完全相同的问题，你只需要解决一次就好，不会浪费时间在重复的工作上。\n\n通过编程，你可以将那些枯燥无味的重复性工作中的部分甚至全部，交由机器来接管，这样就可以将你的时间和注意力从具体的事务中解放出来，去做更有价值的事情。比如研究和优化工作流程，或者陪伴家人，又或者去读一本书……\n\n体验“开挂”的人生\n\n喜欢网游的朋友可能都知道外挂这东西，这是指和游戏一起运行、为了提升游戏体验的小程序。外挂一般分为两类：一是在游戏系统框架内简化玩家操作的辅助性外挂，提供如自动拾取、自动打怪、改键之类的功能；二是利用游戏漏洞营造不平等的作弊性外挂，提供如开图、加速、无敌之类的功能。\n\n随着游戏行业的迅猛发展，现今很多网游都已经配备了原本由辅助性外挂提供的功能，辅助性外挂正在逐渐消亡，只剩下作弊性外挂和衍生的一系列黑色产业链，你懂得。\n\n当我们说某个人“开挂”时，其实是在说他的表现（数量、速度、精确度……）明显超出正常的范畴。当原始土著人碰到使用着各种科技产品的现代人时，大概就是一种“**，这哥们开挂了吧？”的感觉。\n\n要知道，身体并不强壮的人类之所以能征服地球，就是因为我们会创造并使用工具来突破生理的局限，做到原本不可能做到的事情。在即将到来的全信息时代，编程将是创造工具，甚至使用工具的主要方法。我们天天都在使用的软件和APP，不管是文字处理，K歌软件还是搜索引擎，都已经和我们的生活融为一体，成为我们生命的延伸。通过使用这些软件，我们可以不断突破自己经验和能力上的局限，我们随时都在给自己“开挂”。\n\n然而大多数人都是在被动地等待别人来满足自己的需求。他们会使用通过朋友推荐、广告宣传等各种渠道推送给自己的软件，然后感慨一下：“哇，居然还可以这样！”他们只会使用已有软件提供的标准功能，而一些个性化的特殊需求就只能因为软件不支持放弃掉了。这就和穿衣服一样，大多数人只会买标准尺寸穿，如果这里紧那里松，这里长那里短，也就只能忍了。\n\n还有少部分人会主动考虑如何去满足自己的特殊需求。他们会想：“如果有XXX功能就好了……”他们会主动去寻找能满足自己需求的软件，研究软件的个性化配置，或者给软件的开发者提功能建议。同样，追求个性的人可能会找裁缝为自己量身订做或者修改衣服，使其尽可能地适合自己。\n\n只有极少数的人有能力自己去实现那些别人不能满足的需求。他们能在原有软件的基础上开发插件，对软件进行二次开发，甚至写出一个全新的软件。同样，追求完美的人可能会亲自设计衣服，并把一件衣服不停地改来改去，直到自己彻底满意为止。\n\n如果说学好英语能为你的世界打开一扇门，让你拥有更多的选择的话；那么学好编程就能让你有机会以“上帝视角”来认识和改造这个世界，并拥有几乎无限的可能性。因为在现实中的一切最终都会被信息化，而你可以通过编程来对信息做任何形式的加工和处理，只要你想得到，就能做得到。\n\n你想体验“开挂”的人生吗？那就赶紧开始学编程吧。\n\n培养深入的思维方式\n\n每个人都应该学习编程，因为它将教会你如何思考。——苹果创始人 乔布斯 [1]\n\n思考，是人之所以为人的行为，而编程是一种对人的思考进行再思考的行为。我们不需要把每件事情想清楚，就可以在现实社会中生存。对某些从事机械性操作的职业来说，甚至完全不需要进行思考。然而在编程时，我们只有在想清楚之后，才能把程序写出来。在编写正确、高效、优雅的程序的同时，我们也在塑造自己的大脑，让它能思考得更清楚、运转得更高效。\n\n编程要求我们客观地去思考事物的本质，将注意力放在事物本身，而不是事物与我们的关系上。当古代的妇女在河边洗脏衣服时，她可能在想：“河水好冷啊……这衣服颜色真漂亮……我家孩子为啥这么调皮……”而当我们在为洗衣机设计程序时，只会想：“哦，这有一堆脏衣服需要洗”。其实很多原本困扰你许久的问题，只要跳出“我”的范畴，进行“忘我”的思考，就变得特别简单和容易解决。\n\n编程是将人的想法“实体化”的过程，这要求我们进行更深入、更细致、更全面地思考。为了实现一个需求，你必须对其原理和运转流程了解得十分透彻，否则就无法用编程语言精确地描述出来让机器去执行。在实体化的过程中，想法的结构缺陷和逻辑漏洞会自然凸显出来，你总会发现存在没有考虑到的可能性，以及需要进一步思考的细节。\n\n编程要求我们能够对事物和流程进行拆分，并在不同的抽象层次上进行完整自洽的思考，这使我们有可能去解决那些规模无比庞大的问题。在实现一个稍具规模的需求时，我们不太可能同时考虑主体流程和操作细节，也不太可能同时从多个角度进行思考。经过合理拆分后的需求细粒度需求简单明了，实现难度大大降低的同时，还可以分配给多人来共同进行。在一个成熟的软件或互联网公司，上千名工程师一起开发同一款产品是很常见的，而你能想象这么多人一起去写一本书么？\n\n编程是不断解决问题的过程，也是不断完善解决问题的方法论的过程。一个优秀的程序员总是解决问题的高手。在编程的各个阶段（需求定义、方案设计、编码实现、调试纠错……）中，都将面临无穷无尽的问题。这个问题要不要解决？什么时候解决？其根源是什么？需要考虑哪些方面？如何做取舍？有哪些方案可供选择？选择的原则是什么？……解决问题的方法论展开来讲可以写一本书了，且容我有时间另撰一文来表。\n\n在未来更好地生存\n\n半个世纪以前，美国有70%的人口在农场工作；随着自动化耕种的大面积普及，现在只剩下不到1%。 ——凯文.凯利《必然》\n\n从登录月球到生产纳米机器人，我们已经通过设计并使用各种机器完成了人类原本不可能亲手做到的各种事情。迄今为止，人类从事的简单重复性的工作（如洗衣、耕种、制造等）已经几乎完全被机器接管，人类的工作方向已经转向对机器的研发和维护。而那些需要复杂知识和精密操作的工作（比如驾驶汽车、外科手术等）也正在被机器逐步接管。\n\n洗衣机解放了家庭主妇们的双手，全自动流水线则解雇了工厂里的大部分工人。只有在一些正享受人口红利的发展中国家（比如中国和印度），由于技术引入成本比人工成本高，目前体力劳动者还有一些生存的空间。但技术的成本会不可阻挡地快速持续下降，而人口红利的窗口期将快速消失，拐点很快就会到来。\n\n资本是具有意志的，且不为人性所改变。当产出的质量不变，而技术的成本显著低于人工成本时，几乎所有的体力劳动者都会失业。机器不知疲倦，不会抱怨，干得比人又快又好又省钱，人类怎么可能和机器竞争？\n\n与此同时，人工智能正在将逐渐接管人类的简单重复性思考活动（如寻路、翻译等），人类只需要下达命令、制定原则和做出选择即可。人工智能甚至已经进入了那些被人们认为是“人之所以为人”的领域：写作、编曲、绘画……\n\nGoogle的AlphaGo战胜李世石是一个里程碑式的事件，它证明了人工智能已经可以在人类最擅长的思考领域超越人类。现在最优秀的棋手都在向AI学习下棋，职业棋手和AI进行日常训练成了常态。纯机比纯人强，人机比纯机强，这早已是棋界的共识。\n\n当人工智能在某个思考领域的能力接近或超过人类（这在很多领域已经做到了 ）时，而其成本极其低廉（这是早晚的事）时，在资本意志的作用下，这个领域就会将不可逆地被人工智能迅速占领。我们今天已经习惯了使用计算器来取代大脑进行数字计算，在不远的将来，我们也会习惯将原本需要自己思考的许多问题交给由无数程序组成的人工智能来处理。在可以预见的未来，所有构建在经验和技能基础上的非创造性工作岗位都会消失，人类的工作方向会转变成对人工智能的研发和维护。\n\n人工智能全面普及的时代正在以光一样的速度向我们飞奔，可能下一秒就将我们远远地甩在身后，连车尾灯都看不到。届时，几乎所有的工作都将和人工智能密不可分。只有那些理解人工智能，能够很好地和人工智能合作，并帮助改进人工智能的人，才能在那个时代更好地生存下去。\n\n每个人都应该尽早开始学习编程，我的孩子起步太晚了，我觉得应该在教他们ABC和颜色的时候就开始。——美国第44任总统 奥巴马 [2]\n\n在《未来我们该学什么语言？》一文中，我曾畅想过未来可能出现的脑接口技术，以及与之配套的人机共用结构化语言。届时我们的大脑将直接接入互联网和人工智能，可以瞬间将需要的知识和信息下载到大脑中……我们将成为神一样的存在。但前提是你必须具备编程基础，才能享用这一革命性技术带来的成果。\n\n什么，那时候你还没学会编程？可以洗洗睡了。\n\n我知道你会问……\n\n可是我又不准备当程序员啊，有必要学编程吗？\n\n你可能会开车，还是个老司机，很会享受驾驶的乐趣，但你未必愿意去当一名出租车司机吧？同理，学习编程不一定非要做程序员，但却能使你拥有全新的视角、深入的思维方式和效率优化的思维，这都将成为你重要的软实力。在不久的将来，编程将会变成像英语、驾驶一样人人必备的技能。到那时，你希望自己是一名老司机，还是搭车族呢？\n\n“学这个有用吗？”其实是一个很可怕的想法。由于很多东西现在看起来并没有什么用，大多数人就放弃了学习，而只有少数人会抱着“学学看能有什么用”的念头去尝试。在之后的某一天，真正需要这项技能和知识时，那些选择放弃的人只能感慨“要是当时……就好了！”，而选择学习的人则会惊喜地发现“哇，原来还能用在这里！”……所谓的“惊喜”和“运气”其实就是这样一回事：在不知不觉间，已经提前做好了准备。\n\n那我能不能现在努力赚钱，然后雇一个专业的程序员呢？\n\n不错，你是可以找一个程序员来实现你的想法，但我们之前请秘书时遇到的诸多问题又会接踵而来。更重要的是，如果你不会编程，你可能连个靠谱沾边的想法都提不出来。就好比没有见过汽车的人，只会想着让别人为他造一辆更快的马车。只有在理解了某个事物的原理之后，这个事物的概念才能在你的脑中清晰起来，才能真正融入你的认知结构中。\n\n有了清晰的概念，你才能对其进行思考，判断它能够用来做什么，不能做什么。如果概念不清晰，你甚至都无法讲清楚自己的需求，更难和程序员进行沟通和合作。每一位程序员在面对“给我做一个淘宝”这样的需求时，都会崩溃的。\n\n我的英语很烂，能学会编程吗？\n\n英语不是学习编程的瓶颈，关键在于理解其概念和原理，以及改变思维方式。虽然几乎所有编程语言的关键字都是英语，但常用的关键字也就那么几个，热门语言的相关书籍也都有译版。如果你愿意的话，甚至可以用中文来给程序里的变量、函数和命名。正如只要你认得start / save / load / quit这几个单词，就能去玩英文游戏；只要会说sorry和how much，就能去国外旅游一样。\n\n我已经工作好多年了，现在开始学还来得及吗？该从什么地方开始？\n\n任何时候开始都不晚，当然越早越好。中国第一款杀毒软件KV300的作者王江民，就是38岁才开始学习编程的。至于应该怎么学，从哪里开始，这将是我今年的主要思考方向，敬请期待我的后续系列文章。也许你可以先试试这些网站：\n\nCodeCombat: Learn to Code by Playing a Game ：通过玩游戏来启蒙，有中文版，适合初学者\n\nKhan Academy ：纯英文视频讲解教学，适合学霸和笔记党\n\nLearn to code ：交互式实战，效果最佳，但需有一定基础\n\n后话\n\n曾经何时，想要建立一个网站服务，需要购买动辙数万元的专业服务器，支付昂贵的机房托管和带宽租赁费用，聘请专业的开发人才或团队来研发，再通过广告和运营活动去拉拢用户……门槛如此之高，使绝大多数人望而却步，只有企业才能负担得起。\n\n而现如今，云服务器甚至比家里的宽带都便宜了，各种开源技术唾手可得，各种开放平台提供了免费的用户和渠道，一个APP通过社交网络可能瞬间火遍全国……有了树莓派这样超便宜的卡片电脑，再加上现在各种家电都在向智能化发展，想通过编程在实现自己的一些小创意，真是不要太简单。\n\n我们正身处一个只要愿意思考，就能改变世界的时代。那么你是愿意去改变世界，还是等待被世界改变呢？\n\n参考资料\n\n[1] Steve Jobs - The lost interview (1995), Steve Jobs - The lost interview (1995)\n\n[2] Obama: Everybody\'s Got to Learn How to Code, Obama: Everybody\'s Got to Learn How to Code', '1', '2017-02-04 15:02:52', null, '0', '6', null);
INSERT INTO `question` VALUES ('29', null, 'asd', '\n					<p>请输入内容...</p>\n                为所欲为的人所上交的智商税。指数绝大部分情况下都会走成教科书那样的，只要尊重客观事实，市场还是相当慷慨的。\n20161212一颗大阴线下来，市场先生明明白白告诉你：“我要开始跌了，你当心点，先撤吧“；后来几天市场先生没改变主意，那就不存在返场做多的理由了。看下我以前的回答吧，如果你愿意建立一种客观理性的操作体系。\n\n纯粹的交易（没有内幕消息，不控盘）是一场和内心的博弈，大部分人都是在妄想，其实未来的走势一直明明白白放在眼前，只是大部分人选择视而不见，按自己的所谓“分析”进行投机。\n\n作者：马俊\n链接：https://www.zhihu.com/question/54927090/answer/144858183\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权', '3', '2017-02-11 15:17:45', null, null, null, 'dddd');
INSERT INTO `question` VALUES ('30', null, 'ddddd', 'wwwww', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '用户类型，0-普通用户 | 1-管理员',
  `status` int(1) DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'bin', '123', 'hb', '405622418@qq.com', null, '男', '。。', '1', null);
INSERT INTO `user` VALUES ('2', '2', 'hb', '123', 'hb', '1677323371@qq.com', null, '女', '。。', '0', null);
INSERT INTO `user` VALUES ('3', '3', 'bin2', '1', 'b', '1', null, null, null, null, null);
