package com.silence.elasticsearch;

import com.silence.DO.DiscussPostDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPostDO, Integer> {
}
