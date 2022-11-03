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

public abstract class BaseService<M extends BaseModel<K>, K extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract BaseRepository<M, K> getRepository();

    @Transactional(readOnly = true)
    public Iterable<M> getAll(){
        return this.getRepository().findAll();
    }

    @Transactional(readOnly = true)
    public Page<M> findPaginated(SearchDto searchDTO) {
        Pageable pageable = createPageRequest(searchDTO);
        return this.getRepository().findAll(searchDTO, pageable);
    }

    @Transactional(readOnly = true)
    public M getOne(K id) throws BusinessException {
        return this.getRepository().findById(id).orElseThrow(() -> new BusinessException("entity.not.found"));
    }

    protected M preInsert(M model) throws BusinessException {
        return model;
    }

    protected void validateInsert(M model) throws BusinessException{

    }

    @Transactional
    public M insert(M model) throws BusinessException {
        model = preInsert(model);
        validateInsert(model);
        return this.getRepository().save(model);
    }

    protected M preUpdate(M model) {
        return model;
    }

    protected void validateUpdate(M model) {

    }

    @Transactional
    public M update(K id, M model) {
        model = preUpdate(model);
        validateUpdate(model);
        return getRepository().save(model);
    }

    protected void preDelete(K id) {

    }

    protected void validateDelete(K id) {

    }

    @Transactional
    public void delete(K id) {
        validateDelete(id);

        preDelete(id);

        getRepository().deleteById(id);
    }

    @Transactional
    public void delete(List<K> ids) {
        for(K id: ids){
            this.delete(id);
        }
    }

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
