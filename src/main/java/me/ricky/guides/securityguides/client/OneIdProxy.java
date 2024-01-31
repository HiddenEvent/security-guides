package me.ricky.guides.securityguides.client;

import me.ricky.guides.securityguides.model.OneIdUser;

import java.util.Optional;

public interface OneIdProxy {
    Optional<OneIdUser> findBySub(String sub);
}
