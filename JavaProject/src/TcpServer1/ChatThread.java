package TcpServer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatThread extends Thread {
    private Socket sock;
    private HashMap<String, PrintWriter> hm;
    private String id;
    private BufferedReader br;
    private boolean initFlag = false;

    // 자식 스레드의 생성자
    public ChatThread(Socket sck, HashMap<String, PrintWriter> hm) {
        // 0. 전달받은 Socket 객체와 HashMap 객체 저장
        sock = sck;
        this.hm = hm;

        try {
            // 7. (서버) I/O Stream 객체 생성
            // (1) 출력스트림 객체 생성(클라이언트에 write 하기 위한)
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));

            // (2) 입력스트림 객체 생성(클라이언트 메시지 read 하기 위한)
            br = new BufferedReader(new InputStreamReader(sock.getInputStream(),"UTF-8"));

            // 8. (클라이언트) 클라이언트들의 I/O Stream 객체 생성
            // 9. (클라이언트) ID 전송
            // 10. (서버) 클라이언트의 ID 읽음
            id = br.readLine();
            // 11. (클라이언트) ReadThread 생성 및 start
            // 12. (서버) 접속된 클라이언트의 ID를 broadcast
            broadcast(id + "님이 접속했습니다.");
            System.out.println("접속한 사용자 아이디 : " + id);
            // 13. (서버) HashMap에 클라이언트 정보 저장(id, PrintWriter 객체)
            synchronized (hm) { // 동기화
                hm.put(id, pw);
            }
            initFlag = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }

    }

    // 자식 스레드의 run( )
    public void run() {
        // 14. (클라이언트) 메시지 송신
        // 15. (서버) 클라이언트의 메시지를 한 라인씩 읽어, 모든 클라이언트에 전송
        String line = null;

        try {
            while((line = br.readLine()) != null) {
                // (1) “quit” 면 -> 17번 클라이언트 연결 종료 코드로
                if (line.equals("/quit")) break;
                // (2) “/to 클라이언트명 메시지” 이면 특정 클라이언트에 전송 -> sendmsg( )
                if (line.indexOf("/to ") == 0) {
                    sendmsg(line);
                }
                // (3) 그 이외에는 모든 클라이언트에 브로드캐스트 -> broadcast( )
                else {
                    broadcast("[" + id + "] : " + line);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 16. (클라이언트) 메시지 수신
        // 17. (서버) 클라이언트 연결 종료 처리
        finally { // HashMap에 현재 스레드의 id 정보 삭제 후 나머지
            synchronized (hm) { // 클라이언트들에 접속종료 메시지 broadcast 후 socket 닫기
                hm.remove(id);
            }

            broadcast(id + " 님이 접속 종료하였습니다.");
            System.out.println(id + " 접속 종료.....");
            try {
                if (sock != null)
                    sock.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 연결된 모든 클라이언트에게 메시지 전달하는 함수
    public void broadcast(String msg) {
        synchronized (hm) {
            // HashMap에 저장된 각각의 클라이언트의 pw객체를 객체 배열(Collection)에
            // 가져옴. value()는 HashMap에 포함된 값들을 Collection 객체로 return.
            Collection<PrintWriter> collection = hm.values();
            // Collection의 요소들을 반복하여 가지고 오기 위해 Iterator 객체 생성 후
            // Iterator를 통하여 모든 pw 객체를 가져와 모든 클라이언트에 메시지 전송
            Iterator<PrintWriter> iter = collection.iterator();
            while (iter.hasNext()) {
                PrintWriter pw = (PrintWriter) iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    }

    public void sendmsg(String msg) {
        // 문자열 중 처음 공백보다 1큰 값을 return
        int start = msg.indexOf(" ") + 1;
        // start번째 index에서 시작하여 다음 공백의 index값 return
        int end = msg.indexOf(" ", start);
        if (end != -1) {
            // start~end 인덱스의 문자열 가져옴(id 가져옴)
            String to = msg.substring(start, end);
            String msg2 = msg.substring(end + 1); // 전달할 메시지 가져옴
            Object obj = hm.get(to); // HashMap에서 해당 id의 출력스트림 가져옴
            if (obj != null) {
                PrintWriter pw = (PrintWriter) obj;
                pw.println(id + " 님이 귓속말을 보내셨습니다. : " + msg2);
                pw.flush();
            }
        }
    }

}
