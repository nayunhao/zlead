package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.*;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import com.zlead.entity.goods.ZlwShopGoodsSpec;
import com.zlead.service.IZlwShopGoodsSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class ZlwShopGoodsSkuServiceImpl extends ServiceImpl<ZlwShopGoodsSkuMapper, ZlwShopGoodsSku> implements IZlwShopGoodsSkuService {

    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsSkuServiceImpl.class);
    @Override
    public List<ZlwShopGoodsSku> getGoodsListByStatus(ZlwShopGoodsSku zlwShopGoodsSku) {

        List<ZlwShopGoodsSku> goodsSkuList = zlwShopGoodsSkuMapper.getGoodsListByStatus(zlwShopGoodsSku);
        logger.info(""+goodsSkuList.size());

        goodsSkuList.forEach(
                goodsSku ->{
                    //根据sguId查询spu名称
                    String sgName = zlwShopGoodsMapper.getShopGoods(goodsSku.getSguId()).getSgName();
                    goodsSku.setSgName(sgName);
                    //获取库存
                    String inventoryValue = zlwShopGoodsInventoryMapper.getInventoryValue(goodsSku.getSgiId());

                    //获取分类名称
                    String className1=zlwShopGoodsClassMapper.getClassName(goodsSku.getSgClass1());
                    String className2=zlwShopGoodsClassMapper.getClassName(goodsSku.getSgClass2());
                    //获取sku规格信息
                    ZlwShopGoodsSpec shopGoodsSpecs =zlwShopGoodsSpecMapper.getGoodsSpec(goodsSku.getSgCode());

                    goodsSku.setGoodSpecName(zlwShopGoodsSpecsNameMapper.getShopGoodsSpecsName(shopGoodsSpecs.getSgsnId()));
                    goodsSku.setGoodSpecValue(zlwShopGoodsSpecsValueMapper.getGoodsSpecValue(shopGoodsSpecs.getSgsvId()));
                    goodsSku.setSgClassName1(className1);
                    goodsSku.setSgClassName2(className2);
                    goodsSku.setInventoryValue(inventoryValue);
                }
        );
        return goodsSkuList ;
    }

    @Autowired
    private ZlwShopGoodsInventoryMapper zlwShopGoodsInventoryMapper;

    @Autowired
    private ZlwShopGoodsSpecsNameMapper zlwShopGoodsSpecsNameMapper;

    @Autowired
    private ZlwShopGoodsSpecsValueMapper zlwShopGoodsSpecsValueMapper;

    @Autowired
    private ZlwShopGoodsSpecMapper zlwShopGoodsSpecMapper;

    @Autowired
    private ZlwShopGoodsSkuMapper zlwShopGoodsSkuMapper;

    @Autowired
    private ZlwShopGoodsMapper zlwShopGoodsMapper;

    @Autowired
    private ZlwShopGoodsClassMapper zlwShopGoodsClassMapper;
    @Override
    public boolean batchEditGoodsStatus(String goodsIds, String type) {
        try {
            String[] goodsArr = goodsIds.split(",");
            for (String goodsId:goodsArr){
                ZlwShopGoodsSku model = new ZlwShopGoodsSku();
                model.setSgkId(goodsId);
                if("delete".equals(type)){//删除
                    model.setSgIsDelete(1);
                }else if("under".equals(type)){//下架
                    model.setSgStatus(2);
                }else if("up".equals(type)){//上架
                    model.setSgStatus(1);
                }else if ("top".equals(type)){//置顶
                    model.setSgSort(2L);
                    model.setSgSortTime(new Date()); //置顶时间
                }else if ("cancelTop".equals(type)){//取消置顶
                    model.setSgSort(1L);
                }
                this.baseMapper.updateById(model);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean batchSetGoodsClass(Map<String,Object> data) {
        try {
            String goodsIds  = (String)data.get("sgGoodsIds");
            Integer sgClassId1 = (Integer)data.get("sgClassId1");
            Integer sgClassId2 = (Integer)data.get("sgClassId2");
            String[] goodsArr = goodsIds.split(",");
            ZlwShopGoodsSku model = null;
            for (String goodsId:goodsArr){
                model = new ZlwShopGoodsSku();
                model.setSgkId(goodsId);
                model.setSgClass1(sgClassId1.longValue());
                model.setSgClass2(sgClassId2.longValue());
                this.baseMapper.updateById(model);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
