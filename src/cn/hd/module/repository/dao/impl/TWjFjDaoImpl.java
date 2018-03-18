package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TWjFjEntity;
import cn.hd.module.repository.dao.TWjFjDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  附件表
*/
@Repository
@Qualifier("tWjFjDao")
public class TWjFjDaoImpl extends BasicImplDao<TWjFjEntity> implements TWjFjDao {

}