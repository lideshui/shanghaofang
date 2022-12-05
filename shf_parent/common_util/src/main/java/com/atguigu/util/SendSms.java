package com.atguigu.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import darabonba.core.client.ClientOverrideConfiguration;
import darabonba.core.exception.TeaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.util.AliSmsUtil
 */
public class SendSms {

    private static String accessKeyId = "LTAI5t9v9gFPqHrTtTbwGQux";
    private static String accessKeySecret = "XLkWChxUC6xk2rtKG0ADV22LkUsot3";


    public static Boolean sendSms(String phone, String code) {

        // 配置凭据身份验证信息
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder().accessKeyId(accessKeyId).accessKeySecret(accessKeySecret).build());

        // 配置阿里云客户端
        AsyncClient client = AsyncClient.builder()
                .region("cn-beijing").credentialsProvider(provider)
                .overrideConfiguration(ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com"))
                .build();

        // API参数：签名名称、模板Code、手机号、验证码
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("李德水个人网站")
                .templateCode("SMS_262450421")
                .phoneNumbers(phone)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        // 异步获取API请求的返回值
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);

        // 处理发送结果
        try {
            SendSmsResponseBody body = response.get().getBody();
            Logger logger = LoggerFactory.getLogger(SendSms.class);
            if (body.getCode().equals("OK")){
                logger.info("用户验证码发送成功");
                return true;
            }else {
                logger.error(body.getMessage());
            }
        } catch (TeaException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭客户端
            client.close();
        }
        return false;
    }
}

