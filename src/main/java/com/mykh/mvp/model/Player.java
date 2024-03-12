package com.mykh.mvp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Player {

    protected final String name;

    @EqualsAndHashCode.Include
    protected final String nickname;

}
