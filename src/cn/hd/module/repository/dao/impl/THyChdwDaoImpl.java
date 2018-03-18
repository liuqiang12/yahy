package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyChdwEntity;
import cn.hd.module.repository.dao.THyChdwDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  参会单位
*/
@Repository
@Qualifier("tHyChdwDao")
public class THyChdwDaoImpl extends BasicImplDao<THyChdwEntity> implements THyChdwDao {

}