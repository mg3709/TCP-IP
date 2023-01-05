package TcpClientGUI;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReadThread extends Thread{
    private Socket sock = null;
    private BufferedReader readStream = null;

    //생성자
    // r 자식 스레드의 생성자
    // 0. 전달받은 Socket 객체와 입력Stream 객체 저장
    public ReadThread(Socket sock, BufferedReader readStream) {
        this.sock = sock;
        this.readStream = readStream;
    }

    // r 자식 스레드의 run( )
    //run
    public void run() {
        // 15. (서버) 클라이언트의 메시지를 한 라인씩 읽어, (모든) 클라이언트에 전송
        // 16. (클라이언트) 서버로부터 메시지 수신하여 출력 및 연결 종료 처리

        try {
            String line = null;
            while((line = readStream.readLine()) != null ) {
                System.out.println(line);
                TcpClientGUI1.txtReceive.append(line+"\n");

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("소켓 종료 되었습니다.");
        }
        //클라이언트 접속 종료 코드
        finally {
            try {
                if(readStream != null) readStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                if(sock != null) sock.close();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}