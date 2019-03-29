package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class JavaNioTest {
    static String fileName = "F:\\hosts";
    public static void main(String[] args){
        readFileByNIO();
    }


    public static void readFileByNIO(){
        try{
            FileChannel channel = new RandomAccessFile(fileName,"r").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(800);
            int bytesRead  = channel.read(buffer);
            System.out.println(bytesRead);;
            while(bytesRead != -1){
                buffer.flip();
                while(buffer.hasRemaining()){
                    System.out.print((char)buffer.get());
                }
                System.out.println();
                buffer.compact();
                bytesRead = channel.read(buffer);
                System.out.println(bytesRead);
            }

        }catch (Exception e){

        }

    }
}
