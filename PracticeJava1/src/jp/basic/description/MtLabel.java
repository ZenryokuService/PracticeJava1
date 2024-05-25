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
        setText(this.view + this.ope);
        setFont(new Font("Arial", Font.PLAIN, 24));
        setHorizontalAlignment(JLabel.RIGHT);
        setBorder(new LineBorder(Color.BLACK));
    }
    
    public void setText(String mes) {
        this.view += mes;
        super.setText(this.view + this.ope);
    }
    
    public void setOpe(String ope) {
        this.ope = ope;
        setText("");
    }
}
