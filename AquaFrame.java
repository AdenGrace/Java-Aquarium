/*
Authors:
Aden Turi - 211419395.

 */

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AquaFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private AquaPanel panel;
    private String[] names = {"Exit", "Image", "Blue", "None", "Help", "Save Object State", "Restore Object State"};
    private JMenu m1, m2, m3, m4;
    private JMenuItem[] mi;
    private JMenuBar mb;
    public MarineAnimalCaretaker marineAnimalCaretaker;
    public ImmobileCaretaker immobileCaretaker;

    public static void main(String[] args) {
        AquaFrame aqua = new AquaFrame();
        aqua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aqua.setSize(800, 600);
        aqua.setVisible(true);
    }

    public AquaFrame() {
        super("my Aquarium");
        marineAnimalCaretaker = new MarineAnimalCaretaker();
        immobileCaretaker = new ImmobileCaretaker();
        createMainPanel();
    }

    public void createMainPanel() {
        panel = new AquaPanel(this);
        add(panel);
        panel.setVisible(true);

        mb = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Background");
        m3 = new JMenu("Help");
        m4 = new JMenu("Memento");
        mi = new JMenuItem[names.length];

        for (int i = 0; i < names.length; i++) {
            mi[i] = new JMenuItem(names[i]);
            mi[i].addActionListener(this);
        }

        m1.add(mi[0]);

        m2.add(mi[1]);
        m2.addSeparator();
        m2.add(mi[2]);
        m2.addSeparator();
        m2.add(mi[3]);

        m3.add(mi[4]);

        m4.add(mi[5]);
        m4.add(mi[6]);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        setJMenuBar(mb);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mi[0])
            destroy();
        else if (e.getSource() == mi[1])
            panel.setBackgr(2);
        else if (e.getSource() == mi[2])
            panel.setBackgr(1);
        else if (e.getSource() == mi[3])
            panel.setBackgr(0);
        else if (e.getSource() == mi[4])
            printHelp();
        else if (e.getSource() == mi[5])
            saveObjectState();
        else if (e.getSource() == mi[6])
            restoreObjectState();
    }

    private void saveObjectState() {
        SaveObjectDialog dial = new SaveObjectDialog(this, panel, "Save Object State");
        dial.setVisible(true);
    }

    private void restoreObjectState() {
        RestoreObjectDialog dial = new RestoreObjectDialog(this, panel, "Restore Object State");
        dial.setVisible(true);
    }

    public void destroy() {
        System.exit(0);
    }

    public void printHelp() {
        JOptionPane.showMessageDialog(this, "Home Work 4\nDesign Patterns");
    }

}
