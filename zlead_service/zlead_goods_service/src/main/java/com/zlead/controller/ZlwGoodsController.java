package com.zlead.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwGoodsController {

    @Autowired
    private IZlwShopGoodsClassService iZlwShopGoodsClassService;
    @Autowired
    IZlwShopGoodsService iZlwShopGoodsService;


    @Autowired
    private IZlwShopGoodsSkuService zlwShopGoodsSkuService;
    @Autowired
    private IZlwShopGoodsBrandService iZlwShopGoodsBrandService;
    @Autowired
    private IZlwShopGoodsUnitService iZlwShopGoodsUnitService;

    /* *
     * @Author GuoYunFei
     * @Description //批量修改商品状态
     * @Date 下午 3:55 2019/6/5 0005
     * @Param [data]
     * @return boolean
     */
    @PostMapping("/editGoodsStatus")
    public boolean editGoodsStatus(@RequestBody Map data){
        String goodsIds = (String) data.get("sgGoodsIds");
        String type = (String) data.get("sgType");
        boolean result = zlwShopGoodsSkuService.batchEditGoodsStatus(goodsIds, type);
        return result;
    }

    /* *
     * @Author GuoYunFei
     * @Description //批量修改商品分类
     * @Date 下午 3:55 2019/6/5 0005
     * @Param [data]
     * @return boolean
     */
    @PostMapping("/batchSetGoodsClass")
    public boolean setGoodsClass(@RequestBody Map data){
        boolean result =  zlwShopGoodsSkuService.batchSetGoodsClass(data);
        return result;
    }

    /* *
     * @Author GuoYunFei
     * @Description //修改商品分类
     * @Date 下午 6:56 2019/6/5 0005
     * @Param
     * @return
     */
    @PostMapping("/editShopGoodsClass")
    public boolean editShopGoodsClass(@RequestBody Map data) {
        boolean result = iZlwShopGoodsClassService.editShopGoodsClass(data);
        return result;
    }

    /* *
     * @Author GuoYunFei
     * @Description //删除店铺分类
     * @Date 下午 7:07 2019/6/5 0005
     * @Param
     * @return
     */
    @PostMapping("/removeShopGoodsClass/{sgcId}")
    public boolean removeShopGoodsClass(@PathVariable("sgcId") String sgcId) {
        boolean result = iZlwShopGoodsClassService.removeShopGoodsClass(sgcId);
        return result;
    }

    @Autowired
    private IZlwShopGoodsSkuService iZlwShopGoodsSkuService;
    /**
     * nayunhao
     * 添加店铺分类
     * @param zlwShopGoodsClass
     * @return
     */
    @PostMapping("/addShopGoodsClass")
    public boolean addShopGoodsClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass){
        boolean result = iZlwShopGoodsClassService.save(zlwShopGoodsClass);
        return result;
    }

    /**
     * nayunhao
     * 添加商品，事务控制
     * @param data
     * @return
     */
    @PostMapping("/addGoodsByOne")
    public boolean addGoodsByOne(@RequestBody Map<String,Object> data){
        ObjectMapper objectMapper = new ObjectMapper();
        ZlwShopGoods zlwShopGoods = objectMapper.convertValue(data.get("zlwShopGoods"),ZlwShopGoods.class);
        List<ZlwShopGoodsSku> zlwShopGoodsSkus = objectMapper.convertValue(data.get("zlwShopGoodsSku"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsSku.class));
        List<ZlwShopGoodsSpec> zlwShopGoodsSpecs = objectMapper.convertValue(data.get("zlwShopGoodsSpec"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsSpec.class));
        List<ZlwShopGoodsPrice> zlwShopGoodsPrices = objectMapper.convertValue(data.get("zlwShopGoodsPrice"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsPrice.class));
        List<ZlwShopGoodsSpecsName> zlwShopGoodsSpecsNames = objectMapper.convertValue(data.get("zlwShopGoodsSpecsName"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsSpecsName.class));
        List<ZlwShopGoodsSpecsValue> zlwShopGoodsSpecsValues = objectMapper.convertValue(data.get("zlwShopGoodsSpecsValue"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsSpecsValue.class));
        List<ZlwShopGoodsImages> ZlwShopGoodsImages = objectMapper.convertValue(data.get("zlwShopGoodsImages"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsImages.class));
        List<ZlwShopGoodsInventory> zlwShopGoodsInventorys = objectMapper.convertValue(data.get("zlwShopGoodsInventory"),objectMapper.getTypeFactory().constructParametricType(List.class, ZlwShopGoodsInventory.class));
        try{
            iZlwShopGoodsService.addGoodsTrans(zlwShopGoods,zlwShopGoodsSkus,zlwShopGoodsSpecs,zlwShopGoodsPrices,zlwShopGoodsSpecsNames,zlwShopGoodsSpecsValues,ZlwShopGoodsImages,zlwShopGoodsInventorys);
        }catch(Exception e){
            e.printStackTrace();
           return false;
        }
        return true;
    }

    /**
     * nayunhao
     * 根据分类名查询分类
     * @param className
     * @return
     */
    @PostMapping("/getClassByName")
    public ZlwShopGoodsClass getClassByName(@RequestBody String className){
        ZlwShopGoodsClass zlwShopGoodsClass = iZlwShopGoodsClassService.selectOneByName(className);
        return zlwShopGoodsClass;
    }

    /**
     *nayunhao
     * 根据名称查询品牌
     * @param brandName
     * @return
     */
    @PostMapping("/getShopGoodsBrandByName")
    public ZlwShopGoodsBrand getShopGoodsBrandByName(@RequestBody String brandName){
        ZlwShopGoodsBrand zlwShopGoodsBrand = iZlwShopGoodsBrandService.getShopGoodsBrandByName(brandName);
        return zlwShopGoodsBrand;
    }

    /**
     * nayunhao
     * 根据名称查询单位
     * @param unitName
     * @return
     */
    @PostMapping("/getShopGoodsUnitByName")
    public ZlwShopGoodsUnit getShopGoodsUnitByName(@RequestBody String unitName){
        ZlwShopGoodsUnit zlwShopGoodsUnit = iZlwShopGoodsUnitService.getShopGoodsUnitByName(unitName);
        return zlwShopGoodsUnit;
    }
    /**
     * nayunhao
     *通过规格查询商品sku
     * @param data
     * @return
     */
    @PostMapping("/getShopGoodsBySpecs")
    public List<ZlwShopGoodsSku> getShopGoodsBySpecs(@RequestBody  Map data){
        String sgSpecs = (String)data.get("specs");
        String sgStatus = (String)data.get("sgStatus");
        QueryWrapper<ZlwShopGoodsSku> queryWrapper = new QueryWrapper<ZlwShopGoodsSku>();
        queryWrapper.eq("sg_specs",sgSpecs);
        queryWrapper.eq("sg_status",sgStatus);
        List<ZlwShopGoodsSku> list = zlwShopGoodsSkuService.list(queryWrapper);
        return list;
    }
    /**
     *nayunhao
     * 通过规格名称信息查询规格值数量
     * @param data
     * @return
     */
    @PostMapping("/countBySpecValueBySpecName")
    public int countBySpecValueBySpecName(@RequestBody Map<String,String> data){
        int count = iZlwShopGoodsSkuService.countBySpecValueBySpecName(data);
        return count;
    }
    @PostMapping("/getGoodsListByStatus")
    public List<ZlwShopGoodsSku> getGoodsListByStatus(@RequestBody Map data){
        Integer sgStatus = (Integer) data.get("sgStatus");
        String shopId = (String) data.get("shopId");
        ZlwShopGoodsSku zlwShopGoodsSku = new ZlwShopGoodsSku();
        zlwShopGoodsSku.setSgStatus(sgStatus);
        zlwShopGoodsSku.setShopId(shopId);
        return  iZlwShopGoodsSkuService.getGoodsListByStatus(zlwShopGoodsSku);
    }


    /**
     * 校验自定义商品编码
     * shopId 店铺id
     * customCode 商品编码
     * @return
     */
    @PostMapping("/checkCustomInputCode")
    public ApiResult checkCustomInputCode(@RequestBody ApiRequest apiRequest){
        String token = apiRequest.getToken();
        ApiResult apiResult;
        try {
            String shopId = (String)apiRequest.getData().get("shopId");
            String customCode = (String)apiRequest.getData().get("customCode");
            apiResult = iZlwShopGoodsService.checkCustomInputCode(token, shopId, customCode);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = ApiResult.isErr(token,"请求失败", null);
        }
        return apiResult;
    }



}
