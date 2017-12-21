package com.blog.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy(false)
public class DemoTask {

    private static final Logger logger = LogManager.getLogger(DemoTask.class);

    public void fistTask(){
//        logger.info("fistTask task start");
//        try {
//            logger.info("fistTask task process");
//        }catch (Exception e){
//            logger.error("fist task error",e);
//        }
//        logger.info("fistTask task end");
    }

}
