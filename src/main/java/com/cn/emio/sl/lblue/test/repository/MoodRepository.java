package com.cn.emio.sl.lblue.test.repository;

import com.cn.emio.sl.lblue.test.entity.Mood;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Silence_Lurker
 */
public interface MoodRepository extends JpaRepository<Mood, String> {

}
