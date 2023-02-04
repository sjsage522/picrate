package com.junseok.picrate.common.config;

import com.junseok.picrate.common.property.AppProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        AppProperty.class
})
public class PropertyConfig {
}
