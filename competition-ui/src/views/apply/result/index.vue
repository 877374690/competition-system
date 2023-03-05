<template>
  <div class="app-container">

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="竞赛名称" align="center" prop="competitionName" />
      <el-table-column label="简介" align="center" prop="competitionDesc" />
      <el-table-column label="比赛类型" align="center" prop="applyType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_competition_type" :value="scope.row.applyType"/>
        </template>
      </el-table-column>
      <el-table-column label="获奖人员" align="center" prop="contestants" />
      <el-table-column label="得分" align="center" prop="score" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改比赛申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="竞赛名称" align="center" prop="competitionName" >
          <el-select v-model="form.competitionName" placeholder="请输入赛事名称" style="margin-left: -160px;">
            <el-option
              v-for="(item,index) of competitionList"
              :key="index"
              :label="item.competitionName"
              :value="item.competitionName"
              style="width: 100px"
            >{{item.competitionName}}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="简介" prop="competitionDesc">
          <el-input v-model="form.competitionDesc" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="获奖人员" prop="contestants">
          <el-input v-model="form.contestants" placeholder="请输入获奖人员" />
        </el-form-item>
        <el-form-item label="题目答案" prop="answer">
          <editor v-model="form.answer" :min-height="192"/>
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
import { listApplyResult, getApply, delApply, addApply, updateApply,listCompetition } from "@/api/apply/works";

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
  methods: {

    getCompetitionList(type) {
      listCompetition(type).then(response => {
        this.competitionList = response;
      });
    },
    /** 查询比赛申请列表 */
    getList() {
      this.loading = true;
      listApplyResult(this.queryParams).then(response => {
        this.applyList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        this.title = "修改比赛申请";
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
