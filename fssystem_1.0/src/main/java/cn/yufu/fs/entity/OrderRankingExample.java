package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRankingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderRankingExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andCellPhoneIsNull() {
            addCriterion("CELL_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andCellPhoneIsNotNull() {
            addCriterion("CELL_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andCellPhoneEqualTo(String value) {
            addCriterion("CELL_PHONE =", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNotEqualTo(String value) {
            addCriterion("CELL_PHONE <>", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneGreaterThan(String value) {
            addCriterion("CELL_PHONE >", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CELL_PHONE >=", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneLessThan(String value) {
            addCriterion("CELL_PHONE <", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneLessThanOrEqualTo(String value) {
            addCriterion("CELL_PHONE <=", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneLike(String value) {
            addCriterion("CELL_PHONE like", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNotLike(String value) {
            addCriterion("CELL_PHONE not like", value, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneIn(List<String> values) {
            addCriterion("CELL_PHONE in", values, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNotIn(List<String> values) {
            addCriterion("CELL_PHONE not in", values, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneBetween(String value1, String value2) {
            addCriterion("CELL_PHONE between", value1, value2, "cellPhone");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNotBetween(String value1, String value2) {
            addCriterion("CELL_PHONE not between", value1, value2, "cellPhone");
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

        public Criteria andPanAccountIsNull() {
            addCriterion("PAN_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPanAccountIsNotNull() {
            addCriterion("PAN_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPanAccountEqualTo(Long value) {
            addCriterion("PAN_ACCOUNT =", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountNotEqualTo(Long value) {
            addCriterion("PAN_ACCOUNT <>", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountGreaterThan(Long value) {
            addCriterion("PAN_ACCOUNT >", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountGreaterThanOrEqualTo(Long value) {
            addCriterion("PAN_ACCOUNT >=", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountLessThan(Long value) {
            addCriterion("PAN_ACCOUNT <", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountLessThanOrEqualTo(Long value) {
            addCriterion("PAN_ACCOUNT <=", value, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountIn(List<Long> values) {
            addCriterion("PAN_ACCOUNT in", values, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountNotIn(List<Long> values) {
            addCriterion("PAN_ACCOUNT not in", values, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountBetween(Long value1, Long value2) {
            addCriterion("PAN_ACCOUNT between", value1, value2, "panAccount");
            return (Criteria) this;
        }

        public Criteria andPanAccountNotBetween(Long value1, Long value2) {
            addCriterion("PAN_ACCOUNT not between", value1, value2, "panAccount");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdIsNull() {
            addCriterion("AMT_EACH_CRD is null");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdIsNotNull() {
            addCriterion("AMT_EACH_CRD is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdEqualTo(BigDecimal value) {
            addCriterion("AMT_EACH_CRD =", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdNotEqualTo(BigDecimal value) {
            addCriterion("AMT_EACH_CRD <>", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdGreaterThan(BigDecimal value) {
            addCriterion("AMT_EACH_CRD >", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMT_EACH_CRD >=", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdLessThan(BigDecimal value) {
            addCriterion("AMT_EACH_CRD <", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMT_EACH_CRD <=", value, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdIn(List<BigDecimal> values) {
            addCriterion("AMT_EACH_CRD in", values, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdNotIn(List<BigDecimal> values) {
            addCriterion("AMT_EACH_CRD not in", values, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMT_EACH_CRD between", value1, value2, "amtEachCrd");
            return (Criteria) this;
        }

        public Criteria andAmtEachCrdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMT_EACH_CRD not between", value1, value2, "amtEachCrd");
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

        public Criteria andCardNunIsNull() {
            addCriterion("CARD_NUN is null");
            return (Criteria) this;
        }

        public Criteria andCardNunIsNotNull() {
            addCriterion("CARD_NUN is not null");
            return (Criteria) this;
        }

        public Criteria andCardNunEqualTo(String value) {
            addCriterion("CARD_NUN =", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunNotEqualTo(String value) {
            addCriterion("CARD_NUN <>", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunGreaterThan(String value) {
            addCriterion("CARD_NUN >", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NUN >=", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunLessThan(String value) {
            addCriterion("CARD_NUN <", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunLessThanOrEqualTo(String value) {
            addCriterion("CARD_NUN <=", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunLike(String value) {
            addCriterion("CARD_NUN like", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunNotLike(String value) {
            addCriterion("CARD_NUN not like", value, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunIn(List<String> values) {
            addCriterion("CARD_NUN in", values, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunNotIn(List<String> values) {
            addCriterion("CARD_NUN not in", values, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunBetween(String value1, String value2) {
            addCriterion("CARD_NUN between", value1, value2, "cardNun");
            return (Criteria) this;
        }

        public Criteria andCardNunNotBetween(String value1, String value2) {
            addCriterion("CARD_NUN not between", value1, value2, "cardNun");
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

        public Criteria andFreeField1IsNull() {
            addCriterion("FREE_FIELD1 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField1IsNotNull() {
            addCriterion("FREE_FIELD1 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField1EqualTo(String value) {
            addCriterion("FREE_FIELD1 =", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1NotEqualTo(String value) {
            addCriterion("FREE_FIELD1 <>", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1GreaterThan(String value) {
            addCriterion("FREE_FIELD1 >", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD1 >=", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1LessThan(String value) {
            addCriterion("FREE_FIELD1 <", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD1 <=", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1Like(String value) {
            addCriterion("FREE_FIELD1 like", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1NotLike(String value) {
            addCriterion("FREE_FIELD1 not like", value, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1In(List<String> values) {
            addCriterion("FREE_FIELD1 in", values, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1NotIn(List<String> values) {
            addCriterion("FREE_FIELD1 not in", values, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1Between(String value1, String value2) {
            addCriterion("FREE_FIELD1 between", value1, value2, "freeField1");
            return (Criteria) this;
        }

        public Criteria andFreeField1NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD1 not between", value1, value2, "freeField1");
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

        public Criteria andFreeField4IsNull() {
            addCriterion("FREE_FIELD4 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField4IsNotNull() {
            addCriterion("FREE_FIELD4 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField4EqualTo(String value) {
            addCriterion("FREE_FIELD4 =", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotEqualTo(String value) {
            addCriterion("FREE_FIELD4 <>", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4GreaterThan(String value) {
            addCriterion("FREE_FIELD4 >", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD4 >=", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4LessThan(String value) {
            addCriterion("FREE_FIELD4 <", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD4 <=", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4Like(String value) {
            addCriterion("FREE_FIELD4 like", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotLike(String value) {
            addCriterion("FREE_FIELD4 not like", value, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4In(List<String> values) {
            addCriterion("FREE_FIELD4 in", values, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotIn(List<String> values) {
            addCriterion("FREE_FIELD4 not in", values, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4Between(String value1, String value2) {
            addCriterion("FREE_FIELD4 between", value1, value2, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField4NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD4 not between", value1, value2, "freeField4");
            return (Criteria) this;
        }

        public Criteria andFreeField5IsNull() {
            addCriterion("FREE_FIELD5 is null");
            return (Criteria) this;
        }

        public Criteria andFreeField5IsNotNull() {
            addCriterion("FREE_FIELD5 is not null");
            return (Criteria) this;
        }

        public Criteria andFreeField5EqualTo(String value) {
            addCriterion("FREE_FIELD5 =", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotEqualTo(String value) {
            addCriterion("FREE_FIELD5 <>", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5GreaterThan(String value) {
            addCriterion("FREE_FIELD5 >", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5GreaterThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD5 >=", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5LessThan(String value) {
            addCriterion("FREE_FIELD5 <", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5LessThanOrEqualTo(String value) {
            addCriterion("FREE_FIELD5 <=", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5Like(String value) {
            addCriterion("FREE_FIELD5 like", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotLike(String value) {
            addCriterion("FREE_FIELD5 not like", value, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5In(List<String> values) {
            addCriterion("FREE_FIELD5 in", values, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotIn(List<String> values) {
            addCriterion("FREE_FIELD5 not in", values, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5Between(String value1, String value2) {
            addCriterion("FREE_FIELD5 between", value1, value2, "freeField5");
            return (Criteria) this;
        }

        public Criteria andFreeField5NotBetween(String value1, String value2) {
            addCriterion("FREE_FIELD5 not between", value1, value2, "freeField5");
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