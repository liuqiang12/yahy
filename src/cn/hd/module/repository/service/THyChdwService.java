package cn.hd.module.repository.service;

import cn.hd.entity.THyChdwEntity;
import cn.hd.utils.ResponseJSON;

import java.util.Map;

/**
 * 动态生成   参会单位
 */
public interface THyChdwService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tHyChdw
     * @return
     * @throws Exception
     */
    ResponseJSON saveTHyChdw(THyChdwEntity tHyChdw) throws Exception;

    /**
     * 修改
     *
     * @param tHyChdw
     * @return
     * @throws Exception
     */
    ResponseJSON updateTHyChdw(THyChdwEntity tHyChdw) throws Exception;

    /**
     * 删除
     *
     * @param tHyChdw
     * @return
     * @throws Exception
     */
    ResponseJSON delTHyChdw(THyChdwEntity tHyChdw) throws Exception;

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
     * 获取参会单位数据
     * @param chdwId
     * @return
     */
    THyChdwEntity findById(String chdwId);
}