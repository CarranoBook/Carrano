/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

/**
 * This class parses a sentence in a standard notation of propositional
 * logic and builds a truth table for that sentence.  For this parser,
 * negation has operator precedence over conjunction and disjunction, and
 * conjunction and disjunction are evaluated right to left.  Recommend copious
 * use of parenthesis
 * @author NBleier
 */
public class LogicParser {
    private String sentence;
    private int variableCount;
    private final String originalSentence;
    
    
    /**
     * Constructor for LogicParser
     * @param sentence is a sentence of propositional logic
     * @throws IllegalArgumentException is thrown if sentence 
     * is not formatted correctly.  The main culprit is unballanced delimiters
     * aka '(', '['
     */
    public LogicParser(String sentence) throws IllegalArgumentException
    {
        this.sentence = sentence;
        originalSentence = sentence;
        if ( !this.stringParser() ) {
            IllegalArgumentException e = new IllegalArgumentException();
            throw e;
        }
        this.postFixTranslator();
        this.variableCount = freeVariableCounter();
    }
    
    /**
     * This method generates an array of Strings.  Each string is a binary number
     * that is the length of variableCount.
     * 
     * If variableCount = 2, then this will return the following array:
     * 00
     * 01
     * 10
     * 11
     * @return a String array consisting of all of the permutations of 0 and 1
     * of size variableCount
     */
    private String[] generateStringArrays() {
        int num = (int) Math.pow(2, variableCount);
        String[] result = new String[num];
        int counter = 0;
        for ( int i = 0; i < num; i ++ ) {
            String bin = Integer.toBinaryString(i);
            while ( bin.length() < variableCount ) {
                bin = "0" + bin;
            }
            result[counter] = bin;
            counter++;
        }
        return result;
    }
    
    /**
     * This method generates the truth table of the sentence contained in the LogicParser object
     * @return the truth table of the propositional logic sentence
     */
    public String generateTruthTable() {
        boolean[][] vals = generateBooleanArrays();
        String results = createHeader();
        boolean temp;
        int num = (int) Math.pow(2, variableCount);
        int width = this.originalSentence.length() / 2;
        for ( int i = 0; i < num; i++ ) {
            temp = evaluateFromBooleans(vals[i]);
            results += boolArrayToString(vals[i]);
            for ( int j = 0; j < width; j ++ ) {
                results += " ";
            }
            
            if ( temp ) 
                results += "T";
            else
                results += "F";
            
            
            results += "\n";
        }
        
        return results;
        
    }
    
    /**
     * This method converts an array of boolean values into a String of "T"s and "F"s
     * This is used to generate the Truth Table
     * @param vals array of boolean values
     * @return String of "T"s and "F"s
     */
    private String boolArrayToString(boolean[] vals) {
        String result = "";
        for ( boolean x : vals ) {
            if ( x ) 
                result += "T | ";
            else
                result += "F | ";
        }
        return result;
    }
    
    /**
     * This method evaluates whether the sentence is true or false
     * based on the boolean values passed to this method
     * @param vals array of boolean values that will be used to evaluate the sentence
     * @return true if the sentence is true for the passed array of boolean values
     */
    private boolean evaluateFromBooleans(boolean[] vals) {
        return evaluateTrueFalseString(parseToTrueFalse(vals));
    }
    
    /**
     * This method creates the header of the Truth Table of the sentence
     * @return String containing the header of the Truth Table
     */
    private String createHeader() {
        Character[] props = getProps();
        String result = "";
        for ( char x : props ) {
            result += x + " | ";
        }
        result += " " + this.originalSentence + "\n";
        int l = result.length();
        for ( int i = 0; i < l; i++ ) 
            result += "-";
        result += "\n";
        return result;
    }
    
    /**
     * This method creates an array of boolean arrays based on the string array created
     * by the method generateStringArrays(): String[]
     * 0's are converted into True and 1s into False (this helps with the formatting of the truth table
     * @return 
     */
    private boolean[][] generateBooleanArrays() {
        String[] input = generateStringArrays();
        int num = (int) Math.pow(2, variableCount);
        boolean[][] result = new boolean[num][variableCount];
        int outerIndex = 0;
        for ( String s : input ) {
            int innerIndex = 0;
            for ( char x : s.toCharArray() ) {
                if ( x == '0' ) 
                    result[outerIndex][innerIndex] = true;
                else
                    result[outerIndex][innerIndex] = false;
                
                innerIndex++;
            }
            outerIndex++;
        } 
        return result;
    }
    
    /**
     * Method that returns the truth value of the sentence when passed an array
     * of boolean values
     * @param vals array of boolean values that are used to evaluate the sentence
     * values are assosciated with variables by alphabetic order.
     * @return 
     */
    public boolean isTrue(boolean[] vals) {
        return evaluateTrueFalseString(parseToTrueFalse(vals));
    }
    
    /**
     * This method evaluates the sentence after it has been turned into a string
     * of 't's, 'f's, and logical operators
     * @param s string which will be evaluated
     * @return true if it returns true, false if not
     */
    private boolean evaluateTrueFalseString(String s) {     
        OurStack<Character> vals = new OurStack<>(s.length());

        
        for ( Character x : s.toCharArray() ) {
            if (  x == 't' || x == 'f' ) { 
                vals.push(x);
            }
            else if ( vals.getSize() >= 1 && x == '¬' ) {
                char temp = vals.pop();
                if ( temp == 'f' )
                    vals.push('t');
                else
                    vals.push('f');
            }
            else if ( vals.getSize() >= 2 && x == '∧') {
                char temp1 = vals.pop();
                char temp2 = vals.pop();
                if ( temp1 == 't' && temp2 == 't')
                    vals.push('t');
                else
                    vals.push('f');
            }
            else if ( vals.getSize() >= 2 && x == '∨') {
                char temp1 = vals.pop();
                char temp2 = vals.pop();
                if ( temp1 == 't' || temp2 == 't' ) 
                    vals.push('t');
                else
                    vals.push('f');
            }
        }
        
        if ( vals.getSize() != 1 ) {
            IllegalArgumentException e = new IllegalArgumentException();
            throw e;
        }
        
        return vals.pop() == 't';
        
    }
    
    /**
     * This method parses the sentence to a string containing 't's, 'f's, and 
     * logical operators based on a boolean array 
     * @param vals array of boolean values that are used to replace the free variables
     * @return a string of 't's, 'f's, and logical operators
     */
    private String parseToTrueFalse(boolean[] vals) {
        if ( vals.length != variableCount ) {
           IllegalArgumentException e = new IllegalArgumentException();
           throw e;
        }
        String trueFalse = sentence;
        Character[] props = getProps();
        
        for ( int index = 0; index < variableCount; index++ ) {
            if ( vals[index] == true ) {
                trueFalse = trueFalse.replaceAll(props[index].toString(), "1111");
            }
            else
                trueFalse = trueFalse.replaceAll(props[index].toString(), "2222");
        }
        
        trueFalse = trueFalse.replaceAll("1111", "t");
        trueFalse = trueFalse.replaceAll("2222", "f");
        trueFalse = trueFalse.replaceAll("\\s+", "");
        return trueFalse;
    }
    
    /**
     * This method gets the distinct free variables in a sentence
     * @return Character array of all the free variables in the sentence
     */
    private Character[] getProps() {
        Character[] props = new Character[variableCount];
        int index = 0;
        LinkedBag<Character> bag = new LinkedBag<>();
        for ( Character x : sentence.toCharArray() ) {
            if ( Character.isLetter(x) && !bag.contains(x) ) {
                props[index] = x;
                index++;
                bag.add(x);
            }//end if
        }//end for
        MyUtils.arrayShellSort(props, 0, variableCount - 1);
        Character[] result = new Character[variableCount];
        
        for ( int i = variableCount - 1; i >=0; i-- ) {
            result[variableCount - i - 1] = props[i];
        }
        
        return result;
    }
    
    /**
     * Counts the number of free variables in the sentence
     * @return the number of free variables in the sentence
     */
    private int freeVariableCounter() {
        ArraySet<Character> count = new ArraySet<>();
        for ( char x : sentence.toCharArray() ) {
            if ( Character.isLetter(x) )
                count.add(x);
        }
        return count.size();
    }
    
    /**
     * The operators.  Operator precedence hasn't quite been implemented yet
     */
    private enum Operators {
        CONJUNCTION(1, '∧'), DISJUNCTION(2, '∨'), NEGATION(0, '¬'), PARENS(-1, '(');
        private final int val;
        private final Character symbol;
        
        private Operators(int val, Character symbol) {
            this.val = val;
            this.symbol = symbol;
        }
        
        private int getVal() {
            return val;
        }
        
        private Character getSymbol() {
            return symbol;
        }
        
    }//end enum Operators
    
    /**
     * sentence mutator.  HAsn't really been implemented yet
     * @param sentence 
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
        this.stringParser();
    }
    
    /**
     * sentence accessor
     * @return the sentence
     */
    public String getSentence() {
        return this.sentence;
    }
    
    /**
     * variableCount accessor
     * @return the number of free variables in the sentence
     */
    public int getVariableCount() {
        return variableCount;
    }
    
    /**
     * Does a little bit of tidying up of the sentence.
     * Removes white space, sets all delimiters to parens, and ensures
     * the delimiters are ballanced
     * @return 
     */
    private boolean stringParser() {
        this.whiteSpaceRemove();
        this.delimiterSetter();
        return this.balancedDelimiter();
    }

    /**
     * removes white space from the sentence
     */
    private void whiteSpaceRemove() {
        if ( sentence != null )         
            sentence = sentence.replaceAll("\\s+", "");
    }
    
    /**
     * sets all delimiters to parens
     */
    private void delimiterSetter() {
        if ( sentence != null ) {
            sentence = sentence.replaceAll("\\[|\\{", "(");
            sentence = sentence.replaceAll("\\]|\\}", ")");
        }
    }
    
    /**
     * ensures delimiters are balanced
     * @return true if delimiters ballanced, fales if not
     */
    private boolean balancedDelimiter() {
        OurStack<Character> parens = new OurStack<>(sentence.length());
        Character temp = null;
        
        for ( char x : sentence.toCharArray() ) {
            if ( x == '(' )
                parens.push(x); //end if
            else if ( x == ')' ) {
                try {
                    temp = parens.pop();
                    if ( temp != '(' || temp == null )
                        return false;
                } //end try
                catch (IndexOutOfBoundsException | NullPointerException e) {
                    return false;
                } //end catch
            } //end else if
        }//end for
        return parens.isEmpty();
    }//end balancedDelimiter
    
    /**
     * This method translates the sentence from its input syntax to
     * reverse Polish notation
     */
    private void postFixTranslator() {
        OurStack<Operators> ops = new OurStack<>(sentence.length());
        String result = "";
        this.sentenceReverser();
        
        for ( Character x : sentence.toCharArray() ) {
            if ( Character.isLetter(x) && x != 'v' ) 
                result += x + " ";
            else if ( x == '!' || x == '~' || x == '-' || x == '¬')
                result += "¬ ";
            else if ( x == 'v' || x == '∨' ) {
                result += operatorOps(Operators.DISJUNCTION, ops);
            }
            else if ( x == '^' || x == '∧' ) {
                result += operatorOps(Operators.CONJUNCTION, ops);
            }
            else if ( x == '(' ) {
                ops.push(Operators.PARENS);
            }
            else if ( x == ')' ) {
                result += parensOps(ops);
            }
        }
        result += closeStack(ops);
        sentence = result;
    }
    
    /**
     * This method reverses the order of the sentence.  This is done in an effort
     * to handle the unary negation operator
     */
    private void sentenceReverser() {
        String temp = new StringBuffer(sentence).reverse().toString();
        sentence = "";
        for ( char x : temp.toCharArray() ) {
            if ( x == '(' )
                sentence += ')';
            else if ( x == ')' )
                sentence += '(';
            else
                sentence += x;
        }
    }
    
    /**
     * Creates a string that is the concatention of any remaining operator symbols
     * from the operator stack.  Used to empty out the operator stack
     * @param ops operators stack
     * @return string of operator symbols
     */
    private String closeStack(OurStack<Operators> ops) {
        String result = "";
        while ( !ops.isEmpty() ) {
            result += ops.pop().getSymbol();
        }
        return result;
    }
    
    /**
     * Handles parenthesis.  Used by postFixTranslator method
     * @param ops
     * @return string of operator symbols
     */
    private String parensOps(OurStack<Operators> ops) {
        String result = "";
        while ( ops.peek().getVal() != -1 ) {
            result+=ops.pop().getSymbol() + " ";
        }
        ops.pop();
        return result;
    }
        
    /**
     * Handles operators.  Used by postFixTranslator method
     * @param operator
     * @param ops
     * @return string of operator symbols
     */
    private String operatorOps(Operators operator, OurStack<Operators> ops) {
        String result = "";
        int opVal = operator.getVal();
        while ( !ops.isEmpty() && opVal <= ops.peek().getVal() )
            result += ops.pop().getSymbol();
        
        ops.push(operator);
        return result;
    }

}
