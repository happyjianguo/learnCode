package cn.yufu.cortexBak.entity;

import java.util.ArrayList;
import java.util.List;

public class MrchClassBakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MrchClassBakExample() {
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

        public Criteria andInstIdIsNull() {
            addCriterion("INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNotNull() {
            addCriterion("INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdEqualTo(Long value) {
            addCriterion("INST_ID =", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotEqualTo(Long value) {
            addCriterion("INST_ID <>", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThan(Long value) {
            addCriterion("INST_ID >", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INST_ID >=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThan(Long value) {
            addCriterion("INST_ID <", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThanOrEqualTo(Long value) {
            addCriterion("INST_ID <=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdIn(List<Long> values) {
            addCriterion("INST_ID in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotIn(List<Long> values) {
            addCriterion("INST_ID not in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdBetween(Long value1, Long value2) {
            addCriterion("INST_ID between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotBetween(Long value1, Long value2) {
            addCriterion("INST_ID not between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andMrchnoIsNull() {
            addCriterion("MRCHNO is null");
            return (Criteria) this;
        }

        public Criteria andMrchnoIsNotNull() {
            addCriterion("MRCHNO is not null");
            return (Criteria) this;
        }

        public Criteria andMrchnoEqualTo(String value) {
            addCriterion("MRCHNO =", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotEqualTo(String value) {
            addCriterion("MRCHNO <>", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoGreaterThan(String value) {
            addCriterion("MRCHNO >", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoGreaterThanOrEqualTo(String value) {
            addCriterion("MRCHNO >=", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLessThan(String value) {
            addCriterion("MRCHNO <", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLessThanOrEqualTo(String value) {
            addCriterion("MRCHNO <=", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLike(String value) {
            addCriterion("MRCHNO like", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotLike(String value) {
            addCriterion("MRCHNO not like", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoIn(List<String> values) {
            addCriterion("MRCHNO in", values, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotIn(List<String> values) {
            addCriterion("MRCHNO not in", values, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoBetween(String value1, String value2) {
            addCriterion("MRCHNO between", value1, value2, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotBetween(String value1, String value2) {
            addCriterion("MRCHNO not between", value1, value2, "mrchno");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNull() {
            addCriterion("CLASSIFY is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("CLASSIFY is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(String value) {
            addCriterion("CLASSIFY =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(String value) {
            addCriterion("CLASSIFY <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(String value) {
            addCriterion("CLASSIFY >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("CLASSIFY >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(String value) {
            addCriterion("CLASSIFY <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(String value) {
            addCriterion("CLASSIFY <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLike(String value) {
            addCriterion("CLASSIFY like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotLike(String value) {
            addCriterion("CLASSIFY not like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<String> values) {
            addCriterion("CLASSIFY in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<String> values) {
            addCriterion("CLASSIFY not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(String value1, String value2) {
            addCriterion("CLASSIFY between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(String value1, String value2) {
            addCriterion("CLASSIFY not between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andFmrchnoIsNull() {
            addCriterion("FMRCHNO is null");
            return (Criteria) this;
        }

        public Criteria andFmrchnoIsNotNull() {
            addCriterion("FMRCHNO is not null");
            return (Criteria) this;
        }

        public Criteria andFmrchnoEqualTo(String value) {
            addCriterion("FMRCHNO =", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoNotEqualTo(String value) {
            addCriterion("FMRCHNO <>", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoGreaterThan(String value) {
            addCriterion("FMRCHNO >", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoGreaterThanOrEqualTo(String value) {
            addCriterion("FMRCHNO >=", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoLessThan(String value) {
            addCriterion("FMRCHNO <", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoLessThanOrEqualTo(String value) {
            addCriterion("FMRCHNO <=", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoLike(String value) {
            addCriterion("FMRCHNO like", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoNotLike(String value) {
            addCriterion("FMRCHNO not like", value, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoIn(List<String> values) {
            addCriterion("FMRCHNO in", values, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoNotIn(List<String> values) {
            addCriterion("FMRCHNO not in", values, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoBetween(String value1, String value2) {
            addCriterion("FMRCHNO between", value1, value2, "fmrchno");
            return (Criteria) this;
        }

        public Criteria andFmrchnoNotBetween(String value1, String value2) {
            addCriterion("FMRCHNO not between", value1, value2, "fmrchno");
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