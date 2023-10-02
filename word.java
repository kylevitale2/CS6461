public class word {
    public boolean[] value;

    /*
     * Constructors to handle different inputs for creating a word object
     */
    public word(){
        value = new boolean[16];
    }

    public word(String s){
        value = new boolean[16];
        if(s.length() == 16){  
            for(int i = 0; i<16; i++){
                if(s.charAt(i) == '0')
                    value[i] = false;
                else
                    value[i] = true;
            }
        }
        else if(s.length() < 16){
            boolean[] bin = operational.binaryStringToBinary(s);
            int pos = 15;
            for(int i = bin.length-1; i>=0; i--){
                value[pos] = bin[i];
                pos--;
            }
        }
    }

    public word(boolean[] a){
        value = new boolean[16];
        if(a.length == 16)
            for(int i = 0; i<16; i++)
                value[i] = a[i];
    }

    /*
     * Prints out the string of the word
     */
    public String toString(){
        String val = "";
        for(int i = 0; i<value.length; i++)
            val += (value[i] ? 1 : 0);
        return val;
    }

    /*
     * Returns boolean array representation of word
     */
    public boolean[] toBitArray(){
        return value;
    }

    /*
     * Deeply replaces the word
     */
    public void overWrite(word replacement){
        boolean[] replace = replacement.toBitArray();
        for(int i = 0; i<16; i++)
            value[i] = replace[i];
    }

}
