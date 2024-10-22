package cdu.amber.boapiclientsdk;

import cdu.amber.boapiclientsdk.client.BoApiClient;
import cdu.amber.boapiclientsdk.model.User;
import cdu.amber.boapiclientsdk.utils.SignUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("boapi.client")
@Data
@ComponentScan
public class BoApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public BoApiClient boApiClient() {
        return new BoApiClient(accessKey,secretKey);
    }
}
