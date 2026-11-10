package com.example.libraryjpa.controller;

import com.example.libraryjpa.transfer.BookRequest;
import com.example.libraryjpa.transfer.BookResponse;
import com.example.libraryjpa.transfer.PageResponse;
import com.example.libraryjpa.service.BookService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  // GET /api/books?page=0&size=2&sort=title
  // GET /api/books?author=Robert C. Martin&sort=publishedYear,desc
  @GetMapping
  public PageResponse<BookResponse> findAll(
      @RequestParam(required = false) String author,
      @PageableDefault(sort = "title") Pageable pageable) {
    return bookService.findAll(author, pageable);
  }

  @GetMapping("/available")
  public List<BookResponse> findAvailable() {
    return bookService.findAvailable();
  }

  @GetMapping("/count-by-author")
  public Map<String, Long> countByAuthor() {
    return bookService.countByAuthor();
  }

  @GetMapping("/{id}")
  public BookResponse findById(@PathVariable Long id) {
    return bookService.findById(id);
  }

  @PostMapping
  public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest request) {
    BookResponse created = bookService.create(request);
    URI location = URI.create("/api/books/" + created.id());
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
    return bookService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
