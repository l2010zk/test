package com.ad.cpm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lzk on 2017/3/8.
 */
public class Constants {

    public static String APP_ROOT_PATH;
    public static ServletContext APP_ROOT_CONTEXT;
    public static String CONFIG_PATH;
    public static Properties CONFIG;

    public static final String KEY_IP_DAY_INTERCEPT = "intercept.ip.day";
    public static final String KEY_COOKIE_DAY_INTERCEPT = "intercept.cookie.day";
    public static final String KEY_IP_HOUR_INTERCEPT = "intercept.ip.hour";
    public static final String KEY_COOKIE_HOUR_INTERCEPT = "intercept.cookie.hour";
    public static final String BIZ_LOG_LOGO = "REQUEST@AD|";

    public static final String ALARM_PROPORTION = "alarm.proportion";
    public static final String ALARM_MAIL= "alarm.mail.address";
    public static final String ALARM_CHANNELS= "alarm.channel.ids";

    public static final String REDIS_DB_INNER = "inner";
    public static final String REDIS_DB_OUT = "out";
    public static final String REDIS_DB_IRE = "ire";

    public static final String LOSE_RATIO = "loseratio";
    public static final String LOSE_COUNT = "losecount";

    public static final String[] REFERERS = {Constants.REDIS_DB_OUT,Constants.REDIS_DB_INNER,Constants.REDIS_DB_IRE};


    public static void init(){
        CONFIG_PATH = APP_ROOT_PATH+"WEB-INF"+ File.separator+"classes"+File.separator+"config.properties";
        loadConfig();
    }


    /**
     * 读取配置文件
     * @param path
     * @return
     */
    @SuppressWarnings("unused")
    public static Properties loadConfig(){
        File file= null;
        if(CONFIG_PATH!=null){
            new File(CONFIG_PATH);
        }
        if(file!=null && file.exists()){
            CONFIG = new Properties();
            try {
                CONFIG.load(new FileInputStream(file));
                return CONFIG;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Resource resource = (Resource) new ClassPathResource("/config.properties");
            try {
                CONFIG = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CONFIG;
    }

    public static String getConfig(String key){
        if(CONFIG==null){
            CONFIG = loadConfig();
        }
        return CONFIG.getProperty(key);
    }
}
