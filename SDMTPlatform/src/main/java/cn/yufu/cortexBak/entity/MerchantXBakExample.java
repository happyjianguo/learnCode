package cn.yufu.cortexBak.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MerchantXBakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantXBakExample() {
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

        public Criteria andIdValidityEqualTo(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY =", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotEqualTo(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY <>", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityGreaterThan(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY >", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY >=", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityLessThan(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY <", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ID_VALIDITY <=", value, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityIn(List<Date> values) {
            addCriterionForJDBCDate("ID_VALIDITY in", values, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotIn(List<Date> values) {
            addCriterionForJDBCDate("ID_VALIDITY not in", values, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ID_VALIDITY between", value1, value2, "idValidity");
            return (Criteria) this;
        }

        public Criteria andIdValidityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ID_VALIDITY not between", value1, value2, "idValidity");
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

        public Criteria andBusLicValidityEqualTo(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY =", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotEqualTo(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY <>", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityGreaterThan(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY >", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY >=", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityLessThan(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY <", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY <=", value, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityIn(List<Date> values) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY in", values, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotIn(List<Date> values) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY not in", values, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY between", value1, value2, "busLicValidity");
            return (Criteria) this;
        }

        public Criteria andBusLicValidityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BUS_LIC_VALIDITY not between", value1, value2, "busLicValidity");
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

        public Criteria andTaxIdValidityEqualTo(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY =", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotEqualTo(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY <>", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityGreaterThan(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY >", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY >=", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityLessThan(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY <", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY <=", value, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityIn(List<Date> values) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY in", values, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotIn(List<Date> values) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY not in", values, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY between", value1, value2, "taxIdValidity");
            return (Criteria) this;
        }

        public Criteria andTaxIdValidityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TAX_ID_VALIDITY not between", value1, value2, "taxIdValidity");
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

        public Criteria andOrgValidityEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY =", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY <>", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityGreaterThan(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY >", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY >=", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityLessThan(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY <", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_VALIDITY <=", value, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_VALIDITY in", values, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_VALIDITY not in", values, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_VALIDITY between", value1, value2, "orgValidity");
            return (Criteria) this;
        }

        public Criteria andOrgValidityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_VALIDITY not between", value1, value2, "orgValidity");
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

        public Criteria andMerchantIdIsNull() {
            addCriterion("MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("MERCHANT_ID =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("MERCHANT_ID <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("MERCHANT_ID >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("MERCHANT_ID <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("MERCHANT_ID in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("MERCHANT_ID not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andBusLicPicIsNull() {
            addCriterion("BUS_LIC_PIC is null");
            return (Criteria) this;
        }

        public Criteria andBusLicPicIsNotNull() {
            addCriterion("BUS_LIC_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andBusLicPicEqualTo(String value) {
            addCriterion("BUS_LIC_PIC =", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicNotEqualTo(String value) {
            addCriterion("BUS_LIC_PIC <>", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicGreaterThan(String value) {
            addCriterion("BUS_LIC_PIC >", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicGreaterThanOrEqualTo(String value) {
            addCriterion("BUS_LIC_PIC >=", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicLessThan(String value) {
            addCriterion("BUS_LIC_PIC <", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicLessThanOrEqualTo(String value) {
            addCriterion("BUS_LIC_PIC <=", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicLike(String value) {
            addCriterion("BUS_LIC_PIC like", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicNotLike(String value) {
            addCriterion("BUS_LIC_PIC not like", value, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicIn(List<String> values) {
            addCriterion("BUS_LIC_PIC in", values, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicNotIn(List<String> values) {
            addCriterion("BUS_LIC_PIC not in", values, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicBetween(String value1, String value2) {
            addCriterion("BUS_LIC_PIC between", value1, value2, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andBusLicPicNotBetween(String value1, String value2) {
            addCriterion("BUS_LIC_PIC not between", value1, value2, "busLicPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicIsNull() {
            addCriterion("TAX_ID_PIC is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicIsNotNull() {
            addCriterion("TAX_ID_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicEqualTo(String value) {
            addCriterion("TAX_ID_PIC =", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicNotEqualTo(String value) {
            addCriterion("TAX_ID_PIC <>", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicGreaterThan(String value) {
            addCriterion("TAX_ID_PIC >", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_ID_PIC >=", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicLessThan(String value) {
            addCriterion("TAX_ID_PIC <", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicLessThanOrEqualTo(String value) {
            addCriterion("TAX_ID_PIC <=", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicLike(String value) {
            addCriterion("TAX_ID_PIC like", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicNotLike(String value) {
            addCriterion("TAX_ID_PIC not like", value, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicIn(List<String> values) {
            addCriterion("TAX_ID_PIC in", values, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicNotIn(List<String> values) {
            addCriterion("TAX_ID_PIC not in", values, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicBetween(String value1, String value2) {
            addCriterion("TAX_ID_PIC between", value1, value2, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andTaxIdPicNotBetween(String value1, String value2) {
            addCriterion("TAX_ID_PIC not between", value1, value2, "taxIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicIsNull() {
            addCriterion("ORG_ID_PIC is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicIsNotNull() {
            addCriterion("ORG_ID_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicEqualTo(String value) {
            addCriterion("ORG_ID_PIC =", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicNotEqualTo(String value) {
            addCriterion("ORG_ID_PIC <>", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicGreaterThan(String value) {
            addCriterion("ORG_ID_PIC >", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID_PIC >=", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicLessThan(String value) {
            addCriterion("ORG_ID_PIC <", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID_PIC <=", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicLike(String value) {
            addCriterion("ORG_ID_PIC like", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicNotLike(String value) {
            addCriterion("ORG_ID_PIC not like", value, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicIn(List<String> values) {
            addCriterion("ORG_ID_PIC in", values, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicNotIn(List<String> values) {
            addCriterion("ORG_ID_PIC not in", values, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicBetween(String value1, String value2) {
            addCriterion("ORG_ID_PIC between", value1, value2, "orgIdPic");
            return (Criteria) this;
        }

        public Criteria andOrgIdPicNotBetween(String value1, String value2) {
            addCriterion("ORG_ID_PIC not between", value1, value2, "orgIdPic");
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

        public Criteria andLrIdValidityEqualTo(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY =", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotEqualTo(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY <>", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityGreaterThan(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY >", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY >=", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityLessThan(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY <", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LR_ID_VALIDITY <=", value, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityIn(List<Date> values) {
            addCriterionForJDBCDate("LR_ID_VALIDITY in", values, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotIn(List<Date> values) {
            addCriterionForJDBCDate("LR_ID_VALIDITY not in", values, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LR_ID_VALIDITY between", value1, value2, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andLrIdValidityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LR_ID_VALIDITY not between", value1, value2, "lrIdValidity");
            return (Criteria) this;
        }

        public Criteria andFsCycleIsNull() {
            addCriterion("FS_CYCLE is null");
            return (Criteria) this;
        }

        public Criteria andFsCycleIsNotNull() {
            addCriterion("FS_CYCLE is not null");
            return (Criteria) this;
        }

        public Criteria andFsCycleEqualTo(String value) {
            addCriterion("FS_CYCLE =", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleNotEqualTo(String value) {
            addCriterion("FS_CYCLE <>", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleGreaterThan(String value) {
            addCriterion("FS_CYCLE >", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleGreaterThanOrEqualTo(String value) {
            addCriterion("FS_CYCLE >=", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleLessThan(String value) {
            addCriterion("FS_CYCLE <", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleLessThanOrEqualTo(String value) {
            addCriterion("FS_CYCLE <=", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleLike(String value) {
            addCriterion("FS_CYCLE like", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleNotLike(String value) {
            addCriterion("FS_CYCLE not like", value, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleIn(List<String> values) {
            addCriterion("FS_CYCLE in", values, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleNotIn(List<String> values) {
            addCriterion("FS_CYCLE not in", values, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleBetween(String value1, String value2) {
            addCriterion("FS_CYCLE between", value1, value2, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleNotBetween(String value1, String value2) {
            addCriterion("FS_CYCLE not between", value1, value2, "fsCycle");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamIsNull() {
            addCriterion("FS_CYCLE_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamIsNotNull() {
            addCriterion("FS_CYCLE_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamEqualTo(String value) {
            addCriterion("FS_CYCLE_PARAM =", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamNotEqualTo(String value) {
            addCriterion("FS_CYCLE_PARAM <>", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamGreaterThan(String value) {
            addCriterion("FS_CYCLE_PARAM >", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamGreaterThanOrEqualTo(String value) {
            addCriterion("FS_CYCLE_PARAM >=", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamLessThan(String value) {
            addCriterion("FS_CYCLE_PARAM <", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamLessThanOrEqualTo(String value) {
            addCriterion("FS_CYCLE_PARAM <=", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamLike(String value) {
            addCriterion("FS_CYCLE_PARAM like", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamNotLike(String value) {
            addCriterion("FS_CYCLE_PARAM not like", value, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamIn(List<String> values) {
            addCriterion("FS_CYCLE_PARAM in", values, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamNotIn(List<String> values) {
            addCriterion("FS_CYCLE_PARAM not in", values, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamBetween(String value1, String value2) {
            addCriterion("FS_CYCLE_PARAM between", value1, value2, "fsCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsCycleParamNotBetween(String value1, String value2) {
            addCriterion("FS_CYCLE_PARAM not between", value1, value2, "fsCycleParam");
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

        public Criteria andLastSettleDateEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE =", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <>", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE >", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE >=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThan(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE not in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE not between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryIsNull() {
            addCriterion("IS_CONSUMP_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryIsNotNull() {
            addCriterion("IS_CONSUMP_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY =", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <>", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryGreaterThan(String value) {
            addCriterion("IS_CONSUMP_CATEGORY >", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY >=", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLessThan(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLessThanOrEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <=", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLike(String value) {
            addCriterion("IS_CONSUMP_CATEGORY like", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotLike(String value) {
            addCriterion("IS_CONSUMP_CATEGORY not like", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryIn(List<String> values) {
            addCriterion("IS_CONSUMP_CATEGORY in", values, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotIn(List<String> values) {
            addCriterion("IS_CONSUMP_CATEGORY not in", values, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryBetween(String value1, String value2) {
            addCriterion("IS_CONSUMP_CATEGORY between", value1, value2, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotBetween(String value1, String value2) {
            addCriterion("IS_CONSUMP_CATEGORY not between", value1, value2, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIsNull() {
            addCriterion("AMT_CONSUMP_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIsNotNull() {
            addCriterion("AMT_CONSUMP_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY =", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <>", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryGreaterThan(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY >", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY >=", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLessThan(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLessThanOrEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <=", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLike(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY like", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotLike(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY not like", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIn(List<String> values) {
            addCriterion("AMT_CONSUMP_CATEGORY in", values, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotIn(List<String> values) {
            addCriterion("AMT_CONSUMP_CATEGORY not in", values, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryBetween(String value1, String value2) {
            addCriterion("AMT_CONSUMP_CATEGORY between", value1, value2, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotBetween(String value1, String value2) {
            addCriterion("AMT_CONSUMP_CATEGORY not between", value1, value2, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideIsNull() {
            addCriterion("IS_FMRCHNO_DECIDE is null");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideIsNotNull() {
            addCriterion("IS_FMRCHNO_DECIDE is not null");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideEqualTo(String value) {
            addCriterion("IS_FMRCHNO_DECIDE =", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideNotEqualTo(String value) {
            addCriterion("IS_FMRCHNO_DECIDE <>", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideGreaterThan(String value) {
            addCriterion("IS_FMRCHNO_DECIDE >", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideGreaterThanOrEqualTo(String value) {
            addCriterion("IS_FMRCHNO_DECIDE >=", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideLessThan(String value) {
            addCriterion("IS_FMRCHNO_DECIDE <", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideLessThanOrEqualTo(String value) {
            addCriterion("IS_FMRCHNO_DECIDE <=", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideLike(String value) {
            addCriterion("IS_FMRCHNO_DECIDE like", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideNotLike(String value) {
            addCriterion("IS_FMRCHNO_DECIDE not like", value, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideIn(List<String> values) {
            addCriterion("IS_FMRCHNO_DECIDE in", values, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideNotIn(List<String> values) {
            addCriterion("IS_FMRCHNO_DECIDE not in", values, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideBetween(String value1, String value2) {
            addCriterion("IS_FMRCHNO_DECIDE between", value1, value2, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andIsFmrchnoDecideNotBetween(String value1, String value2) {
            addCriterion("IS_FMRCHNO_DECIDE not between", value1, value2, "isFmrchnoDecide");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleIsNull() {
            addCriterion("FS_KP_CYCLE is null");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleIsNotNull() {
            addCriterion("FS_KP_CYCLE is not null");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleEqualTo(String value) {
            addCriterion("FS_KP_CYCLE =", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleNotEqualTo(String value) {
            addCriterion("FS_KP_CYCLE <>", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleGreaterThan(String value) {
            addCriterion("FS_KP_CYCLE >", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleGreaterThanOrEqualTo(String value) {
            addCriterion("FS_KP_CYCLE >=", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleLessThan(String value) {
            addCriterion("FS_KP_CYCLE <", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleLessThanOrEqualTo(String value) {
            addCriterion("FS_KP_CYCLE <=", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleLike(String value) {
            addCriterion("FS_KP_CYCLE like", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleNotLike(String value) {
            addCriterion("FS_KP_CYCLE not like", value, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleIn(List<String> values) {
            addCriterion("FS_KP_CYCLE in", values, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleNotIn(List<String> values) {
            addCriterion("FS_KP_CYCLE not in", values, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleBetween(String value1, String value2) {
            addCriterion("FS_KP_CYCLE between", value1, value2, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleNotBetween(String value1, String value2) {
            addCriterion("FS_KP_CYCLE not between", value1, value2, "fsKpCycle");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamIsNull() {
            addCriterion("FS_KP_CYCLE_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamIsNotNull() {
            addCriterion("FS_KP_CYCLE_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamEqualTo(String value) {
            addCriterion("FS_KP_CYCLE_PARAM =", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamNotEqualTo(String value) {
            addCriterion("FS_KP_CYCLE_PARAM <>", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamGreaterThan(String value) {
            addCriterion("FS_KP_CYCLE_PARAM >", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamGreaterThanOrEqualTo(String value) {
            addCriterion("FS_KP_CYCLE_PARAM >=", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamLessThan(String value) {
            addCriterion("FS_KP_CYCLE_PARAM <", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamLessThanOrEqualTo(String value) {
            addCriterion("FS_KP_CYCLE_PARAM <=", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamLike(String value) {
            addCriterion("FS_KP_CYCLE_PARAM like", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamNotLike(String value) {
            addCriterion("FS_KP_CYCLE_PARAM not like", value, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamIn(List<String> values) {
            addCriterion("FS_KP_CYCLE_PARAM in", values, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamNotIn(List<String> values) {
            addCriterion("FS_KP_CYCLE_PARAM not in", values, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamBetween(String value1, String value2) {
            addCriterion("FS_KP_CYCLE_PARAM between", value1, value2, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andFsKpCycleParamNotBetween(String value1, String value2) {
            addCriterion("FS_KP_CYCLE_PARAM not between", value1, value2, "fsKpCycleParam");
            return (Criteria) this;
        }

        public Criteria andLastKpDateIsNull() {
            addCriterion("LAST_KP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastKpDateIsNotNull() {
            addCriterion("LAST_KP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastKpDateEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE =", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE <>", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE >", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE >=", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateLessThan(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE <", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_KP_DATE <=", value, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_KP_DATE in", values, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_KP_DATE not in", values, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_KP_DATE between", value1, value2, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andLastKpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_KP_DATE not between", value1, value2, "lastKpDate");
            return (Criteria) this;
        }

        public Criteria andIsBjIsNull() {
            addCriterion("IS_BJ is null");
            return (Criteria) this;
        }

        public Criteria andIsBjIsNotNull() {
            addCriterion("IS_BJ is not null");
            return (Criteria) this;
        }

        public Criteria andIsBjEqualTo(String value) {
            addCriterion("IS_BJ =", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjNotEqualTo(String value) {
            addCriterion("IS_BJ <>", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjGreaterThan(String value) {
            addCriterion("IS_BJ >", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BJ >=", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjLessThan(String value) {
            addCriterion("IS_BJ <", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjLessThanOrEqualTo(String value) {
            addCriterion("IS_BJ <=", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjLike(String value) {
            addCriterion("IS_BJ like", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjNotLike(String value) {
            addCriterion("IS_BJ not like", value, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjIn(List<String> values) {
            addCriterion("IS_BJ in", values, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjNotIn(List<String> values) {
            addCriterion("IS_BJ not in", values, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjBetween(String value1, String value2) {
            addCriterion("IS_BJ between", value1, value2, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsBjNotBetween(String value1, String value2) {
            addCriterion("IS_BJ not between", value1, value2, "isBj");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupIsNull() {
            addCriterion("IS_CARD_TYPE_GROUP is null");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupIsNotNull() {
            addCriterion("IS_CARD_TYPE_GROUP is not null");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupEqualTo(String value) {
            addCriterion("IS_CARD_TYPE_GROUP =", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupNotEqualTo(String value) {
            addCriterion("IS_CARD_TYPE_GROUP <>", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupGreaterThan(String value) {
            addCriterion("IS_CARD_TYPE_GROUP >", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CARD_TYPE_GROUP >=", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupLessThan(String value) {
            addCriterion("IS_CARD_TYPE_GROUP <", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupLessThanOrEqualTo(String value) {
            addCriterion("IS_CARD_TYPE_GROUP <=", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupLike(String value) {
            addCriterion("IS_CARD_TYPE_GROUP like", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupNotLike(String value) {
            addCriterion("IS_CARD_TYPE_GROUP not like", value, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupIn(List<String> values) {
            addCriterion("IS_CARD_TYPE_GROUP in", values, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupNotIn(List<String> values) {
            addCriterion("IS_CARD_TYPE_GROUP not in", values, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupBetween(String value1, String value2) {
            addCriterion("IS_CARD_TYPE_GROUP between", value1, value2, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsCardTypeGroupNotBetween(String value1, String value2) {
            addCriterion("IS_CARD_TYPE_GROUP not between", value1, value2, "isCardTypeGroup");
            return (Criteria) this;
        }

        public Criteria andIsKpIsNull() {
            addCriterion("IS_KP is null");
            return (Criteria) this;
        }

        public Criteria andIsKpIsNotNull() {
            addCriterion("IS_KP is not null");
            return (Criteria) this;
        }

        public Criteria andIsKpEqualTo(String value) {
            addCriterion("IS_KP =", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotEqualTo(String value) {
            addCriterion("IS_KP <>", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpGreaterThan(String value) {
            addCriterion("IS_KP >", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpGreaterThanOrEqualTo(String value) {
            addCriterion("IS_KP >=", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLessThan(String value) {
            addCriterion("IS_KP <", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLessThanOrEqualTo(String value) {
            addCriterion("IS_KP <=", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLike(String value) {
            addCriterion("IS_KP like", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotLike(String value) {
            addCriterion("IS_KP not like", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpIn(List<String> values) {
            addCriterion("IS_KP in", values, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotIn(List<String> values) {
            addCriterion("IS_KP not in", values, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpBetween(String value1, String value2) {
            addCriterion("IS_KP between", value1, value2, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotBetween(String value1, String value2) {
            addCriterion("IS_KP not between", value1, value2, "isKp");
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