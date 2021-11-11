package com.basovProjects.wokBar.service.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageByLocalServiceImpl implements MessageByLocalService{

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, null, locale);
    }

}
