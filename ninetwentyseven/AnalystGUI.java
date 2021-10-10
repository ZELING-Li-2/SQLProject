import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.util.Arrays;

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
                    returnstring += (columnValue);
                    returnstring += ("/");
                    }
                }
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

public class AnalystGUI extends JFrame implements ActionListener{    
    static JFrame f;    
    JMenuBar mb;    
    JMenu Home, Shows, Movies, Shorts, Videos;    
    JMenuItem top_show, top_movie, movie_star, show_star, show_cus_rating, movie_cus_rating, movie_director, show_director, movie_writer, show_writer;  
    JButton team_logo, search_button;
    JTextField search_field;
    private JPanel main_panel, buff_field, most_watched, top_rated, data_stats;
    String user = "";
    JList<String> most_watchedlist, top_ratedlist, data_statslist;
    
    AnalystGUI(){//wtf is this language is this a constructor?
        /* NAVBAR SECTION*/
        FHold my_Fhold = new FHold();
        // Logo and search button
        // team_logo = new JButton(new ImageIcon("Team_Logo.png"));    
        // team_logo.setBounds(10,10,10, 4); 
        search_button = new JButton("Search");
        search_field = new JTextField("Search Movies/Shows");
        search_field.setEditable(true);  

         // The menue items (inside the menue list)
        f = new JFrame("ZAS ANALYST GUI");    
        
        
        top_show = new JMenuItem("Top 10");  
        show_star = new JMenuItem("By Star");    
        show_director = new JMenuItem("By director"); 
        show_cus_rating = new JMenuItem("By rating");    
        show_writer = new JMenuItem("By Writer"); 
        // Each content can only have one parent
        top_movie = new JMenuItem("Top 10");  
        movie_star = new JMenuItem("By Star");    
        movie_director = new JMenuItem("By director"); 
        movie_cus_rating = new JMenuItem("By rating");    
        movie_writer = new JMenuItem("By Writer");     

        // Add action Listeners
        top_movie.addActionListener(this);    
        movie_star.addActionListener(this);    
        movie_director.addActionListener(this);    
        movie_cus_rating.addActionListener(this); 
        movie_writer.addActionListener(this);
        top_show.addActionListener(this);    
        show_star.addActionListener(this);    
        show_director.addActionListener(this);    
        show_cus_rating.addActionListener(this); 
        show_writer.addActionListener(this);
        
        //search_button.addActionListener(this());
        search_button.addActionListener(e -> searchButton(my_Fhold));//using a lambda>
        // team_logo.addActionListener(this);    

        // Menue and contents
        mb = new JMenuBar();    
        Home = new JMenu("Home");    
        Shows = new JMenu("Shows");    
        Movies = new JMenu("Movies");     
        Shorts = new JMenu("Shorts");
        Videos = new JMenu("Videos");

        // Add menue items to menue lists, and menue list to menue bar
        Movies.add(top_movie);Movies.add(movie_star);Movies.add(movie_director);Movies.add(movie_cus_rating);Movies.add(movie_writer);    
        Shows.add(top_show);Shows.add(show_star);Shows.add(show_director);Shows.add(show_cus_rating);Shows.add(show_writer);    
        
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
        main_panel.setLayout(new GridLayout(1, 3));
        main_panel.add(new JLabel("Main Panel"));
        main_panel.setBackground(Color.white);
        main_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Most watched movie
        most_watched = new JPanel();
        TitledBorder title_most_watched = new TitledBorder("Most Watched");
        title_most_watched.setTitleJustification(TitledBorder.CENTER);
        title_most_watched.setTitlePosition(TitledBorder.TOP);
        /* List of movies*/
        DefaultListModel<String> l1 = new DefaultListModel<>();  
        l1.addElement("Most Watched"); 
        String searchQuery_mostwatched = "SELECT originalTitle FROM titles ORDER BY Views DESC LIMIT 10;"; 
        String mostwatched_query_def = my_Fhold.call_query(searchQuery_mostwatched);//query the text 
        String[] mostwatched_query_deflist = Arrays.copyOfRange(mostwatched_query_def.split("/"),0,4); 
        for (int z = 0; z < 10; z++){
            l1.addElement(mostwatched_query_deflist[z]);
        }
        JList most_watchedlist_def = new JList<>(l1);  
        most_watchedlist = new JList<>(l1);  
        most_watchedlist.setBounds(100,100, 75,75);  

        most_watched.removeAll();
        most_watched.revalidate();
        most_watched.add(most_watchedlist);  // Add the list to the movie  
        most_watched.revalidate();

        // Top rated movie
        top_rated = new JPanel(); // sub-panel 2
        DefaultListModel<String> l2 = new DefaultListModel<>();  
        l2.addElement("Top Rated"); //top 5 in numvotes in titles matching the search
        String searchQuery_top_rated = "SELECT originalTitle FROM titles ORDER BY averageRating DESC LIMIT 10;"; 
        String top_rated_query_def = my_Fhold.call_query(searchQuery_top_rated);//query the text 
        String[] top_rated_query_deflist = Arrays.copyOfRange(top_rated_query_def.split("/"),0,4); 
        for (int z = 0; z < 10; z++){
            l2.addElement(top_rated_query_deflist[z]);
        }        
        top_ratedlist = new JList<>(l2);  
        // top_ratedlist.setBounds(100,100, 75,75);   

        top_ratedlist.setBounds(100,100, 75,75);
        top_rated.removeAll();
        top_rated.revalidate();
        top_rated.add(top_ratedlist);
        top_rated.revalidate();


        //Statistics
        data_stats = new JPanel(); // sub-panel 3
        DefaultListModel<String> l3 = new DefaultListModel<>();  
        l3.addElement("Top Stars"); 
        String searchQuery_data_stats = "SELECT primaryName, COUNT(principals.titleId) as totalMovies FROM names INNER JOIN principals ON names.nconst = principals.nconst GROUP BY names.primaryname ORDER BY totalMovies DESC LIMIT 10;"; 
        String data_stats_query_def = my_Fhold.call_query(searchQuery_data_stats);//query the text 
        String[] data_stats_query_deflist = Arrays.copyOfRange(data_stats_query_def.split("/"),0,4); 
        for (int z = 0; z < 10; z++){
            l3.addElement(data_stats_query_deflist[z]);
        }        
        data_statslist = new JList<>(l3);  
        data_statslist.setBounds(100,100, 75,75);  

        data_stats.removeAll();
        data_stats.revalidate();
        data_stats.add(data_statslist);
        data_stats.revalidate();

        // Add to the main panel
        main_panel.add(most_watched);
        main_panel.add(top_rated);
        main_panel.add(data_stats);

        // Adding everything to the frame
        f.setLayout(new BorderLayout(50, 15));
        f.add(mb);f.add(main_panel, BorderLayout.CENTER);    
        f.setJMenuBar(mb);  
        f.setSize(1200,700);  
        setLocationRelativeTo(null);  
        f.setVisible(true);
        JOptionPane.showMessageDialog(f,"Hello, Welcome \n Z A S");
    
    }
    // Actions when events is detected
    
    public void actionPerformed(ActionEvent e) {    
        if (e.getSource() == top_movie){ // Reset main panel
            most_watched.removeAll();
            most_watched.revalidate();
            most_watched.add(most_watchedlist);
            most_watched.revalidate();

            top_rated.removeAll();
            top_rated.revalidate();
            top_rated.add(top_ratedlist);
            top_rated.revalidate();

            data_stats.removeAll();
            data_stats.revalidate();
            data_stats.add(data_statslist);
            data_stats.revalidate();
        }
    }   
    
    public void searchButton(FHold my_Fhold){//u gotta pass it in because aparently this does not share the class scope
        //System.out.println("Search Button Pressed!");
        //get string from JTextField search_field;
        String searchValue = search_field.getText();
        String searchQuery = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;"; 
        String searchQuery2 = "SELECT averageRating FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;"; 
        String searchQuery3 = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY views DESC;"; 


        //generate queries

        String top_rated_query = my_Fhold.call_query(searchQuery);//query the text 
        String top_rated_query_R = my_Fhold.call_query(searchQuery2);//ratings
        String top_rated_query_V = my_Fhold.call_query(searchQuery3);//Views

        String[] top_rated_querylist = Arrays.copyOfRange(top_rated_query.split("/"),0,10); 
        String[] top_rated_querylist_R = Arrays.copyOfRange(top_rated_query_R.split("/"),0,10);//ratings
        String[] top_rated_querylist_V = Arrays.copyOfRange(top_rated_query_V.split("/"),0,10);//votes
        

        //write to top rated

        DefaultListModel<String> l2a = new DefaultListModel<>(); 
        l2a.addElement("Top Rated"); //top 5 in numvotes in titles matching the search
        for (int z = 0; z < 10; z++){
            l2a.addElement(top_rated_querylist[z]);
        }

        JList<String> top_ratedlist2 = new JList<>(l2a);  
        top_ratedlist2.setBounds(100,100, 75,75);
        top_rated.removeAll();
        top_rated.revalidate();
        top_rated.add(top_ratedlist2);
        top_rated.revalidate();

        
        
        //write to statistics column. We are doing rating for now, you can look through my code and change it if you please
        DefaultListModel<String> l3a = new DefaultListModel<>(); 
        l3a.addElement("Average Rating"); //top 5 in numvotes in titles matching the search
        for (int z = 0; z < 10; z++){
            l3a.addElement(top_rated_querylist_R[z]);
        }
        JList<String> RatingsList = new JList<>(l3a);  
        data_stats.setBounds(100,100, 75,75);
        data_stats.removeAll();
        data_stats.revalidate();
        data_stats.add(RatingsList);
        data_stats.revalidate();
        
        //write to most watched, assuming that is proportional to votes
    
     
        DefaultListModel<String> l4a = new DefaultListModel<>(); 
        l4a.addElement("Most Watched"); //top 5 in numvotes in titles matching the search
        for (int z = 0; z < 10; z++){
            l4a.addElement(top_rated_querylist_V[z]);
        } 
        JList<String> ViewsList = new JList<>(l4a);  
        most_watched.setBounds(100,100, 75,75);
        most_watched.removeAll();
        most_watched.revalidate();
        most_watched.add(ViewsList);
        most_watched.revalidate();
 
        //fill in the thing
        
        
    }
    // Main Function
    public static void main(String[] args) {    
        new AnalystGUI();
        
    }    
}    
