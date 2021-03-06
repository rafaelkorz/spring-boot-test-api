package com.example.movies.repository;

import com.example.movies.model.MoviesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<MoviesModel, Integer> {
    @Query(value = "select  producer, \n" +
            "(max(year) - min(year)) intervalo,\n" +
            "min(year) previousWin, \n" +
            "max(year) FollowingWin \n" +
            "from movies \n" +
            "where winner = 'yes' \n" +
            "group by producer\n" +
            "having intervalo = select  (max(year) - min(year)) from movies where winner = 'yes' group by producer order by 1 desc limit 1", nativeQuery = true)
    public List<String> findProducersMoreWinners();

    @Query(value = "select  producer, \n" +
            "(max(year) - min(year)) intervalo, \n" +
            "min(year) previousWin, \n" +
            "max(year) FollowingWin \n" +
            "from movies \n" +
            "where winner = 'yes' \n" +
            "group by producer\n" +
            "having intervalo = select (max(year) - min(year)) media from movies where winner = 'yes' group by producer having media > 0 order by 1 asc limit 1", nativeQuery = true)
    public List<String> findProducersLessWinners();
}
