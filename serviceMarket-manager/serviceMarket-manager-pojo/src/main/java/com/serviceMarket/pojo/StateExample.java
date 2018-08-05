package com.serviceMarket.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StateExample() {
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

        public Criteria andUserAcceptStateIsNull() {
            addCriterion("user_accept_state is null");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateIsNotNull() {
            addCriterion("user_accept_state is not null");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateEqualTo(Integer value) {
            addCriterion("user_accept_state =", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateNotEqualTo(Integer value) {
            addCriterion("user_accept_state <>", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateGreaterThan(Integer value) {
            addCriterion("user_accept_state >", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_accept_state >=", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateLessThan(Integer value) {
            addCriterion("user_accept_state <", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateLessThanOrEqualTo(Integer value) {
            addCriterion("user_accept_state <=", value, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateIn(List<Integer> values) {
            addCriterion("user_accept_state in", values, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateNotIn(List<Integer> values) {
            addCriterion("user_accept_state not in", values, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateBetween(Integer value1, Integer value2) {
            addCriterion("user_accept_state between", value1, value2, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andUserAcceptStateNotBetween(Integer value1, Integer value2) {
            addCriterion("user_accept_state not between", value1, value2, "userAcceptState");
            return (Criteria) this;
        }

        public Criteria andArriveStateIsNull() {
            addCriterion("arrive_state is null");
            return (Criteria) this;
        }

        public Criteria andArriveStateIsNotNull() {
            addCriterion("arrive_state is not null");
            return (Criteria) this;
        }

        public Criteria andArriveStateEqualTo(Integer value) {
            addCriterion("arrive_state =", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateNotEqualTo(Integer value) {
            addCriterion("arrive_state <>", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateGreaterThan(Integer value) {
            addCriterion("arrive_state >", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrive_state >=", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateLessThan(Integer value) {
            addCriterion("arrive_state <", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateLessThanOrEqualTo(Integer value) {
            addCriterion("arrive_state <=", value, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateIn(List<Integer> values) {
            addCriterion("arrive_state in", values, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateNotIn(List<Integer> values) {
            addCriterion("arrive_state not in", values, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateBetween(Integer value1, Integer value2) {
            addCriterion("arrive_state between", value1, value2, "arriveState");
            return (Criteria) this;
        }

        public Criteria andArriveStateNotBetween(Integer value1, Integer value2) {
            addCriterion("arrive_state not between", value1, value2, "arriveState");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNull() {
            addCriterion("arrive_time is null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNotNull() {
            addCriterion("arrive_time is not null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeEqualTo(Date value) {
            addCriterion("arrive_time =", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotEqualTo(Date value) {
            addCriterion("arrive_time <>", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThan(Date value) {
            addCriterion("arrive_time >", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_time >=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThan(Date value) {
            addCriterion("arrive_time <", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrive_time <=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIn(List<Date> values) {
            addCriterion("arrive_time in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotIn(List<Date> values) {
            addCriterion("arrive_time not in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeBetween(Date value1, Date value2) {
            addCriterion("arrive_time between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrive_time not between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andAppraiseIsNull() {
            addCriterion("appraise is null");
            return (Criteria) this;
        }

        public Criteria andAppraiseIsNotNull() {
            addCriterion("appraise is not null");
            return (Criteria) this;
        }

        public Criteria andAppraiseEqualTo(String value) {
            addCriterion("appraise =", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseNotEqualTo(String value) {
            addCriterion("appraise <>", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseGreaterThan(String value) {
            addCriterion("appraise >", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseGreaterThanOrEqualTo(String value) {
            addCriterion("appraise >=", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseLessThan(String value) {
            addCriterion("appraise <", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseLessThanOrEqualTo(String value) {
            addCriterion("appraise <=", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseLike(String value) {
            addCriterion("appraise like", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseNotLike(String value) {
            addCriterion("appraise not like", value, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseIn(List<String> values) {
            addCriterion("appraise in", values, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseNotIn(List<String> values) {
            addCriterion("appraise not in", values, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseBetween(String value1, String value2) {
            addCriterion("appraise between", value1, value2, "appraise");
            return (Criteria) this;
        }

        public Criteria andAppraiseNotBetween(String value1, String value2) {
            addCriterion("appraise not between", value1, value2, "appraise");
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