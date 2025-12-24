cat <<'EOF' > src/main/java/com/example/demo/CustomUserDetailsService.java
package com.example.demo;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository repository;

    public CustomUserDetailsService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile cp = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // We return a dummy hash since we are not using a real DB password column for this demo
        return new User(cp.getEmail(), "$2a$10$DUMMYHASH", Collections.emptyList());
    }
}
EOF