package dev.jgregorio.demo.back.application.in.common.usecase;

public interface UpdateUseCase<D, U> {

  D update(U toUpdate);
}
