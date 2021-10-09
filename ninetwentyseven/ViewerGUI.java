import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/*
  TODO:
  1) Change credentials for your own team's database
  2) Change SQL command to a relevant query that retrieves a small amount of data
  3) Create a JTextArea object using the queried data
  4) Add the new object to the JPanel p
*/

public class ViewerGUI extends JFrame implements ActionListener {
  static JFrame f;    
  JMenuBar mb;    
  JMenu Home, Shows, Movies, Shorts, Videos;    
  JMenuItem Recently_watched, Most_popular, Sort_by_genre, Recently_watched2, Most_popular2, Sort_by_genre2, Recently_watched3, Most_popular3, Sort_by_genre3, Recently_watched4, Most_popular4, Sort_by_genre4, Recently_watched5, Most_popular5, Sort_by_genre5;  
  JButton team_logo, search_button;
  JTextField search_field;
  private JPanel main_panel, buff_field, most_watched, top_rated, data_stats;

  ViewerGUI(){
      /* NAVBAR SECTION*/

      // Logo and search button
      // team_logo = new JButton(new ImageIcon("Team_Logo.png"));    
      // team_logo.setBounds(10,10,10, 4); 
      search_button = new JButton("Search");
      search_field = new JTextField("Search Movies/Shows");
      search_field.setEditable(true);  

       // The menue items (inside the menue list)
      f = new JFrame("ZAS VIEWER GUI");    
      Recently_watched = new JMenuItem("Recently Watched");  
      Most_popular = new JMenuItem("Most_popular");    
      Sort_by_genre = new JMenuItem("Sort_by_genre");     

      // Add action Listeners
      Recently_watched.addActionListener(this);    
      Most_popular.addActionListener(this);    
      Sort_by_genre.addActionListener(this);    
      search_button.addActionListener(this);
      // team_logo.addActionListener(this);    

      // Menue and contents
      mb = new JMenuBar();    
      Home = new JMenu("Home");    
      Shows = new JMenu("Shows");    
      Movies = new JMenu("Movies");     
      Shorts = new JMenu("Shorts");
      Videos = new JMenu("Videos");

      // Add menue items to menue lists, and menue list to menue bar
      Recently_watched5 = new JMenuItem("Recently Watched");  
      Most_popular5 = new JMenuItem("Most_popular");    
      Sort_by_genre5 = new JMenuItem("Sort_by_genre");     

      // Add action Listeners
      Recently_watched5.addActionListener(this);    
      Most_popular5.addActionListener(this);    
      Sort_by_genre5.addActionListener(this);    

      Home.add(Recently_watched4);Home.add(Most_popular4);Home.add(Sort_by_genre4);  

      Movies.add(Recently_watched);Movies.add(Most_popular);Movies.add(Sort_by_genre); 

      Recently_watched2 = new JMenuItem("Recently Watched");  
      Most_popular2 = new JMenuItem("Most_popular");    
      Sort_by_genre2 = new JMenuItem("Sort_by_genre");     

      // Add action Listeners
      Recently_watched2.addActionListener(this);    
      Most_popular2.addActionListener(this);    
      Sort_by_genre2.addActionListener(this);    

      Shows.add(Recently_watched2);Shows.add(Most_popular2);Shows.add(Sort_by_genre2);  

      Recently_watched3 = new JMenuItem("Recently Watched");  
      Most_popular3 = new JMenuItem("Most_popular");    
      Sort_by_genre3 = new JMenuItem("Sort_by_genre");     

      // Add action Listeners
      Recently_watched3.addActionListener(this);    
      Most_popular3.addActionListener(this);    
      Sort_by_genre3.addActionListener(this);    

      Shorts.add(Recently_watched3);Shorts.add(Most_popular3);Shorts.add(Sort_by_genre3);  

      Recently_watched4 = new JMenuItem("Recently Watched");  
      Most_popular4 = new JMenuItem("Most_popular");    
      Sort_by_genre4 = new JMenuItem("Sort_by_genre");     

      // Add action Listeners
      Recently_watched4.addActionListener(this);    
      Most_popular4.addActionListener(this);    
      Sort_by_genre4.addActionListener(this);    

      Videos.add(Recently_watched4);Videos.add(Most_popular4);Videos.add(Sort_by_genre4);  
      
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
      main_panel.add(new JLabel("Main Panel"));
      main_panel.setBackground(Color.white);
      main_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

      most_watched = new JPanel(); // sub-panel 1
      most_watched.add(new JLabel("Most Watched"));

      top_rated = new JPanel(); // sub-panel 2
      top_rated.add(new JLabel("Top Rated"));

      data_stats = new JPanel(); // sub-panel 3
      data_stats.add(new JLabel("Statistics"));

      // Add to the main panel
      main_panel.add(most_watched);
      main_panel.add(top_rated);
      main_panel.add(data_stats);

      // Adding everything together
      f.add(mb);f.add(main_panel);    
      f.setJMenuBar(mb);  
      f.setLayout(null);    
      f.setSize(1200,700);  
      setLocationRelativeTo(null);  
      f.setVisible(true);    
  }
  // Actions when events is detected     
  public void actionPerformed(ActionEvent e) {    
      
  }   
  // Main Function
  public static void main(String[] args) {    
      new ViewerGUI();    
  }    
}    