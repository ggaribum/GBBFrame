import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;

public class GBBFrame extends JFrame  {
	
	Container contentPane;
	int judgement = 0;
	
		
	ImageIcon [] gbbImage = { new ImageIcon("images/gawi.jpg"),
							  new ImageIcon("images/bawi.jpg"),
							  new ImageIcon("images/bo.jpg")};		//이미지 객체넣기		
	
	
	ImageIcon [] gbbCheckImage = { new ImageIcon("images/gawiCheck.jpg"),
			  				  	   new ImageIcon("images/bawiCheck.jpg"),
			  				       new ImageIcon("images/boCheck.jpg")};	  //체크된 이미지 객체넣기	
	
	JRadioButton [] radioBtn = new JRadioButton[3]; //이거 GBBFrame에 넣으면 리스너쓸때 안됨.
	
	JLabel winner = new JLabel();
	JLabel loser = new JLabel();
	JLabel whowinner = new JLabel();
	JLabel wholoser = new JLabel();
	JLabel notice = new JLabel();
	JLabel result1 = new JLabel();
	String result2 = new String();  //(일시)
	String result3 = new String();  //(누가)
	String result4 = new String();  //(무엇으로)
	String result5 = new String();  //(이긴횟수,진횟수),(가위,바위,보내서 이긴경우 진경우)
	
	JLabel com = new JLabel();
	JLabel user = new JLabel();
	
	int userwin = 0;
	int comwin = 0;
	
	String printwinner;
	String printchoice;
	
	int usergawiwin = 0;
	int usergawilose = 0;
	int userbawiwin = 0;
	int userbawilose = 0;
	int userbowin = 0;
	int userbolose = 0;
	
	MainPanel mainPanel = new MainPanel();
	FightPanel fightPanel = new FightPanel();
	ResultPanel resultPanel = new ResultPanel();
	SearchPanel searchPanel = new SearchPanel();
	
	JButton btn;
	
	public GBBFrame(){
		contentPane = getContentPane();
		
		setTitle("묵 찌 빠 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(new BorderLayout());
		
		add(mainPanel,BorderLayout.NORTH);
		add(fightPanel,BorderLayout.CENTER);
		add(resultPanel,BorderLayout.SOUTH);
		add(searchPanel,BorderLayout.EAST);
		
		
		setSize(1000,900);
		setVisible(true);
					
	}
	class MainPanel extends JPanel {
		public MainPanel() {
			setBackground(new Color(150,150,150));
			ButtonGroup bg = new ButtonGroup();
			for(int i=0; i<radioBtn.length; i++) {
				radioBtn[i] = new JRadioButton(gbbImage[i]);
				bg.add(radioBtn[i]);
				add(radioBtn[i]);
				radioBtn[i].setSelectedIcon(gbbCheckImage[i]);  //여기서 바꿔주는것.
				radioBtn[i].setBorderPainted(true); //체크된거 음영  
				radioBtn[i].addActionListener(new MyActionListener());
			}
		}
	}
	class FightPanel extends JPanel {
		public FightPanel() {
			setBackground(new Color(250,250,0));
			add(winner);
			add(whowinner);
			add(loser);
			add(wholoser);
			add(notice,BorderLayout.CENTER);
			add(result1,BorderLayout.SOUTH);
			}
	}
	class ResultPanel extends JPanel {
		public ResultPanel() {
			setBackground(new Color(100,100,0));
			
			btn = new JButton("통계 보기(묵찌빠)");
			btn.setToolTipText("통계보기");
			ToolTipManager k = ToolTipManager.sharedInstance();
			k.setDismissDelay(1000); //튤립이 켜저있는 시간을 1000ms로  
			MyButtonListener blistener = new MyButtonListener();
			btn.addActionListener(blistener);
			add(btn);
			}
			
	}
	class SearchPanel extends JPanel {
		
		JTextArea JTA = new JTextArea(20,30);
		JScrollPane JSP = new JScrollPane(JTA);
		
		public SearchPanel() {
			setBackground(new Color(250,0,250));
			JTA.setEditable(false);//이거 수정불가하게하는것
			add(JSP);	
			
			addMouseListener(new MyMouseListener());
		}
		void Search(){
			JTA.setText(JTA.getText()+"\n"+result2+" "+result3+" 가  "+result4+"으로 이김");
		}
	}
	class MyMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==2){
				int r =(int)(Math.random()*256);
				int g =(int)(Math.random()*256);
				int b =(int)(Math.random()*256);
				JPanel p = (JPanel)e.getSource();
				p.setBackground(new Color(r,g,b));
			}
		}
	
	}
	class MyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("통계 보기(묵찌빠)")){
				b.setText(result5);
			}else{
				b.setText("통계 보기(묵찌빠)");   //통계를 켜놓은 상태에서 묵찌빠를 하면 통계가 수정이 안됨.
			}
		}
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton j = (JRadioButton)e.getSource();
			
			Calendar now = Calendar.getInstance();  //시간 
			
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH)+1;
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR);
			int minute = now.get(Calendar.MINUTE);
			
			int userChoice = 0;
			
			if(j == radioBtn[0]){
				userChoice = 0; //가위
			}else if(j == radioBtn[1]){
				userChoice = 1; //바위
			}else if(j == radioBtn[2]){
				userChoice = 2; //보
			}
			
			int comChoice = (int)(Math.random()*3); 
			
			
			result1.setText("승패 출력란");
			
			if(judgement == 0){//연산 
				
				if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){ //위너가 유저인경우
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					
					notice.setText("USER 의 공격");
					
					judgement = 1;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){ //위너가 컴인경우
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM 의 공격");
					
					judgement = 2;
					
				}else{  //동점일때? 그냥 유저를 위너로 했다. 
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("비김_다시 선택!");
					
					judgement = 0; //그냥 써줌 이대로면 무시하고 돌아서 다시 이 루프로들어옴
					
				}
			}else if(judgement == 1){  //유저가 컴을 이긴 상황
				
				
				if(userChoice == comChoice){  //1. 비기면 유저승 추가하고 judgement 0으로 준다.
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("USER의 승리");
					
					userwin += 1;
					printwinner = "user";
					
					if(userChoice == 0){ //가위
						usergawiwin += 1;
						printchoice = "가위";
					}else if(userChoice == 1){ //바위
						userbawiwin += 1;
						printchoice = "바위";
					}else if(userChoice == 2){ //보
						userbowin += 1;
						printchoice = "보";
					}
										
					judgement = 3;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){//2. 만약 컴이 상황을 역전시키면 judgement 2로 준다.
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM 의 반격");
					judgement = 2;
					
				}else if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){//3. 다른걸로 다시 유저가 이기고 있는 상황이 오면 judgement를 1로 준다(안줘도 상관없음).
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("USER 의 재 공격");
					judgement = 1;
				}
				
			}else if(judgement == 2){  //컴이 유저를 이긴 상황
								
				if(userChoice == comChoice){  //1. 비기면 컴터승 추가하고 judgement 0으로 준다.
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM의 승리");
					
					comwin += 1;
					printwinner = "com";
					
					if(userChoice == 0){ //가위
						usergawilose += 1;
						printchoice = "가위";						
					}else if(userChoice == 1){ //바위
						userbawilose += 1;
						printchoice = "바위";
					}else if(userChoice == 2){ //보
						userbolose += 1;
						printchoice = "보";
					}
					
					judgement = 3; 
					
				}else if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){  //2. 만약 유저가 상황을 역전시키면 judgement 1로 준다.
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);  
					wholoser.setText("COM");
					notice.setText("USER 의 반격");
					judgement = 1;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){  //3. 다른걸로 다시 컴터가 이기고 있는 상황이 오면 judgement를 2로 준다(안줘도 상관없음).
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");  
					notice.setText("COM 재 공격");
					judgement = 2;
				}
			
			}if(judgement == 3){  //프린트하는 것 처음에 else if로 위에 바로 줬는데 아니였음.
				result1.setText("user 승 :"+userwin+" user 패 :"+comwin);
				
				result2=(year+"년"+month+"월"+day+"일"+hour+"시"+minute+"분");  //일시
				result3=(printwinner); //누가
				result4=(printchoice);  //무엇으로
				result5=("USER이긴횟수: "+userwin+" 진횟수: "+comwin+"  USER가위 Win:"+usergawiwin +" Lose:"+ usergawilose+" USER바위Win:"+ userbawiwin+" Lose:"+userbawilose+" USER보Win:"+ userbowin+" Lose:"+userbolose);
								
				judgement = 0; //초기화
				searchPanel.Search();
			}
		}
	}
	public static void main(String[] args) {
		new GBBFrame();
	}
}