/*
Authors:
Aden Turi - 211419395.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddPlantDialog extends JDialog  implements ItemListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel p1, p2, p3, p4;
    private JButton ok, cancel;
    private JLabel lbl_sz;
    private JSlider sl_sz;
    private ButtonGroup rbg1;
    private String[] plants = { "Zostera","Laminaria"};
    private String chosenPlant;
    private JRadioButton[] plants_rb;
    private AquaPanel ap;

    public AddPlantDialog(AquaFrame parent, AquaPanel pan, String title)
    {
    	super((AquaFrame)parent,title,true);
    	ap = pan;

    	setSize(550,300);
	
		setBackground(new Color(100,230,255));
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
	
		p1.setLayout(new GridLayout(2,1,10,0));
		lbl_sz = new JLabel("Size of plant",JLabel.CENTER);
		p1.add(lbl_sz);

		sl_sz = new JSlider(20,320);
		sl_sz.setMajorTickSpacing(100);
		sl_sz.setMinorTickSpacing(10);
		sl_sz.setPaintTicks(true);
		sl_sz.setPaintLabels(true);
		p1.add(sl_sz);

		p3.setLayout(new GridLayout(2,1,5,5));
		rbg1 = new ButtonGroup();
		plants_rb =new JRadioButton[plants.length];
		for(int i = 0; i< plants.length; i++)
		{
		    plants_rb[i] = new JRadioButton(plants[i],false);
		    plants_rb[i].addItemListener(this);
		    rbg1.add(plants_rb[i]);
		    p3.add(plants_rb[i]);
		}
		
		plants_rb[0].setSelected(true);

		p2.setLayout(new GridLayout(1,2,5,5));
		ok=new JButton("OK");
		ok.addActionListener(this);
		ok.setBackground(Color.lightGray);
		p2.add(ok);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBackground(Color.lightGray);
		p2.add(cancel);
		
		p4.setLayout(new GridLayout(1,2,10,10));
		p4.add(p3);

		p3.setBorder(BorderFactory.createEmptyBorder(20,80,20,20));
		
		p3.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Choose an plant"),
			    p3.getBorder()));

		setLayout(new BorderLayout());
		add("North" , p1);
		add("Center", p4);
		add("South" , p2);
    }
    public void itemStateChanged(ItemEvent e)
    {
		for(int i = 0; i< plants_rb.length; i++)
			if(plants_rb[i].isSelected())
		    {
				chosenPlant = plants[i];
		    	break;
	        }
    }
    public void actionPerformed(ActionEvent e)
    {
 		if(e.getSource() == ok)
		{
		    ap.addPlant(chosenPlant,sl_sz.getValue());
		    setVisible(false);
		}
		else 
		    setVisible(false);
    }
}
