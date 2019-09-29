package cn.yufu.cortex.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CortexViewAccdetStatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CortexViewAccdetStatExample() {
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

        public Criteria andCardBinIsNull() {
            addCriterion("CARD_BIN is null");
            return (Criteria) this;
        }

        public Criteria andCardBinIsNotNull() {
            addCriterion("CARD_BIN is not null");
            return (Criteria) this;
        }

        public Criteria andCardBinEqualTo(String value) {
            addCriterion("CARD_BIN =", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotEqualTo(String value) {
            addCriterion("CARD_BIN <>", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinGreaterThan(String value) {
            addCriterion("CARD_BIN >", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_BIN >=", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLessThan(String value) {
            addCriterion("CARD_BIN <", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLessThanOrEqualTo(String value) {
            addCriterion("CARD_BIN <=", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinLike(String value) {
            addCriterion("CARD_BIN like", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotLike(String value) {
            addCriterion("CARD_BIN not like", value, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinIn(List<String> values) {
            addCriterion("CARD_BIN in", values, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotIn(List<String> values) {
            addCriterion("CARD_BIN not in", values, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinBetween(String value1, String value2) {
            addCriterion("CARD_BIN between", value1, value2, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCardBinNotBetween(String value1, String value2) {
            addCriterion("CARD_BIN not between", value1, value2, "cardBin");
            return (Criteria) this;
        }

        public Criteria andCrdproductIsNull() {
            addCriterion("CRDPRODUCT is null");
            return (Criteria) this;
        }

        public Criteria andCrdproductIsNotNull() {
            addCriterion("CRDPRODUCT is not null");
            return (Criteria) this;
        }

        public Criteria andCrdproductEqualTo(String value) {
            addCriterion("CRDPRODUCT =", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductNotEqualTo(String value) {
            addCriterion("CRDPRODUCT <>", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductGreaterThan(String value) {
            addCriterion("CRDPRODUCT >", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductGreaterThanOrEqualTo(String value) {
            addCriterion("CRDPRODUCT >=", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductLessThan(String value) {
            addCriterion("CRDPRODUCT <", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductLessThanOrEqualTo(String value) {
            addCriterion("CRDPRODUCT <=", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductLike(String value) {
            addCriterion("CRDPRODUCT like", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductNotLike(String value) {
            addCriterion("CRDPRODUCT not like", value, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductIn(List<String> values) {
            addCriterion("CRDPRODUCT in", values, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductNotIn(List<String> values) {
            addCriterion("CRDPRODUCT not in", values, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductBetween(String value1, String value2) {
            addCriterion("CRDPRODUCT between", value1, value2, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCrdproductNotBetween(String value1, String value2) {
            addCriterion("CRDPRODUCT not between", value1, value2, "crdproduct");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameIsNull() {
            addCriterion("CARD_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameIsNotNull() {
            addCriterion("CARD_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameEqualTo(String value) {
            addCriterion("CARD_TYPE_NAME =", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameNotEqualTo(String value) {
            addCriterion("CARD_TYPE_NAME <>", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameGreaterThan(String value) {
            addCriterion("CARD_TYPE_NAME >", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE_NAME >=", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameLessThan(String value) {
            addCriterion("CARD_TYPE_NAME <", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE_NAME <=", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameLike(String value) {
            addCriterion("CARD_TYPE_NAME like", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameNotLike(String value) {
            addCriterion("CARD_TYPE_NAME not like", value, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameIn(List<String> values) {
            addCriterion("CARD_TYPE_NAME in", values, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameNotIn(List<String> values) {
            addCriterion("CARD_TYPE_NAME not in", values, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameBetween(String value1, String value2) {
            addCriterion("CARD_TYPE_NAME between", value1, value2, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardTypeNameNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE_NAME not between", value1, value2, "cardTypeName");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNull() {
            addCriterion("CARD_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNotNull() {
            addCriterion("CARD_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusEqualTo(String value) {
            addCriterion("CARD_STATUS =", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotEqualTo(String value) {
            addCriterion("CARD_STATUS <>", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThan(String value) {
            addCriterion("CARD_STATUS >", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS >=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThan(String value) {
            addCriterion("CARD_STATUS <", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS <=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLike(String value) {
            addCriterion("CARD_STATUS like", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotLike(String value) {
            addCriterion("CARD_STATUS not like", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIn(List<String> values) {
            addCriterion("CARD_STATUS in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotIn(List<String> values) {
            addCriterion("CARD_STATUS not in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusBetween(String value1, String value2) {
            addCriterion("CARD_STATUS between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotBetween(String value1, String value2) {
            addCriterion("CARD_STATUS not between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameIsNull() {
            addCriterion("CARD_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameIsNotNull() {
            addCriterion("CARD_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameEqualTo(String value) {
            addCriterion("CARD_STATUS_NAME =", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameNotEqualTo(String value) {
            addCriterion("CARD_STATUS_NAME <>", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameGreaterThan(String value) {
            addCriterion("CARD_STATUS_NAME >", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS_NAME >=", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameLessThan(String value) {
            addCriterion("CARD_STATUS_NAME <", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS_NAME <=", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameLike(String value) {
            addCriterion("CARD_STATUS_NAME like", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameNotLike(String value) {
            addCriterion("CARD_STATUS_NAME not like", value, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameIn(List<String> values) {
            addCriterion("CARD_STATUS_NAME in", values, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameNotIn(List<String> values) {
            addCriterion("CARD_STATUS_NAME not in", values, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameBetween(String value1, String value2) {
            addCriterion("CARD_STATUS_NAME between", value1, value2, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardStatusNameNotBetween(String value1, String value2) {
            addCriterion("CARD_STATUS_NAME not between", value1, value2, "cardStatusName");
            return (Criteria) this;
        }

        public Criteria andCardCntIsNull() {
            addCriterion("CARD_CNT is null");
            return (Criteria) this;
        }

        public Criteria andCardCntIsNotNull() {
            addCriterion("CARD_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andCardCntEqualTo(BigDecimal value) {
            addCriterion("CARD_CNT =", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntNotEqualTo(BigDecimal value) {
            addCriterion("CARD_CNT <>", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntGreaterThan(BigDecimal value) {
            addCriterion("CARD_CNT >", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_CNT >=", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntLessThan(BigDecimal value) {
            addCriterion("CARD_CNT <", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_CNT <=", value, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntIn(List<BigDecimal> values) {
            addCriterion("CARD_CNT in", values, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntNotIn(List<BigDecimal> values) {
            addCriterion("CARD_CNT not in", values, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_CNT between", value1, value2, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardCntNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_CNT not between", value1, value2, "cardCnt");
            return (Criteria) this;
        }

        public Criteria andCardBalIsNull() {
            addCriterion("CARD_BAL is null");
            return (Criteria) this;
        }

        public Criteria andCardBalIsNotNull() {
            addCriterion("CARD_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andCardBalEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL =", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL <>", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalGreaterThan(BigDecimal value) {
            addCriterion("CARD_BAL >", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL >=", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalLessThan(BigDecimal value) {
            addCriterion("CARD_BAL <", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CARD_BAL <=", value, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalIn(List<BigDecimal> values) {
            addCriterion("CARD_BAL in", values, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotIn(List<BigDecimal> values) {
            addCriterion("CARD_BAL not in", values, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_BAL between", value1, value2, "cardBal");
            return (Criteria) this;
        }

        public Criteria andCardBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARD_BAL not between", value1, value2, "cardBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalIsNull() {
            addCriterion("TRUE_BAL is null");
            return (Criteria) this;
        }

        public Criteria andTrueBalIsNotNull() {
            addCriterion("TRUE_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andTrueBalEqualTo(BigDecimal value) {
            addCriterion("TRUE_BAL =", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalNotEqualTo(BigDecimal value) {
            addCriterion("TRUE_BAL <>", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalGreaterThan(BigDecimal value) {
            addCriterion("TRUE_BAL >", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRUE_BAL >=", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalLessThan(BigDecimal value) {
            addCriterion("TRUE_BAL <", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRUE_BAL <=", value, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalIn(List<BigDecimal> values) {
            addCriterion("TRUE_BAL in", values, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalNotIn(List<BigDecimal> values) {
            addCriterion("TRUE_BAL not in", values, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRUE_BAL between", value1, value2, "trueBal");
            return (Criteria) this;
        }

        public Criteria andTrueBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRUE_BAL not between", value1, value2, "trueBal");
            return (Criteria) this;
        }

        public Criteria andInstBalIsNull() {
            addCriterion("INST_BAL is null");
            return (Criteria) this;
        }

        public Criteria andInstBalIsNotNull() {
            addCriterion("INST_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andInstBalEqualTo(BigDecimal value) {
            addCriterion("INST_BAL =", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalNotEqualTo(BigDecimal value) {
            addCriterion("INST_BAL <>", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalGreaterThan(BigDecimal value) {
            addCriterion("INST_BAL >", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INST_BAL >=", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalLessThan(BigDecimal value) {
            addCriterion("INST_BAL <", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INST_BAL <=", value, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalIn(List<BigDecimal> values) {
            addCriterion("INST_BAL in", values, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalNotIn(List<BigDecimal> values) {
            addCriterion("INST_BAL not in", values, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INST_BAL between", value1, value2, "instBal");
            return (Criteria) this;
        }

        public Criteria andInstBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INST_BAL not between", value1, value2, "instBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalIsNull() {
            addCriterion("TOTAL_BAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalBalIsNotNull() {
            addCriterion("TOTAL_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBalEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL =", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL <>", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_BAL >", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL >=", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalLessThan(BigDecimal value) {
            addCriterion("TOTAL_BAL <", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_BAL <=", value, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalIn(List<BigDecimal> values) {
            addCriterion("TOTAL_BAL in", values, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_BAL not in", values, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_BAL between", value1, value2, "totalBal");
            return (Criteria) this;
        }

        public Criteria andTotalBalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_BAL not between", value1, value2, "totalBal");
            return (Criteria) this;
        }
        
        public Criteria andExelusiveCardFlagEqualTo(String value) {
            addCriterion("ISEXCLUSIVE =", value, "exelusiveCardFlag");
            return (Criteria) this;
        }
        public Criteria andStlFlagequalto(String value) {
            addCriterion("STLFLAG =", value, "stlFlag");
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