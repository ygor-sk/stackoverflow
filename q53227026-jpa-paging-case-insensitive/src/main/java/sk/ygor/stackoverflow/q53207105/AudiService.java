package sk.ygor.stackoverflow.q53207105;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class AudiService {

    private final EntityManager em;

    public enum Sorting {
        descriptionCaseInsensitive
    }

    public AudiService(EntityManager em) {
        this.em = em;
    }

    public Page<Audi> getPage(int pageNumber, int pageSize, String descriptionFilter, Sorting sorting) {
        return new PageImpl<>(
                getPageItems(pageNumber, pageSize, descriptionFilter, sorting),
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sorting.name())),
                getTotalCount(descriptionFilter)
        );
    }

    private List<Audi> getPageItems(int pageNumber, int pageSize, String descriptionFilter, Sorting sorting) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Audi> query = cb.createQuery(Audi.class);
        Root<Audi> root = query.from(Audi.class);
        query.where(createSpecification(descriptionFilter).toPredicate(root, query, cb));

        if (sorting.equals(Sorting.descriptionCaseInsensitive)) {
            query.orderBy(cb.asc(cb.lower(root.get("description"))));
        } else {
            throw new UnsupportedOperationException("Unsupported sorting: " + sorting.name());
        }
        query.select(root);

        return em.createQuery(query)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    private long getTotalCount(String descriptionFilter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Audi> root = query.from(Audi.class);
        query.where(createSpecification(descriptionFilter).toPredicate(root, query, cb));
        query.select(cb.countDistinct(root));

        // getSingleResult can return null, if no rows fulfill the predicate
        return Optional.ofNullable(em.createQuery(query).getSingleResult()).orElse(0L);
    }

    private Specification<Audi> createSpecification(String descriptionFilter) {
        return Specification.where(
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + descriptionFilter.toLowerCase() + "%")
        );
    }
}
