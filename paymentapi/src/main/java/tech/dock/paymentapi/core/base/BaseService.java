package tech.dock.paymentapi.core.base;

import tech.dock.paymentapi.core.dto.SearchDto;
import tech.dock.paymentapi.core.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Reusable service class
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
public abstract class BaseService<M extends BaseModel<K>, K extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get the repository instance
     * @return BaseRepository
     */
    protected abstract BaseRepository<M, K> getRepository();

    /**
     * Get all items
     * @return BaseRepository
     */
    @Transactional(readOnly = true)
    public Iterable<M> getAll(){
        return this.getRepository().findAll();
    }

    /**
     * Get items with search support
     * @param searchDTO SearchDTO
     * @return Page
     */
    @Transactional(readOnly = true)
    public Page<M> findPaginated(SearchDto searchDTO) {
        Pageable pageable = createPageRequest(searchDTO);
        return this.getRepository().findAll(searchDTO, pageable);
    }

    /**
     * Get one item
     * @param id - ID of the item
     * @return BaseModel
     */
    @Transactional(readOnly = true)
    public M getOne(K id) throws BusinessException {
        return this.getRepository().findById(id).orElseThrow(() -> new BusinessException("entity.not.found"));
    }

    /**
     * Execute before the save operation
     * @return BaseModel
     */
    protected M preInsert(M model) throws BusinessException {
        return model;
    }

    /**
     * Validate the insert operation
     */
    protected void validateInsert(M model) throws BusinessException{

    }

    /**
     * Insert a new item
     * @return BaseModel
     */
    @Transactional
    public M insert(M model) throws BusinessException {
        model = preInsert(model);
        validateInsert(model);
        return this.getRepository().save(model);
    }

    /**
     * Execute before update operation
     * @return BaseModel
     */
    protected M preUpdate(M model) {
        return model;
    }

    /**
     * Validate the update
     */
    protected void validateUpdate(M model) {

    }

    /**
     * Update the item
     * @return BaseModel
     */
    @Transactional
    public M update(K id, M model) {
        model = preUpdate(model);
        validateUpdate(model);
        return getRepository().save(model);
    }

    /**
     * Execute before delete operation
     */
    protected void preDelete(K id) {

    }

    /**
     * Validate the deletion
     */
    protected void validateDelete(K id) {

    }

    /**
     * Delete the item
     */
    @Transactional
    public void delete(K id) {
        validateDelete(id);

        preDelete(id);

        getRepository().deleteById(id);
    }

    /**
     * Delete a list of items
     */
    @Transactional
    public void delete(List<K> ids) {
        for(K id: ids){
            this.delete(id);
        }
    }

    /**
     * Create pagination request
     * @return Pageable
     */
    protected Pageable createPageRequest(SearchDto searchDTO) {
        if (searchDTO.getSort().getColumns() == null) {
            return PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize());
        }
        String[] columns = searchDTO.getSort().getColumns().replace(" ", "").split(",");
        return PageRequest.of(searchDTO.getCurrentPage(),
                searchDTO.getPageSize(),
                Sort.Direction.fromString(searchDTO.getSort().getOrder()),
                columns
        );
    }
}
