/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

/**
 * Download from <a href="http://www.zenjouken.com/index.php?action=pages_view_main&block_id=150&active_action=journal_view_main_detail&post_id=55#_150">here</a>
 * 
 * @author takunoji
 * @see http://www.procon-asahikawa.org/
 * @see https://github.com/oika/CHaserGuiServer
 * 2019/05/12
 */
public class AsahikawaU16_HttpClient {

     public static void main(String[] argv) throws Exception {
	 	//
		URL url = null;
		HttpURLConnection connection =null;
		Map<String, List<String>> headers =null;
		Iterator it =  null;
		CookieStore store =null;
		List<HttpCookie> cookies =null;
		BufferedReader reader = null;
		String buffer =null;
		String info;
		//
//		String host = "http://www7019ug.sakura.ne.jp/CHaserOnline003/user/";
		String host = "http://127.0.0.1:2009/";
		String user = "cool";
		String pass = "cool";
		String roomNumber = "3";
		String proxyhost = null;
		String proxyport = null;
		String command1 = null;
		String command2 = null;
		String command3 = null;
		int opt = 0;
	 	// �����m�F
		for(String cmd : argv){
			switch(opt){
				case 1:
					host=cmd;
					opt=0;
				break;
				case 2:
					String[] Opt = cmd.split(":",2);
					proxyhost=Opt[0];
					proxyport=Opt[1];
					//�v���L�V�ݒ�
					System.setProperty("proxySet","true");
					System.setProperty("proxyHost",proxyhost);
					System.setProperty("proxyPort",proxyport);
					opt=0;
				break;
				case 3:
					user=cmd;
					opt=0;
				break;
				case 4:
					pass=cmd;
					opt=0;
				break;
				case 5:
					roomNumber=cmd;
					opt=0;
				break;
				case 6:
					opt=0;
				break;
			}
			//�w���v�̕\��
			if( cmd.equals("-h") ){
				System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				System.out.println("�S�������Z�p���猤�������Á@CHaserOnline�@�T���v���N���C�A���g�@java�Ńw���v");
				System.out.println("javaClient2014 Help");
				System.out.println("java javaClient2014 -a http://www7019ug.sakura.ne.jp/CHaserOnline003/user/ -u cool -p cool -x 192.168.30.251:80 -r 3");
				System.out.println("-h : Help");
				System.out.println("-x [IPaddress:Port]  �v���L�V�ݒ�         �ݒ���  -x 192.168.1.1:8080  �ȗ����́AProxy�Ȃ�");
				System.out.println("-u [UserID]          ���[�UID�ݒ�         �ݒ���  -u cool              �ȗ����́Acool");
				System.out.println("-p [Password]        �p�X���[�h�ݒ�       �ݒ���  -p cool              �ȗ����́Acool");
				System.out.println("-r [RoomNumber]      ���[���i���o�[�ݒ�   �ݒ���  -r 3                 �ȗ����́A3");
				System.out.println("-a [IPaddress]       �T�[�o�ݒ�           �ݒ���  -a http://www7019ug.sakura.ne.jp/CHaserOnline003/user/");
				System.out.println("                                                  �ȗ����́Ahttp://www7019ug.sakura.ne.jp/CHaserOnline003/user/");
//					System.out.println("-f [file]            �ݒ��t�@�C���ǂݍ��݁i�ŗD���Őݒ肳���܂��B�j-f javaClient.ini");
				System.out.println("�I�v�V�����ݒ��́A���p�󔒂ŋ��؂��܂��B");
				System.out.println("�Q�[���I�����́A�G���[�ŏI�����܂��B");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				System.out.println("�T�|�[�g�ΏۊO�ł����A���ӌ��͏����܂��B�ύX�Ȃǂ́A�C�����������s�����܂��B");
				System.out.println("���{�����ł́A���쌠�͕����ł��Ȃ��̂ŁE�E�E");
				System.out.println("���쌠�́A�S�������Z�p���猤�����v���O���~���O�R���e�X�g�ɑ����܂��B");
				System.out.println("���쌠�҂����̊��]�́A�F�����ł��ǂ��R�[�h�ɕύX���Ă������ėǂ����̂����J���Ă��������ł��B");
				System.out.println("�����͂��肢���܂��B");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				return ;
			}
			if( cmd.equals("-a") ){
				opt=1;
			}
			if( cmd.equals("-x") ){
				opt=2;
			}
			if( cmd.equals("-u") ){
				opt=3;
			}
			if( cmd.equals("-p") ){
				opt=4;
			}
			if( cmd.equals("-r") ){
				opt=5;
			}
		}
//			host = host + " -u " + user + " -p " + pass;
		//
		CookieManager manager = new CookieManager();
		CookieHandler.setDefault(manager);
		//
	 	 String jsession = new String("JSESSIONID");//jsession
         int i=0;
	 	String getUrl=null;
	 	/* アクセスURL: host名 + UserName + pass(ユーザー名に同じ?)
	 	 * 
	 	 */
		 while(i<5){
			switch(i){
				case 0://user authenticate
					getUrl=host+"UserCheck?user="+user+"&pass="+pass;
				break;
				case 1:
					getUrl=host+"RoomNumberCheck?roomNumber="+roomNumber;
				break;
				case 2:
					getUrl=host+"GetReadyCheck?command1=gr";
				break;
				case 3:
					getUrl=host+"CommandCheck?command2=wu";
				break;
				case 4:
					getUrl=host+"EndCommandCheck?command3=%23";
				break;
			 }
//    	 	 System.out.println("host>>"+getUrl);
	    	 // HOST����URL�𐶐�,�ڑ�
//    	 	 getUrl = "http://127.0.0.1:2009";
			 System.out.println("URL: " + getUrl);
	         url = new URL(getUrl);

	         connection = (HttpURLConnection) url.openConnection();
	         // POSTを可能にする？
	         connection.setDoOutput(true);
	         connection.setRequestMethod("GET");
			 connection.setInstanceFollowRedirects(false);
			 connection.setAllowUserInteraction(true);
			 connection.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
			 connection.setDoInput(true);
			 connection.setDoInput(true);
			 connection.connect();
			 System.out.println("ContentType: " + connection.getContentType());
			 System.out.println("Connect");
			 
//			 dumpConnection(connection);
//	         System.out.println("----------------GetReady--------------");
//			 System.out.println("ResponseCode: " + connection.getResponseCode());
			 // �w�b�_�������o��
//			 System.out.println("Header");
//	         headers = connection.getHeaderFields();
//			 System.out.println("Get HeadFields OK");
//			 headers.forEach((key, val) -> {
//				 System.out.println("Key: " + key);
//				 System.out.println("val: " + val);
//			 }); 
//	         System.out.println("----------------Cookies: JSESSIONID --------------");
//			 store = manager.getCookieStore();
//			 cookies = store.getCookies();
	/**/
//	         for(HttpCookie cookie : cookies){
//	        	 System.out.println(cookie.getName());
//	            if(cookie.getName().equals("JSESSIONID")){
//	                jsession=cookie.getValue();
//	                cookie.setValue(jsession);
//	            }
//	         }
//	         System.out.println("JSESSIONID: "+jsession);
	/**/
	         System.out.println("----------------Cookies--------------");
             // �R���e���c���o��
	         System.out.println("AllowUserInteraction: " + connection.getAllowUserInteraction());
	         
	         InputStream in = connection.getInputStream();
	         System.out.println("*** get InputStream ***");
             reader =  new BufferedReader(new InputStreamReader (in, "JISAutoDetect"));
             System.out.println("*** BufferedReader ***");
             buffer = reader.readLine();
         	System.out.println(buffer.toString());
             while (null != buffer) {
				if ( buffer.indexOf("ReturnCode") > -1 ) {
				   String[] info_b =buffer.split("=",2);
				   info=info_b[1];
                   System.out.println(info_b[1]);
				}
				//
              	buffer = reader.readLine();
             }
	         connection.disconnect();
			 i++;
			 if(i>4){
			 	i=2;
			 }
		 }
		 System.out.println("処理終了");
	 	return ;
     }

     /**
      * 接続したサーバーからのレスポンスコードなどをダンプする
      * @param con HttpURLConnection
      * @see https://docs.oracle.com/javase/jp/8/docs/api/java/net/HttpURLConnection.html
      */
     private static void dumpConnection(HttpURLConnection con) {
//        	 System.out.println("ResponseCode: " + con.getResponseCode());
//        	 System.out.println("Method: " + con.getRequestMethod());
//        	 System.out.println("ContentEncoding: " + con.getContentEncoding());
//        	 System.out.println("ResponseCode: " + con.getHeaderFields());
     }
}
