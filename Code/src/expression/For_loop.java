package expression;

import java.util.ArrayList;

public class For_loop extends Expression {
	double counter;
	ArrayList <Expression> body;
	public For_loop(String counter, ArrayList <Expression> body) {
		this.counter = Double.parseDouble(counter);
		this.body = body;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < counter; i++) {
			for (Expression e: body) {
				result +=   e.toString() + "\n";
			}
		}
		return result;
	}
}