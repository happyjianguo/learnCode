package cn.yufu.posp.entity;

import java.util.ArrayList;
import java.util.List;

public class MerchantExtraExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantExtraExample() {
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

        public Criteria andMerchantIdIsNull() {
            addCriterion("MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(String value) {
            addCriterion("MERCHANT_ID =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(String value) {
            addCriterion("MERCHANT_ID <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(String value) {
            addCriterion("MERCHANT_ID >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ID >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(String value) {
            addCriterion("MERCHANT_ID <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ID <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLike(String value) {
            addCriterion("MERCHANT_ID like", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotLike(String value) {
            addCriterion("MERCHANT_ID not like", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<String> values) {
            addCriterion("MERCHANT_ID in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<String> values) {
            addCriterion("MERCHANT_ID not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(String value1, String value2) {
            addCriterion("MERCHANT_ID between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_ID not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andSndNameIsNull() {
            addCriterion("SND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSndNameIsNotNull() {
            addCriterion("SND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSndNameEqualTo(String value) {
            addCriterion("SND_NAME =", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameNotEqualTo(String value) {
            addCriterion("SND_NAME <>", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameGreaterThan(String value) {
            addCriterion("SND_NAME >", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameGreaterThanOrEqualTo(String value) {
            addCriterion("SND_NAME >=", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameLessThan(String value) {
            addCriterion("SND_NAME <", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameLessThanOrEqualTo(String value) {
            addCriterion("SND_NAME <=", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameLike(String value) {
            addCriterion("SND_NAME like", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameNotLike(String value) {
            addCriterion("SND_NAME not like", value, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameIn(List<String> values) {
            addCriterion("SND_NAME in", values, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameNotIn(List<String> values) {
            addCriterion("SND_NAME not in", values, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameBetween(String value1, String value2) {
            addCriterion("SND_NAME between", value1, value2, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndNameNotBetween(String value1, String value2) {
            addCriterion("SND_NAME not between", value1, value2, "sndName");
            return (Criteria) this;
        }

        public Criteria andSndAcctIsNull() {
            addCriterion("SND_ACCT is null");
            return (Criteria) this;
        }

        public Criteria andSndAcctIsNotNull() {
            addCriterion("SND_ACCT is not null");
            return (Criteria) this;
        }

        public Criteria andSndAcctEqualTo(String value) {
            addCriterion("SND_ACCT =", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctNotEqualTo(String value) {
            addCriterion("SND_ACCT <>", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctGreaterThan(String value) {
            addCriterion("SND_ACCT >", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctGreaterThanOrEqualTo(String value) {
            addCriterion("SND_ACCT >=", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctLessThan(String value) {
            addCriterion("SND_ACCT <", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctLessThanOrEqualTo(String value) {
            addCriterion("SND_ACCT <=", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctLike(String value) {
            addCriterion("SND_ACCT like", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctNotLike(String value) {
            addCriterion("SND_ACCT not like", value, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctIn(List<String> values) {
            addCriterion("SND_ACCT in", values, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctNotIn(List<String> values) {
            addCriterion("SND_ACCT not in", values, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctBetween(String value1, String value2) {
            addCriterion("SND_ACCT between", value1, value2, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndAcctNotBetween(String value1, String value2) {
            addCriterion("SND_ACCT not between", value1, value2, "sndAcct");
            return (Criteria) this;
        }

        public Criteria andSndBankIsNull() {
            addCriterion("SND_BANK is null");
            return (Criteria) this;
        }

        public Criteria andSndBankIsNotNull() {
            addCriterion("SND_BANK is not null");
            return (Criteria) this;
        }

        public Criteria andSndBankEqualTo(String value) {
            addCriterion("SND_BANK =", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankNotEqualTo(String value) {
            addCriterion("SND_BANK <>", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankGreaterThan(String value) {
            addCriterion("SND_BANK >", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankGreaterThanOrEqualTo(String value) {
            addCriterion("SND_BANK >=", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankLessThan(String value) {
            addCriterion("SND_BANK <", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankLessThanOrEqualTo(String value) {
            addCriterion("SND_BANK <=", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankLike(String value) {
            addCriterion("SND_BANK like", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankNotLike(String value) {
            addCriterion("SND_BANK not like", value, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankIn(List<String> values) {
            addCriterion("SND_BANK in", values, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankNotIn(List<String> values) {
            addCriterion("SND_BANK not in", values, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankBetween(String value1, String value2) {
            addCriterion("SND_BANK between", value1, value2, "sndBank");
            return (Criteria) this;
        }

        public Criteria andSndBankNotBetween(String value1, String value2) {
            addCriterion("SND_BANK not between", value1, value2, "sndBank");
            return (Criteria) this;
        }

        public Criteria andRcvNameIsNull() {
            addCriterion("RCV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRcvNameIsNotNull() {
            addCriterion("RCV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRcvNameEqualTo(String value) {
            addCriterion("RCV_NAME =", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameNotEqualTo(String value) {
            addCriterion("RCV_NAME <>", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameGreaterThan(String value) {
            addCriterion("RCV_NAME >", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameGreaterThanOrEqualTo(String value) {
            addCriterion("RCV_NAME >=", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameLessThan(String value) {
            addCriterion("RCV_NAME <", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameLessThanOrEqualTo(String value) {
            addCriterion("RCV_NAME <=", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameLike(String value) {
            addCriterion("RCV_NAME like", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameNotLike(String value) {
            addCriterion("RCV_NAME not like", value, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameIn(List<String> values) {
            addCriterion("RCV_NAME in", values, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameNotIn(List<String> values) {
            addCriterion("RCV_NAME not in", values, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameBetween(String value1, String value2) {
            addCriterion("RCV_NAME between", value1, value2, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvNameNotBetween(String value1, String value2) {
            addCriterion("RCV_NAME not between", value1, value2, "rcvName");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1IsNull() {
            addCriterion("RCV_ACCT1 is null");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1IsNotNull() {
            addCriterion("RCV_ACCT1 is not null");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1EqualTo(String value) {
            addCriterion("RCV_ACCT1 =", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1NotEqualTo(String value) {
            addCriterion("RCV_ACCT1 <>", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1GreaterThan(String value) {
            addCriterion("RCV_ACCT1 >", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1GreaterThanOrEqualTo(String value) {
            addCriterion("RCV_ACCT1 >=", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1LessThan(String value) {
            addCriterion("RCV_ACCT1 <", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1LessThanOrEqualTo(String value) {
            addCriterion("RCV_ACCT1 <=", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1Like(String value) {
            addCriterion("RCV_ACCT1 like", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1NotLike(String value) {
            addCriterion("RCV_ACCT1 not like", value, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1In(List<String> values) {
            addCriterion("RCV_ACCT1 in", values, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1NotIn(List<String> values) {
            addCriterion("RCV_ACCT1 not in", values, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1Between(String value1, String value2) {
            addCriterion("RCV_ACCT1 between", value1, value2, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct1NotBetween(String value1, String value2) {
            addCriterion("RCV_ACCT1 not between", value1, value2, "rcvAcct1");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2IsNull() {
            addCriterion("RCV_ACCT2 is null");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2IsNotNull() {
            addCriterion("RCV_ACCT2 is not null");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2EqualTo(String value) {
            addCriterion("RCV_ACCT2 =", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2NotEqualTo(String value) {
            addCriterion("RCV_ACCT2 <>", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2GreaterThan(String value) {
            addCriterion("RCV_ACCT2 >", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2GreaterThanOrEqualTo(String value) {
            addCriterion("RCV_ACCT2 >=", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2LessThan(String value) {
            addCriterion("RCV_ACCT2 <", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2LessThanOrEqualTo(String value) {
            addCriterion("RCV_ACCT2 <=", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2Like(String value) {
            addCriterion("RCV_ACCT2 like", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2NotLike(String value) {
            addCriterion("RCV_ACCT2 not like", value, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2In(List<String> values) {
            addCriterion("RCV_ACCT2 in", values, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2NotIn(List<String> values) {
            addCriterion("RCV_ACCT2 not in", values, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2Between(String value1, String value2) {
            addCriterion("RCV_ACCT2 between", value1, value2, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvAcct2NotBetween(String value1, String value2) {
            addCriterion("RCV_ACCT2 not between", value1, value2, "rcvAcct2");
            return (Criteria) this;
        }

        public Criteria andRcvBankIsNull() {
            addCriterion("RCV_BANK is null");
            return (Criteria) this;
        }

        public Criteria andRcvBankIsNotNull() {
            addCriterion("RCV_BANK is not null");
            return (Criteria) this;
        }

        public Criteria andRcvBankEqualTo(String value) {
            addCriterion("RCV_BANK =", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankNotEqualTo(String value) {
            addCriterion("RCV_BANK <>", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankGreaterThan(String value) {
            addCriterion("RCV_BANK >", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankGreaterThanOrEqualTo(String value) {
            addCriterion("RCV_BANK >=", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankLessThan(String value) {
            addCriterion("RCV_BANK <", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankLessThanOrEqualTo(String value) {
            addCriterion("RCV_BANK <=", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankLike(String value) {
            addCriterion("RCV_BANK like", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankNotLike(String value) {
            addCriterion("RCV_BANK not like", value, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankIn(List<String> values) {
            addCriterion("RCV_BANK in", values, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankNotIn(List<String> values) {
            addCriterion("RCV_BANK not in", values, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankBetween(String value1, String value2) {
            addCriterion("RCV_BANK between", value1, value2, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andRcvBankNotBetween(String value1, String value2) {
            addCriterion("RCV_BANK not between", value1, value2, "rcvBank");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgIsNull() {
            addCriterion("MCHT_STLM_FLG is null");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgIsNotNull() {
            addCriterion("MCHT_STLM_FLG is not null");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgEqualTo(String value) {
            addCriterion("MCHT_STLM_FLG =", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgNotEqualTo(String value) {
            addCriterion("MCHT_STLM_FLG <>", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgGreaterThan(String value) {
            addCriterion("MCHT_STLM_FLG >", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgGreaterThanOrEqualTo(String value) {
            addCriterion("MCHT_STLM_FLG >=", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgLessThan(String value) {
            addCriterion("MCHT_STLM_FLG <", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgLessThanOrEqualTo(String value) {
            addCriterion("MCHT_STLM_FLG <=", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgLike(String value) {
            addCriterion("MCHT_STLM_FLG like", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgNotLike(String value) {
            addCriterion("MCHT_STLM_FLG not like", value, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgIn(List<String> values) {
            addCriterion("MCHT_STLM_FLG in", values, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgNotIn(List<String> values) {
            addCriterion("MCHT_STLM_FLG not in", values, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgBetween(String value1, String value2) {
            addCriterion("MCHT_STLM_FLG between", value1, value2, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmFlgNotBetween(String value1, String value2) {
            addCriterion("MCHT_STLM_FLG not between", value1, value2, "mchtStlmFlg");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNIsNull() {
            addCriterion("MCHT_STLM_N is null");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNIsNotNull() {
            addCriterion("MCHT_STLM_N is not null");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNEqualTo(String value) {
            addCriterion("MCHT_STLM_N =", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNNotEqualTo(String value) {
            addCriterion("MCHT_STLM_N <>", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNGreaterThan(String value) {
            addCriterion("MCHT_STLM_N >", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNGreaterThanOrEqualTo(String value) {
            addCriterion("MCHT_STLM_N >=", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNLessThan(String value) {
            addCriterion("MCHT_STLM_N <", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNLessThanOrEqualTo(String value) {
            addCriterion("MCHT_STLM_N <=", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNLike(String value) {
            addCriterion("MCHT_STLM_N like", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNNotLike(String value) {
            addCriterion("MCHT_STLM_N not like", value, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNIn(List<String> values) {
            addCriterion("MCHT_STLM_N in", values, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNNotIn(List<String> values) {
            addCriterion("MCHT_STLM_N not in", values, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNBetween(String value1, String value2) {
            addCriterion("MCHT_STLM_N between", value1, value2, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtStlmNNotBetween(String value1, String value2) {
            addCriterion("MCHT_STLM_N not between", value1, value2, "mchtStlmN");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleIsNull() {
            addCriterion("MCHT_FEECYCLE is null");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleIsNotNull() {
            addCriterion("MCHT_FEECYCLE is not null");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleEqualTo(String value) {
            addCriterion("MCHT_FEECYCLE =", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleNotEqualTo(String value) {
            addCriterion("MCHT_FEECYCLE <>", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleGreaterThan(String value) {
            addCriterion("MCHT_FEECYCLE >", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleGreaterThanOrEqualTo(String value) {
            addCriterion("MCHT_FEECYCLE >=", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleLessThan(String value) {
            addCriterion("MCHT_FEECYCLE <", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleLessThanOrEqualTo(String value) {
            addCriterion("MCHT_FEECYCLE <=", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleLike(String value) {
            addCriterion("MCHT_FEECYCLE like", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleNotLike(String value) {
            addCriterion("MCHT_FEECYCLE not like", value, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleIn(List<String> values) {
            addCriterion("MCHT_FEECYCLE in", values, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleNotIn(List<String> values) {
            addCriterion("MCHT_FEECYCLE not in", values, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleBetween(String value1, String value2) {
            addCriterion("MCHT_FEECYCLE between", value1, value2, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeecycleNotBetween(String value1, String value2) {
            addCriterion("MCHT_FEECYCLE not between", value1, value2, "mchtFeecycle");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNIsNull() {
            addCriterion("MCHT_FEEDAY_N is null");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNIsNotNull() {
            addCriterion("MCHT_FEEDAY_N is not null");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNEqualTo(String value) {
            addCriterion("MCHT_FEEDAY_N =", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNNotEqualTo(String value) {
            addCriterion("MCHT_FEEDAY_N <>", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNGreaterThan(String value) {
            addCriterion("MCHT_FEEDAY_N >", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNGreaterThanOrEqualTo(String value) {
            addCriterion("MCHT_FEEDAY_N >=", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNLessThan(String value) {
            addCriterion("MCHT_FEEDAY_N <", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNLessThanOrEqualTo(String value) {
            addCriterion("MCHT_FEEDAY_N <=", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNLike(String value) {
            addCriterion("MCHT_FEEDAY_N like", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNNotLike(String value) {
            addCriterion("MCHT_FEEDAY_N not like", value, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNIn(List<String> values) {
            addCriterion("MCHT_FEEDAY_N in", values, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNNotIn(List<String> values) {
            addCriterion("MCHT_FEEDAY_N not in", values, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNBetween(String value1, String value2) {
            addCriterion("MCHT_FEEDAY_N between", value1, value2, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeedayNNotBetween(String value1, String value2) {
            addCriterion("MCHT_FEEDAY_N not between", value1, value2, "mchtFeedayN");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeIsNull() {
            addCriterion("MCHT_FEEMODE is null");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeIsNotNull() {
            addCriterion("MCHT_FEEMODE is not null");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeEqualTo(String value) {
            addCriterion("MCHT_FEEMODE =", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeNotEqualTo(String value) {
            addCriterion("MCHT_FEEMODE <>", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeGreaterThan(String value) {
            addCriterion("MCHT_FEEMODE >", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeGreaterThanOrEqualTo(String value) {
            addCriterion("MCHT_FEEMODE >=", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeLessThan(String value) {
            addCriterion("MCHT_FEEMODE <", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeLessThanOrEqualTo(String value) {
            addCriterion("MCHT_FEEMODE <=", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeLike(String value) {
            addCriterion("MCHT_FEEMODE like", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeNotLike(String value) {
            addCriterion("MCHT_FEEMODE not like", value, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeIn(List<String> values) {
            addCriterion("MCHT_FEEMODE in", values, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeNotIn(List<String> values) {
            addCriterion("MCHT_FEEMODE not in", values, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeBetween(String value1, String value2) {
            addCriterion("MCHT_FEEMODE between", value1, value2, "mchtFeemode");
            return (Criteria) this;
        }

        public Criteria andMchtFeemodeNotBetween(String value1, String value2) {
            addCriterion("MCHT_FEEMODE not between", value1, value2, "mchtFeemode");
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