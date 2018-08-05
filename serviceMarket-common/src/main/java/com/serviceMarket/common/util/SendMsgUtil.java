package com.serviceMarket.common.util;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月11日 下午8:22:53 
* 类说明 :SMS_webchinese发送短信util
*/
public class SendMsgUtil {
	
	//手机绑定短信验证码接口
	public static HashMap<String,String> getMessageStatus(String phone){
		HashMap<String,String> map=new HashMap<String,String>();
		
		HttpClient client = new HttpClient();
		
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		
		int num=(int)((Math.random()*9+1)*100000);//验证码
		String code=num+"";
		
		NameValuePair[] data ={ 
				new NameValuePair("Uid", "zhangweipeng123"),
				new NameValuePair("Key", "5db4ab8e8ab0ba3dd9f5"),
				new NameValuePair("smsMob","13420118137"),
				new NameValuePair("smsText","【服务集市】您正在绑定账号手机号码,本次验证码为:"+code+""+"，有效时间为5分钟,请不要随意泄露验证码")};
		map.put("code", code);
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:"+statusCode);
		Header[] headers = post.getResponseHeaders();
		for(Header h : headers)
		{
			System.out.println(h.toString());
		}
		
		
		String result = null;
		try {
			result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		System.out.println(result); //打印返回消息状态
		map.put("result", result);

		post.releaseConnection();
		return map;
	}
	
	
}
