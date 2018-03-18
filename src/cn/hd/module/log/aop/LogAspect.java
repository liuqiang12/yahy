package cn.hd.module.log.aop;


import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.service.TXtRzService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
	@Autowired
	private TXtRzService xtRzService;//系统日志
	//	@Pointcut("execution(* cn.hd.web.controllers.TestController.addLog(*)) && args(rz)")
	@Pointcut("execution(* cn.hd.web.controllers.BaseController.addLog(..))")
	public void pointcut() { // 形参名必须和 args()一致

		System.out.println("pointcut+*************");

	}
	//	@After("pointcut(rz)")
	@AfterReturning(pointcut="pointcut()", returning="returnValue")
	public void after(Object returnValue){
		try {
			System.out.println("*****************");
			if(returnValue !=  null  ){
				T_xt_rz t_xt_rz = (T_xt_rz)returnValue;
				if(t_xt_rz.getMk_bz() != null ){
					// mkBz 必须要有模块标志
					xtRzService.saveTXtRz((T_xt_rz)returnValue);
				}
			}
			System.out.println("*****************");
		} catch (Throwable e) {
			// 调用出现异常后的代码
		}
	}
}
