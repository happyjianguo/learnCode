package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WankeCridetOnAccountBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WankeCridetOnAccountBookExample() {
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

        public Criteria andMerNoIsNull() {
            addCriterion("MER_NO is null");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNotNull() {
            addCriterion("MER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMerNoEqualTo(String value) {
            addCriterion("MER_NO =", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotEqualTo(String value) {
            addCriterion("MER_NO <>", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThan(String value) {
            addCriterion("MER_NO >", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("MER_NO >=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThan(String value) {
            addCriterion("MER_NO <", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThanOrEqualTo(String value) {
            addCriterion("MER_NO <=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLike(String value) {
            addCriterion("MER_NO like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotLike(String value) {
            addCriterion("MER_NO not like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIn(List<String> values) {
            addCriterion("MER_NO in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotIn(List<String> values) {
            addCriterion("MER_NO not in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoBetween(String value1, String value2) {
            addCriterion("MER_NO between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotBetween(String value1, String value2) {
            addCriterion("MER_NO not between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNull() {
            addCriterion("MER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNotNull() {
            addCriterion("MER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerNameEqualTo(String value) {
            addCriterion("MER_NAME =", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotEqualTo(String value) {
            addCriterion("MER_NAME <>", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThan(String value) {
            addCriterion("MER_NAME >", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("MER_NAME >=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThan(String value) {
            addCriterion("MER_NAME <", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThanOrEqualTo(String value) {
            addCriterion("MER_NAME <=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLike(String value) {
            addCriterion("MER_NAME like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotLike(String value) {
            addCriterion("MER_NAME not like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameIn(List<String> values) {
            addCriterion("MER_NAME in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotIn(List<String> values) {
            addCriterion("MER_NAME not in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameBetween(String value1, String value2) {
            addCriterion("MER_NAME between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotBetween(String value1, String value2) {
            addCriterion("MER_NAME not between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andTerNoIsNull() {
            addCriterion("TER_NO is null");
            return (Criteria) this;
        }

        public Criteria andTerNoIsNotNull() {
            addCriterion("TER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTerNoEqualTo(String value) {
            addCriterion("TER_NO =", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotEqualTo(String value) {
            addCriterion("TER_NO <>", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoGreaterThan(String value) {
            addCriterion("TER_NO >", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoGreaterThanOrEqualTo(String value) {
            addCriterion("TER_NO >=", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLessThan(String value) {
            addCriterion("TER_NO <", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLessThanOrEqualTo(String value) {
            addCriterion("TER_NO <=", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoLike(String value) {
            addCriterion("TER_NO like", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotLike(String value) {
            addCriterion("TER_NO not like", value, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoIn(List<String> values) {
            addCriterion("TER_NO in", values, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotIn(List<String> values) {
            addCriterion("TER_NO not in", values, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoBetween(String value1, String value2) {
            addCriterion("TER_NO between", value1, value2, "terNo");
            return (Criteria) this;
        }

        public Criteria andTerNoNotBetween(String value1, String value2) {
            addCriterion("TER_NO not between", value1, value2, "terNo");
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

        public Criteria andCardTypeIsNull() {
            addCriterion("CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(String value) {
            addCriterion("CARD_TYPE =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(String value) {
            addCriterion("CARD_TYPE <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(String value) {
            addCriterion("CARD_TYPE >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(String value) {
            addCriterion("CARD_TYPE <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(String value) {
            addCriterion("CARD_TYPE like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(String value) {
            addCriterion("CARD_TYPE not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<String> values) {
            addCriterion("CARD_TYPE in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<String> values) {
            addCriterion("CARD_TYPE not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(String value1, String value2) {
            addCriterion("CARD_TYPE between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCredetDateIsNull() {
            addCriterion("CREDET_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCredetDateIsNotNull() {
            addCriterion("CREDET_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCredetDateEqualTo(String value) {
            addCriterion("CREDET_DATE =", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateNotEqualTo(String value) {
            addCriterion("CREDET_DATE <>", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateGreaterThan(String value) {
            addCriterion("CREDET_DATE >", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREDET_DATE >=", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateLessThan(String value) {
            addCriterion("CREDET_DATE <", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateLessThanOrEqualTo(String value) {
            addCriterion("CREDET_DATE <=", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateLike(String value) {
            addCriterion("CREDET_DATE like", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateNotLike(String value) {
            addCriterion("CREDET_DATE not like", value, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateIn(List<String> values) {
            addCriterion("CREDET_DATE in", values, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateNotIn(List<String> values) {
            addCriterion("CREDET_DATE not in", values, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateBetween(String value1, String value2) {
            addCriterion("CREDET_DATE between", value1, value2, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetDateNotBetween(String value1, String value2) {
            addCriterion("CREDET_DATE not between", value1, value2, "credetDate");
            return (Criteria) this;
        }

        public Criteria andCredetTimeIsNull() {
            addCriterion("CREDET_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCredetTimeIsNotNull() {
            addCriterion("CREDET_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCredetTimeEqualTo(String value) {
            addCriterion("CREDET_TIME =", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeNotEqualTo(String value) {
            addCriterion("CREDET_TIME <>", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeGreaterThan(String value) {
            addCriterion("CREDET_TIME >", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREDET_TIME >=", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeLessThan(String value) {
            addCriterion("CREDET_TIME <", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeLessThanOrEqualTo(String value) {
            addCriterion("CREDET_TIME <=", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeLike(String value) {
            addCriterion("CREDET_TIME like", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeNotLike(String value) {
            addCriterion("CREDET_TIME not like", value, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeIn(List<String> values) {
            addCriterion("CREDET_TIME in", values, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeNotIn(List<String> values) {
            addCriterion("CREDET_TIME not in", values, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeBetween(String value1, String value2) {
            addCriterion("CREDET_TIME between", value1, value2, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCredetTimeNotBetween(String value1, String value2) {
            addCriterion("CREDET_TIME not between", value1, value2, "credetTime");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIsNull() {
            addCriterion("CREDIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIsNotNull() {
            addCriterion("CREDIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAmtEqualTo(BigDecimal value) {
            addCriterion("CREDIT_AMT =", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotEqualTo(BigDecimal value) {
            addCriterion("CREDIT_AMT <>", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtGreaterThan(BigDecimal value) {
            addCriterion("CREDIT_AMT >", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CREDIT_AMT >=", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtLessThan(BigDecimal value) {
            addCriterion("CREDIT_AMT <", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CREDIT_AMT <=", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIn(List<BigDecimal> values) {
            addCriterion("CREDIT_AMT in", values, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotIn(List<BigDecimal> values) {
            addCriterion("CREDIT_AMT not in", values, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREDIT_AMT between", value1, value2, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREDIT_AMT not between", value1, value2, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtIsNull() {
            addCriterion("REPAY_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRepayAmtIsNotNull() {
            addCriterion("REPAY_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRepayAmtEqualTo(BigDecimal value) {
            addCriterion("REPAY_AMT =", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtNotEqualTo(BigDecimal value) {
            addCriterion("REPAY_AMT <>", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtGreaterThan(BigDecimal value) {
            addCriterion("REPAY_AMT >", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REPAY_AMT >=", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtLessThan(BigDecimal value) {
            addCriterion("REPAY_AMT <", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REPAY_AMT <=", value, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtIn(List<BigDecimal> values) {
            addCriterion("REPAY_AMT in", values, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtNotIn(List<BigDecimal> values) {
            addCriterion("REPAY_AMT not in", values, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REPAY_AMT between", value1, value2, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REPAY_AMT not between", value1, value2, "repayAmt");
            return (Criteria) this;
        }

        public Criteria andRepayDateIsNull() {
            addCriterion("REPAY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRepayDateIsNotNull() {
            addCriterion("REPAY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRepayDateEqualTo(String value) {
            addCriterion("REPAY_DATE =", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateNotEqualTo(String value) {
            addCriterion("REPAY_DATE <>", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateGreaterThan(String value) {
            addCriterion("REPAY_DATE >", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPAY_DATE >=", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateLessThan(String value) {
            addCriterion("REPAY_DATE <", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateLessThanOrEqualTo(String value) {
            addCriterion("REPAY_DATE <=", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateLike(String value) {
            addCriterion("REPAY_DATE like", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateNotLike(String value) {
            addCriterion("REPAY_DATE not like", value, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateIn(List<String> values) {
            addCriterion("REPAY_DATE in", values, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateNotIn(List<String> values) {
            addCriterion("REPAY_DATE not in", values, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateBetween(String value1, String value2) {
            addCriterion("REPAY_DATE between", value1, value2, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayDateNotBetween(String value1, String value2) {
            addCriterion("REPAY_DATE not between", value1, value2, "repayDate");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIsNull() {
            addCriterion("REPAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIsNotNull() {
            addCriterion("REPAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRepayTimeEqualTo(String value) {
            addCriterion("REPAY_TIME =", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotEqualTo(String value) {
            addCriterion("REPAY_TIME <>", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeGreaterThan(String value) {
            addCriterion("REPAY_TIME >", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPAY_TIME >=", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeLessThan(String value) {
            addCriterion("REPAY_TIME <", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeLessThanOrEqualTo(String value) {
            addCriterion("REPAY_TIME <=", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeLike(String value) {
            addCriterion("REPAY_TIME like", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotLike(String value) {
            addCriterion("REPAY_TIME not like", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIn(List<String> values) {
            addCriterion("REPAY_TIME in", values, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotIn(List<String> values) {
            addCriterion("REPAY_TIME not in", values, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeBetween(String value1, String value2) {
            addCriterion("REPAY_TIME between", value1, value2, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotBetween(String value1, String value2) {
            addCriterion("REPAY_TIME not between", value1, value2, "repayTime");
            return (Criteria) this;
        }

        public Criteria andDebtAmtIsNull() {
            addCriterion("DEBT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andDebtAmtIsNotNull() {
            addCriterion("DEBT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andDebtAmtEqualTo(BigDecimal value) {
            addCriterion("DEBT_AMT =", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtNotEqualTo(BigDecimal value) {
            addCriterion("DEBT_AMT <>", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtGreaterThan(BigDecimal value) {
            addCriterion("DEBT_AMT >", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DEBT_AMT >=", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtLessThan(BigDecimal value) {
            addCriterion("DEBT_AMT <", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DEBT_AMT <=", value, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtIn(List<BigDecimal> values) {
            addCriterion("DEBT_AMT in", values, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtNotIn(List<BigDecimal> values) {
            addCriterion("DEBT_AMT not in", values, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEBT_AMT between", value1, value2, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andDebtAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEBT_AMT not between", value1, value2, "debtAmt");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidIsNull() {
            addCriterion("IC_SALE_CODEID is null");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidIsNotNull() {
            addCriterion("IC_SALE_CODEID is not null");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidEqualTo(String value) {
            addCriterion("IC_SALE_CODEID =", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidNotEqualTo(String value) {
            addCriterion("IC_SALE_CODEID <>", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidGreaterThan(String value) {
            addCriterion("IC_SALE_CODEID >", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidGreaterThanOrEqualTo(String value) {
            addCriterion("IC_SALE_CODEID >=", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidLessThan(String value) {
            addCriterion("IC_SALE_CODEID <", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidLessThanOrEqualTo(String value) {
            addCriterion("IC_SALE_CODEID <=", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidLike(String value) {
            addCriterion("IC_SALE_CODEID like", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidNotLike(String value) {
            addCriterion("IC_SALE_CODEID not like", value, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidIn(List<String> values) {
            addCriterion("IC_SALE_CODEID in", values, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidNotIn(List<String> values) {
            addCriterion("IC_SALE_CODEID not in", values, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidBetween(String value1, String value2) {
            addCriterion("IC_SALE_CODEID between", value1, value2, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeidNotBetween(String value1, String value2) {
            addCriterion("IC_SALE_CODEID not between", value1, value2, "icSaleCodeid");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeIsNull() {
            addCriterion("IC_SALE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeIsNotNull() {
            addCriterion("IC_SALE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeEqualTo(String value) {
            addCriterion("IC_SALE_CODE =", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeNotEqualTo(String value) {
            addCriterion("IC_SALE_CODE <>", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeGreaterThan(String value) {
            addCriterion("IC_SALE_CODE >", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("IC_SALE_CODE >=", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeLessThan(String value) {
            addCriterion("IC_SALE_CODE <", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeLessThanOrEqualTo(String value) {
            addCriterion("IC_SALE_CODE <=", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeLike(String value) {
            addCriterion("IC_SALE_CODE like", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeNotLike(String value) {
            addCriterion("IC_SALE_CODE not like", value, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeIn(List<String> values) {
            addCriterion("IC_SALE_CODE in", values, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeNotIn(List<String> values) {
            addCriterion("IC_SALE_CODE not in", values, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeBetween(String value1, String value2) {
            addCriterion("IC_SALE_CODE between", value1, value2, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleCodeNotBetween(String value1, String value2) {
            addCriterion("IC_SALE_CODE not between", value1, value2, "icSaleCode");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameIsNull() {
            addCriterion("IC_SALE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameIsNotNull() {
            addCriterion("IC_SALE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameEqualTo(String value) {
            addCriterion("IC_SALE_NAME =", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameNotEqualTo(String value) {
            addCriterion("IC_SALE_NAME <>", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameGreaterThan(String value) {
            addCriterion("IC_SALE_NAME >", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameGreaterThanOrEqualTo(String value) {
            addCriterion("IC_SALE_NAME >=", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameLessThan(String value) {
            addCriterion("IC_SALE_NAME <", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameLessThanOrEqualTo(String value) {
            addCriterion("IC_SALE_NAME <=", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameLike(String value) {
            addCriterion("IC_SALE_NAME like", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameNotLike(String value) {
            addCriterion("IC_SALE_NAME not like", value, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameIn(List<String> values) {
            addCriterion("IC_SALE_NAME in", values, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameNotIn(List<String> values) {
            addCriterion("IC_SALE_NAME not in", values, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameBetween(String value1, String value2) {
            addCriterion("IC_SALE_NAME between", value1, value2, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcSaleNameNotBetween(String value1, String value2) {
            addCriterion("IC_SALE_NAME not between", value1, value2, "icSaleName");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidIsNull() {
            addCriterion("IC_ITEM_CODEID is null");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidIsNotNull() {
            addCriterion("IC_ITEM_CODEID is not null");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidEqualTo(String value) {
            addCriterion("IC_ITEM_CODEID =", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidNotEqualTo(String value) {
            addCriterion("IC_ITEM_CODEID <>", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidGreaterThan(String value) {
            addCriterion("IC_ITEM_CODEID >", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidGreaterThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_CODEID >=", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidLessThan(String value) {
            addCriterion("IC_ITEM_CODEID <", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidLessThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_CODEID <=", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidLike(String value) {
            addCriterion("IC_ITEM_CODEID like", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidNotLike(String value) {
            addCriterion("IC_ITEM_CODEID not like", value, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidIn(List<String> values) {
            addCriterion("IC_ITEM_CODEID in", values, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidNotIn(List<String> values) {
            addCriterion("IC_ITEM_CODEID not in", values, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidBetween(String value1, String value2) {
            addCriterion("IC_ITEM_CODEID between", value1, value2, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeidNotBetween(String value1, String value2) {
            addCriterion("IC_ITEM_CODEID not between", value1, value2, "icItemCodeid");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeIsNull() {
            addCriterion("IC_ITEM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeIsNotNull() {
            addCriterion("IC_ITEM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeEqualTo(String value) {
            addCriterion("IC_ITEM_CODE =", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeNotEqualTo(String value) {
            addCriterion("IC_ITEM_CODE <>", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeGreaterThan(String value) {
            addCriterion("IC_ITEM_CODE >", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_CODE >=", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeLessThan(String value) {
            addCriterion("IC_ITEM_CODE <", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeLessThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_CODE <=", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeLike(String value) {
            addCriterion("IC_ITEM_CODE like", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeNotLike(String value) {
            addCriterion("IC_ITEM_CODE not like", value, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeIn(List<String> values) {
            addCriterion("IC_ITEM_CODE in", values, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeNotIn(List<String> values) {
            addCriterion("IC_ITEM_CODE not in", values, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeBetween(String value1, String value2) {
            addCriterion("IC_ITEM_CODE between", value1, value2, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemCodeNotBetween(String value1, String value2) {
            addCriterion("IC_ITEM_CODE not between", value1, value2, "icItemCode");
            return (Criteria) this;
        }

        public Criteria andIcItemNameIsNull() {
            addCriterion("IC_ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIcItemNameIsNotNull() {
            addCriterion("IC_ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIcItemNameEqualTo(String value) {
            addCriterion("IC_ITEM_NAME =", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameNotEqualTo(String value) {
            addCriterion("IC_ITEM_NAME <>", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameGreaterThan(String value) {
            addCriterion("IC_ITEM_NAME >", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_NAME >=", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameLessThan(String value) {
            addCriterion("IC_ITEM_NAME <", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameLessThanOrEqualTo(String value) {
            addCriterion("IC_ITEM_NAME <=", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameLike(String value) {
            addCriterion("IC_ITEM_NAME like", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameNotLike(String value) {
            addCriterion("IC_ITEM_NAME not like", value, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameIn(List<String> values) {
            addCriterion("IC_ITEM_NAME in", values, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameNotIn(List<String> values) {
            addCriterion("IC_ITEM_NAME not in", values, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameBetween(String value1, String value2) {
            addCriterion("IC_ITEM_NAME between", value1, value2, "icItemName");
            return (Criteria) this;
        }

        public Criteria andIcItemNameNotBetween(String value1, String value2) {
            addCriterion("IC_ITEM_NAME not between", value1, value2, "icItemName");
            return (Criteria) this;
        }

        
        public Criteria andCardOwnerIsNull() {
            addCriterion("CARD_OWNER is null");
            return (Criteria) this;
        }

        public Criteria andCardOwnerIsNotNull() {
            addCriterion("CARD_OWNER is not null");
            return (Criteria) this;
        }

        public Criteria andCardOwnerEqualTo(String value) {
            addCriterion("CARD_OWNER =", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerNotEqualTo(String value) {
            addCriterion("CARD_OWNER <>", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerGreaterThan(String value) {
            addCriterion("CARD_OWNER >", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_OWNER >=", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerLessThan(String value) {
            addCriterion("CARD_OWNER <", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerLessThanOrEqualTo(String value) {
            addCriterion("CARD_OWNER <=", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerLike(String value) {
            addCriterion("CARD_OWNER like", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerNotLike(String value) {
            addCriterion("CARD_OWNER not like", value, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerIn(List<String> values) {
            addCriterion("CARD_OWNER in", values, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerNotIn(List<String> values) {
            addCriterion("CARD_OWNER not in", values, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerBetween(String value1, String value2) {
            addCriterion("CARD_OWNER between", value1, value2, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andCardOwnerNotBetween(String value1, String value2) {
            addCriterion("CARD_OWNER not between", value1, value2, "cardOwner");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNull() {
            addCriterion("TELPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNotNull() {
            addCriterion("TELPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelphoneEqualTo(String value) {
            addCriterion("TELPHONE =", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotEqualTo(String value) {
            addCriterion("TELPHONE <>", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThan(String value) {
            addCriterion("TELPHONE >", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELPHONE >=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThan(String value) {
            addCriterion("TELPHONE <", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThanOrEqualTo(String value) {
            addCriterion("TELPHONE <=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLike(String value) {
            addCriterion("TELPHONE like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotLike(String value) {
            addCriterion("TELPHONE not like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneIn(List<String> values) {
            addCriterion("TELPHONE in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotIn(List<String> values) {
            addCriterion("TELPHONE not in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneBetween(String value1, String value2) {
            addCriterion("TELPHONE between", value1, value2, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotBetween(String value1, String value2) {
            addCriterion("TELPHONE not between", value1, value2, "telphone");
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