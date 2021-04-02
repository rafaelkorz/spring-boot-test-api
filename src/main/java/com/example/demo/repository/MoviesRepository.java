package com.example.demo.repository;

import com.example.demo.model.MoviesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MoviesRepository extends CrudRepository<MoviesModel, Integer> {
    @Query(value = "select  producer, \n" +
            "min(year) previousWin, \n" +
            "max(year) FollowingWin, \n" +
            "(max(year) - min(year)) intervalo\n" +
            "from movies \n" +
            "where winner = 'yes' \n" +
            "group by producer\n" +
            "having intervalo = select  (max(year) - min(year)) from movies where winner = 'yes' group by producer order by 1 desc limit 1", nativeQuery = true)
    public List<String> findProducersMoreWinners();

    @Query(value = "select  producer, \n" +
            "min(year) previousWin, \n" +
            "max(year) FollowingWin, \n" +
            "(max(year) - min(year)) intervalo\n" +
            "from movies \n" +
            "where winner = 'yes' \n" +
            "group by producer\n" +
            "having intervalo = select (max(year) - min(year)) media from movies where winner = 'yes' group by producer having media > 0 order by 1 asc limit 1", nativeQuery = true)
    public List<String> findProducersLessWinners();
}
