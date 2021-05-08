/*
 * Copyright 2016-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.guest.book;

import com.guest.book.controllers.EntryController;
import com.guest.book.entities.Entry;
import com.guest.book.repositories.EntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link EntryController}.
 */
@ExtendWith(MockitoExtension.class)
class GuestbookControllerUnitTests {

    @Mock
    EntryRepository guestbook;

    @Test
    void populatesModelForGuestbook() {
        Entry entry = new Entry("Hello", "Test");
        doReturn(List.of(entry)).when(guestbook).findAll();
        Model model = new ExtendedModelMap();
        EntryController controller = new EntryController(guestbook);
        String viewName = controller.guestBook(model);
        assertThat(viewName).isEqualTo("entries");
        assertThat(model.asMap().get("entries")).isInstanceOf(Iterable.class);
        verify(guestbook, times(1)).findAll();
    }
}
