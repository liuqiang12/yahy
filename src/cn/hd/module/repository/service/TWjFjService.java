package cn.hd.module.repository.service;

import cn.hd.entity.TWjFjEntity;
import cn.hd.utils.ResponseJSON;

import java.util.List;
import java.util.Map;

/**
 * 动态生成   附件表
 */
public interface TWjFjService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tWjFj
     * @return
     * @throws Exception
     */
    ResponseJSON saveTWjFj(TWjFjEntity tWjFj) throws Exception;

    /**
     * 修改
     *
     * @param tWjFj
     * @return
     * @throws Exception
     */
    ResponseJSON updateTWjFj(TWjFjEntity tWjFj) throws Exception;

    /**
     * 删除
     *
     * @param tWjFj
     * @return
     * @throws Exception
     */
    ResponseJSON delTWjFj(TWjFjEntity tWjFj) throws Exception;

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
     * 通过接待ID获取附件信息:其中得到附件
     * @param id
     * @return
     */
    List<TWjFjEntity> findByJdxxId(String id);

    /**
     *
     * @return
     */
    TWjFjEntity findTzfjByHyId(String id);

    /**
     *
     * @param id
     * @return
     */
    TWjFjEntity findBsclByHyId(String id);

    /**
     * 制定方案
     * @param id
     * @return
     */
    public List<TWjFjEntity> findZdfaByJdxxId(String id);
    public List<TWjFjEntity> findZdfaJdxxByJdxxId(String id);

    /**
     * 获取附件信息
     * @param id
     * @return
     */
    public List<TWjFjEntity> findFjByHyId(String id);

}