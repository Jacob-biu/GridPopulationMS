import request from '@/utils/request'

// 查询居民信息列表
export function listResidentinfo(query) {
  return request({
    url: '/management/residentinfo/list',
    method: 'get',
    params: query
  })
}

// 查询居民信息详细
export function getResidentinfo(idcardInfo) {
  return request({
    url: '/management/residentinfo/' + idcardInfo,
    method: 'get'
  })
}

// 新增居民信息
export function addResidentinfo(data) {
  return request({
    url: '/management/residentinfo',
    method: 'post',
    data: data
  })
}

// 修改居民信息
export function updateResidentinfo(data) {
  return request({
    url: '/management/residentinfo',
    method: 'put',
    data: data
  })
}

// 删除居民信息
export function delResidentinfo(idcardInfo) {
  return request({
    url: '/management/residentinfo/' + idcardInfo,
    method: 'delete'
  })
}
