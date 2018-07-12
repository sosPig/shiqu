package com.sq.fs.realm;


import com.sq.fs.pojo.User;
import com.sq.fs.service.UserService;
import com.sq.fs.shiro.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/7/1.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomizedToken customizedToken = (CustomizedToken) token;
        String usernameInput = (String) customizedToken.getPrincipal();
        String passwordInput = new String((char[]) customizedToken.getCredentials());
        User user = userService.login(usernameInput);
        if(user==null){
            throw new UnknownAccountException("用户不存在");
        }

        String password = user.getPassword();
//        Md5Hash md5Hash = new Md5Hash(passwordInput, usernameInput, Constant.HASH_ITERATIONS);
//        passwordInput=md5Hash.toString();
        if(!password.equals(passwordInput)){
            throw new IncorrectCredentialsException("用户密码不正确");
        }


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


       return null;
    }
}
