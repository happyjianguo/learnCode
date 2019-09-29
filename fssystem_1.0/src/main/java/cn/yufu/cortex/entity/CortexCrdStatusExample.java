package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.List;

public class CortexCrdStatusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CortexCrdStatusExample() {
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

        public Criteria andVernoCtxIsNull() {
            addCriterion("VERNO_CTX is null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIsNotNull() {
            addCriterion("VERNO_CTX is not null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxEqualTo(Long value) {
            addCriterion("VERNO_CTX =", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotEqualTo(Long value) {
            addCriterion("VERNO_CTX <>", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThan(Long value) {
            addCriterion("VERNO_CTX >", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX >=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThan(Long value) {
            addCriterion("VERNO_CTX <", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX <=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIn(List<Long> values) {
            addCriterion("VERNO_CTX in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotIn(List<Long> values) {
            addCriterion("VERNO_CTX not in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX not between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andSysdefIsNull() {
            addCriterion("SYSDEF is null");
            return (Criteria) this;
        }

        public Criteria andSysdefIsNotNull() {
            addCriterion("SYSDEF is not null");
            return (Criteria) this;
        }

        public Criteria andSysdefEqualTo(String value) {
            addCriterion("SYSDEF =", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefNotEqualTo(String value) {
            addCriterion("SYSDEF <>", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefGreaterThan(String value) {
            addCriterion("SYSDEF >", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefGreaterThanOrEqualTo(String value) {
            addCriterion("SYSDEF >=", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefLessThan(String value) {
            addCriterion("SYSDEF <", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefLessThanOrEqualTo(String value) {
            addCriterion("SYSDEF <=", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefLike(String value) {
            addCriterion("SYSDEF like", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefNotLike(String value) {
            addCriterion("SYSDEF not like", value, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefIn(List<String> values) {
            addCriterion("SYSDEF in", values, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefNotIn(List<String> values) {
            addCriterion("SYSDEF not in", values, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefBetween(String value1, String value2) {
            addCriterion("SYSDEF between", value1, value2, "sysdef");
            return (Criteria) this;
        }

        public Criteria andSysdefNotBetween(String value1, String value2) {
            addCriterion("SYSDEF not between", value1, value2, "sysdef");
            return (Criteria) this;
        }

        public Criteria andDescrIsNull() {
            addCriterion("DESCR is null");
            return (Criteria) this;
        }

        public Criteria andDescrIsNotNull() {
            addCriterion("DESCR is not null");
            return (Criteria) this;
        }

        public Criteria andDescrEqualTo(String value) {
            addCriterion("DESCR =", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotEqualTo(String value) {
            addCriterion("DESCR <>", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThan(String value) {
            addCriterion("DESCR >", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThanOrEqualTo(String value) {
            addCriterion("DESCR >=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThan(String value) {
            addCriterion("DESCR <", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThanOrEqualTo(String value) {
            addCriterion("DESCR <=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLike(String value) {
            addCriterion("DESCR like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotLike(String value) {
            addCriterion("DESCR not like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrIn(List<String> values) {
            addCriterion("DESCR in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotIn(List<String> values) {
            addCriterion("DESCR not in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrBetween(String value1, String value2) {
            addCriterion("DESCR between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotBetween(String value1, String value2) {
            addCriterion("DESCR not between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andActioncodeIsNull() {
            addCriterion("ACTIONCODE is null");
            return (Criteria) this;
        }

        public Criteria andActioncodeIsNotNull() {
            addCriterion("ACTIONCODE is not null");
            return (Criteria) this;
        }

        public Criteria andActioncodeEqualTo(String value) {
            addCriterion("ACTIONCODE =", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeNotEqualTo(String value) {
            addCriterion("ACTIONCODE <>", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeGreaterThan(String value) {
            addCriterion("ACTIONCODE >", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIONCODE >=", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeLessThan(String value) {
            addCriterion("ACTIONCODE <", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeLessThanOrEqualTo(String value) {
            addCriterion("ACTIONCODE <=", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeLike(String value) {
            addCriterion("ACTIONCODE like", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeNotLike(String value) {
            addCriterion("ACTIONCODE not like", value, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeIn(List<String> values) {
            addCriterion("ACTIONCODE in", values, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeNotIn(List<String> values) {
            addCriterion("ACTIONCODE not in", values, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeBetween(String value1, String value2) {
            addCriterion("ACTIONCODE between", value1, value2, "actioncode");
            return (Criteria) this;
        }

        public Criteria andActioncodeNotBetween(String value1, String value2) {
            addCriterion("ACTIONCODE not between", value1, value2, "actioncode");
            return (Criteria) this;
        }

        public Criteria andRspcodeIsNull() {
            addCriterion("RSPCODE is null");
            return (Criteria) this;
        }

        public Criteria andRspcodeIsNotNull() {
            addCriterion("RSPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRspcodeEqualTo(String value) {
            addCriterion("RSPCODE =", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotEqualTo(String value) {
            addCriterion("RSPCODE <>", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeGreaterThan(String value) {
            addCriterion("RSPCODE >", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeGreaterThanOrEqualTo(String value) {
            addCriterion("RSPCODE >=", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLessThan(String value) {
            addCriterion("RSPCODE <", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLessThanOrEqualTo(String value) {
            addCriterion("RSPCODE <=", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLike(String value) {
            addCriterion("RSPCODE like", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotLike(String value) {
            addCriterion("RSPCODE not like", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeIn(List<String> values) {
            addCriterion("RSPCODE in", values, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotIn(List<String> values) {
            addCriterion("RSPCODE not in", values, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeBetween(String value1, String value2) {
            addCriterion("RSPCODE between", value1, value2, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotBetween(String value1, String value2) {
            addCriterion("RSPCODE not between", value1, value2, "rspcode");
            return (Criteria) this;
        }

        public Criteria andCanceledIsNull() {
            addCriterion("CANCELED is null");
            return (Criteria) this;
        }

        public Criteria andCanceledIsNotNull() {
            addCriterion("CANCELED is not null");
            return (Criteria) this;
        }

        public Criteria andCanceledEqualTo(String value) {
            addCriterion("CANCELED =", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledNotEqualTo(String value) {
            addCriterion("CANCELED <>", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledGreaterThan(String value) {
            addCriterion("CANCELED >", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledGreaterThanOrEqualTo(String value) {
            addCriterion("CANCELED >=", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledLessThan(String value) {
            addCriterion("CANCELED <", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledLessThanOrEqualTo(String value) {
            addCriterion("CANCELED <=", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledLike(String value) {
            addCriterion("CANCELED like", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledNotLike(String value) {
            addCriterion("CANCELED not like", value, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledIn(List<String> values) {
            addCriterion("CANCELED in", values, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledNotIn(List<String> values) {
            addCriterion("CANCELED not in", values, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledBetween(String value1, String value2) {
            addCriterion("CANCELED between", value1, value2, "canceled");
            return (Criteria) this;
        }

        public Criteria andCanceledNotBetween(String value1, String value2) {
            addCriterion("CANCELED not between", value1, value2, "canceled");
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