package cn.hd.module.repository.service;

import cn.hd.entity.THyChryEntity;
import cn.hd.utils.ResponseJSON;

import java.util.List;
import java.util.Map;

/**
* 动态生成   参会人员
*/
public interface THyChryService {
/********************************[基本的操作方法]   start****************************/
/**
* 保存
* @param tHyChry
* @return
* @throws Exception
*/
ResponseJSON saveTHyChry(THyChryEntity tHyChry) throws Exception;
/**
* 修改
* @param tHyChry
* @return
* @throws Exception
*/
ResponseJSON updateTHyChry(THyChryEntity tHyChry) throws Exception;
/**
* 删除
* @param tHyChry
* @return
* @throws Exception
*/
ResponseJSON delTHyChry(THyChryEntity tHyChry) throws Exception;
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
ResponseJSON queryWinList(Map<String,Object> paramMap);
/********************************[基本的操作方法]   end****************************/
    /**
     * 获取参会人员
     * @param id
     * @return
     */
    THyChryEntity findById(String id);

    /***
     * shanchu
     * @param tHyChry
     * @return
     * @throws Exception
     */
    ResponseJSON delTHyChryById(THyChryEntity tHyChry) throws Exception;
    List<THyChryEntity> queryForWordWinList(Map<String,Object> paramMap);

}