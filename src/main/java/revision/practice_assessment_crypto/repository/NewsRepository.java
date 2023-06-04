package revision.practice_assessment_crypto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {
    
    @Autowired
    @Qualifier("crypto")
    private RedisTemplate<String, Object> template;

}
