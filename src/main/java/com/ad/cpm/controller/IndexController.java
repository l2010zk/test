package com.ad.cpm.controller;
import com.ad.cpm.comm.RedisClient;
import com.ad.cpm.comm.SyncData;
import com.ad.cpm.utils.Constants;
import com.ad.cpm.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * Created by lzk on 2017/3/8.
 */
@Controller
public class IndexController  extends BaseController {

    @Autowired
    private RedisClient redisClinet;
    final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    //当前应该转向的url
     static private  String currentUrl = "";

    /**
     * cookies 域名设置
     */
    @Value("${system.domain.area1}")
    private  String area1;

    @Value("${system.url.filepath}")
    private  String  filepath;

    /**
     * URL 固定参数
     */
    @Value("${system.optimize.urlpara}")
    private  String optimizeUrlpara;

     //加载优化url
    static {

        if( SyncData.urlList.size() < 1){
            SyncData.urlList.clear();
            SyncData.urlList.add("http://teachsavemoney.life/index.html?") ;
            SyncData.urlList.add("http://teachsavemoney.life/index1.html") ;
//          SyncData.urlCollectionIndex.set(SyncData.urlList.size()-1);
        }
        if( SyncData.adUrlList.size()<1){
            SyncData.adUrlList.clear();
            SyncData.adUrlList.add("http://teachsavemoney.life/prduct/product.html");
            SyncData.adUrlList.add("http://teachsavemoney.life/adprduct/adproduct.html");
//          SyncData.adUrlCollectionIndex.set(SyncData.adUrlList.size()-1);
        }
    }

    /**
     *
     * @param request
     * @param resp
     * @param model
     * @return
     */
    @RequestMapping(value = "input", produces = "application/json;charset=UTF-8")
    public String input(HttpServletRequest request,HttpServletResponse resp,Model model) throws Exception {

        //记录请求日志
        //ip|时间|refer|url|浏览器|操作系统
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(  new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() ) + "|");
        stringBuilder.append(request.getHeader("Host") + "|");
        stringBuilder.append(request.getHeader("Referer") + "|");
        stringBuilder.append(request.getRequestURI() + "|");
        stringBuilder.append(request.getHeader("User-Agent") + "|");
        stringBuilder.append(request.getQueryString() + "|");

        long  id  = SyncData.requestId.incrementAndGet();
        //写本地日志
        logger.info( id + "|in|" + stringBuilder.toString());
        //写redis
       redisClinet.set(id + "in",stringBuilder.toString() );

        //写cookies*************************** start
        setCookieValue(resp, area1,"peoplefeature", "peoplefeature");
        setCookieValue(resp, area1,"requestId", String.valueOf(id));

        logger.info( "判断cookies-peoplefeature：" +  getCookieValue(request,"peoplefeature"));
        logger.info( "判断cookies-requestId："      + getCookieValue(request,"requestId"));
        //写cookies*************************** end

        //生成跳转网址************************ start
        Random rdm = new Random();
        int index  =  rdm.nextInt(SyncData.urlList.size());
        currentUrl = SyncData.urlList.get(index);
        String [] urlpara = currentUrl.split("=");
        String Suffix = String.valueOf( System.currentTimeMillis()) ;
        setCookieValue(resp, area1,"id", urlpara[1]);
        String url = currentUrl.replace("\r","").replace(" ", "");
        url = "'" + url + "product" + "'";
        model.addAttribute("html",url) ;
        //生成跳转网址************************ end
        return "input";
    }

    /**
     * 处理product 跳转
     * @param request
     * @param resp
     * @param model
     * @return
     */
    @RequestMapping(value = "home", produces = "application/json;charset=UTF-8")
    public String home(HttpServletRequest request,HttpServletResponse resp,Model model
     ) throws Exception {

        // cookie操作*************************** start
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(  new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() ) + "|");
        stringBuilder.append(request.getHeader("Host") + "|");
        stringBuilder.append(request.getHeader("Referer") + "|");
        stringBuilder.append(request.getRequestURI() + "|");
        stringBuilder.append(request.getHeader("User-Agent") + "|");
        stringBuilder.append(request.getQueryString() + "|");
        String  id =  getCookieValue(request,"requestId");

        String peoplefeature = getCookieValue(request,"peoplefeature");
        removeCookieValue("peoplefeature",area1,request, resp);
        removeCookieValue("requestId",area1,request, resp);

        if( peoplefeature == null  ||  !peoplefeature.equals("peoplefeature")){
            logger.info( id + "|out|truepage|" + stringBuilder.toString());
            //写redis
            redisClinet.set(id + "outtruepage",stringBuilder.toString() );
            return  "realjump";
        }
        String indexid = getCookieValue(request,"id");
      //  String  urlpara = SyncData.adUrlHash.get(indexid);

        logger.info( id + "|in|falsepage|" + stringBuilder.toString());
        //写redis
        redisClinet.set(id + "infalsepage",stringBuilder.toString() );

        //判断cookie*************************** end
//
//        //生成跳转网址************************* start
//        currentUrl = SyncData.adUrlList.get(SyncData.adUrlCollectionIndex.get());
//        // 达到最大值循环归零
//        if(  SyncData.adUrlCollectionIndex.get() >=  SyncData.adUrlList.size() ){
//            SyncData.adUrlCollectionIndex.set(0);
//        }else{
//            SyncData.adUrlCollectionIndex.incrementAndGet();
//        }
//        String Suffix = String.valueOf( System.currentTimeMillis()) ;
     //        model.addAttribute("html", "'"+currentUrl.replace("\r","").replace(" ", "")+"/?adproduct"+ "02" + Suffix +"'") ;

     /******* end 暂时不用 等到进一步优化时再用  ******/

    //生成跳转网址************************* end
    // return "home" + SyncData.urlCollectionIndex.get() ;
        return indexid  ;
}

    /**
     *
     * 重新加载系统配置
     * @param request
     * @param resp
     * @param command
     * @return
     */
    @RequestMapping(value = "initEnvironment", produces = "application/json;charset=UTF-8")
    public  String initEnvironment( HttpServletRequest request, HttpServletResponse resp,
                                   @RequestParam(value = "command",required=true) String command ){
        //操作权限验证
        if(!command.equals("123456") ){
            return "no";
        }
        //清空
        SyncData.adUrlList.clear();
        SyncData.adUrlHash.clear();
        SyncData.urlList.clear();
        //重新初始化（通过配置文件）
        String urlPath = filepath;
//        URL xxxx =  this.getClass().getClassLoader().getResource(urlPath);
//        urlPath = xxxx.toString().replace(" ","").replace("file:/","");
        try {
            String encoding="GBK";
            File file=new File(urlPath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String [] keyValue = lineTxt.split("oooo");
                    SyncData.adUrlList.add(keyValue[1]);
                    SyncData.adUrlHash.put(keyValue[0],keyValue[1]);
                    SyncData.urlList.add(keyValue[1]);
                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                logger.info("找不到指定的文件");
            }
        } catch (Exception e) {
            logger.info("读取文件内容出错");
            e.printStackTrace();
        }
//        try{
//            SyncData.adUrlList =  redisClinet.getList("address");
//        }catch ( Exception ex){
//            logger.debug(ex.getMessage());
//        }
        return  "ok";
    }
}
