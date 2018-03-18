package cn.hd.module.repository.service;

import cn.hd.entity.TOperateEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成
 */
public interface TXtOperateService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtOperate(TOperateEntity tXtOperate) throws Exception;

    /**
     * 修改
     *
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtOperate(TOperateEntity tXtOperate) throws Exception;

    /**
     * 删除
     *
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtOperate(TOperateEntity tXtOperate) throws Exception;

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
    /**
     * 修改
     *
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    ResponseJSON updateOperateIsHold(TOperateEntity tXtOperate) throws Exception;
}