package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.List;

public class TermPosExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermPosExample() {
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

        public Criteria andTypeidIsNull() {
            addCriterion("TYPEID is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("TYPEID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("TYPEID =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("TYPEID <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("TYPEID >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("TYPEID >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("TYPEID <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("TYPEID <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("TYPEID in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("TYPEID not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("TYPEID between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("TYPEID not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTermcodeIsNull() {
            addCriterion("TERMCODE is null");
            return (Criteria) this;
        }

        public Criteria andTermcodeIsNotNull() {
            addCriterion("TERMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTermcodeEqualTo(String value) {
            addCriterion("TERMCODE =", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotEqualTo(String value) {
            addCriterion("TERMCODE <>", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThan(String value) {
            addCriterion("TERMCODE >", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TERMCODE >=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThan(String value) {
            addCriterion("TERMCODE <", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThanOrEqualTo(String value) {
            addCriterion("TERMCODE <=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLike(String value) {
            addCriterion("TERMCODE like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotLike(String value) {
            addCriterion("TERMCODE not like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeIn(List<String> values) {
            addCriterion("TERMCODE in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotIn(List<String> values) {
            addCriterion("TERMCODE not in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeBetween(String value1, String value2) {
            addCriterion("TERMCODE between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotBetween(String value1, String value2) {
            addCriterion("TERMCODE not between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTestflagIsNull() {
            addCriterion("TESTFLAG is null");
            return (Criteria) this;
        }

        public Criteria andTestflagIsNotNull() {
            addCriterion("TESTFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTestflagEqualTo(Integer value) {
            addCriterion("TESTFLAG =", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagNotEqualTo(Integer value) {
            addCriterion("TESTFLAG <>", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagGreaterThan(Integer value) {
            addCriterion("TESTFLAG >", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("TESTFLAG >=", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagLessThan(Integer value) {
            addCriterion("TESTFLAG <", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagLessThanOrEqualTo(Integer value) {
            addCriterion("TESTFLAG <=", value, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagIn(List<Integer> values) {
            addCriterion("TESTFLAG in", values, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagNotIn(List<Integer> values) {
            addCriterion("TESTFLAG not in", values, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagBetween(Integer value1, Integer value2) {
            addCriterion("TESTFLAG between", value1, value2, "testflag");
            return (Criteria) this;
        }

        public Criteria andTestflagNotBetween(Integer value1, Integer value2) {
            addCriterion("TESTFLAG not between", value1, value2, "testflag");
            return (Criteria) this;
        }

        public Criteria andStatusidIsNull() {
            addCriterion("STATUSID is null");
            return (Criteria) this;
        }

        public Criteria andStatusidIsNotNull() {
            addCriterion("STATUSID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusidEqualTo(Integer value) {
            addCriterion("STATUSID =", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotEqualTo(Integer value) {
            addCriterion("STATUSID <>", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidGreaterThan(Integer value) {
            addCriterion("STATUSID >", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUSID >=", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidLessThan(Integer value) {
            addCriterion("STATUSID <", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidLessThanOrEqualTo(Integer value) {
            addCriterion("STATUSID <=", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidIn(List<Integer> values) {
            addCriterion("STATUSID in", values, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotIn(List<Integer> values) {
            addCriterion("STATUSID not in", values, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidBetween(Integer value1, Integer value2) {
            addCriterion("STATUSID between", value1, value2, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUSID not between", value1, value2, "statusid");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIsNull() {
            addCriterion("CURRCODE is null");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIsNotNull() {
            addCriterion("CURRCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrcodeEqualTo(String value) {
            addCriterion("CURRCODE =", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotEqualTo(String value) {
            addCriterion("CURRCODE <>", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeGreaterThan(String value) {
            addCriterion("CURRCODE >", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("CURRCODE >=", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLessThan(String value) {
            addCriterion("CURRCODE <", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLessThanOrEqualTo(String value) {
            addCriterion("CURRCODE <=", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLike(String value) {
            addCriterion("CURRCODE like", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotLike(String value) {
            addCriterion("CURRCODE not like", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIn(List<String> values) {
            addCriterion("CURRCODE in", values, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotIn(List<String> values) {
            addCriterion("CURRCODE not in", values, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeBetween(String value1, String value2) {
            addCriterion("CURRCODE between", value1, value2, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotBetween(String value1, String value2) {
            addCriterion("CURRCODE not between", value1, value2, "currcode");
            return (Criteria) this;
        }

        public Criteria andTermnoIsNull() {
            addCriterion("TERMNO is null");
            return (Criteria) this;
        }

        public Criteria andTermnoIsNotNull() {
            addCriterion("TERMNO is not null");
            return (Criteria) this;
        }

        public Criteria andTermnoEqualTo(Integer value) {
            addCriterion("TERMNO =", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoNotEqualTo(Integer value) {
            addCriterion("TERMNO <>", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoGreaterThan(Integer value) {
            addCriterion("TERMNO >", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("TERMNO >=", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoLessThan(Integer value) {
            addCriterion("TERMNO <", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoLessThanOrEqualTo(Integer value) {
            addCriterion("TERMNO <=", value, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoIn(List<Integer> values) {
            addCriterion("TERMNO in", values, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoNotIn(List<Integer> values) {
            addCriterion("TERMNO not in", values, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoBetween(Integer value1, Integer value2) {
            addCriterion("TERMNO between", value1, value2, "termno");
            return (Criteria) this;
        }

        public Criteria andTermnoNotBetween(Integer value1, Integer value2) {
            addCriterion("TERMNO not between", value1, value2, "termno");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("LOCATION is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("LOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("LOCATION =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("LOCATION <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("LOCATION >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATION >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("LOCATION <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("LOCATION <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("LOCATION like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("LOCATION not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("LOCATION in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("LOCATION not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("LOCATION between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("LOCATION not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andConaccnoIsNull() {
            addCriterion("CONACCNO is null");
            return (Criteria) this;
        }

        public Criteria andConaccnoIsNotNull() {
            addCriterion("CONACCNO is not null");
            return (Criteria) this;
        }

        public Criteria andConaccnoEqualTo(String value) {
            addCriterion("CONACCNO =", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotEqualTo(String value) {
            addCriterion("CONACCNO <>", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoGreaterThan(String value) {
            addCriterion("CONACCNO >", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoGreaterThanOrEqualTo(String value) {
            addCriterion("CONACCNO >=", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLessThan(String value) {
            addCriterion("CONACCNO <", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLessThanOrEqualTo(String value) {
            addCriterion("CONACCNO <=", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLike(String value) {
            addCriterion("CONACCNO like", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotLike(String value) {
            addCriterion("CONACCNO not like", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoIn(List<String> values) {
            addCriterion("CONACCNO in", values, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotIn(List<String> values) {
            addCriterion("CONACCNO not in", values, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoBetween(String value1, String value2) {
            addCriterion("CONACCNO between", value1, value2, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotBetween(String value1, String value2) {
            addCriterion("CONACCNO not between", value1, value2, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConcurIsNull() {
            addCriterion("CONCUR is null");
            return (Criteria) this;
        }

        public Criteria andConcurIsNotNull() {
            addCriterion("CONCUR is not null");
            return (Criteria) this;
        }

        public Criteria andConcurEqualTo(String value) {
            addCriterion("CONCUR =", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotEqualTo(String value) {
            addCriterion("CONCUR <>", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurGreaterThan(String value) {
            addCriterion("CONCUR >", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurGreaterThanOrEqualTo(String value) {
            addCriterion("CONCUR >=", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLessThan(String value) {
            addCriterion("CONCUR <", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLessThanOrEqualTo(String value) {
            addCriterion("CONCUR <=", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLike(String value) {
            addCriterion("CONCUR like", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotLike(String value) {
            addCriterion("CONCUR not like", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurIn(List<String> values) {
            addCriterion("CONCUR in", values, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotIn(List<String> values) {
            addCriterion("CONCUR not in", values, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurBetween(String value1, String value2) {
            addCriterion("CONCUR between", value1, value2, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotBetween(String value1, String value2) {
            addCriterion("CONCUR not between", value1, value2, "concur");
            return (Criteria) this;
        }

        public Criteria andPoschicIsNull() {
            addCriterion("POSCHIC is null");
            return (Criteria) this;
        }

        public Criteria andPoschicIsNotNull() {
            addCriterion("POSCHIC is not null");
            return (Criteria) this;
        }

        public Criteria andPoschicEqualTo(String value) {
            addCriterion("POSCHIC =", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicNotEqualTo(String value) {
            addCriterion("POSCHIC <>", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicGreaterThan(String value) {
            addCriterion("POSCHIC >", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicGreaterThanOrEqualTo(String value) {
            addCriterion("POSCHIC >=", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicLessThan(String value) {
            addCriterion("POSCHIC <", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicLessThanOrEqualTo(String value) {
            addCriterion("POSCHIC <=", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicLike(String value) {
            addCriterion("POSCHIC like", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicNotLike(String value) {
            addCriterion("POSCHIC not like", value, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicIn(List<String> values) {
            addCriterion("POSCHIC in", values, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicNotIn(List<String> values) {
            addCriterion("POSCHIC not in", values, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicBetween(String value1, String value2) {
            addCriterion("POSCHIC between", value1, value2, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschicNotBetween(String value1, String value2) {
            addCriterion("POSCHIC not between", value1, value2, "poschic");
            return (Criteria) this;
        }

        public Criteria andPoschacIsNull() {
            addCriterion("POSCHAC is null");
            return (Criteria) this;
        }

        public Criteria andPoschacIsNotNull() {
            addCriterion("POSCHAC is not null");
            return (Criteria) this;
        }

        public Criteria andPoschacEqualTo(String value) {
            addCriterion("POSCHAC =", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacNotEqualTo(String value) {
            addCriterion("POSCHAC <>", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacGreaterThan(String value) {
            addCriterion("POSCHAC >", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacGreaterThanOrEqualTo(String value) {
            addCriterion("POSCHAC >=", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacLessThan(String value) {
            addCriterion("POSCHAC <", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacLessThanOrEqualTo(String value) {
            addCriterion("POSCHAC <=", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacLike(String value) {
            addCriterion("POSCHAC like", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacNotLike(String value) {
            addCriterion("POSCHAC not like", value, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacIn(List<String> values) {
            addCriterion("POSCHAC in", values, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacNotIn(List<String> values) {
            addCriterion("POSCHAC not in", values, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacBetween(String value1, String value2) {
            addCriterion("POSCHAC between", value1, value2, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoschacNotBetween(String value1, String value2) {
            addCriterion("POSCHAC not between", value1, value2, "poschac");
            return (Criteria) this;
        }

        public Criteria andPoscrcIsNull() {
            addCriterion("POSCRC is null");
            return (Criteria) this;
        }

        public Criteria andPoscrcIsNotNull() {
            addCriterion("POSCRC is not null");
            return (Criteria) this;
        }

        public Criteria andPoscrcEqualTo(String value) {
            addCriterion("POSCRC =", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcNotEqualTo(String value) {
            addCriterion("POSCRC <>", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcGreaterThan(String value) {
            addCriterion("POSCRC >", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcGreaterThanOrEqualTo(String value) {
            addCriterion("POSCRC >=", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcLessThan(String value) {
            addCriterion("POSCRC <", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcLessThanOrEqualTo(String value) {
            addCriterion("POSCRC <=", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcLike(String value) {
            addCriterion("POSCRC like", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcNotLike(String value) {
            addCriterion("POSCRC not like", value, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcIn(List<String> values) {
            addCriterion("POSCRC in", values, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcNotIn(List<String> values) {
            addCriterion("POSCRC not in", values, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcBetween(String value1, String value2) {
            addCriterion("POSCRC between", value1, value2, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPoscrcNotBetween(String value1, String value2) {
            addCriterion("POSCRC not between", value1, value2, "poscrc");
            return (Criteria) this;
        }

        public Criteria andPosoeIsNull() {
            addCriterion("POSOE is null");
            return (Criteria) this;
        }

        public Criteria andPosoeIsNotNull() {
            addCriterion("POSOE is not null");
            return (Criteria) this;
        }

        public Criteria andPosoeEqualTo(String value) {
            addCriterion("POSOE =", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeNotEqualTo(String value) {
            addCriterion("POSOE <>", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeGreaterThan(String value) {
            addCriterion("POSOE >", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeGreaterThanOrEqualTo(String value) {
            addCriterion("POSOE >=", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeLessThan(String value) {
            addCriterion("POSOE <", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeLessThanOrEqualTo(String value) {
            addCriterion("POSOE <=", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeLike(String value) {
            addCriterion("POSOE like", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeNotLike(String value) {
            addCriterion("POSOE not like", value, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeIn(List<String> values) {
            addCriterion("POSOE in", values, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeNotIn(List<String> values) {
            addCriterion("POSOE not in", values, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeBetween(String value1, String value2) {
            addCriterion("POSOE between", value1, value2, "posoe");
            return (Criteria) this;
        }

        public Criteria andPosoeNotBetween(String value1, String value2) {
            addCriterion("POSOE not between", value1, value2, "posoe");
            return (Criteria) this;
        }

        public Criteria andPoscdocIsNull() {
            addCriterion("POSCDOC is null");
            return (Criteria) this;
        }

        public Criteria andPoscdocIsNotNull() {
            addCriterion("POSCDOC is not null");
            return (Criteria) this;
        }

        public Criteria andPoscdocEqualTo(String value) {
            addCriterion("POSCDOC =", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocNotEqualTo(String value) {
            addCriterion("POSCDOC <>", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocGreaterThan(String value) {
            addCriterion("POSCDOC >", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocGreaterThanOrEqualTo(String value) {
            addCriterion("POSCDOC >=", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocLessThan(String value) {
            addCriterion("POSCDOC <", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocLessThanOrEqualTo(String value) {
            addCriterion("POSCDOC <=", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocLike(String value) {
            addCriterion("POSCDOC like", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocNotLike(String value) {
            addCriterion("POSCDOC not like", value, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocIn(List<String> values) {
            addCriterion("POSCDOC in", values, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocNotIn(List<String> values) {
            addCriterion("POSCDOC not in", values, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocBetween(String value1, String value2) {
            addCriterion("POSCDOC between", value1, value2, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPoscdocNotBetween(String value1, String value2) {
            addCriterion("POSCDOC not between", value1, value2, "poscdoc");
            return (Criteria) this;
        }

        public Criteria andPostocIsNull() {
            addCriterion("POSTOC is null");
            return (Criteria) this;
        }

        public Criteria andPostocIsNotNull() {
            addCriterion("POSTOC is not null");
            return (Criteria) this;
        }

        public Criteria andPostocEqualTo(String value) {
            addCriterion("POSTOC =", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocNotEqualTo(String value) {
            addCriterion("POSTOC <>", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocGreaterThan(String value) {
            addCriterion("POSTOC >", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocGreaterThanOrEqualTo(String value) {
            addCriterion("POSTOC >=", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocLessThan(String value) {
            addCriterion("POSTOC <", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocLessThanOrEqualTo(String value) {
            addCriterion("POSTOC <=", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocLike(String value) {
            addCriterion("POSTOC like", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocNotLike(String value) {
            addCriterion("POSTOC not like", value, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocIn(List<String> values) {
            addCriterion("POSTOC in", values, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocNotIn(List<String> values) {
            addCriterion("POSTOC not in", values, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocBetween(String value1, String value2) {
            addCriterion("POSTOC between", value1, value2, "postoc");
            return (Criteria) this;
        }

        public Criteria andPostocNotBetween(String value1, String value2) {
            addCriterion("POSTOC not between", value1, value2, "postoc");
            return (Criteria) this;
        }

        public Criteria andPospccIsNull() {
            addCriterion("POSPCC is null");
            return (Criteria) this;
        }

        public Criteria andPospccIsNotNull() {
            addCriterion("POSPCC is not null");
            return (Criteria) this;
        }

        public Criteria andPospccEqualTo(String value) {
            addCriterion("POSPCC =", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccNotEqualTo(String value) {
            addCriterion("POSPCC <>", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccGreaterThan(String value) {
            addCriterion("POSPCC >", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccGreaterThanOrEqualTo(String value) {
            addCriterion("POSPCC >=", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccLessThan(String value) {
            addCriterion("POSPCC <", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccLessThanOrEqualTo(String value) {
            addCriterion("POSPCC <=", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccLike(String value) {
            addCriterion("POSPCC like", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccNotLike(String value) {
            addCriterion("POSPCC not like", value, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccIn(List<String> values) {
            addCriterion("POSPCC in", values, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccNotIn(List<String> values) {
            addCriterion("POSPCC not in", values, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccBetween(String value1, String value2) {
            addCriterion("POSPCC between", value1, value2, "pospcc");
            return (Criteria) this;
        }

        public Criteria andPospccNotBetween(String value1, String value2) {
            addCriterion("POSPCC not between", value1, value2, "pospcc");
            return (Criteria) this;
        }

        public Criteria andTimezoneIsNull() {
            addCriterion("TIMEZONE is null");
            return (Criteria) this;
        }

        public Criteria andTimezoneIsNotNull() {
            addCriterion("TIMEZONE is not null");
            return (Criteria) this;
        }

        public Criteria andTimezoneEqualTo(String value) {
            addCriterion("TIMEZONE =", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotEqualTo(String value) {
            addCriterion("TIMEZONE <>", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneGreaterThan(String value) {
            addCriterion("TIMEZONE >", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneGreaterThanOrEqualTo(String value) {
            addCriterion("TIMEZONE >=", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLessThan(String value) {
            addCriterion("TIMEZONE <", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLessThanOrEqualTo(String value) {
            addCriterion("TIMEZONE <=", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLike(String value) {
            addCriterion("TIMEZONE like", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotLike(String value) {
            addCriterion("TIMEZONE not like", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneIn(List<String> values) {
            addCriterion("TIMEZONE in", values, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotIn(List<String> values) {
            addCriterion("TIMEZONE not in", values, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneBetween(String value1, String value2) {
            addCriterion("TIMEZONE between", value1, value2, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotBetween(String value1, String value2) {
            addCriterion("TIMEZONE not between", value1, value2, "timezone");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNull() {
            addCriterion("CAT_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNotNull() {
            addCriterion("CAT_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andCatParamsEqualTo(String value) {
            addCriterion("CAT_PARAMS =", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotEqualTo(String value) {
            addCriterion("CAT_PARAMS <>", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThan(String value) {
            addCriterion("CAT_PARAMS >", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS >=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThan(String value) {
            addCriterion("CAT_PARAMS <", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS <=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLike(String value) {
            addCriterion("CAT_PARAMS like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotLike(String value) {
            addCriterion("CAT_PARAMS not like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsIn(List<String> values) {
            addCriterion("CAT_PARAMS in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotIn(List<String> values) {
            addCriterion("CAT_PARAMS not in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS not between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("MERCHANT_ID =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("MERCHANT_ID <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("MERCHANT_ID >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("MERCHANT_ID <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("MERCHANT_ID in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("MERCHANT_ID not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andTermtypeIsNull() {
            addCriterion("TERMTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTermtypeIsNotNull() {
            addCriterion("TERMTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTermtypeEqualTo(String value) {
            addCriterion("TERMTYPE =", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotEqualTo(String value) {
            addCriterion("TERMTYPE <>", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeGreaterThan(String value) {
            addCriterion("TERMTYPE >", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeGreaterThanOrEqualTo(String value) {
            addCriterion("TERMTYPE >=", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLessThan(String value) {
            addCriterion("TERMTYPE <", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLessThanOrEqualTo(String value) {
            addCriterion("TERMTYPE <=", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLike(String value) {
            addCriterion("TERMTYPE like", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotLike(String value) {
            addCriterion("TERMTYPE not like", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeIn(List<String> values) {
            addCriterion("TERMTYPE in", values, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotIn(List<String> values) {
            addCriterion("TERMTYPE not in", values, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeBetween(String value1, String value2) {
            addCriterion("TERMTYPE between", value1, value2, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotBetween(String value1, String value2) {
            addCriterion("TERMTYPE not between", value1, value2, "termtype");
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