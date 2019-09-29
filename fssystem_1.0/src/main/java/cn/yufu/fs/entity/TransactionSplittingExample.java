package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionSplittingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionSplittingExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andCardBinIsNull() {
            addCriterion("CARD_BIN is null");
            return (Criteria) this;
        }

        public Criteria andCardBinIsNotNull() {
            addCriterion("CARD_BIN is not null");
            return (Criteria) this;
        }

        public Criteria andCardBinEqualTo(String value) {
            addCriterion("CARD_BIN =", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotEqualTo(String value) {
            addCriterion("CARD_BIN <>", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinGreaterThan(String value) {
            addCriterion("CARD_BIN >", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_BIN >=", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLessThan(String value) {
            addCriterion("CARD_BIN <", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLessThanOrEqualTo(String value) {
            addCriterion("CARD_BIN <=", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLike(String value) {
            addCriterion("CARD_BIN like", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotLike(String value) {
            addCriterion("CARD_BIN not like", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinIn(List<String> values) {
            addCriterion("CARD_BIN in", values, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotIn(List<String> values) {
            addCriterion("CARD_BIN not in", values, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinBetween(String value1, String value2) {
            addCriterion("CARD_BIN between", value1, value2, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotBetween(String value1, String value2) {
            addCriterion("CARD_BIN not between", value1, value2, "cardBin");
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

        public Criteria andSplitAmtIsNull() {
            addCriterion("SPLIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andSplitAmtIsNotNull() {
            addCriterion("SPLIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andSplitAmtEqualTo(BigDecimal value) {
            addCriterion("SPLIT_AMT =", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtNotEqualTo(BigDecimal value) {
            addCriterion("SPLIT_AMT <>", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtGreaterThan(BigDecimal value) {
            addCriterion("SPLIT_AMT >", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SPLIT_AMT >=", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtLessThan(BigDecimal value) {
            addCriterion("SPLIT_AMT <", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SPLIT_AMT <=", value, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtIn(List<BigDecimal> values) {
            addCriterion("SPLIT_AMT in", values, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtNotIn(List<BigDecimal> values) {
            addCriterion("SPLIT_AMT not in", values, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SPLIT_AMT between", value1, value2, "splitAmt");
            return (Criteria) this;
        }

        public Criteria andSplitAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SPLIT_AMT not between", value1, value2, "splitAmt");
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

        public Criteria andComments1IsNull() {
            addCriterion("COMMENTS1 is null");
            return (Criteria) this;
        }

        public Criteria andComments1IsNotNull() {
            addCriterion("COMMENTS1 is not null");
            return (Criteria) this;
        }

        public Criteria andComments1EqualTo(String value) {
            addCriterion("COMMENTS1 =", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotEqualTo(String value) {
            addCriterion("COMMENTS1 <>", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThan(String value) {
            addCriterion("COMMENTS1 >", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 >=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThan(String value) {
            addCriterion("COMMENTS1 <", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 <=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Like(String value) {
            addCriterion("COMMENTS1 like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotLike(String value) {
            addCriterion("COMMENTS1 not like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1In(List<String> values) {
            addCriterion("COMMENTS1 in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotIn(List<String> values) {
            addCriterion("COMMENTS1 not in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Between(String value1, String value2) {
            addCriterion("COMMENTS1 between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotBetween(String value1, String value2) {
            addCriterion("COMMENTS1 not between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments2IsNull() {
            addCriterion("COMMENTS2 is null");
            return (Criteria) this;
        }

        public Criteria andComments2IsNotNull() {
            addCriterion("COMMENTS2 is not null");
            return (Criteria) this;
        }

        public Criteria andComments2EqualTo(String value) {
            addCriterion("COMMENTS2 =", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotEqualTo(String value) {
            addCriterion("COMMENTS2 <>", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThan(String value) {
            addCriterion("COMMENTS2 >", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 >=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThan(String value) {
            addCriterion("COMMENTS2 <", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 <=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Like(String value) {
            addCriterion("COMMENTS2 like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotLike(String value) {
            addCriterion("COMMENTS2 not like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2In(List<String> values) {
            addCriterion("COMMENTS2 in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotIn(List<String> values) {
            addCriterion("COMMENTS2 not in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Between(String value1, String value2) {
            addCriterion("COMMENTS2 between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotBetween(String value1, String value2) {
            addCriterion("COMMENTS2 not between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments3IsNull() {
            addCriterion("COMMENTS3 is null");
            return (Criteria) this;
        }

        public Criteria andComments3IsNotNull() {
            addCriterion("COMMENTS3 is not null");
            return (Criteria) this;
        }

        public Criteria andComments3EqualTo(String value) {
            addCriterion("COMMENTS3 =", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotEqualTo(String value) {
            addCriterion("COMMENTS3 <>", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3GreaterThan(String value) {
            addCriterion("COMMENTS3 >", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS3 >=", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3LessThan(String value) {
            addCriterion("COMMENTS3 <", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS3 <=", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3Like(String value) {
            addCriterion("COMMENTS3 like", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotLike(String value) {
            addCriterion("COMMENTS3 not like", value, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3In(List<String> values) {
            addCriterion("COMMENTS3 in", values, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotIn(List<String> values) {
            addCriterion("COMMENTS3 not in", values, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3Between(String value1, String value2) {
            addCriterion("COMMENTS3 between", value1, value2, "comments3");
            return (Criteria) this;
        }

        public Criteria andComments3NotBetween(String value1, String value2) {
            addCriterion("COMMENTS3 not between", value1, value2, "comments3");
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