import javax.swing.*;

import java.awt.*;


class gui{
    //Create textboxes
    public static JTextField GPR0, GPR1, GPR2, GPR3, IXR1, IXR2, IXR3, PC, MAR, MBR, IR, MFR, PRIV, input;

    public static void main(String args[]){
        start();
    }

    public static void start(){
        operational.PC = new boolean[12];
        operational.MAR = new boolean[12];
        operational.MBR = new word();
        operational.IR = new word();
        operational.MFR = new boolean[4];
        operational.Priv = false;
        operational.memory = new word[2048];

        for(int i = 0; i<operational.memory.length; i++){
            operational.memory[i] = new word();
        }

        gpr.R0 = new word();
        gpr.R1 = new word();
        gpr.R2 = new word();
        gpr.R3 = new word();

        index.X1 = new word();
        index.X2 = new word();
        index.X3 = new word();

        JFrame frame = new JFrame("CISC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        
        // Set JFrame layout manager 
        frame.setLayout(new GridLayout(8,3));
        

        //4 GPRs
        JLabel GPR0L = new JLabel("GPR0");
        frame.add(GPR0L);
        GPR0 = new JTextField(gpr.R0.toString());
        GPR0.setEditable(false);
        frame.add(GPR0);
        JButton GPR0B = new JButton("LD");
        frame.add(GPR0B);
        GPR0B.addActionListener(e -> GPR0BPressed());

        JLabel GPR1L = new JLabel("GPR1");
        frame.add(GPR1L);
        GPR1 = new JTextField(gpr.R1.toString());
        GPR1.setEditable(false);
        frame.add(GPR1);
        JButton GPR1B = new JButton("LD");
        frame.add(GPR1B);
        GPR1B.addActionListener(e -> GPR1BPressed());

        JLabel GPR2L = new JLabel("GPR2");
        frame.add(GPR2L);
        GPR2 = new JTextField(gpr.R2.toString());
        GPR2.setEditable(false);
        frame.add(GPR2);
        JButton GPR2B = new JButton("LD");
        frame.add(GPR2B);
        GPR2B.addActionListener(e -> GPR2BPressed());

        JLabel GPR3L = new JLabel("GPR3");
        frame.add(GPR3L);
        GPR3 = new JTextField(gpr.R3.toString());
        GPR3.setEditable(false);
        frame.add(GPR3);
        JButton GPR3B = new JButton("LD");
        frame.add(GPR3B);
        GPR3B.addActionListener(e -> GPR3BPressed());


        //3 IXRs
        JLabel IXR1L = new JLabel("IXR1");
        frame.add(IXR1L);
        IXR1 = new JTextField(index.X1.toString());
        IXR1.setEditable(false);
        frame.add(IXR1);
        JButton IXR1B = new JButton("LD");
        frame.add(IXR1B);
        IXR1B.addActionListener(e -> IXR1BPressed());

        JLabel IXR2L = new JLabel("IXR2");
        frame.add(IXR2L);
        IXR2 = new JTextField(index.X2.toString());
        IXR2.setEditable(false);
        frame.add(IXR2);
        JButton IXR2B = new JButton("LD");
        frame.add(IXR2B);
        IXR2B.addActionListener(e -> IXR2BPressed());

        JLabel IXR3L = new JLabel("IXR3");
        frame.add(IXR3L);
        IXR3 = new JTextField(index.X3.toString());
        IXR3.setEditable(false);
        frame.add(IXR3);
        JButton IXR3B = new JButton("LD");
        frame.add(IXR3B);
        IXR3B.addActionListener(e -> IXR3BPressed());

        //PC
        JLabel PCL = new JLabel("PC");
        frame.add(PCL);
        PC = new JTextField("000000000000");
        PC.setEditable(false);
        frame.add(PC);
        JButton PCB = new JButton("LD");
        frame.add(PCB);
        PCB.addActionListener(e -> PCBPressed());

        //MAR
        JLabel MARL = new JLabel("MAR");
        frame.add(MARL);
        MAR = new JTextField("000000000000");
        MAR.setEditable(false);
        frame.add(MAR);
        JButton MARB = new JButton("LD");
        frame.add(MARB);
        MARB.addActionListener(e -> MARBPressed());

        //MBR
        JLabel MBRL = new JLabel("MBR");
        frame.add(MBRL);
        MBR = new JTextField("0000000000000000");
        MBR.setEditable(false);
        frame.add(MBR);
        JButton MBRB = new JButton("LD");
        frame.add(MBRB);
        MBRB.addActionListener(e -> MBRBPressed());

        //IR
        JLabel IRL = new JLabel("IR");
        frame.add(IRL);
        IR = new JTextField("0000000000000000");
        IR.setEditable(false);
        frame.add(IR);

        //MFR
        JLabel MFRL = new JLabel("MFR");
        frame.add(MFRL);
        MFR = new JTextField("0000");
        MFR.setEditable(false);
        frame.add(MFR);

        //Privileged
        JLabel PRIVL = new JLabel("Privileged");
        frame.add(PRIVL);
        PRIV = new JTextField("0");
        PRIV.setEditable(false);
        frame.add(PRIV);

        //Input
        JLabel inputL = new JLabel("Input");
        frame.add(inputL);
        input = new JTextField("0000000000000000", 16);
        frame.add(input);


        

        //Option Buttons
        //Store
        JButton StoreB = new JButton("Store");
        frame.add(StoreB);
        StoreB.addActionListener(e -> StoreBPressed());

        //Store+
        JButton StorePB = new JButton("St+");
        frame.add(StorePB);
        StorePB.addActionListener(e -> StorePBPressed());

        //Load
        JButton LoadB = new JButton("Load");
        frame.add(LoadB);
        LoadB.addActionListener(e -> LoadBPressed());

        //Init
        JButton InitB = new JButton("Init");
        frame.add(InitB);
        InitB.addActionListener(e -> InitBPressed());

        //SS
        JButton SSB = new JButton("SS");
        frame.add(SSB);
        SSB.addActionListener(e -> SSBPressed());

        //Run
        JButton RunB = new JButton("Run");
        frame.add(RunB);
        RunB.addActionListener(e -> RunBPressed());





        //Display Window
        frame.setVisible(true);
    }

    public static boolean validInputWord(String inText){
        if(inText.length() != 16)
            return false;
        for(int x = 0; x<inText.length(); x++){
            if(inText.charAt(x) != '0' && inText.charAt(x) != '1'){
                System.out.println("Invalid input string");
                return false;
            }   
        }
        return true;
    }


    /**
     *  NOW WE DEFINE METHODS FOR THE BUTTONS TO LOAD IN VALUES
     */

    /**
     *  Until the next multi-line header, these methods define pressing 'LD' buttons next to the various registers
     */
    public static void GPR0BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        GPR0.setText(text);
        gpr.R0 = new word(text);
    }

    public static void GPR1BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        GPR1.setText(text);
        gpr.R1 = new word(text);
    }

    public static void GPR2BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        GPR2.setText(text);
        gpr.R2 = new word(text);
    }

    public static void GPR3BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        GPR3.setText(text);
        gpr.R3 = new word(text);
    }

    public static void IXR1BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        IXR1.setText(text);
        index.X1 = new word(text);
    }

    public static void IXR2BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        IXR2.setText(text);
        index.X2 = new word(text);
    }

    public static void IXR3BPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        IXR3.setText(text);
        index.X3 = new word(text);
    }

    public static void PCBPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        String b12 = text.substring(4);
        PC.setText(b12);
        operational.PC = operational.binaryStringToBinary(b12);
    }

    public static void MARBPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        String b12 = text.substring(4);
        MAR.setText(b12);
        operational.MAR = operational.binaryStringToBinary(b12);
    }

    public static void MBRBPressed(){
        String text = input.getText();
        if(!validInputWord(text)){
            return;
        }
        MBR.setText(text);
        operational.MBR = new word(text);
    }


    /*
     * These operations define different operations the console can takes
     */
    //Stores MBR into memory at MAR
    public static void StoreBPressed(){
        operational.Store();
        update();
    }
    //Stores MBR into memory at MAR, and increments MAR
    public static void StorePBPressed(){
        operational.StorePlus();
        update();
    }

    //Load in the MBR from memory
    public static void LoadBPressed(){
        operational.MBR = operational.memory[operational.binToDec(operational.MAR)];
        gui.MBR.setText(operational.MBR.toString());

    }
    //Load program file
    public static void InitBPressed(){
        operational.Init();
        update();
    }
    //Run a line of the program
    public static void SSBPressed(){
        operational.SS();
        update();
    }
    //Runs entire program
    public static void RunBPressed(){
        operational.Run();
        update();
    }

    public static void update(){
        GPR0.setText(gpr.R0.toString());
        GPR1.setText(gpr.R1.toString());
        GPR2.setText(gpr.R2.toString());
        GPR3.setText(gpr.R3.toString());

        IXR1.setText(index.X1.toString());
        IXR2.setText(index.X2.toString());
        IXR3.setText(index.X3.toString());

        PC.setText(operational.binaryToString(operational.PC));
        MAR.setText(operational.binaryToString(operational.MAR));
        MBR.setText(operational.MBR.toString());
        IR.setText(operational.IR.toString());
        MFR.setText(operational.binaryToString(operational.MFR));
        PRIV.setText((operational.Priv ? "1" : "0"));
    }

}
