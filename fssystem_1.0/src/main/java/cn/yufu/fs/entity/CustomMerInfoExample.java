package cn.yufu.fs.entity;

import java.util.ArrayList;
import java.util.List;

public class CustomMerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomMerInfoExample() {
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

        public Criteria andBatchIdIsNull() {
            addCriterion("BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("BATCH_ID =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("BATCH_ID <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("BATCH_ID >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_ID >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("BATCH_ID <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("BATCH_ID <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLike(String value) {
            addCriterion("BATCH_ID like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotLike(String value) {
            addCriterion("BATCH_ID not like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("BATCH_ID in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("BATCH_ID not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("BATCH_ID between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("BATCH_ID not between", value1, value2, "batchId");
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

        public Criteria andCompanyNameIsNull() {
            addCriterion("COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("COMPANY_NAME =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("COMPANY_NAME <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("COMPANY_NAME >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("COMPANY_NAME <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("COMPANY_NAME like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("COMPANY_NAME not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("COMPANY_NAME in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("COMPANY_NAME not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andTranStartDateIsNull() {
            addCriterion("TRAN_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTranStartDateIsNotNull() {
            addCriterion("TRAN_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTranStartDateEqualTo(String value) {
            addCriterion("TRAN_START_DATE =", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateNotEqualTo(String value) {
            addCriterion("TRAN_START_DATE <>", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateGreaterThan(String value) {
            addCriterion("TRAN_START_DATE >", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_START_DATE >=", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateLessThan(String value) {
            addCriterion("TRAN_START_DATE <", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateLessThanOrEqualTo(String value) {
            addCriterion("TRAN_START_DATE <=", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateLike(String value) {
            addCriterion("TRAN_START_DATE like", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateNotLike(String value) {
            addCriterion("TRAN_START_DATE not like", value, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateIn(List<String> values) {
            addCriterion("TRAN_START_DATE in", values, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateNotIn(List<String> values) {
            addCriterion("TRAN_START_DATE not in", values, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateBetween(String value1, String value2) {
            addCriterion("TRAN_START_DATE between", value1, value2, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranStartDateNotBetween(String value1, String value2) {
            addCriterion("TRAN_START_DATE not between", value1, value2, "tranStartDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateIsNull() {
            addCriterion("TRAN_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTranEndDateIsNotNull() {
            addCriterion("TRAN_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTranEndDateEqualTo(String value) {
            addCriterion("TRAN_END_DATE =", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateNotEqualTo(String value) {
            addCriterion("TRAN_END_DATE <>", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateGreaterThan(String value) {
            addCriterion("TRAN_END_DATE >", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAN_END_DATE >=", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateLessThan(String value) {
            addCriterion("TRAN_END_DATE <", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateLessThanOrEqualTo(String value) {
            addCriterion("TRAN_END_DATE <=", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateLike(String value) {
            addCriterion("TRAN_END_DATE like", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateNotLike(String value) {
            addCriterion("TRAN_END_DATE not like", value, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateIn(List<String> values) {
            addCriterion("TRAN_END_DATE in", values, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateNotIn(List<String> values) {
            addCriterion("TRAN_END_DATE not in", values, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateBetween(String value1, String value2) {
            addCriterion("TRAN_END_DATE between", value1, value2, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andTranEndDateNotBetween(String value1, String value2) {
            addCriterion("TRAN_END_DATE not between", value1, value2, "tranEndDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("ORDER_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("ORDER_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(String value) {
            addCriterion("ORDER_DATE =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(String value) {
            addCriterion("ORDER_DATE <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(String value) {
            addCriterion("ORDER_DATE >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_DATE >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(String value) {
            addCriterion("ORDER_DATE <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(String value) {
            addCriterion("ORDER_DATE <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLike(String value) {
            addCriterion("ORDER_DATE like", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotLike(String value) {
            addCriterion("ORDER_DATE not like", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<String> values) {
            addCriterion("ORDER_DATE in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<String> values) {
            addCriterion("ORDER_DATE not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(String value1, String value2) {
            addCriterion("ORDER_DATE between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(String value1, String value2) {
            addCriterion("ORDER_DATE not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNull() {
            addCriterion("DEAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNotNull() {
            addCriterion("DEAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDealDateEqualTo(String value) {
            addCriterion("DEAL_DATE =", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotEqualTo(String value) {
            addCriterion("DEAL_DATE <>", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThan(String value) {
            addCriterion("DEAL_DATE >", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_DATE >=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThan(String value) {
            addCriterion("DEAL_DATE <", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThanOrEqualTo(String value) {
            addCriterion("DEAL_DATE <=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLike(String value) {
            addCriterion("DEAL_DATE like", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotLike(String value) {
            addCriterion("DEAL_DATE not like", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateIn(List<String> values) {
            addCriterion("DEAL_DATE in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotIn(List<String> values) {
            addCriterion("DEAL_DATE not in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateBetween(String value1, String value2) {
            addCriterion("DEAL_DATE between", value1, value2, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotBetween(String value1, String value2) {
            addCriterion("DEAL_DATE not between", value1, value2, "dealDate");
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

        public Criteria andChoiceFlagIsNull() {
            addCriterion("CHOICE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagIsNotNull() {
            addCriterion("CHOICE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagEqualTo(String value) {
            addCriterion("CHOICE_FLAG =", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagNotEqualTo(String value) {
            addCriterion("CHOICE_FLAG <>", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagGreaterThan(String value) {
            addCriterion("CHOICE_FLAG >", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CHOICE_FLAG >=", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagLessThan(String value) {
            addCriterion("CHOICE_FLAG <", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagLessThanOrEqualTo(String value) {
            addCriterion("CHOICE_FLAG <=", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagLike(String value) {
            addCriterion("CHOICE_FLAG like", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagNotLike(String value) {
            addCriterion("CHOICE_FLAG not like", value, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagIn(List<String> values) {
            addCriterion("CHOICE_FLAG in", values, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagNotIn(List<String> values) {
            addCriterion("CHOICE_FLAG not in", values, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagBetween(String value1, String value2) {
            addCriterion("CHOICE_FLAG between", value1, value2, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andChoiceFlagNotBetween(String value1, String value2) {
            addCriterion("CHOICE_FLAG not between", value1, value2, "choiceFlag");
            return (Criteria) this;
        }

        public Criteria andFreeField2IsNull() {
            addCriterion("FREE_FIELD2 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField2IsNotNull() {
            addCriterion("FREE_FIELD2 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField2EqualTo(String value) {
            addCriterion("FREE_FIELD2 =", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2NotEqualTo(String value) {
            addCriterion("FREE_FIELD2 <>", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2GreaterThan(String value) {
            addCriterion("FREE_FIELD2 >", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD2 >=", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2LessThan(String value) {
            addCriterion("FREE_FIELD2 <", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD2 <=", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2Like(String value) {
            addCriterion("FREE_FIELD2 like", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2NotLike(String value) {
            addCriterion("FREE_FIELD2 not like", value, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2In(List<String> values) {
            addCriterion("FREE_FIELD2 in", values, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2NotIn(List<String> values) {
            addCriterion("FREE_FIELD2 not in", values, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2Between(String value1, String value2) {
            addCriterion("FREE_FIELD2 between", value1, value2, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField2NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD2 not between", value1, value2, "freeField2");
            return (Criteria) this;
        }

        public Criteria andFreeField3IsNull() {
            addCriterion("FREE_FIELD3 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField3IsNotNull() {
            addCriterion("FREE_FIELD3 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField3EqualTo(String value) {
            addCriterion("FREE_FIELD3 =", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotEqualTo(String value) {
            addCriterion("FREE_FIELD3 <>", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3GreaterThan(String value) {
            addCriterion("FREE_FIELD3 >", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD3 >=", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3LessThan(String value) {
            addCriterion("FREE_FIELD3 <", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD3 <=", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3Like(String value) {
            addCriterion("FREE_FIELD3 like", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotLike(String value) {
            addCriterion("FREE_FIELD3 not like", value, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3In(List<String> values) {
            addCriterion("FREE_FIELD3 in", values, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotIn(List<String> values) {
            addCriterion("FREE_FIELD3 not in", values, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3Between(String value1, String value2) {
            addCriterion("FREE_FIELD3 between", value1, value2, "freeField3");
            return (Criteria) this;
        }

        public Criteria andFreeField3NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD3 not between", value1, value2, "freeField3");
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