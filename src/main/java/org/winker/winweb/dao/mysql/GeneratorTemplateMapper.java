package org.winker.winweb.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;

import java.util.List;

@Mapper
@Component
public interface GeneratorTemplateMapper {

    TableInfoDO queryById(Long id);

    List<TableInfoDO> queryPage(TableInfoQuery tableInfoQuery);
}
