package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtHyddEntity;
import cn.hd.module.repository.dao.TXtHyddDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  会议地点
*/
@Repository
@Qualifier("tXtHyddDao")
public class TXtHyddDaoImpl extends BasicImplDao<TXtHyddEntity> implements TXtHyddDao {

}