package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearTransactionAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearTransactionAccountExample() {
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

        public Criteria andDescrIsNull() {
            addCriterion("DESCR is null");
            return (Criteria) this;
        }

        public Criteria andDescrIsNotNull() {
            addCriterion("DESCR is not null");
            return (Criteria) this;
        }

        public Criteria andDescrEqualTo(String value) {
            addCriterion("DESCR =", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotEqualTo(String value) {
            addCriterion("DESCR <>", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThan(String value) {
            addCriterion("DESCR >", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThanOrEqualTo(String value) {
            addCriterion("DESCR >=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThan(String value) {
            addCriterion("DESCR <", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThanOrEqualTo(String value) {
            addCriterion("DESCR <=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLike(String value) {
            addCriterion("DESCR like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotLike(String value) {
            addCriterion("DESCR not like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrIn(List<String> values) {
            addCriterion("DESCR in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotIn(List<String> values) {
            addCriterion("DESCR not in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrBetween(String value1, String value2) {
            addCriterion("DESCR between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotBetween(String value1, String value2) {
            addCriterion("DESCR not between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andIidIsNull() {
            addCriterion("IID is null");
            return (Criteria) this;
        }

        public Criteria andIidIsNotNull() {
            addCriterion("IID is not null");
            return (Criteria) this;
        }

        public Criteria andIidEqualTo(String value) {
            addCriterion("IID =", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotEqualTo(String value) {
            addCriterion("IID <>", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThan(String value) {
            addCriterion("IID >", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThanOrEqualTo(String value) {
            addCriterion("IID >=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThan(String value) {
            addCriterion("IID <", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThanOrEqualTo(String value) {
            addCriterion("IID <=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLike(String value) {
            addCriterion("IID like", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotLike(String value) {
            addCriterion("IID not like", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidIn(List<String> values) {
            addCriterion("IID in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotIn(List<String> values) {
            addCriterion("IID not in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidBetween(String value1, String value2) {
            addCriterion("IID between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotBetween(String value1, String value2) {
            addCriterion("IID not between", value1, value2, "iid");
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
            addCriterion("TRANSACTIONDATE >=", value, "startDt");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThan(String value) {
            addCriterion("TRANSACTIONDATE <", value, "transactiondate");
            return (Criteria) this;
        }

        public Criteria andTransactiondateLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONDATE <=", value, "endDt");
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