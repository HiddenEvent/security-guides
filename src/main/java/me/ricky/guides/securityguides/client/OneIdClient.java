package me.ricky.guides.securityguides.client;

import lombok.extern.slf4j.Slf4j;
import me.ricky.guides.securityguides.model.OneIdUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@ConditionalOnMissingBean(value = LocalOneIdConfig.class)
@Component
public class OneIdClient implements OneIdProxy {
    @Override
    public Optional<OneIdUser> findBySub(String sub) {
        return Optional.empty();
    }
}
