package good.invoke;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import good.invoke.callback.CommonFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "zlead-common-service", fallback = CommonFallback.class)
public interface CommonInvoke {

    @PostMapping("/common/getGoodsId")
    public String getGoodsId();

    /**
     * nayunhao
     * 上传图片
     * @param files
     * @param apiRequest
     * @return
     */
    @PostMapping("/common/uploadImage")
    public ApiResult uploadImage(@RequestParam("imageFiles") MultipartFile[] files, @RequestBody ApiRequest apiRequest);
}
