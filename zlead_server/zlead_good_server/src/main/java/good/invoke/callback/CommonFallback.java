package good.invoke.callback;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import good.invoke.CommonInvoke;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonFallback implements CommonInvoke {



    /**
     * 熔断机制，如果出现问题，返回00000
     * @return
     */
    @Override
    public String getGoodsId() {
        return "00000";
    }

    @Override
    public ApiResult uploadImage(MultipartFile[] files, ApiRequest request) {
        return null;
    }
}
