import com.oracle.ddbookmarket.uitl.MD5Util;

public class MD5Test {
    public static void main(String[]args) throws Exception{
        String s="admin";
        System.out.println(MD5Util.getEncryptedPwd(s));
    }
}
