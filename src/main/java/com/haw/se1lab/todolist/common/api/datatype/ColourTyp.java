package com.haw.se1lab.todolist.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public enum ColourTyp {
    RED, BLUE, BLACK, GREEN, YELLOW, PURPLE //ENUM COLOUR
}
