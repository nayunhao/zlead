package com.zlead.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.invoke.PlatformGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwImportGoodsController {

    @Autowired
    private PlatformGoodsInvoke platformGoodsInvoke;

    @PostMapping("/importGoods")
    public ApiResult importGoods(@RequestBody ApiRequest apiRequest){
        Map data=(Map)apiRequest.getData();
        List<ZlwImportGoodsParam> list=null;
        if(data!=null){
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.convertValue(data.get("goods"), new TypeReference<List<ZlwImportGoodsParam>>() { });
        }
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(e->{
                platformGoodsInvoke.getPlatFormGoodsBySpuCode(e.getSpuCode());
            });
        }
        return new ApiResult();
    }
}
