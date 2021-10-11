package org.winker.winweb.web.controller;


import com.alibaba.druid.support.json.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;
import org.winker.winweb.dao.mysql.entity.TemplateDO;
import org.winker.winweb.dao.mysql.entity.TemplateQuery;
import org.winker.winweb.result.ResultPageWrapper;
import org.winker.winweb.service.DataBaseService;
import org.winker.winweb.utils.database.MysqlParserUtils;
import org.winker.winweb.web.bean.Table;
import org.winker.winweb.web.bean.TemplateEntity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database")
public class DatabaseController {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);
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

    @ResponseBody
    @PostMapping("/createTemplate.json")
    ResultPageWrapper createTemplate(@RequestBody TemplateDO templateDO) throws SQLException {
        int result = dataBaseService.createTemplate(templateDO);
        return ResultPageWrapper.ofSuccess(result);
    }

    @ResponseBody
    @PostMapping("/createCode.json")
    ResultPageWrapper createCode(@RequestBody Map<String,Object> map) throws SQLException {
        Long sqlId = Long.parseLong(map.get("sqlId").toString());
        List<String> templateNames = (List<String>) map.get("templateNames");
        List<TemplateEntity> result = dataBaseService.createCode(sqlId,templateNames);
        return ResultPageWrapper.ofSuccess(result);
    }

    @GetMapping("/downloadCode.json")
    ResponseEntity<byte[]> downloadCode(@RequestParam("param") String param) throws SQLException, IOException {
        Jackson
        Long sqlId = Long.parseLong(map.get("sqlId").toString());
        List<String> templateNames = (List<String>) map.get("templateNames");
        List<TemplateEntity> templateEntityList = dataBaseService.createCode(sqlId,templateNames);
        File zipFile = MysqlParserUtils.resultToZip(templateEntityList);
        //获取文件对象
        try {
            byte[] bytes = FileUtils.readFileToByteArray(zipFile);
            HttpHeaders headers=new HttpHeaders();
            headers.set("Content-Disposition","attachment;filename="+zipFile.getName());
            ResponseEntity<byte[]> entity=new ResponseEntity<>(bytes,headers, HttpStatus.OK);
            return entity;
        } catch (IOException e) {
            logger.error("下载出错:",e);
            return null;
        }finally {
            zipFile.delete();
        }
    }
}
