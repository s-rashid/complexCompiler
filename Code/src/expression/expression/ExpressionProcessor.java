package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
	List<Expression> list;
	public Map<String,String> values;
	public Map<String,String> array_values;
	public Map<String,String> array_values_string;
	public ArrayList<String> final_input;
	
	public ArrayList<String> Nested_if_condition;
	int counter ;
	public ExpressionProcessor(List<Expression> list) {
		this.list = list;
		this.values = new HashMap<>();
		this.array_values = new HashMap<>();
		this.array_values_string = new HashMap<>();
		this.final_input = new ArrayList<>();
		this.Nested_if_condition =  new ArrayList<>();
		counter = 0;
	}
	
	public List<String> getEvaluationResults(List<Expression> elist){
		List<String> evalution = new ArrayList<>();
		
		if (elist == null)
		{ 
		 elist = list;
		}
		
		for(Expression e: elist) {
			counter++;
			if(e instanceof Variable_Declaration_no_type) {
				
				Variable_Declaration_no_type decl = (Variable_Declaration_no_type) e;
				String input = e.toString();
				this.final_input.add(input);
				//evalution.add(input);
			}
		 else if (e instanceof Until) {
			Until until = (Until) e;
			//System.out.println("Here");
			while (getEvalResult(until.condition).equals("false")) {
			//	System.out.println("Here1" + getEvalResult(until.condition));
			//System.out.println("THe body is " + this.values.get("i"));
				getEvaluationResults(until.body);
			}
		}
		 else if(e instanceof For_loop) {
			//System.out.println("entered");
			String input = e.toString();
			this.final_input.add(input);
			//evalution.add(input);
		}
			else if(e instanceof ARRAY_insertion_with_num) {
				ARRAY_insertion_with_num input = (ARRAY_insertion_with_num) e;
				if(this.array_values.containsKey(input.id+input.pos)) {
					String value_in_array = "(assert (= (store "+input.id+ " " + input.pos + " " +this.array_values.get(input.pos) + " ) " + input.id + "))" ;
					for(int i = 0; i< this.final_input.size(); i++) {
						String from_final_input_list = this.final_input.get(i).trim();
						//System.out.println("THE VALUE IN ARRAY IS" + from_final_input_list );
						//System.out.println("THE FROM FINAL INPUT LSIT IS" + from_final_input_list);
						if(value_in_array.equals(from_final_input_list)) {
							String new_string = e.toString();
							this.final_input.set(i, new_string);
						}
					}
					this.array_values.put(input.id+input.pos, input.value);
					
				}
				else {
					this.final_input.add(input.toString());
				
					this.array_values.put(input.id+input.pos, input.value);
				}
			}
			else if(e instanceof ARRAY_insertion_with_bool) {
				ARRAY_insertion_with_bool input = (ARRAY_insertion_with_bool) e;
				if(this.array_values_string.containsKey(input.id+input.pos)) {
					String value_in_array = "(assert (= (store "+input.id+ " " + input.pos + " " +this.array_values_string.get(input.pos) + " ) " + input.id + "))" ;
					for(int i = 0; i< this.final_input.size(); i++) {
						String from_final_input_list = this.final_input.get(i).trim();
						//System.out.println("THE VALUE IN ARRAY IS" + from_final_input_list );
						//System.out.println("THE FROM FINAL INPUT LSIT IS" + from_final_input_list);
						if(value_in_array.equals(from_final_input_list)) {
							String new_string = e.toString();
							this.final_input.set(i, new_string);
						}
					}
					this.array_values_string.put(input.id+input.pos, input.value);
					
				}
				else {
					this.final_input.add(input.toString());
				
					this.array_values_string.put(input.id+input.pos, input.value);
				}
			}
			else if(e instanceof Bool_greater_then_or_equal_to || e instanceof Bool_less_then_or_equal_to || e instanceof Bool_greater || e instanceof Bool_less_then || e instanceof Bool_and || e instanceof Bool_or || e instanceof Bool_not || e instanceof Bool_equal || e instanceof FunctionInvocWithPrama || e instanceof FuncInvocWithoutParam) {
				String input = e.toString();
				this.final_input.add("(assert " + input + ")");
			}
			else if(e instanceof Variable_Assignment) {
				Variable_Assignment decl = (Variable_Assignment) e;
				if(this.values.containsKey(decl.id)) {
					String id = decl.id;
					String value = this.values.get(decl.id);
					String value_in_array = "(assert (= "+id+" "+ value+"))";
					//System.out.println("THE VALUE IN ARRAY IS " + value_in_array);
					value_in_array.trim();
					for(int i = 0; i< this.final_input.size(); i++) {
						String from_final_input_list = this.final_input.get(i).trim();
						//System.out.println("THE VALUE IN ARRAY IS" + value_in_array);
						//System.out.println("THE FROM FINAL INPUT LSIT IS" + from_final_input_list);
						if(this.stringCompare(value_in_array, from_final_input_list) == 0) {
							//System.out.println("TESTING!@#");
							String new_string = e.toString();
							this.final_input.set(i, new_string);
						}
					}
					
					this.values.put(decl.id, decl.value);
					
				}
				else {
					this.final_input.add(decl.toString());
					this.values.put(decl.id, decl.value);
				}
				
		//	System.out.println("The map values are " + this.values);
				String input = decl.toString();
				//evalution.add(input);
			}
			else if(e instanceof Variable_Declaration_with_value) {
			//System.out.println("HERE");
				Variable_Declaration_with_value decl = (Variable_Declaration_with_value) e;
				this.values.put(decl.id, decl.value);
				String[] split_white_space = decl.toString().split("\n");
				this.final_input.add(split_white_space[0]);
				this.final_input.add(split_white_space[1]);
			//	System.out.println("The map values are " + this.values);
				String input = e.toString();
			//	evalution.add(input);
			}
			else if(e instanceof Assign_to_arth) {
			//	System.out.println("Here ASSIGN TO ARTH");
				Assign_to_arth decl =  (Assign_to_arth) e;
				if(this.values.containsKey(decl.id)) {
					String result = getEvalResult(e);
					this.values.put(decl.id,result);
					this.final_input.add(decl.toString());
				}
				
				else {
					String result = getEvalResult(e);
					//System.out.println("ASsign result is " + result);
					this.values.put(decl.id,result);
					this.final_input.add(decl.toString());
					
				}
				//System.out.println("sss " + counter + "the list size is " + this.list.size());
			}
			
			else if(e instanceof until_to_expression) {
				until_to_expression  decl =  (until_to_expression) e;
				if(this.values.containsKey(decl.id)) {
					String result = getEvalResult(e);
					this.values.put(decl.id,result);
					this.final_input.add(decl.toString());
				}
				
				else {
					String result = getEvalResult(e);
					//System.out.println("ASsign result is " + result);
					this.values.put(decl.id,result);
					this.final_input.add(decl.toString());
					
				}
			}
			
			else if(e instanceof correctness_assignment) {
				correctness_assignment a1 = (correctness_assignment) e;
				//System.out.println("HERE assign");
				String result = getEvalResult(e);
				this.final_input.add(result);
			}
			else if(e instanceof correctness_if_condition) {
				correctness_if_condition a1 = (correctness_if_condition) e;
				//System.out.println("HERE assign");
				String result = getEvalResult(e);
				this.final_input.add(result);
			}
			else if(e instanceof if_condition_to_condition) {
				
				String result = getEvalResult(e);
				String result1 = "";
				//System.out.println("the array is " + this.Nested_if_condition);
				int counter = 0;
				result1 = "( assert (=> " + this.Nested_if_condition.get(this.Nested_if_condition.size() -1);
				for(int i = 0; i< this.Nested_if_condition.size()-1; i++) {
					
					String index = this.Nested_if_condition.get(i);
					//System.out.println("the index is " + index);
					result1 = result1+ "(=> "+  index ;
					counter++;
				}
				result1 = result1 + result;
				for(int i = 0; i< counter; i++) {
					result1= result1 + ")";
				}
				result1 = result1 + "))";
				//System.out.println(result1);
				this.final_input.add(result1);
			}
			else if(e instanceof nested_else) {
			
				String result = getEvalResult(e);
				String result1 = "(assert (ite ";
				String[] result_split = result.split("true");
				result1 = result1 + result_split[0];
				String[] true_split = result_split[1].split("else");
				result1 = result1 + true_split[0];
				result1 = result1+ true_split[1];
				result1 = result1 + "))";
				this.final_input.add(result1);
				//System.out.println("The result is " + result1);
			}
			else if(e instanceof nested_if_else_assignment) {
				
				String result = getEvalResult(e);
				String result1 = "(assert (ite ";
				String[] result_split = result.split("true");
				result1 = result1 + result_split[0];
				String[] true_split = result_split[1].split("else");
				result1 = result1 + true_split[0];
				result1 = result1+ true_split[1];
				result1 = result1 + "))";
				this.final_input.add(result1);
			}
			else if(e instanceof nested_if_assignment_else) {
			
				String result = getEvalResult(e);
				
				String result1 = "(assert (ite ";
				String[] result_split = result.split("true");
				result1 = result1 + result_split[0];
				
				String[] true_split = result_split[1].split("else");
				result1 = result1 + true_split[0];
				result1 = result1+ true_split[1];
				result1 = result1 + "))";
				this.final_input.add(result1);
			}
			else if(e instanceof if_condition_to_assignment) {
				String result = "(assert ";
				 result = result + getEvalResult(e);
				result= result +  " )";
				this.final_input.add(result);
				
			}
			else{
			
				String input = e.toString();
				//String result = getEvalResult(e);
				
				this.final_input.add(input);
			//	System.out.println("HERE in the else "+ counter);
			//	evalution.add(input);
			}
		
			
		}
		
	//	System.out.println("THe final list is " + this.final_input);
		
		evalution = this.final_input;
		
		return evalution;
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	private String getEvalResult(Expression e) {
		String result = "";
		
		if(e instanceof Number) {
			 Number num = (Number) e;
			 result = num.num;
		}
		else if(e instanceof Variable) {
			Variable num = (Variable) e;
			 result = num.id;
		}
		else if (e instanceof Pure_if_else) {
			String e1 = e.toString();
			e1 = e1.replace("assert", "");
			e1 = e1.substring(1, e1.length()-1);
		
			result = "split2" + e1;
			
		}
		else if(e instanceof nested_if_assignment_else) {
			nested_if_assignment_else num = (nested_if_assignment_else) e;
			String else_condition = getEvalResult(num.else_expression);
			String true_condition = num.true_expression.toString();
			result = num.if_expression.toString();
			result = result + " true ";
			if(true_condition.contains("assert")) {
				true_condition = true_condition.replace("assert", "");
				true_condition = true_condition.substring(1, true_condition.length()-1);
				}
			result = result + true_condition;
			result = result + " else ";
			
			
			String[] else_condition_split = else_condition.split("split2");
			
			int counter = 0;
			for(int i = 0; i< this.Nested_if_condition.size(); i++) {
				
				String index = this.Nested_if_condition.get(i);
				//System.out.println("the index is " + index);
				result= result+ "(=> "+  index ;
				counter++;
			}
	
			
			if(else_condition_split.length == 1) {
				result = result +" " +   else_condition;
			}
			else if(else_condition_split.length == 2) {
				result = result + " "+else_condition_split[1];
			}
			else {
				
			}
	
			if(counter > 0) {
			for(int i = 0; i< counter; i++) {
				result= result + ")";
			}
			}
		}
		else if(e instanceof nested_if_else_assignment) {
			nested_if_else_assignment num = (nested_if_else_assignment) e;
			String true_condition = getEvalResult(num.true_expression);
			String else_condition = num.else_expression.toString();
			
			

			result = num.if_expression.toString();
			result = result + " true ";
			
			String[] true_condition_split = true_condition.split("split2");
			
			int counter = 0;
			for(int i = 0; i< this.Nested_if_condition.size(); i++) {
				
				String index = this.Nested_if_condition.get(i);
				//System.out.println("the index is " + index);
				result= result+ "(=> "+  index ;
				counter++;
			}
	
			
			if(true_condition_split.length == 1) {
				result = result +" " +   true_condition;
			}
			else if(true_condition_split.length == 2) {
				result = result + " "+true_condition_split[1];
			}
			else {
				
			}
	
			if(counter > 0) {
			for(int i = 0; i< counter; i++) {
				result= result + ")";
			}
			}
			result = result + " else ";
			
			result = result + else_condition;
			 int counter1 = 0;
			for(int i = 0; i< this.Nested_if_condition.size(); i++) {
				
				String index = this.Nested_if_condition.get(i);
				//System.out.println("the index is " + index);
				result= result+ "(=> "+  index ;
				counter1++;
			}
			
			if(counter1 > 0) {
				for(int i = 0; i< counter1; i++) {
					result= result + ")";
				}
				}
			
			
		}
		else if(e instanceof nested_else) {
			nested_else num = (nested_else) e;
			String true_condition = getEvalResult(num.true_expression);
			
			
			result = num.if_expression.toString();
			result = result + " true ";
			
			String[] true_condition_split = true_condition.split("split2");
			
			int counter = 0;
			for(int i = 0; i< this.Nested_if_condition.size(); i++) {
				
				String index = this.Nested_if_condition.get(i);
				//System.out.println("the index is " + index);
				result= result+ "(=> "+  index ;
				counter++;
			}
	
			
			if(true_condition_split.length == 1) {
				result = result +" " +   true_condition;
			}
			else if(true_condition_split.length == 2) {
				result = result + " "+true_condition_split[1];
			}
			else {
				
			}
	
			if(counter > 0) {
			for(int i = 0; i< counter; i++) {
				result= result + ")";
			}
			}
			result = result + " else ";
			this.Nested_if_condition = new ArrayList<>();
			
			String else_condition = getEvalResult(num.else_expression);
			String[] else_condition_split = else_condition.split("split2");
			
		
			 int counter1 = 0;
			for(int i = 0; i< this.Nested_if_condition.size(); i++) {
				
				String index = this.Nested_if_condition.get(i);
				//System.out.println("the index is " + index);
				result= result+ "(=> "+  index ;
				counter1++;
			}
	
			
			
			if(else_condition_split.length == 1) {
				result = result + " "+else_condition_split[0];
			}
			else if(else_condition_split.length == 2) {
			
				result = result + " "+else_condition_split[1];
			}
			else {
				
			}
			//System.out.println("The coutner length is " + counter);
			if(counter1 > 0) {
			for(int i = 0; i< counter1; i++) {
				result= result + ")";
			}
			}
			
			//System.out.println("The true condition is "+ true_condition);
			//System.out.println("The else condition is " + else_condition);
			
		}
		else if(e instanceof if_condition_to_assignment) {
			if_condition_to_assignment num = (if_condition_to_assignment) e;
			String right_expression = num.right.toString();
			if(right_expression.contains("assert")) {
				right_expression = right_expression.replace("assert", "");
				right_expression = right_expression.substring(2, right_expression.length()-1);
				}
		
			result = "( => "+ num.left + " " + right_expression+ " )"; 
			
		}
		else if(e instanceof if_condition_to_condition) {
			
			if_condition_to_condition num = (if_condition_to_condition) e;
		//	String true_condtition_class = num.true_expression.getClass().toString();
			
			
		
			String true_condition = getEvalResult(num.true_expression);
		
			String[] true_condition_split = true_condition.split("split2");
			
			if(true_condition_split.length == 1) {
				result = true_condition;
			}
			else if(true_condition_split.length == 2) {
				result = true_condition_split[1];
			}
			else {
				//System.out.println("Split more then 0 here" + true_condition_split[1]);
			}
			
			
		
			this.Nested_if_condition.add(num.if_expression.toString());
		
			
		}
		else if(e instanceof correctness_assignment) {
			correctness_assignment num = (correctness_assignment) e;
			String imple = num.implmentation.toString();
			String post = num.postcondition.toString();
		//	System.out.println(imple);
			
			imple = imple.replace("(", "");
			imple = imple.replace(")", "");
			
			post = post.replace("(", "");
			post = post.replace(")", "");
			
			String[] imple_split = imple.split(" ");
			String new_imple = "";
				
			if(imple_split[0].equals("=")) {
				new_imple += imple_split[1] + " " + imple_split[0];
				if(imple_split.length > 3 && imple_split.length <= 5) {
					
						new_imple += " "+imple_split[3]+ " " + imple_split[2] +" " + imple_split[4];
					
				}
				if(imple_split.length == 3) {
					new_imple += " "+imple_split[3];
				}
				
			}
			//String post = num.postcondition;
			String[] new_imple_split = new_imple.split("=");
			String[] post_split = post.split(" ");
		//	System.out.println(new_imple_split[0]);
			String new_post_split_1 = post_split[1].replace(new_imple_split[0].trim(), new_imple_split[1].trim());
			//System.out.println("POst split " + new_post_split_1 );
			String post_new = new_post_split_1 + " " + post_split[0] + " " + post_split[2]; 
			if(post_split[0].equals("=")) {
			
				String[] math_split =  new_post_split_1.split(" ");
				if(math_split[1].equals("+")) {
				
					try
				    {
					int before_equal = Integer.parseInt(math_split[2]);
					int after_equal = Integer.parseInt(post_split[2]);
					int total = after_equal-before_equal ;
					post_new =  "( = " +math_split[0] +" " +total +")"; 
				    }
					catch (NumberFormatException ex)
				    {
						float before_equal = Float.parseFloat(math_split[2]);
						float after_equal = Float.parseFloat(post_split[2]);
						float total = after_equal-before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")";
				    }
				}
				if(math_split[1].equals("-")) {
					
					try
				    {
					int before_equal = Integer.parseInt(math_split[2]);
					int after_equal = Integer.parseInt(post_split[2]);
					int total = after_equal+before_equal ;
					post_new =  "( = " +math_split[0] +" " +total +")"; 
				    }
					catch (NumberFormatException ex)
				    {
						float before_equal = Float.parseFloat(math_split[2]);
						float after_equal = Float.parseFloat(post_split[2]);
						float total = after_equal+before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")";
				    }
				}
				
				
				
			}
			if(post_split[0].equals(">") || post_split[0].equals("<")) {
				
				String[] math_split =  new_post_split_1.split(" ");
				if(math_split[1].equals("+")) {
					if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
							&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal-before_equal ;
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal-before_equal ;
							post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
					    }
					}
					else{
						String before_equal = math_split[2];
						String after_equal = post_split[2];
						post_new =  "( " + post_split[0] +" " +math_split[0] +" (- " +before_equal + " " + after_equal +"))"; 
						//System.out.println("Here");
					}
				}
				if(math_split[1].equals("-")) {
					if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
							&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal+before_equal ;
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal+before_equal ;
							post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
					    }	
					}
					else{
						String before_equal = math_split[2];
						String after_equal = post_split[2];
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" (+ " +before_equal + " " + after_equal +"))"; 
						//System.out.println("Here");
					}
					
				}
				if(math_split[1].equals("*")) {
					if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
							&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal/before_equal ;
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal/before_equal ;
							post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
					    }	
					}
					else{
						String before_equal = math_split[2];
						String after_equal = post_split[2];
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" (div " +before_equal + " " + after_equal +"))"; 
						//System.out.println("Here");
					}
				}
				
				if(math_split[1].equals("div")) {
					
					if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
							&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal * before_equal ;
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal * before_equal ;
							post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
					    }	
					}
					else{
						String before_equal = math_split[2];
						String after_equal = post_split[2];
						post_new =  "( " + post_split[0] +" "  +math_split[0] +" (* " +before_equal + " " + after_equal +"))"; 
						//System.out.println("Here");
					}
				}
			//	System.out.println("Greater then ");
			}
			
			if(post_split[0].equals("and")) {
				String[] bool_split =  new_post_split_1.split(" ");
				
					post_new =  "(and " + "(" +bool_split[1] + " " + bool_split[0] +" " + bool_split[2] +")" + " " + post_split[2] + ")";
				
				
			}
			
			if(post_split[0].equals("or")) {
				String[] bool_split =  new_post_split_1.split(" ");
				post_new =  "(or " + "(" +bool_split[1] + " " + bool_split[0] +" " + bool_split[2] +")" + " " + post_split[2] + ")";
				
			}
		
			String pre = num.precondition.toString();
			result = ("( assert (not( => " + pre + " " + post_new + " )))");
			
		}
		else if(e instanceof if_else_expr) {
			if_else_expr num = (if_else_expr) e;
		
			String true_expression = num.true_expression.toString();
			if(true_expression.contains("assert")) {
				true_expression = true_expression.replace("assert", "");
				true_expression = true_expression.substring(1, true_expression.length()-1);
				}
			
			String else_expression = num.else_expression.toString();
			if(else_expression.contains("assert")) {
				else_expression = else_expression.replace("assert", "");
				else_expression = else_expression.substring(1, else_expression.length()-1);
				}
			result = num.if_expression + "!" + true_expression + "!" + else_expression + "split2" + "(ite " +num.if_expression + " " +true_expression + " " + else_expression +" )";
		}
		
		else if(e instanceof correctness_if_condition) {
			correctness_if_condition num = (correctness_if_condition) e;
			String imple = num.implmentation.toString();
			String post = num.postcondition.toString();
			
			
			
			
			String imple_class_type = num.implmentation.getClass().toString();
				//System.out.println(imple);
			if(imple_class_type.equals("class expression.if_else_expr")) {
				
				String[] do_part1 = getEvalResult(num.implmentation).split("split2");
				
				String[] do_part = do_part1[0].split("!");
				
				//start of the wp of the true part
				String pre_condition_true = do_part[0];
				String implementation_true = do_part[1];
				
				String implementation_else = do_part[2];
				String post_else = num.postcondition.toString();
				
				
				
				implementation_true = implementation_true.replace("(", "");
				implementation_true = implementation_true.replace(")", "");
				
				implementation_else = implementation_else.replace("(", "");
				implementation_else = implementation_else.replace(")", "");
				
				
				String[] imple_split = implementation_true.split(" ");
				
				String[] imple_split_else = implementation_else.split(" ");
				
				//System.out.println("Here "  +imple_split_else[1]);
				
				String new_imple = "";
				
				
		
				if(imple_split[1].equals("=")) { //formating in the terms of y = id (= or - or div) id
				
					new_imple += imple_split[2] + " " + imple_split[1];
					
					if(imple_split.length > 4 && imple_split.length <= 7) {
						
							new_imple += " "+imple_split[4]+ " " + imple_split[3] +" " + imple_split[5];
						
					}
					
				
					
					
					
				}
			
				String new_imple_else = "";
				if(imple_split_else[1].equals("=")) { //formating in the terms of y = id (= or - or div) id
			
					new_imple_else += imple_split_else[2] + " " + imple_split_else[1];
					
					if(imple_split_else.length > 4 && imple_split_else.length <= 7) {
						
							new_imple_else += " "+imple_split_else[4]+ " " + imple_split_else[3] +" " + imple_split_else[5];
						
					}
					
					if(imple_split_else.length <= 4) {
						
						new_imple_else += imple_split_else[3];
					}
					
				
					
					
					
				}
			
				
	String[] new_imple_split = new_imple.split("=");
	
	
	
	String[] new_imple_split_else = new_imple_else.split("=");
				
				post = post.replace("(", "");
				post = post.replace(")", "");
				
				post_else = post_else.replace("(", "");
				post_else = post_else.replace(")", "");
				
			//	System.out.println("Post 2 is " + post);
				String[] post_split = post.split(" ");
				
				
			
				String[] post_split_else = post.split(" ");
				//System.out.println("Error" + new_imple_split[1]);
				
				String new_post_split_1 = post_split[1].replace(new_imple_split[0].trim(), new_imple_split[1].trim());
				
				String new_post_split_1_else = post_split_else[1].replace(new_imple_split_else[0].trim(), new_imple_split_else[1].trim());
			
				
				
				String post_new = new_post_split_1 + " " + post_split[0] + " " + post_split[2]; 
				
				String post_new_else = new_post_split_1_else + " " + post_split_else[0] + " " + post_split_else[2]; 
			
				if(post_split[0].equals("=")) {
					String[] math_split =  new_post_split_1.split(" ");
					String[] math_split_else =  new_post_split_1_else.split(" ");
					
					if(math_split_else.length > 1) {
					if(math_split_else [1].equals("+")) {
						
						try
					    {
						int before_equal = Integer.parseInt(math_split_else [2]);
						int after_equal = Integer.parseInt(post_split_else [2]);
						int total = after_equal-before_equal ;
						post_new =  "( = " +math_split_else [0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split_else [2]);
							float after_equal = Float.parseFloat(post_split_else [2]);
							float total = after_equal-before_equal ;
							post_new_else  =  "( = " +math_split_else [0] +" " +total +")";
					    }
					}
					if(math_split_else [1].equals("-")) {
						
						try
					    {
						int before_equal = Integer.parseInt(math_split_else [2]);
						int after_equal = Integer.parseInt(post_split_else [2]);
						int total = after_equal+before_equal ;
						post_new_else =  "( = " +math_split_else[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split_else [2]);
							float after_equal = Float.parseFloat(post_split_else [2]);
							float total = after_equal+before_equal ;
							post_new_else =  "( = " +math_split_else[0] +" " +total +")";
					    }
					}
					}
					else {
						//System.out.println("Here3");
						post_new_else =  "( " +new_post_split_1_else  + " " + post_split_else[0] + " " + post_split_else[2]  + " )";
 
					}
					
					
					if(math_split.length > 1) {
					if(math_split[1].equals("+")) {
					
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal-before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal-before_equal ;
							post_new =  "( = " +math_split[0] +" " +total +")";
					    }
					}
					if(math_split[1].equals("-")) {
						
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal+before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal+before_equal ;
							post_new =  "( = " +math_split[0] +" " +total +")";
					    }
					}
					}
					else {
						post_new =  "( " +post_split[0] + " " + new_post_split_1 + " " + post_split[2]  + " )"; 
 
					}
					
					
				}
				
				if(post_split_else[0].equals(">") || post_split_else[0].equals("<")) {
					
					String[] math_split_else =  new_post_split_1_else.split(" ");
					
					if(math_split_else.length > 1) {
					
					if(math_split_else[1].equals("+")) {
						if((math_split_else[2].startsWith("0") || math_split_else[2].startsWith("1") || math_split_else[2].startsWith("2") || math_split_else[2].startsWith("3") || math_split_else[2].startsWith("4") ||math_split_else[2].startsWith("5") || math_split_else[2].startsWith("6") || math_split_else[2].startsWith("7") || math_split_else[2].startsWith("8") || math_split_else[2].startsWith("9") )
								&& (post_split_else[2].startsWith("0") || post_split_else[2].startsWith("1") || post_split_else[2].startsWith("2") || post_split_else[2].startsWith("3") || post_split_else[2].startsWith("4") ||post_split_else[2].startsWith("5") || post_split_else[2].startsWith("6") || post_split_else[2].startsWith("7") || post_split_else[2].startsWith("8") || post_split_else[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split_else[2]);
							int after_equal = Integer.parseInt(post_split_else[2]);
							int total = after_equal-before_equal ;
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split_else[2]);
								float after_equal = Float.parseFloat(post_split_else[2]);
								float total = after_equal-before_equal ;
								post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")";
						    }
						}
						else{
							String before_equal = math_split_else[2];
							String after_equal = post_split_else[2];
							post_new_else =   "( " + post_split_else[0] +" " +math_split_else[0] +" (- " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					if(math_split_else[1].equals("-")) {
						if((math_split_else[2].startsWith("0") || math_split_else[2].startsWith("1") || math_split_else[2].startsWith("2") || math_split_else[2].startsWith("3") || math_split_else[2].startsWith("4") ||math_split_else[2].startsWith("5") || math_split_else[2].startsWith("6") || math_split_else[2].startsWith("7") || math_split_else[2].startsWith("8") || math_split_else[2].startsWith("9") )
								&& (post_split_else[2].startsWith("0") || post_split_else[2].startsWith("1") || post_split_else[2].startsWith("2") || post_split_else[2].startsWith("3") || post_split_else[2].startsWith("4") ||post_split_else[2].startsWith("5") || post_split_else[2].startsWith("6") || post_split_else[2].startsWith("7") || post_split_else[2].startsWith("8") || post_split_else[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split_else[2]);
							int after_equal = Integer.parseInt(post_split_else[2]);
							int total = after_equal+before_equal ;
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split_else[2]);
								float after_equal = Float.parseFloat(post_split_else[2]);
								float total = after_equal+before_equal ;
								post_new_else =   "( " + post_split_else[0] +" " +math_split_else[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split_else[2];
							String after_equal = post_split_else[2];
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" (+ " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
						
					}
					if(math_split_else[1].equals("*")) {
						if((math_split_else[2].startsWith("0") || math_split_else[2].startsWith("1") || math_split_else[2].startsWith("2") || math_split_else[2].startsWith("3") || math_split_else[2].startsWith("4") ||math_split_else[2].startsWith("5") || math_split_else[2].startsWith("6") || math_split_else[2].startsWith("7") || math_split_else[2].startsWith("8") || math_split_else[2].startsWith("9") )
								&& (post_split_else[2].startsWith("0") || post_split_else[2].startsWith("1") || post_split_else[2].startsWith("2") || post_split_else[2].startsWith("3") || post_split_else[2].startsWith("4") ||post_split_else[2].startsWith("5") || post_split_else[2].startsWith("6") || post_split_else[2].startsWith("7") || post_split_else[2].startsWith("8") || post_split_else[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split_else[2]);
							int after_equal = Integer.parseInt(post_split_else[2]);
							int total = after_equal/before_equal ;
							post_new_else =   "( " + post_split_else[0] +" " +math_split_else[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split_else[2]);
								float after_equal = Float.parseFloat(post_split_else[2]);
								float total = after_equal/before_equal ;
								post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split_else[2];
							String after_equal = post_split_else[2];
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" (div " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					
					if(math_split_else[1].equals("div")) {
						
						if((math_split_else[2].startsWith("0") || math_split_else[2].startsWith("1") || math_split_else[2].startsWith("2") || math_split_else[2].startsWith("3") || math_split_else[2].startsWith("4") ||math_split_else[2].startsWith("5") || math_split_else[2].startsWith("6") || math_split_else[2].startsWith("7") || math_split_else[2].startsWith("8") || math_split_else[2].startsWith("9") )
								&& (post_split_else[2].startsWith("0") || post_split_else[2].startsWith("1") || post_split_else[2].startsWith("2") || post_split_else[2].startsWith("3") || post_split_else[2].startsWith("4") ||post_split_else[2].startsWith("5") || post_split_else[2].startsWith("6") || post_split_else[2].startsWith("7") || post_split_else[2].startsWith("8") || post_split_else[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split_else[2]);
							int after_equal = Integer.parseInt(post_split_else[2]);
							int total = after_equal * before_equal ;
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split_else[2]);
								float after_equal = Float.parseFloat(post_split_else[2]);
								float total = after_equal * before_equal ;
								post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split_else[2];
							String after_equal = post_split_else[2];
							post_new_else =   "( " + post_split_else[0] +" "  +math_split_else[0] +" (* " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
				//	System.out.println("Greater then ");
				}
					else {
					
						post_new_else =  "( " + post_split_else[0]   + " " + new_post_split_1_else + " " + post_split_else[2]  + " )"; 
					}
				}
				
				
				
				if(post_split[0].equals(">") || post_split[0].equals("<")) {
					String[] math_split =  new_post_split_1.split(" ");
					if(math_split.length > 1) {
					if(math_split[1].equals("+")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal-before_equal ;
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal-before_equal ;
								post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
						    }
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" (- " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					if(math_split[1].equals("-")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal+before_equal ;
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal+before_equal ;
								post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =   "( " + post_split[0] +" " +math_split[0] +" (+ " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
						
					}
					if(math_split[1].equals("*")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal/before_equal ;
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal/before_equal ;
								post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" (div " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					
					if(math_split[1].equals("div")) {
						
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal * before_equal ;
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal * before_equal ;
								post_new =  "( " + post_split[0] +" "  +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =   "( " + post_split[0] +" "  +math_split[0] +" (* " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					}
					else {
						post_new =  "( " +post_split[0] + " " + new_post_split_1 + " " + post_split[2]  + " )"; 
 
					}
				//	System.out.println("Greater then ");
				}
			
				
				
				String pre = num.precondition.toString();
				//System.out.println("imple_pre" + pre_condition_true);
			//	pre_condition_true = pre_condition_true.replace("(", "");
				//pre_condition_true = pre_condition_true.replace(")", "");
			
				//String result1 = " ( => " + pre_condition_true + " " + post_new +")";
				String result1 = "( => ( and " + pre + " " + pre_condition_true + ") " + post_new + ")";
			
				//String result2 = " ( => (not " + pre_condition_true + " ) " + post_new_else +")";
				String result2 = "( => ( and " + pre + " (not " + pre_condition_true + ")) " + post_new_else + ")";
		
				result = "(assert (not (and "+ result1+ " " + result2 +    " )))";
				//result = "( assert (not( => " + pre + " (and " + result1 +" " + result2 + " ))))";
				//result = ("( assert (not( => (and" + pre + " (" + imple_pre + ") ) " + post_new + " )))");
				//System.out.println(result);
				
				//System.out.println("Result is " + result);
			
				
			}
			
			if(imple_class_type.equals("class expression.if_condition_to_assignment")) {
			
				String[] imple_new_line_split = imple.split("  ");
				String imple_pre = imple_new_line_split[0].replace("assert", "");
				
				imple_pre = imple_pre.replace("=>", "");
				imple_pre = imple_pre.replace("(", "");
				imple_pre = imple_pre.replace(")", "");
				//System.out.println("Here 1" + imple_pre);
				String imple_2 = imple_new_line_split[1];
				imple_2 = imple_2.replace("(", "");
				imple_2 = imple_2.replace(")", "");
				//imple_2 = imple_2.replace("assert", "");
				//System.out.println("Here2" + imple_2);
				//System.out.println("New imple is " + imple_2);
				if(imple_2.contains("assert")) {
					imple_2 = imple_2.replace("assert", "");
				}
				String[] imple_split = imple_2.split(" ");
				
				String new_imple = "";
				
				
				
				
				
				
				if(imple_split[2].equals("=")) {
					
					new_imple += imple_split[3] + " " + imple_split[2];
					if(imple_split.length > 5 && imple_split.length <= 8) {
						
							new_imple += " "+imple_split[5]+ " " + imple_split[4] +" " + imple_split[6];
						
					}
					if(imple_split.length == 5) {
						new_imple += " "+imple_split[6];
					}
					
				}
			//	System.out.println("New imple is " + new_imple);
				
				
				String[] new_imple_split = new_imple.split("=");
				
				post = post.replace("(", "");
				post = post.replace(")", "");
			//	System.out.println("Post 2 is " + post);
				String[] post_split = post.split(" ");
			
				String new_post_split_1 = post_split[1].replace(new_imple_split[0].trim(), new_imple_split[1].trim());
			
				String post_new = new_post_split_1 + " " + post_split[0] + " " + post_split[2]; 
				if(post_split[0].equals("=")) {
					String[] math_split =  new_post_split_1.split(" ");
					if(math_split[1].equals("+")) {
					
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal-before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal-before_equal ;
							post_new =  "( = " +math_split[0] +" " +total +")";
					    }
					}
					if(math_split[1].equals("-")) {
						
						try
					    {
						int before_equal = Integer.parseInt(math_split[2]);
						int after_equal = Integer.parseInt(post_split[2]);
						int total = after_equal+before_equal ;
						post_new =  "( = " +math_split[0] +" " +total +")"; 
					    }
						catch (NumberFormatException ex)
					    {
							float before_equal = Float.parseFloat(math_split[2]);
							float after_equal = Float.parseFloat(post_split[2]);
							float total = after_equal+before_equal ;
							post_new =  "( = " +math_split[0] +" " +total +")";
					    }
					}
					
					
					
				}
				if(post_split[0].equals(">")) {
					String[] math_split =  new_post_split_1.split(" ");
					if(math_split[1].equals("+")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal-before_equal ;
							post_new =  "( > " +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal-before_equal ;
								post_new =  "( > " +math_split[0] +" " +total +")";
						    }
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =  "( = " +math_split[0] +" (- " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					if(math_split[1].equals("-")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal+before_equal ;
							post_new =  "( > " +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal+before_equal ;
								post_new =  "( > " +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =  "( = " +math_split[0] +" (+ " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
						
					}
					if(math_split[1].equals("*")) {
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal/before_equal ;
							post_new =  "( > " +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal/before_equal ;
								post_new =  "( > " +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =  "( = " +math_split[0] +" (div " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
					
					if(math_split[1].equals("div")) {
						
						if((math_split[2].startsWith("0") || math_split[2].startsWith("1") || math_split[2].startsWith("2") || math_split[2].startsWith("3") || math_split[2].startsWith("4") ||math_split[2].startsWith("5") || math_split[2].startsWith("6") || math_split[2].startsWith("7") || math_split[2].startsWith("8") || math_split[2].startsWith("9") )
								&& (post_split[2].startsWith("0") || post_split[2].startsWith("1") || post_split[2].startsWith("2") || post_split[2].startsWith("3") || post_split[2].startsWith("4") ||post_split[2].startsWith("5") || post_split[2].startsWith("6") || post_split[2].startsWith("7") || post_split[2].startsWith("8") || post_split[2].startsWith("9") ) ) {
							try
						    {
							int before_equal = Integer.parseInt(math_split[2]);
							int after_equal = Integer.parseInt(post_split[2]);
							int total = after_equal * before_equal ;
							post_new =  "( > " +math_split[0] +" " +total +")"; 
						    }
							catch (NumberFormatException ex)
						    {
								float before_equal = Float.parseFloat(math_split[2]);
								float after_equal = Float.parseFloat(post_split[2]);
								float total = after_equal * before_equal ;
								post_new =  "( > " +math_split[0] +" " +total +")";
						    }	
						}
						else{
							String before_equal = math_split[2];
							String after_equal = post_split[2];
							post_new =  "( = " +math_split[0] +" (* " +before_equal + " " + after_equal +"))"; 
							//System.out.println("Here");
						}
					}
				//	System.out.println("Greater then ");
				}
			
				
				
				String pre = num.precondition.toString();
			//	System.out.println("imple_pre" + imple_pre);
				
				
				
				imple_pre = imple_pre.replace("(", "");
				imple_pre = imple_pre.replace(")", "");
				String result1 = " ( => (" + imple_pre + ") " + post_new +")";
				result = ("( assert (not( => " + pre + " " + result1 + " )))");
				//result = ("( assert (not( => (and" + pre + " (" + imple_pre + ") ) " + post_new + " )))");
				//System.out.println(result);
			}
			//System.out.println("The size of imple_new_line_split is " + imple_new_line_split.length);
			
			
			
		
				
			
		
			
		
			
		}
		else if(e instanceof Arth_multi) {
		//	System.out.println("HERE");
			Arth_multi add = (Arth_multi) e;
			String left = getEvalResult(add.left);
			String right = getEvalResult(add.right);
			if(this.values.containsKey(left) && this.values.containsKey(right)) {
				
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x * Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x * Float.parseFloat((this.values.get(right)));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else if(this.values.containsKey(left)) {
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x * Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x * Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
				
			}
			else if(this.values.containsKey(right)) {
			
				
				try{
					int x = Integer.parseInt(left);
					int x1 = x * Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x * Float.parseFloat(this.values.get(right));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else {
			
				try{
					int x = Integer.parseInt(left);
					int x1 = x * Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x * Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
			}
		}
		else if(e instanceof parentheses_expr) {
			//System.out.println("HERE parthenses" + e);
			parentheses_expr var = (parentheses_expr) e;
			String left = getEvalResult(var.middle);
		//	System.out.println("THe left from the parentheses is "+ left);
			result = left;
		}
		else if(e instanceof change_to_real_num) {
			//System.out.println("HERE IN THE CHANGE TO REAL NUM");
			change_to_real_num var = (change_to_real_num) e;
			String left = getEvalResult(var.right);
			//System.out.println("The right from the change to real num is " + left);
			result = left;
		}
		else if(e instanceof Arth_add) {
			//System.out.println("HERE 2" + e);
			Arth_add add = (Arth_add) e;
			String left = getEvalResult(add.left);
			String right = getEvalResult(add.right);
			//System.out.println("INSIDE THE ADD " + left + " " + right);
			if(this.values.containsKey(left) && this.values.containsKey(right)) {
				
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x + Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x + Float.parseFloat((this.values.get(right)));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else if(this.values.containsKey(left)) {
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x + Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x + Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
				
			}
			else if(this.values.containsKey(right)) {
			
				
				try{
					int x = Integer.parseInt(left);
					int x1 = x + Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x + Float.parseFloat(this.values.get(right));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else {
			
				try{
					int x = Integer.parseInt(left);
					int x1 = x + Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x + Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
			}
		}
		else if(e instanceof Arth_sub) {
			Arth_sub sub = (Arth_sub) e;
			String left = getEvalResult(sub.left);
			String right = getEvalResult(sub.right);
			//System.out.println("INSIDE THE ADD " + left + " " + right);
			if(this.values.containsKey(left) && this.values.containsKey(right)) {
				
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x + Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x - Float.parseFloat((this.values.get(right)));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else if(this.values.containsKey(left)) {
				try{
					int x = Integer.parseInt(this.values.get(left));
					int x1 = x - Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(this.values.get(left));
			       Float x1 =  x - Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
				
			}
			else if(this.values.containsKey(right)) {
			
				
				try{
					int x = Integer.parseInt(left);
					int x1 = x - Integer.parseInt(this.values.get(right));
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x - Float.parseFloat(this.values.get(right));
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
				
			}
			else {
			
				try{
					int x = Integer.parseInt(left);
					int x1 = x - Integer.parseInt(right);
					result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not int
			    }
			    //check if float
			    try{
			       Float x =  Float.parseFloat(left);
			       Float x1 =  x - Float.parseFloat(right);
			       result = String.valueOf(x1);
			    }catch(NumberFormatException e1){
			        //not float
			    }
			}
		}
		else if(e instanceof Assign_to_arth) {
			Assign_to_arth var = (Assign_to_arth) e;
			String right = getEvalResult(var.right);
			
		//	System.out.println("The test is " + right);
			result =  right;
		}
		else if(e instanceof until_to_expression) {
			until_to_expression var = (until_to_expression)e;
			String right = getEvalResult(var.right);
			
			//	System.out.println("The test is " + right);
				result =  right;
		}
		

				// Evalutes the boolean expressions.
				// Boolean AND
				else if (e instanceof Bool_and) {
					Bool_and and = (Bool_and) e;
					String left = getEvalResult(and.left);
					String right = getEvalResult(and.right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x && Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else if (this.values.containsKey(left)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x && Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					} else if (this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x && Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x && Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					}
				}
				// Boolean OR
				else if (e instanceof Bool_or) {
					Bool_or or = (Bool_or) e;
					String left = getEvalResult(or.left);
					String right = getEvalResult(or.right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x || Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else if (this.values.containsKey(left)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x || Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					} else if (this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x || Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x || Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					}
				} else if (e instanceof Bool_or) {
					Bool_or or = (Bool_or) e;
					String left = getEvalResult(or.left);
					String right = getEvalResult(or.right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x || Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else if (this.values.containsKey(left)) {
						boolean x = Boolean.parseBoolean(this.values.get(left));
						boolean x1 = x || Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					} else if (this.values.containsKey(right)) {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x || Boolean.parseBoolean(this.values.get(right));
						result = String.valueOf(x1);
					} else {
						boolean x = Boolean.parseBoolean(left);
						boolean x1 = x || Boolean.parseBoolean(right);
						result = String.valueOf(x1);
					}
				}
				// Boolean NOT
				else if (e instanceof Bool_not) {
					Bool_not not = (Bool_not) e;
					String right = getEvalResult(not.right);
					if (this.values.containsKey(right)) {
						boolean expr = Boolean.parseBoolean(this.values.get(right));
						boolean eval = !expr;
						result = eval ? "true" : "false";
					} else {
						boolean expr = Boolean.parseBoolean(right);
						boolean eval = !expr;
						result = eval ? "true" : "false";
					}
				}


				/* This part of the code evaluates boolean expressions
				*  '>', '<', '>=', '<=' and '==' all are similar to 
				*  implementation of addition expression 
				*  which was doen by Kavees. Slight modifiation was doen
				*  to accomodate the need of operations.
				*/


				// For expr '>' expr
				else if (e instanceof Bool_greater) {
					Bool_greater gt = (Bool_greater) e;
					String left = getEvalResult(gt.left);
					String right = getEvalResult(gt.right);
					// System.out.println("INSIDE THE ADD " + left + " " + right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat((this.values.get(right)));
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(left)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(right);
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(this.values.get(right));
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(right);
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp > rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					}
				}
				// For expr '<' expr
				else if (e instanceof Bool_less_then) {
					Bool_less_then lt = (Bool_less_then) e;
					String left = getEvalResult(lt.left);
					String right = getEvalResult(lt.right);
					// System.out.println("INSIDE THE ADD " + left + " " + right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat((this.values.get(right)));
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(left)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(right);
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(this.values.get(right));
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(right);
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp < rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					}
				}
				// For expr '>=' expr
				else if (e instanceof Bool_greater_then_or_equal_to) {
					Bool_greater_then_or_equal_to gte = (Bool_greater_then_or_equal_to) e;
					String left = getEvalResult(gte.left);
					String right = getEvalResult(gte.right);
					// System.out.println("INSIDE THE ADD " + left + " " + right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat((this.values.get(right)));
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(left)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(right);
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(this.values.get(right));
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(right);
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp >= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					}
					
					
				}
				// For expr '<=' expr
				else if (e instanceof Bool_less_then_or_equal_to) {
					Bool_less_then_or_equal_to lte = (Bool_less_then_or_equal_to) e;
					String left = getEvalResult(lte.left);
					String right = getEvalResult(lte.right);
					// System.out.println("INSIDE THE ADD " + left + " " + right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat((this.values.get(right)));
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(left)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(right);
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(this.values.get(right));
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(right);
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp <= rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					}
				}
				// For expr '==' expr
				else if (e instanceof Bool_equal) {
					Bool_equal eq = (Bool_equal) e;
					String left = getEvalResult(eq.left);
					String right = getEvalResult(eq.right);
					// System.out.println("INSIDE THE ADD " + left + " " + right);
					if (this.values.containsKey(left) && this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat((this.values.get(right)));
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(left)) {
						try {
							int lOp = Integer.parseInt(this.values.get(left));
							int rOp = Integer.parseInt(right);
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(this.values.get(left));
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else if (this.values.containsKey(right)) {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(this.values.get(right));
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(this.values.get(right));
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					} else {
						try {
							int lOp = Integer.parseInt(left);
							int rOp = Integer.parseInt(right);
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not int
						}
						// check if float
						try {
							Float lOp = Float.parseFloat(left);
							Float rOp = Float.parseFloat(right);
							boolean eval = lOp == rOp;
							result = eval ? "true" : "false";
						} catch (NumberFormatException e1) {
							// not float
						}
					}
				}



		
		
		return result;
		
			
	}
	
	
	
	public static int stringCompare(String str1, String str2) 
    { 
  
        int l1 = str1.length(); 
        int l2 = str2.length(); 
        int lmin = Math.min(l1, l2); 
  
        for (int i = 0; i < lmin; i++) { 
            int str1_ch = (int)str1.charAt(i); 
            int str2_ch = (int)str2.charAt(i); 
  
            if (str1_ch != str2_ch) { 
                return str1_ch - str2_ch; 
            } 
        } 
  
        // Edge case for strings like 
        // String 1="Geeks" and String 2="Geeksforgeeks" 
        if (l1 != l2) { 
            return l1 - l2; 
        } 
  
        // If none of the above conditions is true, 
        // it implies both the strings are equal 
        else { 
            return 0; 
        } 
    } 
	
}
