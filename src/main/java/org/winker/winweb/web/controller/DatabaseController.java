package org.winker.winweb.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;
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
    ResultPageWrapper getTableList() throws SQLException {
        System.out.println("getTableList2");
        List<Table> list  = dataBaseService.getTableList2();
        return ResultPageWrapper.ofSuccess(list);

    }



    @ResponseBody
    @GetMapping("/getTablelist.json")
    ResultPageWrapper getTableList2() throws SQLException {
        TableInfoQuery tableInfoQuery = new TableInfoQuery();
        List<TableInfoDO>  tableInfoDOList= dataBaseService.getTableList(tableInfoQuery);
        return ResultPageWrapper.ofSuccess(tableInfoDOList);

    }


}
