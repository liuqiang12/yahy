package cn.hd.module.repository.service.impl;

import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtRzDao;
import cn.hd.module.repository.service.TXtRzService;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态生成   日志表
 */
@Service("tXtRzService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TXtRzServiceImpl extends BasicImplDao<T_xt_rz> implements TXtRzService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TXtRzDao tXtRzDao;

    /**
     * 保存
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTXtRz(T_xt_rz tXtRz) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtRzDao.save(tXtRz);
        return responseJSON;
    }

    /**
     * 修改
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTXtRz(T_xt_rz tXtRz) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            tXtRzDao.update(tXtRz);
            responseJSON.setMsg("修改成功");
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
            responseJSON.setMsg("删除失败");
        }
        return responseJSON;
    }

    /**
     * 删除
     *
     * @param tXtRz
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTXtRz(T_xt_rz tXtRz) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();

        try {
            tXtRzDao.delete(tXtRz);
            responseJSON.setMsg("删除成功");
        } catch (Exception e) {
            responseJSON.setMsg("删除失败");
        }
        return responseJSON;
    }

    /**
     * 分页查询
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    @Override
    public ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        StringBuffer countHql = new StringBuffer();/*手动填写总数*/
        countHql.append("select count(*) from T_xt_rz");
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        result.put("total", tXtRzDao.count(countHql.toString(), paramMap));
        Map<String,Object> param = new HashMap<String,Object>();
        rowsHql.append("from T_xt_rz order by cj_sj desc");//
        result.put("rows", tXtRzDao.find(rowsHql.toString(), paramMap, page, rows));
        responseJSON.setResult(result);
        responseJSON.setMsg("查询成功");
        return responseJSON;
    }

    /**
     * 查询所有的数据
     *
     * @param paramMap 传递的参数
     * @return
     */
    @Override
    public ResponseJSON queryList(Map<String, Object> paramMap) {
        return null;
    }
}