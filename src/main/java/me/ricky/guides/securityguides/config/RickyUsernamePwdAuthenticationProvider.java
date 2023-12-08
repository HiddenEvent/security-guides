package me.ricky.guides.securityguides.config;

import me.ricky.guides.securityguides.model.Authority;
import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RickyUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public RickyUsernamePwdAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 자체 인증 로직 구현
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customer = customerRepository.findByEmail(username);
        if (!customer.isEmpty()) {
            if (passwordEncoder.matches(pwd, customer.get(0).getPwd())) {
                List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(customer.get(0).getAuthorities());
                return new UsernamePasswordAuthenticationToken(username, pwd, grantedAuthorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }
    // getGrantedAuthorities 메서드 생성
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //DaoAuthenticationProvider 클래스의 supports 메소드를 참고하여 구현
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
