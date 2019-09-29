package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FkStlDayEndStepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FkStlDayEndStepExample() {
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

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDailydateIsNull() {
            addCriterion("DAILYDATE is null");
            return (Criteria) this;
        }

        public Criteria andDailydateIsNotNull() {
            addCriterion("DAILYDATE is not null");
            return (Criteria) this;
        }

        public Criteria andDailydateEqualTo(String value) {
            addCriterion("DAILYDATE =", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateNotEqualTo(String value) {
            addCriterion("DAILYDATE <>", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateGreaterThan(String value) {
            addCriterion("DAILYDATE >", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateGreaterThanOrEqualTo(String value) {
            addCriterion("DAILYDATE >=", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateLessThan(String value) {
            addCriterion("DAILYDATE <", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateLessThanOrEqualTo(String value) {
            addCriterion("DAILYDATE <=", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateLike(String value) {
            addCriterion("DAILYDATE like", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateNotLike(String value) {
            addCriterion("DAILYDATE not like", value, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateIn(List<String> values) {
            addCriterion("DAILYDATE in", values, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateNotIn(List<String> values) {
            addCriterion("DAILYDATE not in", values, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateBetween(String value1, String value2) {
            addCriterion("DAILYDATE between", value1, value2, "dailydate");
            return (Criteria) this;
        }

        public Criteria andDailydateNotBetween(String value1, String value2) {
            addCriterion("DAILYDATE not between", value1, value2, "dailydate");
            return (Criteria) this;
        }

        public Criteria andStepnameIsNull() {
            addCriterion("STEPNAME is null");
            return (Criteria) this;
        }

        public Criteria andStepnameIsNotNull() {
            addCriterion("STEPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andStepnameEqualTo(String value) {
            addCriterion("STEPNAME =", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameNotEqualTo(String value) {
            addCriterion("STEPNAME <>", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameGreaterThan(String value) {
            addCriterion("STEPNAME >", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameGreaterThanOrEqualTo(String value) {
            addCriterion("STEPNAME >=", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameLessThan(String value) {
            addCriterion("STEPNAME <", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameLessThanOrEqualTo(String value) {
            addCriterion("STEPNAME <=", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameLike(String value) {
            addCriterion("STEPNAME like", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameNotLike(String value) {
            addCriterion("STEPNAME not like", value, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameIn(List<String> values) {
            addCriterion("STEPNAME in", values, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameNotIn(List<String> values) {
            addCriterion("STEPNAME not in", values, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameBetween(String value1, String value2) {
            addCriterion("STEPNAME between", value1, value2, "stepname");
            return (Criteria) this;
        }

        public Criteria andStepnameNotBetween(String value1, String value2) {
            addCriterion("STEPNAME not between", value1, value2, "stepname");
            return (Criteria) this;
        }

        public Criteria andFuncnameIsNull() {
            addCriterion("FUNCNAME is null");
            return (Criteria) this;
        }

        public Criteria andFuncnameIsNotNull() {
            addCriterion("FUNCNAME is not null");
            return (Criteria) this;
        }

        public Criteria andFuncnameEqualTo(String value) {
            addCriterion("FUNCNAME =", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotEqualTo(String value) {
            addCriterion("FUNCNAME <>", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameGreaterThan(String value) {
            addCriterion("FUNCNAME >", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCNAME >=", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLessThan(String value) {
            addCriterion("FUNCNAME <", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLessThanOrEqualTo(String value) {
            addCriterion("FUNCNAME <=", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLike(String value) {
            addCriterion("FUNCNAME like", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotLike(String value) {
            addCriterion("FUNCNAME not like", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameIn(List<String> values) {
            addCriterion("FUNCNAME in", values, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotIn(List<String> values) {
            addCriterion("FUNCNAME not in", values, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameBetween(String value1, String value2) {
            addCriterion("FUNCNAME between", value1, value2, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotBetween(String value1, String value2) {
            addCriterion("FUNCNAME not between", value1, value2, "funcname");
            return (Criteria) this;
        }

        public Criteria andLibnameIsNull() {
            addCriterion("LIBNAME is null");
            return (Criteria) this;
        }

        public Criteria andLibnameIsNotNull() {
            addCriterion("LIBNAME is not null");
            return (Criteria) this;
        }

        public Criteria andLibnameEqualTo(String value) {
            addCriterion("LIBNAME =", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameNotEqualTo(String value) {
            addCriterion("LIBNAME <>", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameGreaterThan(String value) {
            addCriterion("LIBNAME >", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameGreaterThanOrEqualTo(String value) {
            addCriterion("LIBNAME >=", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameLessThan(String value) {
            addCriterion("LIBNAME <", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameLessThanOrEqualTo(String value) {
            addCriterion("LIBNAME <=", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameLike(String value) {
            addCriterion("LIBNAME like", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameNotLike(String value) {
            addCriterion("LIBNAME not like", value, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameIn(List<String> values) {
            addCriterion("LIBNAME in", values, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameNotIn(List<String> values) {
            addCriterion("LIBNAME not in", values, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameBetween(String value1, String value2) {
            addCriterion("LIBNAME between", value1, value2, "libname");
            return (Criteria) this;
        }

        public Criteria andLibnameNotBetween(String value1, String value2) {
            addCriterion("LIBNAME not between", value1, value2, "libname");
            return (Criteria) this;
        }

        public Criteria andExecStatusIsNull() {
            addCriterion("EXEC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andExecStatusIsNotNull() {
            addCriterion("EXEC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andExecStatusEqualTo(BigDecimal value) {
            addCriterion("EXEC_STATUS =", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusNotEqualTo(BigDecimal value) {
            addCriterion("EXEC_STATUS <>", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusGreaterThan(BigDecimal value) {
            addCriterion("EXEC_STATUS >", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EXEC_STATUS >=", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusLessThan(BigDecimal value) {
            addCriterion("EXEC_STATUS <", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EXEC_STATUS <=", value, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusIn(List<BigDecimal> values) {
            addCriterion("EXEC_STATUS in", values, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusNotIn(List<BigDecimal> values) {
            addCriterion("EXEC_STATUS not in", values, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXEC_STATUS between", value1, value2, "execStatus");
            return (Criteria) this;
        }

        public Criteria andExecStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXEC_STATUS not between", value1, value2, "execStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(BigDecimal value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(BigDecimal value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(BigDecimal value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(BigDecimal value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<BigDecimal> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<BigDecimal> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("BEGINTIME is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("BEGINTIME is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("BEGINTIME =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("BEGINTIME <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("BEGINTIME >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BEGINTIME >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("BEGINTIME <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("BEGINTIME <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("BEGINTIME in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("BEGINTIME not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("BEGINTIME between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("BEGINTIME not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("ENDTIME is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("ENDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("ENDTIME =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("ENDTIME <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("ENDTIME >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ENDTIME >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("ENDTIME <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("ENDTIME <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("ENDTIME in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("ENDTIME not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("ENDTIME between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("ENDTIME not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("PID is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PID is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(BigDecimal value) {
            addCriterion("PID =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(BigDecimal value) {
            addCriterion("PID <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(BigDecimal value) {
            addCriterion("PID >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PID >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(BigDecimal value) {
            addCriterion("PID <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PID <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<BigDecimal> values) {
            addCriterion("PID in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<BigDecimal> values) {
            addCriterion("PID not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PID between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PID not between", value1, value2, "pid");
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