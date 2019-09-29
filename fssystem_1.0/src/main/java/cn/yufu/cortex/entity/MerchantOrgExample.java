package cn.yufu.cortex.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MerchantOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantOrgExample() {
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

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgRatioIsNull() {
            addCriterion("ORG_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andOrgRatioIsNotNull() {
            addCriterion("ORG_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgRatioEqualTo(BigDecimal value) {
            addCriterion("ORG_RATIO =", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioNotEqualTo(BigDecimal value) {
            addCriterion("ORG_RATIO <>", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioGreaterThan(BigDecimal value) {
            addCriterion("ORG_RATIO >", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_RATIO >=", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioLessThan(BigDecimal value) {
            addCriterion("ORG_RATIO <", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_RATIO <=", value, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioIn(List<BigDecimal> values) {
            addCriterion("ORG_RATIO in", values, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioNotIn(List<BigDecimal> values) {
            addCriterion("ORG_RATIO not in", values, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_RATIO between", value1, value2, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_RATIO not between", value1, value2, "orgRatio");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtIsNull() {
            addCriterion("ORG_SINGLE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtIsNotNull() {
            addCriterion("ORG_SINGLE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtEqualTo(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT =", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtNotEqualTo(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT <>", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtGreaterThan(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT >", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT >=", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtLessThan(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT <", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_SINGLE_AMT <=", value, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtIn(List<BigDecimal> values) {
            addCriterion("ORG_SINGLE_AMT in", values, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtNotIn(List<BigDecimal> values) {
            addCriterion("ORG_SINGLE_AMT not in", values, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_SINGLE_AMT between", value1, value2, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgSingleAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_SINGLE_AMT not between", value1, value2, "orgSingleAmt");
            return (Criteria) this;
        }

        public Criteria andOrgStatIsNull() {
            addCriterion("ORG_STAT is null");
            return (Criteria) this;
        }

        public Criteria andOrgStatIsNotNull() {
            addCriterion("ORG_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgStatEqualTo(String value) {
            addCriterion("ORG_STAT =", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatNotEqualTo(String value) {
            addCriterion("ORG_STAT <>", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatGreaterThan(String value) {
            addCriterion("ORG_STAT >", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_STAT >=", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatLessThan(String value) {
            addCriterion("ORG_STAT <", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatLessThanOrEqualTo(String value) {
            addCriterion("ORG_STAT <=", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatLike(String value) {
            addCriterion("ORG_STAT like", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatNotLike(String value) {
            addCriterion("ORG_STAT not like", value, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatIn(List<String> values) {
            addCriterion("ORG_STAT in", values, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatNotIn(List<String> values) {
            addCriterion("ORG_STAT not in", values, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatBetween(String value1, String value2) {
            addCriterion("ORG_STAT between", value1, value2, "orgStat");
            return (Criteria) this;
        }

        public Criteria andOrgStatNotBetween(String value1, String value2) {
            addCriterion("ORG_STAT not between", value1, value2, "orgStat");
            return (Criteria) this;
        }

        public Criteria andReservedFieldIsNull() {
            addCriterion("RESERVED_FIELD is null");
            return (Criteria) this;
        }

        public Criteria andReservedFieldIsNotNull() {
            addCriterion("RESERVED_FIELD is not null");
            return (Criteria) this;
        }

        public Criteria andReservedFieldEqualTo(String value) {
            addCriterion("RESERVED_FIELD =", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldNotEqualTo(String value) {
            addCriterion("RESERVED_FIELD <>", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldGreaterThan(String value) {
            addCriterion("RESERVED_FIELD >", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldGreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD >=", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldLessThan(String value) {
            addCriterion("RESERVED_FIELD <", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldLessThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD <=", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldLike(String value) {
            addCriterion("RESERVED_FIELD like", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldNotLike(String value) {
            addCriterion("RESERVED_FIELD not like", value, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldIn(List<String> values) {
            addCriterion("RESERVED_FIELD in", values, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldNotIn(List<String> values) {
            addCriterion("RESERVED_FIELD not in", values, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD between", value1, value2, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedFieldNotBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD not between", value1, value2, "reservedField");
            return (Criteria) this;
        }

        public Criteria andReservedField1IsNull() {
            addCriterion("RESERVED_FIELD1 is null");
            return (Criteria) this;
        }

        public Criteria andReservedField1IsNotNull() {
            addCriterion("RESERVED_FIELD1 is not null");
            return (Criteria) this;
        }

        public Criteria andReservedField1EqualTo(String value) {
            addCriterion("RESERVED_FIELD1 =", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotEqualTo(String value) {
            addCriterion("RESERVED_FIELD1 <>", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1GreaterThan(String value) {
            addCriterion("RESERVED_FIELD1 >", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD1 >=", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1LessThan(String value) {
            addCriterion("RESERVED_FIELD1 <", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1LessThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD1 <=", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1Like(String value) {
            addCriterion("RESERVED_FIELD1 like", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotLike(String value) {
            addCriterion("RESERVED_FIELD1 not like", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1In(List<String> values) {
            addCriterion("RESERVED_FIELD1 in", values, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotIn(List<String> values) {
            addCriterion("RESERVED_FIELD1 not in", values, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1Between(String value1, String value2) {
            addCriterion("RESERVED_FIELD1 between", value1, value2, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD1 not between", value1, value2, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField2IsNull() {
            addCriterion("RESERVED_FIELD2 is null");
            return (Criteria) this;
        }

        public Criteria andReservedField2IsNotNull() {
            addCriterion("RESERVED_FIELD2 is not null");
            return (Criteria) this;
        }

        public Criteria andReservedField2EqualTo(String value) {
            addCriterion("RESERVED_FIELD2 =", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotEqualTo(String value) {
            addCriterion("RESERVED_FIELD2 <>", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2GreaterThan(String value) {
            addCriterion("RESERVED_FIELD2 >", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD2 >=", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2LessThan(String value) {
            addCriterion("RESERVED_FIELD2 <", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2LessThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD2 <=", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2Like(String value) {
            addCriterion("RESERVED_FIELD2 like", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotLike(String value) {
            addCriterion("RESERVED_FIELD2 not like", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2In(List<String> values) {
            addCriterion("RESERVED_FIELD2 in", values, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotIn(List<String> values) {
            addCriterion("RESERVED_FIELD2 not in", values, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2Between(String value1, String value2) {
            addCriterion("RESERVED_FIELD2 between", value1, value2, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD2 not between", value1, value2, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
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