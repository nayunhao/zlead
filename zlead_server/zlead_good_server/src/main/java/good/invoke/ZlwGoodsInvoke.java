package good.invoke;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import good.invoke.callback.ZlwGoodsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "zlead-goods-service", fallback = ZlwGoodsFallback.class)
public interface ZlwGoodsInvoke {

    @PostMapping("/ZlwGoods/getGoodsListByStatus")
    public List<ZlwShopGoodsSku> getGoodsListByStatus(@RequestBody Map data);

    /**
     * 添加分类
     * @param zlwShopGoodsClass
     * @return
     */
    @PostMapping("/ZlwGoods/addShopGoodsClass")
    public boolean addShopGoodsClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass);

    /**
     * 添加单个商品
     * @param data
     * @return
     *
     */
    @PostMapping("/ZlwGoods/addGoodsByOne")
    public boolean addGoodsByOne(@RequestBody Map<String,Object> data);
    /**
     * 根据分类名称查询分类
     * @param className
     * @return
     */
    @PostMapping("/ZlwGoods/getClassByName")
    public ZlwShopGoodsClass getClassByName(@RequestBody String className);

    /**
     * nayunhao
     * 根据名称查询品牌
     * @param brandName
     * @return
     */
    @PostMapping("/ZlwGoods/getShopGoodsBrandByName")
    public ZlwShopGoodsBrand getShopGoodsBrandByName(@RequestBody String brandName);

    /**
     * nayunhao
     * 根据名称查询单位
     * @param unitName
     * @return
     */
    @PostMapping("/ZlwGoods/getShopGoodsUnitByName")
    public ZlwShopGoodsUnit getShopGoodsUnitByName(@RequestBody String unitName);
    /**
     * nayunhao
     *通过规格查询商品sku
     * @param data
     * @return
     */
    @PostMapping("/ZlwGoods/getShopGoodsBySpecs")
    public List<ZlwShopGoodsSku> getShopGoodsBySpecs(@RequestBody Map data);

    /**
     *nayunhao
     * 通过规格名称信息查询规格值数量
     * @param data
     * @return
     */
    @PostMapping("/ZlwGoods/countBySpecValueBySpecName")
    public int countBySpecValueBySpecName(@RequestBody Map<String,String> data);
    @PostMapping("/ZlwGoods/importGoods")
    public ApiResult importGoods(@RequestBody ApiRequest apiRequest);
    @PostMapping("/ZlwGoods/addShopClass")
    public boolean addShopClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass);

    @PostMapping("/ZlwGoods/editGoodsStatus")
    boolean editGoodsStatus(@RequestBody Map map);

    @PostMapping("/ZlwGoods/batchSetGoodsClass")
    boolean setBatchGoodsClass(@RequestBody Map map);

    @PostMapping("/ZlwGoods/editShopGoodsClass")
    boolean editShopGoodsClass(@RequestBody Map data);

    @PostMapping("/ZlwGoods/removeShopGoodsClass/{sgcId}")
    boolean removeShopGoodsClass(@PathVariable("sgcId") String sgcId);
}
