import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;

public class GBBFrame extends JFrame  {
	
	Container contentPane;
	int judgement = 0;
	
		
	ImageIcon [] gbbImage = { new ImageIcon("images/gawi.jpg"),
							  new ImageIcon("images/bawi.jpg"),
							  new ImageIcon("images/bo.jpg")};		//�̹��� ��ü�ֱ�		
	
	
	ImageIcon [] gbbCheckImage = { new ImageIcon("images/gawiCheck.jpg"),
			  				  	   new ImageIcon("images/bawiCheck.jpg"),
			  				       new ImageIcon("images/boCheck.jpg")};	  //üũ�� �̹��� ��ü�ֱ�	
	
	JRadioButton [] radioBtn = new JRadioButton[3]; //�̰� GBBFrame�� ������ �����ʾ��� �ȵ�.
	
	JLabel winner = new JLabel();
	JLabel loser = new JLabel();
	JLabel whowinner = new JLabel();
	JLabel wholoser = new JLabel();
	JLabel notice = new JLabel();
	JLabel result1 = new JLabel();
	String result2 = new String();  //(�Ͻ�)
	String result3 = new String();  //(����)
	String result4 = new String();  //(��������)
	String result5 = new String();  //(�̱�Ƚ��,��Ƚ��),(����,����,������ �̱��� �����)
	
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
		
		setTitle("�� �� �� ����");
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
				radioBtn[i].setSelectedIcon(gbbCheckImage[i]);  //���⼭ �ٲ��ִ°�.
				radioBtn[i].setBorderPainted(true); //üũ�Ȱ� ����  
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
			
			btn = new JButton("��� ����(�����)");
			btn.setToolTipText("��躸��");
			ToolTipManager k = ToolTipManager.sharedInstance();
			k.setDismissDelay(1000); //ƫ���� �����ִ� �ð��� 1000ms��  
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
			JTA.setEditable(false);//�̰� �����Ұ��ϰ��ϴ°�
			add(JSP);	
			
			addMouseListener(new MyMouseListener());
		}
		void Search(){
			JTA.setText(JTA.getText()+"\n"+result2+" "+result3+" ��  "+result4+"���� �̱�");
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
			if(b.getText().equals("��� ����(�����)")){
				b.setText(result5);
			}else{
				b.setText("��� ����(�����)");   //��踦 �ѳ��� ���¿��� ������� �ϸ� ��谡 ������ �ȵ�.
			}
		}
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton j = (JRadioButton)e.getSource();
			
			Calendar now = Calendar.getInstance();  //�ð� 
			
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH)+1;
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR);
			int minute = now.get(Calendar.MINUTE);
			
			int userChoice = 0;
			
			if(j == radioBtn[0]){
				userChoice = 0; //����
			}else if(j == radioBtn[1]){
				userChoice = 1; //����
			}else if(j == radioBtn[2]){
				userChoice = 2; //��
			}
			
			int comChoice = (int)(Math.random()*3); 
			
			
			result1.setText("���� ��¶�");
			
			if(judgement == 0){//���� 
				
				if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){ //���ʰ� �����ΰ��
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					
					notice.setText("USER �� ����");
					
					judgement = 1;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){ //���ʰ� ���ΰ��
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM �� ����");
					
					judgement = 2;
					
				}else{  //�����϶�? �׳� ������ ���ʷ� �ߴ�. 
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("���_�ٽ� ����!");
					
					judgement = 0; //�׳� ���� �̴�θ� �����ϰ� ���Ƽ� �ٽ� �� �����ε���
					
				}
			}else if(judgement == 1){  //������ ���� �̱� ��Ȳ
				
				
				if(userChoice == comChoice){  //1. ���� ������ �߰��ϰ� judgement 0���� �ش�.
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("USER�� �¸�");
					
					userwin += 1;
					printwinner = "user";
					
					if(userChoice == 0){ //����
						usergawiwin += 1;
						printchoice = "����";
					}else if(userChoice == 1){ //����
						userbawiwin += 1;
						printchoice = "����";
					}else if(userChoice == 2){ //��
						userbowin += 1;
						printchoice = "��";
					}
										
					judgement = 3;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){//2. ���� ���� ��Ȳ�� ������Ű�� judgement 2�� �ش�.
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM �� �ݰ�");
					judgement = 2;
					
				}else if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){//3. �ٸ��ɷ� �ٽ� ������ �̱�� �ִ� ��Ȳ�� ���� judgement�� 1�� �ش�(���൵ �������).
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);
					wholoser.setText("COM");
					notice.setText("USER �� �� ����");
					judgement = 1;
				}
				
			}else if(judgement == 2){  //���� ������ �̱� ��Ȳ
								
				if(userChoice == comChoice){  //1. ���� ���ͽ� �߰��ϰ� judgement 0���� �ش�.
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");
					notice.setText("COM�� �¸�");
					
					comwin += 1;
					printwinner = "com";
					
					if(userChoice == 0){ //����
						usergawilose += 1;
						printchoice = "����";						
					}else if(userChoice == 1){ //����
						userbawilose += 1;
						printchoice = "����";
					}else if(userChoice == 2){ //��
						userbolose += 1;
						printchoice = "��";
					}
					
					judgement = 3; 
					
				}else if((userChoice==0&&comChoice==2)||(userChoice==1&&comChoice==0)||(userChoice==2&&comChoice==1)){  //2. ���� ������ ��Ȳ�� ������Ű�� judgement 1�� �ش�.
					winner.setIcon(gbbImage[userChoice]);
					whowinner.setText("USER");
					loser.setIcon(gbbImage[comChoice]);  
					wholoser.setText("COM");
					notice.setText("USER �� �ݰ�");
					judgement = 1;
					
				}else if((userChoice==0&&comChoice==1)||(userChoice==1&&comChoice==2)||(userChoice==2&&comChoice==0)){  //3. �ٸ��ɷ� �ٽ� ���Ͱ� �̱�� �ִ� ��Ȳ�� ���� judgement�� 2�� �ش�(���൵ �������).
					winner.setIcon(gbbImage[comChoice]);
					whowinner.setText("COM");
					loser.setIcon(gbbImage[userChoice]);
					wholoser.setText("USER");  
					notice.setText("COM �� ����");
					judgement = 2;
				}
			
			}if(judgement == 3){  //����Ʈ�ϴ� �� ó���� else if�� ���� �ٷ� ��µ� �ƴϿ���.
				result1.setText("user �� :"+userwin+" user �� :"+comwin);
				
				result2=(year+"��"+month+"��"+day+"��"+hour+"��"+minute+"��");  //�Ͻ�
				result3=(printwinner); //����
				result4=(printchoice);  //��������
				result5=("USER�̱�Ƚ��: "+userwin+" ��Ƚ��: "+comwin+"  USER���� Win:"+usergawiwin +" Lose:"+ usergawilose+" USER����Win:"+ userbawiwin+" Lose:"+userbawilose+" USER��Win:"+ userbowin+" Lose:"+userbolose);
								
				judgement = 0; //�ʱ�ȭ
				searchPanel.Search();
			}
		}
	}
	public static void main(String[] args) {
		new GBBFrame();
	}
}