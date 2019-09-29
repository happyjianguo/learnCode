package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearFeeBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearFeeBookExample() {
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

        public Criteria andGenDateIsNull() {
            addCriterion("GEN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andGenDateIsNotNull() {
            addCriterion("GEN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andGenDateEqualTo(String value) {
            addCriterion("SUBSTR(GEN_DATE,0,8) =", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotEqualTo(String value) {
            addCriterion("GEN_DATE <>", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateGreaterThan(String value) {
            addCriterion("GEN_DATE >", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateGreaterThanOrEqualTo(String value) {
            addCriterion("GEN_DATE >=", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateLessThan(String value) {
            addCriterion("GEN_DATE <", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateLessThanOrEqualTo(String value) {
            addCriterion("GEN_DATE <=", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateLike(String value) {
            addCriterion("GEN_DATE like", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotLike(String value) {
            addCriterion("GEN_DATE not like", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateIn(List<String> values) {
            addCriterion("GEN_DATE in", values, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotIn(List<String> values) {
            addCriterion("GEN_DATE not in", values, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateBetween(String value1, String value2) {
            addCriterion("GEN_DATE between", value1, value2, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotBetween(String value1, String value2) {
            addCriterion("GEN_DATE not between", value1, value2, "genDate");
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

        public Criteria andHeadOfficeIsNull() {
            addCriterion("HEAD_OFFICE is null");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeIsNotNull() {
            addCriterion("HEAD_OFFICE is not null");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeEqualTo(String value) {
            addCriterion("HEAD_OFFICE =", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotEqualTo(String value) {
            addCriterion("HEAD_OFFICE <>", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeGreaterThan(String value) {
            addCriterion("HEAD_OFFICE >", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_OFFICE >=", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLessThan(String value) {
            addCriterion("HEAD_OFFICE <", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLessThanOrEqualTo(String value) {
            addCriterion("HEAD_OFFICE <=", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeLike(String value) {
            addCriterion("HEAD_OFFICE like", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotLike(String value) {
            addCriterion("HEAD_OFFICE not like", value, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeIn(List<String> values) {
            addCriterion("HEAD_OFFICE in", values, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotIn(List<String> values) {
            addCriterion("HEAD_OFFICE not in", values, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeBetween(String value1, String value2) {
            addCriterion("HEAD_OFFICE between", value1, value2, "headOffice");
            return (Criteria) this;
        }

        public Criteria andHeadOfficeNotBetween(String value1, String value2) {
            addCriterion("HEAD_OFFICE not between", value1, value2, "headOffice");
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

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
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

        public Criteria andExpressDateIsNull() {
            addCriterion("EXPRESS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExpressDateIsNotNull() {
            addCriterion("EXPRESS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpressDateEqualTo(String value) {
            addCriterion("EXPRESS_DATE =", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateNotEqualTo(String value) {
            addCriterion("EXPRESS_DATE <>", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateGreaterThan(String value) {
            addCriterion("EXPRESS_DATE >", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_DATE >=", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateLessThan(String value) {
            addCriterion("EXPRESS_DATE <", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_DATE <=", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateLike(String value) {
            addCriterion("EXPRESS_DATE like", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateNotLike(String value) {
            addCriterion("EXPRESS_DATE not like", value, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateIn(List<String> values) {
            addCriterion("EXPRESS_DATE in", values, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateNotIn(List<String> values) {
            addCriterion("EXPRESS_DATE not in", values, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateBetween(String value1, String value2) {
            addCriterion("EXPRESS_DATE between", value1, value2, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressDateNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_DATE not between", value1, value2, "expressDate");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("EXPRESS_NO =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("EXPRESS_NO <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("EXPRESS_NO >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("EXPRESS_NO <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("EXPRESS_NO like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("EXPRESS_NO not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("EXPRESS_NO in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("EXPRESS_NO not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoIsNull() {
            addCriterion("OFFICE_NO is null");
            return (Criteria) this;
        }

        public Criteria andOfficeNoIsNotNull() {
            addCriterion("OFFICE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeNoEqualTo(String value) {
            addCriterion("OFFICE_NO =", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoNotEqualTo(String value) {
            addCriterion("OFFICE_NO <>", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoGreaterThan(String value) {
            addCriterion("OFFICE_NO >", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICE_NO >=", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoLessThan(String value) {
            addCriterion("OFFICE_NO <", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoLessThanOrEqualTo(String value) {
            addCriterion("OFFICE_NO <=", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoLike(String value) {
            addCriterion("OFFICE_NO like", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoNotLike(String value) {
            addCriterion("OFFICE_NO not like", value, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoIn(List<String> values) {
            addCriterion("OFFICE_NO in", values, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoNotIn(List<String> values) {
            addCriterion("OFFICE_NO not in", values, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoBetween(String value1, String value2) {
            addCriterion("OFFICE_NO between", value1, value2, "officeNo");
            return (Criteria) this;
        }

        public Criteria andOfficeNoNotBetween(String value1, String value2) {
            addCriterion("OFFICE_NO not between", value1, value2, "officeNo");
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

        public Criteria andIsKpIsNull() {
            addCriterion("IS_KP is null");
            return (Criteria) this;
        }

        public Criteria andIsKpIsNotNull() {
            addCriterion("IS_KP is not null");
            return (Criteria) this;
        }

        public Criteria andIsKpEqualTo(String value) {
            addCriterion("IS_KP =", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotEqualTo(String value) {
            addCriterion("IS_KP <>", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpGreaterThan(String value) {
            addCriterion("IS_KP >", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpGreaterThanOrEqualTo(String value) {
            addCriterion("IS_KP >=", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLessThan(String value) {
            addCriterion("IS_KP <", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLessThanOrEqualTo(String value) {
            addCriterion("IS_KP <=", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpLike(String value) {
            addCriterion("IS_KP like", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotLike(String value) {
            addCriterion("IS_KP not like", value, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpIn(List<String> values) {
            addCriterion("IS_KP in", values, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotIn(List<String> values) {
            addCriterion("IS_KP not in", values, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpBetween(String value1, String value2) {
            addCriterion("IS_KP between", value1, value2, "isKp");
            return (Criteria) this;
        }

        public Criteria andIsKpNotBetween(String value1, String value2) {
            addCriterion("IS_KP not between", value1, value2, "isKp");
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