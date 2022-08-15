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

		JLabel label = new JLabel("１以上の整数値を半角で入力してください。");
		label.setForeground(Color.BLACK);
		label.setFont(new Font(Font.SERIF, Font.BOLD,20));
		label.setBounds(0,0,label.getWidth(),label.getHeight());
		panel.add(label);

		in = new JTextArea();

		text = new JTextField(50);

		JButton button = new JButton("完了");
		button.addActionListener(this);

		panel.add(text);
		panel.add(button);

		Container contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(in, BorderLayout.SOUTH);
	}
//ここまで入力用フレームおよびテキストボックスの表示・ボタンの押下による動作実行指示

	public void actionPerformed(ActionEvent e){

		String input = text.getText();
		System.out.println("input="+input);

//ここまででボタン押下によって入力値の取得(変数:input)↑

//ここからinputの整数値確認および数値計算の実行↓

		String regex = "^[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(input);

		if(match.find()){
			BigInteger inputed = new BigInteger(input);
			System.out.println("値の入力に成功しました。");

			in.setText("入力された値:"+text.getText());
			text.setBackground(Color.WHITE);

//ここまでで入力値の正常取得完了
//ここからlogファイルの作成・書き込みと計算

//			DecimalFormat format = new DecimalFormat("0.#");
			String fileName = "collatz.log";

			if(inputed.mod(BigInteger.valueOf(2))==BigInteger.ZERO){

				System.out.println("偶数");
			}else if((inputed.mod(BigInteger.valueOf(2))).subtract(BigInteger.ONE)==BigInteger.ZERO){
				System.out.println("奇数");
			}else{
				System.out.println("失敗");
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
					in.append("    操作回数:"+ (i-1));
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


//計算ここまで
//ここから入力値の不正認証
		}else{
			System.out.println("入力された値が無効です。");

			in.setText("入力された値が無効です。");
			text.setBackground(Color.PINK);
		}
//入力値の不正認証ここまで
	}
}