package com.zlead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsClassMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.service.IZlwPlatformGoodsClassService;
import com.zlead.utils.StringUtils;
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
public class ZlwPlatformGoodsClassServiceImpl extends ServiceImpl<ZlwPlatformGoodsClassMapper, ZlwPlatformGoodsClass> implements IZlwPlatformGoodsClassService {
    @Autowired
    ZlwPlatformGoodsClassMapper zlwPlatformGoodsClassMapper;

    /**
     * 根据分类id查询下级分类
     * @param pgcId 不传参数/0时,查询一级分类
     */
    @Override
    public Map<String, Object> getPlatFormClass(String pgcId) {

        List<ZlwPlatformGoodsClass> list = null;

        if(StringUtils.isEmpty(pgcId) || "0".equals(pgcId)){
            list = zlwPlatformGoodsClassMapper.getPlatFormClassByLevel(1);
        }else{
            list = zlwPlatformGoodsClassMapper.getPlatFormSonClass(pgcId);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("platFormClass", list);

        return resultMap;
    }

    /**
     * nayunhao
     * 根据分类名获取分类
     * @param className
     * @return
     */
    @Override
    public ZlwPlatformGoodsClass getPlatGoodsClassByName(String className) {
        QueryWrapper<ZlwPlatformGoodsClass> queryWrapper = new QueryWrapper<ZlwPlatformGoodsClass>();
        queryWrapper.eq("pgc_name",className);
        ZlwPlatformGoodsClass zlwPlatformGoodsClass = zlwPlatformGoodsClassMapper.selectOne(queryWrapper);
        return zlwPlatformGoodsClass;
    }
}
