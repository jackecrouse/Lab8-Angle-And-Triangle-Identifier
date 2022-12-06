package preprocessor;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.AngleLinkedEquivalenceClass;

public class AngleIdentifier
{
	protected AngleEquivalenceClasses _angles;
	protected Map<Segment, Segment> _segments;

	public AngleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested; memoize results for subsequent calls.
	 */
	public AngleEquivalenceClasses getAngles()
	{
		if (_angles != null) return _angles;

		_angles = new AngleEquivalenceClasses();

		try {
			computeAngles();
		} catch (FactException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _angles;
	}

	private void computeAngles() throws FactException
	{
		ArrayList<Segment> segArray = new ArrayList<Segment>(_segments.keySet());
		for(int i = 0; i < _segments.size() - 1; i++)
		{
			Segment s1 = segArray.get(i);
			for(int j = i + 1; j < _segments.size(); j++)
			{
				Segment s2 = segArray.get(j);
				try
				{
					if(!_angles.contains(new Angle(s2, s1)) ||
							!s1.equals(s2) ||
							!s1.hasSubSegment(s2) ||
							!s2.hasSubSegment(s1) ||
							!Segment.overlaysAsRay(s1, s2))
					{
						_angles.add(new Angle(s1, s2)); 
					}
				}
				catch(FactException e)
				{
					
				}
			}
		}
	}
}
