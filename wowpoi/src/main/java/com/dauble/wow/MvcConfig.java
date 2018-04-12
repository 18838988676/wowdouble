package com.dauble.wow;

import com.dauble.wow.format.HttpMessageConvert;
import com.dauble.wow.format.StringToListConvert;
import com.dauble.wow.interceptor.TimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

@EnableWebMvc
@ComponentScan("com.dauble.wow")
@Configuration
@EnableScheduling
public class MvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new
                InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 过滤静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assest/**")
                .addResourceLocations("classpath:/assest/");
    }

    @Bean
    public TimeInterceptor demoInterceptor(){
        return new TimeInterceptor();
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( demoInterceptor());
    }

    /**
     * 快捷的视图跳转 没有任何业务只做视图跳转的.
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/toUpload").setViewName("upload/upload");
        registry.addViewController("/toConvert").setViewName("converter");
        registry.addViewController("/sse").setViewName("sse");
        registry.addViewController("/toDefer").setViewName("defer");
    }

    /**
     * 路径 匹配 参数 配置
     * 让开发人员可以根据需求定制URL路径的匹配规则。
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //false表示设计人员希望系统对外暴露的URL不会识别和匹配.*后缀。
        configurer.setUseSuffixPatternMatch(false);
        //表示系统不区分URL的最后一个字符是否是斜杠/
        configurer.setUseTrailingSlashMatch(true);
        //如果需要定制path匹配发生的过程，
        // 可以提供自己定制的PathMatcher和UrlPathHelper，但是这种需求并不常见
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter());
        registry.addConverter(new StringToListConvert());
    }

    /**
     * 文件上传设置
     *
     * SpringMVC 中，文件的上传，是通过 MultipartResolver 实现的。 所以，如果要实现文件的上传，只要在 spring-mvc.xml 中注册相应的 MultipartResolver 即可。
     MultipartResolver 的实现类有两个：

     CommonsMultipartResolver
     StandardServletMultipartResolver
     两个的区别：

     第一个需要使用 Apache 的 commons-fileupload 等 jar 包支持，但它能在比较旧的 servlet 版本中使用。
     第二个不需要第三方 jar 包支持，它使用 servlet 内置的上传功能，但是只能在 Servlet 3 以上的版本使用。
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver commonsMultipartResolver = new
                CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(104857600);
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxInMemorySize(40960);
      /*  commonsMultipartResolver.setUploadTempDir(new Resource() {

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public boolean exists() {
                return false;
            }

            @Override
            public boolean isReadable() {
                return false;
            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public URL getURL() throws IOException {
                return null;
            }

            @Override
            public URI getURI() throws IOException {
                return null;
            }

            @Override
            public File getFile() throws IOException {
                return null;
            }

            @Override
            public long contentLength() throws IOException {
                return 0;
            }

            @Override
            public long lastModified() throws IOException {
                return 0;
            }

            @Override
            public Resource createRelative(String s) throws IOException {
                return null;
            }

            @Override
            public String getFilename() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });*/
        return  commonsMultipartResolver;
    }

    /**
     * 扩展请求的处理
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConvert());
    }

    @Bean
    public HttpMessageConvert messageConvert(){
        return new HttpMessageConvert();
    }
}
