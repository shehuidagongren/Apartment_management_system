import request from '@/utils/request'

// 查询公寓列表
export function listApartment(query) {
  return request({
    url: '/system/apartment/list',
    method: 'get',
    params: query
  })
}

// 查询公寓详细
export function getApartment(id) {
  return request({
    url: '/system/apartment/' + id,
    method: 'get'
  })
}

// 新增公寓
export function addApartment(data) {
  return request({
    url: '/system/apartment',
    method: 'post',
    data: data
  })
}

// 修改公寓
export function updateApartment(data) {
  return request({
    url: '/system/apartment',
    method: 'put',
    data: data
  })
}

// 删除公寓
export function delApartment(id) {
  return request({
    url: '/system/apartment/' + id,
    method: 'delete'
  })
}
