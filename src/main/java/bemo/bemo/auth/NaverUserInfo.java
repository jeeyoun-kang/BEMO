package bemo.bemo.auth;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes; //OAuth2User.getAttributes();
    private Map<String, Object> attributesResponse;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.attributesResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getNickname() {
        return attributesResponse.get("nickname").toString();
    }

    @Override
    public String getBirthday() {
        return attributesResponse.get("birthday").toString();
    }

    @Override
    public String getCellphone() {
        return attributesResponse.get("mobile").toString();
    }

    @Override
    public String getBirthYear() {return attributesResponse.get("birthyear").toString();}
}