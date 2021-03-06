package com.zlead.entity.goods;

    import java.io.Serializable;
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
    public class ZlwShopGoodsUnit implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String sguId;

            /**
            * 单位名称
            */
    private String sguName;

            /**
            * 备注
            */
    private String sguRemark;


}
