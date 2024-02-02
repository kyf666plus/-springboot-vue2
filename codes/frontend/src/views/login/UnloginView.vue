<template>
  <div class="timetable w100 h100">
    <div class="time-b w100">
      <div class="time-detail">{{ startTime }} - {{ endTime }}</div>
      <div class="time-controller">
        <el-button-group>
          <el-button
            type="primary"
            icon="el-icon-arrow-left"
            @click="changeCount(1), getWeek(count)"
          ></el-button>
          <el-button round class="date-btn">本周</el-button>
          <el-button
            type="primary"
            icon="el-icon-arrow-right"
            @click="changeCount(-1), getWeek(count)"
          ></el-button>
        </el-button-group>
      </div>
    </div>
    <div class="timetable-b w100">
      <table class="timetable-content w100">
        <thead>
          <tr>
            <th></th>
            <th v-for="(item1, index1) in weeks" :key="index1">
              {{ "周" + numberToChinease(item1 + 1, "week") }}
            </th>
          </tr>
        </thead>
        <tbody>
          <!-- 分隔栏 -->
          <tr>
            <td colspan="8" class="separator"></td>
          </tr>
          <!-- 课表内容 -->
          <tr v-for="(item2, index2) in maxCourseLength" :key="index2">
            <td>
              <p>{{ "第" + numberToChinease(item2) + "节" }}</p>
            </td>
            <template v-for="(item3, index3) in weeks">
              <td
                :key="index3"
                :rowspan="
                  showData(index3, index2 + 1).subject &&
                  showData(index3, index2).subject ===
                    showData(index3, index2 + 1).subject
                    ? 2
                    : 1
                "
              >
                <div class="dmsjandjs-b">
                  <p>
                    {{ showData(index3, index2).startTime }}
                    {{ showData(index3, index2).startTime ? "-" : "" }}
                    {{ showData(index3, index2).endTime }}
                  </p>
                  <p>{{ showData(index3, index2).subject }}</p>
                  <p>{{ showData(index3, index2).major }}</p>
                  <p>{{ showData(index3, index2).class }}</p>
                </div>
              </td>
            </template>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { weekCourse, colorList } from "./Timetable";
export default {
  data() {
    return {
      startTime: "2022.10.17",
      endTime: "2022.10.23",
      colorList: [],
      weekCourse: [],
      weeks: [],
      maxCourseLength: 0,
      count: 0,
    };
  },
  created() {
    this.weekCourse = weekCourse;
    this.colorList = colorList;
    this.sortData();
    this.init();
    this.getWeek(0);
  },
  methods: {
    changeCount(i) {
      this.count += i;
      return this.count;
    },
    sortData() {
      this.weekCourse.sort((a, b) => {
        return a.week - b.week;
      });
      this.weekCourse.forEach((item) => {
        for (const key in item) {
          if (key === "courses") {
            item[key].sort((a, b) => {
              return a.index - b.index;
            });
          }
        }
      });
    },
    init() {
      this.weeks = [];
      this.maxCourseLength = 0;
      this.weeks = this.weekCourse.map((item) => {
        for (const key in item) {
          if (key === "courses") {
            let max = 0;
            for (let j of item[key]) {
              j.index > this.maxCourseLength &&
                (this.maxCourseLength = j.index);
              j.index > max && (max = j.index);
            }
            if (item[key].length < max) {
              for (let i = 0; i < max; i++) {
                if (!item[key][i] || item[key][i].index != i + 1) {
                  item[key].splice(i, 0, " ");
                }
              }
            }
          }
        }
        return item.week;
      });
    },
    showData(weekIndex, courseNum) {
      if (
        this.weekCourse[weekIndex] &&
        this.weekCourse[weekIndex].courses[courseNum] &&
        this.weekCourse[weekIndex].courses[courseNum].index === courseNum + 1
      ) {
        return this.weekCourse[weekIndex].courses[courseNum];
      }
      return false;
    },
    numberToChinease(n, identifier) {
      const chnArr = [        "零",        "一",        "二",        "三",        "四",        "五",        "六",        "七",        "八",        "九",        "十",        "十一",        "十二",      ];
      return identifier === "week" && (n === 0 || n === 7) ? "日" : chnArr[n];
    },
    getWeek(i) {
      let weekOfDay = parseInt(moment().format("E"));
      let last_monday = moment()
        .subtract(weekOfDay + 7 * i - 1, "days")
        .format("YYYY-MM-DD");
      let last_sunday = moment()
        .subtract(weekOfDay + 7 * (i - 1), "days")
        .format("YYYY-MM-DD");
      this.startTime = last_monday;
      this.endTime = last_sunday;
    },
  },
};
</script>

<style scoped lang="scss">

</style>

