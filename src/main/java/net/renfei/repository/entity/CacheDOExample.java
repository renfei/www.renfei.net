package net.renfei.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CacheDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CacheDOExample() {
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

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andCacheKeyIsNull() {
            addCriterion("cache_key is null");
            return (Criteria) this;
        }

        public Criteria andCacheKeyIsNotNull() {
            addCriterion("cache_key is not null");
            return (Criteria) this;
        }

        public Criteria andCacheKeyEqualTo(String value) {
            addCriterion("cache_key =", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyNotEqualTo(String value) {
            addCriterion("cache_key <>", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyGreaterThan(String value) {
            addCriterion("cache_key >", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyGreaterThanOrEqualTo(String value) {
            addCriterion("cache_key >=", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyLessThan(String value) {
            addCriterion("cache_key <", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyLessThanOrEqualTo(String value) {
            addCriterion("cache_key <=", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyLike(String value) {
            addCriterion("cache_key like", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyNotLike(String value) {
            addCriterion("cache_key not like", value, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyIn(List<String> values) {
            addCriterion("cache_key in", values, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyNotIn(List<String> values) {
            addCriterion("cache_key not in", values, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyBetween(String value1, String value2) {
            addCriterion("cache_key between", value1, value2, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andCacheKeyNotBetween(String value1, String value2) {
            addCriterion("cache_key not between", value1, value2, "cacheKey");
            return (Criteria) this;
        }

        public Criteria andExpiresIsNull() {
            addCriterion("expires is null");
            return (Criteria) this;
        }

        public Criteria andExpiresIsNotNull() {
            addCriterion("expires is not null");
            return (Criteria) this;
        }

        public Criteria andExpiresEqualTo(Date value) {
            addCriterion("expires =", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresNotEqualTo(Date value) {
            addCriterion("expires <>", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresGreaterThan(Date value) {
            addCriterion("expires >", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresGreaterThanOrEqualTo(Date value) {
            addCriterion("expires >=", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresLessThan(Date value) {
            addCriterion("expires <", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresLessThanOrEqualTo(Date value) {
            addCriterion("expires <=", value, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresIn(List<Date> values) {
            addCriterion("expires in", values, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresNotIn(List<Date> values) {
            addCriterion("expires not in", values, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresBetween(Date value1, Date value2) {
            addCriterion("expires between", value1, value2, "expires");
            return (Criteria) this;
        }

        public Criteria andExpiresNotBetween(Date value1, Date value2) {
            addCriterion("expires not between", value1, value2, "expires");
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