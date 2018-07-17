import java.io.File;

public class FileDigest {
    public static void main(String[] args){
        try {
            String filepath = "/home/zemoso/inf-sec/message.txt";
            File file = new File(filepath);
            String sha1Hash = HashGenerator.generateHash(file);
            System.out.println("hash for file:"+sha1Hash);
        }catch (HashGenerationException ex){
            ex.printStackTrace();
        }
    }
}
