package dev.jgregorio.demo.back.application.in.common.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchUseCase<D, S> {

  Page<D> search(S toSearch, Pageable pageable);
}
