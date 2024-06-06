package jp.basic.description;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MtLabel extends JLabel {
    private String view;
    private String ope;
    public MtLabel(String mes) {
        this.view = "";
        this.ope = " ";
        setText("");
        setFont(new Font("Arial", Font.PLAIN, 24));
        setHorizontalAlignment(JLabel.RIGHT);
        setBorder(new LineBorder(Color.BLACK));
    }

    public int calc(String num) {
        int left = toNumber(this.view);
        int right = toNumber(num);
        int ans = 0;
        switch (this.ope) {
            case MtButton.PLUS:
                ans = left + right;
                break;
            case MtButton.MINUS:
                ans = left - right;
                break;
            case MtButton.MULTIPLE:
                ans = left * right;
                break;
            case MtButton.DIVIDE:
                double d = left / right;
                ans = (int) Math.round(d);
                break;
            default:
                System.out.println("想定外の値: " + this.ope);
        }
        return ans;
    }
    public boolean exeCalc() {
        return !isEmpty(this.view) && !isEmpty(this.ope.trim());
    }
    public int toNumber(String num) {
        return Integer.parseInt(num.trim());
    }
    public void setText(String mes) {
        this.view += mes;
        super.setText(this.view + this.ope);
    }
    public void setText(int ans, boolean is) {
        this.view = String.valueOf(ans);
        super.setText(this.view + this.ope);
    }

    public void setOpe(String ope) {
        this.ope = ope;
        setText("");
    }
    public boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
    public void debug() {
        System.out.println("view: [" + this.view + "]");
        System.out.println("ope: [" + this.ope + "]");
    }
    
    public void clear() {
        this.view = "";
        this.ope = " ";
        setText("");
    }
}
