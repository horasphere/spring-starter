package com.horasphere.springstarter.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
    @Autowired
	private Environment env;

    @Value("${info.app.version}")
    private String version;

    @Value("${frontend.dist.path}")
    private String frontendDistPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        boolean devMode = this.env.acceptsProfiles("dev");

        VersionResourceResolver versionResolver = new VersionResourceResolver()
				.addFixedVersionStrategy(version, "/**/*.js")
				.addContentVersionStrategy("/**");

        if(devMode) {
            registry.addResourceHandler("/**")
                .addResourceLocations(frontendDistPath, "classpath:static/").resourceChain(false);
        }

        //TODO configure cache
//		registry.addResourceHandler("/**")
//				.addResourceLocations(location)
//				.setCachePeriod(0)
//				.resourceChain(false);
//					.addResolver(versionResolver);

        super.addResourceHandlers(registry);
    }
}
