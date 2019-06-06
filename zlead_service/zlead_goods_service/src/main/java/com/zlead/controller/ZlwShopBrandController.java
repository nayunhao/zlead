package com.zlead.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.service.IZlwShopGoodsBrandService;
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
public class ZlwShopBrandController {
    @Autowired
    IZlwShopGoodsBrandService iZlwShopGoodsBrandService;

    /**
     * 获取所有品牌
     */
    @PostMapping("/getGoodsBrand")
    public ApiResult getShopGoodsUnit(@RequestBody ApiRequest apiRequest){
        String token = apiRequest.getToken();
        ApiResult apiResult;
        try {
            String searchKey = (String)apiRequest.getData().get("searchKey");
            Map<String, Object> resultMap = iZlwShopGoodsBrandService.getShopGoodsBrand(searchKey);

            apiResult = ApiResult.isOk(token,"请求成功", resultMap);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = ApiResult.isErr(token,"请求失败", null);
        }
        return apiResult;
    }

}
