package me.ricky.guides.securityguides.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "sub")
public class OneIdUser {
    private String sub;
    private String email;
    private String name;
    private String phone;
}
