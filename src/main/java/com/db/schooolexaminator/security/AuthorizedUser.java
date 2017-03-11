package com.db.schooolexaminator.security;

import com.db.schooolexaminator.model.Teacher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Created by Blik on 03/09/2017.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    public AuthorizedUser(Teacher teacher) {
        super(teacher.getUserName(), teacher.getPassword(), Arrays.asList((GrantedAuthority) () -> "ROLE_USER", (GrantedAuthority) () -> "ROLE_ADMIN"));
    }

    private static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static String current_user() {
        AuthorizedUser user = safeGet();
        requireNonNull(user);
        return user.getUsername();
    }
}
