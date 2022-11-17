package com.yytech.mpcdsepc.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

public class TencentMsgUtils {
    public static boolean sendMsg(String phoneNumber, String data) throws TencentCloudSDKException {
        Credential cred = new Credential("AKID1tWv2uXu54sTpzwhFYbWFpDNK3YpreCU", "83uVqvmQsdBTYP1VlndJn8JJ8lndJn36");

        SmsClient client = new SmsClient(cred, "ap-beijing");
        SendSmsRequest req = new SendSmsRequest();

        String sdkAppId = "1400765145";
        req.setSmsSdkAppid(sdkAppId);

        /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名 */
        // 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
        String signName = "云影安全";
        req.setSign(signName);

        /* 模板 ID: 必须填写已审核通过的模板 ID */
        // 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
        String templateId = "1610531";
        req.setTemplateID(templateId);

        /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
        String[] templateParamSet = {data};
        req.setTemplateParamSet(templateParamSet);

        /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
         * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
        String[] phoneNumberSet = {"+86" + phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet);


        /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
        SendSmsResponse res = client.SendSms(req);
        return true;
    }
}
