package cn.yufu.bak.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatDailyAccdeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StatDailyAccdeExample() {
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

        public Criteria andDailyDateIsNull() {
            addCriterion("DAILY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDailyDateIsNotNull() {
            addCriterion("DAILY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDailyDateEqualTo(String value) {
            addCriterion("DAILY_DATE =", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotEqualTo(String value) {
            addCriterion("DAILY_DATE <>", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThan(String value) {
            addCriterion("DAILY_DATE >", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThanOrEqualTo(String value) {
            addCriterion("DAILY_DATE >=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThan(String value) {
            addCriterion("DAILY_DATE <", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThanOrEqualTo(String value) {
            addCriterion("DAILY_DATE <=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLike(String value) {
            addCriterion("DAILY_DATE like", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotLike(String value) {
            addCriterion("DAILY_DATE not like", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateIn(List<String> values) {
            addCriterion("DAILY_DATE in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotIn(List<String> values) {
            addCriterion("DAILY_DATE not in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateBetween(String value1, String value2) {
            addCriterion("DAILY_DATE between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotBetween(String value1, String value2) {
            addCriterion("DAILY_DATE not between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andGenDtIsNull() {
            addCriterion("GEN_DT is null");
            return (Criteria) this;
        }

        public Criteria andGenDtIsNotNull() {
            addCriterion("GEN_DT is not null");
            return (Criteria) this;
        }

        public Criteria andGenDtEqualTo(String value) {
            addCriterion("GEN_DT =", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotEqualTo(String value) {
            addCriterion("GEN_DT <>", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtGreaterThan(String value) {
            addCriterion("GEN_DT >", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtGreaterThanOrEqualTo(String value) {
            addCriterion("GEN_DT >=", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLessThan(String value) {
            addCriterion("GEN_DT <", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLessThanOrEqualTo(String value) {
            addCriterion("GEN_DT <=", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtLike(String value) {
            addCriterion("GEN_DT like", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotLike(String value) {
            addCriterion("GEN_DT not like", value, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtIn(List<String> values) {
            addCriterion("GEN_DT in", values, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotIn(List<String> values) {
            addCriterion("GEN_DT not in", values, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtBetween(String value1, String value2) {
            addCriterion("GEN_DT between", value1, value2, "genDt");
            return (Criteria) this;
        }

        public Criteria andGenDtNotBetween(String value1, String value2) {
            addCriterion("GEN_DT not between", value1, value2, "genDt");
            return (Criteria) this;
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

        public Criteria andCardStatusDescIsNull() {
            addCriterion("CARD_STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescIsNotNull() {
            addCriterion("CARD_STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescEqualTo(String value) {
            addCriterion("CARD_STATUS_DESC =", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescNotEqualTo(String value) {
            addCriterion("CARD_STATUS_DESC <>", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescGreaterThan(String value) {
            addCriterion("CARD_STATUS_DESC >", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS_DESC >=", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescLessThan(String value) {
            addCriterion("CARD_STATUS_DESC <", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescLessThanOrEqualTo(String value) {
            addCriterion("CARD_STATUS_DESC <=", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescLike(String value) {
            addCriterion("CARD_STATUS_DESC like", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescNotLike(String value) {
            addCriterion("CARD_STATUS_DESC not like", value, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescIn(List<String> values) {
            addCriterion("CARD_STATUS_DESC in", values, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescNotIn(List<String> values) {
            addCriterion("CARD_STATUS_DESC not in", values, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescBetween(String value1, String value2) {
            addCriterion("CARD_STATUS_DESC between", value1, value2, "cardStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCardStatusDescNotBetween(String value1, String value2) {
            addCriterion("CARD_STATUS_DESC not between", value1, value2, "cardStatusDesc");
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

        public Criteria andAcc01IsNull() {
            addCriterion("ACC01 is null");
            return (Criteria) this;
        }

        public Criteria andAcc01IsNotNull() {
            addCriterion("ACC01 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc01EqualTo(BigDecimal value) {
            addCriterion("ACC01 =", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01NotEqualTo(BigDecimal value) {
            addCriterion("ACC01 <>", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01GreaterThan(BigDecimal value) {
            addCriterion("ACC01 >", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC01 >=", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01LessThan(BigDecimal value) {
            addCriterion("ACC01 <", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC01 <=", value, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01In(List<BigDecimal> values) {
            addCriterion("ACC01 in", values, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01NotIn(List<BigDecimal> values) {
            addCriterion("ACC01 not in", values, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC01 between", value1, value2, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc01NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC01 not between", value1, value2, "acc01");
            return (Criteria) this;
        }

        public Criteria andAcc02IsNull() {
            addCriterion("ACC02 is null");
            return (Criteria) this;
        }

        public Criteria andAcc02IsNotNull() {
            addCriterion("ACC02 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc02EqualTo(BigDecimal value) {
            addCriterion("ACC02 =", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02NotEqualTo(BigDecimal value) {
            addCriterion("ACC02 <>", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02GreaterThan(BigDecimal value) {
            addCriterion("ACC02 >", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC02 >=", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02LessThan(BigDecimal value) {
            addCriterion("ACC02 <", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC02 <=", value, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02In(List<BigDecimal> values) {
            addCriterion("ACC02 in", values, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02NotIn(List<BigDecimal> values) {
            addCriterion("ACC02 not in", values, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC02 between", value1, value2, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc02NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC02 not between", value1, value2, "acc02");
            return (Criteria) this;
        }

        public Criteria andAcc03IsNull() {
            addCriterion("ACC03 is null");
            return (Criteria) this;
        }

        public Criteria andAcc03IsNotNull() {
            addCriterion("ACC03 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc03EqualTo(BigDecimal value) {
            addCriterion("ACC03 =", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03NotEqualTo(BigDecimal value) {
            addCriterion("ACC03 <>", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03GreaterThan(BigDecimal value) {
            addCriterion("ACC03 >", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC03 >=", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03LessThan(BigDecimal value) {
            addCriterion("ACC03 <", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC03 <=", value, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03In(List<BigDecimal> values) {
            addCriterion("ACC03 in", values, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03NotIn(List<BigDecimal> values) {
            addCriterion("ACC03 not in", values, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC03 between", value1, value2, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc03NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC03 not between", value1, value2, "acc03");
            return (Criteria) this;
        }

        public Criteria andAcc04IsNull() {
            addCriterion("ACC04 is null");
            return (Criteria) this;
        }

        public Criteria andAcc04IsNotNull() {
            addCriterion("ACC04 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc04EqualTo(BigDecimal value) {
            addCriterion("ACC04 =", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04NotEqualTo(BigDecimal value) {
            addCriterion("ACC04 <>", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04GreaterThan(BigDecimal value) {
            addCriterion("ACC04 >", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC04 >=", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04LessThan(BigDecimal value) {
            addCriterion("ACC04 <", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC04 <=", value, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04In(List<BigDecimal> values) {
            addCriterion("ACC04 in", values, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04NotIn(List<BigDecimal> values) {
            addCriterion("ACC04 not in", values, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC04 between", value1, value2, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc04NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC04 not between", value1, value2, "acc04");
            return (Criteria) this;
        }

        public Criteria andAcc05IsNull() {
            addCriterion("ACC05 is null");
            return (Criteria) this;
        }

        public Criteria andAcc05IsNotNull() {
            addCriterion("ACC05 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc05EqualTo(BigDecimal value) {
            addCriterion("ACC05 =", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05NotEqualTo(BigDecimal value) {
            addCriterion("ACC05 <>", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05GreaterThan(BigDecimal value) {
            addCriterion("ACC05 >", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC05 >=", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05LessThan(BigDecimal value) {
            addCriterion("ACC05 <", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC05 <=", value, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05In(List<BigDecimal> values) {
            addCriterion("ACC05 in", values, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05NotIn(List<BigDecimal> values) {
            addCriterion("ACC05 not in", values, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC05 between", value1, value2, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc05NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC05 not between", value1, value2, "acc05");
            return (Criteria) this;
        }

        public Criteria andAcc06IsNull() {
            addCriterion("ACC06 is null");
            return (Criteria) this;
        }

        public Criteria andAcc06IsNotNull() {
            addCriterion("ACC06 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc06EqualTo(BigDecimal value) {
            addCriterion("ACC06 =", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06NotEqualTo(BigDecimal value) {
            addCriterion("ACC06 <>", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06GreaterThan(BigDecimal value) {
            addCriterion("ACC06 >", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC06 >=", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06LessThan(BigDecimal value) {
            addCriterion("ACC06 <", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC06 <=", value, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06In(List<BigDecimal> values) {
            addCriterion("ACC06 in", values, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06NotIn(List<BigDecimal> values) {
            addCriterion("ACC06 not in", values, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC06 between", value1, value2, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc06NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC06 not between", value1, value2, "acc06");
            return (Criteria) this;
        }

        public Criteria andAcc07IsNull() {
            addCriterion("ACC07 is null");
            return (Criteria) this;
        }

        public Criteria andAcc07IsNotNull() {
            addCriterion("ACC07 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc07EqualTo(BigDecimal value) {
            addCriterion("ACC07 =", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07NotEqualTo(BigDecimal value) {
            addCriterion("ACC07 <>", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07GreaterThan(BigDecimal value) {
            addCriterion("ACC07 >", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC07 >=", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07LessThan(BigDecimal value) {
            addCriterion("ACC07 <", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC07 <=", value, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07In(List<BigDecimal> values) {
            addCriterion("ACC07 in", values, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07NotIn(List<BigDecimal> values) {
            addCriterion("ACC07 not in", values, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC07 between", value1, value2, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc07NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC07 not between", value1, value2, "acc07");
            return (Criteria) this;
        }

        public Criteria andAcc08IsNull() {
            addCriterion("ACC08 is null");
            return (Criteria) this;
        }

        public Criteria andAcc08IsNotNull() {
            addCriterion("ACC08 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc08EqualTo(BigDecimal value) {
            addCriterion("ACC08 =", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08NotEqualTo(BigDecimal value) {
            addCriterion("ACC08 <>", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08GreaterThan(BigDecimal value) {
            addCriterion("ACC08 >", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC08 >=", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08LessThan(BigDecimal value) {
            addCriterion("ACC08 <", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC08 <=", value, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08In(List<BigDecimal> values) {
            addCriterion("ACC08 in", values, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08NotIn(List<BigDecimal> values) {
            addCriterion("ACC08 not in", values, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC08 between", value1, value2, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc08NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC08 not between", value1, value2, "acc08");
            return (Criteria) this;
        }

        public Criteria andAcc09IsNull() {
            addCriterion("ACC09 is null");
            return (Criteria) this;
        }

        public Criteria andAcc09IsNotNull() {
            addCriterion("ACC09 is not null");
            return (Criteria) this;
        }

        public Criteria andAcc09EqualTo(BigDecimal value) {
            addCriterion("ACC09 =", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09NotEqualTo(BigDecimal value) {
            addCriterion("ACC09 <>", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09GreaterThan(BigDecimal value) {
            addCriterion("ACC09 >", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC09 >=", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09LessThan(BigDecimal value) {
            addCriterion("ACC09 <", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09LessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC09 <=", value, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09In(List<BigDecimal> values) {
            addCriterion("ACC09 in", values, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09NotIn(List<BigDecimal> values) {
            addCriterion("ACC09 not in", values, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC09 between", value1, value2, "acc09");
            return (Criteria) this;
        }

        public Criteria andAcc09NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC09 not between", value1, value2, "acc09");
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

        public Criteria andAbFlagIsNull() {
            addCriterion("AB_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAbFlagIsNotNull() {
            addCriterion("AB_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAbFlagEqualTo(String value) {
            addCriterion("AB_FLAG =", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagNotEqualTo(String value) {
            addCriterion("AB_FLAG <>", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagGreaterThan(String value) {
            addCriterion("AB_FLAG >", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AB_FLAG >=", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagLessThan(String value) {
            addCriterion("AB_FLAG <", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagLessThanOrEqualTo(String value) {
            addCriterion("AB_FLAG <=", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagLike(String value) {
            addCriterion("AB_FLAG like", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagNotLike(String value) {
            addCriterion("AB_FLAG not like", value, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagIn(List<String> values) {
            addCriterion("AB_FLAG in", values, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagNotIn(List<String> values) {
            addCriterion("AB_FLAG not in", values, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagBetween(String value1, String value2) {
            addCriterion("AB_FLAG between", value1, value2, "abFlag");
            return (Criteria) this;
        }

        public Criteria andAbFlagNotBetween(String value1, String value2) {
            addCriterion("AB_FLAG not between", value1, value2, "abFlag");
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