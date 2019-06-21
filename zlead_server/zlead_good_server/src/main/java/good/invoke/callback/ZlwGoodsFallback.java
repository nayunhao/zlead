package good.invoke.callback;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;

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

    /**
     * nayunhao
     * 添加商品分类
     * @param zlwShopGoodsClass
     * @return
     */

    @Override
    public boolean addShopClass(ZlwShopGoodsClass zlwShopGoodsClass) {
        return false;
    }

    /**
     *nayunhao
     * 根据名称获得分类
     * @param className
     * @return
     */
    @Override
    public ZlwShopGoodsClass getClassByName(String className) {
        return null;
    }

    /**
     *nayunhao
     * 单条添加商品
     * @param data
     * @return
     */
    @Override
    public boolean addGoodsByOne(Map<String, Object> data) {
        return false;
    }

    /**
     *nayunhao
     * 根据名称获得品牌
     * @param brandName
     * @return
     */
    @Override
    public ZlwShopGoodsBrand getShopGoodsBrandByName(String brandName) {
        return null;
    }

    /**
     * nayunhao
     * 根据名称查询单位
     * @param unitName
     * @return
     */
    @Override
    public ZlwShopGoodsUnit getShopGoodsUnitByName(String unitName) {
        return null;
    }
    /**
     * nayunhao
     * @param data
     * @return
     */
    @Override
    public List<ZlwShopGoodsSku> getShopGoodsBySpecs(Map data) {
        return null;
    }

    /**
     *nayunhao
     * 通过规格名称信息查询规格值数量
     * @param data
     * @return
     */
    @Override
    public int countBySpecValueBySpecName(Map data) {
        return -1;
    }

    @Override
    public ApiResult importGoods(ApiRequest apiRequest) {
        return null;
    }


}
