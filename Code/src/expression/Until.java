package expression;

import java.util.ArrayList;

public class Until extends Expression {
	Expression condition;
	ArrayList <Expression> body;
	public Until(Expression condition, ArrayList <Expression> body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "";
	}
}