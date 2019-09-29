package cn.yufu.fs.entity;

import java.util.ArrayList;
import java.util.List;

public class MerstlErrorSetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerstlErrorSetExample() {
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
        
        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumIsNull() {
            addCriterion("ERROR_CARD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumIsNotNull() {
            addCriterion("ERROR_CARD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumEqualTo(String value) {
            addCriterion("ERROR_CARD_NUM =", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumNotEqualTo(String value) {
            addCriterion("ERROR_CARD_NUM <>", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumGreaterThan(String value) {
            addCriterion("ERROR_CARD_NUM >", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_CARD_NUM >=", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumLessThan(String value) {
            addCriterion("ERROR_CARD_NUM <", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumLessThanOrEqualTo(String value) {
            addCriterion("ERROR_CARD_NUM <=", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumLike(String value) {
            addCriterion("ERROR_CARD_NUM like", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumNotLike(String value) {
            addCriterion("ERROR_CARD_NUM not like", value, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumIn(List<String> values) {
            addCriterion("ERROR_CARD_NUM in", values, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumNotIn(List<String> values) {
            addCriterion("ERROR_CARD_NUM not in", values, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumBetween(String value1, String value2) {
            addCriterion("ERROR_CARD_NUM between", value1, value2, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardNumNotBetween(String value1, String value2) {
            addCriterion("ERROR_CARD_NUM not between", value1, value2, "errorCardNum");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtIsNull() {
            addCriterion("ERROR_CARD_AMT is null");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtIsNotNull() {
            addCriterion("ERROR_CARD_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtEqualTo(String value) {
            addCriterion("ERROR_CARD_AMT =", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtNotEqualTo(String value) {
            addCriterion("ERROR_CARD_AMT <>", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtGreaterThan(String value) {
            addCriterion("ERROR_CARD_AMT >", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_CARD_AMT >=", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtLessThan(String value) {
            addCriterion("ERROR_CARD_AMT <", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtLessThanOrEqualTo(String value) {
            addCriterion("ERROR_CARD_AMT <=", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtLike(String value) {
            addCriterion("ERROR_CARD_AMT like", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtNotLike(String value) {
            addCriterion("ERROR_CARD_AMT not like", value, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtIn(List<String> values) {
            addCriterion("ERROR_CARD_AMT in", values, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtNotIn(List<String> values) {
            addCriterion("ERROR_CARD_AMT not in", values, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtBetween(String value1, String value2) {
            addCriterion("ERROR_CARD_AMT between", value1, value2, "errorCardAmt");
            return (Criteria) this;
        }

        public Criteria andErrorCardAmtNotBetween(String value1, String value2) {
            addCriterion("ERROR_CARD_AMT not between", value1, value2, "errorCardAmt");
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