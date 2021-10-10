import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class ViewerGUI extends JFrame implements ActionListener{    
    static JFrame f;    
    JMenuBar mb;    
    JMenu Home, Shows, Movies, Shorts, Videos; 
    JMenuItem home_, show_, movie_, short_, video_;
    JButton team_logo, search_button;
    JTextField search_field;
    TextField text = new TextField(15), most_watch = new TextField(15), top_rate = new TextField(20), recent_watch = new TextField(15);
    private JPanel main_panel, buff_field, most_watched, top_rated, recently_watched;
    private int numClicks = 0;
    ViewerGUI(){
        /* NAVBAR SECTION*/

        // Logo and search button
        // team_logo = new JButton(new ImageIcon("Team_Logo.png"));    
        // team_logo.setBounds(10,10,10, 4); 
        search_button = new JButton("Search");
        search_field = new JTextField("Search Movies/Shows");
        search_field.setEditable(true);  

         // The menu items (inside the menu list)
        f = new JFrame("ZAS VIEWER GUI");    

        home_ = new JMenuItem("Show all titles");
        show_ = new JMenuItem("Show only Shows");
        movie_ = new JMenuItem("Show only Movies");
        short_ = new JMenuItem("Show only Shorts");
        video_ = new JMenuItem("Show only Videos");


        home_.addActionListener(this);
        show_.addActionListener(this);
        movie_.addActionListener(this);
        short_.addActionListener(this);
        video_.addActionListener(this);

        // team_logo.addActionListener(this);    

        // Menue and contents
        mb = new JMenuBar();    
        Home = new JMenu("Home");    
        Shows = new JMenu("Shows");    
        Movies = new JMenu("Movies");     
        Shorts = new JMenu("Shorts");
        Videos = new JMenu("Videos");

        Home.add(home_); Movies.add(movie_); Shows.add(show_); Shorts.add(short_); Videos.add(video_);

        // mb.add(team_logo);
        mb.add(Box.createHorizontalGlue());
        mb.add(Home);mb.add(Box.createHorizontalGlue());
        mb.add(Shows);mb.add(Box.createHorizontalGlue());
        mb.add(Movies);mb.add(Box.createHorizontalGlue());
        mb.add(Shorts);mb.add(Box.createHorizontalGlue());
        mb.add(Videos);mb.add(Box.createHorizontalGlue());
        mb.add(search_field);mb.add(search_button);  
        mb.revalidate();

        /* MAIN CONTENT SECTION*/
        buff_field = new JPanel();
        main_panel = new JPanel();  // Main container
        // main_panel.setBackground(Color.white);

        // Panels
        main_panel = new JPanel(); // main panel
        main_panel.setLayout(new GridLayout(3, 1));
        //main_panel.add(new JLabel("Main Panel"));
        main_panel.setBackground(Color.white);
        main_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Most watched movie
        most_watched = new JPanel();
        TitledBorder title_most_watched = new TitledBorder("Most Watched");
        title_most_watched.setTitleJustification(TitledBorder.CENTER);
        title_most_watched.setTitlePosition(TitledBorder.TOP);
        /* List of movies*/
        DefaultListModel<String> l1 = new DefaultListModel<>();  
        l1.addElement("MOST WATCHED"); 
        l1.addElement("Item1");  
        l1.addElement("Item2");  
        l1.addElement("Item3");  
        l1.addElement("Item4");  
        JList<String> most_watchedlist = new JList<>(l1);  
        most_watchedlist.setBounds(100,100, 375,375);  

        most_watched.add(most_watchedlist);  // Add the list to the movie  

        // Top rated movie
        top_rated = new JPanel(); // sub-panel 2
        DefaultListModel<String> l2 = new DefaultListModel<>();  
        l2.addElement("TOP RATED"); 
        l2.addElement("Item1");  
        l2.addElement("Item2");  
        l2.addElement("Item3");  
        l2.addElement("Item4");  
        JList<String> top_ratedlist = new JList<>(l2);  
        top_ratedlist.setBounds(100,100, 75,75);  

        top_rated.add(top_ratedlist);

        // Recently Watched
        recently_watched = new JPanel(); // sub-panel 3
        DefaultListModel<String> l3 = new DefaultListModel<>();  
        l3.addElement("RECENTLY WATCHED"); 
        l3.addElement("Item1");  
        l3.addElement("Item2");  
        l3.addElement("Item3");  
        l3.addElement("Item4");  
        JList<String> recentlist = new JList<>(l3);  
        recentlist.setBounds(100,100, 75,75);  

        recently_watched.add(recentlist);

        // Add to the main panel
        //main_panel.add(most_watched);
        main_panel.add(most_watch);
        //main_panel.add(top_rated);
        main_panel.add(top_rate);
        //main_panel.add(recently_watched);
        main_panel.add(recent_watch);
        //main_panel.add(text);

        // Adding everything to the frame
        f.setLayout(new BorderLayout(50, 15));
        f.add(mb);f.add(main_panel, BorderLayout.CENTER);    
        f.setJMenuBar(mb);  
        f.setSize(1200,700);  
        setLocationRelativeTo(null);  
        f.setVisible(true);    
    }
    // Actions when events is detected     
    public void actionPerformed(ActionEvent e) {    
        numClicks ++;
        //text.setText("Button Clicked " + numClicks + " times");
        if(e.getSource() == home_) {
          //text.setText("Button Clicked " + numClicks + " times after clicking home");
        } else if (e.getSource() == movie_) {
          //text.setText("Button Clicked " + numClicks + " times after clicking movies");
        } else if (e.getSource() == video_) {
          //text.setText("Button Clicked " + numClicks + " times after clicking videos");
        } else if (e.getSource() == short_) {
          //text.setText("Button Clicked " + numClicks + " times after clicking shorts");
        } else if (e.getSource() == show_) {
          //text.setText("Button Clicked " + numClicks + " times after clicking shows");
        }

      
    }   

    




    class FHold {//Connection Runner "Function Holder" allows us to call queries without reestablishing connection
    //static fields are here
    String userName;
    Connection conn;
    String teamNumber;
    String sectionNumber;
    String dbName;
    String dbConnectionString;
    String userPassword;
    public FHold(){
        teamNumber = "2";
        sectionNumber = "901";
        dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
        dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
        userPassword = "password1";
        try {
            conn = DriverManager.getConnection(dbConnectionString,userName, userPassword);
            } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
            }
        System.out.println("Opened database successfully");   
    }
     
    public String call_query(String Query) {
        //System.out.println(userName);
        String returnstring = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(Query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            //System.out.println("--------------------Results: --------------------");
            //figure out how to turn these into returnables later

            while (resultSet.next()) {
                for (int i = 0; i <= columnsNumber; i++) {
                    
                    if (i > 0){ 
                    
                    String columnValue = resultSet.getString(i);
                    returnstring += (columnValue + " " + rsmd.getColumnName(i));
                    returnstring += (",  ");
                    }
                }
                System.out.println("");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
            }
        
        return returnstring;
    }
}







    // Main Function
    public static void main(String[] args) {    
        new ViewerGUI();    
    }    
    
}    