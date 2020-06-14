package biz;

import java.io.File;

public class ChatMethods {
	
	private static ChatMethods chatMethod;
    private String root = "C:\\Users\\wotjr\\OneDrive\\���� ȭ��\\netty";

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
     * ���� ����� �����ش�.
     */
    public String showList() {
        String str = "";
        // ���� ���丮
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
