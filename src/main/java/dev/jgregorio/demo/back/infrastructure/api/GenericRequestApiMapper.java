package dev.jgregorio.demo.back.infrastructure.api;

public interface GenericRequestApiMapper<R, D> {

  D fromRequest(R request);
}
