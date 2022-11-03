package tech.dock.paymentapi.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageSourceUtil {
    public static String findMessage(String message, Locale locale) {
        ResourceBundle mSource = ResourceBundle.getBundle("ValidationMessages", locale);
        return mSource.getString(message);
    }
}
