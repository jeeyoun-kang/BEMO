package bemo.bemo.controller;

import bemo.bemo.service.PostsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;


@Controller
public class MovieDetailController {

    @Value("${search.client.id}")
    private String searchClientId;
    @Value("${search.client.secret}")
    private String searchClientSecret;
    @Value("${search.apiURL1}")
    private String searchApiURL1;
    @Value("${search.apiURL2}")
    private String searchApiURL2;
    @Value("${data.client.id}")
    private String dataclientId;
    @Value("${data.client.secret}")
    private String dataclientSecret;
    @Value("${data.apiURL}")
    private String dataApiURL;
    @Autowired
    PostsService postsService;
    @RequestMapping("/moviedetail/{detail}/{code}")
    public String moviedetail(Model model, @PathVariable String detail, @PathVariable String code) {

        model.addAttribute("Posts",postsService.findByMvtitle(Collections.singletonList(detail))); // Service 접근
        model.addAttribute("movie_title",detail);
        model.addAttribute("code",code);



        String apiURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=" + code;    // json 결과

        Map<String, String> requestHeaders2 = new HashMap<>();//
        requestHeaders2.put("X-Naver-Client-Id", searchClientId);
        requestHeaders2.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody = get(apiURL,requestHeaders2);

        List<String> jsonlistopen = new ArrayList<String>();
        List<String> jsonlistgen = new ArrayList<String>();
        List<String> jsonlistdi = new ArrayList<String>();
        List<String> jsonlistactor = new ArrayList<String>();


        JSONObject jObject = new JSONObject(responseBody);
        JSONObject batters = jObject.getJSONObject("movieInfoResult");
        JSONObject batters2 = batters.getJSONObject("movieInfo");
        String openDt = batters2.getString("openDt");
        JSONArray gebatter = batters2.getJSONArray("genres");
        JSONArray dibatter = batters2.getJSONArray("directors");
        JSONArray acbatter = batters2.getJSONArray("actors");

        model.addAttribute("openDt",openDt);

        for(int i=0; i < gebatter.length();i++){
            JSONObject obj = gebatter.getJSONObject(i);
            String genreNm = obj.getString("genreNm"); //장르

            jsonlistgen.add(genreNm);

        }
        String genreNm = jsonlistgen.get(0);
        model.addAttribute("genreNm",genreNm);

        for(int i=0; i < dibatter.length();i++){
            JSONObject obj = dibatter.getJSONObject(i);
            String peopleNm = obj.getString("peopleNm"); //장르

            jsonlistdi.add(peopleNm);

        }
        String peopleNm = jsonlistdi.get(0);
        model.addAttribute("peopleNm",peopleNm);

        for(int i=0; i < acbatter.length();i++){
            JSONObject obj = acbatter.getJSONObject(i);
            String acpeopleNm = obj.getString("peopleNm"); //장르

            jsonlistactor.add(acpeopleNm);

        }
        String actorstr = String.join(",", jsonlistactor);
        model.addAttribute("actorNm",actorstr);


        //영화 이미지 가져오기

        String text = null;
        text = URLEncoder.encode(detail, StandardCharsets.UTF_8);
        System.out.println(text);
        String image_apiURL = searchApiURL2 + text;    // json 결과


        Map<String, String> requestHeaders3 = new HashMap<>();
        requestHeaders3.put("X-Naver-Client-Id", searchClientId);
        requestHeaders3.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody3 = get(image_apiURL,requestHeaders3);
        model.addAttribute("jsonbody",responseBody3);
        System.out.println(responseBody3);

        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject4 = new JSONObject(responseBody3);
        // 배열을 가져옵니다.
        JSONArray jArray3 = jObject4.getJSONArray("items");


        List<String> jsonlistimage = new ArrayList<String>();


        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray3.length(); i++) {
            JSONObject obj = jArray3.getJSONObject(i);
            String image = obj.getString("image");
            image=image.replaceAll("\\\\","");
            jsonlistimage.add(image);
            System.out.println();
        }


        model.addAttribute("image1",jsonlistimage.get(0));

        //네이버 트렌드 데이터


        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", dataclientId);
        requestHeaders.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders.put("Content-Type", "application/json");

        LocalDate now = LocalDate.now(); //현재 날짜
        LocalDate lastmonth = now.minusMonths(1); //현재 날짜 기준 한달 전
        LocalDate lastseven = now.minusWeeks(1); //현재 날짜 기준 일주일 전



        String requestBody = "{\"startDate\":\""+lastseven+"\"," +
                "\"endDate\":\""+now+"\"," +
                "\"timeUnit\":\"date\"," +
                "\"keywordGroups\":[{\"groupName\":\"영화\"," + "\"keywords\":[\""+detail+"\"]}]," +

                "\"device\":\"pc\"," +
                "\"ages\":[\"1\"]," +
                "\"gender\":\"\"}";

        String responseBody2 = post(apiUrl, requestHeaders, requestBody);
        System.out.println(responseBody2);
        JSONObject jObject2 = new JSONObject(responseBody2);
        JSONArray batter = jObject2.getJSONArray("results");
        JSONObject inner_json = batter.getJSONObject(0);

        JSONArray jObject3 = inner_json.getJSONArray("data");
        System.out.println(jObject3);

        List<String> jsonperiod = new ArrayList<String>();
        List<String> jsonratio = new ArrayList<String>();

        for(int i=0; i < jObject3.length();i++){
            JSONObject obj = jObject3.getJSONObject(i);
            String period = obj.getString("period"); //영화코드
            Integer ratio = obj.getInt("ratio"); //영

            jsonperiod.add(period);
            jsonratio.add(String.valueOf(ratio)); //소숫점 제거

        }
        if(jsonperiod.size()==7) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);

            String period6 = jsonperiod.get(5);
            String ratio6 = jsonratio.get(5);
            model.addAttribute("period6",period6);
            model.addAttribute("ratio6",ratio6);

            String period7 = jsonperiod.get(6);
            String ratio7 = jsonratio.get(6);
            model.addAttribute("period7",period7);
            model.addAttribute("ratio7",ratio7);
        }
        if(jsonperiod.size()==6) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);

            String period6 = jsonperiod.get(5);
            String ratio6 = jsonratio.get(5);
            model.addAttribute("period6",period6);
            model.addAttribute("ratio6",ratio6);


        }
        if(jsonperiod.size()==5) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);


        }
        if(jsonperiod.size()==4) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);


        }
        if(jsonperiod.size()==3) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);


        }
        if(jsonperiod.size()==2) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

        }
        if(jsonperiod.size()==1) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

        }



        return "moviedetail";


    }

    @RequestMapping("/moviedetail/{detail}")
    public String moviedetail(Model model, @PathVariable String detail)
    {
        model.addAttribute("Posts",postsService.findByMvtitle(Collections.singletonList(detail))); // Service 접근
        model.addAttribute("movie_title",detail);



        String text = null;
        text = URLEncoder.encode(detail, StandardCharsets.UTF_8);
        System.out.println(text);
        String apiURL = searchApiURL2 + text;    // json 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", searchClientId);
        requestHeaders.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody = get(apiURL,requestHeaders);
        model.addAttribute("jsonbody",responseBody);
        System.out.println(responseBody);

        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(responseBody);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("items");

        List<String> jsonlisttitle = new ArrayList<String>();
        List<String> jsonlistimage = new ArrayList<String>();
        List<String> jsonlistdi = new ArrayList<String>();
        List<String> jsonlistac = new ArrayList<String>();

        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            String title = obj.getString("title");
            title = title.replaceAll("\\<.*?>","");
            title = title.replaceAll("&amp;","&");
            String image = obj.getString("image");
            image=image.replaceAll("\\\\","");
            String director = obj.getString("director");
            director=director.replaceAll("\\|","");
            String actor = obj.getString("actor");
            actor=actor.replaceAll("\\|",",");
            actor=actor.replaceAll("\\<.*?>","");

            jsonlisttitle.add(title);
            jsonlistimage.add(image);
            jsonlistdi.add(director);
            jsonlistac.add(actor);
            System.out.println();
        }

        String actorstr = String.join(",", jsonlistac.get(0));
        if (actorstr.isEmpty()){
            model.addAttribute("actorNm","");
        }
        else{
            actorstr = actorstr.substring(0, actorstr.length() - 1);
            model.addAttribute("actorNm",actorstr);

        }
        model.addAttribute("peopleNm",jsonlistdi.get(0));
        model.addAttribute("movie_title",jsonlisttitle.get(0));
        model.addAttribute("image1",jsonlistimage.get(0));

        //네이버 트렌드 데이터


        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders2 = new HashMap<>();
        requestHeaders2.put("X-Naver-Client-Id", dataclientId);
        requestHeaders2.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders2.put("Content-Type", "application/json");

        LocalDate now = LocalDate.now(); //현재 날짜
        LocalDate lastmonth = now.minusMonths(1); //현재 날짜 기준 한달 전
        LocalDate lastseven = now.minusWeeks(1); //현재 날짜 기준 일주일 전


        String requestBody = "{\"startDate\":\""+lastseven+"\"," +
                "\"endDate\":\""+now+"\"," +
                "\"timeUnit\":\"date\"," +
                "\"keywordGroups\":[{\"groupName\":\"영화\"," + "\"keywords\":[\""+detail+"\"]}]," +

                "\"device\":\"pc\"," +
                "\"ages\":[\"1\"]," +
                "\"gender\":\"\"}";

        String responseBody2 = post(apiUrl, requestHeaders2, requestBody);

        JSONObject jObject2 = new JSONObject(responseBody2);

        JSONArray batter = jObject2.getJSONArray("results");
        JSONObject inner_json = batter.getJSONObject(0);

        JSONArray jObject3 = inner_json.getJSONArray("data");


        List<String> jsonperiod = new ArrayList<String>();
        List<String> jsonratio = new ArrayList<String>();

        for(int i=0; i < jObject3.length();i++){
            JSONObject obj = jObject3.getJSONObject(i);
            String period = obj.getString("period"); //영화코드
            Integer ratio = obj.getInt("ratio"); //영

            jsonperiod.add(period);
            jsonratio.add(String.valueOf(ratio)); //소숫점 제거

        }
        if(jsonperiod.size()==7) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);

            String period6 = jsonperiod.get(5);
            String ratio6 = jsonratio.get(5);
            model.addAttribute("period6",period6);
            model.addAttribute("ratio6",ratio6);

            String period7 = jsonperiod.get(6);
            String ratio7 = jsonratio.get(6);
            model.addAttribute("period7",period7);
            model.addAttribute("ratio7",ratio7);
        }
        if(jsonperiod.size()==6) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);

            String period6 = jsonperiod.get(5);
            String ratio6 = jsonratio.get(5);
            model.addAttribute("period6",period6);
            model.addAttribute("ratio6",ratio6);


        }
        if(jsonperiod.size()==5) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);

            String period5 = jsonperiod.get(4);
            String ratio5= jsonratio.get(4);
            model.addAttribute("period5",period5);
            model.addAttribute("ratio5",ratio5);


        }
        if(jsonperiod.size()==4) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);

            String period4 = jsonperiod.get(3);
            String ratio4 = jsonratio.get(3);
            model.addAttribute("period4",period4);
            model.addAttribute("ratio4",ratio4);


        }
        if(jsonperiod.size()==3) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

            String period3 = jsonperiod.get(2);
            String ratio3 = jsonratio.get(2);
            model.addAttribute("period3",period3);
            model.addAttribute("ratio3",ratio3);


        }
        if(jsonperiod.size()==2) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

            String period2 = jsonperiod.get(1);
            String ratio2 = jsonratio.get(1);
            model.addAttribute("period2",period2);
            model.addAttribute("ratio2",ratio2);

        }
        if(jsonperiod.size()==1) {
            String period1 = jsonperiod.get(0);
            String ratio1 = jsonratio.get(0);
            model.addAttribute("period1",period1);
            model.addAttribute("ratio1",ratio1);

        }



        return "moviedetail2";


    }
    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
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