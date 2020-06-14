package biz;

import java.io.File;

public class ChatMethods {
	
	private static ChatMethods chatMethod;
    private String root = "C:\\Users\\wotjr\\OneDrive\\바탕 화면\\netty";

    private ChatMethods() {
        System.out.println("ChatMethod Object");
    }

    public static ChatMethods getInstance(){
        if( chatMethod == null){
            chatMethod = new ChatMethods();
        }
        return chatMethod;
    }
	
	 /**
     * 파일 목록을 보여준다.
     */
    public String showList() {
        String str = "";
        // 하위 디렉토리
        for (File file : new File(root).listFiles()) {
        	if(file.isFile()) {
        		str += "f :: " + file.getName()+"%";
        	}else {
        		str += "d :: " + file.getName()+"%";
        	}
        	
        }
        return str;
    }
    
    public String readFile(String fileNm) {
    	String str = "";
    	
    	
    	File file = new File(root + File.pathSeparator + fileNm);
    	
    	return str;
    }

}
