package bemo.bemo.auth;

import java.util.Map;

public interface OAuth2UserInfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getNickname();
    String getBirthday();
    String getCellphone();
    String getBirthYear();
}
