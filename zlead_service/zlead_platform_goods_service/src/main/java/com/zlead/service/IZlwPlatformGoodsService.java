package com.zlead.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoods;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */

public interface IZlwPlatformGoodsService extends IService<ZlwPlatformGoods> {




    List<ZlwPlatformGoods> selectpage(Page<ZlwPlatformGoods> page);

}
