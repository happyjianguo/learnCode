package cn.yufu.system.modules.sys.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务抽象类，定义模版
 * 
 * @AbstractCronJob.java
 * @author MA HUAN
 * @2016年1月4日 下午7:36:30 www.yufusoft.com Inc. All rights reserved.
 */
public abstract class AbstractCronJob implements CronJob {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /*
     *是否启用报警 
     */
    private String alarm;
    
    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        try {
            doJob();
        } catch (Throwable e) {
            logger.error("Unhandled Error!", e);
        }
    }

    public abstract void doJob();
}
