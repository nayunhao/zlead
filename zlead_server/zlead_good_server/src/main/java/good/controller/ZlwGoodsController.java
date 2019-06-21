package good.controller;

import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.utils.JsonMapper;
import com.zlead.utils.StringUtil;
import com.zlead.utils.StringUtils;
import good.invoke.CommonInvoke;
import good.invoke.ZlwGoodsInvoke;
import good.invoke.ZlwPlatformGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * goods controller
 *
 *
 */

@RestController
@RequestMapping("/goods")
@Slf4j
public class ZlwGoodsController extends BaseController {

    @Autowired
    private CommonInvoke commonInvoke;
    @Autowired
    private ZlwGoodsInvoke zlwGoodsInvoke;
    @Autowired
    private ZlwPlatformGoodsInvoke zlwPlatformGoodsInvoke;

    /**
     * 批量更新状态
     * @param apiRequest
     * @return
     */
    @PostMapping("/batchEditGoodsStatus")
    public ApiResult editGoodsStatus(@RequestBody ApiRequest apiRequest){
        ApiResult result = new ApiResult();
        Map data = apiRequest.getData();
        String goodsIds = (String) data.get("sgGoodsIds");
        String type =  (String) data.get("sgType");
        if(StringUtils.isEmpty(goodsIds) || StringUtils.isEmpty(type)){
            result.setStatus(ApiResult.CODE_ERR);
            result.setMessage("缺少必填参数!");
            result.setStatus(500);
        }
        //TODO invoke
        return result;
    }

    /**
     * 批量设置分类
     * @param apiRequest
     * @return
     */
    @PostMapping("/batchSetGoodsClass")
    public ApiResult setGoodsClass(@RequestBody ApiRequest apiRequest) {
        ApiResult result = new ApiResult();
        Map data = apiRequest.getData();
        String goodsIds = (String) data.get("sgGoodsIds");
        String sgClassId1 = (String) data.get("sgClassId1");
        String sgClassId2 = (String) data.get("sgClassId2");
        if(StringUtils.isEmpty(goodsIds) || StringUtils.isEmpty(sgClassId1) ){
            result.setStatus(ApiResult.CODE_ERR);
            result.setMessage("缺少必填参数!");
            result.setStatus(500);
        }
        //TODO invoke
        return result;
    }

    /**
     * nayunhao
     * 添加分类
     * @param apiRequest
     * @return
     */
    @RequestMapping(value="/addShopGoodsClass",method = RequestMethod.POST)
    public ApiResult addShopGoodsClass(@RequestBody ApiRequest apiRequest){
        Map data = apiRequest.getData();
        String shopId = (String)data.get("shopId");
        String className = (String)data.get("className");
        String parentId = (String)data.get("parentId");
        String sgcId = commonInvoke.getGoodsId();

        Map errorData = new HashMap();
        Map successData = new HashMap();
        errorData.put("isSuccess",false);
        successData.put("isSuccess",true);
        if(StringUtil.isNull(shopId)||StringUtil.isNull(className)||StringUtil.isNull(parentId)){
            return ApiResult.isErrNoToken("必填参数为空或未填",errorData);
        }
        if(sgcId==null||"0000".equals(sgcId)){
            log.error("生成分类id失败");
            return ApiResult.isErrNoToken("id生成有误",errorData);
        }

        ZlwShopGoodsClass zlwShopGoodsClass = new ZlwShopGoodsClass();
        zlwShopGoodsClass.setSgcId(sgcId);
        zlwShopGoodsClass.setShopId(shopId);
        zlwShopGoodsClass.setSgcName(className);
        zlwShopGoodsClass.setSgcParentId(parentId);
        if("0".equals(parentId)){
            zlwShopGoodsClass.setSgcLevel(1);
        }else{
            zlwShopGoodsClass.setSgcLevel(2);
        }
        boolean result = zlwGoodsInvoke.addShopGoodsClass(zlwShopGoodsClass);
        if(result){
            return ApiResult.isOkNoToken("添加成功",successData);
        }else{
            return ApiResult.isErrNoToken("添加失败",errorData);
        }
    }


    /**
     * nayunhao
     * 单条添加商品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value="/addGoodsByOne",method = RequestMethod.POST)
    public ApiResult addGoodsByOne(ApiRequest apiRequest,@RequestParam(value = "imgFiles")MultipartFile[] imgFiles){
        Map errorResult = new HashMap();
        Map successResult = new HashMap();
        errorResult.put("isSuccess",false);
        successResult.put("isSuccess",true);
        Map data =  apiRequest.getData();

        //spu-------------------------------------
        //店铺spu表主键id
        String sguId = commonInvoke.getGoodsId();
        //店铺spu表spu编码，用于外键关联sku
        String spuCode = commonInvoke.getGoodsId();
        //店铺id
        String shopId = (String) data.get("shopid");
        //商品状态1:销售中,2：下架,3：违规
        String sgStatus = (String)data.get("goodStatus");
        //用户id
        String userid = (String)data.get("userid");
        //平台分类
        String pgClassName1 = (String)data.get("platformClass1");
        String pgClassName2 = (String)data.get("platformClass2");
        String pgClassName3 = (String)data.get("platformClass3");
        String pgClassId1 = getClassIdByName(pgClassName1,new ZlwPlatformGoodsClass());
        if("".equals(pgClassId1)){
            return ApiResult.isErrNoToken("商品平台一级分类未填",errorResult);
        }
        String pgClassId2 = getClassIdByName(pgClassName2,new ZlwPlatformGoodsClass());
        String pgClassId3 = getClassIdByName(pgClassName3,new ZlwPlatformGoodsClass());
        //店铺分类
        String sgClassName1 = (String)data.get("shopClass1");
        String sgclassName2 = (String)data.get("shopClass2");
        String sgClassId1 = getClassIdByName(sgClassName1,new ZlwShopGoodsClass());
        String sgClassId2 = getClassIdByName(sgclassName2,new ZlwShopGoodsClass());

        //品牌
        String brandName = (String)data.get("brandName");
        ZlwShopGoodsBrand zlwShopGoodsBrand = zlwGoodsInvoke.getShopGoodsBrandByName(brandName);
        String sgbId = zlwShopGoodsBrand.getSgbId();
        //商品名称
        String sgName = (String)data.get("goodsName");
        //单位
        String unitName = (String)data.get("goodsUnit");
        ZlwShopGoodsUnit zlwShopGoodsUnit = zlwGoodsInvoke.getShopGoodsUnitByName(unitName);
        String sgUnit = zlwShopGoodsUnit.getSguId();
        //型号
        String sgModel = (String)data.get("model");
        //发布渠道
        String sgSalesTarget = (String)data.get("publishChannel");
        //自定义编码
        String sgCustomCode = (String)data.get("goodsCustomizeCode");
        ZlwShopGoods zlwShopGoods = new ZlwShopGoods();
        String sgId = commonInvoke.getGoodsId();
        zlwShopGoods.setSgId(sgId);
        zlwShopGoods.setSpuCode(spuCode);
        zlwShopGoods.setShopId(shopId);
        zlwShopGoods.setSgSpuStatus(Integer.parseInt(sgStatus));
        zlwShopGoods.setPgClass1(pgClassId1);
        zlwShopGoods.setPgClass2(pgClassId2);
        zlwShopGoods.setPgClass3(pgClassId3);
        zlwShopGoods.setSgClass1(sgClassId1);
        zlwShopGoods.setSgClass2(sgClassId2);
        zlwShopGoods.setSgbId(sgbId);
        zlwShopGoods.setSgUnit(sgUnit);
        zlwShopGoods.setSgModel(sgModel);
        zlwShopGoods.setSgName(sgName);
        zlwShopGoods.setSgCustomCode(sgCustomCode);

        //spu-------------------------------------
        //sku-------------------------------------
        //处理sku
        List<Map> goodsSkuArray = (List<Map>) data.get("goodsSku");
        List<ZlwShopGoodsSpec> zlwShopGoodsSpecs = new ArrayList<>();
        List<ZlwShopGoodsSpecsName> zlwShopGoodsSpecsNames = new ArrayList<>();
        List<ZlwShopGoodsSpecsValue> zlwShopGoodsSpecsValues = new ArrayList<>();
        List<ZlwShopGoodsSku> zlwShopGoodsSkus = new ArrayList<>();
        List<ZlwShopGoodsInventory> zlwShopGoodsInventorys = new ArrayList<>();
        List<ZlwShopGoodsPrice> zlwShopGoodsPrices = new ArrayList<>();
        for (Map goodsSkuMap:goodsSkuArray) {
            //sku表
            ZlwShopGoodsSku zlwShopGoodsSku = new ZlwShopGoodsSku();
            String sgkId = commonInvoke.getGoodsId();
            String sgCode = commonInvoke.getGoodsId();
            zlwShopGoodsSku.setSgkId(sgkId);
            zlwShopGoodsSku.setSguId(commonInvoke.getGoodsId());
            zlwShopGoodsSku.setSgCode(sgCode);
            zlwShopGoodsSku.setSpuCode(spuCode);
            zlwShopGoodsSku.setSgCustomCode(sgCustomCode);
            zlwShopGoodsSku.setShopId(shopId);
            zlwShopGoodsSku.setSgClass1(Long.parseLong(sgClassId1));
            zlwShopGoodsSku.setSgClass2(Long.parseLong(sgClassId2));
            zlwShopGoodsSku.setSgStatus(Integer.parseInt(sgStatus));
            String sgpId = commonInvoke.getGoodsId(); //价格Id
            String sgiId = commonInvoke.getGoodsId(); //库存Id
            zlwShopGoodsSku.setSgpId(sgpId);
            zlwShopGoodsSku.setSgiId(sgiId);
            String sgPackageList = (String)goodsSkuMap.get("packageList");
            zlwShopGoodsSku.setSgPackageList(sgPackageList);

            //sku去重
            String goodsSpDataStr = String.valueOf(goodsSkuMap.get("goodsSpDetail"));
            Map param = new HashMap();
            param.put("specs",goodsSpDataStr);
            param.put("sgStatus",sgStatus);
            List<ZlwShopGoodsSku> skuList = zlwGoodsInvoke.getShopGoodsBySpecs(param);
            if(skuList!=null){
                if(skuList.size()>0){
                    continue;
                }
            }
            //商品规格
            zlwShopGoodsSku.setSgSpecs(goodsSpDataStr);
            //价格编号id
            zlwShopGoodsSkus.add(zlwShopGoodsSku);
            List<Map> goodsSpMaps = (List<Map>) goodsSkuMap.get("goodsSpDetail");
            for (Map goodsSpMap:goodsSpMaps) {
                String specsName = (String) goodsSpMap.get("specsName");
                String specsValue = (String)goodsSpMap.get("specsValue");
                //添加规格前必须校验规格明细，进行去重
                //规格表中商品分类编号为该商品的最低分类
                String sgcId = "";
                if("".equals(sgClassId2)){
                    sgcId = sgClassId1;
                }else{
                    sgcId=sgClassId2;
                }
                Map<String,String> map = new HashMap<>();
                map.put("sgcId",sgcId);
                map.put("sgsnName",specsName);
                map.put("sgsnValue",specsValue);
                int count = zlwGoodsInvoke.countBySpecValueBySpecName(map);
                if(count>0){
                    continue;
                }
                //添加规格
                ZlwShopGoodsSpec zlwShopGoodsSpec = new ZlwShopGoodsSpec();
                String sgsId = commonInvoke.getGoodsId();
                String sgsnId = commonInvoke.getGoodsId();
                String sgsvId = commonInvoke.getGoodsId();
                zlwShopGoodsSpec.setSgsId(sgsId);
                zlwShopGoodsSpec.setSgCode(sgCode);
                zlwShopGoodsSpec.setSgsnId(sgsnId);
                zlwShopGoodsSpec.setSgsvId(sgsvId);
                zlwShopGoodsSpec.setShopId(shopId);
                zlwShopGoodsSpecs.add(zlwShopGoodsSpec);

                //添加规格名称
                ZlwShopGoodsSpecsName zlwShopGoodsSpecsName = new ZlwShopGoodsSpecsName();
                zlwShopGoodsSpecsName.setSgsnId(sgsnId);
                zlwShopGoodsSpecsName.setSgcId(sgcId);
                zlwShopGoodsSpecsName.setSgsnName(specsName);
                zlwShopGoodsSpecsName.setShopId(shopId);
                zlwShopGoodsSpecsNames.add(zlwShopGoodsSpecsName);

                //添加规格值

                ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue = new ZlwShopGoodsSpecsValue();
                zlwShopGoodsSpecsValue.setSgsvId(sgsvId);
                zlwShopGoodsSpecsValue.setSgsnId(sgsnId);
                zlwShopGoodsSpecsValue.setSgsnValue(specsValue);
                zlwShopGoodsSpecsValues.add(zlwShopGoodsSpecsValue);
            }

            //库存
            String inventory = (String)goodsSkuMap.get("inventory");
            //价格
            String sgpPrivatePrice = (String)goodsSkuMap.get("sgpPrivatePrice"); //进货价格
            String sgpCostPrice = (String)goodsSkuMap.get("sgpCostPrice"); //成本价格
            String sgpPublicPrice = (String)goodsSkuMap.get("sgpPublicPrice");//线下售价
            String sgpPublicEprice = (String)goodsSkuMap.get("sgpPublicEprice"); //网销售价
            String sgpReferencePrice = (String)goodsSkuMap.get("sgpReferencePrice"); //参考售价
            String sgpWholePrice = (String)goodsSkuMap.get("sgpWholePrice");//整批售价


            //库存
            ZlwShopGoodsInventory zlwShopGoodsInventory = new ZlwShopGoodsInventory();
            zlwShopGoodsInventory.setSgiId(sgiId);
            zlwShopGoodsInventory.setSgiValue(inventory);
            zlwShopGoodsInventorys.add(zlwShopGoodsInventory);
            //价格
            ZlwShopGoodsPrice zlwShopGoodsPrice = new ZlwShopGoodsPrice();
            zlwShopGoodsPrice.setSgpId(sgpId);
            zlwShopGoodsPrice.setSgpCostPrice(new BigDecimal(sgpCostPrice));
            zlwShopGoodsPrice.setSgpPrivatePrice(new BigDecimal(sgpPrivatePrice));
            zlwShopGoodsPrice.setSgpPublicEprice(new BigDecimal(sgpPublicEprice));
            zlwShopGoodsPrice.setSgpPublicPrice(new BigDecimal(sgpPublicPrice));
            zlwShopGoodsPrice.setSgpReferencePrice(new BigDecimal(sgpReferencePrice));
            zlwShopGoodsPrice.setSgpWholePrice(new BigDecimal(sgpWholePrice));
            zlwShopGoodsPrices.add(zlwShopGoodsPrice);
        }
        ApiRequest imgReq = new ApiRequest();
        Map imgData = new HashMap();
        imgData.put("shopId",shopId);
        imgData.put("userId",userid);
        imgReq.setData(imgData);
        ApiResult imgRes = commonInvoke.uploadImage(imgFiles,imgReq);
        Map imgResData = (Map) imgRes.getData();
        List<ZlwShopGoodsImages> zlwShopGoodsImages = new ArrayList<>();
        //封面图片
        List<Map> coverImgArray = (List<Map>) imgResData.get("coverImg");
        for (Map coverImg:coverImgArray) {
           String imgUrl = (String)coverImg.get("imgUrl"); //原图片url
           String thumbnailUrl = (String)coverImg.get("thumbnailUrl"); //缩略图url
            String sort = String.valueOf(coverImg.get("sort"));
            String desc = (String)coverImg.get("desc");

            ZlwShopGoodsImages zlwShopGoodsImage = new ZlwShopGoodsImages();
            zlwShopGoodsImage.setSgkId(sgId);
            zlwShopGoodsImage.setSgImageId(commonInvoke.getGoodsId());
            zlwShopGoodsImage.setSgImageName(imgUrl+","+thumbnailUrl);
            zlwShopGoodsImage.setSgImageSort(Integer.parseInt(sort));
            if("jpg".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(1);
            }else if("bmp".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(2);
            }else if("gif".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(3);
            }
            zlwShopGoodsImage.setSgApplicationType(1);
            zlwShopGoodsImage.setSgImageDesc(desc);
            zlwShopGoodsImages.add(zlwShopGoodsImage);
        }
        //图片描述
        List<Map> imgArray = (List<Map>) imgResData.get("images");
        for (Map img:imgArray) {
            String imgUrl = (String)img.get("imgUrl"); //原图片url
            String thumbnailUrl = (String)img.get("thumbnailUrl"); //缩略图url
            String sort = String.valueOf(img.get("sort"));
            String desc = (String)img.get("desc");

            ZlwShopGoodsImages zlwShopGoodsImage = new ZlwShopGoodsImages();
            zlwShopGoodsImage.setSgkId(sgId);
            zlwShopGoodsImage.setSgImageId(commonInvoke.getGoodsId());
            zlwShopGoodsImage.setSgImageName(imgUrl+","+thumbnailUrl);
            zlwShopGoodsImage.setSgImageSort(Integer.parseInt(sort));
            if("jpg".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(1);
            }else if("bmp".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(2);
            }else if("gif".equals(imgUrl.substring(imgUrl.lastIndexOf(".")+1))){
                zlwShopGoodsImage.setSgImageType(3);
            }
            zlwShopGoodsImage.setSgApplicationType(2);
            zlwShopGoodsImage.setSgImageDesc(desc);
            zlwShopGoodsImages.add(zlwShopGoodsImage);

        }
        //sku-------------------------------------
        //入库
        Map<String,Object> sqlParam = new HashMap<String,Object>();
        sqlParam.put("zlwShopGoods",zlwShopGoods);
        sqlParam.put("zlwShopGoodsSku",zlwShopGoodsSkus);
        sqlParam.put("zlwShopGoodsSpec",zlwShopGoodsSpecs);
        sqlParam.put("zlwShopGoodsPrice",zlwShopGoodsPrices);
        sqlParam.put("zlwShopGoodsSpecsName",zlwShopGoodsSpecsNames);
        sqlParam.put("zlwShopGoodsSpecsValue",zlwShopGoodsSpecsValues);
        sqlParam.put("zlwShopGoodsImages",zlwShopGoodsImages);
        sqlParam.put("zlwShopGoodsInventory",zlwShopGoodsInventorys);
        boolean res = zlwGoodsInvoke.addGoodsByOne(sqlParam);

        if(res){
            return ApiResult.isOkNoToken("添加成功",successResult);
        }else{
           return ApiResult.isErrNoToken("服务错误，校验sku失败",errorResult);
        }

    }

    /**
     * nayunhao
     * 根据分类名称获得平台分类id
     * @param name
     * @return
     */
    private String getClassIdByName(String name,Object o){

       if(o instanceof ZlwShopGoodsClass){   //店铺商品分类
           if(!StringUtil.isNull(name)){
               ZlwShopGoodsClass zlwShopGoodsClass = zlwGoodsInvoke.getClassByName(name);
               if(zlwShopGoodsClass!=null){
                   return zlwShopGoodsClass.getSgcId();
               }
           }
       }else if(o instanceof ZlwPlatformGoodsClass){ //平台商品分类
           if(!StringUtil.isNull(name)){
               ZlwPlatformGoodsClass zlwPlatformGoodsClass = zlwPlatformGoodsInvoke.getPlatGoodsClassByName(name);
               if(zlwPlatformGoodsClass!=null){
                   return zlwPlatformGoodsClass.getPgcId();
               }
           }
        }
        return "";
    }
}
