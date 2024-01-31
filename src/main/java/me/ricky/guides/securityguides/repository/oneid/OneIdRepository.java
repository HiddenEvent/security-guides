package me.ricky.guides.securityguides.repository.oneid;

import lombok.RequiredArgsConstructor;
import me.ricky.guides.securityguides.client.OneIdProxy;
import me.ricky.guides.securityguides.model.OneIdUser;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneIdRepository {
    private final OneIdProxy oneIdProxy;
    public OneIdUser find(String sub) {
        return oneIdProxy.findBySub(sub).orElse(null);
    }
}
