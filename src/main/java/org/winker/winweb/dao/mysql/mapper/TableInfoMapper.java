package org.winker.winweb.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;

import java.util.List;

@Mapper
public interface TableInfoMapper {

    TableInfoDO queryById(Long id);

    List<TableInfoDO> queryPage(TableInfoQuery query);

    int update(TableInfoDO tableInfoDO);

    int insert(TableInfoDO tableInfoDO);

    int deleteById(Long id);
}