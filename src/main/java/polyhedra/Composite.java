package polyhedra;

import java.util.Scanner;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;

/**
 * A Composite Polyhedron. Polyhedra of this type are built from
 * other Polyhedra. This,in theory, can include Composite objects
 * composed of other (nested) Composite objects.
 *
 * @author Dillon Sapp
 */
public class Composite extends Polyhedron
    implements Cloneable, Iterable<Polyhedron>
{

    /**
     * Collection of polyhedra of which this composite polyhedron is composed.
     */
    private List<Polyhedron> allPolyhedra;

    /**
     * Default Composite Constructor.
     */
    public Composite()
    {
        super("Composite");

        allPolyhedra = new Vector<Polyhedron>();
    }

    /**
     * Composite Copy Constructor.
     *
     * @param src source Composite object to copy
     *
     * @TODO complete this function
     */
    public Composite(Composite src)
    {
        super("Composite");

        allPolyhedra = new Vector<Polyhedron>();
        
        Iterator<Polyhedron> it = src.iterator();
        
        while (it.hasNext())
        {
        	this.add(it.next());
        }
        
    }

    /**
     * Add a cloned copy of a Polyhedron to the `Composite` collection.
     *
     * @param toAdd is cloned and the copy is added
     *
     * @TODO complete this function
     */
    public void add(Polyhedron toAdd)
    {
    	this.allPolyhedra.add(toAdd.clone());
    	
    	this.boundingBox.merge(toAdd.getBoundingBox());
    }

    /**
     * Read all component polyhedra.
     *
     * @param scanner input source
     *
     * @TODO complete this function
     */
    public void read(Scanner scanner)
    {
    	while (scanner.hasNext())
    	{
    		String numPoly = scanner.nextLine();
    		
    		this.add(PolyhedronFactory.createAndRead(scanner));
    	}	
    }

    /**
     * Iterate through all sub-polyhedra, scale them, and update all
     * bounding boxes.
     *
     * @param scalingFactor scaling factor
     *
     * @TODO complete this function
     */
    public void scale(double scalingFactor)
    {
    	allPolyhedra = CreatePolyhedra.duplicateAndScale(allPolyhedra, scalingFactor);
    	
    	this.boundingBox.scale(scalingFactor);
    }

    /**
     * Retrieve the number of Polyhedra.
     *
     * @return the number of Polyhedra that comprise this Composite object
     */
    public int size()
    {
        return allPolyhedra.size();
    }

    @Override
    public Iterator<Polyhedron> iterator()
    {
        return allPolyhedra.iterator();
    }

    @Override
    public Polyhedron clone()
    {
        return new Composite(this);
    }

    /**
     * "Print" all polyhedra.
     *
     * @return String containing all component _Polyhedra_ objects
     *
     * @TODO complete this function
     */
    @Override
    public String toString()
    {
    	
    	String CompositeString = String.format("%s%d polyhedra \n", 
    			super.toString(), 
    			allPolyhedra.size());
        
        for (Polyhedron it : allPolyhedra)
        {
        	CompositeString.concat(it.toString());
        	CompositeString.concat("\n");
        }
        
        return CompositeString;
    }
}

