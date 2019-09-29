package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EnckeyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnckeyExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andKeytypeIsNull() {
            addCriterion("KEYTYPE is null");
            return (Criteria) this;
        }

        public Criteria andKeytypeIsNotNull() {
            addCriterion("KEYTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andKeytypeEqualTo(Integer value) {
            addCriterion("KEYTYPE =", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotEqualTo(Integer value) {
            addCriterion("KEYTYPE <>", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeGreaterThan(Integer value) {
            addCriterion("KEYTYPE >", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("KEYTYPE >=", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeLessThan(Integer value) {
            addCriterion("KEYTYPE <", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeLessThanOrEqualTo(Integer value) {
            addCriterion("KEYTYPE <=", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeIn(List<Integer> values) {
            addCriterion("KEYTYPE in", values, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotIn(List<Integer> values) {
            addCriterion("KEYTYPE not in", values, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeBetween(Integer value1, Integer value2) {
            addCriterion("KEYTYPE between", value1, value2, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("KEYTYPE not between", value1, value2, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeystatusIsNull() {
            addCriterion("KEYSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andKeystatusIsNotNull() {
            addCriterion("KEYSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andKeystatusEqualTo(Integer value) {
            addCriterion("KEYSTATUS =", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusNotEqualTo(Integer value) {
            addCriterion("KEYSTATUS <>", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusGreaterThan(Integer value) {
            addCriterion("KEYSTATUS >", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("KEYSTATUS >=", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusLessThan(Integer value) {
            addCriterion("KEYSTATUS <", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusLessThanOrEqualTo(Integer value) {
            addCriterion("KEYSTATUS <=", value, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusIn(List<Integer> values) {
            addCriterion("KEYSTATUS in", values, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusNotIn(List<Integer> values) {
            addCriterion("KEYSTATUS not in", values, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusBetween(Integer value1, Integer value2) {
            addCriterion("KEYSTATUS between", value1, value2, "keystatus");
            return (Criteria) this;
        }

        public Criteria andKeystatusNotBetween(Integer value1, Integer value2) {
            addCriterion("KEYSTATUS not between", value1, value2, "keystatus");
            return (Criteria) this;
        }

        public Criteria andTstflagIsNull() {
            addCriterion("TSTFLAG is null");
            return (Criteria) this;
        }

        public Criteria andTstflagIsNotNull() {
            addCriterion("TSTFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTstflagEqualTo(Integer value) {
            addCriterion("TSTFLAG =", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagNotEqualTo(Integer value) {
            addCriterion("TSTFLAG <>", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagGreaterThan(Integer value) {
            addCriterion("TSTFLAG >", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("TSTFLAG >=", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagLessThan(Integer value) {
            addCriterion("TSTFLAG <", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagLessThanOrEqualTo(Integer value) {
            addCriterion("TSTFLAG <=", value, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagIn(List<Integer> values) {
            addCriterion("TSTFLAG in", values, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagNotIn(List<Integer> values) {
            addCriterion("TSTFLAG not in", values, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagBetween(Integer value1, Integer value2) {
            addCriterion("TSTFLAG between", value1, value2, "tstflag");
            return (Criteria) this;
        }

        public Criteria andTstflagNotBetween(Integer value1, Integer value2) {
            addCriterion("TSTFLAG not between", value1, value2, "tstflag");
            return (Criteria) this;
        }

        public Criteria andRefcodeIsNull() {
            addCriterion("REFCODE is null");
            return (Criteria) this;
        }

        public Criteria andRefcodeIsNotNull() {
            addCriterion("REFCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRefcodeEqualTo(String value) {
            addCriterion("REFCODE =", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeNotEqualTo(String value) {
            addCriterion("REFCODE <>", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeGreaterThan(String value) {
            addCriterion("REFCODE >", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeGreaterThanOrEqualTo(String value) {
            addCriterion("REFCODE >=", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeLessThan(String value) {
            addCriterion("REFCODE <", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeLessThanOrEqualTo(String value) {
            addCriterion("REFCODE <=", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeLike(String value) {
            addCriterion("REFCODE like", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeNotLike(String value) {
            addCriterion("REFCODE not like", value, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeIn(List<String> values) {
            addCriterion("REFCODE in", values, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeNotIn(List<String> values) {
            addCriterion("REFCODE not in", values, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeBetween(String value1, String value2) {
            addCriterion("REFCODE between", value1, value2, "refcode");
            return (Criteria) this;
        }

        public Criteria andRefcodeNotBetween(String value1, String value2) {
            addCriterion("REFCODE not between", value1, value2, "refcode");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoIsNull() {
            addCriterion("KEYSEQNO is null");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoIsNotNull() {
            addCriterion("KEYSEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoEqualTo(Integer value) {
            addCriterion("KEYSEQNO =", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoNotEqualTo(Integer value) {
            addCriterion("KEYSEQNO <>", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoGreaterThan(Integer value) {
            addCriterion("KEYSEQNO >", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("KEYSEQNO >=", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoLessThan(Integer value) {
            addCriterion("KEYSEQNO <", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoLessThanOrEqualTo(Integer value) {
            addCriterion("KEYSEQNO <=", value, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoIn(List<Integer> values) {
            addCriterion("KEYSEQNO in", values, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoNotIn(List<Integer> values) {
            addCriterion("KEYSEQNO not in", values, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoBetween(Integer value1, Integer value2) {
            addCriterion("KEYSEQNO between", value1, value2, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyseqnoNotBetween(Integer value1, Integer value2) {
            addCriterion("KEYSEQNO not between", value1, value2, "keyseqno");
            return (Criteria) this;
        }

        public Criteria andKeyvalueIsNull() {
            addCriterion("KEYVALUE is null");
            return (Criteria) this;
        }

        public Criteria andKeyvalueIsNotNull() {
            addCriterion("KEYVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andKeyvalueEqualTo(String value) {
            addCriterion("KEYVALUE =", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueNotEqualTo(String value) {
            addCriterion("KEYVALUE <>", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueGreaterThan(String value) {
            addCriterion("KEYVALUE >", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueGreaterThanOrEqualTo(String value) {
            addCriterion("KEYVALUE >=", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueLessThan(String value) {
            addCriterion("KEYVALUE <", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueLessThanOrEqualTo(String value) {
            addCriterion("KEYVALUE <=", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueLike(String value) {
            addCriterion("KEYVALUE like", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueNotLike(String value) {
            addCriterion("KEYVALUE not like", value, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueIn(List<String> values) {
            addCriterion("KEYVALUE in", values, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueNotIn(List<String> values) {
            addCriterion("KEYVALUE not in", values, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueBetween(String value1, String value2) {
            addCriterion("KEYVALUE between", value1, value2, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andKeyvalueNotBetween(String value1, String value2) {
            addCriterion("KEYVALUE not between", value1, value2, "keyvalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueIsNull() {
            addCriterion("PREVALUE is null");
            return (Criteria) this;
        }

        public Criteria andPrevalueIsNotNull() {
            addCriterion("PREVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPrevalueEqualTo(String value) {
            addCriterion("PREVALUE =", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueNotEqualTo(String value) {
            addCriterion("PREVALUE <>", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueGreaterThan(String value) {
            addCriterion("PREVALUE >", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueGreaterThanOrEqualTo(String value) {
            addCriterion("PREVALUE >=", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueLessThan(String value) {
            addCriterion("PREVALUE <", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueLessThanOrEqualTo(String value) {
            addCriterion("PREVALUE <=", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueLike(String value) {
            addCriterion("PREVALUE like", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueNotLike(String value) {
            addCriterion("PREVALUE not like", value, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueIn(List<String> values) {
            addCriterion("PREVALUE in", values, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueNotIn(List<String> values) {
            addCriterion("PREVALUE not in", values, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueBetween(String value1, String value2) {
            addCriterion("PREVALUE between", value1, value2, "prevalue");
            return (Criteria) this;
        }

        public Criteria andPrevalueNotBetween(String value1, String value2) {
            addCriterion("PREVALUE not between", value1, value2, "prevalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIsNull() {
            addCriterion("CHECKVALUE is null");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIsNotNull() {
            addCriterion("CHECKVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckvalueEqualTo(String value) {
            addCriterion("CHECKVALUE =", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotEqualTo(String value) {
            addCriterion("CHECKVALUE <>", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueGreaterThan(String value) {
            addCriterion("CHECKVALUE >", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueGreaterThanOrEqualTo(String value) {
            addCriterion("CHECKVALUE >=", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLessThan(String value) {
            addCriterion("CHECKVALUE <", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLessThanOrEqualTo(String value) {
            addCriterion("CHECKVALUE <=", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLike(String value) {
            addCriterion("CHECKVALUE like", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotLike(String value) {
            addCriterion("CHECKVALUE not like", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIn(List<String> values) {
            addCriterion("CHECKVALUE in", values, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotIn(List<String> values) {
            addCriterion("CHECKVALUE not in", values, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueBetween(String value1, String value2) {
            addCriterion("CHECKVALUE between", value1, value2, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotBetween(String value1, String value2) {
            addCriterion("CHECKVALUE not between", value1, value2, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andActdateIsNull() {
            addCriterion("ACTDATE is null");
            return (Criteria) this;
        }

        public Criteria andActdateIsNotNull() {
            addCriterion("ACTDATE is not null");
            return (Criteria) this;
        }

        public Criteria andActdateEqualTo(Date value) {
            addCriterionForJDBCDate("ACTDATE =", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTDATE <>", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTDATE >", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTDATE >=", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateLessThan(Date value) {
            addCriterionForJDBCDate("ACTDATE <", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTDATE <=", value, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateIn(List<Date> values) {
            addCriterionForJDBCDate("ACTDATE in", values, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTDATE not in", values, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTDATE between", value1, value2, "actdate");
            return (Criteria) this;
        }

        public Criteria andActdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTDATE not between", value1, value2, "actdate");
            return (Criteria) this;
        }

        public Criteria andActtimeIsNull() {
            addCriterion("ACTTIME is null");
            return (Criteria) this;
        }

        public Criteria andActtimeIsNotNull() {
            addCriterion("ACTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andActtimeEqualTo(Long value) {
            addCriterion("ACTTIME =", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeNotEqualTo(Long value) {
            addCriterion("ACTTIME <>", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeGreaterThan(Long value) {
            addCriterion("ACTTIME >", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ACTTIME >=", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeLessThan(Long value) {
            addCriterion("ACTTIME <", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeLessThanOrEqualTo(Long value) {
            addCriterion("ACTTIME <=", value, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeIn(List<Long> values) {
            addCriterion("ACTTIME in", values, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeNotIn(List<Long> values) {
            addCriterion("ACTTIME not in", values, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeBetween(Long value1, Long value2) {
            addCriterion("ACTTIME between", value1, value2, "acttime");
            return (Criteria) this;
        }

        public Criteria andActtimeNotBetween(Long value1, Long value2) {
            addCriterion("ACTTIME not between", value1, value2, "acttime");
            return (Criteria) this;
        }

        public Criteria andExpiryIsNull() {
            addCriterion("EXPIRY is null");
            return (Criteria) this;
        }

        public Criteria andExpiryIsNotNull() {
            addCriterion("EXPIRY is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRY =", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryNotEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRY <>", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryGreaterThan(Date value) {
            addCriterionForJDBCDate("EXPIRY >", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRY >=", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryLessThan(Date value) {
            addCriterionForJDBCDate("EXPIRY <", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRY <=", value, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryIn(List<Date> values) {
            addCriterionForJDBCDate("EXPIRY in", values, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryNotIn(List<Date> values) {
            addCriterionForJDBCDate("EXPIRY not in", values, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPIRY between", value1, value2, "expiry");
            return (Criteria) this;
        }

        public Criteria andExpiryNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPIRY not between", value1, value2, "expiry");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalIsNull() {
            addCriterion("LONGKEYVAL is null");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalIsNotNull() {
            addCriterion("LONGKEYVAL is not null");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalEqualTo(String value) {
            addCriterion("LONGKEYVAL =", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalNotEqualTo(String value) {
            addCriterion("LONGKEYVAL <>", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalGreaterThan(String value) {
            addCriterion("LONGKEYVAL >", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalGreaterThanOrEqualTo(String value) {
            addCriterion("LONGKEYVAL >=", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalLessThan(String value) {
            addCriterion("LONGKEYVAL <", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalLessThanOrEqualTo(String value) {
            addCriterion("LONGKEYVAL <=", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalLike(String value) {
            addCriterion("LONGKEYVAL like", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalNotLike(String value) {
            addCriterion("LONGKEYVAL not like", value, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalIn(List<String> values) {
            addCriterion("LONGKEYVAL in", values, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalNotIn(List<String> values) {
            addCriterion("LONGKEYVAL not in", values, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalBetween(String value1, String value2) {
            addCriterion("LONGKEYVAL between", value1, value2, "longkeyval");
            return (Criteria) this;
        }

        public Criteria andLongkeyvalNotBetween(String value1, String value2) {
            addCriterion("LONGKEYVAL not between", value1, value2, "longkeyval");
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