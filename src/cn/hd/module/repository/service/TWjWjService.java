package cn.hd.module.repository.service;

import cn.hd.entity.TWjWjEntity;
import cn.hd.utils.ResponseJSON;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 动态生成   物理文件表
 */
public interface TWjWjService {
/********************************[基本的操作方法]   start****************************/
    /**
     * 保存
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    ResponseJSON saveTWjWj(TWjWjEntity tWjWj) throws Exception;

    /**
     * 修改
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    ResponseJSON updateTWjWj(TWjWjEntity tWjWj) throws Exception;

    /**
     * 删除
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    ResponseJSON delTWjWj(TWjWjEntity tWjWj) throws Exception;

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

    /**
     * 获取报送材料
     * @param paramMap
     * @return
     */
    ResponseJSON queryBsclList(Map<String, Object> paramMap);


/********************************[基本的操作方法]   end****************************/
    void downLoadFile(HttpServletResponse response,TWjWjEntity wjEntity) throws Exception;

    /**
     * 通过接待管理Id获取文件附件列表信息
     * @param id
     * @return
     */
    List<TWjWjEntity> findByJdxxId(String id);
}