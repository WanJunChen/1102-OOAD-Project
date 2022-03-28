import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;

public class UML extends JFrame{ 

    private ToolBar toolbar;
    private MenuBar menubar;
    private Canvas canvas;

    //Constructor
    public UML() {  
        // canvas = Canvas.getInstance();
        toolbar = new ToolBar();
        menubar = new MenuBar();
        canvas = Canvas.getInstance();
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(menubar);
        getContentPane().add(toolbar, BorderLayout.WEST);
        getContentPane().add(canvas, BorderLayout.EAST);
    }
    
    

    public static void main(String[] args) { 
        UML mainWindow = new UML();
        mainWindow.setTitle("UML Editor");
        mainWindow.setSize(800, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindow.setVisible(true);
    }
}