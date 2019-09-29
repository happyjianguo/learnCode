package cn.yufu.cortexBak.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MrchAccBakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MrchAccBakExample() {
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

        public Criteria andDateLastStmtIsNull() {
            addCriterion("DATE_LAST_STMT is null");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtIsNotNull() {
            addCriterion("DATE_LAST_STMT is not null");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT =", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtNotEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT <>", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtGreaterThan(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT >", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT >=", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtLessThan(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT <", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_LAST_STMT <=", value, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtIn(List<Date> values) {
            addCriterionForJDBCDate("DATE_LAST_STMT in", values, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtNotIn(List<Date> values) {
            addCriterionForJDBCDate("DATE_LAST_STMT not in", values, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE_LAST_STMT between", value1, value2, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andDateLastStmtNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE_LAST_STMT not between", value1, value2, "dateLastStmt");
            return (Criteria) this;
        }

        public Criteria andClosingBalIsNull() {
            addCriterion("CLOSING_BAL is null");
            return (Criteria) this;
        }

        public Criteria andClosingBalIsNotNull() {
            addCriterion("CLOSING_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andClosingBalEqualTo(Double value) {
            addCriterion("CLOSING_BAL =", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalNotEqualTo(Double value) {
            addCriterion("CLOSING_BAL <>", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalGreaterThan(Double value) {
            addCriterion("CLOSING_BAL >", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalGreaterThanOrEqualTo(Double value) {
            addCriterion("CLOSING_BAL >=", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalLessThan(Double value) {
            addCriterion("CLOSING_BAL <", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalLessThanOrEqualTo(Double value) {
            addCriterion("CLOSING_BAL <=", value, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalIn(List<Double> values) {
            addCriterion("CLOSING_BAL in", values, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalNotIn(List<Double> values) {
            addCriterion("CLOSING_BAL not in", values, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalBetween(Double value1, Double value2) {
            addCriterion("CLOSING_BAL between", value1, value2, "closingBal");
            return (Criteria) this;
        }

        public Criteria andClosingBalNotBetween(Double value1, Double value2) {
            addCriterion("CLOSING_BAL not between", value1, value2, "closingBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalIsNull() {
            addCriterion("CURRENT_BAL is null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalIsNotNull() {
            addCriterion("CURRENT_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalEqualTo(Double value) {
            addCriterion("CURRENT_BAL =", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalNotEqualTo(Double value) {
            addCriterion("CURRENT_BAL <>", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalGreaterThan(Double value) {
            addCriterion("CURRENT_BAL >", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalGreaterThanOrEqualTo(Double value) {
            addCriterion("CURRENT_BAL >=", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalLessThan(Double value) {
            addCriterion("CURRENT_BAL <", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalLessThanOrEqualTo(Double value) {
            addCriterion("CURRENT_BAL <=", value, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalIn(List<Double> values) {
            addCriterion("CURRENT_BAL in", values, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalNotIn(List<Double> values) {
            addCriterion("CURRENT_BAL not in", values, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalBetween(Double value1, Double value2) {
            addCriterion("CURRENT_BAL between", value1, value2, "currentBal");
            return (Criteria) this;
        }

        public Criteria andCurrentBalNotBetween(Double value1, Double value2) {
            addCriterion("CURRENT_BAL not between", value1, value2, "currentBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalIsNull() {
            addCriterion("LAST_POST_BAL is null");
            return (Criteria) this;
        }

        public Criteria andLastPostBalIsNotNull() {
            addCriterion("LAST_POST_BAL is not null");
            return (Criteria) this;
        }

        public Criteria andLastPostBalEqualTo(Double value) {
            addCriterion("LAST_POST_BAL =", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalNotEqualTo(Double value) {
            addCriterion("LAST_POST_BAL <>", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalGreaterThan(Double value) {
            addCriterion("LAST_POST_BAL >", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalGreaterThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_BAL >=", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalLessThan(Double value) {
            addCriterion("LAST_POST_BAL <", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalLessThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_BAL <=", value, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalIn(List<Double> values) {
            addCriterion("LAST_POST_BAL in", values, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalNotIn(List<Double> values) {
            addCriterion("LAST_POST_BAL not in", values, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_BAL between", value1, value2, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostBalNotBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_BAL not between", value1, value2, "lastPostBal");
            return (Criteria) this;
        }

        public Criteria andLastPostComIsNull() {
            addCriterion("LAST_POST_COM is null");
            return (Criteria) this;
        }

        public Criteria andLastPostComIsNotNull() {
            addCriterion("LAST_POST_COM is not null");
            return (Criteria) this;
        }

        public Criteria andLastPostComEqualTo(Double value) {
            addCriterion("LAST_POST_COM =", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComNotEqualTo(Double value) {
            addCriterion("LAST_POST_COM <>", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComGreaterThan(Double value) {
            addCriterion("LAST_POST_COM >", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComGreaterThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_COM >=", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComLessThan(Double value) {
            addCriterion("LAST_POST_COM <", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComLessThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_COM <=", value, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComIn(List<Double> values) {
            addCriterion("LAST_POST_COM in", values, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComNotIn(List<Double> values) {
            addCriterion("LAST_POST_COM not in", values, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_COM between", value1, value2, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostComNotBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_COM not between", value1, value2, "lastPostCom");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxIsNull() {
            addCriterion("LAST_POST_TAX is null");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxIsNotNull() {
            addCriterion("LAST_POST_TAX is not null");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxEqualTo(Double value) {
            addCriterion("LAST_POST_TAX =", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxNotEqualTo(Double value) {
            addCriterion("LAST_POST_TAX <>", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxGreaterThan(Double value) {
            addCriterion("LAST_POST_TAX >", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxGreaterThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_TAX >=", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxLessThan(Double value) {
            addCriterion("LAST_POST_TAX <", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxLessThanOrEqualTo(Double value) {
            addCriterion("LAST_POST_TAX <=", value, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxIn(List<Double> values) {
            addCriterion("LAST_POST_TAX in", values, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxNotIn(List<Double> values) {
            addCriterion("LAST_POST_TAX not in", values, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_TAX between", value1, value2, "lastPostTax");
            return (Criteria) this;
        }

        public Criteria andLastPostTaxNotBetween(Double value1, Double value2) {
            addCriterion("LAST_POST_TAX not between", value1, value2, "lastPostTax");
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