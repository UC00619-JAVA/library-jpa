package com.example.libraryjpa.service;

import com.example.libraryjpa.exception.BookNotFoundException;
import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanService {

  private final BookRepository bookRepository;

  @Transactional
  public void borrow(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
    if (!book.isAvailable()) {
      throw new IllegalStateException("Book already borrowed: " + book.getTitle());
    }
    book.setAvailable(false);
  }

  @Transactional
  public void borrowAll(List<Long> ids) {
    ids.forEach(this::borrow);
  }

  @Transactional
  public int returnAll() {
    return bookRepository.markAllAvailable();
  }
}
