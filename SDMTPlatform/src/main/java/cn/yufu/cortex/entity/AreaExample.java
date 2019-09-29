package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AreaExample() {
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

        public Criteria andProvinceCityIsNull() {
            addCriterion("PROVINCE_CITY is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCityIsNotNull() {
            addCriterion("PROVINCE_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCityEqualTo(String value) {
            addCriterion("PROVINCE_CITY =", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityNotEqualTo(String value) {
            addCriterion("PROVINCE_CITY <>", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityGreaterThan(String value) {
            addCriterion("PROVINCE_CITY >", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CITY >=", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityLessThan(String value) {
            addCriterion("PROVINCE_CITY <", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CITY <=", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityLike(String value) {
            addCriterion("PROVINCE_CITY like", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityNotLike(String value) {
            addCriterion("PROVINCE_CITY not like", value, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityIn(List<String> values) {
            addCriterion("PROVINCE_CITY in", values, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityNotIn(List<String> values) {
            addCriterion("PROVINCE_CITY not in", values, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityBetween(String value1, String value2) {
            addCriterion("PROVINCE_CITY between", value1, value2, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andProvinceCityNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_CITY not between", value1, value2, "provinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityIsNull() {
            addCriterion("ENPROVINCE_CITY is null");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityIsNotNull() {
            addCriterion("ENPROVINCE_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityEqualTo(String value) {
            addCriterion("ENPROVINCE_CITY =", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityNotEqualTo(String value) {
            addCriterion("ENPROVINCE_CITY <>", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityGreaterThan(String value) {
            addCriterion("ENPROVINCE_CITY >", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityGreaterThanOrEqualTo(String value) {
            addCriterion("ENPROVINCE_CITY >=", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityLessThan(String value) {
            addCriterion("ENPROVINCE_CITY <", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityLessThanOrEqualTo(String value) {
            addCriterion("ENPROVINCE_CITY <=", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityLike(String value) {
            addCriterion("ENPROVINCE_CITY like", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityNotLike(String value) {
            addCriterion("ENPROVINCE_CITY not like", value, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityIn(List<String> values) {
            addCriterion("ENPROVINCE_CITY in", values, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityNotIn(List<String> values) {
            addCriterion("ENPROVINCE_CITY not in", values, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityBetween(String value1, String value2) {
            addCriterion("ENPROVINCE_CITY between", value1, value2, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andEnprovinceCityNotBetween(String value1, String value2) {
            addCriterion("ENPROVINCE_CITY not between", value1, value2, "enprovinceCity");
            return (Criteria) this;
        }

        public Criteria andFidIsNull() {
            addCriterion("FID is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("FID is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(Long value) {
            addCriterion("FID =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Long value) {
            addCriterion("FID <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Long value) {
            addCriterion("FID >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Long value) {
            addCriterion("FID >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Long value) {
            addCriterion("FID <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Long value) {
            addCriterion("FID <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Long> values) {
            addCriterion("FID in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Long> values) {
            addCriterion("FID not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Long value1, Long value2) {
            addCriterion("FID between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Long value1, Long value2) {
            addCriterion("FID not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andParentpathIsNull() {
            addCriterion("PARENTPATH is null");
            return (Criteria) this;
        }

        public Criteria andParentpathIsNotNull() {
            addCriterion("PARENTPATH is not null");
            return (Criteria) this;
        }

        public Criteria andParentpathEqualTo(String value) {
            addCriterion("PARENTPATH =", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathNotEqualTo(String value) {
            addCriterion("PARENTPATH <>", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathGreaterThan(String value) {
            addCriterion("PARENTPATH >", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTPATH >=", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathLessThan(String value) {
            addCriterion("PARENTPATH <", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathLessThanOrEqualTo(String value) {
            addCriterion("PARENTPATH <=", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathLike(String value) {
            addCriterion("PARENTPATH like", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathNotLike(String value) {
            addCriterion("PARENTPATH not like", value, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathIn(List<String> values) {
            addCriterion("PARENTPATH in", values, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathNotIn(List<String> values) {
            addCriterion("PARENTPATH not in", values, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathBetween(String value1, String value2) {
            addCriterion("PARENTPATH between", value1, value2, "parentpath");
            return (Criteria) this;
        }

        public Criteria andParentpathNotBetween(String value1, String value2) {
            addCriterion("PARENTPATH not between", value1, value2, "parentpath");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("DEPTH is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("DEPTH is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(Short value) {
            addCriterion("DEPTH =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(Short value) {
            addCriterion("DEPTH <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(Short value) {
            addCriterion("DEPTH >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(Short value) {
            addCriterion("DEPTH >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(Short value) {
            addCriterion("DEPTH <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(Short value) {
            addCriterion("DEPTH <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<Short> values) {
            addCriterion("DEPTH in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<Short> values) {
            addCriterion("DEPTH not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(Short value1, Short value2) {
            addCriterion("DEPTH between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(Short value1, Short value2) {
            addCriterion("DEPTH not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("ORDERID is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("ORDERID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("ORDERID =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("ORDERID <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("ORDERID >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDERID >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("ORDERID <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("ORDERID <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("ORDERID in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("ORDERID not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("ORDERID between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("ORDERID not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andChildIsNull() {
            addCriterion("CHILD is null");
            return (Criteria) this;
        }

        public Criteria andChildIsNotNull() {
            addCriterion("CHILD is not null");
            return (Criteria) this;
        }

        public Criteria andChildEqualTo(Long value) {
            addCriterion("CHILD =", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildNotEqualTo(Long value) {
            addCriterion("CHILD <>", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildGreaterThan(Long value) {
            addCriterion("CHILD >", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildGreaterThanOrEqualTo(Long value) {
            addCriterion("CHILD >=", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildLessThan(Long value) {
            addCriterion("CHILD <", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildLessThanOrEqualTo(Long value) {
            addCriterion("CHILD <=", value, "child");
            return (Criteria) this;
        }

        public Criteria andChildIn(List<Long> values) {
            addCriterion("CHILD in", values, "child");
            return (Criteria) this;
        }

        public Criteria andChildNotIn(List<Long> values) {
            addCriterion("CHILD not in", values, "child");
            return (Criteria) this;
        }

        public Criteria andChildBetween(Long value1, Long value2) {
            addCriterion("CHILD between", value1, value2, "child");
            return (Criteria) this;
        }

        public Criteria andChildNotBetween(Long value1, Long value2) {
            addCriterion("CHILD not between", value1, value2, "child");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNull() {
            addCriterion("ISUSE is null");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNotNull() {
            addCriterion("ISUSE is not null");
            return (Criteria) this;
        }

        public Criteria andIsuseEqualTo(String value) {
            addCriterion("ISUSE =", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotEqualTo(String value) {
            addCriterion("ISUSE <>", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThan(String value) {
            addCriterion("ISUSE >", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThanOrEqualTo(String value) {
            addCriterion("ISUSE >=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThan(String value) {
            addCriterion("ISUSE <", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThanOrEqualTo(String value) {
            addCriterion("ISUSE <=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLike(String value) {
            addCriterion("ISUSE like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotLike(String value) {
            addCriterion("ISUSE not like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseIn(List<String> values) {
            addCriterion("ISUSE in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotIn(List<String> values) {
            addCriterion("ISUSE not in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseBetween(String value1, String value2) {
            addCriterion("ISUSE between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotBetween(String value1, String value2) {
            addCriterion("ISUSE not between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andAdddateIsNull() {
            addCriterion("ADDDATE is null");
            return (Criteria) this;
        }

        public Criteria andAdddateIsNotNull() {
            addCriterion("ADDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andAdddateEqualTo(Date value) {
            addCriterionForJDBCDate("ADDDATE =", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ADDDATE <>", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateGreaterThan(Date value) {
            addCriterionForJDBCDate("ADDDATE >", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADDDATE >=", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateLessThan(Date value) {
            addCriterionForJDBCDate("ADDDATE <", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADDDATE <=", value, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateIn(List<Date> values) {
            addCriterionForJDBCDate("ADDDATE in", values, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ADDDATE not in", values, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADDDATE between", value1, value2, "adddate");
            return (Criteria) this;
        }

        public Criteria andAdddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADDDATE not between", value1, value2, "adddate");
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