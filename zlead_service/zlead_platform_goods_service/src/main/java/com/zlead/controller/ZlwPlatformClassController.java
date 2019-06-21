package com.zlead.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import com.zlead.service.IZlwPlatformGoodsClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/ZlwPlatformGoods")
@Slf4j
public class ZlwPlatformClassController {
    @Autowired
    IZlwPlatformGoodsClassService iZlwPlatformGoodsClassService;

    /**
     * 根据分类id查询下级分类
     * pgcId  不传参数/0时,查询一级分类
     */
    @PostMapping("/getPlatFormClass")
    public ApiResult getPlatFormClass(@RequestBody ApiRequest apiRequest){
        String token = apiRequest.getToken();
        ApiResult apiResult;
        try {
            String pgcId = (String)apiRequest.getData().get("pgcId");
            Map<String, Object> resultMap = iZlwPlatformGoodsClassService.getPlatFormClass(pgcId);

            apiResult = ApiResult.isOk(token,"请求成功", resultMap);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = ApiResult.isErr(token,"请求失败", null);
        }
        return apiResult;
    }

    @PostMapping("/getPlatGoodsClassByName")
    public ZlwPlatformGoodsClass getPlatGoodsClassByName(@RequestBody String className){
        ZlwPlatformGoodsClass zlwPlatformGoodsClass = iZlwPlatformGoodsClassService.getPlatGoodsClassByName(className);
        return zlwPlatformGoodsClass;
    }


}
