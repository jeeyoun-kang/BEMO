package hello.hellospring.auth;

import java.util.Map;

public interface OAuth2UserInfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getBirthday();
    String getCellphone();
}
