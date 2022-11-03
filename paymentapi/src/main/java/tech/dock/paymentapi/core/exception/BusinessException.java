package tech.dock.paymentapi.core.exception;

import tech.dock.paymentapi.core.util.MessageSourceUtil;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessException extends Exception {

    public BusinessException(String message) {
        this(message, LocaleContextHolder.getLocale(), null);
    }

    public BusinessException(String message, Locale locale, Exception e){
        super(MessageSourceUtil.findMessage(message, locale), e);
    }

    public BusinessException(String message, Exception e) {
        this(message, LocaleContextHolder.getLocale(), e);
    }
}