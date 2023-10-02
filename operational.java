import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Handles most backend processes
public class operational {
    public static boolean[] PC;
    public static boolean[] MAR;
    public static word MBR;
    public static word IR;
    public static boolean[] MFR;
    public static boolean Priv;
    public static word[] memory;
    
    //Stores the MBR into memory at MAR
    public static void Store(){
        int address = binToDec(MAR);
        if(address >= memory.length){
            System.out.println("Error: Out of memory bounds");
            return;
        }
        memory[address].overWrite(MBR);
    }

    //Same as store, but increments MAR
    public static void StorePlus(){
        Store();
        increment(MAR);
    }

    //binary to decimal
    public static int binToDec(boolean[] bin){
        int dec = 0;
        int multiplier = 1;
        for(int i = bin.length-1; i>=0; i--){
            dec += multiplier * (bin[i] ? 1 : 0);
            multiplier *=2;
        }
        return dec;
    }

    //decimal to binary
    public static boolean[] decToBin(int dec, int indexCount){
        String bin = Integer.toBinaryString(dec);
        boolean[] result = new boolean[indexCount];
        if(bin.length()>indexCount){
            System.out.println("Binary overflow");
            return null;
        }

        while(bin.length() < indexCount){
            bin = "0" + bin;
        }

        for(int i = 0; i<indexCount; i++){
            if(bin.charAt(i) == '0')
                result[i] = false;
            else
                result[i] = true;
        }

        return result;
    }

    //increment binary
    public static void increment(boolean[] bin){
        for(int i = bin.length-1; i>=0; i--){
            if(bin[i]){
                bin[i] = false;
            }
            else{
                bin[i] = true;
                return;
            }
        }
    }

    //converts a string to a 'binary' array
    public static boolean[] binaryStringToBinary(String s){
        boolean[] bin = new boolean[s.length()];
        for(int i = 0; i<bin.length; i++){
            bin[i] = ('1' == s.charAt(i));
        }
        return bin;
    }

    //convert binary to string
    public static String binaryToString(boolean[] binary){
        String val = "";
        for(int i = 0; i<binary.length; i++)
            val += (binary[i] ? 1 : 0);
        return val;
    }

    //forward operation to string format
    public static void decodeOpcode(boolean[] instruction){
        decodeOpcode(binaryToString(instruction));
    }

    //decode operation
    public static boolean decodeOpcode(String instruction){
        int opCode = binToDec(binaryStringToBinary(instruction.substring(0, 6)));
        int gprReg = binToDec(binaryStringToBinary(instruction.substring(6, 8)));
        int indexReg = binToDec(binaryStringToBinary(instruction.substring(8, 10)));
        int address = binToDec(binaryStringToBinary(instruction.substring(11)));
        int EA;
        word genR;
        word inR;

        //Evaluate what the general purpose register is
        if(gprReg == 0)
            genR = gpr.R0;
        else if(gprReg == 1)
            genR = gpr.R1;
        else if(gprReg == 2)
            genR = gpr.R2;
        else
            genR = gpr.R3;

        //Evaluate what the index register is
        if(indexReg == 1)
            inR = index.X1;
        else if(indexReg == 2)
            inR = index.X2;
        else if(indexReg == 3)
            inR = index.X3;
        else
            inR = null;


        //Compute the effective address (EA)
        //direct address
        if(instruction.charAt(10) == '0'){
            if(indexReg == 0){
                EA = address;
            }
            else{
                EA = operational.binToDec(inR.toBitArray()) + address;
            }
        }
        //indirect addressing
        else{
            if(indexReg == 0){
                EA = operational.binToDec(memory[address].toBitArray());
            }
            else{
                EA = operational.binToDec(memory[address+operational.binToDec(inR.toBitArray())].toBitArray());
            }
        }

        //Halt program operation
        if(opCode == 0){
            System.out.println("Halt program");
            gui.halt.setText("1");
            return true;
        }

        /**
         * LDR
         * Load Register From Memory, r = 0..3 
            r <- c(EA) 
         */
        else if (opCode == 1){
            genR.overWrite(memory[EA]);
        }

        /**
         * STR
         * Store Register To Memory, r = 0..3 
         *  Memory(EA) <- c(r) 

         */
        else if (opCode == 2){
            memory[EA].overWrite(genR);
        }

        /**
         * LDA
         * Load Register with Address, r = 0..3 
         * r <- EA 
         */
        else if (opCode == 3){
            genR.overWrite(new word(operational.decToBin(EA, 16)));
        }

        /*
         * LDX
         * Load Index Register from Memory, x = 1..3 
            Xx <- c(EA)
         */
        else if (opCode == 33){
            inR.overWrite(memory[EA]);
        }

        /*
         * STX
         * Store Index Register to Memory. X = 1..3 
         * Memory(EA) <- c(Xx)
         */
        else if (opCode == 34){
            memory[EA].overWrite(inR);
        }

        //unknown operation
        else{
            System.out.println("Unknown Operation");
            return true;
        }

        return false;

    }

    //Runs through a single step in the program
    public static void SS(){
        int address = operational.binToDec(PC);
        decodeOpcode(memory[address].toString());
        operational.increment(PC);
    }

    //Runs through all steps in the program
    public static void Run(){
        int address = operational.binToDec(PC);
        while(!decodeOpcode(memory[address].toString())){
            operational.increment(PC);
            address = operational.binToDec(PC);
        }
    }

    //Loads in the IPL.txt file to load in the program
    public static void Init(){
        File file = new File("./ipl.txt");
        String line;
        int address;
        int instruction;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
            while (line != null) {
                address = Integer.parseInt(line.substring(0, 4), 16);
                System.out.println(address);
                //System.out.println(line.length());
                instruction = Integer.parseInt(line.substring((line.length() - 4)), 16);
                System.out.println(instruction);
                memory[address] = new word(Integer.toBinaryString(instruction));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
