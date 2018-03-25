package org.winker.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:/config/servlet-context.xml","classpath:/config/application-context2.xml"})
public class XMLConfig {

}