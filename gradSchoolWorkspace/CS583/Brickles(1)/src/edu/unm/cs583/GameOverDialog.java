package edu.unm.cs583;
import java.awt.*;
import java.awt.event.*;

/**
 *This modal dialog signals the end of the game
 *@author John D. McGregor
 *@version 1.0
 */
public class GameOverDialog extends Dialog implements ActionListener {
    protected Frame _theFrame;
    protected Button _theButton;

    /**
     *The constructor sets attributes of the dialog and sets the window to be visible
     *@param frame The owner of this dialog
     *@param str The string to be displayed
     *@param modal If true then modal
     */
    public GameOverDialog(Frame frame, String str, boolean modal) {
        super(frame, str, true);
        _theButton = new Button("OK");
        _theButton.setSize(80, 60);
        _theButton.addActionListener(this);
        setSize(150, 150);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        add(new Label("Click OK to exit game", Label.CENTER));
        add(_theButton);
        setVisible(true);
    }

    /**
     *Method is invoked wheneverthe ActionListener detects an event
     *@param e is the event detected
     */
    @Override
	public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == _theButton)
            System.exit(0);
    }
}
