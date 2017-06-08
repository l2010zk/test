package com.ad.cpm.utils;

import java.util.Collection;

/**
 * Created by lzk on 2017/3/8.
 */
public class Tools {
    public static boolean isEmpty(Object o){
        if(o==null){
            return true;
        }

        if(o instanceof String){
            return ((String)o).length() == 0;
        }

        if(o instanceof Collection){
            return ((Collection<?>)o).size() == 0;
        }
        return false;
    }

    /**
     * 判断indexPage 传递的URL是否合法
     */
    public static boolean isLegalURL(String url){
        //暂时只进行是否为空判断
        return !isEmpty(url);
    }
}
