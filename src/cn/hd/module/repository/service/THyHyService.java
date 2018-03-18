package cn.hd.module.repository.service;

import cn.hd.entity.THyHyEntity;
import cn.hd.utils.ResponseJSON;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 动态生成   会议表
 */
public interface THyHyService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tHyHy
     * @return
     * @throws Exception
     */
    ResponseJSON saveTHyHy(THyHyEntity tHyHy, HttpServletRequest request) throws Exception;

    /**
     * 修改
     *
     * @param tHyHy
     * @return
     * @throws Exception
     */
    ResponseJSON updateTHyHy(THyHyEntity tHyHy) throws Exception;

    /**
     * 删除
     *
     * @param tHyHy
     * @return
     * @throws Exception
     */
    ResponseJSON delTHyHy(THyHyEntity tHyHy) throws Exception;

    /**
     * 分页查询
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows);
    ResponseJSON countList();

    ResponseJSON loadNotQsGridData(Map<String, Object> paramMap);
    ResponseJSON loadChdwGridData(Map<String, Object> paramMap);

    /**
     *
     * @param paramMap
     * @param page
     * @param rows
     * @return
     */
    ResponseJSON queryBjZtPageList(Map<String, Object> paramMap, int page, int rows);


    /**
     * 查询所有的数据
     *
     * @param paramMap 传递的参数
     * @return
     */
    ResponseJSON queryList(Map<String, Object> paramMap);
/********************************[基本的操作方法]   end****************************/
    /**
     *
     * @param tHyHy
     * @return
     */
    Boolean isExistsByMc(THyHyEntity tHyHy);

    /**
     * 会议信息
     * @param id
     * @return
     */
    THyHyEntity findById(String id);

    /**
     * 统计上报数据
     * @param paramMap
     * @return
     */
    ResponseJSON refreshTjInfo(Map<String, Object> paramMap);
    /**
     * 判断是否具有已选参会人员
     */
    Map<String,Object> isCHryExist(Map<String, Object> paramMap);

    /**
     * 上报状态修改
     * @param tHyHy
     * @return
     * @throws Exception
     */
    ResponseJSON updateTHyHyForSbZtById(THyHyEntity tHyHy) throws Exception;
    ResponseJSON updateTHyHyForBjZtById(THyHyEntity tHyHy) throws Exception;
}