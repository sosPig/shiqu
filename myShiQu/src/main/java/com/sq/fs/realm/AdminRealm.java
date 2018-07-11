package com.sq.fs.realm;

import com.sq.fs.pojo.Admin;
import com.sq.fs.service.AdminService;
import com.sq.fs.shiro.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/7/11.
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomizedToken customizedToken = (CustomizedToken) token;
        String adminnameInput = (String) customizedToken.getPrincipal();
        String passwordInput = new String((char[]) customizedToken.getCredentials());
        Admin admin = adminService.login(adminnameInput,passwordInput);
        if(admin==null){
            throw new UnknownAccountException("用户不存在");
        }

        String password = admin.getPassword();
//        Md5Hash md5Hash = new Md5Hash(passwordInput, adminnameInput, Constant.HASH_ITERATIONS);
//        passwordInput=md5Hash.toString();
        if(!password.equals(passwordInput)){
            throw new IncorrectCredentialsException("用户密码不正确");
        }


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin, password, getName());
        return info;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    
}
