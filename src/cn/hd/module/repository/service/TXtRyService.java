package cn.hd.module.repository.service;

import cn.hd.entity.TXtRyEntity;
import cn.hd.utils.ResponseJSON;

import java.util.List;
import java.util.Map;

/**
 * 动态生成   系统人员
 */
public interface TXtRyService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtRy
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtRy(TXtRyEntity tXtRy) throws Exception;

    /**
     * 修改
     *
     * @param tXtRy
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtRy(TXtRyEntity tXtRy) throws Exception;

    /**
     * 删除
     *
     * @param tXtRy
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtRy(TXtRyEntity tXtRy) throws Exception;

    /**
     * 分页查询
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows);
    ResponseJSON queryPageListOtherHyId(Map<String, Object> paramMap, int page, int rows);

    /**
     * 查询所有的数据
     *
     * @param paramMap 传递的参数
     * @return
     */
    ResponseJSON queryList(Map<String, Object> paramMap);
/********************************[基本的操作方法]   end****************************/
    /**
     * 通过id获取人员基本信息
     * @param id
     * @return
     */
    TXtRyEntity findById(String id);

    /**
        获取分配人员列表
     */
    List<TXtRyEntity> findByFpryStatus(Integer fpryStatus);
    /**
     * 初始化数据
     * @throws Exception
     */
    public void contextInitData() throws Exception;

    /**
     * 获取默认数据
     * @return
     */
    ResponseJSON defaultInfo();

    /**
     * 获取用户信息
     * @param hql
     * @param params
     * @return
     */
    TXtRyEntity getAuthUserByFlag(String hql,Map<String,Object> params);

    /**
     * 单位ID
     * @param dwId
     * @return
     */
    ResponseJSON queryMulitSelectList(String dwId);
}