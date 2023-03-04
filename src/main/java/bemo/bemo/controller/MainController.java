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

@Controller
public class MainController {
    @Value("${kobis.apiURL}")
    private String KobisApiURL;
    @Value("${search.client.id}")
    private String searchClientId;
    @Value("${search.client.secret}")
    private String searchClientSecret;

    @Value("${search.apiURL1}")
    private String searchApiURL1;

    @Value("${search.apiURL2}")
    private String searchApiURL2;
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
        model.addAttribute("hashtag", hashtag);        // 어제 날짜 구하기 (시스템 시계, 시스템 타임존)
        Calendar c1 = new GregorianCalendar();
        c1.add(Calendar.DATE, -1); // 오늘날짜로부터 -1

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷

        String yesterday = sdf.format(c1.getTime());
        //애플리케이션 클라이언트 시크릿값"
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

            jsonlistmvcode.add(mvcode);

        }
        String code1 = jsonlistmvcode.get(0);
        String code2 = jsonlistmvcode.get(1);
        String code3 = jsonlistmvcode.get(2);
        String code4 = jsonlistmvcode.get(3);
        String code5 = jsonlistmvcode.get(4);
        String code6 = jsonlistmvcode.get(5);
        String code7 = jsonlistmvcode.get(6);
        String code8 = jsonlistmvcode.get(7);

        model.addAttribute("code1",code1);
        model.addAttribute("code2",code2);
        model.addAttribute("code3",code3);
        model.addAttribute("code4",code4);
        model.addAttribute("code5",code5);
        model.addAttribute("code6",code6);
        model.addAttribute("code7",code7);
        model.addAttribute("code8",code8);




        String apiURL1 = searchApiURL1 + code1;
        Map<String, String> requestHeaders1 = new HashMap<>();//
        requestHeaders1.put("X-Naver-Client-Id", searchClientId);
        requestHeaders1.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody1 = get(apiURL1,requestHeaders1);
        JSONObject jObject1 = new JSONObject(responseBody1);
        JSONObject batters1 = jObject1.getJSONObject("movieInfoResult");
        JSONObject bat1 = batters1.getJSONObject("movieInfo");
        String movieNm1 = bat1.getString("movieNm");
        model.addAttribute("boxtitle1",movieNm1);
        String apiURL2 = searchApiURL1 + code2;
        Map<String, String> requestHeaders2 = new HashMap<>();
        requestHeaders2.put("X-Naver-Client-Id", searchClientId);
        requestHeaders2.put("X-Naver-Client-Secret", searchClientSecret);

        String responseBody2 = get(apiURL2,requestHeaders2);
        JSONObject jObject2 = new JSONObject(responseBody2);
        JSONObject batters2 = jObject2.getJSONObject("movieInfoResult");
        JSONObject bat2 = batters2.getJSONObject("movieInfo");
        String movieNm2 = bat2.getString("movieNm");
        model.addAttribute("boxtitle2",movieNm2);

        String apiURL3= searchApiURL1 + code3;
        Map<String, String> requestHeaders3 = new HashMap<>();//
        requestHeaders3.put("X-Naver-Client-Id", searchClientId);
        requestHeaders3.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody3 = get(apiURL3,requestHeaders3);
        JSONObject jObject3 = new JSONObject(responseBody3);
        JSONObject batters3 = jObject3.getJSONObject("movieInfoResult");
        JSONObject bat3 = batters3.getJSONObject("movieInfo");
        String movieNm3 = bat3.getString("movieNm");
        model.addAttribute("boxtitle3",movieNm3);

        String apiURL4 = searchApiURL1 + code4;
        Map<String, String> requestHeaders4 = new HashMap<>();//
        requestHeaders4.put("X-Naver-Client-Id", searchClientId);
        requestHeaders4.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody4 = get(apiURL4,requestHeaders4);
        JSONObject jObject4 = new JSONObject(responseBody4);
        JSONObject batters4 = jObject4.getJSONObject("movieInfoResult");
        JSONObject bat4 = batters4.getJSONObject("movieInfo");
        String movieNm4 = bat4.getString("movieNm");
        model.addAttribute("boxtitle4",movieNm4);

        String apiURL5 = searchApiURL1 + code5;
        Map<String, String> requestHeaders5 = new HashMap<>();//
        requestHeaders5.put("X-Naver-Client-Id", searchClientId);
        requestHeaders5.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody5 = get(apiURL5,requestHeaders5);
        JSONObject jObject5 = new JSONObject(responseBody5);
        JSONObject batters5 = jObject5.getJSONObject("movieInfoResult");
        JSONObject bat5 = batters5.getJSONObject("movieInfo");
        String movieNm5 = bat5.getString("movieNm");
        model.addAttribute("boxtitle5",movieNm5);

        String apiURL6 = searchApiURL1 + code6;
        Map<String, String> requestHeaders6 = new HashMap<>();//
        requestHeaders6.put("X-Naver-Client-Id", searchClientId);
        requestHeaders6.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody6 = get(apiURL6,requestHeaders6);
        JSONObject jObject6 = new JSONObject(responseBody6);
        JSONObject batters6 = jObject6.getJSONObject("movieInfoResult");
        JSONObject bat6 = batters6.getJSONObject("movieInfo");
        String movieNm6 = bat6.getString("movieNm");
        model.addAttribute("boxtitle6",movieNm6);

        String apiURL7 = searchApiURL1 + code7;
        Map<String, String> requestHeaders7 = new HashMap<>();//
        requestHeaders7.put("X-Naver-Client-Id", searchClientId);
        requestHeaders7.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody7 = get(apiURL7,requestHeaders7);
        JSONObject jObject7 = new JSONObject(responseBody7);
        JSONObject batters7 = jObject7.getJSONObject("movieInfoResult");
        JSONObject bat7 = batters7.getJSONObject("movieInfo");
        String movieNm7 = bat7.getString("movieNm");
        model.addAttribute("boxtitle7",movieNm7);

        String apiURL8 = searchApiURL1 + code8;
        Map<String, String> requestHeaders8 = new HashMap<>();//
        requestHeaders8.put("X-Naver-Client-Id", searchClientId);
        requestHeaders8.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody8 = get(apiURL8,requestHeaders8);
        JSONObject jObject8 = new JSONObject(responseBody8);
        JSONObject batters8 = jObject8.getJSONObject("movieInfoResult");
        JSONObject bat8 = batters8.getJSONObject("movieInfo");
        String movieNm8 = bat8.getString("movieNm");
        model.addAttribute("boxtitle8",movieNm8);

        String text1 = URLEncoder.encode(movieNm1, StandardCharsets.UTF_8);
        String text2 = URLEncoder.encode(movieNm2, StandardCharsets.UTF_8);
        String text3 = URLEncoder.encode(movieNm3, StandardCharsets.UTF_8);
        String text4 = URLEncoder.encode(movieNm4, StandardCharsets.UTF_8);
        String text5 = URLEncoder.encode(movieNm5, StandardCharsets.UTF_8);
        String text6 = URLEncoder.encode(movieNm6, StandardCharsets.UTF_8);
        String text7 = URLEncoder.encode(movieNm7, StandardCharsets.UTF_8);
        String text8 = URLEncoder.encode(movieNm8, StandardCharsets.UTF_8);

        String imageURL1 = searchApiURL2 + text1;
        String imageURL2 = searchApiURL2 + text2;
        String imageURL3 = searchApiURL2 + text3;
        String imageURL4 = searchApiURL2 + text4;
        String imageURL5 = searchApiURL2 + text5;
        String imageURL6 = searchApiURL2 + text6;
        String imageURL7 = searchApiURL2 + text7;
        String imageURL8 = searchApiURL2 + text8;

        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("X-Naver-Client-Id", searchClientId);
        requestHeader.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBodys1 = get(imageURL1,requestHeaders);
        String responseBodys2 = get(imageURL2,requestHeaders);
        String responseBodys3 = get(imageURL3,requestHeaders);
        String responseBodys4 = get(imageURL4,requestHeaders);
        String responseBodys5 = get(imageURL5,requestHeaders);
        String responseBodys6 = get(imageURL6,requestHeaders);
        String responseBodys7 = get(imageURL7,requestHeaders);
        String responseBodys8 = get(imageURL8,requestHeaders);

        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject Main1Object = new JSONObject(responseBodys1);
        // 배열을 가져옵니다.
        JSONArray jArray1 = Main1Object.getJSONArray("items");

        JSONObject Main2Object = new JSONObject(responseBodys2);
        // 배열을 가져옵니다.
        JSONArray jArray2 = Main2Object.getJSONArray("items");

        JSONObject Main3Object = new JSONObject(responseBodys3);
        // 배열을 가져옵니다.
        JSONArray jArray3 = Main3Object.getJSONArray("items");
        JSONObject Main4Object = new JSONObject(responseBodys4);
        // 배열을 가져옵니다.
        JSONArray jArray4 = Main4Object.getJSONArray("items");
        JSONObject Main5Object = new JSONObject(responseBodys5);
        // 배열을 가져옵니다.
        JSONArray jArray5 = Main5Object.getJSONArray("items");

        JSONObject Main6Object = new JSONObject(responseBodys6);
        // 배열을 가져옵니다.
        JSONArray jArray6 = Main6Object.getJSONArray("items");

        JSONObject Main7Object = new JSONObject(responseBodys7);
        // 배열을 가져옵니다.
        JSONArray jArray7 = Main7Object.getJSONArray("items");

        JSONObject Main8Object = new JSONObject(responseBodys8);
        // 배열을 가져옵니다.
        JSONArray jArray8 = Main8Object.getJSONArray("items");

        List<String> mainimage1 = new ArrayList<String>();
        List<String> mainimage2 = new ArrayList<String>();
        List<String> mainimage3 = new ArrayList<String>();
        List<String> mainimage4 = new ArrayList<String>();
        List<String> mainimage5 = new ArrayList<String>();
        List<String> mainimage6 = new ArrayList<String>();
        List<String> mainimage7 = new ArrayList<String>();
        List<String> mainimage8 = new ArrayList<String>();
        //List<String> jsonlistresult1 = new ArrayList<String>();
        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray1.length(); i++) {
            JSONObject obj = jArray1.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm1)){
                mainimage1.add(image);
            }
        }
        for (int i = 0; i < jArray2.length(); i++) {
            JSONObject obj = jArray2.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm2)){
                mainimage2.add(image);
            }        }
        for (int i = 0; i < jArray3.length(); i++) {
            JSONObject obj = jArray3.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm3)){
                mainimage3.add(image);
            }
        }
        for (int i = 0; i < jArray4.length(); i++) {
            JSONObject obj = jArray4.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm4)){
                mainimage4.add(image);
            }
        }
        for (int i = 0; i < jArray5.length(); i++) {
            JSONObject obj = jArray5.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm5)){
                mainimage5.add(image);
            }
        }

        for (int i = 0; i < jArray6.length(); i++) {
            JSONObject obj = jArray6.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm6)){
                mainimage6.add(image);
            }
        }

        for (int i = 0; i < jArray7.length(); i++) {
            JSONObject obj = jArray7.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm7)){
                mainimage7.add(image);
            }
        }

        for (int i = 0; i < jArray8.length(); i++) {
            JSONObject obj = jArray8.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            if (title.equals(movieNm8)){
                mainimage8.add(image);
            }
        }
        model.addAttribute("boximage1",mainimage1.get(0));
        model.addAttribute("boximage2",mainimage2.get(0));
        model.addAttribute("boximage3",mainimage3.get(0));
        model.addAttribute("boximage4",mainimage4.get(0));
        model.addAttribute("boximage5",mainimage5.get(0));
        model.addAttribute("boximage6",mainimage6.get(0));
        model.addAttribute("boximage7",mainimage7.get(0));
        model.addAttribute("boximage8",mainimage8.get(0));

        return "main";
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
