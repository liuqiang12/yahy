package cn.hd.module.repository.service;

import cn.hd.entity.TXtSzEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成   系统设置
 */
public interface TXtSzService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtSz
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtSz(TXtSzEntity tXtSz) throws Exception;

    /**
     * 修改
     *
     * @param tXtSz
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtSz(TXtSzEntity tXtSz) throws Exception;

    /**
     * 删除
     *
     * @param tXtSz
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtSz(TXtSzEntity tXtSz) throws Exception;

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
     * @param tXtSz
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtSzQz(TXtSzEntity tXtSz) throws Exception;
}