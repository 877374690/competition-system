import request from '@/utils/request'

export function getCompetitionList(data) {
  return request({
    url: '/manage/online/getCompetitionList',
    method: 'get',
    params: data
  })
}

export function getWorkLists(data) {
  return request({
    url: '/manage/online/getWorkLists',
    method: 'get',
    params: data
  })
}
export function getCompetitionLists(data) {
  return request({
    url: '/manage/online/getCompetitionList1',
    method: 'get',
    params: data
  })
}

export function selTCreditModel(data) {
  return request({
    url: '/manage/online/getCompetitionList',
    method: 'get',
    params: data
  })
}

export function selTIndexLibraryTwoList(data) {
  return request({
    url: '/wise/selTIndexLibraryTwoList',
    method: 'get',
    params: data
  })
}

export function updateTCreditModel(data) {
  return request({
    url: '/wise/updateTCreditModel',
    method: 'post',
    data: data
  })
}

export function updateTIndexLibrary(data) {
  return request({
    url: '/wise/updateTIndexLibrary',
    method: 'post',
    data: data
  })
}

export function insertTCreditModel(creditForm,competitionId) {
  const data = {
    creditForm,
    competitionId
  }
  return request({
    url: '/manage/question',
    method: 'post',
    data: data
  })
}

// 风险 确定
export function updateTIndexLibraryTwoList(momentForm,competitionId1) {
  const data = {
    momentForm,
    competitionId1
  }
  return request({
    url: '/manage/question/moment',
    method: 'post',
    data: data
  })
}

// 模板查询
export function selUploadList() {
  return request({
    url: '/wise/selUploadList',
    method: 'get'
  })
}

// 图文识别---模板新增
export function insertUploadList(data) {
  return request({
    url: '/wise/insertUploadList',
    method: 'post',
    data: data
  })
}

