package tech.dock.paymentapi.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapperUtil {

    private static final ModelMapper MAPPER = modelMapperFactory();
    
    private static ModelMapper modelMapperFactory() {
        return new ModelMapper();
    }

    public static  <M, T> M map(T source, Class<M> dClass) {
        return MAPPER.map(source, dClass);
    }

    public static  <M, T> List<M> mapList(List<T> sourceList, Class<M> destClass) {
        List<M> list = new ArrayList<>();
        if(sourceList != null) {
            sourceList.forEach(item -> list.add(map(item, destClass)));
        }
        return list;
    }
}
