package com.mycompany.wheelofmisfortune;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.animation.RotateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    final String apiKey = "c360b84a";

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
    private void handleButtonAction(ActionEvent event) {
        List<String> movieList = new BufferedReader(new InputStreamReader(MainClass.class.getResourceAsStream("/list.txt"),
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        String movieName = movieList.get((int) (Math.random() * 126));
        Movie mv = ApiActionCall(movieName);
        //movieTitle.setVisible(true);  
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(3600);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.millis(10000));
        rotate.setAutoReverse(false);
        rotate.setRate(4.0);
        rotate.setNode(wheel);
        rotate.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event1) {
                if (mv != null) {
                    movieTitle.setText(mv.getTitle());
                    movieRating.setText(mv.getImdbRating());
                    movieYear.setText(mv.getYear());
                    imdbURL.setText("https://www.imdb.com/title/" + mv.getImdbID());
                    movieSynopsis.setText(mv.getPlot());
                    posterShow.setImage(new Image(mv.getPoster()));
                } else {
                    movieTitle.setText(movieName);
                }
            }
        });
        rotate.play();
        System.out.println(rotate.getCurrentRate());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Movie ApiActionCall(String movieTitle) {
        Movie mv = null;
        String omdbURL = "http://www.omdbapi.com/?t=" + movieTitle.replaceAll(" ", "%20") + "&plot=full&apikey=c360b84a" + "&apikey=" + apiKey + "&";
        System.out.println(omdbURL);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mv = mapper.readValue(new URL(omdbURL), Movie.class);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }
}
