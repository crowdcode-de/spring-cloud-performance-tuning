package io.crowdcode.commons.jpa.annotations;

import io.crowdcode.commons.jpa.CommonsJPA;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = CommonsJPA.class)
public class CommonsJpaConfiguration {
}
