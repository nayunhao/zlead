package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwShopGoodsBrand;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsBrandService extends IService<ZlwShopGoodsBrand> {

    /**
     * 获取所有品牌
     */
    Map<String, Object> getShopGoodsBrand(String searchKey);
    /**
     * nayunhao
     * 根据品牌名获得品牌
     *
     */
    ZlwShopGoodsBrand getShopGoodsBrandByName(String brandName);
}
