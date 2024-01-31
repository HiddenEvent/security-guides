package me.ricky.guides.securityguides.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(value = "local")
@Configuration
public class LocalOneIdConfig {
}
