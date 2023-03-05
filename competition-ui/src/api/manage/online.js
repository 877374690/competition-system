import request from '@/utils/request'

// 查询添加赛事列表
export function listOnline(query) {
  return request({
    url: '/manage/online/list',
    method: 'get',
    params: query
  })
}
export function answerList(query) {
  return request({
    url: '/manage/online/answerList',
    method: 'get',
    params: query
  })
}


// 查询添加赛事列表
export function listCheck(query) {
  return request({
    url: '/manage/online/check/list',
    method: 'get',
    params: query
  })
}
export function listCancel(query) {
  return request({
    url: '/manage/online/cancel/list',
    method: 'get',
    params: query
  })
}

// 查询添加赛事详细
export function getOnline(id) {
  return request({
    url: '/manage/online/' + id,
    method: 'get'
  })
}

// 新增添加赛事
export function addOnline(data) {
  return request({
    url: '/manage/online',
    method: 'post',
    data: data
  })
}

// 修改添加赛事
export function updateOnline(data) {
  return request({
    url: '/manage/online',
    method: 'put',
    data: data
  })
}

// 删除添加赛事
export function delOnline(id) {
  return request({
    url: '/manage/online/' + id,
    method: 'delete'
  })
}
