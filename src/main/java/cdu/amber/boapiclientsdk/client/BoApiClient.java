package cdu.amber.boapiclientsdk.client;


import cdu.amber.boapiclientsdk.model.User;
import cdu.amber.boapiclientsdk.utils.SignUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BoApiClient {

    private String accessKey;

    private String secretKey;

    public BoApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "GET 名字");

        String result= HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        System.out.println(result);
    }


    public void getNameByPost( String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "GET 名字");

        String result= HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        System.out.println(result);
    }

    public Map<String, String> getHeaderMap(String body){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", "amber");
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        hashMap.put("body", body);
        hashMap.put("once", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        return hashMap;
    }

    public void getUserNameByPost( User user) {
        String body = JSONUtil.toJsonStr(user);
        String encode = URLEncoder.encode(body, StandardCharsets.UTF_8);
        String result = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeaderMap(encode))
                .body(body)
                .execute().body();
        System.out.println(result);
    }
}
