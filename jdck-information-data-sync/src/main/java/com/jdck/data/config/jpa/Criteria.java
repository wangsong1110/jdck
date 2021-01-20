package com.jdck.data.config.jpa;


import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 定义一个查询条件容器
 *
 * @param <T>
 */
public class Criteria<T> implements Specification<T> {

    private List<Criterion> criterions = new ArrayList<Criterion>();

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (Criterion c : criterions) {
                predicates.add(c.toPredicate(root, query, builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }

    /**
     * 增加简单条件表达式
     *
     * @param
     * @Methods Name add
     */
    public Criteria<T> add(Criterion criterion) {

        if (criterion != null) {
            criterions.add(criterion);
        }
        return this;
    }
   /* Criteria<EntityAlarmrecord> criteria = new Criteria<>();
        criteria.add(Restrictions.equals("id", 10)).add(Restrictions.like("name", "abc"));*/

    public Criteria<T>
    getCriteria(List<Parameters> parametersList) {
        Criteria<T> criteria = new Criteria<>();
        for (Parameters parameters : parametersList) {
            switch (parameters.getOperator()) {
                case equals:
                    criteria.add(Restrictions.equals(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case like:
                    criteria.add(Restrictions.like(parameters.getAttributeName(), parameters.getValue().toString()));
                    break;
                case greaterThanOrEqualTo:
                    criteria.add(Restrictions.gte(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case lessThanOrEqualTo:
                    criteria.add(Restrictions.lte(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case greaterThan:
                    criteria.add(Restrictions.greaterThan(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case lessThan:
                    criteria.add(Restrictions.lessThan(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case notequals:
                    criteria.add(Restrictions.notEquals(parameters.getAttributeName(), parameters.getValue()));
                    break;
                case in:
                    criteria.add(Restrictions.in(parameters.getAttributeName(), (Collection) parameters.getValue(), true));
                    break;
                case isNull:
                    criteria.add(Restrictions.isNull(parameters.getAttributeName()));
                    break;
                case isNotNull:
                    criteria.add(Restrictions.isNotNull(parameters.getAttributeName()));
                    break;
            }
        }
        return criteria;
    }

    /**
     * or方法
     *
     * @param parametersList
     * @param or
     * @return
     */
    public Criteria<T>
    getCriteria(Criteria<T> criteria,List<Parameters> parametersList, String or) {
        if (or.equals("or")) {
            criteria.add(Restrictions.or(parametersList));
        }
        return criteria;
    }
}
