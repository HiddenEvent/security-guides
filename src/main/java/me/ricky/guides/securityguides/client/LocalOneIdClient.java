package me.ricky.guides.securityguides.client;

import me.ricky.guides.securityguides.model.OneIdUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@ConditionalOnBean(value = LocalOneIdConfig.class)
@Component
public class LocalOneIdClient implements OneIdProxy {
    @Override
    public Optional<OneIdUser> findBySub(String sub) {
        return Optional.empty();
    }
}
