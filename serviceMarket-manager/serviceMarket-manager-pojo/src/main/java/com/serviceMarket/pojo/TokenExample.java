package com.serviceMarket.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TokenExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExpertimeIsNull() {
            addCriterion("experTime is null");
            return (Criteria) this;
        }

        public Criteria andExpertimeIsNotNull() {
            addCriterion("experTime is not null");
            return (Criteria) this;
        }

        public Criteria andExpertimeEqualTo(Date value) {
            addCriterion("experTime =", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeNotEqualTo(Date value) {
            addCriterion("experTime <>", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeGreaterThan(Date value) {
            addCriterion("experTime >", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("experTime >=", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeLessThan(Date value) {
            addCriterion("experTime <", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeLessThanOrEqualTo(Date value) {
            addCriterion("experTime <=", value, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeIn(List<Date> values) {
            addCriterion("experTime in", values, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeNotIn(List<Date> values) {
            addCriterion("experTime not in", values, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeBetween(Date value1, Date value2) {
            addCriterion("experTime between", value1, value2, "expertime");
            return (Criteria) this;
        }

        public Criteria andExpertimeNotBetween(Date value1, Date value2) {
            addCriterion("experTime not between", value1, value2, "expertime");
            return (Criteria) this;
        }

        public Criteria andIsshoperIsNull() {
            addCriterion("isShoper is null");
            return (Criteria) this;
        }

        public Criteria andIsshoperIsNotNull() {
            addCriterion("isShoper is not null");
            return (Criteria) this;
        }

        public Criteria andIsshoperEqualTo(Boolean value) {
            addCriterion("isShoper =", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperNotEqualTo(Boolean value) {
            addCriterion("isShoper <>", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperGreaterThan(Boolean value) {
            addCriterion("isShoper >", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isShoper >=", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperLessThan(Boolean value) {
            addCriterion("isShoper <", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperLessThanOrEqualTo(Boolean value) {
            addCriterion("isShoper <=", value, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperIn(List<Boolean> values) {
            addCriterion("isShoper in", values, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperNotIn(List<Boolean> values) {
            addCriterion("isShoper not in", values, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperBetween(Boolean value1, Boolean value2) {
            addCriterion("isShoper between", value1, value2, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIsshoperNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isShoper not between", value1, value2, "isshoper");
            return (Criteria) this;
        }

        public Criteria andIshelperIsNull() {
            addCriterion("isHelper is null");
            return (Criteria) this;
        }

        public Criteria andIshelperIsNotNull() {
            addCriterion("isHelper is not null");
            return (Criteria) this;
        }

        public Criteria andIshelperEqualTo(Boolean value) {
            addCriterion("isHelper =", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperNotEqualTo(Boolean value) {
            addCriterion("isHelper <>", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperGreaterThan(Boolean value) {
            addCriterion("isHelper >", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isHelper >=", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperLessThan(Boolean value) {
            addCriterion("isHelper <", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperLessThanOrEqualTo(Boolean value) {
            addCriterion("isHelper <=", value, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperIn(List<Boolean> values) {
            addCriterion("isHelper in", values, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperNotIn(List<Boolean> values) {
            addCriterion("isHelper not in", values, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperBetween(Boolean value1, Boolean value2) {
            addCriterion("isHelper between", value1, value2, "ishelper");
            return (Criteria) this;
        }

        public Criteria andIshelperNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isHelper not between", value1, value2, "ishelper");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
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