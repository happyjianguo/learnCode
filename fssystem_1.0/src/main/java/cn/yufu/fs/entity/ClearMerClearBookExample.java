package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearMerClearBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearMerClearBookExample() {
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

        public Criteria andSrcIdIsNull() {
            addCriterion("SRC_ID is null");
            return (Criteria) this;
        }

        public Criteria andSrcIdIsNotNull() {
            addCriterion("SRC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSrcIdEqualTo(String value) {
            addCriterion("SRC_ID =", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotEqualTo(String value) {
            addCriterion("SRC_ID <>", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdGreaterThan(String value) {
            addCriterion("SRC_ID >", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdGreaterThanOrEqualTo(String value) {
            addCriterion("SRC_ID >=", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLessThan(String value) {
            addCriterion("SRC_ID <", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLessThanOrEqualTo(String value) {
            addCriterion("SRC_ID <=", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLike(String value) {
            addCriterion("SRC_ID like", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotLike(String value) {
            addCriterion("SRC_ID not like", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdIn(List<String> values) {
            addCriterion("SRC_ID in", values, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotIn(List<String> values) {
            addCriterion("SRC_ID not in", values, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdBetween(String value1, String value2) {
            addCriterion("SRC_ID between", value1, value2, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotBetween(String value1, String value2) {
            addCriterion("SRC_ID not between", value1, value2, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcFlagIsNull() {
            addCriterion("SRC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSrcFlagIsNotNull() {
            addCriterion("SRC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSrcFlagEqualTo(String value) {
            addCriterion("SRC_FLAG =", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagNotEqualTo(String value) {
            addCriterion("SRC_FLAG <>", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagGreaterThan(String value) {
            addCriterion("SRC_FLAG >", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SRC_FLAG >=", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagLessThan(String value) {
            addCriterion("SRC_FLAG <", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagLessThanOrEqualTo(String value) {
            addCriterion("SRC_FLAG <=", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagLike(String value) {
            addCriterion("SRC_FLAG like", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagNotLike(String value) {
            addCriterion("SRC_FLAG not like", value, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagIn(List<String> values) {
            addCriterion("SRC_FLAG in", values, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagNotIn(List<String> values) {
            addCriterion("SRC_FLAG not in", values, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagBetween(String value1, String value2) {
            addCriterion("SRC_FLAG between", value1, value2, "srcFlag");
            return (Criteria) this;
        }

        public Criteria andSrcFlagNotBetween(String value1, String value2) {
            addCriterion("SRC_FLAG not between", value1, value2, "srcFlag");
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

        public Criteria andFmrchNameIsNull() {
            addCriterion("FMRCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFmrchNameIsNotNull() {
            addCriterion("FMRCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFmrchNameEqualTo(String value) {
            addCriterion("FMRCH_NAME =", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameNotEqualTo(String value) {
            addCriterion("FMRCH_NAME <>", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameGreaterThan(String value) {
            addCriterion("FMRCH_NAME >", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameGreaterThanOrEqualTo(String value) {
            addCriterion("FMRCH_NAME >=", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameLessThan(String value) {
            addCriterion("FMRCH_NAME <", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameLessThanOrEqualTo(String value) {
            addCriterion("FMRCH_NAME <=", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameLike(String value) {
            addCriterion("FMRCH_NAME like", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameNotLike(String value) {
            addCriterion("FMRCH_NAME not like", value, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameIn(List<String> values) {
            addCriterion("FMRCH_NAME in", values, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameNotIn(List<String> values) {
            addCriterion("FMRCH_NAME not in", values, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameBetween(String value1, String value2) {
            addCriterion("FMRCH_NAME between", value1, value2, "fmrchName");
            return (Criteria) this;
        }

        public Criteria andFmrchNameNotBetween(String value1, String value2) {
            addCriterion("FMRCH_NAME not between", value1, value2, "fmrchName");
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

        public Criteria andTranNumIsNull() {
            addCriterion("TRAN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTranNumIsNotNull() {
            addCriterion("TRAN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTranNumEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM =", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM <>", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumGreaterThan(BigDecimal value) {
            addCriterion("TRAN_NUM >", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM >=", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumLessThan(BigDecimal value) {
            addCriterion("TRAN_NUM <", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRAN_NUM <=", value, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumIn(List<BigDecimal> values) {
            addCriterion("TRAN_NUM in", values, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotIn(List<BigDecimal> values) {
            addCriterion("TRAN_NUM not in", values, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_NUM between", value1, value2, "tranNum");
            return (Criteria) this;
        }

        public Criteria andTranNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRAN_NUM not between", value1, value2, "tranNum");
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

        public Criteria andRefNumIsNull() {
            addCriterion("REF_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRefNumIsNotNull() {
            addCriterion("REF_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRefNumEqualTo(BigDecimal value) {
            addCriterion("REF_NUM =", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotEqualTo(BigDecimal value) {
            addCriterion("REF_NUM <>", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumGreaterThan(BigDecimal value) {
            addCriterion("REF_NUM >", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REF_NUM >=", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumLessThan(BigDecimal value) {
            addCriterion("REF_NUM <", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REF_NUM <=", value, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumIn(List<BigDecimal> values) {
            addCriterion("REF_NUM in", values, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotIn(List<BigDecimal> values) {
            addCriterion("REF_NUM not in", values, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REF_NUM between", value1, value2, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REF_NUM not between", value1, value2, "refNum");
            return (Criteria) this;
        }

        public Criteria andRefAmtIsNull() {
            addCriterion("REF_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRefAmtIsNotNull() {
            addCriterion("REF_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRefAmtEqualTo(BigDecimal value) {
            addCriterion("REF_AMT =", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotEqualTo(BigDecimal value) {
            addCriterion("REF_AMT <>", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtGreaterThan(BigDecimal value) {
            addCriterion("REF_AMT >", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REF_AMT >=", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtLessThan(BigDecimal value) {
            addCriterion("REF_AMT <", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REF_AMT <=", value, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtIn(List<BigDecimal> values) {
            addCriterion("REF_AMT in", values, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotIn(List<BigDecimal> values) {
            addCriterion("REF_AMT not in", values, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REF_AMT between", value1, value2, "refAmt");
            return (Criteria) this;
        }

        public Criteria andRefAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REF_AMT not between", value1, value2, "refAmt");
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

        public Criteria andFeeTypeIsNull() {
            addCriterion("FEE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIsNotNull() {
            addCriterion("FEE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeEqualTo(String value) {
            addCriterion("FEE_TYPE =", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotEqualTo(String value) {
            addCriterion("FEE_TYPE <>", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThan(String value) {
            addCriterion("FEE_TYPE >", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_TYPE >=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThan(String value) {
            addCriterion("FEE_TYPE <", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThanOrEqualTo(String value) {
            addCriterion("FEE_TYPE <=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLike(String value) {
            addCriterion("FEE_TYPE like", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotLike(String value) {
            addCriterion("FEE_TYPE not like", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIn(List<String> values) {
            addCriterion("FEE_TYPE in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotIn(List<String> values) {
            addCriterion("FEE_TYPE not in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeBetween(String value1, String value2) {
            addCriterion("FEE_TYPE between", value1, value2, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotBetween(String value1, String value2) {
            addCriterion("FEE_TYPE not between", value1, value2, "feeType");
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

        public Criteria andStlBookIdIsNull() {
            addCriterion("STL_BOOK_ID is null");
            return (Criteria) this;
        }

        public Criteria andStlBookIdIsNotNull() {
            addCriterion("STL_BOOK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStlBookIdEqualTo(String value) {
            addCriterion("STL_BOOK_ID =", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdNotEqualTo(String value) {
            addCriterion("STL_BOOK_ID <>", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdGreaterThan(String value) {
            addCriterion("STL_BOOK_ID >", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdGreaterThanOrEqualTo(String value) {
            addCriterion("STL_BOOK_ID >=", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdLessThan(String value) {
            addCriterion("STL_BOOK_ID <", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdLessThanOrEqualTo(String value) {
            addCriterion("STL_BOOK_ID <=", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdLike(String value) {
            addCriterion("STL_BOOK_ID like", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdNotLike(String value) {
            addCriterion("STL_BOOK_ID not like", value, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdIn(List<String> values) {
            addCriterion("STL_BOOK_ID in", values, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdNotIn(List<String> values) {
            addCriterion("STL_BOOK_ID not in", values, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdBetween(String value1, String value2) {
            addCriterion("STL_BOOK_ID between", value1, value2, "stlBookId");
            return (Criteria) this;
        }

        public Criteria andStlBookIdNotBetween(String value1, String value2) {
            addCriterion("STL_BOOK_ID not between", value1, value2, "stlBookId");
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

        public Criteria andFeeStlIdIsNull() {
            addCriterion("FEE_STL_ID is null");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdIsNotNull() {
            addCriterion("FEE_STL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdEqualTo(String value) {
            addCriterion("FEE_STL_ID =", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdNotEqualTo(String value) {
            addCriterion("FEE_STL_ID <>", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdGreaterThan(String value) {
            addCriterion("FEE_STL_ID >", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_STL_ID >=", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdLessThan(String value) {
            addCriterion("FEE_STL_ID <", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdLessThanOrEqualTo(String value) {
            addCriterion("FEE_STL_ID <=", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdLike(String value) {
            addCriterion("FEE_STL_ID like", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdNotLike(String value) {
            addCriterion("FEE_STL_ID not like", value, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdIn(List<String> values) {
            addCriterion("FEE_STL_ID in", values, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdNotIn(List<String> values) {
            addCriterion("FEE_STL_ID not in", values, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdBetween(String value1, String value2) {
            addCriterion("FEE_STL_ID between", value1, value2, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeStlIdNotBetween(String value1, String value2) {
            addCriterion("FEE_STL_ID not between", value1, value2, "feeStlId");
            return (Criteria) this;
        }

        public Criteria andFeeFlagIsNull() {
            addCriterion("FEE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFeeFlagIsNotNull() {
            addCriterion("FEE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFeeFlagEqualTo(String value) {
            addCriterion("FEE_FLAG =", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagNotEqualTo(String value) {
            addCriterion("FEE_FLAG <>", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagGreaterThan(String value) {
            addCriterion("FEE_FLAG >", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_FLAG >=", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagLessThan(String value) {
            addCriterion("FEE_FLAG <", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagLessThanOrEqualTo(String value) {
            addCriterion("FEE_FLAG <=", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagLike(String value) {
            addCriterion("FEE_FLAG like", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagNotLike(String value) {
            addCriterion("FEE_FLAG not like", value, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagIn(List<String> values) {
            addCriterion("FEE_FLAG in", values, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagNotIn(List<String> values) {
            addCriterion("FEE_FLAG not in", values, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagBetween(String value1, String value2) {
            addCriterion("FEE_FLAG between", value1, value2, "feeFlag");
            return (Criteria) this;
        }

        public Criteria andFeeFlagNotBetween(String value1, String value2) {
            addCriterion("FEE_FLAG not between", value1, value2, "feeFlag");
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