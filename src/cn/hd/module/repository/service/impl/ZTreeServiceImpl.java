package cn.hd.module.repository.service.impl;

import cn.hd.common.constant.TreeNode;
import cn.hd.common.enumeration.ZtreeEnum;
import cn.hd.entity.TXtDwEntity;
import cn.hd.module.repository.dao.TXtDwDao;
import cn.hd.module.repository.service.ZTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 树形结构...
 */
@Service("zTreeService")
@Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class ZTreeServiceImpl implements ZTreeService {
    @Autowired
    private TXtDwDao xtDwDao;//系统单位

    @Override
    public TXtDwEntity findAllChildren(int menuId) {
        // TODO Auto-generated method stub
        TXtDwEntity root=new TXtDwEntity();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",menuId);
        root = xtDwDao.getByHql("",map);
        return root;
    }

    /**
     *
     * @param ztreeEnum:树形模块标志
     * @param pid:父节点
     * @return
     */
    @Override
    public List<TreeNode> getTree(TreeNode treeNode ,ZtreeEnum ztreeEnum,String pid,String nocheck) {
        if(ztreeEnum.getVal() == 1){//单位属性结构
            /* 加载相应的属性结构 */
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id",pid);
            List<TXtDwEntity> listTmp = xtDwDao.findByCacheable("from TXtDwEntity t where t.parent.id is null");
            /* 通过遍历封装数据到treeNode中 */
            Set<TXtDwEntity> list = new HashSet<TXtDwEntity>();
            list.addAll(listTmp);
            return wapperDwTreeNode(treeNode,list,nocheck);
        }
        return null;
    }
    public List<TreeNode> wapperDwTreeNode(TreeNode treeNode, Collection<TXtDwEntity> list, String nocheck){
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        if(list != null && !list.isEmpty()){
            Iterator<TXtDwEntity> it = list.iterator();
            while(it.hasNext()){
                TreeNode treeNodeTmp = new TreeNode();
                TXtDwEntity dwEntity = it.next();
                treeNodeTmp.setId(dwEntity.getId());
                treeNodeTmp.setNocheck(nocheck);
                if(dwEntity.getParent() == null){
                    treeNodeTmp.setPid("---");
                }else{
                    treeNodeTmp.setPid(dwEntity.getParent().getId());
                }
                if( dwEntity.getParent() != null && !"".equals(dwEntity.getParent()) ){
                    treeNodeTmp.setPid(dwEntity.getParent().getId());
                }
                treeNodeTmp.setName(dwEntity.getJgMc());
                treeNodeTmp.setTitle(dwEntity.getJgMc());
                treeNodeTmp.setOpen("true");
                if(dwEntity.getChildren() != null && !dwEntity.getChildren().isEmpty()){
                    treeNodeTmp.setIsParent("true");
                    treeNodeTmp.setNodeType("isParent");
                    wapperDwTreeNode(treeNodeTmp,dwEntity.getChildren(),nocheck);
                }
                treeNodeTmp.setIsedit("true");
                treeNode.addTreeNode(treeNodeTmp);
            }
        }
        return treeNodes;
    }
}
