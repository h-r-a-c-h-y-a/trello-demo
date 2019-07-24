package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.BoardEntity;

public interface BoardService {

    BoardEntity create(BoardEntity entity);
    void delete(byte id);
    BoardEntity update(BoardEntity entity);
}
