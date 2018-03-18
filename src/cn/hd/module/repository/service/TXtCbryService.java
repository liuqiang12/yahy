package cn.hd.module.repository.service;

import cn.hd.entity.TXtCbryEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
* 动态生成   承办人员表[从系统用户中选定而来]
*/
public interface TXtCbryService {
/********************************[基本的操作方法]   start****************************/
/**
* 保存
* @param tXtCbry
* @return
* @throws Exception
*/
ResponseJSON saveTXtCbry(TXtCbryEntity tXtCbry) throws Exception;
/**
* 修改
* @param tXtCbry
* @return
* @throws Exception
*/
ResponseJSON updateTXtCbry(TXtCbryEntity tXtCbry) throws Exception;
/**
* 删除
* @param tXtCbry
* @return
* @throws Exception
*/
ResponseJSON delTXtCbry(TXtCbryEntity tXtCbry) throws Exception;
/**
* 分页查询
* @param paramMap 传递的参数
* @param page 页号
* @param rows 条数
* @return
*/
ResponseJSON queryPageList(Map<String,Object> paramMap, int page, int rows);
/**
* 查询所有的数据
* @param paramMap 传递的参数
* @return
*/
ResponseJSON queryList(Map<String,Object> paramMap);
/********************************[基本的操作方法]   end****************************/
}