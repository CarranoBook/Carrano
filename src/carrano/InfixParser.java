/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

/**
 *
 * @author NBleier
 */
public class InfixParser {
    
    
    public static double evaluatePostfix(String postfix) {
        OurStack<Double>  valStack = new OurStack<>();
        double operandOne, operandTwo;
        
        for ( char x : postfix.replaceAll("\\s+", "").toCharArray() ) {
            if ( Character.isDigit(x) )
                valStack.push((double) Character.getNumericValue(x));
            else {
                operandTwo = valStack.pop();
                operandOne = valStack.pop();
                if ( x == '+' ) valStack.push(operandOne + operandTwo);
                else if ( x == '-' ) valStack.push(operandOne - operandTwo);
                else if ( x == '*' ) valStack.push(operandOne * operandTwo);
                else if ( x == '/' ) valStack.push(operandOne / operandTwo);
                else valStack.push(Math.pow(operandOne, operandTwo));
            }// end else
        }// end for
        return valStack.peek();
    } 
    

    /**
     * This method facilitates the parsing of infix to postfix notation by
     * handling arithmetic operators other than exponentiation.
     * The method pops operators from the stack and appends them to the result
     * string until the stack is empty, or an operator with lower precedence is
     * found.  Once complete, it pushes the passed operator to the operator stack
     * @param op the arithmetic operator to be handled by the method
     * @param opStack the operator stack
     * @return a string of operators popped from the operator stack
     */
    private static String arithOps(Operators op, OurStack<Operators> opStack ) {
        String result = "";
        
        while ( !(opStack.isEmpty()) && op.getVal() <= opStack.peek().getVal() ) {
            result += opStack.pop().getSymbol();
        } //end while
        opStack.push(op);
        return result;
    } //end arithOps
    
    
    /**
     * This method facilitates the parsing of infix to postfix notation by 
     * handling arithmetic operators other than exponentiation.
     * The method first removes all white space from an arithmetic expression.
     * It then adds an "*" to all places where multiplication is implicitly 
     * invoked in the expression.  I.E ab => a*b
     * 
     * @param s is an arithmetic expression
     * @return an arithmetic expression with all multiplication explicitly invoked
     */
    private static String multiplicationExplicitizer(String s) {
        String result = " ";
        int index;
        s.replaceAll("\\s*", "");
        
        for (char x : s.toCharArray()) {
            index = result.length();
            if ( addMultSign(result.charAt(index-1),x) ) {
                result += "*" + x;
            } //end if
            else result += x; //end else
           
        } //end for
        return result.trim();
    }
    
    /**
     * This method determines whether an implicit multiplication occurs.
     * @param first first character
     * @param second second character
     * @return true if implicit multiplication occurs between first and second
     * characters
     */
    private static boolean addMultSign(char first, char second) {
        if ( Character.isLetter(first) && Character.isLetter(second) ) //both chars are letters
            return true;
        if ( Character.isLetter(first) && Character.isDigit(second) ) //first char is letter, second digt
            return true;
        if ( Character.isDigit(first) && Character.isLetter(second) ) //first char is digit, second letter
            return true;
        if ( ( Character.isDigit(first) || Character.isLetter(first) ) && second == '(' ) //first char is digit or letter, and second digit is close parens
                return true;
        if ( first == ')' && ( Character.isDigit(second) || Character.isAlphabetic(second)) ) //first char is ')' and second digit is number or letter
            return true;
        return false;
    }
    
    
    /**
     * This method facilitates the parsing of infix to postfix notation by 
     * handling close parenthesis - open parenthesis were previously added to
     * the operator stack.  This method pops operators from the operator stack
     * and appends them to a string until it finds an open parens, which it
     * removes from the stack, but does not concatenate to the result string.
     * @param opStack the operator stack containing an open parens
     * @return string of operators between the open and close parens
     */
    private static String parensOps(OurStack<Operators> opStack) {
        String result = "";
        while ( opStack.peek().getVal() != 0 ) {
            result += opStack.pop().getSymbol() + " ";
        } //end while
        opStack.pop();
        return result;
    } //end parensOps

    /**
     * this method facilitates the parsing of infix to postfix notation by
     * "closing" out the operator stack.  It pops all remaining operators from 
     * the operator stack and appends them to a string.  This is the last step
     * in parsing of infix to postfix notation
     * @param opStack the operator stack
     * @return string consisting of operators popped from the operator stack
     */
    private static String closeStack(OurStack<Operators> opStack) {
        String result = "";
        while ( !(opStack.isEmpty()) ) {
            result += " " + opStack.pop().getSymbol();
        }
        return result;
    }
    
    /**
     * this method facilitates the parsing of infix to postfix notation by
     * replacing curly brackets and square brackets with normal parens.
     * @param s the infix string
     * @return the infix string with only parens as arithmetic delimiters
     */
    private static String unifyDelimiters(String s) {
        s = s.replace('{', '(');
        s = s.replace('[', '(');
        s = s.replace('}', ')');
        s = s.replace(']', ')');
        return s;
    }

    private static String whiteSpaceFix(String input) {
        input = input.replaceAll("\\s+", "");
        String result = "";
        
        for ( char x : input.toCharArray() ) {
                result += x + " ";
        }
        return result.trim();
    }
    
    /**
     * This enumeration contains arithmetic operators for addition, subtraction,
     * multiplication, division, and exponentiation, as well as a parens element
     * The value is used to by other methods to determine when to stop popping 
     * operators from the operator stack.
     */
    private enum Operators {
        PLUS(1, '+'), MINUS(1, '-'), TIMES(2, '*'), DIVIDE(2, '/'), POW(3, '^'),
            PARENS(0, '(');
        private final int value;
        private final char symbol;
        
        private Operators(int value, char symbol) {
            this.value = value;
            this.symbol = symbol;
        } //end constructor
        
        private int getVal() {
            return value;
        }// end getVal
        
        private char getSymbol() {
            return symbol;
        } //end getSymbol
        
        
    }// end Operators
    
    /**
     * This function determines whether the arithmetic delimiters (parens, etc)
     * of an arithmetic expression are syntactically correct.
     * @param s is an arithmetic expression
     * @return true if delimiters are syntactically correct, else false
     */
    public static boolean  balancedDelimiters(String s) {
        OurStack<Character> parens = new OurStack<>();
        Character temp = null;
        
        
        for ( char x : s.toCharArray() ) {
            if ( x == '(' || x == '{' || x == '[' )
                parens.push(x);
            
            else if ( x == ')' ) {
                temp = parens.pop();
                try {
                    if ( temp != '(' || temp == null)
                        return false;
                }
                catch (NullPointerException e) {
                    return false;
                }
            }
            
            else if ( x == '}' ) {
                temp = parens.pop();
                try {
                if ( temp != '{' || temp == null) 
                    return false;
                }
                catch (NullPointerException e) {
                    return false;
                }
            }
            
            else if ( x == ']' ) {
                temp = parens.pop();
                try {
                if ( temp != '['  || temp == null)
                    return false; 
                }
                catch (NullPointerException e) {
                    return false;
                }
            }
        }
        
        
        return parens.isEmpty();
    }
    
    /**
     * This method parses an arithmetic expression from infix notation (i.e. a + b)
     * to postfix notation (i.e. a b +).  The algorithm comes from
     * Corrano Data Structures and Abstractions edition 3 page 126
     * @param s infix notation arithmetic expression
     * @return postfix notation arithmetic expression equivalent to @param s
     */
    public static String postfixTranlator(String s) {
        s = unifyDelimiters(s);
        
        if ( !balancedDelimiters(s) ) return "Error: Delimiters not ballanced";
        OurStack<Operators> operators = new OurStack<>();
        String result = "";
        s = multiplicationExplicitizer(s);
        
        for (Character x : s.toCharArray() ) {
            if ( Character.isLetter(x) )
                result += x + " ";
            else if ( x == '+' ) {
                result += arithOps(Operators.PLUS, operators);
            }
            
            else if ( x == '-' ) {
                result += arithOps(Operators.MINUS, operators);
            }
            
            else if ( x == '*' ) {
                result += arithOps(Operators.TIMES, operators);
            }
            
            else if ( x == '/' ) {
                result += arithOps(Operators.DIVIDE, operators);
            }
            
            else if ( x == '^' ) operators.push(Operators.POW);
            
            else if ( x == '(' ) operators.push(Operators.PARENS);
            
            else if ( x == ')' ) {
                result += parensOps(operators);
            }
        }//end for
        
        result += closeStack(operators);
        
        result = whiteSpaceFix(result);
        
        return result;
    } //end postfixTranslator
   
}
