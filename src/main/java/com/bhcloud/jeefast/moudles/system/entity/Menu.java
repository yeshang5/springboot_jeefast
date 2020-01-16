package com.bhcloud.jeefast.moudles.system.entity;

import java.util.List;
import com.bhcloud.jeefast.common.entity.DataEntity;
import com.bhcloud.jeefast.moudles.system.utils.UserUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单Entity
 * @author bh
 * @version 2020-01-16
 */
@Data
public class Menu extends DataEntity<Menu> {

	private static final long serialVersionUID = 1L;
	private Menu parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private List<Menu> children;	// 父级菜单
	private String name; 	// 名称
	private String href; 	// 链接
	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; 	// 图标
	private Integer sort; 	// 排序
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String type; //按钮类型
	private String permission; // 权限标识
	private boolean hasChildren;
	private List<DataRule> dataRuleList;
	
	private String userId;
	
	public Menu(){
		super();
		this.sort = 30;
		this.isShow = "1";
		this.type="1";
	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	@JsonIgnore
	public boolean hasPermisson(){
		List<Menu> menuList = UserUtils.getMenuList();
		for(Menu menu:menuList){
			if(menu.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@JsonIgnore
	public static String getRootId(){
		return "1";
	}
}