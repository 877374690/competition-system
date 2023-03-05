import {requestApi} from '@/utils/request';
import { option } from '@/api/optionParam';

export const mergeFile = data => {
    option.isJson = true
    option.data = JSON.stringify(data)
    option.method = 'post'
    option.url = '/common/mergeFile'
    return requestApi(option)
  }

export const selectFileList = query => {
    option.isJson = true
    option.data = query
    option.method = 'post'
    option.url = '/common/selectFileList'
    return requestApi(option)
};

export const deleteFile = data => {
  option.isJson = true
  option.data = JSON.stringify(data)
  option.method = 'post'
  option.url = '/common/deleteFile'
  return requestApi(option)
}

export const downloadFile = query => {
  option.isJson = true
  option.data = query
  option.method = 'get'
  option.url = '/common/download'
  return requestApi(option)
};
