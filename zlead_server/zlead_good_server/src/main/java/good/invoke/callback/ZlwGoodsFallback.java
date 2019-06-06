package good.invoke.callback;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsClass;

import com.zlead.entity.goods.ZlwShopGoodsSku;

import good.invoke.ZlwGoodsInvoke;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ZlwGoodsFallback implements ZlwGoodsInvoke {


    @Override
    public boolean addShopGoodsClass(ZlwShopGoodsClass zlwShopGoodsClass) {
        return false;
    }

    @Override
    public List<ZlwShopGoodsSku> getGoodsListByStatus(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return null;
    }

    @Override
    public boolean addShopClass(ZlwShopGoodsClass zlwShopGoodsClass) {
        return false;
    }

    @Override
    public boolean setBatchGoodsClass(Map map) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    @Override
    public boolean editShopGoodsClass(Map data) {
        return false;
    }

    @Override
    public boolean removeShopGoodsClass(String sgcId) {
        return false;
    }

    @Override
    public boolean editGoodsStatus(Map map) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    @Override
    public ZlwShopGoodsClass getClassByName(String className) {
        return null;
    }

    @Override
    public boolean addGoodsByOne(Map<String, Object> data) {
        return false;
    }

    @Override
    public ApiResult importGoods(ApiRequest apiRequest) {
        return null;
    }
}
