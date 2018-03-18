/*
package cn.hd.module.recvmsg.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hd.common.enumeration.GenderEnum;
import cn.hd.entity.Person;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.recvmsg.dao.PersonDao;
import cn.hd.module.recvmsg.service.PersonService;

*/
/**
 * Created by XRog
 * On 2/2/2017.2:40 PM
 *//*

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;
    public Long savePerson(){
		return null;}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T_xt_rz savePerson1() {
        Person person = new Person();
        person.setUsername("XRog");
        person.setGender(GenderEnum.male);
        personDao.save(person);
        personDao.update();
        T_xt_rz  rz=new T_xt_rz();
        rz.setNr("123");
        return rz;
    }
}*/
