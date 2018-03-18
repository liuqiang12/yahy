package cn.hd.module.repository.service;

import cn.hd.entity.T_xt_rz;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成   日志表
 */
public interface TXtRzService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtRz(T_xt_rz tXtRz) throws Exception;

    /**
     * 修改
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtRz(T_xt_rz tXtRz) throws Exception;

    /**
     * 删除
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtRz(T_xt_rz tXtRz) throws Exception;

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
}