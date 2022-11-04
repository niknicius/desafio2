package tech.dock.paymentapi.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import tech.dock.paymentapi.core.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * Object mapper Utilities
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapperUtil {

    private static final ModelMapper MAPPER = modelMapperFactory();
    
    private static ModelMapper modelMapperFactory() {
        return new ModelMapper();
    }

    /**
     * Map an Object to Another
     * @param source Object to map
     * @param destClass Destination Class
     * @return dClass object
     */
    public static  <M, T> M map(T source, Class<M> destClass) {
        return MAPPER.map(source, destClass);
    }

    /**
     * Map an Object list to another class type
     * @param sourceList Object to map
     * @param destClass Destination Class
     * @return list of destClass objects
     */
    public static  <M, T> List<M> mapList(List<T> sourceList, Class<M> destClass) {
        List<M> list = new ArrayList<>();
        if(sourceList != null) {
            sourceList.forEach(item -> list.add(map(item, destClass)));
        }
        return list;
    }

    /**
     * Map an Object json representation
     * @param source Object to map
     * @return json representation
     */
    public static String asJsonString(final Object source) throws BusinessException {
        try {
            return new ObjectMapper().writeValueAsString(source);
        } catch (Exception e) {
            throw new BusinessException("user.entity");
        }
    }
}
