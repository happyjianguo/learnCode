package cn.yufu.bak.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MerchantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andHeadOfficeIsNull() {
            addCriterion("HEAD_OFFICE is null");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeIsNotNull() {
            addCriterion("HEAD_OFFICE is not null");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeEqualTo(String value) {
            addCriterion("HEAD_OFFICE =", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotEqualTo(String value) {
            addCriterion("HEAD_OFFICE <>", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeGreaterThan(String value) {
            addCriterion("HEAD_OFFICE >", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_OFFICE >=", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLessThan(String value) {
            addCriterion("HEAD_OFFICE <", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLessThanOrEqualTo(String value) {
            addCriterion("HEAD_OFFICE <=", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLike(String value) {
            addCriterion("HEAD_OFFICE like", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotLike(String value) {
            addCriterion("HEAD_OFFICE not like", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeIn(List<String> values) {
            addCriterion("HEAD_OFFICE in", values, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotIn(List<String> values) {
            addCriterion("HEAD_OFFICE not in", values, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeBetween(String value1, String value2) {
            addCriterion("HEAD_OFFICE between", value1, value2, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotBetween(String value1, String value2) {
            addCriterion("HEAD_OFFICE not between", value1, value2, "headOffice");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTradingAsIsNull() {
            addCriterion("TRADING_AS is null");
            return (Criteria) this;
        }

        public Criteria andTradingAsIsNotNull() {
            addCriterion("TRADING_AS is not null");
            return (Criteria) this;
        }

        public Criteria andTradingAsEqualTo(String value) {
            addCriterion("TRADING_AS =", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsNotEqualTo(String value) {
            addCriterion("TRADING_AS <>", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsGreaterThan(String value) {
            addCriterion("TRADING_AS >", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsGreaterThanOrEqualTo(String value) {
            addCriterion("TRADING_AS >=", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsLessThan(String value) {
            addCriterion("TRADING_AS <", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsLessThanOrEqualTo(String value) {
            addCriterion("TRADING_AS <=", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsLike(String value) {
            addCriterion("TRADING_AS like", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsNotLike(String value) {
            addCriterion("TRADING_AS not like", value, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsIn(List<String> values) {
            addCriterion("TRADING_AS in", values, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsNotIn(List<String> values) {
            addCriterion("TRADING_AS not in", values, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsBetween(String value1, String value2) {
            addCriterion("TRADING_AS between", value1, value2, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andTradingAsNotBetween(String value1, String value2) {
            addCriterion("TRADING_AS not between", value1, value2, "tradingAs");
            return (Criteria) this;
        }

        public Criteria andAddress1IsNull() {
            addCriterion("ADDRESS1 is null");
            return (Criteria) this;
        }

        public Criteria andAddress1IsNotNull() {
            addCriterion("ADDRESS1 is not null");
            return (Criteria) this;
        }

        public Criteria andAddress1EqualTo(String value) {
            addCriterion("ADDRESS1 =", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1NotEqualTo(String value) {
            addCriterion("ADDRESS1 <>", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1GreaterThan(String value) {
            addCriterion("ADDRESS1 >", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1GreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS1 >=", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1LessThan(String value) {
            addCriterion("ADDRESS1 <", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1LessThanOrEqualTo(String value) {
            addCriterion("ADDRESS1 <=", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1Like(String value) {
            addCriterion("ADDRESS1 like", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1NotLike(String value) {
            addCriterion("ADDRESS1 not like", value, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1In(List<String> values) {
            addCriterion("ADDRESS1 in", values, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1NotIn(List<String> values) {
            addCriterion("ADDRESS1 not in", values, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1Between(String value1, String value2) {
            addCriterion("ADDRESS1 between", value1, value2, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress1NotBetween(String value1, String value2) {
            addCriterion("ADDRESS1 not between", value1, value2, "address1");
            return (Criteria) this;
        }

        public Criteria andAddress2IsNull() {
            addCriterion("ADDRESS2 is null");
            return (Criteria) this;
        }

        public Criteria andAddress2IsNotNull() {
            addCriterion("ADDRESS2 is not null");
            return (Criteria) this;
        }

        public Criteria andAddress2EqualTo(String value) {
            addCriterion("ADDRESS2 =", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2NotEqualTo(String value) {
            addCriterion("ADDRESS2 <>", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2GreaterThan(String value) {
            addCriterion("ADDRESS2 >", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2GreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS2 >=", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2LessThan(String value) {
            addCriterion("ADDRESS2 <", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2LessThanOrEqualTo(String value) {
            addCriterion("ADDRESS2 <=", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2Like(String value) {
            addCriterion("ADDRESS2 like", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2NotLike(String value) {
            addCriterion("ADDRESS2 not like", value, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2In(List<String> values) {
            addCriterion("ADDRESS2 in", values, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2NotIn(List<String> values) {
            addCriterion("ADDRESS2 not in", values, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2Between(String value1, String value2) {
            addCriterion("ADDRESS2 between", value1, value2, "address2");
            return (Criteria) this;
        }

        public Criteria andAddress2NotBetween(String value1, String value2) {
            addCriterion("ADDRESS2 not between", value1, value2, "address2");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("CITY is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("CITY =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("CITY <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("CITY >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("CITY >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("CITY <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("CITY <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("CITY like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("CITY not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("CITY in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("CITY not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("CITY between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("CITY not between", value1, value2, "city");
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

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("POSTCODE =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("POSTCODE <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("POSTCODE >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("POSTCODE >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("POSTCODE <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("POSTCODE <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("POSTCODE like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("POSTCODE not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("POSTCODE in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("POSTCODE not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("POSTCODE between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("POSTCODE not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeIsNull() {
            addCriterion("COUNTRYCODE is null");
            return (Criteria) this;
        }

        public Criteria andCountrycodeIsNotNull() {
            addCriterion("COUNTRYCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountrycodeEqualTo(String value) {
            addCriterion("COUNTRYCODE =", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeNotEqualTo(String value) {
            addCriterion("COUNTRYCODE <>", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeGreaterThan(String value) {
            addCriterion("COUNTRYCODE >", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTRYCODE >=", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeLessThan(String value) {
            addCriterion("COUNTRYCODE <", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTRYCODE <=", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeLike(String value) {
            addCriterion("COUNTRYCODE like", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeNotLike(String value) {
            addCriterion("COUNTRYCODE not like", value, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeIn(List<String> values) {
            addCriterion("COUNTRYCODE in", values, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeNotIn(List<String> values) {
            addCriterion("COUNTRYCODE not in", values, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeBetween(String value1, String value2) {
            addCriterion("COUNTRYCODE between", value1, value2, "countrycode");
            return (Criteria) this;
        }

        public Criteria andCountrycodeNotBetween(String value1, String value2) {
            addCriterion("COUNTRYCODE not between", value1, value2, "countrycode");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1IsNull() {
            addCriterion("PHY_ADDRESS1 is null");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1IsNotNull() {
            addCriterion("PHY_ADDRESS1 is not null");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1EqualTo(String value) {
            addCriterion("PHY_ADDRESS1 =", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1NotEqualTo(String value) {
            addCriterion("PHY_ADDRESS1 <>", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1GreaterThan(String value) {
            addCriterion("PHY_ADDRESS1 >", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1GreaterThanOrEqualTo(String value) {
            addCriterion("PHY_ADDRESS1 >=", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1LessThan(String value) {
            addCriterion("PHY_ADDRESS1 <", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1LessThanOrEqualTo(String value) {
            addCriterion("PHY_ADDRESS1 <=", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1Like(String value) {
            addCriterion("PHY_ADDRESS1 like", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1NotLike(String value) {
            addCriterion("PHY_ADDRESS1 not like", value, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1In(List<String> values) {
            addCriterion("PHY_ADDRESS1 in", values, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1NotIn(List<String> values) {
            addCriterion("PHY_ADDRESS1 not in", values, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1Between(String value1, String value2) {
            addCriterion("PHY_ADDRESS1 between", value1, value2, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress1NotBetween(String value1, String value2) {
            addCriterion("PHY_ADDRESS1 not between", value1, value2, "phyAddress1");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2IsNull() {
            addCriterion("PHY_ADDRESS2 is null");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2IsNotNull() {
            addCriterion("PHY_ADDRESS2 is not null");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2EqualTo(String value) {
            addCriterion("PHY_ADDRESS2 =", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2NotEqualTo(String value) {
            addCriterion("PHY_ADDRESS2 <>", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2GreaterThan(String value) {
            addCriterion("PHY_ADDRESS2 >", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2GreaterThanOrEqualTo(String value) {
            addCriterion("PHY_ADDRESS2 >=", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2LessThan(String value) {
            addCriterion("PHY_ADDRESS2 <", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2LessThanOrEqualTo(String value) {
            addCriterion("PHY_ADDRESS2 <=", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2Like(String value) {
            addCriterion("PHY_ADDRESS2 like", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2NotLike(String value) {
            addCriterion("PHY_ADDRESS2 not like", value, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2In(List<String> values) {
            addCriterion("PHY_ADDRESS2 in", values, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2NotIn(List<String> values) {
            addCriterion("PHY_ADDRESS2 not in", values, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2Between(String value1, String value2) {
            addCriterion("PHY_ADDRESS2 between", value1, value2, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyAddress2NotBetween(String value1, String value2) {
            addCriterion("PHY_ADDRESS2 not between", value1, value2, "phyAddress2");
            return (Criteria) this;
        }

        public Criteria andPhyCityIsNull() {
            addCriterion("PHY_CITY is null");
            return (Criteria) this;
        }

        public Criteria andPhyCityIsNotNull() {
            addCriterion("PHY_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andPhyCityEqualTo(String value) {
            addCriterion("PHY_CITY =", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityNotEqualTo(String value) {
            addCriterion("PHY_CITY <>", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityGreaterThan(String value) {
            addCriterion("PHY_CITY >", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityGreaterThanOrEqualTo(String value) {
            addCriterion("PHY_CITY >=", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityLessThan(String value) {
            addCriterion("PHY_CITY <", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityLessThanOrEqualTo(String value) {
            addCriterion("PHY_CITY <=", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityLike(String value) {
            addCriterion("PHY_CITY like", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityNotLike(String value) {
            addCriterion("PHY_CITY not like", value, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityIn(List<String> values) {
            addCriterion("PHY_CITY in", values, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityNotIn(List<String> values) {
            addCriterion("PHY_CITY not in", values, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityBetween(String value1, String value2) {
            addCriterion("PHY_CITY between", value1, value2, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyCityNotBetween(String value1, String value2) {
            addCriterion("PHY_CITY not between", value1, value2, "phyCity");
            return (Criteria) this;
        }

        public Criteria andPhyStateIsNull() {
            addCriterion("PHY_STATE is null");
            return (Criteria) this;
        }

        public Criteria andPhyStateIsNotNull() {
            addCriterion("PHY_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andPhyStateEqualTo(String value) {
            addCriterion("PHY_STATE =", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateNotEqualTo(String value) {
            addCriterion("PHY_STATE <>", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateGreaterThan(String value) {
            addCriterion("PHY_STATE >", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateGreaterThanOrEqualTo(String value) {
            addCriterion("PHY_STATE >=", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateLessThan(String value) {
            addCriterion("PHY_STATE <", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateLessThanOrEqualTo(String value) {
            addCriterion("PHY_STATE <=", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateLike(String value) {
            addCriterion("PHY_STATE like", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateNotLike(String value) {
            addCriterion("PHY_STATE not like", value, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateIn(List<String> values) {
            addCriterion("PHY_STATE in", values, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateNotIn(List<String> values) {
            addCriterion("PHY_STATE not in", values, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateBetween(String value1, String value2) {
            addCriterion("PHY_STATE between", value1, value2, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyStateNotBetween(String value1, String value2) {
            addCriterion("PHY_STATE not between", value1, value2, "phyState");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeIsNull() {
            addCriterion("PHY_POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeIsNotNull() {
            addCriterion("PHY_POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeEqualTo(String value) {
            addCriterion("PHY_POSTCODE =", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeNotEqualTo(String value) {
            addCriterion("PHY_POSTCODE <>", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeGreaterThan(String value) {
            addCriterion("PHY_POSTCODE >", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PHY_POSTCODE >=", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeLessThan(String value) {
            addCriterion("PHY_POSTCODE <", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeLessThanOrEqualTo(String value) {
            addCriterion("PHY_POSTCODE <=", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeLike(String value) {
            addCriterion("PHY_POSTCODE like", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeNotLike(String value) {
            addCriterion("PHY_POSTCODE not like", value, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeIn(List<String> values) {
            addCriterion("PHY_POSTCODE in", values, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeNotIn(List<String> values) {
            addCriterion("PHY_POSTCODE not in", values, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeBetween(String value1, String value2) {
            addCriterion("PHY_POSTCODE between", value1, value2, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyPostcodeNotBetween(String value1, String value2) {
            addCriterion("PHY_POSTCODE not between", value1, value2, "phyPostcode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeIsNull() {
            addCriterion("PHY_COUNTRYCODE is null");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeIsNotNull() {
            addCriterion("PHY_COUNTRYCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeEqualTo(String value) {
            addCriterion("PHY_COUNTRYCODE =", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeNotEqualTo(String value) {
            addCriterion("PHY_COUNTRYCODE <>", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeGreaterThan(String value) {
            addCriterion("PHY_COUNTRYCODE >", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeGreaterThanOrEqualTo(String value) {
            addCriterion("PHY_COUNTRYCODE >=", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeLessThan(String value) {
            addCriterion("PHY_COUNTRYCODE <", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeLessThanOrEqualTo(String value) {
            addCriterion("PHY_COUNTRYCODE <=", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeLike(String value) {
            addCriterion("PHY_COUNTRYCODE like", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeNotLike(String value) {
            addCriterion("PHY_COUNTRYCODE not like", value, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeIn(List<String> values) {
            addCriterion("PHY_COUNTRYCODE in", values, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeNotIn(List<String> values) {
            addCriterion("PHY_COUNTRYCODE not in", values, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeBetween(String value1, String value2) {
            addCriterion("PHY_COUNTRYCODE between", value1, value2, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andPhyCountrycodeNotBetween(String value1, String value2) {
            addCriterion("PHY_COUNTRYCODE not between", value1, value2, "phyCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegAddress1IsNull() {
            addCriterion("REG_ADDRESS1 is null");
            return (Criteria) this;
        }

        public Criteria andRegAddress1IsNotNull() {
            addCriterion("REG_ADDRESS1 is not null");
            return (Criteria) this;
        }

        public Criteria andRegAddress1EqualTo(String value) {
            addCriterion("REG_ADDRESS1 =", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1NotEqualTo(String value) {
            addCriterion("REG_ADDRESS1 <>", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1GreaterThan(String value) {
            addCriterion("REG_ADDRESS1 >", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1GreaterThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS1 >=", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1LessThan(String value) {
            addCriterion("REG_ADDRESS1 <", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1LessThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS1 <=", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1Like(String value) {
            addCriterion("REG_ADDRESS1 like", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1NotLike(String value) {
            addCriterion("REG_ADDRESS1 not like", value, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1In(List<String> values) {
            addCriterion("REG_ADDRESS1 in", values, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1NotIn(List<String> values) {
            addCriterion("REG_ADDRESS1 not in", values, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1Between(String value1, String value2) {
            addCriterion("REG_ADDRESS1 between", value1, value2, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress1NotBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS1 not between", value1, value2, "regAddress1");
            return (Criteria) this;
        }

        public Criteria andRegAddress2IsNull() {
            addCriterion("REG_ADDRESS2 is null");
            return (Criteria) this;
        }

        public Criteria andRegAddress2IsNotNull() {
            addCriterion("REG_ADDRESS2 is not null");
            return (Criteria) this;
        }

        public Criteria andRegAddress2EqualTo(String value) {
            addCriterion("REG_ADDRESS2 =", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2NotEqualTo(String value) {
            addCriterion("REG_ADDRESS2 <>", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2GreaterThan(String value) {
            addCriterion("REG_ADDRESS2 >", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2GreaterThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS2 >=", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2LessThan(String value) {
            addCriterion("REG_ADDRESS2 <", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2LessThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS2 <=", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2Like(String value) {
            addCriterion("REG_ADDRESS2 like", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2NotLike(String value) {
            addCriterion("REG_ADDRESS2 not like", value, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2In(List<String> values) {
            addCriterion("REG_ADDRESS2 in", values, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2NotIn(List<String> values) {
            addCriterion("REG_ADDRESS2 not in", values, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2Between(String value1, String value2) {
            addCriterion("REG_ADDRESS2 between", value1, value2, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegAddress2NotBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS2 not between", value1, value2, "regAddress2");
            return (Criteria) this;
        }

        public Criteria andRegCityIsNull() {
            addCriterion("REG_CITY is null");
            return (Criteria) this;
        }

        public Criteria andRegCityIsNotNull() {
            addCriterion("REG_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andRegCityEqualTo(String value) {
            addCriterion("REG_CITY =", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityNotEqualTo(String value) {
            addCriterion("REG_CITY <>", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityGreaterThan(String value) {
            addCriterion("REG_CITY >", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityGreaterThanOrEqualTo(String value) {
            addCriterion("REG_CITY >=", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityLessThan(String value) {
            addCriterion("REG_CITY <", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityLessThanOrEqualTo(String value) {
            addCriterion("REG_CITY <=", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityLike(String value) {
            addCriterion("REG_CITY like", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityNotLike(String value) {
            addCriterion("REG_CITY not like", value, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityIn(List<String> values) {
            addCriterion("REG_CITY in", values, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityNotIn(List<String> values) {
            addCriterion("REG_CITY not in", values, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityBetween(String value1, String value2) {
            addCriterion("REG_CITY between", value1, value2, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegCityNotBetween(String value1, String value2) {
            addCriterion("REG_CITY not between", value1, value2, "regCity");
            return (Criteria) this;
        }

        public Criteria andRegStateIsNull() {
            addCriterion("REG_STATE is null");
            return (Criteria) this;
        }

        public Criteria andRegStateIsNotNull() {
            addCriterion("REG_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegStateEqualTo(String value) {
            addCriterion("REG_STATE =", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateNotEqualTo(String value) {
            addCriterion("REG_STATE <>", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateGreaterThan(String value) {
            addCriterion("REG_STATE >", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateGreaterThanOrEqualTo(String value) {
            addCriterion("REG_STATE >=", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateLessThan(String value) {
            addCriterion("REG_STATE <", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateLessThanOrEqualTo(String value) {
            addCriterion("REG_STATE <=", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateLike(String value) {
            addCriterion("REG_STATE like", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateNotLike(String value) {
            addCriterion("REG_STATE not like", value, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateIn(List<String> values) {
            addCriterion("REG_STATE in", values, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateNotIn(List<String> values) {
            addCriterion("REG_STATE not in", values, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateBetween(String value1, String value2) {
            addCriterion("REG_STATE between", value1, value2, "regState");
            return (Criteria) this;
        }

        public Criteria andRegStateNotBetween(String value1, String value2) {
            addCriterion("REG_STATE not between", value1, value2, "regState");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeIsNull() {
            addCriterion("REG_POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeIsNotNull() {
            addCriterion("REG_POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeEqualTo(String value) {
            addCriterion("REG_POSTCODE =", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeNotEqualTo(String value) {
            addCriterion("REG_POSTCODE <>", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeGreaterThan(String value) {
            addCriterion("REG_POSTCODE >", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("REG_POSTCODE >=", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeLessThan(String value) {
            addCriterion("REG_POSTCODE <", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeLessThanOrEqualTo(String value) {
            addCriterion("REG_POSTCODE <=", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeLike(String value) {
            addCriterion("REG_POSTCODE like", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeNotLike(String value) {
            addCriterion("REG_POSTCODE not like", value, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeIn(List<String> values) {
            addCriterion("REG_POSTCODE in", values, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeNotIn(List<String> values) {
            addCriterion("REG_POSTCODE not in", values, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeBetween(String value1, String value2) {
            addCriterion("REG_POSTCODE between", value1, value2, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegPostcodeNotBetween(String value1, String value2) {
            addCriterion("REG_POSTCODE not between", value1, value2, "regPostcode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeIsNull() {
            addCriterion("REG_COUNTRYCODE is null");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeIsNotNull() {
            addCriterion("REG_COUNTRYCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeEqualTo(String value) {
            addCriterion("REG_COUNTRYCODE =", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeNotEqualTo(String value) {
            addCriterion("REG_COUNTRYCODE <>", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeGreaterThan(String value) {
            addCriterion("REG_COUNTRYCODE >", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeGreaterThanOrEqualTo(String value) {
            addCriterion("REG_COUNTRYCODE >=", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeLessThan(String value) {
            addCriterion("REG_COUNTRYCODE <", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeLessThanOrEqualTo(String value) {
            addCriterion("REG_COUNTRYCODE <=", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeLike(String value) {
            addCriterion("REG_COUNTRYCODE like", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeNotLike(String value) {
            addCriterion("REG_COUNTRYCODE not like", value, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeIn(List<String> values) {
            addCriterion("REG_COUNTRYCODE in", values, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeNotIn(List<String> values) {
            addCriterion("REG_COUNTRYCODE not in", values, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeBetween(String value1, String value2) {
            addCriterion("REG_COUNTRYCODE between", value1, value2, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andRegCountrycodeNotBetween(String value1, String value2) {
            addCriterion("REG_COUNTRYCODE not between", value1, value2, "regCountrycode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIsNull() {
            addCriterion("CURRCODE is null");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIsNotNull() {
            addCriterion("CURRCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrcodeEqualTo(String value) {
            addCriterion("CURRCODE =", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotEqualTo(String value) {
            addCriterion("CURRCODE <>", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeGreaterThan(String value) {
            addCriterion("CURRCODE >", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("CURRCODE >=", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLessThan(String value) {
            addCriterion("CURRCODE <", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLessThanOrEqualTo(String value) {
            addCriterion("CURRCODE <=", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeLike(String value) {
            addCriterion("CURRCODE like", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotLike(String value) {
            addCriterion("CURRCODE not like", value, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeIn(List<String> values) {
            addCriterion("CURRCODE in", values, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotIn(List<String> values) {
            addCriterion("CURRCODE not in", values, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeBetween(String value1, String value2) {
            addCriterion("CURRCODE between", value1, value2, "currcode");
            return (Criteria) this;
        }

        public Criteria andCurrcodeNotBetween(String value1, String value2) {
            addCriterion("CURRCODE not between", value1, value2, "currcode");
            return (Criteria) this;
        }

        public Criteria andMrchtypeIsNull() {
            addCriterion("MRCHTYPE is null");
            return (Criteria) this;
        }

        public Criteria andMrchtypeIsNotNull() {
            addCriterion("MRCHTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMrchtypeEqualTo(Integer value) {
            addCriterion("MRCHTYPE =", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeNotEqualTo(Integer value) {
            addCriterion("MRCHTYPE <>", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeGreaterThan(Integer value) {
            addCriterion("MRCHTYPE >", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MRCHTYPE >=", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeLessThan(Integer value) {
            addCriterion("MRCHTYPE <", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeLessThanOrEqualTo(Integer value) {
            addCriterion("MRCHTYPE <=", value, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeIn(List<Integer> values) {
            addCriterion("MRCHTYPE in", values, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeNotIn(List<Integer> values) {
            addCriterion("MRCHTYPE not in", values, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeBetween(Integer value1, Integer value2) {
            addCriterion("MRCHTYPE between", value1, value2, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andMrchtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("MRCHTYPE not between", value1, value2, "mrchtype");
            return (Criteria) this;
        }

        public Criteria andAcptbusIsNull() {
            addCriterion("ACPTBUS is null");
            return (Criteria) this;
        }

        public Criteria andAcptbusIsNotNull() {
            addCriterion("ACPTBUS is not null");
            return (Criteria) this;
        }

        public Criteria andAcptbusEqualTo(Integer value) {
            addCriterion("ACPTBUS =", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusNotEqualTo(Integer value) {
            addCriterion("ACPTBUS <>", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusGreaterThan(Integer value) {
            addCriterion("ACPTBUS >", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACPTBUS >=", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusLessThan(Integer value) {
            addCriterion("ACPTBUS <", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusLessThanOrEqualTo(Integer value) {
            addCriterion("ACPTBUS <=", value, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusIn(List<Integer> values) {
            addCriterion("ACPTBUS in", values, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusNotIn(List<Integer> values) {
            addCriterion("ACPTBUS not in", values, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusBetween(Integer value1, Integer value2) {
            addCriterion("ACPTBUS between", value1, value2, "acptbus");
            return (Criteria) this;
        }

        public Criteria andAcptbusNotBetween(Integer value1, Integer value2) {
            addCriterion("ACPTBUS not between", value1, value2, "acptbus");
            return (Criteria) this;
        }

        public Criteria andContact1IsNull() {
            addCriterion("CONTACT1 is null");
            return (Criteria) this;
        }

        public Criteria andContact1IsNotNull() {
            addCriterion("CONTACT1 is not null");
            return (Criteria) this;
        }

        public Criteria andContact1EqualTo(String value) {
            addCriterion("CONTACT1 =", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotEqualTo(String value) {
            addCriterion("CONTACT1 <>", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1GreaterThan(String value) {
            addCriterion("CONTACT1 >", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1GreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT1 >=", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1LessThan(String value) {
            addCriterion("CONTACT1 <", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1LessThanOrEqualTo(String value) {
            addCriterion("CONTACT1 <=", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1Like(String value) {
            addCriterion("CONTACT1 like", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotLike(String value) {
            addCriterion("CONTACT1 not like", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1In(List<String> values) {
            addCriterion("CONTACT1 in", values, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotIn(List<String> values) {
            addCriterion("CONTACT1 not in", values, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1Between(String value1, String value2) {
            addCriterion("CONTACT1 between", value1, value2, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotBetween(String value1, String value2) {
            addCriterion("CONTACT1 not between", value1, value2, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact2IsNull() {
            addCriterion("CONTACT2 is null");
            return (Criteria) this;
        }

        public Criteria andContact2IsNotNull() {
            addCriterion("CONTACT2 is not null");
            return (Criteria) this;
        }

        public Criteria andContact2EqualTo(String value) {
            addCriterion("CONTACT2 =", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotEqualTo(String value) {
            addCriterion("CONTACT2 <>", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2GreaterThan(String value) {
            addCriterion("CONTACT2 >", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2GreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT2 >=", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2LessThan(String value) {
            addCriterion("CONTACT2 <", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2LessThanOrEqualTo(String value) {
            addCriterion("CONTACT2 <=", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2Like(String value) {
            addCriterion("CONTACT2 like", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotLike(String value) {
            addCriterion("CONTACT2 not like", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2In(List<String> values) {
            addCriterion("CONTACT2 in", values, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotIn(List<String> values) {
            addCriterion("CONTACT2 not in", values, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2Between(String value1, String value2) {
            addCriterion("CONTACT2 between", value1, value2, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotBetween(String value1, String value2) {
            addCriterion("CONTACT2 not between", value1, value2, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact3IsNull() {
            addCriterion("CONTACT3 is null");
            return (Criteria) this;
        }

        public Criteria andContact3IsNotNull() {
            addCriterion("CONTACT3 is not null");
            return (Criteria) this;
        }

        public Criteria andContact3EqualTo(String value) {
            addCriterion("CONTACT3 =", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotEqualTo(String value) {
            addCriterion("CONTACT3 <>", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3GreaterThan(String value) {
            addCriterion("CONTACT3 >", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3GreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT3 >=", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3LessThan(String value) {
            addCriterion("CONTACT3 <", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3LessThanOrEqualTo(String value) {
            addCriterion("CONTACT3 <=", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3Like(String value) {
            addCriterion("CONTACT3 like", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotLike(String value) {
            addCriterion("CONTACT3 not like", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3In(List<String> values) {
            addCriterion("CONTACT3 in", values, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotIn(List<String> values) {
            addCriterion("CONTACT3 not in", values, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3Between(String value1, String value2) {
            addCriterion("CONTACT3 between", value1, value2, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotBetween(String value1, String value2) {
            addCriterion("CONTACT3 not between", value1, value2, "contact3");
            return (Criteria) this;
        }

        public Criteria andTelno1IsNull() {
            addCriterion("TELNO1 is null");
            return (Criteria) this;
        }

        public Criteria andTelno1IsNotNull() {
            addCriterion("TELNO1 is not null");
            return (Criteria) this;
        }

        public Criteria andTelno1EqualTo(String value) {
            addCriterion("TELNO1 =", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1NotEqualTo(String value) {
            addCriterion("TELNO1 <>", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1GreaterThan(String value) {
            addCriterion("TELNO1 >", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1GreaterThanOrEqualTo(String value) {
            addCriterion("TELNO1 >=", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1LessThan(String value) {
            addCriterion("TELNO1 <", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1LessThanOrEqualTo(String value) {
            addCriterion("TELNO1 <=", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1Like(String value) {
            addCriterion("TELNO1 like", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1NotLike(String value) {
            addCriterion("TELNO1 not like", value, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1In(List<String> values) {
            addCriterion("TELNO1 in", values, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1NotIn(List<String> values) {
            addCriterion("TELNO1 not in", values, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1Between(String value1, String value2) {
            addCriterion("TELNO1 between", value1, value2, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno1NotBetween(String value1, String value2) {
            addCriterion("TELNO1 not between", value1, value2, "telno1");
            return (Criteria) this;
        }

        public Criteria andTelno2IsNull() {
            addCriterion("TELNO2 is null");
            return (Criteria) this;
        }

        public Criteria andTelno2IsNotNull() {
            addCriterion("TELNO2 is not null");
            return (Criteria) this;
        }

        public Criteria andTelno2EqualTo(String value) {
            addCriterion("TELNO2 =", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2NotEqualTo(String value) {
            addCriterion("TELNO2 <>", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2GreaterThan(String value) {
            addCriterion("TELNO2 >", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2GreaterThanOrEqualTo(String value) {
            addCriterion("TELNO2 >=", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2LessThan(String value) {
            addCriterion("TELNO2 <", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2LessThanOrEqualTo(String value) {
            addCriterion("TELNO2 <=", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2Like(String value) {
            addCriterion("TELNO2 like", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2NotLike(String value) {
            addCriterion("TELNO2 not like", value, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2In(List<String> values) {
            addCriterion("TELNO2 in", values, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2NotIn(List<String> values) {
            addCriterion("TELNO2 not in", values, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2Between(String value1, String value2) {
            addCriterion("TELNO2 between", value1, value2, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno2NotBetween(String value1, String value2) {
            addCriterion("TELNO2 not between", value1, value2, "telno2");
            return (Criteria) this;
        }

        public Criteria andTelno3IsNull() {
            addCriterion("TELNO3 is null");
            return (Criteria) this;
        }

        public Criteria andTelno3IsNotNull() {
            addCriterion("TELNO3 is not null");
            return (Criteria) this;
        }

        public Criteria andTelno3EqualTo(String value) {
            addCriterion("TELNO3 =", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3NotEqualTo(String value) {
            addCriterion("TELNO3 <>", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3GreaterThan(String value) {
            addCriterion("TELNO3 >", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3GreaterThanOrEqualTo(String value) {
            addCriterion("TELNO3 >=", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3LessThan(String value) {
            addCriterion("TELNO3 <", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3LessThanOrEqualTo(String value) {
            addCriterion("TELNO3 <=", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3Like(String value) {
            addCriterion("TELNO3 like", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3NotLike(String value) {
            addCriterion("TELNO3 not like", value, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3In(List<String> values) {
            addCriterion("TELNO3 in", values, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3NotIn(List<String> values) {
            addCriterion("TELNO3 not in", values, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3Between(String value1, String value2) {
            addCriterion("TELNO3 between", value1, value2, "telno3");
            return (Criteria) this;
        }

        public Criteria andTelno3NotBetween(String value1, String value2) {
            addCriterion("TELNO3 not between", value1, value2, "telno3");
            return (Criteria) this;
        }

        public Criteria andFaxnoIsNull() {
            addCriterion("FAXNO is null");
            return (Criteria) this;
        }

        public Criteria andFaxnoIsNotNull() {
            addCriterion("FAXNO is not null");
            return (Criteria) this;
        }

        public Criteria andFaxnoEqualTo(String value) {
            addCriterion("FAXNO =", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoNotEqualTo(String value) {
            addCriterion("FAXNO <>", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoGreaterThan(String value) {
            addCriterion("FAXNO >", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoGreaterThanOrEqualTo(String value) {
            addCriterion("FAXNO >=", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoLessThan(String value) {
            addCriterion("FAXNO <", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoLessThanOrEqualTo(String value) {
            addCriterion("FAXNO <=", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoLike(String value) {
            addCriterion("FAXNO like", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoNotLike(String value) {
            addCriterion("FAXNO not like", value, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoIn(List<String> values) {
            addCriterion("FAXNO in", values, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoNotIn(List<String> values) {
            addCriterion("FAXNO not in", values, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoBetween(String value1, String value2) {
            addCriterion("FAXNO between", value1, value2, "faxno");
            return (Criteria) this;
        }

        public Criteria andFaxnoNotBetween(String value1, String value2) {
            addCriterion("FAXNO not between", value1, value2, "faxno");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andTelexIsNull() {
            addCriterion("TELEX is null");
            return (Criteria) this;
        }

        public Criteria andTelexIsNotNull() {
            addCriterion("TELEX is not null");
            return (Criteria) this;
        }

        public Criteria andTelexEqualTo(String value) {
            addCriterion("TELEX =", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexNotEqualTo(String value) {
            addCriterion("TELEX <>", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexGreaterThan(String value) {
            addCriterion("TELEX >", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexGreaterThanOrEqualTo(String value) {
            addCriterion("TELEX >=", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexLessThan(String value) {
            addCriterion("TELEX <", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexLessThanOrEqualTo(String value) {
            addCriterion("TELEX <=", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexLike(String value) {
            addCriterion("TELEX like", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexNotLike(String value) {
            addCriterion("TELEX not like", value, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexIn(List<String> values) {
            addCriterion("TELEX in", values, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexNotIn(List<String> values) {
            addCriterion("TELEX not in", values, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexBetween(String value1, String value2) {
            addCriterion("TELEX between", value1, value2, "telex");
            return (Criteria) this;
        }

        public Criteria andTelexNotBetween(String value1, String value2) {
            addCriterion("TELEX not between", value1, value2, "telex");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNull() {
            addCriterion("SORTCODE is null");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNotNull() {
            addCriterion("SORTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andSortcodeEqualTo(String value) {
            addCriterion("SORTCODE =", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotEqualTo(String value) {
            addCriterion("SORTCODE <>", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThan(String value) {
            addCriterion("SORTCODE >", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThanOrEqualTo(String value) {
            addCriterion("SORTCODE >=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThan(String value) {
            addCriterion("SORTCODE <", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThanOrEqualTo(String value) {
            addCriterion("SORTCODE <=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLike(String value) {
            addCriterion("SORTCODE like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotLike(String value) {
            addCriterion("SORTCODE not like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeIn(List<String> values) {
            addCriterion("SORTCODE in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotIn(List<String> values) {
            addCriterion("SORTCODE not in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeBetween(String value1, String value2) {
            addCriterion("SORTCODE between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotBetween(String value1, String value2) {
            addCriterion("SORTCODE not between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andBrncodeIsNull() {
            addCriterion("BRNCODE is null");
            return (Criteria) this;
        }

        public Criteria andBrncodeIsNotNull() {
            addCriterion("BRNCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBrncodeEqualTo(String value) {
            addCriterion("BRNCODE =", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeNotEqualTo(String value) {
            addCriterion("BRNCODE <>", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeGreaterThan(String value) {
            addCriterion("BRNCODE >", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeGreaterThanOrEqualTo(String value) {
            addCriterion("BRNCODE >=", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeLessThan(String value) {
            addCriterion("BRNCODE <", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeLessThanOrEqualTo(String value) {
            addCriterion("BRNCODE <=", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeLike(String value) {
            addCriterion("BRNCODE like", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeNotLike(String value) {
            addCriterion("BRNCODE not like", value, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeIn(List<String> values) {
            addCriterion("BRNCODE in", values, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeNotIn(List<String> values) {
            addCriterion("BRNCODE not in", values, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeBetween(String value1, String value2) {
            addCriterion("BRNCODE between", value1, value2, "brncode");
            return (Criteria) this;
        }

        public Criteria andBrncodeNotBetween(String value1, String value2) {
            addCriterion("BRNCODE not between", value1, value2, "brncode");
            return (Criteria) this;
        }

        public Criteria andAccnoIsNull() {
            addCriterion("ACCNO is null");
            return (Criteria) this;
        }

        public Criteria andAccnoIsNotNull() {
            addCriterion("ACCNO is not null");
            return (Criteria) this;
        }

        public Criteria andAccnoEqualTo(String value) {
            addCriterion("ACCNO =", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotEqualTo(String value) {
            addCriterion("ACCNO <>", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoGreaterThan(String value) {
            addCriterion("ACCNO >", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCNO >=", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLessThan(String value) {
            addCriterion("ACCNO <", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLessThanOrEqualTo(String value) {
            addCriterion("ACCNO <=", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLike(String value) {
            addCriterion("ACCNO like", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotLike(String value) {
            addCriterion("ACCNO not like", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoIn(List<String> values) {
            addCriterion("ACCNO in", values, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotIn(List<String> values) {
            addCriterion("ACCNO not in", values, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoBetween(String value1, String value2) {
            addCriterion("ACCNO between", value1, value2, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotBetween(String value1, String value2) {
            addCriterion("ACCNO not between", value1, value2, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnmIsNull() {
            addCriterion("ACCNM is null");
            return (Criteria) this;
        }

        public Criteria andAccnmIsNotNull() {
            addCriterion("ACCNM is not null");
            return (Criteria) this;
        }

        public Criteria andAccnmEqualTo(String value) {
            addCriterion("ACCNM =", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmNotEqualTo(String value) {
            addCriterion("ACCNM <>", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmGreaterThan(String value) {
            addCriterion("ACCNM >", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmGreaterThanOrEqualTo(String value) {
            addCriterion("ACCNM >=", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmLessThan(String value) {
            addCriterion("ACCNM <", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmLessThanOrEqualTo(String value) {
            addCriterion("ACCNM <=", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmLike(String value) {
            addCriterion("ACCNM like", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmNotLike(String value) {
            addCriterion("ACCNM not like", value, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmIn(List<String> values) {
            addCriterion("ACCNM in", values, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmNotIn(List<String> values) {
            addCriterion("ACCNM not in", values, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmBetween(String value1, String value2) {
            addCriterion("ACCNM between", value1, value2, "accnm");
            return (Criteria) this;
        }

        public Criteria andAccnmNotBetween(String value1, String value2) {
            addCriterion("ACCNM not between", value1, value2, "accnm");
            return (Criteria) this;
        }

        public Criteria andTaxcodeIsNull() {
            addCriterion("TAXCODE is null");
            return (Criteria) this;
        }

        public Criteria andTaxcodeIsNotNull() {
            addCriterion("TAXCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxcodeEqualTo(String value) {
            addCriterion("TAXCODE =", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeNotEqualTo(String value) {
            addCriterion("TAXCODE <>", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeGreaterThan(String value) {
            addCriterion("TAXCODE >", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TAXCODE >=", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeLessThan(String value) {
            addCriterion("TAXCODE <", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeLessThanOrEqualTo(String value) {
            addCriterion("TAXCODE <=", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeLike(String value) {
            addCriterion("TAXCODE like", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeNotLike(String value) {
            addCriterion("TAXCODE not like", value, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeIn(List<String> values) {
            addCriterion("TAXCODE in", values, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeNotIn(List<String> values) {
            addCriterion("TAXCODE not in", values, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeBetween(String value1, String value2) {
            addCriterion("TAXCODE between", value1, value2, "taxcode");
            return (Criteria) this;
        }

        public Criteria andTaxcodeNotBetween(String value1, String value2) {
            addCriterion("TAXCODE not between", value1, value2, "taxcode");
            return (Criteria) this;
        }

        public Criteria andStmtfreqIsNull() {
            addCriterion("STMTFREQ is null");
            return (Criteria) this;
        }

        public Criteria andStmtfreqIsNotNull() {
            addCriterion("STMTFREQ is not null");
            return (Criteria) this;
        }

        public Criteria andStmtfreqEqualTo(String value) {
            addCriterion("STMTFREQ =", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqNotEqualTo(String value) {
            addCriterion("STMTFREQ <>", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqGreaterThan(String value) {
            addCriterion("STMTFREQ >", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqGreaterThanOrEqualTo(String value) {
            addCriterion("STMTFREQ >=", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqLessThan(String value) {
            addCriterion("STMTFREQ <", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqLessThanOrEqualTo(String value) {
            addCriterion("STMTFREQ <=", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqLike(String value) {
            addCriterion("STMTFREQ like", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqNotLike(String value) {
            addCriterion("STMTFREQ not like", value, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqIn(List<String> values) {
            addCriterion("STMTFREQ in", values, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqNotIn(List<String> values) {
            addCriterion("STMTFREQ not in", values, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqBetween(String value1, String value2) {
            addCriterion("STMTFREQ between", value1, value2, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmtfreqNotBetween(String value1, String value2) {
            addCriterion("STMTFREQ not between", value1, value2, "stmtfreq");
            return (Criteria) this;
        }

        public Criteria andStmttoIsNull() {
            addCriterion("STMTTO is null");
            return (Criteria) this;
        }

        public Criteria andStmttoIsNotNull() {
            addCriterion("STMTTO is not null");
            return (Criteria) this;
        }

        public Criteria andStmttoEqualTo(Integer value) {
            addCriterion("STMTTO =", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoNotEqualTo(Integer value) {
            addCriterion("STMTTO <>", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoGreaterThan(Integer value) {
            addCriterion("STMTTO >", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoGreaterThanOrEqualTo(Integer value) {
            addCriterion("STMTTO >=", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoLessThan(Integer value) {
            addCriterion("STMTTO <", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoLessThanOrEqualTo(Integer value) {
            addCriterion("STMTTO <=", value, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoIn(List<Integer> values) {
            addCriterion("STMTTO in", values, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoNotIn(List<Integer> values) {
            addCriterion("STMTTO not in", values, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoBetween(Integer value1, Integer value2) {
            addCriterion("STMTTO between", value1, value2, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoNotBetween(Integer value1, Integer value2) {
            addCriterion("STMTTO not between", value1, value2, "stmtto");
            return (Criteria) this;
        }

        public Criteria andStmttoHoIsNull() {
            addCriterion("STMTTO_HO is null");
            return (Criteria) this;
        }

        public Criteria andStmttoHoIsNotNull() {
            addCriterion("STMTTO_HO is not null");
            return (Criteria) this;
        }

        public Criteria andStmttoHoEqualTo(Integer value) {
            addCriterion("STMTTO_HO =", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoNotEqualTo(Integer value) {
            addCriterion("STMTTO_HO <>", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoGreaterThan(Integer value) {
            addCriterion("STMTTO_HO >", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoGreaterThanOrEqualTo(Integer value) {
            addCriterion("STMTTO_HO >=", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoLessThan(Integer value) {
            addCriterion("STMTTO_HO <", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoLessThanOrEqualTo(Integer value) {
            addCriterion("STMTTO_HO <=", value, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoIn(List<Integer> values) {
            addCriterion("STMTTO_HO in", values, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoNotIn(List<Integer> values) {
            addCriterion("STMTTO_HO not in", values, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoBetween(Integer value1, Integer value2) {
            addCriterion("STMTTO_HO between", value1, value2, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andStmttoHoNotBetween(Integer value1, Integer value2) {
            addCriterion("STMTTO_HO not between", value1, value2, "stmttoHo");
            return (Criteria) this;
        }

        public Criteria andPaymtypeIsNull() {
            addCriterion("PAYMTYPE is null");
            return (Criteria) this;
        }

        public Criteria andPaymtypeIsNotNull() {
            addCriterion("PAYMTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPaymtypeEqualTo(String value) {
            addCriterion("PAYMTYPE =", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeNotEqualTo(String value) {
            addCriterion("PAYMTYPE <>", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeGreaterThan(String value) {
            addCriterion("PAYMTYPE >", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMTYPE >=", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeLessThan(String value) {
            addCriterion("PAYMTYPE <", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeLessThanOrEqualTo(String value) {
            addCriterion("PAYMTYPE <=", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeLike(String value) {
            addCriterion("PAYMTYPE like", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeNotLike(String value) {
            addCriterion("PAYMTYPE not like", value, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeIn(List<String> values) {
            addCriterion("PAYMTYPE in", values, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeNotIn(List<String> values) {
            addCriterion("PAYMTYPE not in", values, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeBetween(String value1, String value2) {
            addCriterion("PAYMTYPE between", value1, value2, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtypeNotBetween(String value1, String value2) {
            addCriterion("PAYMTYPE not between", value1, value2, "paymtype");
            return (Criteria) this;
        }

        public Criteria andPaymtoIsNull() {
            addCriterion("PAYMTO is null");
            return (Criteria) this;
        }

        public Criteria andPaymtoIsNotNull() {
            addCriterion("PAYMTO is not null");
            return (Criteria) this;
        }

        public Criteria andPaymtoEqualTo(String value) {
            addCriterion("PAYMTO =", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoNotEqualTo(String value) {
            addCriterion("PAYMTO <>", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoGreaterThan(String value) {
            addCriterion("PAYMTO >", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMTO >=", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoLessThan(String value) {
            addCriterion("PAYMTO <", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoLessThanOrEqualTo(String value) {
            addCriterion("PAYMTO <=", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoLike(String value) {
            addCriterion("PAYMTO like", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoNotLike(String value) {
            addCriterion("PAYMTO not like", value, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoIn(List<String> values) {
            addCriterion("PAYMTO in", values, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoNotIn(List<String> values) {
            addCriterion("PAYMTO not in", values, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoBetween(String value1, String value2) {
            addCriterion("PAYMTO between", value1, value2, "paymto");
            return (Criteria) this;
        }

        public Criteria andPaymtoNotBetween(String value1, String value2) {
            addCriterion("PAYMTO not between", value1, value2, "paymto");
            return (Criteria) this;
        }

        public Criteria andPosflagIsNull() {
            addCriterion("POSFLAG is null");
            return (Criteria) this;
        }

        public Criteria andPosflagIsNotNull() {
            addCriterion("POSFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPosflagEqualTo(String value) {
            addCriterion("POSFLAG =", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagNotEqualTo(String value) {
            addCriterion("POSFLAG <>", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagGreaterThan(String value) {
            addCriterion("POSFLAG >", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagGreaterThanOrEqualTo(String value) {
            addCriterion("POSFLAG >=", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagLessThan(String value) {
            addCriterion("POSFLAG <", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagLessThanOrEqualTo(String value) {
            addCriterion("POSFLAG <=", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagLike(String value) {
            addCriterion("POSFLAG like", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagNotLike(String value) {
            addCriterion("POSFLAG not like", value, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagIn(List<String> values) {
            addCriterion("POSFLAG in", values, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagNotIn(List<String> values) {
            addCriterion("POSFLAG not in", values, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagBetween(String value1, String value2) {
            addCriterion("POSFLAG between", value1, value2, "posflag");
            return (Criteria) this;
        }

        public Criteria andPosflagNotBetween(String value1, String value2) {
            addCriterion("POSFLAG not between", value1, value2, "posflag");
            return (Criteria) this;
        }

        public Criteria andVipflagIsNull() {
            addCriterion("VIPFLAG is null");
            return (Criteria) this;
        }

        public Criteria andVipflagIsNotNull() {
            addCriterion("VIPFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andVipflagEqualTo(String value) {
            addCriterion("VIPFLAG =", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagNotEqualTo(String value) {
            addCriterion("VIPFLAG <>", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagGreaterThan(String value) {
            addCriterion("VIPFLAG >", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagGreaterThanOrEqualTo(String value) {
            addCriterion("VIPFLAG >=", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagLessThan(String value) {
            addCriterion("VIPFLAG <", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagLessThanOrEqualTo(String value) {
            addCriterion("VIPFLAG <=", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagLike(String value) {
            addCriterion("VIPFLAG like", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagNotLike(String value) {
            addCriterion("VIPFLAG not like", value, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagIn(List<String> values) {
            addCriterion("VIPFLAG in", values, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagNotIn(List<String> values) {
            addCriterion("VIPFLAG not in", values, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagBetween(String value1, String value2) {
            addCriterion("VIPFLAG between", value1, value2, "vipflag");
            return (Criteria) this;
        }

        public Criteria andVipflagNotBetween(String value1, String value2) {
            addCriterion("VIPFLAG not between", value1, value2, "vipflag");
            return (Criteria) this;
        }

        public Criteria andMscChargeIsNull() {
            addCriterion("MSC_CHARGE is null");
            return (Criteria) this;
        }

        public Criteria andMscChargeIsNotNull() {
            addCriterion("MSC_CHARGE is not null");
            return (Criteria) this;
        }

        public Criteria andMscChargeEqualTo(String value) {
            addCriterion("MSC_CHARGE =", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeNotEqualTo(String value) {
            addCriterion("MSC_CHARGE <>", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeGreaterThan(String value) {
            addCriterion("MSC_CHARGE >", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeGreaterThanOrEqualTo(String value) {
            addCriterion("MSC_CHARGE >=", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeLessThan(String value) {
            addCriterion("MSC_CHARGE <", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeLessThanOrEqualTo(String value) {
            addCriterion("MSC_CHARGE <=", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeLike(String value) {
            addCriterion("MSC_CHARGE like", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeNotLike(String value) {
            addCriterion("MSC_CHARGE not like", value, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeIn(List<String> values) {
            addCriterion("MSC_CHARGE in", values, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeNotIn(List<String> values) {
            addCriterion("MSC_CHARGE not in", values, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeBetween(String value1, String value2) {
            addCriterion("MSC_CHARGE between", value1, value2, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscChargeNotBetween(String value1, String value2) {
            addCriterion("MSC_CHARGE not between", value1, value2, "mscCharge");
            return (Criteria) this;
        }

        public Criteria andMscCalcIsNull() {
            addCriterion("MSC_CALC is null");
            return (Criteria) this;
        }

        public Criteria andMscCalcIsNotNull() {
            addCriterion("MSC_CALC is not null");
            return (Criteria) this;
        }

        public Criteria andMscCalcEqualTo(String value) {
            addCriterion("MSC_CALC =", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcNotEqualTo(String value) {
            addCriterion("MSC_CALC <>", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcGreaterThan(String value) {
            addCriterion("MSC_CALC >", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcGreaterThanOrEqualTo(String value) {
            addCriterion("MSC_CALC >=", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcLessThan(String value) {
            addCriterion("MSC_CALC <", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcLessThanOrEqualTo(String value) {
            addCriterion("MSC_CALC <=", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcLike(String value) {
            addCriterion("MSC_CALC like", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcNotLike(String value) {
            addCriterion("MSC_CALC not like", value, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcIn(List<String> values) {
            addCriterion("MSC_CALC in", values, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcNotIn(List<String> values) {
            addCriterion("MSC_CALC not in", values, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcBetween(String value1, String value2) {
            addCriterion("MSC_CALC between", value1, value2, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscCalcNotBetween(String value1, String value2) {
            addCriterion("MSC_CALC not between", value1, value2, "mscCalc");
            return (Criteria) this;
        }

        public Criteria andMscTableIsNull() {
            addCriterion("MSC_TABLE is null");
            return (Criteria) this;
        }

        public Criteria andMscTableIsNotNull() {
            addCriterion("MSC_TABLE is not null");
            return (Criteria) this;
        }

        public Criteria andMscTableEqualTo(Integer value) {
            addCriterion("MSC_TABLE =", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableNotEqualTo(Integer value) {
            addCriterion("MSC_TABLE <>", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableGreaterThan(Integer value) {
            addCriterion("MSC_TABLE >", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableGreaterThanOrEqualTo(Integer value) {
            addCriterion("MSC_TABLE >=", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableLessThan(Integer value) {
            addCriterion("MSC_TABLE <", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableLessThanOrEqualTo(Integer value) {
            addCriterion("MSC_TABLE <=", value, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableIn(List<Integer> values) {
            addCriterion("MSC_TABLE in", values, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableNotIn(List<Integer> values) {
            addCriterion("MSC_TABLE not in", values, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableBetween(Integer value1, Integer value2) {
            addCriterion("MSC_TABLE between", value1, value2, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscTableNotBetween(Integer value1, Integer value2) {
            addCriterion("MSC_TABLE not between", value1, value2, "mscTable");
            return (Criteria) this;
        }

        public Criteria andMscIsNull() {
            addCriterion("MSC is null");
            return (Criteria) this;
        }

        public Criteria andMscIsNotNull() {
            addCriterion("MSC is not null");
            return (Criteria) this;
        }

        public Criteria andMscEqualTo(Double value) {
            addCriterion("MSC =", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscNotEqualTo(Double value) {
            addCriterion("MSC <>", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscGreaterThan(Double value) {
            addCriterion("MSC >", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscGreaterThanOrEqualTo(Double value) {
            addCriterion("MSC >=", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscLessThan(Double value) {
            addCriterion("MSC <", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscLessThanOrEqualTo(Double value) {
            addCriterion("MSC <=", value, "msc");
            return (Criteria) this;
        }

        public Criteria andMscIn(List<Double> values) {
            addCriterion("MSC in", values, "msc");
            return (Criteria) this;
        }

        public Criteria andMscNotIn(List<Double> values) {
            addCriterion("MSC not in", values, "msc");
            return (Criteria) this;
        }

        public Criteria andMscBetween(Double value1, Double value2) {
            addCriterion("MSC between", value1, value2, "msc");
            return (Criteria) this;
        }

        public Criteria andMscNotBetween(Double value1, Double value2) {
            addCriterion("MSC not between", value1, value2, "msc");
            return (Criteria) this;
        }

        public Criteria andContrnoIsNull() {
            addCriterion("CONTRNO is null");
            return (Criteria) this;
        }

        public Criteria andContrnoIsNotNull() {
            addCriterion("CONTRNO is not null");
            return (Criteria) this;
        }

        public Criteria andContrnoEqualTo(String value) {
            addCriterion("CONTRNO =", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoNotEqualTo(String value) {
            addCriterion("CONTRNO <>", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoGreaterThan(String value) {
            addCriterion("CONTRNO >", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRNO >=", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoLessThan(String value) {
            addCriterion("CONTRNO <", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoLessThanOrEqualTo(String value) {
            addCriterion("CONTRNO <=", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoLike(String value) {
            addCriterion("CONTRNO like", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoNotLike(String value) {
            addCriterion("CONTRNO not like", value, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoIn(List<String> values) {
            addCriterion("CONTRNO in", values, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoNotIn(List<String> values) {
            addCriterion("CONTRNO not in", values, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoBetween(String value1, String value2) {
            addCriterion("CONTRNO between", value1, value2, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrnoNotBetween(String value1, String value2) {
            addCriterion("CONTRNO not between", value1, value2, "contrno");
            return (Criteria) this;
        }

        public Criteria andContrdateIsNull() {
            addCriterion("CONTRDATE is null");
            return (Criteria) this;
        }

        public Criteria andContrdateIsNotNull() {
            addCriterion("CONTRDATE is not null");
            return (Criteria) this;
        }

        public Criteria andContrdateEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRDATE =", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRDATE <>", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateGreaterThan(Date value) {
            addCriterionForJDBCDate("CONTRDATE >", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRDATE >=", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateLessThan(Date value) {
            addCriterionForJDBCDate("CONTRDATE <", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRDATE <=", value, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateIn(List<Date> values) {
            addCriterionForJDBCDate("CONTRDATE in", values, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CONTRDATE not in", values, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CONTRDATE between", value1, value2, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CONTRDATE not between", value1, value2, "contrdate");
            return (Criteria) this;
        }

        public Criteria andContrcnxIsNull() {
            addCriterion("CONTRCNX is null");
            return (Criteria) this;
        }

        public Criteria andContrcnxIsNotNull() {
            addCriterion("CONTRCNX is not null");
            return (Criteria) this;
        }

        public Criteria andContrcnxEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRCNX =", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxNotEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRCNX <>", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxGreaterThan(Date value) {
            addCriterionForJDBCDate("CONTRCNX >", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRCNX >=", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxLessThan(Date value) {
            addCriterionForJDBCDate("CONTRCNX <", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CONTRCNX <=", value, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxIn(List<Date> values) {
            addCriterionForJDBCDate("CONTRCNX in", values, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxNotIn(List<Date> values) {
            addCriterionForJDBCDate("CONTRCNX not in", values, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CONTRCNX between", value1, value2, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrcnxNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CONTRCNX not between", value1, value2, "contrcnx");
            return (Criteria) this;
        }

        public Criteria andContrsignIsNull() {
            addCriterion("CONTRSIGN is null");
            return (Criteria) this;
        }

        public Criteria andContrsignIsNotNull() {
            addCriterion("CONTRSIGN is not null");
            return (Criteria) this;
        }

        public Criteria andContrsignEqualTo(String value) {
            addCriterion("CONTRSIGN =", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignNotEqualTo(String value) {
            addCriterion("CONTRSIGN <>", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignGreaterThan(String value) {
            addCriterion("CONTRSIGN >", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRSIGN >=", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignLessThan(String value) {
            addCriterion("CONTRSIGN <", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignLessThanOrEqualTo(String value) {
            addCriterion("CONTRSIGN <=", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignLike(String value) {
            addCriterion("CONTRSIGN like", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignNotLike(String value) {
            addCriterion("CONTRSIGN not like", value, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignIn(List<String> values) {
            addCriterion("CONTRSIGN in", values, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignNotIn(List<String> values) {
            addCriterion("CONTRSIGN not in", values, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignBetween(String value1, String value2) {
            addCriterion("CONTRSIGN between", value1, value2, "contrsign");
            return (Criteria) this;
        }

        public Criteria andContrsignNotBetween(String value1, String value2) {
            addCriterion("CONTRSIGN not between", value1, value2, "contrsign");
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

        public Criteria andMrchstatIsNull() {
            addCriterion("MRCHSTAT is null");
            return (Criteria) this;
        }

        public Criteria andMrchstatIsNotNull() {
            addCriterion("MRCHSTAT is not null");
            return (Criteria) this;
        }

        public Criteria andMrchstatEqualTo(Integer value) {
            addCriterion("MRCHSTAT =", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatNotEqualTo(Integer value) {
            addCriterion("MRCHSTAT <>", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatGreaterThan(Integer value) {
            addCriterion("MRCHSTAT >", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatGreaterThanOrEqualTo(Integer value) {
            addCriterion("MRCHSTAT >=", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatLessThan(Integer value) {
            addCriterion("MRCHSTAT <", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatLessThanOrEqualTo(Integer value) {
            addCriterion("MRCHSTAT <=", value, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatIn(List<Integer> values) {
            addCriterion("MRCHSTAT in", values, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatNotIn(List<Integer> values) {
            addCriterion("MRCHSTAT not in", values, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatBetween(Integer value1, Integer value2) {
            addCriterion("MRCHSTAT between", value1, value2, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andMrchstatNotBetween(Integer value1, Integer value2) {
            addCriterion("MRCHSTAT not between", value1, value2, "mrchstat");
            return (Criteria) this;
        }

        public Criteria andRncIsNull() {
            addCriterion("RNC is null");
            return (Criteria) this;
        }

        public Criteria andRncIsNotNull() {
            addCriterion("RNC is not null");
            return (Criteria) this;
        }

        public Criteria andRncEqualTo(String value) {
            addCriterion("RNC =", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncNotEqualTo(String value) {
            addCriterion("RNC <>", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncGreaterThan(String value) {
            addCriterion("RNC >", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncGreaterThanOrEqualTo(String value) {
            addCriterion("RNC >=", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncLessThan(String value) {
            addCriterion("RNC <", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncLessThanOrEqualTo(String value) {
            addCriterion("RNC <=", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncLike(String value) {
            addCriterion("RNC like", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncNotLike(String value) {
            addCriterion("RNC not like", value, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncIn(List<String> values) {
            addCriterion("RNC in", values, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncNotIn(List<String> values) {
            addCriterion("RNC not in", values, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncBetween(String value1, String value2) {
            addCriterion("RNC between", value1, value2, "rnc");
            return (Criteria) this;
        }

        public Criteria andRncNotBetween(String value1, String value2) {
            addCriterion("RNC not between", value1, value2, "rnc");
            return (Criteria) this;
        }

        public Criteria andTaxregIsNull() {
            addCriterion("TAXREG is null");
            return (Criteria) this;
        }

        public Criteria andTaxregIsNotNull() {
            addCriterion("TAXREG is not null");
            return (Criteria) this;
        }

        public Criteria andTaxregEqualTo(String value) {
            addCriterion("TAXREG =", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregNotEqualTo(String value) {
            addCriterion("TAXREG <>", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregGreaterThan(String value) {
            addCriterion("TAXREG >", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregGreaterThanOrEqualTo(String value) {
            addCriterion("TAXREG >=", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregLessThan(String value) {
            addCriterion("TAXREG <", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregLessThanOrEqualTo(String value) {
            addCriterion("TAXREG <=", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregLike(String value) {
            addCriterion("TAXREG like", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregNotLike(String value) {
            addCriterion("TAXREG not like", value, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregIn(List<String> values) {
            addCriterion("TAXREG in", values, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregNotIn(List<String> values) {
            addCriterion("TAXREG not in", values, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregBetween(String value1, String value2) {
            addCriterion("TAXREG between", value1, value2, "taxreg");
            return (Criteria) this;
        }

        public Criteria andTaxregNotBetween(String value1, String value2) {
            addCriterion("TAXREG not between", value1, value2, "taxreg");
            return (Criteria) this;
        }

        public Criteria andRetenamtIsNull() {
            addCriterion("RETENAMT is null");
            return (Criteria) this;
        }

        public Criteria andRetenamtIsNotNull() {
            addCriterion("RETENAMT is not null");
            return (Criteria) this;
        }

        public Criteria andRetenamtEqualTo(Double value) {
            addCriterion("RETENAMT =", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtNotEqualTo(Double value) {
            addCriterion("RETENAMT <>", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtGreaterThan(Double value) {
            addCriterion("RETENAMT >", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtGreaterThanOrEqualTo(Double value) {
            addCriterion("RETENAMT >=", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtLessThan(Double value) {
            addCriterion("RETENAMT <", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtLessThanOrEqualTo(Double value) {
            addCriterion("RETENAMT <=", value, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtIn(List<Double> values) {
            addCriterion("RETENAMT in", values, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtNotIn(List<Double> values) {
            addCriterion("RETENAMT not in", values, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtBetween(Double value1, Double value2) {
            addCriterion("RETENAMT between", value1, value2, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenamtNotBetween(Double value1, Double value2) {
            addCriterion("RETENAMT not between", value1, value2, "retenamt");
            return (Criteria) this;
        }

        public Criteria andRetenpcIsNull() {
            addCriterion("RETENPC is null");
            return (Criteria) this;
        }

        public Criteria andRetenpcIsNotNull() {
            addCriterion("RETENPC is not null");
            return (Criteria) this;
        }

        public Criteria andRetenpcEqualTo(Double value) {
            addCriterion("RETENPC =", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcNotEqualTo(Double value) {
            addCriterion("RETENPC <>", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcGreaterThan(Double value) {
            addCriterion("RETENPC >", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcGreaterThanOrEqualTo(Double value) {
            addCriterion("RETENPC >=", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcLessThan(Double value) {
            addCriterion("RETENPC <", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcLessThanOrEqualTo(Double value) {
            addCriterion("RETENPC <=", value, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcIn(List<Double> values) {
            addCriterion("RETENPC in", values, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcNotIn(List<Double> values) {
            addCriterion("RETENPC not in", values, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcBetween(Double value1, Double value2) {
            addCriterion("RETENPC between", value1, value2, "retenpc");
            return (Criteria) this;
        }

        public Criteria andRetenpcNotBetween(Double value1, Double value2) {
            addCriterion("RETENPC not between", value1, value2, "retenpc");
            return (Criteria) this;
        }

        public Criteria andZonegeogIsNull() {
            addCriterion("ZONEGEOG is null");
            return (Criteria) this;
        }

        public Criteria andZonegeogIsNotNull() {
            addCriterion("ZONEGEOG is not null");
            return (Criteria) this;
        }

        public Criteria andZonegeogEqualTo(String value) {
            addCriterion("ZONEGEOG =", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogNotEqualTo(String value) {
            addCriterion("ZONEGEOG <>", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogGreaterThan(String value) {
            addCriterion("ZONEGEOG >", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogGreaterThanOrEqualTo(String value) {
            addCriterion("ZONEGEOG >=", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogLessThan(String value) {
            addCriterion("ZONEGEOG <", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogLessThanOrEqualTo(String value) {
            addCriterion("ZONEGEOG <=", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogLike(String value) {
            addCriterion("ZONEGEOG like", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogNotLike(String value) {
            addCriterion("ZONEGEOG not like", value, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogIn(List<String> values) {
            addCriterion("ZONEGEOG in", values, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogNotIn(List<String> values) {
            addCriterion("ZONEGEOG not in", values, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogBetween(String value1, String value2) {
            addCriterion("ZONEGEOG between", value1, value2, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonegeogNotBetween(String value1, String value2) {
            addCriterion("ZONEGEOG not between", value1, value2, "zonegeog");
            return (Criteria) this;
        }

        public Criteria andZonecomerIsNull() {
            addCriterion("ZONECOMER is null");
            return (Criteria) this;
        }

        public Criteria andZonecomerIsNotNull() {
            addCriterion("ZONECOMER is not null");
            return (Criteria) this;
        }

        public Criteria andZonecomerEqualTo(String value) {
            addCriterion("ZONECOMER =", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerNotEqualTo(String value) {
            addCriterion("ZONECOMER <>", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerGreaterThan(String value) {
            addCriterion("ZONECOMER >", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerGreaterThanOrEqualTo(String value) {
            addCriterion("ZONECOMER >=", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerLessThan(String value) {
            addCriterion("ZONECOMER <", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerLessThanOrEqualTo(String value) {
            addCriterion("ZONECOMER <=", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerLike(String value) {
            addCriterion("ZONECOMER like", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerNotLike(String value) {
            addCriterion("ZONECOMER not like", value, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerIn(List<String> values) {
            addCriterion("ZONECOMER in", values, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerNotIn(List<String> values) {
            addCriterion("ZONECOMER not in", values, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerBetween(String value1, String value2) {
            addCriterion("ZONECOMER between", value1, value2, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonecomerNotBetween(String value1, String value2) {
            addCriterion("ZONECOMER not between", value1, value2, "zonecomer");
            return (Criteria) this;
        }

        public Criteria andZonepostalIsNull() {
            addCriterion("ZONEPOSTAL is null");
            return (Criteria) this;
        }

        public Criteria andZonepostalIsNotNull() {
            addCriterion("ZONEPOSTAL is not null");
            return (Criteria) this;
        }

        public Criteria andZonepostalEqualTo(String value) {
            addCriterion("ZONEPOSTAL =", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalNotEqualTo(String value) {
            addCriterion("ZONEPOSTAL <>", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalGreaterThan(String value) {
            addCriterion("ZONEPOSTAL >", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalGreaterThanOrEqualTo(String value) {
            addCriterion("ZONEPOSTAL >=", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalLessThan(String value) {
            addCriterion("ZONEPOSTAL <", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalLessThanOrEqualTo(String value) {
            addCriterion("ZONEPOSTAL <=", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalLike(String value) {
            addCriterion("ZONEPOSTAL like", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalNotLike(String value) {
            addCriterion("ZONEPOSTAL not like", value, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalIn(List<String> values) {
            addCriterion("ZONEPOSTAL in", values, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalNotIn(List<String> values) {
            addCriterion("ZONEPOSTAL not in", values, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalBetween(String value1, String value2) {
            addCriterion("ZONEPOSTAL between", value1, value2, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andZonepostalNotBetween(String value1, String value2) {
            addCriterion("ZONEPOSTAL not between", value1, value2, "zonepostal");
            return (Criteria) this;
        }

        public Criteria andUsrdata1IsNull() {
            addCriterion("USRDATA1 is null");
            return (Criteria) this;
        }

        public Criteria andUsrdata1IsNotNull() {
            addCriterion("USRDATA1 is not null");
            return (Criteria) this;
        }

        public Criteria andUsrdata1EqualTo(String value) {
            addCriterion("USRDATA1 =", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotEqualTo(String value) {
            addCriterion("USRDATA1 <>", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1GreaterThan(String value) {
            addCriterion("USRDATA1 >", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1GreaterThanOrEqualTo(String value) {
            addCriterion("USRDATA1 >=", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1LessThan(String value) {
            addCriterion("USRDATA1 <", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1LessThanOrEqualTo(String value) {
            addCriterion("USRDATA1 <=", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1Like(String value) {
            addCriterion("USRDATA1 like", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotLike(String value) {
            addCriterion("USRDATA1 not like", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1In(List<String> values) {
            addCriterion("USRDATA1 in", values, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotIn(List<String> values) {
            addCriterion("USRDATA1 not in", values, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1Between(String value1, String value2) {
            addCriterion("USRDATA1 between", value1, value2, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotBetween(String value1, String value2) {
            addCriterion("USRDATA1 not between", value1, value2, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata2IsNull() {
            addCriterion("USRDATA2 is null");
            return (Criteria) this;
        }

        public Criteria andUsrdata2IsNotNull() {
            addCriterion("USRDATA2 is not null");
            return (Criteria) this;
        }

        public Criteria andUsrdata2EqualTo(String value) {
            addCriterion("USRDATA2 =", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotEqualTo(String value) {
            addCriterion("USRDATA2 <>", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2GreaterThan(String value) {
            addCriterion("USRDATA2 >", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2GreaterThanOrEqualTo(String value) {
            addCriterion("USRDATA2 >=", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2LessThan(String value) {
            addCriterion("USRDATA2 <", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2LessThanOrEqualTo(String value) {
            addCriterion("USRDATA2 <=", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2Like(String value) {
            addCriterion("USRDATA2 like", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotLike(String value) {
            addCriterion("USRDATA2 not like", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2In(List<String> values) {
            addCriterion("USRDATA2 in", values, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotIn(List<String> values) {
            addCriterion("USRDATA2 not in", values, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2Between(String value1, String value2) {
            addCriterion("USRDATA2 between", value1, value2, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotBetween(String value1, String value2) {
            addCriterion("USRDATA2 not between", value1, value2, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata3IsNull() {
            addCriterion("USRDATA3 is null");
            return (Criteria) this;
        }

        public Criteria andUsrdata3IsNotNull() {
            addCriterion("USRDATA3 is not null");
            return (Criteria) this;
        }

        public Criteria andUsrdata3EqualTo(String value) {
            addCriterion("USRDATA3 =", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3NotEqualTo(String value) {
            addCriterion("USRDATA3 <>", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3GreaterThan(String value) {
            addCriterion("USRDATA3 >", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3GreaterThanOrEqualTo(String value) {
            addCriterion("USRDATA3 >=", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3LessThan(String value) {
            addCriterion("USRDATA3 <", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3LessThanOrEqualTo(String value) {
            addCriterion("USRDATA3 <=", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3Like(String value) {
            addCriterion("USRDATA3 like", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3NotLike(String value) {
            addCriterion("USRDATA3 not like", value, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3In(List<String> values) {
            addCriterion("USRDATA3 in", values, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3NotIn(List<String> values) {
            addCriterion("USRDATA3 not in", values, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3Between(String value1, String value2) {
            addCriterion("USRDATA3 between", value1, value2, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andUsrdata3NotBetween(String value1, String value2) {
            addCriterion("USRDATA3 not between", value1, value2, "usrdata3");
            return (Criteria) this;
        }

        public Criteria andCycbeginIsNull() {
            addCriterion("CYCBEGIN is null");
            return (Criteria) this;
        }

        public Criteria andCycbeginIsNotNull() {
            addCriterion("CYCBEGIN is not null");
            return (Criteria) this;
        }

        public Criteria andCycbeginEqualTo(Date value) {
            addCriterionForJDBCDate("CYCBEGIN =", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginNotEqualTo(Date value) {
            addCriterionForJDBCDate("CYCBEGIN <>", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginGreaterThan(Date value) {
            addCriterionForJDBCDate("CYCBEGIN >", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CYCBEGIN >=", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginLessThan(Date value) {
            addCriterionForJDBCDate("CYCBEGIN <", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CYCBEGIN <=", value, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginIn(List<Date> values) {
            addCriterionForJDBCDate("CYCBEGIN in", values, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginNotIn(List<Date> values) {
            addCriterionForJDBCDate("CYCBEGIN not in", values, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CYCBEGIN between", value1, value2, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCycbeginNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CYCBEGIN not between", value1, value2, "cycbegin");
            return (Criteria) this;
        }

        public Criteria andCyclenIsNull() {
            addCriterion("CYCLEN is null");
            return (Criteria) this;
        }

        public Criteria andCyclenIsNotNull() {
            addCriterion("CYCLEN is not null");
            return (Criteria) this;
        }

        public Criteria andCyclenEqualTo(Integer value) {
            addCriterion("CYCLEN =", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenNotEqualTo(Integer value) {
            addCriterion("CYCLEN <>", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenGreaterThan(Integer value) {
            addCriterion("CYCLEN >", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenGreaterThanOrEqualTo(Integer value) {
            addCriterion("CYCLEN >=", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenLessThan(Integer value) {
            addCriterion("CYCLEN <", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenLessThanOrEqualTo(Integer value) {
            addCriterion("CYCLEN <=", value, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenIn(List<Integer> values) {
            addCriterion("CYCLEN in", values, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenNotIn(List<Integer> values) {
            addCriterion("CYCLEN not in", values, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenBetween(Integer value1, Integer value2) {
            addCriterion("CYCLEN between", value1, value2, "cyclen");
            return (Criteria) this;
        }

        public Criteria andCyclenNotBetween(Integer value1, Integer value2) {
            addCriterion("CYCLEN not between", value1, value2, "cyclen");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsIsNull() {
            addCriterion("NO_IMPRNTRS is null");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsIsNotNull() {
            addCriterion("NO_IMPRNTRS is not null");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsEqualTo(Integer value) {
            addCriterion("NO_IMPRNTRS =", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsNotEqualTo(Integer value) {
            addCriterion("NO_IMPRNTRS <>", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsGreaterThan(Integer value) {
            addCriterion("NO_IMPRNTRS >", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsGreaterThanOrEqualTo(Integer value) {
            addCriterion("NO_IMPRNTRS >=", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsLessThan(Integer value) {
            addCriterion("NO_IMPRNTRS <", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsLessThanOrEqualTo(Integer value) {
            addCriterion("NO_IMPRNTRS <=", value, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsIn(List<Integer> values) {
            addCriterion("NO_IMPRNTRS in", values, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsNotIn(List<Integer> values) {
            addCriterion("NO_IMPRNTRS not in", values, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsBetween(Integer value1, Integer value2) {
            addCriterion("NO_IMPRNTRS between", value1, value2, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andNoImprntrsNotBetween(Integer value1, Integer value2) {
            addCriterion("NO_IMPRNTRS not between", value1, value2, "noImprntrs");
            return (Criteria) this;
        }

        public Criteria andConaccnoIsNull() {
            addCriterion("CONACCNO is null");
            return (Criteria) this;
        }

        public Criteria andConaccnoIsNotNull() {
            addCriterion("CONACCNO is not null");
            return (Criteria) this;
        }

        public Criteria andConaccnoEqualTo(String value) {
            addCriterion("CONACCNO =", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotEqualTo(String value) {
            addCriterion("CONACCNO <>", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoGreaterThan(String value) {
            addCriterion("CONACCNO >", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoGreaterThanOrEqualTo(String value) {
            addCriterion("CONACCNO >=", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLessThan(String value) {
            addCriterion("CONACCNO <", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLessThanOrEqualTo(String value) {
            addCriterion("CONACCNO <=", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoLike(String value) {
            addCriterion("CONACCNO like", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotLike(String value) {
            addCriterion("CONACCNO not like", value, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoIn(List<String> values) {
            addCriterion("CONACCNO in", values, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotIn(List<String> values) {
            addCriterion("CONACCNO not in", values, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoBetween(String value1, String value2) {
            addCriterion("CONACCNO between", value1, value2, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConaccnoNotBetween(String value1, String value2) {
            addCriterion("CONACCNO not between", value1, value2, "conaccno");
            return (Criteria) this;
        }

        public Criteria andConcurIsNull() {
            addCriterion("CONCUR is null");
            return (Criteria) this;
        }

        public Criteria andConcurIsNotNull() {
            addCriterion("CONCUR is not null");
            return (Criteria) this;
        }

        public Criteria andConcurEqualTo(String value) {
            addCriterion("CONCUR =", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotEqualTo(String value) {
            addCriterion("CONCUR <>", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurGreaterThan(String value) {
            addCriterion("CONCUR >", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurGreaterThanOrEqualTo(String value) {
            addCriterion("CONCUR >=", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLessThan(String value) {
            addCriterion("CONCUR <", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLessThanOrEqualTo(String value) {
            addCriterion("CONCUR <=", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurLike(String value) {
            addCriterion("CONCUR like", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotLike(String value) {
            addCriterion("CONCUR not like", value, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurIn(List<String> values) {
            addCriterion("CONCUR in", values, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotIn(List<String> values) {
            addCriterion("CONCUR not in", values, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurBetween(String value1, String value2) {
            addCriterion("CONCUR between", value1, value2, "concur");
            return (Criteria) this;
        }

        public Criteria andConcurNotBetween(String value1, String value2) {
            addCriterion("CONCUR not between", value1, value2, "concur");
            return (Criteria) this;
        }

        public Criteria andPayoffsetIsNull() {
            addCriterion("PAYOFFSET is null");
            return (Criteria) this;
        }

        public Criteria andPayoffsetIsNotNull() {
            addCriterion("PAYOFFSET is not null");
            return (Criteria) this;
        }

        public Criteria andPayoffsetEqualTo(Integer value) {
            addCriterion("PAYOFFSET =", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetNotEqualTo(Integer value) {
            addCriterion("PAYOFFSET <>", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetGreaterThan(Integer value) {
            addCriterion("PAYOFFSET >", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAYOFFSET >=", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetLessThan(Integer value) {
            addCriterion("PAYOFFSET <", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetLessThanOrEqualTo(Integer value) {
            addCriterion("PAYOFFSET <=", value, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetIn(List<Integer> values) {
            addCriterion("PAYOFFSET in", values, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetNotIn(List<Integer> values) {
            addCriterion("PAYOFFSET not in", values, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetBetween(Integer value1, Integer value2) {
            addCriterion("PAYOFFSET between", value1, value2, "payoffset");
            return (Criteria) this;
        }

        public Criteria andPayoffsetNotBetween(Integer value1, Integer value2) {
            addCriterion("PAYOFFSET not between", value1, value2, "payoffset");
            return (Criteria) this;
        }

        public Criteria andAllowinstIsNull() {
            addCriterion("ALLOWINST is null");
            return (Criteria) this;
        }

        public Criteria andAllowinstIsNotNull() {
            addCriterion("ALLOWINST is not null");
            return (Criteria) this;
        }

        public Criteria andAllowinstEqualTo(Integer value) {
            addCriterion("ALLOWINST =", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstNotEqualTo(Integer value) {
            addCriterion("ALLOWINST <>", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstGreaterThan(Integer value) {
            addCriterion("ALLOWINST >", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstGreaterThanOrEqualTo(Integer value) {
            addCriterion("ALLOWINST >=", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstLessThan(Integer value) {
            addCriterion("ALLOWINST <", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstLessThanOrEqualTo(Integer value) {
            addCriterion("ALLOWINST <=", value, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstIn(List<Integer> values) {
            addCriterion("ALLOWINST in", values, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstNotIn(List<Integer> values) {
            addCriterion("ALLOWINST not in", values, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstBetween(Integer value1, Integer value2) {
            addCriterion("ALLOWINST between", value1, value2, "allowinst");
            return (Criteria) this;
        }

        public Criteria andAllowinstNotBetween(Integer value1, Integer value2) {
            addCriterion("ALLOWINST not between", value1, value2, "allowinst");
            return (Criteria) this;
        }

        public Criteria andPppymntattrIsNull() {
            addCriterion("PPPYMNTATTR is null");
            return (Criteria) this;
        }

        public Criteria andPppymntattrIsNotNull() {
            addCriterion("PPPYMNTATTR is not null");
            return (Criteria) this;
        }

        public Criteria andPppymntattrEqualTo(Integer value) {
            addCriterion("PPPYMNTATTR =", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrNotEqualTo(Integer value) {
            addCriterion("PPPYMNTATTR <>", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrGreaterThan(Integer value) {
            addCriterion("PPPYMNTATTR >", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrGreaterThanOrEqualTo(Integer value) {
            addCriterion("PPPYMNTATTR >=", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrLessThan(Integer value) {
            addCriterion("PPPYMNTATTR <", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrLessThanOrEqualTo(Integer value) {
            addCriterion("PPPYMNTATTR <=", value, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrIn(List<Integer> values) {
            addCriterion("PPPYMNTATTR in", values, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrNotIn(List<Integer> values) {
            addCriterion("PPPYMNTATTR not in", values, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrBetween(Integer value1, Integer value2) {
            addCriterion("PPPYMNTATTR between", value1, value2, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andPppymntattrNotBetween(Integer value1, Integer value2) {
            addCriterion("PPPYMNTATTR not between", value1, value2, "pppymntattr");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNull() {
            addCriterion("CAT_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNotNull() {
            addCriterion("CAT_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andCatParamsEqualTo(String value) {
            addCriterion("CAT_PARAMS =", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotEqualTo(String value) {
            addCriterion("CAT_PARAMS <>", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThan(String value) {
            addCriterion("CAT_PARAMS >", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS >=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThan(String value) {
            addCriterion("CAT_PARAMS <", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS <=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLike(String value) {
            addCriterion("CAT_PARAMS like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotLike(String value) {
            addCriterion("CAT_PARAMS not like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsIn(List<String> values) {
            addCriterion("CAT_PARAMS in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotIn(List<String> values) {
            addCriterion("CAT_PARAMS not in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS not between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIsNull() {
            addCriterion("VERNO_CTX is null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIsNotNull() {
            addCriterion("VERNO_CTX is not null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxEqualTo(Long value) {
            addCriterion("VERNO_CTX =", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotEqualTo(Long value) {
            addCriterion("VERNO_CTX <>", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThan(Long value) {
            addCriterion("VERNO_CTX >", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX >=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThan(Long value) {
            addCriterion("VERNO_CTX <", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX <=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIn(List<Long> values) {
            addCriterion("VERNO_CTX in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotIn(List<Long> values) {
            addCriterion("VERNO_CTX not in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX not between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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