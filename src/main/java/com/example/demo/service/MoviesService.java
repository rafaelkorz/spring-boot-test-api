package com.example.demo.service;

import com.example.demo.model.MoviesModel;
import com.example.demo.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository repository;

    String line = "";
    public void saveMoviesData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("movieslist.csv"));
            while ((line = br.readLine())!= null) {
                String [] data = line.split(";");
                String [] producer = data[3].split("and ");
                if (producer.length > 1) {
                    for(int i = 0; i <= producer.length -1; ++i) {
                        String [] producer2 = producer[i].split(", ");
                        if (producer2.length > 1) {
                            for(int x = 0; x <= producer2.length -1; ++x) {
                                MoviesModel m = new MoviesModel();
                                m.setYear(Integer.parseInt(data[0]));
                                m.setTitle(data[1]);
                                m.setStudios(data[2]);
                                m.setProducer(producer2[x]);
                                m.setWinner(data[4]);
                                repository.save(m);
                            }
                        } else {
                            MoviesModel m = new MoviesModel();
                            m.setYear(Integer.parseInt(data[0]));
                            m.setTitle(data[1]);
                            m.setStudios(data[2]);
                            m.setProducer(producer[i]);
                            m.setWinner(data[4]);
                            repository.save(m);
                        }
                    }
                } else {
                    MoviesModel m = new MoviesModel();
                    m.setYear(Integer.parseInt(data[0]));
                    m.setTitle(data[1]);
                    m.setStudios(data[2]);
                    m.setProducer(data[3]);
                    m.setWinner(data[4]);
                    repository.save(m);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
