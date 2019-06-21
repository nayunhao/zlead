package good.invoke;

import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import good.invoke.callback.ZlwGoodsFallback;
import good.invoke.callback.ZlwPlatformGoodsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "zlead-platform-goods-service", fallback = ZlwPlatformGoodsFallback.class)
public interface ZlwPlatformGoodsInvoke {
    /**
     * nayunhao
     * 根据名称查询平台分类
     * @param className
     * @return
     */
    @PostMapping("/ZlwPlatformGoods/getPlatGoodsClassByName")
    public ZlwPlatformGoodsClass getPlatGoodsClassByName(@RequestBody String className);
}
