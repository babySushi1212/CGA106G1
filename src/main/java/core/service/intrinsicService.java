package core.service;

import java.util.List;

public interface intrinsicService<D> {
    List<D> getAllDTO();
    void insertAllDTO(List<D> dto);
}
