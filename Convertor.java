
package project3csc4101;
import java.util.Stack;

/**
 *
 * @author shelbyantill
 */
public class Convertor {
 
    
    public static void prefixToInfix (String Expression){
        StringBuilder express=new StringBuilder(Expression).reverse();
        Stack <String>stack = new Stack<>();
        Stack <Integer> nums = new Stack<>();
        StringBuilder str =new StringBuilder();
        boolean bool = false;

        int value = 0;


        for (int i =0; i<express.length();i++ ){

            if(express.charAt(express.length()-1) == '*' || express.charAt(express.length()-1) == '/' ){
                bool = true;
            }


            char c = express.charAt(i);
            if(c=='*'||c=='+'||c=='-'||c=='/'){

                if(!stack.isEmpty()){
                    String var1 = stack.pop();
                    String var2 = stack.pop();

                    int num1 = nums.pop();
                    int num2 = nums.pop();
                    nums.push(calculate(num1, c, num2));

                    //Formatting
                    String tmp = "";
                    String noParenth = var1 +" "+ c + " "+var2;
                    String parenth = tmp = "(" + var1 +" "+ c + " "+var2 + ")";

                    if(Expression.contains("/") &&Expression.contains("*")){
                        tmp = parenth;
                    }
                    else if((c=='*' ||  c=='/') && bool == true ){
                         tmp =noParenth;
                    }
                    else if(!Expression.contains("/") &&!Expression.contains("*")){
                        tmp =noParenth;
                    }
                    else {
                         tmp = parenth;
                    }
                    //Formatting

                    stack.push(tmp);



                }  
            }
            else if(Character.isLetterOrDigit(c)){
                str.append(c);
                if(i== express.length()-1 || express.charAt(i+1)==' '){
                    String num=str.reverse().toString();
                    stack.push(num); 

                    nums.push(Integer.parseInt(num));
                    str.setLength(0);
                }

            }
            else if(c==' '){
                //skip over 
            }

        }


        String result = stack.pop();
        value= nums.pop();
        System.out.println(Expression + " ➔ " + result + " = "+ value);

    }

    public static int calculate(int expr1, char op, int expr2) {
        if(op == '*'){
            return expr1 * expr2;
        }
        else if(op == '-'){
            return expr1 - expr2;
        }
        else if(op == '+'){
            return expr1 + expr2;
        }
        else if(op == '/'){
            return expr1 / expr2;
        }
        return 0;
    }

     
      

}