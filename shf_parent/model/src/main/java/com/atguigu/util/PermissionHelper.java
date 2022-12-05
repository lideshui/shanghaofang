package com.atguigu.util;

import com.atguigu.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
        //创建存储处理结果的集合
        List<Permission> trees = new ArrayList<>();
        //循环每一个权限菜单节点
        for (Permission treeNode : treeNodes) {
            //找出所有parent_id为0的节点
            if (treeNode.getParentId().longValue() == 0) {
                //parent_id为0设置为一级节点
                treeNode.setLevel(1);
                //传入本次循环的一级节点，和所有节点列表，为其子节点属性赋值
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        //初始化当前节点的子节点集合
        treeNode.setChildren(new ArrayList<Permission>());

        //循环每一个权限菜单节点
        for (Permission it : treeNodes) {
            //查找当前节点的直接子节点
            if(treeNode.getId().longValue() == it.getParentId().longValue()) {
                //设置直接子节点的等级为父节点等级+1
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                //确保子节点已经初始化，其实之前已经初始化过了，这里加上双保险
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                //将当前节点的直接子节点存入到自己的Children属性(list集合)中
                //直接递归，查找子节点的直接子节点，直到第N辈的子节点无子节点可循环时跳出该递归⚠️
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}