/*
package cn.hd.module.recvmsg.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hd.entity.Person;
import cn.hd.module.recvmsg.dao.PersonDao;

import java.util.List;

*/
/**
 * Created by XRog
 * On 2/2/2017.2:30 PM
 *//*

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Person load(Long id) {
        return (Person)getCurrentSession().load(Person.class,id);
    }

    public Person get(Long id) {
        return (Person)getCurrentSession().get(Person.class,id);
    }

    public List<Person> findAll() {
        return null;
    }

    public void persist(Person entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(Person entity)  {
    	
    	Long a = (Long) getCurrentSession().save(entity);
        return a;
    }

    
    
    public void update(){
    	String hql="update Person set username='XRogxsx' where id=1";
    	getCurrentSession().createQuery(hql).executeUpdate();
    }
    
    
    
    
    
    
    
    public void saveOrUpdate(Person entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {
        Person person = load(id);
        getCurrentSession().delete(person);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}*/
