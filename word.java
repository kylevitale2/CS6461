public class word {
    public boolean[] value;

    //empty constructor -- returns empty word
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
    }

    public word(boolean[] a){
        if(a.length == 16)
            for(int i = 0; i<16; i++)
                value[i] = a[i];
    }

    public String toString(){
        String val = "";
        for(int i = 0; i<value.length; i++)
            val += (value[i] ? 1 : 0);
        return val;
    }

    public boolean[] toBitArray(){
        return value;
    }

    public void overWrite(word replacement){
        boolean[] replace = replacement.toBitArray();
        for(int i = 0; i<16; i++)
            value[i] = replace[i];
    }

}
