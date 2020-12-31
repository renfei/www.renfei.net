package net.renfei.discuz.repository.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzForumPostDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzForumPostDOExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Integer value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Integer value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Integer value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Integer value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Integer value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Integer> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Integer> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Integer value1, Integer value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Integer value1, Integer value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Integer value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Integer value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Integer value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Integer value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Integer value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Integer> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Integer> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Integer value1, Integer value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andFidEqualTo(Integer value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Integer value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Integer value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Integer value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Integer value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Integer> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Integer> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Integer value1, Integer value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Integer value1, Integer value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFirstIsNull() {
            addCriterion("first is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsNotNull() {
            addCriterion("first is not null");
            return (Criteria) this;
        }

        public Criteria andFirstEqualTo(Boolean value) {
            addCriterion("first =", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotEqualTo(Boolean value) {
            addCriterion("first <>", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThan(Boolean value) {
            addCriterion("first >", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThanOrEqualTo(Boolean value) {
            addCriterion("first >=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThan(Boolean value) {
            addCriterion("first <", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThanOrEqualTo(Boolean value) {
            addCriterion("first <=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstIn(List<Boolean> values) {
            addCriterion("first in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotIn(List<Boolean> values) {
            addCriterion("first not in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstBetween(Boolean value1, Boolean value2) {
            addCriterion("first between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotBetween(Boolean value1, Boolean value2) {
            addCriterion("first not between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthoridIsNull() {
            addCriterion("authorid is null");
            return (Criteria) this;
        }

        public Criteria andAuthoridIsNotNull() {
            addCriterion("authorid is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoridEqualTo(Integer value) {
            addCriterion("authorid =", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotEqualTo(Integer value) {
            addCriterion("authorid <>", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridGreaterThan(Integer value) {
            addCriterion("authorid >", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridGreaterThanOrEqualTo(Integer value) {
            addCriterion("authorid >=", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridLessThan(Integer value) {
            addCriterion("authorid <", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridLessThanOrEqualTo(Integer value) {
            addCriterion("authorid <=", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridIn(List<Integer> values) {
            addCriterion("authorid in", values, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotIn(List<Integer> values) {
            addCriterion("authorid not in", values, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridBetween(Integer value1, Integer value2) {
            addCriterion("authorid between", value1, value2, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotBetween(Integer value1, Integer value2) {
            addCriterion("authorid not between", value1, value2, "authorid");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andDatelineIsNull() {
            addCriterion("dateline is null");
            return (Criteria) this;
        }

        public Criteria andDatelineIsNotNull() {
            addCriterion("dateline is not null");
            return (Criteria) this;
        }

        public Criteria andDatelineEqualTo(Integer value) {
            addCriterion("dateline =", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotEqualTo(Integer value) {
            addCriterion("dateline <>", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineGreaterThan(Integer value) {
            addCriterion("dateline >", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineGreaterThanOrEqualTo(Integer value) {
            addCriterion("dateline >=", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineLessThan(Integer value) {
            addCriterion("dateline <", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineLessThanOrEqualTo(Integer value) {
            addCriterion("dateline <=", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineIn(List<Integer> values) {
            addCriterion("dateline in", values, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotIn(List<Integer> values) {
            addCriterion("dateline not in", values, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineBetween(Integer value1, Integer value2) {
            addCriterion("dateline between", value1, value2, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotBetween(Integer value1, Integer value2) {
            addCriterion("dateline not between", value1, value2, "dateline");
            return (Criteria) this;
        }

        public Criteria andUseipIsNull() {
            addCriterion("useip is null");
            return (Criteria) this;
        }

        public Criteria andUseipIsNotNull() {
            addCriterion("useip is not null");
            return (Criteria) this;
        }

        public Criteria andUseipEqualTo(String value) {
            addCriterion("useip =", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotEqualTo(String value) {
            addCriterion("useip <>", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipGreaterThan(String value) {
            addCriterion("useip >", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipGreaterThanOrEqualTo(String value) {
            addCriterion("useip >=", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLessThan(String value) {
            addCriterion("useip <", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLessThanOrEqualTo(String value) {
            addCriterion("useip <=", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLike(String value) {
            addCriterion("useip like", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotLike(String value) {
            addCriterion("useip not like", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipIn(List<String> values) {
            addCriterion("useip in", values, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotIn(List<String> values) {
            addCriterion("useip not in", values, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipBetween(String value1, String value2) {
            addCriterion("useip between", value1, value2, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotBetween(String value1, String value2) {
            addCriterion("useip not between", value1, value2, "useip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Short value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Short value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Short value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Short value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Short value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Short value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Short> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Short> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Short value1, Short value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Short value1, Short value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andInvisibleIsNull() {
            addCriterion("invisible is null");
            return (Criteria) this;
        }

        public Criteria andInvisibleIsNotNull() {
            addCriterion("invisible is not null");
            return (Criteria) this;
        }

        public Criteria andInvisibleEqualTo(Boolean value) {
            addCriterion("invisible =", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotEqualTo(Boolean value) {
            addCriterion("invisible <>", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleGreaterThan(Boolean value) {
            addCriterion("invisible >", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("invisible >=", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleLessThan(Boolean value) {
            addCriterion("invisible <", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleLessThanOrEqualTo(Boolean value) {
            addCriterion("invisible <=", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleIn(List<Boolean> values) {
            addCriterion("invisible in", values, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotIn(List<Boolean> values) {
            addCriterion("invisible not in", values, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleBetween(Boolean value1, Boolean value2) {
            addCriterion("invisible between", value1, value2, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("invisible not between", value1, value2, "invisible");
            return (Criteria) this;
        }

        public Criteria andAnonymousIsNull() {
            addCriterion("anonymous is null");
            return (Criteria) this;
        }

        public Criteria andAnonymousIsNotNull() {
            addCriterion("anonymous is not null");
            return (Criteria) this;
        }

        public Criteria andAnonymousEqualTo(Boolean value) {
            addCriterion("anonymous =", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotEqualTo(Boolean value) {
            addCriterion("anonymous <>", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThan(Boolean value) {
            addCriterion("anonymous >", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThanOrEqualTo(Boolean value) {
            addCriterion("anonymous >=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThan(Boolean value) {
            addCriterion("anonymous <", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThanOrEqualTo(Boolean value) {
            addCriterion("anonymous <=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousIn(List<Boolean> values) {
            addCriterion("anonymous in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotIn(List<Boolean> values) {
            addCriterion("anonymous not in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousBetween(Boolean value1, Boolean value2) {
            addCriterion("anonymous between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotBetween(Boolean value1, Boolean value2) {
            addCriterion("anonymous not between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andUsesigIsNull() {
            addCriterion("usesig is null");
            return (Criteria) this;
        }

        public Criteria andUsesigIsNotNull() {
            addCriterion("usesig is not null");
            return (Criteria) this;
        }

        public Criteria andUsesigEqualTo(Boolean value) {
            addCriterion("usesig =", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotEqualTo(Boolean value) {
            addCriterion("usesig <>", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigGreaterThan(Boolean value) {
            addCriterion("usesig >", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigGreaterThanOrEqualTo(Boolean value) {
            addCriterion("usesig >=", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigLessThan(Boolean value) {
            addCriterion("usesig <", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigLessThanOrEqualTo(Boolean value) {
            addCriterion("usesig <=", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigIn(List<Boolean> values) {
            addCriterion("usesig in", values, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotIn(List<Boolean> values) {
            addCriterion("usesig not in", values, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigBetween(Boolean value1, Boolean value2) {
            addCriterion("usesig between", value1, value2, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotBetween(Boolean value1, Boolean value2) {
            addCriterion("usesig not between", value1, value2, "usesig");
            return (Criteria) this;
        }

        public Criteria andHtmlonIsNull() {
            addCriterion("htmlon is null");
            return (Criteria) this;
        }

        public Criteria andHtmlonIsNotNull() {
            addCriterion("htmlon is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlonEqualTo(Boolean value) {
            addCriterion("htmlon =", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotEqualTo(Boolean value) {
            addCriterion("htmlon <>", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonGreaterThan(Boolean value) {
            addCriterion("htmlon >", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonGreaterThanOrEqualTo(Boolean value) {
            addCriterion("htmlon >=", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonLessThan(Boolean value) {
            addCriterion("htmlon <", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonLessThanOrEqualTo(Boolean value) {
            addCriterion("htmlon <=", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonIn(List<Boolean> values) {
            addCriterion("htmlon in", values, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotIn(List<Boolean> values) {
            addCriterion("htmlon not in", values, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonBetween(Boolean value1, Boolean value2) {
            addCriterion("htmlon between", value1, value2, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotBetween(Boolean value1, Boolean value2) {
            addCriterion("htmlon not between", value1, value2, "htmlon");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIsNull() {
            addCriterion("bbcodeoff is null");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIsNotNull() {
            addCriterion("bbcodeoff is not null");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffEqualTo(Boolean value) {
            addCriterion("bbcodeoff =", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotEqualTo(Boolean value) {
            addCriterion("bbcodeoff <>", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffGreaterThan(Boolean value) {
            addCriterion("bbcodeoff >", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bbcodeoff >=", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffLessThan(Boolean value) {
            addCriterion("bbcodeoff <", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffLessThanOrEqualTo(Boolean value) {
            addCriterion("bbcodeoff <=", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIn(List<Boolean> values) {
            addCriterion("bbcodeoff in", values, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotIn(List<Boolean> values) {
            addCriterion("bbcodeoff not in", values, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffBetween(Boolean value1, Boolean value2) {
            addCriterion("bbcodeoff between", value1, value2, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bbcodeoff not between", value1, value2, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIsNull() {
            addCriterion("smileyoff is null");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIsNotNull() {
            addCriterion("smileyoff is not null");
            return (Criteria) this;
        }

        public Criteria andSmileyoffEqualTo(Boolean value) {
            addCriterion("smileyoff =", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotEqualTo(Boolean value) {
            addCriterion("smileyoff <>", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffGreaterThan(Boolean value) {
            addCriterion("smileyoff >", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffGreaterThanOrEqualTo(Boolean value) {
            addCriterion("smileyoff >=", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffLessThan(Boolean value) {
            addCriterion("smileyoff <", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffLessThanOrEqualTo(Boolean value) {
            addCriterion("smileyoff <=", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIn(List<Boolean> values) {
            addCriterion("smileyoff in", values, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotIn(List<Boolean> values) {
            addCriterion("smileyoff not in", values, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffBetween(Boolean value1, Boolean value2) {
            addCriterion("smileyoff between", value1, value2, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotBetween(Boolean value1, Boolean value2) {
            addCriterion("smileyoff not between", value1, value2, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andParseurloffIsNull() {
            addCriterion("parseurloff is null");
            return (Criteria) this;
        }

        public Criteria andParseurloffIsNotNull() {
            addCriterion("parseurloff is not null");
            return (Criteria) this;
        }

        public Criteria andParseurloffEqualTo(Boolean value) {
            addCriterion("parseurloff =", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotEqualTo(Boolean value) {
            addCriterion("parseurloff <>", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffGreaterThan(Boolean value) {
            addCriterion("parseurloff >", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffGreaterThanOrEqualTo(Boolean value) {
            addCriterion("parseurloff >=", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffLessThan(Boolean value) {
            addCriterion("parseurloff <", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffLessThanOrEqualTo(Boolean value) {
            addCriterion("parseurloff <=", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffIn(List<Boolean> values) {
            addCriterion("parseurloff in", values, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotIn(List<Boolean> values) {
            addCriterion("parseurloff not in", values, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffBetween(Boolean value1, Boolean value2) {
            addCriterion("parseurloff between", value1, value2, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotBetween(Boolean value1, Boolean value2) {
            addCriterion("parseurloff not between", value1, value2, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(Boolean value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(Boolean value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(Boolean value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(Boolean value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(Boolean value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<Boolean> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<Boolean> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(Boolean value1, Boolean value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Short value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Short value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Short value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Short value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Short value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Short value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Short> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Short> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Short value1, Short value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Short value1, Short value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRatetimesIsNull() {
            addCriterion("ratetimes is null");
            return (Criteria) this;
        }

        public Criteria andRatetimesIsNotNull() {
            addCriterion("ratetimes is not null");
            return (Criteria) this;
        }

        public Criteria andRatetimesEqualTo(Byte value) {
            addCriterion("ratetimes =", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotEqualTo(Byte value) {
            addCriterion("ratetimes <>", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesGreaterThan(Byte value) {
            addCriterion("ratetimes >", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("ratetimes >=", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesLessThan(Byte value) {
            addCriterion("ratetimes <", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesLessThanOrEqualTo(Byte value) {
            addCriterion("ratetimes <=", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesIn(List<Byte> values) {
            addCriterion("ratetimes in", values, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotIn(List<Byte> values) {
            addCriterion("ratetimes not in", values, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesBetween(Byte value1, Byte value2) {
            addCriterion("ratetimes between", value1, value2, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotBetween(Byte value1, Byte value2) {
            addCriterion("ratetimes not between", value1, value2, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(Boolean value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(Boolean value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(Boolean value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(Boolean value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(Boolean value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<Boolean> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<Boolean> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(Boolean value1, Boolean value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andReplycreditIsNull() {
            addCriterion("replycredit is null");
            return (Criteria) this;
        }

        public Criteria andReplycreditIsNotNull() {
            addCriterion("replycredit is not null");
            return (Criteria) this;
        }

        public Criteria andReplycreditEqualTo(Integer value) {
            addCriterion("replycredit =", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotEqualTo(Integer value) {
            addCriterion("replycredit <>", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditGreaterThan(Integer value) {
            addCriterion("replycredit >", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("replycredit >=", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditLessThan(Integer value) {
            addCriterion("replycredit <", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditLessThanOrEqualTo(Integer value) {
            addCriterion("replycredit <=", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditIn(List<Integer> values) {
            addCriterion("replycredit in", values, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotIn(List<Integer> values) {
            addCriterion("replycredit not in", values, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditBetween(Integer value1, Integer value2) {
            addCriterion("replycredit between", value1, value2, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotBetween(Integer value1, Integer value2) {
            addCriterion("replycredit not between", value1, value2, "replycredit");
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