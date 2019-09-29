package cn.yufu.posp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EdcTerminalOrmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcTerminalOrmExample() {
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

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(BigDecimal value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(BigDecimal value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(BigDecimal value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(BigDecimal value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<BigDecimal> values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<BigDecimal> values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
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

        public Criteria andBankIdIsNull() {
            addCriterion("BANK_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNotNull() {
            addCriterion("BANK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankIdEqualTo(String value) {
            addCriterion("BANK_ID =", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotEqualTo(String value) {
            addCriterion("BANK_ID <>", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThan(String value) {
            addCriterion("BANK_ID >", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ID >=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThan(String value) {
            addCriterion("BANK_ID <", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_ID <=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLike(String value) {
            addCriterion("BANK_ID like", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotLike(String value) {
            addCriterion("BANK_ID not like", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdIn(List<String> values) {
            addCriterion("BANK_ID in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotIn(List<String> values) {
            addCriterion("BANK_ID not in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdBetween(String value1, String value2) {
            addCriterion("BANK_ID between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotBetween(String value1, String value2) {
            addCriterion("BANK_ID not between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdIsNull() {
            addCriterion("BANK_MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdIsNotNull() {
            addCriterion("BANK_MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdEqualTo(String value) {
            addCriterion("BANK_MERCHANT_ID =", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdNotEqualTo(String value) {
            addCriterion("BANK_MERCHANT_ID <>", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdGreaterThan(String value) {
            addCriterion("BANK_MERCHANT_ID >", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_MERCHANT_ID >=", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdLessThan(String value) {
            addCriterion("BANK_MERCHANT_ID <", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_MERCHANT_ID <=", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdLike(String value) {
            addCriterion("BANK_MERCHANT_ID like", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdNotLike(String value) {
            addCriterion("BANK_MERCHANT_ID not like", value, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdIn(List<String> values) {
            addCriterion("BANK_MERCHANT_ID in", values, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdNotIn(List<String> values) {
            addCriterion("BANK_MERCHANT_ID not in", values, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdBetween(String value1, String value2) {
            addCriterion("BANK_MERCHANT_ID between", value1, value2, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankMerchantIdNotBetween(String value1, String value2) {
            addCriterion("BANK_MERCHANT_ID not between", value1, value2, "bankMerchantId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdIsNull() {
            addCriterion("BANK_TERMINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdIsNotNull() {
            addCriterion("BANK_TERMINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdEqualTo(String value) {
            addCriterion("BANK_TERMINAL_ID =", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdNotEqualTo(String value) {
            addCriterion("BANK_TERMINAL_ID <>", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdGreaterThan(String value) {
            addCriterion("BANK_TERMINAL_ID >", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TERMINAL_ID >=", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdLessThan(String value) {
            addCriterion("BANK_TERMINAL_ID <", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_TERMINAL_ID <=", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdLike(String value) {
            addCriterion("BANK_TERMINAL_ID like", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdNotLike(String value) {
            addCriterion("BANK_TERMINAL_ID not like", value, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdIn(List<String> values) {
            addCriterion("BANK_TERMINAL_ID in", values, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdNotIn(List<String> values) {
            addCriterion("BANK_TERMINAL_ID not in", values, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdBetween(String value1, String value2) {
            addCriterion("BANK_TERMINAL_ID between", value1, value2, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andBankTerminalIdNotBetween(String value1, String value2) {
            addCriterion("BANK_TERMINAL_ID not between", value1, value2, "bankTerminalId");
            return (Criteria) this;
        }

        public Criteria andSysTraceIsNull() {
            addCriterion("SYS_TRACE is null");
            return (Criteria) this;
        }

        public Criteria andSysTraceIsNotNull() {
            addCriterion("SYS_TRACE is not null");
            return (Criteria) this;
        }

        public Criteria andSysTraceEqualTo(BigDecimal value) {
            addCriterion("SYS_TRACE =", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceNotEqualTo(BigDecimal value) {
            addCriterion("SYS_TRACE <>", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceGreaterThan(BigDecimal value) {
            addCriterion("SYS_TRACE >", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SYS_TRACE >=", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceLessThan(BigDecimal value) {
            addCriterion("SYS_TRACE <", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SYS_TRACE <=", value, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceIn(List<BigDecimal> values) {
            addCriterion("SYS_TRACE in", values, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceNotIn(List<BigDecimal> values) {
            addCriterion("SYS_TRACE not in", values, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SYS_TRACE between", value1, value2, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andSysTraceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SYS_TRACE not between", value1, value2, "sysTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceIsNull() {
            addCriterion("BANK_TRACE is null");
            return (Criteria) this;
        }

        public Criteria andBankTraceIsNotNull() {
            addCriterion("BANK_TRACE is not null");
            return (Criteria) this;
        }

        public Criteria andBankTraceEqualTo(BigDecimal value) {
            addCriterion("BANK_TRACE =", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceNotEqualTo(BigDecimal value) {
            addCriterion("BANK_TRACE <>", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceGreaterThan(BigDecimal value) {
            addCriterion("BANK_TRACE >", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BANK_TRACE >=", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceLessThan(BigDecimal value) {
            addCriterion("BANK_TRACE <", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BANK_TRACE <=", value, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceIn(List<BigDecimal> values) {
            addCriterion("BANK_TRACE in", values, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceNotIn(List<BigDecimal> values) {
            addCriterion("BANK_TRACE not in", values, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BANK_TRACE between", value1, value2, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andBankTraceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BANK_TRACE not between", value1, value2, "bankTrace");
            return (Criteria) this;
        }

        public Criteria andPinFmtIsNull() {
            addCriterion("PIN_FMT is null");
            return (Criteria) this;
        }

        public Criteria andPinFmtIsNotNull() {
            addCriterion("PIN_FMT is not null");
            return (Criteria) this;
        }

        public Criteria andPinFmtEqualTo(BigDecimal value) {
            addCriterion("PIN_FMT =", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtNotEqualTo(BigDecimal value) {
            addCriterion("PIN_FMT <>", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtGreaterThan(BigDecimal value) {
            addCriterion("PIN_FMT >", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PIN_FMT >=", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtLessThan(BigDecimal value) {
            addCriterion("PIN_FMT <", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PIN_FMT <=", value, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtIn(List<BigDecimal> values) {
            addCriterion("PIN_FMT in", values, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtNotIn(List<BigDecimal> values) {
            addCriterion("PIN_FMT not in", values, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PIN_FMT between", value1, value2, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andPinFmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PIN_FMT not between", value1, value2, "pinFmt");
            return (Criteria) this;
        }

        public Criteria andEncMethodIsNull() {
            addCriterion("ENC_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andEncMethodIsNotNull() {
            addCriterion("ENC_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andEncMethodEqualTo(BigDecimal value) {
            addCriterion("ENC_METHOD =", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodNotEqualTo(BigDecimal value) {
            addCriterion("ENC_METHOD <>", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodGreaterThan(BigDecimal value) {
            addCriterion("ENC_METHOD >", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENC_METHOD >=", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodLessThan(BigDecimal value) {
            addCriterion("ENC_METHOD <", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENC_METHOD <=", value, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodIn(List<BigDecimal> values) {
            addCriterion("ENC_METHOD in", values, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodNotIn(List<BigDecimal> values) {
            addCriterion("ENC_METHOD not in", values, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENC_METHOD between", value1, value2, "encMethod");
            return (Criteria) this;
        }

        public Criteria andEncMethodNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENC_METHOD not between", value1, value2, "encMethod");
            return (Criteria) this;
        }

        public Criteria andMacFlagIsNull() {
            addCriterion("MAC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andMacFlagIsNotNull() {
            addCriterion("MAC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andMacFlagEqualTo(BigDecimal value) {
            addCriterion("MAC_FLAG =", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagNotEqualTo(BigDecimal value) {
            addCriterion("MAC_FLAG <>", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagGreaterThan(BigDecimal value) {
            addCriterion("MAC_FLAG >", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MAC_FLAG >=", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagLessThan(BigDecimal value) {
            addCriterion("MAC_FLAG <", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MAC_FLAG <=", value, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagIn(List<BigDecimal> values) {
            addCriterion("MAC_FLAG in", values, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagNotIn(List<BigDecimal> values) {
            addCriterion("MAC_FLAG not in", values, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAC_FLAG between", value1, value2, "macFlag");
            return (Criteria) this;
        }

        public Criteria andMacFlagNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAC_FLAG not between", value1, value2, "macFlag");
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

        public Criteria andPikIsNull() {
            addCriterion("PIK is null");
            return (Criteria) this;
        }

        public Criteria andPikIsNotNull() {
            addCriterion("PIK is not null");
            return (Criteria) this;
        }

        public Criteria andPikEqualTo(String value) {
            addCriterion("PIK =", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikNotEqualTo(String value) {
            addCriterion("PIK <>", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikGreaterThan(String value) {
            addCriterion("PIK >", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikGreaterThanOrEqualTo(String value) {
            addCriterion("PIK >=", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikLessThan(String value) {
            addCriterion("PIK <", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikLessThanOrEqualTo(String value) {
            addCriterion("PIK <=", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikLike(String value) {
            addCriterion("PIK like", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikNotLike(String value) {
            addCriterion("PIK not like", value, "pik");
            return (Criteria) this;
        }

        public Criteria andPikIn(List<String> values) {
            addCriterion("PIK in", values, "pik");
            return (Criteria) this;
        }

        public Criteria andPikNotIn(List<String> values) {
            addCriterion("PIK not in", values, "pik");
            return (Criteria) this;
        }

        public Criteria andPikBetween(String value1, String value2) {
            addCriterion("PIK between", value1, value2, "pik");
            return (Criteria) this;
        }

        public Criteria andPikNotBetween(String value1, String value2) {
            addCriterion("PIK not between", value1, value2, "pik");
            return (Criteria) this;
        }

        public Criteria andMakIsNull() {
            addCriterion("MAK is null");
            return (Criteria) this;
        }

        public Criteria andMakIsNotNull() {
            addCriterion("MAK is not null");
            return (Criteria) this;
        }

        public Criteria andMakEqualTo(String value) {
            addCriterion("MAK =", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakNotEqualTo(String value) {
            addCriterion("MAK <>", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakGreaterThan(String value) {
            addCriterion("MAK >", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakGreaterThanOrEqualTo(String value) {
            addCriterion("MAK >=", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakLessThan(String value) {
            addCriterion("MAK <", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakLessThanOrEqualTo(String value) {
            addCriterion("MAK <=", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakLike(String value) {
            addCriterion("MAK like", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakNotLike(String value) {
            addCriterion("MAK not like", value, "mak");
            return (Criteria) this;
        }

        public Criteria andMakIn(List<String> values) {
            addCriterion("MAK in", values, "mak");
            return (Criteria) this;
        }

        public Criteria andMakNotIn(List<String> values) {
            addCriterion("MAK not in", values, "mak");
            return (Criteria) this;
        }

        public Criteria andMakBetween(String value1, String value2) {
            addCriterion("MAK between", value1, value2, "mak");
            return (Criteria) this;
        }

        public Criteria andMakNotBetween(String value1, String value2) {
            addCriterion("MAK not between", value1, value2, "mak");
            return (Criteria) this;
        }

        public Criteria andTmkIsNull() {
            addCriterion("TMK is null");
            return (Criteria) this;
        }

        public Criteria andTmkIsNotNull() {
            addCriterion("TMK is not null");
            return (Criteria) this;
        }

        public Criteria andTmkEqualTo(String value) {
            addCriterion("TMK =", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkNotEqualTo(String value) {
            addCriterion("TMK <>", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkGreaterThan(String value) {
            addCriterion("TMK >", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkGreaterThanOrEqualTo(String value) {
            addCriterion("TMK >=", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkLessThan(String value) {
            addCriterion("TMK <", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkLessThanOrEqualTo(String value) {
            addCriterion("TMK <=", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkLike(String value) {
            addCriterion("TMK like", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkNotLike(String value) {
            addCriterion("TMK not like", value, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkIn(List<String> values) {
            addCriterion("TMK in", values, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkNotIn(List<String> values) {
            addCriterion("TMK not in", values, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkBetween(String value1, String value2) {
            addCriterion("TMK between", value1, value2, "tmk");
            return (Criteria) this;
        }

        public Criteria andTmkNotBetween(String value1, String value2) {
            addCriterion("TMK not between", value1, value2, "tmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkIsNull() {
            addCriterion("PIK_TMK is null");
            return (Criteria) this;
        }

        public Criteria andPikTmkIsNotNull() {
            addCriterion("PIK_TMK is not null");
            return (Criteria) this;
        }

        public Criteria andPikTmkEqualTo(String value) {
            addCriterion("PIK_TMK =", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkNotEqualTo(String value) {
            addCriterion("PIK_TMK <>", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkGreaterThan(String value) {
            addCriterion("PIK_TMK >", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkGreaterThanOrEqualTo(String value) {
            addCriterion("PIK_TMK >=", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkLessThan(String value) {
            addCriterion("PIK_TMK <", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkLessThanOrEqualTo(String value) {
            addCriterion("PIK_TMK <=", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkLike(String value) {
            addCriterion("PIK_TMK like", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkNotLike(String value) {
            addCriterion("PIK_TMK not like", value, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkIn(List<String> values) {
            addCriterion("PIK_TMK in", values, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkNotIn(List<String> values) {
            addCriterion("PIK_TMK not in", values, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkBetween(String value1, String value2) {
            addCriterion("PIK_TMK between", value1, value2, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andPikTmkNotBetween(String value1, String value2) {
            addCriterion("PIK_TMK not between", value1, value2, "pikTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkIsNull() {
            addCriterion("MAK_TMK is null");
            return (Criteria) this;
        }

        public Criteria andMakTmkIsNotNull() {
            addCriterion("MAK_TMK is not null");
            return (Criteria) this;
        }

        public Criteria andMakTmkEqualTo(String value) {
            addCriterion("MAK_TMK =", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkNotEqualTo(String value) {
            addCriterion("MAK_TMK <>", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkGreaterThan(String value) {
            addCriterion("MAK_TMK >", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkGreaterThanOrEqualTo(String value) {
            addCriterion("MAK_TMK >=", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkLessThan(String value) {
            addCriterion("MAK_TMK <", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkLessThanOrEqualTo(String value) {
            addCriterion("MAK_TMK <=", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkLike(String value) {
            addCriterion("MAK_TMK like", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkNotLike(String value) {
            addCriterion("MAK_TMK not like", value, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkIn(List<String> values) {
            addCriterion("MAK_TMK in", values, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkNotIn(List<String> values) {
            addCriterion("MAK_TMK not in", values, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkBetween(String value1, String value2) {
            addCriterion("MAK_TMK between", value1, value2, "makTmk");
            return (Criteria) this;
        }

        public Criteria andMakTmkNotBetween(String value1, String value2) {
            addCriterion("MAK_TMK not between", value1, value2, "makTmk");
            return (Criteria) this;
        }

        public Criteria andKeyIndexIsNull() {
            addCriterion("KEY_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andKeyIndexIsNotNull() {
            addCriterion("KEY_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andKeyIndexEqualTo(String value) {
            addCriterion("KEY_INDEX =", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexNotEqualTo(String value) {
            addCriterion("KEY_INDEX <>", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexGreaterThan(String value) {
            addCriterion("KEY_INDEX >", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_INDEX >=", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexLessThan(String value) {
            addCriterion("KEY_INDEX <", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexLessThanOrEqualTo(String value) {
            addCriterion("KEY_INDEX <=", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexLike(String value) {
            addCriterion("KEY_INDEX like", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexNotLike(String value) {
            addCriterion("KEY_INDEX not like", value, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexIn(List<String> values) {
            addCriterion("KEY_INDEX in", values, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexNotIn(List<String> values) {
            addCriterion("KEY_INDEX not in", values, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexBetween(String value1, String value2) {
            addCriterion("KEY_INDEX between", value1, value2, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andKeyIndexNotBetween(String value1, String value2) {
            addCriterion("KEY_INDEX not between", value1, value2, "keyIndex");
            return (Criteria) this;
        }

        public Criteria andSettStatusIsNull() {
            addCriterion("SETT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSettStatusIsNotNull() {
            addCriterion("SETT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSettStatusEqualTo(BigDecimal value) {
            addCriterion("SETT_STATUS =", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusNotEqualTo(BigDecimal value) {
            addCriterion("SETT_STATUS <>", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusGreaterThan(BigDecimal value) {
            addCriterion("SETT_STATUS >", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SETT_STATUS >=", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusLessThan(BigDecimal value) {
            addCriterion("SETT_STATUS <", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SETT_STATUS <=", value, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusIn(List<BigDecimal> values) {
            addCriterion("SETT_STATUS in", values, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusNotIn(List<BigDecimal> values) {
            addCriterion("SETT_STATUS not in", values, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SETT_STATUS between", value1, value2, "settStatus");
            return (Criteria) this;
        }

        public Criteria andSettStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SETT_STATUS not between", value1, value2, "settStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusIsNull() {
            addCriterion("LOGON_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andLogonStatusIsNotNull() {
            addCriterion("LOGON_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andLogonStatusEqualTo(BigDecimal value) {
            addCriterion("LOGON_STATUS =", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusNotEqualTo(BigDecimal value) {
            addCriterion("LOGON_STATUS <>", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusGreaterThan(BigDecimal value) {
            addCriterion("LOGON_STATUS >", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGON_STATUS >=", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusLessThan(BigDecimal value) {
            addCriterion("LOGON_STATUS <", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGON_STATUS <=", value, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusIn(List<BigDecimal> values) {
            addCriterion("LOGON_STATUS in", values, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusNotIn(List<BigDecimal> values) {
            addCriterion("LOGON_STATUS not in", values, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGON_STATUS between", value1, value2, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andLogonStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGON_STATUS not between", value1, value2, "logonStatus");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(BigDecimal value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(BigDecimal value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(BigDecimal value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(BigDecimal value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<BigDecimal> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<BigDecimal> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
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