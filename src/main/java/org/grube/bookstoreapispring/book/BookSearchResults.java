package org.grube.bookstoreapispring.book;

import java.util.List;

public record BookSearchResults(
        String titleFilter,
        int pageLimit,
        long totalNumberOfResults,
        int numberOfPages,
        int currentPage,
        List<Book> results) {
}
