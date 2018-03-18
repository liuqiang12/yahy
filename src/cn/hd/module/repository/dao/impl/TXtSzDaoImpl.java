package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtSzEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtSzDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  系统设置
*/
@Repository
@Qualifier("tXtSzDao")
public class TXtSzDaoImpl extends BasicImplDao<TXtSzEntity> implements TXtSzDao {

}