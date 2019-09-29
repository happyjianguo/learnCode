package cn.yufu.posp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BtsKeyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BtsKeyExample() {
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

        public Criteria andTerminalIdIsNull() {
            addCriterion("TERMINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIsNotNull() {
            addCriterion("TERMINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdEqualTo(String value) {
            addCriterion("TERMINAL_ID =", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotEqualTo(String value) {
            addCriterion("TERMINAL_ID <>", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThan(String value) {
            addCriterion("TERMINAL_ID >", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAL_ID >=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThan(String value) {
            addCriterion("TERMINAL_ID <", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThanOrEqualTo(String value) {
            addCriterion("TERMINAL_ID <=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLike(String value) {
            addCriterion("TERMINAL_ID like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotLike(String value) {
            addCriterion("TERMINAL_ID not like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIn(List<String> values) {
            addCriterion("TERMINAL_ID in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotIn(List<String> values) {
            addCriterion("TERMINAL_ID not in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdBetween(String value1, String value2) {
            addCriterion("TERMINAL_ID between", value1, value2, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotBetween(String value1, String value2) {
            addCriterion("TERMINAL_ID not between", value1, value2, "terminalId");
            return (Criteria) this;
        }

        public Criteria andOperNoIsNull() {
            addCriterion("OPER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOperNoIsNotNull() {
            addCriterion("OPER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOperNoEqualTo(String value) {
            addCriterion("OPER_NO =", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoNotEqualTo(String value) {
            addCriterion("OPER_NO <>", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoGreaterThan(String value) {
            addCriterion("OPER_NO >", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_NO >=", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoLessThan(String value) {
            addCriterion("OPER_NO <", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoLessThanOrEqualTo(String value) {
            addCriterion("OPER_NO <=", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoLike(String value) {
            addCriterion("OPER_NO like", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoNotLike(String value) {
            addCriterion("OPER_NO not like", value, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoIn(List<String> values) {
            addCriterion("OPER_NO in", values, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoNotIn(List<String> values) {
            addCriterion("OPER_NO not in", values, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoBetween(String value1, String value2) {
            addCriterion("OPER_NO between", value1, value2, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperNoNotBetween(String value1, String value2) {
            addCriterion("OPER_NO not between", value1, value2, "operNo");
            return (Criteria) this;
        }

        public Criteria andOperPasswdIsNull() {
            addCriterion("OPER_PASSWD is null");
            return (Criteria) this;
        }

        public Criteria andOperPasswdIsNotNull() {
            addCriterion("OPER_PASSWD is not null");
            return (Criteria) this;
        }

        public Criteria andOperPasswdEqualTo(String value) {
            addCriterion("OPER_PASSWD =", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdNotEqualTo(String value) {
            addCriterion("OPER_PASSWD <>", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdGreaterThan(String value) {
            addCriterion("OPER_PASSWD >", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_PASSWD >=", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdLessThan(String value) {
            addCriterion("OPER_PASSWD <", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdLessThanOrEqualTo(String value) {
            addCriterion("OPER_PASSWD <=", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdLike(String value) {
            addCriterion("OPER_PASSWD like", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdNotLike(String value) {
            addCriterion("OPER_PASSWD not like", value, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdIn(List<String> values) {
            addCriterion("OPER_PASSWD in", values, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdNotIn(List<String> values) {
            addCriterion("OPER_PASSWD not in", values, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdBetween(String value1, String value2) {
            addCriterion("OPER_PASSWD between", value1, value2, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andOperPasswdNotBetween(String value1, String value2) {
            addCriterion("OPER_PASSWD not between", value1, value2, "operPasswd");
            return (Criteria) this;
        }

        public Criteria andMasterKeyIsNull() {
            addCriterion("MASTER_KEY is null");
            return (Criteria) this;
        }

        public Criteria andMasterKeyIsNotNull() {
            addCriterion("MASTER_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andMasterKeyEqualTo(String value) {
            addCriterion("MASTER_KEY =", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyNotEqualTo(String value) {
            addCriterion("MASTER_KEY <>", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyGreaterThan(String value) {
            addCriterion("MASTER_KEY >", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_KEY >=", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyLessThan(String value) {
            addCriterion("MASTER_KEY <", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyLessThanOrEqualTo(String value) {
            addCriterion("MASTER_KEY <=", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyLike(String value) {
            addCriterion("MASTER_KEY like", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyNotLike(String value) {
            addCriterion("MASTER_KEY not like", value, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyIn(List<String> values) {
            addCriterion("MASTER_KEY in", values, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyNotIn(List<String> values) {
            addCriterion("MASTER_KEY not in", values, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyBetween(String value1, String value2) {
            addCriterion("MASTER_KEY between", value1, value2, "masterKey");
            return (Criteria) this;
        }

        public Criteria andMasterKeyNotBetween(String value1, String value2) {
            addCriterion("MASTER_KEY not between", value1, value2, "masterKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNull() {
            addCriterion("PIN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNotNull() {
            addCriterion("PIN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andPinKeyEqualTo(String value) {
            addCriterion("PIN_KEY =", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotEqualTo(String value) {
            addCriterion("PIN_KEY <>", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThan(String value) {
            addCriterion("PIN_KEY >", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PIN_KEY >=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThan(String value) {
            addCriterion("PIN_KEY <", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThanOrEqualTo(String value) {
            addCriterion("PIN_KEY <=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLike(String value) {
            addCriterion("PIN_KEY like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotLike(String value) {
            addCriterion("PIN_KEY not like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyIn(List<String> values) {
            addCriterion("PIN_KEY in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotIn(List<String> values) {
            addCriterion("PIN_KEY not in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyBetween(String value1, String value2) {
            addCriterion("PIN_KEY between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotBetween(String value1, String value2) {
            addCriterion("PIN_KEY not between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyIsNull() {
            addCriterion("MAC_KEY is null");
            return (Criteria) this;
        }

        public Criteria andMacKeyIsNotNull() {
            addCriterion("MAC_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andMacKeyEqualTo(String value) {
            addCriterion("MAC_KEY =", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotEqualTo(String value) {
            addCriterion("MAC_KEY <>", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyGreaterThan(String value) {
            addCriterion("MAC_KEY >", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MAC_KEY >=", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLessThan(String value) {
            addCriterion("MAC_KEY <", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLessThanOrEqualTo(String value) {
            addCriterion("MAC_KEY <=", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyLike(String value) {
            addCriterion("MAC_KEY like", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotLike(String value) {
            addCriterion("MAC_KEY not like", value, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyIn(List<String> values) {
            addCriterion("MAC_KEY in", values, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotIn(List<String> values) {
            addCriterion("MAC_KEY not in", values, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyBetween(String value1, String value2) {
            addCriterion("MAC_KEY between", value1, value2, "macKey");
            return (Criteria) this;
        }

        public Criteria andMacKeyNotBetween(String value1, String value2) {
            addCriterion("MAC_KEY not between", value1, value2, "macKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyIsNull() {
            addCriterion("POS_KEY is null");
            return (Criteria) this;
        }

        public Criteria andPosKeyIsNotNull() {
            addCriterion("POS_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andPosKeyEqualTo(String value) {
            addCriterion("POS_KEY =", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyNotEqualTo(String value) {
            addCriterion("POS_KEY <>", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyGreaterThan(String value) {
            addCriterion("POS_KEY >", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyGreaterThanOrEqualTo(String value) {
            addCriterion("POS_KEY >=", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyLessThan(String value) {
            addCriterion("POS_KEY <", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyLessThanOrEqualTo(String value) {
            addCriterion("POS_KEY <=", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyLike(String value) {
            addCriterion("POS_KEY like", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyNotLike(String value) {
            addCriterion("POS_KEY not like", value, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyIn(List<String> values) {
            addCriterion("POS_KEY in", values, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyNotIn(List<String> values) {
            addCriterion("POS_KEY not in", values, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyBetween(String value1, String value2) {
            addCriterion("POS_KEY between", value1, value2, "posKey");
            return (Criteria) this;
        }

        public Criteria andPosKeyNotBetween(String value1, String value2) {
            addCriterion("POS_KEY not between", value1, value2, "posKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyIsNull() {
            addCriterion("LOGON_KEY is null");
            return (Criteria) this;
        }

        public Criteria andLogonKeyIsNotNull() {
            addCriterion("LOGON_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andLogonKeyEqualTo(String value) {
            addCriterion("LOGON_KEY =", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyNotEqualTo(String value) {
            addCriterion("LOGON_KEY <>", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyGreaterThan(String value) {
            addCriterion("LOGON_KEY >", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyGreaterThanOrEqualTo(String value) {
            addCriterion("LOGON_KEY >=", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyLessThan(String value) {
            addCriterion("LOGON_KEY <", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyLessThanOrEqualTo(String value) {
            addCriterion("LOGON_KEY <=", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyLike(String value) {
            addCriterion("LOGON_KEY like", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyNotLike(String value) {
            addCriterion("LOGON_KEY not like", value, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyIn(List<String> values) {
            addCriterion("LOGON_KEY in", values, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyNotIn(List<String> values) {
            addCriterion("LOGON_KEY not in", values, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyBetween(String value1, String value2) {
            addCriterion("LOGON_KEY between", value1, value2, "logonKey");
            return (Criteria) this;
        }

        public Criteria andLogonKeyNotBetween(String value1, String value2) {
            addCriterion("LOGON_KEY not between", value1, value2, "logonKey");
            return (Criteria) this;
        }

        public Criteria andSettleFlagIsNull() {
            addCriterion("SETTLE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSettleFlagIsNotNull() {
            addCriterion("SETTLE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSettleFlagEqualTo(String value) {
            addCriterion("SETTLE_FLAG =", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagNotEqualTo(String value) {
            addCriterion("SETTLE_FLAG <>", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagGreaterThan(String value) {
            addCriterion("SETTLE_FLAG >", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLE_FLAG >=", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagLessThan(String value) {
            addCriterion("SETTLE_FLAG <", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagLessThanOrEqualTo(String value) {
            addCriterion("SETTLE_FLAG <=", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagLike(String value) {
            addCriterion("SETTLE_FLAG like", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagNotLike(String value) {
            addCriterion("SETTLE_FLAG not like", value, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagIn(List<String> values) {
            addCriterion("SETTLE_FLAG in", values, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagNotIn(List<String> values) {
            addCriterion("SETTLE_FLAG not in", values, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagBetween(String value1, String value2) {
            addCriterion("SETTLE_FLAG between", value1, value2, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleFlagNotBetween(String value1, String value2) {
            addCriterion("SETTLE_FLAG not between", value1, value2, "settleFlag");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("BATCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("BATCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(BigDecimal value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(BigDecimal value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(BigDecimal value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(BigDecimal value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<BigDecimal> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<BigDecimal> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyIsNull() {
            addCriterion("TMKMASTER_KEY is null");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyIsNotNull() {
            addCriterion("TMKMASTER_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyEqualTo(String value) {
            addCriterion("TMKMASTER_KEY =", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyNotEqualTo(String value) {
            addCriterion("TMKMASTER_KEY <>", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyGreaterThan(String value) {
            addCriterion("TMKMASTER_KEY >", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyGreaterThanOrEqualTo(String value) {
            addCriterion("TMKMASTER_KEY >=", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyLessThan(String value) {
            addCriterion("TMKMASTER_KEY <", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyLessThanOrEqualTo(String value) {
            addCriterion("TMKMASTER_KEY <=", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyLike(String value) {
            addCriterion("TMKMASTER_KEY like", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyNotLike(String value) {
            addCriterion("TMKMASTER_KEY not like", value, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyIn(List<String> values) {
            addCriterion("TMKMASTER_KEY in", values, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyNotIn(List<String> values) {
            addCriterion("TMKMASTER_KEY not in", values, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyBetween(String value1, String value2) {
            addCriterion("TMKMASTER_KEY between", value1, value2, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andTmkmasterKeyNotBetween(String value1, String value2) {
            addCriterion("TMKMASTER_KEY not between", value1, value2, "tmkmasterKey");
            return (Criteria) this;
        }

        public Criteria andLmkTdkIsNull() {
            addCriterion("LMK_TDK is null");
            return (Criteria) this;
        }

        public Criteria andLmkTdkIsNotNull() {
            addCriterion("LMK_TDK is not null");
            return (Criteria) this;
        }

        public Criteria andLmkTdkEqualTo(String value) {
            addCriterion("LMK_TDK =", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkNotEqualTo(String value) {
            addCriterion("LMK_TDK <>", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkGreaterThan(String value) {
            addCriterion("LMK_TDK >", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkGreaterThanOrEqualTo(String value) {
            addCriterion("LMK_TDK >=", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkLessThan(String value) {
            addCriterion("LMK_TDK <", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkLessThanOrEqualTo(String value) {
            addCriterion("LMK_TDK <=", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkLike(String value) {
            addCriterion("LMK_TDK like", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkNotLike(String value) {
            addCriterion("LMK_TDK not like", value, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkIn(List<String> values) {
            addCriterion("LMK_TDK in", values, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkNotIn(List<String> values) {
            addCriterion("LMK_TDK not in", values, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkBetween(String value1, String value2) {
            addCriterion("LMK_TDK between", value1, value2, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andLmkTdkNotBetween(String value1, String value2) {
            addCriterion("LMK_TDK not between", value1, value2, "lmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkIsNull() {
            addCriterion("TMK_TDK is null");
            return (Criteria) this;
        }

        public Criteria andTmkTdkIsNotNull() {
            addCriterion("TMK_TDK is not null");
            return (Criteria) this;
        }

        public Criteria andTmkTdkEqualTo(String value) {
            addCriterion("TMK_TDK =", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkNotEqualTo(String value) {
            addCriterion("TMK_TDK <>", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkGreaterThan(String value) {
            addCriterion("TMK_TDK >", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkGreaterThanOrEqualTo(String value) {
            addCriterion("TMK_TDK >=", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkLessThan(String value) {
            addCriterion("TMK_TDK <", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkLessThanOrEqualTo(String value) {
            addCriterion("TMK_TDK <=", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkLike(String value) {
            addCriterion("TMK_TDK like", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkNotLike(String value) {
            addCriterion("TMK_TDK not like", value, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkIn(List<String> values) {
            addCriterion("TMK_TDK in", values, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkNotIn(List<String> values) {
            addCriterion("TMK_TDK not in", values, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkBetween(String value1, String value2) {
            addCriterion("TMK_TDK between", value1, value2, "tmkTdk");
            return (Criteria) this;
        }

        public Criteria andTmkTdkNotBetween(String value1, String value2) {
            addCriterion("TMK_TDK not between", value1, value2, "tmkTdk");
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