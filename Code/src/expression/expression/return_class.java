package expression;

public class return_class  extends Expression {
	

	
	public return_class() {
		
		
	}
	
	@Override
	public String toString() {
		return "(check-sat)\n (get-model)\n";
				//(check-sat)
				//(get-model)
	}
}