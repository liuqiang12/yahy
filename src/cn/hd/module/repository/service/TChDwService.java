package cn.hd.module.repository.service;

import cn.hd.entity.ChDwEntity;
import cn.hd.utils.ResponseJSON;

import java.util.List;
import java.util.Map;

/**
* 动态生成   
*/
public interface TChDwService {
/********************************[基本的操作方法]   start****************************/
/**
* 保存
* @param tChDw
* @return
* @throws Exception
*/
ResponseJSON saveTChDw(ChDwEntity tChDw) throws Exception;
/**
* 修改
* @param tChDw
* @return
* @throws Exception
*/
ResponseJSON updateTChDw(ChDwEntity tChDw) throws Exception;
/**
* 删除
* @param tChDw
* @return
* @throws Exception
*/
ResponseJSON delTChDw(ChDwEntity tChDw) throws Exception;
/**
* 分页查询
* @param paramMap 传递的参数
* @param page 页号
* @param rows 条数
* @return
*/
ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows);
/**
* 查询所有的数据
* @param paramMap 传递的参数
* @return
*/
ResponseJSON queryList(Map<String, Object> paramMap);
/********************************[基本的操作方法]   end****************************/
    /**
     * 通过会议id 查询所有参会人员
     * @param hyId
     * @return
     */
    List<ChDwEntity> findByHyId(String hyId);
    List<ChDwEntity> findByHyId4Total(String hyId);
    List queryListWord(String hyId);
}