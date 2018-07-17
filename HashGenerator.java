import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    public static String generateHash(File file) throws  HashGenerationException{
        return hashFile(file);
    }

    private static String hashFile(File file) throws HashGenerationException{
        try(FileInputStream inputStream=new FileInputStream(file)){
            MessageDigest digest=MessageDigest.getInstance("SHA-1");
            byte[] buffer=new byte[1024];
            int bytesRead=-1;
            while ((bytesRead = inputStream.read(buffer))!=-1){
                digest.update(buffer, 0, bytesRead);
            }
            byte[] hash=digest.digest();
            return byteArrayToString(hash);
        }catch (NoSuchAlgorithmException|IOException e) {
            throw new HashGenerationException("could not hash the file");
        }
    }

    private static String byteArrayToString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
