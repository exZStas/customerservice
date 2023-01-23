package ru.test.customerservice.customerservice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.test.customerservice.customerservice.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Customer> searchCustomers(String lastname, String firstName, String patronymic, String phoneNumber, String email) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> customerRoot = query.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();

        if(isNotBlank(lastname)) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("lastname"), lastname));
        }

        if(isNotBlank(firstName)) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("firstName"), firstName));
        }

        if(isNotBlank(patronymic)) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("patronymic"), patronymic));
        }

        if(isNotBlank(phoneNumber)) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("phoneNumber"), phoneNumber));
        }

        if(isNotBlank(email)) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("email"), email));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }
}
