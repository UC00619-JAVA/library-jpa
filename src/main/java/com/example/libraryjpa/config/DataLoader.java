package com.example.libraryjpa.config;

import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final BookRepository bookRepository;

  @Override
  public void run(String... args) {
    if (bookRepository.count() == 0) {
      bookRepository.saveAll(List.of(
          new Book("Clean Code", "Robert C. Martin", 2008),
          new Book("The Clean Coder", "Robert C. Martin", 2011),
          new Book("Effective Java", "Joshua Bloch", 2018),
          new Book("Head First Java", "Kathy Sierra", 2005)));
      log.info("Inserted the 4 sample books");
    }
  }
}
