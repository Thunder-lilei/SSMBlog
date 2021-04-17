package pers.lilei.blog.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SortExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SortExample() {
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

        public Criteria andSortIdIsNull() {
            addCriterion("sort_id is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Long value) {
            addCriterion("sort_id =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Long value) {
            addCriterion("sort_id <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Long value) {
            addCriterion("sort_id >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sort_id >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Long value) {
            addCriterion("sort_id <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Long value) {
            addCriterion("sort_id <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Long> values) {
            addCriterion("sort_id in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Long> values) {
            addCriterion("sort_id not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Long value1, Long value2) {
            addCriterion("sort_id between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Long value1, Long value2) {
            addCriterion("sort_id not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNull() {
            addCriterion("sort_name is null");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNotNull() {
            addCriterion("sort_name is not null");
            return (Criteria) this;
        }

        public Criteria andSortNameEqualTo(String value) {
            addCriterion("sort_name =", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotEqualTo(String value) {
            addCriterion("sort_name <>", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThan(String value) {
            addCriterion("sort_name >", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThanOrEqualTo(String value) {
            addCriterion("sort_name >=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThan(String value) {
            addCriterion("sort_name <", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThanOrEqualTo(String value) {
            addCriterion("sort_name <=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLike(String value) {
            addCriterion("sort_name like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotLike(String value) {
            addCriterion("sort_name not like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameIn(List<String> values) {
            addCriterion("sort_name in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotIn(List<String> values) {
            addCriterion("sort_name not in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameBetween(String value1, String value2) {
            addCriterion("sort_name between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotBetween(String value1, String value2) {
            addCriterion("sort_name not between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionIsNull() {
            addCriterion("sort_description is null");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionIsNotNull() {
            addCriterion("sort_description is not null");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionEqualTo(String value) {
            addCriterion("sort_description =", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionNotEqualTo(String value) {
            addCriterion("sort_description <>", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionGreaterThan(String value) {
            addCriterion("sort_description >", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("sort_description >=", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionLessThan(String value) {
            addCriterion("sort_description <", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionLessThanOrEqualTo(String value) {
            addCriterion("sort_description <=", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionLike(String value) {
            addCriterion("sort_description like", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionNotLike(String value) {
            addCriterion("sort_description not like", value, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionIn(List<String> values) {
            addCriterion("sort_description in", values, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionNotIn(List<String> values) {
            addCriterion("sort_description not in", values, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionBetween(String value1, String value2) {
            addCriterion("sort_description between", value1, value2, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andSortDescriptionNotBetween(String value1, String value2) {
            addCriterion("sort_description not between", value1, value2, "sortDescription");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNull() {
            addCriterion("parent_sort_id is null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNotNull() {
            addCriterion("parent_sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdEqualTo(Long value) {
            addCriterion("parent_sort_id =", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotEqualTo(Long value) {
            addCriterion("parent_sort_id <>", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThan(Long value) {
            addCriterion("parent_sort_id >", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_sort_id >=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThan(Long value) {
            addCriterion("parent_sort_id <", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_sort_id <=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIn(List<Long> values) {
            addCriterion("parent_sort_id in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotIn(List<Long> values) {
            addCriterion("parent_sort_id not in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdBetween(Long value1, Long value2) {
            addCriterion("parent_sort_id between", value1, value2, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_sort_id not between", value1, value2, "parentSortId");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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
