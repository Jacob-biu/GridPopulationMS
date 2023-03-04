<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="pName">
        <el-input
          v-model="queryParams.pName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件类型" prop="idcardType">
        <el-select v-model="queryParams.idcardType" placeholder="请选择证件类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_idcardtype"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码" prop="idcardInfo">
        <el-input
          v-model="queryParams.idcardInfo"
          placeholder="请输入证件号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="pSex">
        <el-select v-model="queryParams.pSex" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="国籍" prop="pNation">
        <el-select v-model="queryParams.pNation" placeholder="请选择国籍" clearable>
          <el-option
            v-for="dict in dict.type.sys_nation"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="职业" prop="pJob">
        <el-select v-model="queryParams.pJob" placeholder="请选择职业" clearable>
          <el-option
            v-for="dict in dict.type.sys_job"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="住址" prop="pAddress">
        <el-input
          v-model="queryParams.pAddress"
          placeholder="请输入住址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="pPhone">
        <el-input
          v-model="queryParams.pPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="政治面貌" prop="pParty">
        <el-select v-model="queryParams.pParty" placeholder="请选择政治面貌" clearable>
          <el-option
            v-for="dict in dict.type.sys_politic"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="家庭人数" prop="pFamilynum">
        <el-select v-model="queryParams.pFamilynum" placeholder="请选择家庭人数" clearable>
          <el-option
            v-for="dict in dict.type.sys_familynum"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否高龄" prop="pOlder">
        <el-select v-model="queryParams.pOlder" placeholder="请选择是否高龄" clearable>
          <el-option
            v-for="dict in dict.type.sys_older"
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
          v-hasPermi="['management:residentinfo:add']"
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
          v-hasPermi="['management:residentinfo:edit']"
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
          v-hasPermi="['management:residentinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['management:residentinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="residentinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="pName" />
      <el-table-column label="证件类型" align="center" prop="idcardType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_idcardtype" :value="scope.row.idcardType"/>
        </template>
      </el-table-column>
      <el-table-column label="证件号码" align="center" prop="idcardInfo" />
      <el-table-column label="性别" align="center" prop="pSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.pSex"/>
        </template>
      </el-table-column>
      <el-table-column label="国籍" align="center" prop="pNation">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_nation" :value="scope.row.pNation"/>
        </template>
      </el-table-column>
      <el-table-column label="职业" align="center" prop="pJob">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_job" :value="scope.row.pJob"/>
        </template>
      </el-table-column>
      <el-table-column label="住址" align="center" prop="pAddress" />
      <el-table-column label="联系电话" align="center" prop="pPhone" />
      <el-table-column label="政治面貌" align="center" prop="pParty">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_politic" :value="scope.row.pParty"/>
        </template>
      </el-table-column>
      <el-table-column label="家庭人数" align="center" prop="pFamilynum">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_familynum" :value="scope.row.pFamilynum"/>
        </template>
      </el-table-column>
      <el-table-column label="是否高龄" align="center" prop="pOlder">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_older" :value="scope.row.pOlder"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="pNotice" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['management:residentinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['management:residentinfo:remove']"
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

    <!-- 添加或修改居民信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="pName">
          <el-input v-model="form.pName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="证件类型" prop="idcardType">
          <el-select v-model="form.idcardType" placeholder="请选择证件类型">
            <el-option
              v-for="dict in dict.type.sys_idcardtype"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码" prop="idcardInfo">
          <el-input v-model="form.idcardInfo" placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="性别" prop="pSex">
          <el-select v-model="form.pSex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="国籍" prop="pNation">
          <el-select v-model="form.pNation" placeholder="请选择国籍">
            <el-option
              v-for="dict in dict.type.sys_nation"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职业" prop="pJob">
          <el-select v-model="form.pJob" placeholder="请选择职业">
            <el-option
              v-for="dict in dict.type.sys_job"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="住址" prop="pAddress">
          <el-input v-model="form.pAddress" placeholder="请输入住址" />
        </el-form-item>
        <el-form-item label="联系电话" prop="pPhone">
          <el-input v-model="form.pPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="政治面貌" prop="pParty">
          <el-select v-model="form.pParty" placeholder="请选择政治面貌">
            <el-option
              v-for="dict in dict.type.sys_politic"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="家庭人数" prop="pFamilynum">
          <el-select v-model="form.pFamilynum" placeholder="请选择家庭人数">
            <el-option
              v-for="dict in dict.type.sys_familynum"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否高龄" prop="pOlder">
          <el-select v-model="form.pOlder" placeholder="请选择是否高龄">
            <el-option
              v-for="dict in dict.type.sys_older"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="pNotice">
          <el-input v-model="form.pNotice" type="textarea" placeholder="请输入内容" />
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
import { listResidentinfo, getResidentinfo, delResidentinfo, addResidentinfo, updateResidentinfo } from "@/api/management/residentinfo";

export default {
  name: "Residentinfo",
  dicts: ['sys_politic', 'sys_older', 'sys_user_sex', 'sys_idcardtype', 'sys_job', 'sys_familynum', 'sys_nation'],
  data() {
    return {

      // 默认是新增channel
      isAdd: true,
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
      // 居民信息表格数据
      residentinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pName: null,
        idcardType: null,
        idcardInfo: null,
        pSex: null,
        pNation: null,
        pJob: null,
        pAddress: null,
        pPhone: null,
        pParty: null,
        pFamilynum: null,
        pOlder: null,
        pNotice: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        idcardType: [
          { required: true, message: "证件类型不能为空", trigger: "change" }
        ],
        idcardInfo: [
          { required: true, message: "证件号码不能为空", trigger: "blur" }
        ],
        pSex: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
        pNation: [
          { required: true, message: "国籍不能为空", trigger: "change" }
        ],
        pAddress: [
          { required: true, message: "住址不能为空", trigger: "blur" }
        ],
        pPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
        pOlder: [
          { required: true, message: "是否高龄不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询居民信息列表 */
    getList() {
      this.loading = true;
      listResidentinfo(this.queryParams).then(response => {
        this.residentinfoList = response.rows;
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
        pName: null,
        idcardType: null,
        idcardInfo: null,
        pSex: null,
        pNation: null,
        pJob: null,
        pAddress: null,
        pPhone: null,
        pParty: null,
        pFamilynum: null,
        pOlder: null,
        pNotice: null
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
      this.ids = selection.map(item => item.idcardInfo)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.isAdd = true;
      this.title = "添加居民信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const idcardInfo = row.idcardInfo || this.ids
      getResidentinfo(idcardInfo).then(response => {
        this.form = response.data;
        this.open = true;
        this.isAdd = false;
        this.title = "修改居民信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.isAdd == false) {
            updateResidentinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResidentinfo(this.form).then(response => {
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
      const idcardInfos = row.idcardInfo || this.ids;
      this.$modal.confirm('是否确认删除居民信息编号为"' + idcardInfos + '"的数据项？').then(function() {
        return delResidentinfo(idcardInfos);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('management/residentinfo/export', {
        ...this.queryParams
      }, `residentinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
