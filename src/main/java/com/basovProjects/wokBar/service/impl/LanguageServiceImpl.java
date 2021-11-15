package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.LanguageService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService<String, Language> {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language getById(String id) {
        return languageRepository.getById(id);
    }
}
