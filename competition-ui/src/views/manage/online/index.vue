<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="竞赛名称" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入竞赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名截止时间" prop="enrollEndTime">
        <el-date-picker clearable
                        v-model="queryParams.enrollEndTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择报名截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="比赛日期" prop="competitionTime">
        <el-date-picker clearable
                        v-model="queryParams.competitionTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择比赛日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="参赛人员限制" prop="personnelLimit">
        <el-input
          v-model="queryParams.personnelLimit"
          placeholder="请输入参赛人员限制"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛类型" prop="competitionType">
      <el-select v-model="queryParams.competitionType" placeholder="请选择比赛类型" clearable>
        <el-option
          v-for="dict in dict.type.sys_competition_type"
          :key="dict.value"
          :label="dict.label"
          :value="dict.value"
        />
      </el-select>
    </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:online:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:online:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:online:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:online:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="onlineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="id"/>
      <el-table-column label="竞赛名称" align="center" prop="competitionName"/>
      <el-table-column label="报名截止时间" align="center" prop="enrollEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛日期" align="center" prop="competitionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="参赛人员限制" align="center" prop="personnelLimit"/>
      <el-table-column label="比赛类型" align="center" prop="competitionType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_competition_type" :value="scope.row.competitionType"/>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="checkStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_check_status" :value="scope.row.checkStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:online:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:online:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
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
        <el-form-item label="竞赛名称" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入竞赛名称"/>
        </el-form-item>
        <el-form-item label="报名截止时间" prop="enrollEndTime">
          <el-date-picker clearable
                          v-model="form.enrollEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择报名截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛日期" prop="competitionTime">
          <el-date-picker clearable
                          v-model="form.competitionTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择比赛日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="参赛人员限制" prop="personnelLimit">
          <el-input v-model="form.personnelLimit" placeholder="请输入参赛人员限制"/>
        </el-form-item>
        <el-form-item label="比赛类型" prop="competitionType">
          <el-select v-model="form.competitionType" placeholder="请选择比赛类型">
            <el-option
              v-for="dict in dict.type.sys_competition_type"
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
  import {listOnline, getOnline, delOnline, addOnline, updateOnline} from "@/api/manage/online";

  export default {
    name: "Online",
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
        onlineList: [],
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
        listOnline(this.queryParams).then(response => {
          this.onlineList = response.rows;
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
