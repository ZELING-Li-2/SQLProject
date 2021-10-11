import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;


public class ViewerGUI extends JFrame implements ActionListener{    
    static JFrame f;    
    JMenuBar mb;    
    JMenu Home, Shows, Movies, Shorts, Videos; 
    JMenuItem home_, show_, movie_, short_, video_;
    JButton team_logo, search_button, user_button;
    JTextField search_field, user_field;
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
        user_button = new JButton("Login");
        user_field = new JTextField("Login by inputting ID");
        user_field.setEditable(true);

         // The menu items (inside the menu list)
        f = new JFrame("ZAS VIEWER GUI");    

        home_ = new JMenuItem("Show all titles");
        show_ = new JMenuItem("Show only Shows");
        movie_ = new JMenuItem("Show only Movies");
        short_ = new JMenuItem("Show only Shorts");
        video_ = new JMenuItem("Show only Videos");


        home_.addActionListener(e->homeButton(my_Fhold));
        show_.addActionListener(e->showButton(my_Fhold));
        movie_.addActionListener(e->movieButton(my_Fhold));
        short_.addActionListener(e->shortButton(my_Fhold));
        video_.addActionListener(e->videoButton(my_Fhold));
        search_button.addActionListener(e->searchButton(my_Fhold));
        user_button.addActionListener(e->userButton(my_Fhold));
        

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
        mb.add(user_field);mb.add(user_button);
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
        l1.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l1.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l1.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l1.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update"); 
        JList<String> most_watchedlist = new JList<>(l1);  
        most_watchedlist.setBounds(100,100, 75,75);  

        most_watched.add(most_watchedlist);  // Add the list to the movie  

        // Top rated movie
        top_rated = new JPanel(); // sub-panel 2
        DefaultListModel<String> l2 = new DefaultListModel<>();  
        l2.addElement("TOP RATED"); 
        l2.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l2.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l2.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        l2.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update"); 
        JList<String> top_ratedlist = new JList<>(l2);  
        top_ratedlist.setBounds(100,100, 75,75);  

        top_rated.add(top_ratedlist);

        // Recently Watched
        recently_watched = new JPanel(); // sub-panel 3
        DefaultListModel<String> l3 = new DefaultListModel<>();  
        l3.addElement("RECENTLY WATCHED"); 
        l3.addElement("No Recently Watched Movies/Shows/Shorts/Videos.");  
        l3.addElement("Login to update");  
        //l3.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");  
        //l3.addElement("Search or Click on Home/Movies/Shows/Shorts/Videos to update");    
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
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout(50, 15));
        f.add(mb);f.add(main_panel, BorderLayout.CENTER);    
        f.setJMenuBar(mb);  
        f.setSize(1200,700);  
        setLocationRelativeTo(null);  
        f.setVisible(true);
        
        // Change Icon
        ImageIcon image = new ImageIcon("Team Icon.png");
        f.setIconImage(image.getImage());
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

    
    
    public void homeButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,10);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,10);

      DefaultListModel<String> l2h = new DefaultListModel<>(); 
      l2h.addElement("Top Rated Titles"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Rated.length; i ++) {
        l2h.addElement(top10Rated[i]);
      }
      JList<String> topRatedList = new JList<>(l2h);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3h = new DefaultListModel<>(); 
      l3h.addElement("Most Watched Titles"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Watched.length; i++) {
        l3h.addElement(top10Watched[i]);
      }
      JList<String> ViewsList = new JList<>(l3h);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched Titles"); //top 5 in numvotes in titles matching the search
      
    }

    public void movieButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles WHERE titleType LIKE 'movie' ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles WHERE titleType LIKE 'movie' ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,10);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,10);

      DefaultListModel<String> l2m = new DefaultListModel<>(); 
      l2m.addElement("Top Rated Movies"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Rated.length; i ++) {
        l2m.addElement(top10Rated[i]);
      }
      JList<String> topRatedList = new JList<>(l2m);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3m = new DefaultListModel<>(); 
      l3m.addElement("Most Watched Movies"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Watched.length; i++) {
        l3m.addElement(top10Watched[i]);
      }
      JList<String> ViewsList = new JList<>(l3m);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched Movies"); //top 5 in numvotes in titles matching the search
      
    }

    public void showButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles WHERE titleType LIKE 'tvSeries' OR titleType LIKE 'tvEpisode' OR titleType LIKE 'tvMiniSeries' ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles WHERE titleType LIKE 'tvSeries' OR titleType LIKE 'tvEpisode' OR titleType LIKE 'tvMiniSeries' ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,10);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,10);

      DefaultListModel<String> l2sw = new DefaultListModel<>(); 
      l2sw.addElement("Top Rated Shows"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Rated.length; i ++) {
        l2sw.addElement(top10Rated[i]);
      }
      JList<String> topRatedList = new JList<>(l2sw);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3sw = new DefaultListModel<>(); 
      l3sw.addElement("Most Watched Shows"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Watched.length; i++) {
        l3sw.addElement(top10Watched[i]);
      }
      JList<String> ViewsList = new JList<>(l3sw);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched Shows"); //top 5 in numvotes in titles matching the search
    }

    public void shortButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles WHERE titleType LIKE 'short' ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles WHERE titleType LIKE 'short' ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,10);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,10);

      DefaultListModel<String> l2sh = new DefaultListModel<>(); 
      l2sh.addElement("Top Rated Shorts"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Rated.length; i ++) {
        l2sh.addElement(top10Rated[i]);
      }
      JList<String> topRatedList = new JList<>(l2sh);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3sh = new DefaultListModel<>(); 
      l3sh.addElement("Most Watched Shorts"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Watched.length; i++) {
        l3sh.addElement(top10Watched[i]);
      }
      JList<String> ViewsList = new JList<>(l3sh);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched Shorts"); //top 5 in numvotes in titles matching the search
    }
    
    public void videoButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles WHERE titleType LIKE 'video' ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles WHERE titleType LIKE 'video' ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,10);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,10);

      DefaultListModel<String> l2v = new DefaultListModel<>(); 
      l2v.addElement("Top Rated Videos"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Rated.length; i ++) {
        l2v.addElement(top10Rated[i]);
      }
      JList<String> topRatedList = new JList<>(l2v);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3v = new DefaultListModel<>(); 
      l3v.addElement("Most Watched Videos"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < top10Watched.length; i++) {
        l3v.addElement(top10Watched[i]);
      }
      JList<String> ViewsList = new JList<>(l3v);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched Videos"); //top 5 in numvotes in titles matching the search
    }

    public void userButton(FHold my_Fhold) {
      String loginValue = user_field.getText();
      String searchRecent = "SELECT titleID FROM customer_ratings WHERE customerID LIKE \'%" + loginValue + "%\' ORDER BY date DESC;";
      String watchHistoryQuery = my_Fhold.call_query(searchRecent);
      String[] watchHistory = Arrays.copyOfRange(watchHistoryQuery.split("/"),0,10);

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched"); //top 5 in numvotes in titles matching the search
      for (int i = 0; i < watchHistory.length; i++) {
        String titlessearch = "SELECT originalTitle FROM titles WHERE titles.titleId LIKE \'%" + watchHistory[i] + "%\' ORDER BY Year DESC;";
        String titlesQuery = my_Fhold.call_query(titlessearch);
        String[] titlesCall = Arrays.copyOfRange(titlesQuery.split("/"),0,10);
        l4a.addElement(titlesCall[0]);
      }
      
      // Recently Watched/searched (it updates everytime the user searches for a title)
      JList<String> recentlist = new JList<>(l4a);  
      recently_watched.setBounds(100,100, 75,75);
      recently_watched.removeAll();
      recently_watched.revalidate();
      recently_watched.add(recentlist);
      recently_watched.revalidate();


      
    }

    public void searchButton(FHold my_Fhold){//u gotta pass it in because aparently this does not share the class scope
      //System.out.println("Search Button Pressed!");
      //get string from JTextField search_field;
      String searchValue = search_field.getText();
      

      
      String searchQuery = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;";
      String searchQueryYear = "SELECT Year FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;";
      //String searchQuery2 = "SELECT averageRating FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY averageRating DESC;"; 
      String searchQuery3 = "SELECT originalTitle FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY views DESC;";
      String searchQuery3Year = "SELECT Year FROM titles WHERE originalTitle LIKE \'%" + searchValue + "%\' ORDER BY views DESC;"; 


      //generate queries

      String top_rated_query = my_Fhold.call_query(searchQuery);//query the text
      String top_rated_query_year = my_Fhold.call_query(searchQueryYear);
     // String top_rated_query_R = my_Fhold.call_query(searchQuery2);//ratings
      String top_rated_query_V = my_Fhold.call_query(searchQuery3);//Views
      String top_rated_query_V_year = my_Fhold.call_query(searchQuery3Year);

      String[] top_rated_querylist = Arrays.copyOfRange(top_rated_query.split("/"),0,10); 
      String[] top_rated_querylist_year = Arrays.copyOfRange(top_rated_query_year.split("/"), 0, 10);
      //String[] top_rated_querylist_R = Arrays.copyOfRange(top_rated_query_R.split("/"),0,4);//ratings
      String[] top_rated_querylist_V = Arrays.copyOfRange(top_rated_query_V.split("/"),0,10);//votes
      String[] top_rated_querylist_V_year = Arrays.copyOfRange(top_rated_query_V_year.split("/"),0,10);//votes
      
      
      

      //write to top rated

      DefaultListModel<String> l2a = new DefaultListModel<>(); 
      l2a.addElement("TOP RATED"); //top 5 in numvotes in titles matching the search
      /*
      l2a.addElement(top_rated_querylist[0]);  
      l2a.addElement(top_rated_querylist[1]);  
      l2a.addElement(top_rated_querylist[2]);  
      l2a.addElement(top_rated_querylist[3]);  
*/
      
      for (int i = 0; i < top_rated_querylist.length; i++) {
        l2a.addElement(top_rated_querylist[i] + " (" + top_rated_querylist_year[i] + ")");
      }

      JList<String> top_ratedlist2 = new JList<>(l2a);  
      top_ratedlist2.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(top_ratedlist2);
      top_rated.revalidate();
      
      //write to most watched, assuming that is proportional to votes
  
   
      DefaultListModel<String> l3a = new DefaultListModel<>(); 
      l3a.addElement("Most Watched"); //top 5 in numvotes in titles matching the search
      /*
      l3a.addElement(top_rated_querylist_V[0]);  
      l3a.addElement(top_rated_querylist_V[1]);  
      l3a.addElement(top_rated_querylist_V[2]);  
      l3a.addElement(top_rated_querylist_V[3]);  
      */

      for (int i = 0; i < top_rated_querylist_V.length; i++) {
        l3a.addElement(top_rated_querylist_V[i] + " (" + top_rated_querylist_V_year[i] + ")");
      }

      JList<String> ViewsList = new JList<>(l3a);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      
      //fill in the thing
      
      
  }




    // Main Function
    public static void main(String[] args) {    
        new ViewerGUI();    
    }    
    
}    