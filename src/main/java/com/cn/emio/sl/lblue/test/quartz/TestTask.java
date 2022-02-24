package com.cn.emio.sl.lblue.test.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Silence_Lurker
 */
public class TestTask {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void run() {
        logger.info("定时器运行……");
    }
}