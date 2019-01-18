package demo.spring.reactivespring.config;

import static com.googlecode.jmapper.api.JMapperAPI.global;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import demo.spring.reactivespring.dto.TweetDto;
import demo.spring.reactivespring.model.Tweet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {

    @Bean
    public JMapper<TweetDto, Tweet> tweetMapper() {
        JMapperAPI jMapperApi = new JMapperAPI().add(mappedClass(TweetDto.class).add(global()));
        return new JMapper<>(TweetDto.class, Tweet.class, jMapperApi);
    }


}
