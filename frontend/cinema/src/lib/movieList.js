/*
  票房和喜欢数这连个字段没有填真实值
  注意导演和地区是list
  解析出来的日期比填进去的早一天，目前把日期填晚了一天
*/

export const movies = [
  {
    id: 1,
    name: "一秒钟",
    types: ["剧情", "犯罪"],
    location: ["中国大陆"],
    time: 104,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p1.meituan.net/movie/13cb85d810b0530b951625430c2f35044951112.jpg@464w_644h_1e_1c",
    score: 8.7,
    like: 0,
    startDay: new Date(2020, 10, 28),
    description: `二十世纪七十年代中期，看电影是人们重要的精神享受。
                      西北某地，没赶上场次的张九声怅然若失，他悄悄从农场溜出来，就是为了看一场电影，
                      那是一盘宣传性质的新闻胶卷带，他相信在那盘胶片中有他已经去世的女儿的“一秒钟”影像。
                      为了找到这胶片，他开始疯狂起来。此时他恰逢流浪儿刘闺女，冤家路窄的两个人，原来同是天涯沦落人。
                      一个是失去女儿的父亲，一个是失去父爱的女儿；一个想要看某一场电影，一个想要12.5米废胶片。
                      目的完全相反，最后却殊途同归，因为一场电影结下了不解之缘。`,
    director: ["张艺谋"],
    stars: [
      {
        star: "张译",
        role: "张久声"
      },
      {
        star: "刘浩存",
        role: "刘闺女"
      },
      {
        star: "范伟",
        role: "范电影"
      },
      {
        star: "余皑磊",
        role: "崔干事"
      }
    ]
  },
  {
    id: 2,
    name: "如果声音不记得",
    types: ["爱情", "青春", "奇幻"],
    location: ["中国大陆"],
    time: 102,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p1.meituan.net/movie/f6ec2a022d3644ef493f881d359f65303190471.jpg@464w_644h_1e_1c",
    score: 8.0,
    like: 5000, // 以k为单位
    startDay: new Date(2020,11,5),
    description: `如果你喜欢的女孩，得了抑郁症，你该怎么办？
                辛唐（孙晨竣 饰）拥有通过声音给他人制造快乐的能力，
                但对同一人使用三次后，性命就会和此人绑定，只有对方开心，
                辛唐才能活命。偶然，辛唐救下准备自杀的同校网络红人吉择（章若楠 饰），
                两人借此绑定。吉择表面开朗，但实际患了抑郁症。
                辛唐最初为了活下去，费尽心思让吉择开心，而后续也真的投入深情。
                遗憾辛唐的秘密总会败露，而吉择暗黑的过往也在网络上被人揭开....愿爱情的温暖，能治愈抑郁的青春`,
    director: ["落落"],
    stars: [
      {
        star: "章若楠",
        role: "吉择"
      },
      {
        star: "孙晨竣",
        role: "辛唐"
      },
      {
        star: "王彦霖",
        role: "尉迟瞳"
      },
      {
        star: "严屹宽",
        role: "辛唐的父亲"
      }
    ]
  },
  {
    id: 3,
    name: "赤狐书生",
    types: ["奇幻", "古装", "喜剧"],
    location: ["中国大陆"],
    time: 125,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p0.meituan.net/movie/303c2e671cc4df875c151d688ecbd8962085989.jpg@464w_644h_1e_1c",
    score: 7.7,
    like: 0,
    startDay: new Date(2020,11,5),
    description: `清贫书生王子进（陈立农 饰）进京赶考，被来到人界取丹的狐妖白十三（李现 饰）盯上。
              为了骗取书生信任，狐妖联合各路妖鬼，设下重重陷阱。一场奇幻旅程等待着他们……`,
    director: ["宋灏霖", "伊力奇"],
    stars: [
      {
        star: "陈立农",
        role: "王子进"
      },
      {
        star: "李现",
        role: "白十三"
      },
      {
        star: "哈妮克孜",
        role: "莲花精"
      }
    ]
  },
  {
    id: 4,
    name: "除暴",
    types: ["犯罪", "剧情", "动作"],
    location: ["中国香港", "中国大陆"],
    time: 95,
    boxOffice: 96288, // 以k为单位
    poster:
      "https://p1.meituan.net/movie/38dd31a0e1b18e1b00aeb2170c5a65b13885486.jpg@464w_644h_1e_1c",
    score: 8.6,
    like: 0,
    startDay: new Date(2020, 10, 21),
    description: `上世纪90年代，刑警钟诚受命追捕悍匪集团“老鹰帮”。这群悍匪犯下惊天连环劫案，
                训练有素且纪律严明，首领张隼更屡次恶意挑衅，矛头直指钟诚。
                为将“老鹰帮”捉拿归案，钟诚带领刑警小队咬死不放，誓与恶势力斗争到底。
                数年间，警匪上演了一次次紧张刺激的较量，悍匪愈加猖獗，警方步步逼近，双方展开殊死对决……`,
    director: ["刘浩良"],
    stars: [
      {
        star: "王千源",
        role: "钟诚"
      },
      {
        star: "吴彦祖",
        role: "张隼"
      }
    ]
  },
  {
    id: 5,
    name: "疯狂原始人",
    types: ["喜剧", "动画", "冒险"],
    location: ["美国"],
    time: 96,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p1.meituan.net/moviemachine/6fb1855f663f9856bfc47b7d013e0474651230.jpg@464w_644h_1e_1c",
    score: 9.1,
    like: 0,
    startDay: new Date(2020,10,28),
    description: `影片主要讲述了咕噜家族需要一个新的栖息地。于是，这一史前首个家族踏上征途，
                  寻找一片可以称之为“家”的安定之所。当他们在一片高墙内，发现一块符合他们全部设想的天堂般的田园时，
                  他们以为一切迎刃而解了……除了一个小问题：已经有另一支家族生活在这里。他们就是：
                  文明人家族。 文明人一家（重点在“文明”）有着精心建造的树屋、令人羡艳的发明和淡水灌溉的丰产农田，
                  在进化阶梯上比咕噜家族高了好几个层次。在他们邀请有史以来第一群访客咕噜一家进门后，没过多久，
                  原始家庭和现代家庭之间的矛盾便迅速升级。 就在一切看起来都将要失控时，
                  一个新的威胁促使两个家庭冲出维护他们安全的高墙之外，开展了一场史诗级的冒险，
                  也迫使他们拥抱彼此的差异，相互汲取力量，共同缔造未来。`,
    director: ["乔尔·克劳福德"],
    stars: [
      {
        star: "艾玛·斯通",
        role: "小伊 Eep"
      },
      {
        star: "里古拉斯·凯奇",
        role: "瓜哥 Grug"
      },
      {
        star: "瑞安·雷诺兹",
        role: "盖伊 Guy"
      },
      {
        star:"凯瑟琳·基纳",
        role: "巫嘎 Ugga"
      }
    ]
  },
  {
    id: 6,
    name: "野性的呼唤",
    types: ["剧情", "冒险"],
    location: ["美国"],
    time: 100,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p0.meituan.net/moviemachine/cf7d6942f2aa9189cce20519b490b6b1879487.jpg@464w_644h_1e_1c",
    score: 9.2,
    like: 0,
    startDay: new Date(2020,10,14),
    description: `巴克是一只体型庞大、脾气暴躁的圣伯纳犬与苏格兰牧羊犬混血，
                  它和米勒法官一家在美国加州的圣克拉拉市一起居住。但巴克不幸被绑架，
                  并被卖了出去，成了一只奴役狗。后来巴克成了佩罗（奥马·希 饰）的狗拉雪橇队中一员，
                  佩罗是一个生性温和的邮递员，他的工作任务是在被冰雪覆盖、自然环境严酷恶劣的育空冻原上投递信件。
                  巴克很快融入到这只狗狗团队中，而且逐渐变成了狗狗们的领袖，但是电报系统的出现，
                  让佩罗的工作变得过时了。后来巴克又遇到了一系列新的主人，这里面有残酷的采矿者哈尔（丹·史蒂文斯 饰），
                  还有善良的好人约翰·桑顿（哈里森·福特 饰）。巴克很喜欢约翰，随着约翰旅程的持续进行，
                  本来是被高度驯化的巴克发现自己逐渐显露出动物天性，而且越来越多展现出动物冲动的本性，并被其所驱使。`,
    director: ["克里斯·桑德斯"],
    stars: [
      {
        star: "哈里森·福特",
        role: "John Thornton"
      },
      {
        star: "丹·史蒂文斯",
        role: "Hal"
      },
      {
        star: "凯伦·吉兰",
        role: "Mercedes"
      },
      {
        star: "珍·路易莎·凯利",
        role: "Katie Miller"
      }
    ]
  },
  {
    id: 7,
    name: "哆啦A梦：大雄的新恐龙",
    types: ["动画", "冒险", "剧情"],
    location: ["日本"],
    time: 111,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p1.meituan.net/movie/7335afbfd567ca5261d28a11d5bab706106991.jpg@464w_644h_1e_1c",
    score: -1,
    like: 0,
    startDay: new Date(2020,11,12),
    description: `大雄把恐龙展发现的化石带回家，借助哆啦A梦的时光包袱竟然成功孵化出两只从未被发现过的长着翅膀的新恐龙。
                大雄和小伙伴们决定将小恐龙们带回到属于它们的时代，一场惊心动魄的白垩纪大冒险即将上演。
                本片是《哆啦A梦》漫画连载50周年纪念作品，同时也是《哆啦A梦》系列电影的第40部作品，
                将于2020年12月11日全国公映。全新的故事，全新的大雄，全新的冒险，全新出发！`,
    director: ["今井一晓"],
    stars: [
      {
        star: "水田山葵",
        role: "哆啦A梦"
      },
      {
        star: "大原惠美",
        role: "大雄"
      },
      {
        star: "嘉数由美",
        role: "静香"
      },
      {
        start: "木村昴",
        role: "胖虎"
      }
    ]
  },
  {
    id: 8,
    name: "紧急救援",
    types: ["剧情", "灾难", "动作"],
    location: ["中国大陆"],
    time: 134,
    boxOffice: 96280, // 以k为单位
    poster:
      "https://p0.meituan.net/moviemachine/70be7bb482b0edc1c74f93e02d95a88a3068051.jpg@464w_644h_1e_1c",
    score: -1,
    like: 0,
    startDay: new Date(2020,11,19),
    description: `倾覆沉没的钻井平台，顺流直冲的运油车头，直坠入海的满载客机。
                  交通海上应急反应特勤队队长高谦（彭于晏 饰）、机长方宇凌（辛芷蕾 饰）
                  和绞车手赵呈（王彦霖 饰）一次次带领队伍第一时间抵达，站在水火咆哮的最前面，
                  守在危急撤离的最后面，用生命对抗天灾人祸。但在自然面前，特勤员毕竟没有超能力，
                  血肉之躯踩在死亡边缘，真实的恐惧无数次让这些斗士颤抖、无助和气馁。
                  而海上救援的字典里没有“退缩”。当你听见旋翼的轰鸣、洪亮的汽笛、
                  马达的飞驰，那是他们无惧艰难险阻，舍己为人，谱写的一曲英雄之歌。`,
    director: ["林超贤"],
    stars: [
      {
        star: "彭于晏",
        role: "高谦"
      },
      {
        star: "王彦霖",
        role: "赵呈"
      },
      {
        star: "辛芷蕾",
        role: "方宇凌"
      },
      {
        start: "蓝盈莹",
        role: "文珊"
      }
    ]
  },
  {
    id: 9,
    name: "城市猎人",
    types: ["喜剧", "动作", "爱情"],
    location: ["法国"],
    time: 91,
    boxOffice: 96281, // 以k为单位
    poster:
      "https://p0.meituan.net/movie/80b9ec1d4bc6bb7e727a252d9e4e54432714531.jpg@464w_644h_1e_1c",
    score: -1,
    like: 0,
    startDay: new Date(2020,11,12),
    description: `北条司著名漫画《城市猎人》的法国真人版，Nicky Larson（寒羽良）是人称“城市猎人”的私家侦探，
                  他集幽默、正义、欣赏美女的天性于一身，擅长处理五花八门的棘手任务，深受委托人信赖。
                  阿香与寒羽良为多年搭档，两人看似相爱相杀，实则互相关心，有一天，阿香为两人接来了
                  一单神秘生意——保护一瓶无论谁用了都会魅力大增的香水，神探寒羽良和阿香即将开启新的
                  一次冒险旅程，这一切背后交织着怎样的阴谋，又有多少人卷入了这场啼笑皆非的大战中....`,
    director: ["菲利普·拉肖"],
    stars: [
      {
        star: "菲利普·拉肖",
        role: "Nicky Larson"
      },
      {
        star: "艾洛蒂·丰唐",
        role: "Laura"
      },
      {
        star: "塔雷克·布达里",
        role: "Pancho"
      },
      {
        start: "朱利安·阿鲁蒂",
        role: "Skippy"
      }
    ]
  },
  {
    id: 10,
    name: "少女佳禾",
    types: ["剧情"],
    location: ["中国大陆"],
    time: 100,
    boxOffice: 96277, // 以k为单位
    poster:
      "https://p0.meituan.net/movie/59b5174b5eb82aeed2cb738eb261b18d797572.jpg@464w_644h_1e_1c",
    score: -1,
    like: 0,
    startDay: new Date(2020,11,12),
    description: `两年前，母亲（李娟 饰）的意外遇害，令李佳禾（邓恩熙 饰）的生活彻底“黑暗”起来。
                  面对同学的霸凌和懦弱的父亲（吴国华 饰），少女佳禾逐渐养成了有仇必报的刚硬性格。
                  一次车祸，她撞见了在工读学校表现良好而提前归来的“少年犯”于镭（李感 饰），
                  寻求律师无果后，佳禾决心要在14岁生日之前完成自己的“未成年复仇计划”。
                  她跟踪并伺机接近于镭，学喝酒、泡网吧，甚至和他身边的社会青年混成一片。
                  终于，当机会到来时，她一步步陷入了危险与犯罪的边缘……`,
    director: ["周笋"],
    stars: [
      {
        star: "邓恩熙",
        role: "李佳禾"
      },
      {
        star: "李感",
        role: "于镭"
      },
      {
        star: "李依宸",
        role: "曼莉"
      }
    ]
  }
];

export let moviesOnScreen = movies;

export function getAllMovies() {
  return movies;
}

export function getAllMovieTypes() {
  let types = [];
  movies.map(item => types.push(...item.types));
  types = [...new Set(types)];
  return types;
}

export function getAllMovieLocations() {
  let locations = [];
  movies.map(item => locations.push(...item.location));
  locations = [...new Set(locations)];
  return locations;
}

export function getMoviesByKeyword(keyword) {
  const ans = [];

  movies.map(item => {
    if (item.name.includes(keyword)) ans.push(item);
  });

  return ans;
}

export function getCurrentMovies() {
  const ans = [];
  const today = new Date();

  movies.map(item => {
    if (item.startDay.getTime() < today.getTime()) ans.push(item);
  });

  moviesOnScreen = ans;
  return ans;
}

export function getFutureMovies() {
  const ans = [];
  const today = new Date();

  movies.map(item => {
    if (item.startDay.getTime() > today.getTime()) ans.push(item);
  });

  moviesOnScreen = ans;
  return ans;
}

export function sortMoviesByHeat(active) {
  // let ans = [];

  // if (active === "current") ans = getCurrentMovies();
  // else getFutureMovies();
  moviesOnScreen.sort((a, b) => b.boxOffice - a.boxOffice);
  return moviesOnScreen;
}

export function sortMoviesByDate(active) {
  // let ans = [];

  // if (active === "current") ans = getCurrentMovies();
  // else getFutureMovies();

  moviesOnScreen.sort((a, b) => a.startDay.getTime() - b.startDay.getTime());
  return moviesOnScreen;
}

export function sortMoviesByScore() {
  // const currentMovies = getCurrentMovies();
  moviesOnScreen.sort((a, b) => b.score - a.score);
  return moviesOnScreen;
}

export function filterMovies(type, location, active) {
  let moviesToFilter = active === "current"? getCurrentMovies() : getFutureMovies();
  let ans = [];

  if (type !== "all" && location !== "all") {
    moviesToFilter = filterMoviesByType(moviesToFilter, type);
    ans = filterMoviesByLocation(moviesToFilter, location);
  }
  else if (type === "all" && location !== "all") {
    ans = filterMoviesByLocation(moviesToFilter, location);
  }
  else if (type !== "all" && location === "all") {
    ans = filterMoviesByType(moviesToFilter, type);
  }
  else {
    ans = moviesToFilter;
  }

  moviesOnScreen = ans;
  return ans;
}

function filterMoviesByType(movieList, type) {
  let ans = [];

  ans = movieList.filter(item => item.types.includes(type));

  return ans;
}

function filterMoviesByLocation(movieList, location) {
  console.log(movieList, location);
  let ans = [];

  ans = movieList.filter(item => item.location.includes(location));

  return ans;
}