package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtRzDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  日志表
*/
@Repository
@Qualifier("tXtRzDao")
public class TXtRzDaoImpl extends BasicImplDao<T_xt_rz> implements TXtRzDao {

}