package com.murilonerdx.restapispring.service;

import com.murilonerdx.restapispring.dto.BookDTO;
import com.murilonerdx.restapispring.dto.PersonDTO;
import com.murilonerdx.restapispring.exceptions.ResourceNotFoundException;
import com.murilonerdx.restapispring.model.Book;
import com.murilonerdx.restapispring.model.Person;
import com.murilonerdx.restapispring.repository.BookRepository;
import com.murilonerdx.restapispring.repository.PersonRepository;
import com.murilonerdx.restapispring.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    final
    BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO create(BookDTO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
        return vo;
    }

    public List<BookDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookDTO.class);
    }

    public BookDTO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, BookDTO.class);
    }

    public BookDTO update(BookDTO book) {
        var entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
