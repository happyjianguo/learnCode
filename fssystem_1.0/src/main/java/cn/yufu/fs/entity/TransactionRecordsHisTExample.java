package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TransactionRecordsHisTExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionRecordsHisTExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIsNull() {
            addCriterion("MERCHANTNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIsNotNull() {
            addCriterion("MERCHANTNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberEqualTo(String value) {
            addCriterion("MERCHANTNUMBER =", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <>", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThan(String value) {
            addCriterion("MERCHANTNUMBER >", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER >=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThan(String value) {
            addCriterion("MERCHANTNUMBER <", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLessThanOrEqualTo(String value) {
            addCriterion("MERCHANTNUMBER <=", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberLike(String value) {
            addCriterion("MERCHANTNUMBER like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotLike(String value) {
            addCriterion("MERCHANTNUMBER not like", value, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberIn(List<String> values) {
            addCriterion("MERCHANTNUMBER in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotIn(List<String> values) {
            addCriterion("MERCHANTNUMBER not in", values, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andMerchantnumberNotBetween(String value1, String value2) {
            addCriterion("MERCHANTNUMBER not between", value1, value2, "merchantnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberIsNull() {
            addCriterion("TERMINALNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberIsNotNull() {
            addCriterion("TERMINALNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberEqualTo(String value) {
            addCriterion("TERMINALNUMBER =", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberNotEqualTo(String value) {
            addCriterion("TERMINALNUMBER <>", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberGreaterThan(String value) {
            addCriterion("TERMINALNUMBER >", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINALNUMBER >=", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberLessThan(String value) {
            addCriterion("TERMINALNUMBER <", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberLessThanOrEqualTo(String value) {
            addCriterion("TERMINALNUMBER <=", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberLike(String value) {
            addCriterion("TERMINALNUMBER like", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberNotLike(String value) {
            addCriterion("TERMINALNUMBER not like", value, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberIn(List<String> values) {
            addCriterion("TERMINALNUMBER in", values, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberNotIn(List<String> values) {
            addCriterion("TERMINALNUMBER not in", values, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberBetween(String value1, String value2) {
            addCriterion("TERMINALNUMBER between", value1, value2, "terminalnumber");
            return (Criteria) this;
        }

        public Criteria andTerminalnumberNotBetween(String value1, String value2) {
            addCriterion("TERMINALNUMBER not between", value1, value2, "terminalnumber");
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

        public Criteria andTransactionmoneyIsNull() {
            addCriterion("TRANSACTIONMONEY is null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIsNotNull() {
            addCriterion("TRANSACTIONMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY =", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <>", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY >=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThan(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTIONMONEY <=", value, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTIONMONEY not in", values, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andTransactionmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTIONMONEY not between", value1, value2, "transactionmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyIsNull() {
            addCriterion("CARDACCOUNTMONEY is null");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyIsNotNull() {
            addCriterion("CARDACCOUNTMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyEqualTo(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY =", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY <>", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyGreaterThan(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY >", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY >=", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyLessThan(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY <", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CARDACCOUNTMONEY <=", value, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyIn(List<BigDecimal> values) {
            addCriterion("CARDACCOUNTMONEY in", values, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("CARDACCOUNTMONEY not in", values, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARDACCOUNTMONEY between", value1, value2, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andCardaccountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CARDACCOUNTMONEY not between", value1, value2, "cardaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyIsNull() {
            addCriterion("TRUEACCOUNTMONEY is null");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyIsNotNull() {
            addCriterion("TRUEACCOUNTMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyEqualTo(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY =", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY <>", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyGreaterThan(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY >", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY >=", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyLessThan(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY <", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRUEACCOUNTMONEY <=", value, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyIn(List<BigDecimal> values) {
            addCriterion("TRUEACCOUNTMONEY in", values, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("TRUEACCOUNTMONEY not in", values, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRUEACCOUNTMONEY between", value1, value2, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andTrueaccountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRUEACCOUNTMONEY not between", value1, value2, "trueaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyIsNull() {
            addCriterion("INTEGRATIONACCOUNTMONEY is null");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyIsNotNull() {
            addCriterion("INTEGRATIONACCOUNTMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyEqualTo(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY =", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY <>", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyGreaterThan(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY >", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY >=", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyLessThan(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY <", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INTEGRATIONACCOUNTMONEY <=", value, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyIn(List<BigDecimal> values) {
            addCriterion("INTEGRATIONACCOUNTMONEY in", values, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("INTEGRATIONACCOUNTMONEY not in", values, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INTEGRATIONACCOUNTMONEY between", value1, value2, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andIntegrationaccountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INTEGRATIONACCOUNTMONEY not between", value1, value2, "integrationaccountmoney");
            return (Criteria) this;
        }

        public Criteria andSerialnumberIsNull() {
            addCriterion("SERIALNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSerialnumberIsNotNull() {
            addCriterion("SERIALNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSerialnumberEqualTo(BigDecimal value) {
            addCriterion("SERIALNUMBER =", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberNotEqualTo(BigDecimal value) {
            addCriterion("SERIALNUMBER <>", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberGreaterThan(BigDecimal value) {
            addCriterion("SERIALNUMBER >", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SERIALNUMBER >=", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberLessThan(BigDecimal value) {
            addCriterion("SERIALNUMBER <", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SERIALNUMBER <=", value, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberIn(List<BigDecimal> values) {
            addCriterion("SERIALNUMBER in", values, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberNotIn(List<BigDecimal> values) {
            addCriterion("SERIALNUMBER not in", values, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SERIALNUMBER between", value1, value2, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andSerialnumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SERIALNUMBER not between", value1, value2, "serialnumber");
            return (Criteria) this;
        }

        public Criteria andLotnoIsNull() {
            addCriterion("LOTNO is null");
            return (Criteria) this;
        }

        public Criteria andLotnoIsNotNull() {
            addCriterion("LOTNO is not null");
            return (Criteria) this;
        }

        public Criteria andLotnoEqualTo(BigDecimal value) {
            addCriterion("LOTNO =", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoNotEqualTo(BigDecimal value) {
            addCriterion("LOTNO <>", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoGreaterThan(BigDecimal value) {
            addCriterion("LOTNO >", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOTNO >=", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoLessThan(BigDecimal value) {
            addCriterion("LOTNO <", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOTNO <=", value, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoIn(List<BigDecimal> values) {
            addCriterion("LOTNO in", values, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoNotIn(List<BigDecimal> values) {
            addCriterion("LOTNO not in", values, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOTNO between", value1, value2, "lotno");
            return (Criteria) this;
        }

        public Criteria andLotnoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOTNO not between", value1, value2, "lotno");
            return (Criteria) this;
        }

        public Criteria andReferencenumberIsNull() {
            addCriterion("REFERENCENUMBER is null");
            return (Criteria) this;
        }

        public Criteria andReferencenumberIsNotNull() {
            addCriterion("REFERENCENUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andReferencenumberEqualTo(String value) {
            addCriterion("REFERENCENUMBER =", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberNotEqualTo(String value) {
            addCriterion("REFERENCENUMBER <>", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberGreaterThan(String value) {
            addCriterion("REFERENCENUMBER >", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberGreaterThanOrEqualTo(String value) {
            addCriterion("REFERENCENUMBER >=", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberLessThan(String value) {
            addCriterion("REFERENCENUMBER <", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberLessThanOrEqualTo(String value) {
            addCriterion("REFERENCENUMBER <=", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberLike(String value) {
            addCriterion("REFERENCENUMBER like", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberNotLike(String value) {
            addCriterion("REFERENCENUMBER not like", value, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberIn(List<String> values) {
            addCriterion("REFERENCENUMBER in", values, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberNotIn(List<String> values) {
            addCriterion("REFERENCENUMBER not in", values, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberBetween(String value1, String value2) {
            addCriterion("REFERENCENUMBER between", value1, value2, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReferencenumberNotBetween(String value1, String value2) {
            addCriterion("REFERENCENUMBER not between", value1, value2, "referencenumber");
            return (Criteria) this;
        }

        public Criteria andReturnpointsIsNull() {
            addCriterion("RETURNPOINTS is null");
            return (Criteria) this;
        }

        public Criteria andReturnpointsIsNotNull() {
            addCriterion("RETURNPOINTS is not null");
            return (Criteria) this;
        }

        public Criteria andReturnpointsEqualTo(BigDecimal value) {
            addCriterion("RETURNPOINTS =", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsNotEqualTo(BigDecimal value) {
            addCriterion("RETURNPOINTS <>", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsGreaterThan(BigDecimal value) {
            addCriterion("RETURNPOINTS >", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RETURNPOINTS >=", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsLessThan(BigDecimal value) {
            addCriterion("RETURNPOINTS <", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RETURNPOINTS <=", value, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsIn(List<BigDecimal> values) {
            addCriterion("RETURNPOINTS in", values, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsNotIn(List<BigDecimal> values) {
            addCriterion("RETURNPOINTS not in", values, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RETURNPOINTS between", value1, value2, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andReturnpointsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RETURNPOINTS not between", value1, value2, "returnpoints");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIsNull() {
            addCriterion("TRANSACTIONTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIsNotNull() {
            addCriterion("TRANSACTIONTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE =", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE <>", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeGreaterThan(String value) {
            addCriterion("TRANSACTIONTYPE >", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE >=", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLessThan(String value) {
            addCriterion("TRANSACTIONTYPE <", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTYPE <=", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeLike(String value) {
            addCriterion("TRANSACTIONTYPE like", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotLike(String value) {
            addCriterion("TRANSACTIONTYPE not like", value, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeIn(List<String> values) {
            addCriterion("TRANSACTIONTYPE in", values, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotIn(List<String> values) {
            addCriterion("TRANSACTIONTYPE not in", values, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTYPE between", value1, value2, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactiontypeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTYPE not between", value1, value2, "transactiontype");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusIsNull() {
            addCriterion("TRANSACTIONSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusIsNotNull() {
            addCriterion("TRANSACTIONSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusEqualTo(Short value) {
            addCriterion("TRANSACTIONSTATUS =", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusNotEqualTo(Short value) {
            addCriterion("TRANSACTIONSTATUS <>", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusGreaterThan(Short value) {
            addCriterion("TRANSACTIONSTATUS >", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("TRANSACTIONSTATUS >=", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusLessThan(Short value) {
            addCriterion("TRANSACTIONSTATUS <", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusLessThanOrEqualTo(Short value) {
            addCriterion("TRANSACTIONSTATUS <=", value, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusIn(List<Short> values) {
            addCriterion("TRANSACTIONSTATUS in", values, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusNotIn(List<Short> values) {
            addCriterion("TRANSACTIONSTATUS not in", values, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusBetween(Short value1, Short value2) {
            addCriterion("TRANSACTIONSTATUS between", value1, value2, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andTransactionstatusNotBetween(Short value1, Short value2) {
            addCriterion("TRANSACTIONSTATUS not between", value1, value2, "transactionstatus");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNull() {
            addCriterion("CARDTYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNotNull() {
            addCriterion("CARDTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeEqualTo(Short value) {
            addCriterion("CARDTYPE =", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotEqualTo(Short value) {
            addCriterion("CARDTYPE <>", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThan(Short value) {
            addCriterion("CARDTYPE >", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("CARDTYPE >=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThan(Short value) {
            addCriterion("CARDTYPE <", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThanOrEqualTo(Short value) {
            addCriterion("CARDTYPE <=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeIn(List<Short> values) {
            addCriterion("CARDTYPE in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotIn(List<Short> values) {
            addCriterion("CARDTYPE not in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeBetween(Short value1, Short value2) {
            addCriterion("CARDTYPE between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotBetween(Short value1, Short value2) {
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

        public Criteria andMerchanttypeIsNull() {
            addCriterion("MERCHANTTYPE is null");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeIsNotNull() {
            addCriterion("MERCHANTTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeEqualTo(String value) {
            addCriterion("MERCHANTTYPE =", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeNotEqualTo(String value) {
            addCriterion("MERCHANTTYPE <>", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeGreaterThan(String value) {
            addCriterion("MERCHANTTYPE >", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANTTYPE >=", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeLessThan(String value) {
            addCriterion("MERCHANTTYPE <", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeLessThanOrEqualTo(String value) {
            addCriterion("MERCHANTTYPE <=", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeLike(String value) {
            addCriterion("MERCHANTTYPE like", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeNotLike(String value) {
            addCriterion("MERCHANTTYPE not like", value, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeIn(List<String> values) {
            addCriterion("MERCHANTTYPE in", values, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeNotIn(List<String> values) {
            addCriterion("MERCHANTTYPE not in", values, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeBetween(String value1, String value2) {
            addCriterion("MERCHANTTYPE between", value1, value2, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchanttypeNotBetween(String value1, String value2) {
            addCriterion("MERCHANTTYPE not between", value1, value2, "merchanttype");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsIsNull() {
            addCriterion("MERCHANTSECTORS is null");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsIsNotNull() {
            addCriterion("MERCHANTSECTORS is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsEqualTo(String value) {
            addCriterion("MERCHANTSECTORS =", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsNotEqualTo(String value) {
            addCriterion("MERCHANTSECTORS <>", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsGreaterThan(String value) {
            addCriterion("MERCHANTSECTORS >", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANTSECTORS >=", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsLessThan(String value) {
            addCriterion("MERCHANTSECTORS <", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsLessThanOrEqualTo(String value) {
            addCriterion("MERCHANTSECTORS <=", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsLike(String value) {
            addCriterion("MERCHANTSECTORS like", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsNotLike(String value) {
            addCriterion("MERCHANTSECTORS not like", value, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsIn(List<String> values) {
            addCriterion("MERCHANTSECTORS in", values, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsNotIn(List<String> values) {
            addCriterion("MERCHANTSECTORS not in", values, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsBetween(String value1, String value2) {
            addCriterion("MERCHANTSECTORS between", value1, value2, "merchantsectors");
            return (Criteria) this;
        }

        public Criteria andMerchantsectorsNotBetween(String value1, String value2) {
            addCriterion("MERCHANTSECTORS not between", value1, value2, "merchantsectors");
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

        public Criteria andAreaIsNull() {
            addCriterion("AREA is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("AREA is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("AREA =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("AREA <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("AREA >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("AREA >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("AREA <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("AREA <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("AREA like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("AREA not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("AREA in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("AREA not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("AREA between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("AREA not between", value1, value2, "area");
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

        public Criteria andDatasourceidEqualTo(BigDecimal value) {
            addCriterion("DATASOURCEID =", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotEqualTo(BigDecimal value) {
            addCriterion("DATASOURCEID <>", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidGreaterThan(BigDecimal value) {
            addCriterion("DATASOURCEID >", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DATASOURCEID >=", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidLessThan(BigDecimal value) {
            addCriterion("DATASOURCEID <", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DATASOURCEID <=", value, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidIn(List<BigDecimal> values) {
            addCriterion("DATASOURCEID in", values, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotIn(List<BigDecimal> values) {
            addCriterion("DATASOURCEID not in", values, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DATASOURCEID between", value1, value2, "datasourceid");
            return (Criteria) this;
        }

        public Criteria andDatasourceidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DATASOURCEID not between", value1, value2, "datasourceid");
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

        public Criteria andSettlementaccountIsNull() {
            addCriterion("SETTLEMENTACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountIsNotNull() {
            addCriterion("SETTLEMENTACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountEqualTo(String value) {
            addCriterion("SETTLEMENTACCOUNT =", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountNotEqualTo(String value) {
            addCriterion("SETTLEMENTACCOUNT <>", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountGreaterThan(String value) {
            addCriterion("SETTLEMENTACCOUNT >", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLEMENTACCOUNT >=", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountLessThan(String value) {
            addCriterion("SETTLEMENTACCOUNT <", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountLessThanOrEqualTo(String value) {
            addCriterion("SETTLEMENTACCOUNT <=", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountLike(String value) {
            addCriterion("SETTLEMENTACCOUNT like", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountNotLike(String value) {
            addCriterion("SETTLEMENTACCOUNT not like", value, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountIn(List<String> values) {
            addCriterion("SETTLEMENTACCOUNT in", values, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountNotIn(List<String> values) {
            addCriterion("SETTLEMENTACCOUNT not in", values, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountBetween(String value1, String value2) {
            addCriterion("SETTLEMENTACCOUNT between", value1, value2, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andSettlementaccountNotBetween(String value1, String value2) {
            addCriterion("SETTLEMENTACCOUNT not between", value1, value2, "settlementaccount");
            return (Criteria) this;
        }

        public Criteria andPerfeeIsNull() {
            addCriterion("PERFEE is null");
            return (Criteria) this;
        }

        public Criteria andPerfeeIsNotNull() {
            addCriterion("PERFEE is not null");
            return (Criteria) this;
        }

        public Criteria andPerfeeEqualTo(BigDecimal value) {
            addCriterion("PERFEE =", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotEqualTo(BigDecimal value) {
            addCriterion("PERFEE <>", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeGreaterThan(BigDecimal value) {
            addCriterion("PERFEE >", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PERFEE >=", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeLessThan(BigDecimal value) {
            addCriterion("PERFEE <", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PERFEE <=", value, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeIn(List<BigDecimal> values) {
            addCriterion("PERFEE in", values, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotIn(List<BigDecimal> values) {
            addCriterion("PERFEE not in", values, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERFEE between", value1, value2, "perfee");
            return (Criteria) this;
        }

        public Criteria andPerfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERFEE not between", value1, value2, "perfee");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIsNull() {
            addCriterion("FEE_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIsNotNull() {
            addCriterion("FEE_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andFeeOrderEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER =", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER <>", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderGreaterThan(BigDecimal value) {
            addCriterion("FEE_ORDER >", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER >=", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderLessThan(BigDecimal value) {
            addCriterion("FEE_ORDER <", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE_ORDER <=", value, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderIn(List<BigDecimal> values) {
            addCriterion("FEE_ORDER in", values, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotIn(List<BigDecimal> values) {
            addCriterion("FEE_ORDER not in", values, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_ORDER between", value1, value2, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andFeeOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE_ORDER not between", value1, value2, "feeOrder");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNull() {
            addCriterion("TRANSACTIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIsNotNull() {
            addCriterion("TRANSACTIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiondateEqualTo(String value) {
            addCriterion("TRANSACTIONDATE =", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <>", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThan(String value) {
            addCriterion("TRANSACTIONDATE >", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE >=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThan(String value) {
            addCriterion("TRANSACTIONDATE <", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <=", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLike(String value) {
            addCriterion("TRANSACTIONDATE like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotLike(String value) {
            addCriterion("TRANSACTIONDATE not like", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateIn(List<String> values) {
            addCriterion("TRANSACTIONDATE in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotIn(List<String> values) {
            addCriterion("TRANSACTIONDATE not in", values, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE between", value1, value2, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONDATE not between", value1, value2, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIsNull() {
            addCriterion("TRANSACTIONTIME is null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIsNotNull() {
            addCriterion("TRANSACTIONTIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeEqualTo(String value) {
            addCriterion("TRANSACTIONTIME =", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <>", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThan(String value) {
            addCriterion("TRANSACTIONTIME >", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME >=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThan(String value) {
            addCriterion("TRANSACTIONTIME <", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONTIME <=", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeLike(String value) {
            addCriterion("TRANSACTIONTIME like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotLike(String value) {
            addCriterion("TRANSACTIONTIME not like", value, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeIn(List<String> values) {
            addCriterion("TRANSACTIONTIME in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotIn(List<String> values) {
            addCriterion("TRANSACTIONTIME not in", values, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME between", value1, value2, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTransactiontimeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONTIME not between", value1, value2, "transactiontime");
            return (Criteria) this;
        }

        public Criteria andTerminallocationIsNull() {
            addCriterion("TERMINALLOCATION is null");
            return (Criteria) this;
        }

        public Criteria andTerminallocationIsNotNull() {
            addCriterion("TERMINALLOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andTerminallocationEqualTo(String value) {
            addCriterion("TERMINALLOCATION =", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationNotEqualTo(String value) {
            addCriterion("TERMINALLOCATION <>", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationGreaterThan(String value) {
            addCriterion("TERMINALLOCATION >", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINALLOCATION >=", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationLessThan(String value) {
            addCriterion("TERMINALLOCATION <", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationLessThanOrEqualTo(String value) {
            addCriterion("TERMINALLOCATION <=", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationLike(String value) {
            addCriterion("TERMINALLOCATION like", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationNotLike(String value) {
            addCriterion("TERMINALLOCATION not like", value, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationIn(List<String> values) {
            addCriterion("TERMINALLOCATION in", values, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationNotIn(List<String> values) {
            addCriterion("TERMINALLOCATION not in", values, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationBetween(String value1, String value2) {
            addCriterion("TERMINALLOCATION between", value1, value2, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andTerminallocationNotBetween(String value1, String value2) {
            addCriterion("TERMINALLOCATION not between", value1, value2, "terminallocation");
            return (Criteria) this;
        }

        public Criteria andDatasourceIsNull() {
            addCriterion("DATASOURCE is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIsNotNull() {
            addCriterion("DATASOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceEqualTo(String value) {
            addCriterion("DATASOURCE =", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceNotEqualTo(String value) {
            addCriterion("DATASOURCE <>", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceGreaterThan(String value) {
            addCriterion("DATASOURCE >", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceGreaterThanOrEqualTo(String value) {
            addCriterion("DATASOURCE >=", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceLessThan(String value) {
            addCriterion("DATASOURCE <", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceLessThanOrEqualTo(String value) {
            addCriterion("DATASOURCE <=", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceLike(String value) {
            addCriterion("DATASOURCE like", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceNotLike(String value) {
            addCriterion("DATASOURCE not like", value, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceIn(List<String> values) {
            addCriterion("DATASOURCE in", values, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceNotIn(List<String> values) {
            addCriterion("DATASOURCE not in", values, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceBetween(String value1, String value2) {
            addCriterion("DATASOURCE between", value1, value2, "datasource");
            return (Criteria) this;
        }

        public Criteria andDatasourceNotBetween(String value1, String value2) {
            addCriterion("DATASOURCE not between", value1, value2, "datasource");
            return (Criteria) this;
        }

        public Criteria andTlogidIsNull() {
            addCriterion("TLOGID is null");
            return (Criteria) this;
        }

        public Criteria andTlogidIsNotNull() {
            addCriterion("TLOGID is not null");
            return (Criteria) this;
        }

        public Criteria andTlogidEqualTo(Long value) {
            addCriterion("TLOGID =", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidNotEqualTo(Long value) {
            addCriterion("TLOGID <>", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidGreaterThan(Long value) {
            addCriterion("TLOGID >", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidGreaterThanOrEqualTo(Long value) {
            addCriterion("TLOGID >=", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidLessThan(Long value) {
            addCriterion("TLOGID <", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidLessThanOrEqualTo(Long value) {
            addCriterion("TLOGID <=", value, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidIn(List<Long> values) {
            addCriterion("TLOGID in", values, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidNotIn(List<Long> values) {
            addCriterion("TLOGID not in", values, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidBetween(Long value1, Long value2) {
            addCriterion("TLOGID between", value1, value2, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogidNotBetween(Long value1, Long value2) {
            addCriterion("TLOGID not between", value1, value2, "tlogid");
            return (Criteria) this;
        }

        public Criteria andTlogadddateIsNull() {
            addCriterion("TLOGADDDATE is null");
            return (Criteria) this;
        }

        public Criteria andTlogadddateIsNotNull() {
            addCriterion("TLOGADDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andTlogadddateEqualTo(String value) {
            addCriterion("TLOGADDDATE =", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateNotEqualTo(String value) {
            addCriterion("TLOGADDDATE <>", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateGreaterThan(String value) {
            addCriterion("TLOGADDDATE >", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateGreaterThanOrEqualTo(String value) {
            addCriterion("TLOGADDDATE >=", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateLessThan(String value) {
            addCriterion("TLOGADDDATE <", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateLessThanOrEqualTo(String value) {
            addCriterion("TLOGADDDATE <=", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateLike(String value) {
            addCriterion("TLOGADDDATE like", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateNotLike(String value) {
            addCriterion("TLOGADDDATE not like", value, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateIn(List<String> values) {
            addCriterion("TLOGADDDATE in", values, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateNotIn(List<String> values) {
            addCriterion("TLOGADDDATE not in", values, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateBetween(String value1, String value2) {
            addCriterion("TLOGADDDATE between", value1, value2, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andTlogadddateNotBetween(String value1, String value2) {
            addCriterion("TLOGADDDATE not between", value1, value2, "tlogadddate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateIsNull() {
            addCriterion("SETTLEMENTDATE is null");
            return (Criteria) this;
        }

        public Criteria andSettlementdateIsNotNull() {
            addCriterion("SETTLEMENTDATE is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementdateEqualTo(String value) {
            addCriterion("SETTLEMENTDATE =", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateNotEqualTo(String value) {
            addCriterion("SETTLEMENTDATE <>", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateGreaterThan(String value) {
            addCriterion("SETTLEMENTDATE >", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLEMENTDATE >=", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateLessThan(String value) {
            addCriterion("SETTLEMENTDATE <", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateLessThanOrEqualTo(String value) {
            addCriterion("SETTLEMENTDATE <=", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateLike(String value) {
            addCriterion("SETTLEMENTDATE like", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateNotLike(String value) {
            addCriterion("SETTLEMENTDATE not like", value, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateIn(List<String> values) {
            addCriterion("SETTLEMENTDATE in", values, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateNotIn(List<String> values) {
            addCriterion("SETTLEMENTDATE not in", values, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateBetween(String value1, String value2) {
            addCriterion("SETTLEMENTDATE between", value1, value2, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andSettlementdateNotBetween(String value1, String value2) {
            addCriterion("SETTLEMENTDATE not between", value1, value2, "settlementdate");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveIsNull() {
            addCriterion("ISEXCLUSIVE is null");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveIsNotNull() {
            addCriterion("ISEXCLUSIVE is not null");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveEqualTo(String value) {
            addCriterion("ISEXCLUSIVE =", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveNotEqualTo(String value) {
            addCriterion("ISEXCLUSIVE <>", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveGreaterThan(String value) {
            addCriterion("ISEXCLUSIVE >", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveGreaterThanOrEqualTo(String value) {
            addCriterion("ISEXCLUSIVE >=", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveLessThan(String value) {
            addCriterion("ISEXCLUSIVE <", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveLessThanOrEqualTo(String value) {
            addCriterion("ISEXCLUSIVE <=", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveLike(String value) {
            addCriterion("ISEXCLUSIVE like", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveNotLike(String value) {
            addCriterion("ISEXCLUSIVE not like", value, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveIn(List<String> values) {
            addCriterion("ISEXCLUSIVE in", values, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveNotIn(List<String> values) {
            addCriterion("ISEXCLUSIVE not in", values, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveBetween(String value1, String value2) {
            addCriterion("ISEXCLUSIVE between", value1, value2, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andIsexclusiveNotBetween(String value1, String value2) {
            addCriterion("ISEXCLUSIVE not between", value1, value2, "isexclusive");
            return (Criteria) this;
        }

        public Criteria andConsumetypeIsNull() {
            addCriterion("CONSUMETYPE is null");
            return (Criteria) this;
        }

        public Criteria andConsumetypeIsNotNull() {
            addCriterion("CONSUMETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConsumetypeEqualTo(String value) {
            addCriterion("CONSUMETYPE =", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeNotEqualTo(String value) {
            addCriterion("CONSUMETYPE <>", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeGreaterThan(String value) {
            addCriterion("CONSUMETYPE >", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMETYPE >=", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeLessThan(String value) {
            addCriterion("CONSUMETYPE <", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeLessThanOrEqualTo(String value) {
            addCriterion("CONSUMETYPE <=", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeLike(String value) {
            addCriterion("CONSUMETYPE like", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeNotLike(String value) {
            addCriterion("CONSUMETYPE not like", value, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeIn(List<String> values) {
            addCriterion("CONSUMETYPE in", values, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeNotIn(List<String> values) {
            addCriterion("CONSUMETYPE not in", values, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeBetween(String value1, String value2) {
            addCriterion("CONSUMETYPE between", value1, value2, "consumetype");
            return (Criteria) this;
        }

        public Criteria andConsumetypeNotBetween(String value1, String value2) {
            addCriterion("CONSUMETYPE not between", value1, value2, "consumetype");
            return (Criteria) this;
        }

        public Criteria andStanorgIsNull() {
            addCriterion("STANORG is null");
            return (Criteria) this;
        }

        public Criteria andStanorgIsNotNull() {
            addCriterion("STANORG is not null");
            return (Criteria) this;
        }

        public Criteria andStanorgEqualTo(Long value) {
            addCriterion("STANORG =", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgNotEqualTo(Long value) {
            addCriterion("STANORG <>", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgGreaterThan(Long value) {
            addCriterion("STANORG >", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgGreaterThanOrEqualTo(Long value) {
            addCriterion("STANORG >=", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgLessThan(Long value) {
            addCriterion("STANORG <", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgLessThanOrEqualTo(Long value) {
            addCriterion("STANORG <=", value, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgIn(List<Long> values) {
            addCriterion("STANORG in", values, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgNotIn(List<Long> values) {
            addCriterion("STANORG not in", values, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgBetween(Long value1, Long value2) {
            addCriterion("STANORG between", value1, value2, "stanorg");
            return (Criteria) this;
        }

        public Criteria andStanorgNotBetween(Long value1, Long value2) {
            addCriterion("STANORG not between", value1, value2, "stanorg");
            return (Criteria) this;
        }

        public Criteria andRrnIsNull() {
            addCriterion("RRN is null");
            return (Criteria) this;
        }

        public Criteria andRrnIsNotNull() {
            addCriterion("RRN is not null");
            return (Criteria) this;
        }

        public Criteria andRrnEqualTo(String value) {
            addCriterion("RRN =", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotEqualTo(String value) {
            addCriterion("RRN <>", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnGreaterThan(String value) {
            addCriterion("RRN >", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnGreaterThanOrEqualTo(String value) {
            addCriterion("RRN >=", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLessThan(String value) {
            addCriterion("RRN <", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLessThanOrEqualTo(String value) {
            addCriterion("RRN <=", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnLike(String value) {
            addCriterion("RRN like", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotLike(String value) {
            addCriterion("RRN not like", value, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnIn(List<String> values) {
            addCriterion("RRN in", values, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotIn(List<String> values) {
            addCriterion("RRN not in", values, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnBetween(String value1, String value2) {
            addCriterion("RRN between", value1, value2, "rrn");
            return (Criteria) this;
        }

        public Criteria andRrnNotBetween(String value1, String value2) {
            addCriterion("RRN not between", value1, value2, "rrn");
            return (Criteria) this;
        }

        public Criteria andTermtypeIsNull() {
            addCriterion("TERMTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTermtypeIsNotNull() {
            addCriterion("TERMTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTermtypeEqualTo(String value) {
            addCriterion("TERMTYPE =", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotEqualTo(String value) {
            addCriterion("TERMTYPE <>", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeGreaterThan(String value) {
            addCriterion("TERMTYPE >", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeGreaterThanOrEqualTo(String value) {
            addCriterion("TERMTYPE >=", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLessThan(String value) {
            addCriterion("TERMTYPE <", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLessThanOrEqualTo(String value) {
            addCriterion("TERMTYPE <=", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeLike(String value) {
            addCriterion("TERMTYPE like", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotLike(String value) {
            addCriterion("TERMTYPE not like", value, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeIn(List<String> values) {
            addCriterion("TERMTYPE in", values, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotIn(List<String> values) {
            addCriterion("TERMTYPE not in", values, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeBetween(String value1, String value2) {
            addCriterion("TERMTYPE between", value1, value2, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermtypeNotBetween(String value1, String value2) {
            addCriterion("TERMTYPE not between", value1, value2, "termtype");
            return (Criteria) this;
        }

        public Criteria andTermseqIsNull() {
            addCriterion("TERMSEQ is null");
            return (Criteria) this;
        }

        public Criteria andTermseqIsNotNull() {
            addCriterion("TERMSEQ is not null");
            return (Criteria) this;
        }

        public Criteria andTermseqEqualTo(Integer value) {
            addCriterion("TERMSEQ =", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqNotEqualTo(Integer value) {
            addCriterion("TERMSEQ <>", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqGreaterThan(Integer value) {
            addCriterion("TERMSEQ >", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqGreaterThanOrEqualTo(Integer value) {
            addCriterion("TERMSEQ >=", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqLessThan(Integer value) {
            addCriterion("TERMSEQ <", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqLessThanOrEqualTo(Integer value) {
            addCriterion("TERMSEQ <=", value, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqIn(List<Integer> values) {
            addCriterion("TERMSEQ in", values, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqNotIn(List<Integer> values) {
            addCriterion("TERMSEQ not in", values, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqBetween(Integer value1, Integer value2) {
            addCriterion("TERMSEQ between", value1, value2, "termseq");
            return (Criteria) this;
        }

        public Criteria andTermseqNotBetween(Integer value1, Integer value2) {
            addCriterion("TERMSEQ not between", value1, value2, "termseq");
            return (Criteria) this;
        }

        public Criteria andPanIsNull() {
            addCriterion("PAN is null");
            return (Criteria) this;
        }

        public Criteria andPanIsNotNull() {
            addCriterion("PAN is not null");
            return (Criteria) this;
        }

        public Criteria andPanEqualTo(String value) {
            addCriterion("PAN =", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotEqualTo(String value) {
            addCriterion("PAN <>", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThan(String value) {
            addCriterion("PAN >", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanGreaterThanOrEqualTo(String value) {
            addCriterion("PAN >=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThan(String value) {
            addCriterion("PAN <", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLessThanOrEqualTo(String value) {
            addCriterion("PAN <=", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanLike(String value) {
            addCriterion("PAN like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotLike(String value) {
            addCriterion("PAN not like", value, "pan");
            return (Criteria) this;
        }

        public Criteria andPanIn(List<String> values) {
            addCriterion("PAN in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotIn(List<String> values) {
            addCriterion("PAN not in", values, "pan");
            return (Criteria) this;
        }

        public Criteria andPanBetween(String value1, String value2) {
            addCriterion("PAN between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andPanNotBetween(String value1, String value2) {
            addCriterion("PAN not between", value1, value2, "pan");
            return (Criteria) this;
        }

        public Criteria andAiidIsNull() {
            addCriterion("AIID is null");
            return (Criteria) this;
        }

        public Criteria andAiidIsNotNull() {
            addCriterion("AIID is not null");
            return (Criteria) this;
        }

        public Criteria andAiidEqualTo(String value) {
            addCriterion("AIID =", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidNotEqualTo(String value) {
            addCriterion("AIID <>", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidGreaterThan(String value) {
            addCriterion("AIID >", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidGreaterThanOrEqualTo(String value) {
            addCriterion("AIID >=", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidLessThan(String value) {
            addCriterion("AIID <", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidLessThanOrEqualTo(String value) {
            addCriterion("AIID <=", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidLike(String value) {
            addCriterion("AIID like", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidNotLike(String value) {
            addCriterion("AIID not like", value, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidIn(List<String> values) {
            addCriterion("AIID in", values, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidNotIn(List<String> values) {
            addCriterion("AIID not in", values, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidBetween(String value1, String value2) {
            addCriterion("AIID between", value1, value2, "aiid");
            return (Criteria) this;
        }

        public Criteria andAiidNotBetween(String value1, String value2) {
            addCriterion("AIID not between", value1, value2, "aiid");
            return (Criteria) this;
        }

        public Criteria andTxnsrcIsNull() {
            addCriterion("TXNSRC is null");
            return (Criteria) this;
        }

        public Criteria andTxnsrcIsNotNull() {
            addCriterion("TXNSRC is not null");
            return (Criteria) this;
        }

        public Criteria andTxnsrcEqualTo(String value) {
            addCriterion("TXNSRC =", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcNotEqualTo(String value) {
            addCriterion("TXNSRC <>", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcGreaterThan(String value) {
            addCriterion("TXNSRC >", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcGreaterThanOrEqualTo(String value) {
            addCriterion("TXNSRC >=", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcLessThan(String value) {
            addCriterion("TXNSRC <", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcLessThanOrEqualTo(String value) {
            addCriterion("TXNSRC <=", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcLike(String value) {
            addCriterion("TXNSRC like", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcNotLike(String value) {
            addCriterion("TXNSRC not like", value, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcIn(List<String> values) {
            addCriterion("TXNSRC in", values, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcNotIn(List<String> values) {
            addCriterion("TXNSRC not in", values, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcBetween(String value1, String value2) {
            addCriterion("TXNSRC between", value1, value2, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcNotBetween(String value1, String value2) {
            addCriterion("TXNSRC not between", value1, value2, "txnsrc");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgIsNull() {
            addCriterion("TXNSRCORG is null");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgIsNotNull() {
            addCriterion("TXNSRCORG is not null");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgEqualTo(String value) {
            addCriterion("TXNSRCORG =", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgNotEqualTo(String value) {
            addCriterion("TXNSRCORG <>", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgGreaterThan(String value) {
            addCriterion("TXNSRCORG >", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgGreaterThanOrEqualTo(String value) {
            addCriterion("TXNSRCORG >=", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgLessThan(String value) {
            addCriterion("TXNSRCORG <", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgLessThanOrEqualTo(String value) {
            addCriterion("TXNSRCORG <=", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgLike(String value) {
            addCriterion("TXNSRCORG like", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgNotLike(String value) {
            addCriterion("TXNSRCORG not like", value, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgIn(List<String> values) {
            addCriterion("TXNSRCORG in", values, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgNotIn(List<String> values) {
            addCriterion("TXNSRCORG not in", values, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgBetween(String value1, String value2) {
            addCriterion("TXNSRCORG between", value1, value2, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxnsrcorgNotBetween(String value1, String value2) {
            addCriterion("TXNSRCORG not between", value1, value2, "txnsrcorg");
            return (Criteria) this;
        }

        public Criteria andTxncodeIsNull() {
            addCriterion("TXNCODE is null");
            return (Criteria) this;
        }

        public Criteria andTxncodeIsNotNull() {
            addCriterion("TXNCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTxncodeEqualTo(Integer value) {
            addCriterion("TXNCODE =", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotEqualTo(Integer value) {
            addCriterion("TXNCODE <>", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeGreaterThan(Integer value) {
            addCriterion("TXNCODE >", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TXNCODE >=", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeLessThan(Integer value) {
            addCriterion("TXNCODE <", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeLessThanOrEqualTo(Integer value) {
            addCriterion("TXNCODE <=", value, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeIn(List<Integer> values) {
            addCriterion("TXNCODE in", values, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotIn(List<Integer> values) {
            addCriterion("TXNCODE not in", values, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeBetween(Integer value1, Integer value2) {
            addCriterion("TXNCODE between", value1, value2, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxncodeNotBetween(Integer value1, Integer value2) {
            addCriterion("TXNCODE not between", value1, value2, "txncode");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIsNull() {
            addCriterion("TXNSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIsNotNull() {
            addCriterion("TXNSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTxnstatusEqualTo(Integer value) {
            addCriterion("TXNSTATUS =", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotEqualTo(Integer value) {
            addCriterion("TXNSTATUS <>", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusGreaterThan(Integer value) {
            addCriterion("TXNSTATUS >", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("TXNSTATUS >=", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusLessThan(Integer value) {
            addCriterion("TXNSTATUS <", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusLessThanOrEqualTo(Integer value) {
            addCriterion("TXNSTATUS <=", value, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusIn(List<Integer> values) {
            addCriterion("TXNSTATUS in", values, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotIn(List<Integer> values) {
            addCriterion("TXNSTATUS not in", values, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusBetween(Integer value1, Integer value2) {
            addCriterion("TXNSTATUS between", value1, value2, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andTxnstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("TXNSTATUS not between", value1, value2, "txnstatus");
            return (Criteria) this;
        }

        public Criteria andCurtxnIsNull() {
            addCriterion("CURTXN is null");
            return (Criteria) this;
        }

        public Criteria andCurtxnIsNotNull() {
            addCriterion("CURTXN is not null");
            return (Criteria) this;
        }

        public Criteria andCurtxnEqualTo(String value) {
            addCriterion("CURTXN =", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnNotEqualTo(String value) {
            addCriterion("CURTXN <>", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnGreaterThan(String value) {
            addCriterion("CURTXN >", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnGreaterThanOrEqualTo(String value) {
            addCriterion("CURTXN >=", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnLessThan(String value) {
            addCriterion("CURTXN <", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnLessThanOrEqualTo(String value) {
            addCriterion("CURTXN <=", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnLike(String value) {
            addCriterion("CURTXN like", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnNotLike(String value) {
            addCriterion("CURTXN not like", value, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnIn(List<String> values) {
            addCriterion("CURTXN in", values, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnNotIn(List<String> values) {
            addCriterion("CURTXN not in", values, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnBetween(String value1, String value2) {
            addCriterion("CURTXN between", value1, value2, "curtxn");
            return (Criteria) this;
        }

        public Criteria andCurtxnNotBetween(String value1, String value2) {
            addCriterion("CURTXN not between", value1, value2, "curtxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnIsNull() {
            addCriterion("AMTTXN is null");
            return (Criteria) this;
        }

        public Criteria andAmttxnIsNotNull() {
            addCriterion("AMTTXN is not null");
            return (Criteria) this;
        }

        public Criteria andAmttxnEqualTo(BigDecimal value) {
            addCriterion("AMTTXN =", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotEqualTo(BigDecimal value) {
            addCriterion("AMTTXN <>", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnGreaterThan(BigDecimal value) {
            addCriterion("AMTTXN >", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXN >=", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnLessThan(BigDecimal value) {
            addCriterion("AMTTXN <", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXN <=", value, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnIn(List<BigDecimal> values) {
            addCriterion("AMTTXN in", values, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotIn(List<BigDecimal> values) {
            addCriterion("AMTTXN not in", values, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXN between", value1, value2, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXN not between", value1, value2, "amttxn");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeIsNull() {
            addCriterion("AMTTXNFEE is null");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeIsNotNull() {
            addCriterion("AMTTXNFEE is not null");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeEqualTo(BigDecimal value) {
            addCriterion("AMTTXNFEE =", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeNotEqualTo(BigDecimal value) {
            addCriterion("AMTTXNFEE <>", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeGreaterThan(BigDecimal value) {
            addCriterion("AMTTXNFEE >", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXNFEE >=", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeLessThan(BigDecimal value) {
            addCriterion("AMTTXNFEE <", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXNFEE <=", value, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeIn(List<BigDecimal> values) {
            addCriterion("AMTTXNFEE in", values, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeNotIn(List<BigDecimal> values) {
            addCriterion("AMTTXNFEE not in", values, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXNFEE between", value1, value2, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXNFEE not between", value1, value2, "amttxnfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeIsNull() {
            addCriterion("AMTPROCFEE is null");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeIsNotNull() {
            addCriterion("AMTPROCFEE is not null");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeEqualTo(BigDecimal value) {
            addCriterion("AMTPROCFEE =", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeNotEqualTo(BigDecimal value) {
            addCriterion("AMTPROCFEE <>", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeGreaterThan(BigDecimal value) {
            addCriterion("AMTPROCFEE >", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTPROCFEE >=", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeLessThan(BigDecimal value) {
            addCriterion("AMTPROCFEE <", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTPROCFEE <=", value, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeIn(List<BigDecimal> values) {
            addCriterion("AMTPROCFEE in", values, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeNotIn(List<BigDecimal> values) {
            addCriterion("AMTPROCFEE not in", values, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTPROCFEE between", value1, value2, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmtprocfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTPROCFEE not between", value1, value2, "amtprocfee");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgIsNull() {
            addCriterion("AMTTXNORG is null");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgIsNotNull() {
            addCriterion("AMTTXNORG is not null");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgEqualTo(BigDecimal value) {
            addCriterion("AMTTXNORG =", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgNotEqualTo(BigDecimal value) {
            addCriterion("AMTTXNORG <>", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgGreaterThan(BigDecimal value) {
            addCriterion("AMTTXNORG >", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXNORG >=", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgLessThan(BigDecimal value) {
            addCriterion("AMTTXNORG <", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMTTXNORG <=", value, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgIn(List<BigDecimal> values) {
            addCriterion("AMTTXNORG in", values, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgNotIn(List<BigDecimal> values) {
            addCriterion("AMTTXNORG not in", values, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXNORG between", value1, value2, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andAmttxnorgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMTTXNORG not between", value1, value2, "amttxnorg");
            return (Criteria) this;
        }

        public Criteria andActype1IsNull() {
            addCriterion("ACTYPE1 is null");
            return (Criteria) this;
        }

        public Criteria andActype1IsNotNull() {
            addCriterion("ACTYPE1 is not null");
            return (Criteria) this;
        }

        public Criteria andActype1EqualTo(Integer value) {
            addCriterion("ACTYPE1 =", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1NotEqualTo(Integer value) {
            addCriterion("ACTYPE1 <>", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1GreaterThan(Integer value) {
            addCriterion("ACTYPE1 >", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1GreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTYPE1 >=", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1LessThan(Integer value) {
            addCriterion("ACTYPE1 <", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1LessThanOrEqualTo(Integer value) {
            addCriterion("ACTYPE1 <=", value, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1In(List<Integer> values) {
            addCriterion("ACTYPE1 in", values, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1NotIn(List<Integer> values) {
            addCriterion("ACTYPE1 not in", values, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1Between(Integer value1, Integer value2) {
            addCriterion("ACTYPE1 between", value1, value2, "actype1");
            return (Criteria) this;
        }

        public Criteria andActype1NotBetween(Integer value1, Integer value2) {
            addCriterion("ACTYPE1 not between", value1, value2, "actype1");
            return (Criteria) this;
        }

        public Criteria andAcnum1IsNull() {
            addCriterion("ACNUM1 is null");
            return (Criteria) this;
        }

        public Criteria andAcnum1IsNotNull() {
            addCriterion("ACNUM1 is not null");
            return (Criteria) this;
        }

        public Criteria andAcnum1EqualTo(String value) {
            addCriterion("ACNUM1 =", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1NotEqualTo(String value) {
            addCriterion("ACNUM1 <>", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1GreaterThan(String value) {
            addCriterion("ACNUM1 >", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1GreaterThanOrEqualTo(String value) {
            addCriterion("ACNUM1 >=", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1LessThan(String value) {
            addCriterion("ACNUM1 <", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1LessThanOrEqualTo(String value) {
            addCriterion("ACNUM1 <=", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1Like(String value) {
            addCriterion("ACNUM1 like", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1NotLike(String value) {
            addCriterion("ACNUM1 not like", value, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1In(List<String> values) {
            addCriterion("ACNUM1 in", values, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1NotIn(List<String> values) {
            addCriterion("ACNUM1 not in", values, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1Between(String value1, String value2) {
            addCriterion("ACNUM1 between", value1, value2, "acnum1");
            return (Criteria) this;
        }

        public Criteria andAcnum1NotBetween(String value1, String value2) {
            addCriterion("ACNUM1 not between", value1, value2, "acnum1");
            return (Criteria) this;
        }

        public Criteria andActype2IsNull() {
            addCriterion("ACTYPE2 is null");
            return (Criteria) this;
        }

        public Criteria andActype2IsNotNull() {
            addCriterion("ACTYPE2 is not null");
            return (Criteria) this;
        }

        public Criteria andActype2EqualTo(Integer value) {
            addCriterion("ACTYPE2 =", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2NotEqualTo(Integer value) {
            addCriterion("ACTYPE2 <>", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2GreaterThan(Integer value) {
            addCriterion("ACTYPE2 >", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2GreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTYPE2 >=", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2LessThan(Integer value) {
            addCriterion("ACTYPE2 <", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2LessThanOrEqualTo(Integer value) {
            addCriterion("ACTYPE2 <=", value, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2In(List<Integer> values) {
            addCriterion("ACTYPE2 in", values, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2NotIn(List<Integer> values) {
            addCriterion("ACTYPE2 not in", values, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2Between(Integer value1, Integer value2) {
            addCriterion("ACTYPE2 between", value1, value2, "actype2");
            return (Criteria) this;
        }

        public Criteria andActype2NotBetween(Integer value1, Integer value2) {
            addCriterion("ACTYPE2 not between", value1, value2, "actype2");
            return (Criteria) this;
        }

        public Criteria andAcnum2IsNull() {
            addCriterion("ACNUM2 is null");
            return (Criteria) this;
        }

        public Criteria andAcnum2IsNotNull() {
            addCriterion("ACNUM2 is not null");
            return (Criteria) this;
        }

        public Criteria andAcnum2EqualTo(String value) {
            addCriterion("ACNUM2 =", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2NotEqualTo(String value) {
            addCriterion("ACNUM2 <>", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2GreaterThan(String value) {
            addCriterion("ACNUM2 >", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2GreaterThanOrEqualTo(String value) {
            addCriterion("ACNUM2 >=", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2LessThan(String value) {
            addCriterion("ACNUM2 <", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2LessThanOrEqualTo(String value) {
            addCriterion("ACNUM2 <=", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2Like(String value) {
            addCriterion("ACNUM2 like", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2NotLike(String value) {
            addCriterion("ACNUM2 not like", value, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2In(List<String> values) {
            addCriterion("ACNUM2 in", values, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2NotIn(List<String> values) {
            addCriterion("ACNUM2 not in", values, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2Between(String value1, String value2) {
            addCriterion("ACNUM2 between", value1, value2, "acnum2");
            return (Criteria) this;
        }

        public Criteria andAcnum2NotBetween(String value1, String value2) {
            addCriterion("ACNUM2 not between", value1, value2, "acnum2");
            return (Criteria) this;
        }

        public Criteria andRspcodeIsNull() {
            addCriterion("RSPCODE is null");
            return (Criteria) this;
        }

        public Criteria andRspcodeIsNotNull() {
            addCriterion("RSPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRspcodeEqualTo(String value) {
            addCriterion("RSPCODE =", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotEqualTo(String value) {
            addCriterion("RSPCODE <>", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeGreaterThan(String value) {
            addCriterion("RSPCODE >", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeGreaterThanOrEqualTo(String value) {
            addCriterion("RSPCODE >=", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLessThan(String value) {
            addCriterion("RSPCODE <", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLessThanOrEqualTo(String value) {
            addCriterion("RSPCODE <=", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeLike(String value) {
            addCriterion("RSPCODE like", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotLike(String value) {
            addCriterion("RSPCODE not like", value, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeIn(List<String> values) {
            addCriterion("RSPCODE in", values, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotIn(List<String> values) {
            addCriterion("RSPCODE not in", values, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeBetween(String value1, String value2) {
            addCriterion("RSPCODE between", value1, value2, "rspcode");
            return (Criteria) this;
        }

        public Criteria andRspcodeNotBetween(String value1, String value2) {
            addCriterion("RSPCODE not between", value1, value2, "rspcode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeIsNull() {
            addCriterion("REASONCODE is null");
            return (Criteria) this;
        }

        public Criteria andReasoncodeIsNotNull() {
            addCriterion("REASONCODE is not null");
            return (Criteria) this;
        }

        public Criteria andReasoncodeEqualTo(Integer value) {
            addCriterion("REASONCODE =", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeNotEqualTo(Integer value) {
            addCriterion("REASONCODE <>", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeGreaterThan(Integer value) {
            addCriterion("REASONCODE >", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("REASONCODE >=", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeLessThan(Integer value) {
            addCriterion("REASONCODE <", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeLessThanOrEqualTo(Integer value) {
            addCriterion("REASONCODE <=", value, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeIn(List<Integer> values) {
            addCriterion("REASONCODE in", values, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeNotIn(List<Integer> values) {
            addCriterion("REASONCODE not in", values, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeBetween(Integer value1, Integer value2) {
            addCriterion("REASONCODE between", value1, value2, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andReasoncodeNotBetween(Integer value1, Integer value2) {
            addCriterion("REASONCODE not between", value1, value2, "reasoncode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeIsNull() {
            addCriterion("APRVLCODE is null");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeIsNotNull() {
            addCriterion("APRVLCODE is not null");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeEqualTo(String value) {
            addCriterion("APRVLCODE =", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeNotEqualTo(String value) {
            addCriterion("APRVLCODE <>", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeGreaterThan(String value) {
            addCriterion("APRVLCODE >", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeGreaterThanOrEqualTo(String value) {
            addCriterion("APRVLCODE >=", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeLessThan(String value) {
            addCriterion("APRVLCODE <", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeLessThanOrEqualTo(String value) {
            addCriterion("APRVLCODE <=", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeLike(String value) {
            addCriterion("APRVLCODE like", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeNotLike(String value) {
            addCriterion("APRVLCODE not like", value, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeIn(List<String> values) {
            addCriterion("APRVLCODE in", values, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeNotIn(List<String> values) {
            addCriterion("APRVLCODE not in", values, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeBetween(String value1, String value2) {
            addCriterion("APRVLCODE between", value1, value2, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andAprvlcodeNotBetween(String value1, String value2) {
            addCriterion("APRVLCODE not between", value1, value2, "aprvlcode");
            return (Criteria) this;
        }

        public Criteria andSysdatesIsNull() {
            addCriterion("SYSDATES is null");
            return (Criteria) this;
        }

        public Criteria andSysdatesIsNotNull() {
            addCriterion("SYSDATES is not null");
            return (Criteria) this;
        }

        public Criteria andSysdatesEqualTo(Date value) {
            addCriterionForJDBCDate("SYSDATES =", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesNotEqualTo(Date value) {
            addCriterionForJDBCDate("SYSDATES <>", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesGreaterThan(Date value) {
            addCriterionForJDBCDate("SYSDATES >", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SYSDATES >=", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesLessThan(Date value) {
            addCriterionForJDBCDate("SYSDATES <", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SYSDATES <=", value, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesIn(List<Date> values) {
            addCriterionForJDBCDate("SYSDATES in", values, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesNotIn(List<Date> values) {
            addCriterionForJDBCDate("SYSDATES not in", values, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SYSDATES between", value1, value2, "sysdates");
            return (Criteria) this;
        }

        public Criteria andSysdatesNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SYSDATES not between", value1, value2, "sysdates");
            return (Criteria) this;
        }

        public Criteria andRatebillIsNull() {
            addCriterion("RATEBILL is null");
            return (Criteria) this;
        }

        public Criteria andRatebillIsNotNull() {
            addCriterion("RATEBILL is not null");
            return (Criteria) this;
        }

        public Criteria andRatebillEqualTo(BigDecimal value) {
            addCriterion("RATEBILL =", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillNotEqualTo(BigDecimal value) {
            addCriterion("RATEBILL <>", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillGreaterThan(BigDecimal value) {
            addCriterion("RATEBILL >", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RATEBILL >=", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillLessThan(BigDecimal value) {
            addCriterion("RATEBILL <", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RATEBILL <=", value, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillIn(List<BigDecimal> values) {
            addCriterion("RATEBILL in", values, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillNotIn(List<BigDecimal> values) {
            addCriterion("RATEBILL not in", values, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RATEBILL between", value1, value2, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatebillNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RATEBILL not between", value1, value2, "ratebill");
            return (Criteria) this;
        }

        public Criteria andRatesetIsNull() {
            addCriterion("RATESET is null");
            return (Criteria) this;
        }

        public Criteria andRatesetIsNotNull() {
            addCriterion("RATESET is not null");
            return (Criteria) this;
        }

        public Criteria andRatesetEqualTo(BigDecimal value) {
            addCriterion("RATESET =", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetNotEqualTo(BigDecimal value) {
            addCriterion("RATESET <>", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetGreaterThan(BigDecimal value) {
            addCriterion("RATESET >", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RATESET >=", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetLessThan(BigDecimal value) {
            addCriterion("RATESET <", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RATESET <=", value, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetIn(List<BigDecimal> values) {
            addCriterion("RATESET in", values, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetNotIn(List<BigDecimal> values) {
            addCriterion("RATESET not in", values, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RATESET between", value1, value2, "rateset");
            return (Criteria) this;
        }

        public Criteria andRatesetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RATESET not between", value1, value2, "rateset");
            return (Criteria) this;
        }

        public Criteria andAdddataIsNull() {
            addCriterion("ADDDATA is null");
            return (Criteria) this;
        }

        public Criteria andAdddataIsNotNull() {
            addCriterion("ADDDATA is not null");
            return (Criteria) this;
        }

        public Criteria andAdddataEqualTo(String value) {
            addCriterion("ADDDATA =", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataNotEqualTo(String value) {
            addCriterion("ADDDATA <>", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataGreaterThan(String value) {
            addCriterion("ADDDATA >", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataGreaterThanOrEqualTo(String value) {
            addCriterion("ADDDATA >=", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataLessThan(String value) {
            addCriterion("ADDDATA <", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataLessThanOrEqualTo(String value) {
            addCriterion("ADDDATA <=", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataLike(String value) {
            addCriterion("ADDDATA like", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataNotLike(String value) {
            addCriterion("ADDDATA not like", value, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataIn(List<String> values) {
            addCriterion("ADDDATA in", values, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataNotIn(List<String> values) {
            addCriterion("ADDDATA not in", values, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataBetween(String value1, String value2) {
            addCriterion("ADDDATA between", value1, value2, "adddata");
            return (Criteria) this;
        }

        public Criteria andAdddataNotBetween(String value1, String value2) {
            addCriterion("ADDDATA not between", value1, value2, "adddata");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeIsNull() {
            addCriterion("SUB_TXNCODE is null");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeIsNotNull() {
            addCriterion("SUB_TXNCODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeEqualTo(String value) {
            addCriterion("SUB_TXNCODE =", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeNotEqualTo(String value) {
            addCriterion("SUB_TXNCODE <>", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeGreaterThan(String value) {
            addCriterion("SUB_TXNCODE >", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_TXNCODE >=", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeLessThan(String value) {
            addCriterion("SUB_TXNCODE <", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeLessThanOrEqualTo(String value) {
            addCriterion("SUB_TXNCODE <=", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeLike(String value) {
            addCriterion("SUB_TXNCODE like", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeNotLike(String value) {
            addCriterion("SUB_TXNCODE not like", value, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeIn(List<String> values) {
            addCriterion("SUB_TXNCODE in", values, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeNotIn(List<String> values) {
            addCriterion("SUB_TXNCODE not in", values, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeBetween(String value1, String value2) {
            addCriterion("SUB_TXNCODE between", value1, value2, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andSubTxncodeNotBetween(String value1, String value2) {
            addCriterion("SUB_TXNCODE not between", value1, value2, "subTxncode");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("FLAG like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("FLAG not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andAgetinegralIsNull() {
            addCriterion("AGETINEGRAL is null");
            return (Criteria) this;
        }

        public Criteria andAgetinegralIsNotNull() {
            addCriterion("AGETINEGRAL is not null");
            return (Criteria) this;
        }

        public Criteria andAgetinegralEqualTo(String value) {
            addCriterion("AGETINEGRAL =", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralNotEqualTo(String value) {
            addCriterion("AGETINEGRAL <>", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralGreaterThan(String value) {
            addCriterion("AGETINEGRAL >", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralGreaterThanOrEqualTo(String value) {
            addCriterion("AGETINEGRAL >=", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralLessThan(String value) {
            addCriterion("AGETINEGRAL <", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralLessThanOrEqualTo(String value) {
            addCriterion("AGETINEGRAL <=", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralLike(String value) {
            addCriterion("AGETINEGRAL like", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralNotLike(String value) {
            addCriterion("AGETINEGRAL not like", value, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralIn(List<String> values) {
            addCriterion("AGETINEGRAL in", values, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralNotIn(List<String> values) {
            addCriterion("AGETINEGRAL not in", values, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralBetween(String value1, String value2) {
            addCriterion("AGETINEGRAL between", value1, value2, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andAgetinegralNotBetween(String value1, String value2) {
            addCriterion("AGETINEGRAL not between", value1, value2, "agetinegral");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIsNull() {
            addCriterion("ORDERNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIsNotNull() {
            addCriterion("ORDERNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernumberEqualTo(String value) {
            addCriterion("ORDERNUMBER =", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotEqualTo(String value) {
            addCriterion("ORDERNUMBER <>", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberGreaterThan(String value) {
            addCriterion("ORDERNUMBER >", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberGreaterThanOrEqualTo(String value) {
            addCriterion("ORDERNUMBER >=", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLessThan(String value) {
            addCriterion("ORDERNUMBER <", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLessThanOrEqualTo(String value) {
            addCriterion("ORDERNUMBER <=", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLike(String value) {
            addCriterion("ORDERNUMBER like", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotLike(String value) {
            addCriterion("ORDERNUMBER not like", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIn(List<String> values) {
            addCriterion("ORDERNUMBER in", values, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotIn(List<String> values) {
            addCriterion("ORDERNUMBER not in", values, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberBetween(String value1, String value2) {
            addCriterion("ORDERNUMBER between", value1, value2, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotBetween(String value1, String value2) {
            addCriterion("ORDERNUMBER not between", value1, value2, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIsNull() {
            addCriterion("CHECK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIsNotNull() {
            addCriterion("CHECK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCheckFlagEqualTo(BigDecimal value) {
            addCriterion("CHECK_FLAG =", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotEqualTo(BigDecimal value) {
            addCriterion("CHECK_FLAG <>", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagGreaterThan(BigDecimal value) {
            addCriterion("CHECK_FLAG >", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHECK_FLAG >=", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagLessThan(BigDecimal value) {
            addCriterion("CHECK_FLAG <", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHECK_FLAG <=", value, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagIn(List<BigDecimal> values) {
            addCriterion("CHECK_FLAG in", values, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotIn(List<BigDecimal> values) {
            addCriterion("CHECK_FLAG not in", values, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHECK_FLAG between", value1, value2, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andCheckFlagNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHECK_FLAG not between", value1, value2, "checkFlag");
            return (Criteria) this;
        }

        public Criteria andClearbookIdIsNull() {
            addCriterion("CLEARBOOK_ID is null");
            return (Criteria) this;
        }

        public Criteria andClearbookIdIsNotNull() {
            addCriterion("CLEARBOOK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClearbookIdEqualTo(String value) {
            addCriterion("CLEARBOOK_ID =", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdNotEqualTo(String value) {
            addCriterion("CLEARBOOK_ID <>", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdGreaterThan(String value) {
            addCriterion("CLEARBOOK_ID >", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLEARBOOK_ID >=", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdLessThan(String value) {
            addCriterion("CLEARBOOK_ID <", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdLessThanOrEqualTo(String value) {
            addCriterion("CLEARBOOK_ID <=", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdLike(String value) {
            addCriterion("CLEARBOOK_ID like", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdNotLike(String value) {
            addCriterion("CLEARBOOK_ID not like", value, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdIn(List<String> values) {
            addCriterion("CLEARBOOK_ID in", values, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdNotIn(List<String> values) {
            addCriterion("CLEARBOOK_ID not in", values, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdBetween(String value1, String value2) {
            addCriterion("CLEARBOOK_ID between", value1, value2, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andClearbookIdNotBetween(String value1, String value2) {
            addCriterion("CLEARBOOK_ID not between", value1, value2, "clearbookId");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberIsNull() {
            addCriterion("OLDSERIALNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberIsNotNull() {
            addCriterion("OLDSERIALNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberEqualTo(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER =", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberNotEqualTo(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER <>", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberGreaterThan(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER >", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER >=", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberLessThan(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER <", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDSERIALNUMBER <=", value, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberIn(List<BigDecimal> values) {
            addCriterion("OLDSERIALNUMBER in", values, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberNotIn(List<BigDecimal> values) {
            addCriterion("OLDSERIALNUMBER not in", values, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDSERIALNUMBER between", value1, value2, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldserialnumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDSERIALNUMBER not between", value1, value2, "oldserialnumber");
            return (Criteria) this;
        }

        public Criteria andOldlotnoIsNull() {
            addCriterion("OLDLOTNO is null");
            return (Criteria) this;
        }

        public Criteria andOldlotnoIsNotNull() {
            addCriterion("OLDLOTNO is not null");
            return (Criteria) this;
        }

        public Criteria andOldlotnoEqualTo(BigDecimal value) {
            addCriterion("OLDLOTNO =", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoNotEqualTo(BigDecimal value) {
            addCriterion("OLDLOTNO <>", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoGreaterThan(BigDecimal value) {
            addCriterion("OLDLOTNO >", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDLOTNO >=", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoLessThan(BigDecimal value) {
            addCriterion("OLDLOTNO <", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDLOTNO <=", value, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoIn(List<BigDecimal> values) {
            addCriterion("OLDLOTNO in", values, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoNotIn(List<BigDecimal> values) {
            addCriterion("OLDLOTNO not in", values, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDLOTNO between", value1, value2, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldlotnoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDLOTNO not between", value1, value2, "oldlotno");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberIsNull() {
            addCriterion("OLDREFERENCENUMBER is null");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberIsNotNull() {
            addCriterion("OLDREFERENCENUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberEqualTo(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER =", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberNotEqualTo(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER <>", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberGreaterThan(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER >", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER >=", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberLessThan(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER <", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OLDREFERENCENUMBER <=", value, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberIn(List<BigDecimal> values) {
            addCriterion("OLDREFERENCENUMBER in", values, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberNotIn(List<BigDecimal> values) {
            addCriterion("OLDREFERENCENUMBER not in", values, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDREFERENCENUMBER between", value1, value2, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldreferencenumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OLDREFERENCENUMBER not between", value1, value2, "oldreferencenumber");
            return (Criteria) this;
        }

        public Criteria andOldtransdateIsNull() {
            addCriterion("OLDTRANSDATE is null");
            return (Criteria) this;
        }

        public Criteria andOldtransdateIsNotNull() {
            addCriterion("OLDTRANSDATE is not null");
            return (Criteria) this;
        }

        public Criteria andOldtransdateEqualTo(String value) {
            addCriterion("OLDTRANSDATE =", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateNotEqualTo(String value) {
            addCriterion("OLDTRANSDATE <>", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateGreaterThan(String value) {
            addCriterion("OLDTRANSDATE >", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateGreaterThanOrEqualTo(String value) {
            addCriterion("OLDTRANSDATE >=", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateLessThan(String value) {
            addCriterion("OLDTRANSDATE <", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateLessThanOrEqualTo(String value) {
            addCriterion("OLDTRANSDATE <=", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateLike(String value) {
            addCriterion("OLDTRANSDATE like", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateNotLike(String value) {
            addCriterion("OLDTRANSDATE not like", value, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateIn(List<String> values) {
            addCriterion("OLDTRANSDATE in", values, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateNotIn(List<String> values) {
            addCriterion("OLDTRANSDATE not in", values, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateBetween(String value1, String value2) {
            addCriterion("OLDTRANSDATE between", value1, value2, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtransdateNotBetween(String value1, String value2) {
            addCriterion("OLDTRANSDATE not between", value1, value2, "oldtransdate");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeIsNull() {
            addCriterion("OLDTRANSTIME is null");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeIsNotNull() {
            addCriterion("OLDTRANSTIME is not null");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeEqualTo(String value) {
            addCriterion("OLDTRANSTIME =", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeNotEqualTo(String value) {
            addCriterion("OLDTRANSTIME <>", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeGreaterThan(String value) {
            addCriterion("OLDTRANSTIME >", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeGreaterThanOrEqualTo(String value) {
            addCriterion("OLDTRANSTIME >=", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeLessThan(String value) {
            addCriterion("OLDTRANSTIME <", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeLessThanOrEqualTo(String value) {
            addCriterion("OLDTRANSTIME <=", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeLike(String value) {
            addCriterion("OLDTRANSTIME like", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeNotLike(String value) {
            addCriterion("OLDTRANSTIME not like", value, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeIn(List<String> values) {
            addCriterion("OLDTRANSTIME in", values, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeNotIn(List<String> values) {
            addCriterion("OLDTRANSTIME not in", values, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeBetween(String value1, String value2) {
            addCriterion("OLDTRANSTIME between", value1, value2, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andOldtranstimeNotBetween(String value1, String value2) {
            addCriterion("OLDTRANSTIME not between", value1, value2, "oldtranstime");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("BUSINESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("BUSINESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(String value) {
            addCriterion("BUSINESS_ID =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(String value) {
            addCriterion("BUSINESS_ID <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(String value) {
            addCriterion("BUSINESS_ID >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ID >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(String value) {
            addCriterion("BUSINESS_ID <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ID <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLike(String value) {
            addCriterion("BUSINESS_ID like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotLike(String value) {
            addCriterion("BUSINESS_ID not like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<String> values) {
            addCriterion("BUSINESS_ID in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<String> values) {
            addCriterion("BUSINESS_ID not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(String value1, String value2) {
            addCriterion("BUSINESS_ID between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_ID not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andKindIdIsNull() {
            addCriterion("KIND_ID is null");
            return (Criteria) this;
        }

        public Criteria andKindIdIsNotNull() {
            addCriterion("KIND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andKindIdEqualTo(String value) {
            addCriterion("KIND_ID =", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotEqualTo(String value) {
            addCriterion("KIND_ID <>", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdGreaterThan(String value) {
            addCriterion("KIND_ID >", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdGreaterThanOrEqualTo(String value) {
            addCriterion("KIND_ID >=", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdLessThan(String value) {
            addCriterion("KIND_ID <", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdLessThanOrEqualTo(String value) {
            addCriterion("KIND_ID <=", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdLike(String value) {
            addCriterion("KIND_ID like", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotLike(String value) {
            addCriterion("KIND_ID not like", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdIn(List<String> values) {
            addCriterion("KIND_ID in", values, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotIn(List<String> values) {
            addCriterion("KIND_ID not in", values, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdBetween(String value1, String value2) {
            addCriterion("KIND_ID between", value1, value2, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotBetween(String value1, String value2) {
            addCriterion("KIND_ID not between", value1, value2, "kindId");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyIsNull() {
            addCriterion("CO_BRANDED_ACCOUNTMONEY is null");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyIsNotNull() {
            addCriterion("CO_BRANDED_ACCOUNTMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyEqualTo(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY =", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY <>", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyGreaterThan(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY >", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY >=", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyLessThan(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY <", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY <=", value, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyIn(List<BigDecimal> values) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY in", values, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY not in", values, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY between", value1, value2, "coBrandedAccountmoney");
            return (Criteria) this;
        }

        public Criteria andCoBrandedAccountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CO_BRANDED_ACCOUNTMONEY not between", value1, value2, "coBrandedAccountmoney");
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