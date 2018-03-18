package cn.hd.module.repository.service;

import cn.hd.entity.TXtJsEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成   角色表
 */
public interface TXtJsService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtJs
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtJs(TXtJsEntity tXtJs) throws Exception;

    /**
     * 修改
     *
     * @param tXtJs
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtJs(TXtJsEntity tXtJs) throws Exception;

    /**
     * 删除
     *
     * @param tXtJs
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtJs(TXtJsEntity tXtJs) throws Exception;

    /**
     * 分页查询
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows);

    /**
     * 查询所有的数据
     *
     * @param paramMap 传递的参数
     * @return
     */
    ResponseJSON queryList(Map<String, Object> paramMap);
/********************************[基本的操作方法]   end****************************/
    /**
     * 初始化数据
     * @throws Exception
     */
    public void contextInitData() throws Exception;
}