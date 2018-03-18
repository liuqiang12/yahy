package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TWjWjEntity;
import cn.hd.module.repository.dao.TWjWjDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  物理文件表
*/
@Repository
@Qualifier("tWjWjDao")
public class TWjWjDaoImpl extends BasicImplDao<TWjWjEntity> implements TWjWjDao {

}