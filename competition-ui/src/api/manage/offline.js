import request from '@/utils/request'

// 查询线下赛事列表
export function listOffline(query) {
  return request({
    url: '/manage/offline/list',
    method: 'get',
    params: query
  })
}

// 查询线下赛事详细
export function getOffline(id) {
  return request({
    url: '/manage/offline/' + id,
    method: 'get'
  })
}

// 新增线下赛事
export function addOffline(data) {
  return request({
    url: '/manage/offline',
    method: 'post',
    data: data
  })
}

// 修改线下赛事
export function updateOffline(data) {
  return request({
    url: '/manage/offline',
    method: 'put',
    data: data
  })
}

// 删除线下赛事
export function delOffline(id) {
  return request({
    url: '/manage/offline/' + id,
    method: 'delete'
  })
}
