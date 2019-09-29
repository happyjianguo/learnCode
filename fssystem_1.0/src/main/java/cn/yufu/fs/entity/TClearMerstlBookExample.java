package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TClearMerstlBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TClearMerstlBookExample() {
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

        public Criteria andFmrchNoIsNull() {
            addCriterion("FMRCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andFmrchNoIsNotNull() {
            addCriterion("FMRCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andFmrchNoEqualTo(String value) {
            addCriterion("FMRCH_NO =", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoNotEqualTo(String value) {
            addCriterion("FMRCH_NO <>", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoGreaterThan(String value) {
            addCriterion("FMRCH_NO >", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoGreaterThanOrEqualTo(String value) {
            addCriterion("FMRCH_NO >=", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoLessThan(String value) {
            addCriterion("FMRCH_NO <", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoLessThanOrEqualTo(String value) {
            addCriterion("FMRCH_NO <=", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoLike(String value) {
            addCriterion("FMRCH_NO like", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoNotLike(String value) {
            addCriterion("FMRCH_NO not like", value, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoIn(List<String> values) {
            addCriterion("FMRCH_NO in", values, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoNotIn(List<String> values) {
            addCriterion("FMRCH_NO not in", values, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoBetween(String value1, String value2) {
            addCriterion("FMRCH_NO between", value1, value2, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrchNoNotBetween(String value1, String value2) {
            addCriterion("FMRCH_NO not between", value1, value2, "fmrchNo");
            return (Criteria) this;
        }

        public Criteria andFmrNameIsNull() {
            addCriterion("FMR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFmrNameIsNotNull() {
            addCriterion("FMR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFmrNameEqualTo(String value) {
            addCriterion("FMR_NAME =", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameNotEqualTo(String value) {
            addCriterion("FMR_NAME <>", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameGreaterThan(String value) {
            addCriterion("FMR_NAME >", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameGreaterThanOrEqualTo(String value) {
            addCriterion("FMR_NAME >=", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameLessThan(String value) {
            addCriterion("FMR_NAME <", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameLessThanOrEqualTo(String value) {
            addCriterion("FMR_NAME <=", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameLike(String value) {
            addCriterion("FMR_NAME like", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameNotLike(String value) {
            addCriterion("FMR_NAME not like", value, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameIn(List<String> values) {
            addCriterion("FMR_NAME in", values, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameNotIn(List<String> values) {
            addCriterion("FMR_NAME not in", values, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameBetween(String value1, String value2) {
            addCriterion("FMR_NAME between", value1, value2, "fmrName");
            return (Criteria) this;
        }

        public Criteria andFmrNameNotBetween(String value1, String value2) {
            addCriterion("FMR_NAME not between", value1, value2, "fmrName");
            return (Criteria) this;
        }

        public Criteria andStlDateIsNull() {
            addCriterion("STL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStlDateIsNotNull() {
            addCriterion("STL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStlDateEqualTo(String value) {
            addCriterion("STL_DATE =", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotEqualTo(String value) {
            addCriterion("STL_DATE <>", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateGreaterThan(String value) {
            addCriterion("STL_DATE >", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateGreaterThanOrEqualTo(String value) {
            addCriterion("STL_DATE >=", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLessThan(String value) {
            addCriterion("STL_DATE <", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLessThanOrEqualTo(String value) {
            addCriterion("STL_DATE <=", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateLike(String value) {
            addCriterion("STL_DATE like", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotLike(String value) {
            addCriterion("STL_DATE not like", value, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateIn(List<String> values) {
            addCriterion("STL_DATE in", values, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotIn(List<String> values) {
            addCriterion("STL_DATE not in", values, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateBetween(String value1, String value2) {
            addCriterion("STL_DATE between", value1, value2, "stlDate");
            return (Criteria) this;
        }

        public Criteria andStlDateNotBetween(String value1, String value2) {
            addCriterion("STL_DATE not between", value1, value2, "stlDate");
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

        public Criteria andSceneIsNull() {
            addCriterion("SCENE is null");
            return (Criteria) this;
        }

        public Criteria andSceneIsNotNull() {
            addCriterion("SCENE is not null");
            return (Criteria) this;
        }

        public Criteria andSceneEqualTo(String value) {
            addCriterion("SCENE =", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotEqualTo(String value) {
            addCriterion("SCENE <>", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThan(String value) {
            addCriterion("SCENE >", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThanOrEqualTo(String value) {
            addCriterion("SCENE >=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThan(String value) {
            addCriterion("SCENE <", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThanOrEqualTo(String value) {
            addCriterion("SCENE <=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLike(String value) {
            addCriterion("SCENE like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotLike(String value) {
            addCriterion("SCENE not like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneIn(List<String> values) {
            addCriterion("SCENE in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotIn(List<String> values) {
            addCriterion("SCENE not in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneBetween(String value1, String value2) {
            addCriterion("SCENE between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotBetween(String value1, String value2) {
            addCriterion("SCENE not between", value1, value2, "scene");
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

        public Criteria andFeestlTypeIsNull() {
            addCriterion("FEESTL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeIsNotNull() {
            addCriterion("FEESTL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeEqualTo(String value) {
            addCriterion("FEESTL_TYPE =", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeNotEqualTo(String value) {
            addCriterion("FEESTL_TYPE <>", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeGreaterThan(String value) {
            addCriterion("FEESTL_TYPE >", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FEESTL_TYPE >=", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeLessThan(String value) {
            addCriterion("FEESTL_TYPE <", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeLessThanOrEqualTo(String value) {
            addCriterion("FEESTL_TYPE <=", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeLike(String value) {
            addCriterion("FEESTL_TYPE like", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeNotLike(String value) {
            addCriterion("FEESTL_TYPE not like", value, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeIn(List<String> values) {
            addCriterion("FEESTL_TYPE in", values, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeNotIn(List<String> values) {
            addCriterion("FEESTL_TYPE not in", values, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeBetween(String value1, String value2) {
            addCriterion("FEESTL_TYPE between", value1, value2, "feestlType");
            return (Criteria) this;
        }

        public Criteria andFeestlTypeNotBetween(String value1, String value2) {
            addCriterion("FEESTL_TYPE not between", value1, value2, "feestlType");
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

        public Criteria andAccNameIsNull() {
            addCriterion("ACC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNotNull() {
            addCriterion("ACC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccNameEqualTo(String value) {
            addCriterion("ACC_NAME =", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotEqualTo(String value) {
            addCriterion("ACC_NAME <>", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThan(String value) {
            addCriterion("ACC_NAME >", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NAME >=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThan(String value) {
            addCriterion("ACC_NAME <", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_NAME <=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLike(String value) {
            addCriterion("ACC_NAME like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotLike(String value) {
            addCriterion("ACC_NAME not like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameIn(List<String> values) {
            addCriterion("ACC_NAME in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotIn(List<String> values) {
            addCriterion("ACC_NAME not in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameBetween(String value1, String value2) {
            addCriterion("ACC_NAME between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotBetween(String value1, String value2) {
            addCriterion("ACC_NAME not between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
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

        public Criteria andBranchNameIsNull() {
            addCriterion("BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("BRANCH_NAME =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("BRANCH_NAME <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("BRANCH_NAME >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("BRANCH_NAME <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("BRANCH_NAME like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("BRANCH_NAME not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("BRANCH_NAME in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("BRANCH_NAME not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andUnionNoIsNull() {
            addCriterion("UNION_NO is null");
            return (Criteria) this;
        }

        public Criteria andUnionNoIsNotNull() {
            addCriterion("UNION_NO is not null");
            return (Criteria) this;
        }

        public Criteria andUnionNoEqualTo(String value) {
            addCriterion("UNION_NO =", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoNotEqualTo(String value) {
            addCriterion("UNION_NO <>", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoGreaterThan(String value) {
            addCriterion("UNION_NO >", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoGreaterThanOrEqualTo(String value) {
            addCriterion("UNION_NO >=", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoLessThan(String value) {
            addCriterion("UNION_NO <", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoLessThanOrEqualTo(String value) {
            addCriterion("UNION_NO <=", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoLike(String value) {
            addCriterion("UNION_NO like", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoNotLike(String value) {
            addCriterion("UNION_NO not like", value, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoIn(List<String> values) {
            addCriterion("UNION_NO in", values, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoNotIn(List<String> values) {
            addCriterion("UNION_NO not in", values, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoBetween(String value1, String value2) {
            addCriterion("UNION_NO between", value1, value2, "unionNo");
            return (Criteria) this;
        }

        public Criteria andUnionNoNotBetween(String value1, String value2) {
            addCriterion("UNION_NO not between", value1, value2, "unionNo");
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

        public Criteria andIsConsumpCategoryIsNull() {
            addCriterion("IS_CONSUMP_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryIsNotNull() {
            addCriterion("IS_CONSUMP_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY =", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <>", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryGreaterThan(String value) {
            addCriterion("IS_CONSUMP_CATEGORY >", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY >=", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLessThan(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLessThanOrEqualTo(String value) {
            addCriterion("IS_CONSUMP_CATEGORY <=", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryLike(String value) {
            addCriterion("IS_CONSUMP_CATEGORY like", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotLike(String value) {
            addCriterion("IS_CONSUMP_CATEGORY not like", value, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryIn(List<String> values) {
            addCriterion("IS_CONSUMP_CATEGORY in", values, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotIn(List<String> values) {
            addCriterion("IS_CONSUMP_CATEGORY not in", values, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryBetween(String value1, String value2) {
            addCriterion("IS_CONSUMP_CATEGORY between", value1, value2, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andIsConsumpCategoryNotBetween(String value1, String value2) {
            addCriterion("IS_CONSUMP_CATEGORY not between", value1, value2, "isConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIsNull() {
            addCriterion("AMT_CONSUMP_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIsNotNull() {
            addCriterion("AMT_CONSUMP_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY =", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <>", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryGreaterThan(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY >", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY >=", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLessThan(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLessThanOrEqualTo(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY <=", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryLike(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY like", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotLike(String value) {
            addCriterion("AMT_CONSUMP_CATEGORY not like", value, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryIn(List<String> values) {
            addCriterion("AMT_CONSUMP_CATEGORY in", values, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotIn(List<String> values) {
            addCriterion("AMT_CONSUMP_CATEGORY not in", values, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryBetween(String value1, String value2) {
            addCriterion("AMT_CONSUMP_CATEGORY between", value1, value2, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andAmtConsumpCategoryNotBetween(String value1, String value2) {
            addCriterion("AMT_CONSUMP_CATEGORY not between", value1, value2, "amtConsumpCategory");
            return (Criteria) this;
        }

        public Criteria andGroupFlagIsNull() {
            addCriterion("GROUP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlagIsNotNull() {
            addCriterion("GROUP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlagEqualTo(String value) {
            addCriterion("GROUP_FLAG =", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagNotEqualTo(String value) {
            addCriterion("GROUP_FLAG <>", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagGreaterThan(String value) {
            addCriterion("GROUP_FLAG >", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLAG >=", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagLessThan(String value) {
            addCriterion("GROUP_FLAG <", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLAG <=", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagLike(String value) {
            addCriterion("GROUP_FLAG like", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagNotLike(String value) {
            addCriterion("GROUP_FLAG not like", value, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagIn(List<String> values) {
            addCriterion("GROUP_FLAG in", values, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagNotIn(List<String> values) {
            addCriterion("GROUP_FLAG not in", values, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagBetween(String value1, String value2) {
            addCriterion("GROUP_FLAG between", value1, value2, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andGroupFlagNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLAG not between", value1, value2, "groupFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdIsNull() {
            addCriterion("FINAL_STL_ID is null");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdIsNotNull() {
            addCriterion("FINAL_STL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdEqualTo(String value) {
            addCriterion("FINAL_STL_ID =", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdNotEqualTo(String value) {
            addCriterion("FINAL_STL_ID <>", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdGreaterThan(String value) {
            addCriterion("FINAL_STL_ID >", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdGreaterThanOrEqualTo(String value) {
            addCriterion("FINAL_STL_ID >=", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdLessThan(String value) {
            addCriterion("FINAL_STL_ID <", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdLessThanOrEqualTo(String value) {
            addCriterion("FINAL_STL_ID <=", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdLike(String value) {
            addCriterion("FINAL_STL_ID like", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdNotLike(String value) {
            addCriterion("FINAL_STL_ID not like", value, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdIn(List<String> values) {
            addCriterion("FINAL_STL_ID in", values, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdNotIn(List<String> values) {
            addCriterion("FINAL_STL_ID not in", values, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdBetween(String value1, String value2) {
            addCriterion("FINAL_STL_ID between", value1, value2, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlIdNotBetween(String value1, String value2) {
            addCriterion("FINAL_STL_ID not between", value1, value2, "finalStlId");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagIsNull() {
            addCriterion("FINAL_STL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagIsNotNull() {
            addCriterion("FINAL_STL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagEqualTo(String value) {
            addCriterion("FINAL_STL_FLAG =", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagNotEqualTo(String value) {
            addCriterion("FINAL_STL_FLAG <>", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagGreaterThan(String value) {
            addCriterion("FINAL_STL_FLAG >", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FINAL_STL_FLAG >=", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagLessThan(String value) {
            addCriterion("FINAL_STL_FLAG <", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagLessThanOrEqualTo(String value) {
            addCriterion("FINAL_STL_FLAG <=", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagLike(String value) {
            addCriterion("FINAL_STL_FLAG like", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagNotLike(String value) {
            addCriterion("FINAL_STL_FLAG not like", value, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagIn(List<String> values) {
            addCriterion("FINAL_STL_FLAG in", values, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagNotIn(List<String> values) {
            addCriterion("FINAL_STL_FLAG not in", values, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagBetween(String value1, String value2) {
            addCriterion("FINAL_STL_FLAG between", value1, value2, "finalStlFlag");
            return (Criteria) this;
        }

        public Criteria andFinalStlFlagNotBetween(String value1, String value2) {
            addCriterion("FINAL_STL_FLAG not between", value1, value2, "finalStlFlag");
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
            addCriterion("PROVINCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("PROVINCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(Long value) {
            addCriterion("PROVINCE_CODE =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(Long value) {
            addCriterion("PROVINCE_CODE <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(Long value) {
            addCriterion("PROVINCE_CODE >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("PROVINCE_CODE >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(Long value) {
            addCriterion("PROVINCE_CODE <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(Long value) {
            addCriterion("PROVINCE_CODE <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<Long> values) {
            addCriterion("PROVINCE_CODE in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<Long> values) {
            addCriterion("PROVINCE_CODE not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(Long value1, Long value2) {
            addCriterion("PROVINCE_CODE between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(Long value1, Long value2) {
            addCriterion("PROVINCE_CODE not between", value1, value2, "provinceCode");
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
            addCriterion("BJ_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBjFlagIsNotNull() {
            addCriterion("BJ_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBjFlagEqualTo(String value) {
            addCriterion("BJ_FLAG =", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotEqualTo(String value) {
            addCriterion("BJ_FLAG <>", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagGreaterThan(String value) {
            addCriterion("BJ_FLAG >", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BJ_FLAG >=", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLessThan(String value) {
            addCriterion("BJ_FLAG <", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLessThanOrEqualTo(String value) {
            addCriterion("BJ_FLAG <=", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagLike(String value) {
            addCriterion("BJ_FLAG like", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotLike(String value) {
            addCriterion("BJ_FLAG not like", value, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagIn(List<String> values) {
            addCriterion("BJ_FLAG in", values, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotIn(List<String> values) {
            addCriterion("BJ_FLAG not in", values, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagBetween(String value1, String value2) {
            addCriterion("BJ_FLAG between", value1, value2, "bjFlag");
            return (Criteria) this;
        }

        public Criteria andBjFlagNotBetween(String value1, String value2) {
            addCriterion("BJ_FLAG not between", value1, value2, "bjFlag");
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

        public Criteria andExelusiveCardFlagIsNull() {
            addCriterion("EXELUSIVE_CARD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagIsNotNull() {
            addCriterion("EXELUSIVE_CARD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagEqualTo(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG =", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotEqualTo(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG <>", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagGreaterThan(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG >", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG >=", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLessThan(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG <", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLessThanOrEqualTo(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG <=", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagLike(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG like", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotLike(String value) {
            addCriterion("EXELUSIVE_CARD_FLAG not like", value, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagIn(List<String> values) {
            addCriterion("EXELUSIVE_CARD_FLAG in", values, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotIn(List<String> values) {
            addCriterion("EXELUSIVE_CARD_FLAG not in", values, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagBetween(String value1, String value2) {
            addCriterion("EXELUSIVE_CARD_FLAG between", value1, value2, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andExelusiveCardFlagNotBetween(String value1, String value2) {
            addCriterion("EXELUSIVE_CARD_FLAG not between", value1, value2, "exelusiveCardFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagIsNull() {
            addCriterion("STL_NEED_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagIsNotNull() {
            addCriterion("STL_NEED_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagEqualTo(String value) {
            addCriterion("STL_NEED_FLAG =", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotEqualTo(String value) {
            addCriterion("STL_NEED_FLAG <>", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagGreaterThan(String value) {
            addCriterion("STL_NEED_FLAG >", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagGreaterThanOrEqualTo(String value) {
            addCriterion("STL_NEED_FLAG >=", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLessThan(String value) {
            addCriterion("STL_NEED_FLAG <", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLessThanOrEqualTo(String value) {
            addCriterion("STL_NEED_FLAG <=", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagLike(String value) {
            addCriterion("STL_NEED_FLAG like", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotLike(String value) {
            addCriterion("STL_NEED_FLAG not like", value, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagIn(List<String> values) {
            addCriterion("STL_NEED_FLAG in", values, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotIn(List<String> values) {
            addCriterion("STL_NEED_FLAG not in", values, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagBetween(String value1, String value2) {
            addCriterion("STL_NEED_FLAG between", value1, value2, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andStlNeedFlagNotBetween(String value1, String value2) {
            addCriterion("STL_NEED_FLAG not between", value1, value2, "stlNeedFlag");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("CITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("CITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(Long value) {
            addCriterion("CITY_CODE =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(Long value) {
            addCriterion("CITY_CODE <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(Long value) {
            addCriterion("CITY_CODE >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("CITY_CODE >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(Long value) {
            addCriterion("CITY_CODE <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(Long value) {
            addCriterion("CITY_CODE <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<Long> values) {
            addCriterion("CITY_CODE in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<Long> values) {
            addCriterion("CITY_CODE not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(Long value1, Long value2) {
            addCriterion("CITY_CODE between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(Long value1, Long value2) {
            addCriterion("CITY_CODE not between", value1, value2, "cityCode");
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

        public Criteria andMrchSnippetIsNull() {
            addCriterion("MRCH_SNIPPET is null");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetIsNotNull() {
            addCriterion("MRCH_SNIPPET is not null");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetEqualTo(String value) {
            addCriterion("MRCH_SNIPPET =", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetNotEqualTo(String value) {
            addCriterion("MRCH_SNIPPET <>", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetGreaterThan(String value) {
            addCriterion("MRCH_SNIPPET >", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetGreaterThanOrEqualTo(String value) {
            addCriterion("MRCH_SNIPPET >=", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetLessThan(String value) {
            addCriterion("MRCH_SNIPPET <", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetLessThanOrEqualTo(String value) {
            addCriterion("MRCH_SNIPPET <=", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetLike(String value) {
            addCriterion("MRCH_SNIPPET like", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetNotLike(String value) {
            addCriterion("MRCH_SNIPPET not like", value, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetIn(List<String> values) {
            addCriterion("MRCH_SNIPPET in", values, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetNotIn(List<String> values) {
            addCriterion("MRCH_SNIPPET not in", values, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetBetween(String value1, String value2) {
            addCriterion("MRCH_SNIPPET between", value1, value2, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMrchSnippetNotBetween(String value1, String value2) {
            addCriterion("MRCH_SNIPPET not between", value1, value2, "mrchSnippet");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagIsNull() {
            addCriterion("MERGE_MONEY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagIsNotNull() {
            addCriterion("MERGE_MONEY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagEqualTo(String value) {
            addCriterion("MERGE_MONEY_FLAG =", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagNotEqualTo(String value) {
            addCriterion("MERGE_MONEY_FLAG <>", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagGreaterThan(String value) {
            addCriterion("MERGE_MONEY_FLAG >", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MERGE_MONEY_FLAG >=", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagLessThan(String value) {
            addCriterion("MERGE_MONEY_FLAG <", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagLessThanOrEqualTo(String value) {
            addCriterion("MERGE_MONEY_FLAG <=", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagLike(String value) {
            addCriterion("MERGE_MONEY_FLAG like", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagNotLike(String value) {
            addCriterion("MERGE_MONEY_FLAG not like", value, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagIn(List<String> values) {
            addCriterion("MERGE_MONEY_FLAG in", values, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagNotIn(List<String> values) {
            addCriterion("MERGE_MONEY_FLAG not in", values, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagBetween(String value1, String value2) {
            addCriterion("MERGE_MONEY_FLAG between", value1, value2, "mergeMoneyFlag");
            return (Criteria) this;
        }

        public Criteria andMergeMoneyFlagNotBetween(String value1, String value2) {
            addCriterion("MERGE_MONEY_FLAG not between", value1, value2, "mergeMoneyFlag");
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
            addCriterion("RESERVED_FIELD_1 is null");
            return (Criteria) this;
        }

        public Criteria andReservedField1IsNotNull() {
            addCriterion("RESERVED_FIELD_1 is not null");
            return (Criteria) this;
        }

        public Criteria andReservedField1EqualTo(String value) {
            addCriterion("RESERVED_FIELD_1 =", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotEqualTo(String value) {
            addCriterion("RESERVED_FIELD_1 <>", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1GreaterThan(String value) {
            addCriterion("RESERVED_FIELD_1 >", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD_1 >=", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1LessThan(String value) {
            addCriterion("RESERVED_FIELD_1 <", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1LessThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD_1 <=", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1Like(String value) {
            addCriterion("RESERVED_FIELD_1 like", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotLike(String value) {
            addCriterion("RESERVED_FIELD_1 not like", value, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1In(List<String> values) {
            addCriterion("RESERVED_FIELD_1 in", values, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotIn(List<String> values) {
            addCriterion("RESERVED_FIELD_1 not in", values, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1Between(String value1, String value2) {
            addCriterion("RESERVED_FIELD_1 between", value1, value2, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField1NotBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD_1 not between", value1, value2, "reservedField1");
            return (Criteria) this;
        }

        public Criteria andReservedField2IsNull() {
            addCriterion("RESERVED_FIELD_2 is null");
            return (Criteria) this;
        }

        public Criteria andReservedField2IsNotNull() {
            addCriterion("RESERVED_FIELD_2 is not null");
            return (Criteria) this;
        }

        public Criteria andReservedField2EqualTo(String value) {
            addCriterion("RESERVED_FIELD_2 =", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotEqualTo(String value) {
            addCriterion("RESERVED_FIELD_2 <>", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2GreaterThan(String value) {
            addCriterion("RESERVED_FIELD_2 >", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD_2 >=", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2LessThan(String value) {
            addCriterion("RESERVED_FIELD_2 <", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2LessThanOrEqualTo(String value) {
            addCriterion("RESERVED_FIELD_2 <=", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2Like(String value) {
            addCriterion("RESERVED_FIELD_2 like", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotLike(String value) {
            addCriterion("RESERVED_FIELD_2 not like", value, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2In(List<String> values) {
            addCriterion("RESERVED_FIELD_2 in", values, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotIn(List<String> values) {
            addCriterion("RESERVED_FIELD_2 not in", values, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2Between(String value1, String value2) {
            addCriterion("RESERVED_FIELD_2 between", value1, value2, "reservedField2");
            return (Criteria) this;
        }

        public Criteria andReservedField2NotBetween(String value1, String value2) {
            addCriterion("RESERVED_FIELD_2 not between", value1, value2, "reservedField2");
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