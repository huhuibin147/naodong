package com.nd.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.ConcernAnswer;

@Transactional
@Repository
public class ConcernAnswerDao extends BaseDao<ConcernAnswer>{

}
