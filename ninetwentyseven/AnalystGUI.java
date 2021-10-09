import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AnalystGUI extends JFrame implements ActionListener{    
    static JFrame f;    
    JMenuBar mb;    
    JMenu Home, Shows, Movies, Shorts, Videos;    
    JMenuItem top_show, top_movie, movie_star, show_star, show_cus_rating, movie_cus_rating, movie_director, show_director, movie_writer, show_writer;  
    JButton team_logo, search_button;
    JTextField search_field;
    private JPanel main_panel, buff_field, most_watched, top_rated, data_stats;

    AnalystGUI(){
        /* NAVBAR SECTION*/

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
        new AnalystGUI();    
    }    
}    
