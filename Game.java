import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Game{

    private List<String> word_tr = new ArrayList<>();
    private List<String> word_eng = new ArrayList<>();
    private int point;
    private int index;

    public Game(String engFile, String trFile){

        readEng(engFile);
        readTr(trFile);
        this.point = 0;
        this.index = 0; 
    }

    private void readEng(String engFile){

        File file = new File("english.txt");

        try{

        Scanner sc= new Scanner(file);

        while(sc.hasNextLine()){ 
            
            String temp = sc.nextLine();

            word_eng.add(temp);
         
        }

        sc.close();

        }catch(Exception e){

        e.printStackTrace();

    }

    }

    private void readTr(String engFile){

        File file = new File("turkish.txt");

        try{

        Scanner sc= new Scanner(file);

        while(sc.hasNextLine()){ 
            
            String temp = sc.nextLine();
            word_tr.add(temp);
         
        }

        sc.close();
       
        }catch(Exception e){

            e.printStackTrace();

        }

    }    

    public String randomWordChooser(){

        Random random = new Random();

        if(word_eng.isEmpty()){

            System.out.println("Empty list");
            return null;
        }

        else{

            index = random.nextInt(word_eng.size());
            
            return word_eng.get(index);

        }

    }

    public String getTurkishWord(){

        return word_tr.get(index);

    }

    public int getPoint(){

        return point;

    }    

    public void pointIncreaser(){

        point++;

    }

    public void pointDecreaser(){

        point--;

    }

    public void start(){
        
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String input = JOptionPane.showInputDialog("Kaç kez soru görmek istersiniz?");
    
            int numQuestions = 0;
            try {
                numQuestions = Integer.parseInt(input);
                if(numQuestions <= 0)
                    throw new IllegalArgumentException("Soru sayısı 0 veya negatif olamaz");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Lütfen geçerli bir sayı girin!");
                System.exit(0);
            }
            catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
    
            // Set up the initial GUI panel
            JFrame frame = new JFrame("ENG-TR");
            JPanel panel = new JPanel();
            JLabel outputLabel = new JLabel("", SwingConstants.CENTER);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.BLACK);

            JLabel puanLabel = new JLabel("Puan: 0"); // Başlangıçta puanı sıfır olarak ayarlayın
            puanLabel.setFont(new Font("Courier New", Font.BOLD, 18));
            puanLabel.setForeground(Color.GREEN); // Yazı rengini ayarlayın

            outputLabel.setFont(new Font("Courier New", Font.BOLD, 18)); // 18 piksel yazı boyutu
            outputLabel.setOpaque(true); // Arka planın opak olmasını sağlar
            outputLabel.setBackground(Color.DARK_GRAY);
            outputLabel.setForeground(Color.CYAN);

            JRadioButton radioButton1 = new JRadioButton("");
            radioButton1.setFont(new Font("Courier New", Font.BOLD, 18));

            JRadioButton radioButton2 = new JRadioButton("");
            radioButton2.setFont(new Font("Courier New", Font.BOLD, 18));

            JRadioButton radioButton3 = new JRadioButton("");
            radioButton3.setFont(new Font("Courier New", Font.BOLD, 18));

            JRadioButton radioButton4 = new JRadioButton("");
            radioButton4.setFont(new Font("Courier New", Font.BOLD, 18));

            radioButton1.setForeground(Color.ORANGE); // Yazı rengi
            radioButton1.setBackground(Color.DARK_GRAY); // Arka plan rengi
            radioButton1.setOpaque(true);

            radioButton2.setForeground(Color.ORANGE); // Yazı rengi
            radioButton2.setBackground(Color.DARK_GRAY); // Arka plan rengi
            radioButton2.setOpaque(true);

            radioButton3.setForeground(Color.ORANGE); // Yazı rengi
            radioButton3.setBackground(Color.DARK_GRAY); // Arka plan rengi
            radioButton3.setOpaque(true);

            radioButton4.setForeground(Color.ORANGE); // Yazı rengi
            radioButton4.setBackground(Color.DARK_GRAY); // Arka plan rengi
            radioButton4.setOpaque(true);

            ButtonGroup buttonGroup = new ButtonGroup();
    
            panel.add(outputLabel);
            panel.add(Box.createVerticalStrut(15));
            panel.add(radioButton1);
            panel.add(radioButton2);
            panel.add(radioButton3);
            panel.add(radioButton4);  
            
            buttonGroup.add(radioButton1);
            buttonGroup.add(radioButton2);
            buttonGroup.add(radioButton3);
            buttonGroup.add(radioButton4);

            panel.add(Box.createVerticalStrut(30));
            panel.add(puanLabel); // Panelin alt kısmına puanLabel'ı ekleyin
    
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setBounds(650, 300, 405,205);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    
            int puan = 0;

            // Soruyu belirtilen sayıda kez gösterin
            for (int i = 0; i < numQuestions; i++) {
               
                Random rand = new Random();
                int dogruCevap = rand.nextInt(4) + 1 ;
                int sonuc = soruSorucu(panel, outputLabel,puanLabel, radioButton1, radioButton2, radioButton3, radioButton4, buttonGroup,dogruCevap);
                puan += sonuc;
                puanLabel.setText("Puan: " + puan);

            }
 
            JOptionPane.showMessageDialog(null ,"Tekrar oynamak için yeniden başlatın !\nPuanınız : " +puan, "OYUN BİTTİ", JOptionPane.INFORMATION_MESSAGE);
    
        }

        public int soruSorucu(JPanel panel, JLabel outputLabel,JLabel puanLabel, JRadioButton radioButton1, JRadioButton radioButton2, JRadioButton radioButton3, JRadioButton radioButton4, ButtonGroup buttonGroup,int dogruCevap){
    
            Game start = new Game("english.txt", "turkish.txt");

            String wordEng,temp_eng_1,temp_eng_2,temp_eng_3;
            String wordTr,temp_tr_1,temp_tr_2,temp_tr_3;

            wordEng = start.randomWordChooser();
            wordTr = start.getTurkishWord();
            temp_eng_1 = start.randomWordChooser();
            temp_tr_1 = start.getTurkishWord();
            temp_eng_2 = start.randomWordChooser();
            temp_tr_2 = start.getTurkishWord();
            temp_eng_3 = start.randomWordChooser();
            temp_tr_3 = start.getTurkishWord();         

            while(wordEng.equals(temp_eng_1) || wordEng.equals(temp_eng_2) || wordEng.equals(temp_eng_3) || temp_eng_1.equals(temp_eng_2) || temp_eng_1.equals(temp_eng_3) || temp_eng_2.equals(temp_eng_3)){
                wordEng = start.randomWordChooser();
                wordTr = start.getTurkishWord();
                temp_eng_1 = start.randomWordChooser();
                temp_tr_1 = start.getTurkishWord();
                temp_eng_2 = start.randomWordChooser();
                temp_tr_2 = start.getTurkishWord();
                temp_eng_3 = start.randomWordChooser();
                temp_tr_3 = start.getTurkishWord();
            }

            outputLabel.setText("The word is : " + wordEng);
            
            if(dogruCevap==1){
                radioButton1.setText(wordTr);
                radioButton2.setText(temp_tr_1);
                radioButton3.setText(temp_tr_2);
                radioButton4.setText(temp_tr_3);
            }

            else if(dogruCevap==2){
                radioButton1.setText(temp_tr_1);
                radioButton2.setText(wordTr);
                radioButton3.setText(temp_tr_2);
                radioButton4.setText(temp_tr_3); 
            }

            else if(dogruCevap==3){
                radioButton1.setText(temp_tr_1);
                radioButton2.setText(temp_tr_2);
                radioButton3.setText(wordTr);
                radioButton4.setText(temp_tr_3); 
            }
            else{           
                radioButton1.setText(temp_tr_1);
                radioButton2.setText(temp_tr_2);
                radioButton3.setText(temp_tr_3); 
                radioButton4.setText(wordTr);         
            }

            radioButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(dogruCevap == 1)
                    {
                        start.pointIncreaser();
                    } 
                }    
            });
    
            radioButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(dogruCevap == 2) 
                    {
                        start.pointIncreaser();
                    }        
                }    
            });

            radioButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(dogruCevap == 3)
                    {
                        start.pointIncreaser();
                    } 
                }    
            });
    
            radioButton4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(dogruCevap == 4) 
                    {
                        start.pointIncreaser();
                    }        
                }    
            });

            // Wait for the user to make a selection
            while (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            // Clear the selection for the next question
            buttonGroup.clearSelection();
    
            return start.getPoint();
        }
}