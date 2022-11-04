package tech.dock.paymentapi.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Translate messages utilities
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageSourceUtil {

    /**
     * Find a message in the resource bundle and return
     * @param message
     * @param locale
     * @return Translated message
     */
    public static String findMessage(String message, Locale locale) {
        ResourceBundle mSource = ResourceBundle.getBundle("ValidationMessages", locale);
        return mSource.getString(message);
    }
}
