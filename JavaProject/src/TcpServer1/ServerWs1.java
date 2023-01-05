package TcpServer1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerWs1 {

    public static void main(String[] args) {
        // 0. 준비 작업
        // 0-1. ServerSocket 객체 선언
        ServerSocket ss = null;

        // 0-2. 통신을 위한 클라이언트 Socket 객체 선언
        Socket csk = null;

        // 0-3. 표준입력 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            // 0-4. 표준입력으로부터 서버의 포트 번호 입력
            System.out.println("Server Port: ");
            int port = Integer.parseInt(br.readLine());

            // 1. (서버) 서버소켓 객체 생성 및 포트 활성화
            ss = new ServerSocket(port);
            System.out.println("-- Server 실행 중 ....(port 번호 :"+port+") --\n");
            // 2. (서버) HashMap 객체 생성(클라이언트들의 전용 출력스트림 저장)
            HashMap<String, PrintWriter> hm = new HashMap<String, PrintWriter>();

            while (true) {
                // 3. (서버) 클라이언트 접속 대기 - accept( )
                // 4. (클라이언트) 클라이언트들의 접속 요청
                // 5. (서버) 연결 승인 및 각 클라이언트들과 통신할 Socket 객체 return
                csk = ss.accept();
                // 6. (서버) 클라이언트와 통신을 위한 자식 스레드 생성 및 start
                // 이때, 자식 스레드 생성자에 Socket객체와 HashMap 객체 전달
                ChatThread ct = new ChatThread(csk, hm);
                ct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}