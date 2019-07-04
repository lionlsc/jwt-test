package com.project.authserver.controller;
import com.alibaba.fastjson.JSONObject;
import com.project.authserver.dao.ScrectDao;
import com.project.authserver.dao.UserInfoDAO;
import com.project.authserver.dao.UserTokenDAO;
import com.project.authserver.bean.UserInfo;
import com.project.authserver.bean.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.project.authserver.token.TokenUtil;
@RestController
public class Webcontroller {
    @Autowired
    private ScrectDao screctDao;
    @Autowired
    private UserInfoDAO userInfodao;
    @Autowired
    private UserTokenDAO userTokendao;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/register")
    @Transactional
    public String register(UserInfo user){
       JSONObject result=new JSONObject();                     //返回的结果
        UserInfo tempUser=userInfodao.getUserByUsername(user);//查询用户名是否已经存在
        UserToken token=new UserToken();                       //user_token对应的实体
        String privateKey=screctDao.getKey().getPrivateKey();  //获取加密所需要的密码
        Date tempDate=new Date(System.currentTimeMillis()+60*1000*60*24); //设置过期时间
        token.setUsertokenexpire(tempDate);
        if(tempUser!=null){                                   /*用户名重复，不能注册*/
            result.put("flag",false);
            return result.toJSONString();
        }else {

            String userPwd=user.getUserpwd();
            String encodedPwd=passwordEncoder.encode(userPwd);
            user.setUserpwd(encodedPwd);  /*密码加密*/

            userInfodao.register(user);/*将注册的用户信息写入数据库*/
            UserInfo tempuser=userInfodao.getUserByUsername(user);  /*获取刚刚注册的用户*/

            token.setUserid(tempuser.getUserid());
            Map<String,String> message=new HashMap<>();
            message.put("userName",tempuser.getUsername());  /*存取信息*/
            TokenUtil tokenUtil=new TokenUtil(privateKey,tempDate,message);
            String jwt=tokenUtil.encode();
            token.setUsertoken(jwt);
            userTokendao.addToken(token);
            result.put("flag",true);
            result.put("token",jwt);
            return result.toJSONString();
        }

    }
    @PostMapping("/login")
    @Transactional
    public String login(UserInfo user){
        JSONObject result=new JSONObject();
        UserInfo tempUser=userInfodao.getUserByUsername(user); /*查询是否注册*/
        if (tempUser==null){
            result.put("flag",false);
            result.put("reason","用户不存在");
            return result.toJSONString();
        }else {
            boolean ismatch;
            ismatch=passwordEncoder.matches(user.getUserpwd(),tempUser.getUserpwd()); /*查询密码是否正确*/
            if(ismatch==false){
                result.put("flag",false);
                result.put("reason","用户不存在");
            }else{
                UserToken token=new UserToken();
                token.setUserid(tempUser.getUserid());
                Date tempDate=new Date(System.currentTimeMillis()+60*1000*60*24); /*设置过期时间*/
                token.setUsertokenexpire(tempDate);

                String privatekey=screctDao.getKey().getPrivateKey();
                Map<String,String> message=new HashMap<>();
                message.put("userName",tempUser.getUsername());


                TokenUtil tokenUtil=new TokenUtil(privatekey,tempDate,message);
                String jwt=tokenUtil.encode();


                token.setUsertoken(jwt);
                userTokendao.updateToken(token);           /*注册时获取的token过期了，更新数据库中存的token*/
                result.put("flag",true);
                result.put("token",jwt);
            }
        }
        return result.toJSONString();
    }
}
