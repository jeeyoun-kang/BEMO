package hello.hellospring.controller;

import hello.hellospring.service.PostsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MovieDetailController {
    @Autowired
    PostsService postsService;
    @RequestMapping("/moviedetail/{detail}/{code}")
    public String moviedetail(Model model, @PathVariable String detail, @PathVariable String code) {
        //System.out.println(fromDTO);
        model.addAttribute("Posts",postsService.findByMvtitle(detail)); // Service 접근
        model.addAttribute("movie_title",detail);
        model.addAttribute("code",code);


        String clientId = "******"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "******"; //애플리케이션 클라이언트 시크릿값"
        String apiURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=" + code;    // json 결과

        Map<String, String> requestHeaders2 = new HashMap<>();//
        requestHeaders2.put("X-Naver-Client-Id", clientId);
        requestHeaders2.put("X-Naver-Client-Secret", clientSecret);
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




        return "moviedetail";


    }

    @RequestMapping("/moviedetail/{detail}")
    public String moviedetail(Model model, @PathVariable String detail)
    {
        model.addAttribute("movie_title",detail);


        String clientId = "******"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "******"; //애플리케이션 클라이언트 시크릿값"

        String text = null;
        text = URLEncoder.encode(detail, StandardCharsets.UTF_8);

        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;    // json 결과
        //String apiURL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + text;


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
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
        actorstr = actorstr.substring(0, actorstr.length() - 1);
        model.addAttribute("peopleNm",jsonlistdi.get(0));
        model.addAttribute("actorNm",actorstr);
        model.addAttribute("movie_title",jsonlisttitle.get(0));
        model.addAttribute("image1",jsonlistimage.get(0));



        return "moviedetail2";


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