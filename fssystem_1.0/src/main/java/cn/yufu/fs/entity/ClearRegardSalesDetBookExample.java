package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClearRegardSalesDetBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClearRegardSalesDetBookExample() {
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

        public Criteria andSalesProvinceIsNull() {
            addCriterion("SALES_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceIsNotNull() {
            addCriterion("SALES_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceEqualTo(String value) {
            addCriterion("SALES_PROVINCE =", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotEqualTo(String value) {
            addCriterion("SALES_PROVINCE <>", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceGreaterThan(String value) {
            addCriterion("SALES_PROVINCE >", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("SALES_PROVINCE >=", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLessThan(String value) {
            addCriterion("SALES_PROVINCE <", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLessThanOrEqualTo(String value) {
            addCriterion("SALES_PROVINCE <=", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLike(String value) {
            addCriterion("SALES_PROVINCE like", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotLike(String value) {
            addCriterion("SALES_PROVINCE not like", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceIn(List<String> values) {
            addCriterion("SALES_PROVINCE in", values, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotIn(List<String> values) {
            addCriterion("SALES_PROVINCE not in", values, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceBetween(String value1, String value2) {
            addCriterion("SALES_PROVINCE between", value1, value2, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotBetween(String value1, String value2) {
            addCriterion("SALES_PROVINCE not between", value1, value2, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesCityIsNull() {
            addCriterion("SALES_CITY is null");
            return (Criteria) this;
        }

        public Criteria andSalesCityIsNotNull() {
            addCriterion("SALES_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andSalesCityEqualTo(Long value) {
            addCriterion("SALES_CITY =", value, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityNotEqualTo(Long value) {
            addCriterion("SALES_CITY <>", value, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityGreaterThan(Long value) {
            addCriterion("SALES_CITY >", value, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityGreaterThanOrEqualTo(Long value) {
            addCriterion("SALES_CITY >=", value, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityLessThan(Long value) {
            addCriterion("SALES_CITY <", value, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityLessThanOrEqualTo(Long value) {
            addCriterion("SALES_CITY <=", value, "salesCity");
            return (Criteria) this;
        }


        public Criteria andSalesCityIn(List<Long> values) {
            addCriterion("SALES_CITY in", values, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityNotIn(List<Long> values) {
            addCriterion("SALES_CITY not in", values, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityBetween(Long value1, Long value2) {
            addCriterion("SALES_CITY between", value1, value2, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesCityNotBetween(Long value1, Long value2) {
            addCriterion("SALES_CITY not between", value1, value2, "salesCity");
            return (Criteria) this;
        }

        public Criteria andSalesZoneIsNull() {
            addCriterion("SALES_ZONE is null");
            return (Criteria) this;
        }

        public Criteria andSalesZoneIsNotNull() {
            addCriterion("SALES_ZONE is not null");
            return (Criteria) this;
        }

        public Criteria andSalesZoneEqualTo(String value) {
            addCriterion("SALES_ZONE =", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneNotEqualTo(String value) {
            addCriterion("SALES_ZONE <>", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneGreaterThan(String value) {
            addCriterion("SALES_ZONE >", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneGreaterThanOrEqualTo(String value) {
            addCriterion("SALES_ZONE >=", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneLessThan(String value) {
            addCriterion("SALES_ZONE <", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneLessThanOrEqualTo(String value) {
            addCriterion("SALES_ZONE <=", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneLike(String value) {
            addCriterion("SALES_ZONE like", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneNotLike(String value) {
            addCriterion("SALES_ZONE not like", value, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneIn(List<String> values) {
            addCriterion("SALES_ZONE in", values, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneNotIn(List<String> values) {
            addCriterion("SALES_ZONE not in", values, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneBetween(String value1, String value2) {
            addCriterion("SALES_ZONE between", value1, value2, "salesZone");
            return (Criteria) this;
        }

        public Criteria andSalesZoneNotBetween(String value1, String value2) {
            addCriterion("SALES_ZONE not between", value1, value2, "salesZone");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceIsNull() {
            addCriterion("CONSUM_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceIsNotNull() {
            addCriterion("CONSUM_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceEqualTo(String value) {
            addCriterion("CONSUM_PROVINCE =", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceNotEqualTo(String value) {
            addCriterion("CONSUM_PROVINCE <>", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceGreaterThan(String value) {
            addCriterion("CONSUM_PROVINCE >", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUM_PROVINCE >=", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceLessThan(String value) {
            addCriterion("CONSUM_PROVINCE <", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceLessThanOrEqualTo(String value) {
            addCriterion("CONSUM_PROVINCE <=", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceLike(String value) {
            addCriterion("CONSUM_PROVINCE like", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceNotLike(String value) {
            addCriterion("CONSUM_PROVINCE not like", value, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceIn(List<String> values) {
            addCriterion("CONSUM_PROVINCE in", values, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceNotIn(List<String> values) {
            addCriterion("CONSUM_PROVINCE not in", values, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceBetween(String value1, String value2) {
            addCriterion("CONSUM_PROVINCE between", value1, value2, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumProvinceNotBetween(String value1, String value2) {
            addCriterion("CONSUM_PROVINCE not between", value1, value2, "consumProvince");
            return (Criteria) this;
        }

        public Criteria andConsumCityIsNull() {
            addCriterion("CONSUM_CITY is null");
            return (Criteria) this;
        }

        public Criteria andConsumCityIsNotNull() {
            addCriterion("CONSUM_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andConsumCityEqualTo(Long value) {
            addCriterion("CONSUM_CITY =", value, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityNotEqualTo(Long value) {
            addCriterion("CONSUM_CITY <>", value, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityGreaterThan(Long value) {
            addCriterion("CONSUM_CITY >", value, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityGreaterThanOrEqualTo(Long value) {
            addCriterion("CONSUM_CITY >=", value, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityLessThan(Long value) {
            addCriterion("CONSUM_CITY <", value, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityLessThanOrEqualTo(Long value) {
            addCriterion("CONSUM_CITY <=", value, "consumCity");
            return (Criteria) this;
        }

       
        public Criteria andConsumCityIn(List<Long> values) {
            addCriterion("CONSUM_CITY in", values, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityNotIn(List<Long> values) {
            addCriterion("CONSUM_CITY not in", values, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityBetween(Long value1, Long value2) {
            addCriterion("CONSUM_CITY between", value1, value2, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumCityNotBetween(Long value1, Long value2) {
            addCriterion("CONSUM_CITY not between", value1, value2, "consumCity");
            return (Criteria) this;
        }

        public Criteria andConsumZoneIsNull() {
            addCriterion("CONSUM_ZONE is null");
            return (Criteria) this;
        }

        public Criteria andConsumZoneIsNotNull() {
            addCriterion("CONSUM_ZONE is not null");
            return (Criteria) this;
        }

        public Criteria andConsumZoneEqualTo(String value) {
            addCriterion("CONSUM_ZONE =", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneNotEqualTo(String value) {
            addCriterion("CONSUM_ZONE <>", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneGreaterThan(String value) {
            addCriterion("CONSUM_ZONE >", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUM_ZONE >=", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneLessThan(String value) {
            addCriterion("CONSUM_ZONE <", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneLessThanOrEqualTo(String value) {
            addCriterion("CONSUM_ZONE <=", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneLike(String value) {
            addCriterion("CONSUM_ZONE like", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneNotLike(String value) {
            addCriterion("CONSUM_ZONE not like", value, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneIn(List<String> values) {
            addCriterion("CONSUM_ZONE in", values, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneNotIn(List<String> values) {
            addCriterion("CONSUM_ZONE not in", values, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneBetween(String value1, String value2) {
            addCriterion("CONSUM_ZONE between", value1, value2, "consumZone");
            return (Criteria) this;
        }

        public Criteria andConsumZoneNotBetween(String value1, String value2) {
            addCriterion("CONSUM_ZONE not between", value1, value2, "consumZone");
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