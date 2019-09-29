package cn.yufu.yufuOld1.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RUExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RUExample() {
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

        public Criteria andLruidIsNull() {
            addCriterion("lRUID is null");
            return (Criteria) this;
        }

        public Criteria andLruidIsNotNull() {
            addCriterion("lRUID is not null");
            return (Criteria) this;
        }

        public Criteria andLruidEqualTo(String value) {
            addCriterion("lRUID =", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotEqualTo(String value) {
            addCriterion("lRUID <>", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidGreaterThan(String value) {
            addCriterion("lRUID >", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidGreaterThanOrEqualTo(String value) {
            addCriterion("lRUID >=", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLessThan(String value) {
            addCriterion("lRUID <", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLessThanOrEqualTo(String value) {
            addCriterion("lRUID <=", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidLike(String value) {
            addCriterion("lRUID like", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotLike(String value) {
            addCriterion("lRUID not like", value, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidIn(List<String> values) {
            addCriterion("lRUID in", values, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotIn(List<String> values) {
            addCriterion("lRUID not in", values, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidBetween(String value1, String value2) {
            addCriterion("lRUID between", value1, value2, "lruid");
            return (Criteria) this;
        }

        public Criteria andLruidNotBetween(String value1, String value2) {
            addCriterion("lRUID not between", value1, value2, "lruid");
            return (Criteria) this;
        }

        public Criteria andSruIsNull() {
            addCriterion("sRU is null");
            return (Criteria) this;
        }

        public Criteria andSruIsNotNull() {
            addCriterion("sRU is not null");
            return (Criteria) this;
        }

        public Criteria andSruEqualTo(String value) {
            addCriterion("sRU =", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruNotEqualTo(String value) {
            addCriterion("sRU <>", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruGreaterThan(String value) {
            addCriterion("sRU >", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruGreaterThanOrEqualTo(String value) {
            addCriterion("sRU >=", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruLessThan(String value) {
            addCriterion("sRU <", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruLessThanOrEqualTo(String value) {
            addCriterion("sRU <=", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruLike(String value) {
            addCriterion("sRU like", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruNotLike(String value) {
            addCriterion("sRU not like", value, "sru");
            return (Criteria) this;
        }

        public Criteria andSruIn(List<String> values) {
            addCriterion("sRU in", values, "sru");
            return (Criteria) this;
        }

        public Criteria andSruNotIn(List<String> values) {
            addCriterion("sRU not in", values, "sru");
            return (Criteria) this;
        }

        public Criteria andSruBetween(String value1, String value2) {
            addCriterion("sRU between", value1, value2, "sru");
            return (Criteria) this;
        }

        public Criteria andSruNotBetween(String value1, String value2) {
            addCriterion("sRU not between", value1, value2, "sru");
            return (Criteria) this;
        }

        public Criteria andScontactIsNull() {
            addCriterion("sContact is null");
            return (Criteria) this;
        }

        public Criteria andScontactIsNotNull() {
            addCriterion("sContact is not null");
            return (Criteria) this;
        }

        public Criteria andScontactEqualTo(String value) {
            addCriterion("sContact =", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactNotEqualTo(String value) {
            addCriterion("sContact <>", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactGreaterThan(String value) {
            addCriterion("sContact >", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactGreaterThanOrEqualTo(String value) {
            addCriterion("sContact >=", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactLessThan(String value) {
            addCriterion("sContact <", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactLessThanOrEqualTo(String value) {
            addCriterion("sContact <=", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactLike(String value) {
            addCriterion("sContact like", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactNotLike(String value) {
            addCriterion("sContact not like", value, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactIn(List<String> values) {
            addCriterion("sContact in", values, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactNotIn(List<String> values) {
            addCriterion("sContact not in", values, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactBetween(String value1, String value2) {
            addCriterion("sContact between", value1, value2, "scontact");
            return (Criteria) this;
        }

        public Criteria andScontactNotBetween(String value1, String value2) {
            addCriterion("sContact not between", value1, value2, "scontact");
            return (Criteria) this;
        }

        public Criteria andSphoneIsNull() {
            addCriterion("sPhone is null");
            return (Criteria) this;
        }

        public Criteria andSphoneIsNotNull() {
            addCriterion("sPhone is not null");
            return (Criteria) this;
        }

        public Criteria andSphoneEqualTo(String value) {
            addCriterion("sPhone =", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneNotEqualTo(String value) {
            addCriterion("sPhone <>", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneGreaterThan(String value) {
            addCriterion("sPhone >", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneGreaterThanOrEqualTo(String value) {
            addCriterion("sPhone >=", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneLessThan(String value) {
            addCriterion("sPhone <", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneLessThanOrEqualTo(String value) {
            addCriterion("sPhone <=", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneLike(String value) {
            addCriterion("sPhone like", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneNotLike(String value) {
            addCriterion("sPhone not like", value, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneIn(List<String> values) {
            addCriterion("sPhone in", values, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneNotIn(List<String> values) {
            addCriterion("sPhone not in", values, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneBetween(String value1, String value2) {
            addCriterion("sPhone between", value1, value2, "sphone");
            return (Criteria) this;
        }

        public Criteria andSphoneNotBetween(String value1, String value2) {
            addCriterion("sPhone not between", value1, value2, "sphone");
            return (Criteria) this;
        }

        public Criteria andSaddr1IsNull() {
            addCriterion("sAddr1 is null");
            return (Criteria) this;
        }

        public Criteria andSaddr1IsNotNull() {
            addCriterion("sAddr1 is not null");
            return (Criteria) this;
        }

        public Criteria andSaddr1EqualTo(String value) {
            addCriterion("sAddr1 =", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1NotEqualTo(String value) {
            addCriterion("sAddr1 <>", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1GreaterThan(String value) {
            addCriterion("sAddr1 >", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1GreaterThanOrEqualTo(String value) {
            addCriterion("sAddr1 >=", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1LessThan(String value) {
            addCriterion("sAddr1 <", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1LessThanOrEqualTo(String value) {
            addCriterion("sAddr1 <=", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1Like(String value) {
            addCriterion("sAddr1 like", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1NotLike(String value) {
            addCriterion("sAddr1 not like", value, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1In(List<String> values) {
            addCriterion("sAddr1 in", values, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1NotIn(List<String> values) {
            addCriterion("sAddr1 not in", values, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1Between(String value1, String value2) {
            addCriterion("sAddr1 between", value1, value2, "saddr1");
            return (Criteria) this;
        }

        public Criteria andSaddr1NotBetween(String value1, String value2) {
            addCriterion("sAddr1 not between", value1, value2, "saddr1");
            return (Criteria) this;
        }

        public Criteria andScodeIsNull() {
            addCriterion("sCode is null");
            return (Criteria) this;
        }

        public Criteria andScodeIsNotNull() {
            addCriterion("sCode is not null");
            return (Criteria) this;
        }

        public Criteria andScodeEqualTo(String value) {
            addCriterion("sCode =", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeNotEqualTo(String value) {
            addCriterion("sCode <>", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeGreaterThan(String value) {
            addCriterion("sCode >", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeGreaterThanOrEqualTo(String value) {
            addCriterion("sCode >=", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeLessThan(String value) {
            addCriterion("sCode <", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeLessThanOrEqualTo(String value) {
            addCriterion("sCode <=", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeLike(String value) {
            addCriterion("sCode like", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeNotLike(String value) {
            addCriterion("sCode not like", value, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeIn(List<String> values) {
            addCriterion("sCode in", values, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeNotIn(List<String> values) {
            addCriterion("sCode not in", values, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeBetween(String value1, String value2) {
            addCriterion("sCode between", value1, value2, "scode");
            return (Criteria) this;
        }

        public Criteria andScodeNotBetween(String value1, String value2) {
            addCriterion("sCode not between", value1, value2, "scode");
            return (Criteria) this;
        }

        public Criteria andDtimeIsNull() {
            addCriterion("dtime is null");
            return (Criteria) this;
        }

        public Criteria andDtimeIsNotNull() {
            addCriterion("dtime is not null");
            return (Criteria) this;
        }

        public Criteria andDtimeEqualTo(Date value) {
            addCriterion("dtime =", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotEqualTo(Date value) {
            addCriterion("dtime <>", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeGreaterThan(Date value) {
            addCriterion("dtime >", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dtime >=", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeLessThan(Date value) {
            addCriterion("dtime <", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeLessThanOrEqualTo(Date value) {
            addCriterion("dtime <=", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeIn(List<Date> values) {
            addCriterion("dtime in", values, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotIn(List<Date> values) {
            addCriterion("dtime not in", values, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeBetween(Date value1, Date value2) {
            addCriterion("dtime between", value1, value2, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotBetween(Date value1, Date value2) {
            addCriterion("dtime not between", value1, value2, "dtime");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andStaIsNull() {
            addCriterion("sta is null");
            return (Criteria) this;
        }

        public Criteria andStaIsNotNull() {
            addCriterion("sta is not null");
            return (Criteria) this;
        }

        public Criteria andStaEqualTo(String value) {
            addCriterion("sta =", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotEqualTo(String value) {
            addCriterion("sta <>", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaGreaterThan(String value) {
            addCriterion("sta >", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaGreaterThanOrEqualTo(String value) {
            addCriterion("sta >=", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLessThan(String value) {
            addCriterion("sta <", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLessThanOrEqualTo(String value) {
            addCriterion("sta <=", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLike(String value) {
            addCriterion("sta like", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotLike(String value) {
            addCriterion("sta not like", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaIn(List<String> values) {
            addCriterion("sta in", values, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotIn(List<String> values) {
            addCriterion("sta not in", values, "sta");
            return (Criteria) this;
        }

        public Criteria andStaBetween(String value1, String value2) {
            addCriterion("sta between", value1, value2, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotBetween(String value1, String value2) {
            addCriterion("sta not between", value1, value2, "sta");
            return (Criteria) this;
        }

        public Criteria andTrafficIsNull() {
            addCriterion("traffic is null");
            return (Criteria) this;
        }

        public Criteria andTrafficIsNotNull() {
            addCriterion("traffic is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficEqualTo(String value) {
            addCriterion("traffic =", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotEqualTo(String value) {
            addCriterion("traffic <>", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficGreaterThan(String value) {
            addCriterion("traffic >", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficGreaterThanOrEqualTo(String value) {
            addCriterion("traffic >=", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLessThan(String value) {
            addCriterion("traffic <", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLessThanOrEqualTo(String value) {
            addCriterion("traffic <=", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLike(String value) {
            addCriterion("traffic like", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotLike(String value) {
            addCriterion("traffic not like", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficIn(List<String> values) {
            addCriterion("traffic in", values, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotIn(List<String> values) {
            addCriterion("traffic not in", values, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficBetween(String value1, String value2) {
            addCriterion("traffic between", value1, value2, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotBetween(String value1, String value2) {
            addCriterion("traffic not between", value1, value2, "traffic");
            return (Criteria) this;
        }

        public Criteria andGuidepostIsNull() {
            addCriterion("guidepost is null");
            return (Criteria) this;
        }

        public Criteria andGuidepostIsNotNull() {
            addCriterion("guidepost is not null");
            return (Criteria) this;
        }

        public Criteria andGuidepostEqualTo(String value) {
            addCriterion("guidepost =", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostNotEqualTo(String value) {
            addCriterion("guidepost <>", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostGreaterThan(String value) {
            addCriterion("guidepost >", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostGreaterThanOrEqualTo(String value) {
            addCriterion("guidepost >=", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostLessThan(String value) {
            addCriterion("guidepost <", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostLessThanOrEqualTo(String value) {
            addCriterion("guidepost <=", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostLike(String value) {
            addCriterion("guidepost like", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostNotLike(String value) {
            addCriterion("guidepost not like", value, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostIn(List<String> values) {
            addCriterion("guidepost in", values, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostNotIn(List<String> values) {
            addCriterion("guidepost not in", values, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostBetween(String value1, String value2) {
            addCriterion("guidepost between", value1, value2, "guidepost");
            return (Criteria) this;
        }

        public Criteria andGuidepostNotBetween(String value1, String value2) {
            addCriterion("guidepost not between", value1, value2, "guidepost");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("Picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("Picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("Picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("Picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("Picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("Picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("Picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("Picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("Picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("Picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("Picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("Picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("Picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("Picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("cityid is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("cityid is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(Integer value) {
            addCriterion("cityid =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(Integer value) {
            addCriterion("cityid <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(Integer value) {
            addCriterion("cityid >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityid >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(Integer value) {
            addCriterion("cityid <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(Integer value) {
            addCriterion("cityid <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<Integer> values) {
            addCriterion("cityid in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<Integer> values) {
            addCriterion("cityid not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(Integer value1, Integer value2) {
            addCriterion("cityid between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("cityid not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andRuflagIsNull() {
            addCriterion("ruflag is null");
            return (Criteria) this;
        }

        public Criteria andRuflagIsNotNull() {
            addCriterion("ruflag is not null");
            return (Criteria) this;
        }

        public Criteria andRuflagEqualTo(Integer value) {
            addCriterion("ruflag =", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagNotEqualTo(Integer value) {
            addCriterion("ruflag <>", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagGreaterThan(Integer value) {
            addCriterion("ruflag >", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("ruflag >=", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagLessThan(Integer value) {
            addCriterion("ruflag <", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagLessThanOrEqualTo(Integer value) {
            addCriterion("ruflag <=", value, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagIn(List<Integer> values) {
            addCriterion("ruflag in", values, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagNotIn(List<Integer> values) {
            addCriterion("ruflag not in", values, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagBetween(Integer value1, Integer value2) {
            addCriterion("ruflag between", value1, value2, "ruflag");
            return (Criteria) this;
        }

        public Criteria andRuflagNotBetween(Integer value1, Integer value2) {
            addCriterion("ruflag not between", value1, value2, "ruflag");
            return (Criteria) this;
        }

        public Criteria andStoptimeIsNull() {
            addCriterion("StopTime is null");
            return (Criteria) this;
        }

        public Criteria andStoptimeIsNotNull() {
            addCriterion("StopTime is not null");
            return (Criteria) this;
        }

        public Criteria andStoptimeEqualTo(Date value) {
            addCriterion("StopTime =", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotEqualTo(Date value) {
            addCriterion("StopTime <>", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeGreaterThan(Date value) {
            addCriterion("StopTime >", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("StopTime >=", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeLessThan(Date value) {
            addCriterion("StopTime <", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeLessThanOrEqualTo(Date value) {
            addCriterion("StopTime <=", value, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeIn(List<Date> values) {
            addCriterion("StopTime in", values, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotIn(List<Date> values) {
            addCriterion("StopTime not in", values, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeBetween(Date value1, Date value2) {
            addCriterion("StopTime between", value1, value2, "stoptime");
            return (Criteria) this;
        }

        public Criteria andStoptimeNotBetween(Date value1, Date value2) {
            addCriterion("StopTime not between", value1, value2, "stoptime");
            return (Criteria) this;
        }

        public Criteria andRuacountIsNull() {
            addCriterion("RuAcount is null");
            return (Criteria) this;
        }

        public Criteria andRuacountIsNotNull() {
            addCriterion("RuAcount is not null");
            return (Criteria) this;
        }

        public Criteria andRuacountEqualTo(String value) {
            addCriterion("RuAcount =", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountNotEqualTo(String value) {
            addCriterion("RuAcount <>", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountGreaterThan(String value) {
            addCriterion("RuAcount >", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountGreaterThanOrEqualTo(String value) {
            addCriterion("RuAcount >=", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountLessThan(String value) {
            addCriterion("RuAcount <", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountLessThanOrEqualTo(String value) {
            addCriterion("RuAcount <=", value, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountIn(List<String> values) {
            addCriterion("RuAcount in", values, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountNotIn(List<String> values) {
            addCriterion("RuAcount not in", values, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountBetween(String value1, String value2) {
            addCriterion("RuAcount between", value1, value2, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRuacountNotBetween(String value1, String value2) {
            addCriterion("RuAcount not between", value1, value2, "ruacount");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonIsNull() {
            addCriterion("Rulegalperson is null");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonIsNotNull() {
            addCriterion("Rulegalperson is not null");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonEqualTo(String value) {
            addCriterion("Rulegalperson =", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonNotEqualTo(String value) {
            addCriterion("Rulegalperson <>", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonGreaterThan(String value) {
            addCriterion("Rulegalperson >", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonGreaterThanOrEqualTo(String value) {
            addCriterion("Rulegalperson >=", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonLessThan(String value) {
            addCriterion("Rulegalperson <", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonLessThanOrEqualTo(String value) {
            addCriterion("Rulegalperson <=", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonLike(String value) {
            addCriterion("Rulegalperson like", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonNotLike(String value) {
            addCriterion("Rulegalperson not like", value, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonIn(List<String> values) {
            addCriterion("Rulegalperson in", values, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonNotIn(List<String> values) {
            addCriterion("Rulegalperson not in", values, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonBetween(String value1, String value2) {
            addCriterion("Rulegalperson between", value1, value2, "rulegalperson");
            return (Criteria) this;
        }

        public Criteria andRulegalpersonNotBetween(String value1, String value2) {
            addCriterion("Rulegalperson not between", value1, value2, "rulegalperson");
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andZshflagIsNull() {
            addCriterion("zshflag is null");
            return (Criteria) this;
        }

        public Criteria andZshflagIsNotNull() {
            addCriterion("zshflag is not null");
            return (Criteria) this;
        }

        public Criteria andZshflagEqualTo(Integer value) {
            addCriterion("zshflag =", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagNotEqualTo(Integer value) {
            addCriterion("zshflag <>", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagGreaterThan(Integer value) {
            addCriterion("zshflag >", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("zshflag >=", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagLessThan(Integer value) {
            addCriterion("zshflag <", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagLessThanOrEqualTo(Integer value) {
            addCriterion("zshflag <=", value, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagIn(List<Integer> values) {
            addCriterion("zshflag in", values, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagNotIn(List<Integer> values) {
            addCriterion("zshflag not in", values, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagBetween(Integer value1, Integer value2) {
            addCriterion("zshflag between", value1, value2, "zshflag");
            return (Criteria) this;
        }

        public Criteria andZshflagNotBetween(Integer value1, Integer value2) {
            addCriterion("zshflag not between", value1, value2, "zshflag");
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

        public Criteria andFidIsNull() {
            addCriterion("fid is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("fid is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(String value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(String value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(String value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(String value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(String value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(String value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLike(String value) {
            addCriterion("fid like", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotLike(String value) {
            addCriterion("fid not like", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<String> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<String> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(String value1, String value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(String value1, String value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andRutypeIsNull() {
            addCriterion("RuType is null");
            return (Criteria) this;
        }

        public Criteria andRutypeIsNotNull() {
            addCriterion("RuType is not null");
            return (Criteria) this;
        }

        public Criteria andRutypeEqualTo(Integer value) {
            addCriterion("RuType =", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeNotEqualTo(Integer value) {
            addCriterion("RuType <>", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeGreaterThan(Integer value) {
            addCriterion("RuType >", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RuType >=", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeLessThan(Integer value) {
            addCriterion("RuType <", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeLessThanOrEqualTo(Integer value) {
            addCriterion("RuType <=", value, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeIn(List<Integer> values) {
            addCriterion("RuType in", values, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeNotIn(List<Integer> values) {
            addCriterion("RuType not in", values, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeBetween(Integer value1, Integer value2) {
            addCriterion("RuType between", value1, value2, "rutype");
            return (Criteria) this;
        }

        public Criteria andRutypeNotBetween(Integer value1, Integer value2) {
            addCriterion("RuType not between", value1, value2, "rutype");
            return (Criteria) this;
        }

        public Criteria andAdminbstateIsNull() {
            addCriterion("Adminbstate is null");
            return (Criteria) this;
        }

        public Criteria andAdminbstateIsNotNull() {
            addCriterion("Adminbstate is not null");
            return (Criteria) this;
        }

        public Criteria andAdminbstateEqualTo(Integer value) {
            addCriterion("Adminbstate =", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateNotEqualTo(Integer value) {
            addCriterion("Adminbstate <>", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateGreaterThan(Integer value) {
            addCriterion("Adminbstate >", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("Adminbstate >=", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateLessThan(Integer value) {
            addCriterion("Adminbstate <", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateLessThanOrEqualTo(Integer value) {
            addCriterion("Adminbstate <=", value, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateIn(List<Integer> values) {
            addCriterion("Adminbstate in", values, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateNotIn(List<Integer> values) {
            addCriterion("Adminbstate not in", values, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateBetween(Integer value1, Integer value2) {
            addCriterion("Adminbstate between", value1, value2, "adminbstate");
            return (Criteria) this;
        }

        public Criteria andAdminbstateNotBetween(Integer value1, Integer value2) {
            addCriterion("Adminbstate not between", value1, value2, "adminbstate");
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