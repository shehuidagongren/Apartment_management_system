<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公寓编号" prop="apartmentId">
        <el-input
          v-model="queryParams.apartmentId"
          placeholder="请输入公寓编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单元编号" prop="unitId">
        <el-input
          v-model="queryParams.unitId"
          placeholder="请输入单元编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="楼层编号" prop="floodId">
        <el-input
          v-model="queryParams.floodId"
          placeholder="请输入楼层编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间编号" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入房间编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="床位编号" prop="bedId">
        <el-input
          v-model="queryParams.bedId"
          placeholder="请输入床位编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="flag">
        <el-select v-model="queryParams.flag" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.apar_apartment_flag"
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
          v-hasPermi="['system:apartment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:apartment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:apartment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:apartment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apartmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="表单编号" align="center" prop="id" />
      <el-table-column label="公寓编号" align="center" prop="apartmentId" />
      <el-table-column label="单元编号" align="center" prop="unitId" />
      <el-table-column label="楼层编号" align="center" prop="floodId" />
      <el-table-column label="房间编号" align="center" prop="roomId" />
      <el-table-column label="床位编号" align="center" prop="bedId" />
      <el-table-column prop="flag" label="状态" >
              <template slot-scope="scope">
                <dict-tag :options="dict.type.apar_apartment_flag" :value="scope.row.flag"/>
              </template>
            </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:apartment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:apartment:remove']"
          >删除</el-button>
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

    <!-- 添加或修改公寓对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公寓编号" prop="apartmentId">
          <el-input v-model="form.apartmentId" placeholder="请输入公寓编号" />
        </el-form-item>
        <el-form-item label="单元编号" prop="unitId">
          <el-input v-model="form.unitId" placeholder="请输入单元编号" />
        </el-form-item>
        <el-form-item label="楼层编号" prop="floodId">
          <el-input v-model="form.floodId" placeholder="请输入楼层编号" />
        </el-form-item>
        <el-form-item label="房间编号" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间编号" />
        </el-form-item>
        <el-form-item label="床位编号" prop="bedId">
          <el-input v-model="form.bedId" placeholder="请输入床位编号" />
        </el-form-item>
        <el-form-item label="状态" prop="flag">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.flag"/>
          </template>

        </el-form-item>
        <el-form-item label="备注" prop="re三mark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listApartment, getApartment, delApartment, addApartment, updateApartment } from "@/api/apartment/apartment";

export default {
  name: "ApartmentInformation",
  dicts: ['apar_apartment_flag'],
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
      // 公寓表格数据
      apartmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apartmentId: null,
        unitId: null,
        floodId: null,
        roomId: null,
        bedId: null,
        flag:null
      },
      // 表单参数
      form: {
      },
      flagOptions: [{
        "label": "使用中",
        "value": 0
      }, {
        "label": "空闲中",
        "value": 1
      }],
      // 表单校验
      rules: {
        apartmentId: [
          { required: true, message: "公寓编号不能为空", trigger: "blur" }
        ],
        unitId: [
          { required: true, message: "单元编号不能为空", trigger: "blur" }
        ],
        floodId: [
          { required: true, message: "楼层编号不能为空", trigger: "blur" }
        ],
        roomId: [
          { required: true, message: "房间编号不能为空", trigger: "blur" }
        ],
        bedId: [
          { required: true, message: "床位编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公寓列表 */
    getList() {
      this.loading = true;
      listApartment(this.queryParams).then(response => {
        this.apartmentList = response.rows;
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
        apartmentId: null,
        unitId: null,
        floodId: null,
        roomId: null,
        bedId: null,
        flag: null,
        remark: null,
        createTime: null,
        updateTime: null
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
      this.title = "添加公寓";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApartment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公寓";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApartment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApartment(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除公寓编号为"' + ids + '"的数据项？').then(function() {
        return delApartment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/apartment/export', {
        ...this.queryParams
      }, `apartment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
