package cn.hd.utils;

import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.entity.T_xt_rz;

import java.util.Map;

/**
 * Created by DELL on 2017/6/9.
 * 用于返回给客户端的结果
 */
public class ResponseJSON {
    //默认返回值是true
    private boolean success = true;
    private String msg = "SUCCESSFUL";
    private Map<String,Object> result;
    private TXtRyEntity ryEntity;
    private TXtDwEntity xtDwEntity;//系统单位
    private String dwStrs;//上报材料单位
    private Boolean isExists = false;
    private String id;//主键信息
    private T_xt_rz t_xt_rz;//日志情况

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String,Object> getResult() {
        return result;
    }

    public void setResult(Map<String,Object> result) {
        this.result = result;
    }

    public T_xt_rz getT_xt_rz() {
        return t_xt_rz;
    }

    public void setT_xt_rz(T_xt_rz t_xt_rz) {
        this.t_xt_rz = t_xt_rz;
    }

    public Boolean getIsExists() {
        return isExists;
    }

    public void setIsExists(Boolean isExists) {
        this.isExists = isExists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TXtRyEntity getRyEntity() {
        return ryEntity;
    }

    public void setRyEntity(TXtRyEntity ryEntity) {
        this.ryEntity = ryEntity;
    }

    public TXtDwEntity getXtDwEntity() {
        return xtDwEntity;
    }

    public void setXtDwEntity(TXtDwEntity xtDwEntity) {
        this.xtDwEntity = xtDwEntity;
    }

    public String getDwStrs() {
        return dwStrs;
    }

    public void setDwStrs(String dwStrs) {
        this.dwStrs = dwStrs;
    }
}
