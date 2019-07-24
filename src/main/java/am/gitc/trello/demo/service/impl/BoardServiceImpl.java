package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.BoardEntity;
import am.gitc.trello.demo.repository.BoardRepository;
import am.gitc.trello.demo.service.BoardService;
import am.gitc.trello.demo.service.redis.impl.RedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final RedisImpl<BoardEntity> redis;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, RedisImpl<BoardEntity> redis) {
        this.boardRepository = boardRepository;
        this.redis = redis;
    }

    @Override
    public BoardEntity create(BoardEntity entity) {
        return null;
    }

    @Override
    public void delete(byte id) {

    }

    @Override
    public BoardEntity update(byte id, BoardEntity entity) {
        return null;
    }
}
