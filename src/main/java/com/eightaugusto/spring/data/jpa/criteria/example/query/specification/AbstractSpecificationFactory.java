package com.eightaugusto.spring.data.jpa.criteria.example.query.specification;

import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.Query;
import jakarta.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 * <code>AbstractSpecificationFactory</code> is an abstract specification aggregator (and) which
 * uses an abstract function to get the custom specification for the provided type of entity.
 *
 * @param <E> Entity.
 * @param <Q> Query.
 */
public abstract class AbstractSpecificationFactory<E, Q extends Query> {

  /**
   * Based on <code>Query</code> create an aggregator (and) <code>Specification</code> using the
   * abstract function <code>getSpecificationByQuery(query)</code> which contains none to multiple
   * specification depending on the custom implementation.
   *
   * @param query Query.
   * @return Specification.
   */
  public Specification<E> build(@NotNull Q query) {
    final List<Specification<E>> specifications = getSpecificationByQuery(query);

    final Iterator<Specification<E>> iterator = specifications.listIterator();
    if (!iterator.hasNext()) {
      return Specification.where(null);
    }

    Specification<E> specification = iterator.next();
    while (iterator.hasNext()) {
      specification = specification.and(iterator.next());
    }

    return specification;
  }

  protected abstract List<Specification<E>> getSpecificationByQuery(Q query);
}
