package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric=new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        Book ddd=new Book();
        ddd.setTitle("Test book");
        ddd.setIsbn("123456");

        Author ericSaved=authorRepository.save(eric);
        Book dddSaved=bookRepository.save(ddd);

        Author rod=new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
        Book noEJB=new Book();
        noEJB.setTitle("J2ee withoud ejb");
        noEJB.setIsbn("456789");

        Publisher supernova= new Publisher();
        supernova.setPublisherName("Supernowa");
        supernova.setAddress("Skalna 3");
        supernova.setCity("Lipnik");
        supernova.setZip("73-110");
        supernova.setState("zach-pom");


        Author rodSaved=authorRepository.save(rod);
        Book noEJBSaved=bookRepository.save(noEJB);
        Publisher supernowaSaved=publisherRepository.save(supernova);
        dddSaved.setPublisher(supernowaSaved);

        authorRepository.save(rodSaved);
        authorRepository.save(ericSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);
        publisherRepository.save(supernowaSaved);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        System.out.println("In Bootstrap:");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());


    }
}
