package com.event.bbs.web.service.security;

import com.event.bbs.web.common.UserRole;
import com.event.bbs.web.entity.AdminUser;
import com.event.bbs.web.service.AdminUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {


  private final AdminUserService adminUserService;
  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

    log.info("UserSecurityService loadUserByUsername !! " + loginId);
    log.info(UserRole.ADMIN.getValue());

    Optional<AdminUser> adminUser = this.adminUserService.findByLoginId(loginId);
    if (adminUser.isEmpty()) {
      throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
    }
    AdminUser user = adminUser.get();
    log.info(user.getLoginId().toString());

    List<GrantedAuthority> authorities = new ArrayList<>();
    if ("admin".equals(loginId)) {
      authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
    } else {
      authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
    }

    return new User(user.getLoginId(), user.getPassword(), authorities);
  }
}
