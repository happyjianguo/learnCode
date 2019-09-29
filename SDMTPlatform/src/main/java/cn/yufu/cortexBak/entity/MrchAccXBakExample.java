package cn.yufu.cortexBak.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MrchAccXBakExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MrchAccXBakExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andMrchnoIsNull() {
            addCriterion("MRCHNO is null");
            return (Criteria) this;
        }

        public Criteria andMrchnoIsNotNull() {
            addCriterion("MRCHNO is not null");
            return (Criteria) this;
        }

        public Criteria andMrchnoEqualTo(String value) {
            addCriterion("MRCHNO =", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotEqualTo(String value) {
            addCriterion("MRCHNO <>", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoGreaterThan(String value) {
            addCriterion("MRCHNO >", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoGreaterThanOrEqualTo(String value) {
            addCriterion("MRCHNO >=", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLessThan(String value) {
            addCriterion("MRCHNO <", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLessThanOrEqualTo(String value) {
            addCriterion("MRCHNO <=", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoLike(String value) {
            addCriterion("MRCHNO like", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotLike(String value) {
            addCriterion("MRCHNO not like", value, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoIn(List<String> values) {
            addCriterion("MRCHNO in", values, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotIn(List<String> values) {
            addCriterion("MRCHNO not in", values, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoBetween(String value1, String value2) {
            addCriterion("MRCHNO between", value1, value2, "mrchno");
            return (Criteria) this;
        }

        public Criteria andMrchnoNotBetween(String value1, String value2) {
            addCriterion("MRCHNO not between", value1, value2, "mrchno");
            return (Criteria) this;
        }

        public Criteria andAccnoIsNull() {
            addCriterion("ACCNO is null");
            return (Criteria) this;
        }

        public Criteria andAccnoIsNotNull() {
            addCriterion("ACCNO is not null");
            return (Criteria) this;
        }

        public Criteria andAccnoEqualTo(String value) {
            addCriterion("ACCNO =", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotEqualTo(String value) {
            addCriterion("ACCNO <>", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoGreaterThan(String value) {
            addCriterion("ACCNO >", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCNO >=", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLessThan(String value) {
            addCriterion("ACCNO <", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLessThanOrEqualTo(String value) {
            addCriterion("ACCNO <=", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoLike(String value) {
            addCriterion("ACCNO like", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotLike(String value) {
            addCriterion("ACCNO not like", value, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoIn(List<String> values) {
            addCriterion("ACCNO in", values, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotIn(List<String> values) {
            addCriterion("ACCNO not in", values, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoBetween(String value1, String value2) {
            addCriterion("ACCNO between", value1, value2, "accno");
            return (Criteria) this;
        }

        public Criteria andAccnoNotBetween(String value1, String value2) {
            addCriterion("ACCNO not between", value1, value2, "accno");
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

        public Criteria andBankNoIsNull() {
            addCriterion("BANK_NO is null");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNotNull() {
            addCriterion("BANK_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBankNoEqualTo(String value) {
            addCriterion("BANK_NO =", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotEqualTo(String value) {
            addCriterion("BANK_NO <>", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThan(String value) {
            addCriterion("BANK_NO >", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NO >=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThan(String value) {
            addCriterion("BANK_NO <", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThanOrEqualTo(String value) {
            addCriterion("BANK_NO <=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLike(String value) {
            addCriterion("BANK_NO like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotLike(String value) {
            addCriterion("BANK_NO not like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoIn(List<String> values) {
            addCriterion("BANK_NO in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotIn(List<String> values) {
            addCriterion("BANK_NO not in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoBetween(String value1, String value2) {
            addCriterion("BANK_NO between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotBetween(String value1, String value2) {
            addCriterion("BANK_NO not between", value1, value2, "bankNo");
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

        public Criteria andAccAddDateIsNull() {
            addCriterion("ACC_ADD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAccAddDateIsNotNull() {
            addCriterion("ACC_ADD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAccAddDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE =", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE <>", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE >", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE >=", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateLessThan(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE <", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACC_ADD_DATE <=", value, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACC_ADD_DATE in", values, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACC_ADD_DATE not in", values, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACC_ADD_DATE between", value1, value2, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccAddDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACC_ADD_DATE not between", value1, value2, "accAddDate");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIsNull() {
            addCriterion("ACC_NICK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIsNotNull() {
            addCriterion("ACC_NICK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccNickNameEqualTo(String value) {
            addCriterion("ACC_NICK_NAME =", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotEqualTo(String value) {
            addCriterion("ACC_NICK_NAME <>", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameGreaterThan(String value) {
            addCriterion("ACC_NICK_NAME >", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NICK_NAME >=", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLessThan(String value) {
            addCriterion("ACC_NICK_NAME <", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_NICK_NAME <=", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameLike(String value) {
            addCriterion("ACC_NICK_NAME like", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotLike(String value) {
            addCriterion("ACC_NICK_NAME not like", value, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameIn(List<String> values) {
            addCriterion("ACC_NICK_NAME in", values, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotIn(List<String> values) {
            addCriterion("ACC_NICK_NAME not in", values, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameBetween(String value1, String value2) {
            addCriterion("ACC_NICK_NAME between", value1, value2, "accNickName");
            return (Criteria) this;
        }

        public Criteria andAccNickNameNotBetween(String value1, String value2) {
            addCriterion("ACC_NICK_NAME not between", value1, value2, "accNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIsNull() {
            addCriterion("SHORT_NICK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIsNotNull() {
            addCriterion("SHORT_NICK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShortNickNameEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME =", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME <>", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameGreaterThan(String value) {
            addCriterion("SHORT_NICK_NAME >", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME >=", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLessThan(String value) {
            addCriterion("SHORT_NICK_NAME <", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLessThanOrEqualTo(String value) {
            addCriterion("SHORT_NICK_NAME <=", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameLike(String value) {
            addCriterion("SHORT_NICK_NAME like", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotLike(String value) {
            addCriterion("SHORT_NICK_NAME not like", value, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameIn(List<String> values) {
            addCriterion("SHORT_NICK_NAME in", values, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotIn(List<String> values) {
            addCriterion("SHORT_NICK_NAME not in", values, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameBetween(String value1, String value2) {
            addCriterion("SHORT_NICK_NAME between", value1, value2, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andShortNickNameNotBetween(String value1, String value2) {
            addCriterion("SHORT_NICK_NAME not between", value1, value2, "shortNickName");
            return (Criteria) this;
        }

        public Criteria andIndividualIsNull() {
            addCriterion("INDIVIDUAL is null");
            return (Criteria) this;
        }

        public Criteria andIndividualIsNotNull() {
            addCriterion("INDIVIDUAL is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualEqualTo(String value) {
            addCriterion("INDIVIDUAL =", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotEqualTo(String value) {
            addCriterion("INDIVIDUAL <>", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualGreaterThan(String value) {
            addCriterion("INDIVIDUAL >", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualGreaterThanOrEqualTo(String value) {
            addCriterion("INDIVIDUAL >=", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLessThan(String value) {
            addCriterion("INDIVIDUAL <", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLessThanOrEqualTo(String value) {
            addCriterion("INDIVIDUAL <=", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualLike(String value) {
            addCriterion("INDIVIDUAL like", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotLike(String value) {
            addCriterion("INDIVIDUAL not like", value, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualIn(List<String> values) {
            addCriterion("INDIVIDUAL in", values, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotIn(List<String> values) {
            addCriterion("INDIVIDUAL not in", values, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualBetween(String value1, String value2) {
            addCriterion("INDIVIDUAL between", value1, value2, "individual");
            return (Criteria) this;
        }

        public Criteria andIndividualNotBetween(String value1, String value2) {
            addCriterion("INDIVIDUAL not between", value1, value2, "individual");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIsNull() {
            addCriterion("LAST_SETTLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIsNotNull() {
            addCriterion("LAST_SETTLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE =", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <>", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE >", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE >=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThan(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE <=", value, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE not in", values, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andLastSettleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SETTLE_DATE not between", value1, value2, "lastSettleDate");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("MERCHANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("MERCHANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("MERCHANT_ID =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("MERCHANT_ID <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("MERCHANT_ID >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("MERCHANT_ID <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_ID <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("MERCHANT_ID in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("MERCHANT_ID not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_ID not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("ACCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("ACCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_ID =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_ID <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(BigDecimal value) {
            addCriterion("ACCOUNT_ID >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_ID >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(BigDecimal value) {
            addCriterion("ACCOUNT_ID <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_ID <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_ID in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_ID not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_ID between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_ID not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccIntrodIsNull() {
            addCriterion("ACC_INTROD is null");
            return (Criteria) this;
        }

        public Criteria andAccIntrodIsNotNull() {
            addCriterion("ACC_INTROD is not null");
            return (Criteria) this;
        }

        public Criteria andAccIntrodEqualTo(String value) {
            addCriterion("ACC_INTROD =", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodNotEqualTo(String value) {
            addCriterion("ACC_INTROD <>", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodGreaterThan(String value) {
            addCriterion("ACC_INTROD >", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_INTROD >=", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodLessThan(String value) {
            addCriterion("ACC_INTROD <", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodLessThanOrEqualTo(String value) {
            addCriterion("ACC_INTROD <=", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodLike(String value) {
            addCriterion("ACC_INTROD like", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodNotLike(String value) {
            addCriterion("ACC_INTROD not like", value, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodIn(List<String> values) {
            addCriterion("ACC_INTROD in", values, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodNotIn(List<String> values) {
            addCriterion("ACC_INTROD not in", values, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodBetween(String value1, String value2) {
            addCriterion("ACC_INTROD between", value1, value2, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andAccIntrodNotBetween(String value1, String value2) {
            addCriterion("ACC_INTROD not between", value1, value2, "accIntrod");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIsNull() {
            addCriterion("IS_BJ_ACCT is null");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIsNotNull() {
            addCriterion("IS_BJ_ACCT is not null");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctEqualTo(String value) {
            addCriterion("IS_BJ_ACCT =", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotEqualTo(String value) {
            addCriterion("IS_BJ_ACCT <>", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctGreaterThan(String value) {
            addCriterion("IS_BJ_ACCT >", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BJ_ACCT >=", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLessThan(String value) {
            addCriterion("IS_BJ_ACCT <", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLessThanOrEqualTo(String value) {
            addCriterion("IS_BJ_ACCT <=", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctLike(String value) {
            addCriterion("IS_BJ_ACCT like", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotLike(String value) {
            addCriterion("IS_BJ_ACCT not like", value, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctIn(List<String> values) {
            addCriterion("IS_BJ_ACCT in", values, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotIn(List<String> values) {
            addCriterion("IS_BJ_ACCT not in", values, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctBetween(String value1, String value2) {
            addCriterion("IS_BJ_ACCT between", value1, value2, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andIsBjAcctNotBetween(String value1, String value2) {
            addCriterion("IS_BJ_ACCT not between", value1, value2, "isBjAcct");
            return (Criteria) this;
        }

        public Criteria andBisIsNull() {
            addCriterion("BIS is null");
            return (Criteria) this;
        }

        public Criteria andBisIsNotNull() {
            addCriterion("BIS is not null");
            return (Criteria) this;
        }

        public Criteria andBisEqualTo(String value) {
            addCriterion("BIS =", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotEqualTo(String value) {
            addCriterion("BIS <>", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisGreaterThan(String value) {
            addCriterion("BIS >", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisGreaterThanOrEqualTo(String value) {
            addCriterion("BIS >=", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLessThan(String value) {
            addCriterion("BIS <", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLessThanOrEqualTo(String value) {
            addCriterion("BIS <=", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisLike(String value) {
            addCriterion("BIS like", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotLike(String value) {
            addCriterion("BIS not like", value, "bis");
            return (Criteria) this;
        }

        public Criteria andBisIn(List<String> values) {
            addCriterion("BIS in", values, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotIn(List<String> values) {
            addCriterion("BIS not in", values, "bis");
            return (Criteria) this;
        }

        public Criteria andBisBetween(String value1, String value2) {
            addCriterion("BIS between", value1, value2, "bis");
            return (Criteria) this;
        }

        public Criteria andBisNotBetween(String value1, String value2) {
            addCriterion("BIS not between", value1, value2, "bis");
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