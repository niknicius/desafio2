package tech.dock.paymentapi.core.base;

import tech.dock.paymentapi.core.domain.SearchSpecification;
import tech.dock.paymentapi.core.dto.SearchDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Reusable repository with pagination and search support
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@NoRepositoryBean
public interface BaseRepository<M extends BaseModel<K>, K extends Serializable>
        extends PagingAndSortingRepository<M, Serializable>, JpaSpecificationExecutor<M> {

    /**
     * Find all items with the specified filter and page settings
     * @return Page
     */
    default Page<M> findAll(SearchDto searchFilter, Pageable pageable) {
        return this.findAll(generateSpecification(searchFilter.getSearch()), pageable);
    }

    /**
     * Generate the search specification
     * @return Specification
     */
    default Specification<M> generateSpecification(String search) {
        return new SearchSpecification<>(search);
    }
}
