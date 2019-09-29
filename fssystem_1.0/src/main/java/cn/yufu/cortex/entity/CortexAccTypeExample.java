package cn.yufu.cortex.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CortexAccTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CortexAccTypeExample() {
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

        public Criteria andTypecodeIsNull() {
            addCriterion("TYPECODE is null");
            return (Criteria) this;
        }

        public Criteria andTypecodeIsNotNull() {
            addCriterion("TYPECODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypecodeEqualTo(String value) {
            addCriterion("TYPECODE =", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeNotEqualTo(String value) {
            addCriterion("TYPECODE <>", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeGreaterThan(String value) {
            addCriterion("TYPECODE >", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPECODE >=", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeLessThan(String value) {
            addCriterion("TYPECODE <", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeLessThanOrEqualTo(String value) {
            addCriterion("TYPECODE <=", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeLike(String value) {
            addCriterion("TYPECODE like", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeNotLike(String value) {
            addCriterion("TYPECODE not like", value, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeIn(List<String> values) {
            addCriterion("TYPECODE in", values, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeNotIn(List<String> values) {
            addCriterion("TYPECODE not in", values, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeBetween(String value1, String value2) {
            addCriterion("TYPECODE between", value1, value2, "typecode");
            return (Criteria) this;
        }

        public Criteria andTypecodeNotBetween(String value1, String value2) {
            addCriterion("TYPECODE not between", value1, value2, "typecode");
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

        public Criteria andClassidIsNull() {
            addCriterion("CLASSID is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("CLASSID is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("CLASSID =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("CLASSID <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("CLASSID >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CLASSID >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("CLASSID <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("CLASSID <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("CLASSID in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("CLASSID not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("CLASSID between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("CLASSID not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNull() {
            addCriterion("ISOCODE is null");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNotNull() {
            addCriterion("ISOCODE is not null");
            return (Criteria) this;
        }

        public Criteria andIsocodeEqualTo(Integer value) {
            addCriterion("ISOCODE =", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotEqualTo(Integer value) {
            addCriterion("ISOCODE <>", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThan(Integer value) {
            addCriterion("ISOCODE >", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISOCODE >=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThan(Integer value) {
            addCriterion("ISOCODE <", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThanOrEqualTo(Integer value) {
            addCriterion("ISOCODE <=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeIn(List<Integer> values) {
            addCriterion("ISOCODE in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotIn(List<Integer> values) {
            addCriterion("ISOCODE not in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeBetween(Integer value1, Integer value2) {
            addCriterion("ISOCODE between", value1, value2, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotBetween(Integer value1, Integer value2) {
            addCriterion("ISOCODE not between", value1, value2, "isocode");
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

        public Criteria andCurrcode2IsNull() {
            addCriterion("CURRCODE2 is null");
            return (Criteria) this;
        }

        public Criteria andCurrcode2IsNotNull() {
            addCriterion("CURRCODE2 is not null");
            return (Criteria) this;
        }

        public Criteria andCurrcode2EqualTo(String value) {
            addCriterion("CURRCODE2 =", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2NotEqualTo(String value) {
            addCriterion("CURRCODE2 <>", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2GreaterThan(String value) {
            addCriterion("CURRCODE2 >", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2GreaterThanOrEqualTo(String value) {
            addCriterion("CURRCODE2 >=", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2LessThan(String value) {
            addCriterion("CURRCODE2 <", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2LessThanOrEqualTo(String value) {
            addCriterion("CURRCODE2 <=", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2Like(String value) {
            addCriterion("CURRCODE2 like", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2NotLike(String value) {
            addCriterion("CURRCODE2 not like", value, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2In(List<String> values) {
            addCriterion("CURRCODE2 in", values, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2NotIn(List<String> values) {
            addCriterion("CURRCODE2 not in", values, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2Between(String value1, String value2) {
            addCriterion("CURRCODE2 between", value1, value2, "currcode2");
            return (Criteria) this;
        }

        public Criteria andCurrcode2NotBetween(String value1, String value2) {
            addCriterion("CURRCODE2 not between", value1, value2, "currcode2");
            return (Criteria) this;
        }

        public Criteria andSrvidIsNull() {
            addCriterion("SRVID is null");
            return (Criteria) this;
        }

        public Criteria andSrvidIsNotNull() {
            addCriterion("SRVID is not null");
            return (Criteria) this;
        }

        public Criteria andSrvidEqualTo(Integer value) {
            addCriterion("SRVID =", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidNotEqualTo(Integer value) {
            addCriterion("SRVID <>", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidGreaterThan(Integer value) {
            addCriterion("SRVID >", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SRVID >=", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidLessThan(Integer value) {
            addCriterion("SRVID <", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidLessThanOrEqualTo(Integer value) {
            addCriterion("SRVID <=", value, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidIn(List<Integer> values) {
            addCriterion("SRVID in", values, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidNotIn(List<Integer> values) {
            addCriterion("SRVID not in", values, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidBetween(Integer value1, Integer value2) {
            addCriterion("SRVID between", value1, value2, "srvid");
            return (Criteria) this;
        }

        public Criteria andSrvidNotBetween(Integer value1, Integer value2) {
            addCriterion("SRVID not between", value1, value2, "srvid");
            return (Criteria) this;
        }

        public Criteria andFmtidIsNull() {
            addCriterion("FMTID is null");
            return (Criteria) this;
        }

        public Criteria andFmtidIsNotNull() {
            addCriterion("FMTID is not null");
            return (Criteria) this;
        }

        public Criteria andFmtidEqualTo(Integer value) {
            addCriterion("FMTID =", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidNotEqualTo(Integer value) {
            addCriterion("FMTID <>", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidGreaterThan(Integer value) {
            addCriterion("FMTID >", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FMTID >=", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidLessThan(Integer value) {
            addCriterion("FMTID <", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidLessThanOrEqualTo(Integer value) {
            addCriterion("FMTID <=", value, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidIn(List<Integer> values) {
            addCriterion("FMTID in", values, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidNotIn(List<Integer> values) {
            addCriterion("FMTID not in", values, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidBetween(Integer value1, Integer value2) {
            addCriterion("FMTID between", value1, value2, "fmtid");
            return (Criteria) this;
        }

        public Criteria andFmtidNotBetween(Integer value1, Integer value2) {
            addCriterion("FMTID not between", value1, value2, "fmtid");
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

        public Criteria andBankacccodeIsNull() {
            addCriterion("BANKACCCODE is null");
            return (Criteria) this;
        }

        public Criteria andBankacccodeIsNotNull() {
            addCriterion("BANKACCCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBankacccodeEqualTo(String value) {
            addCriterion("BANKACCCODE =", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeNotEqualTo(String value) {
            addCriterion("BANKACCCODE <>", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeGreaterThan(String value) {
            addCriterion("BANKACCCODE >", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeGreaterThanOrEqualTo(String value) {
            addCriterion("BANKACCCODE >=", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeLessThan(String value) {
            addCriterion("BANKACCCODE <", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeLessThanOrEqualTo(String value) {
            addCriterion("BANKACCCODE <=", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeLike(String value) {
            addCriterion("BANKACCCODE like", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeNotLike(String value) {
            addCriterion("BANKACCCODE not like", value, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeIn(List<String> values) {
            addCriterion("BANKACCCODE in", values, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeNotIn(List<String> values) {
            addCriterion("BANKACCCODE not in", values, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeBetween(String value1, String value2) {
            addCriterion("BANKACCCODE between", value1, value2, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andBankacccodeNotBetween(String value1, String value2) {
            addCriterion("BANKACCCODE not between", value1, value2, "bankacccode");
            return (Criteria) this;
        }

        public Criteria andNowIsNull() {
            addCriterion("NOW is null");
            return (Criteria) this;
        }

        public Criteria andNowIsNotNull() {
            addCriterion("NOW is not null");
            return (Criteria) this;
        }

        public Criteria andNowEqualTo(Integer value) {
            addCriterion("NOW =", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowNotEqualTo(Integer value) {
            addCriterion("NOW <>", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowGreaterThan(Integer value) {
            addCriterion("NOW >", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowGreaterThanOrEqualTo(Integer value) {
            addCriterion("NOW >=", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowLessThan(Integer value) {
            addCriterion("NOW <", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowLessThanOrEqualTo(Integer value) {
            addCriterion("NOW <=", value, "now");
            return (Criteria) this;
        }

        public Criteria andNowIn(List<Integer> values) {
            addCriterion("NOW in", values, "now");
            return (Criteria) this;
        }

        public Criteria andNowNotIn(List<Integer> values) {
            addCriterion("NOW not in", values, "now");
            return (Criteria) this;
        }

        public Criteria andNowBetween(Integer value1, Integer value2) {
            addCriterion("NOW between", value1, value2, "now");
            return (Criteria) this;
        }

        public Criteria andNowNotBetween(Integer value1, Integer value2) {
            addCriterion("NOW not between", value1, value2, "now");
            return (Criteria) this;
        }

        public Criteria andAcclenIsNull() {
            addCriterion("ACCLEN is null");
            return (Criteria) this;
        }

        public Criteria andAcclenIsNotNull() {
            addCriterion("ACCLEN is not null");
            return (Criteria) this;
        }

        public Criteria andAcclenEqualTo(Integer value) {
            addCriterion("ACCLEN =", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenNotEqualTo(Integer value) {
            addCriterion("ACCLEN <>", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenGreaterThan(Integer value) {
            addCriterion("ACCLEN >", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACCLEN >=", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenLessThan(Integer value) {
            addCriterion("ACCLEN <", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenLessThanOrEqualTo(Integer value) {
            addCriterion("ACCLEN <=", value, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenIn(List<Integer> values) {
            addCriterion("ACCLEN in", values, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenNotIn(List<Integer> values) {
            addCriterion("ACCLEN not in", values, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenBetween(Integer value1, Integer value2) {
            addCriterion("ACCLEN between", value1, value2, "acclen");
            return (Criteria) this;
        }

        public Criteria andAcclenNotBetween(Integer value1, Integer value2) {
            addCriterion("ACCLEN not between", value1, value2, "acclen");
            return (Criteria) this;
        }

        public Criteria andAllowsvcIsNull() {
            addCriterion("ALLOWSVC is null");
            return (Criteria) this;
        }

        public Criteria andAllowsvcIsNotNull() {
            addCriterion("ALLOWSVC is not null");
            return (Criteria) this;
        }

        public Criteria andAllowsvcEqualTo(String value) {
            addCriterion("ALLOWSVC =", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcNotEqualTo(String value) {
            addCriterion("ALLOWSVC <>", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcGreaterThan(String value) {
            addCriterion("ALLOWSVC >", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcGreaterThanOrEqualTo(String value) {
            addCriterion("ALLOWSVC >=", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcLessThan(String value) {
            addCriterion("ALLOWSVC <", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcLessThanOrEqualTo(String value) {
            addCriterion("ALLOWSVC <=", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcLike(String value) {
            addCriterion("ALLOWSVC like", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcNotLike(String value) {
            addCriterion("ALLOWSVC not like", value, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcIn(List<String> values) {
            addCriterion("ALLOWSVC in", values, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcNotIn(List<String> values) {
            addCriterion("ALLOWSVC not in", values, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcBetween(String value1, String value2) {
            addCriterion("ALLOWSVC between", value1, value2, "allowsvc");
            return (Criteria) this;
        }

        public Criteria andAllowsvcNotBetween(String value1, String value2) {
            addCriterion("ALLOWSVC not between", value1, value2, "allowsvc");
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

        public Criteria andBrktypeIsNull() {
            addCriterion("BRKTYPE is null");
            return (Criteria) this;
        }

        public Criteria andBrktypeIsNotNull() {
            addCriterion("BRKTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBrktypeEqualTo(Integer value) {
            addCriterion("BRKTYPE =", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotEqualTo(Integer value) {
            addCriterion("BRKTYPE <>", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeGreaterThan(Integer value) {
            addCriterion("BRKTYPE >", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("BRKTYPE >=", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeLessThan(Integer value) {
            addCriterion("BRKTYPE <", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeLessThanOrEqualTo(Integer value) {
            addCriterion("BRKTYPE <=", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeIn(List<Integer> values) {
            addCriterion("BRKTYPE in", values, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotIn(List<Integer> values) {
            addCriterion("BRKTYPE not in", values, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeBetween(Integer value1, Integer value2) {
            addCriterion("BRKTYPE between", value1, value2, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotBetween(Integer value1, Integer value2) {
            addCriterion("BRKTYPE not between", value1, value2, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetIsNull() {
            addCriterion("BRKOFFSET is null");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetIsNotNull() {
            addCriterion("BRKOFFSET is not null");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetEqualTo(Long value) {
            addCriterion("BRKOFFSET =", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetNotEqualTo(Long value) {
            addCriterion("BRKOFFSET <>", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetGreaterThan(Long value) {
            addCriterion("BRKOFFSET >", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetGreaterThanOrEqualTo(Long value) {
            addCriterion("BRKOFFSET >=", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetLessThan(Long value) {
            addCriterion("BRKOFFSET <", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetLessThanOrEqualTo(Long value) {
            addCriterion("BRKOFFSET <=", value, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetIn(List<Long> values) {
            addCriterion("BRKOFFSET in", values, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetNotIn(List<Long> values) {
            addCriterion("BRKOFFSET not in", values, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetBetween(Long value1, Long value2) {
            addCriterion("BRKOFFSET between", value1, value2, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andBrkoffsetNotBetween(Long value1, Long value2) {
            addCriterion("BRKOFFSET not between", value1, value2, "brkoffset");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdIsNull() {
            addCriterion("CAT_ACCTMRKUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdIsNotNull() {
            addCriterion("CAT_ACCTMRKUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdEqualTo(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID =", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdNotEqualTo(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID <>", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdGreaterThan(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID >", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID >=", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdLessThan(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID <", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdLessThanOrEqualTo(Long value) {
            addCriterion("CAT_ACCTMRKUP_ID <=", value, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdIn(List<Long> values) {
            addCriterion("CAT_ACCTMRKUP_ID in", values, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdNotIn(List<Long> values) {
            addCriterion("CAT_ACCTMRKUP_ID not in", values, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdBetween(Long value1, Long value2) {
            addCriterion("CAT_ACCTMRKUP_ID between", value1, value2, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andCatAcctmrkupIdNotBetween(Long value1, Long value2) {
            addCriterion("CAT_ACCTMRKUP_ID not between", value1, value2, "catAcctmrkupId");
            return (Criteria) this;
        }

        public Criteria andAccloggingIsNull() {
            addCriterion("ACCLOGGING is null");
            return (Criteria) this;
        }

        public Criteria andAccloggingIsNotNull() {
            addCriterion("ACCLOGGING is not null");
            return (Criteria) this;
        }

        public Criteria andAccloggingEqualTo(Long value) {
            addCriterion("ACCLOGGING =", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingNotEqualTo(Long value) {
            addCriterion("ACCLOGGING <>", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingGreaterThan(Long value) {
            addCriterion("ACCLOGGING >", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCLOGGING >=", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingLessThan(Long value) {
            addCriterion("ACCLOGGING <", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingLessThanOrEqualTo(Long value) {
            addCriterion("ACCLOGGING <=", value, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingIn(List<Long> values) {
            addCriterion("ACCLOGGING in", values, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingNotIn(List<Long> values) {
            addCriterion("ACCLOGGING not in", values, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingBetween(Long value1, Long value2) {
            addCriterion("ACCLOGGING between", value1, value2, "acclogging");
            return (Criteria) this;
        }

        public Criteria andAccloggingNotBetween(Long value1, Long value2) {
            addCriterion("ACCLOGGING not between", value1, value2, "acclogging");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtIsNull() {
            addCriterion("MAXOVRRDAMT is null");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtIsNotNull() {
            addCriterion("MAXOVRRDAMT is not null");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtEqualTo(BigDecimal value) {
            addCriterion("MAXOVRRDAMT =", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtNotEqualTo(BigDecimal value) {
            addCriterion("MAXOVRRDAMT <>", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtGreaterThan(BigDecimal value) {
            addCriterion("MAXOVRRDAMT >", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MAXOVRRDAMT >=", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtLessThan(BigDecimal value) {
            addCriterion("MAXOVRRDAMT <", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MAXOVRRDAMT <=", value, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtIn(List<BigDecimal> values) {
            addCriterion("MAXOVRRDAMT in", values, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtNotIn(List<BigDecimal> values) {
            addCriterion("MAXOVRRDAMT not in", values, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAXOVRRDAMT between", value1, value2, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAXOVRRDAMT not between", value1, value2, "maxovrrdamt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntIsNull() {
            addCriterion("MAXOVRRDCRLMPRCNT is null");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntIsNotNull() {
            addCriterion("MAXOVRRDCRLMPRCNT is not null");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntEqualTo(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT =", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntNotEqualTo(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT <>", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntGreaterThan(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT >", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntGreaterThanOrEqualTo(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT >=", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntLessThan(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT <", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntLessThanOrEqualTo(Double value) {
            addCriterion("MAXOVRRDCRLMPRCNT <=", value, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntIn(List<Double> values) {
            addCriterion("MAXOVRRDCRLMPRCNT in", values, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntNotIn(List<Double> values) {
            addCriterion("MAXOVRRDCRLMPRCNT not in", values, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntBetween(Double value1, Double value2) {
            addCriterion("MAXOVRRDCRLMPRCNT between", value1, value2, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andMaxovrrdcrlmprcntNotBetween(Double value1, Double value2) {
            addCriterion("MAXOVRRDCRLMPRCNT not between", value1, value2, "maxovrrdcrlmprcnt");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdIsNull() {
            addCriterion("CAT_ACCTRISK_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdIsNotNull() {
            addCriterion("CAT_ACCTRISK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdEqualTo(Long value) {
            addCriterion("CAT_ACCTRISK_ID =", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdNotEqualTo(Long value) {
            addCriterion("CAT_ACCTRISK_ID <>", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdGreaterThan(Long value) {
            addCriterion("CAT_ACCTRISK_ID >", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CAT_ACCTRISK_ID >=", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdLessThan(Long value) {
            addCriterion("CAT_ACCTRISK_ID <", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdLessThanOrEqualTo(Long value) {
            addCriterion("CAT_ACCTRISK_ID <=", value, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdIn(List<Long> values) {
            addCriterion("CAT_ACCTRISK_ID in", values, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdNotIn(List<Long> values) {
            addCriterion("CAT_ACCTRISK_ID not in", values, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdBetween(Long value1, Long value2) {
            addCriterion("CAT_ACCTRISK_ID between", value1, value2, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andCatAcctriskIdNotBetween(Long value1, Long value2) {
            addCriterion("CAT_ACCTRISK_ID not between", value1, value2, "catAcctriskId");
            return (Criteria) this;
        }

        public Criteria andGenaccnumIsNull() {
            addCriterion("GENACCNUM is null");
            return (Criteria) this;
        }

        public Criteria andGenaccnumIsNotNull() {
            addCriterion("GENACCNUM is not null");
            return (Criteria) this;
        }

        public Criteria andGenaccnumEqualTo(Integer value) {
            addCriterion("GENACCNUM =", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumNotEqualTo(Integer value) {
            addCriterion("GENACCNUM <>", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumGreaterThan(Integer value) {
            addCriterion("GENACCNUM >", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("GENACCNUM >=", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumLessThan(Integer value) {
            addCriterion("GENACCNUM <", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumLessThanOrEqualTo(Integer value) {
            addCriterion("GENACCNUM <=", value, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumIn(List<Integer> values) {
            addCriterion("GENACCNUM in", values, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumNotIn(List<Integer> values) {
            addCriterion("GENACCNUM not in", values, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumBetween(Integer value1, Integer value2) {
            addCriterion("GENACCNUM between", value1, value2, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andGenaccnumNotBetween(Integer value1, Integer value2) {
            addCriterion("GENACCNUM not between", value1, value2, "genaccnum");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqIsNull() {
            addCriterion("ACCTYPESEQ is null");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqIsNotNull() {
            addCriterion("ACCTYPESEQ is not null");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqEqualTo(Long value) {
            addCriterion("ACCTYPESEQ =", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqNotEqualTo(Long value) {
            addCriterion("ACCTYPESEQ <>", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqGreaterThan(Long value) {
            addCriterion("ACCTYPESEQ >", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCTYPESEQ >=", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqLessThan(Long value) {
            addCriterion("ACCTYPESEQ <", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqLessThanOrEqualTo(Long value) {
            addCriterion("ACCTYPESEQ <=", value, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqIn(List<Long> values) {
            addCriterion("ACCTYPESEQ in", values, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqNotIn(List<Long> values) {
            addCriterion("ACCTYPESEQ not in", values, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqBetween(Long value1, Long value2) {
            addCriterion("ACCTYPESEQ between", value1, value2, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAcctypeseqNotBetween(Long value1, Long value2) {
            addCriterion("ACCTYPESEQ not between", value1, value2, "acctypeseq");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtIsNull() {
            addCriterion("ACCNUMFRMT is null");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtIsNotNull() {
            addCriterion("ACCNUMFRMT is not null");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtEqualTo(String value) {
            addCriterion("ACCNUMFRMT =", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtNotEqualTo(String value) {
            addCriterion("ACCNUMFRMT <>", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtGreaterThan(String value) {
            addCriterion("ACCNUMFRMT >", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtGreaterThanOrEqualTo(String value) {
            addCriterion("ACCNUMFRMT >=", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtLessThan(String value) {
            addCriterion("ACCNUMFRMT <", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtLessThanOrEqualTo(String value) {
            addCriterion("ACCNUMFRMT <=", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtLike(String value) {
            addCriterion("ACCNUMFRMT like", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtNotLike(String value) {
            addCriterion("ACCNUMFRMT not like", value, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtIn(List<String> values) {
            addCriterion("ACCNUMFRMT in", values, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtNotIn(List<String> values) {
            addCriterion("ACCNUMFRMT not in", values, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtBetween(String value1, String value2) {
            addCriterion("ACCNUMFRMT between", value1, value2, "accnumfrmt");
            return (Criteria) this;
        }

        public Criteria andAccnumfrmtNotBetween(String value1, String value2) {
            addCriterion("ACCNUMFRMT not between", value1, value2, "accnumfrmt");
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