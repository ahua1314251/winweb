package org.winker.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:/config/servlet-context.xml"})
public class XMLConfig {

}