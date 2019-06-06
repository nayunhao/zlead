package com.zlead.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsClassService;
import com.zlead.service.IZlwShopGoodsService;
import com.zlead.service.IZlwShopGoodsSkuService;
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
     * 添加商品，事务控制
     * @param data
     * @return
     */
    @PostMapping("/addGoodsByOne")
    public boolean addGoodsByOne(@RequestBody Map<String,Object> data){
        ZlwShopGoods zlwShopGoods = (ZlwShopGoods)data.get("zlwShopGoods");
        ZlwShopGoodsSku zlwShopGoodsSku = (ZlwShopGoodsSku)data.get("zlwShopGoodsSku");
        ZlwShopGoodsSpec zlwShopGoodsSpec = (ZlwShopGoodsSpec)data.get("zlwShopGoodsSpec");
        ZlwShopGoodsPrice zlwShopGoodsPrice = (ZlwShopGoodsPrice)data.get("zlwShopGoodsPrice");
        ZlwShopGoodsSpecsName zlwShopGoodsSpecsName = (ZlwShopGoodsSpecsName)data.get("zlwShopGoodsSpecsName");
        ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue = (ZlwShopGoodsSpecsValue)data.get("zlwShopGoodsSpecsValue");
        ZlwShopGoodsImages ZlwShopGoodsImages = (ZlwShopGoodsImages)data.get("ZlwShopGoodsImages");
        ZlwShopGoodsInventory zlwShopGoodsInventory = (ZlwShopGoodsInventory)data.get("zlwShopGoodsInventory");

        try{
            iZlwShopGoodsService.addGoodsTrans(zlwShopGoods,zlwShopGoodsSku,zlwShopGoodsSpec,zlwShopGoodsPrice,zlwShopGoodsSpecsName,zlwShopGoodsSpecsValue,ZlwShopGoodsImages,zlwShopGoodsInventory);
        }catch(Exception e){
           return false;
        }
        return true;
    }

    /**
     * 根据分类名查询分类
     * @param className
     * @return
     */
    @PostMapping("/getClassByName")
    public ZlwShopGoodsClass getClassByName(@RequestBody String className){
        ZlwShopGoodsClass zlwShopGoodsClass = iZlwShopGoodsClassService.selectOneByName(className);
        return zlwShopGoodsClass;
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
