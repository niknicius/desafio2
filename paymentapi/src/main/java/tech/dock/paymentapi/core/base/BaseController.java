package tech.dock.paymentapi.core.base;

import java.io.Serializable;
import java.util.List;

import tech.dock.paymentapi.core.util.MapperUtil;

public abstract class BaseController<M extends BaseModel<K>, K extends Serializable, D extends BaseDTO> {

    protected <S, Z> Z mapTo(S source, Class<Z> destClass) {
        return MapperUtil.map(source, destClass);
    }

    protected <D, S> List<D> toList(List<S> items, Class<D> destClass) {
        return MapperUtil.mapList(items, destClass) ;
    }

}
