package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtDwEntity;
import cn.hd.module.repository.dao.TXtDwDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  系统单位
*/
@Repository
@Qualifier("tXtDwDao")
public class TXtDwDaoImpl extends BasicImplDao<TXtDwEntity> implements TXtDwDao {

}