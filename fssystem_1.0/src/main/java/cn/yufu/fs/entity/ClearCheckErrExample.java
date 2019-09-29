package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearCheckErrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearCheckErrExample() {
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(String value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(String value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(String value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(String value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLike(String value) {
            addCriterion("CHECK_TIME like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotLike(String value) {
            addCriterion("CHECK_TIME not like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<String> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<String> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(String value1, String value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(String value1, String value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andGenTypeIsNull() {
            addCriterion("GEN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGenTypeIsNotNull() {
            addCriterion("GEN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGenTypeEqualTo(String value) {
            addCriterion("GEN_TYPE =", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeNotEqualTo(String value) {
            addCriterion("GEN_TYPE <>", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeGreaterThan(String value) {
            addCriterion("GEN_TYPE >", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GEN_TYPE >=", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeLessThan(String value) {
            addCriterion("GEN_TYPE <", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeLessThanOrEqualTo(String value) {
            addCriterion("GEN_TYPE <=", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeLike(String value) {
            addCriterion("GEN_TYPE like", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeNotLike(String value) {
            addCriterion("GEN_TYPE not like", value, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeIn(List<String> values) {
            addCriterion("GEN_TYPE in", values, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeNotIn(List<String> values) {
            addCriterion("GEN_TYPE not in", values, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeBetween(String value1, String value2) {
            addCriterion("GEN_TYPE between", value1, value2, "genType");
            return (Criteria) this;
        }

        public Criteria andGenTypeNotBetween(String value1, String value2) {
            addCriterion("GEN_TYPE not between", value1, value2, "genType");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
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

        public Criteria andTermNoIsNull() {
            addCriterion("TERM_NO is null");
            return (Criteria) this;
        }

        public Criteria andTermNoIsNotNull() {
            addCriterion("TERM_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTermNoEqualTo(String value) {
            addCriterion("TERM_NO =", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoNotEqualTo(String value) {
            addCriterion("TERM_NO <>", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoGreaterThan(String value) {
            addCriterion("TERM_NO >", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_NO >=", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoLessThan(String value) {
            addCriterion("TERM_NO <", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoLessThanOrEqualTo(String value) {
            addCriterion("TERM_NO <=", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoLike(String value) {
            addCriterion("TERM_NO like", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoNotLike(String value) {
            addCriterion("TERM_NO not like", value, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoIn(List<String> values) {
            addCriterion("TERM_NO in", values, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoNotIn(List<String> values) {
            addCriterion("TERM_NO not in", values, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoBetween(String value1, String value2) {
            addCriterion("TERM_NO between", value1, value2, "termNo");
            return (Criteria) this;
        }

        public Criteria andTermNoNotBetween(String value1, String value2) {
            addCriterion("TERM_NO not between", value1, value2, "termNo");
            return (Criteria) this;
        }

        public Criteria andTranDateIsNull() {
            addCriterion("TRAN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTranDateIsNotNull() {
            addCriterion("TRAN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTranDateEqualTo(String value) {
            addCriterion("TRAN_DATE =", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateNotEqualTo(String value) {
            addCriterion("TRAN_DATE <>", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateGreaterThan(String value) {
            addCriterion("TRAN_DATE >", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_DATE >=", value, "tranDate");
            return (Criteria) this;
        }
        //添加时间段查询条件
        public Criteria andTranDtGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_DATE||TRAN_TIME >=", value, "startDt");
            return (Criteria) this;
        }       
        
        public Criteria andTranDtLessThanOrEqualTo(String value) {
            addCriterion("TRAN_DATE||TRAN_TIME <=", value, "endDt");
            return (Criteria) this;
        }
        public Criteria andTranDateLessThan(String value) {
            addCriterion("TRAN_DATE <", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateLessThanOrEqualTo(String value) {
            addCriterion("TRAN_DATE <=", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateLike(String value) {
            addCriterion("TRAN_DATE like", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateNotLike(String value) {
            addCriterion("TRAN_DATE not like", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateIn(List<String> values) {
            addCriterion("TRAN_DATE in", values, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateNotIn(List<String> values) {
            addCriterion("TRAN_DATE not in", values, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateBetween(String value1, String value2) {
            addCriterion("TRAN_DATE between", value1, value2, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranDateNotBetween(String value1, String value2) {
            addCriterion("TRAN_DATE not between", value1, value2, "tranDate");
            return (Criteria) this;
        }

        public Criteria andTranTimeIsNull() {
            addCriterion("TRAN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTranTimeIsNotNull() {
            addCriterion("TRAN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTranTimeEqualTo(String value) {
            addCriterion("TRAN_TIME =", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeNotEqualTo(String value) {
            addCriterion("TRAN_TIME <>", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeGreaterThan(String value) {
            addCriterion("TRAN_TIME >", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_TIME >=", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeLessThan(String value) {
            addCriterion("TRAN_TIME <", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeLessThanOrEqualTo(String value) {
            addCriterion("TRAN_TIME <=", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeLike(String value) {
            addCriterion("TRAN_TIME like", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeNotLike(String value) {
            addCriterion("TRAN_TIME not like", value, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeIn(List<String> values) {
            addCriterion("TRAN_TIME in", values, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeNotIn(List<String> values) {
            addCriterion("TRAN_TIME not in", values, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeBetween(String value1, String value2) {
            addCriterion("TRAN_TIME between", value1, value2, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTimeNotBetween(String value1, String value2) {
            addCriterion("TRAN_TIME not between", value1, value2, "tranTime");
            return (Criteria) this;
        }

        public Criteria andTranTypeIsNull() {
            addCriterion("TRAN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTranTypeIsNotNull() {
            addCriterion("TRAN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTranTypeEqualTo(String value) {
            addCriterion("TRAN_TYPE =", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeNotEqualTo(String value) {
            addCriterion("TRAN_TYPE <>", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeGreaterThan(String value) {
            addCriterion("TRAN_TYPE >", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_TYPE >=", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeLessThan(String value) {
            addCriterion("TRAN_TYPE <", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeLessThanOrEqualTo(String value) {
            addCriterion("TRAN_TYPE <=", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeLike(String value) {
            addCriterion("TRAN_TYPE like", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeNotLike(String value) {
            addCriterion("TRAN_TYPE not like", value, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeIn(List<String> values) {
            addCriterion("TRAN_TYPE in", values, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeNotIn(List<String> values) {
            addCriterion("TRAN_TYPE not in", values, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeBetween(String value1, String value2) {
            addCriterion("TRAN_TYPE between", value1, value2, "tranType");
            return (Criteria) this;
        }

        public Criteria andTranTypeNotBetween(String value1, String value2) {
            addCriterion("TRAN_TYPE not between", value1, value2, "tranType");
            return (Criteria) this;
        }

        public Criteria andCorebatNoIsNull() {
            addCriterion("COREBAT_NO is null");
            return (Criteria) this;
        }

        public Criteria andCorebatNoIsNotNull() {
            addCriterion("COREBAT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCorebatNoEqualTo(BigDecimal value) {
            addCriterion("COREBAT_NO =", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoNotEqualTo(BigDecimal value) {
            addCriterion("COREBAT_NO <>", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoGreaterThan(BigDecimal value) {
            addCriterion("COREBAT_NO >", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COREBAT_NO >=", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoLessThan(BigDecimal value) {
            addCriterion("COREBAT_NO <", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COREBAT_NO <=", value, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoIn(List<BigDecimal> values) {
            addCriterion("COREBAT_NO in", values, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoNotIn(List<BigDecimal> values) {
            addCriterion("COREBAT_NO not in", values, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COREBAT_NO between", value1, value2, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorebatNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COREBAT_NO not between", value1, value2, "corebatNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoIsNull() {
            addCriterion("COREJONL_NO is null");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoIsNotNull() {
            addCriterion("COREJONL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoEqualTo(BigDecimal value) {
            addCriterion("COREJONL_NO =", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoNotEqualTo(BigDecimal value) {
            addCriterion("COREJONL_NO <>", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoGreaterThan(BigDecimal value) {
            addCriterion("COREJONL_NO >", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COREJONL_NO >=", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoLessThan(BigDecimal value) {
            addCriterion("COREJONL_NO <", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COREJONL_NO <=", value, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoIn(List<BigDecimal> values) {
            addCriterion("COREJONL_NO in", values, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoNotIn(List<BigDecimal> values) {
            addCriterion("COREJONL_NO not in", values, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COREJONL_NO between", value1, value2, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCorejonlNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COREJONL_NO not between", value1, value2, "corejonlNo");
            return (Criteria) this;
        }

        public Criteria andCoreRrnIsNull() {
            addCriterion("CORE_RRN is null");
            return (Criteria) this;
        }

        public Criteria andCoreRrnIsNotNull() {
            addCriterion("CORE_RRN is not null");
            return (Criteria) this;
        }

        public Criteria andCoreRrnEqualTo(String value) {
            addCriterion("CORE_RRN =", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnNotEqualTo(String value) {
            addCriterion("CORE_RRN <>", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnGreaterThan(String value) {
            addCriterion("CORE_RRN >", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnGreaterThanOrEqualTo(String value) {
            addCriterion("CORE_RRN >=", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnLessThan(String value) {
            addCriterion("CORE_RRN <", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnLessThanOrEqualTo(String value) {
            addCriterion("CORE_RRN <=", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnLike(String value) {
            addCriterion("CORE_RRN like", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnNotLike(String value) {
            addCriterion("CORE_RRN not like", value, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnIn(List<String> values) {
            addCriterion("CORE_RRN in", values, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnNotIn(List<String> values) {
            addCriterion("CORE_RRN not in", values, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnBetween(String value1, String value2) {
            addCriterion("CORE_RRN between", value1, value2, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoreRrnNotBetween(String value1, String value2) {
            addCriterion("CORE_RRN not between", value1, value2, "coreRrn");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtIsNull() {
            addCriterion("CORETRAN_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtIsNotNull() {
            addCriterion("CORETRAN_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtEqualTo(BigDecimal value) {
            addCriterion("CORETRAN_AMT =", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtNotEqualTo(BigDecimal value) {
            addCriterion("CORETRAN_AMT <>", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtGreaterThan(BigDecimal value) {
            addCriterion("CORETRAN_AMT >", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CORETRAN_AMT >=", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtLessThan(BigDecimal value) {
            addCriterion("CORETRAN_AMT <", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CORETRAN_AMT <=", value, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtIn(List<BigDecimal> values) {
            addCriterion("CORETRAN_AMT in", values, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtNotIn(List<BigDecimal> values) {
            addCriterion("CORETRAN_AMT not in", values, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CORETRAN_AMT between", value1, value2, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andCoretranAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CORETRAN_AMT not between", value1, value2, "coretranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoIsNull() {
            addCriterion("ACQBAT_NO is null");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoIsNotNull() {
            addCriterion("ACQBAT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoEqualTo(BigDecimal value) {
            addCriterion("ACQBAT_NO =", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoNotEqualTo(BigDecimal value) {
            addCriterion("ACQBAT_NO <>", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoGreaterThan(BigDecimal value) {
            addCriterion("ACQBAT_NO >", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQBAT_NO >=", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoLessThan(BigDecimal value) {
            addCriterion("ACQBAT_NO <", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQBAT_NO <=", value, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoIn(List<BigDecimal> values) {
            addCriterion("ACQBAT_NO in", values, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoNotIn(List<BigDecimal> values) {
            addCriterion("ACQBAT_NO not in", values, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQBAT_NO between", value1, value2, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqbatNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQBAT_NO not between", value1, value2, "acqbatNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoIsNull() {
            addCriterion("ACQJONL_NO is null");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoIsNotNull() {
            addCriterion("ACQJONL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoEqualTo(BigDecimal value) {
            addCriterion("ACQJONL_NO =", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoNotEqualTo(BigDecimal value) {
            addCriterion("ACQJONL_NO <>", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoGreaterThan(BigDecimal value) {
            addCriterion("ACQJONL_NO >", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQJONL_NO >=", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoLessThan(BigDecimal value) {
            addCriterion("ACQJONL_NO <", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQJONL_NO <=", value, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoIn(List<BigDecimal> values) {
            addCriterion("ACQJONL_NO in", values, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoNotIn(List<BigDecimal> values) {
            addCriterion("ACQJONL_NO not in", values, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQJONL_NO between", value1, value2, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqjonlNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQJONL_NO not between", value1, value2, "acqjonlNo");
            return (Criteria) this;
        }

        public Criteria andAcqRrnIsNull() {
            addCriterion("ACQ_RRN is null");
            return (Criteria) this;
        }

        public Criteria andAcqRrnIsNotNull() {
            addCriterion("ACQ_RRN is not null");
            return (Criteria) this;
        }

        public Criteria andAcqRrnEqualTo(String value) {
            addCriterion("ACQ_RRN =", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnNotEqualTo(String value) {
            addCriterion("ACQ_RRN <>", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnGreaterThan(String value) {
            addCriterion("ACQ_RRN >", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnGreaterThanOrEqualTo(String value) {
            addCriterion("ACQ_RRN >=", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnLessThan(String value) {
            addCriterion("ACQ_RRN <", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnLessThanOrEqualTo(String value) {
            addCriterion("ACQ_RRN <=", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnLike(String value) {
            addCriterion("ACQ_RRN like", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnNotLike(String value) {
            addCriterion("ACQ_RRN not like", value, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnIn(List<String> values) {
            addCriterion("ACQ_RRN in", values, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnNotIn(List<String> values) {
            addCriterion("ACQ_RRN not in", values, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnBetween(String value1, String value2) {
            addCriterion("ACQ_RRN between", value1, value2, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqRrnNotBetween(String value1, String value2) {
            addCriterion("ACQ_RRN not between", value1, value2, "acqRrn");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtIsNull() {
            addCriterion("ACQTRAN_AMT is null");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtIsNotNull() {
            addCriterion("ACQTRAN_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtEqualTo(BigDecimal value) {
            addCriterion("ACQTRAN_AMT =", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtNotEqualTo(BigDecimal value) {
            addCriterion("ACQTRAN_AMT <>", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtGreaterThan(BigDecimal value) {
            addCriterion("ACQTRAN_AMT >", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQTRAN_AMT >=", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtLessThan(BigDecimal value) {
            addCriterion("ACQTRAN_AMT <", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACQTRAN_AMT <=", value, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtIn(List<BigDecimal> values) {
            addCriterion("ACQTRAN_AMT in", values, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtNotIn(List<BigDecimal> values) {
            addCriterion("ACQTRAN_AMT not in", values, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQTRAN_AMT between", value1, value2, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andAcqtranAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACQTRAN_AMT not between", value1, value2, "acqtranAmt");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("FEE is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("FEE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("FEE =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("FEE <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("FEE >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("FEE <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("FEE in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("FEE not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andChkFlagIsNull() {
            addCriterion("CHK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andChkFlagIsNotNull() {
            addCriterion("CHK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andChkFlagEqualTo(String value) {
            addCriterion("CHK_FLAG =", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotEqualTo(String value) {
            addCriterion("CHK_FLAG <>", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagGreaterThan(String value) {
            addCriterion("CHK_FLAG >", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CHK_FLAG >=", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLessThan(String value) {
            addCriterion("CHK_FLAG <", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLessThanOrEqualTo(String value) {
            addCriterion("CHK_FLAG <=", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagLike(String value) {
            addCriterion("CHK_FLAG like", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotLike(String value) {
            addCriterion("CHK_FLAG not like", value, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagIn(List<String> values) {
            addCriterion("CHK_FLAG in", values, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotIn(List<String> values) {
            addCriterion("CHK_FLAG not in", values, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagBetween(String value1, String value2) {
            addCriterion("CHK_FLAG between", value1, value2, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andChkFlagNotBetween(String value1, String value2) {
            addCriterion("CHK_FLAG not between", value1, value2, "chkFlag");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagIsNull() {
            addCriterion("ADJUST_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagIsNotNull() {
            addCriterion("ADJUST_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagEqualTo(String value) {
            addCriterion("ADJUST_FLAG =", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagNotEqualTo(String value) {
            addCriterion("ADJUST_FLAG <>", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagGreaterThan(String value) {
            addCriterion("ADJUST_FLAG >", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_FLAG >=", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagLessThan(String value) {
            addCriterion("ADJUST_FLAG <", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_FLAG <=", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagLike(String value) {
            addCriterion("ADJUST_FLAG like", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagNotLike(String value) {
            addCriterion("ADJUST_FLAG not like", value, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagIn(List<String> values) {
            addCriterion("ADJUST_FLAG in", values, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagNotIn(List<String> values) {
            addCriterion("ADJUST_FLAG not in", values, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagBetween(String value1, String value2) {
            addCriterion("ADJUST_FLAG between", value1, value2, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andAdjustFlagNotBetween(String value1, String value2) {
            addCriterion("ADJUST_FLAG not between", value1, value2, "adjustFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagIsNull() {
            addCriterion("DC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDcFlagIsNotNull() {
            addCriterion("DC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDcFlagEqualTo(String value) {
            addCriterion("DC_FLAG =", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotEqualTo(String value) {
            addCriterion("DC_FLAG <>", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagGreaterThan(String value) {
            addCriterion("DC_FLAG >", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DC_FLAG >=", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLessThan(String value) {
            addCriterion("DC_FLAG <", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLessThanOrEqualTo(String value) {
            addCriterion("DC_FLAG <=", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLike(String value) {
            addCriterion("DC_FLAG like", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotLike(String value) {
            addCriterion("DC_FLAG not like", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagIn(List<String> values) {
            addCriterion("DC_FLAG in", values, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotIn(List<String> values) {
            addCriterion("DC_FLAG not in", values, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagBetween(String value1, String value2) {
            addCriterion("DC_FLAG between", value1, value2, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotBetween(String value1, String value2) {
            addCriterion("DC_FLAG not between", value1, value2, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagIsNull() {
            addCriterion("STL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andStlFlagIsNotNull() {
            addCriterion("STL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andStlFlagEqualTo(String value) {
            addCriterion("STL_FLAG =", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagNotEqualTo(String value) {
            addCriterion("STL_FLAG <>", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagGreaterThan(String value) {
            addCriterion("STL_FLAG >", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagGreaterThanOrEqualTo(String value) {
            addCriterion("STL_FLAG >=", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagLessThan(String value) {
            addCriterion("STL_FLAG <", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagLessThanOrEqualTo(String value) {
            addCriterion("STL_FLAG <=", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagLike(String value) {
            addCriterion("STL_FLAG like", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagNotLike(String value) {
            addCriterion("STL_FLAG not like", value, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagIn(List<String> values) {
            addCriterion("STL_FLAG in", values, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagNotIn(List<String> values) {
            addCriterion("STL_FLAG not in", values, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagBetween(String value1, String value2) {
            addCriterion("STL_FLAG between", value1, value2, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlFlagNotBetween(String value1, String value2) {
            addCriterion("STL_FLAG not between", value1, value2, "stlFlag");
            return (Criteria) this;
        }

        public Criteria andStlAmtIsNull() {
            addCriterion("STL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andStlAmtIsNotNull() {
            addCriterion("STL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andStlAmtEqualTo(BigDecimal value) {
            addCriterion("STL_AMT =", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtNotEqualTo(BigDecimal value) {
            addCriterion("STL_AMT <>", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtGreaterThan(BigDecimal value) {
            addCriterion("STL_AMT >", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STL_AMT >=", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtLessThan(BigDecimal value) {
            addCriterion("STL_AMT <", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STL_AMT <=", value, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtIn(List<BigDecimal> values) {
            addCriterion("STL_AMT in", values, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtNotIn(List<BigDecimal> values) {
            addCriterion("STL_AMT not in", values, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STL_AMT between", value1, value2, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andStlAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STL_AMT not between", value1, value2, "stlAmt");
            return (Criteria) this;
        }

        public Criteria andErrbookIdIsNull() {
            addCriterion("ERRBOOK_ID is null");
            return (Criteria) this;
        }

        public Criteria andErrbookIdIsNotNull() {
            addCriterion("ERRBOOK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andErrbookIdEqualTo(String value) {
            addCriterion("ERRBOOK_ID =", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdNotEqualTo(String value) {
            addCriterion("ERRBOOK_ID <>", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdGreaterThan(String value) {
            addCriterion("ERRBOOK_ID >", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdGreaterThanOrEqualTo(String value) {
            addCriterion("ERRBOOK_ID >=", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdLessThan(String value) {
            addCriterion("ERRBOOK_ID <", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdLessThanOrEqualTo(String value) {
            addCriterion("ERRBOOK_ID <=", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdLike(String value) {
            addCriterion("ERRBOOK_ID like", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdNotLike(String value) {
            addCriterion("ERRBOOK_ID not like", value, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdIn(List<String> values) {
            addCriterion("ERRBOOK_ID in", values, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdNotIn(List<String> values) {
            addCriterion("ERRBOOK_ID not in", values, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdBetween(String value1, String value2) {
            addCriterion("ERRBOOK_ID between", value1, value2, "errbookId");
            return (Criteria) this;
        }

        public Criteria andErrbookIdNotBetween(String value1, String value2) {
            addCriterion("ERRBOOK_ID not between", value1, value2, "errbookId");
            return (Criteria) this;
        }

        public Criteria andOperor1IsNull() {
            addCriterion("OPEROR1 is null");
            return (Criteria) this;
        }

        public Criteria andOperor1IsNotNull() {
            addCriterion("OPEROR1 is not null");
            return (Criteria) this;
        }

        public Criteria andOperor1EqualTo(String value) {
            addCriterion("OPEROR1 =", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1NotEqualTo(String value) {
            addCriterion("OPEROR1 <>", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1GreaterThan(String value) {
            addCriterion("OPEROR1 >", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1GreaterThanOrEqualTo(String value) {
            addCriterion("OPEROR1 >=", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1LessThan(String value) {
            addCriterion("OPEROR1 <", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1LessThanOrEqualTo(String value) {
            addCriterion("OPEROR1 <=", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1Like(String value) {
            addCriterion("OPEROR1 like", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1NotLike(String value) {
            addCriterion("OPEROR1 not like", value, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1In(List<String> values) {
            addCriterion("OPEROR1 in", values, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1NotIn(List<String> values) {
            addCriterion("OPEROR1 not in", values, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1Between(String value1, String value2) {
            addCriterion("OPEROR1 between", value1, value2, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperor1NotBetween(String value1, String value2) {
            addCriterion("OPEROR1 not between", value1, value2, "operor1");
            return (Criteria) this;
        }

        public Criteria andOperDt1IsNull() {
            addCriterion("OPER_DT1 is null");
            return (Criteria) this;
        }

        public Criteria andOperDt1IsNotNull() {
            addCriterion("OPER_DT1 is not null");
            return (Criteria) this;
        }

        public Criteria andOperDt1EqualTo(String value) {
            addCriterion("OPER_DT1 =", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1NotEqualTo(String value) {
            addCriterion("OPER_DT1 <>", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1GreaterThan(String value) {
            addCriterion("OPER_DT1 >", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1GreaterThanOrEqualTo(String value) {
            addCriterion("OPER_DT1 >=", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1LessThan(String value) {
            addCriterion("OPER_DT1 <", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1LessThanOrEqualTo(String value) {
            addCriterion("OPER_DT1 <=", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1Like(String value) {
            addCriterion("OPER_DT1 like", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1NotLike(String value) {
            addCriterion("OPER_DT1 not like", value, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1In(List<String> values) {
            addCriterion("OPER_DT1 in", values, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1NotIn(List<String> values) {
            addCriterion("OPER_DT1 not in", values, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1Between(String value1, String value2) {
            addCriterion("OPER_DT1 between", value1, value2, "operDt1");
            return (Criteria) this;
        }

        public Criteria andOperDt1NotBetween(String value1, String value2) {
            addCriterion("OPER_DT1 not between", value1, value2, "operDt1");
            return (Criteria) this;
        }

        public Criteria andComments1IsNull() {
            addCriterion("COMMENTS1 is null");
            return (Criteria) this;
        }

        public Criteria andComments1IsNotNull() {
            addCriterion("COMMENTS1 is not null");
            return (Criteria) this;
        }

        public Criteria andComments1EqualTo(String value) {
            addCriterion("COMMENTS1 =", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotEqualTo(String value) {
            addCriterion("COMMENTS1 <>", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThan(String value) {
            addCriterion("COMMENTS1 >", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 >=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThan(String value) {
            addCriterion("COMMENTS1 <", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS1 <=", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Like(String value) {
            addCriterion("COMMENTS1 like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotLike(String value) {
            addCriterion("COMMENTS1 not like", value, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1In(List<String> values) {
            addCriterion("COMMENTS1 in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotIn(List<String> values) {
            addCriterion("COMMENTS1 not in", values, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1Between(String value1, String value2) {
            addCriterion("COMMENTS1 between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andComments1NotBetween(String value1, String value2) {
            addCriterion("COMMENTS1 not between", value1, value2, "comments1");
            return (Criteria) this;
        }

        public Criteria andOperor2IsNull() {
            addCriterion("OPEROR2 is null");
            return (Criteria) this;
        }

        public Criteria andOperor2IsNotNull() {
            addCriterion("OPEROR2 is not null");
            return (Criteria) this;
        }

        public Criteria andOperor2EqualTo(String value) {
            addCriterion("OPEROR2 =", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2NotEqualTo(String value) {
            addCriterion("OPEROR2 <>", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2GreaterThan(String value) {
            addCriterion("OPEROR2 >", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2GreaterThanOrEqualTo(String value) {
            addCriterion("OPEROR2 >=", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2LessThan(String value) {
            addCriterion("OPEROR2 <", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2LessThanOrEqualTo(String value) {
            addCriterion("OPEROR2 <=", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2Like(String value) {
            addCriterion("OPEROR2 like", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2NotLike(String value) {
            addCriterion("OPEROR2 not like", value, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2In(List<String> values) {
            addCriterion("OPEROR2 in", values, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2NotIn(List<String> values) {
            addCriterion("OPEROR2 not in", values, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2Between(String value1, String value2) {
            addCriterion("OPEROR2 between", value1, value2, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperor2NotBetween(String value1, String value2) {
            addCriterion("OPEROR2 not between", value1, value2, "operor2");
            return (Criteria) this;
        }

        public Criteria andOperDt2IsNull() {
            addCriterion("OPER_DT2 is null");
            return (Criteria) this;
        }

        public Criteria andOperDt2IsNotNull() {
            addCriterion("OPER_DT2 is not null");
            return (Criteria) this;
        }

        public Criteria andOperDt2EqualTo(String value) {
            addCriterion("OPER_DT2 =", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2NotEqualTo(String value) {
            addCriterion("OPER_DT2 <>", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2GreaterThan(String value) {
            addCriterion("OPER_DT2 >", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2GreaterThanOrEqualTo(String value) {
            addCriterion("OPER_DT2 >=", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2LessThan(String value) {
            addCriterion("OPER_DT2 <", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2LessThanOrEqualTo(String value) {
            addCriterion("OPER_DT2 <=", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2Like(String value) {
            addCriterion("OPER_DT2 like", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2NotLike(String value) {
            addCriterion("OPER_DT2 not like", value, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2In(List<String> values) {
            addCriterion("OPER_DT2 in", values, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2NotIn(List<String> values) {
            addCriterion("OPER_DT2 not in", values, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2Between(String value1, String value2) {
            addCriterion("OPER_DT2 between", value1, value2, "operDt2");
            return (Criteria) this;
        }

        public Criteria andOperDt2NotBetween(String value1, String value2) {
            addCriterion("OPER_DT2 not between", value1, value2, "operDt2");
            return (Criteria) this;
        }

        public Criteria andComments2IsNull() {
            addCriterion("COMMENTS2 is null");
            return (Criteria) this;
        }

        public Criteria andComments2IsNotNull() {
            addCriterion("COMMENTS2 is not null");
            return (Criteria) this;
        }

        public Criteria andComments2EqualTo(String value) {
            addCriterion("COMMENTS2 =", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotEqualTo(String value) {
            addCriterion("COMMENTS2 <>", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThan(String value) {
            addCriterion("COMMENTS2 >", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2GreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 >=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThan(String value) {
            addCriterion("COMMENTS2 <", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2LessThanOrEqualTo(String value) {
            addCriterion("COMMENTS2 <=", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Like(String value) {
            addCriterion("COMMENTS2 like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotLike(String value) {
            addCriterion("COMMENTS2 not like", value, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2In(List<String> values) {
            addCriterion("COMMENTS2 in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotIn(List<String> values) {
            addCriterion("COMMENTS2 not in", values, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2Between(String value1, String value2) {
            addCriterion("COMMENTS2 between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andComments2NotBetween(String value1, String value2) {
            addCriterion("COMMENTS2 not between", value1, value2, "comments2");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("COMMENTS is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("COMMENTS is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("COMMENTS =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("COMMENTS <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("COMMENTS >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("COMMENTS <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("COMMENTS <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("COMMENTS like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("COMMENTS not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("COMMENTS in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("COMMENTS not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("COMMENTS between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("COMMENTS not between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeIsNull() {
            addCriterion("CONSUME_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeIsNotNull() {
            addCriterion("CONSUME_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeEqualTo(String value) {
            addCriterion("CONSUME_TYPE =", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeNotEqualTo(String value) {
            addCriterion("CONSUME_TYPE <>", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeGreaterThan(String value) {
            addCriterion("CONSUME_TYPE >", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUME_TYPE >=", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeLessThan(String value) {
            addCriterion("CONSUME_TYPE <", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeLessThanOrEqualTo(String value) {
            addCriterion("CONSUME_TYPE <=", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeLike(String value) {
            addCriterion("CONSUME_TYPE like", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeNotLike(String value) {
            addCriterion("CONSUME_TYPE not like", value, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeIn(List<String> values) {
            addCriterion("CONSUME_TYPE in", values, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeNotIn(List<String> values) {
            addCriterion("CONSUME_TYPE not in", values, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeBetween(String value1, String value2) {
            addCriterion("CONSUME_TYPE between", value1, value2, "consumeType");
            return (Criteria) this;
        }

        public Criteria andConsumeTypeNotBetween(String value1, String value2) {
            addCriterion("CONSUME_TYPE not between", value1, value2, "consumeType");
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