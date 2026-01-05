package dev.jgregorio.demo.back.application.in.common.usecase;

public interface CreateUseCase<D, C> {

  D create(C toCreate);
}
