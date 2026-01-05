package dev.jgregorio.demo.back.application;

import java.util.Optional;

public interface GenericPersistence<D, I> {

  D create(D domain);

  Optional<D> read(I id);

  D update(D domain);

  void delete(I id);
}
