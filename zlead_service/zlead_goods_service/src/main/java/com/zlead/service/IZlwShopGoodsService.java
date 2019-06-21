package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsService extends IService<ZlwShopGoods> {

    /**
     * 校验自定义商品编码
     * @param shopId 店铺id
     * @param customCode 商品编码
     * @return
     */
    ApiResult checkCustomInputCode(String token, String shopId, String customCode);

    /**
     * 添加商品，需要调用事务
     * @param zlwShopGoods 商品店铺spu表
     * @param zlwShopGoodsSkus  商品店铺sku表
     * @param zlwShopGoodsSpecs  店铺SKU-商品规格表
     * @param zlwShopGoodsPrices 店铺SKU-商品价格表
     * @param zlwShopGoodsSpecsNames 店铺SKU-商品名称规格表
     * @param zlwShopGoodsSpecsValues 店铺SKU-商品规格值表
     * @param zlwShopGoodsImages 店铺SKU-商品图片表
     * @param zlwShopGoodsInventorys 店铺SKU-商品库存表
     */
     void addGoodsTrans(ZlwShopGoods zlwShopGoods,
                        List<ZlwShopGoodsSku> zlwShopGoodsSkus,
                        List<ZlwShopGoodsSpec> zlwShopGoodsSpecs,
                        List<ZlwShopGoodsPrice> zlwShopGoodsPrices,
                        List<ZlwShopGoodsSpecsName> zlwShopGoodsSpecsNames,
                        List<ZlwShopGoodsSpecsValue> zlwShopGoodsSpecsValues,
                        List<ZlwShopGoodsImages> zlwShopGoodsImages,
                        List<ZlwShopGoodsInventory> zlwShopGoodsInventorys) throws Exception;
}
