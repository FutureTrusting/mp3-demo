package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.business.AppBusiness;
import com.fengwenyi.mp3demo.dto.SecureUriDTO;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.fengwenyi.mp3demo.threadpool.ConcurrentCompletableFuture.sleep;

/**
 * @author : Caixin
 * @date 2019/8/12 14:03
 */
@Slf4j
public class DownLoadTest extends Mp3DemoApplicationTests {
    @Value("${security.const.uriKey:E_0013}")
    private String constUriKey;

    @Test
    public void testDownLoad() {
        String token = "ccUtroc1v716Bw6IbzQObEmsg-DT_j8dkSJIHpB1JuM=";
        String ts = "31556889864403199";
        String constUriKey1 = "E_0013";
        String fileName = "2002198RQ3T6M1_VK4EWjO0g0zTCERpEdShb1zVbfqm_qD1pU.png";
        System.err.println(constUriKey1);
        SecureUriDTO secureUriDTO = new SecureUriDTO(ts, constUriKey1, fileName);
        String toCheck = generateSecureUriToken(secureUriDTO);
        log.warn("toCheck:{},token:{}", toCheck, token);
        System.err.println(toCheck.equals(token));
    }

    private String generateSecureUriToken(SecureUriDTO secureUriDTO) {

        String toHashString = secureUriDTO.toString();
        log.info("{} token待生成", toHashString);
        byte[] afterHashBytes = Hashing.sha256().hashString(toHashString, StandardCharsets.UTF_8).asBytes();
        String secureUriToken = BaseEncoding.base64Url().encode(afterHashBytes);
        log.info("{} token生成: {}", toHashString, secureUriToken);
        return secureUriToken;
    }
}
