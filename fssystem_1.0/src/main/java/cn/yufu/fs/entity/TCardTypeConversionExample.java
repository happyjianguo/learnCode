package cn.yufu.fs.entity;

import java.util.ArrayList;
import java.util.List;

public class TCardTypeConversionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCardTypeConversionExample() {
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

        public Criteria andCardtypeIsNull() {
            addCriterion("CARDTYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNotNull() {
            addCriterion("CARDTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeEqualTo(Integer value) {
            addCriterion("CARDTYPE =", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotEqualTo(Integer value) {
            addCriterion("CARDTYPE <>", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThan(Integer value) {
            addCriterion("CARDTYPE >", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CARDTYPE >=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThan(Integer value) {
            addCriterion("CARDTYPE <", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThanOrEqualTo(Integer value) {
            addCriterion("CARDTYPE <=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeIn(List<Integer> values) {
            addCriterion("CARDTYPE in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotIn(List<Integer> values) {
            addCriterion("CARDTYPE not in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeBetween(Integer value1, Integer value2) {
            addCriterion("CARDTYPE between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("CARDTYPE not between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIsNull() {
            addCriterion("CARDTYPENAME is null");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIsNotNull() {
            addCriterion("CARDTYPENAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypenameEqualTo(String value) {
            addCriterion("CARDTYPENAME =", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotEqualTo(String value) {
            addCriterion("CARDTYPENAME <>", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameGreaterThan(String value) {
            addCriterion("CARDTYPENAME >", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("CARDTYPENAME >=", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLessThan(String value) {
            addCriterion("CARDTYPENAME <", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLessThanOrEqualTo(String value) {
            addCriterion("CARDTYPENAME <=", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameLike(String value) {
            addCriterion("CARDTYPENAME like", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotLike(String value) {
            addCriterion("CARDTYPENAME not like", value, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameIn(List<String> values) {
            addCriterion("CARDTYPENAME in", values, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotIn(List<String> values) {
            addCriterion("CARDTYPENAME not in", values, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameBetween(String value1, String value2) {
            addCriterion("CARDTYPENAME between", value1, value2, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypenameNotBetween(String value1, String value2) {
            addCriterion("CARDTYPENAME not between", value1, value2, "cardtypename");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdIsNull() {
            addCriterion("CARDTYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdIsNotNull() {
            addCriterion("CARDTYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdEqualTo(Integer value) {
            addCriterion("CARDTYPE_ID =", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNotEqualTo(Integer value) {
            addCriterion("CARDTYPE_ID <>", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdGreaterThan(Integer value) {
            addCriterion("CARDTYPE_ID >", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CARDTYPE_ID >=", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdLessThan(Integer value) {
            addCriterion("CARDTYPE_ID <", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("CARDTYPE_ID <=", value, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdIn(List<Integer> values) {
            addCriterion("CARDTYPE_ID in", values, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNotIn(List<Integer> values) {
            addCriterion("CARDTYPE_ID not in", values, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdBetween(Integer value1, Integer value2) {
            addCriterion("CARDTYPE_ID between", value1, value2, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CARDTYPE_ID not between", value1, value2, "cardtypeId");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameIsNull() {
            addCriterion("CARDTYPE_ID_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameIsNotNull() {
            addCriterion("CARDTYPE_ID_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameEqualTo(String value) {
            addCriterion("CARDTYPE_ID_NAME =", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameNotEqualTo(String value) {
            addCriterion("CARDTYPE_ID_NAME <>", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameGreaterThan(String value) {
            addCriterion("CARDTYPE_ID_NAME >", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameGreaterThanOrEqualTo(String value) {
            addCriterion("CARDTYPE_ID_NAME >=", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameLessThan(String value) {
            addCriterion("CARDTYPE_ID_NAME <", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameLessThanOrEqualTo(String value) {
            addCriterion("CARDTYPE_ID_NAME <=", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameLike(String value) {
            addCriterion("CARDTYPE_ID_NAME like", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameNotLike(String value) {
            addCriterion("CARDTYPE_ID_NAME not like", value, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameIn(List<String> values) {
            addCriterion("CARDTYPE_ID_NAME in", values, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameNotIn(List<String> values) {
            addCriterion("CARDTYPE_ID_NAME not in", values, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameBetween(String value1, String value2) {
            addCriterion("CARDTYPE_ID_NAME between", value1, value2, "cardtypeIdName");
            return (Criteria) this;
        }

        public Criteria andCardtypeIdNameNotBetween(String value1, String value2) {
            addCriterion("CARDTYPE_ID_NAME not between", value1, value2, "cardtypeIdName");
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