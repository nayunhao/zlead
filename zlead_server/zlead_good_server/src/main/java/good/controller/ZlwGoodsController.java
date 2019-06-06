package good.controller;

import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.utils.JsonMapper;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import com.zlead.utils.StringUtil;
import com.zlead.utils.StringUtils;
import good.invoke.CommonInvoke;
import good.invoke.ZlwGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 单条添加商品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value="/addGoodsByOne",method = RequestMethod.POST)
    public ApiResult addGoodsByOne(@RequestBody ApiRequest apiRequest){
        Map data =  apiRequest.getData();
        String shopId = (String) data.get("shopid");
        String sgStatus = (String)data.get("goodStatus");
        String userid = (String)data.get("userid");
        String goodsDetail = (String)data.get("goodsDetail");
        JsonMapper jsonMapper = JsonMapper.getInstance();
        Map goodsDetailData = jsonMapper.fromJson(goodsDetail,Map.class);
        List<Map> goodsSpDataArray = jsonMapper.fromJson((String)goodsDetailData.get("goodsSpDetail"),List.class);
        return null;
    }
}
