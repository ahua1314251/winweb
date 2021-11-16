package org.winker.winweb.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.winker.winweb.dao.mysql.entity.SpiderDO;
import org.winker.winweb.dao.mysql.entity.SpiderQuery;

import java.util.List;

@Mapper
public interface SpiderMapper {

    SpiderDO queryById(Long id);

    List<SpiderDO> queryPage(SpiderQuery query);

    int update(SpiderDO spiderDO);

    int insert(SpiderDO spiderDO);

    int deleteById(Long id);

    int deleteByUk(String uk);

    Integer existByUk(String uk);

    int updateByUk(SpiderDO spiderDO);
}
