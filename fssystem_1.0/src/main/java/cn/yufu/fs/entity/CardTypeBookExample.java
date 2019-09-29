package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardTypeBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardTypeBookExample() {
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

        public Criteria andCardTypeIdIsNull() {
            addCriterion("CARD_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdIsNotNull() {
            addCriterion("CARD_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdEqualTo(String value) {
            addCriterion("CARD_TYPE_ID =", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdNotEqualTo(String value) {
            addCriterion("CARD_TYPE_ID <>", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdGreaterThan(String value) {
            addCriterion("CARD_TYPE_ID >", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE_ID >=", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdLessThan(String value) {
            addCriterion("CARD_TYPE_ID <", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE_ID <=", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdLike(String value) {
            addCriterion("CARD_TYPE_ID like", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdNotLike(String value) {
            addCriterion("CARD_TYPE_ID not like", value, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdIn(List<String> values) {
            addCriterion("CARD_TYPE_ID in", values, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdNotIn(List<String> values) {
            addCriterion("CARD_TYPE_ID not in", values, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdBetween(String value1, String value2) {
            addCriterion("CARD_TYPE_ID between", value1, value2, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andCardTypeIdNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE_ID not between", value1, value2, "cardTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIsNull() {
            addCriterion("FEE_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIsNotNull() {
            addCriterion("FEE_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andFeeOrderEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER =", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER <>", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderGreaterThan(BigDecimal value) {
            addCriterion("FEE_ORDER >", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER >=", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderLessThan(BigDecimal value) {
            addCriterion("FEE_ORDER <", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER <=", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIn(List<BigDecimal> values) {
            addCriterion("FEE_ORDER in", values, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotIn(List<BigDecimal> values) {
            addCriterion("FEE_ORDER not in", values, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_ORDER between", value1, value2, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_ORDER not between", value1, value2, "feeOrder");
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

        public Criteria andComments1IsNull() {
            addCriterion("COMMENTS1 is null");
            return (Criteria) this;
        }

        public Criteria andComments1IsNotNull() {
            addCriterion("COMMENTS1 is not null");
            return (Criteria) this;
        }

        public Criteria andComments1EqualTo(String value) {
            addCriterion("COMMENTS1 =", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotEqualTo(String value) {
            addCriterion("COMMENTS1 <>", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThan(String value) {
            addCriterion("COMMENTS1 >", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 >=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThan(String value) {
            addCriterion("COMMENTS1 <", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 <=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Like(String value) {
            addCriterion("COMMENTS1 like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotLike(String value) {
            addCriterion("COMMENTS1 not like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1In(List<String> values) {
            addCriterion("COMMENTS1 in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotIn(List<String> values) {
            addCriterion("COMMENTS1 not in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Between(String value1, String value2) {
            addCriterion("COMMENTS1 between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotBetween(String value1, String value2) {
            addCriterion("COMMENTS1 not between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments2IsNull() {
            addCriterion("COMMENTS2 is null");
            return (Criteria) this;
        }

        public Criteria andComments2IsNotNull() {
            addCriterion("COMMENTS2 is not null");
            return (Criteria) this;
        }

        public Criteria andComments2EqualTo(String value) {
            addCriterion("COMMENTS2 =", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotEqualTo(String value) {
            addCriterion("COMMENTS2 <>", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThan(String value) {
            addCriterion("COMMENTS2 >", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 >=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThan(String value) {
            addCriterion("COMMENTS2 <", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 <=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Like(String value) {
            addCriterion("COMMENTS2 like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotLike(String value) {
            addCriterion("COMMENTS2 not like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2In(List<String> values) {
            addCriterion("COMMENTS2 in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotIn(List<String> values) {
            addCriterion("COMMENTS2 not in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Between(String value1, String value2) {
            addCriterion("COMMENTS2 between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotBetween(String value1, String value2) {
            addCriterion("COMMENTS2 not between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments3IsNull() {
            addCriterion("COMMENTS3 is null");
            return (Criteria) this;
        }

        public Criteria andComments3IsNotNull() {
            addCriterion("COMMENTS3 is not null");
            return (Criteria) this;
        }

        public Criteria andComments3EqualTo(String value) {
            addCriterion("COMMENTS3 =", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotEqualTo(String value) {
            addCriterion("COMMENTS3 <>", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3GreaterThan(String value) {
            addCriterion("COMMENTS3 >", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS3 >=", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3LessThan(String value) {
            addCriterion("COMMENTS3 <", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS3 <=", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3Like(String value) {
            addCriterion("COMMENTS3 like", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotLike(String value) {
            addCriterion("COMMENTS3 not like", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3In(List<String> values) {
            addCriterion("COMMENTS3 in", values, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotIn(List<String> values) {
            addCriterion("COMMENTS3 not in", values, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3Between(String value1, String value2) {
            addCriterion("COMMENTS3 between", value1, value2, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotBetween(String value1, String value2) {
            addCriterion("COMMENTS3 not between", value1, value2, "comments3");
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