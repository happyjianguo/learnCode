package cn.yufu.posp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EdcTerminalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcTerminalExample() {
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

        public Criteria andTerminalStatIsNull() {
            addCriterion("TERMINAL_STAT is null");
            return (Criteria) this;
        }

        public Criteria andTerminalStatIsNotNull() {
            addCriterion("TERMINAL_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalStatEqualTo(String value) {
            addCriterion("TERMINAL_STAT =", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatNotEqualTo(String value) {
            addCriterion("TERMINAL_STAT <>", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatGreaterThan(String value) {
            addCriterion("TERMINAL_STAT >", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAL_STAT >=", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatLessThan(String value) {
            addCriterion("TERMINAL_STAT <", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatLessThanOrEqualTo(String value) {
            addCriterion("TERMINAL_STAT <=", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatLike(String value) {
            addCriterion("TERMINAL_STAT like", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatNotLike(String value) {
            addCriterion("TERMINAL_STAT not like", value, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatIn(List<String> values) {
            addCriterion("TERMINAL_STAT in", values, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatNotIn(List<String> values) {
            addCriterion("TERMINAL_STAT not in", values, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatBetween(String value1, String value2) {
            addCriterion("TERMINAL_STAT between", value1, value2, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andTerminalStatNotBetween(String value1, String value2) {
            addCriterion("TERMINAL_STAT not between", value1, value2, "terminalStat");
            return (Criteria) this;
        }

        public Criteria andEdcTypeIsNull() {
            addCriterion("EDC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEdcTypeIsNotNull() {
            addCriterion("EDC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEdcTypeEqualTo(String value) {
            addCriterion("EDC_TYPE =", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeNotEqualTo(String value) {
            addCriterion("EDC_TYPE <>", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeGreaterThan(String value) {
            addCriterion("EDC_TYPE >", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EDC_TYPE >=", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeLessThan(String value) {
            addCriterion("EDC_TYPE <", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeLessThanOrEqualTo(String value) {
            addCriterion("EDC_TYPE <=", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeLike(String value) {
            addCriterion("EDC_TYPE like", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeNotLike(String value) {
            addCriterion("EDC_TYPE not like", value, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeIn(List<String> values) {
            addCriterion("EDC_TYPE in", values, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeNotIn(List<String> values) {
            addCriterion("EDC_TYPE not in", values, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeBetween(String value1, String value2) {
            addCriterion("EDC_TYPE between", value1, value2, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcTypeNotBetween(String value1, String value2) {
            addCriterion("EDC_TYPE not between", value1, value2, "edcType");
            return (Criteria) this;
        }

        public Criteria andEdcDocIsNull() {
            addCriterion("EDC_DOC is null");
            return (Criteria) this;
        }

        public Criteria andEdcDocIsNotNull() {
            addCriterion("EDC_DOC is not null");
            return (Criteria) this;
        }

        public Criteria andEdcDocEqualTo(String value) {
            addCriterion("EDC_DOC =", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocNotEqualTo(String value) {
            addCriterion("EDC_DOC <>", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocGreaterThan(String value) {
            addCriterion("EDC_DOC >", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocGreaterThanOrEqualTo(String value) {
            addCriterion("EDC_DOC >=", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocLessThan(String value) {
            addCriterion("EDC_DOC <", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocLessThanOrEqualTo(String value) {
            addCriterion("EDC_DOC <=", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocLike(String value) {
            addCriterion("EDC_DOC like", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocNotLike(String value) {
            addCriterion("EDC_DOC not like", value, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocIn(List<String> values) {
            addCriterion("EDC_DOC in", values, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocNotIn(List<String> values) {
            addCriterion("EDC_DOC not in", values, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocBetween(String value1, String value2) {
            addCriterion("EDC_DOC between", value1, value2, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andEdcDocNotBetween(String value1, String value2) {
            addCriterion("EDC_DOC not between", value1, value2, "edcDoc");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeIsNull() {
            addCriterion("PRINTER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeIsNotNull() {
            addCriterion("PRINTER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeEqualTo(String value) {
            addCriterion("PRINTER_TYPE =", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeNotEqualTo(String value) {
            addCriterion("PRINTER_TYPE <>", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeGreaterThan(String value) {
            addCriterion("PRINTER_TYPE >", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRINTER_TYPE >=", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeLessThan(String value) {
            addCriterion("PRINTER_TYPE <", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeLessThanOrEqualTo(String value) {
            addCriterion("PRINTER_TYPE <=", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeLike(String value) {
            addCriterion("PRINTER_TYPE like", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeNotLike(String value) {
            addCriterion("PRINTER_TYPE not like", value, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeIn(List<String> values) {
            addCriterion("PRINTER_TYPE in", values, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeNotIn(List<String> values) {
            addCriterion("PRINTER_TYPE not in", values, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeBetween(String value1, String value2) {
            addCriterion("PRINTER_TYPE between", value1, value2, "printerType");
            return (Criteria) this;
        }

        public Criteria andPrinterTypeNotBetween(String value1, String value2) {
            addCriterion("PRINTER_TYPE not between", value1, value2, "printerType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeIsNull() {
            addCriterion("PINPAD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeIsNotNull() {
            addCriterion("PINPAD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeEqualTo(String value) {
            addCriterion("PINPAD_TYPE =", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeNotEqualTo(String value) {
            addCriterion("PINPAD_TYPE <>", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeGreaterThan(String value) {
            addCriterion("PINPAD_TYPE >", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PINPAD_TYPE >=", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeLessThan(String value) {
            addCriterion("PINPAD_TYPE <", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeLessThanOrEqualTo(String value) {
            addCriterion("PINPAD_TYPE <=", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeLike(String value) {
            addCriterion("PINPAD_TYPE like", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeNotLike(String value) {
            addCriterion("PINPAD_TYPE not like", value, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeIn(List<String> values) {
            addCriterion("PINPAD_TYPE in", values, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeNotIn(List<String> values) {
            addCriterion("PINPAD_TYPE not in", values, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeBetween(String value1, String value2) {
            addCriterion("PINPAD_TYPE between", value1, value2, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andPinpadTypeNotBetween(String value1, String value2) {
            addCriterion("PINPAD_TYPE not between", value1, value2, "pinpadType");
            return (Criteria) this;
        }

        public Criteria andSoftVerIsNull() {
            addCriterion("SOFT_VER is null");
            return (Criteria) this;
        }

        public Criteria andSoftVerIsNotNull() {
            addCriterion("SOFT_VER is not null");
            return (Criteria) this;
        }

        public Criteria andSoftVerEqualTo(String value) {
            addCriterion("SOFT_VER =", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotEqualTo(String value) {
            addCriterion("SOFT_VER <>", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerGreaterThan(String value) {
            addCriterion("SOFT_VER >", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerGreaterThanOrEqualTo(String value) {
            addCriterion("SOFT_VER >=", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLessThan(String value) {
            addCriterion("SOFT_VER <", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLessThanOrEqualTo(String value) {
            addCriterion("SOFT_VER <=", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLike(String value) {
            addCriterion("SOFT_VER like", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotLike(String value) {
            addCriterion("SOFT_VER not like", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerIn(List<String> values) {
            addCriterion("SOFT_VER in", values, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotIn(List<String> values) {
            addCriterion("SOFT_VER not in", values, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerBetween(String value1, String value2) {
            addCriterion("SOFT_VER between", value1, value2, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotBetween(String value1, String value2) {
            addCriterion("SOFT_VER not between", value1, value2, "softVer");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagIsNull() {
            addCriterion("DOWNLOAD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagIsNotNull() {
            addCriterion("DOWNLOAD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagEqualTo(String value) {
            addCriterion("DOWNLOAD_FLAG =", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagNotEqualTo(String value) {
            addCriterion("DOWNLOAD_FLAG <>", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagGreaterThan(String value) {
            addCriterion("DOWNLOAD_FLAG >", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DOWNLOAD_FLAG >=", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagLessThan(String value) {
            addCriterion("DOWNLOAD_FLAG <", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagLessThanOrEqualTo(String value) {
            addCriterion("DOWNLOAD_FLAG <=", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagLike(String value) {
            addCriterion("DOWNLOAD_FLAG like", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagNotLike(String value) {
            addCriterion("DOWNLOAD_FLAG not like", value, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagIn(List<String> values) {
            addCriterion("DOWNLOAD_FLAG in", values, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagNotIn(List<String> values) {
            addCriterion("DOWNLOAD_FLAG not in", values, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagBetween(String value1, String value2) {
            addCriterion("DOWNLOAD_FLAG between", value1, value2, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadFlagNotBetween(String value1, String value2) {
            addCriterion("DOWNLOAD_FLAG not between", value1, value2, "downloadFlag");
            return (Criteria) this;
        }

        public Criteria andDownloadModeIsNull() {
            addCriterion("DOWNLOAD_MODE is null");
            return (Criteria) this;
        }

        public Criteria andDownloadModeIsNotNull() {
            addCriterion("DOWNLOAD_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadModeEqualTo(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE =", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeNotEqualTo(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE <>", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeGreaterThan(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE >", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE >=", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeLessThan(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE <", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DOWNLOAD_MODE <=", value, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeIn(List<BigDecimal> values) {
            addCriterion("DOWNLOAD_MODE in", values, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeNotIn(List<BigDecimal> values) {
            addCriterion("DOWNLOAD_MODE not in", values, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DOWNLOAD_MODE between", value1, value2, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andDownloadModeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DOWNLOAD_MODE not between", value1, value2, "downloadMode");
            return (Criteria) this;
        }

        public Criteria andSetDateIsNull() {
            addCriterion("SET_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSetDateIsNotNull() {
            addCriterion("SET_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSetDateEqualTo(String value) {
            addCriterion("SET_DATE =", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateNotEqualTo(String value) {
            addCriterion("SET_DATE <>", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateGreaterThan(String value) {
            addCriterion("SET_DATE >", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateGreaterThanOrEqualTo(String value) {
            addCriterion("SET_DATE >=", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateLessThan(String value) {
            addCriterion("SET_DATE <", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateLessThanOrEqualTo(String value) {
            addCriterion("SET_DATE <=", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateLike(String value) {
            addCriterion("SET_DATE like", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateNotLike(String value) {
            addCriterion("SET_DATE not like", value, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateIn(List<String> values) {
            addCriterion("SET_DATE in", values, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateNotIn(List<String> values) {
            addCriterion("SET_DATE not in", values, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateBetween(String value1, String value2) {
            addCriterion("SET_DATE between", value1, value2, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetDateNotBetween(String value1, String value2) {
            addCriterion("SET_DATE not between", value1, value2, "setDate");
            return (Criteria) this;
        }

        public Criteria andSetAddrIsNull() {
            addCriterion("SET_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andSetAddrIsNotNull() {
            addCriterion("SET_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andSetAddrEqualTo(String value) {
            addCriterion("SET_ADDR =", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrNotEqualTo(String value) {
            addCriterion("SET_ADDR <>", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrGreaterThan(String value) {
            addCriterion("SET_ADDR >", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrGreaterThanOrEqualTo(String value) {
            addCriterion("SET_ADDR >=", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrLessThan(String value) {
            addCriterion("SET_ADDR <", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrLessThanOrEqualTo(String value) {
            addCriterion("SET_ADDR <=", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrLike(String value) {
            addCriterion("SET_ADDR like", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrNotLike(String value) {
            addCriterion("SET_ADDR not like", value, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrIn(List<String> values) {
            addCriterion("SET_ADDR in", values, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrNotIn(List<String> values) {
            addCriterion("SET_ADDR not in", values, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrBetween(String value1, String value2) {
            addCriterion("SET_ADDR between", value1, value2, "setAddr");
            return (Criteria) this;
        }

        public Criteria andSetAddrNotBetween(String value1, String value2) {
            addCriterion("SET_ADDR not between", value1, value2, "setAddr");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIsNull() {
            addCriterion("UPDATE_OPER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIsNotNull() {
            addCriterion("UPDATE_OPER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperEqualTo(String value) {
            addCriterion("UPDATE_OPER =", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotEqualTo(String value) {
            addCriterion("UPDATE_OPER <>", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperGreaterThan(String value) {
            addCriterion("UPDATE_OPER >", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER >=", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLessThan(String value) {
            addCriterion("UPDATE_OPER <", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER <=", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperLike(String value) {
            addCriterion("UPDATE_OPER like", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotLike(String value) {
            addCriterion("UPDATE_OPER not like", value, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIn(List<String> values) {
            addCriterion("UPDATE_OPER in", values, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotIn(List<String> values) {
            addCriterion("UPDATE_OPER not in", values, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER between", value1, value2, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateOperNotBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER not between", value1, value2, "updateOper");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(String value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(String value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(String value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(String value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLike(String value) {
            addCriterion("UPDATE_DATE like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotLike(String value) {
            addCriterion("UPDATE_DATE not like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<String> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<String> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(String value1, String value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(String value1, String value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("UPDATE_TIME like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("UPDATE_TIME not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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