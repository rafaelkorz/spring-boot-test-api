package com.example.movies.controller;

import com.example.movies.model.JSONModel;
import com.example.movies.model.MoviesModel;
import com.example.movies.model.WinnerModel;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MoviesController {
    @Autowired
    private MoviesRepository repository;

    @Autowired
    private MoviesService ms;

    @RequestMapping(path = "/load")
    public void setDataCSVDB() {
        ms.saveMoviesData();
    }

    @GetMapping(path = "/api")
    public ResponseEntity find() {
        List<WinnerModel> listMore = new ArrayList<WinnerModel>();
        List<WinnerModel> listLess = new ArrayList<WinnerModel>();
        JSONModel mm = new JSONModel();

        List<String> producesMore = repository.findProducersMoreWinners();
        for(int i = 0; i <= producesMore.size() -1; ++i) {
             String [] producers = producesMore.get(i).split(",");
             WinnerModel wm = new WinnerModel();
             wm.setProducer(producers[0]);
             wm.setInterval(producers[1]);
             wm.setPreviouswin(producers[2]);
             wm.setFollowingwin(producers[3]);
            listMore.add(wm);
        }

        mm.setMax(listMore);

        List<String> producesLess = repository.findProducersLessWinners();
        for(int i = 0; i <= producesLess.size() -1; ++i) {
            String [] producers = producesLess.get(i).split(",");
            WinnerModel wm = new WinnerModel();
            wm.setProducer(producers[0]);
            wm.setInterval(producers[1]);
            wm.setPreviouswin(producers[2]);
            wm.setFollowingwin(producers[3]);
            listLess.add(wm);
        }
        mm.setMin(listLess);

        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }

    @PostMapping(path = "/save")
    public MoviesModel save(@RequestBody MoviesModel movie) {
        return repository.save(movie);
    }

    @PutMapping(path = "/")
    public MoviesModel update(@RequestBody MoviesModel movie) {
            return repository.save(movie);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity search(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}
