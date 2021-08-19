package com.fzy.mixed_block.tree;

import cn.hutool.core.lang.Assert;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.*;

@Data
@NoArgsConstructor
public class TreeNode {

    /**
     * 父节点ID
     */
    private String pid;
    /**
     * 节点ID
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 子节点
     */
    private List<TreeNode> children;

    public TreeNode(String pid, String nodeId, String nodeName) {
        this.pid = pid;
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }


    /**
     *
     * 组装树结构数据的方法
     * @param singleTreeNodes 参数
     *
     */
    public static List<TreeNode> assembleTree(List<TreeNode> singleTreeNodes){

        // 判断排序数据是否为空
        if(singleTreeNodes == null || singleTreeNodes.isEmpty()) {
            return null;
        }
        // 用有序Map把传参组装起来
        Map<String,TreeNode> nodeId2treeNodes = new LinkedHashMap<>();
        for(TreeNode node : singleTreeNodes){
            TreeNode treeNode = new TreeNode();
            treeNode.setNodeId(node.getNodeId());
            treeNode.setNodeName(node.getNodeName());
            treeNode.setPid(node.getPid());
            nodeId2treeNodes.put(node.getNodeId(), treeNode);
        }
        // 用来保存组装好的数据, 作为返回值
        List<TreeNode> topNodeTrees = new ArrayList<>();
        // 遍历所有节点
        for(String nodeId : nodeId2treeNodes.keySet()){
            TreeNode treeNode = nodeId2treeNodes.get(nodeId);
            String pid = treeNode.getPid();
            // 当前节点的父id为空 或 整个列表中没有以此父id作为自己的id的情况
            // 即当前节点是根节点
            if(pid==null || pid.length()==0 || !nodeId2treeNodes.containsKey(pid)){
                // treeNode.setPid(""); // 此处可以根据需要调整父id的值
                topNodeTrees.add(treeNode);
            }else{ // 不是父节点, 作为子节点添加进去
                TreeNode parentTreeNode = nodeId2treeNodes.get(pid);
                if(parentTreeNode.getChildren()==null){
                    parentTreeNode.setChildren(new ArrayList<TreeNode>());
                }
                parentTreeNode.getChildren().add(treeNode);
            }
        }
        return topNodeTrees;

    }


    private static void outputResult(List<TreeNode> treeNodes) {
        for (TreeNode treeNode : treeNodes) {
            System.out.println("nodeId: " + treeNode.getNodeId() + "; pid: " + treeNode.getPid() + "; name: "+treeNode.getNodeName());
            List<TreeNode> childrenNode = treeNode.getChildren();
            if (childrenNode != null) {
                outputResult(childrenNode);
            }
        }
    }


    public static void main(String[] args){

        /*List<TreeNode> singleNodes = new ArrayList<TreeNode>();
        singleNodes.add(new TreeNode("a","1","节点1"));
        singleNodes.add(new TreeNode("b","2","节点2"));
        singleNodes.add(new TreeNode("c","3","节点3"));
        singleNodes.add(new TreeNode("1","4","节点1-1"));
        singleNodes.add(new TreeNode("2","5","节点2-1"));
        singleNodes.add(new TreeNode("1","6","节点1-3"));
        singleNodes.add(new TreeNode("1","7","节点1-2"));
        singleNodes.add(new TreeNode("2","8","节点2-2"));
        List<TreeNode> treeNodes = TreeNode.assembleTree(singleNodes);
        outputResult(treeNodes);*/

        /*DecimalFormat df = new DecimalFormat("0%");
        System.out.println(df.format(0.89));*/
        long current = System.currentTimeMillis();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        System.out.println(zero);

    }



}
