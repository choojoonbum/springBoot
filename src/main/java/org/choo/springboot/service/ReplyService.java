package org.choo.springboot.service;

import org.choo.springboot.dto.ReplyDTO;
import org.choo.springboot.entity.Board;
import org.choo.springboot.entity.Reply;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    List<ReplyDTO> getList(Long bno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().bno(replyDTO.getBno()).build();
        Reply.ReplyBuilder replyBuilder = Reply.builder();
        if (replyDTO.getRno() != null) {
            replyBuilder.rno(replyDTO.getRno());
        }
        Reply reply = replyBuilder.text(replyDTO.getText()).replyer(replyDTO.getReplyer()).board(board).build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply) {
        ReplyDTO dto = ReplyDTO.builder().rno(reply.getRno()).text(reply.getText()).replyer(reply.getReplyer()).regDate(reply.getRegDate()).modDate(reply.getModDate()).build();
        return dto;
    }
}
