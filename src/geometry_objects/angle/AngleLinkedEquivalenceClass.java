package geometry_objects.angle;

import geometry_objects.angle.comparators.AngleStructureComparator;

import java.util.Comparator;

import equivalence.LinkedEquivalenceClass;

/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author Jack Crouse and Ian Cho
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{	
	
	public AngleLinkedEquivalenceClass() 
	{
		super(new AngleStructureComparator()); //CHECK
	}
	
	@Override
	public boolean add(Angle ang)
	{
		return false;
	}
	
	@Override
	public boolean removeCanonical()
	{
		return false;
	}
	
	@Override
	public boolean demoteAndSetCanonical(Angle ang)
	{
		return false;
	}
	
	
	@Override public boolean belongs(Angle ang)
	{
		//if(_comparator.compare(_canonical, a
		return false;
	}
}