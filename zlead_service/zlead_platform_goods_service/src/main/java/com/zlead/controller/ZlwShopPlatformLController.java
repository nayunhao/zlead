package com.zlead.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.service.IZlwPlatformGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/513:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ZlwGoods")
public class ZlwShopPlatformLController extends BaseController {

    @Autowired
    private IZlwPlatformGoodsService iZlwPlatformGoodsService;




    /**
     * 查询平台商品列表（分页）
     * @param apiRequest
     * @return
     */
    @PostMapping("/getPlatformGoodsList")
    public ApiResult getPlatformGoodsList(@RequestBody ApiRequest apiRequest){
        Map<String,Object>map=new HashMap<>();
        String token = checkToken(apiRequest);
        if(token!=null){
            if(!token.equals("newjwt")){
                try{
                    String currentPage = String.valueOf(apiRequest.getData().get("currentPage"));
                    String sizePage = String.valueOf(apiRequest.getData().get("sizePage"));
                    Long aPage = Long.valueOf(currentPage);
                    Long aSize = Long.valueOf(sizePage);
                    if(aPage!=null&&aSize!=null){
                        Page<ZlwPlatformGoods> page = new Page<>(aPage,aSize);
                        Page<ZlwPlatformGoods>  zlwPlatformGoodsPage = page.setRecords(iZlwPlatformGoodsService.selectpage(page));
                        map.put("goods",zlwPlatformGoodsPage);
                        return ApiResult.isOk(token,"成功",map);
                    }else {
                        return ApiResult.isErr(token,"分页参数必传必传",map);
                    }
                }catch (Exception e){
                    return ApiResult.isErr(token,"系统错误",map);
                }
            }else {
                return  ApiResult.isErr(token,"token失效",map);
            }

        } else{
            return  ApiResult.isErr(token,"token无效",map);
        }
    }




}
