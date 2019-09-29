package cn.com.jansh.entity.weixin;

import java.math.BigDecimal;

/**
 * 用户关注分析人数Entity
 * @author gll
 * @version 1.0
 */
public class UserAnalysisTotal {
private BigDecimal subscribeTotal;//总关注人数
private BigDecimal subscribeNow;//目前关注人数
private BigDecimal subscribeCancle;//取消关注的人数

public BigDecimal getSubscribeTotal() {
	return subscribeTotal;
}

public void setSubscribeTotal(BigDecimal subscribeTotal) {
	this.subscribeTotal = subscribeTotal;
}

public BigDecimal getSubscribeNow() {
	return subscribeNow;
}

public void setSubscribeNow(BigDecimal subscribeNow) {
	this.subscribeNow = subscribeNow;
}

public BigDecimal getSubscribeCancle() {
	return subscribeCancle;
}

public void setSubscribeCancle(BigDecimal subscribeCancle) {
	this.subscribeCancle = subscribeCancle;
}

@Override
public String toString() {
	return "UserAnalysisTotal [subscribeTotal=" + subscribeTotal + ", subscribeNow=" + subscribeNow
			+ ", subscribeCancle=" + subscribeCancle + "]";
}
}
