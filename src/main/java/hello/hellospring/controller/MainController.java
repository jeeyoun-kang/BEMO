package hello.hellospring.controller;

import hello.hellospring.auth.PrincipalDetails;
import hello.hellospring.service.PostsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import java.time.LocalDate;
import java.util.*;

import static org.apache.el.util.MessageFactory.get;

@Controller
public class MainController {
    @Autowired
    PostsService postsService;
    @RequestMapping(value="/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) {
            model.addAttribute("userinfo", principalDetails.getUsername());
        }
        model.addAttribute("Posts",postsService.findAllDesc());
        // 어제 날짜 구하기 (시스템 시계, 시스템 타임존)
        Calendar c1 = new GregorianCalendar();
        c1.add(Calendar.DATE, -1); // 오늘날짜로부터 -1

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷

        String yesterday = sdf.format(c1.getTime());
        String clientId = "TP8GiRPSMSd67q5Ioip1"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "AS7oKyBvW4"; //애플리케이션 클라이언트 시크릿값"
        String apiURL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + yesterday;    // json 결과
        //String apiURL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + text;

        Map<String, String> requestHeaders = new HashMap<>();//
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        model.addAttribute("jsonbody",responseBody);

        List<String> jsonlisttitle = new ArrayList<String>();
        List<String> jsonlistmvcode = new ArrayList<String>();
        //가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(responseBody);
        JSONObject batters = jObject.getJSONObject("boxOfficeResult");
        JSONArray batter = batters.getJSONArray("dailyBoxOfficeList");

        for(int i=0; i < batter.length();i++){
            JSONObject obj = batter.getJSONObject(i);
            String mvcode = obj.getString("movieCd"); //영화코드
            String title = obj.getString("movieNm");  //영화이름

            jsonlistmvcode.add(mvcode);
            jsonlisttitle.add(title);
        }
        String code1 = jsonlistmvcode.get(0);
        String code2 = jsonlistmvcode.get(1);
        String code3 = jsonlistmvcode.get(2);
        String code4 = jsonlistmvcode.get(3);
        String code5 = jsonlistmvcode.get(0);
        String code6 = jsonlistmvcode.get(1);
        String code7 = jsonlistmvcode.get(2);
        String code8 = jsonlistmvcode.get(3);

        model.addAttribute("code1",code1);
        model.addAttribute("code2",code2);
        model.addAttribute("code3",code3);
        model.addAttribute("code4",code4);
        model.addAttribute("code5",code5);
        model.addAttribute("code6",code6);
        model.addAttribute("code7",code7);
        model.addAttribute("code8",code8);



//        //박스오피스 4순위 영화코드
//        String code1 = URLEncoder.encode(jsonlistmvcode.get(0), StandardCharsets.UTF_8);
//        String code2 = URLEncoder.encode(jsonlistmvcode.get(1), StandardCharsets.UTF_8);
//        String code3 = URLEncoder.encode(jsonlistmvcode.get(2), StandardCharsets.UTF_8);
//        String code4 = URLEncoder.encode(jsonlistmvcode.get(3), StandardCharsets.UTF_8);

        //메인페이지에 띄울 박스오피스 4순위 타이틀과 이미지
        String text1 = URLEncoder.encode(jsonlisttitle.get(0), StandardCharsets.UTF_8);
        String text2 = URLEncoder.encode(jsonlisttitle.get(1), StandardCharsets.UTF_8);
        String text3 = URLEncoder.encode(jsonlisttitle.get(2), StandardCharsets.UTF_8);
        String text4 = URLEncoder.encode(jsonlisttitle.get(3), StandardCharsets.UTF_8);
        String text5 = URLEncoder.encode(jsonlisttitle.get(4), StandardCharsets.UTF_8);
        String text6 = URLEncoder.encode(jsonlisttitle.get(5), StandardCharsets.UTF_8);
        String text7 = URLEncoder.encode(jsonlisttitle.get(6), StandardCharsets.UTF_8);
        String text8 = URLEncoder.encode(jsonlisttitle.get(7), StandardCharsets.UTF_8);

        String imageURL1 = "https://openapi.naver.com/v1/search/movie.json?query=" + text1;
        String imageURL2 = "https://openapi.naver.com/v1/search/movie.json?query=" + text2;
        String imageURL3 = "https://openapi.naver.com/v1/search/movie.json?query=" + text3;
        String imageURL4 = "https://openapi.naver.com/v1/search/movie.json?query=" + text4;
        String imageURL5 = "https://openapi.naver.com/v1/search/movie.json?query=" + text5;
        String imageURL6 = "https://openapi.naver.com/v1/search/movie.json?query=" + text6;
        String imageURL7 = "https://openapi.naver.com/v1/search/movie.json?query=" + text7;
        String imageURL8 = "https://openapi.naver.com/v1/search/movie.json?query=" + text8;


        Map<String, String> requestHeaders2 = new HashMap<>();
        requestHeaders2.put("X-Naver-Client-Id", clientId);
        requestHeaders2.put("X-Naver-Client-Secret", clientSecret);
        String responseBody1 = get(imageURL1,requestHeaders2);
        String responseBody2 = get(imageURL2,requestHeaders2);
        String responseBody3 = get(imageURL3,requestHeaders2);
        String responseBody4 = get(imageURL4,requestHeaders2);
        String responseBody5 = get(imageURL5,requestHeaders2);
        String responseBody6 = get(imageURL6,requestHeaders2);
        String responseBody7 = get(imageURL7,requestHeaders2);
        String responseBody8 = get(imageURL8,requestHeaders2);

        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject Main1Object = new JSONObject(responseBody1);
        // 배열을 가져옵니다.
        JSONArray jArray1 = Main1Object.getJSONArray("items");

        JSONObject Main2Object = new JSONObject(responseBody2);
        // 배열을 가져옵니다.
        JSONArray jArray2 = Main2Object.getJSONArray("items");

        JSONObject Main3Object = new JSONObject(responseBody3);
        // 배열을 가져옵니다.
        JSONArray jArray3 = Main3Object.getJSONArray("items");
        JSONObject Main4Object = new JSONObject(responseBody4);
        // 배열을 가져옵니다.
        JSONArray jArray4 = Main4Object.getJSONArray("items");
        JSONObject Main5Object = new JSONObject(responseBody5);
        // 배열을 가져옵니다.
        JSONArray jArray5 = Main5Object.getJSONArray("items");

        JSONObject Main6Object = new JSONObject(responseBody6);
        // 배열을 가져옵니다.
        JSONArray jArray6 = Main6Object.getJSONArray("items");

        JSONObject Main7Object = new JSONObject(responseBody7);
        // 배열을 가져옵니다.
        JSONArray jArray7 = Main7Object.getJSONArray("items");

        JSONObject Main8Object = new JSONObject(responseBody8);
        // 배열을 가져옵니다.
        JSONArray jArray8 = Main8Object.getJSONArray("items");

        List<String> maintitle1 = new ArrayList<String>();
        List<String> mainimage1 = new ArrayList<String>();
        List<String> maintitle2 = new ArrayList<String>();
        List<String> mainimage2 = new ArrayList<String>();
        List<String> maintitle3 = new ArrayList<String>();
        List<String> mainimage3 = new ArrayList<String>();
        List<String> maintitle4 = new ArrayList<String>();
        List<String> mainimage4 = new ArrayList<String>();
        List<String> maintitle5 = new ArrayList<String>();
        List<String> mainimage5 = new ArrayList<String>();
        List<String> maintitle6 = new ArrayList<String>();
        List<String> mainimage6 = new ArrayList<String>();
        List<String> maintitle7 = new ArrayList<String>();
        List<String> mainimage7 = new ArrayList<String>();
        List<String> maintitle8 = new ArrayList<String>();
        List<String> mainimage8 = new ArrayList<String>();
        //List<String> jsonlistresult1 = new ArrayList<String>();
        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray1.length(); i++) {
            JSONObject obj = jArray1.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle1.add(title);
            mainimage1.add(image);
        }
        for (int i = 0; i < jArray2.length(); i++) {
            JSONObject obj = jArray2.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle2.add(title);
            mainimage2.add(image);
        }
        for (int i = 0; i < jArray3.length(); i++) {
            JSONObject obj = jArray3.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle3.add(title);
            mainimage3.add(image);
        }
        for (int i = 0; i < jArray4.length(); i++) {
            JSONObject obj = jArray4.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle4.add(title);
            mainimage4.add(image);
        }
        for (int i = 0; i < jArray5.length(); i++) {
            JSONObject obj = jArray5.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle5.add(title);
            mainimage5.add(image);
        }

        for (int i = 0; i < jArray6.length(); i++) {
            JSONObject obj = jArray6.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle6.add(title);
            mainimage6.add(image);
        }

        for (int i = 0; i < jArray7.length(); i++) {
            JSONObject obj = jArray7.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle7.add(title);
            mainimage7.add(image);
        }

        for (int i = 0; i < jArray8.length(); i++) {
            JSONObject obj = jArray8.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            maintitle8.add(title);
            mainimage8.add(image);
        }
        //System.out.println(jsonlisttitle.get(1));
        model.addAttribute("boxtitle1",maintitle1.get(0));
        model.addAttribute("boximage1",mainimage1.get(0));
        model.addAttribute("boxtitle2",maintitle2.get(0));
        model.addAttribute("boximage2",mainimage2.get(0));
        model.addAttribute("boxtitle3",maintitle3.get(0));
        model.addAttribute("boximage3",mainimage3.get(0));
        model.addAttribute("boxtitle4",maintitle4.get(0));
        model.addAttribute("boximage4",mainimage4.get(0));
        model.addAttribute("boxtitle5",maintitle5.get(0));
        model.addAttribute("boximage5",mainimage5.get(0));
        model.addAttribute("boxtitle6",maintitle6.get(0));
        model.addAttribute("boximage6",mainimage6.get(0));
        model.addAttribute("boxtitle7",maintitle7.get(0));
        model.addAttribute("boximage7",mainimage7.get(0));
        model.addAttribute("boxtitle8",maintitle8.get(0));
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
