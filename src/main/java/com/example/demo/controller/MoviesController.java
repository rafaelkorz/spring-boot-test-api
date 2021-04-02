package com.example.demo.controller;

import com.example.demo.model.JSONModel;
import com.example.demo.model.MoviesModel;
import com.example.demo.model.WinnerModel;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoviesController {
    List<String> filmes;

    @Autowired
    private MoviesRepository repository;

    @Autowired
    private MoviesService ms;

    @RequestMapping(path = "/movies/load")
    public void setDataCSVDB() {
        ms.saveMoviesData();
    }

    @GetMapping(path = "/movies/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping(path = "/movies")
//    public ResponseEntity consultar() {
//        Iterable<MoviesModel> movies = repository.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(movies);
//    }

    @GetMapping(path = "/movies")
    public ResponseEntity consultar() {
        List<WinnerModel> listMore = new ArrayList<WinnerModel>();
        List<WinnerModel> listLess = new ArrayList<WinnerModel>();
        JSONModel mm = new JSONModel();

        List<String> producesMore = repository.findProducersMoreWinners();
        for(int i = 0; i <= producesMore.size() -1; ++i) {
             String [] producers = producesMore.get(i).split(",");
             WinnerModel wm = new WinnerModel();
             wm.setProducer(producers[0]);
             wm.setPreviouswin(producers[1]);
             wm.setFollowingwin(producers[2]);
             wm.setInterval(producers[3]);
            listMore.add(wm);
        }

        mm.setMax(listMore);

        List<String> producesLess = repository.findProducersLessWinners();
        for(int i = 0; i <= producesLess.size() -1; ++i) {
            String [] producers = producesLess.get(i).split(",");
            WinnerModel wm = new WinnerModel();
            wm.setProducer(producers[0]);
            wm.setPreviouswin(producers[1]);
            wm.setFollowingwin(producers[2]);
            wm.setInterval(producers[3]);
            listLess.add(wm);
        }
        mm.setMin(listLess);

        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }

    @PostMapping(path = "/movies/save")
    public MoviesModel salvar(@RequestBody MoviesModel movie) {
        return repository.save(movie);
    }
}
