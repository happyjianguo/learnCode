package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewTranRecordOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViewTranRecordOrgExample() {
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

        public Criteria andMerchantnumberIsNull() {
            addCriterion("MERCHANTNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIsNotNull() {
            addCriterion("MERCHANTNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberEqualTo(String value) {
            addCriterion("MERCHANTNUMBER =", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <>", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThan(String value) {
            addCriterion("MERCHANTNUMBER >", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER >=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThan(String value) {
            addCriterion("MERCHANTNUMBER <", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLike(String value) {
            addCriterion("MERCHANTNUMBER like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotLike(String value) {
            addCriterion("MERCHANTNUMBER not like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIn(List<String> values) {
            addCriterion("MERCHANTNUMBER in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotIn(List<String> values) {
            addCriterion("MERCHANTNUMBER not in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER not between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMrchtNameIsNull() {
            addCriterion("MRCHT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMrchtNameIsNotNull() {
            addCriterion("MRCHT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMrchtNameEqualTo(String value) {
            addCriterion("MRCHT_NAME =", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameNotEqualTo(String value) {
            addCriterion("MRCHT_NAME <>", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameGreaterThan(String value) {
            addCriterion("MRCHT_NAME >", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameGreaterThanOrEqualTo(String value) {
            addCriterion("MRCHT_NAME >=", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameLessThan(String value) {
            addCriterion("MRCHT_NAME <", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameLessThanOrEqualTo(String value) {
            addCriterion("MRCHT_NAME <=", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameLike(String value) {
            addCriterion("MRCHT_NAME like", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameNotLike(String value) {
            addCriterion("MRCHT_NAME not like", value, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameIn(List<String> values) {
            addCriterion("MRCHT_NAME in", values, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameNotIn(List<String> values) {
            addCriterion("MRCHT_NAME not in", values, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameBetween(String value1, String value2) {
            addCriterion("MRCHT_NAME between", value1, value2, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andMrchtNameNotBetween(String value1, String value2) {
            addCriterion("MRCHT_NAME not between", value1, value2, "mrchtName");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIsNull() {
            addCriterion("TRANSACTIONMONEY is null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIsNotNull() {
            addCriterion("TRANSACTIONMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY =", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <>", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY not in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY not between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNull() {
            addCriterion("TRANSACTIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNotNull() {
            addCriterion("TRANSACTIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateEqualTo(String value) {
            addCriterion("TRANSACTIONDATE =", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <>", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThan(String value) {
            addCriterion("TRANSACTIONDATE >", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE >=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThan(String value) {
            addCriterion("TRANSACTIONDATE <", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLike(String value) {
            addCriterion("TRANSACTIONDATE like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotLike(String value) {
            addCriterion("TRANSACTIONDATE not like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIn(List<String> values) {
            addCriterion("TRANSACTIONDATE in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotIn(List<String> values) {
            addCriterion("TRANSACTIONDATE not in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE between", value1, value2, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE not between", value1, value2, "transactiondate");
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

        public Criteria andPerfeeIsNull() {
            addCriterion("PERFEE is null");
            return (Criteria) this;
        }

        public Criteria andPerfeeIsNotNull() {
            addCriterion("PERFEE is not null");
            return (Criteria) this;
        }

        public Criteria andPerfeeEqualTo(BigDecimal value) {
            addCriterion("PERFEE =", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotEqualTo(BigDecimal value) {
            addCriterion("PERFEE <>", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeGreaterThan(BigDecimal value) {
            addCriterion("PERFEE >", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PERFEE >=", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeLessThan(BigDecimal value) {
            addCriterion("PERFEE <", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PERFEE <=", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeIn(List<BigDecimal> values) {
            addCriterion("PERFEE in", values, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotIn(List<BigDecimal> values) {
            addCriterion("PERFEE not in", values, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERFEE between", value1, value2, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERFEE not between", value1, value2, "perfee");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionIsNull() {
            addCriterion("FEE_RETENTION is null");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionIsNotNull() {
            addCriterion("FEE_RETENTION is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionEqualTo(BigDecimal value) {
            addCriterion("FEE_RETENTION =", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionNotEqualTo(BigDecimal value) {
            addCriterion("FEE_RETENTION <>", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionGreaterThan(BigDecimal value) {
            addCriterion("FEE_RETENTION >", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_RETENTION >=", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionLessThan(BigDecimal value) {
            addCriterion("FEE_RETENTION <", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_RETENTION <=", value, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionIn(List<BigDecimal> values) {
            addCriterion("FEE_RETENTION in", values, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionNotIn(List<BigDecimal> values) {
            addCriterion("FEE_RETENTION not in", values, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_RETENTION between", value1, value2, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andFeeRetentionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_RETENTION not between", value1, value2, "feeRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionIsNull() {
            addCriterion("AMT_RETENTION is null");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionIsNotNull() {
            addCriterion("AMT_RETENTION is not null");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionEqualTo(BigDecimal value) {
            addCriterion("AMT_RETENTION =", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionNotEqualTo(BigDecimal value) {
            addCriterion("AMT_RETENTION <>", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionGreaterThan(BigDecimal value) {
            addCriterion("AMT_RETENTION >", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMT_RETENTION >=", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionLessThan(BigDecimal value) {
            addCriterion("AMT_RETENTION <", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMT_RETENTION <=", value, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionIn(List<BigDecimal> values) {
            addCriterion("AMT_RETENTION in", values, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionNotIn(List<BigDecimal> values) {
            addCriterion("AMT_RETENTION not in", values, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMT_RETENTION between", value1, value2, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andAmtRetentionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMT_RETENTION not between", value1, value2, "amtRetention");
            return (Criteria) this;
        }

        public Criteria andOrgNoIsNull() {
            addCriterion("ORG_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrgNoIsNotNull() {
            addCriterion("ORG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNoEqualTo(String value) {
            addCriterion("ORG_NO =", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotEqualTo(String value) {
            addCriterion("ORG_NO <>", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoGreaterThan(String value) {
            addCriterion("ORG_NO >", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NO >=", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLessThan(String value) {
            addCriterion("ORG_NO <", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLessThanOrEqualTo(String value) {
            addCriterion("ORG_NO <=", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLike(String value) {
            addCriterion("ORG_NO like", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotLike(String value) {
            addCriterion("ORG_NO not like", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoIn(List<String> values) {
            addCriterion("ORG_NO in", values, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotIn(List<String> values) {
            addCriterion("ORG_NO not in", values, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoBetween(String value1, String value2) {
            addCriterion("ORG_NO between", value1, value2, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotBetween(String value1, String value2) {
            addCriterion("ORG_NO not between", value1, value2, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgBinIsNull() {
            addCriterion("ORG_BIN is null");
            return (Criteria) this;
        }

        public Criteria andOrgBinIsNotNull() {
            addCriterion("ORG_BIN is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBinEqualTo(String value) {
            addCriterion("ORG_BIN =", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinNotEqualTo(String value) {
            addCriterion("ORG_BIN <>", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinGreaterThan(String value) {
            addCriterion("ORG_BIN >", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BIN >=", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinLessThan(String value) {
            addCriterion("ORG_BIN <", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinLessThanOrEqualTo(String value) {
            addCriterion("ORG_BIN <=", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinLike(String value) {
            addCriterion("ORG_BIN like", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinNotLike(String value) {
            addCriterion("ORG_BIN not like", value, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinIn(List<String> values) {
            addCriterion("ORG_BIN in", values, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinNotIn(List<String> values) {
            addCriterion("ORG_BIN not in", values, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinBetween(String value1, String value2) {
            addCriterion("ORG_BIN between", value1, value2, "orgBin");
            return (Criteria) this;
        }

        public Criteria andOrgBinNotBetween(String value1, String value2) {
            addCriterion("ORG_BIN not between", value1, value2, "orgBin");
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