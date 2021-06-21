package org.winker.winweb.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;
import org.winker.winweb.dao.mysql.entity.TemplateDO;
import org.winker.winweb.dao.mysql.entity.TemplateQuery;

import java.util.List;

@Mapper
@Component
public interface TemplateMapper {

    TableInfoDO queryById(Long id);

    List<TemplateDO> queryByNames(List<String> templateNames);

    List<TemplateDO> queryPage(TemplateQuery tableInfoQuery);

    int update(TemplateDO templateDO);

    int insert(TemplateDO templateDO);
}
