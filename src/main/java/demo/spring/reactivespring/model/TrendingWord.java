package demo.spring.reactivespring.model;

import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "trendingWords")
public class TrendingWord {
    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String text;

    public TrendingWord(String text) {
        this.text = text;
    }
}
