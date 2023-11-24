package org.choo.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.choo.springboot.dto.GuestbookDTO;
import org.choo.springboot.dto.PageRequestDTO;
import org.choo.springboot.dto.PageResultDTO;
import org.choo.springboot.entity.Guestbook;
import org.choo.springboot.repository.GuestBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {
    private final GuestBookRepository repository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO.................");
        log.info(dto);
        Guestbook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = repository.findAll(pageable);
        //Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, this::entityToDto);
    }


}
