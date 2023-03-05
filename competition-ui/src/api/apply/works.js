import request from '@/utils/request'

export function listCompetition(type) {
  return request({
    url: '/competition/apply/listCompetition/'+type,
    method: 'get'
  })
}
// 查询比赛申请列表
export function listCountApply(query) {
  return request({
    url: '/competition/apply/listCountApply',
    method: 'get',
    params: query
  })
}

export function listApply(query) {
  return request({
    url: '/competition/apply/list',
    method: 'get',
    params: query
  })
}


export function listApplyResult(query) {
  return request({
    url: '/competition/apply/listApplyResult',
    method: 'get',
    params: query
  })
}
// 查询比赛申请详细
export function getApply(id) {
  return request({
    url: '/competition/apply/' + id,
    method: 'get'
  })
}

// 新增比赛申请
export function addApply(data) {
  return request({
    url: '/competition/apply',
    method: 'post',
    data: data
  })
}

// 修改比赛申请
export function updateApply(data) {
  return request({
    url: '/competition/apply',
    method: 'put',
    data: data
  })
}

// 删除比赛申请
export function delApply(id) {
  return request({
    url: '/competition/apply/' + id,
    method: 'delete'
  })
}
