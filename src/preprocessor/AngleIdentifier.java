package preprocessor;

import java.util.ArrayList;
import java.util.List;
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
		for(Segment segment1: _segments.keySet()) {
			for(Segment segment2: _segments.keySet()) {
				if (segment1.getPoint1() == segment2.getPoint1() ||
					segment1.getPoint1() == segment2.getPoint2() ||
					segment1.getPoint2() == segment2.getPoint1() ||
					segment1.getPoint2() == segment2.getPoint2()) {
					Angle a = new Angle(segment1, segment2);

					boolean newLEQ = true;
					for(var angleLEQ: _angles.getClasses())
					{
						if(angleLEQ.belongs(a)) { angleLEQ.add(a); newLEQ = false;}
					}
					if(newLEQ == true) {
						AngleLinkedEquivalenceClass newEquiv = new AngleLinkedEquivalenceClass();
						newEquiv.demoteAndSetCanonical(a);
					}
				}
			}
		}
		
	}
	
}
