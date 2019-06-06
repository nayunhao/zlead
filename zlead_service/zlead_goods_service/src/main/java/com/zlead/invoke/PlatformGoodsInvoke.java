package com.zlead.invoke;

import com.zlead.domain.ApiRequest;
import com.zlead.fallback.PlatformGoodFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zlead-platform-goods-service", fallback = PlatformGoodFallback.class)
public interface PlatformGoodsInvoke {

    @RequestMapping("/platformGoods/{spuCode}")
    public ApiRequest getPlatFormGoodsBySpuCode(String spuCode);
}
