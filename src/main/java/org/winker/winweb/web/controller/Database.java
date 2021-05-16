package org.winker.winweb.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.winker.winweb.result.ResultPageWrapper;
import org.winker.winweb.web.bean.Table;
import org.winker.winweb.web.service.DataBaseService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class Database {

    @Autowired
    DataBaseService dataBaseService;

    @ResponseBody
    @GetMapping("/getTablelist.json")
    ResultPageWrapper getTableList() throws SQLException {
        dataBaseService.initMysqlDatasource();
        System.out.println("getTableList");
        List<Table> list  = dataBaseService.getTableList();
        return ResultPageWrapper.ofSuccess(list);

    }


}
