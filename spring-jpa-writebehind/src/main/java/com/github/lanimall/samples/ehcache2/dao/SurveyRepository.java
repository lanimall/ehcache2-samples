package com.github.lanimall.samples.ehcache2.dao;

import com.github.lanimall.samples.ehcache2.domain.Survey;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fabien.sanglier on 10/31/16.
 */
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
