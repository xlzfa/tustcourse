package com.tust.app.dao;

import com.tust.app.domain.Source;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SourceMapper {

    List<Source> selectAllSource();

    boolean deleteSourceById(Integer id);

    int addSource(Source source);
}
