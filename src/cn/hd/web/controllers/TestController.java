/*
package cn.hd.web.controllers;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hd.entity.T_xt_rz;
import cn.hd.module.log.aop.Log;
*/
/*import cn.hd.module.recvmsg.service.PersonService;*//*


@Controller
public class TestController extends BaseController{
	
	
	@Autowired
    private PersonService personService;

    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        personService.savePerson1();
        T_xt_rz rz = new T_xt_rz();
        rz.setBz("123");
        
       ((BaseController)AopContext.currentProxy()).addLog(rz);
        return "success!";
    }
}
*/
