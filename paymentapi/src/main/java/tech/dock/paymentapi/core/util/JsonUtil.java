package tech.dock.paymentapi.core.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Json Utilities
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    /**
     * GSON instance
     * @see Gson
     */
    private static final Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm")
            .create();

    /**
     * Map an object to a JSON String
     * @param object Object to be mapped
     * @return JSON String
     */
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    /**
     * Map a JSON String to an Object
     * @param json JSON String
     * @param dClass Class of the object
     * @param <D> Class of the Object
     * @return Object from JSON String
     */
    public static <D> D fromJson(String json, Class<D> dClass) {
        return GSON.fromJson(json, dClass);
    }
}
