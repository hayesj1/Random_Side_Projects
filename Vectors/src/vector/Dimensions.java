package vector;

public enum Dimensions {
	
	ONEDIM(1),
	TWODIM(2),
	THREEDIM(3);
	
	private Vector i;
	private Vector j;
	private Vector k;
	private int numDimensions;
	private VectorFactory vf;
	
	private Dimensions(int numDims) {
		numDimensions = numDims;
	}
	public void init() {
		if (numDimensions == 1) {
			vf = new VectorFactory(numDimensions);
			i = vf.getI();
			j = null;
			k = null;
		}else if (numDimensions == 2) {
			vf = new VectorFactory(numDimensions);
			i = vf.getI();
			j = vf.getJ();
			k = null;
		} else if (numDimensions >= 3) {
			vf = new VectorFactory(numDimensions);
			i = vf.getI();
			j = vf.getJ();
			k = vf.getK();
		} else {
			vf = new VectorFactory(2);
			i = vf.getI();
			j = vf.getJ();
			k = null;
		}
	}

	public Vector getI() { return i; }
	public Vector getJ() { return j; }
	public Vector getK() { return k; }
	public int getNumDimensions() { return numDimensions; }
}
