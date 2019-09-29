package cn.yufu.SDMTPlatform.entity;

import java.util.ArrayList;
import java.util.List;

public class MerchantSDMTExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantSDMTExample() {
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

        public Criteria andisTerminalAddFlagIsNull() {
        	addCriterion("IS_TERMINAL_ADD_FLAG is null");
        	return (Criteria) this;
        }
        
        public Criteria andisTerminalAddFlagIsNotNull() {
        	addCriterion("IS_TERMINAL_ADD_FLAG is not null");
        	return (Criteria) this;
        }
        
        public Criteria andisTerminalAddFlagEqualTo(String value) {
        	addCriterion("IS_TERMINAL_ADD_FLAG =", value, "isTerminalAddFlag");
        	return (Criteria) this;
        }
        
        public Criteria andisTerminalAddFlagNotEqualTo(String value) {
        	addCriterion("IS_TERMINAL_ADD_FLAG <>", value, "isTerminalAddFlag");
        	return (Criteria) this;
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

        public Criteria andMccIdIsNull() {
            addCriterion("MCC_ID is null");
            return (Criteria) this;
        }

        public Criteria andMccIdIsNotNull() {
            addCriterion("MCC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMccIdEqualTo(String value) {
            addCriterion("MCC_ID =", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdNotEqualTo(String value) {
            addCriterion("MCC_ID <>", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdGreaterThan(String value) {
            addCriterion("MCC_ID >", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdGreaterThanOrEqualTo(String value) {
            addCriterion("MCC_ID >=", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdLessThan(String value) {
            addCriterion("MCC_ID <", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdLessThanOrEqualTo(String value) {
            addCriterion("MCC_ID <=", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdLike(String value) {
            addCriterion("MCC_ID like", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdNotLike(String value) {
            addCriterion("MCC_ID not like", value, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdIn(List<String> values) {
            addCriterion("MCC_ID in", values, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdNotIn(List<String> values) {
            addCriterion("MCC_ID not in", values, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdBetween(String value1, String value2) {
            addCriterion("MCC_ID between", value1, value2, "mccId");
            return (Criteria) this;
        }

        public Criteria andMccIdNotBetween(String value1, String value2) {
            addCriterion("MCC_ID not between", value1, value2, "mccId");
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

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
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

        public Criteria andAddDateIsNull() {
            addCriterion("ADD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAddDateIsNotNull() {
            addCriterion("ADD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAddDateEqualTo(Long value) {
            addCriterion("ADD_DATE =", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotEqualTo(Long value) {
            addCriterion("ADD_DATE <>", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThan(Long value) {
            addCriterion("ADD_DATE >", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThanOrEqualTo(Long value) {
            addCriterion("ADD_DATE >=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThan(Long value) {
            addCriterion("ADD_DATE <", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThanOrEqualTo(Long value) {
            addCriterion("ADD_DATE <=", value, "addDate");
            return (Criteria) this;
        }
        public Criteria andAddDateEndLessThanOrEqualTo(Long value) {
            addCriterion("ADD_DATE <=", value, "addDateEnd");
            return (Criteria) this;
        }
        
        public Criteria andAddDateIn(List<Long> values) {
            addCriterion("ADD_DATE in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotIn(List<Long> values) {
            addCriterion("ADD_DATE not in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateBetween(Long value1, Long value2) {
            addCriterion("ADD_DATE between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotBetween(Long value1, Long value2) {
            addCriterion("ADD_DATE not between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdIsNull() {
            addCriterion("MRCH_ACC_X_ID is null");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdIsNotNull() {
            addCriterion("MRCH_ACC_X_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdEqualTo(Long value) {
            addCriterion("MRCH_ACC_X_ID =", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdNotEqualTo(Long value) {
            addCriterion("MRCH_ACC_X_ID <>", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdGreaterThan(Long value) {
            addCriterion("MRCH_ACC_X_ID >", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MRCH_ACC_X_ID >=", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdLessThan(Long value) {
            addCriterion("MRCH_ACC_X_ID <", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdLessThanOrEqualTo(Long value) {
            addCriterion("MRCH_ACC_X_ID <=", value, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdIn(List<Long> values) {
            addCriterion("MRCH_ACC_X_ID in", values, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdNotIn(List<Long> values) {
            addCriterion("MRCH_ACC_X_ID not in", values, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdBetween(Long value1, Long value2) {
            addCriterion("MRCH_ACC_X_ID between", value1, value2, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andMrchAccXIdNotBetween(Long value1, Long value2) {
            addCriterion("MRCH_ACC_X_ID not between", value1, value2, "mrchAccXId");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNull() {
            addCriterion("ACC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNotNull() {
            addCriterion("ACC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccNameEqualTo(String value) {
            addCriterion("ACC_NAME =", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotEqualTo(String value) {
            addCriterion("ACC_NAME <>", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThan(String value) {
            addCriterion("ACC_NAME >", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NAME >=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThan(String value) {
            addCriterion("ACC_NAME <", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_NAME <=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLike(String value) {
            addCriterion("ACC_NAME like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotLike(String value) {
            addCriterion("ACC_NAME not like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameIn(List<String> values) {
            addCriterion("ACC_NAME in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotIn(List<String> values) {
            addCriterion("ACC_NAME not in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameBetween(String value1, String value2) {
            addCriterion("ACC_NAME between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotBetween(String value1, String value2) {
            addCriterion("ACC_NAME not between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNoIsNull() {
            addCriterion("ACC_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccNoIsNotNull() {
            addCriterion("ACC_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccNoEqualTo(String value) {
            addCriterion("ACC_NO =", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotEqualTo(String value) {
            addCriterion("ACC_NO <>", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThan(String value) {
            addCriterion("ACC_NO >", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NO >=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThan(String value) {
            addCriterion("ACC_NO <", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThanOrEqualTo(String value) {
            addCriterion("ACC_NO <=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLike(String value) {
            addCriterion("ACC_NO like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotLike(String value) {
            addCriterion("ACC_NO not like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoIn(List<String> values) {
            addCriterion("ACC_NO in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotIn(List<String> values) {
            addCriterion("ACC_NO not in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoBetween(String value1, String value2) {
            addCriterion("ACC_NO between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotBetween(String value1, String value2) {
            addCriterion("ACC_NO not between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIsNull() {
            addCriterion("SHORT_NICK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIsNotNull() {
            addCriterion("SHORT_NICK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShortNickNameEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME =", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME <>", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameGreaterThan(String value) {
            addCriterion("SHORT_NICK_NAME >", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME >=", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLessThan(String value) {
            addCriterion("SHORT_NICK_NAME <", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLessThanOrEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME <=", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLike(String value) {
            addCriterion("SHORT_NICK_NAME like", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotLike(String value) {
            addCriterion("SHORT_NICK_NAME not like", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIn(List<String> values) {
            addCriterion("SHORT_NICK_NAME in", values, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotIn(List<String> values) {
            addCriterion("SHORT_NICK_NAME not in", values, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameBetween(String value1, String value2) {
            addCriterion("SHORT_NICK_NAME between", value1, value2, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotBetween(String value1, String value2) {
            addCriterion("SHORT_NICK_NAME not between", value1, value2, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIsNull() {
            addCriterion("ACC_NICK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIsNotNull() {
            addCriterion("ACC_NICK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccNickNameEqualTo(String value) {
            addCriterion("ACC_NICK_NAME =", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotEqualTo(String value) {
            addCriterion("ACC_NICK_NAME <>", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameGreaterThan(String value) {
            addCriterion("ACC_NICK_NAME >", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NICK_NAME >=", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLessThan(String value) {
            addCriterion("ACC_NICK_NAME <", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_NICK_NAME <=", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLike(String value) {
            addCriterion("ACC_NICK_NAME like", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotLike(String value) {
            addCriterion("ACC_NICK_NAME not like", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIn(List<String> values) {
            addCriterion("ACC_NICK_NAME in", values, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotIn(List<String> values) {
            addCriterion("ACC_NICK_NAME not in", values, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameBetween(String value1, String value2) {
            addCriterion("ACC_NICK_NAME between", value1, value2, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotBetween(String value1, String value2) {
            addCriterion("ACC_NICK_NAME not between", value1, value2, "accNickName");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNull() {
            addCriterion("BANK_NO is null");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNotNull() {
            addCriterion("BANK_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBankNoEqualTo(String value) {
            addCriterion("BANK_NO =", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotEqualTo(String value) {
            addCriterion("BANK_NO <>", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThan(String value) {
            addCriterion("BANK_NO >", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NO >=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThan(String value) {
            addCriterion("BANK_NO <", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThanOrEqualTo(String value) {
            addCriterion("BANK_NO <=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLike(String value) {
            addCriterion("BANK_NO like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotLike(String value) {
            addCriterion("BANK_NO not like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoIn(List<String> values) {
            addCriterion("BANK_NO in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotIn(List<String> values) {
            addCriterion("BANK_NO not in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoBetween(String value1, String value2) {
            addCriterion("BANK_NO between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotBetween(String value1, String value2) {
            addCriterion("BANK_NO not between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andIndividualIsNull() {
            addCriterion("INDIVIDUAL is null");
            return (Criteria) this;
        }

        public Criteria andIndividualIsNotNull() {
            addCriterion("INDIVIDUAL is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualEqualTo(String value) {
            addCriterion("INDIVIDUAL =", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotEqualTo(String value) {
            addCriterion("INDIVIDUAL <>", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualGreaterThan(String value) {
            addCriterion("INDIVIDUAL >", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualGreaterThanOrEqualTo(String value) {
            addCriterion("INDIVIDUAL >=", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLessThan(String value) {
            addCriterion("INDIVIDUAL <", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLessThanOrEqualTo(String value) {
            addCriterion("INDIVIDUAL <=", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLike(String value) {
            addCriterion("INDIVIDUAL like", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotLike(String value) {
            addCriterion("INDIVIDUAL not like", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualIn(List<String> values) {
            addCriterion("INDIVIDUAL in", values, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotIn(List<String> values) {
            addCriterion("INDIVIDUAL not in", values, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualBetween(String value1, String value2) {
            addCriterion("INDIVIDUAL between", value1, value2, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotBetween(String value1, String value2) {
            addCriterion("INDIVIDUAL not between", value1, value2, "individual");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIsNull() {
            addCriterion("LAST_SETTLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIsNotNull() {
            addCriterion("LAST_SETTLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateEqualTo(Integer value) {
            addCriterion("LAST_SETTLE_DATE =", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotEqualTo(Integer value) {
            addCriterion("LAST_SETTLE_DATE <>", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThan(Integer value) {
            addCriterion("LAST_SETTLE_DATE >", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("LAST_SETTLE_DATE >=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThan(Integer value) {
            addCriterion("LAST_SETTLE_DATE <", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThanOrEqualTo(Integer value) {
            addCriterion("LAST_SETTLE_DATE <=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIn(List<Integer> values) {
            addCriterion("LAST_SETTLE_DATE in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotIn(List<Integer> values) {
            addCriterion("LAST_SETTLE_DATE not in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateBetween(Integer value1, Integer value2) {
            addCriterion("LAST_SETTLE_DATE between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotBetween(Integer value1, Integer value2) {
            addCriterion("LAST_SETTLE_DATE not between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNull() {
            addCriterion("INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNotNull() {
            addCriterion("INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdEqualTo(Long value) {
            addCriterion("INST_ID =", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotEqualTo(Long value) {
            addCriterion("INST_ID <>", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThan(Long value) {
            addCriterion("INST_ID >", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INST_ID >=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThan(Long value) {
            addCriterion("INST_ID <", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThanOrEqualTo(Long value) {
            addCriterion("INST_ID <=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdIn(List<Long> values) {
            addCriterion("INST_ID in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotIn(List<Long> values) {
            addCriterion("INST_ID not in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdBetween(Long value1, Long value2) {
            addCriterion("INST_ID between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotBetween(Long value1, Long value2) {
            addCriterion("INST_ID not between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andBrnCodeIsNull() {
            addCriterion("BRN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBrnCodeIsNotNull() {
            addCriterion("BRN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBrnCodeEqualTo(String value) {
            addCriterion("BRN_CODE =", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeNotEqualTo(String value) {
            addCriterion("BRN_CODE <>", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeGreaterThan(String value) {
            addCriterion("BRN_CODE >", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BRN_CODE >=", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeLessThan(String value) {
            addCriterion("BRN_CODE <", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeLessThanOrEqualTo(String value) {
            addCriterion("BRN_CODE <=", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeLike(String value) {
            addCriterion("BRN_CODE like", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeNotLike(String value) {
            addCriterion("BRN_CODE not like", value, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeIn(List<String> values) {
            addCriterion("BRN_CODE in", values, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeNotIn(List<String> values) {
            addCriterion("BRN_CODE not in", values, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeBetween(String value1, String value2) {
            addCriterion("BRN_CODE between", value1, value2, "brnCode");
            return (Criteria) this;
        }

        public Criteria andBrnCodeNotBetween(String value1, String value2) {
            addCriterion("BRN_CODE not between", value1, value2, "brnCode");
            return (Criteria) this;
        }

        public Criteria andOfficialIsNull() {
            addCriterion("OFFICIAL is null");
            return (Criteria) this;
        }

        public Criteria andOfficialIsNotNull() {
            addCriterion("OFFICIAL is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialEqualTo(String value) {
            addCriterion("OFFICIAL =", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialNotEqualTo(String value) {
            addCriterion("OFFICIAL <>", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialGreaterThan(String value) {
            addCriterion("OFFICIAL >", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICIAL >=", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialLessThan(String value) {
            addCriterion("OFFICIAL <", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialLessThanOrEqualTo(String value) {
            addCriterion("OFFICIAL <=", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialLike(String value) {
            addCriterion("OFFICIAL like", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialNotLike(String value) {
            addCriterion("OFFICIAL not like", value, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialIn(List<String> values) {
            addCriterion("OFFICIAL in", values, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialNotIn(List<String> values) {
            addCriterion("OFFICIAL not in", values, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialBetween(String value1, String value2) {
            addCriterion("OFFICIAL between", value1, value2, "official");
            return (Criteria) this;
        }

        public Criteria andOfficialNotBetween(String value1, String value2) {
            addCriterion("OFFICIAL not between", value1, value2, "official");
            return (Criteria) this;
        }

        public Criteria andTypeYfIsNull() {
            addCriterion("TYPE_YF is null");
            return (Criteria) this;
        }

        public Criteria andTypeYfIsNotNull() {
            addCriterion("TYPE_YF is not null");
            return (Criteria) this;
        }

        public Criteria andTypeYfEqualTo(Integer value) {
            addCriterion("TYPE_YF =", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfNotEqualTo(Integer value) {
            addCriterion("TYPE_YF <>", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfGreaterThan(Integer value) {
            addCriterion("TYPE_YF >", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfGreaterThanOrEqualTo(Integer value) {
            addCriterion("TYPE_YF >=", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfLessThan(Integer value) {
            addCriterion("TYPE_YF <", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfLessThanOrEqualTo(Integer value) {
            addCriterion("TYPE_YF <=", value, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfIn(List<Integer> values) {
            addCriterion("TYPE_YF in", values, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfNotIn(List<Integer> values) {
            addCriterion("TYPE_YF not in", values, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfBetween(Integer value1, Integer value2) {
            addCriterion("TYPE_YF between", value1, value2, "typeYf");
            return (Criteria) this;
        }

        public Criteria andTypeYfNotBetween(Integer value1, Integer value2) {
            addCriterion("TYPE_YF not between", value1, value2, "typeYf");
            return (Criteria) this;
        }

        public Criteria andAgentIsNull() {
            addCriterion("AGENT is null");
            return (Criteria) this;
        }

        public Criteria andAgentIsNotNull() {
            addCriterion("AGENT is not null");
            return (Criteria) this;
        }

        public Criteria andAgentEqualTo(String value) {
            addCriterion("AGENT =", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotEqualTo(String value) {
            addCriterion("AGENT <>", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThan(String value) {
            addCriterion("AGENT >", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT >=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThan(String value) {
            addCriterion("AGENT <", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThanOrEqualTo(String value) {
            addCriterion("AGENT <=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLike(String value) {
            addCriterion("AGENT like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotLike(String value) {
            addCriterion("AGENT not like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentIn(List<String> values) {
            addCriterion("AGENT in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotIn(List<String> values) {
            addCriterion("AGENT not in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentBetween(String value1, String value2) {
            addCriterion("AGENT between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotBetween(String value1, String value2) {
            addCriterion("AGENT not between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andIdTypeIsNull() {
            addCriterion("ID_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIdTypeIsNotNull() {
            addCriterion("ID_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIdTypeEqualTo(Integer value) {
            addCriterion("ID_TYPE =", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotEqualTo(Integer value) {
            addCriterion("ID_TYPE <>", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThan(Integer value) {
            addCriterion("ID_TYPE >", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID_TYPE >=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThan(Integer value) {
            addCriterion("ID_TYPE <", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ID_TYPE <=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeIn(List<Integer> values) {
            addCriterion("ID_TYPE in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotIn(List<Integer> values) {
            addCriterion("ID_TYPE not in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeBetween(Integer value1, Integer value2) {
            addCriterion("ID_TYPE between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ID_TYPE not between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdValidityIsNull() {
            addCriterion("ID_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andIdValidityIsNotNull() {
            addCriterion("ID_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andIdValidityEqualTo(Integer value) {
            addCriterion("ID_VALIDITY =", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotEqualTo(Integer value) {
            addCriterion("ID_VALIDITY <>", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityGreaterThan(Integer value) {
            addCriterion("ID_VALIDITY >", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID_VALIDITY >=", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityLessThan(Integer value) {
            addCriterion("ID_VALIDITY <", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityLessThanOrEqualTo(Integer value) {
            addCriterion("ID_VALIDITY <=", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityIn(List<Integer> values) {
            addCriterion("ID_VALIDITY in", values, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotIn(List<Integer> values) {
            addCriterion("ID_VALIDITY not in", values, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityBetween(Integer value1, Integer value2) {
            addCriterion("ID_VALIDITY between", value1, value2, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("ID_VALIDITY not between", value1, value2, "idValidity");
            return (Criteria) this;
        }

        public Criteria andLegalRepIsNull() {
            addCriterion("LEGAL_REP is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepIsNotNull() {
            addCriterion("LEGAL_REP is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepEqualTo(String value) {
            addCriterion("LEGAL_REP =", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepNotEqualTo(String value) {
            addCriterion("LEGAL_REP <>", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepGreaterThan(String value) {
            addCriterion("LEGAL_REP >", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_REP >=", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepLessThan(String value) {
            addCriterion("LEGAL_REP <", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_REP <=", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepLike(String value) {
            addCriterion("LEGAL_REP like", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepNotLike(String value) {
            addCriterion("LEGAL_REP not like", value, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepIn(List<String> values) {
            addCriterion("LEGAL_REP in", values, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepNotIn(List<String> values) {
            addCriterion("LEGAL_REP not in", values, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepBetween(String value1, String value2) {
            addCriterion("LEGAL_REP between", value1, value2, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLegalRepNotBetween(String value1, String value2) {
            addCriterion("LEGAL_REP not between", value1, value2, "legalRep");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeIsNull() {
            addCriterion("LR_ID_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeIsNotNull() {
            addCriterion("LR_ID_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeEqualTo(Integer value) {
            addCriterion("LR_ID_TYPE =", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeNotEqualTo(Integer value) {
            addCriterion("LR_ID_TYPE <>", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeGreaterThan(Integer value) {
            addCriterion("LR_ID_TYPE >", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("LR_ID_TYPE >=", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeLessThan(Integer value) {
            addCriterion("LR_ID_TYPE <", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeLessThanOrEqualTo(Integer value) {
            addCriterion("LR_ID_TYPE <=", value, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeIn(List<Integer> values) {
            addCriterion("LR_ID_TYPE in", values, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeNotIn(List<Integer> values) {
            addCriterion("LR_ID_TYPE not in", values, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeBetween(Integer value1, Integer value2) {
            addCriterion("LR_ID_TYPE between", value1, value2, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("LR_ID_TYPE not between", value1, value2, "lrIdType");
            return (Criteria) this;
        }

        public Criteria andLrIdNoIsNull() {
            addCriterion("LR_ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andLrIdNoIsNotNull() {
            addCriterion("LR_ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andLrIdNoEqualTo(String value) {
            addCriterion("LR_ID_NO =", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoNotEqualTo(String value) {
            addCriterion("LR_ID_NO <>", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoGreaterThan(String value) {
            addCriterion("LR_ID_NO >", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("LR_ID_NO >=", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoLessThan(String value) {
            addCriterion("LR_ID_NO <", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoLessThanOrEqualTo(String value) {
            addCriterion("LR_ID_NO <=", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoLike(String value) {
            addCriterion("LR_ID_NO like", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoNotLike(String value) {
            addCriterion("LR_ID_NO not like", value, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoIn(List<String> values) {
            addCriterion("LR_ID_NO in", values, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoNotIn(List<String> values) {
            addCriterion("LR_ID_NO not in", values, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoBetween(String value1, String value2) {
            addCriterion("LR_ID_NO between", value1, value2, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdNoNotBetween(String value1, String value2) {
            addCriterion("LR_ID_NO not between", value1, value2, "lrIdNo");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityIsNull() {
            addCriterion("LR_ID_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityIsNotNull() {
            addCriterion("LR_ID_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityEqualTo(Integer value) {
            addCriterion("LR_ID_VALIDITY =", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotEqualTo(Integer value) {
            addCriterion("LR_ID_VALIDITY <>", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityGreaterThan(Integer value) {
            addCriterion("LR_ID_VALIDITY >", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("LR_ID_VALIDITY >=", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityLessThan(Integer value) {
            addCriterion("LR_ID_VALIDITY <", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityLessThanOrEqualTo(Integer value) {
            addCriterion("LR_ID_VALIDITY <=", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityIn(List<Integer> values) {
            addCriterion("LR_ID_VALIDITY in", values, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotIn(List<Integer> values) {
            addCriterion("LR_ID_VALIDITY not in", values, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityBetween(Integer value1, Integer value2) {
            addCriterion("LR_ID_VALIDITY between", value1, value2, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("LR_ID_VALIDITY not between", value1, value2, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicNoIsNull() {
            addCriterion("BUS_LIC_NO is null");
            return (Criteria) this;
        }

        public Criteria andBusLicNoIsNotNull() {
            addCriterion("BUS_LIC_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBusLicNoEqualTo(String value) {
            addCriterion("BUS_LIC_NO =", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoNotEqualTo(String value) {
            addCriterion("BUS_LIC_NO <>", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoGreaterThan(String value) {
            addCriterion("BUS_LIC_NO >", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoGreaterThanOrEqualTo(String value) {
            addCriterion("BUS_LIC_NO >=", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoLessThan(String value) {
            addCriterion("BUS_LIC_NO <", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoLessThanOrEqualTo(String value) {
            addCriterion("BUS_LIC_NO <=", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoLike(String value) {
            addCriterion("BUS_LIC_NO like", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoNotLike(String value) {
            addCriterion("BUS_LIC_NO not like", value, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoIn(List<String> values) {
            addCriterion("BUS_LIC_NO in", values, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoNotIn(List<String> values) {
            addCriterion("BUS_LIC_NO not in", values, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoBetween(String value1, String value2) {
            addCriterion("BUS_LIC_NO between", value1, value2, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicNoNotBetween(String value1, String value2) {
            addCriterion("BUS_LIC_NO not between", value1, value2, "busLicNo");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityIsNull() {
            addCriterion("BUS_LIC_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityIsNotNull() {
            addCriterion("BUS_LIC_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityEqualTo(Integer value) {
            addCriterion("BUS_LIC_VALIDITY =", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotEqualTo(Integer value) {
            addCriterion("BUS_LIC_VALIDITY <>", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityGreaterThan(Integer value) {
            addCriterion("BUS_LIC_VALIDITY >", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUS_LIC_VALIDITY >=", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityLessThan(Integer value) {
            addCriterion("BUS_LIC_VALIDITY <", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityLessThanOrEqualTo(Integer value) {
            addCriterion("BUS_LIC_VALIDITY <=", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityIn(List<Integer> values) {
            addCriterion("BUS_LIC_VALIDITY in", values, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotIn(List<Integer> values) {
            addCriterion("BUS_LIC_VALIDITY not in", values, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityBetween(Integer value1, Integer value2) {
            addCriterion("BUS_LIC_VALIDITY between", value1, value2, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("BUS_LIC_VALIDITY not between", value1, value2, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdIsNull() {
            addCriterion("TAX_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdIsNotNull() {
            addCriterion("TAX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdEqualTo(String value) {
            addCriterion("TAX_ID =", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotEqualTo(String value) {
            addCriterion("TAX_ID <>", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThan(String value) {
            addCriterion("TAX_ID >", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_ID >=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThan(String value) {
            addCriterion("TAX_ID <", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThanOrEqualTo(String value) {
            addCriterion("TAX_ID <=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLike(String value) {
            addCriterion("TAX_ID like", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotLike(String value) {
            addCriterion("TAX_ID not like", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdIn(List<String> values) {
            addCriterion("TAX_ID in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotIn(List<String> values) {
            addCriterion("TAX_ID not in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdBetween(String value1, String value2) {
            addCriterion("TAX_ID between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotBetween(String value1, String value2) {
            addCriterion("TAX_ID not between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityIsNull() {
            addCriterion("TAX_ID_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityIsNotNull() {
            addCriterion("TAX_ID_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityEqualTo(Integer value) {
            addCriterion("TAX_ID_VALIDITY =", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotEqualTo(Integer value) {
            addCriterion("TAX_ID_VALIDITY <>", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityGreaterThan(Integer value) {
            addCriterion("TAX_ID_VALIDITY >", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("TAX_ID_VALIDITY >=", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityLessThan(Integer value) {
            addCriterion("TAX_ID_VALIDITY <", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityLessThanOrEqualTo(Integer value) {
            addCriterion("TAX_ID_VALIDITY <=", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityIn(List<Integer> values) {
            addCriterion("TAX_ID_VALIDITY in", values, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotIn(List<Integer> values) {
            addCriterion("TAX_ID_VALIDITY not in", values, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityBetween(Integer value1, Integer value2) {
            addCriterion("TAX_ID_VALIDITY between", value1, value2, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("TAX_ID_VALIDITY not between", value1, value2, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgValidityIsNull() {
            addCriterion("ORG_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andOrgValidityIsNotNull() {
            addCriterion("ORG_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andOrgValidityEqualTo(Integer value) {
            addCriterion("ORG_VALIDITY =", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotEqualTo(Integer value) {
            addCriterion("ORG_VALIDITY <>", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityGreaterThan(Integer value) {
            addCriterion("ORG_VALIDITY >", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORG_VALIDITY >=", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityLessThan(Integer value) {
            addCriterion("ORG_VALIDITY <", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityLessThanOrEqualTo(Integer value) {
            addCriterion("ORG_VALIDITY <=", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityIn(List<Integer> values) {
            addCriterion("ORG_VALIDITY in", values, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotIn(List<Integer> values) {
            addCriterion("ORG_VALIDITY not in", values, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityBetween(Integer value1, Integer value2) {
            addCriterion("ORG_VALIDITY between", value1, value2, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("ORG_VALIDITY not between", value1, value2, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoIsNull() {
            addCriterion("ENTERPRISE_NO is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoIsNotNull() {
            addCriterion("ENTERPRISE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoEqualTo(String value) {
            addCriterion("ENTERPRISE_NO =", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoNotEqualTo(String value) {
            addCriterion("ENTERPRISE_NO <>", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoGreaterThan(String value) {
            addCriterion("ENTERPRISE_NO >", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoGreaterThanOrEqualTo(String value) {
            addCriterion("ENTERPRISE_NO >=", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoLessThan(String value) {
            addCriterion("ENTERPRISE_NO <", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoLessThanOrEqualTo(String value) {
            addCriterion("ENTERPRISE_NO <=", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoLike(String value) {
            addCriterion("ENTERPRISE_NO like", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoNotLike(String value) {
            addCriterion("ENTERPRISE_NO not like", value, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoIn(List<String> values) {
            addCriterion("ENTERPRISE_NO in", values, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoNotIn(List<String> values) {
            addCriterion("ENTERPRISE_NO not in", values, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoBetween(String value1, String value2) {
            addCriterion("ENTERPRISE_NO between", value1, value2, "enterpriseNo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNoNotBetween(String value1, String value2) {
            addCriterion("ENTERPRISE_NO not between", value1, value2, "enterpriseNo");
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

        public Criteria andSeqMrchIdIsNull() {
            addCriterion("SEQ_MRCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdIsNotNull() {
            addCriterion("SEQ_MRCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ID =", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdNotEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ID <>", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdGreaterThan(Long value) {
            addCriterion("SEQ_MRCH_ID >", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ID >=", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdLessThan(Long value) {
            addCriterion("SEQ_MRCH_ID <", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdLessThanOrEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ID <=", value, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdIn(List<Long> values) {
            addCriterion("SEQ_MRCH_ID in", values, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdNotIn(List<Long> values) {
            addCriterion("SEQ_MRCH_ID not in", values, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdBetween(Long value1, Long value2) {
            addCriterion("SEQ_MRCH_ID between", value1, value2, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchIdNotBetween(Long value1, Long value2) {
            addCriterion("SEQ_MRCH_ID not between", value1, value2, "seqMrchId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdIsNull() {
            addCriterion("SEQ_MRCH_ACC_X_ID is null");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdIsNotNull() {
            addCriterion("SEQ_MRCH_ACC_X_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID =", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdNotEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID <>", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdGreaterThan(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID >", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID >=", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdLessThan(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID <", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdLessThanOrEqualTo(Long value) {
            addCriterion("SEQ_MRCH_ACC_X_ID <=", value, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdIn(List<Long> values) {
            addCriterion("SEQ_MRCH_ACC_X_ID in", values, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdNotIn(List<Long> values) {
            addCriterion("SEQ_MRCH_ACC_X_ID not in", values, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdBetween(Long value1, Long value2) {
            addCriterion("SEQ_MRCH_ACC_X_ID between", value1, value2, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andSeqMrchAccXIdNotBetween(Long value1, Long value2) {
            addCriterion("SEQ_MRCH_ACC_X_ID not between", value1, value2, "seqMrchAccXId");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIsNull() {
            addCriterion("IS_BJ_ACCT is null");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIsNotNull() {
            addCriterion("IS_BJ_ACCT is not null");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctEqualTo(String value) {
            addCriterion("IS_BJ_ACCT =", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotEqualTo(String value) {
            addCriterion("IS_BJ_ACCT <>", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctGreaterThan(String value) {
            addCriterion("IS_BJ_ACCT >", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BJ_ACCT >=", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLessThan(String value) {
            addCriterion("IS_BJ_ACCT <", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLessThanOrEqualTo(String value) {
            addCriterion("IS_BJ_ACCT <=", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLike(String value) {
            addCriterion("IS_BJ_ACCT like", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotLike(String value) {
            addCriterion("IS_BJ_ACCT not like", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIn(List<String> values) {
            addCriterion("IS_BJ_ACCT in", values, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotIn(List<String> values) {
            addCriterion("IS_BJ_ACCT not in", values, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctBetween(String value1, String value2) {
            addCriterion("IS_BJ_ACCT between", value1, value2, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotBetween(String value1, String value2) {
            addCriterion("IS_BJ_ACCT not between", value1, value2, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andBisIsNull() {
            addCriterion("BIS is null");
            return (Criteria) this;
        }

        public Criteria andBisIsNotNull() {
            addCriterion("BIS is not null");
            return (Criteria) this;
        }

        public Criteria andBisEqualTo(String value) {
            addCriterion("BIS =", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotEqualTo(String value) {
            addCriterion("BIS <>", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisGreaterThan(String value) {
            addCriterion("BIS >", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisGreaterThanOrEqualTo(String value) {
            addCriterion("BIS >=", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLessThan(String value) {
            addCriterion("BIS <", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLessThanOrEqualTo(String value) {
            addCriterion("BIS <=", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLike(String value) {
            addCriterion("BIS like", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotLike(String value) {
            addCriterion("BIS not like", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisIn(List<String> values) {
            addCriterion("BIS in", values, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotIn(List<String> values) {
            addCriterion("BIS not in", values, "bis");
            return (Criteria) this;
        }

        public Criteria andBisBetween(String value1, String value2) {
            addCriterion("BIS between", value1, value2, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotBetween(String value1, String value2) {
            addCriterion("BIS not between", value1, value2, "bis");
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