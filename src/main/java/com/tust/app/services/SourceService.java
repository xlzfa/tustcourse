package com.tust.app.services;

import com.tust.app.domain.Source;

import java.util.List;

public interface SourceService {
    public List<Source> sourceList();

    boolean deleteSource(Integer id);

    int addSource(Source source);
}
