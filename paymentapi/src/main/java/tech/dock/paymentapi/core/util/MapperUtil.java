package tech.dock.paymentapi.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import tech.dock.paymentapi.core.exception.BusinessException;

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

    public static String asJsonString(final Object obj) throws BusinessException {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new BusinessException("user.entity");
        }
    }
}
