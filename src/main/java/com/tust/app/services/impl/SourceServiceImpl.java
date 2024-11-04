package com.tust.app.services.impl;

import com.tust.app.dao.SourceMapper;
import com.tust.app.domain.Source;
import com.tust.app.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceMapper sourceMapper;
    public List<Source> sourceList(){
        return sourceMapper.selectAllSource();
    }

    @Override
    public boolean deleteSource(Integer id) {
        return sourceMapper.deleteSourceById(id);
    }

    @Override
    public int addSource(Source source) {
        return sourceMapper.addSource(source);
    }
}
