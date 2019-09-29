package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SuspiciousOrderEarlyWarningExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SuspiciousOrderEarlyWarningExample() {
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

        public Criteria andOrdercodeIsNull() {
            addCriterion("ORDERCODE is null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIsNotNull() {
            addCriterion("ORDERCODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeEqualTo(String value) {
            addCriterion("ORDERCODE =", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotEqualTo(String value) {
            addCriterion("ORDERCODE <>", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThan(String value) {
            addCriterion("ORDERCODE >", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDERCODE >=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThan(String value) {
            addCriterion("ORDERCODE <", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThanOrEqualTo(String value) {
            addCriterion("ORDERCODE <=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLike(String value) {
            addCriterion("ORDERCODE like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotLike(String value) {
            addCriterion("ORDERCODE not like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIn(List<String> values) {
            addCriterion("ORDERCODE in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotIn(List<String> values) {
            addCriterion("ORDERCODE not in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeBetween(String value1, String value2) {
            addCriterion("ORDERCODE between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotBetween(String value1, String value2) {
            addCriterion("ORDERCODE not between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNull() {
            addCriterion("PURCHASE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNotNull() {
            addCriterion("PURCHASE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateEqualTo(String value) {
            addCriterion("PURCHASE_DATE =", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotEqualTo(String value) {
            addCriterion("PURCHASE_DATE <>", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThan(String value) {
            addCriterion("PURCHASE_DATE >", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("PURCHASE_DATE >=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThan(String value) {
            addCriterion("PURCHASE_DATE <", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThanOrEqualTo(String value) {
            addCriterion("PURCHASE_DATE <=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLike(String value) {
            addCriterion("PURCHASE_DATE like", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotLike(String value) {
            addCriterion("PURCHASE_DATE not like", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIn(List<String> values) {
            addCriterion("PURCHASE_DATE in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotIn(List<String> values) {
            addCriterion("PURCHASE_DATE not in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateBetween(String value1, String value2) {
            addCriterion("PURCHASE_DATE between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotBetween(String value1, String value2) {
            addCriterion("PURCHASE_DATE not between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtIsNull() {
            addCriterion("PURCHASE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtIsNotNull() {
            addCriterion("PURCHASE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtEqualTo(BigDecimal value) {
            addCriterion("PURCHASE_AMT =", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtNotEqualTo(BigDecimal value) {
            addCriterion("PURCHASE_AMT <>", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtGreaterThan(BigDecimal value) {
            addCriterion("PURCHASE_AMT >", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PURCHASE_AMT >=", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtLessThan(BigDecimal value) {
            addCriterion("PURCHASE_AMT <", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PURCHASE_AMT <=", value, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtIn(List<BigDecimal> values) {
            addCriterion("PURCHASE_AMT in", values, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtNotIn(List<BigDecimal> values) {
            addCriterion("PURCHASE_AMT not in", values, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PURCHASE_AMT between", value1, value2, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PURCHASE_AMT not between", value1, value2, "purchaseAmt");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateIsNull() {
            addCriterion("SUSPICIOUS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateIsNotNull() {
            addCriterion("SUSPICIOUS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateEqualTo(String value) {
            addCriterion("SUSPICIOUS_DATE =", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateNotEqualTo(String value) {
            addCriterion("SUSPICIOUS_DATE <>", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateGreaterThan(String value) {
            addCriterion("SUSPICIOUS_DATE >", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateGreaterThanOrEqualTo(String value) {
            addCriterion("SUSPICIOUS_DATE >=", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateLessThan(String value) {
            addCriterion("SUSPICIOUS_DATE <", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateLessThanOrEqualTo(String value) {
            addCriterion("SUSPICIOUS_DATE <=", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateLike(String value) {
            addCriterion("SUSPICIOUS_DATE like", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateNotLike(String value) {
            addCriterion("SUSPICIOUS_DATE not like", value, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateIn(List<String> values) {
            addCriterion("SUSPICIOUS_DATE in", values, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateNotIn(List<String> values) {
            addCriterion("SUSPICIOUS_DATE not in", values, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateBetween(String value1, String value2) {
            addCriterion("SUSPICIOUS_DATE between", value1, value2, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andSuspiciousDateNotBetween(String value1, String value2) {
            addCriterion("SUSPICIOUS_DATE not between", value1, value2, "suspiciousDate");
            return (Criteria) this;
        }

        public Criteria andProvisionsIsNull() {
            addCriterion("PROVISIONS is null");
            return (Criteria) this;
        }

        public Criteria andProvisionsIsNotNull() {
            addCriterion("PROVISIONS is not null");
            return (Criteria) this;
        }

        public Criteria andProvisionsEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS =", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS <>", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsGreaterThan(BigDecimal value) {
            addCriterion("PROVISIONS >", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS >=", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsLessThan(BigDecimal value) {
            addCriterion("PROVISIONS <", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS <=", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS in", values, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS not in", values, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS between", value1, value2, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS not between", value1, value2, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateIsNull() {
            addCriterion("PROVISIONS_RATE is null");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateIsNotNull() {
            addCriterion("PROVISIONS_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_RATE =", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateNotEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_RATE <>", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateGreaterThan(BigDecimal value) {
            addCriterion("PROVISIONS_RATE >", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_RATE >=", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateLessThan(BigDecimal value) {
            addCriterion("PROVISIONS_RATE <", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_RATE <=", value, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS_RATE in", values, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateNotIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS_RATE not in", values, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS_RATE between", value1, value2, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andProvisionsRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS_RATE not between", value1, value2, "provisionsRate");
            return (Criteria) this;
        }

        public Criteria andFreeField3IsNull() {
            addCriterion("FREE_FIELD3 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField3IsNotNull() {
            addCriterion("FREE_FIELD3 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField3EqualTo(String value) {
            addCriterion("FREE_FIELD3 =", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotEqualTo(String value) {
            addCriterion("FREE_FIELD3 <>", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3GreaterThan(String value) {
            addCriterion("FREE_FIELD3 >", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD3 >=", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3LessThan(String value) {
            addCriterion("FREE_FIELD3 <", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD3 <=", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3Like(String value) {
            addCriterion("FREE_FIELD3 like", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotLike(String value) {
            addCriterion("FREE_FIELD3 not like", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3In(List<String> values) {
            addCriterion("FREE_FIELD3 in", values, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotIn(List<String> values) {
            addCriterion("FREE_FIELD3 not in", values, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3Between(String value1, String value2) {
            addCriterion("FREE_FIELD3 between", value1, value2, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD3 not between", value1, value2, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField4IsNull() {
            addCriterion("FREE_FIELD4 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField4IsNotNull() {
            addCriterion("FREE_FIELD4 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField4EqualTo(String value) {
            addCriterion("FREE_FIELD4 =", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotEqualTo(String value) {
            addCriterion("FREE_FIELD4 <>", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4GreaterThan(String value) {
            addCriterion("FREE_FIELD4 >", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD4 >=", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4LessThan(String value) {
            addCriterion("FREE_FIELD4 <", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD4 <=", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4Like(String value) {
            addCriterion("FREE_FIELD4 like", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotLike(String value) {
            addCriterion("FREE_FIELD4 not like", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4In(List<String> values) {
            addCriterion("FREE_FIELD4 in", values, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotIn(List<String> values) {
            addCriterion("FREE_FIELD4 not in", values, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4Between(String value1, String value2) {
            addCriterion("FREE_FIELD4 between", value1, value2, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD4 not between", value1, value2, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField5IsNull() {
            addCriterion("FREE_FIELD5 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField5IsNotNull() {
            addCriterion("FREE_FIELD5 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField5EqualTo(String value) {
            addCriterion("FREE_FIELD5 =", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotEqualTo(String value) {
            addCriterion("FREE_FIELD5 <>", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5GreaterThan(String value) {
            addCriterion("FREE_FIELD5 >", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD5 >=", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5LessThan(String value) {
            addCriterion("FREE_FIELD5 <", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD5 <=", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5Like(String value) {
            addCriterion("FREE_FIELD5 like", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotLike(String value) {
            addCriterion("FREE_FIELD5 not like", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5In(List<String> values) {
            addCriterion("FREE_FIELD5 in", values, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotIn(List<String> values) {
            addCriterion("FREE_FIELD5 not in", values, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5Between(String value1, String value2) {
            addCriterion("FREE_FIELD5 between", value1, value2, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD5 not between", value1, value2, "freeField5");
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