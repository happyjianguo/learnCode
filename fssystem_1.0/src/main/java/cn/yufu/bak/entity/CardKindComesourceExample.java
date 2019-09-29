package cn.yufu.bak.entity;

import java.util.ArrayList;
import java.util.List;

public class CardKindComesourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardKindComesourceExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }
        
        public Criteria andCardnumberIsNull() {
            addCriterion("CARDNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCardnumberIsNotNull() {
            addCriterion("CARDNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCardnumberEqualTo(String value) {
            addCriterion("CARDNUMBER =", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotEqualTo(String value) {
            addCriterion("CARDNUMBER <>", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberGreaterThan(String value) {
            addCriterion("CARDNUMBER >", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberGreaterThanOrEqualTo(String value) {
            addCriterion("CARDNUMBER >=", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLessThan(String value) {
            addCriterion("CARDNUMBER <", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLessThanOrEqualTo(String value) {
            addCriterion("CARDNUMBER <=", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberLike(String value) {
            addCriterion("CARDNUMBER like", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotLike(String value) {
            addCriterion("CARDNUMBER not like", value, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberIn(List<String> values) {
            addCriterion("CARDNUMBER in", values, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotIn(List<String> values) {
            addCriterion("CARDNUMBER not in", values, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberBetween(String value1, String value2) {
            addCriterion("CARDNUMBER between", value1, value2, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andCardnumberNotBetween(String value1, String value2) {
            addCriterion("CARDNUMBER not between", value1, value2, "cardnumber");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameIsNull() {
            addCriterion("DATABASESOURCENAME is null");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameIsNotNull() {
            addCriterion("DATABASESOURCENAME is not null");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameEqualTo(String value) {
            addCriterion("DATABASESOURCENAME =", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameNotEqualTo(String value) {
            addCriterion("DATABASESOURCENAME <>", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameGreaterThan(String value) {
            addCriterion("DATABASESOURCENAME >", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASESOURCENAME >=", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameLessThan(String value) {
            addCriterion("DATABASESOURCENAME <", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameLessThanOrEqualTo(String value) {
            addCriterion("DATABASESOURCENAME <=", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameLike(String value) {
            addCriterion("DATABASESOURCENAME like", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameNotLike(String value) {
            addCriterion("DATABASESOURCENAME not like", value, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameIn(List<String> values) {
            addCriterion("DATABASESOURCENAME in", values, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameNotIn(List<String> values) {
            addCriterion("DATABASESOURCENAME not in", values, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameBetween(String value1, String value2) {
            addCriterion("DATABASESOURCENAME between", value1, value2, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatabasesourcenameNotBetween(String value1, String value2) {
            addCriterion("DATABASESOURCENAME not between", value1, value2, "databasesourcename");
            return (Criteria) this;
        }

        public Criteria andDatasourceidIsNull() {
            addCriterion("DATASOURCEID is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceidIsNotNull() {
            addCriterion("DATASOURCEID is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceidEqualTo(String value) {
            addCriterion("DATASOURCEID =", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotEqualTo(String value) {
            addCriterion("DATASOURCEID <>", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidGreaterThan(String value) {
            addCriterion("DATASOURCEID >", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidGreaterThanOrEqualTo(String value) {
            addCriterion("DATASOURCEID >=", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidLessThan(String value) {
            addCriterion("DATASOURCEID <", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidLessThanOrEqualTo(String value) {
            addCriterion("DATASOURCEID <=", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidLike(String value) {
            addCriterion("DATASOURCEID like", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotLike(String value) {
            addCriterion("DATASOURCEID not like", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidIn(List<String> values) {
            addCriterion("DATASOURCEID in", values, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotIn(List<String> values) {
            addCriterion("DATASOURCEID not in", values, "datasourceid");
            return (Criteria) this;
        }    

        public Criteria andDatasourceidBetween(String value1, String value2) {
            addCriterion("DATASOURCEID between", value1, value2, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotBetween(String value1, String value2) {
            addCriterion("DATASOURCEID not between", value1, value2, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDataBaseTypeEqualTo(String value) {
            addCriterion("DATABASETYPE =", value, "dataBaseType");
            return (Criteria) this;
        }
        
        public Criteria andOldKindidEqualTo(String value) {
            addCriterion("OLDKINDID =", value, "oldKindid");
            return (Criteria) this;
        }
        
        public Criteria andOldKindidLikeLike(String value) {
            addCriterion("OLDKINDID like", value, "oldKindidLike");
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