package org.winker.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.winker.web.Table;
import org.winker.web.service.DataBaseService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class Database {

    @Autowired
    DataBaseService dataBaseService;

    @ResponseBody
    @GetMapping("/tablelist.json")
    List<Table> getTableList() throws SQLException {
        dataBaseService.initMysqlDatasource();
        System.out.println("getTableList");
        return dataBaseService.getTableList();

    }


}
