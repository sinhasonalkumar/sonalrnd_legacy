/*
 * Copyright 2007-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springbyexample.orm.hibernate3.dao;


import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springbyexample.orm.hibernate3.bean.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Person DAO implementation.
 *
 * @author David Winterfeldt
 */
@Repository
@Transactional(readOnly = true)
public class PersonDaoImpl implements PersonDao {

    protected HibernateTemplate template = null;

    /**
     * Sets Hibernate session factory.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    /**
     * Find persons.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons() throws DataAccessException {
        return (Collection<Person>) template.find("from Person");
    }

    /**
     * Find persons by last name.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersonsByLastName(String lastName) throws DataAccessException {
        return (Collection<Person>) template.find("from Person p where p.lastName = ?", lastName);
    }

    /**
     * Saves person.
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Person person) {
        template.saveOrUpdate(person);
    }

}
