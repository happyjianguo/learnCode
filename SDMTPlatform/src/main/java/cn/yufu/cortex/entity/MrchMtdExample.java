package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.List;

public class MrchMtdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MrchMtdExample() {
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

        public Criteria andBtchcntmtdIsNull() {
            addCriterion("BTCHCNTMTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdIsNotNull() {
            addCriterion("BTCHCNTMTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdEqualTo(Integer value) {
            addCriterion("BTCHCNTMTD =", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdNotEqualTo(Integer value) {
            addCriterion("BTCHCNTMTD <>", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdGreaterThan(Integer value) {
            addCriterion("BTCHCNTMTD >", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTMTD >=", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdLessThan(Integer value) {
            addCriterion("BTCHCNTMTD <", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdLessThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTMTD <=", value, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdIn(List<Integer> values) {
            addCriterion("BTCHCNTMTD in", values, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdNotIn(List<Integer> values) {
            addCriterion("BTCHCNTMTD not in", values, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTMTD between", value1, value2, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntmtdNotBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTMTD not between", value1, value2, "btchcntmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmIsNull() {
            addCriterion("BTCHCNTPM is null");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmIsNotNull() {
            addCriterion("BTCHCNTPM is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmEqualTo(Integer value) {
            addCriterion("BTCHCNTPM =", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmNotEqualTo(Integer value) {
            addCriterion("BTCHCNTPM <>", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmGreaterThan(Integer value) {
            addCriterion("BTCHCNTPM >", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmGreaterThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTPM >=", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmLessThan(Integer value) {
            addCriterion("BTCHCNTPM <", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmLessThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTPM <=", value, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmIn(List<Integer> values) {
            addCriterion("BTCHCNTPM in", values, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmNotIn(List<Integer> values) {
            addCriterion("BTCHCNTPM not in", values, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTPM between", value1, value2, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntpmNotBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTPM not between", value1, value2, "btchcntpm");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdIsNull() {
            addCriterion("BTCHCNTYTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdIsNotNull() {
            addCriterion("BTCHCNTYTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdEqualTo(Integer value) {
            addCriterion("BTCHCNTYTD =", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdNotEqualTo(Integer value) {
            addCriterion("BTCHCNTYTD <>", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdGreaterThan(Integer value) {
            addCriterion("BTCHCNTYTD >", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTYTD >=", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdLessThan(Integer value) {
            addCriterion("BTCHCNTYTD <", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdLessThanOrEqualTo(Integer value) {
            addCriterion("BTCHCNTYTD <=", value, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdIn(List<Integer> values) {
            addCriterion("BTCHCNTYTD in", values, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdNotIn(List<Integer> values) {
            addCriterion("BTCHCNTYTD not in", values, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTYTD between", value1, value2, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchcntytdNotBetween(Integer value1, Integer value2) {
            addCriterion("BTCHCNTYTD not between", value1, value2, "btchcntytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdIsNull() {
            addCriterion("BTCHDRMTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdIsNotNull() {
            addCriterion("BTCHDRMTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdEqualTo(Double value) {
            addCriterion("BTCHDRMTD =", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdNotEqualTo(Double value) {
            addCriterion("BTCHDRMTD <>", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdGreaterThan(Double value) {
            addCriterion("BTCHDRMTD >", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHDRMTD >=", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdLessThan(Double value) {
            addCriterion("BTCHDRMTD <", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHDRMTD <=", value, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdIn(List<Double> values) {
            addCriterion("BTCHDRMTD in", values, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdNotIn(List<Double> values) {
            addCriterion("BTCHDRMTD not in", values, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdBetween(Double value1, Double value2) {
            addCriterion("BTCHDRMTD between", value1, value2, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrmtdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHDRMTD not between", value1, value2, "btchdrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmIsNull() {
            addCriterion("BTCHDRPM is null");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmIsNotNull() {
            addCriterion("BTCHDRPM is not null");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmEqualTo(Double value) {
            addCriterion("BTCHDRPM =", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmNotEqualTo(Double value) {
            addCriterion("BTCHDRPM <>", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmGreaterThan(Double value) {
            addCriterion("BTCHDRPM >", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHDRPM >=", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmLessThan(Double value) {
            addCriterion("BTCHDRPM <", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmLessThanOrEqualTo(Double value) {
            addCriterion("BTCHDRPM <=", value, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmIn(List<Double> values) {
            addCriterion("BTCHDRPM in", values, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmNotIn(List<Double> values) {
            addCriterion("BTCHDRPM not in", values, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmBetween(Double value1, Double value2) {
            addCriterion("BTCHDRPM between", value1, value2, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrpmNotBetween(Double value1, Double value2) {
            addCriterion("BTCHDRPM not between", value1, value2, "btchdrpm");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdIsNull() {
            addCriterion("BTCHDRYTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdIsNotNull() {
            addCriterion("BTCHDRYTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdEqualTo(Double value) {
            addCriterion("BTCHDRYTD =", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdNotEqualTo(Double value) {
            addCriterion("BTCHDRYTD <>", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdGreaterThan(Double value) {
            addCriterion("BTCHDRYTD >", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHDRYTD >=", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdLessThan(Double value) {
            addCriterion("BTCHDRYTD <", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHDRYTD <=", value, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdIn(List<Double> values) {
            addCriterion("BTCHDRYTD in", values, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdNotIn(List<Double> values) {
            addCriterion("BTCHDRYTD not in", values, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdBetween(Double value1, Double value2) {
            addCriterion("BTCHDRYTD between", value1, value2, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchdrytdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHDRYTD not between", value1, value2, "btchdrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdIsNull() {
            addCriterion("BTCHCRMTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdIsNotNull() {
            addCriterion("BTCHCRMTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdEqualTo(Double value) {
            addCriterion("BTCHCRMTD =", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdNotEqualTo(Double value) {
            addCriterion("BTCHCRMTD <>", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdGreaterThan(Double value) {
            addCriterion("BTCHCRMTD >", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCRMTD >=", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdLessThan(Double value) {
            addCriterion("BTCHCRMTD <", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCRMTD <=", value, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdIn(List<Double> values) {
            addCriterion("BTCHCRMTD in", values, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdNotIn(List<Double> values) {
            addCriterion("BTCHCRMTD not in", values, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdBetween(Double value1, Double value2) {
            addCriterion("BTCHCRMTD between", value1, value2, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrmtdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCRMTD not between", value1, value2, "btchcrmtd");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmIsNull() {
            addCriterion("BTCHCRPM is null");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmIsNotNull() {
            addCriterion("BTCHCRPM is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmEqualTo(Double value) {
            addCriterion("BTCHCRPM =", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmNotEqualTo(Double value) {
            addCriterion("BTCHCRPM <>", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmGreaterThan(Double value) {
            addCriterion("BTCHCRPM >", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCRPM >=", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmLessThan(Double value) {
            addCriterion("BTCHCRPM <", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCRPM <=", value, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmIn(List<Double> values) {
            addCriterion("BTCHCRPM in", values, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmNotIn(List<Double> values) {
            addCriterion("BTCHCRPM not in", values, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmBetween(Double value1, Double value2) {
            addCriterion("BTCHCRPM between", value1, value2, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrpmNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCRPM not between", value1, value2, "btchcrpm");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdIsNull() {
            addCriterion("BTCHCRYTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdIsNotNull() {
            addCriterion("BTCHCRYTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdEqualTo(Double value) {
            addCriterion("BTCHCRYTD =", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdNotEqualTo(Double value) {
            addCriterion("BTCHCRYTD <>", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdGreaterThan(Double value) {
            addCriterion("BTCHCRYTD >", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCRYTD >=", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdLessThan(Double value) {
            addCriterion("BTCHCRYTD <", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCRYTD <=", value, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdIn(List<Double> values) {
            addCriterion("BTCHCRYTD in", values, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdNotIn(List<Double> values) {
            addCriterion("BTCHCRYTD not in", values, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdBetween(Double value1, Double value2) {
            addCriterion("BTCHCRYTD between", value1, value2, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcrytdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCRYTD not between", value1, value2, "btchcrytd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdIsNull() {
            addCriterion("BTCHCOMMTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdIsNotNull() {
            addCriterion("BTCHCOMMTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdEqualTo(Double value) {
            addCriterion("BTCHCOMMTD =", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdNotEqualTo(Double value) {
            addCriterion("BTCHCOMMTD <>", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdGreaterThan(Double value) {
            addCriterion("BTCHCOMMTD >", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMMTD >=", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdLessThan(Double value) {
            addCriterion("BTCHCOMMTD <", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMMTD <=", value, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdIn(List<Double> values) {
            addCriterion("BTCHCOMMTD in", values, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdNotIn(List<Double> values) {
            addCriterion("BTCHCOMMTD not in", values, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMMTD between", value1, value2, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcommtdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMMTD not between", value1, value2, "btchcommtd");
            return (Criteria) this;
        }

        public Criteria andBtchcompmIsNull() {
            addCriterion("BTCHCOMPM is null");
            return (Criteria) this;
        }

        public Criteria andBtchcompmIsNotNull() {
            addCriterion("BTCHCOMPM is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcompmEqualTo(Double value) {
            addCriterion("BTCHCOMPM =", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmNotEqualTo(Double value) {
            addCriterion("BTCHCOMPM <>", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmGreaterThan(Double value) {
            addCriterion("BTCHCOMPM >", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMPM >=", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmLessThan(Double value) {
            addCriterion("BTCHCOMPM <", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMPM <=", value, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmIn(List<Double> values) {
            addCriterion("BTCHCOMPM in", values, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmNotIn(List<Double> values) {
            addCriterion("BTCHCOMPM not in", values, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMPM between", value1, value2, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcompmNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMPM not between", value1, value2, "btchcompm");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdIsNull() {
            addCriterion("BTCHCOMYTD is null");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdIsNotNull() {
            addCriterion("BTCHCOMYTD is not null");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdEqualTo(Double value) {
            addCriterion("BTCHCOMYTD =", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdNotEqualTo(Double value) {
            addCriterion("BTCHCOMYTD <>", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdGreaterThan(Double value) {
            addCriterion("BTCHCOMYTD >", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdGreaterThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMYTD >=", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdLessThan(Double value) {
            addCriterion("BTCHCOMYTD <", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdLessThanOrEqualTo(Double value) {
            addCriterion("BTCHCOMYTD <=", value, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdIn(List<Double> values) {
            addCriterion("BTCHCOMYTD in", values, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdNotIn(List<Double> values) {
            addCriterion("BTCHCOMYTD not in", values, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMYTD between", value1, value2, "btchcomytd");
            return (Criteria) this;
        }

        public Criteria andBtchcomytdNotBetween(Double value1, Double value2) {
            addCriterion("BTCHCOMYTD not between", value1, value2, "btchcomytd");
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