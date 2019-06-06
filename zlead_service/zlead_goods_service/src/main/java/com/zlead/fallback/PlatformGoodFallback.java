package com.zlead.fallback;

import com.zlead.domain.ApiRequest;
import com.zlead.invoke.PlatformGoodsInvoke;
import org.springframework.stereotype.Component;

@Component
public class PlatformGoodFallback implements PlatformGoodsInvoke {

 /*   @Override
    public ApiRequest getPlatFormGoodsById() {
        return null;
    }
*/
    @Override
    public ApiRequest getPlatFormGoodsBySpuCode(String spuCode) {
        return null;
    }
}
