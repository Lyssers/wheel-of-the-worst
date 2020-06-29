package com.lyssers.wheelofmisfortune;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable
  {

    //Put your API Key here
    //Using mine for now...
    final String apiKey = "c360b84a";

    //Movie List
    List<String> movieList;

    //FXML GUI Field Declarations
    @FXML
    private Label movieTitle;
    @FXML
    private Label movieRating;
    @FXML
    private Label movieYear;
    @FXML
    private Label imdbURL;
    @FXML
    private Label movieSynopsis;
    @FXML
    private ImageView posterShow;
    @FXML
    private AnchorPane wheel;
    @FXML
    private Pane movieInfo;

    //Animations
    private FadeTransition fade;
    private RotateTransition rotate;

    //Spin Button Handler Code
    @FXML
    private void handleButtonAction(ActionEvent event)
      {
        //Pick Random Movie
        String movieName = movieList.get(ThreadLocalRandom.current().nextInt(1, movieList.size() + 1));

        //Get movie info from IMDB and store in an instance of the movie class
        Movie mv = ApiActionCall(movieName);

        //Display movie details after the spinning animation is finished
        rotate.setOnFinished((ActionEvent event1) -> {

            //If we got a result
            if (mv != null) {
                
                //Use getters of the class instance
                movieTitle.setText(mv.getTitle());
                movieRating.setText(mv.getImdbRating());
                movieYear.setText(mv.getYear());
                imdbURL.setText("https://www.imdb.com/title/" + mv.getImdbID());
                movieSynopsis.setText(mv.getPlot());
                posterShow.setImage(new Image(mv.getPoster()));
            } //If we did not get a result, just display the title
            else {
                movieTitle.setText(movieName);
            }

            //Make the movie info visible on first spin
            if (movieInfo.getOpacity() == 0.0) {
                fade.play();
            }
        });

        //Play the rotate animation.
        rotate.play();
      }

    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        //Hide the movie information container
        movieInfo.setOpacity(0.0);

        //Instantiate and set properties for animations:
        fade = new FadeTransition(Duration.millis(1000), movieInfo);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(3600);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.millis(10000));
        rotate.setAutoReverse(false);
        rotate.setRate(4.0);
        rotate.setNode(wheel);

        //Read in MovieList
        movieList = new BufferedReader(new InputStreamReader(MainClass.class.getResourceAsStream("/list.txt"),
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
      }

    public Movie ApiActionCall(String movieTitle)
      {
        //Empty Instance of Movie class
        Movie mv = null;

        //Construct URL...
        String omdbURL = "http://www.omdbapi.com/?t=" + movieTitle.replaceAll(" ", "%20") + "&plot=full" + "&apikey=" + apiKey + "&";

        //Map json to class
        try {
            ObjectMapper mapper = new ObjectMapper();
            mv = mapper.readValue(new URL(omdbURL), Movie.class);
        } catch (IOException ex) {
            //Log Error responses
            String message = MessageFormat.format("Movie Error: {0} \n URL: {1}", movieTitle, omdbURL);
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, message, ex);
        }

        //Return movie class
        return mv;
      }

  }
