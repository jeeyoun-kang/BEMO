package bemo.bemo.controller;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.dto.PostsDto;
import bemo.bemo.dto.RequestDto;
import bemo.bemo.entity.Posts;
import bemo.bemo.repository.PostsRepository;
import bemo.bemo.service.PrincipalDetailsService;
import bemo.bemo.service.S3Service;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import bemo.bemo.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

@RequiredArgsConstructor
@RestController
public class ApiController {
    @Value("${search.client.id}")
    private String searchClientId;

    @Value("${search.client.secret}")
    private String searchClientSecret;
    @Value("${search.apiURL2}")
    private String searchapiURL2;
    private final S3Service s3Service;

    private final PostsService postsService;

    private final PostsRepository postsRepository;
    private final PrincipalDetailsService principalDetailsService;

    @GetMapping("/users/{id}")
    public ModelAndView comments(Model model, @PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("comments");
        modelAndView.addObject("Posts", postsService.findById(id));
        modelAndView.addObject("id",id);
        PostsDto postsSaveDto = postsService.findById(id);

        if (principalDetails != null) { // 본인이면 수정 가능
            if (postsSaveDto.getAuthor().equals(principalDetails.getUsername())) model.addAttribute("writer", true);
        }
        return modelAndView;
    }

    @PostMapping("/users/")
    public Long save(Model model,@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file) throws IOException, ParseException {

        if (file.isEmpty()) {
            String imgPath = s3Service.getThumbnailPath("bemo.JPG");
            requestDto.setUrl(imgPath);
        }
        else {
            String url = s3Service.uploadFile(file);
            requestDto.setUrl(url);
        }
        String text = null;
        String apiURL=null;
        text = URLEncoder.encode(requestDto.getMvtitle(), StandardCharsets.UTF_8);

        apiURL = searchapiURL2+ text;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", searchClientId);
        requestHeaders.put("X-Naver-Client-Secret", searchClientSecret);
        String responseBody = get(apiURL, requestHeaders);

        model.addAttribute("jsonbody", responseBody);

        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(responseBody);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("items");

        List<String> jsonlisttitle = new ArrayList<String>();
        List<String> jsonlistimage = new ArrayList<String>();
        List<String> jsonlistdi = new ArrayList<String>();

        // 배열의 모든 아이템을 출력합니다.
        for (int j = 0; j < jArray.length(); j++) {
            JSONObject obj = jArray.getJSONObject(j);
            String image = obj.getString("image");
            jsonlistimage.add(image);
        }
        requestDto.setMvurl(jsonlistimage.get(0));

        return postsService.save(requestDto);
    }

    @PutMapping ("/users/{id}")
    public Long update(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file,@PathVariable Long id) throws IOException {
        if (file.isEmpty()) {
            String imgPath = s3Service.getThumbnailPath("bemo.JPG");
            requestDto.setUrl(imgPath);
        }
        else {
            String url = s3Service.uploadFile(file);
            requestDto.setUrl(url);
        }


        requestDto.setId(id);
        return postsService.update(id,requestDto);
    }



    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable Long id) throws IOException {
        s3Service.deleteFile(id);
        postsService.delete(id);
        return id;
    }

    @RequestMapping(value = "/join", method=RequestMethod.POST)
    public Long join(@RequestBody RequestDto request){
        return principalDetailsService.create(request);
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
