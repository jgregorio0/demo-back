package dev.jgregorio.demo.back.application.in.common.usecase;

public interface DeleteUseCase<D> {

  void delete(D toDelete);
}
