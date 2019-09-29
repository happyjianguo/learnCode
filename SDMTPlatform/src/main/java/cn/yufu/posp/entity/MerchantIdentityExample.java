package cn.yufu.posp.entity;

import java.util.ArrayList;
import java.util.List;

public class MerchantIdentityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantIdentityExample() {
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

        public Criteria andMerchantCnameIsNull() {
            addCriterion("MERCHANT_CNAME is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameIsNotNull() {
            addCriterion("MERCHANT_CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameEqualTo(String value) {
            addCriterion("MERCHANT_CNAME =", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameNotEqualTo(String value) {
            addCriterion("MERCHANT_CNAME <>", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameGreaterThan(String value) {
            addCriterion("MERCHANT_CNAME >", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CNAME >=", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameLessThan(String value) {
            addCriterion("MERCHANT_CNAME <", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CNAME <=", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameLike(String value) {
            addCriterion("MERCHANT_CNAME like", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameNotLike(String value) {
            addCriterion("MERCHANT_CNAME not like", value, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameIn(List<String> values) {
            addCriterion("MERCHANT_CNAME in", values, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameNotIn(List<String> values) {
            addCriterion("MERCHANT_CNAME not in", values, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameBetween(String value1, String value2) {
            addCriterion("MERCHANT_CNAME between", value1, value2, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andMerchantCnameNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_CNAME not between", value1, value2, "merchantCname");
            return (Criteria) this;
        }

        public Criteria andBiCardNoIsNull() {
            addCriterion("BI_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andBiCardNoIsNotNull() {
            addCriterion("BI_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBiCardNoEqualTo(String value) {
            addCriterion("BI_CARD_NO =", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoNotEqualTo(String value) {
            addCriterion("BI_CARD_NO <>", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoGreaterThan(String value) {
            addCriterion("BI_CARD_NO >", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("BI_CARD_NO >=", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoLessThan(String value) {
            addCriterion("BI_CARD_NO <", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoLessThanOrEqualTo(String value) {
            addCriterion("BI_CARD_NO <=", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoLike(String value) {
            addCriterion("BI_CARD_NO like", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoNotLike(String value) {
            addCriterion("BI_CARD_NO not like", value, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoIn(List<String> values) {
            addCriterion("BI_CARD_NO in", values, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoNotIn(List<String> values) {
            addCriterion("BI_CARD_NO not in", values, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoBetween(String value1, String value2) {
            addCriterion("BI_CARD_NO between", value1, value2, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardNoNotBetween(String value1, String value2) {
            addCriterion("BI_CARD_NO not between", value1, value2, "biCardNo");
            return (Criteria) this;
        }

        public Criteria andBiCardDateIsNull() {
            addCriterion("BI_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBiCardDateIsNotNull() {
            addCriterion("BI_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBiCardDateEqualTo(String value) {
            addCriterion("BI_CARD_DATE =", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateNotEqualTo(String value) {
            addCriterion("BI_CARD_DATE <>", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateGreaterThan(String value) {
            addCriterion("BI_CARD_DATE >", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("BI_CARD_DATE >=", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateLessThan(String value) {
            addCriterion("BI_CARD_DATE <", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateLessThanOrEqualTo(String value) {
            addCriterion("BI_CARD_DATE <=", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateLike(String value) {
            addCriterion("BI_CARD_DATE like", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateNotLike(String value) {
            addCriterion("BI_CARD_DATE not like", value, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateIn(List<String> values) {
            addCriterion("BI_CARD_DATE in", values, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateNotIn(List<String> values) {
            addCriterion("BI_CARD_DATE not in", values, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateBetween(String value1, String value2) {
            addCriterion("BI_CARD_DATE between", value1, value2, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardDateNotBetween(String value1, String value2) {
            addCriterion("BI_CARD_DATE not between", value1, value2, "biCardDate");
            return (Criteria) this;
        }

        public Criteria andBiCardPicIsNull() {
            addCriterion("BI_CARD_PIC is null");
            return (Criteria) this;
        }

        public Criteria andBiCardPicIsNotNull() {
            addCriterion("BI_CARD_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andBiCardPicEqualTo(String value) {
            addCriterion("BI_CARD_PIC =", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicNotEqualTo(String value) {
            addCriterion("BI_CARD_PIC <>", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicGreaterThan(String value) {
            addCriterion("BI_CARD_PIC >", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicGreaterThanOrEqualTo(String value) {
            addCriterion("BI_CARD_PIC >=", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicLessThan(String value) {
            addCriterion("BI_CARD_PIC <", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicLessThanOrEqualTo(String value) {
            addCriterion("BI_CARD_PIC <=", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicLike(String value) {
            addCriterion("BI_CARD_PIC like", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicNotLike(String value) {
            addCriterion("BI_CARD_PIC not like", value, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicIn(List<String> values) {
            addCriterion("BI_CARD_PIC in", values, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicNotIn(List<String> values) {
            addCriterion("BI_CARD_PIC not in", values, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicBetween(String value1, String value2) {
            addCriterion("BI_CARD_PIC between", value1, value2, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andBiCardPicNotBetween(String value1, String value2) {
            addCriterion("BI_CARD_PIC not between", value1, value2, "biCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoIsNull() {
            addCriterion("TAX_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoIsNotNull() {
            addCriterion("TAX_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoEqualTo(String value) {
            addCriterion("TAX_CARD_NO =", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoNotEqualTo(String value) {
            addCriterion("TAX_CARD_NO <>", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoGreaterThan(String value) {
            addCriterion("TAX_CARD_NO >", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_NO >=", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoLessThan(String value) {
            addCriterion("TAX_CARD_NO <", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoLessThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_NO <=", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoLike(String value) {
            addCriterion("TAX_CARD_NO like", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoNotLike(String value) {
            addCriterion("TAX_CARD_NO not like", value, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoIn(List<String> values) {
            addCriterion("TAX_CARD_NO in", values, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoNotIn(List<String> values) {
            addCriterion("TAX_CARD_NO not in", values, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoBetween(String value1, String value2) {
            addCriterion("TAX_CARD_NO between", value1, value2, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardNoNotBetween(String value1, String value2) {
            addCriterion("TAX_CARD_NO not between", value1, value2, "taxCardNo");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateIsNull() {
            addCriterion("TAX_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateIsNotNull() {
            addCriterion("TAX_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateEqualTo(String value) {
            addCriterion("TAX_CARD_DATE =", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateNotEqualTo(String value) {
            addCriterion("TAX_CARD_DATE <>", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateGreaterThan(String value) {
            addCriterion("TAX_CARD_DATE >", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_DATE >=", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateLessThan(String value) {
            addCriterion("TAX_CARD_DATE <", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateLessThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_DATE <=", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateLike(String value) {
            addCriterion("TAX_CARD_DATE like", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateNotLike(String value) {
            addCriterion("TAX_CARD_DATE not like", value, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateIn(List<String> values) {
            addCriterion("TAX_CARD_DATE in", values, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateNotIn(List<String> values) {
            addCriterion("TAX_CARD_DATE not in", values, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateBetween(String value1, String value2) {
            addCriterion("TAX_CARD_DATE between", value1, value2, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardDateNotBetween(String value1, String value2) {
            addCriterion("TAX_CARD_DATE not between", value1, value2, "taxCardDate");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicIsNull() {
            addCriterion("TAX_CARD_PIC is null");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicIsNotNull() {
            addCriterion("TAX_CARD_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicEqualTo(String value) {
            addCriterion("TAX_CARD_PIC =", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicNotEqualTo(String value) {
            addCriterion("TAX_CARD_PIC <>", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicGreaterThan(String value) {
            addCriterion("TAX_CARD_PIC >", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_PIC >=", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicLessThan(String value) {
            addCriterion("TAX_CARD_PIC <", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicLessThanOrEqualTo(String value) {
            addCriterion("TAX_CARD_PIC <=", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicLike(String value) {
            addCriterion("TAX_CARD_PIC like", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicNotLike(String value) {
            addCriterion("TAX_CARD_PIC not like", value, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicIn(List<String> values) {
            addCriterion("TAX_CARD_PIC in", values, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicNotIn(List<String> values) {
            addCriterion("TAX_CARD_PIC not in", values, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicBetween(String value1, String value2) {
            addCriterion("TAX_CARD_PIC between", value1, value2, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andTaxCardPicNotBetween(String value1, String value2) {
            addCriterion("TAX_CARD_PIC not between", value1, value2, "taxCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoIsNull() {
            addCriterion("ORG_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoIsNotNull() {
            addCriterion("ORG_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoEqualTo(String value) {
            addCriterion("ORG_CARD_NO =", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoNotEqualTo(String value) {
            addCriterion("ORG_CARD_NO <>", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoGreaterThan(String value) {
            addCriterion("ORG_CARD_NO >", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_NO >=", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoLessThan(String value) {
            addCriterion("ORG_CARD_NO <", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoLessThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_NO <=", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoLike(String value) {
            addCriterion("ORG_CARD_NO like", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoNotLike(String value) {
            addCriterion("ORG_CARD_NO not like", value, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoIn(List<String> values) {
            addCriterion("ORG_CARD_NO in", values, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoNotIn(List<String> values) {
            addCriterion("ORG_CARD_NO not in", values, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoBetween(String value1, String value2) {
            addCriterion("ORG_CARD_NO between", value1, value2, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardNoNotBetween(String value1, String value2) {
            addCriterion("ORG_CARD_NO not between", value1, value2, "orgCardNo");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateIsNull() {
            addCriterion("ORG_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateIsNotNull() {
            addCriterion("ORG_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateEqualTo(String value) {
            addCriterion("ORG_CARD_DATE =", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateNotEqualTo(String value) {
            addCriterion("ORG_CARD_DATE <>", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateGreaterThan(String value) {
            addCriterion("ORG_CARD_DATE >", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_DATE >=", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateLessThan(String value) {
            addCriterion("ORG_CARD_DATE <", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateLessThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_DATE <=", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateLike(String value) {
            addCriterion("ORG_CARD_DATE like", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateNotLike(String value) {
            addCriterion("ORG_CARD_DATE not like", value, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateIn(List<String> values) {
            addCriterion("ORG_CARD_DATE in", values, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateNotIn(List<String> values) {
            addCriterion("ORG_CARD_DATE not in", values, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateBetween(String value1, String value2) {
            addCriterion("ORG_CARD_DATE between", value1, value2, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardDateNotBetween(String value1, String value2) {
            addCriterion("ORG_CARD_DATE not between", value1, value2, "orgCardDate");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicIsNull() {
            addCriterion("ORG_CARD_PIC is null");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicIsNotNull() {
            addCriterion("ORG_CARD_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicEqualTo(String value) {
            addCriterion("ORG_CARD_PIC =", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicNotEqualTo(String value) {
            addCriterion("ORG_CARD_PIC <>", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicGreaterThan(String value) {
            addCriterion("ORG_CARD_PIC >", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_PIC >=", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicLessThan(String value) {
            addCriterion("ORG_CARD_PIC <", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicLessThanOrEqualTo(String value) {
            addCriterion("ORG_CARD_PIC <=", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicLike(String value) {
            addCriterion("ORG_CARD_PIC like", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicNotLike(String value) {
            addCriterion("ORG_CARD_PIC not like", value, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicIn(List<String> values) {
            addCriterion("ORG_CARD_PIC in", values, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicNotIn(List<String> values) {
            addCriterion("ORG_CARD_PIC not in", values, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicBetween(String value1, String value2) {
            addCriterion("ORG_CARD_PIC between", value1, value2, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andOrgCardPicNotBetween(String value1, String value2) {
            addCriterion("ORG_CARD_PIC not between", value1, value2, "orgCardPic");
            return (Criteria) this;
        }

        public Criteria andShareNameIsNull() {
            addCriterion("SHARE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShareNameIsNotNull() {
            addCriterion("SHARE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShareNameEqualTo(String value) {
            addCriterion("SHARE_NAME =", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameNotEqualTo(String value) {
            addCriterion("SHARE_NAME <>", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameGreaterThan(String value) {
            addCriterion("SHARE_NAME >", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_NAME >=", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameLessThan(String value) {
            addCriterion("SHARE_NAME <", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameLessThanOrEqualTo(String value) {
            addCriterion("SHARE_NAME <=", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameLike(String value) {
            addCriterion("SHARE_NAME like", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameNotLike(String value) {
            addCriterion("SHARE_NAME not like", value, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameIn(List<String> values) {
            addCriterion("SHARE_NAME in", values, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameNotIn(List<String> values) {
            addCriterion("SHARE_NAME not in", values, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameBetween(String value1, String value2) {
            addCriterion("SHARE_NAME between", value1, value2, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareNameNotBetween(String value1, String value2) {
            addCriterion("SHARE_NAME not between", value1, value2, "shareName");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeIsNull() {
            addCriterion("SHARE_CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeIsNotNull() {
            addCriterion("SHARE_CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeEqualTo(String value) {
            addCriterion("SHARE_CARD_TYPE =", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeNotEqualTo(String value) {
            addCriterion("SHARE_CARD_TYPE <>", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeGreaterThan(String value) {
            addCriterion("SHARE_CARD_TYPE >", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_TYPE >=", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeLessThan(String value) {
            addCriterion("SHARE_CARD_TYPE <", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeLessThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_TYPE <=", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeLike(String value) {
            addCriterion("SHARE_CARD_TYPE like", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeNotLike(String value) {
            addCriterion("SHARE_CARD_TYPE not like", value, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeIn(List<String> values) {
            addCriterion("SHARE_CARD_TYPE in", values, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeNotIn(List<String> values) {
            addCriterion("SHARE_CARD_TYPE not in", values, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_TYPE between", value1, value2, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardTypeNotBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_TYPE not between", value1, value2, "shareCardType");
            return (Criteria) this;
        }

        public Criteria andShareCardNoIsNull() {
            addCriterion("SHARE_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andShareCardNoIsNotNull() {
            addCriterion("SHARE_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andShareCardNoEqualTo(String value) {
            addCriterion("SHARE_CARD_NO =", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoNotEqualTo(String value) {
            addCriterion("SHARE_CARD_NO <>", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoGreaterThan(String value) {
            addCriterion("SHARE_CARD_NO >", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_NO >=", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoLessThan(String value) {
            addCriterion("SHARE_CARD_NO <", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoLessThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_NO <=", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoLike(String value) {
            addCriterion("SHARE_CARD_NO like", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoNotLike(String value) {
            addCriterion("SHARE_CARD_NO not like", value, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoIn(List<String> values) {
            addCriterion("SHARE_CARD_NO in", values, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoNotIn(List<String> values) {
            addCriterion("SHARE_CARD_NO not in", values, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_NO between", value1, value2, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardNoNotBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_NO not between", value1, value2, "shareCardNo");
            return (Criteria) this;
        }

        public Criteria andShareCardDateIsNull() {
            addCriterion("SHARE_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andShareCardDateIsNotNull() {
            addCriterion("SHARE_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andShareCardDateEqualTo(String value) {
            addCriterion("SHARE_CARD_DATE =", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateNotEqualTo(String value) {
            addCriterion("SHARE_CARD_DATE <>", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateGreaterThan(String value) {
            addCriterion("SHARE_CARD_DATE >", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_DATE >=", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateLessThan(String value) {
            addCriterion("SHARE_CARD_DATE <", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateLessThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_DATE <=", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateLike(String value) {
            addCriterion("SHARE_CARD_DATE like", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateNotLike(String value) {
            addCriterion("SHARE_CARD_DATE not like", value, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateIn(List<String> values) {
            addCriterion("SHARE_CARD_DATE in", values, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateNotIn(List<String> values) {
            addCriterion("SHARE_CARD_DATE not in", values, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_DATE between", value1, value2, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardDateNotBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_DATE not between", value1, value2, "shareCardDate");
            return (Criteria) this;
        }

        public Criteria andShareCardTelIsNull() {
            addCriterion("SHARE_CARD_TEL is null");
            return (Criteria) this;
        }

        public Criteria andShareCardTelIsNotNull() {
            addCriterion("SHARE_CARD_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andShareCardTelEqualTo(String value) {
            addCriterion("SHARE_CARD_TEL =", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelNotEqualTo(String value) {
            addCriterion("SHARE_CARD_TEL <>", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelGreaterThan(String value) {
            addCriterion("SHARE_CARD_TEL >", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_TEL >=", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelLessThan(String value) {
            addCriterion("SHARE_CARD_TEL <", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelLessThanOrEqualTo(String value) {
            addCriterion("SHARE_CARD_TEL <=", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelLike(String value) {
            addCriterion("SHARE_CARD_TEL like", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelNotLike(String value) {
            addCriterion("SHARE_CARD_TEL not like", value, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelIn(List<String> values) {
            addCriterion("SHARE_CARD_TEL in", values, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelNotIn(List<String> values) {
            addCriterion("SHARE_CARD_TEL not in", values, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_TEL between", value1, value2, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andShareCardTelNotBetween(String value1, String value2) {
            addCriterion("SHARE_CARD_TEL not between", value1, value2, "shareCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNull() {
            addCriterion("LEGAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNotNull() {
            addCriterion("LEGAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLegalNameEqualTo(String value) {
            addCriterion("LEGAL_NAME =", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotEqualTo(String value) {
            addCriterion("LEGAL_NAME <>", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThan(String value) {
            addCriterion("LEGAL_NAME >", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_NAME >=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThan(String value) {
            addCriterion("LEGAL_NAME <", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_NAME <=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLike(String value) {
            addCriterion("LEGAL_NAME like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotLike(String value) {
            addCriterion("LEGAL_NAME not like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameIn(List<String> values) {
            addCriterion("LEGAL_NAME in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotIn(List<String> values) {
            addCriterion("LEGAL_NAME not in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameBetween(String value1, String value2) {
            addCriterion("LEGAL_NAME between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotBetween(String value1, String value2) {
            addCriterion("LEGAL_NAME not between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeIsNull() {
            addCriterion("LEGAL_CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeIsNotNull() {
            addCriterion("LEGAL_CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeEqualTo(String value) {
            addCriterion("LEGAL_CARD_TYPE =", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeNotEqualTo(String value) {
            addCriterion("LEGAL_CARD_TYPE <>", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeGreaterThan(String value) {
            addCriterion("LEGAL_CARD_TYPE >", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_TYPE >=", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeLessThan(String value) {
            addCriterion("LEGAL_CARD_TYPE <", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_TYPE <=", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeLike(String value) {
            addCriterion("LEGAL_CARD_TYPE like", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeNotLike(String value) {
            addCriterion("LEGAL_CARD_TYPE not like", value, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeIn(List<String> values) {
            addCriterion("LEGAL_CARD_TYPE in", values, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeNotIn(List<String> values) {
            addCriterion("LEGAL_CARD_TYPE not in", values, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_TYPE between", value1, value2, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardTypeNotBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_TYPE not between", value1, value2, "legalCardType");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoIsNull() {
            addCriterion("LEGAL_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoIsNotNull() {
            addCriterion("LEGAL_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoEqualTo(String value) {
            addCriterion("LEGAL_CARD_NO =", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoNotEqualTo(String value) {
            addCriterion("LEGAL_CARD_NO <>", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoGreaterThan(String value) {
            addCriterion("LEGAL_CARD_NO >", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_NO >=", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoLessThan(String value) {
            addCriterion("LEGAL_CARD_NO <", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_NO <=", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoLike(String value) {
            addCriterion("LEGAL_CARD_NO like", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoNotLike(String value) {
            addCriterion("LEGAL_CARD_NO not like", value, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoIn(List<String> values) {
            addCriterion("LEGAL_CARD_NO in", values, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoNotIn(List<String> values) {
            addCriterion("LEGAL_CARD_NO not in", values, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_NO between", value1, value2, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardNoNotBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_NO not between", value1, value2, "legalCardNo");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateIsNull() {
            addCriterion("LEGAL_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateIsNotNull() {
            addCriterion("LEGAL_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateEqualTo(String value) {
            addCriterion("LEGAL_CARD_DATE =", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateNotEqualTo(String value) {
            addCriterion("LEGAL_CARD_DATE <>", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateGreaterThan(String value) {
            addCriterion("LEGAL_CARD_DATE >", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_DATE >=", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateLessThan(String value) {
            addCriterion("LEGAL_CARD_DATE <", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_DATE <=", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateLike(String value) {
            addCriterion("LEGAL_CARD_DATE like", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateNotLike(String value) {
            addCriterion("LEGAL_CARD_DATE not like", value, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateIn(List<String> values) {
            addCriterion("LEGAL_CARD_DATE in", values, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateNotIn(List<String> values) {
            addCriterion("LEGAL_CARD_DATE not in", values, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_DATE between", value1, value2, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardDateNotBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_DATE not between", value1, value2, "legalCardDate");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelIsNull() {
            addCriterion("LEGAL_CARD_TEL is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelIsNotNull() {
            addCriterion("LEGAL_CARD_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelEqualTo(String value) {
            addCriterion("LEGAL_CARD_TEL =", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelNotEqualTo(String value) {
            addCriterion("LEGAL_CARD_TEL <>", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelGreaterThan(String value) {
            addCriterion("LEGAL_CARD_TEL >", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_TEL >=", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelLessThan(String value) {
            addCriterion("LEGAL_CARD_TEL <", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD_TEL <=", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelLike(String value) {
            addCriterion("LEGAL_CARD_TEL like", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelNotLike(String value) {
            addCriterion("LEGAL_CARD_TEL not like", value, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelIn(List<String> values) {
            addCriterion("LEGAL_CARD_TEL in", values, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelNotIn(List<String> values) {
            addCriterion("LEGAL_CARD_TEL not in", values, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_TEL between", value1, value2, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andLegalCardTelNotBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD_TEL not between", value1, value2, "legalCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnNameIsNull() {
            addCriterion("ATTN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttnNameIsNotNull() {
            addCriterion("ATTN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttnNameEqualTo(String value) {
            addCriterion("ATTN_NAME =", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameNotEqualTo(String value) {
            addCriterion("ATTN_NAME <>", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameGreaterThan(String value) {
            addCriterion("ATTN_NAME >", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTN_NAME >=", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameLessThan(String value) {
            addCriterion("ATTN_NAME <", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameLessThanOrEqualTo(String value) {
            addCriterion("ATTN_NAME <=", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameLike(String value) {
            addCriterion("ATTN_NAME like", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameNotLike(String value) {
            addCriterion("ATTN_NAME not like", value, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameIn(List<String> values) {
            addCriterion("ATTN_NAME in", values, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameNotIn(List<String> values) {
            addCriterion("ATTN_NAME not in", values, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameBetween(String value1, String value2) {
            addCriterion("ATTN_NAME between", value1, value2, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnNameNotBetween(String value1, String value2) {
            addCriterion("ATTN_NAME not between", value1, value2, "attnName");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeIsNull() {
            addCriterion("ATTN_CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeIsNotNull() {
            addCriterion("ATTN_CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeEqualTo(String value) {
            addCriterion("ATTN_CARD_TYPE =", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeNotEqualTo(String value) {
            addCriterion("ATTN_CARD_TYPE <>", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeGreaterThan(String value) {
            addCriterion("ATTN_CARD_TYPE >", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_TYPE >=", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeLessThan(String value) {
            addCriterion("ATTN_CARD_TYPE <", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeLessThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_TYPE <=", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeLike(String value) {
            addCriterion("ATTN_CARD_TYPE like", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeNotLike(String value) {
            addCriterion("ATTN_CARD_TYPE not like", value, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeIn(List<String> values) {
            addCriterion("ATTN_CARD_TYPE in", values, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeNotIn(List<String> values) {
            addCriterion("ATTN_CARD_TYPE not in", values, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_TYPE between", value1, value2, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardTypeNotBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_TYPE not between", value1, value2, "attnCardType");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoIsNull() {
            addCriterion("ATTN_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoIsNotNull() {
            addCriterion("ATTN_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoEqualTo(String value) {
            addCriterion("ATTN_CARD_NO =", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoNotEqualTo(String value) {
            addCriterion("ATTN_CARD_NO <>", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoGreaterThan(String value) {
            addCriterion("ATTN_CARD_NO >", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_NO >=", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoLessThan(String value) {
            addCriterion("ATTN_CARD_NO <", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoLessThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_NO <=", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoLike(String value) {
            addCriterion("ATTN_CARD_NO like", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoNotLike(String value) {
            addCriterion("ATTN_CARD_NO not like", value, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoIn(List<String> values) {
            addCriterion("ATTN_CARD_NO in", values, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoNotIn(List<String> values) {
            addCriterion("ATTN_CARD_NO not in", values, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_NO between", value1, value2, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardNoNotBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_NO not between", value1, value2, "attnCardNo");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateIsNull() {
            addCriterion("ATTN_CARD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateIsNotNull() {
            addCriterion("ATTN_CARD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateEqualTo(String value) {
            addCriterion("ATTN_CARD_DATE =", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateNotEqualTo(String value) {
            addCriterion("ATTN_CARD_DATE <>", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateGreaterThan(String value) {
            addCriterion("ATTN_CARD_DATE >", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateGreaterThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_DATE >=", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateLessThan(String value) {
            addCriterion("ATTN_CARD_DATE <", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateLessThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_DATE <=", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateLike(String value) {
            addCriterion("ATTN_CARD_DATE like", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateNotLike(String value) {
            addCriterion("ATTN_CARD_DATE not like", value, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateIn(List<String> values) {
            addCriterion("ATTN_CARD_DATE in", values, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateNotIn(List<String> values) {
            addCriterion("ATTN_CARD_DATE not in", values, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_DATE between", value1, value2, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardDateNotBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_DATE not between", value1, value2, "attnCardDate");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelIsNull() {
            addCriterion("ATTN_CARD_TEL is null");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelIsNotNull() {
            addCriterion("ATTN_CARD_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelEqualTo(String value) {
            addCriterion("ATTN_CARD_TEL =", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelNotEqualTo(String value) {
            addCriterion("ATTN_CARD_TEL <>", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelGreaterThan(String value) {
            addCriterion("ATTN_CARD_TEL >", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelGreaterThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_TEL >=", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelLessThan(String value) {
            addCriterion("ATTN_CARD_TEL <", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelLessThanOrEqualTo(String value) {
            addCriterion("ATTN_CARD_TEL <=", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelLike(String value) {
            addCriterion("ATTN_CARD_TEL like", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelNotLike(String value) {
            addCriterion("ATTN_CARD_TEL not like", value, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelIn(List<String> values) {
            addCriterion("ATTN_CARD_TEL in", values, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelNotIn(List<String> values) {
            addCriterion("ATTN_CARD_TEL not in", values, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_TEL between", value1, value2, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andAttnCardTelNotBetween(String value1, String value2) {
            addCriterion("ATTN_CARD_TEL not between", value1, value2, "attnCardTel");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNull() {
            addCriterion("CLASS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNotNull() {
            addCriterion("CLASS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andClassTypeEqualTo(String value) {
            addCriterion("CLASS_TYPE =", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotEqualTo(String value) {
            addCriterion("CLASS_TYPE <>", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThan(String value) {
            addCriterion("CLASS_TYPE >", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_TYPE >=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThan(String value) {
            addCriterion("CLASS_TYPE <", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThanOrEqualTo(String value) {
            addCriterion("CLASS_TYPE <=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLike(String value) {
            addCriterion("CLASS_TYPE like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotLike(String value) {
            addCriterion("CLASS_TYPE not like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeIn(List<String> values) {
            addCriterion("CLASS_TYPE in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotIn(List<String> values) {
            addCriterion("CLASS_TYPE not in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeBetween(String value1, String value2) {
            addCriterion("CLASS_TYPE between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotBetween(String value1, String value2) {
            addCriterion("CLASS_TYPE not between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("REASON is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("REASON is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("REASON =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("REASON <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("REASON >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REASON >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("REASON <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("REASON <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("REASON like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("REASON not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("REASON in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("REASON not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("REASON between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("REASON not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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