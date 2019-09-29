package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClientAccBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClientAccBalanceExample() {
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

        public Criteria andGenDtIsNull() {
            addCriterion("GEN_DT is null");
            return (Criteria) this;
        }

        public Criteria andGenDtIsNotNull() {
            addCriterion("GEN_DT is not null");
            return (Criteria) this;
        }

        public Criteria andGenDtEqualTo(String value) {
            addCriterion("GEN_DT =", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotEqualTo(String value) {
            addCriterion("GEN_DT <>", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtGreaterThan(String value) {
            addCriterion("GEN_DT >", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtGreaterThanOrEqualTo(String value) {
            addCriterion("GEN_DT >=", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLessThan(String value) {
            addCriterion("GEN_DT <", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLessThanOrEqualTo(String value) {
            addCriterion("GEN_DT <=", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLike(String value) {
            addCriterion("GEN_DT like", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotLike(String value) {
            addCriterion("GEN_DT not like", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtIn(List<String> values) {
            addCriterion("GEN_DT in", values, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotIn(List<String> values) {
            addCriterion("GEN_DT not in", values, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtBetween(String value1, String value2) {
            addCriterion("GEN_DT between", value1, value2, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotBetween(String value1, String value2) {
            addCriterion("GEN_DT not between", value1, value2, "genDt");
            return (Criteria) this;
        }

        public Criteria andDailyDateIsNull() {
            addCriterion("DAILY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDailyDateIsNotNull() {
            addCriterion("DAILY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDailyDateEqualTo(String value) {
            addCriterion("DAILY_DATE =", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotEqualTo(String value) {
            addCriterion("DAILY_DATE <>", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThan(String value) {
            addCriterion("DAILY_DATE >", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThanOrEqualTo(String value) {
            addCriterion("DAILY_DATE >=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThan(String value) {
            addCriterion("DAILY_DATE <", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThanOrEqualTo(String value) {
            addCriterion("DAILY_DATE <=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLike(String value) {
            addCriterion("DAILY_DATE like", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotLike(String value) {
            addCriterion("DAILY_DATE not like", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateIn(List<String> values) {
            addCriterion("DAILY_DATE in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotIn(List<String> values) {
            addCriterion("DAILY_DATE not in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateBetween(String value1, String value2) {
            addCriterion("DAILY_DATE between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotBetween(String value1, String value2) {
            addCriterion("DAILY_DATE not between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andCardBalIsNull() {
            addCriterion("CARD_BAL is null");
            return (Criteria) this;
        }

        public Criteria andCardBalIsNotNull() {
            addCriterion("CARD_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andCardBalEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL =", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL <>", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalGreaterThan(BigDecimal value) {
            addCriterion("CARD_BAL >", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL >=", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalLessThan(BigDecimal value) {
            addCriterion("CARD_BAL <", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL <=", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalIn(List<BigDecimal> values) {
            addCriterion("CARD_BAL in", values, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotIn(List<BigDecimal> values) {
            addCriterion("CARD_BAL not in", values, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_BAL between", value1, value2, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_BAL not between", value1, value2, "cardBal");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtIsNull() {
            addCriterion("WAIT_STL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtIsNotNull() {
            addCriterion("WAIT_STL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtEqualTo(BigDecimal value) {
            addCriterion("WAIT_STL_AMT =", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtNotEqualTo(BigDecimal value) {
            addCriterion("WAIT_STL_AMT <>", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtGreaterThan(BigDecimal value) {
            addCriterion("WAIT_STL_AMT >", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WAIT_STL_AMT >=", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtLessThan(BigDecimal value) {
            addCriterion("WAIT_STL_AMT <", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WAIT_STL_AMT <=", value, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtIn(List<BigDecimal> values) {
            addCriterion("WAIT_STL_AMT in", values, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtNotIn(List<BigDecimal> values) {
            addCriterion("WAIT_STL_AMT not in", values, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WAIT_STL_AMT between", value1, value2, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andWaitStlAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WAIT_STL_AMT not between", value1, value2, "waitStlAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtIsNull() {
            addCriterion("ERR_AMT is null");
            return (Criteria) this;
        }

        public Criteria andErrAmtIsNotNull() {
            addCriterion("ERR_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andErrAmtEqualTo(BigDecimal value) {
            addCriterion("ERR_AMT =", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtNotEqualTo(BigDecimal value) {
            addCriterion("ERR_AMT <>", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtGreaterThan(BigDecimal value) {
            addCriterion("ERR_AMT >", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ERR_AMT >=", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtLessThan(BigDecimal value) {
            addCriterion("ERR_AMT <", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ERR_AMT <=", value, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtIn(List<BigDecimal> values) {
            addCriterion("ERR_AMT in", values, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtNotIn(List<BigDecimal> values) {
            addCriterion("ERR_AMT not in", values, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ERR_AMT between", value1, value2, "errAmt");
            return (Criteria) this;
        }

        public Criteria andErrAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ERR_AMT not between", value1, value2, "errAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtIsNull() {
            addCriterion("CUST_TOTL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtIsNotNull() {
            addCriterion("CUST_TOTL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtEqualTo(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT =", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtNotEqualTo(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT <>", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtGreaterThan(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT >", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT >=", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtLessThan(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT <", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CUST_TOTL_AMT <=", value, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtIn(List<BigDecimal> values) {
            addCriterion("CUST_TOTL_AMT in", values, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtNotIn(List<BigDecimal> values) {
            addCriterion("CUST_TOTL_AMT not in", values, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CUST_TOTL_AMT between", value1, value2, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCustTotlAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CUST_TOTL_AMT not between", value1, value2, "custTotlAmt");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("COMMENTS is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("COMMENTS is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("COMMENTS =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("COMMENTS <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("COMMENTS >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("COMMENTS <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("COMMENTS <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("COMMENTS like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("COMMENTS not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("COMMENTS in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("COMMENTS not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("COMMENTS between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("COMMENTS not between", value1, value2, "comments");
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