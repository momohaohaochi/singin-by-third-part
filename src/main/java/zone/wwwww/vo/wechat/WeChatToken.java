package zone.wwwww.vo.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author wangshuo_dq
 * @data 2020-08-05 18:04:41
 */
public class WeChatToken {

    /**
     * 接口调用凭证
     */
    @JsonAlias({"access_token"})
    private String accessToken;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    @JsonAlias({"expires_in"})
    private String expiresIn;
    /**
     * 用户刷新access_token
     */
    @JsonAlias({"refresh_token"})
    private String refreshToken;
    /**
     * 授权用户唯一标识
     */
    @JsonAlias({"openid"})
    private String openId;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
    /**
     * 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
     */
    @JsonAlias({"unionid"})
    private String unionId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "WeChatToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }
}
