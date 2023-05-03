package bemo.bemo.controller;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.service.PostsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.el.util.MessageFactory.get;

@Controller
public class MainController {
    @Value("${kobis.apiURL}")
    private String KobisApiURL;
    @Value("${search.client.id}")
    private static String searchClientId;
    @Value("${search.client.secret}")
    private static String searchClientSecret;

    @Value("${search.apiURL1}")
    private static String searchApiURL1;

    @Value("${search.apiURL2}")
    private static String searchApiURL2;
    @Autowired
    private PostsService postsService;
    private final Environment env;

    public MainController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value="/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<String> profile = Arrays.asList(env.getActiveProfiles());
        List<String> realProfile = Arrays.asList("real1", "real2");
        String defaultProfile = profile.isEmpty() ? "default" : profile.get(0);
        model.addAttribute("port",profile.stream().filter(realProfile::contains).findAny().orElse(defaultProfile));
        model.addAttribute("allPort",profile);

        if (principalDetails != null) {
            model.addAttribute("userinfo", principalDetails.getUsername());
        }
        model.addAttribute("Posts",postsService.findAllDesc());
        List<String> hashtag = postsService.sortHashtags();
        model.addAttribute("hashtag", hashtag);

        //어제 기준 박스오피스 TOP8 , KOBIS Api + Naver search movie Api 호출해 원하는 데이터 파싱

        Calendar c1 = new GregorianCalendar();
        c1.add(Calendar.DATE, -1); // 오늘날짜로부터 -1

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷

        String yesterday = sdf.format(c1.getTime());
        String apiURL =  KobisApiURL+ yesterday;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();//
        requestHeaders.put("X-Naver-Client-Id", searchClientId);
        requestHeaders.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody = get(apiURL,requestHeaders);

        model.addAttribute("jsonbody",responseBody);

        List<String> jsonlistmvcode = new ArrayList<String>();
        //가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(responseBody);
        JSONObject batters = jObject.getJSONObject("boxOfficeResult");
        JSONArray batter = batters.getJSONArray("dailyBoxOfficeList");

        for(int i=0; i < batter.length();i++){
            JSONObject obj = batter.getJSONObject(i);
            String mvcode = obj.getString("movieCd"); //영화코드

            String code = jsonlistmvcode.get(i);
            model.addAttribute("code"+i,code);

            jsonlistmvcode.add(mvcode);

        }


        for (int i = 1; i < 9; i++) {
            var final_code = "code" + i;
            model.addAttribute("boxtitle" + i, api(final_code));
            String text1 = URLEncoder.encode(api(final_code), StandardCharsets.UTF_8);
            String imageURL1 = searchApiURL2 + text1;
            Map<String, String> requestHeader = new HashMap<>();
            requestHeader.put("X-Naver-Client-Id", searchClientId);
            requestHeader.put("X-Naver-Client-Secret", searchClientSecret);
            String responseBodys1 = get(imageURL1, requestHeaders);
            // 가장 큰 JSONObject를 가져옵니다.
            JSONObject Main1Object = new JSONObject(responseBodys1);
            // 배열을 가져옵니다.
            JSONArray jArray1 = Main1Object.getJSONArray("items");
            List<String> mainimage1 = new ArrayList<String>();

            for (int j = 0; j < jArray1.length(); j++) {
                JSONObject obj = jArray1.getJSONObject(j);
                String title = obj.getString("title");
                title = title.replaceAll("\\<.*?>", "");
                title = title.replaceAll("&amp;", "&");
                String image = obj.getString("image");
                if (title.equals(api(final_code))) {
                    mainimage1.add(image);
                }
            }
            model.addAttribute("boximage"+i,mainimage1.get(0));

        }

        return "main";
    }


    private static String api(String final_code){

        String apiURL1 = searchApiURL1 + final_code;
        Map<String, String> requestHeaders1 = new HashMap<>();//
        requestHeaders1.put("X-Naver-Client-Id", searchClientId);
        requestHeaders1.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody1 = get(apiURL1,requestHeaders1);
        JSONObject jObject1 = new JSONObject(responseBody1);
        JSONObject batters1 = jObject1.getJSONObject("movieInfoResult");
        JSONObject bat1 = batters1.getJSONObject("movieInfo");
        String movieNm1 = bat1.getString("movieNm");



        return movieNm1;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
