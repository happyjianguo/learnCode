package cn.yufu.bak.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionrecordsDailyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionrecordsDailyExample() {
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

        public Criteria andMerchantnumberIsNull() {
            addCriterion("MERCHANTNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIsNotNull() {
            addCriterion("MERCHANTNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberEqualTo(String value) {
            addCriterion("MERCHANTNUMBER =", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <>", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThan(String value) {
            addCriterion("MERCHANTNUMBER >", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER >=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThan(String value) {
            addCriterion("MERCHANTNUMBER <", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLike(String value) {
            addCriterion("MERCHANTNUMBER like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotLike(String value) {
            addCriterion("MERCHANTNUMBER not like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIn(List<String> values) {
            addCriterion("MERCHANTNUMBER in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotIn(List<String> values) {
            addCriterion("MERCHANTNUMBER not in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER not between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNull() {
            addCriterion("TRANSACTIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNotNull() {
            addCriterion("TRANSACTIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateEqualTo(String value) {
            addCriterion("TRANSACTIONDATE =", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <>", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThan(String value) {
            addCriterion("TRANSACTIONDATE >", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE >=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThan(String value) {
            addCriterion("TRANSACTIONDATE <", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLike(String value) {
            addCriterion("TRANSACTIONDATE like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotLike(String value) {
            addCriterion("TRANSACTIONDATE not like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIn(List<String> values) {
            addCriterion("TRANSACTIONDATE in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotIn(List<String> values) {
            addCriterion("TRANSACTIONDATE not in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE between", value1, value2, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE not between", value1, value2, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumIsNull() {
            addCriterion("TRANSACTIONMONEY_SUM is null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumIsNotNull() {
            addCriterion("TRANSACTIONMONEY_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM =", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM <>", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM >", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM >=", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumLessThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM <", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_SUM <=", value, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY_SUM in", values, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY_SUM not in", values, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY_SUM between", value1, value2, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneySumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY_SUM not between", value1, value2, "transactionmoneySum");
            return (Criteria) this;
        }

        public Criteria andMomments1IsNull() {
            addCriterion("MOMMENTS1 is null");
            return (Criteria) this;
        }

        public Criteria andMomments1IsNotNull() {
            addCriterion("MOMMENTS1 is not null");
            return (Criteria) this;
        }

        public Criteria andMomments1EqualTo(String value) {
            addCriterion("MOMMENTS1 =", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1NotEqualTo(String value) {
            addCriterion("MOMMENTS1 <>", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1GreaterThan(String value) {
            addCriterion("MOMMENTS1 >", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1GreaterThanOrEqualTo(String value) {
            addCriterion("MOMMENTS1 >=", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1LessThan(String value) {
            addCriterion("MOMMENTS1 <", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1LessThanOrEqualTo(String value) {
            addCriterion("MOMMENTS1 <=", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1Like(String value) {
            addCriterion("MOMMENTS1 like", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1NotLike(String value) {
            addCriterion("MOMMENTS1 not like", value, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1In(List<String> values) {
            addCriterion("MOMMENTS1 in", values, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1NotIn(List<String> values) {
            addCriterion("MOMMENTS1 not in", values, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1Between(String value1, String value2) {
            addCriterion("MOMMENTS1 between", value1, value2, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments1NotBetween(String value1, String value2) {
            addCriterion("MOMMENTS1 not between", value1, value2, "momments1");
            return (Criteria) this;
        }

        public Criteria andMomments2IsNull() {
            addCriterion("MOMMENTS2 is null");
            return (Criteria) this;
        }

        public Criteria andMomments2IsNotNull() {
            addCriterion("MOMMENTS2 is not null");
            return (Criteria) this;
        }

        public Criteria andMomments2EqualTo(String value) {
            addCriterion("MOMMENTS2 =", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2NotEqualTo(String value) {
            addCriterion("MOMMENTS2 <>", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2GreaterThan(String value) {
            addCriterion("MOMMENTS2 >", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2GreaterThanOrEqualTo(String value) {
            addCriterion("MOMMENTS2 >=", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2LessThan(String value) {
            addCriterion("MOMMENTS2 <", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2LessThanOrEqualTo(String value) {
            addCriterion("MOMMENTS2 <=", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2Like(String value) {
            addCriterion("MOMMENTS2 like", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2NotLike(String value) {
            addCriterion("MOMMENTS2 not like", value, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2In(List<String> values) {
            addCriterion("MOMMENTS2 in", values, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2NotIn(List<String> values) {
            addCriterion("MOMMENTS2 not in", values, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2Between(String value1, String value2) {
            addCriterion("MOMMENTS2 between", value1, value2, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments2NotBetween(String value1, String value2) {
            addCriterion("MOMMENTS2 not between", value1, value2, "momments2");
            return (Criteria) this;
        }

        public Criteria andMomments3IsNull() {
            addCriterion("MOMMENTS3 is null");
            return (Criteria) this;
        }

        public Criteria andMomments3IsNotNull() {
            addCriterion("MOMMENTS3 is not null");
            return (Criteria) this;
        }

        public Criteria andMomments3EqualTo(String value) {
            addCriterion("MOMMENTS3 =", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3NotEqualTo(String value) {
            addCriterion("MOMMENTS3 <>", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3GreaterThan(String value) {
            addCriterion("MOMMENTS3 >", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3GreaterThanOrEqualTo(String value) {
            addCriterion("MOMMENTS3 >=", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3LessThan(String value) {
            addCriterion("MOMMENTS3 <", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3LessThanOrEqualTo(String value) {
            addCriterion("MOMMENTS3 <=", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3Like(String value) {
            addCriterion("MOMMENTS3 like", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3NotLike(String value) {
            addCriterion("MOMMENTS3 not like", value, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3In(List<String> values) {
            addCriterion("MOMMENTS3 in", values, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3NotIn(List<String> values) {
            addCriterion("MOMMENTS3 not in", values, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3Between(String value1, String value2) {
            addCriterion("MOMMENTS3 between", value1, value2, "momments3");
            return (Criteria) this;
        }

        public Criteria andMomments3NotBetween(String value1, String value2) {
            addCriterion("MOMMENTS3 not between", value1, value2, "momments3");
            return (Criteria) this;
        }

        public Criteria andTranNumIsNull() {
            addCriterion("TRAN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTranNumIsNotNull() {
            addCriterion("TRAN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTranNumEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM =", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM <>", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumGreaterThan(BigDecimal value) {
            addCriterion("TRAN_NUM >", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM >=", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumLessThan(BigDecimal value) {
            addCriterion("TRAN_NUM <", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM <=", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumIn(List<BigDecimal> values) {
            addCriterion("TRAN_NUM in", values, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotIn(List<BigDecimal> values) {
            addCriterion("TRAN_NUM not in", values, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_NUM between", value1, value2, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_NUM not between", value1, value2, "tranNum");
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