<template>
  <div class="app-container">
  <!--  <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="竞赛名称" align="center" prop="competitionName" />
      <el-table-column label="参赛人数统计" align="center" prop="contestants" />
    </el-table>-->
    <div id="chartBar" style="width: 800px;height:800px;margin:0 auto"></div>
    <!--<pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"











      @pagination="getList"
    />-->

    <!-- 添加或修改比赛申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入得分" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCountApply, getApply, delApply, addApply, updateApply,listCompetition } from "@/api/apply/works";
import echarts from 'echarts'
export default {
  name: "answer",
  dicts: ['sys_competition_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      competitionList: [],
      // 总条数
      total: 0,
      // 比赛申请表格数据
      applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionName: null,
        competitionDesc: null,
        contestants: null,
        introduction: null,
        applyType: null,
        answer: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getCompetitionList("1");
  },
  mounted() {

  },
  computed:{
    scrollerWidth: function() {
      console.log((window.innerWidth - 46 -50) + 'px')
      return (window.innerWidth - 46 -50) + 'px';
    },
    scrollerHeight: function() {
      console.log((window.innerHeight - 46 -50) + 'px')
      return (window.innerHeight - 46 -50) + 'px';
    }

  },
  methods: {
    renderChart(xData,yData){
      let dom = document.getElementById('chartBar')
      let myChart = echarts.init(dom)
      let option = {
        xAxis: {
          type: 'category',
          data: xData,
          axisLabel:{
            rotate : 60
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: yData,
            type: 'bar',
            barWidth:'20%',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      };
      myChart.setOption(option)
    },
    getCompetitionList(type) {
      listCompetition(type).then(response => {
        this.competitionList = response;
      });
    },
    /** 查询比赛申请列表 */
    getList() {
      this.loading = true;
      listCountApply(this.queryParams).then(response => {
        this.applyList = response.rows;
        this.total = response.total;
        this.loading = false;
        let xData = this.applyList.map(item=>item.competitionName)
        let yData = this.applyList.map(item=>item.contestants)
        this.renderChart(xData,yData)
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        competitionName: null,
        competitionDesc: null,
        contestants: null,
        introduction: null,
        applyType: null,
        answer: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加比赛申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApply(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改得分";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.applyType = "1";
            addApply(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除比赛申请编号为"' + ids + '"的数据项？').then(function() {
        return delApply(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('competition/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
