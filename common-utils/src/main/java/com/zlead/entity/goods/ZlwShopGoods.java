package com.zlead.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class ZlwShopGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String sgId;

    /**
     * SPU编码
     */
    private String spuCode;

    /**
     * 自定义编码
     */
    private String sgCustomCode;

    /**
     * 商品名称
     */
    private String sgName;

    /**
     * 店铺编号
     */
    private String shopId;

    /**
     * 分类1
     */
    private String sgClass1;

    /**
     * 分类2
     */
    private String sgClass2;
    /**
     * nayunhao
     *平台分类1
     *
     */
    private String pgClass1;
    /**
     * nayunhao
     *平台分类2
     *
     */
    private String pgClass2;
    /**
     * nayunhao
     *平台分类3
     *
     */
    private String pgClass3;
    /**
     * 规格
     */
    private String sgSpecs;

    /**
     * 商品所有图片文件夹链接
     */
    private String sgImagesUrl;

    /**
     * 发布渠道 1：门店  2：网络
     */
    private String sgSalesTarget;

    /**
     * 状态： -1：全部，1：销售中，2：下架中，3：违规下架
     */
    private Integer sgSpuStatus;

    /**
     * 创建/更新时间
     */
    private Long sgOptionTime;

    /**
     * 单位
     */
    private String sgUnit;

    /**
     * 型号
     */
    private String sgModel;

    /**
     * 商品描述
     */
    private String sgDesc;

    /**
     * 1：锁定 2：解锁
     */
    private Integer sgIsLock;

    /**
     * 1：删除 2：没删除
     */
    private Integer sgIsDelete;

    /**
     * 平台品牌id
     */
    private String pgbId;
    /**
     * 店铺品牌id
     *
     */
    private String sgbId;
    /**
     * 备注
     */
    private String sgRemark;


}
