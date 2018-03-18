package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TOperateEntity;
import cn.hd.module.repository.dao.TXtOperateDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  
*/
@Repository
@Qualifier("tXtOperateDao")
public class TXtOperateDaoImpl extends BasicImplDao<TOperateEntity> implements TXtOperateDao {

}