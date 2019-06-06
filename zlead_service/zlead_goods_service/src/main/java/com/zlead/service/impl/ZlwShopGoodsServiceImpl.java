package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.*;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsService;
import com.zlead.utils.StringUtil;
import com.zlead.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwShopGoodsServiceImpl extends ServiceImpl<ZlwShopGoodsMapper, ZlwShopGoods> implements IZlwShopGoodsService {
    @Autowired
    ZlwShopGoodsMapper zlwShopGoodsMapper;
    @Autowired
    ZlwShopGoodsSkuMapper zlwShopGoodsSkuMapper;
    @Autowired
    ZlwShopGoodsSpecMapper zlwShopGoodsSpecMapper;
    @Autowired
    ZlwShopGoodsPriceMapper zlwShopGoodsPriceMapper;
    @Autowired
    ZlwShopGoodsSpecsNameMapper zlwShopGoodsSpecsNameMapper;
    @Autowired
    ZlwShopGoodsSpecsValueMapper zlwShopGoodsSpecsValueMapper;
    @Autowired
    ZlwShopGoodsImagesMapper zlwShopGoodsImagesMapper;
    @Autowired
    ZlwShopGoodsInventoryMapper zlwShopGoodsInventoryMapper;



    /**
     * 校验自定义商品编码
     * @param shopId 店铺id
     * @param customCode 商品编码
     * @return
     */
    @Override
    public ApiResult checkCustomInputCode(String token, String shopId, String customCode) {

        if(StringUtils.isEmpty(shopId)){
            return ApiResult.isErr(token,"店铺参数为空", null);
        }
        if(StringUtils.isEmpty(customCode)){
            return ApiResult.isErr(token,"商品编码参数为空", null);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("customCode", customCode);

        int count = zlwShopGoodsMapper.selectCountByShopIdAndCustomCode(map);
        System.out.println(count);

        ApiResult apiResult = null;

        Map<String, Object> resultMap = new HashMap<>();
        if(count < 1){
            resultMap.put("isSccuess", true);
            apiResult = ApiResult.isOk(token,"请求成功", resultMap);
        }else{
            resultMap.put("isSccuess", false);
            apiResult = ApiResult.isErr(token,"编码已存在", resultMap);
        }

        return apiResult;
    }

    /**
     * 添加商品，调用事务
     * @param zlwShopGoods 商品店铺spu表
     * @param zlwShopGoodsSku  商品店铺sku表
     * @param zlwShopGoodsSpec  店铺SKU-商品规格表
     * @param zlwShopGoodsPrice 店铺SKU-商品价格表
     * @param zlwShopGoodsSpecsName 店铺SKU-商品名称规格表
     * @param zlwShopGoodsSpecsValue 店铺SKU-商品规格值表
     * @param zlwShopGoodsImages 店铺SKU-商品图片表
     * @param zlwShopGoodsInventory 店铺SKU-商品库存表
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
    public void addGoodsTrans(ZlwShopGoods zlwShopGoods, ZlwShopGoodsSku zlwShopGoodsSku, ZlwShopGoodsSpec zlwShopGoodsSpec, ZlwShopGoodsPrice zlwShopGoodsPrice, ZlwShopGoodsSpecsName zlwShopGoodsSpecsName, ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue, ZlwShopGoodsImages zlwShopGoodsImages, ZlwShopGoodsInventory zlwShopGoodsInventory) throws Exception{
        zlwShopGoodsMapper.insert(zlwShopGoods);
        zlwShopGoodsSkuMapper.insert(zlwShopGoodsSku);
        zlwShopGoodsSpecMapper.insert(zlwShopGoodsSpec);
        zlwShopGoodsPriceMapper.insert(zlwShopGoodsPrice);
        zlwShopGoodsSpecsNameMapper.insert(zlwShopGoodsSpecsName);
        String s=null;
        s.length();//Exception
        zlwShopGoodsSpecsValueMapper.insert(zlwShopGoodsSpecsValue);
        zlwShopGoodsImagesMapper.insert(zlwShopGoodsImages);
        zlwShopGoodsInventoryMapper.insert(zlwShopGoodsInventory);
    }
}
