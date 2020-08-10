package zone.wwwww.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.wwwww.service.AlipayService;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 10:59:50
 */
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayClient alipayClient;

    @Override
    public AlipaySystemOauthTokenResponse getAlipaySystemOauthTokenResponse(String authCode) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public AlipayUserInfoShareResponse getAlipayUserInfoShareResponse(AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        String token = alipaySystemOauthTokenResponse.getAccessToken();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, token);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
}
