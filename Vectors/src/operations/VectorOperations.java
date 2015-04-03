package operations;

import vector.Dimensions;
import vector.Vector;
import vector.VectorFactory;

public class VectorOperations {

	private VectorFactory vf = null;
	private Dimensions dim = null;
	public VectorOperations(Dimensions dimension) {
		vf = new VectorFactory(dimension.getNumDimensions());
		dim = vf.getDimension();
	}
	public VectorOperations() { this(Dimensions.TWODIM); }
	
	public Vector add(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector(vf.getDimension(), (vec1.getX() + vec2.getX()), null, null);
		case TWODIM:
			return vf.buildVector(vf.getDimension(), (vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), null);
		case THREEDIM:
			return vf.buildVector(vf.getDimension(), (vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), (vec1.getZ() + vec2.getZ()));
		default:
			return vf.buildVector(vf.getDimension(), (vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), null);
		}
	}
	public Vector subtract(Vector vec1, Vector vec2) { return add(vec1, negate(vec2)); }
	public Vector dotProduct(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector(vf.getDimension(), vec1.getX() * vec2.getX(), null, null);
		case TWODIM:
			return vf.buildVector(vf.getDimension(), vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), null);
		case THREEDIM:
			return vf.buildVector(vf.getDimension(), vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), vec1.getZ() * vec2.getZ());
		default:
			return vf.buildVector(vf.getDimension(), vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), null);
		}
	}
	public Vector crossProduct(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector(vf.getDimension(), (vec1.getY() * vec2.getZ() - vec2.getY() * vec1.getZ()), null, null);
		case TWODIM:
			return vf.buildVector(vf.getDimension(), (vec1.getY() * vec2.getZ() - vec2.getY() * vec1.getZ()), (vec1.getZ() * vec2.getX() - vec2.getZ() * vec1.getX()), null);
		case THREEDIM:
			return vf.buildVector(vf.getDimension(), (vec1.getY() * vec2.getZ() - vec2.getY() * vec1.getZ()), (vec1.getZ() * vec2.getX() - vec2.getZ() * vec1.getX()), (vec1.getX() * vec2.getY() - vec2.getX() * vec1.getY()));
		default:
			return vf.buildVector(vf.getDimension(), (vec1.getY() * vec2.getZ() - vec2.getY() * vec1.getZ()), (vec1.getZ() * vec2.getX() - vec2.getZ() * vec1.getX()), null);
		}
	}
	public Vector scalarProduct(Vector vec, double multiplier) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector(vf.getDimension(), vec.getX() * multiplier, null, null);
		case TWODIM:
			return vf.buildVector(vf.getDimension(), vec.getX() * multiplier, vec.getY() * multiplier, null);
		case THREEDIM:
			return vf.buildVector(vf.getDimension(), vec.getX() * multiplier, vec.getY() * multiplier, vec.getZ() * multiplier);
		default:
			return vf.buildVector(vf.getDimension(), vec.getX() * multiplier, vec.getY() * multiplier, null);
		}
	}
	public Vector scalarProduct(Vector vec, int multiplier) { return this.scalarProduct(vec, multiplier * 1.0); }
	private Vector negate(Vector vec1) { return scalarProduct(vec1, -1); }
}