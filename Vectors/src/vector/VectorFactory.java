package vector;

public class VectorFactory {
	
	private Vector i = null;
	private Vector j = null;
	private Vector k = null;
	private Dimensions dimension = null;
	
	public VectorFactory(Integer numDims) {
		switch(numDims) {
		case 3:
			dimension = Dimensions.THREEDIM;
			i = new Vector(dimension, 1.0, 0.0, 0.0);
			j = new Vector(dimension, 0.0, 1.0, 0.0);
			k = new Vector(dimension, 0.0, 0.0, 1.0);
			break;
		case 2:
			dimension = Dimensions.TWODIM;
			i = new Vector(dimension, 1.0, 0.0, null);
			j = new Vector(dimension, 0.0, 1.0, null);
			break;
		case 1:
			dimension = Dimensions.ONEDIM;
			i = new Vector(dimension, 1.0, null, null);
			break;
		default:
			dimension = Dimensions.TWODIM;
			i = new Vector(dimension, 1.0, 0.0, null);
			j = new Vector(dimension, 0.0, 1.0, null);
			break;
		}
	}
	public Vector buildVector(Dimensions dimension, Double magnitude, Double angle) {
		return new Vector(dimension, magnitude, angle);	}
	public Vector buildVector(Dimensions dimension, Double x, Double y, Double z) {
		return new Vector(dimension, x, y, z);	}
	
	public Vector getI() { return i; }
	public Vector getJ() { return j; }
	public Vector getK() { return k; }
	public Dimensions getDimension() { return dimension; }
}
