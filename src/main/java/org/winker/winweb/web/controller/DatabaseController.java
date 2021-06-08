package org.winker.winweb.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;
import org.winker.winweb.dao.mysql.entity.TemplateDO;
import org.winker.winweb.dao.mysql.entity.TemplateQuery;
import org.winker.winweb.result.ResultPageWrapper;
import org.winker.winweb.web.bean.Table;
import org.winker.winweb.service.DataBaseService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    DataBaseService dataBaseService;

    @ResponseBody
    @GetMapping("/getTablelist2.json")
    ResultPageWrapper getTableList2() throws SQLException {
        System.out.println("getTableList2");
        List<Table> list  = dataBaseService.getTableList2();
        return ResultPageWrapper.ofSuccess(list);

    }



    @ResponseBody
    @GetMapping("/getTablelist.json")
    ResultPageWrapper getTableList() throws SQLException {
        TableInfoQuery tableInfoQuery = new TableInfoQuery();
        List<TableInfoDO>  tableInfoDOList= dataBaseService.getTableList(tableInfoQuery);
        return ResultPageWrapper.ofSuccess(tableInfoDOList);

    }
    @ResponseBody
    @GetMapping("/getTemplatelist.json")
    ResultPageWrapper getTemplatelist() throws SQLException {
        TemplateQuery tableInfoQuery = new TemplateQuery();
        List<TemplateDO>  templateDOList= dataBaseService.getTemplateList(tableInfoQuery);
        return ResultPageWrapper.ofSuccess(templateDOList);

    }

    @ResponseBody
    @PostMapping("/updateTemplate.json")
    ResultPageWrapper updateTemplate(@RequestBody TemplateDO templateDO) throws SQLException {
        int result = dataBaseService.updateTemplate(templateDO);
        return ResultPageWrapper.ofSuccess(result);
    }

}
