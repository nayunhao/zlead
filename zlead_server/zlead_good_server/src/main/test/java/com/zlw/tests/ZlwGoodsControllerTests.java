package com.zlw.tests;

import com.zlead.domain.ApiRequest;
import good.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ZlwGoodsControllerTests {

    @Test
    public void testGoods(){
        RestTemplate restTemplate = new RestTemplate();
        ApiRequest request = new ApiRequest();
        HashMap<String, String> map = new HashMap<>();
        map.put("shopId","22222");
        map.put("className","原子弹");
        map.put("parentId","0");
        request.setPlatform("pc");
        request.setData(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.0.224:7001/goods/addGoodsByOne",request,String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }
}
