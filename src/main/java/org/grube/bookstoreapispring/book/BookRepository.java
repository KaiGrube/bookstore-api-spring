package org.grube.bookstoreapispring.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findBooksByTitleContainingIgnoreCase(String filter, Pageable pageable);

//    @Query(value = "SELECT * FROM book b WHERE b.title LIKE '%Java%'", nativeQuery = true)
//    List<Book> MyFindBooksByTitleContaining(String filter);

//    @Query(value = "SELECT count(*) FROM book", nativeQuery = true)
//    int countAllBooks();


}