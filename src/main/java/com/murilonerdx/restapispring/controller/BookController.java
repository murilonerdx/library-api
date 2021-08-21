package com.murilonerdx.restapispring.controller;

import com.murilonerdx.restapispring.dto.BookDTO;
import com.murilonerdx.restapispring.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "BookEndpoint")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService service;

    @ApiOperation(value = "Find all books")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookDTO> findAll() {
        List<BookDTO> books = service.findAll();
        books
                .forEach(p -> p.add(
                                linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()
                        )
                );
        return books;
    }

    @ApiOperation(value = "Find a specific book by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO findById(@PathVariable("id") Long id) {
        BookDTO bookDTO = service.findById(id);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO create(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.create(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getId())).withSelfRel());
        return bookDTO;
    }

    @ApiOperation(value = "Update a specific book")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.update(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getId())).withSelfRel());
        return bookDTO;
    }

    @ApiOperation(value = "Delete a specific book by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
