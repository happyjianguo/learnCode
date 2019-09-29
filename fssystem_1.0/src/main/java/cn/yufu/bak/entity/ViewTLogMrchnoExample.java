package cn.yufu.bak.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewTLogMrchnoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViewTLogMrchnoExample() {
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

        public Criteria andPanIsNull() {
            addCriterion("PAN is null");
            return (Criteria) this;
        }

        public Criteria andPanIsNotNull() {
            addCriterion("PAN is not null");
            return (Criteria) this;
        }

        public Criteria andPanEqualTo(String value) {
            addCriterion("PAN =", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotEqualTo(String value) {
            addCriterion("PAN <>", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThan(String value) {
            addCriterion("PAN >", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThanOrEqualTo(String value) {
            addCriterion("PAN >=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThan(String value) {
            addCriterion("PAN <", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThanOrEqualTo(String value) {
            addCriterion("PAN <=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLike(String value) {
            addCriterion("PAN like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotLike(String value) {
            addCriterion("PAN not like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanIn(List<String> values) {
            addCriterion("PAN in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotIn(List<String> values) {
            addCriterion("PAN not in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanBetween(String value1, String value2) {
            addCriterion("PAN between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotBetween(String value1, String value2) {
            addCriterion("PAN not between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andStanIsNull() {
            addCriterion("STAN is null");
            return (Criteria) this;
        }

        public Criteria andStanIsNotNull() {
            addCriterion("STAN is not null");
            return (Criteria) this;
        }

        public Criteria andStanEqualTo(String value) {
            addCriterion("STAN =", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanNotEqualTo(String value) {
            addCriterion("STAN <>", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanGreaterThan(String value) {
            addCriterion("STAN >", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanGreaterThanOrEqualTo(String value) {
            addCriterion("STAN >=", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanLessThan(String value) {
            addCriterion("STAN <", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanLessThanOrEqualTo(String value) {
            addCriterion("STAN <=", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanLike(String value) {
            addCriterion("STAN like", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanNotLike(String value) {
            addCriterion("STAN not like", value, "stan");
            return (Criteria) this;
        }

        public Criteria andStanIn(List<String> values) {
            addCriterion("STAN in", values, "stan");
            return (Criteria) this;
        }

        public Criteria andStanNotIn(List<String> values) {
            addCriterion("STAN not in", values, "stan");
            return (Criteria) this;
        }

        public Criteria andStanBetween(String value1, String value2) {
            addCriterion("STAN between", value1, value2, "stan");
            return (Criteria) this;
        }

        public Criteria andStanNotBetween(String value1, String value2) {
            addCriterion("STAN not between", value1, value2, "stan");
            return (Criteria) this;
        }

        public Criteria andRrnIsNull() {
            addCriterion("RRN is null");
            return (Criteria) this;
        }

        public Criteria andRrnIsNotNull() {
            addCriterion("RRN is not null");
            return (Criteria) this;
        }

        public Criteria andRrnEqualTo(String value) {
            addCriterion("RRN =", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotEqualTo(String value) {
            addCriterion("RRN <>", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnGreaterThan(String value) {
            addCriterion("RRN >", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnGreaterThanOrEqualTo(String value) {
            addCriterion("RRN >=", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLessThan(String value) {
            addCriterion("RRN <", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLessThanOrEqualTo(String value) {
            addCriterion("RRN <=", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLike(String value) {
            addCriterion("RRN like", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotLike(String value) {
            addCriterion("RRN not like", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnIn(List<String> values) {
            addCriterion("RRN in", values, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotIn(List<String> values) {
            addCriterion("RRN not in", values, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnBetween(String value1, String value2) {
            addCriterion("RRN between", value1, value2, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotBetween(String value1, String value2) {
            addCriterion("RRN not between", value1, value2, "rrn");
            return (Criteria) this;
        }

        public Criteria andAmttxnIsNull() {
            addCriterion("AMTTXN is null");
            return (Criteria) this;
        }

        public Criteria andAmttxnIsNotNull() {
            addCriterion("AMTTXN is not null");
            return (Criteria) this;
        }

        public Criteria andAmttxnEqualTo(BigDecimal value) {
            addCriterion("AMTTXN =", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotEqualTo(BigDecimal value) {
            addCriterion("AMTTXN <>", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnGreaterThan(BigDecimal value) {
            addCriterion("AMTTXN >", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXN >=", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnLessThan(BigDecimal value) {
            addCriterion("AMTTXN <", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXN <=", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnIn(List<BigDecimal> values) {
            addCriterion("AMTTXN in", values, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotIn(List<BigDecimal> values) {
            addCriterion("AMTTXN not in", values, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXN between", value1, value2, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXN not between", value1, value2, "amttxn");
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

        public Criteria andTransactiontimeIsNull() {
            addCriterion("TRANSACTIONTIME is null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIsNotNull() {
            addCriterion("TRANSACTIONTIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeEqualTo(String value) {
            addCriterion("TRANSACTIONTIME =", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <>", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThan(String value) {
            addCriterion("TRANSACTIONTIME >", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME >=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThan(String value) {
            addCriterion("TRANSACTIONTIME <", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLike(String value) {
            addCriterion("TRANSACTIONTIME like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotLike(String value) {
            addCriterion("TRANSACTIONTIME not like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIn(List<String> values) {
            addCriterion("TRANSACTIONTIME in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotIn(List<String> values) {
            addCriterion("TRANSACTIONTIME not in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME between", value1, value2, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME not between", value1, value2, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIsNull() {
            addCriterion("TXNSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIsNotNull() {
            addCriterion("TXNSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTxnstatusEqualTo(BigDecimal value) {
            addCriterion("TXNSTATUS =", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotEqualTo(BigDecimal value) {
            addCriterion("TXNSTATUS <>", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusGreaterThan(BigDecimal value) {
            addCriterion("TXNSTATUS >", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNSTATUS >=", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusLessThan(BigDecimal value) {
            addCriterion("TXNSTATUS <", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNSTATUS <=", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIn(List<BigDecimal> values) {
            addCriterion("TXNSTATUS in", values, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotIn(List<BigDecimal> values) {
            addCriterion("TXNSTATUS not in", values, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNSTATUS between", value1, value2, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNSTATUS not between", value1, value2, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxncodeIsNull() {
            addCriterion("TXNCODE is null");
            return (Criteria) this;
        }

        public Criteria andTxncodeIsNotNull() {
            addCriterion("TXNCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTxncodeEqualTo(BigDecimal value) {
            addCriterion("TXNCODE =", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotEqualTo(BigDecimal value) {
            addCriterion("TXNCODE <>", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeGreaterThan(BigDecimal value) {
            addCriterion("TXNCODE >", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNCODE >=", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeLessThan(BigDecimal value) {
            addCriterion("TXNCODE <", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNCODE <=", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeIn(List<BigDecimal> values) {
            addCriterion("TXNCODE in", values, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotIn(List<BigDecimal> values) {
            addCriterion("TXNCODE not in", values, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNCODE between", value1, value2, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNCODE not between", value1, value2, "txncode");
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