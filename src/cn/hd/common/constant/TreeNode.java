package cn.hd.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 树对象:说明，此对象中不能添加tId字段，否则会报错
 */
public class TreeNode {
	// 本节点
	private String id;
	// 父节点
	private String pid;
	// 节点名称
	private String name;
	// 节点标题
	private String title;
	// 是否展开
	private String open;
	// 是否父节点
	private String isParent="false";
	// 节点类型
	private String nodeType="isChild";//默认是叶子节点，如果是父节点则值是isParent
	// 参数标示
	private String paramFlag;
	// 被选中
	private String checked;
	// 图标
	private String iconSkin;
	private String action;

	private String param;

	private String icon;

	private String isedit;// 是否编辑，0:查询，1:编辑;

	private String nocheck = "true";

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	// 子节点
	private List<TreeNode> children;
	private List<TreeNode> parentChildren = new ArrayList<TreeNode>();

	// treeNode
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParamFlag() {
		return paramFlag;
	}

	public void setParamFlag(String paramFlag) {
		this.paramFlag = paramFlag;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> childNodes) {
		this.children = childNodes;
	}
	public void addTreeNode(TreeNode treeNode){
		if(this.children == null || this.children.isEmpty()){
			this.children = new ArrayList<TreeNode>();
			this.children.add(treeNode);
		}
		if(!this.children.contains(treeNode)){
			this.children.add(treeNode);
		}
	}

	public List<TreeNode> getParentChildren() {
		return parentChildren;
	}

	public void setParentChildren(TreeNode parentChildren) {
		this.parentChildren.add(parentChildren);
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public void setParentChildren(List<TreeNode> parentChildren) {
		this.parentChildren = parentChildren;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getIsedit() {
		return isedit;
	}

	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}
}
