package cn.yufu.yufuOld.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TerminalYufuOldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TerminalYufuOldExample() {
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

        public Criteria andSserialnumIsNull() {
            addCriterion("sSerialNum is null");
            return (Criteria) this;
        }

        public Criteria andSserialnumIsNotNull() {
            addCriterion("sSerialNum is not null");
            return (Criteria) this;
        }

        public Criteria andSserialnumEqualTo(String value) {
            addCriterion("sSerialNum =", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumNotEqualTo(String value) {
            addCriterion("sSerialNum <>", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumGreaterThan(String value) {
            addCriterion("sSerialNum >", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumGreaterThanOrEqualTo(String value) {
            addCriterion("sSerialNum >=", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumLessThan(String value) {
            addCriterion("sSerialNum <", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumLessThanOrEqualTo(String value) {
            addCriterion("sSerialNum <=", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumLike(String value) {
            addCriterion("sSerialNum like", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumNotLike(String value) {
            addCriterion("sSerialNum not like", value, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumIn(List<String> values) {
            addCriterion("sSerialNum in", values, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumNotIn(List<String> values) {
            addCriterion("sSerialNum not in", values, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumBetween(String value1, String value2) {
            addCriterion("sSerialNum between", value1, value2, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andSserialnumNotBetween(String value1, String value2) {
            addCriterion("sSerialNum not between", value1, value2, "sserialnum");
            return (Criteria) this;
        }

        public Criteria andTtypeIsNull() {
            addCriterion("ttype is null");
            return (Criteria) this;
        }

        public Criteria andTtypeIsNotNull() {
            addCriterion("ttype is not null");
            return (Criteria) this;
        }

        public Criteria andTtypeEqualTo(String value) {
            addCriterion("ttype =", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeNotEqualTo(String value) {
            addCriterion("ttype <>", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeGreaterThan(String value) {
            addCriterion("ttype >", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeGreaterThanOrEqualTo(String value) {
            addCriterion("ttype >=", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeLessThan(String value) {
            addCriterion("ttype <", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeLessThanOrEqualTo(String value) {
            addCriterion("ttype <=", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeLike(String value) {
            addCriterion("ttype like", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeNotLike(String value) {
            addCriterion("ttype not like", value, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeIn(List<String> values) {
            addCriterion("ttype in", values, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeNotIn(List<String> values) {
            addCriterion("ttype not in", values, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeBetween(String value1, String value2) {
            addCriterion("ttype between", value1, value2, "ttype");
            return (Criteria) this;
        }

        public Criteria andTtypeNotBetween(String value1, String value2) {
            addCriterion("ttype not between", value1, value2, "ttype");
            return (Criteria) this;
        }

        public Criteria andCommodeIsNull() {
            addCriterion("commode is null");
            return (Criteria) this;
        }

        public Criteria andCommodeIsNotNull() {
            addCriterion("commode is not null");
            return (Criteria) this;
        }

        public Criteria andCommodeEqualTo(String value) {
            addCriterion("commode =", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeNotEqualTo(String value) {
            addCriterion("commode <>", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeGreaterThan(String value) {
            addCriterion("commode >", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeGreaterThanOrEqualTo(String value) {
            addCriterion("commode >=", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeLessThan(String value) {
            addCriterion("commode <", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeLessThanOrEqualTo(String value) {
            addCriterion("commode <=", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeLike(String value) {
            addCriterion("commode like", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeNotLike(String value) {
            addCriterion("commode not like", value, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeIn(List<String> values) {
            addCriterion("commode in", values, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeNotIn(List<String> values) {
            addCriterion("commode not in", values, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeBetween(String value1, String value2) {
            addCriterion("commode between", value1, value2, "commode");
            return (Criteria) this;
        }

        public Criteria andCommodeNotBetween(String value1, String value2) {
            addCriterion("commode not between", value1, value2, "commode");
            return (Criteria) this;
        }

        public Criteria andManufIsNull() {
            addCriterion("manuf is null");
            return (Criteria) this;
        }

        public Criteria andManufIsNotNull() {
            addCriterion("manuf is not null");
            return (Criteria) this;
        }

        public Criteria andManufEqualTo(String value) {
            addCriterion("manuf =", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufNotEqualTo(String value) {
            addCriterion("manuf <>", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufGreaterThan(String value) {
            addCriterion("manuf >", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufGreaterThanOrEqualTo(String value) {
            addCriterion("manuf >=", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufLessThan(String value) {
            addCriterion("manuf <", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufLessThanOrEqualTo(String value) {
            addCriterion("manuf <=", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufLike(String value) {
            addCriterion("manuf like", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufNotLike(String value) {
            addCriterion("manuf not like", value, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufIn(List<String> values) {
            addCriterion("manuf in", values, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufNotIn(List<String> values) {
            addCriterion("manuf not in", values, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufBetween(String value1, String value2) {
            addCriterion("manuf between", value1, value2, "manuf");
            return (Criteria) this;
        }

        public Criteria andManufNotBetween(String value1, String value2) {
            addCriterion("manuf not between", value1, value2, "manuf");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andRefIsNull() {
            addCriterion("ref is null");
            return (Criteria) this;
        }

        public Criteria andRefIsNotNull() {
            addCriterion("ref is not null");
            return (Criteria) this;
        }

        public Criteria andRefEqualTo(String value) {
            addCriterion("ref =", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotEqualTo(String value) {
            addCriterion("ref <>", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefGreaterThan(String value) {
            addCriterion("ref >", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefGreaterThanOrEqualTo(String value) {
            addCriterion("ref >=", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLessThan(String value) {
            addCriterion("ref <", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLessThanOrEqualTo(String value) {
            addCriterion("ref <=", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLike(String value) {
            addCriterion("ref like", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotLike(String value) {
            addCriterion("ref not like", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefIn(List<String> values) {
            addCriterion("ref in", values, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotIn(List<String> values) {
            addCriterion("ref not in", values, "ref");
            return (Criteria) this;
        }

        public Criteria andRefBetween(String value1, String value2) {
            addCriterion("ref between", value1, value2, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotBetween(String value1, String value2) {
            addCriterion("ref not between", value1, value2, "ref");
            return (Criteria) this;
        }

        public Criteria andInvnumIsNull() {
            addCriterion("invnum is null");
            return (Criteria) this;
        }

        public Criteria andInvnumIsNotNull() {
            addCriterion("invnum is not null");
            return (Criteria) this;
        }

        public Criteria andInvnumEqualTo(String value) {
            addCriterion("invnum =", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumNotEqualTo(String value) {
            addCriterion("invnum <>", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumGreaterThan(String value) {
            addCriterion("invnum >", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumGreaterThanOrEqualTo(String value) {
            addCriterion("invnum >=", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumLessThan(String value) {
            addCriterion("invnum <", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumLessThanOrEqualTo(String value) {
            addCriterion("invnum <=", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumLike(String value) {
            addCriterion("invnum like", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumNotLike(String value) {
            addCriterion("invnum not like", value, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumIn(List<String> values) {
            addCriterion("invnum in", values, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumNotIn(List<String> values) {
            addCriterion("invnum not in", values, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumBetween(String value1, String value2) {
            addCriterion("invnum between", value1, value2, "invnum");
            return (Criteria) this;
        }

        public Criteria andInvnumNotBetween(String value1, String value2) {
            addCriterion("invnum not between", value1, value2, "invnum");
            return (Criteria) this;
        }

        public Criteria andMainmIsNull() {
            addCriterion("mainm is null");
            return (Criteria) this;
        }

        public Criteria andMainmIsNotNull() {
            addCriterion("mainm is not null");
            return (Criteria) this;
        }

        public Criteria andMainmEqualTo(String value) {
            addCriterion("mainm =", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmNotEqualTo(String value) {
            addCriterion("mainm <>", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmGreaterThan(String value) {
            addCriterion("mainm >", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmGreaterThanOrEqualTo(String value) {
            addCriterion("mainm >=", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmLessThan(String value) {
            addCriterion("mainm <", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmLessThanOrEqualTo(String value) {
            addCriterion("mainm <=", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmLike(String value) {
            addCriterion("mainm like", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmNotLike(String value) {
            addCriterion("mainm not like", value, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmIn(List<String> values) {
            addCriterion("mainm in", values, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmNotIn(List<String> values) {
            addCriterion("mainm not in", values, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmBetween(String value1, String value2) {
            addCriterion("mainm between", value1, value2, "mainm");
            return (Criteria) this;
        }

        public Criteria andMainmNotBetween(String value1, String value2) {
            addCriterion("mainm not between", value1, value2, "mainm");
            return (Criteria) this;
        }

        public Criteria andLruidIsNull() {
            addCriterion("lruid is null");
            return (Criteria) this;
        }

        public Criteria andLruidIsNotNull() {
            addCriterion("lruid is not null");
            return (Criteria) this;
        }

        public Criteria andLruidEqualTo(String value) {
            addCriterion("lruid =", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotEqualTo(String value) {
            addCriterion("lruid <>", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidGreaterThan(String value) {
            addCriterion("lruid >", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidGreaterThanOrEqualTo(String value) {
            addCriterion("lruid >=", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLessThan(String value) {
            addCriterion("lruid <", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLessThanOrEqualTo(String value) {
            addCriterion("lruid <=", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLike(String value) {
            addCriterion("lruid like", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotLike(String value) {
            addCriterion("lruid not like", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidIn(List<String> values) {
            addCriterion("lruid in", values, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotIn(List<String> values) {
            addCriterion("lruid not in", values, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidBetween(String value1, String value2) {
            addCriterion("lruid between", value1, value2, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotBetween(String value1, String value2) {
            addCriterion("lruid not between", value1, value2, "lruid");
            return (Criteria) this;
        }

        public Criteria andEmploydateIsNull() {
            addCriterion("employdate is null");
            return (Criteria) this;
        }

        public Criteria andEmploydateIsNotNull() {
            addCriterion("employdate is not null");
            return (Criteria) this;
        }

        public Criteria andEmploydateEqualTo(Date value) {
            addCriterion("employdate =", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateNotEqualTo(Date value) {
            addCriterion("employdate <>", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateGreaterThan(Date value) {
            addCriterion("employdate >", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateGreaterThanOrEqualTo(Date value) {
            addCriterion("employdate >=", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateLessThan(Date value) {
            addCriterion("employdate <", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateLessThanOrEqualTo(Date value) {
            addCriterion("employdate <=", value, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateIn(List<Date> values) {
            addCriterion("employdate in", values, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateNotIn(List<Date> values) {
            addCriterion("employdate not in", values, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateBetween(Date value1, Date value2) {
            addCriterion("employdate between", value1, value2, "employdate");
            return (Criteria) this;
        }

        public Criteria andEmploydateNotBetween(Date value1, Date value2) {
            addCriterion("employdate not between", value1, value2, "employdate");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNull() {
            addCriterion("Batchno is null");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNotNull() {
            addCriterion("Batchno is not null");
            return (Criteria) this;
        }

        public Criteria andBatchnoEqualTo(String value) {
            addCriterion("Batchno =", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotEqualTo(String value) {
            addCriterion("Batchno <>", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThan(String value) {
            addCriterion("Batchno >", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("Batchno >=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThan(String value) {
            addCriterion("Batchno <", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThanOrEqualTo(String value) {
            addCriterion("Batchno <=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLike(String value) {
            addCriterion("Batchno like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotLike(String value) {
            addCriterion("Batchno not like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoIn(List<String> values) {
            addCriterion("Batchno in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotIn(List<String> values) {
            addCriterion("Batchno not in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoBetween(String value1, String value2) {
            addCriterion("Batchno between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotBetween(String value1, String value2) {
            addCriterion("Batchno not between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andComalgIsNull() {
            addCriterion("comalg is null");
            return (Criteria) this;
        }

        public Criteria andComalgIsNotNull() {
            addCriterion("comalg is not null");
            return (Criteria) this;
        }

        public Criteria andComalgEqualTo(String value) {
            addCriterion("comalg =", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgNotEqualTo(String value) {
            addCriterion("comalg <>", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgGreaterThan(String value) {
            addCriterion("comalg >", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgGreaterThanOrEqualTo(String value) {
            addCriterion("comalg >=", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgLessThan(String value) {
            addCriterion("comalg <", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgLessThanOrEqualTo(String value) {
            addCriterion("comalg <=", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgLike(String value) {
            addCriterion("comalg like", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgNotLike(String value) {
            addCriterion("comalg not like", value, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgIn(List<String> values) {
            addCriterion("comalg in", values, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgNotIn(List<String> values) {
            addCriterion("comalg not in", values, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgBetween(String value1, String value2) {
            addCriterion("comalg between", value1, value2, "comalg");
            return (Criteria) this;
        }

        public Criteria andComalgNotBetween(String value1, String value2) {
            addCriterion("comalg not between", value1, value2, "comalg");
            return (Criteria) this;
        }

        public Criteria andTpduIsNull() {
            addCriterion("TPDU is null");
            return (Criteria) this;
        }

        public Criteria andTpduIsNotNull() {
            addCriterion("TPDU is not null");
            return (Criteria) this;
        }

        public Criteria andTpduEqualTo(String value) {
            addCriterion("TPDU =", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduNotEqualTo(String value) {
            addCriterion("TPDU <>", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduGreaterThan(String value) {
            addCriterion("TPDU >", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduGreaterThanOrEqualTo(String value) {
            addCriterion("TPDU >=", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduLessThan(String value) {
            addCriterion("TPDU <", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduLessThanOrEqualTo(String value) {
            addCriterion("TPDU <=", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduLike(String value) {
            addCriterion("TPDU like", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduNotLike(String value) {
            addCriterion("TPDU not like", value, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduIn(List<String> values) {
            addCriterion("TPDU in", values, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduNotIn(List<String> values) {
            addCriterion("TPDU not in", values, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduBetween(String value1, String value2) {
            addCriterion("TPDU between", value1, value2, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTpduNotBetween(String value1, String value2) {
            addCriterion("TPDU not between", value1, value2, "tpdu");
            return (Criteria) this;
        }

        public Criteria andTermidIsNull() {
            addCriterion("termid is null");
            return (Criteria) this;
        }

        public Criteria andTermidIsNotNull() {
            addCriterion("termid is not null");
            return (Criteria) this;
        }

        public Criteria andTermidEqualTo(Integer value) {
            addCriterion("termid =", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidNotEqualTo(Integer value) {
            addCriterion("termid <>", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidGreaterThan(Integer value) {
            addCriterion("termid >", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidGreaterThanOrEqualTo(Integer value) {
            addCriterion("termid >=", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidLessThan(Integer value) {
            addCriterion("termid <", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidLessThanOrEqualTo(Integer value) {
            addCriterion("termid <=", value, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidIn(List<Integer> values) {
            addCriterion("termid in", values, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidNotIn(List<Integer> values) {
            addCriterion("termid not in", values, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidBetween(Integer value1, Integer value2) {
            addCriterion("termid between", value1, value2, "termid");
            return (Criteria) this;
        }

        public Criteria andTermidNotBetween(Integer value1, Integer value2) {
            addCriterion("termid not between", value1, value2, "termid");
            return (Criteria) this;
        }

        public Criteria andComidIsNull() {
            addCriterion("comid is null");
            return (Criteria) this;
        }

        public Criteria andComidIsNotNull() {
            addCriterion("comid is not null");
            return (Criteria) this;
        }

        public Criteria andComidEqualTo(Integer value) {
            addCriterion("comid =", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotEqualTo(Integer value) {
            addCriterion("comid <>", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidGreaterThan(Integer value) {
            addCriterion("comid >", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidGreaterThanOrEqualTo(Integer value) {
            addCriterion("comid >=", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidLessThan(Integer value) {
            addCriterion("comid <", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidLessThanOrEqualTo(Integer value) {
            addCriterion("comid <=", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidIn(List<Integer> values) {
            addCriterion("comid in", values, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotIn(List<Integer> values) {
            addCriterion("comid not in", values, "comid");
            return (Criteria) this;
        }

        public Criteria andComidBetween(Integer value1, Integer value2) {
            addCriterion("comid between", value1, value2, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotBetween(Integer value1, Integer value2) {
            addCriterion("comid not between", value1, value2, "comid");
            return (Criteria) this;
        }

        public Criteria andTeraddressIsNull() {
            addCriterion("teraddress is null");
            return (Criteria) this;
        }

        public Criteria andTeraddressIsNotNull() {
            addCriterion("teraddress is not null");
            return (Criteria) this;
        }

        public Criteria andTeraddressEqualTo(String value) {
            addCriterion("teraddress =", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressNotEqualTo(String value) {
            addCriterion("teraddress <>", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressGreaterThan(String value) {
            addCriterion("teraddress >", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressGreaterThanOrEqualTo(String value) {
            addCriterion("teraddress >=", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressLessThan(String value) {
            addCriterion("teraddress <", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressLessThanOrEqualTo(String value) {
            addCriterion("teraddress <=", value, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressIn(List<String> values) {
            addCriterion("teraddress in", values, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressNotIn(List<String> values) {
            addCriterion("teraddress not in", values, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressBetween(String value1, String value2) {
            addCriterion("teraddress between", value1, value2, "teraddress");
            return (Criteria) this;
        }

        public Criteria andTeraddressNotBetween(String value1, String value2) {
            addCriterion("teraddress not between", value1, value2, "teraddress");
            return (Criteria) this;
        }

        public Criteria andBstateIsNull() {
            addCriterion("bstate is null");
            return (Criteria) this;
        }

        public Criteria andBstateIsNotNull() {
            addCriterion("bstate is not null");
            return (Criteria) this;
        }

        public Criteria andBstateEqualTo(Integer value) {
            addCriterion("bstate =", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateNotEqualTo(Integer value) {
            addCriterion("bstate <>", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateGreaterThan(Integer value) {
            addCriterion("bstate >", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bstate >=", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateLessThan(Integer value) {
            addCriterion("bstate <", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateLessThanOrEqualTo(Integer value) {
            addCriterion("bstate <=", value, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateIn(List<Integer> values) {
            addCriterion("bstate in", values, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateNotIn(List<Integer> values) {
            addCriterion("bstate not in", values, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateBetween(Integer value1, Integer value2) {
            addCriterion("bstate between", value1, value2, "bstate");
            return (Criteria) this;
        }

        public Criteria andBstateNotBetween(Integer value1, Integer value2) {
            addCriterion("bstate not between", value1, value2, "bstate");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNull() {
            addCriterion("PIN_Key is null");
            return (Criteria) this;
        }

        public Criteria andPinKeyIsNotNull() {
            addCriterion("PIN_Key is not null");
            return (Criteria) this;
        }

        public Criteria andPinKeyEqualTo(String value) {
            addCriterion("PIN_Key =", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotEqualTo(String value) {
            addCriterion("PIN_Key <>", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThan(String value) {
            addCriterion("PIN_Key >", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PIN_Key >=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThan(String value) {
            addCriterion("PIN_Key <", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLessThanOrEqualTo(String value) {
            addCriterion("PIN_Key <=", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyLike(String value) {
            addCriterion("PIN_Key like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotLike(String value) {
            addCriterion("PIN_Key not like", value, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyIn(List<String> values) {
            addCriterion("PIN_Key in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotIn(List<String> values) {
            addCriterion("PIN_Key not in", values, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyBetween(String value1, String value2) {
            addCriterion("PIN_Key between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinKeyNotBetween(String value1, String value2) {
            addCriterion("PIN_Key not between", value1, value2, "pinKey");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueIsNull() {
            addCriterion("PIN_CHECKVALUE is null");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueIsNotNull() {
            addCriterion("PIN_CHECKVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueEqualTo(String value) {
            addCriterion("PIN_CHECKVALUE =", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueNotEqualTo(String value) {
            addCriterion("PIN_CHECKVALUE <>", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueGreaterThan(String value) {
            addCriterion("PIN_CHECKVALUE >", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueGreaterThanOrEqualTo(String value) {
            addCriterion("PIN_CHECKVALUE >=", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueLessThan(String value) {
            addCriterion("PIN_CHECKVALUE <", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueLessThanOrEqualTo(String value) {
            addCriterion("PIN_CHECKVALUE <=", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueLike(String value) {
            addCriterion("PIN_CHECKVALUE like", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueNotLike(String value) {
            addCriterion("PIN_CHECKVALUE not like", value, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueIn(List<String> values) {
            addCriterion("PIN_CHECKVALUE in", values, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueNotIn(List<String> values) {
            addCriterion("PIN_CHECKVALUE not in", values, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueBetween(String value1, String value2) {
            addCriterion("PIN_CHECKVALUE between", value1, value2, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andPinCheckvalueNotBetween(String value1, String value2) {
            addCriterion("PIN_CHECKVALUE not between", value1, value2, "pinCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyIsNull() {
            addCriterion("Working_Key is null");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyIsNotNull() {
            addCriterion("Working_Key is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyEqualTo(String value) {
            addCriterion("Working_Key =", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyNotEqualTo(String value) {
            addCriterion("Working_Key <>", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyGreaterThan(String value) {
            addCriterion("Working_Key >", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyGreaterThanOrEqualTo(String value) {
            addCriterion("Working_Key >=", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyLessThan(String value) {
            addCriterion("Working_Key <", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyLessThanOrEqualTo(String value) {
            addCriterion("Working_Key <=", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyLike(String value) {
            addCriterion("Working_Key like", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyNotLike(String value) {
            addCriterion("Working_Key not like", value, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyIn(List<String> values) {
            addCriterion("Working_Key in", values, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyNotIn(List<String> values) {
            addCriterion("Working_Key not in", values, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyBetween(String value1, String value2) {
            addCriterion("Working_Key between", value1, value2, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingKeyNotBetween(String value1, String value2) {
            addCriterion("Working_Key not between", value1, value2, "workingKey");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueIsNull() {
            addCriterion("Working_CheckValue is null");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueIsNotNull() {
            addCriterion("Working_CheckValue is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueEqualTo(String value) {
            addCriterion("Working_CheckValue =", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueNotEqualTo(String value) {
            addCriterion("Working_CheckValue <>", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueGreaterThan(String value) {
            addCriterion("Working_CheckValue >", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueGreaterThanOrEqualTo(String value) {
            addCriterion("Working_CheckValue >=", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueLessThan(String value) {
            addCriterion("Working_CheckValue <", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueLessThanOrEqualTo(String value) {
            addCriterion("Working_CheckValue <=", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueLike(String value) {
            addCriterion("Working_CheckValue like", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueNotLike(String value) {
            addCriterion("Working_CheckValue not like", value, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueIn(List<String> values) {
            addCriterion("Working_CheckValue in", values, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueNotIn(List<String> values) {
            addCriterion("Working_CheckValue not in", values, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueBetween(String value1, String value2) {
            addCriterion("Working_CheckValue between", value1, value2, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andWorkingCheckvalueNotBetween(String value1, String value2) {
            addCriterion("Working_CheckValue not between", value1, value2, "workingCheckvalue");
            return (Criteria) this;
        }

        public Criteria andAccountsflagIsNull() {
            addCriterion("Accountsflag is null");
            return (Criteria) this;
        }

        public Criteria andAccountsflagIsNotNull() {
            addCriterion("Accountsflag is not null");
            return (Criteria) this;
        }

        public Criteria andAccountsflagEqualTo(Integer value) {
            addCriterion("Accountsflag =", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagNotEqualTo(Integer value) {
            addCriterion("Accountsflag <>", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagGreaterThan(Integer value) {
            addCriterion("Accountsflag >", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("Accountsflag >=", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagLessThan(Integer value) {
            addCriterion("Accountsflag <", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagLessThanOrEqualTo(Integer value) {
            addCriterion("Accountsflag <=", value, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagIn(List<Integer> values) {
            addCriterion("Accountsflag in", values, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagNotIn(List<Integer> values) {
            addCriterion("Accountsflag not in", values, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagBetween(Integer value1, Integer value2) {
            addCriterion("Accountsflag between", value1, value2, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsflagNotBetween(Integer value1, Integer value2) {
            addCriterion("Accountsflag not between", value1, value2, "accountsflag");
            return (Criteria) this;
        }

        public Criteria andAccountsdateIsNull() {
            addCriterion("Accountsdate is null");
            return (Criteria) this;
        }

        public Criteria andAccountsdateIsNotNull() {
            addCriterion("Accountsdate is not null");
            return (Criteria) this;
        }

        public Criteria andAccountsdateEqualTo(Date value) {
            addCriterion("Accountsdate =", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateNotEqualTo(Date value) {
            addCriterion("Accountsdate <>", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateGreaterThan(Date value) {
            addCriterion("Accountsdate >", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateGreaterThanOrEqualTo(Date value) {
            addCriterion("Accountsdate >=", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateLessThan(Date value) {
            addCriterion("Accountsdate <", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateLessThanOrEqualTo(Date value) {
            addCriterion("Accountsdate <=", value, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateIn(List<Date> values) {
            addCriterion("Accountsdate in", values, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateNotIn(List<Date> values) {
            addCriterion("Accountsdate not in", values, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateBetween(Date value1, Date value2) {
            addCriterion("Accountsdate between", value1, value2, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andAccountsdateNotBetween(Date value1, Date value2) {
            addCriterion("Accountsdate not between", value1, value2, "accountsdate");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordIsNull() {
            addCriterion("sSerialNumPassWord is null");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordIsNotNull() {
            addCriterion("sSerialNumPassWord is not null");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordEqualTo(String value) {
            addCriterion("sSerialNumPassWord =", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordNotEqualTo(String value) {
            addCriterion("sSerialNumPassWord <>", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordGreaterThan(String value) {
            addCriterion("sSerialNumPassWord >", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("sSerialNumPassWord >=", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordLessThan(String value) {
            addCriterion("sSerialNumPassWord <", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordLessThanOrEqualTo(String value) {
            addCriterion("sSerialNumPassWord <=", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordLike(String value) {
            addCriterion("sSerialNumPassWord like", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordNotLike(String value) {
            addCriterion("sSerialNumPassWord not like", value, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordIn(List<String> values) {
            addCriterion("sSerialNumPassWord in", values, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordNotIn(List<String> values) {
            addCriterion("sSerialNumPassWord not in", values, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordBetween(String value1, String value2) {
            addCriterion("sSerialNumPassWord between", value1, value2, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andSserialnumpasswordNotBetween(String value1, String value2) {
            addCriterion("sSerialNumPassWord not between", value1, value2, "sserialnumpassword");
            return (Criteria) this;
        }

        public Criteria andCardareaidIsNull() {
            addCriterion("cardareaid is null");
            return (Criteria) this;
        }

        public Criteria andCardareaidIsNotNull() {
            addCriterion("cardareaid is not null");
            return (Criteria) this;
        }

        public Criteria andCardareaidEqualTo(Integer value) {
            addCriterion("cardareaid =", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidNotEqualTo(Integer value) {
            addCriterion("cardareaid <>", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidGreaterThan(Integer value) {
            addCriterion("cardareaid >", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardareaid >=", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidLessThan(Integer value) {
            addCriterion("cardareaid <", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidLessThanOrEqualTo(Integer value) {
            addCriterion("cardareaid <=", value, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidIn(List<Integer> values) {
            addCriterion("cardareaid in", values, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidNotIn(List<Integer> values) {
            addCriterion("cardareaid not in", values, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidBetween(Integer value1, Integer value2) {
            addCriterion("cardareaid between", value1, value2, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardareaidNotBetween(Integer value1, Integer value2) {
            addCriterion("cardareaid not between", value1, value2, "cardareaid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidIsNull() {
            addCriterion("CardPrefectureID is null");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidIsNotNull() {
            addCriterion("CardPrefectureID is not null");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidEqualTo(Integer value) {
            addCriterion("CardPrefectureID =", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidNotEqualTo(Integer value) {
            addCriterion("CardPrefectureID <>", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidGreaterThan(Integer value) {
            addCriterion("CardPrefectureID >", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CardPrefectureID >=", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidLessThan(Integer value) {
            addCriterion("CardPrefectureID <", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidLessThanOrEqualTo(Integer value) {
            addCriterion("CardPrefectureID <=", value, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidIn(List<Integer> values) {
            addCriterion("CardPrefectureID in", values, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidNotIn(List<Integer> values) {
            addCriterion("CardPrefectureID not in", values, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidBetween(Integer value1, Integer value2) {
            addCriterion("CardPrefectureID between", value1, value2, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andCardprefectureidNotBetween(Integer value1, Integer value2) {
            addCriterion("CardPrefectureID not between", value1, value2, "cardprefectureid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidIsNull() {
            addCriterion("ShihuaShopID is null");
            return (Criteria) this;
        }

        public Criteria andShihuashopidIsNotNull() {
            addCriterion("ShihuaShopID is not null");
            return (Criteria) this;
        }

        public Criteria andShihuashopidEqualTo(String value) {
            addCriterion("ShihuaShopID =", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidNotEqualTo(String value) {
            addCriterion("ShihuaShopID <>", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidGreaterThan(String value) {
            addCriterion("ShihuaShopID >", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidGreaterThanOrEqualTo(String value) {
            addCriterion("ShihuaShopID >=", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidLessThan(String value) {
            addCriterion("ShihuaShopID <", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidLessThanOrEqualTo(String value) {
            addCriterion("ShihuaShopID <=", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidLike(String value) {
            addCriterion("ShihuaShopID like", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidNotLike(String value) {
            addCriterion("ShihuaShopID not like", value, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidIn(List<String> values) {
            addCriterion("ShihuaShopID in", values, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidNotIn(List<String> values) {
            addCriterion("ShihuaShopID not in", values, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidBetween(String value1, String value2) {
            addCriterion("ShihuaShopID between", value1, value2, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andShihuashopidNotBetween(String value1, String value2) {
            addCriterion("ShihuaShopID not between", value1, value2, "shihuashopid");
            return (Criteria) this;
        }

        public Criteria andRugroupidIsNull() {
            addCriterion("RuGroupID is null");
            return (Criteria) this;
        }

        public Criteria andRugroupidIsNotNull() {
            addCriterion("RuGroupID is not null");
            return (Criteria) this;
        }

        public Criteria andRugroupidEqualTo(Integer value) {
            addCriterion("RuGroupID =", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidNotEqualTo(Integer value) {
            addCriterion("RuGroupID <>", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidGreaterThan(Integer value) {
            addCriterion("RuGroupID >", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("RuGroupID >=", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidLessThan(Integer value) {
            addCriterion("RuGroupID <", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidLessThanOrEqualTo(Integer value) {
            addCriterion("RuGroupID <=", value, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidIn(List<Integer> values) {
            addCriterion("RuGroupID in", values, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidNotIn(List<Integer> values) {
            addCriterion("RuGroupID not in", values, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidBetween(Integer value1, Integer value2) {
            addCriterion("RuGroupID between", value1, value2, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andRugroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("RuGroupID not between", value1, value2, "rugroupid");
            return (Criteria) this;
        }

        public Criteria andStoptimeIsNull() {
            addCriterion("stoptime is null");
            return (Criteria) this;
        }

        public Criteria andStoptimeIsNotNull() {
            addCriterion("stoptime is not null");
            return (Criteria) this;
        }

        public Criteria andStoptimeEqualTo(Date value) {
            addCriterion("stoptime =", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotEqualTo(Date value) {
            addCriterion("stoptime <>", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeGreaterThan(Date value) {
            addCriterion("stoptime >", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("stoptime >=", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeLessThan(Date value) {
            addCriterion("stoptime <", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeLessThanOrEqualTo(Date value) {
            addCriterion("stoptime <=", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeIn(List<Date> values) {
            addCriterion("stoptime in", values, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotIn(List<Date> values) {
            addCriterion("stoptime not in", values, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeBetween(Date value1, Date value2) {
            addCriterion("stoptime between", value1, value2, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotBetween(Date value1, Date value2) {
            addCriterion("stoptime not between", value1, value2, "stoptime");
            return (Criteria) this;
        }

        public Criteria andCardcityidIsNull() {
            addCriterion("Cardcityid is null");
            return (Criteria) this;
        }

        public Criteria andCardcityidIsNotNull() {
            addCriterion("Cardcityid is not null");
            return (Criteria) this;
        }

        public Criteria andCardcityidEqualTo(Integer value) {
            addCriterion("Cardcityid =", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidNotEqualTo(Integer value) {
            addCriterion("Cardcityid <>", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidGreaterThan(Integer value) {
            addCriterion("Cardcityid >", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("Cardcityid >=", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidLessThan(Integer value) {
            addCriterion("Cardcityid <", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidLessThanOrEqualTo(Integer value) {
            addCriterion("Cardcityid <=", value, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidIn(List<Integer> values) {
            addCriterion("Cardcityid in", values, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidNotIn(List<Integer> values) {
            addCriterion("Cardcityid not in", values, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidBetween(Integer value1, Integer value2) {
            addCriterion("Cardcityid between", value1, value2, "cardcityid");
            return (Criteria) this;
        }

        public Criteria andCardcityidNotBetween(Integer value1, Integer value2) {
            addCriterion("Cardcityid not between", value1, value2, "cardcityid");
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