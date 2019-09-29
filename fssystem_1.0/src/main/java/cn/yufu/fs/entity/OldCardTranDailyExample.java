package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OldCardTranDailyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OldCardTranDailyExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIsNull() {
            addCriterion("TRANSACTIONMONEY is null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIsNotNull() {
            addCriterion("TRANSACTIONMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY =", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <>", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY not in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY not between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccIsNull() {
            addCriterion("TRANSACTIONMONEY_ACC is null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccIsNotNull() {
            addCriterion("TRANSACTIONMONEY_ACC is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC =", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC <>", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC >", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC >=", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccLessThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC <", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY_ACC <=", value, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY_ACC in", values, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY_ACC not in", values, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY_ACC between", value1, value2, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyAccNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY_ACC not between", value1, value2, "transactionmoneyAcc");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtIsNull() {
            addCriterion("PROVISIONS_AMT is null");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtIsNotNull() {
            addCriterion("PROVISIONS_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_AMT =", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtNotEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_AMT <>", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtGreaterThan(BigDecimal value) {
            addCriterion("PROVISIONS_AMT >", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_AMT >=", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtLessThan(BigDecimal value) {
            addCriterion("PROVISIONS_AMT <", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PROVISIONS_AMT <=", value, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS_AMT in", values, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtNotIn(List<BigDecimal> values) {
            addCriterion("PROVISIONS_AMT not in", values, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS_AMT between", value1, value2, "provisionsAmt");
            return (Criteria) this;
        }

        public Criteria andProvisionsAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROVISIONS_AMT not between", value1, value2, "provisionsAmt");
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

        public Criteria andCardSumIsNull() {
            addCriterion("CARD_SUM is null");
            return (Criteria) this;
        }

        public Criteria andCardSumIsNotNull() {
            addCriterion("CARD_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andCardSumEqualTo(BigDecimal value) {
            addCriterion("CARD_SUM =", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumNotEqualTo(BigDecimal value) {
            addCriterion("CARD_SUM <>", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumGreaterThan(BigDecimal value) {
            addCriterion("CARD_SUM >", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_SUM >=", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumLessThan(BigDecimal value) {
            addCriterion("CARD_SUM <", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_SUM <=", value, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumIn(List<BigDecimal> values) {
            addCriterion("CARD_SUM in", values, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumNotIn(List<BigDecimal> values) {
            addCriterion("CARD_SUM not in", values, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_SUM between", value1, value2, "cardSum");
            return (Criteria) this;
        }

        public Criteria andCardSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_SUM not between", value1, value2, "cardSum");
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