package dev.jgregorio.demo.back.application.in.common.usecase;

public interface ReadUseCase<D, R> {

  D read(R toRead);
}
