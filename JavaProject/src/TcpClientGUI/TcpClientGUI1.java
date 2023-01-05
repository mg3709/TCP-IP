package TcpClientGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextArea;

public class TcpClientGUI1 extends JFrame {

    private JPanel contentPane;
    private JTextField txtIP;
    private JTextField txtPort;
    private JTextField txtID;
    private JTextField txtStatus;
    private JButton btnConnect;
    private JButton btnClose;
    private JButton btnExit;
    private JLabel lblNewLabel_5;
    private JTextField txtSend;
    public static JTextArea txtReceive;
    private JButton btnSend;
    private JButton btnClear;

    // 0-2. 통신을 위한 클라이언트 Socket 객체 선언
    Socket csk = null;

    // 0-3. 서버의 메시지를 수신하기 위한 입력 Stream 객체 선언
    InputStream is = null;
    BufferedReader readStream = null;

    // 0-4. 서버로 메시지를 송신하기 위한 출력 Stream 객체 선언
    OutputStream os = null;
    PrintWriter writeStream = null;

    // 끝났느냐 물어보는거~ㅇㅅㅇ
    boolean endflag = false;

    public static void main(String[] args) {

        TcpClientGUI1 frame = new TcpClientGUI1();
        frame.setVisible(true);

    }

    /**
     * Create the frame.
     */
    public TcpClientGUI1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 552, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("IP : ");
        lblNewLabel.setBounds(35, 40, 23, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Port : ");
        lblNewLabel_1.setBounds(23, 76, 35, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("ID : ");
        lblNewLabel_2.setBounds(36, 115, 35, 15);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Status : ");
        lblNewLabel_3.setForeground(Color.BLUE);
        lblNewLabel_3.setBounds(14, 172, 57, 15);
        contentPane.add(lblNewLabel_3);

        txtIP = new JTextField();
        txtIP.setBounds(83, 37, 116, 21);
        contentPane.add(txtIP);
        txtIP.setColumns(10);

        txtPort = new JTextField();
        txtPort.setBounds(83, 73, 116, 21);
        contentPane.add(txtPort);
        txtPort.setColumns(10);

        txtID = new JTextField();
        txtID.setBounds(83, 112, 116, 21);
        contentPane.add(txtID);
        txtID.setColumns(10);

        txtStatus = new JTextField();
        txtStatus.setBounds(83, 169, 164, 21);
        contentPane.add(txtStatus);
        txtStatus.setColumns(10);

        btnConnect = new JButton("Connect");
        btnConnect.setBounds(35, 228, 97, 23);
        contentPane.add(btnConnect);
        btnConnect.addActionListener(new ActionListener() {
            //중간고사
//버튼 이벤트 처리하는 함수는 addActionListener이다.
//ActionLinstenr이 가지고 있는 메소드는 actionPerformed
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("서버 연결 버튼 클릭");
                connect();
            }
        });

        btnClose = new JButton("Close");
        btnClose.setBounds(156, 228, 97, 23);
        contentPane.add(btnClose);
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("서버 종료 버튼 클릭");
                close();
            }
        });

        btnExit = new JButton("프로그램 종료");
        btnExit.setBounds(35, 272, 218, 29);
        contentPane.add(btnExit);
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                close();
                dispose();
            }
        });

        JLabel lblNewLabel_4 = new JLabel("메시지 전송");
        lblNewLabel_4.setBounds(270, 37, 89, 15);
        contentPane.add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("메시지 수신");
        lblNewLabel_5.setBounds(270, 115, 89, 15);
        contentPane.add(lblNewLabel_5);

        txtSend = new JTextField();
        txtSend.setBounds(270, 65, 169, 21);
        contentPane.add(txtSend);
        txtSend.setColumns(10);

        txtReceive = new JTextArea();
        txtReceive.setBounds(270, 140, 250, 161);
        contentPane.add(txtReceive);

        btnSend = new JButton("전송");
        btnSend.setBounds(451, 64, 69, 23);
        contentPane.add(btnSend);
        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // 14. (클라이언트) (1) 전달할 메시지를 키보드 입력받아 서버로 송신(메인스레드)
                // (2) 만약, /quit를 입력하면 클라이언트 접속을 종료-종료 코드 작성
                String line = txtSend.getText();
                writeStream.println(line);
                writeStream.flush();

                if (line.equals("/quit")) {
                    close();
                }

            }
        });

        btnClear = new JButton("지우기");
        btnClear.setBounds(437, 111, 83, 23);
        contentPane.add(btnClear);
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                txtReceive.setText("");
            }
        });
    }

    public void connect() {

        try {
            // 0-5. 표준입력 장치 이용하여 접속할 서버의 host 명, Port 번호 읽어 오기
            String host = txtIP.getText();

            int port = Integer.parseInt(txtPort.getText());

            // 1. (서버) 서버소켓 객체 생성 및 포트 활성화
            // 2. (서버) HashMap 객체 생성(클라이언트들의 전용 출력스트림 저장)
            // 3. (서버) 클라이언트 접속 대기 - accept( )
            // 4. (클라이언트) 서버에 접속 요청
            csk = new Socket(host, port);
            System.out.println("접속 완료... Socket opened..");
            System.out.println("\n클라이언트 Port 번호: " + csk.getLocalPort() + "\n서버 주소:" + csk.getInetAddress() + "\n서버 포트:"
                    + csk.getPort());

            txtStatus.setText(csk.getInetAddress()+ "," + csk.getPort() + "접속완료");
// 시험문제
// 클라이언트의 port번호를 읽어오는 코드는getLocalProt()
// 서버의 주소를 읽어오는 코드는 getInetAddress()

            // 5. (서버) 연결 승인 및 각 클라이언트들과 통신할 Socket 객체 return
            // 6. (서버) 클라이언트와 통신을 위한 자식 스레드 생성 및 start
            // 이때, 자식 스레드 생성자에 Socket객체와 HashMap 객체 전달
            // 7. (서버) I/O Stream 객체 생성

            // 8. (클라이언트) 서버와 통신을 위한 I/O Stream 객체 생성
            // (1) 서버로 메시지를 송신하기 위한 출력 Stream 객체 생성
            os = csk.getOutputStream();
            writeStream = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            // 시험문제
            // getOutputStrema()은 소켓이 가지고 있는 함수이다.

            // (2) 서버의 메시지를 수신하기 위한 입력 Stream 객체 생성
            is = csk.getInputStream();
            readStream = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            // 9. (클라이언트) ID 전송
            // (1) 사용할 ID를 표준입력 장치로부터 읽어 옴
            String id = txtID.getText();

            // (2) 읽어온 ID를 서버로 전달
            writeStream.println(id);
            writeStream.flush();

            // 10. (서버) 클라이언트의 ID 읽음

            // 11. (클라이언트) ReadThread 생성 및 start
            ReadThread th = new ReadThread(csk, readStream);
            th.start();

            // 12. (서버) 접속된 클라이언트의 ID를 broadcast
            // 13. (서버) HashMap에 클라이언트 정보 저장(id, PrintWriter 객체)

        } catch (Exception e) {
            if (endflag)
                e.printStackTrace();
        }

    }

    public void close() {
        endflag = true;
        // 클라이언트 접속 종료 코드

        try {
            if (writeStream != null)
                writeStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (readStream != null)
                readStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (csk != null)
                csk.close();
        } catch (Exception e) {
            if(!endflag) {
                e.printStackTrace();
            }
        }
        txtStatus.setText("연결 종료 되었습니다.");

    }
}