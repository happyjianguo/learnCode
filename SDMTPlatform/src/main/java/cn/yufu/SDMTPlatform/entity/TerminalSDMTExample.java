package cn.yufu.SDMTPlatform.entity;

import java.util.ArrayList;
import java.util.List;

public class TerminalSDMTExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TerminalSDMTExample() {
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

        public Criteria andTermCodeIsNull() {
            addCriterion("TERM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTermCodeIsNotNull() {
            addCriterion("TERM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTermCodeEqualTo(String value) {
            addCriterion("TERM_CODE =", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotEqualTo(String value) {
            addCriterion("TERM_CODE <>", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThan(String value) {
            addCriterion("TERM_CODE >", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_CODE >=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThan(String value) {
            addCriterion("TERM_CODE <", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThanOrEqualTo(String value) {
            addCriterion("TERM_CODE <=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLike(String value) {
            addCriterion("TERM_CODE like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotLike(String value) {
            addCriterion("TERM_CODE not like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeIn(List<String> values) {
            addCriterion("TERM_CODE in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotIn(List<String> values) {
            addCriterion("TERM_CODE not in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeBetween(String value1, String value2) {
            addCriterion("TERM_CODE between", value1, value2, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotBetween(String value1, String value2) {
            addCriterion("TERM_CODE not between", value1, value2, "termCode");
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

        public Criteria andAddDateIsNull() {
            addCriterion("ADD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAddDateIsNotNull() {
            addCriterion("ADD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAddDateEqualTo(String value) {
            addCriterion("ADD_DATE =", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotEqualTo(String value) {
            addCriterion("ADD_DATE <>", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThan(String value) {
            addCriterion("ADD_DATE >", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThanOrEqualTo(String value) {
            addCriterion("ADD_DATE >=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThan(String value) {
            addCriterion("ADD_DATE <", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThanOrEqualTo(String value) {
            addCriterion("ADD_DATE <=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLike(String value) {
            addCriterion("ADD_DATE like", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotLike(String value) {
            addCriterion("ADD_DATE not like", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateIn(List<String> values) {
            addCriterion("ADD_DATE in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotIn(List<String> values) {
            addCriterion("ADD_DATE not in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateBetween(String value1, String value2) {
            addCriterion("ADD_DATE between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotBetween(String value1, String value2) {
            addCriterion("ADD_DATE not between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andTermAddressIsNull() {
            addCriterion("TERM_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andTermAddressIsNotNull() {
            addCriterion("TERM_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andTermAddressEqualTo(String value) {
            addCriterion("TERM_ADDRESS =", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressNotEqualTo(String value) {
            addCriterion("TERM_ADDRESS <>", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressGreaterThan(String value) {
            addCriterion("TERM_ADDRESS >", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_ADDRESS >=", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressLessThan(String value) {
            addCriterion("TERM_ADDRESS <", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressLessThanOrEqualTo(String value) {
            addCriterion("TERM_ADDRESS <=", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressLike(String value) {
            addCriterion("TERM_ADDRESS like", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressNotLike(String value) {
            addCriterion("TERM_ADDRESS not like", value, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressIn(List<String> values) {
            addCriterion("TERM_ADDRESS in", values, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressNotIn(List<String> values) {
            addCriterion("TERM_ADDRESS not in", values, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressBetween(String value1, String value2) {
            addCriterion("TERM_ADDRESS between", value1, value2, "termAddress");
            return (Criteria) this;
        }

        public Criteria andTermAddressNotBetween(String value1, String value2) {
            addCriterion("TERM_ADDRESS not between", value1, value2, "termAddress");
            return (Criteria) this;
        }

        public Criteria andActDateIsNull() {
            addCriterion("ACT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActDateIsNotNull() {
            addCriterion("ACT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActDateEqualTo(Integer value) {
            addCriterion("ACT_DATE =", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotEqualTo(Integer value) {
            addCriterion("ACT_DATE <>", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateGreaterThan(Integer value) {
            addCriterion("ACT_DATE >", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACT_DATE >=", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateLessThan(Integer value) {
            addCriterion("ACT_DATE <", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateLessThanOrEqualTo(Integer value) {
            addCriterion("ACT_DATE <=", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateIn(List<Integer> values) {
            addCriterion("ACT_DATE in", values, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotIn(List<Integer> values) {
            addCriterion("ACT_DATE not in", values, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateBetween(Integer value1, Integer value2) {
            addCriterion("ACT_DATE between", value1, value2, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotBetween(Integer value1, Integer value2) {
            addCriterion("ACT_DATE not between", value1, value2, "actDate");
            return (Criteria) this;
        }

        public Criteria andActTimeIsNull() {
            addCriterion("ACT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActTimeIsNotNull() {
            addCriterion("ACT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActTimeEqualTo(Long value) {
            addCriterion("ACT_TIME =", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeNotEqualTo(Long value) {
            addCriterion("ACT_TIME <>", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeGreaterThan(Long value) {
            addCriterion("ACT_TIME >", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ACT_TIME >=", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeLessThan(Long value) {
            addCriterion("ACT_TIME <", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeLessThanOrEqualTo(Long value) {
            addCriterion("ACT_TIME <=", value, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeIn(List<Long> values) {
            addCriterion("ACT_TIME in", values, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeNotIn(List<Long> values) {
            addCriterion("ACT_TIME not in", values, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeBetween(Long value1, Long value2) {
            addCriterion("ACT_TIME between", value1, value2, "actTime");
            return (Criteria) this;
        }

        public Criteria andActTimeNotBetween(Long value1, Long value2) {
            addCriterion("ACT_TIME not between", value1, value2, "actTime");
            return (Criteria) this;
        }

        public Criteria andTermTelIsNull() {
            addCriterion("TERM_TEL is null");
            return (Criteria) this;
        }

        public Criteria andTermTelIsNotNull() {
            addCriterion("TERM_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andTermTelEqualTo(String value) {
            addCriterion("TERM_TEL =", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelNotEqualTo(String value) {
            addCriterion("TERM_TEL <>", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelGreaterThan(String value) {
            addCriterion("TERM_TEL >", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_TEL >=", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelLessThan(String value) {
            addCriterion("TERM_TEL <", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelLessThanOrEqualTo(String value) {
            addCriterion("TERM_TEL <=", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelLike(String value) {
            addCriterion("TERM_TEL like", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelNotLike(String value) {
            addCriterion("TERM_TEL not like", value, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelIn(List<String> values) {
            addCriterion("TERM_TEL in", values, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelNotIn(List<String> values) {
            addCriterion("TERM_TEL not in", values, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelBetween(String value1, String value2) {
            addCriterion("TERM_TEL between", value1, value2, "termTel");
            return (Criteria) this;
        }

        public Criteria andTermTelNotBetween(String value1, String value2) {
            addCriterion("TERM_TEL not between", value1, value2, "termTel");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("PROVINCE =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("PROVINCE <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("PROVINCE >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("PROVINCE <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("PROVINCE like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("PROVINCE not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("PROVINCE in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("PROVINCE not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("PROVINCE between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("PROVINCE not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityNoIsNull() {
            addCriterion("CITY_NO is null");
            return (Criteria) this;
        }

        public Criteria andCityNoIsNotNull() {
            addCriterion("CITY_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCityNoEqualTo(String value) {
            addCriterion("CITY_NO =", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotEqualTo(String value) {
            addCriterion("CITY_NO <>", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoGreaterThan(String value) {
            addCriterion("CITY_NO >", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_NO >=", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoLessThan(String value) {
            addCriterion("CITY_NO <", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoLessThanOrEqualTo(String value) {
            addCriterion("CITY_NO <=", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoLike(String value) {
            addCriterion("CITY_NO like", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotLike(String value) {
            addCriterion("CITY_NO not like", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoIn(List<String> values) {
            addCriterion("CITY_NO in", values, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotIn(List<String> values) {
            addCriterion("CITY_NO not in", values, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoBetween(String value1, String value2) {
            addCriterion("CITY_NO between", value1, value2, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotBetween(String value1, String value2) {
            addCriterion("CITY_NO not between", value1, value2, "cityNo");
            return (Criteria) this;
        }

        public Criteria andZoneIsNull() {
            addCriterion("ZONE is null");
            return (Criteria) this;
        }

        public Criteria andZoneIsNotNull() {
            addCriterion("ZONE is not null");
            return (Criteria) this;
        }

        public Criteria andZoneEqualTo(String value) {
            addCriterion("ZONE =", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotEqualTo(String value) {
            addCriterion("ZONE <>", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThan(String value) {
            addCriterion("ZONE >", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThanOrEqualTo(String value) {
            addCriterion("ZONE >=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThan(String value) {
            addCriterion("ZONE <", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThanOrEqualTo(String value) {
            addCriterion("ZONE <=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLike(String value) {
            addCriterion("ZONE like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotLike(String value) {
            addCriterion("ZONE not like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneIn(List<String> values) {
            addCriterion("ZONE in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotIn(List<String> values) {
            addCriterion("ZONE not in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneBetween(String value1, String value2) {
            addCriterion("ZONE between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotBetween(String value1, String value2) {
            addCriterion("ZONE not between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdIsNull() {
            addCriterion("SETTLE_MRCH_ACC_ID is null");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdIsNotNull() {
            addCriterion("SETTLE_MRCH_ACC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdEqualTo(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID =", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdNotEqualTo(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID <>", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdGreaterThan(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID >", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID >=", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdLessThan(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID <", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdLessThanOrEqualTo(Long value) {
            addCriterion("SETTLE_MRCH_ACC_ID <=", value, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdIn(List<Long> values) {
            addCriterion("SETTLE_MRCH_ACC_ID in", values, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdNotIn(List<Long> values) {
            addCriterion("SETTLE_MRCH_ACC_ID not in", values, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdBetween(Long value1, Long value2) {
            addCriterion("SETTLE_MRCH_ACC_ID between", value1, value2, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andSettleMrchAccIdNotBetween(Long value1, Long value2) {
            addCriterion("SETTLE_MRCH_ACC_ID not between", value1, value2, "settleMrchAccId");
            return (Criteria) this;
        }

        public Criteria andXTimezoneIsNull() {
            addCriterion("X_TIMEZONE is null");
            return (Criteria) this;
        }

        public Criteria andXTimezoneIsNotNull() {
            addCriterion("X_TIMEZONE is not null");
            return (Criteria) this;
        }

        public Criteria andXTimezoneEqualTo(String value) {
            addCriterion("X_TIMEZONE =", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneNotEqualTo(String value) {
            addCriterion("X_TIMEZONE <>", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneGreaterThan(String value) {
            addCriterion("X_TIMEZONE >", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneGreaterThanOrEqualTo(String value) {
            addCriterion("X_TIMEZONE >=", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneLessThan(String value) {
            addCriterion("X_TIMEZONE <", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneLessThanOrEqualTo(String value) {
            addCriterion("X_TIMEZONE <=", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneLike(String value) {
            addCriterion("X_TIMEZONE like", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneNotLike(String value) {
            addCriterion("X_TIMEZONE not like", value, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneIn(List<String> values) {
            addCriterion("X_TIMEZONE in", values, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneNotIn(List<String> values) {
            addCriterion("X_TIMEZONE not in", values, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneBetween(String value1, String value2) {
            addCriterion("X_TIMEZONE between", value1, value2, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andXTimezoneNotBetween(String value1, String value2) {
            addCriterion("X_TIMEZONE not between", value1, value2, "xTimezone");
            return (Criteria) this;
        }

        public Criteria andSdFlagIsNull() {
            addCriterion("SD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSdFlagIsNotNull() {
            addCriterion("SD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSdFlagEqualTo(String value) {
            addCriterion("SD_FLAG =", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagNotEqualTo(String value) {
            addCriterion("SD_FLAG <>", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagGreaterThan(String value) {
            addCriterion("SD_FLAG >", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SD_FLAG >=", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagLessThan(String value) {
            addCriterion("SD_FLAG <", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagLessThanOrEqualTo(String value) {
            addCriterion("SD_FLAG <=", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagLike(String value) {
            addCriterion("SD_FLAG like", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagNotLike(String value) {
            addCriterion("SD_FLAG not like", value, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagIn(List<String> values) {
            addCriterion("SD_FLAG in", values, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagNotIn(List<String> values) {
            addCriterion("SD_FLAG not in", values, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagBetween(String value1, String value2) {
            addCriterion("SD_FLAG between", value1, value2, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdFlagNotBetween(String value1, String value2) {
            addCriterion("SD_FLAG not between", value1, value2, "sdFlag");
            return (Criteria) this;
        }

        public Criteria andSdErrorIsNull() {
            addCriterion("SD_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andSdErrorIsNotNull() {
            addCriterion("SD_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andSdErrorEqualTo(String value) {
            addCriterion("SD_ERROR =", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorNotEqualTo(String value) {
            addCriterion("SD_ERROR <>", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorGreaterThan(String value) {
            addCriterion("SD_ERROR >", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorGreaterThanOrEqualTo(String value) {
            addCriterion("SD_ERROR >=", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorLessThan(String value) {
            addCriterion("SD_ERROR <", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorLessThanOrEqualTo(String value) {
            addCriterion("SD_ERROR <=", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorLike(String value) {
            addCriterion("SD_ERROR like", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorNotLike(String value) {
            addCriterion("SD_ERROR not like", value, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorIn(List<String> values) {
            addCriterion("SD_ERROR in", values, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorNotIn(List<String> values) {
            addCriterion("SD_ERROR not in", values, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorBetween(String value1, String value2) {
            addCriterion("SD_ERROR between", value1, value2, "sdError");
            return (Criteria) this;
        }

        public Criteria andSdErrorNotBetween(String value1, String value2) {
            addCriterion("SD_ERROR not between", value1, value2, "sdError");
            return (Criteria) this;
        }

        public Criteria andXFlagIsNull() {
            addCriterion("X_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andXFlagIsNotNull() {
            addCriterion("X_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andXFlagEqualTo(String value) {
            addCriterion("X_FLAG =", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagNotEqualTo(String value) {
            addCriterion("X_FLAG <>", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagGreaterThan(String value) {
            addCriterion("X_FLAG >", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagGreaterThanOrEqualTo(String value) {
            addCriterion("X_FLAG >=", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagLessThan(String value) {
            addCriterion("X_FLAG <", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagLessThanOrEqualTo(String value) {
            addCriterion("X_FLAG <=", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagLike(String value) {
            addCriterion("X_FLAG like", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagNotLike(String value) {
            addCriterion("X_FLAG not like", value, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagIn(List<String> values) {
            addCriterion("X_FLAG in", values, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagNotIn(List<String> values) {
            addCriterion("X_FLAG not in", values, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagBetween(String value1, String value2) {
            addCriterion("X_FLAG between", value1, value2, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXFlagNotBetween(String value1, String value2) {
            addCriterion("X_FLAG not between", value1, value2, "xFlag");
            return (Criteria) this;
        }

        public Criteria andXErrorIsNull() {
            addCriterion("X_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andXErrorIsNotNull() {
            addCriterion("X_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andXErrorEqualTo(String value) {
            addCriterion("X_ERROR =", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorNotEqualTo(String value) {
            addCriterion("X_ERROR <>", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorGreaterThan(String value) {
            addCriterion("X_ERROR >", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorGreaterThanOrEqualTo(String value) {
            addCriterion("X_ERROR >=", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorLessThan(String value) {
            addCriterion("X_ERROR <", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorLessThanOrEqualTo(String value) {
            addCriterion("X_ERROR <=", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorLike(String value) {
            addCriterion("X_ERROR like", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorNotLike(String value) {
            addCriterion("X_ERROR not like", value, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorIn(List<String> values) {
            addCriterion("X_ERROR in", values, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorNotIn(List<String> values) {
            addCriterion("X_ERROR not in", values, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorBetween(String value1, String value2) {
            addCriterion("X_ERROR between", value1, value2, "xError");
            return (Criteria) this;
        }

        public Criteria andXErrorNotBetween(String value1, String value2) {
            addCriterion("X_ERROR not between", value1, value2, "xError");
            return (Criteria) this;
        }

        public Criteria andYufuFlagIsNull() {
            addCriterion("YUFU_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andYufuFlagIsNotNull() {
            addCriterion("YUFU_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andYufuFlagEqualTo(String value) {
            addCriterion("YUFU_FLAG =", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagNotEqualTo(String value) {
            addCriterion("YUFU_FLAG <>", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagGreaterThan(String value) {
            addCriterion("YUFU_FLAG >", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagGreaterThanOrEqualTo(String value) {
            addCriterion("YUFU_FLAG >=", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagLessThan(String value) {
            addCriterion("YUFU_FLAG <", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagLessThanOrEqualTo(String value) {
            addCriterion("YUFU_FLAG <=", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagLike(String value) {
            addCriterion("YUFU_FLAG like", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagNotLike(String value) {
            addCriterion("YUFU_FLAG not like", value, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagIn(List<String> values) {
            addCriterion("YUFU_FLAG in", values, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagNotIn(List<String> values) {
            addCriterion("YUFU_FLAG not in", values, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagBetween(String value1, String value2) {
            addCriterion("YUFU_FLAG between", value1, value2, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuFlagNotBetween(String value1, String value2) {
            addCriterion("YUFU_FLAG not between", value1, value2, "yufuFlag");
            return (Criteria) this;
        }

        public Criteria andYufuErrorIsNull() {
            addCriterion("YUFU_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andYufuErrorIsNotNull() {
            addCriterion("YUFU_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andYufuErrorEqualTo(String value) {
            addCriterion("YUFU_ERROR =", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorNotEqualTo(String value) {
            addCriterion("YUFU_ERROR <>", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorGreaterThan(String value) {
            addCriterion("YUFU_ERROR >", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorGreaterThanOrEqualTo(String value) {
            addCriterion("YUFU_ERROR >=", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorLessThan(String value) {
            addCriterion("YUFU_ERROR <", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorLessThanOrEqualTo(String value) {
            addCriterion("YUFU_ERROR <=", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorLike(String value) {
            addCriterion("YUFU_ERROR like", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorNotLike(String value) {
            addCriterion("YUFU_ERROR not like", value, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorIn(List<String> values) {
            addCriterion("YUFU_ERROR in", values, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorNotIn(List<String> values) {
            addCriterion("YUFU_ERROR not in", values, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorBetween(String value1, String value2) {
            addCriterion("YUFU_ERROR between", value1, value2, "yufuError");
            return (Criteria) this;
        }

        public Criteria andYufuErrorNotBetween(String value1, String value2) {
            addCriterion("YUFU_ERROR not between", value1, value2, "yufuError");
            return (Criteria) this;
        }

        public Criteria andXBakFlagIsNull() {
            addCriterion("X_BAK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andXBakFlagIsNotNull() {
            addCriterion("X_BAK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andXBakFlagEqualTo(String value) {
            addCriterion("X_BAK_FLAG =", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagNotEqualTo(String value) {
            addCriterion("X_BAK_FLAG <>", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagGreaterThan(String value) {
            addCriterion("X_BAK_FLAG >", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagGreaterThanOrEqualTo(String value) {
            addCriterion("X_BAK_FLAG >=", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagLessThan(String value) {
            addCriterion("X_BAK_FLAG <", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagLessThanOrEqualTo(String value) {
            addCriterion("X_BAK_FLAG <=", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagLike(String value) {
            addCriterion("X_BAK_FLAG like", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagNotLike(String value) {
            addCriterion("X_BAK_FLAG not like", value, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagIn(List<String> values) {
            addCriterion("X_BAK_FLAG in", values, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagNotIn(List<String> values) {
            addCriterion("X_BAK_FLAG not in", values, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagBetween(String value1, String value2) {
            addCriterion("X_BAK_FLAG between", value1, value2, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakFlagNotBetween(String value1, String value2) {
            addCriterion("X_BAK_FLAG not between", value1, value2, "xBakFlag");
            return (Criteria) this;
        }

        public Criteria andXBakErrorIsNull() {
            addCriterion("X_BAK_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andXBakErrorIsNotNull() {
            addCriterion("X_BAK_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andXBakErrorEqualTo(String value) {
            addCriterion("X_BAK_ERROR =", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorNotEqualTo(String value) {
            addCriterion("X_BAK_ERROR <>", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorGreaterThan(String value) {
            addCriterion("X_BAK_ERROR >", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorGreaterThanOrEqualTo(String value) {
            addCriterion("X_BAK_ERROR >=", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorLessThan(String value) {
            addCriterion("X_BAK_ERROR <", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorLessThanOrEqualTo(String value) {
            addCriterion("X_BAK_ERROR <=", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorLike(String value) {
            addCriterion("X_BAK_ERROR like", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorNotLike(String value) {
            addCriterion("X_BAK_ERROR not like", value, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorIn(List<String> values) {
            addCriterion("X_BAK_ERROR in", values, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorNotIn(List<String> values) {
            addCriterion("X_BAK_ERROR not in", values, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorBetween(String value1, String value2) {
            addCriterion("X_BAK_ERROR between", value1, value2, "xBakError");
            return (Criteria) this;
        }

        public Criteria andXBakErrorNotBetween(String value1, String value2) {
            addCriterion("X_BAK_ERROR not between", value1, value2, "xBakError");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdIsNull() {
            addCriterion("SEQ_TERMPOS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdIsNotNull() {
            addCriterion("SEQ_TERMPOS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdEqualTo(Long value) {
            addCriterion("SEQ_TERMPOS_ID =", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdNotEqualTo(Long value) {
            addCriterion("SEQ_TERMPOS_ID <>", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdGreaterThan(Long value) {
            addCriterion("SEQ_TERMPOS_ID >", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEQ_TERMPOS_ID >=", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdLessThan(Long value) {
            addCriterion("SEQ_TERMPOS_ID <", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdLessThanOrEqualTo(Long value) {
            addCriterion("SEQ_TERMPOS_ID <=", value, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdIn(List<Long> values) {
            addCriterion("SEQ_TERMPOS_ID in", values, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdNotIn(List<Long> values) {
            addCriterion("SEQ_TERMPOS_ID not in", values, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdBetween(Long value1, Long value2) {
            addCriterion("SEQ_TERMPOS_ID between", value1, value2, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqTermposIdNotBetween(Long value1, Long value2) {
            addCriterion("SEQ_TERMPOS_ID not between", value1, value2, "seqTermposId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdIsNull() {
            addCriterion("SEQ_ENCKEY_ID is null");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdIsNotNull() {
            addCriterion("SEQ_ENCKEY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdEqualTo(Long value) {
            addCriterion("SEQ_ENCKEY_ID =", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdNotEqualTo(Long value) {
            addCriterion("SEQ_ENCKEY_ID <>", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdGreaterThan(Long value) {
            addCriterion("SEQ_ENCKEY_ID >", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEQ_ENCKEY_ID >=", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdLessThan(Long value) {
            addCriterion("SEQ_ENCKEY_ID <", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdLessThanOrEqualTo(Long value) {
            addCriterion("SEQ_ENCKEY_ID <=", value, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdIn(List<Long> values) {
            addCriterion("SEQ_ENCKEY_ID in", values, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdNotIn(List<Long> values) {
            addCriterion("SEQ_ENCKEY_ID not in", values, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdBetween(Long value1, Long value2) {
            addCriterion("SEQ_ENCKEY_ID between", value1, value2, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andSeqEnckeyIdNotBetween(Long value1, Long value2) {
            addCriterion("SEQ_ENCKEY_ID not between", value1, value2, "seqEnckeyId");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryIsNull() {
            addCriterion("CONSUMP_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryIsNotNull() {
            addCriterion("CONSUMP_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryEqualTo(String value) {
            addCriterion("CONSUMP_CATEGORY =", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryNotEqualTo(String value) {
            addCriterion("CONSUMP_CATEGORY <>", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryGreaterThan(String value) {
            addCriterion("CONSUMP_CATEGORY >", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMP_CATEGORY >=", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryLessThan(String value) {
            addCriterion("CONSUMP_CATEGORY <", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryLessThanOrEqualTo(String value) {
            addCriterion("CONSUMP_CATEGORY <=", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryLike(String value) {
            addCriterion("CONSUMP_CATEGORY like", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryNotLike(String value) {
            addCriterion("CONSUMP_CATEGORY not like", value, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryIn(List<String> values) {
            addCriterion("CONSUMP_CATEGORY in", values, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryNotIn(List<String> values) {
            addCriterion("CONSUMP_CATEGORY not in", values, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryBetween(String value1, String value2) {
            addCriterion("CONSUMP_CATEGORY between", value1, value2, "consumpCategory");
            return (Criteria) this;
        }

        public Criteria andConsumpCategoryNotBetween(String value1, String value2) {
            addCriterion("CONSUMP_CATEGORY not between", value1, value2, "consumpCategory");
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