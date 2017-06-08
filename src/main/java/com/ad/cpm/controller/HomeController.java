package com.ad.cpm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lzk on 2017/3/11.
 */
@Controller
public class HomeController  extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Value("${spring.freemarker.charset}")
    private  String charset ;

    @Autowired
    private Environment evn;
        /**
     * 处理product 跳转
     * @param request
     * @param resp
     * @return
     */
    @RequestMapping(value = "test", produces = "application/json;charset=UTF-8")
    public String test(HttpServletRequest request, HttpServletResponse resp ) {

//        String xxxxxx = evn.getProperty("spring.datasource.url");
//        logger.info(evn.getProperty("spring.datasource.url"));
//        logger.info(charset);

        return "test" ;
    }
    @RequestMapping(value = "testtrac", produces = "application/json;charset=UTF-8")
    public String testtrac(HttpServletRequest request, HttpServletResponse resp,Model model
            ,@RequestParam(value = "para",required=false) String para  ) {

        logger.info("testtrac:"+ para);

        return "testtrac" ;
    }

}
