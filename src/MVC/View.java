package MVC;

import BoardComponents.Board.Board;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private Board board;
    private JLabel label;

    public View() {
        setLayout(new BorderLayout());
        label = new JLabel("0",SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(50,50));
        this.add(label, BorderLayout.NORTH);
    }

    public void draw(Board board)
    {
        this.board = board;
        paintComponent(this.getGraphics());
        label.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBoard(board,g);
    }

    private void drawBoard(Board board,Graphics g)
    {
        if(board==null) return;
        for(int i=0; i<board.getNumCols(); i++)
        {
            for (int j = 0; j < board.getNumRows(); j++) {
                g.setColor(board.getColor(j,i));
                g.fillRect(40+20*i,50+20*j,25,25);
            }
        }
    }

    public JLabel getLabel(){ return this.label; }
    public void drawLabel(String str)
    {
        label.setText(str);
    }
}
