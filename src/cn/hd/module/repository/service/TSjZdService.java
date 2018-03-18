package cn.hd.module.repository.service;

import cn.hd.entity.TSjZdEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
* 动态生成   数据字典
*/
public interface TSjZdService {
/********************************[基本的操作方法]   start****************************/
/**
* 保存
* @param tSjZd
* @return
* @throws Exception
*/
ResponseJSON saveTSjZd(TSjZdEntity tSjZd) throws Exception;
/**
* 修改
* @param tSjZd
* @return
* @throws Exception
*/
ResponseJSON updateTSjZd(TSjZdEntity tSjZd) throws Exception;
/**
* 删除
* @param tSjZd
* @return
* @throws Exception
*/
ResponseJSON delTSjZd(TSjZdEntity tSjZd) throws Exception;
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