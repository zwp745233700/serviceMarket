package com.serviceMarket.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelperAcceptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HelperAcceptExample() {
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

        public Criteria andHelperIdIsNull() {
            addCriterion("helper_id is null");
            return (Criteria) this;
        }

        public Criteria andHelperIdIsNotNull() {
            addCriterion("helper_id is not null");
            return (Criteria) this;
        }

        public Criteria andHelperIdEqualTo(Integer value) {
            addCriterion("helper_id =", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdNotEqualTo(Integer value) {
            addCriterion("helper_id <>", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdGreaterThan(Integer value) {
            addCriterion("helper_id >", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("helper_id >=", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdLessThan(Integer value) {
            addCriterion("helper_id <", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdLessThanOrEqualTo(Integer value) {
            addCriterion("helper_id <=", value, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdIn(List<Integer> values) {
            addCriterion("helper_id in", values, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdNotIn(List<Integer> values) {
            addCriterion("helper_id not in", values, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdBetween(Integer value1, Integer value2) {
            addCriterion("helper_id between", value1, value2, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperIdNotBetween(Integer value1, Integer value2) {
            addCriterion("helper_id not between", value1, value2, "helperId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdIsNull() {
            addCriterion("helper_table_id is null");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdIsNotNull() {
            addCriterion("helper_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdEqualTo(Integer value) {
            addCriterion("helper_table_id =", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdNotEqualTo(Integer value) {
            addCriterion("helper_table_id <>", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdGreaterThan(Integer value) {
            addCriterion("helper_table_id >", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("helper_table_id >=", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdLessThan(Integer value) {
            addCriterion("helper_table_id <", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("helper_table_id <=", value, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdIn(List<Integer> values) {
            addCriterion("helper_table_id in", values, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdNotIn(List<Integer> values) {
            addCriterion("helper_table_id not in", values, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdBetween(Integer value1, Integer value2) {
            addCriterion("helper_table_id between", value1, value2, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andHelperTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("helper_table_id not between", value1, value2, "helperTableId");
            return (Criteria) this;
        }

        public Criteria andDeadTimeIsNull() {
            addCriterion("dead_time is null");
            return (Criteria) this;
        }

        public Criteria andDeadTimeIsNotNull() {
            addCriterion("dead_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeadTimeEqualTo(Date value) {
            addCriterion("dead_time =", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeNotEqualTo(Date value) {
            addCriterion("dead_time <>", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeGreaterThan(Date value) {
            addCriterion("dead_time >", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dead_time >=", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeLessThan(Date value) {
            addCriterion("dead_time <", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeLessThanOrEqualTo(Date value) {
            addCriterion("dead_time <=", value, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeIn(List<Date> values) {
            addCriterion("dead_time in", values, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeNotIn(List<Date> values) {
            addCriterion("dead_time not in", values, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeBetween(Date value1, Date value2) {
            addCriterion("dead_time between", value1, value2, "deadTime");
            return (Criteria) this;
        }

        public Criteria andDeadTimeNotBetween(Date value1, Date value2) {
            addCriterion("dead_time not between", value1, value2, "deadTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIsNull() {
            addCriterion("accept_time is null");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIsNotNull() {
            addCriterion("accept_time is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeEqualTo(Date value) {
            addCriterion("accept_time =", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotEqualTo(Date value) {
            addCriterion("accept_time <>", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeGreaterThan(Date value) {
            addCriterion("accept_time >", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("accept_time >=", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeLessThan(Date value) {
            addCriterion("accept_time <", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeLessThanOrEqualTo(Date value) {
            addCriterion("accept_time <=", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIn(List<Date> values) {
            addCriterion("accept_time in", values, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotIn(List<Date> values) {
            addCriterion("accept_time not in", values, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeBetween(Date value1, Date value2) {
            addCriterion("accept_time between", value1, value2, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotBetween(Date value1, Date value2) {
            addCriterion("accept_time not between", value1, value2, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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