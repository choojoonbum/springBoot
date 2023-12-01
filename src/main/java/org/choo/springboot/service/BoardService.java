package org.choo.springboot.service;

import org.choo.springboot.dto.BoardDTO;
import org.choo.springboot.dto.PageRequestDTO;
import org.choo.springboot.dto.PageResultDTO;
import org.choo.springboot.entity.Board;
import org.choo.springboot.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    BoardDTO get(Long bno);
    void removeWithReplies(Long bno);
    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        return Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent()).writer(member).build();
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
        return BoardDTO.builder().bno(board.getBno()).title(board.getTitle()).content(board.getContent())
                .regDate(board.getRegDate()).modDate(board.getModDate()).writerEmail(member.getEmail()).writerName(member.getName())
                .replyCount(replyCount.intValue()).build();
    }
}
