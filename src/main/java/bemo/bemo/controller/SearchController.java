package bemo.bemo.controller;

import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import bemo.bemo.service.PostsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;


@Controller
public class SearchController {
    @Autowired
    PostsService postsService;

    @Value("${search.client.id}")
    private String searchClientId;

    @Value("${search.client.secret}")
    private String searchClientSecret;

    @Value("${search.apiURL2}")
    private String searchapiURL2;
    @GetMapping("search")
    public String ticket(Model model) {
        model.addAttribute("data", "search");
        return "search";//
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("data", "search");
        return "test";
    }
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("data", "search");
        return "index";
    }
    @RequestMapping("/send")
    public String send(String moviename,Model model){

        if (moviename.isEmpty()) {
            model.addAttribute("msg", "검색어를 다시 입력해주세요.");
            model.addAttribute("url", "/");
            return "alert";
        }

        if(moviename.contains("#")){
            moviename = moviename.substring(1, moviename.length());
            System.out.println("해시태그"+moviename);
            List<String> mvtitles = postsService.findMvtitleByHashtag(moviename);
            model.addAttribute("hashtag", moviename);
            model.addAttribute("Movies", mvtitles);
            List<String> jsontitle= new ArrayList<>();
            List<String> jsonimage = new ArrayList<>();
            for(int i = 0; i < mvtitles.size(); i++) {
                System.out.println("posts"+mvtitles.get(i));

                String text = null;
                text = URLEncoder.encode(mvtitles.get(i), StandardCharsets.UTF_8);

                String apiURL = searchapiURL2+ text;    // json 결과

                Map<String, String> requestHeaders = new HashMap<>();
                requestHeaders.put("X-Naver-Client-Id", searchClientId);
                requestHeaders.put("X-Naver-Client-Secret", searchClientSecret);
                String responseBody = get(apiURL, requestHeaders);

                model.addAttribute("jsonbody", responseBody);

                JSONObject jObject = new JSONObject(responseBody);
                JSONArray jArray = jObject.getJSONArray("items");

                List<String> jsonlisttitle = new ArrayList<String>();
                List<String> jsonlistimage = new ArrayList<String>();

                for (int k = 0; k < jArray.length(); k++) {
                    JSONObject obj = jArray.getJSONObject(k);
                    String title = obj.getString("title");
                    title = title.replaceAll("\\<.*?>", "");
                    title = title.replaceAll("&amp;", "&");
                    String image = obj.getString("image");

                    jsonlisttitle.add(title);
                    jsonlistimage.add(image);

                }
                jsontitle.add(jsonlisttitle.get(0));
                jsonimage.add(jsonlistimage.get(0));
            }
            System.out.println(jsontitle);
            System.out.println(jsonimage);

            model.addAttribute("jsontitle", jsontitle);
            model.addAttribute("jsonimage", jsonimage);
            return "hashtag";
        }
        else {

            String text = null;
            text = URLEncoder.encode(moviename, StandardCharsets.UTF_8);
            String apiURL = searchapiURL2+ text;    // json 결과


            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", searchClientId);
            requestHeaders.put("X-Naver-Client-Secret", searchClientSecret);
            String responseBody = get(apiURL, requestHeaders);

            model.addAttribute("jsonbody", responseBody);
            System.out.println(responseBody);

            JSONObject jObject = new JSONObject(responseBody);
            JSONArray jArray = jObject.getJSONArray("items");

            List<String> jsonlisttitle = new ArrayList<String>();
            List<String> jsonlistimage = new ArrayList<String>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);
                String title = obj.getString("title");
                title = title.replaceAll("\\<.*?>", "");
                title = title.replaceAll("&amp;", "&");
                String image = obj.getString("image");

                jsonlisttitle.add(title);
                jsonlistimage.add(image);
            }
            model.addAttribute("title1", jsonlisttitle.get(0));
            model.addAttribute("image1", jsonlistimage.get(0));

            return "search"; //
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



