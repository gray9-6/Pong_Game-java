import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    /**/
    /*Declaring the properties of Text Editor*/
    /*Declaring the JFrame class for application window*/
    JFrame frame;

    /*Declaring JMenuBar class for Menus */
    JMenuBar menuBar;

    /*Creating the menus for MenuBar*/
    JMenu file,edit;

    /*Creating Menu Items*/
    /*File menu items*/
    JMenuItem newFile,openFile,saveFile;
    /*Edit Menu Items*/
    JMenuItem cut,copy,paste,selectAll,close;

    /*Now we need text area */
    JTextArea textArea;


    /*Constructor of main class (i.e TextEditor class)
    * initializing the properties of  data member*/
    TextEditor(){
        /*Initializing the frame*/
        frame = new JFrame();

        /*Intializing the menu bar first,, then add the menubar to frame,, now we have to create the menus*/
        menuBar = new JMenuBar();

        /*Initializing the text area,, after the initializing of menuBar */
        textArea = new JTextArea();

        /*Initializing the menus first, then add it into ,the menuBar*/
        file = new JMenu("File");
        edit = new JMenu("Edit");

        /*Now initializing the menus items*/
        /*Intializing the file menu items*/
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        /*So before adding the menuItems to menu, we need to add the menu items to actionListner so that
        *  action listner can know where the action can accurs*/
        /*Add action listner to file menu*/
        /*this keyword is refering to the object of textEditor class,, so basically Text Editor is behaving like an
        action listner,, it is implementing the method of action listner,so it is behaving like actionListner class*/
        /*so this(which is refering to curr class object i.e Text Editor) is listening to the newfile*/
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        /*Adding the file menu items to the file menu*/
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        /*Intializing the Edit menu items*/
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        /*So before adding the menuItems to menu, we need to add the menu items to actionListner so that
         *  action listner can know where the action can accurs*/
        /*Add action listner to file menu*/
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        /*Adding the Edit menu items to the Edit menu*/
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        /*Add menus to menuBar,,, but make sure before adding the menus to menuBar,
        we have menu items in the menus*/
        menuBar.add(file);
        menuBar.add(edit);

        /*now we have to set menu bar to frame
        * but if i run the program now ,, it will be blank on menu bar
        * becoz we don't have menus(Like file and edit),,, so we will create the menus
        * to add the menuBar in frame ,, setJMenuBar function is used ,, this is special used for menusBars
        * for rest of them we can used add function,. */
        frame.setJMenuBar(menuBar);

        /* create a content Pane*/
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        /*Now after creating the control panel ,,, we will add the text area to the panel*/
        panel.add(textArea,BorderLayout.CENTER);

        /*now we need to create the scroll pane,, and make the text area scrollable*/
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        /*now add scroll pane to panel*/
        panel.add(scrollPane);
        /*now add panel to the frame*/
        frame.add(panel);

        /*set dimensions of fram*/
        frame.setBounds(250,100,800,500);
        /*setting the tile */
        frame.setTitle("Text Editor");
        /*setting the  frame resizable to false */
        frame.setResizable(false);
        /*setting frame visible*/
        frame.setVisible(true);
        /*setting frame layout to null*/
        frame.setLayout(null);
        /*setting famr to close ,when we click on the red cross button*/
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*we are overriding the ActionListener class according to our need*/
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        /*so now we need to tie the action listener to all the places where action event can happen
        * basically we need to tell the action Listener ,, where the action event occurs*/
        /*so we need to tie the action listener to all the menu items,,so that action listener knows
        * here ,, the action event can happen*/

        /*so now we need to specify,according to the action event what functions we need to do */
        /*So there are particular menu items ,, and when we are clicking on them ,, they are generating
        * the action events*/

        if(actionEvent.getSource() == cut){
             // perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // perform select All operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            // perform close operation,, it will exit from the console
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            // open/Initialize a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting the selected file
                File file = fileChooser.getSelectedFile();
                // get path of the selected file
                String filePath = file.getPath();
                //now after this i need to just save all the content of the file text to text area
                try{
                    // intialize the file reader
                    FileReader fileReader = new FileReader(filePath);
                    // intialize the buffer reader,,, using this we are going to read the content of that file
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    // intermediate - it is holding the contents of current line,,, output - is pasting the complete text
                    String intermediate = "", output = "";
                    // read content of file line by line
                    while((intermediate = bufferedReader.readLine()) != null ){
                        /*we are concatinating the intermediate string,, we are concatinating the curr line to
                        * complete string ,,, so we are just reading a line and pasting it to the output,, then we
                        * are reading the another line and pasting it to the output*/
                        output += intermediate + "\n";
                    }
                    // set the output string to the text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // Initialize a file chooser (we can give any path)
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                //now after this i need to just save all the content of the text area to this file
                try{
                    /*Intializing the file writer ,,becoz here we don't need to read the file ,, we just
                    need to write the text in the file from text area to fiel txt */
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize the BufferWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    /*we use a function that is present inside a text area, to write this buffer writer
                    * write the content of text area to the file*/
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            // creating a new text editor
            TextEditor newtextEditor = new TextEditor();

        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

}