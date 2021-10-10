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
    Vector<String> searchedVec = new Vector<String> (0);
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


        home_.addActionListener(e->homeButton(my_Fhold));
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
        l3.addElement("Search to update");  
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

    

    public void homeButton(FHold my_FHold) {
      String searchTop10Watch = "SELECT originalTitle FROM titles ORDER BY Views DESC LIMIT 10;";
      String searchTop10Rated = "SELECT originalTitle FROM titles ORDER BY averageRating DESC LIMIT 10;";
      
      String query = my_FHold.call_query(searchTop10Watch);
      String query2 = my_FHold.call_query(searchTop10Rated);
      
      String[] top10Watched = Arrays.copyOfRange(query.split("/"),0,4);
      String[] top10Rated = Arrays.copyOfRange(query.split("/"),0,4);

      DefaultListModel<String> l2h = new DefaultListModel<>(); 
      l2h.addElement("TOP RATED"); //top 5 in numvotes in titles matching the search
      l2h.addElement(top10Rated[0]);  
      l2h.addElement(top10Rated[1]);  
      l2h.addElement(top10Rated[2]);  
      l2h.addElement(top10Rated[3]);  
      JList<String> topRatedList = new JList<>(l2h);  
      topRatedList.setBounds(100,100, 75,75);
      top_rated.removeAll();
      top_rated.revalidate();
      top_rated.add(topRatedList);
      top_rated.revalidate();

      DefaultListModel<String> l3h = new DefaultListModel<>(); 
      l3h.addElement("Most Watched"); //top 5 in numvotes in titles matching the search
      l3h.addElement(top10Watched[0]);  
      l3h.addElement(top10Watched[1]);  
      l3h.addElement(top10Watched[2]);  
      l3h.addElement(top10Watched[3]);  
      JList<String> ViewsList = new JList<>(l3h);  
      most_watched.setBounds(100,100, 75,75);
      most_watched.removeAll();
      most_watched.revalidate();
      most_watched.add(ViewsList);
      most_watched.revalidate();

      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched"); //top 5 in numvotes in titles matching the search
      l4a.addElement(searchedVec.get(0));  
      if (searchedVec.size() > 1) {
        l4a.addElement(searchedVec.get(1));
      }
      if (searchedVec.size() > 2) {
        l4a.addElement(searchedVec.get(2));
      }
      if (searchedVec.size() > 3) {
        l4a.addElement(searchedVec.get(3));
      }
      if (searchedVec.size() > 4) {
        l4a.addElement(searchedVec.get(4));
      }
      if (searchedVec.size() > 5) {
        l4a.addElement(searchedVec.get(5));
      }
      if (searchedVec.size() > 6) {
        l4a.addElement(searchedVec.get(6));
      }
      if (searchedVec.size() > 7) {
        l4a.addElement(searchedVec.get(7));
      } 
      if (searchedVec.size() > 8) {
        l4a.addElement(searchedVec.get(9));
      }
    }

    public void addToWatchHistory(String title) {
      if (searchedVec.size() > 0) {
        Collections.reverse(searchedVec);
      }
      
      searchedVec.add(title);
      if (searchedVec.size() == 9) {
        searchedVec.remove(0);
      }
      Collections.reverse(searchedVec);
    }

    public void searchButton(FHold my_Fhold){//u gotta pass it in because aparently this does not share the class scope
      //System.out.println("Search Button Pressed!");
      //get string from JTextField search_field;
      String searchValue = search_field.getText();
      
      //adds to history
      addToWatchHistory(searchValue);

      
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

      // Recently Watched/searched (it updates everytime the user searches for a title)
      DefaultListModel<String> l4a = new DefaultListModel<>(); 
      l4a.addElement("Recently Watched"); //top 5 in numvotes in titles matching the search
      l4a.addElement(searchedVec.get(0));  
      if (searchedVec.size() > 1) {
        l4a.addElement(searchedVec.get(1));
      }
      if (searchedVec.size() > 2) {
        l4a.addElement(searchedVec.get(2));
      }
      if (searchedVec.size() > 3) {
        l4a.addElement(searchedVec.get(3));
      }
      if (searchedVec.size() > 4) {
        l4a.addElement(searchedVec.get(4));
      }
      if (searchedVec.size() > 5) {
        l4a.addElement(searchedVec.get(5));
      }
      if (searchedVec.size() > 6) {
        l4a.addElement(searchedVec.get(6));
      }
      if (searchedVec.size() > 7) {
        l4a.addElement(searchedVec.get(7));
      } 
      if (searchedVec.size() > 8) {
        l4a.addElement(searchedVec.get(9));
      }
        
        
        
      //l4a.addElement(rand[4]); 
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