package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearMerStlFinalBookBakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearMerStlFinalBookBakExample() {
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
            addCriterion("SUBSTR(GEN_DT,0,8) =", value, "genDt");
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

        public Criteria andTermMerNoIsNull() {
            addCriterion("TERM_MER_NO is null");
            return (Criteria) this;
        }

        public Criteria andTermMerNoIsNotNull() {
            addCriterion("TERM_MER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTermMerNoEqualTo(String value) {
            addCriterion("TERM_MER_NO =", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoNotEqualTo(String value) {
            addCriterion("TERM_MER_NO <>", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoGreaterThan(String value) {
            addCriterion("TERM_MER_NO >", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_MER_NO >=", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoLessThan(String value) {
            addCriterion("TERM_MER_NO <", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoLessThanOrEqualTo(String value) {
            addCriterion("TERM_MER_NO <=", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoLike(String value) {
            addCriterion("TERM_MER_NO like", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoNotLike(String value) {
            addCriterion("TERM_MER_NO not like", value, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoIn(List<String> values) {
            addCriterion("TERM_MER_NO in", values, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoNotIn(List<String> values) {
            addCriterion("TERM_MER_NO not in", values, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoBetween(String value1, String value2) {
            addCriterion("TERM_MER_NO between", value1, value2, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andTermMerNoNotBetween(String value1, String value2) {
            addCriterion("TERM_MER_NO not between", value1, value2, "termMerNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNull() {
            addCriterion("a.mer_no is null");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNotNull() {
            addCriterion("a.mer_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerNoEqualTo(String value) {
            addCriterion("a.mer_no =", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotEqualTo(String value) {
            addCriterion("a.mer_no <>", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThan(String value) {
            addCriterion("a.mer_no >", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("a.mer_no >=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThan(String value) {
            addCriterion("a.mer_no <", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThanOrEqualTo(String value) {
            addCriterion("a.mer_no <=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLike(String value) {
            addCriterion("a.mer_no like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotLike(String value) {
            addCriterion("a.mer_no not like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIn(List<String> values) {
            addCriterion("a.mer_no in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotIn(List<String> values) {
            addCriterion("a.mer_no not in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoBetween(String value1, String value2) {
            addCriterion("a.mer_no between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotBetween(String value1, String value2) {
            addCriterion("a.mer_no not between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andSceneIsNull() {
            addCriterion("a.SCENE is null");
            return (Criteria) this;
        }

        public Criteria andSceneIsNotNull() {
            addCriterion("a.SCENE is not null");
            return (Criteria) this;
        }

        public Criteria andSceneEqualTo(String value) {
            addCriterion("a.SCENE =", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotEqualTo(String value) {
            addCriterion("a.SCENE <>", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThan(String value) {
            addCriterion("a.SCENE >", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThanOrEqualTo(String value) {
            addCriterion("a.SCENE >=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThan(String value) {
            addCriterion("a.SCENE <", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThanOrEqualTo(String value) {
            addCriterion("a.SCENE <=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLike(String value) {
            addCriterion("a.SCENE like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotLike(String value) {
            addCriterion("a.SCENE not like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneIn(List<String> values) {
            addCriterion("a.SCENE in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotIn(List<String> values) {
            addCriterion("a.SCENE not in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneBetween(String value1, String value2) {
            addCriterion("a.SCENE between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotBetween(String value1, String value2) {
            addCriterion("a.SCENE not between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNull() {
            addCriterion("a.mer_name is null");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNotNull() {
            addCriterion("a.mer_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerNameEqualTo(String value) {
            addCriterion("a.mer_name =", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotEqualTo(String value) {
            addCriterion("a.mer_name <>", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThan(String value) {
            addCriterion("a.mer_name >", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("a.mer_name >=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThan(String value) {
            addCriterion("a.mer_name <", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThanOrEqualTo(String value) {
            addCriterion("a.mer_name <=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLike(String value) {
            addCriterion("a.mer_name like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotLike(String value) {
            addCriterion("a.mer_name not like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameIn(List<String> values) {
            addCriterion("a.mer_name in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotIn(List<String> values) {
            addCriterion("a.mer_name not in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameBetween(String value1, String value2) {
            addCriterion("a.mer_name between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotBetween(String value1, String value2) {
            addCriterion("a.mer_name not between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andStlDateIsNull() {
            addCriterion("a.stl_date is null");
            return (Criteria) this;
        }

        public Criteria andStlDateIsNotNull() {
            addCriterion("a.stl_date is not null");
            return (Criteria) this;
        }

        public Criteria andStlDateEqualTo(String value) {
            addCriterion("a.stl_date =", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotEqualTo(String value) {
            addCriterion("a.stl_date <>", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateGreaterThan(String value) {
            addCriterion("a.stl_date >", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateGreaterThanOrEqualTo(String value) {
            addCriterion("a.stl_date >=", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLessThan(String value) {
            addCriterion("a.stl_date <", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLessThanOrEqualTo(String value) {
            addCriterion("a.stl_date <=", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLike(String value) {
            addCriterion("a.stl_date like", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotLike(String value) {
            addCriterion("a.stl_date not like", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateIn(List<String> values) {
            addCriterion("a.stl_date in", values, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotIn(List<String> values) {
            addCriterion("a.stl_date not in", values, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateBetween(String value1, String value2) {
            addCriterion("a.stl_date between", value1, value2, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotBetween(String value1, String value2) {
            addCriterion("a.stl_date not between", value1, value2, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateIsNull() {
            addCriterion("START_STL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartStlDateIsNotNull() {
            addCriterion("START_STL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartStlDateEqualTo(String value) {
            addCriterion("START_STL_DATE =", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateNotEqualTo(String value) {
            addCriterion("START_STL_DATE <>", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateGreaterThan(String value) {
            addCriterion("START_STL_DATE >", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_STL_DATE >=", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateLessThan(String value) {
            addCriterion("START_STL_DATE <", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateLessThanOrEqualTo(String value) {
            addCriterion("START_STL_DATE <=", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateLike(String value) {
            addCriterion("START_STL_DATE like", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateNotLike(String value) {
            addCriterion("START_STL_DATE not like", value, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateIn(List<String> values) {
            addCriterion("START_STL_DATE in", values, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateNotIn(List<String> values) {
            addCriterion("START_STL_DATE not in", values, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateBetween(String value1, String value2) {
            addCriterion("START_STL_DATE between", value1, value2, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andStartStlDateNotBetween(String value1, String value2) {
            addCriterion("START_STL_DATE not between", value1, value2, "startStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateIsNull() {
            addCriterion("END_STL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndStlDateIsNotNull() {
            addCriterion("END_STL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndStlDateEqualTo(String value) {
            addCriterion("END_STL_DATE =", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateNotEqualTo(String value) {
            addCriterion("END_STL_DATE <>", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateGreaterThan(String value) {
            addCriterion("END_STL_DATE >", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_STL_DATE >=", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateLessThan(String value) {
            addCriterion("END_STL_DATE <", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateLessThanOrEqualTo(String value) {
            addCriterion("END_STL_DATE <=", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateLike(String value) {
            addCriterion("END_STL_DATE like", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateNotLike(String value) {
            addCriterion("END_STL_DATE not like", value, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateIn(List<String> values) {
            addCriterion("END_STL_DATE in", values, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateNotIn(List<String> values) {
            addCriterion("END_STL_DATE not in", values, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateBetween(String value1, String value2) {
            addCriterion("END_STL_DATE between", value1, value2, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andEndStlDateNotBetween(String value1, String value2) {
            addCriterion("END_STL_DATE not between", value1, value2, "endStlDate");
            return (Criteria) this;
        }

        public Criteria andConsumNumIsNull() {
            addCriterion("CONSUM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andConsumNumIsNotNull() {
            addCriterion("CONSUM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andConsumNumEqualTo(BigDecimal value) {
            addCriterion("CONSUM_NUM =", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumNotEqualTo(BigDecimal value) {
            addCriterion("CONSUM_NUM <>", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumGreaterThan(BigDecimal value) {
            addCriterion("CONSUM_NUM >", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CONSUM_NUM >=", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumLessThan(BigDecimal value) {
            addCriterion("CONSUM_NUM <", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CONSUM_NUM <=", value, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumIn(List<BigDecimal> values) {
            addCriterion("CONSUM_NUM in", values, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumNotIn(List<BigDecimal> values) {
            addCriterion("CONSUM_NUM not in", values, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONSUM_NUM between", value1, value2, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONSUM_NUM not between", value1, value2, "consumNum");
            return (Criteria) this;
        }

        public Criteria andConsumAmtIsNull() {
            addCriterion("CONSUM_AMT is null");
            return (Criteria) this;
        }

        public Criteria andConsumAmtIsNotNull() {
            addCriterion("CONSUM_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andConsumAmtEqualTo(BigDecimal value) {
            addCriterion("CONSUM_AMT =", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtNotEqualTo(BigDecimal value) {
            addCriterion("CONSUM_AMT <>", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtGreaterThan(BigDecimal value) {
            addCriterion("CONSUM_AMT >", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CONSUM_AMT >=", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtLessThan(BigDecimal value) {
            addCriterion("CONSUM_AMT <", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CONSUM_AMT <=", value, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtIn(List<BigDecimal> values) {
            addCriterion("CONSUM_AMT in", values, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtNotIn(List<BigDecimal> values) {
            addCriterion("CONSUM_AMT not in", values, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONSUM_AMT between", value1, value2, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andConsumAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONSUM_AMT not between", value1, value2, "consumAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtIsNull() {
            addCriterion("NET_AMT is null");
            return (Criteria) this;
        }

        public Criteria andNetAmtIsNotNull() {
            addCriterion("NET_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andNetAmtEqualTo(BigDecimal value) {
            addCriterion("NET_AMT =", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtNotEqualTo(BigDecimal value) {
            addCriterion("NET_AMT <>", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtGreaterThan(BigDecimal value) {
            addCriterion("NET_AMT >", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_AMT >=", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtLessThan(BigDecimal value) {
            addCriterion("NET_AMT <", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_AMT <=", value, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtIn(List<BigDecimal> values) {
            addCriterion("NET_AMT in", values, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtNotIn(List<BigDecimal> values) {
            addCriterion("NET_AMT not in", values, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_AMT between", value1, value2, "netAmt");
            return (Criteria) this;
        }

        public Criteria andNetAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_AMT not between", value1, value2, "netAmt");
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

        public Criteria andTranAmtIsNull() {
            addCriterion("TRAN_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTranAmtIsNotNull() {
            addCriterion("TRAN_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTranAmtEqualTo(BigDecimal value) {
            addCriterion("TRAN_AMT =", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtNotEqualTo(BigDecimal value) {
            addCriterion("TRAN_AMT <>", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtGreaterThan(BigDecimal value) {
            addCriterion("TRAN_AMT >", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_AMT >=", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtLessThan(BigDecimal value) {
            addCriterion("TRAN_AMT <", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_AMT <=", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtIn(List<BigDecimal> values) {
            addCriterion("TRAN_AMT in", values, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtNotIn(List<BigDecimal> values) {
            addCriterion("TRAN_AMT not in", values, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_AMT between", value1, value2, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andTranAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_AMT not between", value1, value2, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andStlaccIdIsNull() {
            addCriterion("STLACC_ID is null");
            return (Criteria) this;
        }

        public Criteria andStlaccIdIsNotNull() {
            addCriterion("STLACC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStlaccIdEqualTo(Long value) {
            addCriterion("STLACC_ID =", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdNotEqualTo(Long value) {
            addCriterion("STLACC_ID <>", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdGreaterThan(Long value) {
            addCriterion("STLACC_ID >", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STLACC_ID >=", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdLessThan(Long value) {
            addCriterion("STLACC_ID <", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdLessThanOrEqualTo(Long value) {
            addCriterion("STLACC_ID <=", value, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdIn(List<Long> values) {
            addCriterion("STLACC_ID in", values, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdNotIn(List<Long> values) {
            addCriterion("STLACC_ID not in", values, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdBetween(Long value1, Long value2) {
            addCriterion("STLACC_ID between", value1, value2, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andStlaccIdNotBetween(Long value1, Long value2) {
            addCriterion("STLACC_ID not between", value1, value2, "stlaccId");
            return (Criteria) this;
        }

        public Criteria andAccNoIsNull() {
            addCriterion("ACC_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccNoIsNotNull() {
            addCriterion("ACC_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccNoEqualTo(String value) {
            addCriterion("ACC_NO =", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotEqualTo(String value) {
            addCriterion("ACC_NO <>", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThan(String value) {
            addCriterion("ACC_NO >", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NO >=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThan(String value) {
            addCriterion("ACC_NO <", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThanOrEqualTo(String value) {
            addCriterion("ACC_NO <=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLike(String value) {
            addCriterion("ACC_NO like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotLike(String value) {
            addCriterion("ACC_NO not like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoIn(List<String> values) {
            addCriterion("ACC_NO in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotIn(List<String> values) {
            addCriterion("ACC_NO not in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoBetween(String value1, String value2) {
            addCriterion("ACC_NO between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotBetween(String value1, String value2) {
            addCriterion("ACC_NO not between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccBankNameIsNull() {
            addCriterion("ACC_BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccBankNameIsNotNull() {
            addCriterion("ACC_BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccBankNameEqualTo(String value) {
            addCriterion("ACC_BANK_NAME =", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameNotEqualTo(String value) {
            addCriterion("ACC_BANK_NAME <>", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameGreaterThan(String value) {
            addCriterion("ACC_BANK_NAME >", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_BANK_NAME >=", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameLessThan(String value) {
            addCriterion("ACC_BANK_NAME <", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_BANK_NAME <=", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameLike(String value) {
            addCriterion("ACC_BANK_NAME like", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameNotLike(String value) {
            addCriterion("ACC_BANK_NAME not like", value, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameIn(List<String> values) {
            addCriterion("ACC_BANK_NAME in", values, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameNotIn(List<String> values) {
            addCriterion("ACC_BANK_NAME not in", values, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameBetween(String value1, String value2) {
            addCriterion("ACC_BANK_NAME between", value1, value2, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccBankNameNotBetween(String value1, String value2) {
            addCriterion("ACC_BANK_NAME not between", value1, value2, "accBankName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("ACCOUNT_NAME =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("ACCOUNT_NAME >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("ACCOUNT_NAME <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("ACCOUNT_NAME like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("ACCOUNT_NAME not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("ACCOUNT_NAME in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("ACCOUNT_NAME not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andBankUnionIsNull() {
            addCriterion("BANK_UNION is null");
            return (Criteria) this;
        }

        public Criteria andBankUnionIsNotNull() {
            addCriterion("BANK_UNION is not null");
            return (Criteria) this;
        }

        public Criteria andBankUnionEqualTo(String value) {
            addCriterion("BANK_UNION =", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionNotEqualTo(String value) {
            addCriterion("BANK_UNION <>", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionGreaterThan(String value) {
            addCriterion("BANK_UNION >", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_UNION >=", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionLessThan(String value) {
            addCriterion("BANK_UNION <", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionLessThanOrEqualTo(String value) {
            addCriterion("BANK_UNION <=", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionLike(String value) {
            addCriterion("BANK_UNION like", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionNotLike(String value) {
            addCriterion("BANK_UNION not like", value, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionIn(List<String> values) {
            addCriterion("BANK_UNION in", values, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionNotIn(List<String> values) {
            addCriterion("BANK_UNION not in", values, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionBetween(String value1, String value2) {
            addCriterion("BANK_UNION between", value1, value2, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankUnionNotBetween(String value1, String value2) {
            addCriterion("BANK_UNION not between", value1, value2, "bankUnion");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("BANK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("BANK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("BANK_CODE =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("BANK_CODE <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("BANK_CODE >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CODE >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("BANK_CODE <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("BANK_CODE <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("BANK_CODE like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("BANK_CODE not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("BANK_CODE in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("BANK_CODE not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("BANK_CODE between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("BANK_CODE not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagIsNull() {
            addCriterion("BEIJING_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagIsNotNull() {
            addCriterion("BEIJING_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagEqualTo(String value) {
            addCriterion("BEIJING_FLAG =", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagNotEqualTo(String value) {
            addCriterion("BEIJING_FLAG <>", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagGreaterThan(String value) {
            addCriterion("BEIJING_FLAG >", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BEIJING_FLAG >=", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagLessThan(String value) {
            addCriterion("BEIJING_FLAG <", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagLessThanOrEqualTo(String value) {
            addCriterion("BEIJING_FLAG <=", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagLike(String value) {
            addCriterion("BEIJING_FLAG like", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagNotLike(String value) {
            addCriterion("BEIJING_FLAG not like", value, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagIn(List<String> values) {
            addCriterion("BEIJING_FLAG in", values, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagNotIn(List<String> values) {
            addCriterion("BEIJING_FLAG not in", values, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagBetween(String value1, String value2) {
            addCriterion("BEIJING_FLAG between", value1, value2, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andBeijingFlagNotBetween(String value1, String value2) {
            addCriterion("BEIJING_FLAG not between", value1, value2, "beijingFlag");
            return (Criteria) this;
        }

        public Criteria andPayoutDateIsNull() {
            addCriterion("a.PAYOUT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPayoutDateIsNotNull() {
            addCriterion("a.PAYOUT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutDateEqualTo(String value) {
            addCriterion("a.PAYOUT_DATE =", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateNotEqualTo(String value) {
            addCriterion("a.PAYOUT_DATE <>", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateGreaterThan(String value) {
            addCriterion("a.PAYOUT_DATE >", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateGreaterThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_DATE >=", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateLessThan(String value) {
            addCriterion("a.PAYOUT_DATE <", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateLessThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_DATE <=", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateLike(String value) {
            addCriterion("a.PAYOUT_DATE like", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateNotLike(String value) {
            addCriterion("a.PAYOUT_DATE not like", value, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateIn(List<String> values) {
            addCriterion("a.PAYOUT_DATE in", values, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateNotIn(List<String> values) {
            addCriterion("a.PAYOUT_DATE not in", values, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_DATE between", value1, value2, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutDateNotBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_DATE not between", value1, value2, "payoutDate");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlIsNull() {
            addCriterion("a.PAYOUT_JUNL is null");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlIsNotNull() {
            addCriterion("a.PAYOUT_JUNL is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlEqualTo(String value) {
            addCriterion("a.PAYOUT_JUNL =", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlNotEqualTo(String value) {
            addCriterion("a.PAYOUT_JUNL <>", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlGreaterThan(String value) {
            addCriterion("a.PAYOUT_JUNL >", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlGreaterThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_JUNL >=", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlLessThan(String value) {
            addCriterion("a.PAYOUT_JUNL <", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlLessThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_JUNL <=", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlLike(String value) {
            addCriterion("a.PAYOUT_JUNL like", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlNotLike(String value) {
            addCriterion("a.PAYOUT_JUNL not like", value, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlIn(List<String> values) {
            addCriterion("a.PAYOUT_JUNL in", values, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlNotIn(List<String> values) {
            addCriterion("a.PAYOUT_JUNL not in", values, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_JUNL between", value1, value2, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andPayoutJunlNotBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_JUNL not between", value1, value2, "payoutJunl");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("a.SEQ_NO is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("a.SEQ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(String value) {
            addCriterion("a.SEQ_NO =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(String value) {
            addCriterion("a.SEQ_NO <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(String value) {
            addCriterion("a.SEQ_NO >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("a.SEQ_NO >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(String value) {
            addCriterion("a.SEQ_NO <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(String value) {
            addCriterion("a.SEQ_NO <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLike(String value) {
            addCriterion("a.SEQ_NO like", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotLike(String value) {
            addCriterion("a.SEQ_NO not like", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<String> values) {
            addCriterion("a.SEQ_NO in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<String> values) {
            addCriterion("a.SEQ_NO not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(String value1, String value2) {
            addCriterion("a.SEQ_NO between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(String value1, String value2) {
            addCriterion("a.SEQ_NO not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusIsNull() {
            addCriterion("a.PAYOUT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusIsNotNull() {
            addCriterion("a.PAYOUT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusEqualTo(String value) {
            addCriterion("a.PAYOUT_STATUS =", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusNotEqualTo(String value) {
            addCriterion("a.PAYOUT_STATUS <>", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusGreaterThan(String value) {
            addCriterion("a.PAYOUT_STATUS >", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusGreaterThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_STATUS >=", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusLessThan(String value) {
            addCriterion("a.PAYOUT_STATUS <", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusLessThanOrEqualTo(String value) {
            addCriterion("a.PAYOUT_STATUS <=", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusLike(String value) {
            addCriterion("a.PAYOUT_STATUS like", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusNotLike(String value) {
            addCriterion("a.PAYOUT_STATUS not like", value, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusIn(List<String> values) {
            addCriterion("a.PAYOUT_STATUS in", values, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusNotIn(List<String> values) {
            addCriterion("a.PAYOUT_STATUS not in", values, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_STATUS between", value1, value2, "payoutStatus");
            return (Criteria) this;
        }

        public Criteria andPayoutStatusNotBetween(String value1, String value2) {
            addCriterion("a.PAYOUT_STATUS not between", value1, value2, "payoutStatus");
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

        public Criteria andProvinceCodeIsNull() {
            addCriterion("a.PROVINCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("a.PROVINCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(Long value) {
            addCriterion("a.PROVINCE_CODE =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(Long value) {
            addCriterion("a.PROVINCE_CODE <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(Long value) {
            addCriterion("a.PROVINCE_CODE >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("a.PROVINCE_CODE >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(Long value) {
            addCriterion("a.PROVINCE_CODE <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(Long value) {
            addCriterion("a.PROVINCE_CODE <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<Long> values) {
            addCriterion("a.PROVINCE_CODE in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<Long> values) {
            addCriterion("a.PROVINCE_CODE not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(Long value1, Long value2) {
            addCriterion("a.PROVINCE_CODE between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(Long value1, Long value2) {
            addCriterion("a.PROVINCE_CODE not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("PROVINCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("PROVINCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("PROVINCE_NAME =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("PROVINCE_NAME <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("PROVINCE_NAME >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_NAME >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("PROVINCE_NAME <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_NAME <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("PROVINCE_NAME like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("PROVINCE_NAME not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("PROVINCE_NAME in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("PROVINCE_NAME not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("PROVINCE_NAME between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_NAME not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andBjFlagIsNull() {
            addCriterion("a.BJ_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBjFlagIsNotNull() {
            addCriterion("a.BJ_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBjFlagEqualTo(String value) {
            addCriterion("a.BJ_FLAG =", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotEqualTo(String value) {
            addCriterion("a.BJ_FLAG <>", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagGreaterThan(String value) {
            addCriterion("a.BJ_FLAG >", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagGreaterThanOrEqualTo(String value) {
            addCriterion("a.BJ_FLAG >=", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLessThan(String value) {
            addCriterion("a.BJ_FLAG <", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLessThanOrEqualTo(String value) {
            addCriterion("a.BJ_FLAG <=", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLike(String value) {
            addCriterion("a.BJ_FLAG like", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotLike(String value) {
            addCriterion("a.BJ_FLAG not like", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagIn(List<String> values) {
            addCriterion("a.BJ_FLAG in", values, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotIn(List<String> values) {
            addCriterion("a.BJ_FLAG not in", values, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagBetween(String value1, String value2) {
            addCriterion("a.BJ_FLAG between", value1, value2, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotBetween(String value1, String value2) {
            addCriterion("a.BJ_FLAG not between", value1, value2, "bjFlag");
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

        public Criteria andExelusiveCardFlagIsNull() {
            addCriterion("a.EXELUSIVE_CARD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagIsNotNull() {
            addCriterion("a.EXELUSIVE_CARD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagEqualTo(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG =", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotEqualTo(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG <>", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagGreaterThan(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG >", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagGreaterThanOrEqualTo(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG >=", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLessThan(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG <", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLessThanOrEqualTo(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG <=", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLike(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG like", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotLike(String value) {
            addCriterion("a.EXELUSIVE_CARD_FLAG not like", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagIn(List<String> values) {
            addCriterion("a.EXELUSIVE_CARD_FLAG in", values, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotIn(List<String> values) {
            addCriterion("a.EXELUSIVE_CARD_FLAG not in", values, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagBetween(String value1, String value2) {
            addCriterion("a.EXELUSIVE_CARD_FLAG between", value1, value2, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotBetween(String value1, String value2) {
            addCriterion("a.EXELUSIVE_CARD_FLAG not between", value1, value2, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagIsNull() {
            addCriterion("a.STL_NEED_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagIsNotNull() {
            addCriterion("a.STL_NEED_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagEqualTo(String value) {
            addCriterion("a.STL_NEED_FLAG =", value, "stlNeedF");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotEqualTo(String value) {
            addCriterion("a.STL_NEED_FLAG <>", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagGreaterThan(String value) {
            addCriterion("a.STL_NEED_FLAG >", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagGreaterThanOrEqualTo(String value) {
            addCriterion("a.STL_NEED_FLAG >=", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLessThan(String value) {
            addCriterion("a.STL_NEED_FLAG <", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLessThanOrEqualTo(String value) {
            addCriterion("a.STL_NEED_FLAG <=", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLike(String value) {
            addCriterion("a.STL_NEED_FLAG like", value, "stlNeedFlag");
            return (Criteria) this;
        }
        
        public Criteria andStlNeedFlagNotLike(String value) {
            addCriterion("a.STL_NEED_FLAG not like", value, "stlNeedFlag");
            return (Criteria) this;
        }
        
        public Criteria andSettlementPersonIsNull() {
            addCriterion("a.SETTLEMENT_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andSettlementPersonIsNotNull() {
            addCriterion("a.SETTLEMENT_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementPersonEqualTo(String value) {
            addCriterion("a.SETTLEMENT_PERSON =", value, "settlementPerson");
            return (Criteria) this;
        }

        public Criteria andSettlementPersonLike(String value) {
            addCriterion("a.SETTLEMENT_PERSON like", value, "settlementPerson");
            return (Criteria) this;
        }

        //flagOnline=1表示线上商城
        public Criteria andFlagOnline(String value) {
        	if(value!=null&&"1".equals(value)){//线上商城
        		addCriterion("a.mer_no in (select d.merchant_id from T_CLEAR_MERCHANT_ONLINE_LIST d)");
        	}else if(value!=null&&"0".equals(value)){//线下商城
        		addCriterion("a.mer_no not in (select d.merchant_id from T_CLEAR_MERCHANT_ONLINE_LIST d)");
        	}
        	
            return (Criteria) this;
        }
        
        //merchantOrg
        public Criteria andMerchantOrgIsNotNull() {
            addCriterion("a.reserved_field is not null");
            return (Criteria) this;
        }
        
        public Criteria andMerchantOrgEqualTo(String value) {
            addCriterion("a.reserved_field =", value, "merchantOrg");
            return (Criteria) this;
        }
        
        public Criteria andMerchantOrgNotEqualTo(String value) {
            addCriterion("a.reserved_field <>", value, "merchantOrg");
            return (Criteria) this;
        }
        
        public Criteria andStlNeedFlagIn(List<String> values) {
            addCriterion("a.STL_NEED_FLAG in", values, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotIn(List<String> values) {
            addCriterion("a.STL_NEED_FLAG not in", values, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagBetween(String value1, String value2) {
            addCriterion("a.STL_NEED_FLAG between", value1, value2, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotBetween(String value1, String value2) {
            addCriterion("a.STL_NEED_FLAG not between", value1, value2, "stlNeedFlag");
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

        public Criteria andCityCodeIsNull() {
            addCriterion("a.CITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("a.CITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(Long value) {
            addCriterion("a.CITY_CODE =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(Long value) {
            addCriterion("a.CITY_CODE <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(Long value) {
            addCriterion("a.CITY_CODE >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("a.CITY_CODE >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(Long value) {
            addCriterion("a.CITY_CODE <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(Long value) {
            addCriterion("a.CITY_CODE <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<Long> values) {
            addCriterion("a.CITY_CODE in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<Long> values) {
            addCriterion("a.CITY_CODE not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(Long value1, Long value2) {
            addCriterion("a.CITY_CODE between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(Long value1, Long value2) {
            addCriterion("a.CITY_CODE not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("CITY_NAME =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("CITY_NAME <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("CITY_NAME >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_NAME >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("CITY_NAME <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("CITY_NAME <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("CITY_NAME like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("CITY_NAME not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("CITY_NAME in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("CITY_NAME not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("CITY_NAME between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("CITY_NAME not between", value1, value2, "cityName");
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