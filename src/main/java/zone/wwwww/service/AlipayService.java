package zone.wwwww.service;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

/**
 * @author wangshuo_dq
 * @data 2020-08-10 10:59:35
 */
public interface AlipayService {

    /**
     * 获取包含用户accessToken和userid等
     *
     * @param authCode
     * @return
     */
    AlipaySystemOauthTokenResponse getAlipaySystemOauthTokenResponse(String authCode);

    /**
     * 通过accessToken获取用户的公开信息
     *
     * @param alipaySystemOauthTokenResponse
     * @return
     */
    AlipayUserInfoShareResponse getAlipayUserInfoShareResponse(AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse);
}
