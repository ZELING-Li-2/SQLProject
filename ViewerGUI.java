import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.util.Arrays;
import java.util.Vector;

public class ViewerGUI extends JFrame implements ActionListener{    
    static JFrame f;    
    String[] searchedfor = new String[10];
    int n = 0;
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
        FHold my_Fhold = new FHold();
        
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
        search_button.addActionListener(e->searchButton(my_Fhold));

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
        /*l1.addElement("Item1");  
        l1.addElement("Item2");  
        l1.addElement("Item3");  
        l1.addElement("Item4");  */
        JList<String> most_watchedlist = new JList<>(l1);  
        most_watchedlist.setBounds(100,100, 75,75);  

        most_watched.add(most_watchedlist);  // Add the list to the movie  

        // Top rated movie
        top_rated = new JPanel(); // sub-panel 2
        DefaultListModel<String> l2 = new DefaultListModel<>();  
        l2.addElement("TOP RATED"); 
        /*l2.addElement("Item1");  
        l2.addElement("Item2");  
        l2.addElement("Item3");  
        l2.addElement("Item4"); */ 
        JList<String> top_ratedlist = new JList<>(l2);  
        top_ratedlist.setBounds(100,100, 75,75);  

        top_rated.add(top_ratedlist);

        // Recently Watched
        recently_watched = new JPanel(); // sub-panel 3
        DefaultListModel<String> l3 = new DefaultListModel<>();  
        l3.addElement("RECENTLY WATCHED"); 
       /* l3.addElement("Item1");  
        l3.addElement("Item2");  
        l3.addElement("Item3");  
        l3.addElement("Item4");  */
        JList<String> recentlist = new JList<>(l3);  
        recentlist.setBounds(100,100, 75,75);  

        recently_watched.add(recentlist);

        // Add to the main panel
        main_panel.add(most_watched);
        //main_panel.add(most_watch);
        main_panel.add(top_rated);
        //main_panel.add(top_rate);
        main_panel.add(recently_watched);
        //main_panel.add(recent_watch);
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
          //recent_watch.setText("");
          //most_watch.setText("");
          //top_rate.setText("");
          //text.setText("Button Clicked " + numClicks + " times after clicking home");
        } else if (e.getSource() == movie_) {
          //recent_watch.setText("");
          //most_watch.setText("");
          //top_rate.setText("");
          //text.setText("Button Clicked " + numClicks + " times after clicking movies");
        } else if (e.getSource() == video_) {
          //recent_watch.setText("");
          //most_watch.setText("");
          //top_rate.setText("");
          //text.setText("Button Clicked " + numClicks + " times after clicking videos");
        } else if (e.getSource() == short_) {
          //recent_watch.setText("");
          //most_watch.setText("");
          //top_rate.setText("");
          //text.setText("Button Clicked " + numClicks + " times after clicking shorts");
        } else if (e.getSource() == show_) {
          //recent_watch.setText("");
          //most_watch.setText("");
          //top_rate.setText("");
          //text.setText("Button Clicked " + numClicks + " times after clicking shows");
        }

      
    }   

    




    public void searchButton(FHold my_Fhold){//u gotta pass it in because aparently this does not share the class scope
      //System.out.println("Search Button Pressed!");
      //get string from JTextField search_field;
      String searchValue = search_field.getText();
      //Vector<String> searchfor;
      searchedfor[n] = searchValue;
      n ++;
      String searchQuery = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;"; 
      //String searchQuery2 = "SELECT averageRating FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;"; 
      String searchQuery3 = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY views DESC;"; 


      //generate queries

      String top_rated_query = my_Fhold.call_query(searchQuery);//query the text 
     // String top_rated_query_R = my_Fhold.call_query(searchQuery2);//ratings
      String top_rated_query_V = my_Fhold.call_query(searchQuery3);//Views

      String[] top_rated_querylist = Arrays.copyOfRange(top_rated_query.split("/"),0,4); 
      //String[] top_rated_querylist_R = Arrays.copyOfRange(top_rated_query_R.split("/"),0,4);//ratings
      String[] top_rated_querylist_V = Arrays.copyOfRange(top_rated_query_V.split("/"),0,4);//votes
      
      
      

      //write to top rated

      DefaultListModel<String> l2a = new DefaultListModel<>(); 
      l2a.addElement("TOP RATED"); //top 5 in numvotes in titles matching the search
      l2a.addElement(top_rated_querylist[0]);  
      l2a.addElement(top_rated_querylist[1]);  
      l2a.addElement(top_rated_querylist[2]);  
      l2a.addElement(top_rated_querylist[3]);  
      JList<String> top_ratedlist2 = new JList<>(l2a);  
      top_ratedlist2.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(top_ratedlist2);
      top_rated.revalidate();

      
      
      //write to statistics column. We are doing rating for now, you can look through my code and change it if you please
     /* DefaultListModel<String> l3a = new DefaultListModel<>(); 
      l3a.addElement("Average Ratings:"); //top 5 in numvotes in titles matching the search
      l3a.addElement(top_rated_querylist_R[0]);  
      l3a.addElement(top_rated_querylist_R[1]);  
      l3a.addElement(top_rated_querylist_R[2]);  
      l3a.addElement(top_rated_querylist_R[3]);  
      JList<String> RatingsList = new JList<>(l3a);  
      data_stats.setBounds(100,100, 75,75);
      data_stats.removeAll();
      data_stats.revalidate();
      data_stats.add(RatingsList);
      data_stats.revalidate();*/
      
      //write to most watched, assuming that is proportional to votes
  
   
      DefaultListModel<String> l3a = new DefaultListModel<>(); 
      l3a.addElement("Most Watched"); //top 5 in numvotes in titles matching the search
      l3a.addElement(top_rated_querylist_V[0]);  
      l3a.addElement(top_rated_querylist_V[1]);  
      l3a.addElement(top_rated_querylist_V[2]);  
      l3a.addElement(top_rated_querylist_V[3]);  
      JList<String> ViewsList = new JList<>(l3a);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();


      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Most Watched"); //top 5 in numvotes in titles matching the search
      l4a.addElement(searchedfor[0]);  
      l4a.addElement(searchedfor[1]);  
      l4a.addElement(searchedfor[2]);  
      l4a.addElement(searchedfor[3]);  
      JList<String> recentlist = new JList<>(l4a);  
      recently_watched.setBounds(100,100, 75,75);
      recently_watched.removeAll();
      recently_watched.revalidate();
      recently_watched.add(recentlist);
      recently_watched.revalidate();
      //fill in the thing
      
      
  }




    // Main Function
    public static void main(String[] args) {    
        new ViewerGUI();    
    }    
    
}    