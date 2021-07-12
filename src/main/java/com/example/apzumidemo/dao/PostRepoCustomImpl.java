/********************************************
 *
 * Copyright (c) 2003-2021 XML-INTL Ltd.
 *
 * All Rights Reserved
 *
 ********************************************/
package com.example.apzumidemo.dao;

import com.example.apzumidemo.dao.entity.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */

public class PostRepoCustomImpl implements PostRepoCustom
{
    @Autowired
    private EntityManager entityManager;

    @Override
    public Iterable<Post> findByUserId(int userId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> element = criteriaQuery.from(Post.class);
        Predicate[] predicates = new Predicate[1];
        predicates[0]= criteriaBuilder.equal(element.get("userId"), userId);
        criteriaQuery.multiselect(element).where(predicates);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
