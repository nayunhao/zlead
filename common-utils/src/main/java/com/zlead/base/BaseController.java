package com.zlead.base;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.JWTEntity;
import com.zlead.utils.JWTUtils;

public class BaseController {

    public String checkToken(ApiRequest apiRequest) {


        String token = apiRequest.getToken();

        if(token!=null && token.length()>0) {
            //check by BaseController
            JWTEntity jwtEntity = JWTUtils.validateJWT(token);
            if (jwtEntity.getCode()==JWTEntity.JWT_CODE_OK) {

                return token;
            } else if (jwtEntity.getCode()==JWTEntity.JWT_CODE_EXPIRE) {
                //new token
                String newjwt = JWTUtils.createJWT("1", "token", JWTEntity.JWT_TTL);
                return newjwt;
            }
        }
        //error
        return null;
    }

    //TODO 签名的校验
}
