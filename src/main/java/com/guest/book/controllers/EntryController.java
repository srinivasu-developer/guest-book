package com.guest.book.controllers;

import com.guest.book.entities.Entry;
import com.guest.book.repositories.EntryRepository;
import com.guest.book.utils.ImageUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

/**
 * A controller to handle web requests to manage {@link EntryController}s
 *
 * @author Srinivasu Nakka
 */
@Slf4j
@Controller
@NoArgsConstructor
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    /**
     * Creates a new {@link EntryController} using the given {@link EntryRepository}. Spring will look for a bean
     * of type {@link EntryRepository} and hand this into this class when an instance is created.
     *
     * @param entryRepository must not be {@literal null}
     */
    public EntryController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * Handles requests to access the guestbook. Obtains all currently available {@link Entry}s and puts them
     * into the {@link Model} that's used to render the view.
     *
     * @param model the model that's used to render the view
     * @return a view name
     */
    @GetMapping(path = "/entries")
    public String guestBook(Model model) {
        model.addAttribute("entries", entryRepository.findAll());
        return "entries";
    }

    /**
     * Deletes a {@link Entry}. This request can only be performed by authenticated users with
     * admin privileges. Also note how the path variable used in the {@link PutMapping} annotation is bound
     * the controller method using the {@link PathVariable} annotation.
     * If the entry couldn't be found, it will give status as NOT_FOUND
     *
     * @return a redirect string
     */
    @PostMapping(path = "/entries/delete/{id}")
    public String removeEntry(@PathVariable Long id) {
        Optional<Entry> entry = entryRepository.findById(id);
        return entry.map(it -> {
            entryRepository.delete(it);
            return "redirect:/entries";
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles requests to approve an {@link Entry}.
     *
     * @param id the ID of the entry to approve
     * @return a redirect string
     */
    @PostMapping(path = "/entries/approve/{id}")
    String approveEntry(@PathVariable Long id) {
        Optional<Entry> entry = entryRepository.findById(id);
        return entry.map(it -> {
            it.setApproved(true);
            entryRepository.save(it);
            return "redirect:/entries";
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new entry
     *
     * @param entry         entry to create
     * @param multipartFile image file
     * @return a redirect string
     * @throws IOException throws IOException
     */
    @PostMapping(path = "/new-entry")
    String createEntry(@Validated @ModelAttribute("newEntry") Entry entry,
                       @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        String fileName = null;
        if (!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        }
        entry.setImage(fileName);
        Entry entrySaved = entryRepository.save(entry);
        if (fileName != null) {
            String uploadDir = "src/resources/user-photos/" + entrySaved.getId();
            ImageUtils.saveFile(uploadDir, fileName, multipartFile);
        }
        return "redirect:/";
    }
}
