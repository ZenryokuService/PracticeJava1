package jp.basic.description;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MtButton extends JButton
        implements ActionListener {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLE = "*";
    public static final String DIVIDE = "/";
    public static final String AC = "AC";
    private MtLabel lbl;
    public MtButton(String mes) {
        setText(mes);
        setBackground(Color.RED);
        addActionListener(this);
    }

    public MtButton(String mes, MtLabel lbl) {
        this.lbl = lbl;
        setText(mes);
        //setBackground(Color.RED);
        addActionListener(this);
    }
    
    public boolean isOpe(String ope) {
        if (isEmpty(ope)) {
            return false;
        }
        switch (ope) {
            case PLUS:
                System.out.println("PLUS");
                break;
            case MINUS:
                System.out.println("MINUS");
                break;
            case MULTIPLE:
                System.out.println("MULTIPLE");
                break;
            case DIVIDE:
                System.out.println("DIVIDE");
                break;
            default:
                System.out.println("想定外の値です。" + ope);
                return false;
        }
        return true;
    }
    public boolean isNumber(String num) {
        if (isEmpty(num)) {
            return false;
        }
        return num.matches("[0-9]");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String tmp = getText();
        if (isNumber(tmp)) {
            // 入力値が数字
            if (lbl.exeCalc()) {
                // 計算を行う
                int ans = lbl.calc(tmp);
                lbl.setText(ans, true);
            } else {
                // 入力値をラベルにセット
                lbl.setText(tmp);
            }
        } else if (isOpe(tmp)) {
            // 演算子をセット
            lbl.setOpe(tmp);
        }
        if (AC.equals(tmp)) {
            lbl.clear();
        }
    }
    
    public boolean isEmpty(String st) {
        if (st == null || "".equals(st)) {
            return true;
        }
        return false;
    }
}
