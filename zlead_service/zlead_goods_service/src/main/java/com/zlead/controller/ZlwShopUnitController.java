package com.zlead.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.service.IZlwShopGoodsUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwShopUnitController {
    @Autowired
    IZlwShopGoodsUnitService iZlwShopGoodsUnitService;

    /**
     * 获取所有商品单位
     */
    @PostMapping("/getShopGoodsUnit")
    public ApiResult getShopGoodsUnit(@RequestBody ApiRequest apiRequest){
        String token = apiRequest.getToken();
        ApiResult apiResult;
        try {
            Map<String, Object> resultMap = iZlwShopGoodsUnitService.getShopGoodsUnit();

            apiResult = ApiResult.isOk(token,"请求成功", resultMap);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = ApiResult.isErr(token,"请求失败", null);
        }
        return apiResult;
    }

}
