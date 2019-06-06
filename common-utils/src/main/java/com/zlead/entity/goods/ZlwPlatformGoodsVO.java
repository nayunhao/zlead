package com.zlead.entity.goods;

import java.io.Serializable;
import java.util.List;

public class ZlwPlatformGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private String pgId;
    /**
     * SPU编码
     */
    private String spuCode;
    /**
     * 条形码
     */
    private String pgCodebar;
    /**
     * 商品名称
     */
    private String pgName;
    /**
     * 类别1
     */
    private String pgcId1;
    /**
     * 类别2
     */
    private String pgcId2;
    /**
     * 类别3
     */
    private String pgcId3;
    /**
     * 生产企业编号
     */
    private String pgmId;
    /**
     * 商品品牌
     */
    private String pgRemark;
    /**
     * 商品品牌
     */
    private String pgbId;
    /**
     * 图片地址
     */
    private String pgImageUrl;
    /**
     * 图片描述
     */
    private String pgImageDesc ;
    //private List<ZlwPlatform> images;
    private ZlwPlatformGoodsManufacturer zlwPlatformGoodsManufacturer;
    private ZlwPlatformGoodsBrand zlwPlatformGoodsBrand;
    private List<ZlwPlatformGoodsSpecs> zlwPlatformGoodsSpecsList;
    private List<ZlwPlatformGoodsSpecsGroup> zlwPlatformGoodsSpecsGroupList;
    private List<ZlwPlatformGoodsSpecsName> zlwPlatformGoodsSpecsNameList;
    private List<ZlwPlatformGoodsSpecsValue> zlwPlatformGoodsSpecsValueList;

}
