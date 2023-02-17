package bemo.bemo.auth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public KakaoUserInfo(Map<String, Object> attributes) {

        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "Kakao";
    }

    @Override
    public String getNickname() {
        return attributesProfile.get("nickname").toString();
    }

    @Override
    public String getBirthday() {
        String birthday = attributesAccount.get("birthday").toString();
        String month = birthday.substring(0, 2);
        String day = birthday.substring(2);
        birthday = month+"-"+day;
        return birthday;
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