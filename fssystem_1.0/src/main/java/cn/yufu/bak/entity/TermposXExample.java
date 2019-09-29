package cn.yufu.bak.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TermposXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermposXExample() {
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

        public Criteria andPosTelIsNull() {
            addCriterion("POS_TEL is null");
            return (Criteria) this;
        }

        public Criteria andPosTelIsNotNull() {
            addCriterion("POS_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andPosTelEqualTo(String value) {
            addCriterion("POS_TEL =", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelNotEqualTo(String value) {
            addCriterion("POS_TEL <>", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelGreaterThan(String value) {
            addCriterion("POS_TEL >", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelGreaterThanOrEqualTo(String value) {
            addCriterion("POS_TEL >=", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelLessThan(String value) {
            addCriterion("POS_TEL <", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelLessThanOrEqualTo(String value) {
            addCriterion("POS_TEL <=", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelLike(String value) {
            addCriterion("POS_TEL like", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelNotLike(String value) {
            addCriterion("POS_TEL not like", value, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelIn(List<String> values) {
            addCriterion("POS_TEL in", values, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelNotIn(List<String> values) {
            addCriterion("POS_TEL not in", values, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelBetween(String value1, String value2) {
            addCriterion("POS_TEL between", value1, value2, "posTel");
            return (Criteria) this;
        }

        public Criteria andPosTelNotBetween(String value1, String value2) {
            addCriterion("POS_TEL not between", value1, value2, "posTel");
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

        public Criteria andBatchNoEqualTo(Long value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(Long value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(Long value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(Long value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(Long value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(Long value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<Long> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<Long> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(Long value1, Long value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(Long value1, Long value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
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

        public Criteria andAddDateEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_DATE =", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_DATE <>", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ADD_DATE >", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_DATE >=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThan(Date value) {
            addCriterionForJDBCDate("ADD_DATE <", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_DATE <=", value, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateIn(List<Date> values) {
            addCriterionForJDBCDate("ADD_DATE in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ADD_DATE not in", values, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADD_DATE between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andAddDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADD_DATE not between", value1, value2, "addDate");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("LOCATION is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("LOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("LOCATION =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("LOCATION <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("LOCATION >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATION >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("LOCATION <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("LOCATION <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("LOCATION like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("LOCATION not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("LOCATION in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("LOCATION not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("LOCATION between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("LOCATION not between", value1, value2, "location");
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

        public Criteria andCityNoIsNull() {
            addCriterion("CITY_NO is null");
            return (Criteria) this;
        }

        public Criteria andCityNoIsNotNull() {
            addCriterion("CITY_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCityNoEqualTo(Long value) {
            addCriterion("CITY_NO =", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotEqualTo(Long value) {
            addCriterion("CITY_NO <>", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoGreaterThan(Long value) {
            addCriterion("CITY_NO >", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoGreaterThanOrEqualTo(Long value) {
            addCriterion("CITY_NO >=", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoLessThan(Long value) {
            addCriterion("CITY_NO <", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoLessThanOrEqualTo(Long value) {
            addCriterion("CITY_NO <=", value, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoIn(List<Long> values) {
            addCriterion("CITY_NO in", values, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotIn(List<Long> values) {
            addCriterion("CITY_NO not in", values, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoBetween(Long value1, Long value2) {
            addCriterion("CITY_NO between", value1, value2, "cityNo");
            return (Criteria) this;
        }

        public Criteria andCityNoNotBetween(Long value1, Long value2) {
            addCriterion("CITY_NO not between", value1, value2, "cityNo");
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

        public Criteria andProvinceEqualTo(Long value) {
            addCriterion("PROVINCE =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Long value) {
            addCriterion("PROVINCE <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Long value) {
            addCriterion("PROVINCE >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Long value) {
            addCriterion("PROVINCE >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Long value) {
            addCriterion("PROVINCE <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Long value) {
            addCriterion("PROVINCE <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Long> values) {
            addCriterion("PROVINCE in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Long> values) {
            addCriterion("PROVINCE not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Long value1, Long value2) {
            addCriterion("PROVINCE between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Long value1, Long value2) {
            addCriterion("PROVINCE not between", value1, value2, "province");
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

        public Criteria andZoneEqualTo(Long value) {
            addCriterion("ZONE =", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotEqualTo(Long value) {
            addCriterion("ZONE <>", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThan(Long value) {
            addCriterion("ZONE >", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThanOrEqualTo(Long value) {
            addCriterion("ZONE >=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThan(Long value) {
            addCriterion("ZONE <", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThanOrEqualTo(Long value) {
            addCriterion("ZONE <=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneIn(List<Long> values) {
            addCriterion("ZONE in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotIn(List<Long> values) {
            addCriterion("ZONE not in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneBetween(Long value1, Long value2) {
            addCriterion("ZONE between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotBetween(Long value1, Long value2) {
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

        public Criteria andTermcodeIsNull() {
            addCriterion("TERMCODE is null");
            return (Criteria) this;
        }

        public Criteria andTermcodeIsNotNull() {
            addCriterion("TERMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTermcodeEqualTo(String value) {
            addCriterion("TERMCODE =", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotEqualTo(String value) {
            addCriterion("TERMCODE <>", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThan(String value) {
            addCriterion("TERMCODE >", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TERMCODE >=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThan(String value) {
            addCriterion("TERMCODE <", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThanOrEqualTo(String value) {
            addCriterion("TERMCODE <=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLike(String value) {
            addCriterion("TERMCODE like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotLike(String value) {
            addCriterion("TERMCODE not like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeIn(List<String> values) {
            addCriterion("TERMCODE in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotIn(List<String> values) {
            addCriterion("TERMCODE not in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeBetween(String value1, String value2) {
            addCriterion("TERMCODE between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotBetween(String value1, String value2) {
            addCriterion("TERMCODE not between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTimezoneIsNull() {
            addCriterion("TIMEZONE is null");
            return (Criteria) this;
        }

        public Criteria andTimezoneIsNotNull() {
            addCriterion("TIMEZONE is not null");
            return (Criteria) this;
        }

        public Criteria andTimezoneEqualTo(String value) {
            addCriterion("TIMEZONE =", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotEqualTo(String value) {
            addCriterion("TIMEZONE <>", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneGreaterThan(String value) {
            addCriterion("TIMEZONE >", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneGreaterThanOrEqualTo(String value) {
            addCriterion("TIMEZONE >=", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLessThan(String value) {
            addCriterion("TIMEZONE <", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLessThanOrEqualTo(String value) {
            addCriterion("TIMEZONE <=", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneLike(String value) {
            addCriterion("TIMEZONE like", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotLike(String value) {
            addCriterion("TIMEZONE not like", value, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneIn(List<String> values) {
            addCriterion("TIMEZONE in", values, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotIn(List<String> values) {
            addCriterion("TIMEZONE not in", values, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneBetween(String value1, String value2) {
            addCriterion("TIMEZONE between", value1, value2, "timezone");
            return (Criteria) this;
        }

        public Criteria andTimezoneNotBetween(String value1, String value2) {
            addCriterion("TIMEZONE not between", value1, value2, "timezone");
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

        public Criteria andTermStatIsNull() {
            addCriterion("TERM_STAT is null");
            return (Criteria) this;
        }

        public Criteria andTermStatIsNotNull() {
            addCriterion("TERM_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andTermStatEqualTo(String value) {
            addCriterion("TERM_STAT =", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatNotEqualTo(String value) {
            addCriterion("TERM_STAT <>", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatGreaterThan(String value) {
            addCriterion("TERM_STAT >", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_STAT >=", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatLessThan(String value) {
            addCriterion("TERM_STAT <", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatLessThanOrEqualTo(String value) {
            addCriterion("TERM_STAT <=", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatLike(String value) {
            addCriterion("TERM_STAT like", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatNotLike(String value) {
            addCriterion("TERM_STAT not like", value, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatIn(List<String> values) {
            addCriterion("TERM_STAT in", values, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatNotIn(List<String> values) {
            addCriterion("TERM_STAT not in", values, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatBetween(String value1, String value2) {
            addCriterion("TERM_STAT between", value1, value2, "termStat");
            return (Criteria) this;
        }

        public Criteria andTermStatNotBetween(String value1, String value2) {
            addCriterion("TERM_STAT not between", value1, value2, "termStat");
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

        public Criteria andDisabledDateIsNull() {
            addCriterion("DISABLED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDisabledDateIsNotNull() {
            addCriterion("DISABLED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledDateEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE =", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE <>", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE >", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE >=", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateLessThan(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE <", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLED_DATE <=", value, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateIn(List<Date> values) {
            addCriterionForJDBCDate("DISABLED_DATE in", values, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DISABLED_DATE not in", values, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISABLED_DATE between", value1, value2, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andDisabledDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISABLED_DATE not between", value1, value2, "disabledDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateIsNull() {
            addCriterion("ENABLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEnableDateIsNotNull() {
            addCriterion("ENABLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEnableDateEqualTo(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE =", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE <>", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE >", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE >=", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateLessThan(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE <", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ENABLE_DATE <=", value, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateIn(List<Date> values) {
            addCriterionForJDBCDate("ENABLE_DATE in", values, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ENABLE_DATE not in", values, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ENABLE_DATE between", value1, value2, "enableDate");
            return (Criteria) this;
        }

        public Criteria andEnableDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ENABLE_DATE not between", value1, value2, "enableDate");
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