<template>
  <el-tabs v-model="activeName" @tab-click="switchTab" class="tab">
    <div class="filter-bar">
      <el-card class="box-card">
        <el-row class="type">
          <el-col :offset="1" :span="1" class="tag-title">类型:</el-col>
          <el-col :span="20">
            <ul class="list">
              <li :class="{ active: currentTypeIdx === -1 }">全部</li>
              <li
                v-for="(type, index) in this.allTypes"
                :class="{ active: currentTypeIdx === index }"
                :key="index"
                @click="filterType(index)"
              >
                {{ type }}
              </li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="location">
          <el-col :offset="1" :span="1" class="tag-title">区域:</el-col>
          <el-col :span="20">
            <ul class="list">
              <li :class="{ active: currentLocationIdx === -1 }">全部</li>
              <li
                v-for="(location, index) in this.allLocations"
                :class="{ active: currentLocationIdx === index }"
                :key="index"
                @click="filterLocation(index)"
              >
                {{ location }}
              </li>
            </ul>
          </el-col>
        </el-row>
      </el-card>
    </div>

    <div class="sort">
      <el-radio-group v-model="radio">
        <el-radio :label="0">按热度排序</el-radio>
        <el-radio :label="1">按时间排序</el-radio>
        <el-radio :label="2" v-if="activeName === 'current'">按评分排序</el-radio>
      </el-radio-group>
    </div>

    <el-tab-pane label="正在热映" name="current">正在热映</el-tab-pane>
    <el-tab-pane label="即将上映" name="future">即将上映</el-tab-pane>
  </el-tabs>
</template>
<script>
export default {
  data() {
    return {
      activeName: "current",
      allTypes: [
        "爱情",
        "喜剧",
        "动画",
        "剧情",
        "恐怖",
        "惊悚",
        "科幻",
        "动作",
        "悬疑",
        "犯罪",
        "冒险",
        "战争",
        "奇幻",
        "运动",
        "家庭",
        "古装",
        "武侠",
        "西部",
        "历史",
        "传记",
        "歌舞",
      ],
      allLocations: [
        "爱情",
        "喜剧",
        "动画",
        "剧情",
        "恐怖",
        "惊悚",
        "科幻",
        "动作",
        "悬疑",
        "犯罪",
        "冒险",
        "战争",
        "奇幻",
        "运动",
        "家庭",
        "古装",
        "武侠",
      ],
      currentTypeIdx: -1,
      currentLocationIdx: -1,
      radio: 0
    };
  },
  methods: {
    switchTab(tab, event) {
      console.log(event.target);
    },

    filterType(idx) {
      if (idx === null) {
        this.currentTypeIdx = -1;
      } else {
        this.currentTypeIdx = idx;
      }
    },

    filterLocation(idx) {
      if (idx === null) {
        this.currentLocationIdx = -1;
      } else {
        this.currentLocationIdx = idx;
      }
    },
  },
};
</script>

<style>
.active {
  background-color: #ffb400;
  border-radius: 20px;
}

.el-tabs__nav-scroll {
  display: flex;
  justify-content: center;
}

.tab {
  margin-top: 30px;
}

.tab .filter-bar {
  margin-bottom: 15px;
}

.tab .filter-bar .box-card {
  padding: 0 20px;
}

.tab .filter-bar .box-card .tag-title {
  color: #999;
  line-height: 24px;
  height: 24px;
  font-size: 14px;
}

.tab .filter-bar .box-card .list {
  display: inline-block;
  padding: 0;
  margin: 0;
}

.tab .filter-bar .type {
  border-bottom: 1px dotted #e5e5e5;
  padding-bottom: 15px;
}

.tab .filter-bar .location {
  padding-top: 15px;
}

.tab .filter-bar li {
  text-decoration: none;
  display: inline-block;
  padding: 3px 9px;
  margin-left: 12px;
  font-size: 14px;
  cursor: pointer;
}

.tab .sort {
  /* background-color: aquamarine; */
  margin: 50px 0 10px 0;
}
</style>