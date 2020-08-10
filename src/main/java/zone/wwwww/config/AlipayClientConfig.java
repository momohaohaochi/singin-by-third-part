package zone.wwwww.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 10:45:21
 */
@Configuration
public class AlipayClientConfig {

    /**
     * 支付宝网关(固定)
     */
    @Value("${alipay.client.url}")
    private String URL;
    /**
     * APPID即创建应用后生成
     */
    @Value("${alipay.client.app_id}")
    private String APP_ID;
    /**
     * 开发者应用私钥，由开发者自己生成
     */
    @Value("${alipay.client.app_private_key}")
    private String APP_PRIVATE_KEY;
    /**
     * 参数返回格式，只支持json
     */
    @Value("${alipay.client.format}")
    private String FORMAT;
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF-8
     */
    @Value("${alipay.client.charset}")
    private String CHARSET;
    /**
     * 支付宝公钥，由支付宝生成
     */
    @Value("${alipay.client.alipay_public_key}")
    private String ALIPAY_PUBLIC_KEY;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
     */
    @Value("${alipay.client.sign_type}")
    private String SIGN_TYPE;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
    }
}
