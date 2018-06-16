package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.enums.MessageKey;
import kr.co.redbrush.webapp.service.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleMessageSourceServiceImpl implements MessageSourceService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object[] objects) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, objects, locale);
    }

    @Override
    public String getMessage(MessageKey key) {
        return getMessage(key.getKey());
    }

    @Override
    public String getMessage(MessageKey key, Object[] objects) {
        return getMessage(key.getKey(), objects);
    }
}
