package com.ad.cpm.comm;

import com.ad.cpm.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lzk on 2017/3/9.
 *   集合及同步游标
 */
public class SyncData {


    /**
     * 导航页集合同步游标
     */
    static  public AtomicInteger urlCollectionIndex = new AtomicInteger();
    /**
     * 导航url 集合
     */
    static  public ArrayList<String> urlList = new ArrayList<String >();

    /**
     * 广告url集合同步游标
     */
    static public  AtomicInteger adUrlCollectionIndex = new AtomicInteger();
    /**
     * 广告url 集合
     */
    static public  ArrayList<String> adUrlList = new ArrayList<String>();
    /**
     * 广告url hash字典
     */
    static public HashMap<String,String>  adUrlHash = new HashMap<String,String>();

    /**
     * 请求唯一标识：用来统计时延、损失率
     */
    static public AtomicLong requestId = new AtomicLong ();

}
