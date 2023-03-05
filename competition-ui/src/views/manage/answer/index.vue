<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="answerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="competitionId"/>
      <el-table-column label="竞赛名称" align="center" prop="competitionName"/>
      <el-table-column label="参赛人员" align="center" prop="contestants"/>
      <el-table-column label="题目" align="center" prop="questionName"/>
      <el-table-column label="题目答案" align="center" prop="questionAnswer"/>
      <el-table-column label="得分" align="center" prop="score"/>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:check:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:check:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改添加赛事对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item  label="审核状态" align="center" prop="checkStatus">
          <el-select v-model="form.checkStatus" placeholder="请选择审核状态">
            <el-option
              v-for="dict in dict.type.sys_check_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
  import {answerList, getOnline, delOnline, addOnline, updateOnline} from "@/api/manage/online";

  export default {
    name: "check",
    dicts: ['sys_competition_type','sys_check_status'],
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
        // 总条数
        total: 0,
        // 添加赛事表格数据
        answerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        ifdisabled: true,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          competitionName: null,
          enrollEndTime: null,
          competitionTime: null,
          personnelLimit: null,
          competitionType: null,
        },
        // 表单参数
        form: {},
        competitionForm: [],
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询添加赛事列表 */
      getList() {
        this.loading = true;
        answerList(this.queryParams).then(response => {
          this.answerList = response.rows;
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
          enrollEndTime: null,
          competitionTime: null,
          personnelLimit: null,
          competitionType: null,
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加添加赛事";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getOnline(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改添加赛事";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateOnline(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addOnline(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除添加赛事编号为"' + ids + '"的数据项？').then(function () {
          return delOnline(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('manage/online/export', {
          ...this.queryParams
        }, `online_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
