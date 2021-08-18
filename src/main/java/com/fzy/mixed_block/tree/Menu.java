package com.fzy.mixed_block.tree;

import lombok.Data;

import java.util.List;


@Data
public class Menu {
    //菜单id
    private String id;
    //父节点parentId
    private String parentId;
    //菜单名称
    private String text;
    private String url;
    private String yxbz;
    //次级菜单
    private List<Menu> children;

    public Menu(String id,String parentId,String text,String url,String yxbz) {
         this.id=id;
         this.parentId=parentId;
         this.text=text;
         this.url=url;
         this.yxbz=yxbz;
    }
}
