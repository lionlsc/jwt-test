package com.project.resourceserver.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class TokenUtil {
    private String privateKey;            /*哈希算法的密钥*/
    private String token;                 /*颁发的token*/
    private Map<String,Object> header;  /*头部的设置必须是<String,Object>*/
    private Map<String,String> message; /*要在payload中放的信息*/
    private Date expireDate;/*token过期时间*/
    private DecodedJWT decodedJwt; /*解析成功的JWT*/
    public TokenUtil(String privateKey,Date expireDate,Map message) {
        this.privateKey=privateKey;
        this.message=message;
        this.expireDate=expireDate;
        this.header=new HashMap<String, Object>();
        this.header.put("typ","JWT");
        this.header.put("alg","HS256");
    } /*颁发token对应的构造函数*/
    public TokenUtil(String token,String privateKey){
        this.token=token;
        this.privateKey=privateKey;
        this.decodedJwt=null;
    }                 /*解析token对应的构造函数*/
    public String encode(){
        Algorithm algorithm=Algorithm.HMAC256(this.privateKey);
        Builder tempBuilder=JWT.create().withHeader(this.header).withExpiresAt(this.expireDate);
        for (String key:this.message.keySet()){
            tempBuilder=tempBuilder.withClaim(key,this.message.get(key));
        }
        this.token=tempBuilder.sign(algorithm);
        return this.token;
    }         /*产生jwt*/
    public boolean isEffective(){
        try {
            Algorithm algorithm=Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier= JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(this.token);
            this.decodedJwt=jwt;
            return true;
        }catch (Exception e){
            return false;
        }
    }   /*判断是否过期*/
    public String getSomeValue(String value){
        return this.decodedJwt.getClaim(value).asString();
    } /*获取某个加密的值*/
}
