package com.fengwenyi.mp3demo.util;

import com.fengwenyi.mp3demo.dto.SecureUriDTO;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author 01376494
 */
@Slf4j
public class TestUtil {
    public static void main(String[] args) {
        String token = "ccUtroc1v716Bw6IbzQObEmsg-DT_j8dkSJIHpB1JuM=";
        String ts = "31556889864403199";
        String uriKey = "E_0013";
        String fileName = "2002198RQ3T6M1_VK4EWjO0g0zTCERpEdShb1zVbfqm_qD1pU.png";
        SecureUriDTO secureUriDTO = new SecureUriDTO(ts, uriKey, fileName);
        String toCheck = generateSecureUriToken(secureUriDTO);
        log.warn("toCheck:{},token:{}", toCheck, token);
        log.warn("toCheck:{}",toCheck.equalsIgnoreCase(token));
    }

    private static String generateSecureUriToken(SecureUriDTO secureUriDTO) {
        String toHashString = secureUriDTO.toString();
        log.info("{} token待生成", toHashString);
        byte[] afterHashBytes = Hashing.sha256().hashString(toHashString, StandardCharsets.UTF_8).asBytes();
        String secureUriToken = BaseEncoding.base64Url().encode(afterHashBytes);
        log.info("{} token生成: {}", toHashString, secureUriToken);
        return secureUriToken;
    }

}
