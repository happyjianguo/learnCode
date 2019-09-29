package cn.yufu.posp.entity;

import java.util.ArrayList;
import java.util.List;

public class MerchantRefundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantRefundExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(String value) {
            addCriterion("MERCHANT_ID =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(String value) {
            addCriterion("MERCHANT_ID <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(String value) {
            addCriterion("MERCHANT_ID >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ID >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(String value) {
            addCriterion("MERCHANT_ID <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ID <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLike(String value) {
            addCriterion("MERCHANT_ID like", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotLike(String value) {
            addCriterion("MERCHANT_ID not like", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<String> values) {
            addCriterion("MERCHANT_ID in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<String> values) {
            addCriterion("MERCHANT_ID not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(String value1, String value2) {
            addCriterion("MERCHANT_ID between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_ID not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andRefundLimitIsNull() {
            addCriterion("REFUND_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andRefundLimitIsNotNull() {
            addCriterion("REFUND_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundLimitEqualTo(Double value) {
            addCriterion("REFUND_LIMIT =", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitNotEqualTo(Double value) {
            addCriterion("REFUND_LIMIT <>", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitGreaterThan(Double value) {
            addCriterion("REFUND_LIMIT >", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitGreaterThanOrEqualTo(Double value) {
            addCriterion("REFUND_LIMIT >=", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitLessThan(Double value) {
            addCriterion("REFUND_LIMIT <", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitLessThanOrEqualTo(Double value) {
            addCriterion("REFUND_LIMIT <=", value, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitIn(List<Double> values) {
            addCriterion("REFUND_LIMIT in", values, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitNotIn(List<Double> values) {
            addCriterion("REFUND_LIMIT not in", values, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitBetween(Double value1, Double value2) {
            addCriterion("REFUND_LIMIT between", value1, value2, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundLimitNotBetween(Double value1, Double value2) {
            addCriterion("REFUND_LIMIT not between", value1, value2, "refundLimit");
            return (Criteria) this;
        }

        public Criteria andRefundCheckIsNull() {
            addCriterion("REFUND_CHECK is null");
            return (Criteria) this;
        }

        public Criteria andRefundCheckIsNotNull() {
            addCriterion("REFUND_CHECK is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCheckEqualTo(String value) {
            addCriterion("REFUND_CHECK =", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckNotEqualTo(String value) {
            addCriterion("REFUND_CHECK <>", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckGreaterThan(String value) {
            addCriterion("REFUND_CHECK >", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckGreaterThanOrEqualTo(String value) {
            addCriterion("REFUND_CHECK >=", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckLessThan(String value) {
            addCriterion("REFUND_CHECK <", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckLessThanOrEqualTo(String value) {
            addCriterion("REFUND_CHECK <=", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckLike(String value) {
            addCriterion("REFUND_CHECK like", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckNotLike(String value) {
            addCriterion("REFUND_CHECK not like", value, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckIn(List<String> values) {
            addCriterion("REFUND_CHECK in", values, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckNotIn(List<String> values) {
            addCriterion("REFUND_CHECK not in", values, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckBetween(String value1, String value2) {
            addCriterion("REFUND_CHECK between", value1, value2, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andRefundCheckNotBetween(String value1, String value2) {
            addCriterion("REFUND_CHECK not between", value1, value2, "refundCheck");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIsNull() {
            addCriterion("UPDATE_OPER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIsNotNull() {
            addCriterion("UPDATE_OPER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperEqualTo(String value) {
            addCriterion("UPDATE_OPER =", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotEqualTo(String value) {
            addCriterion("UPDATE_OPER <>", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperGreaterThan(String value) {
            addCriterion("UPDATE_OPER >", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER >=", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLessThan(String value) {
            addCriterion("UPDATE_OPER <", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER <=", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLike(String value) {
            addCriterion("UPDATE_OPER like", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotLike(String value) {
            addCriterion("UPDATE_OPER not like", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIn(List<String> values) {
            addCriterion("UPDATE_OPER in", values, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotIn(List<String> values) {
            addCriterion("UPDATE_OPER not in", values, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER between", value1, value2, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER not between", value1, value2, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(String value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(String value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(String value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(String value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLike(String value) {
            addCriterion("UPDATE_DATE like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotLike(String value) {
            addCriterion("UPDATE_DATE not like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<String> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<String> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(String value1, String value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(String value1, String value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("UPDATE_TIME like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("UPDATE_TIME not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}