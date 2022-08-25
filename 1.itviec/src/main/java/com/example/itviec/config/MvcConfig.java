package com.example.itviec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imgDir= Paths.get("./upload_img");
        String imgPath=imgDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/upload_img/**").addResourceLocations("file:/"+imgPath+"/");
    }
}
