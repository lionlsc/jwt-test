package com.project.resourceserver.controller;
import com.alibaba.fastjson.JSONObject;
import com.project.resourceserver.bean.UserInfo;
import com.project.resourceserver.dao.ScrectDao;
import com.project.resourceserver.dao.UserInfoDAO;
import com.project.resourceserver.dao.UserTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.resourceserver.token.TokenUtil;
@RestController
public class Webcontroller {
    @Autowired
    private ScrectDao screctDao;
    @Autowired
    private UserInfoDAO userInfodao;
    @Autowired
    private UserTokenDAO userTokendao;
    @PostMapping("/source")
    public String getSomeResource(@RequestParam("token")String token){
            JSONObject result=new JSONObject();     /*返回结果*/
            UserInfo tempUser=new UserInfo();
            String privatekey=screctDao.getKey().getPrivateKey();
            TokenUtil tokenUtil=new TokenUtil(token,privatekey);
            if (tokenUtil.isEffective()){            /*判断是否过期*/
                tempUser.setUsername(tokenUtil.getSomeValue("userName")); /*解析token信息*/
                tempUser=userInfodao.getUserByUsername(tempUser);
                tempUser.setUserpwd("random string"); /*防止密码暴露*/
                result.put("flag",true);
                result.put("userInfo",tempUser);
            }else{
                result.put("flag",false);
            }
        return result.toJSONString();
    }

}
