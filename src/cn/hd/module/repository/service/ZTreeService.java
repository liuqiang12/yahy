package cn.hd.module.repository.service;

import cn.hd.common.constant.TreeNode;
import cn.hd.common.enumeration.ZtreeEnum;
import cn.hd.entity.TXtDwEntity;

import java.util.List;

/**
 * Created by DELL on 2017/12/19.
 */
public interface ZTreeService {
    /**
     *
     * @param menuId
     * @return
     */
    public TXtDwEntity findAllChildren(int menuId);
    /**
     * 加载单位属性结构
     * @return
     */
    public List<TreeNode> getTree(TreeNode treeNode,ZtreeEnum ztreeEnum,String pid,String nocheck) ;
}
