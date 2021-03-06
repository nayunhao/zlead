package com.zlead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsUnitMapper;
import com.zlead.entity.goods.ZlwShopGoodsUnit;
import com.zlead.service.IZlwShopGoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
public class ZlwShopGoodsUnitServiceImpl extends ServiceImpl<ZlwShopGoodsUnitMapper, ZlwShopGoodsUnit> implements IZlwShopGoodsUnitService {
    @Autowired
    ZlwShopGoodsUnitMapper zlwShopGoodsUnitMapper;

    /**
     * 获取所有商品单位
     */
    @Override
    public Map<String, Object> getShopGoodsUnit() {

        List<ZlwShopGoodsUnit> list = zlwShopGoodsUnitMapper.selectAll();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("unit", list);
        return resultMap;
    }

    /**
     * nayunhao
     * 根据单位名称获取单位
     * @param unitName
     * @return
     */
    @Override
    public ZlwShopGoodsUnit getShopGoodsUnitByName(String unitName) {
        QueryWrapper<ZlwShopGoodsUnit> queryWrapper = new QueryWrapper<ZlwShopGoodsUnit>();
        queryWrapper.eq("sgu_name",unitName);
        ZlwShopGoodsUnit zlwShopGoodsUnit = zlwShopGoodsUnitMapper.selectOne(queryWrapper);
        return zlwShopGoodsUnit;
    }
}
