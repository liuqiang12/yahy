package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyChryEntity;
import cn.hd.module.repository.dao.THyChryDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  参会人员
*/
@Repository
@Qualifier("tHyChryDao")
public class THyChryDaoImpl extends BasicImplDao<THyChryEntity> implements THyChryDao {

}