package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwShopGoodsSku;

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
public interface IZlwShopGoodsSkuService extends IService<ZlwShopGoodsSku> {
    boolean batchEditGoodsStatus(String goodsId,String type);

    boolean batchSetGoodsClass(Map<String,Object> map);
    List<ZlwShopGoodsSku> getGoodsListByStatus(ZlwShopGoodsSku zlwShopGoodsSku);
    /**
     *
     * 通过规格名称信息查询规格值数量
     * @param data
     * @return
     */
    int countBySpecValueBySpecName(Map data);
}
