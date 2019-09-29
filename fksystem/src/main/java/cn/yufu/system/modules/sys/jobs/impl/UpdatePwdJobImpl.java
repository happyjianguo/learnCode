package cn.yufu.system.modules.sys.jobs.impl;

import cn.yufu.system.modules.sys.jobs.AbstractCronJob;
import cn.yufu.system.modules.sys.utils.PwdInvalidUtils;


/**
 * 修改用户密码是否重置任务触发器JOB
 * 
 * UpdatePwdJobImpl.java
 * @author MA HUAN
 * @2016年1月13日 上午9:22:50 www.yufusoft.com Inc. All rights reserved.
 */
public class UpdatePwdJobImpl extends AbstractCronJob{
    
    @Override
    public void doJob() {
   
        logger.debug("任务开始执行!");
        
        try {
        	PwdInvalidUtils.executeUpdateUserPwdIsReset();
        }
        catch(Throwable e) {
            logger.error("任务执行失败!", e);
        }
        
        logger.debug("任务结束执行!");
    }

}
