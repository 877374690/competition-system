import request from '@/utils/request'

// 查询报名列表
export function listCompetition(query) {
  return request({
    url: '/enroll/personal/listCompetition',
    method: 'get',
    params: query
  })
}
export function listPersonal(query) {
  return request({
    url: '/enroll/personal/list',
    method: 'get',
    params: query
  })
}

// 查询报名详细
export function getPersonal(id) {
  return request({
    url: '/enroll/personal/' + id,
    method: 'get'
  })
}

// 新增报名
export function addPersonal(data) {
  return request({
    url: '/enroll/personal',
    method: 'post',
    data: data
  })
}

// 修改报名
export function updatePersonal(data) {
  return request({
    url: '/enroll/personal',
    method: 'put',
    data: data
  })
}

// 删除报名
export function delPersonal(id) {
  return request({
    url: '/enroll/personal/' + id,
    method: 'delete'
  })
}
