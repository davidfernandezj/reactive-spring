package demo.spring.reactivespring.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetDto {
    private String id;
    private String text;
    private Instant createdAt;

}
