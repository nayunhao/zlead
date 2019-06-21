package good.invoke.callback;

import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import good.invoke.ZlwPlatformGoodsInvoke;
import org.springframework.stereotype.Component;

@Component
public class ZlwPlatformGoodsFallback implements ZlwPlatformGoodsInvoke {
    @Override
    public ZlwPlatformGoodsClass getPlatGoodsClassByName(String className) {
        return null;
    }
}
