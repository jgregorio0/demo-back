package dev.jgregorio.demo.back.infrastructure.api;

public interface GenericResponseApiMapper<D, R> {

  R toResponse(D domain);
}
