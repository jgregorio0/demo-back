package dev.jgregorio.demo.back.application.in.center;

import dev.jgregorio.demo.back.application.in.common.usecase.*;
import dev.jgregorio.demo.back.domain.center.*;

public interface CenterUseCase
    extends CreateUseCase<Center, CenterCreation>,
        ReadUseCase<Center, CenterRead>,
        UpdateUseCase<Center, CenterUpdate>,
        DeleteUseCase<CenterDelete>,
        SearchUseCase<Center, CenterSearch> {}
