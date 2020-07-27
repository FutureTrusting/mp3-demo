package com.fengwenyi.mp3demo.util;


import com.fengwenyi.mp3demo.dto.UsSkuInfoPicDTO;
import com.fengwenyi.mp3demo.dto.UsSkuInfoVO;
import com.fengwenyi.mp3demo.dto.UsSkuPicDo;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 01376494
 */
public class AesUtil {

    private static final String KEY = "6RqLnjOcVxJKBjFl";

    private static final String KEY_ALGORITHM = "AES";
    //默认的加密算法

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 创建密码器

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(KEY));
            // 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);
            // 加密

            return Hex.encodeHexString(result);
            //转为16进制
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {

        if (StringUtils.isBlank(content)) {
            return content;
        }

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(KEY));

            //执行操作
            byte[] result = cipher.doFinal(Hex.decodeHex(content.toCharArray()));

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {

        return new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
    }

    public static void main(String[] args) {

        String encrypt1 = AesUtil.encrypt("123456");
        System.err.println(encrypt1);
        String encrypt = AesUtil.encrypt("{\"skuInfo\":[{\"skuId\":\"123\",\"skuNum\":\"998\"},{\"skuId\":\"1234\",\"skuNum\":\"9988\"}],\"receiveUserName\":\"收件地址\",\"receivePhone\":\"13170883884\",\"receiveAddress\":\"北京市海淀区\",\"orgId\":\"81629\",\"remark\":\"备注1\",\"userId\":\"16829\",\"receiveSendType\":1,\"sourceType\":1,\"orderType\":1}");
        System.err.println(encrypt);
        String decrypt = AesUtil.decrypt("b7353694a442f30203227e022dfd3f00");
        System.err.println(decrypt);

        UsSkuPicDo picDo = new UsSkuPicDo();
        UsSkuPicDo picDo1 = new UsSkuPicDo();
        picDo.setId(1L);
        picDo.setSkuId(11L);
        picDo1.setId(2L);
        picDo1.setSkuId(22L);
        UsSkuPicDo picDo3 = new UsSkuPicDo();
        UsSkuPicDo picDo4 = new UsSkuPicDo();
        picDo3.setId(3L);
        picDo3.setSkuId(33L);
        picDo4.setId(4L);
        picDo4.setSkuId(44L);

        UsSkuInfoPicDTO infoPicDTO = new UsSkuInfoPicDTO();
        UsSkuInfoPicDTO infoPicDTO2 = new UsSkuInfoPicDTO();
        infoPicDTO.setSkuPics(Arrays.asList(picDo,picDo1));
        infoPicDTO2.setSkuPics(Arrays.asList(picDo3,picDo4));
        infoPicDTO.setDesc("infoPicDTO");
        infoPicDTO2.setDesc("infoPicDTO2");
        List<UsSkuInfoPicDTO> infoPicDTOS = Arrays.asList(infoPicDTO, infoPicDTO2);

        List<UsSkuInfoVO> infoVOS = infoPicDTOS.stream()
                .map(x -> {
                    UsSkuInfoVO exportVo = new UsSkuInfoVO();
                    BeanCopier copier = BeanCopier.create(UsSkuInfoPicDTO.class, UsSkuInfoVO.class, false);
                    copier.copy(x, exportVo, null);
                    return exportVo;
                })
                .collect(Collectors.toList());
        infoVOS.forEach(System.out::println);
        System.err.println(new Gson().toJson(infoVOS));

    }
}
