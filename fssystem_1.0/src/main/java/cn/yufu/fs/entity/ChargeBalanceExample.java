package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChargeBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChargeBalanceExample() {
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
            addCriterion("A.ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("A.ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("A.ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("A.ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("A.ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("A.ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("A.ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("A.ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("A.ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("A.ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("A.ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("A.ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("A.ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("A.ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNull() {
            addCriterion("A.MER_NO is null");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNotNull() {
            addCriterion("A.MER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMerNoEqualTo(String value) {
            addCriterion("A.MER_NO =", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotEqualTo(String value) {
            addCriterion("A.MER_NO <>", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThan(String value) {
            addCriterion("A.MER_NO >", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("A.MER_NO >=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThan(String value) {
            addCriterion("A.MER_NO <", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThanOrEqualTo(String value) {
            addCriterion("A.MER_NO <=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLike(String value) {
            addCriterion("A.MER_NO like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotLike(String value) {
            addCriterion("A.MER_NO not like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIn(List<String> values) {
            addCriterion("A.MER_NO in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotIn(List<String> values) {
            addCriterion("A.MER_NO not in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoBetween(String value1, String value2) {
            addCriterion("A.MER_NO between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotBetween(String value1, String value2) {
            addCriterion("A.MER_NO not between", value1, value2, "merNo");
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

        public Criteria andTerminalIdIsNull() {
            addCriterion("A.TERMINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIsNotNull() {
            addCriterion("A.TERMINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdEqualTo(String value) {
            addCriterion("A.TERMINAL_ID =", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotEqualTo(String value) {
            addCriterion("A.TERMINAL_ID <>", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThan(String value) {
            addCriterion("A.TERMINAL_ID >", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThanOrEqualTo(String value) {
            addCriterion("A.TERMINAL_ID >=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThan(String value) {
            addCriterion("A.TERMINAL_ID <", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThanOrEqualTo(String value) {
            addCriterion("A.TERMINAL_ID <=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLike(String value) {
            addCriterion("A.TERMINAL_ID like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotLike(String value) {
            addCriterion("A.TERMINAL_ID not like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIn(List<String> values) {
            addCriterion("A.TERMINAL_ID in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotIn(List<String> values) {
            addCriterion("A.TERMINAL_ID not in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdBetween(String value1, String value2) {
            addCriterion("A.TERMINAL_ID between", value1, value2, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotBetween(String value1, String value2) {
            addCriterion("A.TERMINAL_ID not between", value1, value2, "terminalId");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("A.CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("A.CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("A.CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("A.CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("A.CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("A.CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("A.CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("A.CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("A.CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("A.CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("A.CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("A.CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("A.CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("A.CARD_NO not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoIsNull() {
            addCriterion("VOID_TRACE_NO is null");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoIsNotNull() {
            addCriterion("VOID_TRACE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoEqualTo(BigDecimal value) {
            addCriterion("VOID_TRACE_NO =", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoNotEqualTo(BigDecimal value) {
            addCriterion("VOID_TRACE_NO <>", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoGreaterThan(BigDecimal value) {
            addCriterion("VOID_TRACE_NO >", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VOID_TRACE_NO >=", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoLessThan(BigDecimal value) {
            addCriterion("VOID_TRACE_NO <", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VOID_TRACE_NO <=", value, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoIn(List<BigDecimal> values) {
            addCriterion("VOID_TRACE_NO in", values, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoNotIn(List<BigDecimal> values) {
            addCriterion("VOID_TRACE_NO not in", values, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOID_TRACE_NO between", value1, value2, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidTraceNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOID_TRACE_NO not between", value1, value2, "voidTraceNo");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateIsNull() {
            addCriterion("VOID_SYS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateIsNotNull() {
            addCriterion("VOID_SYS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateEqualTo(String value) {
            addCriterion("VOID_SYS_DATE =", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateNotEqualTo(String value) {
            addCriterion("VOID_SYS_DATE <>", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateGreaterThan(String value) {
            addCriterion("VOID_SYS_DATE >", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateGreaterThanOrEqualTo(String value) {
            addCriterion("VOID_SYS_DATE >=", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateLessThan(String value) {
            addCriterion("VOID_SYS_DATE <", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateLessThanOrEqualTo(String value) {
            addCriterion("VOID_SYS_DATE <=", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateLike(String value) {
            addCriterion("VOID_SYS_DATE like", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateNotLike(String value) {
            addCriterion("VOID_SYS_DATE not like", value, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateIn(List<String> values) {
            addCriterion("VOID_SYS_DATE in", values, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateNotIn(List<String> values) {
            addCriterion("VOID_SYS_DATE not in", values, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateBetween(String value1, String value2) {
            addCriterion("VOID_SYS_DATE between", value1, value2, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysDateNotBetween(String value1, String value2) {
            addCriterion("VOID_SYS_DATE not between", value1, value2, "voidSysDate");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeIsNull() {
            addCriterion("VOID_SYS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeIsNotNull() {
            addCriterion("VOID_SYS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeEqualTo(String value) {
            addCriterion("VOID_SYS_TIME =", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeNotEqualTo(String value) {
            addCriterion("VOID_SYS_TIME <>", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeGreaterThan(String value) {
            addCriterion("VOID_SYS_TIME >", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeGreaterThanOrEqualTo(String value) {
            addCriterion("VOID_SYS_TIME >=", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeLessThan(String value) {
            addCriterion("VOID_SYS_TIME <", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeLessThanOrEqualTo(String value) {
            addCriterion("VOID_SYS_TIME <=", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeLike(String value) {
            addCriterion("VOID_SYS_TIME like", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeNotLike(String value) {
            addCriterion("VOID_SYS_TIME not like", value, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeIn(List<String> values) {
            addCriterion("VOID_SYS_TIME in", values, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeNotIn(List<String> values) {
            addCriterion("VOID_SYS_TIME not in", values, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeBetween(String value1, String value2) {
            addCriterion("VOID_SYS_TIME between", value1, value2, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidSysTimeNotBetween(String value1, String value2) {
            addCriterion("VOID_SYS_TIME not between", value1, value2, "voidSysTime");
            return (Criteria) this;
        }

        public Criteria andVoidAmtIsNull() {
            addCriterion("VOID_AMT is null");
            return (Criteria) this;
        }

        public Criteria andVoidAmtIsNotNull() {
            addCriterion("VOID_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andVoidAmtEqualTo(BigDecimal value) {
            addCriterion("VOID_AMT =", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtNotEqualTo(BigDecimal value) {
            addCriterion("VOID_AMT <>", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtGreaterThan(BigDecimal value) {
            addCriterion("VOID_AMT >", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VOID_AMT >=", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtLessThan(BigDecimal value) {
            addCriterion("VOID_AMT <", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VOID_AMT <=", value, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtIn(List<BigDecimal> values) {
            addCriterion("VOID_AMT in", values, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtNotIn(List<BigDecimal> values) {
            addCriterion("VOID_AMT not in", values, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOID_AMT between", value1, value2, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOID_AMT not between", value1, value2, "voidAmt");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeIsNull() {
            addCriterion("VOID_TRACE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeIsNotNull() {
            addCriterion("VOID_TRACE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeEqualTo(String value) {
            addCriterion("VOID_TRACE_CODE =", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeNotEqualTo(String value) {
            addCriterion("VOID_TRACE_CODE <>", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeGreaterThan(String value) {
            addCriterion("VOID_TRACE_CODE >", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VOID_TRACE_CODE >=", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeLessThan(String value) {
            addCriterion("VOID_TRACE_CODE <", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeLessThanOrEqualTo(String value) {
            addCriterion("VOID_TRACE_CODE <=", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeLike(String value) {
            addCriterion("VOID_TRACE_CODE like", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeNotLike(String value) {
            addCriterion("VOID_TRACE_CODE not like", value, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeIn(List<String> values) {
            addCriterion("VOID_TRACE_CODE in", values, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeNotIn(List<String> values) {
            addCriterion("VOID_TRACE_CODE not in", values, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeBetween(String value1, String value2) {
            addCriterion("VOID_TRACE_CODE between", value1, value2, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidTraceCodeNotBetween(String value1, String value2) {
            addCriterion("VOID_TRACE_CODE not between", value1, value2, "voidTraceCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeIsNull() {
            addCriterion("VOID_RESP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeIsNotNull() {
            addCriterion("VOID_RESP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeEqualTo(String value) {
            addCriterion("VOID_RESP_CODE =", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeNotEqualTo(String value) {
            addCriterion("VOID_RESP_CODE <>", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeGreaterThan(String value) {
            addCriterion("VOID_RESP_CODE >", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VOID_RESP_CODE >=", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeLessThan(String value) {
            addCriterion("VOID_RESP_CODE <", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeLessThanOrEqualTo(String value) {
            addCriterion("VOID_RESP_CODE <=", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeLike(String value) {
            addCriterion("VOID_RESP_CODE like", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeNotLike(String value) {
            addCriterion("VOID_RESP_CODE not like", value, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeIn(List<String> values) {
            addCriterion("VOID_RESP_CODE in", values, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeNotIn(List<String> values) {
            addCriterion("VOID_RESP_CODE not in", values, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeBetween(String value1, String value2) {
            addCriterion("VOID_RESP_CODE between", value1, value2, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andVoidRespCodeNotBetween(String value1, String value2) {
            addCriterion("VOID_RESP_CODE not between", value1, value2, "voidRespCode");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIsNull() {
            addCriterion("CHARGE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIsNotNull() {
            addCriterion("CHARGE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAmtEqualTo(BigDecimal value) {
            addCriterion("CHARGE_AMT =", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotEqualTo(BigDecimal value) {
            addCriterion("CHARGE_AMT <>", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtGreaterThan(BigDecimal value) {
            addCriterion("CHARGE_AMT >", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHARGE_AMT >=", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtLessThan(BigDecimal value) {
            addCriterion("CHARGE_AMT <", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHARGE_AMT <=", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIn(List<BigDecimal> values) {
            addCriterion("CHARGE_AMT in", values, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotIn(List<BigDecimal> values) {
            addCriterion("CHARGE_AMT not in", values, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHARGE_AMT between", value1, value2, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHARGE_AMT not between", value1, value2, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeFlagIsNull() {
            addCriterion("A.CHARGE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andChargeFlagIsNotNull() {
            addCriterion("A.CHARGE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andChargeFlagEqualTo(String value) {
            addCriterion("A.CHARGE_FLAG =", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagNotEqualTo(String value) {
            addCriterion("A.CHARGE_FLAG <>", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagGreaterThan(String value) {
            addCriterion("A.CHARGE_FLAG >", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("A.CHARGE_FLAG >=", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagLessThan(String value) {
            addCriterion("A.CHARGE_FLAG <", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagLessThanOrEqualTo(String value) {
            addCriterion("A.CHARGE_FLAG <=", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagLike(String value) {
            addCriterion("A.CHARGE_FLAG like", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagNotLike(String value) {
            addCriterion("A.CHARGE_FLAG not like", value, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagIn(List<String> values) {
            addCriterion("A.CHARGE_FLAG in", values, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagNotIn(List<String> values) {
            addCriterion("A.CHARGE_FLAG not in", values, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagBetween(String value1, String value2) {
            addCriterion("A.CHARGE_FLAG between", value1, value2, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeFlagNotBetween(String value1, String value2) {
            addCriterion("A.CHARGE_FLAG not between", value1, value2, "chargeFlag");
            return (Criteria) this;
        }

        public Criteria andTraceNoIsNull() {
            addCriterion("TRACE_NO is null");
            return (Criteria) this;
        }

        public Criteria andTraceNoIsNotNull() {
            addCriterion("TRACE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTraceNoEqualTo(BigDecimal value) {
            addCriterion("TRACE_NO =", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoNotEqualTo(BigDecimal value) {
            addCriterion("TRACE_NO <>", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoGreaterThan(BigDecimal value) {
            addCriterion("TRACE_NO >", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRACE_NO >=", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoLessThan(BigDecimal value) {
            addCriterion("TRACE_NO <", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRACE_NO <=", value, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoIn(List<BigDecimal> values) {
            addCriterion("TRACE_NO in", values, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoNotIn(List<BigDecimal> values) {
            addCriterion("TRACE_NO not in", values, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRACE_NO between", value1, value2, "traceNo");
            return (Criteria) this;
        }

        public Criteria andTraceNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRACE_NO not between", value1, value2, "traceNo");
            return (Criteria) this;
        }

        public Criteria andRespCodeIsNull() {
            addCriterion("A.RESP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRespCodeIsNotNull() {
            addCriterion("A.RESP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRespCodeEqualTo(String value) {
            addCriterion("A.RESP_CODE =", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotEqualTo(String value) {
            addCriterion("A.RESP_CODE <>", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeGreaterThan(String value) {
            addCriterion("A.RESP_CODE >", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeGreaterThanOrEqualTo(String value) {
            addCriterion("A.RESP_CODE >=", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLessThan(String value) {
            addCriterion("A.RESP_CODE <", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLessThanOrEqualTo(String value) {
            addCriterion("A.RESP_CODE <=", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLike(String value) {
            addCriterion("A.RESP_CODE like", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotLike(String value) {
            addCriterion("A.RESP_CODE not like", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeIn(List<String> values) {
            addCriterion("A.RESP_CODE in", values, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotIn(List<String> values) {
            addCriterion("A.RESP_CODE not in", values, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeBetween(String value1, String value2) {
            addCriterion("A.RESP_CODE between", value1, value2, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotBetween(String value1, String value2) {
            addCriterion("A.RESP_CODE not between", value1, value2, "respCode");
            return (Criteria) this;
        }

        public Criteria andPicRouteIsNull() {
            addCriterion("PIC_ROUTE is null");
            return (Criteria) this;
        }

        public Criteria andPicRouteIsNotNull() {
            addCriterion("PIC_ROUTE is not null");
            return (Criteria) this;
        }

        public Criteria andPicRouteEqualTo(String value) {
            addCriterion("PIC_ROUTE =", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteNotEqualTo(String value) {
            addCriterion("PIC_ROUTE <>", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteGreaterThan(String value) {
            addCriterion("PIC_ROUTE >", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteGreaterThanOrEqualTo(String value) {
            addCriterion("PIC_ROUTE >=", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteLessThan(String value) {
            addCriterion("PIC_ROUTE <", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteLessThanOrEqualTo(String value) {
            addCriterion("PIC_ROUTE <=", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteLike(String value) {
            addCriterion("PIC_ROUTE like", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteNotLike(String value) {
            addCriterion("PIC_ROUTE not like", value, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteIn(List<String> values) {
            addCriterion("PIC_ROUTE in", values, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteNotIn(List<String> values) {
            addCriterion("PIC_ROUTE not in", values, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteBetween(String value1, String value2) {
            addCriterion("PIC_ROUTE between", value1, value2, "picRoute");
            return (Criteria) this;
        }

        public Criteria andPicRouteNotBetween(String value1, String value2) {
            addCriterion("PIC_ROUTE not between", value1, value2, "picRoute");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(Object value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(Object value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(Object value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(Object value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(Object value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(Object value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<Object> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<Object> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(Object value1, Object value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(Object value1, Object value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("A.DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("DEL_FLAG like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("DEL_FLAG not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
        
        public Criteria andStartChargeDateEqualTo(String value) {
            addCriterion("SUBSTR(A.CREATE_DATE, 0, 8) =", value, "startChargeDate");
            return (Criteria) this;
        }
        
        public Criteria andStartChargeDateGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSTR(A.CREATE_DATE, 0, 8) >=", value, "startChargeDate");
            return (Criteria) this;
        }
        
        public Criteria andEndChargeDateEqualTo(String value) {
            addCriterion("SUBSTR(A.CREATE_DATE, 0, 8) =", value, "endChargeDate");
            return (Criteria) this;
        }
        
        public Criteria andEndChargeDateLessThanOrEqualTo(String value) {
            addCriterion("SUBSTR(A.CREATE_DATE, 0, 8) <=", value, "endChargeDate");
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