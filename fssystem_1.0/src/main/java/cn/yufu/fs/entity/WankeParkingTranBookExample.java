package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WankeParkingTranBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WankeParkingTranBookExample() {
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

        public Criteria andMerNoIsNull() {
            addCriterion("MER_NO is null");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNotNull() {
            addCriterion("MER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMerNoEqualTo(String value) {
            addCriterion("MER_NO =", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotEqualTo(String value) {
            addCriterion("MER_NO <>", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThan(String value) {
            addCriterion("MER_NO >", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("MER_NO >=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThan(String value) {
            addCriterion("MER_NO <", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThanOrEqualTo(String value) {
            addCriterion("MER_NO <=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLike(String value) {
            addCriterion("MER_NO like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotLike(String value) {
            addCriterion("MER_NO not like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIn(List<String> values) {
            addCriterion("MER_NO in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotIn(List<String> values) {
            addCriterion("MER_NO not in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoBetween(String value1, String value2) {
            addCriterion("MER_NO between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotBetween(String value1, String value2) {
            addCriterion("MER_NO not between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNull() {
            addCriterion("MER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNotNull() {
            addCriterion("MER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerNameEqualTo(String value) {
            addCriterion("MER_NAME =", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotEqualTo(String value) {
            addCriterion("MER_NAME <>", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThan(String value) {
            addCriterion("MER_NAME >", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("MER_NAME >=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThan(String value) {
            addCriterion("MER_NAME <", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThanOrEqualTo(String value) {
            addCriterion("MER_NAME <=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLike(String value) {
            addCriterion("MER_NAME like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotLike(String value) {
            addCriterion("MER_NAME not like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameIn(List<String> values) {
            addCriterion("MER_NAME in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotIn(List<String> values) {
            addCriterion("MER_NAME not in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameBetween(String value1, String value2) {
            addCriterion("MER_NAME between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotBetween(String value1, String value2) {
            addCriterion("MER_NAME not between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andTerNoIsNull() {
            addCriterion("TER_NO is null");
            return (Criteria) this;
        }

        public Criteria andTerNoIsNotNull() {
            addCriterion("TER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTerNoEqualTo(String value) {
            addCriterion("TER_NO =", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotEqualTo(String value) {
            addCriterion("TER_NO <>", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoGreaterThan(String value) {
            addCriterion("TER_NO >", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoGreaterThanOrEqualTo(String value) {
            addCriterion("TER_NO >=", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLessThan(String value) {
            addCriterion("TER_NO <", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLessThanOrEqualTo(String value) {
            addCriterion("TER_NO <=", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLike(String value) {
            addCriterion("TER_NO like", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotLike(String value) {
            addCriterion("TER_NO not like", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoIn(List<String> values) {
            addCriterion("TER_NO in", values, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotIn(List<String> values) {
            addCriterion("TER_NO not in", values, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoBetween(String value1, String value2) {
            addCriterion("TER_NO between", value1, value2, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotBetween(String value1, String value2) {
            addCriterion("TER_NO not between", value1, value2, "terNo");
            return (Criteria) this;
        }

        public Criteria andCardnumberIsNull() {
            addCriterion("CARDNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCardnumberIsNotNull() {
            addCriterion("CARDNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCardnumberEqualTo(String value) {
            addCriterion("CARDNUMBER =", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotEqualTo(String value) {
            addCriterion("CARDNUMBER <>", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberGreaterThan(String value) {
            addCriterion("CARDNUMBER >", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberGreaterThanOrEqualTo(String value) {
            addCriterion("CARDNUMBER >=", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLessThan(String value) {
            addCriterion("CARDNUMBER <", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLessThanOrEqualTo(String value) {
            addCriterion("CARDNUMBER <=", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLike(String value) {
            addCriterion("CARDNUMBER like", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotLike(String value) {
            addCriterion("CARDNUMBER not like", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberIn(List<String> values) {
            addCriterion("CARDNUMBER in", values, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotIn(List<String> values) {
            addCriterion("CARDNUMBER not in", values, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberBetween(String value1, String value2) {
            addCriterion("CARDNUMBER between", value1, value2, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotBetween(String value1, String value2) {
            addCriterion("CARDNUMBER not between", value1, value2, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(String value) {
            addCriterion("CARD_TYPE =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(String value) {
            addCriterion("CARD_TYPE <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(String value) {
            addCriterion("CARD_TYPE >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(String value) {
            addCriterion("CARD_TYPE <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(String value) {
            addCriterion("CARD_TYPE like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(String value) {
            addCriterion("CARD_TYPE not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<String> values) {
            addCriterion("CARD_TYPE in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<String> values) {
            addCriterion("CARD_TYPE not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(String value1, String value2) {
            addCriterion("CARD_TYPE between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIsNull() {
            addCriterion("TRANSACTIONTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIsNotNull() {
            addCriterion("TRANSACTIONTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE =", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE <>", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeGreaterThan(String value) {
            addCriterion("TRANSACTIONTYPE >", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE >=", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLessThan(String value) {
            addCriterion("TRANSACTIONTYPE <", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE <=", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLike(String value) {
            addCriterion("TRANSACTIONTYPE like", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotLike(String value) {
            addCriterion("TRANSACTIONTYPE not like", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIn(List<String> values) {
            addCriterion("TRANSACTIONTYPE in", values, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotIn(List<String> values) {
            addCriterion("TRANSACTIONTYPE not in", values, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTYPE between", value1, value2, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTYPE not between", value1, value2, "transactiontype");
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

        public Criteria andTransactiontimeIsNull() {
            addCriterion("TRANSACTIONTIME is null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIsNotNull() {
            addCriterion("TRANSACTIONTIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeEqualTo(String value) {
            addCriterion("TRANSACTIONTIME =", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <>", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThan(String value) {
            addCriterion("TRANSACTIONTIME >", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME >=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThan(String value) {
            addCriterion("TRANSACTIONTIME <", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLike(String value) {
            addCriterion("TRANSACTIONTIME like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotLike(String value) {
            addCriterion("TRANSACTIONTIME not like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIn(List<String> values) {
            addCriterion("TRANSACTIONTIME in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotIn(List<String> values) {
            addCriterion("TRANSACTIONTIME not in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME between", value1, value2, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME not between", value1, value2, "transactiontime");
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