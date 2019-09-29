package cn.yufu.posp.entity;

import java.util.ArrayList;
import java.util.List;

public class MerchantBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantBaseExample() {
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

        public Criteria andMccIsNull() {
            addCriterion("MCC is null");
            return (Criteria) this;
        }

        public Criteria andMccIsNotNull() {
            addCriterion("MCC is not null");
            return (Criteria) this;
        }

        public Criteria andMccEqualTo(String value) {
            addCriterion("MCC =", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotEqualTo(String value) {
            addCriterion("MCC <>", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccGreaterThan(String value) {
            addCriterion("MCC >", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccGreaterThanOrEqualTo(String value) {
            addCriterion("MCC >=", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLessThan(String value) {
            addCriterion("MCC <", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLessThanOrEqualTo(String value) {
            addCriterion("MCC <=", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLike(String value) {
            addCriterion("MCC like", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotLike(String value) {
            addCriterion("MCC not like", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccIn(List<String> values) {
            addCriterion("MCC in", values, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotIn(List<String> values) {
            addCriterion("MCC not in", values, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccBetween(String value1, String value2) {
            addCriterion("MCC between", value1, value2, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotBetween(String value1, String value2) {
            addCriterion("MCC not between", value1, value2, "mcc");
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

        public Criteria andMerchantEnameIsNull() {
            addCriterion("MERCHANT_ENAME is null");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameIsNotNull() {
            addCriterion("MERCHANT_ENAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameEqualTo(String value) {
            addCriterion("MERCHANT_ENAME =", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameNotEqualTo(String value) {
            addCriterion("MERCHANT_ENAME <>", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameGreaterThan(String value) {
            addCriterion("MERCHANT_ENAME >", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ENAME >=", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameLessThan(String value) {
            addCriterion("MERCHANT_ENAME <", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_ENAME <=", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameLike(String value) {
            addCriterion("MERCHANT_ENAME like", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameNotLike(String value) {
            addCriterion("MERCHANT_ENAME not like", value, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameIn(List<String> values) {
            addCriterion("MERCHANT_ENAME in", values, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameNotIn(List<String> values) {
            addCriterion("MERCHANT_ENAME not in", values, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameBetween(String value1, String value2) {
            addCriterion("MERCHANT_ENAME between", value1, value2, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andMerchantEnameNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_ENAME not between", value1, value2, "merchantEname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameIsNull() {
            addCriterion("ABBR_CNAME is null");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameIsNotNull() {
            addCriterion("ABBR_CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameEqualTo(String value) {
            addCriterion("ABBR_CNAME =", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameNotEqualTo(String value) {
            addCriterion("ABBR_CNAME <>", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameGreaterThan(String value) {
            addCriterion("ABBR_CNAME >", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameGreaterThanOrEqualTo(String value) {
            addCriterion("ABBR_CNAME >=", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameLessThan(String value) {
            addCriterion("ABBR_CNAME <", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameLessThanOrEqualTo(String value) {
            addCriterion("ABBR_CNAME <=", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameLike(String value) {
            addCriterion("ABBR_CNAME like", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameNotLike(String value) {
            addCriterion("ABBR_CNAME not like", value, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameIn(List<String> values) {
            addCriterion("ABBR_CNAME in", values, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameNotIn(List<String> values) {
            addCriterion("ABBR_CNAME not in", values, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameBetween(String value1, String value2) {
            addCriterion("ABBR_CNAME between", value1, value2, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrCnameNotBetween(String value1, String value2) {
            addCriterion("ABBR_CNAME not between", value1, value2, "abbrCname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameIsNull() {
            addCriterion("ABBR_ENAME is null");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameIsNotNull() {
            addCriterion("ABBR_ENAME is not null");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameEqualTo(String value) {
            addCriterion("ABBR_ENAME =", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameNotEqualTo(String value) {
            addCriterion("ABBR_ENAME <>", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameGreaterThan(String value) {
            addCriterion("ABBR_ENAME >", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameGreaterThanOrEqualTo(String value) {
            addCriterion("ABBR_ENAME >=", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameLessThan(String value) {
            addCriterion("ABBR_ENAME <", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameLessThanOrEqualTo(String value) {
            addCriterion("ABBR_ENAME <=", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameLike(String value) {
            addCriterion("ABBR_ENAME like", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameNotLike(String value) {
            addCriterion("ABBR_ENAME not like", value, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameIn(List<String> values) {
            addCriterion("ABBR_ENAME in", values, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameNotIn(List<String> values) {
            addCriterion("ABBR_ENAME not in", values, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameBetween(String value1, String value2) {
            addCriterion("ABBR_ENAME between", value1, value2, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAbbrEnameNotBetween(String value1, String value2) {
            addCriterion("ABBR_ENAME not between", value1, value2, "abbrEname");
            return (Criteria) this;
        }

        public Criteria andAddressChnIsNull() {
            addCriterion("ADDRESS_CHN is null");
            return (Criteria) this;
        }

        public Criteria andAddressChnIsNotNull() {
            addCriterion("ADDRESS_CHN is not null");
            return (Criteria) this;
        }

        public Criteria andAddressChnEqualTo(String value) {
            addCriterion("ADDRESS_CHN =", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnNotEqualTo(String value) {
            addCriterion("ADDRESS_CHN <>", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnGreaterThan(String value) {
            addCriterion("ADDRESS_CHN >", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS_CHN >=", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnLessThan(String value) {
            addCriterion("ADDRESS_CHN <", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS_CHN <=", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnLike(String value) {
            addCriterion("ADDRESS_CHN like", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnNotLike(String value) {
            addCriterion("ADDRESS_CHN not like", value, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnIn(List<String> values) {
            addCriterion("ADDRESS_CHN in", values, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnNotIn(List<String> values) {
            addCriterion("ADDRESS_CHN not in", values, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnBetween(String value1, String value2) {
            addCriterion("ADDRESS_CHN between", value1, value2, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressChnNotBetween(String value1, String value2) {
            addCriterion("ADDRESS_CHN not between", value1, value2, "addressChn");
            return (Criteria) this;
        }

        public Criteria andAddressEngIsNull() {
            addCriterion("ADDRESS_ENG is null");
            return (Criteria) this;
        }

        public Criteria andAddressEngIsNotNull() {
            addCriterion("ADDRESS_ENG is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEngEqualTo(String value) {
            addCriterion("ADDRESS_ENG =", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngNotEqualTo(String value) {
            addCriterion("ADDRESS_ENG <>", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngGreaterThan(String value) {
            addCriterion("ADDRESS_ENG >", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS_ENG >=", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngLessThan(String value) {
            addCriterion("ADDRESS_ENG <", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS_ENG <=", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngLike(String value) {
            addCriterion("ADDRESS_ENG like", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngNotLike(String value) {
            addCriterion("ADDRESS_ENG not like", value, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngIn(List<String> values) {
            addCriterion("ADDRESS_ENG in", values, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngNotIn(List<String> values) {
            addCriterion("ADDRESS_ENG not in", values, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngBetween(String value1, String value2) {
            addCriterion("ADDRESS_ENG between", value1, value2, "addressEng");
            return (Criteria) this;
        }

        public Criteria andAddressEngNotBetween(String value1, String value2) {
            addCriterion("ADDRESS_ENG not between", value1, value2, "addressEng");
            return (Criteria) this;
        }

        public Criteria andCityCnameIsNull() {
            addCriterion("CITY_CNAME is null");
            return (Criteria) this;
        }

        public Criteria andCityCnameIsNotNull() {
            addCriterion("CITY_CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityCnameEqualTo(String value) {
            addCriterion("CITY_CNAME =", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameNotEqualTo(String value) {
            addCriterion("CITY_CNAME <>", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameGreaterThan(String value) {
            addCriterion("CITY_CNAME >", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_CNAME >=", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameLessThan(String value) {
            addCriterion("CITY_CNAME <", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameLessThanOrEqualTo(String value) {
            addCriterion("CITY_CNAME <=", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameLike(String value) {
            addCriterion("CITY_CNAME like", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameNotLike(String value) {
            addCriterion("CITY_CNAME not like", value, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameIn(List<String> values) {
            addCriterion("CITY_CNAME in", values, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameNotIn(List<String> values) {
            addCriterion("CITY_CNAME not in", values, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameBetween(String value1, String value2) {
            addCriterion("CITY_CNAME between", value1, value2, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityCnameNotBetween(String value1, String value2) {
            addCriterion("CITY_CNAME not between", value1, value2, "cityCname");
            return (Criteria) this;
        }

        public Criteria andCityEnameIsNull() {
            addCriterion("CITY_ENAME is null");
            return (Criteria) this;
        }

        public Criteria andCityEnameIsNotNull() {
            addCriterion("CITY_ENAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityEnameEqualTo(String value) {
            addCriterion("CITY_ENAME =", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotEqualTo(String value) {
            addCriterion("CITY_ENAME <>", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameGreaterThan(String value) {
            addCriterion("CITY_ENAME >", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ENAME >=", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLessThan(String value) {
            addCriterion("CITY_ENAME <", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLessThanOrEqualTo(String value) {
            addCriterion("CITY_ENAME <=", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLike(String value) {
            addCriterion("CITY_ENAME like", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotLike(String value) {
            addCriterion("CITY_ENAME not like", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameIn(List<String> values) {
            addCriterion("CITY_ENAME in", values, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotIn(List<String> values) {
            addCriterion("CITY_ENAME not in", values, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameBetween(String value1, String value2) {
            addCriterion("CITY_ENAME between", value1, value2, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotBetween(String value1, String value2) {
            addCriterion("CITY_ENAME not between", value1, value2, "cityEname");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("FAX is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("FAX is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("FAX =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("FAX <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("FAX >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("FAX >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("FAX <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("FAX <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("FAX like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("FAX not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("FAX in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("FAX not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("FAX between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("FAX not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andManagerIsNull() {
            addCriterion("MANAGER is null");
            return (Criteria) this;
        }

        public Criteria andManagerIsNotNull() {
            addCriterion("MANAGER is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEqualTo(String value) {
            addCriterion("MANAGER =", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotEqualTo(String value) {
            addCriterion("MANAGER <>", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThan(String value) {
            addCriterion("MANAGER >", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER >=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThan(String value) {
            addCriterion("MANAGER <", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThanOrEqualTo(String value) {
            addCriterion("MANAGER <=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLike(String value) {
            addCriterion("MANAGER like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotLike(String value) {
            addCriterion("MANAGER not like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerIn(List<String> values) {
            addCriterion("MANAGER in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotIn(List<String> values) {
            addCriterion("MANAGER not in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerBetween(String value1, String value2) {
            addCriterion("MANAGER between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotBetween(String value1, String value2) {
            addCriterion("MANAGER not between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdIsNull() {
            addCriterion("SETTLE_MERCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdIsNotNull() {
            addCriterion("SETTLE_MERCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdEqualTo(String value) {
            addCriterion("SETTLE_MERCH_ID =", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdNotEqualTo(String value) {
            addCriterion("SETTLE_MERCH_ID <>", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdGreaterThan(String value) {
            addCriterion("SETTLE_MERCH_ID >", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLE_MERCH_ID >=", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdLessThan(String value) {
            addCriterion("SETTLE_MERCH_ID <", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdLessThanOrEqualTo(String value) {
            addCriterion("SETTLE_MERCH_ID <=", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdLike(String value) {
            addCriterion("SETTLE_MERCH_ID like", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdNotLike(String value) {
            addCriterion("SETTLE_MERCH_ID not like", value, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdIn(List<String> values) {
            addCriterion("SETTLE_MERCH_ID in", values, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdNotIn(List<String> values) {
            addCriterion("SETTLE_MERCH_ID not in", values, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdBetween(String value1, String value2) {
            addCriterion("SETTLE_MERCH_ID between", value1, value2, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSettleMerchIdNotBetween(String value1, String value2) {
            addCriterion("SETTLE_MERCH_ID not between", value1, value2, "settleMerchId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdIsNull() {
            addCriterion("SIGN_BANK_ID is null");
            return (Criteria) this;
        }

        public Criteria andSignBankIdIsNotNull() {
            addCriterion("SIGN_BANK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSignBankIdEqualTo(String value) {
            addCriterion("SIGN_BANK_ID =", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdNotEqualTo(String value) {
            addCriterion("SIGN_BANK_ID <>", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdGreaterThan(String value) {
            addCriterion("SIGN_BANK_ID >", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_BANK_ID >=", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdLessThan(String value) {
            addCriterion("SIGN_BANK_ID <", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdLessThanOrEqualTo(String value) {
            addCriterion("SIGN_BANK_ID <=", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdLike(String value) {
            addCriterion("SIGN_BANK_ID like", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdNotLike(String value) {
            addCriterion("SIGN_BANK_ID not like", value, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdIn(List<String> values) {
            addCriterion("SIGN_BANK_ID in", values, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdNotIn(List<String> values) {
            addCriterion("SIGN_BANK_ID not in", values, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdBetween(String value1, String value2) {
            addCriterion("SIGN_BANK_ID between", value1, value2, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignBankIdNotBetween(String value1, String value2) {
            addCriterion("SIGN_BANK_ID not between", value1, value2, "signBankId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdIsNull() {
            addCriterion("SIGN_HOST_ID is null");
            return (Criteria) this;
        }

        public Criteria andSignHostIdIsNotNull() {
            addCriterion("SIGN_HOST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSignHostIdEqualTo(String value) {
            addCriterion("SIGN_HOST_ID =", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdNotEqualTo(String value) {
            addCriterion("SIGN_HOST_ID <>", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdGreaterThan(String value) {
            addCriterion("SIGN_HOST_ID >", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_HOST_ID >=", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdLessThan(String value) {
            addCriterion("SIGN_HOST_ID <", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdLessThanOrEqualTo(String value) {
            addCriterion("SIGN_HOST_ID <=", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdLike(String value) {
            addCriterion("SIGN_HOST_ID like", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdNotLike(String value) {
            addCriterion("SIGN_HOST_ID not like", value, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdIn(List<String> values) {
            addCriterion("SIGN_HOST_ID in", values, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdNotIn(List<String> values) {
            addCriterion("SIGN_HOST_ID not in", values, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdBetween(String value1, String value2) {
            addCriterion("SIGN_HOST_ID between", value1, value2, "signHostId");
            return (Criteria) this;
        }

        public Criteria andSignHostIdNotBetween(String value1, String value2) {
            addCriterion("SIGN_HOST_ID not between", value1, value2, "signHostId");
            return (Criteria) this;
        }

        public Criteria andZbankIsNull() {
            addCriterion("ZBANK is null");
            return (Criteria) this;
        }

        public Criteria andZbankIsNotNull() {
            addCriterion("ZBANK is not null");
            return (Criteria) this;
        }

        public Criteria andZbankEqualTo(String value) {
            addCriterion("ZBANK =", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankNotEqualTo(String value) {
            addCriterion("ZBANK <>", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankGreaterThan(String value) {
            addCriterion("ZBANK >", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankGreaterThanOrEqualTo(String value) {
            addCriterion("ZBANK >=", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankLessThan(String value) {
            addCriterion("ZBANK <", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankLessThanOrEqualTo(String value) {
            addCriterion("ZBANK <=", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankLike(String value) {
            addCriterion("ZBANK like", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankNotLike(String value) {
            addCriterion("ZBANK not like", value, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankIn(List<String> values) {
            addCriterion("ZBANK in", values, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankNotIn(List<String> values) {
            addCriterion("ZBANK not in", values, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankBetween(String value1, String value2) {
            addCriterion("ZBANK between", value1, value2, "zbank");
            return (Criteria) this;
        }

        public Criteria andZbankNotBetween(String value1, String value2) {
            addCriterion("ZBANK not between", value1, value2, "zbank");
            return (Criteria) this;
        }

        public Criteria andCcyTypeIsNull() {
            addCriterion("CCY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCcyTypeIsNotNull() {
            addCriterion("CCY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCcyTypeEqualTo(String value) {
            addCriterion("CCY_TYPE =", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeNotEqualTo(String value) {
            addCriterion("CCY_TYPE <>", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeGreaterThan(String value) {
            addCriterion("CCY_TYPE >", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CCY_TYPE >=", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeLessThan(String value) {
            addCriterion("CCY_TYPE <", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeLessThanOrEqualTo(String value) {
            addCriterion("CCY_TYPE <=", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeLike(String value) {
            addCriterion("CCY_TYPE like", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeNotLike(String value) {
            addCriterion("CCY_TYPE not like", value, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeIn(List<String> values) {
            addCriterion("CCY_TYPE in", values, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeNotIn(List<String> values) {
            addCriterion("CCY_TYPE not in", values, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeBetween(String value1, String value2) {
            addCriterion("CCY_TYPE between", value1, value2, "ccyType");
            return (Criteria) this;
        }

        public Criteria andCcyTypeNotBetween(String value1, String value2) {
            addCriterion("CCY_TYPE not between", value1, value2, "ccyType");
            return (Criteria) this;
        }

        public Criteria andLockModeIsNull() {
            addCriterion("LOCK_MODE is null");
            return (Criteria) this;
        }

        public Criteria andLockModeIsNotNull() {
            addCriterion("LOCK_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andLockModeEqualTo(String value) {
            addCriterion("LOCK_MODE =", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeNotEqualTo(String value) {
            addCriterion("LOCK_MODE <>", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeGreaterThan(String value) {
            addCriterion("LOCK_MODE >", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_MODE >=", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeLessThan(String value) {
            addCriterion("LOCK_MODE <", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeLessThanOrEqualTo(String value) {
            addCriterion("LOCK_MODE <=", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeLike(String value) {
            addCriterion("LOCK_MODE like", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeNotLike(String value) {
            addCriterion("LOCK_MODE not like", value, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeIn(List<String> values) {
            addCriterion("LOCK_MODE in", values, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeNotIn(List<String> values) {
            addCriterion("LOCK_MODE not in", values, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeBetween(String value1, String value2) {
            addCriterion("LOCK_MODE between", value1, value2, "lockMode");
            return (Criteria) this;
        }

        public Criteria andLockModeNotBetween(String value1, String value2) {
            addCriterion("LOCK_MODE not between", value1, value2, "lockMode");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNull() {
            addCriterion("SIGN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNotNull() {
            addCriterion("SIGN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEqualTo(String value) {
            addCriterion("SIGN_DATE =", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotEqualTo(String value) {
            addCriterion("SIGN_DATE <>", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThan(String value) {
            addCriterion("SIGN_DATE >", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DATE >=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThan(String value) {
            addCriterion("SIGN_DATE <", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DATE <=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLike(String value) {
            addCriterion("SIGN_DATE like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotLike(String value) {
            addCriterion("SIGN_DATE not like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateIn(List<String> values) {
            addCriterion("SIGN_DATE in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotIn(List<String> values) {
            addCriterion("SIGN_DATE not in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateBetween(String value1, String value2) {
            addCriterion("SIGN_DATE between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotBetween(String value1, String value2) {
            addCriterion("SIGN_DATE not between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andMerchantStatIsNull() {
            addCriterion("MERCHANT_STAT is null");
            return (Criteria) this;
        }

        public Criteria andMerchantStatIsNotNull() {
            addCriterion("MERCHANT_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantStatEqualTo(String value) {
            addCriterion("MERCHANT_STAT =", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatNotEqualTo(String value) {
            addCriterion("MERCHANT_STAT <>", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatGreaterThan(String value) {
            addCriterion("MERCHANT_STAT >", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_STAT >=", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatLessThan(String value) {
            addCriterion("MERCHANT_STAT <", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_STAT <=", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatLike(String value) {
            addCriterion("MERCHANT_STAT like", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatNotLike(String value) {
            addCriterion("MERCHANT_STAT not like", value, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatIn(List<String> values) {
            addCriterion("MERCHANT_STAT in", values, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatNotIn(List<String> values) {
            addCriterion("MERCHANT_STAT not in", values, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatBetween(String value1, String value2) {
            addCriterion("MERCHANT_STAT between", value1, value2, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andMerchantStatNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_STAT not between", value1, value2, "merchantStat");
            return (Criteria) this;
        }

        public Criteria andSettleModeIsNull() {
            addCriterion("SETTLE_MODE is null");
            return (Criteria) this;
        }

        public Criteria andSettleModeIsNotNull() {
            addCriterion("SETTLE_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andSettleModeEqualTo(String value) {
            addCriterion("SETTLE_MODE =", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotEqualTo(String value) {
            addCriterion("SETTLE_MODE <>", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeGreaterThan(String value) {
            addCriterion("SETTLE_MODE >", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLE_MODE >=", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeLessThan(String value) {
            addCriterion("SETTLE_MODE <", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeLessThanOrEqualTo(String value) {
            addCriterion("SETTLE_MODE <=", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeLike(String value) {
            addCriterion("SETTLE_MODE like", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotLike(String value) {
            addCriterion("SETTLE_MODE not like", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeIn(List<String> values) {
            addCriterion("SETTLE_MODE in", values, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotIn(List<String> values) {
            addCriterion("SETTLE_MODE not in", values, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeBetween(String value1, String value2) {
            addCriterion("SETTLE_MODE between", value1, value2, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotBetween(String value1, String value2) {
            addCriterion("SETTLE_MODE not between", value1, value2, "settleMode");
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