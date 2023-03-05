<template>
  <div class="app-container">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="答题类赛事" name="first">
        <div class="comtit">
          <div>题项管理</div>
          <el-button type="primary" @click="handleChange">编 辑</el-button>
        </div>
        <div class="lowTit" style="width:80%"> 赛事
          <el-select v-model="competitionId" placeholder="请选择赛事">
            <el-option v-for="(item,index) of competitionList" :key="index" :label="item.competitionName"
                       :value="item.id">{{item.competitionName}}
            </el-option>
          </el-select>
        </div>
        <el-row v-for="(item, index) in creditForm" :key="item.id" class="mt">
          <el-col :span="2" :offset="1" class="labelTit" style="text-indent: 10px">
            {{ index + 1 }}、题项：
          </el-col>
          <el-col :span="6">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionName"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
          <el-col :span="2" class="labelTit"> 题目答案：</el-col>
          <el-col :span="5">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionAnswer"
              placeholder="请输入题目答案"
            ></el-input>
          </el-col>
          <el-col :span="2" class="labelTit"> 分值：</el-col>
          <el-col :span="2">
            <el-input
              :disabled="ifdisabled"
              v-model="item.score"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
          <el-col :span="4" class="pl">
            <el-button
              :disabled="ifdisabled"
              v-if="index === creditForm.length - 1"
              type="primary"
              @click="handleAddCredit"
            >新 增
            </el-button
            >
            <el-button
              :disabled="ifdisabled"
              type="danger"
              @click="handleDelCredit(index)"
            >删 除
            </el-button
            >
          </el-col>
        </el-row>
        <el-col :span="24" :offset="12" class="mt">
          <el-button v-if="!ifdisabled" type="primary" @click="handleEdit()"
          >确 认
          </el-button
          >
        </el-col>
      </el-tab-pane>
      <el-tab-pane label="进度类赛事" name="second">
        <div class="comtit">
          <div>阶段管理</div>
          <el-button type="primary" @click="handleChange">编 辑</el-button>
        </div>
        <div class="lowTit" style="width:80%"> 赛事
          <el-select v-model="competitionId1" placeholder="请选择赛事">
            <el-option v-for="(item,index) of competitionList1" :key="index" :label="item.competitionName"
                       :value="item.id">{{item.competitionName}}
            </el-option>
          </el-select>
        </div>

        <el-row v-for="(item, index) in momentForm" :key="item.id" class="mt">
          <el-col :span="3" :offset="1" class="labelTit" style="text-indent: -10px">
            1、开题检查任务：
          </el-col>
          <el-col :span="5">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionName"
              placeholder="请输入开题检查任务"
            ></el-input>
          </el-col>
          <el-col :lg="2" :md="3"> 请上传文档附件：</el-col>
          <el-col :lg="2" :md="2">
            <el-upload ref="file" :headers="headers" :file-list="fileList" :action="uploadFileUrl">
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-col>
          <el-col :lg="1" :md="1"> 时间：</el-col>
          <el-col :lg="3" :md="4">
            <el-date-picker type="daterange" v-model="endTime" format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
                            range-separator="至" clearable style="width: 100px"></el-date-picker>
          </el-col>
          <el-col :span="2" class="labelTit"> 分值：</el-col>
          <el-col :span="4">
            <el-input
              :disabled="ifdisabled"
              v-model="item.score"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in momentForm" :key="item.id" class="mt">
          <el-col :span="3" :offset="1" class="labelTit" style="text-indent: -10px">
            2、期中检查任务：
          </el-col>
          <el-col :span="5">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionName"
              placeholder="请输入开题检查任务"
            ></el-input>
          </el-col>
          <el-col :lg="2" :md="3"> 请上传文档附件：</el-col>
          <el-col :lg="2" :md="2">
            <el-upload ref="file" :headers="headers" :file-list="fileList" :action="uploadFileUrl">
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-col>
          <el-col :lg="1" :md="1"> 时间：</el-col>
          <el-col :lg="3" :md="4">
            <el-date-picker type="daterange" v-model="endTime" format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
                            range-separator="至" clearable style="width: 100px"></el-date-picker>
          </el-col>
          <el-col :span="2" class="labelTit"> 分值：</el-col>
          <el-col :span="4">
            <el-input
              :disabled="ifdisabled"
              v-model="item.score"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in momentForm" :key="item.id" class="mt">
          <el-col :span="3" :offset="1" class="labelTit" style="text-indent: -10px">
            3、结项提交任务：
          </el-col>
          <el-col :span="5">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionName"
              placeholder="请输入开题检查任务"
            ></el-input>
          </el-col>
          <el-col :lg="2" :md="3"> 请上传文档附件：</el-col>
          <el-col :lg="2" :md="2">
            <el-upload ref="file" :headers="headers" :file-list="fileList" :action="uploadFileUrl">
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-col>
          <el-col :lg="1" :md="1"> 时间：</el-col>
          <el-col :lg="3" :md="4">
            <el-date-picker type="daterange" v-model="endTime" format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
                            range-separator="至" clearable style="width: 100px"></el-date-picker>
          </el-col>
          <el-col :span="2" class="labelTit"> 分值：</el-col>
          <el-col :span="4">
            <el-input
              :disabled="ifdisabled"
              v-model="item.score"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
        </el-row>
        <el-col :span="24" :offset="12" class="mt">
          <el-button v-if="!ifdisabled" type="primary" @click="onSubmit()"
          >确 认
          </el-button>
        </el-col>
      </el-tab-pane>

      <el-tab-pane label="作品类赛事" name="three">
        <div class="comtit">
          <div>作品管理</div>
          <el-button type="primary" @click="handleChange">编 辑</el-button>
        </div>
        <div class="lowTit" style="width:80%"> 赛事
          <el-select v-model="competitionId2" placeholder="请选择赛事">
            <el-option v-for="(item,index) of competitionList2" :key="index" :label="item.competitionName"
                       :value="item.id">{{item.competitionName}}
            </el-option>
          </el-select>
        </div>

        <el-row v-for="(item, index) in creditForm" :key="item.id" class="mt">
          <el-col :span="2" :offset="1" class="labelTit" style="text-indent: 10px">
            {{ index + 1 }}、作品：
          </el-col>
          <el-col :span="3">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionName"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
          <el-col :span="2" class="labelTit"> 作品简介：</el-col>
          <el-col :span="4">
            <el-input
              :disabled="ifdisabled"
              v-model="item.questionAnswer"
              placeholder="请输入作品简介"
            ></el-input>
          </el-col>
          <el-col :lg="2" :md="3"> 请上传文档附件：</el-col>
          <el-col :lg="6" :md="6">
            <uploader
              ref="uploader"
              :options="options"
              :autoStart=false
              :headers="headers"
              :file-status-text="fileStatusText"
              @file-added="onFileAdded"
              @file-success="onFileSuccess"
              @file-error="onFileError"
              class="uploader-ui">
              <uploader-unsupport></uploader-unsupport>
              <uploader-drop>
                <div>
                  <uploader-btn id="global-uploader-btn" :attrs="attrs" ref="uploadBtn">选择文件<i class="el-icon-upload el-icon--right"></i></uploader-btn>
                </div>
              </uploader-drop>
              <uploader-list></uploader-list>
            </uploader>
          </el-col>
          <el-col :span="2" class="labelTit"> 分值：</el-col>
          <el-col :span="2">
            <el-input
              :disabled="ifdisabled"
              v-model="item.score"
              placeholder="请输入内容"
            ></el-input>
          </el-col>
          <el-col :span="4" class="pl">
            <el-button
              :disabled="ifdisabled"
              v-if="index === creditForm.length - 1"
              type="primary"
              @click="handleAddCredit"
            >新 增
            </el-button
            >
            <el-button
              :disabled="ifdisabled"
              type="danger"
              @click="handleDelCredit(index)"
            >删 除
            </el-button
            >
          </el-col>
        </el-row>
        <el-col :span="24" :offset="12" class="mt">
          <el-button v-if="!ifdisabled" type="primary" @click="onSubmit1()"
          >确 认
          </el-button>
        </el-col>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {
    selTCreditModel,
    selTIndexLibraryTwoList,
    insertTCreditModel,
    updateTIndexLibraryTwoList,
    getCompetitionList,
    getCompetitionLists,
    getWorkLists,
  } from "@/api/manage/index";
  import { getToken } from "@/utils/auth";
  import {ACCEPT_CONFIG} from '@/components/common/config';
  import SparkMD5 from 'spark-md5';
  import {mergeFile} from "@/api/modules/uploadFile";

  export default {
    data() {
      return {
        options: {
          //目标上传 URL，默认POST
          target: process.env.VUE_APP_BASE_API+"/common/chunk",
          //分块大小(单位：字节)
          chunkSize: '2048000',
          //上传文件时文件内容的参数名，对应chunk里的Multipart对象名，默认对象名为file
          fileParameterName: 'upfile',
          //失败后最多自动重试上传次数
          maxChunkRetries: 3,
          //是否开启服务器分片校验，对应GET类型同名的target URL
          testChunks: true,
          /*
          服务器分片校验函数，判断秒传及断点续传,传入的参数是Uploader.Chunk实例以及请求响应信息
          reponse码是successStatuses码时，才会进入该方法
          reponse码如果返回的是permanentErrors 中的状态码，不会进入该方法，直接进入onFileError函数 ，并显示上传失败
          reponse码是其他状态码，不会进入该方法，正常走标准上传
          checkChunkUploadedByResponse函数直接return true的话，不再调用上传接口
          */
          checkChunkUploadedByResponse: function (chunk, response_msg) {
            let objMessage = JSON.parse(response_msg);
            if (objMessage.skipUpload) {
              return true;
            }
            return (objMessage.uploadedChunks || []).indexOf(chunk.offset + 1) >= 0;
          }
        },
        attrs: {
          accept: ACCEPT_CONFIG.getAll()
        },
        fileStatusText: {
          success: '上传成功',
          error: '上传失败',
          uploading: '上传中',
          paused: '暂停',
          waiting: '等待上传'
        },
        fileList: [],
        activeName: "first",
        form: [],
        data: {},
        endTime: "",
        competitionId: "",
        competitionId1: "",
        uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/breakpoint-upload", // 上传的图片服务器地址
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        creditForm: [{}],
        creditForm1: [{}],
        momentForm: [{}],
        competitionList: [],
        competitionList1: [],
        competitionList2: [],
        competitionId2: [],
        creditShowForm: [],
        ifdisabled: true,
      };
    },
    watch: {
      value: {
        handler(val) {
          if (val) {
            let temp = 1;
            // 首先将值转为数组
            const list = Array.isArray(val) ? val : this.value.split(',');
            // 然后将数组转为对象数组
            this.fileList = list.map(item => {
              if (typeof item === "string") {
                item = {name: item, url: item};
              }
              item.uid = item.uid || new Date().getTime() + temp++;
              return item;
            });
          } else {
            this.fileList = [];
            return [];
          }
        },
        deep: true,
        immediate: true
      }
    },
    created() {
      this.getList();
      //this.getList1();
    },
    /* mounted() {
       this.getCompetitionQuestionList();
     },*/
    methods: {
      onFileAdded(file) {
        this.computeMD5(file);
      },
      /*
            第一个参数 rootFile 就是成功上传的文件所属的根 Uploader.File 对象，它应该包含或者等于成功上传文件；
            第二个参数 file 就是当前成功的 Uploader.File 对象本身；
            第三个参数就是 message 就是服务端响应内容，永远都是字符串；
            第四个参数 chunk 就是 Uploader.Chunk 实例，它就是该文件的最后一个块实例，如果你想得到请求响应码的话，chunk.xhr.status就是
            */
      onFileSuccess(rootFile, file, response, chunk) {
        //refProjectId为预留字段，可关联附件所属目标，例如所属档案，所属工程等
        file.refProjectId = "123456789";
        mergeFile(file).then( responseData=> {
          if(responseData.data.code === 415){
            console.log("合并操作未成功，结果码："+responseData.data.code);
          }
        }).catch(function (error){
          console.log("合并后捕获的未知异常："+error);
        });
      },
      onFileError(rootFile, file, response, chunk) {
        console.log('上传完成后异常信息：'+response);
      },
      /**
       * 计算md5，实现断点续传及秒传
       * @param file
       */
      computeMD5(file) {
        file.pause();

        //单个文件的大小限制2G
        let fileSizeLimit = 2 * 1024 * 1024 * 1024;
        console.log("文件大小："+file.size);
        console.log("限制大小："+fileSizeLimit);
        if(file.size > fileSizeLimit){
          this.$message({
            showClose: true,
            message: '文件大小不能超过2G'
          });
          file.cancel();
        }

        let fileReader = new FileReader();
        let time = new Date().getTime();
        let blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice;
        let currentChunk = 0;
        const chunkSize = 10 * 1024 * 1000;
        let chunks = Math.ceil(file.size / chunkSize);
        let spark = new SparkMD5.ArrayBuffer();
        //由于计算整个文件的Md5太慢，因此采用只计算第1块文件的md5的方式
        let chunkNumberMD5 = 1;

        loadNext();

        fileReader.onload = (e => {
          spark.append(e.target.result);

          if (currentChunk < chunkNumberMD5) {
            loadNext();
          } else {
            let md5 = spark.end();
            file.uniqueIdentifier = md5;
            file.resume();
            console.log(`MD5计算完毕：${file.name} \nMD5：${md5} \n分片：${chunks} 大小:${file.size} 用时：${new Date().getTime() - time} ms`);
          }
        });

        fileReader.onerror = function () {
          this.error(`文件${file.name}读取出错，请检查该文件`)
          file.cancel();
        };

        function loadNext() {
          let start = currentChunk * chunkSize;
          let end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;

          fileReader.readAsArrayBuffer(blobSlice.call(file.file, start, end));
          currentChunk++;
          console.log("计算第"+currentChunk+"块");
        }
      },
      close() {
        this.uploader.cancel();
      },
      error(msg) {
        this.$notify({
          title: '错误',
          message: msg,
          type: 'error',
          duration: 2000
        })
      },
      // 覆盖默认的上传行为
      requestUpload() {
      },
      getList() {
        getCompetitionList().then((res) => {
          this.competitionList = res;
        });
      },

      getList1() {
        getCompetitionLists().then((res) => {
          this.competitionList1 = res;
        });
      },
      getList2() {
        getWorkLists().then((res) => {
          this.competitionList2 = res;
        });
      },
      handleRiskQuery() {
        selTIndexLibraryTwoList().then((res) => {
          this.form = res.data;
        });
      },
      handleClick(tab, event) {
        this.ifdisabled = true;
        if (tab.label == "进度类赛事") {
          this.getList1();
        } else if(tab.label == "作品类赛事"){
          this.getList2();
        }
      },
      handleChange() {
        this.ifdisabled = false;
      },
      handleAddCredit() {
        let obj = {
          value: "",
          num: "",
          competitionId: "",
          file: ""
        };
        this.creditForm.push(obj);
      },
      handleAddCredit1() {
        let obj = {
          value: "",
          num: "",
          competitionId: "",
        };
        this.momentForm.push(obj);
      },
      handleDelCredit(index) {
        if (index == 0) {
          this.$modal.msgSuccess("至少保留一个题项！");
          return
        }
        this.creditForm.splice(index, 1);
      },
      handleDelCredit1(index) {
        if (index == 0) {
          this.$modal.msgSuccess("至少保留一个题项！");
          return
        }
        this.momentForm.splice(index, 1);
      },
      handleDelShowCredit(index) {
        this.creditShowForm.splice(index, 1);
      },
      handleEdit() {
        if (this.activeName == "first") {
          insertTCreditModel(this.creditForm, this.competitionId).then(response => {
            this.$modal.msgSuccess("新增题项成功");
            this.ifdisabled = true;
          });
        }
      },
      onSubmit() {
        updateTIndexLibraryTwoList(this.momentForm, this.competitionId1).then((res) => {
          this.$modal.msgSuccess("新增阶段任务成功");
          this.ifdisabled = true;
        });
      },

      onSubmit1() {
        updateTIndexLibraryTwoList(this.creditForm, this.competitionId2).then((res) => {
          this.$modal.msgSuccess("新增作品成功");
          this.ifdisabled = true;
        });
      },
    },
  };
</script>

<style lang="scss" scoped>
  ::v-deep .el-tabs__nav .is-top {
    font-size: 16px;
  }

  .comtit {
    font-size: 20px;
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    margin-right: 100px;
  }

  .lowTit {
    font-size: 18px;
    text-indent: 36px;
  }

  .labelTit {
    font-size: 16px;
    line-height: 2.4;
    text-indent: 20px;
    text-align: right;
  }

  .mt {
    margin-top: 20px !important;
  }

  .mb {
    margin-bottom: 20px !important;
  }

  .mr {
    margin-right: 10px !important;
  }

  .ml {
    margin-left: 20px !important;
  }

  .pl {
    padding-left: 20px !important;
  }

  .uploader-ui {
/*    padding: 15px;
    margin: 40px auto 0;*/
    font-size: 12px;
    font-family: Microsoft YaHei;
    box-shadow: 0 0 10px rgba(0, 0, 0, .4);
  }
  .uploader-ui .uploader-btn {
    margin-right: 4px;
    font-size: 12px;
    border-radius: 3px;
    color: #FFF;
    background-color: #409EFF;
    border-color: #409EFF;
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
  }
  .uploader-ui .uploader-list {
    max-height: 440px;
    overflow: auto;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
