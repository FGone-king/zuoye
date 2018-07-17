package study.table;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asus on 2018/7/17.
 */
public class JTableDemo extends JFrame{
    public JTableDemo () {
        String [] heads = {"id","isCheck","color"};
        Object [][] datas =new Object[100][3];
                for (int i = 0;i < datas.length; i++){
                    datas[i][0] = i;
                    datas[i][1] = (i % 2==0);
                    datas[i][2] = new Color(i,i*2,i*2);
                }
                JTable table = new JTable(datas,heads);
                JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
                //srcoolPane.getviewport().add(table);
                this.add(scrollPane);
                this.setSize(600,800);
                this.setVisible(true);
    }
    public  static  void  main(String [] args){
        JTableDemo demo =new JTableDemo();
    }
}
