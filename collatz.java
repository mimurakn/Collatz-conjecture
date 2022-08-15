import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.util.regex.*;
import java.util.*;
import java.text.*;
import java.math.*;

class collatz extends JFrame implements ActionListener{

	JTextArea in;
	JTextField text;
	PrintWriter writer;

	public static void main(String[] args)throws IOException{
		collatz frame = new collatz("Collatz");
		frame.setVisible(true);
	}

	collatz(String title){
		setTitle(title);
		setBounds(10,10,700,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		JLabel label = new JLabel("�P�ȏ�̐����l�𔼊p�œ��͂��Ă��������B");
		label.setForeground(Color.BLACK);
		label.setFont(new Font(Font.SERIF, Font.BOLD,20));
		label.setBounds(0,0,label.getWidth(),label.getHeight());
		panel.add(label);

		in = new JTextArea();

		text = new JTextField(50);

		JButton button = new JButton("����");
		button.addActionListener(this);

		panel.add(text);
		panel.add(button);

		Container contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(in, BorderLayout.SOUTH);
	}
//�����܂œ��͗p�t���[������уe�L�X�g�{�b�N�X�̕\���E�{�^���̉����ɂ�铮����s�w��

	public void actionPerformed(ActionEvent e){

		String input = text.getText();
		System.out.println("input="+input);

//�����܂łŃ{�^�������ɂ���ē��͒l�̎擾(�ϐ�:input)��

//��������input�̐����l�m�F����ѐ��l�v�Z�̎��s��

		String regex = "^[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(input);

		if(match.find()){
			BigInteger inputed = new BigInteger(input);
			System.out.println("�l�̓��͂ɐ������܂����B");

			in.setText("���͂��ꂽ�l:"+text.getText());
			text.setBackground(Color.WHITE);

//�����܂łœ��͒l�̐���擾����
//��������log�t�@�C���̍쐬�E�������݂ƌv�Z

//			DecimalFormat format = new DecimalFormat("0.#");
			String fileName = "collatz.log";

			if(inputed.mod(BigInteger.valueOf(2))==BigInteger.ZERO){

				System.out.println("����");
			}else if((inputed.mod(BigInteger.valueOf(2))).subtract(BigInteger.ONE)==BigInteger.ZERO){
				System.out.println("�");
			}else{
				System.out.println("���s");
			}


			try{
				FileWriter writer = new FileWriter(fileName, true);
				writer.write("\n\n0, " + inputed + "\n");
				writer.close();
			}catch(IOException e1){
				System.out.println("Error!");
				e1.printStackTrace();
			}

			for(int i=1; ;i++){
				if(inputed.subtract(BigInteger.ONE)==BigInteger.ZERO){
					System.out.println(i-1);
					in.append("    �����:"+ (i-1));
					break;
				}else if(inputed.mod(BigInteger.valueOf(2))==BigInteger.ZERO){
					inputed = inputed.divide(BigInteger.valueOf(2));
					System.out.println(i+ " : " +inputed);
					try{
						FileWriter writer = new FileWriter(fileName, true);
						writer.write(i + ", " + inputed + "\n");
						writer.close();
					}catch(IOException e1){
						System.out.println("Error!");
						e1.printStackTrace();
						break;
					}
				}else if((inputed.mod(BigInteger.valueOf(2))).subtract(BigInteger.ONE)==BigInteger.ZERO){
					inputed = inputed.multiply(BigInteger.valueOf(3));
					inputed = inputed.add(BigInteger.valueOf(1));
					System.out.println(i+ " : " +inputed);
					try{
						FileWriter writer = new FileWriter(fileName, true);
						writer.write(i + ", " + inputed + "\n");
						writer.close();
					}catch(IOException e1){
						System.out.println("Error!");
						e1.printStackTrace();
						break;
					}
				}else{
					System.out.println("Error!");
					break;
				}

			}


//�v�Z�����܂�
//����������͒l�̕s���F��
		}else{
			System.out.println("���͂��ꂽ�l�������ł��B");

			in.setText("���͂��ꂽ�l�������ł��B");
			text.setBackground(Color.PINK);
		}
//���͒l�̕s���F�؂����܂�
	}
}