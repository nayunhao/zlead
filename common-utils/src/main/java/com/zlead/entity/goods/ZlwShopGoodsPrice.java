package com.zlead.entity.goods;

    import java.io.Serializable;
    import java.math.BigDecimal;

    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author zlw
* @since 2019-05-31
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwShopGoodsPrice implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String sgpId;

            /**
            * 进货价格
            */
    private BigDecimal sgpPrivatePrice;

    /**
     * 成本价格
     *
     */

    private BigDecimal sgpCostPrice;

            /**
            * 线下售价
            */
    private BigDecimal sgpPublicPrice;

            /**
            * 网销售价
            */
    private BigDecimal sgpPublicEprice;

            /**
            * 参考售价
            */
    private BigDecimal sgpReferencePrice;

            /**
            * 整批售价
            */
    private BigDecimal sgpWholePrice;

            /**
            * 店铺id
            */
    private String shopId;

            /**
            * 备注
            */
    private String sgsnRemark;


}
