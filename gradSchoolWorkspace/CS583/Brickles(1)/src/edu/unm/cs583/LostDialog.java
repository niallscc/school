package edu.unm.cs583;
import java.awt.*;
import java.awt.event.*;

public class LostDialog extends Dialog implements ActionListener {
    protected Frame _theFrame;
    protected Button _theButton;

    public LostDialog(Frame frame, String str, boolean modal) {
        super(frame, str, true);
        _theButton = new Button("OK");
        _theButton.setSize(70, 50);
        _theButton.addActionListener(this);
        setSize(150, 150);
        setLayout(new FlowLayout(FlowLayout.CENTER, 3, 50));
        add(_theButton);
        setVisible(true);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == _theButton)
            System.exit(0);
    }
}
