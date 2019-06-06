package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsSkuMapper extends BaseMapper<ZlwShopGoodsSku> {

    @Select("SELECT * FROM zlw_shop_goods_sku WHERE sg_status = #{sgStatus}  and shop_id = #{shopId}")
    List<ZlwShopGoodsSku> getGoodsListByStatus(ZlwShopGoodsSku zlwShopGoodsSku);

}
