package cn.hd.module.repository.service;

import cn.hd.entity.TXtDwEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成   系统单位
 */
public interface TXtDwService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tXtDw
     * @return
     * @throws Exception
     */
    ResponseJSON saveTXtDw(TXtDwEntity tXtDw) throws Exception;

    /**
     * 修改
     *
     * @param tXtDw
     * @return
     * @throws Exception
     */
    ResponseJSON updateTXtDw(TXtDwEntity tXtDw) throws Exception;

    /**
     * 删除
     *
     * @param tXtDw
     * @return
     * @throws Exception
     */
    ResponseJSON delTXtDw(TXtDwEntity tXtDw) throws Exception;

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
     * 通过ID获取相应的实体类
     * @param id
     * @return
     */
    TXtDwEntity findById(String id);
    /**
     * 初始化数据
     * @throws Exception
     */
    public void contextInitData() throws Exception;


}