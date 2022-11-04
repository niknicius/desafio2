package tech.dock.paymentapi.core.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.dock.paymentapi.core.dto.SearchDto;
import tech.dock.paymentapi.core.util.JsonUtil;
import tech.dock.paymentapi.core.util.MapperUtil;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * Reusable controller class
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
public abstract class BaseController<M extends BaseModel<K>, K extends Serializable, D extends BaseDTO> {

    protected abstract BaseService<M, K> getService();

    /**
     * Process the request and return all items
     * @return ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity<Page<M>> getAll(HttpServletRequest request, @PathParam("filters") String filters){
        SearchDto searchDto = new SearchDto();
        if (filters != null) {
            searchDto = JsonUtil.fromJson(filters, SearchDto.class);
        }

        var result = this.getService().findPaginated(searchDto);
        var httpStatus = result.getNumberOfElements() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(result, httpStatus);
    }

    /**
     * Process the request and delete the requested item
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") K id) {
        this.getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected <S, Z> Z mapTo(S source, Class<Z> destClass) {
        return MapperUtil.map(source, destClass);
    }

    protected <Z, S> List<Z> toList(List<S> items, Class<Z> destClass) {
        return MapperUtil.mapList(items, destClass) ;
    }

}
