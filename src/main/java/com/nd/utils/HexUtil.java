package com.nd.utils;

public class HexUtil {
    
    /**
     * ����ͨ�ַ�����16��������
     * ��"WAZX-B55SY6-S6DT5" ����Ϊ��"57415a582d4235355359362d5336445435"
     * */
    public static String strToHex(String str){
    	if(str==null||str.length()==0){
    		return null;
    	}
    	//����16�ֽ�ʱ��ǰ���0����
         while(str.length()%16!=0){
        	 str="0"+str;
         }
         System.out.println(str);
         byte[] bytes = str.getBytes();
//         System.out.println(new String(bytes));
//         System.out.println(bytesToHex(bytes));
         return bytesToHex(bytes);
    }
    
    /**��16�����������ַ�����ԭΪ��ͨ�ַ���
     * ��"57415a582d4235355359362d5336445435" ��ԭΪ��"WAZX-B55SY6-S6DT5"
     * */
    public static String hexToStr(String hex){
        byte[] bytes=hexToBytes(hex);
        return new String(bytes);
    }
    
    
    /**16����תbyte[]*/
    public static byte[] hexToBytes(String hex){
    	
        int length = hex.length() / 2;
        byte[] bytes=new byte[length];
        for(int i=0;i<length;i++){
            String tempStr=hex.substring(2*i, 2*i+2);//byte:8bit=4bit+4bit=ʮ������λ+ʮ������λ
            bytes[i]=(byte) Integer.parseInt(tempStr, 16);
        }
        return bytes;
    }
    
    /**byte[]ת16����*/
    public static String bytesToHex(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        if(bytes == null || bytes.length<=0){
        	return null;
        }
        for(int i=0;i<bytes.length;i++){
            int tempI=bytes[i] & 0xFF;//byte:8bit,int:32bit;��λ����.
            String str = Integer.toHexString(tempI);
            if(str.length()<2){
                sb.append(0).append(str);//���Ȳ�����λ�����룺��16���Ƶ�d,��0d��ʾ��
            }else{
                sb.append(str);
            }
        }
        return sb.toString();
    }

}