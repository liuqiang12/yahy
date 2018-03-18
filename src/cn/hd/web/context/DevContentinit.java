package cn.hd.web.context;

import cn.hd.common.cfgUtils.CommonProperty;
import cn.hd.common.constant.DevContext;
import cn.hd.module.repository.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 */
@Component
public class DevContentinit {
    /* 项目启动的时候，手动insert相关的数据 */
    @Autowired
    private TXtSzService tXtSzService;
    @Autowired
    private TXtOperateService operateService;
    @Autowired
    private TXtJsService jsService;//角色信息
    @Autowired
    private TXtDwService dwService;//系统单位
    @Autowired
    private TXtRyService ryService;//系统人员
    @Autowired
    private TXtHyddService hyddService;//会议地点默认数据
    @PostConstruct
    public void init() {
        String ATTACH_FILE = CommonProperty.getInstance().getPropertyValue("ATTACH_FILE");
        String GNSZ_MC = CommonProperty.getInstance().getPropertyValue("GNSZ_MC");
        Integer GNSZ_QZ = CommonProperty.getInstance().getPropertyIntValue("GNSZ_QZ");
        String GNSZ_BZ = CommonProperty.getInstance().getPropertyValue("GNSZ_BZ");
        DevContext.ATTACH_FILE = ATTACH_FILE;
        DevContext.GNSZ_MC = GNSZ_MC;
        DevContext.GNSZ_QZ = GNSZ_QZ;
        DevContext.GNSZ_BZ = GNSZ_BZ;
        /* 配置 */
        try{
            System.out.println("------------------系统设置数据------------------");
            tXtSzService.contextInitData();
            System.out.println("------------------权限数据------------------");
            operateService.contextInitData();
            System.out.println("------------------角色数据------------------");
            jsService.contextInitData();
            System.out.println("------------------默认单位------------------");
            dwService.contextInitData();
            System.out.println("------------------默认用户人员------------------");
            ryService.contextInitData();
            System.out.println("------------------默认会议地点------------------");
            hyddService.contextInitData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
