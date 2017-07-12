package com.hframe.tag.util;

import com.hframe.tag.bean.TreeItem;

import java.util.*;

/**
 * Created by zhangqh6 on 2015/10/20.
 */
public class DHtmlTreeUtil {


    public  static  String getDhtmlTreeXml(List<TreeItem> treeItems, String title,String condition){

        Map map=new HashMap();

        Set<String> keySet = new HashSet<String>();


        for (TreeItem treeItem : treeItems) {
            List<TreeItem> tempItemsList = null;
            String pid=treeItem.getPid();
            keySet.add(treeItem.getId());
            if(!map.containsKey(pid)){
                tempItemsList=new ArrayList<TreeItem>();
                map.put(pid, tempItemsList);
            }else{
                tempItemsList=(List<TreeItem>) map.get(pid);
            }
            tempItemsList.add(treeItem);
        }
        String pid = "-1";
        for (TreeItem treeItem : treeItems) {
            List<TreeItem> tempItemsList = null;
            String id=treeItem.getPid();
          if(!keySet.contains(id)) {
              pid = id;
          }
        }

        TreeItem result=new TreeItem();
        result.setText(title);

        result.setId(pid);
        orderTreeItemsList(map, pid, result);
//        if(map.get("-1")!=null&&((List<TreeItem>)map.get("-1")).size()>0){
//            result.setId("-1");
//            orderTreeItemsList(map, "-1", result);
//        }else{
//            pid=condition.substring(condition.indexOf("="+1));
//            result.setId(pid);
//            orderTreeItemsList(map,pid, result);
//        }

        return result.toDhtmlTreeString();
    }

    public static void orderTreeItemsList(Map map,String pid,TreeItem result){

        if(map.get(pid)!=null){

            List<TreeItem> tempList=(List<TreeItem>) map.get(pid);
            List<TreeItem> resultList=new ArrayList<TreeItem>();
            for (TreeItem treeItem : tempList) {
                resultList.add(treeItem);
                orderTreeItemsList(map, treeItem.getId(), treeItem);
            }

            result.setTreeItems(resultList);
        }
    }
}
