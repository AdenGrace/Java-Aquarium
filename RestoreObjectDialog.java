/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RestoreObjectDialog extends JDialog  implements ItemListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel p1,p2,p3,p4,p5;
    private JButton restoreObject, cancel;
    private ButtonGroup rbg, rbg1;
    private JRadioButton[] rb, rb1;
	private MarineAnimal[] animals;
	private Immobile[] plants;
	private MarineAnimalMemento[] marineAnimalMementos;
	private ImmobileMemento[] immobileMementos;
	private MarineAnimal chosenAnimal;
	private Immobile chosenImmobile;
	private MarineAnimalMemento chosenAnimalMemento;
	private ImmobileMemento chosenImmobileMemento;
	private AquaFrame parent;
	private AquaPanel ap;

    public RestoreObjectDialog(AquaFrame parent, AquaPanel pan, String title)
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

		plants = pan.plants.toArray(new Immobile[0]);

		marineAnimalMementos = parent.marineAnimalCaretaker.asArray();
		immobileMementos = parent.immobileCaretaker.asArray();

		this.parent = parent;

    	setSize(550,300);
	
		setBackground(new Color(100,230,255));
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();

		int numAnimals = animals.length + plants.length;
		int numMementos = marineAnimalMementos.length + immobileMementos.length;
		p4.setLayout(new GridLayout(numAnimals,1,5,5));
		rbg = new ButtonGroup();
		rb = new JRadioButton[numAnimals];
		rbg1 = new ButtonGroup();
		rb1 = new JRadioButton[numMementos];

		i = 0;
		for (MarineAnimal animal: animals)
		{
		    rb[i] = new JRadioButton(animal.getAnimalName(),false);
		    rb[i].addItemListener(this);
		    rbg.add(rb[i]);
		    p4.add(rb[i]);
			i++;
		}
		for (Immobile plant: plants) {
			rb[i] = new JRadioButton(plant.getName(),false);
			rb[i].addItemListener(this);
			rbg.add(rb[i]);
			p4.add(rb[i]);
			i++;
		}

		i = 0;
		for (MarineAnimalMemento memento: marineAnimalMementos)
		{
			rb1[i] = new JRadioButton(memento.toString(),false);
			rb1[i].addItemListener(this);
			rbg1.add(rb1[i]);
			p2.add(rb1[i]);
			i++;
		}
		for (ImmobileMemento memento: immobileMementos) {
			rb1[i] = new JRadioButton(memento.toString(),false);
			rb1[i].addItemListener(this);
			rbg1.add(rb1[i]);
			p2.add(rb1[i]);
			i++;
		}

		if (rb.length > 0) {
			rb[0].setSelected(true);
		}

		if (rb1.length > 0) {
			rb1[0].setSelected(true);
		}

		p3.setLayout(new GridLayout(1,2,5,5));
		restoreObject =new JButton("OK");
		restoreObject.addActionListener(this);
		restoreObject.setBackground(Color.lightGray);
		p3.add(restoreObject);
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

		p2.setBorder(BorderFactory.createEmptyBorder(20,80,20,20));

		p2.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Choose memento"),
				p2.getBorder()));

		setLayout(new BorderLayout());
		add("Center", p5);
		add("South" , p3);
    }
    public void itemStateChanged(ItemEvent e)
    {
		for(int i=0;i<animals.length;i++)
			if(rb[i].isSelected())
			{
				chosenImmobile = null;
				chosenAnimal = animals[i];
				break;
			}
		for(int i=animals.length;i< animals.length + plants.length;i++)
			if(rb[i].isSelected())
			{
				chosenImmobile = plants[i - animals.length];
				chosenAnimal = null;
				break;
			}

		for(int i=0;i<marineAnimalMementos.length;i++)
			if(rb1[i].isSelected())
			{
				chosenImmobileMemento = null;
				chosenAnimalMemento = marineAnimalMementos[i];
				break;
			}
		for(int i=marineAnimalMementos.length;i< marineAnimalMementos.length + immobileMementos.length;i++)
			if(rb1[i].isSelected())
			{
				chosenImmobileMemento = immobileMementos[i - marineAnimalMementos.length];
				chosenAnimalMemento = null;
				break;
			}
	}
	public void actionPerformed(ActionEvent e)
    {
 		if(e.getSource() == restoreObject)
		{
			if (chosenAnimal != null && chosenAnimalMemento != null) {
				chosenAnimal.getStateFromMemento(chosenAnimalMemento);
			} else if (chosenImmobile != null && chosenImmobileMemento != null) {
				chosenImmobile.getStateFromMemento(chosenImmobileMemento);
			}
			setVisible(false);
		}
		else
		    setVisible(false);
    }
}
