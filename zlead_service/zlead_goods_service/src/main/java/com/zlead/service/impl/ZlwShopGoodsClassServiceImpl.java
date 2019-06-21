package com.zlead.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsClassMapper;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import com.zlead.service.IZlwShopGoodsClassService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
public class ZlwShopGoodsClassServiceImpl extends ServiceImpl<ZlwShopGoodsClassMapper, ZlwShopGoodsClass> implements IZlwShopGoodsClassService {

    @Transactional(rollbackFor=Exception.class)
    @Override
    public  List<ZlwShopGoodsClass> selectListAll(String sgcParentId,String shopId){
        Map<String,Object>map=new HashMap<>();
        map.put("sgcParentId",sgcParentId);
        map.put("shopId",shopId);
        return zlwShopGoodsClassMapper.selectListAll(map);
    }

    @Override
    public ZlwShopGoodsClass selectOneByName(String className) {
        QueryWrapper<ZlwShopGoodsClass> queryWrapper = new QueryWrapper<ZlwShopGoodsClass>();
        queryWrapper.eq("sgc_name",className);
        ZlwShopGoodsClass zlwShopGoodsClass = zlwShopGoodsClassMapper.selectOne(queryWrapper);
        return  zlwShopGoodsClass;
    }

    @Autowired
    private  ZlwShopGoodsClassMapper zlwShopGoodsClassMapper;

//    @Override
//    public List<ZlwShopGoodsClass> selectByMap( Map<String, Object> map){
//        return zlwShopGoodsClassMapper.selectByMap(map);
//    }

    @Override
    public boolean editShopGoodsClass(Map<String, Object> map) {
        try {
            String sgcId = (String) map.get("sgcId");
            String sgcName = (String) map.get("sgcName");
            ZlwShopGoodsClass zlwShopGoodsClass = new ZlwShopGoodsClass();
            zlwShopGoodsClass.setSgcId(sgcId);
            zlwShopGoodsClass.setSgcName(sgcName);
            int i = this.baseMapper.updateById(zlwShopGoodsClass);
            if(i<1){
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeShopGoodsClass(String sgcId) {
        try {
            ZlwShopGoodsClass zlwShopGoodsClass = this.baseMapper.selectById(sgcId);
            Integer level = zlwShopGoodsClass.getSgcLevel();
            zlwShopGoodsClass.setSgcIsDelete(1);//删除
            if(1 == level){  //如果是一级分类  删除下面的二级分类
                zlwShopGoodsClassMapper.updateByParentId(zlwShopGoodsClass);
            }
            int i = this.baseMapper.updateById(zlwShopGoodsClass);
            if(i<1){
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
