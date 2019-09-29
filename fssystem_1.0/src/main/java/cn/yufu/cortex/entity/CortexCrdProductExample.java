package cn.yufu.cortex.entity;

import java.util.ArrayList;
import java.util.List;

public class CortexCrdProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CortexCrdProductExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIsNull() {
            addCriterion("VERNO_CTX is null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIsNotNull() {
            addCriterion("VERNO_CTX is not null");
            return (Criteria) this;
        }

        public Criteria andVernoCtxEqualTo(Long value) {
            addCriterion("VERNO_CTX =", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotEqualTo(Long value) {
            addCriterion("VERNO_CTX <>", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThan(Long value) {
            addCriterion("VERNO_CTX >", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxGreaterThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX >=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThan(Long value) {
            addCriterion("VERNO_CTX <", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxLessThanOrEqualTo(Long value) {
            addCriterion("VERNO_CTX <=", value, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxIn(List<Long> values) {
            addCriterion("VERNO_CTX in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotIn(List<Long> values) {
            addCriterion("VERNO_CTX not in", values, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andVernoCtxNotBetween(Long value1, Long value2) {
            addCriterion("VERNO_CTX not between", value1, value2, "vernoCtx");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNull() {
            addCriterion("INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNotNull() {
            addCriterion("INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdEqualTo(Long value) {
            addCriterion("INST_ID =", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotEqualTo(Long value) {
            addCriterion("INST_ID <>", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThan(Long value) {
            addCriterion("INST_ID >", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INST_ID >=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThan(Long value) {
            addCriterion("INST_ID <", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThanOrEqualTo(Long value) {
            addCriterion("INST_ID <=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdIn(List<Long> values) {
            addCriterion("INST_ID in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotIn(List<Long> values) {
            addCriterion("INST_ID not in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdBetween(Long value1, Long value2) {
            addCriterion("INST_ID between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotBetween(Long value1, Long value2) {
            addCriterion("INST_ID not between", value1, value2, "instId");
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

        public Criteria andCrdformatIdIsNull() {
            addCriterion("CRDFORMAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdIsNotNull() {
            addCriterion("CRDFORMAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdEqualTo(Long value) {
            addCriterion("CRDFORMAT_ID =", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdNotEqualTo(Long value) {
            addCriterion("CRDFORMAT_ID <>", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdGreaterThan(Long value) {
            addCriterion("CRDFORMAT_ID >", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CRDFORMAT_ID >=", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdLessThan(Long value) {
            addCriterion("CRDFORMAT_ID <", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdLessThanOrEqualTo(Long value) {
            addCriterion("CRDFORMAT_ID <=", value, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdIn(List<Long> values) {
            addCriterion("CRDFORMAT_ID in", values, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdNotIn(List<Long> values) {
            addCriterion("CRDFORMAT_ID not in", values, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdBetween(Long value1, Long value2) {
            addCriterion("CRDFORMAT_ID between", value1, value2, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andCrdformatIdNotBetween(Long value1, Long value2) {
            addCriterion("CRDFORMAT_ID not between", value1, value2, "crdformatId");
            return (Criteria) this;
        }

        public Criteria andAffinityIsNull() {
            addCriterion("AFFINITY is null");
            return (Criteria) this;
        }

        public Criteria andAffinityIsNotNull() {
            addCriterion("AFFINITY is not null");
            return (Criteria) this;
        }

        public Criteria andAffinityEqualTo(String value) {
            addCriterion("AFFINITY =", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityNotEqualTo(String value) {
            addCriterion("AFFINITY <>", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityGreaterThan(String value) {
            addCriterion("AFFINITY >", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityGreaterThanOrEqualTo(String value) {
            addCriterion("AFFINITY >=", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityLessThan(String value) {
            addCriterion("AFFINITY <", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityLessThanOrEqualTo(String value) {
            addCriterion("AFFINITY <=", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityLike(String value) {
            addCriterion("AFFINITY like", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityNotLike(String value) {
            addCriterion("AFFINITY not like", value, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityIn(List<String> values) {
            addCriterion("AFFINITY in", values, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityNotIn(List<String> values) {
            addCriterion("AFFINITY not in", values, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityBetween(String value1, String value2) {
            addCriterion("AFFINITY between", value1, value2, "affinity");
            return (Criteria) this;
        }

        public Criteria andAffinityNotBetween(String value1, String value2) {
            addCriterion("AFFINITY not between", value1, value2, "affinity");
            return (Criteria) this;
        }

        public Criteria andAciIsNull() {
            addCriterion("ACI is null");
            return (Criteria) this;
        }

        public Criteria andAciIsNotNull() {
            addCriterion("ACI is not null");
            return (Criteria) this;
        }

        public Criteria andAciEqualTo(String value) {
            addCriterion("ACI =", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciNotEqualTo(String value) {
            addCriterion("ACI <>", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciGreaterThan(String value) {
            addCriterion("ACI >", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciGreaterThanOrEqualTo(String value) {
            addCriterion("ACI >=", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciLessThan(String value) {
            addCriterion("ACI <", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciLessThanOrEqualTo(String value) {
            addCriterion("ACI <=", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciLike(String value) {
            addCriterion("ACI like", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciNotLike(String value) {
            addCriterion("ACI not like", value, "aci");
            return (Criteria) this;
        }

        public Criteria andAciIn(List<String> values) {
            addCriterion("ACI in", values, "aci");
            return (Criteria) this;
        }

        public Criteria andAciNotIn(List<String> values) {
            addCriterion("ACI not in", values, "aci");
            return (Criteria) this;
        }

        public Criteria andAciBetween(String value1, String value2) {
            addCriterion("ACI between", value1, value2, "aci");
            return (Criteria) this;
        }

        public Criteria andAciNotBetween(String value1, String value2) {
            addCriterion("ACI not between", value1, value2, "aci");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccIsNull() {
            addCriterion("DEF_TYPEACC is null");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccIsNotNull() {
            addCriterion("DEF_TYPEACC is not null");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccEqualTo(String value) {
            addCriterion("DEF_TYPEACC =", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccNotEqualTo(String value) {
            addCriterion("DEF_TYPEACC <>", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccGreaterThan(String value) {
            addCriterion("DEF_TYPEACC >", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccGreaterThanOrEqualTo(String value) {
            addCriterion("DEF_TYPEACC >=", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccLessThan(String value) {
            addCriterion("DEF_TYPEACC <", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccLessThanOrEqualTo(String value) {
            addCriterion("DEF_TYPEACC <=", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccLike(String value) {
            addCriterion("DEF_TYPEACC like", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccNotLike(String value) {
            addCriterion("DEF_TYPEACC not like", value, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccIn(List<String> values) {
            addCriterion("DEF_TYPEACC in", values, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccNotIn(List<String> values) {
            addCriterion("DEF_TYPEACC not in", values, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccBetween(String value1, String value2) {
            addCriterion("DEF_TYPEACC between", value1, value2, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andDefTypeaccNotBetween(String value1, String value2) {
            addCriterion("DEF_TYPEACC not between", value1, value2, "defTypeacc");
            return (Criteria) this;
        }

        public Criteria andProdnumIsNull() {
            addCriterion("PRODNUM is null");
            return (Criteria) this;
        }

        public Criteria andProdnumIsNotNull() {
            addCriterion("PRODNUM is not null");
            return (Criteria) this;
        }

        public Criteria andProdnumEqualTo(Integer value) {
            addCriterion("PRODNUM =", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumNotEqualTo(Integer value) {
            addCriterion("PRODNUM <>", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumGreaterThan(Integer value) {
            addCriterion("PRODNUM >", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRODNUM >=", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumLessThan(Integer value) {
            addCriterion("PRODNUM <", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumLessThanOrEqualTo(Integer value) {
            addCriterion("PRODNUM <=", value, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumIn(List<Integer> values) {
            addCriterion("PRODNUM in", values, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumNotIn(List<Integer> values) {
            addCriterion("PRODNUM not in", values, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumBetween(Integer value1, Integer value2) {
            addCriterion("PRODNUM between", value1, value2, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdnumNotBetween(Integer value1, Integer value2) {
            addCriterion("PRODNUM not between", value1, value2, "prodnum");
            return (Criteria) this;
        }

        public Criteria andProdseqIsNull() {
            addCriterion("PRODSEQ is null");
            return (Criteria) this;
        }

        public Criteria andProdseqIsNotNull() {
            addCriterion("PRODSEQ is not null");
            return (Criteria) this;
        }

        public Criteria andProdseqEqualTo(Long value) {
            addCriterion("PRODSEQ =", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqNotEqualTo(Long value) {
            addCriterion("PRODSEQ <>", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqGreaterThan(Long value) {
            addCriterion("PRODSEQ >", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODSEQ >=", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqLessThan(Long value) {
            addCriterion("PRODSEQ <", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqLessThanOrEqualTo(Long value) {
            addCriterion("PRODSEQ <=", value, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqIn(List<Long> values) {
            addCriterion("PRODSEQ in", values, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqNotIn(List<Long> values) {
            addCriterion("PRODSEQ not in", values, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqBetween(Long value1, Long value2) {
            addCriterion("PRODSEQ between", value1, value2, "prodseq");
            return (Criteria) this;
        }

        public Criteria andProdseqNotBetween(Long value1, Long value2) {
            addCriterion("PRODSEQ not between", value1, value2, "prodseq");
            return (Criteria) this;
        }

        public Criteria andPoints1IsNull() {
            addCriterion("POINTS1 is null");
            return (Criteria) this;
        }

        public Criteria andPoints1IsNotNull() {
            addCriterion("POINTS1 is not null");
            return (Criteria) this;
        }

        public Criteria andPoints1EqualTo(Integer value) {
            addCriterion("POINTS1 =", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1NotEqualTo(Integer value) {
            addCriterion("POINTS1 <>", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1GreaterThan(Integer value) {
            addCriterion("POINTS1 >", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1GreaterThanOrEqualTo(Integer value) {
            addCriterion("POINTS1 >=", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1LessThan(Integer value) {
            addCriterion("POINTS1 <", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1LessThanOrEqualTo(Integer value) {
            addCriterion("POINTS1 <=", value, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1In(List<Integer> values) {
            addCriterion("POINTS1 in", values, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1NotIn(List<Integer> values) {
            addCriterion("POINTS1 not in", values, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1Between(Integer value1, Integer value2) {
            addCriterion("POINTS1 between", value1, value2, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints1NotBetween(Integer value1, Integer value2) {
            addCriterion("POINTS1 not between", value1, value2, "points1");
            return (Criteria) this;
        }

        public Criteria andPoints2IsNull() {
            addCriterion("POINTS2 is null");
            return (Criteria) this;
        }

        public Criteria andPoints2IsNotNull() {
            addCriterion("POINTS2 is not null");
            return (Criteria) this;
        }

        public Criteria andPoints2EqualTo(Integer value) {
            addCriterion("POINTS2 =", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2NotEqualTo(Integer value) {
            addCriterion("POINTS2 <>", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2GreaterThan(Integer value) {
            addCriterion("POINTS2 >", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2GreaterThanOrEqualTo(Integer value) {
            addCriterion("POINTS2 >=", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2LessThan(Integer value) {
            addCriterion("POINTS2 <", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2LessThanOrEqualTo(Integer value) {
            addCriterion("POINTS2 <=", value, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2In(List<Integer> values) {
            addCriterion("POINTS2 in", values, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2NotIn(List<Integer> values) {
            addCriterion("POINTS2 not in", values, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2Between(Integer value1, Integer value2) {
            addCriterion("POINTS2 between", value1, value2, "points2");
            return (Criteria) this;
        }

        public Criteria andPoints2NotBetween(Integer value1, Integer value2) {
            addCriterion("POINTS2 not between", value1, value2, "points2");
            return (Criteria) this;
        }

        public Criteria andStmtLine1IsNull() {
            addCriterion("STMT_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andStmtLine1IsNotNull() {
            addCriterion("STMT_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andStmtLine1EqualTo(String value) {
            addCriterion("STMT_LINE1 =", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1NotEqualTo(String value) {
            addCriterion("STMT_LINE1 <>", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1GreaterThan(String value) {
            addCriterion("STMT_LINE1 >", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1GreaterThanOrEqualTo(String value) {
            addCriterion("STMT_LINE1 >=", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1LessThan(String value) {
            addCriterion("STMT_LINE1 <", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1LessThanOrEqualTo(String value) {
            addCriterion("STMT_LINE1 <=", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1Like(String value) {
            addCriterion("STMT_LINE1 like", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1NotLike(String value) {
            addCriterion("STMT_LINE1 not like", value, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1In(List<String> values) {
            addCriterion("STMT_LINE1 in", values, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1NotIn(List<String> values) {
            addCriterion("STMT_LINE1 not in", values, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1Between(String value1, String value2) {
            addCriterion("STMT_LINE1 between", value1, value2, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine1NotBetween(String value1, String value2) {
            addCriterion("STMT_LINE1 not between", value1, value2, "stmtLine1");
            return (Criteria) this;
        }

        public Criteria andStmtLine2IsNull() {
            addCriterion("STMT_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andStmtLine2IsNotNull() {
            addCriterion("STMT_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andStmtLine2EqualTo(String value) {
            addCriterion("STMT_LINE2 =", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2NotEqualTo(String value) {
            addCriterion("STMT_LINE2 <>", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2GreaterThan(String value) {
            addCriterion("STMT_LINE2 >", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2GreaterThanOrEqualTo(String value) {
            addCriterion("STMT_LINE2 >=", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2LessThan(String value) {
            addCriterion("STMT_LINE2 <", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2LessThanOrEqualTo(String value) {
            addCriterion("STMT_LINE2 <=", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2Like(String value) {
            addCriterion("STMT_LINE2 like", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2NotLike(String value) {
            addCriterion("STMT_LINE2 not like", value, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2In(List<String> values) {
            addCriterion("STMT_LINE2 in", values, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2NotIn(List<String> values) {
            addCriterion("STMT_LINE2 not in", values, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2Between(String value1, String value2) {
            addCriterion("STMT_LINE2 between", value1, value2, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine2NotBetween(String value1, String value2) {
            addCriterion("STMT_LINE2 not between", value1, value2, "stmtLine2");
            return (Criteria) this;
        }

        public Criteria andStmtLine3IsNull() {
            addCriterion("STMT_LINE3 is null");
            return (Criteria) this;
        }

        public Criteria andStmtLine3IsNotNull() {
            addCriterion("STMT_LINE3 is not null");
            return (Criteria) this;
        }

        public Criteria andStmtLine3EqualTo(String value) {
            addCriterion("STMT_LINE3 =", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3NotEqualTo(String value) {
            addCriterion("STMT_LINE3 <>", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3GreaterThan(String value) {
            addCriterion("STMT_LINE3 >", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3GreaterThanOrEqualTo(String value) {
            addCriterion("STMT_LINE3 >=", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3LessThan(String value) {
            addCriterion("STMT_LINE3 <", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3LessThanOrEqualTo(String value) {
            addCriterion("STMT_LINE3 <=", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3Like(String value) {
            addCriterion("STMT_LINE3 like", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3NotLike(String value) {
            addCriterion("STMT_LINE3 not like", value, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3In(List<String> values) {
            addCriterion("STMT_LINE3 in", values, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3NotIn(List<String> values) {
            addCriterion("STMT_LINE3 not in", values, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3Between(String value1, String value2) {
            addCriterion("STMT_LINE3 between", value1, value2, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andStmtLine3NotBetween(String value1, String value2) {
            addCriterion("STMT_LINE3 not between", value1, value2, "stmtLine3");
            return (Criteria) this;
        }

        public Criteria andPointsLine1IsNull() {
            addCriterion("POINTS_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andPointsLine1IsNotNull() {
            addCriterion("POINTS_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andPointsLine1EqualTo(String value) {
            addCriterion("POINTS_LINE1 =", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1NotEqualTo(String value) {
            addCriterion("POINTS_LINE1 <>", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1GreaterThan(String value) {
            addCriterion("POINTS_LINE1 >", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1GreaterThanOrEqualTo(String value) {
            addCriterion("POINTS_LINE1 >=", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1LessThan(String value) {
            addCriterion("POINTS_LINE1 <", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1LessThanOrEqualTo(String value) {
            addCriterion("POINTS_LINE1 <=", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1Like(String value) {
            addCriterion("POINTS_LINE1 like", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1NotLike(String value) {
            addCriterion("POINTS_LINE1 not like", value, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1In(List<String> values) {
            addCriterion("POINTS_LINE1 in", values, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1NotIn(List<String> values) {
            addCriterion("POINTS_LINE1 not in", values, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1Between(String value1, String value2) {
            addCriterion("POINTS_LINE1 between", value1, value2, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine1NotBetween(String value1, String value2) {
            addCriterion("POINTS_LINE1 not between", value1, value2, "pointsLine1");
            return (Criteria) this;
        }

        public Criteria andPointsLine2IsNull() {
            addCriterion("POINTS_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andPointsLine2IsNotNull() {
            addCriterion("POINTS_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andPointsLine2EqualTo(String value) {
            addCriterion("POINTS_LINE2 =", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2NotEqualTo(String value) {
            addCriterion("POINTS_LINE2 <>", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2GreaterThan(String value) {
            addCriterion("POINTS_LINE2 >", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2GreaterThanOrEqualTo(String value) {
            addCriterion("POINTS_LINE2 >=", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2LessThan(String value) {
            addCriterion("POINTS_LINE2 <", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2LessThanOrEqualTo(String value) {
            addCriterion("POINTS_LINE2 <=", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2Like(String value) {
            addCriterion("POINTS_LINE2 like", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2NotLike(String value) {
            addCriterion("POINTS_LINE2 not like", value, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2In(List<String> values) {
            addCriterion("POINTS_LINE2 in", values, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2NotIn(List<String> values) {
            addCriterion("POINTS_LINE2 not in", values, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2Between(String value1, String value2) {
            addCriterion("POINTS_LINE2 between", value1, value2, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andPointsLine2NotBetween(String value1, String value2) {
            addCriterion("POINTS_LINE2 not between", value1, value2, "pointsLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1IsNull() {
            addCriterion("OVERLIM_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1IsNotNull() {
            addCriterion("OVERLIM_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1EqualTo(String value) {
            addCriterion("OVERLIM_LINE1 =", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1NotEqualTo(String value) {
            addCriterion("OVERLIM_LINE1 <>", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1GreaterThan(String value) {
            addCriterion("OVERLIM_LINE1 >", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1GreaterThanOrEqualTo(String value) {
            addCriterion("OVERLIM_LINE1 >=", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1LessThan(String value) {
            addCriterion("OVERLIM_LINE1 <", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1LessThanOrEqualTo(String value) {
            addCriterion("OVERLIM_LINE1 <=", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1Like(String value) {
            addCriterion("OVERLIM_LINE1 like", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1NotLike(String value) {
            addCriterion("OVERLIM_LINE1 not like", value, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1In(List<String> values) {
            addCriterion("OVERLIM_LINE1 in", values, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1NotIn(List<String> values) {
            addCriterion("OVERLIM_LINE1 not in", values, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1Between(String value1, String value2) {
            addCriterion("OVERLIM_LINE1 between", value1, value2, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine1NotBetween(String value1, String value2) {
            addCriterion("OVERLIM_LINE1 not between", value1, value2, "overlimLine1");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2IsNull() {
            addCriterion("OVERLIM_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2IsNotNull() {
            addCriterion("OVERLIM_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2EqualTo(String value) {
            addCriterion("OVERLIM_LINE2 =", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2NotEqualTo(String value) {
            addCriterion("OVERLIM_LINE2 <>", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2GreaterThan(String value) {
            addCriterion("OVERLIM_LINE2 >", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2GreaterThanOrEqualTo(String value) {
            addCriterion("OVERLIM_LINE2 >=", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2LessThan(String value) {
            addCriterion("OVERLIM_LINE2 <", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2LessThanOrEqualTo(String value) {
            addCriterion("OVERLIM_LINE2 <=", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2Like(String value) {
            addCriterion("OVERLIM_LINE2 like", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2NotLike(String value) {
            addCriterion("OVERLIM_LINE2 not like", value, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2In(List<String> values) {
            addCriterion("OVERLIM_LINE2 in", values, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2NotIn(List<String> values) {
            addCriterion("OVERLIM_LINE2 not in", values, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2Between(String value1, String value2) {
            addCriterion("OVERLIM_LINE2 between", value1, value2, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andOverlimLine2NotBetween(String value1, String value2) {
            addCriterion("OVERLIM_LINE2 not between", value1, value2, "overlimLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1IsNull() {
            addCriterion("LATEPAY_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1IsNotNull() {
            addCriterion("LATEPAY_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1EqualTo(String value) {
            addCriterion("LATEPAY_LINE1 =", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1NotEqualTo(String value) {
            addCriterion("LATEPAY_LINE1 <>", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1GreaterThan(String value) {
            addCriterion("LATEPAY_LINE1 >", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1GreaterThanOrEqualTo(String value) {
            addCriterion("LATEPAY_LINE1 >=", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1LessThan(String value) {
            addCriterion("LATEPAY_LINE1 <", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1LessThanOrEqualTo(String value) {
            addCriterion("LATEPAY_LINE1 <=", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1Like(String value) {
            addCriterion("LATEPAY_LINE1 like", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1NotLike(String value) {
            addCriterion("LATEPAY_LINE1 not like", value, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1In(List<String> values) {
            addCriterion("LATEPAY_LINE1 in", values, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1NotIn(List<String> values) {
            addCriterion("LATEPAY_LINE1 not in", values, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1Between(String value1, String value2) {
            addCriterion("LATEPAY_LINE1 between", value1, value2, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine1NotBetween(String value1, String value2) {
            addCriterion("LATEPAY_LINE1 not between", value1, value2, "latepayLine1");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2IsNull() {
            addCriterion("LATEPAY_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2IsNotNull() {
            addCriterion("LATEPAY_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2EqualTo(String value) {
            addCriterion("LATEPAY_LINE2 =", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2NotEqualTo(String value) {
            addCriterion("LATEPAY_LINE2 <>", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2GreaterThan(String value) {
            addCriterion("LATEPAY_LINE2 >", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2GreaterThanOrEqualTo(String value) {
            addCriterion("LATEPAY_LINE2 >=", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2LessThan(String value) {
            addCriterion("LATEPAY_LINE2 <", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2LessThanOrEqualTo(String value) {
            addCriterion("LATEPAY_LINE2 <=", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2Like(String value) {
            addCriterion("LATEPAY_LINE2 like", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2NotLike(String value) {
            addCriterion("LATEPAY_LINE2 not like", value, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2In(List<String> values) {
            addCriterion("LATEPAY_LINE2 in", values, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2NotIn(List<String> values) {
            addCriterion("LATEPAY_LINE2 not in", values, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2Between(String value1, String value2) {
            addCriterion("LATEPAY_LINE2 between", value1, value2, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andLatepayLine2NotBetween(String value1, String value2) {
            addCriterion("LATEPAY_LINE2 not between", value1, value2, "latepayLine2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1IsNull() {
            addCriterion("OVERDUE1_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1IsNotNull() {
            addCriterion("OVERDUE1_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1EqualTo(String value) {
            addCriterion("OVERDUE1_LINE1 =", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1NotEqualTo(String value) {
            addCriterion("OVERDUE1_LINE1 <>", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1GreaterThan(String value) {
            addCriterion("OVERDUE1_LINE1 >", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE1_LINE1 >=", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1LessThan(String value) {
            addCriterion("OVERDUE1_LINE1 <", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE1_LINE1 <=", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1Like(String value) {
            addCriterion("OVERDUE1_LINE1 like", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1NotLike(String value) {
            addCriterion("OVERDUE1_LINE1 not like", value, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1In(List<String> values) {
            addCriterion("OVERDUE1_LINE1 in", values, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1NotIn(List<String> values) {
            addCriterion("OVERDUE1_LINE1 not in", values, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1Between(String value1, String value2) {
            addCriterion("OVERDUE1_LINE1 between", value1, value2, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line1NotBetween(String value1, String value2) {
            addCriterion("OVERDUE1_LINE1 not between", value1, value2, "overdue1Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2IsNull() {
            addCriterion("OVERDUE1_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2IsNotNull() {
            addCriterion("OVERDUE1_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2EqualTo(String value) {
            addCriterion("OVERDUE1_LINE2 =", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2NotEqualTo(String value) {
            addCriterion("OVERDUE1_LINE2 <>", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2GreaterThan(String value) {
            addCriterion("OVERDUE1_LINE2 >", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE1_LINE2 >=", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2LessThan(String value) {
            addCriterion("OVERDUE1_LINE2 <", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE1_LINE2 <=", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2Like(String value) {
            addCriterion("OVERDUE1_LINE2 like", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2NotLike(String value) {
            addCriterion("OVERDUE1_LINE2 not like", value, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2In(List<String> values) {
            addCriterion("OVERDUE1_LINE2 in", values, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2NotIn(List<String> values) {
            addCriterion("OVERDUE1_LINE2 not in", values, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2Between(String value1, String value2) {
            addCriterion("OVERDUE1_LINE2 between", value1, value2, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue1Line2NotBetween(String value1, String value2) {
            addCriterion("OVERDUE1_LINE2 not between", value1, value2, "overdue1Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1IsNull() {
            addCriterion("OVERDUE2_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1IsNotNull() {
            addCriterion("OVERDUE2_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1EqualTo(String value) {
            addCriterion("OVERDUE2_LINE1 =", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1NotEqualTo(String value) {
            addCriterion("OVERDUE2_LINE1 <>", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1GreaterThan(String value) {
            addCriterion("OVERDUE2_LINE1 >", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE2_LINE1 >=", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1LessThan(String value) {
            addCriterion("OVERDUE2_LINE1 <", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE2_LINE1 <=", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1Like(String value) {
            addCriterion("OVERDUE2_LINE1 like", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1NotLike(String value) {
            addCriterion("OVERDUE2_LINE1 not like", value, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1In(List<String> values) {
            addCriterion("OVERDUE2_LINE1 in", values, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1NotIn(List<String> values) {
            addCriterion("OVERDUE2_LINE1 not in", values, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1Between(String value1, String value2) {
            addCriterion("OVERDUE2_LINE1 between", value1, value2, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line1NotBetween(String value1, String value2) {
            addCriterion("OVERDUE2_LINE1 not between", value1, value2, "overdue2Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2IsNull() {
            addCriterion("OVERDUE2_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2IsNotNull() {
            addCriterion("OVERDUE2_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2EqualTo(String value) {
            addCriterion("OVERDUE2_LINE2 =", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2NotEqualTo(String value) {
            addCriterion("OVERDUE2_LINE2 <>", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2GreaterThan(String value) {
            addCriterion("OVERDUE2_LINE2 >", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE2_LINE2 >=", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2LessThan(String value) {
            addCriterion("OVERDUE2_LINE2 <", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE2_LINE2 <=", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2Like(String value) {
            addCriterion("OVERDUE2_LINE2 like", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2NotLike(String value) {
            addCriterion("OVERDUE2_LINE2 not like", value, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2In(List<String> values) {
            addCriterion("OVERDUE2_LINE2 in", values, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2NotIn(List<String> values) {
            addCriterion("OVERDUE2_LINE2 not in", values, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2Between(String value1, String value2) {
            addCriterion("OVERDUE2_LINE2 between", value1, value2, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue2Line2NotBetween(String value1, String value2) {
            addCriterion("OVERDUE2_LINE2 not between", value1, value2, "overdue2Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1IsNull() {
            addCriterion("OVERDUE3_LINE1 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1IsNotNull() {
            addCriterion("OVERDUE3_LINE1 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1EqualTo(String value) {
            addCriterion("OVERDUE3_LINE1 =", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1NotEqualTo(String value) {
            addCriterion("OVERDUE3_LINE1 <>", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1GreaterThan(String value) {
            addCriterion("OVERDUE3_LINE1 >", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE3_LINE1 >=", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1LessThan(String value) {
            addCriterion("OVERDUE3_LINE1 <", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE3_LINE1 <=", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1Like(String value) {
            addCriterion("OVERDUE3_LINE1 like", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1NotLike(String value) {
            addCriterion("OVERDUE3_LINE1 not like", value, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1In(List<String> values) {
            addCriterion("OVERDUE3_LINE1 in", values, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1NotIn(List<String> values) {
            addCriterion("OVERDUE3_LINE1 not in", values, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1Between(String value1, String value2) {
            addCriterion("OVERDUE3_LINE1 between", value1, value2, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line1NotBetween(String value1, String value2) {
            addCriterion("OVERDUE3_LINE1 not between", value1, value2, "overdue3Line1");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2IsNull() {
            addCriterion("OVERDUE3_LINE2 is null");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2IsNotNull() {
            addCriterion("OVERDUE3_LINE2 is not null");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2EqualTo(String value) {
            addCriterion("OVERDUE3_LINE2 =", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2NotEqualTo(String value) {
            addCriterion("OVERDUE3_LINE2 <>", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2GreaterThan(String value) {
            addCriterion("OVERDUE3_LINE2 >", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2GreaterThanOrEqualTo(String value) {
            addCriterion("OVERDUE3_LINE2 >=", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2LessThan(String value) {
            addCriterion("OVERDUE3_LINE2 <", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2LessThanOrEqualTo(String value) {
            addCriterion("OVERDUE3_LINE2 <=", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2Like(String value) {
            addCriterion("OVERDUE3_LINE2 like", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2NotLike(String value) {
            addCriterion("OVERDUE3_LINE2 not like", value, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2In(List<String> values) {
            addCriterion("OVERDUE3_LINE2 in", values, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2NotIn(List<String> values) {
            addCriterion("OVERDUE3_LINE2 not in", values, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2Between(String value1, String value2) {
            addCriterion("OVERDUE3_LINE2 between", value1, value2, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andOverdue3Line2NotBetween(String value1, String value2) {
            addCriterion("OVERDUE3_LINE2 not between", value1, value2, "overdue3Line2");
            return (Criteria) this;
        }

        public Criteria andDenysvcIsNull() {
            addCriterion("DENYSVC is null");
            return (Criteria) this;
        }

        public Criteria andDenysvcIsNotNull() {
            addCriterion("DENYSVC is not null");
            return (Criteria) this;
        }

        public Criteria andDenysvcEqualTo(String value) {
            addCriterion("DENYSVC =", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcNotEqualTo(String value) {
            addCriterion("DENYSVC <>", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcGreaterThan(String value) {
            addCriterion("DENYSVC >", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcGreaterThanOrEqualTo(String value) {
            addCriterion("DENYSVC >=", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcLessThan(String value) {
            addCriterion("DENYSVC <", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcLessThanOrEqualTo(String value) {
            addCriterion("DENYSVC <=", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcLike(String value) {
            addCriterion("DENYSVC like", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcNotLike(String value) {
            addCriterion("DENYSVC not like", value, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcIn(List<String> values) {
            addCriterion("DENYSVC in", values, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcNotIn(List<String> values) {
            addCriterion("DENYSVC not in", values, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcBetween(String value1, String value2) {
            addCriterion("DENYSVC between", value1, value2, "denysvc");
            return (Criteria) this;
        }

        public Criteria andDenysvcNotBetween(String value1, String value2) {
            addCriterion("DENYSVC not between", value1, value2, "denysvc");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidIsNull() {
            addCriterion("EMVCRPTGID is null");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidIsNotNull() {
            addCriterion("EMVCRPTGID is not null");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidEqualTo(Integer value) {
            addCriterion("EMVCRPTGID =", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidNotEqualTo(Integer value) {
            addCriterion("EMVCRPTGID <>", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidGreaterThan(Integer value) {
            addCriterion("EMVCRPTGID >", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidGreaterThanOrEqualTo(Integer value) {
            addCriterion("EMVCRPTGID >=", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidLessThan(Integer value) {
            addCriterion("EMVCRPTGID <", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidLessThanOrEqualTo(Integer value) {
            addCriterion("EMVCRPTGID <=", value, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidIn(List<Integer> values) {
            addCriterion("EMVCRPTGID in", values, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidNotIn(List<Integer> values) {
            addCriterion("EMVCRPTGID not in", values, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidBetween(Integer value1, Integer value2) {
            addCriterion("EMVCRPTGID between", value1, value2, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andEmvcrptgidNotBetween(Integer value1, Integer value2) {
            addCriterion("EMVCRPTGID not between", value1, value2, "emvcrptgid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidIsNull() {
            addCriterion("AUTHCHKSID is null");
            return (Criteria) this;
        }

        public Criteria andAuthchksidIsNotNull() {
            addCriterion("AUTHCHKSID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthchksidEqualTo(Integer value) {
            addCriterion("AUTHCHKSID =", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidNotEqualTo(Integer value) {
            addCriterion("AUTHCHKSID <>", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidGreaterThan(Integer value) {
            addCriterion("AUTHCHKSID >", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTHCHKSID >=", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidLessThan(Integer value) {
            addCriterion("AUTHCHKSID <", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidLessThanOrEqualTo(Integer value) {
            addCriterion("AUTHCHKSID <=", value, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidIn(List<Integer> values) {
            addCriterion("AUTHCHKSID in", values, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidNotIn(List<Integer> values) {
            addCriterion("AUTHCHKSID not in", values, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidBetween(Integer value1, Integer value2) {
            addCriterion("AUTHCHKSID between", value1, value2, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAuthchksidNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTHCHKSID not between", value1, value2, "authchksid");
            return (Criteria) this;
        }

        public Criteria andAtclimitIsNull() {
            addCriterion("ATCLIMIT is null");
            return (Criteria) this;
        }

        public Criteria andAtclimitIsNotNull() {
            addCriterion("ATCLIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andAtclimitEqualTo(Long value) {
            addCriterion("ATCLIMIT =", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitNotEqualTo(Long value) {
            addCriterion("ATCLIMIT <>", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitGreaterThan(Long value) {
            addCriterion("ATCLIMIT >", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitGreaterThanOrEqualTo(Long value) {
            addCriterion("ATCLIMIT >=", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitLessThan(Long value) {
            addCriterion("ATCLIMIT <", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitLessThanOrEqualTo(Long value) {
            addCriterion("ATCLIMIT <=", value, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitIn(List<Long> values) {
            addCriterion("ATCLIMIT in", values, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitNotIn(List<Long> values) {
            addCriterion("ATCLIMIT not in", values, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitBetween(Long value1, Long value2) {
            addCriterion("ATCLIMIT between", value1, value2, "atclimit");
            return (Criteria) this;
        }

        public Criteria andAtclimitNotBetween(Long value1, Long value2) {
            addCriterion("ATCLIMIT not between", value1, value2, "atclimit");
            return (Criteria) this;
        }

        public Criteria andArqcfailactIsNull() {
            addCriterion("ARQCFAILACT is null");
            return (Criteria) this;
        }

        public Criteria andArqcfailactIsNotNull() {
            addCriterion("ARQCFAILACT is not null");
            return (Criteria) this;
        }

        public Criteria andArqcfailactEqualTo(String value) {
            addCriterion("ARQCFAILACT =", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactNotEqualTo(String value) {
            addCriterion("ARQCFAILACT <>", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactGreaterThan(String value) {
            addCriterion("ARQCFAILACT >", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactGreaterThanOrEqualTo(String value) {
            addCriterion("ARQCFAILACT >=", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactLessThan(String value) {
            addCriterion("ARQCFAILACT <", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactLessThanOrEqualTo(String value) {
            addCriterion("ARQCFAILACT <=", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactLike(String value) {
            addCriterion("ARQCFAILACT like", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactNotLike(String value) {
            addCriterion("ARQCFAILACT not like", value, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactIn(List<String> values) {
            addCriterion("ARQCFAILACT in", values, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactNotIn(List<String> values) {
            addCriterion("ARQCFAILACT not in", values, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactBetween(String value1, String value2) {
            addCriterion("ARQCFAILACT between", value1, value2, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailactNotBetween(String value1, String value2) {
            addCriterion("ARQCFAILACT not between", value1, value2, "arqcfailact");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspIsNull() {
            addCriterion("ARQCFAILRSP is null");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspIsNotNull() {
            addCriterion("ARQCFAILRSP is not null");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspEqualTo(String value) {
            addCriterion("ARQCFAILRSP =", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspNotEqualTo(String value) {
            addCriterion("ARQCFAILRSP <>", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspGreaterThan(String value) {
            addCriterion("ARQCFAILRSP >", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspGreaterThanOrEqualTo(String value) {
            addCriterion("ARQCFAILRSP >=", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspLessThan(String value) {
            addCriterion("ARQCFAILRSP <", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspLessThanOrEqualTo(String value) {
            addCriterion("ARQCFAILRSP <=", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspLike(String value) {
            addCriterion("ARQCFAILRSP like", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspNotLike(String value) {
            addCriterion("ARQCFAILRSP not like", value, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspIn(List<String> values) {
            addCriterion("ARQCFAILRSP in", values, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspNotIn(List<String> values) {
            addCriterion("ARQCFAILRSP not in", values, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspBetween(String value1, String value2) {
            addCriterion("ARQCFAILRSP between", value1, value2, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andArqcfailrspNotBetween(String value1, String value2) {
            addCriterion("ARQCFAILRSP not between", value1, value2, "arqcfailrsp");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcIsNull() {
            addCriterion("EMVSCRIPTSVC is null");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcIsNotNull() {
            addCriterion("EMVSCRIPTSVC is not null");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcEqualTo(String value) {
            addCriterion("EMVSCRIPTSVC =", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcNotEqualTo(String value) {
            addCriterion("EMVSCRIPTSVC <>", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcGreaterThan(String value) {
            addCriterion("EMVSCRIPTSVC >", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcGreaterThanOrEqualTo(String value) {
            addCriterion("EMVSCRIPTSVC >=", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcLessThan(String value) {
            addCriterion("EMVSCRIPTSVC <", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcLessThanOrEqualTo(String value) {
            addCriterion("EMVSCRIPTSVC <=", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcLike(String value) {
            addCriterion("EMVSCRIPTSVC like", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcNotLike(String value) {
            addCriterion("EMVSCRIPTSVC not like", value, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcIn(List<String> values) {
            addCriterion("EMVSCRIPTSVC in", values, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcNotIn(List<String> values) {
            addCriterion("EMVSCRIPTSVC not in", values, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcBetween(String value1, String value2) {
            addCriterion("EMVSCRIPTSVC between", value1, value2, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andEmvscriptsvcNotBetween(String value1, String value2) {
            addCriterion("EMVSCRIPTSVC not between", value1, value2, "emvscriptsvc");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitIsNull() {
            addCriterion("FALLBACKLIMIT is null");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitIsNotNull() {
            addCriterion("FALLBACKLIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitEqualTo(Integer value) {
            addCriterion("FALLBACKLIMIT =", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitNotEqualTo(Integer value) {
            addCriterion("FALLBACKLIMIT <>", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitGreaterThan(Integer value) {
            addCriterion("FALLBACKLIMIT >", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("FALLBACKLIMIT >=", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitLessThan(Integer value) {
            addCriterion("FALLBACKLIMIT <", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitLessThanOrEqualTo(Integer value) {
            addCriterion("FALLBACKLIMIT <=", value, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitIn(List<Integer> values) {
            addCriterion("FALLBACKLIMIT in", values, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitNotIn(List<Integer> values) {
            addCriterion("FALLBACKLIMIT not in", values, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitBetween(Integer value1, Integer value2) {
            addCriterion("FALLBACKLIMIT between", value1, value2, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbacklimitNotBetween(Integer value1, Integer value2) {
            addCriterion("FALLBACKLIMIT not between", value1, value2, "fallbacklimit");
            return (Criteria) this;
        }

        public Criteria andFallbackactIsNull() {
            addCriterion("FALLBACKACT is null");
            return (Criteria) this;
        }

        public Criteria andFallbackactIsNotNull() {
            addCriterion("FALLBACKACT is not null");
            return (Criteria) this;
        }

        public Criteria andFallbackactEqualTo(String value) {
            addCriterion("FALLBACKACT =", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactNotEqualTo(String value) {
            addCriterion("FALLBACKACT <>", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactGreaterThan(String value) {
            addCriterion("FALLBACKACT >", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactGreaterThanOrEqualTo(String value) {
            addCriterion("FALLBACKACT >=", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactLessThan(String value) {
            addCriterion("FALLBACKACT <", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactLessThanOrEqualTo(String value) {
            addCriterion("FALLBACKACT <=", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactLike(String value) {
            addCriterion("FALLBACKACT like", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactNotLike(String value) {
            addCriterion("FALLBACKACT not like", value, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactIn(List<String> values) {
            addCriterion("FALLBACKACT in", values, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactNotIn(List<String> values) {
            addCriterion("FALLBACKACT not in", values, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactBetween(String value1, String value2) {
            addCriterion("FALLBACKACT between", value1, value2, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackactNotBetween(String value1, String value2) {
            addCriterion("FALLBACKACT not between", value1, value2, "fallbackact");
            return (Criteria) this;
        }

        public Criteria andFallbackrspIsNull() {
            addCriterion("FALLBACKRSP is null");
            return (Criteria) this;
        }

        public Criteria andFallbackrspIsNotNull() {
            addCriterion("FALLBACKRSP is not null");
            return (Criteria) this;
        }

        public Criteria andFallbackrspEqualTo(String value) {
            addCriterion("FALLBACKRSP =", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspNotEqualTo(String value) {
            addCriterion("FALLBACKRSP <>", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspGreaterThan(String value) {
            addCriterion("FALLBACKRSP >", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspGreaterThanOrEqualTo(String value) {
            addCriterion("FALLBACKRSP >=", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspLessThan(String value) {
            addCriterion("FALLBACKRSP <", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspLessThanOrEqualTo(String value) {
            addCriterion("FALLBACKRSP <=", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspLike(String value) {
            addCriterion("FALLBACKRSP like", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspNotLike(String value) {
            addCriterion("FALLBACKRSP not like", value, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspIn(List<String> values) {
            addCriterion("FALLBACKRSP in", values, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspNotIn(List<String> values) {
            addCriterion("FALLBACKRSP not in", values, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspBetween(String value1, String value2) {
            addCriterion("FALLBACKRSP between", value1, value2, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andFallbackrspNotBetween(String value1, String value2) {
            addCriterion("FALLBACKRSP not between", value1, value2, "fallbackrsp");
            return (Criteria) this;
        }

        public Criteria andEmvencschIsNull() {
            addCriterion("EMVENCSCH is null");
            return (Criteria) this;
        }

        public Criteria andEmvencschIsNotNull() {
            addCriterion("EMVENCSCH is not null");
            return (Criteria) this;
        }

        public Criteria andEmvencschEqualTo(Integer value) {
            addCriterion("EMVENCSCH =", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschNotEqualTo(Integer value) {
            addCriterion("EMVENCSCH <>", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschGreaterThan(Integer value) {
            addCriterion("EMVENCSCH >", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschGreaterThanOrEqualTo(Integer value) {
            addCriterion("EMVENCSCH >=", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschLessThan(Integer value) {
            addCriterion("EMVENCSCH <", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschLessThanOrEqualTo(Integer value) {
            addCriterion("EMVENCSCH <=", value, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschIn(List<Integer> values) {
            addCriterion("EMVENCSCH in", values, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschNotIn(List<Integer> values) {
            addCriterion("EMVENCSCH not in", values, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschBetween(Integer value1, Integer value2) {
            addCriterion("EMVENCSCH between", value1, value2, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmvencschNotBetween(Integer value1, Integer value2) {
            addCriterion("EMVENCSCH not between", value1, value2, "emvencsch");
            return (Criteria) this;
        }

        public Criteria andEmviccverIsNull() {
            addCriterion("EMVICCVER is null");
            return (Criteria) this;
        }

        public Criteria andEmviccverIsNotNull() {
            addCriterion("EMVICCVER is not null");
            return (Criteria) this;
        }

        public Criteria andEmviccverEqualTo(Integer value) {
            addCriterion("EMVICCVER =", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverNotEqualTo(Integer value) {
            addCriterion("EMVICCVER <>", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverGreaterThan(Integer value) {
            addCriterion("EMVICCVER >", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverGreaterThanOrEqualTo(Integer value) {
            addCriterion("EMVICCVER >=", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverLessThan(Integer value) {
            addCriterion("EMVICCVER <", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverLessThanOrEqualTo(Integer value) {
            addCriterion("EMVICCVER <=", value, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverIn(List<Integer> values) {
            addCriterion("EMVICCVER in", values, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverNotIn(List<Integer> values) {
            addCriterion("EMVICCVER not in", values, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverBetween(Integer value1, Integer value2) {
            addCriterion("EMVICCVER between", value1, value2, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmviccverNotBetween(Integer value1, Integer value2) {
            addCriterion("EMVICCVER not between", value1, value2, "emviccver");
            return (Criteria) this;
        }

        public Criteria andEmvodaIsNull() {
            addCriterion("EMVODA is null");
            return (Criteria) this;
        }

        public Criteria andEmvodaIsNotNull() {
            addCriterion("EMVODA is not null");
            return (Criteria) this;
        }

        public Criteria andEmvodaEqualTo(Integer value) {
            addCriterion("EMVODA =", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaNotEqualTo(Integer value) {
            addCriterion("EMVODA <>", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaGreaterThan(Integer value) {
            addCriterion("EMVODA >", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaGreaterThanOrEqualTo(Integer value) {
            addCriterion("EMVODA >=", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaLessThan(Integer value) {
            addCriterion("EMVODA <", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaLessThanOrEqualTo(Integer value) {
            addCriterion("EMVODA <=", value, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaIn(List<Integer> values) {
            addCriterion("EMVODA in", values, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaNotIn(List<Integer> values) {
            addCriterion("EMVODA not in", values, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaBetween(Integer value1, Integer value2) {
            addCriterion("EMVODA between", value1, value2, "emvoda");
            return (Criteria) this;
        }

        public Criteria andEmvodaNotBetween(Integer value1, Integer value2) {
            addCriterion("EMVODA not between", value1, value2, "emvoda");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocIsNull() {
            addCriterion("AMTRTILOC is null");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocIsNotNull() {
            addCriterion("AMTRTILOC is not null");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocEqualTo(Double value) {
            addCriterion("AMTRTILOC =", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocNotEqualTo(Double value) {
            addCriterion("AMTRTILOC <>", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocGreaterThan(Double value) {
            addCriterion("AMTRTILOC >", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocGreaterThanOrEqualTo(Double value) {
            addCriterion("AMTRTILOC >=", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocLessThan(Double value) {
            addCriterion("AMTRTILOC <", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocLessThanOrEqualTo(Double value) {
            addCriterion("AMTRTILOC <=", value, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocIn(List<Double> values) {
            addCriterion("AMTRTILOC in", values, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocNotIn(List<Double> values) {
            addCriterion("AMTRTILOC not in", values, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocBetween(Double value1, Double value2) {
            addCriterion("AMTRTILOC between", value1, value2, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtilocNotBetween(Double value1, Double value2) {
            addCriterion("AMTRTILOC not between", value1, value2, "amtrtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocIsNull() {
            addCriterion("CURRTILOC is null");
            return (Criteria) this;
        }

        public Criteria andCurrtilocIsNotNull() {
            addCriterion("CURRTILOC is not null");
            return (Criteria) this;
        }

        public Criteria andCurrtilocEqualTo(String value) {
            addCriterion("CURRTILOC =", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocNotEqualTo(String value) {
            addCriterion("CURRTILOC <>", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocGreaterThan(String value) {
            addCriterion("CURRTILOC >", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocGreaterThanOrEqualTo(String value) {
            addCriterion("CURRTILOC >=", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocLessThan(String value) {
            addCriterion("CURRTILOC <", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocLessThanOrEqualTo(String value) {
            addCriterion("CURRTILOC <=", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocLike(String value) {
            addCriterion("CURRTILOC like", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocNotLike(String value) {
            addCriterion("CURRTILOC not like", value, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocIn(List<String> values) {
            addCriterion("CURRTILOC in", values, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocNotIn(List<String> values) {
            addCriterion("CURRTILOC not in", values, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocBetween(String value1, String value2) {
            addCriterion("CURRTILOC between", value1, value2, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andCurrtilocNotBetween(String value1, String value2) {
            addCriterion("CURRTILOC not between", value1, value2, "currtiloc");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintIsNull() {
            addCriterion("AMTRTIINT is null");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintIsNotNull() {
            addCriterion("AMTRTIINT is not null");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintEqualTo(Double value) {
            addCriterion("AMTRTIINT =", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintNotEqualTo(Double value) {
            addCriterion("AMTRTIINT <>", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintGreaterThan(Double value) {
            addCriterion("AMTRTIINT >", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintGreaterThanOrEqualTo(Double value) {
            addCriterion("AMTRTIINT >=", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintLessThan(Double value) {
            addCriterion("AMTRTIINT <", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintLessThanOrEqualTo(Double value) {
            addCriterion("AMTRTIINT <=", value, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintIn(List<Double> values) {
            addCriterion("AMTRTIINT in", values, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintNotIn(List<Double> values) {
            addCriterion("AMTRTIINT not in", values, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintBetween(Double value1, Double value2) {
            addCriterion("AMTRTIINT between", value1, value2, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andAmtrtiintNotBetween(Double value1, Double value2) {
            addCriterion("AMTRTIINT not between", value1, value2, "amtrtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintIsNull() {
            addCriterion("CURRTIINT is null");
            return (Criteria) this;
        }

        public Criteria andCurrtiintIsNotNull() {
            addCriterion("CURRTIINT is not null");
            return (Criteria) this;
        }

        public Criteria andCurrtiintEqualTo(String value) {
            addCriterion("CURRTIINT =", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintNotEqualTo(String value) {
            addCriterion("CURRTIINT <>", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintGreaterThan(String value) {
            addCriterion("CURRTIINT >", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintGreaterThanOrEqualTo(String value) {
            addCriterion("CURRTIINT >=", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintLessThan(String value) {
            addCriterion("CURRTIINT <", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintLessThanOrEqualTo(String value) {
            addCriterion("CURRTIINT <=", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintLike(String value) {
            addCriterion("CURRTIINT like", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintNotLike(String value) {
            addCriterion("CURRTIINT not like", value, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintIn(List<String> values) {
            addCriterion("CURRTIINT in", values, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintNotIn(List<String> values) {
            addCriterion("CURRTIINT not in", values, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintBetween(String value1, String value2) {
            addCriterion("CURRTIINT between", value1, value2, "currtiint");
            return (Criteria) this;
        }

        public Criteria andCurrtiintNotBetween(String value1, String value2) {
            addCriterion("CURRTIINT not between", value1, value2, "currtiint");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtIsNull() {
            addCriterion("ICCPINFMT is null");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtIsNotNull() {
            addCriterion("ICCPINFMT is not null");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtEqualTo(Integer value) {
            addCriterion("ICCPINFMT =", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtNotEqualTo(Integer value) {
            addCriterion("ICCPINFMT <>", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtGreaterThan(Integer value) {
            addCriterion("ICCPINFMT >", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtGreaterThanOrEqualTo(Integer value) {
            addCriterion("ICCPINFMT >=", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtLessThan(Integer value) {
            addCriterion("ICCPINFMT <", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtLessThanOrEqualTo(Integer value) {
            addCriterion("ICCPINFMT <=", value, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtIn(List<Integer> values) {
            addCriterion("ICCPINFMT in", values, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtNotIn(List<Integer> values) {
            addCriterion("ICCPINFMT not in", values, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtBetween(Integer value1, Integer value2) {
            addCriterion("ICCPINFMT between", value1, value2, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andIccpinfmtNotBetween(Integer value1, Integer value2) {
            addCriterion("ICCPINFMT not between", value1, value2, "iccpinfmt");
            return (Criteria) this;
        }

        public Criteria andScriptsetIsNull() {
            addCriterion("SCRIPTSET is null");
            return (Criteria) this;
        }

        public Criteria andScriptsetIsNotNull() {
            addCriterion("SCRIPTSET is not null");
            return (Criteria) this;
        }

        public Criteria andScriptsetEqualTo(Integer value) {
            addCriterion("SCRIPTSET =", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetNotEqualTo(Integer value) {
            addCriterion("SCRIPTSET <>", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetGreaterThan(Integer value) {
            addCriterion("SCRIPTSET >", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCRIPTSET >=", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetLessThan(Integer value) {
            addCriterion("SCRIPTSET <", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetLessThanOrEqualTo(Integer value) {
            addCriterion("SCRIPTSET <=", value, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetIn(List<Integer> values) {
            addCriterion("SCRIPTSET in", values, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetNotIn(List<Integer> values) {
            addCriterion("SCRIPTSET not in", values, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetBetween(Integer value1, Integer value2) {
            addCriterion("SCRIPTSET between", value1, value2, "scriptset");
            return (Criteria) this;
        }

        public Criteria andScriptsetNotBetween(Integer value1, Integer value2) {
            addCriterion("SCRIPTSET not between", value1, value2, "scriptset");
            return (Criteria) this;
        }

        public Criteria andDenymccIsNull() {
            addCriterion("DENYMCC is null");
            return (Criteria) this;
        }

        public Criteria andDenymccIsNotNull() {
            addCriterion("DENYMCC is not null");
            return (Criteria) this;
        }

        public Criteria andDenymccEqualTo(String value) {
            addCriterion("DENYMCC =", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccNotEqualTo(String value) {
            addCriterion("DENYMCC <>", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccGreaterThan(String value) {
            addCriterion("DENYMCC >", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccGreaterThanOrEqualTo(String value) {
            addCriterion("DENYMCC >=", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccLessThan(String value) {
            addCriterion("DENYMCC <", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccLessThanOrEqualTo(String value) {
            addCriterion("DENYMCC <=", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccLike(String value) {
            addCriterion("DENYMCC like", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccNotLike(String value) {
            addCriterion("DENYMCC not like", value, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccIn(List<String> values) {
            addCriterion("DENYMCC in", values, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccNotIn(List<String> values) {
            addCriterion("DENYMCC not in", values, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccBetween(String value1, String value2) {
            addCriterion("DENYMCC between", value1, value2, "denymcc");
            return (Criteria) this;
        }

        public Criteria andDenymccNotBetween(String value1, String value2) {
            addCriterion("DENYMCC not between", value1, value2, "denymcc");
            return (Criteria) this;
        }

        public Criteria andMaxloadIsNull() {
            addCriterion("MAXLOAD is null");
            return (Criteria) this;
        }

        public Criteria andMaxloadIsNotNull() {
            addCriterion("MAXLOAD is not null");
            return (Criteria) this;
        }

        public Criteria andMaxloadEqualTo(Double value) {
            addCriterion("MAXLOAD =", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadNotEqualTo(Double value) {
            addCriterion("MAXLOAD <>", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadGreaterThan(Double value) {
            addCriterion("MAXLOAD >", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadGreaterThanOrEqualTo(Double value) {
            addCriterion("MAXLOAD >=", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadLessThan(Double value) {
            addCriterion("MAXLOAD <", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadLessThanOrEqualTo(Double value) {
            addCriterion("MAXLOAD <=", value, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadIn(List<Double> values) {
            addCriterion("MAXLOAD in", values, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadNotIn(List<Double> values) {
            addCriterion("MAXLOAD not in", values, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadBetween(Double value1, Double value2) {
            addCriterion("MAXLOAD between", value1, value2, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxloadNotBetween(Double value1, Double value2) {
            addCriterion("MAXLOAD not between", value1, value2, "maxload");
            return (Criteria) this;
        }

        public Criteria andMaxbalIsNull() {
            addCriterion("MAXBAL is null");
            return (Criteria) this;
        }

        public Criteria andMaxbalIsNotNull() {
            addCriterion("MAXBAL is not null");
            return (Criteria) this;
        }

        public Criteria andMaxbalEqualTo(Double value) {
            addCriterion("MAXBAL =", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalNotEqualTo(Double value) {
            addCriterion("MAXBAL <>", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalGreaterThan(Double value) {
            addCriterion("MAXBAL >", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalGreaterThanOrEqualTo(Double value) {
            addCriterion("MAXBAL >=", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalLessThan(Double value) {
            addCriterion("MAXBAL <", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalLessThanOrEqualTo(Double value) {
            addCriterion("MAXBAL <=", value, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalIn(List<Double> values) {
            addCriterion("MAXBAL in", values, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalNotIn(List<Double> values) {
            addCriterion("MAXBAL not in", values, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalBetween(Double value1, Double value2) {
            addCriterion("MAXBAL between", value1, value2, "maxbal");
            return (Criteria) this;
        }

        public Criteria andMaxbalNotBetween(Double value1, Double value2) {
            addCriterion("MAXBAL not between", value1, value2, "maxbal");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeIsNull() {
            addCriterion("RISK_PROF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeIsNotNull() {
            addCriterion("RISK_PROF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeEqualTo(String value) {
            addCriterion("RISK_PROF_CODE =", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeNotEqualTo(String value) {
            addCriterion("RISK_PROF_CODE <>", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeGreaterThan(String value) {
            addCriterion("RISK_PROF_CODE >", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_PROF_CODE >=", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeLessThan(String value) {
            addCriterion("RISK_PROF_CODE <", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeLessThanOrEqualTo(String value) {
            addCriterion("RISK_PROF_CODE <=", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeLike(String value) {
            addCriterion("RISK_PROF_CODE like", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeNotLike(String value) {
            addCriterion("RISK_PROF_CODE not like", value, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeIn(List<String> values) {
            addCriterion("RISK_PROF_CODE in", values, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeNotIn(List<String> values) {
            addCriterion("RISK_PROF_CODE not in", values, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeBetween(String value1, String value2) {
            addCriterion("RISK_PROF_CODE between", value1, value2, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andRiskProfCodeNotBetween(String value1, String value2) {
            addCriterion("RISK_PROF_CODE not between", value1, value2, "riskProfCode");
            return (Criteria) this;
        }

        public Criteria andAuthParamsIsNull() {
            addCriterion("AUTH_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andAuthParamsIsNotNull() {
            addCriterion("AUTH_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andAuthParamsEqualTo(String value) {
            addCriterion("AUTH_PARAMS =", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsNotEqualTo(String value) {
            addCriterion("AUTH_PARAMS <>", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsGreaterThan(String value) {
            addCriterion("AUTH_PARAMS >", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_PARAMS >=", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsLessThan(String value) {
            addCriterion("AUTH_PARAMS <", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsLessThanOrEqualTo(String value) {
            addCriterion("AUTH_PARAMS <=", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsLike(String value) {
            addCriterion("AUTH_PARAMS like", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsNotLike(String value) {
            addCriterion("AUTH_PARAMS not like", value, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsIn(List<String> values) {
            addCriterion("AUTH_PARAMS in", values, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsNotIn(List<String> values) {
            addCriterion("AUTH_PARAMS not in", values, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsBetween(String value1, String value2) {
            addCriterion("AUTH_PARAMS between", value1, value2, "authParams");
            return (Criteria) this;
        }

        public Criteria andAuthParamsNotBetween(String value1, String value2) {
            addCriterion("AUTH_PARAMS not between", value1, value2, "authParams");
            return (Criteria) this;
        }

        public Criteria andFeaturesIsNull() {
            addCriterion("FEATURES is null");
            return (Criteria) this;
        }

        public Criteria andFeaturesIsNotNull() {
            addCriterion("FEATURES is not null");
            return (Criteria) this;
        }

        public Criteria andFeaturesEqualTo(String value) {
            addCriterion("FEATURES =", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotEqualTo(String value) {
            addCriterion("FEATURES <>", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThan(String value) {
            addCriterion("FEATURES >", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThanOrEqualTo(String value) {
            addCriterion("FEATURES >=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThan(String value) {
            addCriterion("FEATURES <", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThanOrEqualTo(String value) {
            addCriterion("FEATURES <=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLike(String value) {
            addCriterion("FEATURES like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotLike(String value) {
            addCriterion("FEATURES not like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesIn(List<String> values) {
            addCriterion("FEATURES in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotIn(List<String> values) {
            addCriterion("FEATURES not in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesBetween(String value1, String value2) {
            addCriterion("FEATURES between", value1, value2, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotBetween(String value1, String value2) {
            addCriterion("FEATURES not between", value1, value2, "features");
            return (Criteria) this;
        }

        public Criteria andIcckeydevIsNull() {
            addCriterion("ICCKEYDEV is null");
            return (Criteria) this;
        }

        public Criteria andIcckeydevIsNotNull() {
            addCriterion("ICCKEYDEV is not null");
            return (Criteria) this;
        }

        public Criteria andIcckeydevEqualTo(String value) {
            addCriterion("ICCKEYDEV =", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevNotEqualTo(String value) {
            addCriterion("ICCKEYDEV <>", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevGreaterThan(String value) {
            addCriterion("ICCKEYDEV >", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevGreaterThanOrEqualTo(String value) {
            addCriterion("ICCKEYDEV >=", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevLessThan(String value) {
            addCriterion("ICCKEYDEV <", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevLessThanOrEqualTo(String value) {
            addCriterion("ICCKEYDEV <=", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevLike(String value) {
            addCriterion("ICCKEYDEV like", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevNotLike(String value) {
            addCriterion("ICCKEYDEV not like", value, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevIn(List<String> values) {
            addCriterion("ICCKEYDEV in", values, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevNotIn(List<String> values) {
            addCriterion("ICCKEYDEV not in", values, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevBetween(String value1, String value2) {
            addCriterion("ICCKEYDEV between", value1, value2, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andIcckeydevNotBetween(String value1, String value2) {
            addCriterion("ICCKEYDEV not between", value1, value2, "icckeydev");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimIsNull() {
            addCriterion("PREAUTH_BLK_LIM is null");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimIsNotNull() {
            addCriterion("PREAUTH_BLK_LIM is not null");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimEqualTo(Double value) {
            addCriterion("PREAUTH_BLK_LIM =", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimNotEqualTo(Double value) {
            addCriterion("PREAUTH_BLK_LIM <>", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimGreaterThan(Double value) {
            addCriterion("PREAUTH_BLK_LIM >", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimGreaterThanOrEqualTo(Double value) {
            addCriterion("PREAUTH_BLK_LIM >=", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimLessThan(Double value) {
            addCriterion("PREAUTH_BLK_LIM <", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimLessThanOrEqualTo(Double value) {
            addCriterion("PREAUTH_BLK_LIM <=", value, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimIn(List<Double> values) {
            addCriterion("PREAUTH_BLK_LIM in", values, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimNotIn(List<Double> values) {
            addCriterion("PREAUTH_BLK_LIM not in", values, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimBetween(Double value1, Double value2) {
            addCriterion("PREAUTH_BLK_LIM between", value1, value2, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthBlkLimNotBetween(Double value1, Double value2) {
            addCriterion("PREAUTH_BLK_LIM not between", value1, value2, "preauthBlkLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimIsNull() {
            addCriterion("PREAUTH_TOT_LIM is null");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimIsNotNull() {
            addCriterion("PREAUTH_TOT_LIM is not null");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimEqualTo(Double value) {
            addCriterion("PREAUTH_TOT_LIM =", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimNotEqualTo(Double value) {
            addCriterion("PREAUTH_TOT_LIM <>", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimGreaterThan(Double value) {
            addCriterion("PREAUTH_TOT_LIM >", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimGreaterThanOrEqualTo(Double value) {
            addCriterion("PREAUTH_TOT_LIM >=", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimLessThan(Double value) {
            addCriterion("PREAUTH_TOT_LIM <", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimLessThanOrEqualTo(Double value) {
            addCriterion("PREAUTH_TOT_LIM <=", value, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimIn(List<Double> values) {
            addCriterion("PREAUTH_TOT_LIM in", values, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimNotIn(List<Double> values) {
            addCriterion("PREAUTH_TOT_LIM not in", values, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimBetween(Double value1, Double value2) {
            addCriterion("PREAUTH_TOT_LIM between", value1, value2, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andPreauthTotLimNotBetween(Double value1, Double value2) {
            addCriterion("PREAUTH_TOT_LIM not between", value1, value2, "preauthTotLim");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNull() {
            addCriterion("CAT_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andCatParamsIsNotNull() {
            addCriterion("CAT_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andCatParamsEqualTo(String value) {
            addCriterion("CAT_PARAMS =", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotEqualTo(String value) {
            addCriterion("CAT_PARAMS <>", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThan(String value) {
            addCriterion("CAT_PARAMS >", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS >=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThan(String value) {
            addCriterion("CAT_PARAMS <", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLessThanOrEqualTo(String value) {
            addCriterion("CAT_PARAMS <=", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsLike(String value) {
            addCriterion("CAT_PARAMS like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotLike(String value) {
            addCriterion("CAT_PARAMS not like", value, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsIn(List<String> values) {
            addCriterion("CAT_PARAMS in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotIn(List<String> values) {
            addCriterion("CAT_PARAMS not in", values, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andCatParamsNotBetween(String value1, String value2) {
            addCriterion("CAT_PARAMS not between", value1, value2, "catParams");
            return (Criteria) this;
        }

        public Criteria andOptionsIsNull() {
            addCriterion("OPTIONS is null");
            return (Criteria) this;
        }

        public Criteria andOptionsIsNotNull() {
            addCriterion("OPTIONS is not null");
            return (Criteria) this;
        }

        public Criteria andOptionsEqualTo(String value) {
            addCriterion("OPTIONS =", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsNotEqualTo(String value) {
            addCriterion("OPTIONS <>", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsGreaterThan(String value) {
            addCriterion("OPTIONS >", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsGreaterThanOrEqualTo(String value) {
            addCriterion("OPTIONS >=", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsLessThan(String value) {
            addCriterion("OPTIONS <", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsLessThanOrEqualTo(String value) {
            addCriterion("OPTIONS <=", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsLike(String value) {
            addCriterion("OPTIONS like", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsNotLike(String value) {
            addCriterion("OPTIONS not like", value, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsIn(List<String> values) {
            addCriterion("OPTIONS in", values, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsNotIn(List<String> values) {
            addCriterion("OPTIONS not in", values, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsBetween(String value1, String value2) {
            addCriterion("OPTIONS between", value1, value2, "options");
            return (Criteria) this;
        }

        public Criteria andOptionsNotBetween(String value1, String value2) {
            addCriterion("OPTIONS not between", value1, value2, "options");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryIsNull() {
            addCriterion("PREAUTH_EXPIRY is null");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryIsNotNull() {
            addCriterion("PREAUTH_EXPIRY is not null");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryEqualTo(Integer value) {
            addCriterion("PREAUTH_EXPIRY =", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryNotEqualTo(Integer value) {
            addCriterion("PREAUTH_EXPIRY <>", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryGreaterThan(Integer value) {
            addCriterion("PREAUTH_EXPIRY >", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryGreaterThanOrEqualTo(Integer value) {
            addCriterion("PREAUTH_EXPIRY >=", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryLessThan(Integer value) {
            addCriterion("PREAUTH_EXPIRY <", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryLessThanOrEqualTo(Integer value) {
            addCriterion("PREAUTH_EXPIRY <=", value, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryIn(List<Integer> values) {
            addCriterion("PREAUTH_EXPIRY in", values, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryNotIn(List<Integer> values) {
            addCriterion("PREAUTH_EXPIRY not in", values, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryBetween(Integer value1, Integer value2) {
            addCriterion("PREAUTH_EXPIRY between", value1, value2, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andPreauthExpiryNotBetween(Integer value1, Integer value2) {
            addCriterion("PREAUTH_EXPIRY not between", value1, value2, "preauthExpiry");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdIsNull() {
            addCriterion("CRDSTATUSTRANSSET_ID is null");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdIsNotNull() {
            addCriterion("CRDSTATUSTRANSSET_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdEqualTo(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID =", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdNotEqualTo(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID <>", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdGreaterThan(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID >", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID >=", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdLessThan(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID <", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdLessThanOrEqualTo(Long value) {
            addCriterion("CRDSTATUSTRANSSET_ID <=", value, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdIn(List<Long> values) {
            addCriterion("CRDSTATUSTRANSSET_ID in", values, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdNotIn(List<Long> values) {
            addCriterion("CRDSTATUSTRANSSET_ID not in", values, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdBetween(Long value1, Long value2) {
            addCriterion("CRDSTATUSTRANSSET_ID between", value1, value2, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andCrdstatustranssetIdNotBetween(Long value1, Long value2) {
            addCriterion("CRDSTATUSTRANSSET_ID not between", value1, value2, "crdstatustranssetId");
            return (Criteria) this;
        }

        public Criteria andValidityIsNull() {
            addCriterion("VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andValidityIsNotNull() {
            addCriterion("VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEqualTo(Integer value) {
            addCriterion("VALIDITY =", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotEqualTo(Integer value) {
            addCriterion("VALIDITY <>", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityGreaterThan(Integer value) {
            addCriterion("VALIDITY >", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("VALIDITY >=", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityLessThan(Integer value) {
            addCriterion("VALIDITY <", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityLessThanOrEqualTo(Integer value) {
            addCriterion("VALIDITY <=", value, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityIn(List<Integer> values) {
            addCriterion("VALIDITY in", values, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotIn(List<Integer> values) {
            addCriterion("VALIDITY not in", values, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityBetween(Integer value1, Integer value2) {
            addCriterion("VALIDITY between", value1, value2, "validity");
            return (Criteria) this;
        }

        public Criteria andValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("VALIDITY not between", value1, value2, "validity");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissIsNull() {
            addCriterion("MINVALDURISS is null");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissIsNotNull() {
            addCriterion("MINVALDURISS is not null");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissEqualTo(Integer value) {
            addCriterion("MINVALDURISS =", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissNotEqualTo(Integer value) {
            addCriterion("MINVALDURISS <>", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissGreaterThan(Integer value) {
            addCriterion("MINVALDURISS >", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissGreaterThanOrEqualTo(Integer value) {
            addCriterion("MINVALDURISS >=", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissLessThan(Integer value) {
            addCriterion("MINVALDURISS <", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissLessThanOrEqualTo(Integer value) {
            addCriterion("MINVALDURISS <=", value, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissIn(List<Integer> values) {
            addCriterion("MINVALDURISS in", values, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissNotIn(List<Integer> values) {
            addCriterion("MINVALDURISS not in", values, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissBetween(Integer value1, Integer value2) {
            addCriterion("MINVALDURISS between", value1, value2, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurissNotBetween(Integer value1, Integer value2) {
            addCriterion("MINVALDURISS not between", value1, value2, "minvalduriss");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgIsNull() {
            addCriterion("MINVALDURDMG is null");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgIsNotNull() {
            addCriterion("MINVALDURDMG is not null");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgEqualTo(Integer value) {
            addCriterion("MINVALDURDMG =", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgNotEqualTo(Integer value) {
            addCriterion("MINVALDURDMG <>", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgGreaterThan(Integer value) {
            addCriterion("MINVALDURDMG >", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgGreaterThanOrEqualTo(Integer value) {
            addCriterion("MINVALDURDMG >=", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgLessThan(Integer value) {
            addCriterion("MINVALDURDMG <", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgLessThanOrEqualTo(Integer value) {
            addCriterion("MINVALDURDMG <=", value, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgIn(List<Integer> values) {
            addCriterion("MINVALDURDMG in", values, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgNotIn(List<Integer> values) {
            addCriterion("MINVALDURDMG not in", values, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgBetween(Integer value1, Integer value2) {
            addCriterion("MINVALDURDMG between", value1, value2, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andMinvaldurdmgNotBetween(Integer value1, Integer value2) {
            addCriterion("MINVALDURDMG not between", value1, value2, "minvaldurdmg");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodIsNull() {
            addCriterion("RNWPSTEXPPERIOD is null");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodIsNotNull() {
            addCriterion("RNWPSTEXPPERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodEqualTo(Integer value) {
            addCriterion("RNWPSTEXPPERIOD =", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodNotEqualTo(Integer value) {
            addCriterion("RNWPSTEXPPERIOD <>", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodGreaterThan(Integer value) {
            addCriterion("RNWPSTEXPPERIOD >", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodGreaterThanOrEqualTo(Integer value) {
            addCriterion("RNWPSTEXPPERIOD >=", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodLessThan(Integer value) {
            addCriterion("RNWPSTEXPPERIOD <", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodLessThanOrEqualTo(Integer value) {
            addCriterion("RNWPSTEXPPERIOD <=", value, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodIn(List<Integer> values) {
            addCriterion("RNWPSTEXPPERIOD in", values, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodNotIn(List<Integer> values) {
            addCriterion("RNWPSTEXPPERIOD not in", values, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodBetween(Integer value1, Integer value2) {
            addCriterion("RNWPSTEXPPERIOD between", value1, value2, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpstexpperiodNotBetween(Integer value1, Integer value2) {
            addCriterion("RNWPSTEXPPERIOD not between", value1, value2, "rnwpstexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodIsNull() {
            addCriterion("RNWPREEXPPERIOD is null");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodIsNotNull() {
            addCriterion("RNWPREEXPPERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodEqualTo(Integer value) {
            addCriterion("RNWPREEXPPERIOD =", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodNotEqualTo(Integer value) {
            addCriterion("RNWPREEXPPERIOD <>", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodGreaterThan(Integer value) {
            addCriterion("RNWPREEXPPERIOD >", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodGreaterThanOrEqualTo(Integer value) {
            addCriterion("RNWPREEXPPERIOD >=", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodLessThan(Integer value) {
            addCriterion("RNWPREEXPPERIOD <", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodLessThanOrEqualTo(Integer value) {
            addCriterion("RNWPREEXPPERIOD <=", value, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodIn(List<Integer> values) {
            addCriterion("RNWPREEXPPERIOD in", values, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodNotIn(List<Integer> values) {
            addCriterion("RNWPREEXPPERIOD not in", values, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodBetween(Integer value1, Integer value2) {
            addCriterion("RNWPREEXPPERIOD between", value1, value2, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwpreexpperiodNotBetween(Integer value1, Integer value2) {
            addCriterion("RNWPREEXPPERIOD not between", value1, value2, "rnwpreexpperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodIsNull() {
            addCriterion("RNWEARLYPERIOD is null");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodIsNotNull() {
            addCriterion("RNWEARLYPERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodEqualTo(Integer value) {
            addCriterion("RNWEARLYPERIOD =", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodNotEqualTo(Integer value) {
            addCriterion("RNWEARLYPERIOD <>", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodGreaterThan(Integer value) {
            addCriterion("RNWEARLYPERIOD >", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodGreaterThanOrEqualTo(Integer value) {
            addCriterion("RNWEARLYPERIOD >=", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodLessThan(Integer value) {
            addCriterion("RNWEARLYPERIOD <", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodLessThanOrEqualTo(Integer value) {
            addCriterion("RNWEARLYPERIOD <=", value, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodIn(List<Integer> values) {
            addCriterion("RNWEARLYPERIOD in", values, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodNotIn(List<Integer> values) {
            addCriterion("RNWEARLYPERIOD not in", values, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodBetween(Integer value1, Integer value2) {
            addCriterion("RNWEARLYPERIOD between", value1, value2, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andRnwearlyperiodNotBetween(Integer value1, Integer value2) {
            addCriterion("RNWEARLYPERIOD not between", value1, value2, "rnwearlyperiod");
            return (Criteria) this;
        }

        public Criteria andUsrdata1IsNull() {
            addCriterion("USRDATA1 is null");
            return (Criteria) this;
        }

        public Criteria andUsrdata1IsNotNull() {
            addCriterion("USRDATA1 is not null");
            return (Criteria) this;
        }

        public Criteria andUsrdata1EqualTo(String value) {
            addCriterion("USRDATA1 =", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotEqualTo(String value) {
            addCriterion("USRDATA1 <>", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1GreaterThan(String value) {
            addCriterion("USRDATA1 >", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1GreaterThanOrEqualTo(String value) {
            addCriterion("USRDATA1 >=", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1LessThan(String value) {
            addCriterion("USRDATA1 <", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1LessThanOrEqualTo(String value) {
            addCriterion("USRDATA1 <=", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1Like(String value) {
            addCriterion("USRDATA1 like", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotLike(String value) {
            addCriterion("USRDATA1 not like", value, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1In(List<String> values) {
            addCriterion("USRDATA1 in", values, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotIn(List<String> values) {
            addCriterion("USRDATA1 not in", values, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1Between(String value1, String value2) {
            addCriterion("USRDATA1 between", value1, value2, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata1NotBetween(String value1, String value2) {
            addCriterion("USRDATA1 not between", value1, value2, "usrdata1");
            return (Criteria) this;
        }

        public Criteria andUsrdata2IsNull() {
            addCriterion("USRDATA2 is null");
            return (Criteria) this;
        }

        public Criteria andUsrdata2IsNotNull() {
            addCriterion("USRDATA2 is not null");
            return (Criteria) this;
        }

        public Criteria andUsrdata2EqualTo(String value) {
            addCriterion("USRDATA2 =", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotEqualTo(String value) {
            addCriterion("USRDATA2 <>", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2GreaterThan(String value) {
            addCriterion("USRDATA2 >", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2GreaterThanOrEqualTo(String value) {
            addCriterion("USRDATA2 >=", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2LessThan(String value) {
            addCriterion("USRDATA2 <", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2LessThanOrEqualTo(String value) {
            addCriterion("USRDATA2 <=", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2Like(String value) {
            addCriterion("USRDATA2 like", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotLike(String value) {
            addCriterion("USRDATA2 not like", value, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2In(List<String> values) {
            addCriterion("USRDATA2 in", values, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotIn(List<String> values) {
            addCriterion("USRDATA2 not in", values, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2Between(String value1, String value2) {
            addCriterion("USRDATA2 between", value1, value2, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andUsrdata2NotBetween(String value1, String value2) {
            addCriterion("USRDATA2 not between", value1, value2, "usrdata2");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdIsNull() {
            addCriterion("CRDPLSTCTYP_ID is null");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdIsNotNull() {
            addCriterion("CRDPLSTCTYP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdEqualTo(Long value) {
            addCriterion("CRDPLSTCTYP_ID =", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdNotEqualTo(Long value) {
            addCriterion("CRDPLSTCTYP_ID <>", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdGreaterThan(Long value) {
            addCriterion("CRDPLSTCTYP_ID >", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CRDPLSTCTYP_ID >=", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdLessThan(Long value) {
            addCriterion("CRDPLSTCTYP_ID <", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdLessThanOrEqualTo(Long value) {
            addCriterion("CRDPLSTCTYP_ID <=", value, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdIn(List<Long> values) {
            addCriterion("CRDPLSTCTYP_ID in", values, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdNotIn(List<Long> values) {
            addCriterion("CRDPLSTCTYP_ID not in", values, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdBetween(Long value1, Long value2) {
            addCriterion("CRDPLSTCTYP_ID between", value1, value2, "crdplstctypId");
            return (Criteria) this;
        }

        public Criteria andCrdplstctypIdNotBetween(Long value1, Long value2) {
            addCriterion("CRDPLSTCTYP_ID not between", value1, value2, "crdplstctypId");
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