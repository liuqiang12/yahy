package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtJsEntity;
import cn.hd.module.repository.dao.TXtJsDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  角色表
*/
@Repository
@Qualifier("tXtJsDao")
public class TXtJsDaoImpl extends BasicImplDao<TXtJsEntity> implements TXtJsDao {

}