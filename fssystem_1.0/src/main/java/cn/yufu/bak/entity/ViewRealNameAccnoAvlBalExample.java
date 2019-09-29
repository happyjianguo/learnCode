package cn.yufu.bak.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewRealNameAccnoAvlBalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViewRealNameAccnoAvlBalExample() {
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

        public Criteria andCardbinIsNull() {
            addCriterion("CARDBIN is null");
            return (Criteria) this;
        }

        public Criteria andCardbinIsNotNull() {
            addCriterion("CARDBIN is not null");
            return (Criteria) this;
        }

        public Criteria andCardbinEqualTo(String value) {
            addCriterion("CARDBIN =", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinNotEqualTo(String value) {
            addCriterion("CARDBIN <>", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinGreaterThan(String value) {
            addCriterion("CARDBIN >", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinGreaterThanOrEqualTo(String value) {
            addCriterion("CARDBIN >=", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinLessThan(String value) {
            addCriterion("CARDBIN <", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinLessThanOrEqualTo(String value) {
            addCriterion("CARDBIN <=", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinLike(String value) {
            addCriterion("CARDBIN like", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinNotLike(String value) {
            addCriterion("CARDBIN not like", value, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinIn(List<String> values) {
            addCriterion("CARDBIN in", values, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinNotIn(List<String> values) {
            addCriterion("CARDBIN not in", values, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinBetween(String value1, String value2) {
            addCriterion("CARDBIN between", value1, value2, "cardbin");
            return (Criteria) this;
        }

        public Criteria andCardbinNotBetween(String value1, String value2) {
            addCriterion("CARDBIN not between", value1, value2, "cardbin");
            return (Criteria) this;
        }

        public Criteria andPanIsNull() {
            addCriterion("PAN is null");
            return (Criteria) this;
        }

        public Criteria andPanIsNotNull() {
            addCriterion("PAN is not null");
            return (Criteria) this;
        }

        public Criteria andPanEqualTo(String value) {
            addCriterion("PAN =", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotEqualTo(String value) {
            addCriterion("PAN <>", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThan(String value) {
            addCriterion("PAN >", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThanOrEqualTo(String value) {
            addCriterion("PAN >=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThan(String value) {
            addCriterion("PAN <", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThanOrEqualTo(String value) {
            addCriterion("PAN <=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLike(String value) {
            addCriterion("PAN like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotLike(String value) {
            addCriterion("PAN not like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanIn(List<String> values) {
            addCriterion("PAN in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotIn(List<String> values) {
            addCriterion("PAN not in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanBetween(String value1, String value2) {
            addCriterion("PAN between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotBetween(String value1, String value2) {
            addCriterion("PAN not between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andStatcodeIsNull() {
            addCriterion("STATCODE is null");
            return (Criteria) this;
        }

        public Criteria andStatcodeIsNotNull() {
            addCriterion("STATCODE is not null");
            return (Criteria) this;
        }

        public Criteria andStatcodeEqualTo(String value) {
            addCriterion("STATCODE =", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeNotEqualTo(String value) {
            addCriterion("STATCODE <>", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeGreaterThan(String value) {
            addCriterion("STATCODE >", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATCODE >=", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeLessThan(String value) {
            addCriterion("STATCODE <", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeLessThanOrEqualTo(String value) {
            addCriterion("STATCODE <=", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeLike(String value) {
            addCriterion("STATCODE like", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeNotLike(String value) {
            addCriterion("STATCODE not like", value, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeIn(List<String> values) {
            addCriterion("STATCODE in", values, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeNotIn(List<String> values) {
            addCriterion("STATCODE not in", values, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeBetween(String value1, String value2) {
            addCriterion("STATCODE between", value1, value2, "statcode");
            return (Criteria) this;
        }

        public Criteria andStatcodeNotBetween(String value1, String value2) {
            addCriterion("STATCODE not between", value1, value2, "statcode");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andAv01IsNull() {
            addCriterion("AV01 is null");
            return (Criteria) this;
        }

        public Criteria andAv01IsNotNull() {
            addCriterion("AV01 is not null");
            return (Criteria) this;
        }

        public Criteria andAv01EqualTo(BigDecimal value) {
            addCriterion("AV01 =", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01NotEqualTo(BigDecimal value) {
            addCriterion("AV01 <>", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01GreaterThan(BigDecimal value) {
            addCriterion("AV01 >", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AV01 >=", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01LessThan(BigDecimal value) {
            addCriterion("AV01 <", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01LessThanOrEqualTo(BigDecimal value) {
            addCriterion("AV01 <=", value, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01In(List<BigDecimal> values) {
            addCriterion("AV01 in", values, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01NotIn(List<BigDecimal> values) {
            addCriterion("AV01 not in", values, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV01 between", value1, value2, "av01");
            return (Criteria) this;
        }

        public Criteria andAv01NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV01 not between", value1, value2, "av01");
            return (Criteria) this;
        }

        public Criteria andAv02IsNull() {
            addCriterion("AV02 is null");
            return (Criteria) this;
        }

        public Criteria andAv02IsNotNull() {
            addCriterion("AV02 is not null");
            return (Criteria) this;
        }

        public Criteria andAv02EqualTo(BigDecimal value) {
            addCriterion("AV02 =", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02NotEqualTo(BigDecimal value) {
            addCriterion("AV02 <>", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02GreaterThan(BigDecimal value) {
            addCriterion("AV02 >", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AV02 >=", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02LessThan(BigDecimal value) {
            addCriterion("AV02 <", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02LessThanOrEqualTo(BigDecimal value) {
            addCriterion("AV02 <=", value, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02In(List<BigDecimal> values) {
            addCriterion("AV02 in", values, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02NotIn(List<BigDecimal> values) {
            addCriterion("AV02 not in", values, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV02 between", value1, value2, "av02");
            return (Criteria) this;
        }

        public Criteria andAv02NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV02 not between", value1, value2, "av02");
            return (Criteria) this;
        }

        public Criteria andAv04IsNull() {
            addCriterion("AV04 is null");
            return (Criteria) this;
        }

        public Criteria andAv04IsNotNull() {
            addCriterion("AV04 is not null");
            return (Criteria) this;
        }

        public Criteria andAv04EqualTo(BigDecimal value) {
            addCriterion("AV04 =", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04NotEqualTo(BigDecimal value) {
            addCriterion("AV04 <>", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04GreaterThan(BigDecimal value) {
            addCriterion("AV04 >", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AV04 >=", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04LessThan(BigDecimal value) {
            addCriterion("AV04 <", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04LessThanOrEqualTo(BigDecimal value) {
            addCriterion("AV04 <=", value, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04In(List<BigDecimal> values) {
            addCriterion("AV04 in", values, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04NotIn(List<BigDecimal> values) {
            addCriterion("AV04 not in", values, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV04 between", value1, value2, "av04");
            return (Criteria) this;
        }

        public Criteria andAv04NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV04 not between", value1, value2, "av04");
            return (Criteria) this;
        }

        public Criteria andAv09IsNull() {
            addCriterion("AV09 is null");
            return (Criteria) this;
        }

        public Criteria andAv09IsNotNull() {
            addCriterion("AV09 is not null");
            return (Criteria) this;
        }

        public Criteria andAv09EqualTo(BigDecimal value) {
            addCriterion("AV09 =", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09NotEqualTo(BigDecimal value) {
            addCriterion("AV09 <>", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09GreaterThan(BigDecimal value) {
            addCriterion("AV09 >", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AV09 >=", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09LessThan(BigDecimal value) {
            addCriterion("AV09 <", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09LessThanOrEqualTo(BigDecimal value) {
            addCriterion("AV09 <=", value, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09In(List<BigDecimal> values) {
            addCriterion("AV09 in", values, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09NotIn(List<BigDecimal> values) {
            addCriterion("AV09 not in", values, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV09 between", value1, value2, "av09");
            return (Criteria) this;
        }

        public Criteria andAv09NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AV09 not between", value1, value2, "av09");
            return (Criteria) this;
        }

        public Criteria andTotalBalIsNull() {
            addCriterion("TOTAL_BAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalBalIsNotNull() {
            addCriterion("TOTAL_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBalEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL =", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL <>", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_BAL >", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL >=", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalLessThan(BigDecimal value) {
            addCriterion("TOTAL_BAL <", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL <=", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalIn(List<BigDecimal> values) {
            addCriterion("TOTAL_BAL in", values, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_BAL not in", values, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_BAL between", value1, value2, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_BAL not between", value1, value2, "totalBal");
            return (Criteria) this;
        }
        
        public Criteria andAv01AndAv02EqualTo(BigDecimal value) {
            addCriterion("(AV01 + AV02) =", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv01AndAv02GreaterThan(BigDecimal value) {
            addCriterion("(AV01 + AV02) >", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv01AndAv02GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("(AV01 + AV02) >=", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv01AndAv02LessThan(BigDecimal value) {
            addCriterion("(AV01 + AV02) <", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv01AndAv02LessThanOrEqualTo(BigDecimal value) {
            addCriterion("(AV01 + AV02) <=", value, "avlThreshold");
            return (Criteria) this;
        }
        
        public Criteria andAv04AndAv09EqualTo(BigDecimal value) {
            addCriterion("(AV04 + AV09) =", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv04AndAv09GreaterThan(BigDecimal value) {
            addCriterion("(AV04 + AV09) >", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv04AndAv09GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("(AV04 + AV09) >=", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv04AndAv09LessThan(BigDecimal value) {
            addCriterion("(AV04 + AV09) <", value, "avlThreshold");
            return (Criteria) this;
        }

        public Criteria andAv04AndAv09LessThanOrEqualTo(BigDecimal value) {
            addCriterion("(AV04 + AV09) <=", value, "avlThreshold");
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