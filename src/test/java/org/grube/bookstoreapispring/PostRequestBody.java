package org.grube.bookstoreapispring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRequestBody {
    private String title = "test_title";
    private String subtitle = "test_subtitle";
    private String isbn13 = "1234567890128";
    private double price = 42.42;
    private String image = "https://picsum.photos/200/300/?blur";
    private String url = "http://www.someUrl.org";
}
