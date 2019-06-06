package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsMapper extends BaseMapper<ZlwShopGoods> {
    @Select("SELECT sg_name FROM zlw_shop_goods WHERE sg_id = #{sgId}")
    public ZlwShopGoods getShopGoods(String sguId);

    @Select("SELECT count(*) FROM zlw_shop_goods WHERE shop_id = #{shopId} and sg_custom_code = #{customCode}")
    int selectCountByShopIdAndCustomCode(Map<String, Object> map);

}
