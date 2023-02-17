package bemo.bemo.auth;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getNickname() {
        return attributes.get("name").toString();
    }

    @Override
    public String getBirthday() {
        return null;
    }

    @Override
    public String getCellphone() {
        return null;
    }

    @Override
    public String getBirthYear() {
        return null;
    }
}