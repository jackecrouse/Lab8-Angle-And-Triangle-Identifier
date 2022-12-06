package geometry_objects.angle;

import geometry_objects.angle.comparators.AngleStructureComparator;

import java.util.Comparator;

import equivalence.EquivalenceClasses;
import equivalence.LinkedEquivalenceClass;

/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */

public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
	public AngleEquivalenceClasses(Comparator<Angle> comparator) 
	{
		super(comparator);
	}	
	
	public AngleEquivalenceClasses()
	{
		super(new AngleStructureComparator());
	}
	
	/**
	 * Creates 
	 * @param element
	 * @return true if addition was successful
	 */
	@Override
	public boolean add(Angle element) {
		if (element == null) return false;

		int index = indexOfClass(element);
		if (index == -1) {
			AngleLinkedEquivalenceClass c = new AngleLinkedEquivalenceClass();
			c.demoteAndSetCanonical(element);
			return _classes.add(c);
		}
		
		return _classes.get(index).add(element);
		
	}
	
	/**
	 * Returns the index a particular class is located at; Returns -1 if
	 * the input is null or the class is not contained in the list
	 * @param element
	 * @return index of particular class
	 */
	@Override
	protected int indexOfClass(Angle element) {
		//check if element is null
		if (element == null) return -1;
		//index location element belongs to
		for (int i = 0; i < _classes.size(); i++) {
			//check if target is equal to current item
			if (_classes.get(i).belongs(element)) return i;
		}
		//not contained
		return -1;
	}
	
}
