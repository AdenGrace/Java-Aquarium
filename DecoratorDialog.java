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

public class DecoratorDialog extends JDialog  implements ItemListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel p1,p2,p3,p4,p5;
    private JButton changeColor, cancel;
    private ButtonGroup rbg;
    private JRadioButton[] rb;
	private MarineAnimal[] animals;
	private MarineAnimal chosenAnimal;
	private AquaFrame parent;
	private AquaPanel ap;

    public DecoratorDialog(AquaFrame parent, AquaPanel pan, String title)
    {
    	super((AquaFrame)parent,title,true);
    	ap = pan;

		int size = 0;
		for (Swimmable sw: pan.animals) {
			String type = sw.type;
			if (!sw.type.equals("Worm")) {
				size++;
			}
		}

		animals = new MarineAnimal[size];
		int i = 0;
		for (Swimmable sw: pan.animals) {
			String type = sw.type;
			if (!sw.type.equals("Worm")) {
				animals[i] = (MarineAnimal) sw;
				i++;
			}
		}

		this.parent = parent;

    	setSize(550,300);
	
		setBackground(new Color(100,230,255));
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();

		int numAnimals = animals.length;
		p4.setLayout(new GridLayout(numAnimals,1,5,5));
		rbg = new ButtonGroup();
		rb = new JRadioButton[numAnimals];
		i = 0;
		for(MarineAnimal animal: animals)
		{
		    rb[i] = new JRadioButton(animal.getAnimalName(),false);
		    rb[i].addItemListener(this);
		    rbg.add(rb[i]);
		    p4.add(rb[i]);
			i++;
		}

		if (rb.length > 0) {
			rb[0].setSelected(true);
		}

		p3.setLayout(new GridLayout(1,2,5,5));
		changeColor=new JButton("OK");
		changeColor.addActionListener(this);
		changeColor.setBackground(Color.lightGray);
		p3.add(changeColor);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBackground(Color.lightGray);
		p3.add(cancel);
		
		p5.setLayout(new GridLayout(1,2,10,10));
		p5.add(p4);
		p5.add(p2);

		p4.setBorder(BorderFactory.createEmptyBorder(20,80,20,20));
		
		p4.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Choose an animal"),
			    p4.getBorder()));

		setLayout(new BorderLayout());
		add("Center", p5);
		add("South" , p3);
    }
    public void itemStateChanged(ItemEvent e)
    {
		for(int i=0;i<rb.length;i++)
			if(rb[i].isSelected())
		    {
				chosenAnimal = animals[i];
		    	break;
	        }
    }
    public void actionPerformed(ActionEvent e)
    {
 		if(e.getSource() == changeColor)
		{
			// find the animal
			if (chosenAnimal != null) {
				Color color = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
				MarineAnimalDecorator marineAnimalDecorator = new MarineAnimalDecorator(chosenAnimal);
				marineAnimalDecorator.paintFish(color);
			}
			setVisible(false);
		}
		else
		    setVisible(false);
    }
}
