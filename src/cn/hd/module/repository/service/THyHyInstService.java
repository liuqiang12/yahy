package cn.hd.module.repository.service;

import cn.hd.entity.THyHyInstEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成
 */
public interface THyHyInstService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    ResponseJSON saveTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception;

    /**
     * 修改
     *
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    ResponseJSON updateTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception;

    /**
     * 删除
     *
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    ResponseJSON delTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception;

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
    THyHyInstEntity findById(String hyInstId);
    ResponseJSON updateForBjZtById(String hyInstId)throws Exception;
    ResponseJSON updateForSbZtyId(String hyInstId)throws Exception;
}