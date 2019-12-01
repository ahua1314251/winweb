//
//
//import java.lang.annotation.Annotation;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.util.ClassUtils;
//import org.winker.web.controller.Dictionary;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import winker.dal.model.WinkerUser;
//
//public class Main {
//	 static  String RESOURCE_PATTERN = "/**/*.class";
//	public static void main(String[] args) throws JsonProcessingException {
//
//
//		WinkerUser bean = new WinkerUser();
//		bean.setAddress("easadasd");
//        change(bean);
//	}
//	public static <T> T  change(T bean) throws JsonProcessingException{
//	    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//		ObjectMapper mapper = new ObjectMapper();
//		Dictionary[]  annotations = bean.getClass().getAnnotationsByType(Dictionary.class);
//	    String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath("winker.dal.model")+RESOURCE_PATTERN ;
//	    Resource[] resources = resourcePatternResolver.getResources(pattern);
//
//		System.out.println(mapper.writeValueAsString();
//
//
//		return bean;
//	}
//
//}
